/*
 * TeamController
 */
package com.ecard.webapp.controller;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ecard.core.model.AdminPossessionCard;
import com.ecard.core.model.AdminPossessionCardId;
import com.ecard.core.model.GroupCompanyInfo;
import com.ecard.core.model.TeamInfo;
import com.ecard.core.model.UserInfo;
import com.ecard.core.service.AdminPossessionCardService;
import com.ecard.core.service.GroupCompanyInfoService;
import com.ecard.core.service.TeamInfoService;
import com.ecard.core.service.UserInfoService;
import com.ecard.core.vo.UserInfoVo;
import com.ecard.webapp.util.StringUtilsHelper;
import com.ecard.webapp.vo.AllocationTeamVO;
import com.ecard.webapp.vo.ObjectCardNumber;
import com.ecard.webapp.vo.ObjectMembers;
import com.ecard.webapp.vo.ObjectTeamVO;
import com.ecard.webapp.vo.TeamDisplayVO;
import com.ecard.webapp.vo.TeamInfoVO;
import com.ecard.webapp.vo.TeamListVO;
import com.ecard.webapp.vo.UserInfoVO;
import com.ecard.webapp.vo.UserVo;

@Controller
@RequestMapping("/teams/*")
public class TeamController {	
    @Autowired
    TeamInfoService teamInfoService;
    
    @Autowired
    UserInfoService userInfoService;
    
    @Autowired
    GroupCompanyInfoService groupCompanyInfoService;
    
    @Autowired
    AdminPossessionCardService adminPossessionCardService;
    
    @RequestMapping("list")  
    public ModelAndView list() {  
		List<TeamInfo> teamList = null;
		teamList = teamInfoService.getAllTeamInfo();
		TeamListVO teamListVO = new TeamListVO();
		teamListVO.setTeamList(teamList);
		// bach.le conver entity to vo
		// List<TeamInfoVO>
		// teamInforVoList1=StreamSupport.stream(teamList.spliterator(),
		// false).map(t->t).collect(Collectors.toList());
		List<TeamInfoVO> teamInforVoList = new ArrayList<TeamInfoVO>();
		List<UserInfo> users = userInfoService.getAllUserInfo();
		teamList.forEach(t -> {
			TeamInfoVO teamInfoVO = new TeamInfoVO();
			teamInfoVO.setTeamId(t.getTeamId());
			teamInfoVO.setTeamName(t.getTeamName());
			teamInfoVO.setTargetCount(t.getTargetCount());
			int countUser = (int) users.stream()
					.filter(u -> u.getTeamInfo() != null && u.getTeamInfo().getTeamId() == t.getTeamId()).count();
			teamInfoVO.setUserCount(countUser);
			teamInforVoList.add(teamInfoVO);
		});

		return new ModelAndView("teamlist", "teamlistVO", teamInforVoList);
    }
    
    @RequestMapping(value="display/{id:[\\d]+}", method= RequestMethod.GET)  
    public ModelAndView display(@PathVariable("id") Integer id) {  
		TeamInfo teamInfo = teamInfoService.getTeamInfoById(id);
		// bach.le
		List<UserInfo> users = new ArrayList<UserInfo>();
		List<GroupCompanyInfo> companies = groupCompanyInfoService.getListCompany();
		users.addAll(teamInfo.getUserInfos().stream()
				.filter(x -> x.getTeamInfo() != null && x.getDeleteFlg() == 0 && x.getLeaveFlg() == 0)
				.collect(Collectors.toList()));
		List<UserInfoVO> usersVO = new ArrayList<UserInfoVO>();
		users.forEach(u -> {
			UserInfoVO userVO = new UserInfoVO();
			userVO.setCompanyName(companies.stream().filter(x -> x.getGroupCompanyId() == u.getGroupCompanyId())
					.findFirst().get().getGroupCompanyName());
			userVO.setName(StringUtilsHelper.mergerStringEitherAWord(u.getLastName(), u.getFirstName(), " "));
			usersVO.add(userVO);
		});

		TeamInfoVO teamVO = new TeamInfoVO();
		teamVO.setTeamId(teamInfo.getTeamId());
		teamVO.setTeamName(teamInfo.getTeamName());
		teamVO.setUserInfos(usersVO);
		return new ModelAndView("teamdisplay", "teamVO", teamVO);
    }

    
    @RequestMapping(value="register", method=RequestMethod.GET)
    public ModelAndView add() {  
    	TeamDisplayVO teamDisplayVO = new TeamDisplayVO();
    	teamDisplayVO.setAction("register");
    	teamDisplayVO.setName("");
    	return new ModelAndView("teamedit", "teamDisplayVO", teamDisplayVO);
    }
    
