package com.ecard.webapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ecard.core.model.DownloadCsv;
import com.ecard.core.model.UserInfo;
import com.ecard.core.service.UserInfoService;
import com.ecard.core.vo.CSVDownloadInfo;
import com.ecard.core.vo.UserInfoVo;
import com.ecard.webapp.security.EcardUser;
import com.ecard.webapp.security.RoleType;

@Controller
@RequestMapping("/download/*")
public class DownloadController {
	@Autowired
	UserInfoService userInfoService;
	
	@RequestMapping("list") 
    public ModelAndView listDownloadCSV(HttpServletRequest request) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		
		//find user with email to get groupCompanyId
		UserInfo userOperatorInfo = userInfoService.findUserByEmail(authentication.getName());
		List<UserInfoVo> lstuserInfo = userInfoService.searchUserOfMyCompany("", -1, -1,userOperatorInfo.getGroupCompanyId());
		List<Integer> lstUserId = new ArrayList<>();
		for (UserInfoVo userInfo : lstuserInfo) {
			lstUserId.add(userInfo.getUserId());
		}
		List<DownloadCsv> downloadCSVHistory = null;
		if (roles.contains(RoleType.ROLE_ADMIN.name())) {
			downloadCSVHistory = userInfoService.getAllHistoryDownload();
		} else {
			downloadCSVHistory = userInfoService.getAllHistoryDownloadByCompany(lstUserId);
		}

		List<CSVDownloadInfo> downloadGroupCSV = new ArrayList<CSVDownloadInfo>();
		List<CSVDownloadInfo> downloadAllCSV = new ArrayList<CSVDownloadInfo>();

		for (DownloadCsv csv : downloadCSVHistory) {
			CSVDownloadInfo downloadInfo = new CSVDownloadInfo();
			UserInfo userInfo = new UserInfo();
			userInfo = userInfoService.getUserInfoByUserId(csv.getUserInfo().getUserId());

			downloadInfo.setUserId(csv.getCsvId());
			downloadInfo.setCsvId(csv.getCsvId());
			downloadInfo.setUserName(userInfo.getLastName() + " " + userInfo.getFirstName());
			downloadInfo.setCompanyName(userInfo.getCompanyName());
			downloadInfo.setDepartmentName(userInfo.getDepartmentName());
			downloadInfo.setPositionName(userInfo.getPositionName());

			downloadInfo.setApprovalDate(csv.getApprovalDate());
			downloadInfo.setCsvApprovalStatus(csv.getCsvApprovalStatus());
			downloadInfo.setRequestDate(csv.getRequestDate());
			downloadInfo.setCsvType(csv.getCsvType());
			downloadInfo.setCsvUrl(csv.getCsvUrl());
			downloadInfo.setOperaterId(csv.getOperaterId());
			downloadInfo.setOperaterName(userOperatorInfo.getLastName() + " " + userOperatorInfo.getFirstName());
			// downloadInfo.setOperaterId(csv.getOperaterId());
			if (downloadInfo.getCsvType() == 2)
				downloadGroupCSV.add(downloadInfo);
			if (downloadInfo.getCsvType() == 3)
				downloadAllCSV.add(downloadInfo);
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("downloadlist");
		modelAndView.addObject("downloadGroupCSVHistory", downloadGroupCSV);
		modelAndView.addObject("downloadAllCSVHistory", downloadAllCSV);
		return modelAndView;
    }
	
	@RequestMapping(value="recognitionDownload" , method = RequestMethod.POST)
	@ResponseBody
    public Integer recognitionDownload(@RequestParam("csvId") Integer csvId,@RequestParam("csvStatus") int csvStatus) {
		EcardUser ecardUser = (EcardUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		boolean result = userInfoService.updateRecognitionDownload(csvId, csvStatus,ecardUser.getUserId());
		if (result == true)
			return 200;
		else
			return 204;
    }
    
    @RequestMapping("managerDownloadCSV") 
    public ModelAndView managerDownloadCSV(HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("managerDownloadCSV");
		return modelAndView;
    }
}
