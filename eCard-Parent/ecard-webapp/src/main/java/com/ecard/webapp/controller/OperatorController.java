/*
 * OperatorController
 */
package com.ecard.webapp.controller;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.context.Context;

import com.ecard.core.model.GroupCompanyInfo;
import com.ecard.core.model.Roles;
import com.ecard.core.model.TeamInfo;
import com.ecard.core.model.UserInfo;
import com.ecard.core.service.AdminPossessionCardService;
import com.ecard.core.service.CardInfoService;
import com.ecard.core.service.EmailService;
import com.ecard.core.service.GroupCompanyInfoService;
import com.ecard.core.service.MasterService;
import com.ecard.core.service.TeamInfoService;
import com.ecard.core.service.UserInfoService;
import com.ecard.core.vo.CardInfo;
import com.ecard.core.vo.GroupDepartmentVO;
import com.ecard.core.vo.UserInfoVo;
import com.ecard.webapp.constant.CommonConstants;
import com.ecard.webapp.security.EcardUser;
import com.ecard.webapp.security.RoleType;
import com.ecard.webapp.util.StringUtilsHelper;
import com.ecard.webapp.vo.DataPagingJsonVO;
import com.ecard.webapp.vo.ObjectCardNumber;
import com.ecard.webapp.vo.ObjectTeamVO;
import com.ecard.webapp.vo.OperatorEditVO;
import com.ecard.webapp.vo.UpdateCardUser;
import com.ecard.webapp.vo.UserInfoResultVO;

@Controller
@RequestMapping("/operators/*")
public class OperatorController {
	@Autowired
	UserInfoService userInfoService;
	
	@Autowired
	MasterService masterService;
	
	@Autowired
	TeamInfoService teamInfoService;
	
	@Autowired
	GroupCompanyInfoService groupCompanyInfoService;
	
	@Autowired
    EmailService emailService;
	
	@Autowired
	CardInfoService cardInfoService;
	
	@Autowired
	AdminPossessionCardService adminPossessionCardService;
	
	@Value("${mail.server.from}")
	private String fromUser;
	
	@RequestMapping("list")
	public ModelAndView list() {
		return new ModelAndView("operatorlist");
	} 
	
	@RequestMapping(value = "search", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public DataPagingJsonVO<UserInfoResultVO> search(HttpServletRequest request) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();	
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
		Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		DataPagingJsonVO<UserInfoResultVO> dataTableResponse = new DataPagingJsonVO<UserInfoResultVO>();
		List<UserInfoResultVO> userInfoResultVOs = new ArrayList<UserInfoResultVO>();
		String criteriaSearch = request.getParameter("criteriaSearch");
		String textSearch = criteriaSearch.trim().replaceAll(" +", "|");
		int limit = parseIntParameter(request.getParameter("length"), 0);
		int offset = parseIntParameter(request.getParameter("start"), 0);
		List<UserInfoVo> userInfos = null ;
		BigInteger count = null ;
		if(roles.contains(RoleType.ROLE_ADMIN.name())){
			 userInfos = userInfoService.searchUserForList(textSearch, limit, offset);
			 count = userInfoService.countUserForList(textSearch);
		}else{
			 userInfos = userInfoService.searchUserOfMyCompanyForList(criteriaSearch, limit, offset, ecardUser.getGroupCompanyId());
			 count = userInfoService.countUserForList(criteriaSearch, ecardUser.getGroupCompanyId());
		}
		
		long totalRecord = count.longValue();
		for (UserInfoVo info : userInfos) {
			UserInfoResultVO userInfoResultVO = new UserInfoResultVO(info.getUserId(), info.getName(), info.getCompanyName(), info.getPositionName(), info.getEmail(), info.getMobileNumber(), info.getCreateDate().toString() ,info.getFirstName(),info.getLastName(),info.getFirstNameKana(),info.getLastNameKana(),info.getDepartmentName(),info.getUserIndexNo());
			userInfoResultVOs.add(userInfoResultVO);
		}
		dataTableResponse.setDraw(parseIntParameter(request.getParameter("draw"), 0));
		dataTableResponse.setRecordsTotal(totalRecord);
		dataTableResponse.setRecordsFiltered(totalRecord);
		dataTableResponse.setData(userInfoResultVOs);
	
		return dataTableResponse;
	}
	