    @RequestMapping(value="register", method=RequestMethod.POST)
    public ModelAndView register(@RequestParam(value="name") String name) {
		TeamInfo team = new TeamInfo();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserInfo userInfor = userInfoService.getAllUserInfo().stream().filter(x -> x.getEmail().equals(auth.getName())).findFirst().get();
		Date date = new Date();
		team.setCreateDate(date);
		team.setUpdateDate(date);
		team.setTeamName(name);
		team.setTargetCount(0);
		team.setOperaterId(userInfor.getUserId());
		teamInfoService.registerTeam(team);
		return new ModelAndView("redirect:list");
    }
    
    
    @RequestMapping(value="registerteam") 
    @ResponseBody
    public int controllerMethod1(@RequestParam(value="cbUser[]") ArrayList<String> cbUser,@RequestParam(value="teamName") String teamName,@RequestParam(value="targetCount") int targetCount){
		boolean _rs = false;
		TeamInfo team = new TeamInfo();
		team.setOperaterId(1);
		team.setTargetCount(targetCount);
		team.setTeamName(teamName);
		team.setUpdateDate(new Date());
		team.setCreateDate(new Date());
		team = teamInfoService.registerTeam(team);

		if (cbUser != null && team != null) {
			for (int i = 0; i < cbUser.size(); i++) {
				int userId = Integer.parseInt(cbUser.get(i));
				_rs = userInfoService.updateTeamId(userId, team.getTeamId());
			}
		}

		if (!_rs)
			return 0;
		return 1;
    }
    
    @RequestMapping(value="edit/{id:[\\d]+}", method=RequestMethod.GET)
    public ModelAndView edit(@PathVariable("id") Integer id) {  
    	TeamInfo teamInfo = teamInfoService.getTeamInfoById(id);
    	TeamDisplayVO teamDisplayVO = new TeamDisplayVO();
    	teamDisplayVO.setAction("../edit/"+id);
    	teamDisplayVO.setName(teamInfo.getTeamName());
    	teamDisplayVO.setCount(teamInfo.getTargetCount());
    	return new ModelAndView("teamedit", "teamDisplayVO", teamDisplayVO);
    }
    
    @RequestMapping(value="edit/{id:[\\d]+}", method=RequestMethod.POST)
    public ModelAndView editPost(@PathVariable("id") Integer id,@RequestParam(value="name") String name,@RequestParam(value="count") int count) {  
		TeamInfo team = new TeamInfo();
		team.setTeamId(id);
		team.setTargetCount(count);
		team.setTeamName(name);
		teamInfoService.updateTeamInfo(team);

		return new ModelAndView("redirect:../list");
    }

    @RequestMapping("editexecute") 
    @ResponseBody
    public int controllerMethod(@RequestParam(value="cbUser[]") ArrayList<String> userList,@RequestParam(value="teamID") int teamID,@RequestParam(value="teamName") String teamName,@RequestParam(value="targetCount") Integer targetCount){
		boolean _rs = false;
		if (userList != null) {
			// remove users in team current
			List<UserInfo> usersInTeam = userInfoService.getAllUserInfo().stream()
					.filter(u -> u.getTeamInfo() != null && u.getTeamInfo().getTeamId().intValue() == teamID)
					.collect(Collectors.toList());

			usersInTeam.forEach(u -> {
				userInfoService.updateTeamId(u.getUserId(), null);
			});

			for (int i = 0; i < userList.size(); i++) {
				int userId = Integer.parseInt(userList.get(i));
				_rs = userInfoService.updateTeamId(userId, teamID);
			}
		}
		TeamInfo teamInfo = teamInfoService.getTeamInfoById(teamID);
		if (!teamInfo.getTeamName().equals(teamName)) {
			teamInfo.setTeamName(teamName);

		}
		if (targetCount != null && teamInfo.getTargetCount() != targetCount)
			teamInfo.setTargetCount(targetCount);
		_rs = teamInfoService.updateTeamInfo(teamInfo);
		if (!_rs)
			return 0;
		return 1;
    }
    
