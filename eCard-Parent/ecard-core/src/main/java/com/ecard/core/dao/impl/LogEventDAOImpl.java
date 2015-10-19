package com.ecard.core.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ecard.core.dao.LogEventDAO;
//import com.ecard.core.model.LoggingEvent;
import com.ecard.core.vo.CardInfoConnectUser;
import com.ecard.core.vo.LogEventVo;

@Repository("logEventDAO")
public class LogEventDAOImpl extends GenericDao implements LogEventDAO {

	@Override
	public List<LogEventVo> getAllLogEvent() {
		// TODO Auto-generated method stub
		 Query query = getEntityManager().createNativeQuery("select user_id,action_type,action_message,action_date from action_log ");
	      
		 List<Object[]> rows = query.getResultList();
	     List<LogEventVo> result = new ArrayList<>(rows.size());
	     for (Object[] row : rows) {
	            result.add(new LogEventVo((int)row[0],(int) row[1],(String) row[2],(Date) row[3]));
	     }
	     
		return result;
	}

	@Override
	public List<LogEventVo> searchLog(String criteriaSearch) {
		// TODO Auto-generated method stub
		Query query = getEntityManager().createNativeQuery(" select user_id,action_type,action_message,action_date  "
				+ " FROM action_log "
				+ " WHERE action_type REGEXP :criteriaSearch"
				+ " OR action_message REGEXP :criteriaSearch "
				+ " OR action_date REGEXP :criteriaSearch");
		 query.setParameter("criteriaSearch",criteriaSearch.trim());
		 List<Object[]> rows = query.getResultList();
	     List<LogEventVo> result = new ArrayList<>(rows.size());
	     for (Object[] row : rows) {
	            result.add(new LogEventVo((int)row[0],(int) row[1],(String) row[2],(Date) row[3]));
	     }
	     
		return result;
	}

	@Override
	public boolean deleteLog() {
		Query q2 = getEntityManager().createNativeQuery(" DELETE FROM action_log WHERE action_date <  (NOW() - INTERVAL 1 YEAR)");
		if(q2.executeUpdate()>0)
			return true;
		return false;
	}

	@Override
	public boolean deleteLogCardUpdateHistory() {
		Query q2 = getEntityManager().createNativeQuery(" DELETE FROM card_update_history  WHERE create_date <  (NOW() - INTERVAL 1 YEAR)");
		if(q2.executeUpdate()>0)
			return true;
		return false;
	}

}
