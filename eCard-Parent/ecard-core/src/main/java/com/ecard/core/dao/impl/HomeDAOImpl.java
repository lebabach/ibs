/*
 * HomeDAOImpl class
 */
package com.ecard.core.dao.impl;

import com.ecard.core.dao.HomeDAO;
import com.ecard.core.vo.CardInfoConnectUser;
import com.ecard.core.vo.CardInfoName;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

/**
 *
 * @author vinhla
 */
@Repository("homeDAO")
public class HomeDAOImpl extends GenericDao implements HomeDAO{
    @Value("${compliance.recordDate}")
    private String complianceDate;
    
    public CardInfoName getMyCardInfo(Integer userId) {
//        Query query = getEntityManager().createNativeQuery("SELECT ui.name FROM user_info AS ui WHERE ui.user_id = :userId");
        Query query = getEntityManager().createNativeQuery("SELECT ui.name, ui.last_name, ui.first_name," 
                + " ui.name_kana, ui.last_name_kana, ui.first_name_kana" 
                + " FROM user_info AS ui WHERE ui.user_id = :userId");
        query.setParameter("userId", userId);
        List<Object[]> rows = query.getResultList();
        List<CardInfoName> result = new ArrayList<>(rows.size());
        for (Object[] row : rows) {
            result.add(new CardInfoName((String)row[0], (String)row[1], (String)row[2], (String)row[3], (String)row[4], (String)row[5]));
        }
        return result.get(0);

//        return (Object[])query.getResultList().get(0);

    }
    
    public BigInteger countRecentCard(Integer userId) {
        String sqlStr = "SELECT count(DISTINCT c.card_id ) \n" +
                        "FROM prusal_history p INNER JOIN card_info c ON p.card_id = c.card_id \n" +
                        "WHERE p.user_id = :userId AND p.prusal_date >= (NOW() - INTERVAL 1 WEEK) \n" +
                        "AND c.approval_status = 1 \n" +
                        "AND c.delete_flg = 0 \n" +
                        "ORDER BY p.prusal_date DESC";
        
        Query query = getEntityManager().createNativeQuery(sqlStr);

        query.setParameter("userId", userId);
        return (BigInteger)getOrNull(query);
    }
    
    public Long countPossessionCard(Integer userId) {
        Query query = getEntityManager().createQuery("SELECT count(*) "
                + "FROM PossessionCard p, CardInfo c "
                + " WHERE p.id.cardId = c.cardId AND p.id.userId = :userId AND c.approvalStatus = 1");
        query.setParameter("userId", userId);
        return (Long)getOrNull(query);
    }
    
    public Long countInputWaitCard(Integer userId) {
//        Query query = getEntityManager().createQuery("SELECT count(*) FROM MyCard mc, CardInfo c WHERE mc.id.cardId = c.cardId AND mc.id.userId = :userId");
        Query query = getEntityManager().createQuery("SELECT count(DISTINCT d.id.cardId) FROM PossessionCard d "
                + " LEFT JOIN d.cardInfo c"
                + " WHERE c.approvalStatus != 1 and c.deleteFlg != 1 "
                + " AND d.id.userId = :userId ");
        query.setParameter("userId", userId);
        return (Long)getOrNull(query);
    }
    
    public BigInteger countConnectCard(Integer userId, Integer groupCompanyId) {
        
        String sqlStr = "SELECT  " +
                        "    COUNT(*) " +
                        "  FROM " +
                        "    ( " +
                        "     SELECT  " +
                        "          MC.card_id " +
                        "     FROM " +
                        "         ( " +
                        "          SELECT  " +
                        "               card_id " +
                        "              ,email " +
                        "              ,name " +
                        "              ,company_name, create_date " +                        
                        "            FROM  " +
                        "              card_info CI " +
                        "           WHERE  " +
                        "              CI.card_owner_id = :cardOwnerId " +
                        "          AND old_card_flg = 0  " +
                        "          AND approval_status = 1  " +
                        "          AND delete_flg = 0 " +
                        "     ) MC " +
                        "     INNER JOIN( " +
                        "         SELECT  " +
                        "              card_id " +
                        "             ,email " +
                        "             ,name " +
                        "             ,company_name, create_date " +                        
                        "           FROM " +
                        "             card_info CI " +
                        "          WHERE  " +
                        "             NOT CI.card_owner_id = :cardOwnerId " +
                        "         AND old_card_flg = 0  " +
                        "         AND approval_status = 1  " +
                        "         AND delete_flg = 0 ";
        if(groupCompanyId ==1 || groupCompanyId == 2 || groupCompanyId == 3 || groupCompanyId == 4 || groupCompanyId == 5){        
            sqlStr +=   "         AND ( " 
                       +"               group_company_id IN(1,2,3,4,5) " 
                       +"           OR (group_company_id NOT IN(1,2,3,4,5) AND contact_date >= '"+ this.complianceDate +"') " 
                       +"         ) ";
        }else{        
            sqlStr +=   "         AND ( " 
                       +"               group_company_id = :groupCompanyId "  
                       +"           OR (group_company_id <> :groupCompanyId AND contact_date >= '"+ this.complianceDate +"') " 
                       +"         ) ";
        }
            sqlStr +=   " ) CI " +
                        "     WHERE  " +
                        "         ( " +
                        "             (MC.email = CI.email AND MC.email <> '') " +
                        "          OR (MC.name = CI.name AND MC.company_name = CI.company_name) " +
                        "     ) " +                        
                        "     GROUP BY MC.card_id, MC.company_name, MC.name " +
                        "     HAVING count(*) >= 1 " +
                        ") TC";
        
        Query query = getEntityManager().createNativeQuery(sqlStr);
        query.setParameter("cardOwnerId", userId);
        if(groupCompanyId !=1 && groupCompanyId != 2 && groupCompanyId != 3 && groupCompanyId != 4 && groupCompanyId != 5){
            query.setParameter("groupCompanyId", groupCompanyId);
        }
                
        return (BigInteger)getOrNull(query);
    }
    
