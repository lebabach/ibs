/*
 * HomeController
 */
package com.ecard.webapp.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ecard.core.service.LogEventService;
import com.ecard.core.service.TeamInfoService;
import com.ecard.core.service.UserInfoService;
import com.ecard.core.vo.TeamDisInfo;


/**
 *
 * @author vinhla
 */
@Controller
@RequestMapping("/manager/*")
public class HomeController {  
    @Autowired
    TeamInfoService teamInfoService;
    
    @Autowired
	UserInfoService userInfoService;
    
    @Autowired
    LogEventService  logEventService;
    
    @RequestMapping("home") 
    public ModelAndView helloWorld(HttpSession session) {  
		List<TeamDisInfo> teamDisInfos = null;
		try {
			teamDisInfos = teamInfoService.getTeamInfo();
			for (TeamDisInfo teamInfo : teamDisInfos) {
				int processNumber = teamInfo.getProcessNumber().intValue();
				int numberMember = teamInfo.getMemberNumber().intValue();
				int percent = 0;
				if (processNumber > 0) {
					percent = numberMember * 100 / processNumber;
				}
				teamInfo.setStateProgress(percent);
			}
		} catch (Exception e) {
			teamDisInfos = null;
		}
		return new ModelAndView("menutop", "teamDisInfos", teamDisInfos);
    }  

}
