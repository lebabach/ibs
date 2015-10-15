package com.ecard.webapp.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.context.Context;

import com.ecard.core.model.GroupCompanyInfo;
import com.ecard.core.model.HistorySendEmail;
import com.ecard.core.model.UserInfo;
import com.ecard.core.service.ContactNotificationService;
import com.ecard.core.service.EmailService;
import com.ecard.core.service.GroupCompanyInfoService;
import com.ecard.core.service.UserInfoService;
import com.ecard.core.vo.UserInfoVo;
import com.ecard.webapp.constant.CommonConstants;
import com.ecard.webapp.security.EcardUser;
import com.ecard.webapp.security.RoleType;
import com.ecard.webapp.util.StringUtilsHelper;
import com.ecard.webapp.vo.CompanyDisplayVO;
import com.ecard.webapp.vo.DataPagingJsonVO;
import com.ecard.webapp.vo.MailGroupVO;
import com.ecard.webapp.vo.ShowHistoryMailVO;

import com.ecard.webapp.vo.UserInfoResultVO;

@Controller
@RequestMapping("/mails/*")
public class MailController {
	
	@Autowired
    GroupCompanyInfoService groupCompanyInfoService;
	
	@Autowired
	UserInfoService userInfoService;
	
	@Autowired
    EmailService emailService;
	
    @Autowired
    GroupCompanyInfoService groupCompnayInfoService;
    
    @Autowired
    ContactNotificationService contactNotificationService;
    
    @Value("${mail.server.from}")
	private String fromUser;

	@RequestMapping("displayMail")
	public ModelAndView sendMail(@RequestParam(value = "criteriaSearch", required = false) String criteriaSearch){
		// list user
		List<UserInfoResultVO> userInfoResultVOs = new ArrayList<UserInfoResultVO>();
		List<UserInfoVo> userInfos;
		if (criteriaSearch != null) {
			userInfos = userInfoService.searchUser(criteriaSearch, -1, -1);
		} else {
			userInfos = userInfoService.searchUser("", -1, -1);
		}
		for (UserInfoVo info : userInfos) {
			UserInfoResultVO userInfoResultVO = new UserInfoResultVO(info.getUserId(), info.getName(),
					info.getCompanyName(), info.getPositionName(), info.getEmail(), info.getMobileNumber(),
					info.getCreateDate().toString(), info.getFirstName(), info.getLastName(), info.getFirstNameKana(),
					info.getLastNameKana(), info.getDepartmentName(), info.getUserIndexNo());
			userInfoResultVOs.add(userInfoResultVO);
		}
		// list company
		List<GroupCompanyInfo> companyInfos = null;
		List<CompanyDisplayVO> lstcompanyDisplayVO = new ArrayList<>();
		companyInfos = groupCompanyInfoService.getListCompany();
		CompanyDisplayVO companyDisplayVO = null;
		if (companyInfos != null) {
			for (GroupCompanyInfo groupCompanyInfo : companyInfos) {
				String groupCompanyInfoIndex = StringUtilsHelper
						.convertIdToString(groupCompanyInfo.getGroupCompanyId());
				companyDisplayVO = new CompanyDisplayVO(groupCompanyInfo.getGroupCompanyId(), groupCompanyInfoIndex,
						groupCompanyInfo.getGroupCompanyName(), groupCompanyInfo.getGroupCompanyNameKana(),
						groupCompanyInfo.getCreateDate(), groupCompanyInfo.getUpdateDate(),
						groupCompanyInfo.getOperaterId());
				lstcompanyDisplayVO.add(companyDisplayVO);
			}
		}
    	
    	MailGroupVO mailGroupVO = new MailGroupVO(userInfoResultVOs, lstcompanyDisplayVO);
		return new ModelAndView("displayMail","mailGroupVO",mailGroupVO);
	}
	
