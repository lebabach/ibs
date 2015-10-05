/*
 * CardInfoCountMapper
 */
package com.ecard.core.batch.beans.mapper;

import com.ecard.core.batch.beans.CardInfoCount;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author vinhla
 */
public class CardInfoCountMapper implements RowMapper<CardInfoCount> {
    
    public CardInfoCount mapRow(ResultSet rs, int rowNum) throws SQLException {
        CardInfoCount cardCnt = new CardInfoCount();
        cardCnt.setCardCnt(rs.getLong("cnt"));
        
        return cardCnt;
    }
    
}
