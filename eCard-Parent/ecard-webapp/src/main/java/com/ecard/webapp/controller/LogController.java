package com.ecard.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ecard.core.service.LogEventService;
import com.ecard.core.vo.LogEventVo;

@Controller
@RequestMapping("/logs/*")
public class LogController {

	 @Autowired
	 LogEventService  logEventService;
	
	@RequestMapping("listlog")
	public ModelAndView searchUser(@RequestParam(value = "criteriaSearch", required = false) String criteriaSearch) {
		
		List<LogEventVo> listLogEventVo = null;
		if (criteriaSearch == null || criteriaSearch.isEmpty()){
			listLogEventVo = logEventService.getAllLogEvent();
		}else{
			listLogEventVo = logEventService.searchLog(criteriaSearch);
		}				
	  return new  ModelAndView("listlog","listLogEventVo",listLogEventVo);
		
    } 
}