	@RequestMapping(value = "search", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public DataPagingJsonVO<UserInfoResultVO> search(HttpServletRequest request) {
		DataPagingJsonVO<UserInfoResultVO> dataTableResponse = new DataPagingJsonVO<UserInfoResultVO>();
		List<UserInfoResultVO> userInfoResultVOs = new ArrayList<UserInfoResultVO>();
		String criteriaSearch = request.getParameter("criteriaSearch");
		String textSearch = criteriaSearch.trim().replaceAll(" +", "|");
		int limit = parseIntParameter(request.getParameter("length"), 0);
		int offset = parseIntParameter(request.getParameter("start"), 0);
		List<UserInfoVo> userInfos = userInfoService.searchUser(textSearch, limit, offset) ;
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
	@RequestMapping(value="adduser", method=RequestMethod.POST)
	@ResponseBody
	public List<UserInfoResultVO> addUser(@RequestParam(value="listUserId") ArrayList<Integer> listUserId) {  
		List<UserInfoResultVO>  lstUser = new ArrayList<>();
		List<UserInfoVo> userInfos = userInfoService.getUserInArrUserId(listUserId);
		for (UserInfoVo info : userInfos) {
			UserInfoResultVO userInfoResultVO = new UserInfoResultVO(info.getUserId(), info.getName(), info.getCompanyName(), info.getPositionName(), info.getEmail(), info.getMobileNumber(), info.getCreateDate().toString() ,info.getFirstName(),info.getLastName(),info.getFirstNameKana(),info.getLastNameKana(),info.getDepartmentName(),info.getUserIndexNo());
			lstUser.add(userInfoResultVO);
		}
		return lstUser;
    }
	
	@RequestMapping("displayPastMail")
	public ModelAndView pastMail(){
		List<HistorySendEmail> listHistorySendMail = emailService.getAllEmail();
		List<GroupCompanyInfo> listGroupCompany = groupCompnayInfoService.getListCompany();
		
		List<ShowHistoryMailVO> showListHistory = new ArrayList<ShowHistoryMailVO>();
		List<UserInfo> listUser = userInfoService.getAllUserInfo();
		ModelAndView modelAndView = new ModelAndView();
		for (HistorySendEmail history : listHistorySendMail) {
			ShowHistoryMailVO historyTmp = null;
			if (history.getSendType() == 3) {
				List<Integer> listUserId = convertToInteger(history.getUserSendTo());
				String sendTo = "";
				int position = 1;
				for (Integer listId : listUserId) {
					if (position == listUserId.size()) {
						
						if(listUser.stream().filter(s -> s.getUserId() == listId).anyMatch(s->true)){
							sendTo += listUser.stream().filter(s -> s.getUserId() == listId).findFirst().get().getName();
						} 
//						else {
//							sendTo += "Deleted User";
//						}
						
					} else {
						if(listUser.stream().filter(s -> s.getUserId() == listId).anyMatch(s->true)){
							sendTo += listUser.stream().filter(s -> s.getUserId() == listId).findFirst().get().getName() + ", ";
						} 
//						else {
//							sendTo += "Deleted User, ";
//						}
					}
					position++;
				}
				if(!sendTo.equals("")){
					historyTmp = new ShowHistoryMailVO(history.getId(), sendTo, listUserId, history.getMailTitle(),
							history.getMailDetail(), history.getSendType(), history.getCreateDate());
				} else {
					continue;
				}
				
				
			} else if (history.getSendType() == 2) {  
				String companyName = "";
				companyName = listGroupCompany.stream().filter(c->c.getGroupCompanyId()== Integer.parseInt(history.getUserSendTo())).findFirst().get().getGroupCompanyName();
				historyTmp = new ShowHistoryMailVO(history.getId(), companyName, null,
						history.getMailTitle(), history.getMailDetail(), history.getSendType(),
						history.getCreateDate());
			} else {
				historyTmp = new ShowHistoryMailVO(history.getId(), history.getUserSendTo(), null,
						history.getMailTitle(), history.getMailDetail(), history.getSendType(),
						history.getCreateDate());
			}
			showListHistory.add(historyTmp);

		}
		modelAndView.setViewName("displayPastMail");
		modelAndView.addObject("showListHistory", showListHistory);		
		return modelAndView;		
	}
	
	@RequestMapping("listuser")
	@ResponseBody
	public List<UserInfoVo> searchUser(@RequestParam(value = "criteriaSearch", required = false) String criteriaSearch) {
		String textSearch = criteriaSearch.trim().replaceAll(" +", "|");
		List<UserInfoVo> userInfoVOs = null;
		if (criteriaSearch == null || criteriaSearch.isEmpty()){
			userInfoVOs = userInfoService.getAllUserSearchInfo();
		}else{
			userInfoVOs = userInfoService.searchUser(textSearch, -1, -1);
		}
		return userInfoVOs;
    }
	
	@RequestMapping("/listUserForMail")
    @ResponseBody
    public JSONObject listUserForMail(@RequestParam(value = "listUser", required = false) String listUser) {  
		JSONArray obj = new JSONArray();
		JSONObject response = new JSONObject();
		
		listUser = listUser.replace("[", "");
		listUser = listUser.replace("]", "");
		listUser = listUser.replace(" ", "");
		List<UserInfo> listUserInfo = userInfoService.getAllUserInfo();
		List<Integer> listUserId = convertToInteger(listUser);
		for(Integer user : listUserId){
			JSONObject userJSON = new JSONObject();
			if(listUserInfo.stream().filter(u->u.getUserId()==user).anyMatch(u->true)){
				userJSON.put("userName",listUserInfo.stream().filter(u->u.getUserId()==user).findFirst().get().getName());
				userJSON.put("companyName",listUserInfo.stream().filter(u->u.getUserId()==user).findFirst().get().getCompanyName());
				userJSON.put("departmentName",listUserInfo.stream().filter(u->u.getUserId()==user).findFirst().get().getDepartmentName());
				userJSON.put("positionName",listUserInfo.stream().filter(u->u.getUserId()==user).findFirst().get().getPositionName());
				userJSON.put("userId",user);
			} else {
				continue;
			}
			
//			else {
//				userJSON.put("userName","Deleted User");
//				userJSON.put("companyName","No value");
//				userJSON.put("departmentName","No value");
//				userJSON.put("position","No value");
//				userJSON.put("userId","No value");
//			}
			
			obj.add(userJSON);
		}
		response.put("userId", obj);
		response.put("status","Sucess");
		return response;
    }
	
	@RequestMapping(value="sendmailcontact",method=RequestMethod.POST)
	@ResponseBody
	public int sendMailContact(@RequestParam(value="sendType") String sendType,@RequestParam(value="userSendTo") String userSendTo,@RequestParam(value="titleMail") String titleMail,@RequestParam(value="contentMail") String contentMail){
		List<Integer> lstUserId = null; 
		int type = Integer.parseInt(sendType);
		lstUserId = convertToInteger(userSendTo);
		HistorySendEmail history = new HistorySendEmail();
		history.setSendType(type);
		history.setMailTitle(titleMail);
		history.setMailDetail(contentMail);
		history.setUserSendTo(userSendTo);
		history.setCreateDate(new Date());
		contactNotificationService.sendContactMail(history);
		List<String> lstEmail = userInfoService.getListEmail(lstUserId, type);
		Context ctx = new Context();
		String contentText = contentMail.replaceAll("(\r\n|\n)", " <br />");
		ctx.setVariable("content", contentText);
		try {
			emailService.sendMailContact(CommonConstants.USER_FROM_MAIL, lstEmail, titleMail, ctx, "mailtocontact");
		} catch (MessagingException e) {
			e.printStackTrace();
			return 1;
		}
		return 0;
	}
	


	private List<Integer> convertToInteger(String seq){      
        String[] seqStr = seq.split(",");
        int i=0;
        Integer[] intarray=new Integer[seqStr.length];
        for (String myStr : seqStr) {
            try {
                intarray[i]=Integer.parseInt(myStr);
                i++;
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Not a number: " + myStr + " at index " + i, e);
            }
        }
         List<Integer> seqList = Arrays.asList(intarray);
        return seqList;
    }
	
	private int parseIntParameter(String parameter, int defaultValue) {
		try {
			return Integer.parseInt(parameter);
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}
}
