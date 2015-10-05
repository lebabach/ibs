/*
 * PossessionCardDAOImpl
 */
package com.ecard.core.dao.impl;

import org.springframework.stereotype.Repository;

import com.ecard.core.dao.PossessionCardDAO;
import com.ecard.core.model.PossessionCard;
import com.ecard.core.model.enums.SearchConditions;
import com.ecard.core.vo.PosessionCardInfo;
import java.util.List;
import javax.persistence.Query;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

/**
 *
 * @author vinhla
 */
@Repository("possessionCardDAO")
public class PossessionCardDAOImpl extends GenericDao implements PossessionCardDAO{

    public void registerPosCard(PossessionCard possessionCard) {
    	getEntityManager().persist(possessionCard);
        getEntityManager().flush();
    }
    public List<PossessionCard> listPossesionCard(String searchText, String sort) {
        String sqlStr = "SELECT p.cardInfo.cardId, c.name, c.companyName, c.departmentName, c.positionName, c.imageFile "
                            + "FROM PossessionCard p LEFT JOIN CardInfo c ON p.cardInfo.cardId = c.cardId ";
        
        if(StringUtils.lowerCase(SearchConditions.NAME.name()).equals(sort)) {
            sqlStr += "ORDER BY c.name DESC ";
        }
        else if(StringUtils.lowerCase(SearchConditions.COMPANY.name()).equals(sort)) {
            sqlStr += "ORDER BY c.companyName DESC ";
        }
        else if(StringUtils.lowerCase(SearchConditions.POSITION.name()).equals(sort)) {
            sqlStr += "ORDER BY c.positionName DESC ";
        }
        else if(StringUtils.lowerCase(SearchConditions.UPDATE_DATE.name()).equals(sort)) {
            sqlStr += "ORDER BY c.updateDate DESC ";
        }
        
        sqlStr += "WHERE c.companyName = :searchText";
        Query query = getEntityManager().createQuery(sqlStr);
        query.setParameter("searchText", searchText);
        
        return query.getResultList();
    }
    
    public List<PosessionCardInfo> listCardConnect(Integer userId, Integer cardId) {
        Validate.notNull(cardId, "cardId is not null");
        
        Query query = getEntityManager().createNativeQuery("SELECT ca.user_id AS user_id, ca.card_id AS card_id, ca.seq AS seq, c.name AS name, "
                        + "c.position_name AS position_name, c.department_name AS department_name, c.company_name AS company_name, "
                        + "c.image_file AS image_file, c.tel_number_company AS tel_number_company "
                        + "FROM " 
                        + "(" 
                        + "SELECT mc.card_id, mc.user_id, mc.seq FROM my_card mc " 
                        + "UNION " 
                        + "SELECT p.card_id, p.user_id, p.create_date FROM possession_card p " 
                        + ") ca " 
                        + "LEFT JOIN card_info c "
                        + "ON ca.card_id = c.card_id " 
                        + "WHERE ca.user_id = :userId AND ca.card_id = :cardId AND ca.seq=1");
        query.setParameter("userId", userId);
        query.setParameter("cardId", cardId);
        return query.getResultList();
    }
    
    public List<PossessionCard> getListPossessionCardById(Integer userId, Integer cardId) {
        Validate.notNull(userId, "userId is not null");
        Validate.notNull(cardId, "cardId is not null");
        
        Query query = getEntityManager().createQuery("SELECT po FROM PossessionCard po WHERE po.id.userId = :userId AND po.id.cardId = :cardId");
        query.setParameter("userId", userId);
        query.setParameter("cardId", cardId);
        return query.getResultList();
    }
    
    public int deletePossessionCard(Integer userId, Integer cardId){
        Validate.notNull(userId, "userId is not null");
        Validate.notNull(cardId, "cardId is not null");
        
        Query query = getEntityManager().createQuery("DELETE FROM PossessionCard po WHERE po.id.cardId = :cardId AND po.id.userId = :userId");
        query.setParameter("userId", userId);
        query.setParameter("cardId", cardId);
        return query.executeUpdate();
    }
    
    public String getUserEmailByCardId(Integer cardId){
        Validate.notNull(cardId, "cardId is not null");
        
        Query query = getEntityManager().createQuery("SELECT u.email FROM PossessionCard po INNER JOIN po.userInfo u "
                        + "WHERE u.mailSendFlg = 1 AND po.id.cardId = :cardId");
        query.setParameter("cardId", cardId);
        
        return (String)getOrNull(query);
    }
    
    public Integer getUserIdByCardId(Integer cardId){
        Query query = getEntityManager().createQuery("SELECT po.id.userId FROM PossessionCard po "
                + " WHERE po.id.cardId = :cardId"
                + " ORDER BY po.id.userId");
        query.setParameter("cardId", cardId);
        if(query.getResultList().size() == 0)
            return 0;
        return (Integer) query.getResultList().get(0);
    }
}
