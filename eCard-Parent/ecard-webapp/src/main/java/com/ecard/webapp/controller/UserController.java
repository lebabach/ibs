package com.ecard.webapp.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.ecard.core.model.CardInfo;
import com.ecard.core.model.DownloadCsv;
import com.ecard.core.model.UserInfo;
import com.ecard.core.service.CardInfoService;
import com.ecard.core.service.UserInfoService;
import com.ecard.core.service.converter.CardInfoConverter;
import com.ecard.core.vo.CardInfoCSV;
import com.ecard.core.vo.CardInfoResponse;
import com.ecard.core.vo.CardInfoUserVo;
import com.ecard.core.vo.UserDownloadPermission;
import com.ecard.webapp.security.EcardUser;
import com.ecard.webapp.util.UploadFileUtil;
import com.ecard.webapp.vo.CardInfoPCVo;


@Controller
@RequestMapping("/user/*")
public class UserController {
		
	@Autowired
	UserInfoService userInfoService;
	
	@Autowired
	CardInfoService cardInfoService;
	
	@Value("${scp.hostname}")
	private String scpHostName;
	    
	@Value("${scp.user}")
    private String scpUser;
	    
	@Value("${scp.password}")
	private String scpPassword;
	    
	@Value("${scp.port}")
	private String scpPort;
	
