/*
 * UserTagDAOImpl class
 */
package com.ecard.core.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ecard.core.dao.UserTagDAO;
import com.ecard.core.model.UserTag;
import com.ecard.core.vo.UserTagAndCardTag;
import java.math.BigInteger;
import java.util.ArrayList;
import org.apache.commons.lang.Validate;

/**
 *
 * @author vinhla
 */
@Repository("userTagDAO")
public class UserTagDAOImpl extends GenericDao implements UserTagDAO {

    public void editUserTag(UserTag userTag) {
    	getEntityManager().merge(userTag);
        getEntityManager().flush();       
    }
    
    public int registerUserTag(UserTag userTag){
    	getEntityManager().persist(userTag);
        getEntityManager().flush();                     
        return userTag.getTagId();
    }

    public int deleteUserTag(Integer tagId) {
        Validate.notNull(tagId, "TagId is not null");
        Query query = getEntityManager().createQuery("DELETE FROM UserTag u WHERE u.tagId = :tagId");
        query.setParameter("tagId", tagId);
        
        return query.executeUpdate();
    }

    public List<UserTagAndCardTag> getListUserTagByUserId(Integer userId) {
        Validate.notNull(userId, "userId is not null");
        Query query = getEntityManager().createNativeQuery("SELECT count(ct.tag_id) AS cardCount , ut.tag_id as tagId, ut.tag_name AS tagName " +
                        "FROM user_tag AS ut " +
                        "LEFT JOIN card_tag AS ct " +
                        "ON ut.tag_id = ct.tag_id " +
                        "WHERE ut.user_id = :userId " +
                        "GROUP BY ut.tag_id");
        query.setParameter("userId", userId);
        
        List<Object[]> rows = query.getResultList();
        List<UserTagAndCardTag> result = new ArrayList<>(rows.size());
        for (Object[] row : rows) {
            result.add(new UserTagAndCardTag((BigInteger)row[0], (Integer)row[1], (String)row[2]));
        }
        return result;
    }

    public Integer checkUserTag(Integer userId, String tagName){
    	Query query = getEntityManager().createQuery("SELECT u FROM UserTag u WHERE u.userInfo.userId = :userId AND u.tagName = :tagName");
        query.setParameter("userId", userId);
        query.setParameter("tagName", tagName);
        List<UserTag> utags =((List<UserTag>)query.getResultList());
        if(utags!=null && utags.size()>0){
        	UserTag usertag = utags.stream().findFirst().get();
        	return usertag.getTagId();
        }
        return 0;
        
    	
    }
    public List<UserTag> listUserTag() {
        Query query = getEntityManager().createQuery("SELECT ut FROM UserTag ut");
        return (List<UserTag>)query.getResultList();
    }
}
