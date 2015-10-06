package com.ecard.core.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;import javax.persistence.LockModeType;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ecard.core.dao.CompanyInfoDAO;
import com.ecard.core.model.CompanyInfo;

@Repository("companyInfoDAO")
public class CompanyInfoDAOImpl extends GenericDao implements CompanyInfoDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<CompanyInfo> findAll() {
		Query query = getEntityManager().createQuery("SELECT c FROM CompanyInfo c");
		return (List<CompanyInfo>) query.getResultList();
	}

	@Override
	public boolean add(CompanyInfo companyInfo) {
		EntityManager em = getEntityManager();
        em.persist(companyInfo);
        int id = companyInfo.getCompanyId();
        em.close();
        if(id > 0)
        	return true;
        else
        	return false;
	}

	@Override
	public boolean delete(int companyID) {
		Query q2 = getEntityManager().createQuery("DELETE FROM CompanyInfo c WHERE c.companyId = :companyId");
		q2.setParameter("companyId", companyID);
		if(q2.executeUpdate()>0)
			return true;
		return false;
	}

}
