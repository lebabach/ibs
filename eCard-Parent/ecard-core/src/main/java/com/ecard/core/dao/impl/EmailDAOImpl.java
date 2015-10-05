package com.ecard.core.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ecard.core.dao.EmailDAO;

import com.ecard.core.model.HistorySendEmail;

@Repository("emailDAO")
public class EmailDAOImpl extends GenericDao implements EmailDAO{
	public List<HistorySendEmail> getAllEmail(){
		Query query = getEntityManager().createQuery("SELECT c FROM HistorySendEmail c ");
		return (List<HistorySendEmail>) query.getResultList();
	}
}