    public BigInteger countRecentConnectCard(Integer userId, Integer groupCompanyId) {
    	String sqlStr = "SELECT  " +
                        "    COUNT(*) " +
                        "  FROM " +
                        "    ( " +
                        "     SELECT  " +
                        "          MC.card_id " +
                        "     FROM " +
                        "         ( " +
                        "          SELECT  " +
                        "               card_id " +
                        "              ,email " +
                        "              ,name " +
                        "              ,company_name, create_date " +                        
                        "            FROM  " +
                        "              card_info CI " +
                        "           WHERE  " +
                        "              CI.card_owner_id = :cardOwnerId " +
                        "          AND old_card_flg = 0  " +
                        "          AND approval_status = 1  " +
                        "          AND delete_flg = 0 " +
                        "     ) MC " +
                        "     INNER JOIN( " +
                        "         SELECT  " +
                        "              card_id " +
                        "             ,email " +
                        "             ,name " +
                        "             ,company_name, create_date " +                        
                        "           FROM " +
                        "             card_info CI " +
                        "          WHERE  " +
                        "             NOT CI.card_owner_id = :cardOwnerId " +
                        "         AND old_card_flg = 0  " +
                        "         AND approval_status = 1  " +
                        "         AND delete_flg = 0 ";
        if(groupCompanyId ==1 || groupCompanyId == 2 || groupCompanyId == 3 || groupCompanyId == 4 || groupCompanyId == 5){        
            sqlStr +=   "         AND ( " 
                       +"               group_company_id IN(1,2,3,4,5) " 
                       +"           OR (group_company_id NOT IN(1,2,3,4,5) AND contact_date >= '"+ this.complianceDate +"') " 
                       +"         ) ";
        }else{        
            sqlStr +=   "         AND ( " 
                       +"               group_company_id = :groupCompanyId "  
                       +"           OR (group_company_id <> :groupCompanyId AND contact_date >= '"+ this.complianceDate +"') " 
                       +"         ) ";
        }
            sqlStr +=   " ) CI " +
                        "     WHERE  " +
                        "         ( " +
                        "             (MC.email = CI.email AND MC.email <> '') " +
                        "          OR (MC.name = CI.name AND MC.company_name = CI.company_name) " +
                        "     ) " +                        
                        "     AND (MC.create_date >=  (NOW() - INTERVAL 1 WEEK) OR CI.create_date >=  (NOW() - INTERVAL 1 WEEK)) "+
                        "     GROUP BY MC.card_id, MC.company_name, MC.name " +
                        "     HAVING count(*) >= 1 " +
                        ") TC";
    	
        Query query = getEntityManager().createNativeQuery(sqlStr);
        query.setParameter("cardOwnerId", userId);
        if(groupCompanyId !=1 && groupCompanyId != 2 && groupCompanyId != 3 && groupCompanyId != 4 && groupCompanyId != 5){
            query.setParameter("groupCompanyId", groupCompanyId);
        }
                
        return (BigInteger)getOrNull(query);
    }
    
    public BigInteger countNotificationCard(Integer userId) {
        Query query = getEntityManager().createNativeQuery("SELECT count(*) " 
                    + " FROM user_notification AS un " 
                    + " WHERE un.user_id = :userId AND un.read_flg = 0");
        query.setParameter("userId", userId);
        return (BigInteger)getOrNull(query);
    }
    
    public BigInteger countRecentPossessionCard(Integer userId){
       
        String sqlStr = "SELECT COUNT(*) FROM card_info "
                + "WHERE old_card_flg = 0 AND approval_status = 1 AND delete_flg = 0 "
                + "AND create_date >= (NOW() - INTERVAL 1 WEEK) AND card_owner_id = :userId "
                + "ORDER BY create_date DESC";
        
        Query query = getEntityManager().createNativeQuery(sqlStr);
        
        query.setParameter("userId", userId);
        return (BigInteger)getOrNull(query);
    }
}