	@RequestMapping("delete")
	@ResponseBody
	public int delete(@RequestParam(value = "teamId") int teamId) {
		try {
			teamInfoService.deleteTeam(teamId);
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
		return 0;
	}
	
	@RequestMapping(value="allocation", method=RequestMethod.GET)
    public ModelAndView allocation() {  
		List<TeamInfo> teamList = null;
		teamList = teamInfoService.getAllTeamInfo();
		BigInteger totalCard = teamInfoService.getTotalCardNotInAdminPossession();
		List<Integer> listCard = teamInfoService.getListCardNotInAdminPossession();
		BigInteger totalCardTeam;
		List<UserInfoVo> listUser = null;
		List<UserVo> lstUserVo = new ArrayList<>();
		long total = totalCard.longValue();
		if (teamList.size() > 0) {
			int teamId = teamList.get(0).getTeamId();
			listUser = userInfoService.getListUserofTeam(teamId);
			List<Integer> lstUserId = new ArrayList<>();
			for (UserInfoVo uservo : listUser) {
				lstUserId.add(uservo.getUserId());
			}
			if (listUser.size() > 0) {
				totalCardTeam = adminPossessionCardService.getTotalCardUserOfTeam(lstUserId);
			} else {
				totalCardTeam = new BigInteger("0");
			}
			UserVo userVo = null;
			for (int i = 0; i < listUser.size(); i += 3) {
				Integer userId = 0;
				Integer userId1 = 0;
				Integer userId2 = 0;
				Integer teamdivide = 0;
				Integer teamdivide1 = 0;
				Integer teamdivide2 = 0;
				userId = listUser.get(i).getUserId();
				String user1 = listUser.get(i).getName();
				teamdivide = listUser.get(i).getTeamDivideCnt()==null?0:listUser.get(i).getTeamDivideCnt().intValue();
				String user2 = null;
				String user3 = null;
				int j = i + 1;
				int k = i + 2;
				if (j < listUser.size()) {
					user2 = listUser.get(j).getName();
					userId1 = listUser.get(j).getUserId();
					teamdivide1 = listUser.get(j).getTeamDivideCnt()==null?0:listUser.get(j).getTeamDivideCnt().intValue();
				}
				if (k < listUser.size()) {
					user3 = listUser.get(k).getName();
					userId2 = listUser.get(k).getUserId();
					teamdivide2 = listUser.get(k).getTeamDivideCnt()==null?0:listUser.get(k).getTeamDivideCnt().intValue();
				}
				userVo = new UserVo(user1, user2, user3, userId, userId1, userId2, teamList.get(0).getCurrentCount(),
						teamList.get(0).getTargetCount(), totalCardTeam.intValue(), teamdivide, teamdivide1,teamdivide2);
				lstUserVo.add(userVo);
			}
		}
		AllocationTeamVO allocationTeamVO = new AllocationTeamVO(teamList, total, listUser, lstUserVo, listCard);
		return new ModelAndView("team_allocation", "allocationTeamVO", allocationTeamVO);
    }
	
	@RequestMapping(value="listAllocation", method=RequestMethod.POST)
	@ResponseBody
    public List<UserVo> listAllocation(@RequestParam(value="teamId") int teamId) {  
		List<UserInfoVo> listUser = userInfoService.getListUserofTeam(teamId);
		List<Integer> lstUserId = new ArrayList<>();
		for (UserInfoVo uservo : listUser) {
			lstUserId.add(uservo.getUserId());
		}
		BigInteger totalCardTeam;
		if (listUser.size() > 0) {
			totalCardTeam = adminPossessionCardService.getTotalCardUserOfTeam(lstUserId);
		} else {
			totalCardTeam = new BigInteger("0");
		}

		TeamInfo teamInfo = teamInfoService.getTeamInfoById(teamId);
		List<UserVo> lstUserVo = new ArrayList<>();
		UserVo userVo = null;
		for (int i = 0; i < listUser.size(); i += 3) {
			Integer teamDivideCnt = listUser.get(i).getTeamDivideCnt().intValue();
			if(teamDivideCnt == null){
				teamDivideCnt = 0;
			}
			Integer userId = 0;
			Integer userId1 = 0;
			Integer userId2 = 0;
			Integer teamdivide = 0;
			Integer teamdivide1 = 0;
			Integer teamdivide2 = 0;
			userId = listUser.get(i).getUserId();
			teamdivide = listUser.get(i).getTeamDivideCnt()==null?0:listUser.get(i).getTeamDivideCnt().intValue();
			String user1 = listUser.get(i).getName();
			String user2 = null;
			String user3 = null;
			int j = i + 1;
			int k = i + 2;
			if (j < listUser.size()) {
				user2 = listUser.get(j).getName();
				userId1 = listUser.get(j).getUserId();
				teamdivide1 = listUser.get(j).getTeamDivideCnt()==null?0:listUser.get(j).getTeamDivideCnt().intValue();
			}
			if (k < listUser.size()) {
				user3 = listUser.get(k).getName();
				userId2 = listUser.get(k).getUserId();
				teamdivide2 = listUser.get(k).getTeamDivideCnt()==null?0:listUser.get(k).getTeamDivideCnt().intValue();
			}
			userVo = new UserVo(user1, user2, user3, userId, userId1, userId2, teamInfo.getCurrentCount(),
					teamInfo.getTargetCount(), totalCardTeam.intValue(), teamdivide, teamdivide1, teamdivide2);
			lstUserVo.add(userVo);
		}
		return lstUserVo;
    }
	
	@RequestMapping(value="saveAllocation", method=RequestMethod.POST)
	@ResponseBody
	public ObjectCardNumber saveAllocation(@RequestBody final  ObjectTeamVO objectTeamVO) {  
		TeamInfo teamInfo = teamInfoService.getTeamInfoById(objectTeamVO.getTeamId());
		List<Integer> listCard = teamInfoService.getListCardNotInAdminPossession();
		List<UserInfoVo> listUser = userInfoService.getListUserofTeam(objectTeamVO.getTeamId());
		teamInfo.setTargetCount(objectTeamVO.getTarget_count());
		teamInfo.setCurrentCount(objectTeamVO.getCurrent_count());
		if (objectTeamVO.getListUser().size() > 0) {
			for (ObjectMembers objectMembers : objectTeamVO.getListUser()) {
				UserInfo userInfo = userInfoService.getUserInfoByUserId(Integer.parseInt(objectMembers.getUserId()));
				userInfo.setTeamDivideCnt(Integer.parseInt(objectMembers.getTeamdivide()));
				userInfoService.updateProfileAdminAllocation(userInfo);
				for (int i = 1; i <= Integer.parseInt(objectMembers.getTeamdivide()); i++) {
					for (Integer cardId : listCard) {
						AdminPossessionCardId adminPossessionCardId = new AdminPossessionCardId();
						adminPossessionCardId.setCardId(cardId.intValue());
						adminPossessionCardId.setUserId(Integer.parseInt(objectMembers.getUserId()));
						adminPossessionCardId.setCreateDate(new Date());
						com.ecard.core.model.CardInfo cardInfo = new com.ecard.core.model.CardInfo();
						cardInfo.setCardId(cardId);
						AdminPossessionCard adminPossessionCard = new AdminPossessionCard();
						adminPossessionCard.setId(adminPossessionCardId);
						adminPossessionCard.setCardInfo(cardInfo);
						adminPossessionCard.setUserInfo(userInfo);
						adminPossessionCardService.registerAdminPosCard(adminPossessionCard);
						listCard.remove(cardId);
						break;
					}
				}
			}
		}
		teamInfoService.updateTeamInfo(teamInfo);

		BigInteger totalCard = teamInfoService.getTotalCardNotInAdminPossession();
		List<Integer> lstUserId = new ArrayList<>();
		for (UserInfoVo uservo : listUser) {
			lstUserId.add(uservo.getUserId());
		}
		BigInteger totalCardTeam;
		if (listUser.size() > 0) {
			totalCardTeam = adminPossessionCardService.getTotalCardUserOfTeam(lstUserId);
		} else {
			totalCardTeam = new BigInteger("0");
		}
		ObjectCardNumber objCardNumber = new ObjectCardNumber(totalCard, totalCardTeam);
		return objCardNumber;
    }
	
	
	@RequestMapping(value="add-team-member/{id:[\\d]+}", method=RequestMethod.GET)
    public ModelAndView addTeamMembers(@PathVariable("id") Integer id) {  
		TeamInfo teamInfo = teamInfoService.getTeamInfoById(id);
		List<UserInfo> userInfos = userInfoService.getUserInfoNotInAnyTeam();
				/*.stream().filter(x -> x.getRoles() != null
						&& x.getRoles().getRoleId().intValue() == Roles.Operator.getStatusCode())
				.collect(Collectors.toList());*/
		List<UserInfo> usersInTeam = userInfoService.getAllUserInfo().stream()
				.filter(u -> u.getTeamInfo() != null
						&& u.getTeamInfo().getTeamId().intValue() == teamInfo.getTeamId().intValue()
						&& u.getDeleteFlg() == 0 && u.getLeaveFlg() == 0)
				.collect(Collectors.toList());
		// userInfos.addAll(usersInTeam);
		ObjectTeamVO team = new ObjectTeamVO();
		ArrayList<ObjectMembers> liNotExistedMembers = new ArrayList<ObjectMembers>();
		ArrayList<ObjectMembers> liExitedMembers = new ArrayList<ObjectMembers>();
		if (teamInfo != null && userInfos != null) {
			userInfos.forEach(u -> {
				ObjectMembers member = new ObjectMembers();
				member.setUserId(u.getUserId().toString());
				member.setMemberName(StringUtilsHelper.mergerStringEitherAWord(u.getLastName(), u.getFirstName(), " "));
				liNotExistedMembers.add(member);
			});
			usersInTeam.forEach(u -> {
				ObjectMembers member = new ObjectMembers();
				member.setUserId(u.getUserId().toString());
				member.setMemberName(StringUtilsHelper.mergerStringEitherAWord(u.getLastName(), u.getFirstName(), " "));
				liExitedMembers.add(member);
			});
			team.setTeamId(id);
			team.setTeamName(teamInfo.getTeamName());
			team.setListNotExitedUser(liNotExistedMembers);
			team.setListUser(liExitedMembers);
		}

		return new ModelAndView("add-team-member", "team", team);
    }
	
	@RequestMapping(value="add-team-member", method=RequestMethod.POST)
	@ResponseBody
	public boolean addTeamMemberPost(@RequestBody final  ObjectTeamVO objectTeamVO) {  
		// bach.le
		List<String> cbUser = objectTeamVO.getListUser().stream().map(x -> x.getUserId()).collect(Collectors.toList());
		TeamInfo team = teamInfoService.getTeamInfoById(objectTeamVO.getTeamId());
		team.setTeamId(objectTeamVO.getTeamId());
		team.setUpdateDate(new Date());
		team.setCurrentCount((cbUser != null && cbUser.size() > 0) ? cbUser.size() : 0);

		teamInfoService.updateTeamInfo(team);
		if (cbUser != null && team != null) {
			List<String> usersInTeam = userInfoService.getAllUserInfo().stream()
					.filter(u -> u.getTeamInfo() != null
							&& u.getTeamInfo().getTeamId().intValue() == objectTeamVO.getTeamId()
							&& u.getDeleteFlg() == 0 && u.getLeaveFlg() == 0)
					.map(u -> u.getUserId().toString()).collect(Collectors.toList());

			// add team to memebers
			for (int i = 0; i < cbUser.size(); i++) {
				int userId = Integer.parseInt(cbUser.get(i));
				userInfoService.updateTeamId(userId, team.getTeamId());
			}

			// remove team in members
			List<String> userIdRemove = usersInTeam.stream().filter(x -> !cbUser.contains(x))
					.collect(Collectors.toList());
			for (int i = 0; i < userIdRemove.size(); i++) {
				int userId = Integer.parseInt(userIdRemove.get(i));
				userInfoService.updateTeamId(userId, null);
			}
		}

		return true;
    }
	
	@RequestMapping(value="resetAllocationTeam", method=RequestMethod.POST)
	@ResponseBody
    public Integer resetAllocationTeam(@RequestParam(value="teamId") int teamId) {
		System.out.println("AAAA = "+teamId);
		Integer resetCard = 0;
		List<Integer> listUserId = new ArrayList<Integer>();
		if(teamId != 0){
			resetCard = adminPossessionCardService.resetAllocationTeam(teamId);
			if(resetCard != 0){
				teamInfoService.resetTargetCountForTeam(teamId);
				List<UserInfoVo> listUser = userInfoService.getListUserofTeam(teamId);
				for(UserInfoVo list : listUser){
					listUserId.add(list.getUserId());
				}
				userInfoService.updateTeamDivideCnt(listUserId);
			}
		}
		return resetCard;
    }
	
}
