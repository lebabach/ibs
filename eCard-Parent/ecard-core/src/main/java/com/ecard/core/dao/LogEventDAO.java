package com.ecard.core.dao;

import java.util.List;

//import com.ecard.core.model.LoggingEvent;
import com.ecard.core.vo.LogEventVo;



public interface LogEventDAO {
	public List<LogEventVo> getAllLogEvent();
	public List<LogEventVo> searchLog(String criteriaSearch);
}
