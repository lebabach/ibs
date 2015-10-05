/*
 * CardInfo class
 */
package com.ecard.core.batch.beans.mapper;

import com.ecard.core.batch.beans.CardInfo;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author vinhla
 */
public class CardInfoMapper implements RowMapper<CardInfo> {
    
    public CardInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
        CardInfo cardInfo = new CardInfo();
        cardInfo.setUserId(Integer.parseInt(rs.getString("user_id")));
        cardInfo.setCardId(Integer.parseInt(rs.getString("card_id")));
        cardInfo.setName(rs.getString("name"));
        cardInfo.setCompanyName(rs.getString("company_name"));
        cardInfo.setPositionName(rs.getString("position_name"));
        cardInfo.setDepartmentName(rs.getString("department_name"));
        cardInfo.setTelNumberCompany(rs.getString("tel_number_company"));
        cardInfo.setAddress1(rs.getString("address_1"));
        cardInfo.setEmail(rs.getString("email"));
        
        return cardInfo;
    }
}
