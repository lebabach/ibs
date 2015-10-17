package com.ecard.core.service.impl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecard.core.dao.AdminPossessionCardDAO;
import com.ecard.core.model.AdminPossessionCard;
import com.ecard.core.service.AdminPossessionCardService;

@Service("adminPossessionCardServiceImpl")
public class AdminPossessionCardServiceImpl implements AdminPossessionCardService {

	
	@Autowired
	AdminPossessionCardDAO adminPossessionCardDAO;
	
	
	
	@Override
	public void registerAdminPosCard(AdminPossessionCard adminPossessionCard) {
		adminPossessionCardDAO.registerAdminPosCard(adminPossessionCard);
	}

	public Boolean checkPermissionEdit(Integer userId, Integer cardId){
		return adminPossessionCardDAO.checkPermissionEdit(userId,cardId);
	}

	@Override
	public BigInteger getTotalCardUserOfTeam(List<Integer> lstUserId) {
		return adminPossessionCardDAO.getTotalCardUserOfTeam(lstUserId);
	}
	
	public Integer resetAllocationTeam(Integer teamId){
		return adminPossessionCardDAO.resetAllocationTeam(teamId);
	}

	@Override
	public Integer updateUserCard(List<Integer> listCardUser, Integer userLeave, Integer userAssign) {
		// TODO Auto-generated method stub
		return adminPossessionCardDAO.updateUserCard(listCardUser,userLeave,userAssign);
	}
}
