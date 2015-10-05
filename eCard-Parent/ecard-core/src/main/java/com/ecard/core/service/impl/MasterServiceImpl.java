package com.ecard.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecard.core.dao.RolesDAO;
import com.ecard.core.model.Roles;
import com.ecard.core.service.MasterService;

@Service("groupCompositionService")
@Transactional
public class MasterServiceImpl implements MasterService {
	
//	@Autowired
//	GroupCompositionDAO groupCompositionDAO;
	@Autowired
	RolesDAO rolesDAO;
//	@Override
//	public List<GroupComposition> getAllComposition() {
//		return groupCompositionDAO.findAll();
//	}

	@Override
	public List<Roles> getAllRoles() {
		return rolesDAO.findAll();
	}

}
