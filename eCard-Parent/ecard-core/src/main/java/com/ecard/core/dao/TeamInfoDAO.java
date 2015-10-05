package com.ecard.core.dao;

import java.math.BigInteger;
import java.util.List;

//import com.ecard.core.model.OperaterActionHistoryId;
import com.ecard.core.model.TeamInfo;
import com.ecard.core.vo.TeamDisInfo;

public interface TeamInfoDAO extends IGenericDao<TeamInfo>{
	
    List<TeamInfo> findAllTeams();
    List<TeamDisInfo> getTeamInfo();
    TeamInfo getTeamInfoById(Integer id);
    int deleteTeam(Integer id);
    boolean updateTeamInfo(TeamInfo teamInfo);
    TeamInfo addTeamInfo(TeamInfo teamInfo);
    BigInteger getTotalCardNotInAdminPossession();
//	boolean addOperaterActionHistory(OperaterActionHistoryId operaterActionHistoryId);
    public List<Integer> getListCardNotInAdminPossession();
    public void resetTargetCountForTeam(Integer teamId);
 }
