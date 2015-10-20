package com.ecard.core.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import com.ecard.core.dao.TeamInfoDAO;
//import com.ecard.core.model.OperaterActionHistoryId;
import com.ecard.core.model.TeamInfo;
import com.ecard.core.vo.TeamDisInfo;

@Repository("teamInfoDAO")
public class TeamInfoDAOImpl extends GenericDao implements TeamInfoDAO {
	@SuppressWarnings("unchecked")
	public List<TeamInfo> findAllTeams() {
    	Query query = getEntityManager().createQuery("SELECT t FROM TeamInfo t");
		return (List<TeamInfo>) query.getResultList();
    }

	@Override
	public List<TeamDisInfo> getTeamInfo() {
		// TODO Auto-generated method stub
		Query query = getEntityManager().createNativeQuery("select t.team_name, t.target_count , (SELECT COUNT(ci.card_id)  FROM card_info ci" 
					+ " INNER JOIN admin_possession_card ap "
					+ " INNER JOIN user_info ui"
					+ " ON ui.user_id = ap.user_id AND  ci.card_id = ap.card_id"
					+ " WHERE ui.team_id = t.team_id  AND ci.approval_status = 1) as number_member"
					+ " from team_info t");
		    @SuppressWarnings("unchecked")
			List<Object[]> obj = query.getResultList();
	        List<TeamDisInfo> result = new ArrayList<>(obj.size());
	        for (Object[] row : obj) {
	            result.add(new TeamDisInfo((String)row[0],(Integer)row[1],(BigInteger)row[2],50));
	        }
	        return result;
		
	}


	@Override
	public TeamInfo getTeamInfoById(Integer id) {
		Query q = getEntityManager().createQuery("SELECT t FROM TeamInfo AS t WHERE t.teamId = :teamId");
        q.setParameter("teamId", id);
        //getEntityManager().getS
        TeamInfo teamInfo = (TeamInfo)q.getSingleResult();
        if(teamInfo!=null){
        	Hibernate.initialize(teamInfo.getUserInfos());
        	//Hibernate.initialize(teamInfo.getOperaterActionHistories());
        }
		return teamInfo;
	}

	@Override
	public int deleteTeam(Integer id) {
		//Delete team
		Query q2 = getEntityManager().createQuery("DELETE FROM TeamInfo t WHERE t.teamId = :teamId");
		q2.setParameter("teamId", id);
		return q2.executeUpdate();
	}

	@Override
	public boolean updateTeamInfo(TeamInfo teamInfo) {
		Query query = getEntityManager().createQuery("UPDATE TeamInfo t SET t.teamName = :teamName, t.targetCount = :targetCount,t.currentCount = :currentCount WHERE t.teamId = :teamId");
		query.setParameter("teamId", teamInfo.getTeamId());
		query.setParameter("teamName", teamInfo.getTeamName());
		query.setParameter("targetCount", teamInfo.getTargetCount());
		query.setParameter("currentCount", teamInfo.getCurrentCount());
		int result = query.executeUpdate();
        if(result == 0){
            return false;
        } else {
            return true;
        }
	}

	@Override
	public TeamInfo addTeamInfo(TeamInfo teamInfo) {
		EntityManager em = getEntityManager();
		em.persist(teamInfo);
		em.close();
		return teamInfo;
	}

	@Override
	public BigInteger getTotalCardNotInAdminPossession() {
		// TODO Auto-generated method stub
		Query query = getEntityManager().createNativeQuery("select  count(*) from card_info "
                             + " where card_id not in (select card_id from admin_possession_card) and (approval_status = 2 or approval_status = 3) and delete_flg = 0");
		return (BigInteger)getOrNull(query);
	}

	@Override
	public List<Integer> getListCardNotInAdminPossession() {
		Query query = getEntityManager().createNativeQuery("select  card_id from card_info "
                + " where card_id not in (select card_id from admin_possession_card)  and  (approval_status = 2 or approval_status = 3) and delete_flg = 0 "
                + " order by CASE WHEN (substr(card_index_no, 18, 1) = 'S') THEN (create_date + INTERVAL 4 DAY) ELSE create_date END  ASC");
           
		return (List<Integer>)query.getResultList();
	}

	public void resetTargetCountForTeam(Integer teamId){
		Query query = getEntityManager().createQuery("UPDATE TeamInfo t SET t.targetCount = 0 WHERE t.teamId = :teamId");
		query.setParameter("teamId", teamId);
		query.executeUpdate();
	}
//	@Override
//	public boolean addOperaterActionHistory(OperaterActionHistoryId operaterActionHistoryId) {
//		Query q1 = getEntityManager().createNativeQuery("INSERT INTO operater_action_history (team_id,user_id,action_type,target, action_date) VALUES(:teamID,:userID,:actionType,:target,:actionDate)");
//		q1.setParameter("teamID", operaterActionHistoryId.getTeamId());
//		q1.setParameter("userID", operaterActionHistoryId.getUserId());
//		q1.setParameter("actionType", operaterActionHistoryId.getActionType());
//		q1.setParameter("target", operaterActionHistoryId.getTarget());
//		q1.setParameter("actionDate", operaterActionHistoryId.getActionDate());
//		int rs =  q1.executeUpdate();
//		if(rs > 0)
//			return true;
//		return false;
//	}
}

