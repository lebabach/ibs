package com.ecard.core.dao.impl;

import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import com.ecard.core.dao.GroupCompanyInfoDAO;
import com.ecard.core.model.GroupCompanyDepartment;
import com.ecard.core.model.GroupCompanyInfo;
import com.ecard.core.vo.GroupDepartmentVO;

@Repository("groupCompanyInfoDAO")
public class GroupCompanyInfoDAOImpl  extends GenericDao implements GroupCompanyInfoDAO {

	@Override
	public List<GroupCompanyInfo> getListCompany() {
		// TODO Auto-generated method stub
		Query query = getEntityManager().createQuery("SELECT c FROM GroupCompanyInfo c ORDER BY c.groupCompanyId ASC");
		return (List<GroupCompanyInfo>) query.getResultList();
	}

	@Override
	public boolean delete(int companyId) {
		// TODO Auto-generated method stub
		Query q2 = getEntityManager().createNativeQuery("DELETE FROM group_company_info  WHERE group_company_id = :groupCompanyId");
		q2.setParameter("groupCompanyId", companyId);
		if(q2.executeUpdate()>0)
			return true;
		return false;
	}

	@Override
	public int add(GroupCompanyInfo companyInfo) {
		// TODO Auto-generated method stub
		EntityManager em = getEntityManager();
        em.persist(companyInfo);
        int companyId = companyInfo.getGroupCompanyId();
        em.close();
        if(companyId > 0)
        	return companyId;
        else
        	return 0;
	}

	@Override
	public GroupCompanyInfo getCompanyById(int id) {
		// TODO Auto-generated method stub
		Query query = getEntityManager().createQuery("SELECT c FROM GroupCompanyInfo c WHERE c.groupCompanyId = :id");
		query.setParameter("id", id);
		return   (GroupCompanyInfo) query.getResultList().get(0);
	}

	@Override
	public int addDepartment(GroupCompanyDepartment groupCompanyDepartment) {
		// TODO Auto-generated method stub
		EntityManager em = getEntityManager();
        em.persist(groupCompanyDepartment);
        em.close();
        return 0;
	}

	@Override
	public boolean deleteDepartment(int companyId) {
		// TODO Auto-generated method stub
		Query q2 = getEntityManager().createNativeQuery("DELETE FROM group_company_department  WHERE group_company_id = :groupCompanyId");
		q2.setParameter("groupCompanyId", companyId);
		if(q2.executeUpdate()>0)
			return true;
		return false;
	}

	@Override
	public List<GroupDepartmentVO> getCompanyDepartmentById(int id) {
		// TODO Auto-generated method stub
		Query query = getEntityManager().createNativeQuery("SELECT * FROM group_company_department c WHERE c.group_company_id = :id");
		query.setParameter("id", id);
		List<GroupDepartmentVO> lstgroupCompanyDepartmentVo = new ArrayList<>();
		List<Object[]> rows = query.getResultList();
		for (Object[] row : rows) {
			lstgroupCompanyDepartmentVo.add(new GroupDepartmentVO((Integer)row[0],(String)row[1],(Integer)row[2])  );
        }
        return lstgroupCompanyDepartmentVo;
		
	}

	@Override
	public int update(GroupCompanyInfo companyInfo) {
		 Query query = getEntityManager().createQuery("UPDATE GroupCompanyInfo c SET c.groupCompanyName = :groupCompanyName, c.groupCompanyNameKana= :groupCompanyNameKana,c.createDate =:createDate "
	                + " WHERE c.groupCompanyId = :groupCompanyId");
	        
	        query.setParameter("groupCompanyName", companyInfo.getGroupCompanyName());
	        query.setParameter("groupCompanyNameKana", companyInfo.getGroupCompanyNameKana());
	        query.setParameter("groupCompanyId", companyInfo.getGroupCompanyId());
	        query.setParameter("createDate", companyInfo.getCreateDate());
	        return query.executeUpdate();
	}

	@Override
	public List<GroupCompanyInfo> getListCompanyOfUser(int groupCompanyInfoId) {
		// TODO Auto-generated method stub
		Query query = getEntityManager().createQuery("SELECT c FROM GroupCompanyInfo c WHERE c.groupCompanyId = :groupCompanyId  ORDER BY c.groupCompanyId ASC");
		query.setParameter("groupCompanyId", groupCompanyInfoId);
		return (List<GroupCompanyInfo>) query.getResultList();
		
	}

}
