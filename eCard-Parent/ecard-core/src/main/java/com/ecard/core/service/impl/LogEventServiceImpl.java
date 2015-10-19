package com.ecard.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecard.core.dao.LogEventDAO;
import com.ecard.core.service.LogEventService;
import com.ecard.core.vo.LogEventVo;

@Service("logEventService")
@Transactional
public class LogEventServiceImpl implements LogEventService {
    
	@Autowired
	LogEventDAO logEventDAO;
	
	@Override
	public List<LogEventVo> getAllLogEvent() {
		return logEventDAO.getAllLogEvent();
	}

	@Override
	public List<LogEventVo> searchLog(String criteriaSearch) {
		return logEventDAO.searchLog(criteriaSearch);
	}

	@Override
   public boolean deleteLog() {
		return logEventDAO.deleteLog();
	}

	@Override
	public boolean deleteLogCardUpdateHistory() {
		return logEventDAO.deleteLogCardUpdateHistory();
	}

}
