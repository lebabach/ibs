/*
 * CardTagDAOImpl
 */
package com.ecard.core.dao.impl;

import com.ecard.core.dao.CardTagDAO;
import com.ecard.core.model.CardInfo;
import com.ecard.core.model.CardTag;
import com.ecard.core.model.CardTagId;
import com.ecard.core.model.UserTag;
import com.ecard.core.vo.CardTagAndCardInfo;
import com.ecard.core.vo.CardTagAndCompany;
import com.ecard.core.vo.MyCardAndUserInfo;
import com.ecard.core.vo.TagForCard;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.apache.commons.lang.Validate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author vinhla
 */
@Repository("cardTagDAO")
public class CardTagDAOImpl extends GenericDao implements CardTagDAO{
    
    public void addCardTag(CardTagId cardTag) {
        CardTag cardUserTag = new CardTag();
        UserTag userTag = new UserTag();
        CardInfo cardInfo = new CardInfo();
        userTag.setTagId(cardTag.getTagId());
        cardInfo.setCardId(cardTag.getCardId());
        
        cardUserTag.setId(cardTag);
        cardUserTag.setCardInfo(cardInfo);
        cardUserTag.setUserTag(null);
        
        getEntityManager().persist(cardUserTag);
        getEntityManager().flush();
    }
    
    public List<TagForCard> listCardTagByCardId(Integer userId) {
//        Query query = getEntityManager().createNativeQuery("SELECT ct.tag_id FROM card_tag AS ct WHERE ct.card_id = :cardId");
        
        Query query = getEntityManager().createNativeQuery("SELECT ut.user_id, ct.card_id, ut.tag_id, ut.tag_name " +
                    "FROM user_tag AS ut LEFT JOIN card_tag AS ct ON ut.tag_id = ct.tag_id " +
                    "WHERE ut.user_id IN (SELECT ut.user_id FROM card_tag AS ct INNER JOIN user_tag AS ut ON ct.tag_id = ut.tag_id WHERE ut.user_id = :userId)");
        query.setParameter("userId", userId);
        
        List<Object[]> rows = query.getResultList();
        List<TagForCard> result = new ArrayList<>(rows.size());
        for (Object[] row : rows) {
            result.add(new TagForCard((Integer)row[0],(Integer)row[1],(Integer)row[2],(String)row[3]));
        }
        return result;
    }
    
    public CardTagAndCompany listCardWhereTag(Integer tagId) {
        Validate.notNull(tagId, "tagId is not null");
        
//        String sqlStr = "SELECT NEW com.ecard.core.vo.CardTagAndCompany( ct.id.cardId, ut.tagName, c.name, c.companyName, c.departmentName, c.positionName, c.imageFile ) "
//                                + "FROM CardTag ct "
//                                + "LEFT JOIN ct.cardInfo c "
//                                + "LEFT JOIN ct.userTag ut "
//                                + "LEFT JOIN c.companyInfo co "
//                                + "WHERE co.companyId = :companyId "
//                                + "ORDER BY c.createDate DESC";
//        TypedQuery<CardTagAndCompany> query = getEntityManager().createQuery(sqlStr, CardTagAndCompany.class);
        Query query = getEntityManager().createNativeQuery("SELECT ct.card_id, ci.name, ci.last_name, ci.first_name, ci.name_kana, ci.last_name_kana, ci.first_name_kana, "
                + " ci.company_name, ci.department_name, ci.position_name, ci.image_file, ut.tag_name" 
                + " FROM card_info ci"
                + " INNER JOIN card_tag ct ON ct.card_id = ci.card_id"
                + " INNER JOIN user_tag ut ON ct.tag_id = ut.tag_id"
                + " WHERE ct.tag_id = :tagId");
        query.setParameter("tagId", tagId);
        
        List<Object[]> rows = query.getResultList();
        List<CardTagAndCardInfo> result = new ArrayList<>(rows.size());
        String tagName = new String();
        for (Object[] row : rows) {
            result.add(new CardTagAndCardInfo((Integer)row[0],(String)row[1],(String)row[2],(String)row[3],(String)row[4],(String)row[5],
                    (String)row[6],(String)row[7],(String)row[8],(String)row[9],(String)row[10]));
            tagName = (String)row[6];
        }
        CardTagAndCompany finalResult = new CardTagAndCompany(result,tagName);
        return finalResult;
    }
    
    public int deleteCardTag(Integer cardId, Integer tagId){
        Validate.notNull(cardId, "CardId is not null");
        Validate.notNull(tagId, "TagId is not null");
        
        Query query = getEntityManager().createQuery("DELETE FROM CardTag c WHERE c.id.cardId = :cardId AND c.id.tagId = :tagId");
        query.setParameter("cardId", cardId);
        query.setParameter("tagId", tagId);
        
        return query.executeUpdate();
    }
    
    public int deleteCardTagByTagId(Integer tagId){
        Validate.notNull(tagId, "TagId is not null");
        
        Query query = getEntityManager().createQuery("DELETE FROM CardTag c WHERE c.id.tagId = :tagId");
        query.setParameter("tagId", tagId);
     
        return query.executeUpdate();
    }
    
    public int registerCardTag(Integer cardId, Integer tagId){
        Validate.notNull(cardId, "CardId is not null");
        Validate.notNull(tagId, "TagId is not null");
        Query query = getEntityManager().createQuery("UPDATE CardTag ct "
                + " SET ct.id.cardId= :cardId"
                + " WHERE ct.id.tagId= :tagId");
        query.setParameter("cardId", cardId);
        query.setParameter("tagId", tagId);     
        int result = query.executeUpdate();
        return result;
        
    }
    
    public List<TagForCard> listUserTagByUserId(Integer userId){
        Query query = getEntityManager().createNativeQuery("SELECT ut.user_id, ct.card_id, ut.tag_id, ut.tag_name \n" +
                    "FROM user_tag AS ut LEFT JOIN card_tag AS ct ON ut.tag_id = ct.tag_id WHERE ut.user_id = :userId");
        query.setParameter("userId", userId);
        
        List<Object[]> rows = query.getResultList();
        List<TagForCard> result = new ArrayList<>(rows.size());
        for (Object[] row : rows) {
            result.add(new TagForCard((Integer)row[0],(Integer)row[1],(Integer)row[2],(String)row[3]));
        }
        return result;
    }
    
    public List<Integer> listCardIdByTagId(Integer tagId){
        Query query = getEntityManager().createNativeQuery("SELECT ct.card_id FROM card_tag AS ct WHERE ct.tag_id = :tagId");
        query.setParameter("tagId", tagId); 
        
        List<Object> rows = query.getResultList();
        List<Integer> result = new ArrayList<>(rows.size());
        for (Object row : rows) {
            result.add((Integer)row);
        }
        return result;
    }
}
