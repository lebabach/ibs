package com.ecard.core.service;

import java.util.List;

import com.ecard.core.vo.LogEventVo;;
public interface LogEventService  {
	
	public List<LogEventVo> getAllLogEvent();
	public List<LogEventVo> searchLog(String criteriaSearch);

}
