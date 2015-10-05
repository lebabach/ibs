package com.ecard.core.service;

import java.math.BigInteger;
import java.util.List;

import com.ecard.core.model.AdminPossessionCard;


public interface AdminPossessionCardService {
	public void registerAdminPosCard(AdminPossessionCard adminPossessionCard);
	
	public Boolean checkPermissionEdit(Integer userId, Integer cardId);
	public BigInteger getTotalCardUserOfTeam(List<Integer>lstUserId);
	public Integer resetAllocationTeam(Integer teamId);
}