    @RequestMapping(value = "edit/{id:[\\d]+}", method= RequestMethod.GET)
    public ModelAndView editView(@PathVariable("id") Integer id,HttpSession session) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
		session.setAttribute("gourpCompanyId", ecardUser.getGroupCompanyId());
		Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		OperatorEditVO operatorEdit = new OperatorEditVO();

		UserInfo operatorEditUser = userInfoService.getUserInfoByUserId(id);

		// bach.le set userinfor + company + department
		List<GroupCompanyInfo> groupCompany = null;
		if (roles.contains(RoleType.ROLE_AUTHORITY_USER.name())) {
			groupCompany = groupCompanyInfoService.getListCompanyOfUser(operatorEditUser.getGroupCompanyId());
		} else {
			groupCompany = groupCompanyInfoService.getListCompany();
		}
		Map<Integer, String> mapGroupCompany = new LinkedHashMap<Integer, String>();
		mapGroupCompany.put(0, "未選択");
		for (GroupCompanyInfo item : groupCompany) {
			mapGroupCompany.put(item.getGroupCompanyId(), item.getGroupCompanyName());
		}
		Map<Integer, String> mapGroupCompanyDepartment = new LinkedHashMap<Integer, String>();
		mapGroupCompanyDepartment.put(0, "テスト部");
		List<GroupDepartmentVO> departments = groupCompanyInfoService.getCompanyDepartmentById(operatorEditUser.getGroupCompanyId());
		for (GroupDepartmentVO item : departments) {
			int departmentId = 1 + (int) (Math.random() * 1000);
			mapGroupCompanyDepartment.put(departmentId, item.getGroupCompanyDepartmentName());
		}

