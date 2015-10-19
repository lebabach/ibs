package com.ecard.webapp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.ecard.core.service.LogEventService;
import com.ecard.core.service.UserInfoService;
import com.ecard.core.vo.ActionLogDownloadVo;
import com.ecard.core.vo.CardUpdateHistoryDownloadVo;
import com.ecard.core.vo.LogEventVo;

@Controller
@RequestMapping("/logs/*")
public class LogController {

	@Autowired
	LogEventService  logEventService;
	
	@Autowired
	UserInfoService userInfoService;
	
	@RequestMapping("listlog")
	public ModelAndView searchUser(@RequestParam(value = "criteriaSearch", required = false) String criteriaSearch) {
		
		List<LogEventVo> listLogEventVo = null;
		logEventService.deleteLog();
		logEventService.deleteLogCardUpdateHistory();
		if (criteriaSearch == null || criteriaSearch.isEmpty()){
			listLogEventVo = logEventService.getAllLogEvent();
		}else{
			listLogEventVo = logEventService.searchLog(criteriaSearch);
		}		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("criteriaSearch",criteriaSearch);
		modelAndView.addObject("listLogEventVo", listLogEventVo);
		modelAndView.setViewName("listlog");
	  return modelAndView;
		
    }
	
	@RequestMapping(value = "actionlogdownload", method = RequestMethod.GET)	

	public void actionLogDownload(HttpServletResponse response) throws IOException {
		
		String[] header = { "userId", "actionType", "actionMessage", "actionDate" };
		
		List<ActionLogDownloadVo> listActionLogDownload = userInfoService.getListActionLog();
		
		String fileName = "action_log"+".csv";
		
		response.setContentType("application/force-download");
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", fileName);
		response.setCharacterEncoding("UTF-8");
		response.setHeader(headerKey, headerValue);
		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		csvWriter.writeHeader(header);

		for (ActionLogDownloadVo aCard : listActionLogDownload) {
			csvWriter.write(aCard, header);
		}
		csvWriter.close();
		
	}
	
	@RequestMapping(value = "updatecarddownload", method = RequestMethod.GET)	
	public void updateCardDownload(HttpServletResponse response) throws IOException {

		String[] header = { "cardId", "paramType", "oldData", "newData", "createDate", "updateDate" , "operaterId"};
		
		List<CardUpdateHistoryDownloadVo> listCardUpdateHistoryDownloadVo = userInfoService.getListCardUpdateHistory();
		
		String fileName = "card_history_log"+".csv";
		
		response.setContentType("application/force-download");
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", fileName);
		response.setCharacterEncoding("UTF-8");
		response.setHeader(headerKey, headerValue);
		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		csvWriter.writeHeader(header);

		for (CardUpdateHistoryDownloadVo aCard : listCardUpdateHistoryDownloadVo) {
			csvWriter.write(aCard, header);
		}
		csvWriter.close();
		
	}
}