	@RequestMapping("home") 
    public ModelAndView home() {	
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
		List<CardInfo> cardInfoDisp = new ArrayList<>();
		List<CardInfoPCVo>  lstCardInfoPCVo = new ArrayList<>();
		if (ecardUser != null) {
			List<String> lstNameSort = cardInfoService.getListSortType(ecardUser.getUserId());
			List<CardInfoUserVo> lstCardInfo = cardInfoService.getListPossesionCard(ecardUser.getUserId());
			
			for(String nameSort : lstNameSort) {
			    for(CardInfoUserVo cardInfo :lstCardInfo ){
			    	if(nameSort.trim().equals(cardInfo.getSortType().trim())){
			    		cardInfoDisp.add(cardInfo.getCardInfo());
			    	}
			    }
			    CardInfoPCVo cardInfoPCVo;
				try {
					cardInfoPCVo = new CardInfoPCVo(nameSort,CardInfoConverter.convertCardInforList(cardInfoDisp));
					lstCardInfoPCVo.add(cardInfoPCVo);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    
			}
			
		}
		return new ModelAndView("homePC", "lstCardInfoPCVo", lstCardInfoPCVo);
    }
	
	@RequestMapping("getImageFile") 
	@ResponseBody
	public String getFileImageSCP(@RequestParam(value = "fileImage") String fileImage){
		String fileNameFromSCP = UploadFileUtil.getImageFileFromSCP(fileImage, scpHostName, scpUser, scpPassword,Integer.parseInt(scpPort));
		return fileNameFromSCP;
	}
	
	@RequestMapping("download") 
    public ModelAndView DownloadCSV(HttpServletRequest request) {		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
		UserDownloadPermission permissionType = userInfoService.getPermisionDownloadByUserId(ecardUser.getUserId());
		List<DownloadCsv> downloadCSVHistory = userInfoService.getHistoryCSVDownload(ecardUser.getUserId());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("download");
		modelAndView.addObject("permissionType", permissionType);
		modelAndView.addObject("downloadCSVHistory", downloadCSVHistory);
		return modelAndView;
    }
	
	@RequestMapping(value = "/downloadCSV/{id:[\\d]+}",  method = RequestMethod.GET)
	@ResponseBody
	public void downloadCSV(HttpServletResponse response,@PathVariable("id") int id) throws IOException {		
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserInfo userInfo = userInfoService.findUserByEmail(authentication.getName());
			UserDownloadPermission permissionType = userInfoService.getPermisionDownloadByUserId(userInfo.getUserId());
			DownloadCsv downloadCsvId = new DownloadCsv();
			downloadCsvId.setUserInfo(userInfo);
			downloadCsvId.setCsvApprovalStatus(0);
			downloadCsvId.setApprovalDate(new Date());
			downloadCsvId.setRequestDate(new Date());

			String current = new SimpleDateFormat("ddMMyyyyhhmmss").format(new Date());
			String fileName = new String();
			List<CardInfoCSV> listUserInfoCSV;
			// If request all data
			if (id == 3 && permissionType.getDownloadAll() == 1) {
				List<CardInfo> listCardInfo = cardInfoService.listAllCardInfo();
				listUserInfoCSV = CardInfoConverter.convertCardInfoList(listCardInfo);
				// fileName = "グループの名刺をダウンロード_" + current+".csv";
				fileName = "AllCard_" + current + userInfo.getUserId() + ".csv";
				downloadCsvId.setCsvType(3);
				// waiting approval to download
				if (permissionType.getAllDataDlRequestFlg() != 1) {
					createCSVFile(response, fileName, listUserInfoCSV, 2);
					downloadCsvId.setCsvUrl(fileName);
					cardInfoService.saveDownloadHistory(downloadCsvId);
				} else {
					createCSVFile(response, fileName, listUserInfoCSV, 1);
				}
			}

			if (id == 2 && permissionType.getDownloadGroup() == 1) {
				// Download data with group
				listUserInfoCSV = null;
				if (StringUtils.isNotEmpty(userInfo.getCompanyName())) {
					List<CardInfo> listCardInfo = cardInfoService.getCompanyCard(userInfo.getGroupCompanyId());
					listUserInfoCSV = CardInfoConverter.convertCardInfoList(listCardInfo);
				} else {
					List<CardInfo> listCardInfo = cardInfoService.getListMyCard(userInfo.getUserId());
					listUserInfoCSV = CardInfoConverter.convertCardInfoList(listCardInfo);
				}
				fileName = "CompanyCard_" + current + userInfo.getUserId() + ".csv";
				downloadCsvId.setCsvType(2);
				if (permissionType.getGroupDataDlRequestFlg() != 1) {
					createCSVFile(response, fileName, listUserInfoCSV, 2);
					downloadCsvId.setCsvUrl(fileName);
					cardInfoService.saveDownloadHistory(downloadCsvId);
				} else {
					createCSVFile(response, fileName, listUserInfoCSV, 1);
				}
			}

			if (id == 1) {
				List<CardInfo> listCardInfo = cardInfoService.getListMyCard(userInfo.getUserId());
				listUserInfoCSV = CardInfoConverter.convertCardInfoList(listCardInfo);
				fileName = "MyCard_" + current + userInfo.getUserId() + ".csv";
				downloadCsvId.setCsvType(1);
				createCSVFile(response, fileName, listUserInfoCSV, 1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/downloadFileCSV/{fileName}",  method = RequestMethod.GET)
	@ResponseBody
	public void downloadFileCSV(HttpServletResponse response,@PathVariable("fileName") String fileName) throws IOException {		
		try {
			fileName = fileName + ".csv";
			// Connect to SCP and download File
			byte[] myBytes = UploadFileUtil.getFileCSVFromSCP(fileName, scpHostName, scpUser, scpPassword, Integer.parseInt(scpPort));
			// Set file response
			response.setContentType("application/force-download");
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", fileName);
			byte[] bufferData = new byte[1024];
			response.setHeader(headerKey, headerValue);
			ServletOutputStream os = response.getOutputStream();

			InputStream inputStream = new ByteArrayInputStream(myBytes);
			try {
				int c;
				while ((c = inputStream.read(bufferData)) != -1) {
					os.write(bufferData, 0, c);
				}
				os.flush();
			} finally {
				if (inputStream != null)
					inputStream.close();
				os.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void createCSVFile(HttpServletResponse response, String fileName,List<CardInfoCSV> listUserInfoCSV, Integer typeCSV) throws IOException{
		String csvFileName = fileName;
		// System.setProperty("user.data","/tmp/csv");

		String workingDirectory = System.getProperty("user.dir") + "/csv";
		// String workingDirectory = System.getProperty("/data") + "/csv";
		String absoluteFilePath = "";
		absoluteFilePath = workingDirectory + File.separator + csvFileName;

		String[] header = { "CardId", "companyName", "companyNameKana", "departmentName", "positionName", "lastName",
				"firstName", "lastNameKana", "firstNameKana", "email", "zipCode", "addressFull", "address1", "address2",
				"address3", "telNumberCompany", "telNumberDepartment", "telNumberDirect", "faxNumber", "mobileNumber",
				"companyUrl", "subAddressFull", "subZipCode", "subAddress1", "subAddress2", "subAddress3",
				"subTelNumberCompany", "subTelNumberDepartment", "subTelNumberDirect", "subFaxNumber", "memo1", "memo2",
				"createDate", "updateDate" };

		if (typeCSV == 1) {
			response.setContentType("text/csv");
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", csvFileName);
			response.setHeader(headerKey, headerValue);
			ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
			csvWriter.writeHeader(header);

			for (CardInfoCSV aCard : listUserInfoCSV) {
				csvWriter.write(aCard, header);
			}
			csvWriter.close();
		} else {
			response.setContentType("text/html");
			ICsvBeanWriter csvWriter = new CsvBeanWriter(new FileWriter(absoluteFilePath), CsvPreference.STANDARD_PREFERENCE);
			csvWriter.writeHeader(header);

			for (CardInfoCSV aCard : listUserInfoCSV) {
				csvWriter.write(aCard, header);
			}
			csvWriter.close();
		}
		
	}
	
	@RequestMapping("profile") 
    public ModelAndView profile() {	
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
		List<CardInfo> cardInfoDisp = new ArrayList<>();
		List<CardInfoPCVo>  lstCardInfoPCVo = new ArrayList<>();
		if (ecardUser != null) {
			List<String> lstNameSort = cardInfoService.getListSortType(ecardUser.getUserId());
			List<CardInfoUserVo> lstCardInfo = cardInfoService.getListPossesionCard(ecardUser.getUserId());
			
			for(String nameSort : lstNameSort) {
			    for(CardInfoUserVo cardInfo :lstCardInfo ){
			    	if(nameSort.trim().equals(cardInfo.getSortType().trim())){
			    		cardInfoDisp.add(cardInfo.getCardInfo());
			    	}
			    }
			    CardInfoPCVo cardInfoPCVo;
				try {
					cardInfoPCVo = new CardInfoPCVo(nameSort,CardInfoConverter.convertCardInforList(cardInfoDisp));
					lstCardInfoPCVo.add(cardInfoPCVo);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    
			}
			
		}
		return new ModelAndView("profile", "lstCardInfoPCVo", lstCardInfoPCVo);
    }
	
	@RequestMapping("contact") 
    public ModelAndView contact() {	
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
		List<CardInfo> cardInfoDisp = new ArrayList<>();
		List<CardInfoPCVo>  lstCardInfoPCVo = new ArrayList<>();
		if (ecardUser != null) {
			List<String> lstNameSort = cardInfoService.getListSortType(ecardUser.getUserId());
			List<CardInfoUserVo> lstCardInfo = cardInfoService.getListPossesionCard(ecardUser.getUserId());
			
			for(String nameSort : lstNameSort) {
			    for(CardInfoUserVo cardInfo :lstCardInfo ){
			    	if(nameSort.trim().equals(cardInfo.getSortType().trim())){
			    		cardInfoDisp.add(cardInfo.getCardInfo());
			    	}
			    }
			    CardInfoPCVo cardInfoPCVo;
				try {
					cardInfoPCVo = new CardInfoPCVo(nameSort,CardInfoConverter.convertCardInforList(cardInfoDisp));
					lstCardInfoPCVo.add(cardInfoPCVo);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    
			}
			
		}
		return new ModelAndView("contact-us", "lstCardInfoPCVo", lstCardInfoPCVo);
    }
	
	@RequestMapping("faq") 
    public ModelAndView faq() {	
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
		List<CardInfo> cardInfoDisp = new ArrayList<>();
		List<CardInfoPCVo>  lstCardInfoPCVo = new ArrayList<>();
		if (ecardUser != null) {
			List<String> lstNameSort = cardInfoService.getListSortType(ecardUser.getUserId());
			List<CardInfoUserVo> lstCardInfo = cardInfoService.getListPossesionCard(ecardUser.getUserId());
			
			for(String nameSort : lstNameSort) {
			    for(CardInfoUserVo cardInfo :lstCardInfo ){
			    	if(nameSort.trim().equals(cardInfo.getSortType().trim())){
			    		cardInfoDisp.add(cardInfo.getCardInfo());
			    	}
			    }
			    CardInfoPCVo cardInfoPCVo;
				try {
					cardInfoPCVo = new CardInfoPCVo(nameSort,CardInfoConverter.convertCardInforList(cardInfoDisp));
					lstCardInfoPCVo.add(cardInfoPCVo);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    
			}
			
		}
		return new ModelAndView("faq", "lstCardInfoPCVo", lstCardInfoPCVo);
    }
	
	@RequestMapping("mailbox") 
    public ModelAndView mailbox() {	
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
		List<CardInfo> cardInfoDisp = new ArrayList<>();
		List<CardInfoPCVo>  lstCardInfoPCVo = new ArrayList<>();
		if (ecardUser != null) {
			List<String> lstNameSort = cardInfoService.getListSortType(ecardUser.getUserId());
			List<CardInfoUserVo> lstCardInfo = cardInfoService.getListPossesionCard(ecardUser.getUserId());
			
			for(String nameSort : lstNameSort) {
			    for(CardInfoUserVo cardInfo :lstCardInfo ){
			    	if(nameSort.trim().equals(cardInfo.getSortType().trim())){
			    		cardInfoDisp.add(cardInfo.getCardInfo());
			    	}
			    }
			    CardInfoPCVo cardInfoPCVo;
				try {
					cardInfoPCVo = new CardInfoPCVo(nameSort,CardInfoConverter.convertCardInforList(cardInfoDisp));
					lstCardInfoPCVo.add(cardInfoPCVo);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    
			}
			
		}
		return new ModelAndView("mailbox", "lstCardInfoPCVo", lstCardInfoPCVo);
    }
}
