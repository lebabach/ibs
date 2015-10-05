package com.ecard.core.service;

import java.math.BigInteger;
import java.util.List;

//import com.ecard.core.model.OperaterActionHistoryId;
import com.ecard.core.model.TeamInfo;
import com.ecard.core.vo.TeamDisInfo;

public interface TeamInfoService {
	public List<TeamInfo> getAllTeamInfo();
	public List<TeamDisInfo> getTeamInfo();
	
	/**
	 * Get TeamInfo detail : teamInfo, userInfoSet, operaterActionHistorySet
	 * @param id
	 * @return
	 */
	public TeamInfo getTeamInfoById(Integer id);
	public boolean updateTeamInfo(TeamInfo teamInfo);
	public Integer deleteTeam(Integer id);
	public TeamInfo registerTeam(TeamInfo team);
	public BigInteger getTotalCardNotInAdminPossession();
//	public boolean addOperaterActionHistory(OperaterActionHistoryId operaterActionHistoryId);
	
	public List<Integer> getListCardNotInAdminPossession();
	public void updateTeamCommon(TeamInfo teamInfo);
	public void resetTargetCountForTeam(Integer teamId);
}
