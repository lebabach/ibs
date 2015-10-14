/*
 * CardUpdateHistoryDAOImpl
 */
package com.ecard.core.dao.impl;

import com.ecard.core.dao.CardUpdateHistoryDAO;
import com.ecard.core.model.CardInfo;
import com.ecard.core.model.enums.CardParamType;
import com.ecard.core.model.enums.SearchConditions;
import com.ecard.core.vo.CardUpdateHisAndUserInfo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author vinhla
 */
@Repository("cardUpdateHistoryDAO")
public class CardUpdateHistoryDAOImpl extends GenericDao implements CardUpdateHistoryDAO {
    
    public List<CardInfo> listAllCardHistory(String sort) {
        String sqlStr = "SELECT DISTINCT(chris.cardInfo) FROM CardUpdateHistory chris "
                        + "LEFT JOIN chris.cardInfo c ";
        Query query = getEntityManager().createQuery(sqlStr);
        
        if(sort.equals(StringUtils.lowerCase(SearchConditions.POSITION.name()))) {
            sqlStr += "ORDER BY c.positionName DESC";
        }
        else if(sort.equals(SearchConditions.NAME.name())) {
            sqlStr += "ORDER BY c.name DESC";
        }
        else if(sort.equals(SearchConditions.COMPANY.name())) {
            sqlStr += "ORDER BY c.companyName DESC";
        }
        else if(sort.equals(SearchConditions.UPDATE_DATE.name())) {
            sqlStr += "ORDER BY c.updateDate DESC";
        }
        
        return query.getResultList();
    }
    
    public List<CardUpdateHisAndUserInfo> getListCardUpdateHistory(Integer cardId){
        Validate.notNull(cardId, "cardId is not null");
        
        Query query = getEntityManager().createNativeQuery("SELECT ca.card_id, ca.param_type, ca.old_data, ca.new_data, ca.create_date, ca.operater_id, u.name "
                                    + "FROM card_update_history ca INNER JOIN user_info u ON ca.operater_id = u.user_id "
                                    + "WHERE ca.card_id = :cardId");
        query.setParameter("cardId", cardId);
        
        List<Object[]> rows = query.getResultList();
        List<CardUpdateHisAndUserInfo> result = new ArrayList<>(rows.size());
        
        String paramText = "";
        for (Object[] row : rows) {
            switch((Integer)row[1]){
                case 1 :
                    paramText = CardParamType.COMPANY_NAME.name();
                    break;
                case 2 :
                    paramText = CardParamType.POST.name();
                    break;
                case 3 :
                    paramText = CardParamType.DIVISION_NAME.name();
                    break;
                case 4 :
                    paramText = CardParamType.PHONE_NUMBER.name();
                    break;
                case 5 :
                    paramText = CardParamType.STREET_ADDRESS.name();
                    break;
                case 6 :
                    paramText = CardParamType.EMAIL_ADDRESS.name();
                    break;
                default :
                    paramText = CardParamType.NAME.name();
                    break;
            };
            result.add(new CardUpdateHisAndUserInfo((Integer)row[0], paramText, (String)row[2], (String)row[3], (Date)row[4], (Integer)row[5], (String)row[6]));
        }
        
        return result;
    }

    public void registerCardUpdateHistory(CardUpdateHisAndUserInfo cardUpdateHis){
    	getEntityManager().persist(cardUpdateHis);
    	getEntityManager().flush();
    }
}
