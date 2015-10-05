package com.ecard.core.service.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecard.core.dao.TeamInfoDAO;
import com.ecard.core.dao.UserInfoDAO;
//import com.ecard.core.model.OperaterActionHistoryId;
import com.ecard.core.model.TeamInfo;
import com.ecard.core.model.UserInfo;
import com.ecard.core.service.TeamInfoService;
import com.ecard.core.vo.TeamDisInfo;

@Service("teamInfoService")
@Transactional
public class TeamInfoServiceImpl implements TeamInfoService {
	@Autowired
    TeamInfoDAO teamInfoDAO;
	
	@Autowired
    UserInfoDAO userInfoDAO;
	
	@Override
	public List<TeamInfo> getAllTeamInfo(){
		return teamInfoDAO.findAllTeams();
	}

	@Override
	public List<TeamDisInfo> getTeamInfo() {
		return teamInfoDAO.getTeamInfo();
	}

	@Override
	public TeamInfo getTeamInfoById(Integer id) {
		if (id == null)
			return null;
		try {
			return teamInfoDAO.getTeamInfoById(id);
		} catch (Exception ex) {
			return null;
		}
	}

	@Override
	public Integer deleteTeam(Integer id) {
		if (id == null) {
			return null;
		}
		Integer result = null;
		try {
			userInfoDAO.removeTeamInfoByTeamId(id);
			result = teamInfoDAO.deleteTeam(id);
		} catch (Exception ex) {
			// TODO: handle exception
		}
		return result;
	}

	@Override
	public boolean updateTeamInfo(TeamInfo teamInfo) {
		return teamInfoDAO.updateTeamInfo(teamInfo);
		
	}

	@Override
	public TeamInfo registerTeam(TeamInfo team) {
		Set<UserInfo> userList = team.getUserInfos();
		team = teamInfoDAO.addTeamInfo(team);
		for(UserInfo userInfo : userList){
			userInfoDAO.updateTeamId(userInfo.getUserId(), team.getTeamId());
		}
		team = teamInfoDAO.getTeamInfoById(team.getTeamId());
		return team;
	}

	@Override
	public BigInteger getTotalCardNotInAdminPossession() {
		return teamInfoDAO.getTotalCardNotInAdminPossession();
	}

	@Override
	public List<Integer> getListCardNotInAdminPossession() {
		return teamInfoDAO.getListCardNotInAdminPossession();
	}
	
	public void updateTeamCommon(TeamInfo teamInfo) {
		teamInfoDAO.saveOrUpdate(teamInfo);
	}
	public void resetTargetCountForTeam(Integer teamId){
		teamInfoDAO.resetTargetCountForTeam(teamId);
	}

//	@Override
//	public boolean addOperaterActionHistory(OperaterActionHistoryId operaterActionHistoryId) {
//		return teamInfoDAO.addOperaterActionHistory(operaterActionHistoryId);
//	}

}