		operatorEdit.setUser(operatorEditUser);
		operatorEdit.setCompany(mapGroupCompany);
		operatorEdit.setDepartment(mapGroupCompanyDepartment);
		return new ModelAndView("operatoredit", "operatoredit", operatorEdit);
    	
    }
    
    @RequestMapping(value = "saveEdit", method = RequestMethod.POST)
    public ModelAndView edit(HttpServletRequest request, UserInfo userInfo){
		userInfo.setUpdateDate(new Date());
		String roleId = request.getParameter("roleId");
		Roles role = new Roles();
		if (StringUtils.isNotEmpty(roleId)) {
			role.setRoleId(Integer.parseInt(roleId));
			userInfo.setRoles(role);
		}
		UserInfo uTemp = userInfoService.getUserInfoByUserId(userInfo.getUserId());
		String passDB = uTemp.getPassword();
		// if(!passDB.equals(userInfo.getPassword())){
		// String encodePass = (new BCryptPasswordEncoder()).encode(userInfo.getPassword());
		// userInfo.setPassword(encodePass);
		// }
		userInfo.setPassword(passDB);
		userInfo.setCompanyNameKana(userInfo.getCompanyName());
		userInfo.setName(StringUtilsHelper.mergerStringEitherAWord(userInfo.getLastName(), userInfo.getFirstName(), " "));
		userInfo.setNameKana(StringUtilsHelper.mergerStringEitherAWord(userInfo.getLastNameKana(), userInfo.getFirstNameKana(), " "));
		userInfo.setUserIndexNo(uTemp.getUserIndexNo());
		int result = userInfoService.updateProfileAdmin(userInfo);
		if (result == 1)
			return new ModelAndView("redirect:list");
		return new ModelAndView("operatoredit", "operatoredit", userInfo);
    	
    	
    }
	
	@RequestMapping(value = "register", method = RequestMethod.GET)  
    public ModelAndView registerLoadView(HttpSession session) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
		session.setAttribute("gourpCompanyId", ecardUser.getGroupCompanyId());
		Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());	

		List<Roles> listRoles = masterService.getAllRoles();
		Map<Integer,String> mapRoles = new LinkedHashMap<Integer,String>();
		for(Roles item : listRoles) {
			mapRoles.put(item.getRoleId(), item.getRoleName());
		}
		@SuppressWarnings("rawtypes")
		Map<String, Map> data = new LinkedHashMap<String,Map>();
		data.put("listRoles", mapRoles);
		//bach.le set userinfor + company + department
		List<GroupCompanyInfo> groupCompany = null;
		if(roles.contains(RoleType.ROLE_AUTHORITY_USER.name())){
			groupCompany = groupCompanyInfoService.getListCompanyOfUser(ecardUser.getGroupCompanyId());
    	  
		}else{
			groupCompany = groupCompanyInfoService.getListCompany();
		}
		Map<Integer,String> mapGroupCompany = new LinkedHashMap<Integer,String>();
		mapGroupCompany.put(0, "未選択");
		for(GroupCompanyInfo item : groupCompany) {
			mapGroupCompany.put(item.getGroupCompanyId(), item.getGroupCompanyName());
		}
		
		Map<Integer,String> mapGroupCompanyDepartment = new LinkedHashMap<Integer,String>();
		mapGroupCompanyDepartment.put(0, "テスト部");
		
		data.put("listCompany", mapGroupCompany);
		data.put("listDepartment", mapGroupCompanyDepartment);
		
        return new ModelAndView("operatorregister", "operatorregisterData", data);  
    }
	
	@RequestMapping(value = "register", method = RequestMethod.POST)  
    public ModelAndView registerSave(UserInfo userInfo) {
		String email = userInfo.getEmail();
		//String pass = userInfo.getPassword();
		if (userInfoService.findUserByEmail(email).getEmail() != null) {
			return new ModelAndView("redirect:register");
		}
		//String encodePass = (new BCryptPasswordEncoder()).encode(userInfo.getPassword());
		userInfo.setCompanyNameKana(userInfo.getCompanyName());
		//userInfo.setPassword(encodePass);
		userInfo.setCreateDate(new Date());
		userInfo.setUpdateDate(new Date());
		userInfo.setOperaterId(1);
		userInfo.setDeleteFlg(0);
		userInfo.setMailSendFlg(1);
		userInfo.setMailNoticeFlg(1);
		userInfo.setMailUseAssistFlg(1);
		userInfo.setMailNewsFlg(1);
		userInfo.setLastChangePasswordDate(new Date());
		userInfo.setName(StringUtilsHelper.mergerStringEitherAWord(userInfo.getLastName(), userInfo.getFirstName(), " "));
		userInfo.setNameKana(StringUtilsHelper.mergerStringEitherAWord(userInfo.getLastNameKana(), userInfo.getFirstNameKana(), " "));
		
		//https://livepass.backlog.jp/view/MEISHI-765
		String password = new BigInteger(130, new SecureRandom()).toString(32);
		String encodePass = (new BCryptPasswordEncoder()).encode(password);
		userInfo.setPassword(encodePass);
		int result = userInfoService.registerProfileAdmin(userInfo);
		if (result > 0) {
			Context ctx = new Context();
			ctx.setVariable("password", password);
			ctx.setVariable("recipientEmail", email);
			try {
				emailService.sendMail(CommonConstants.USER_FROM_MAIL, email,CommonConstants.TITLE_RECOVERPASS_MAIL, ctx, "newpassword");
			} catch (MessagingException e) {
				e.printStackTrace();
			}

			return new ModelAndView("redirect:list");
		}
		return new ModelAndView("redirect:list");  
    }

	@RequestMapping("delete")
	@ResponseBody
	public int delete(@RequestParam(value = "userId") int userId) {
		try {
			userInfoService.deleteUser(userId);
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
		return 0;
	}
	
	@RequestMapping(value = "confirm/{id:[\\d]+}", method = RequestMethod.GET)
	public ModelAndView confirm(@PathVariable("id") int id) {
		UserInfo operatorConfirm = null;
		operatorConfirm = userInfoService.getUserInfoByUserId(id);
		if (operatorConfirm.getTeamInfo() != null) {
			int teamId = operatorConfirm.getTeamInfo().getTeamId();
			TeamInfo teamInfo = teamInfoService.getTeamInfoById(teamId);
			operatorConfirm.setTeamInfo(teamInfo);
		}
		if (operatorConfirm.getRoles() != null) {
			Roles role = userInfoService.findRoleByUserId(operatorConfirm.getUserId());
			operatorConfirm.setRoles(role);
		}
		return new ModelAndView("operatorconfirm", "operatorConfirm", operatorConfirm);
	}
	
	@RequestMapping("userLeave")
	@ResponseBody
	public int userLeave(@RequestParam(value = "userId") int userId) {
		try {
			userInfoService.userLeave(userId);
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
		return 0;
	}
	
	private int parseIntParameter(String parameter, int defaultValue) {
		try {
			return Integer.parseInt(parameter);
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}
	
	@RequestMapping("getDepartment")
	@ResponseBody
	public List<GroupDepartmentVO> getDepartment(@RequestParam(value = "departmentId") int departmentId) {
		List<GroupDepartmentVO> department=null;
		try {
			department=groupCompanyInfoService.getCompanyDepartmentById(departmentId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return department;
	}
	
    @RequestMapping("checkexistemail")
    @ResponseBody
    public int checkMail(@RequestParam(value = "email", required = false) String email) {  
		UserInfo userInfo = userInfoService.findUserByEmail(email);
		if(userInfo.getEmail() != null){
			return 1;
		}
	  return 0;
    }
    @RequestMapping(value = "changeowner/{id:[\\d]+}", method = RequestMethod.GET)
	public ModelAndView changeowner(@PathVariable("id") int id) {
    	ModelAndView modelAndView = new ModelAndView();
    	UserInfo userLeave = userInfoService.getUserInfoByUserId(id);
    	List<CardInfo> listCardInfo = cardInfoService.getListCardAllocationUser(id);
    	List<UserInfoVo> lstUserInfo = userInfoService.getAllUserOfCompany(userLeave.getGroupCompanyId());
		modelAndView.setViewName("changeowner");
		modelAndView.addObject("userLeave", userLeave);
		modelAndView.addObject("listCardInfo", listCardInfo);
		modelAndView.addObject("lstUserInfo", lstUserInfo);
		return modelAndView;
		
	}
    
    @RequestMapping(value = "searchList", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public DataPagingJsonVO<UserInfoResultVO> searchList(HttpServletRequest request) {
		DataPagingJsonVO<UserInfoResultVO> dataTableResponse = new DataPagingJsonVO<UserInfoResultVO>();
		List<UserInfoResultVO> userInfoResultVOs = new ArrayList<UserInfoResultVO>();
		int limit = parseIntParameter(request.getParameter("length"), 0);
		int offset = parseIntParameter(request.getParameter("start"), 0);
		String criteriaSearch = request.getParameter("criteriaSearch");
		String textSearch = criteriaSearch.trim().replaceAll(" +", "|");
		
		List<UserInfoVo> userInfos =userInfoService.searchUser(textSearch,-1,-1); 
		BigInteger count = userInfoService.countUser(textSearch);
		long totalRecord = count.longValue();
		for (UserInfoVo info : userInfos) {
			UserInfoResultVO userInfoResultVO = new UserInfoResultVO(info.getUserId(), info.getName(), info.getCompanyName(), info.getPositionName(), info.getEmail(), info.getMobileNumber(), info.getCreateDate().toString() ,info.getFirstName(),info.getLastName(),info.getFirstNameKana(),info.getLastNameKana(),info.getDepartmentName(),info.getUserIndexNo());
			userInfoResultVOs.add(userInfoResultVO);
		}
		dataTableResponse.setDraw(parseIntParameter(request.getParameter("draw"), 0));
		dataTableResponse.setRecordsTotal(totalRecord);
		dataTableResponse.setRecordsFiltered(totalRecord);
		dataTableResponse.setData(userInfoResultVOs);
	
		return dataTableResponse;
	}
    
    @RequestMapping(value="updateCardUser", method=RequestMethod.POST)
	@ResponseBody
	public int updateCardUser(@RequestBody final  UpdateCardUser updateCardUser) { 
    	List<Integer> listCardId = new ArrayList<>();
    	if(updateCardUser.isCheckAll()){
    		List<CardInfo> listCardInfo = cardInfoService.getListCardAllocationUser(Integer.parseInt(updateCardUser.getUserLeave()));
    		for(CardInfo cardId : listCardInfo){
    			listCardId.add(cardId.getCardId());
    		}
    	}else{
    		listCardId.addAll(updateCardUser.getListCardId());
    	}
    	
    	if(listCardId.size() > 0){
    		adminPossessionCardService.updateUserCard(listCardId,Integer.parseInt( updateCardUser.getUserLeave()), Integer.parseInt( updateCardUser.getUserAssign()));
    	}
    	return Integer.parseInt( updateCardUser.getUserLeave());
    }
	
}
