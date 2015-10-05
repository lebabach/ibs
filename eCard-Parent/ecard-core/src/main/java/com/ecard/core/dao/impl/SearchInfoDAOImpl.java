/*
 * SearchInfoDAOImpl
 */
package com.ecard.core.dao.impl;

import com.ecard.core.dao.SearchInfoDAO;
import com.ecard.core.model.UserSearch;
import com.ecard.core.vo.SearchInfo;
import java.util.Arrays;
import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author vinhla
 */
@Repository("searchInfoDAO")
public class SearchInfoDAOImpl extends GenericDao implements SearchInfoDAO{
    
    public Integer registerSearchText(UserSearch searchInfo) {
        // Check
        // userId and seq 
        Query query = getEntityManager().createQuery("UPDATE UserSearch AS us"+
                " SET us.freeText = :freeText, us.title = :title,"+
                " us.owner = :owner , us.company = :company , us.department =:department, "+
                " us.position =:position, us.name=:name, us.parameterFlg = :parameterFlg"+
                " WHERE us.userId = :userId AND us.seq = :seq");
        query.setParameter("freeText", searchInfo.getFreeText());
        query.setParameter("title", searchInfo.getTitle());
        query.setParameter("owner", searchInfo.getOwner());
        query.setParameter("company", searchInfo.getCompany());
        query.setParameter("department", searchInfo.getDepartment());
        query.setParameter("position", searchInfo.getPosition());
        query.setParameter("name", searchInfo.getName());
        query.setParameter("userId", searchInfo.getUserId());
        query.setParameter("seq", searchInfo.getSeq());
        query.setParameter("parameterFlg", searchInfo.getParameterFlg());
        int result = query.executeUpdate();
        return result;
    }
    
    public void createSearchText(UserSearch searchInfo){
        getEntityManager().persist(searchInfo);
        getEntityManager().flush();
    }
    
    public List<SearchInfo> listSearchText(Integer userId) {
        Query query = getEntityManager().createQuery("SELECT new com.ecard.core.vo.SearchInfo(us.freeText, us.title, us.owner, us.company, us.department, us.position, us.name, us.seq, us.parameterFlg ) "
                + " FROM UserSearch AS us "
                + " WHERE us.userId= :userId"
                + " ORDER BY us.seq");
        query.setParameter("userId", userId);        
        return query.getResultList();
    }
    
    public void deleteSearchText(Integer userId, List<Integer> seqArray){
        Query query = getEntityManager().createQuery("DELETE FROM UserSearch "
                + "WHERE userId= :userId AND seq IN (:seqArray)");        
        query.setParameter("userId",userId);
        query.setParameter("seqArray", seqArray);
        query.executeUpdate();
    }
}
