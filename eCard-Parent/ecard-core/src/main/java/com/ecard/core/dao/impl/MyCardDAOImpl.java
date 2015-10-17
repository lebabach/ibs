/*
 * CardInfoDAOImpl class
 */
package com.ecard.core.dao.impl;

import com.ecard.core.dao.MyCardDAO;
import com.ecard.core.model.CardInfo;
import com.ecard.core.vo.MyCardAndCardInfo;
import com.ecard.core.vo.MyCardAndUserInfo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

/**
 *
 * @author vinhla
 */
@Repository("myCardDAO")
public class MyCardDAOImpl extends GenericDao implements MyCardDAO {
    @Value("${record.maxResult}")
    private Integer maxResult;
    
    public List<com.ecard.core.vo.CardInfo> listCardRecent(Integer userId) {

        String sqlStr = "SELECT DISTINCT(p.card_id), c.name, c.last_name, c.first_name, c.name_kana, c.last_name_kana, c.first_name_kana, \n" +
                        "c.company_name, c.department_name, c.position_name, c.image_file, c.approval_status, c.create_date,c.email,c.tel_number_company \n" +
                        "FROM prusal_history p INNER JOIN card_info c ON p.card_id = c.card_id \n" +
                        "WHERE p.user_id = :userId AND p.prusal_date >= (NOW() - INTERVAL 1 WEEK) \n" +
                        "AND c.approval_status = 1 \n" +
                        "AND c.delete_flg = 0 \n" +
                        "ORDER BY p.prusal_date DESC";
        Query query = getEntityManager().createNativeQuery(sqlStr);
        query.setParameter("userId", userId);
        List<Object[]> rows = query.getResultList();
        List<com.ecard.core.vo.CardInfo> result = new ArrayList<>(rows.size());
        for (Object[] row : rows) {
            result.add(new com.ecard.core.vo.CardInfo((int)row[0], (String)row[1], 
                    (String)row[2], (String)row[3], (String)row[4], (String)row[5], 
                    (String)row[6], (String)row[7], (String)row[8], (String)row[9], (String)row[10], (Integer)row[11],(Date)row[12],(String)row[13],(String)row[14]));
        }
        return result;
    }
    
    public List<MyCardAndCardInfo> listAllMyCard(Integer userId) {
        Validate.notNull(userId, "UserId not null");
        
        String queryStr ="SELECT NEW com.ecard.core.vo.MyCardAndCardInfo(mc.id.cardId, c.companyName, c.departmentName, c.positionName, c.imageFile, " 
                        + "mc.id.startDate, mc.id.endDate, c.approvalStatus, mc.id.seq) "
                        + "FROM MyCard AS mc LEFT JOIN mc.cardInfo c "
                        + "WHERE mc.id.userId = :userId";
        
        TypedQuery<MyCardAndCardInfo> query = getEntityManager().createQuery(queryStr, MyCardAndCardInfo.class);
        query.setParameter("userId", userId);
        query.setFirstResult(0);
        query.setMaxResults(this.maxResult);
        List<MyCardAndCardInfo> results = query.getResultList();
  
        return results;
    }
    
    public int updateMyCardSeq(Integer cardId, Integer seq) {
        Validate.notNull(cardId, "CardId is not null");
        Validate.notNull(seq, "Sequence is not null");
        
        Query query = getEntityManager().createQuery("UPDATE MyCard AS mc SET mc.id.seq = :seq WHERE mc.cardInfo.cardId = :cardId");
        query.setParameter("cardId", cardId);
        query.setParameter("seq", seq);
        
        return query.executeUpdate();
    }
    
    public int updateCardIdForPosCard(Integer cardId){
        Validate.notNull(cardId, "CardId is not null");
        Query query = getEntityManager().createQuery("UPDATE PossessionCard AS po SET po.id.cardId = :cardId");
        query.setParameter("cardId", cardId);
        return query.executeUpdate();
    }
    
    public int updateCardIdForPrusalHis(Integer cardId){
        Validate.notNull(cardId, "CardId is not null");
        Query query = getEntityManager().createQuery("UPDATE PrusalHistory AS p SET p.id.cardId = :cardId");
        query.setParameter("cardId", cardId);
        return query.executeUpdate();
    }
    
    public int updateCardIdForCardTag(Integer cardId){
        Validate.notNull(cardId, "CardId is not null");
        Query query = getEntityManager().createQuery("UPDATE CardTag AS c SET c.id.cardId = :cardId");
        query.setParameter("cardId", cardId);
        return query.executeUpdate();
    }
    
    public void registerMyCard(CardInfo cardInfo){
        getEntityManager().merge(cardInfo);
        getEntityManager().flush();
    }
    
    public int updateOldCardFlg(Integer cardId){
        Validate.notNull(cardId, "CardId is not null");
        Query query = getEntityManager().createQuery("UPDATE CardInfo AS c SET c.oldCardFlg = 1 "
                + "WHERE c.cardId = :cardId");
        query.setParameter("cardId", cardId);
        return query.executeUpdate();
    }
    
    public int updateNewCardFlg(Integer cardId){
        Validate.notNull(cardId, "CardId is not null");
        Query query = getEntityManager().createQuery("UPDATE CardInfo AS c SET c.oldCardFlg = 0 "
                + "WHERE c.cardId = :cardId");
        query.setParameter("cardId", cardId);
        return query.executeUpdate();
    }
    public List<MyCardAndUserInfo> getListMyCardById(Integer userId, Integer cardId){
        Validate.notNull(userId, "userId is not null");
        Validate.notNull(cardId, "CardId is not null");
        Query query = getEntityManager().createNativeQuery("SELECT mc.card_id, mc.user_id, mc.seq "
                + "FROM my_card mc WHERE mc.user_id = :userId AND mc.card_id = :cardId");
        query.setParameter("userId", userId);
        query.setParameter("cardId", cardId);
        
        List<Object[]> rows = query.getResultList();
        List<MyCardAndUserInfo> result = new ArrayList<>(rows.size());
        for (Object[] row : rows) {
            result.add(new MyCardAndUserInfo((Integer)row[0], (Integer)row[1], (Integer)row[2]));
        }
        return result;
    }
    
    public int deleteMyCard(Integer userId, Integer cardId){
        Validate.notNull(userId, "userId is not null");
        Validate.notNull(cardId, "CardId is not null");
        Query query = getEntityManager().createQuery("DELETE FROM MyCard mc WHERE mc.id.userId = :userId AND mc.id.cardId = :cardId");
        query.setParameter("userId", userId);
        query.setParameter("cardId", cardId);
        return query.executeUpdate();
    }
    
}
