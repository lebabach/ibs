package com.ecard.core.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ecard.core.dao.RolesDAO;
import com.ecard.core.model.Roles;
@Repository("rolesDAO")
public class RolesDAOImpl extends GenericDao implements RolesDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<Roles> findAll() {
		Query query = getEntityManager().createQuery("SELECT r FROM Roles r");
		return (List<Roles>) query.getResultList();
	}

}
