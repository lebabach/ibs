package com.ecard.core.dao.impl;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ecard.core.dao.AdminPossessionCardDAO;
import com.ecard.core.model.AdminPossessionCard;

@Repository("adminPossessionCardDAO")
@Transactional
public class AdminPossessionCardDAOImpl extends GenericDao implements AdminPossessionCardDAO {

	@Override
	public void registerAdminPosCard(AdminPossessionCard adminPossessionCard) {
		// TODO Auto-generated method stub
		getEntityManager().persist(adminPossessionCard);
		getEntityManager().flush();
	}

	public Boolean checkPermissionEdit(Integer userId, Integer cardId) {
		Query query = getEntityManager().createNativeQuery(
				"SELECT * FROM admin_possession_card pc WHERE pc.user_id = :userId AND" + " pc.card_id = :cardId ");
		query.setParameter("userId", userId);
		query.setParameter("cardId", cardId);
		if (query.getResultList().size() == 0) {
			return false;
		} else {
			return true;
		}

	}

	@Override
	public BigInteger getTotalCardUserOfTeam(List<Integer> lstUserId) {
		// TODO Auto-generated method stub
		Query query = getEntityManager().createNativeQuery(
				"select count(*) " + " from admin_possession_card " + "where user_id in  (:user_id) ");
		query.setParameter("user_id", lstUserId);
		return (BigInteger) getOrNull(query);
	}

	public Integer resetAllocationTeam(Integer teamId) {
		Query query = getEntityManager()
				.createNativeQuery("DELETE FROM admin_possession_card " + " WHERE create_date < CURRENT_DATE "
						+ " AND user_id IN( SELECT user_id FROM user_info WHERE team_id = :teamId )");
		query.setParameter("teamId", teamId);
		return query.executeUpdate();
	}

	@Override
	public Integer updateUserCard(List<Integer> listCardUser, Integer userLeave, Integer userAssign) {
		String sql = " update admin_possession_card SET user_id  = :userAssign where  user_id= :userLeave and card_id in (:listCardUser)";
		Query query = getEntityManager().createNativeQuery(sql);
		query.setParameter("userAssign", userAssign);
		query.setParameter("userLeave", userLeave);
		query.setParameter("listCardUser", listCardUser);
		return query.executeUpdate();
	}

}
