package com.ecard.webapp.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
import org.thymeleaf.context.Context;

import com.ecard.core.model.ActionLog;
import com.ecard.core.model.ActionLogId;
import com.ecard.core.model.CardInfo;
import com.ecard.core.model.CardTagId;
import com.ecard.core.model.CompanyInfo;
import com.ecard.core.model.ContactHistory;
import com.ecard.core.model.DownloadCsv;
import com.ecard.core.model.InquiryInfo;
import com.ecard.core.model.OldCard;
import com.ecard.core.model.OldCardId;
import com.ecard.core.model.PossessionCard;
import com.ecard.core.model.PossessionCardId;
import com.ecard.core.model.PrusalHistory;
import com.ecard.core.model.PrusalHistoryId;
import com.ecard.core.model.UserCardMemoId;
import com.ecard.core.model.UserInfo;
import com.ecard.core.model.UserNotification;
import com.ecard.core.model.UserSearch;
import com.ecard.core.model.UserTag;
import com.ecard.core.model.enums.ActionLogType;
import com.ecard.core.model.enums.SearchConditions;
import com.ecard.core.service.CardInfoService;
import com.ecard.core.service.CardMemoService;
import com.ecard.core.service.CardTagService;
import com.ecard.core.service.ContactHistoryService;
import com.ecard.core.service.EmailService;
import com.ecard.core.service.GroupCompanyInfoService;
import com.ecard.core.service.LogEventService;
import com.ecard.core.service.MyCardService;
import com.ecard.core.service.NotificationInfoService;
import com.ecard.core.service.OldCardService;
import com.ecard.core.service.PossessionCardService;
import com.ecard.core.service.SearchInfoService;
import com.ecard.core.service.SettingsInfoService;
import com.ecard.core.service.UserCardMemoService;
import com.ecard.core.service.UserInfoService;
import com.ecard.core.service.UserTagService;
import com.ecard.core.service.converter.CardInfoConverter;
import com.ecard.core.vo.CardAndUserTag;
import com.ecard.core.vo.CardConnectModel;
import com.ecard.core.vo.CardInfoAndPosCard;
import com.ecard.core.vo.CardInfoCSV;
import com.ecard.core.vo.CardInfoConnectUser;
import com.ecard.core.vo.CardInfoConnectUserResponse;
import com.ecard.core.vo.CardInfoMemo;
import com.ecard.core.vo.CardInfoUserVo;
import com.ecard.core.vo.NotificationList;
import com.ecard.core.vo.SearchInfo;
import com.ecard.core.vo.TagForCard;
import com.ecard.core.vo.TagGroup;
import com.ecard.core.vo.UserInfoVo;
import com.ecard.core.vo.UserTagAndCardTag;
import com.ecard.webapp.constant.CommonConstants;
import com.ecard.webapp.constant.CsvConstant;
import com.ecard.webapp.security.EcardUser;
import com.ecard.webapp.util.FileUploadModel;
import com.ecard.webapp.util.StringUtilsHelper;
import com.ecard.webapp.util.UploadFileUtil;
import com.ecard.webapp.vo.CardAndUserTagHome;
import com.ecard.webapp.vo.CardInfoAndPosCardVO;
import com.ecard.webapp.vo.CardInfoLoadMoreVO;
import com.ecard.webapp.vo.CardInfoPCVo;
import com.ecard.webapp.vo.CardInfoSaleforce;
import com.ecard.webapp.vo.DataPagingJsonVO;
import com.ecard.webapp.vo.ListCardDelete;
import com.ecard.webapp.vo.ObjectCards;
import com.ecard.webapp.vo.ObjectListSearchUsers;
import com.ecard.webapp.vo.OwnerCards;
import com.ecard.webapp.vo.TagUserHome;
import com.ecard.webapp.vo.UserInfoVO;
import com.ecard.webapp.vo.UserSearchVO;
import com.google.gson.Gson;

@Controller
@RequestMapping("/user/*")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private String[] header = { "cardIndexNo", "companyName", "companyNameKana", "departmentName", "positionName", "lastName",
			"firstName", "lastNameKana", "firstNameKana", "email", "zipCode", "addressFull", "address1", "address2",
			"address3", "telNumberCompany", "telNumberDepartment", "faxNumber", "mobileNumber",
			"companyUrl", "contactDate" };
	private String[] headerJapanse = { "名刺採番", "会社名", "会社名カナ", "部署名", "役職名", "氏名（姓）",
			"氏名（名）", "氏名（姓）カナ", "氏名（名）カナ", "メール", "郵便番号", "住所", "住所１", "住所２",
			"住所３", "電話番号１", "電話番号２ ", "FAX番号", "携帯電話番号",
			"会社URL", "名刺交換日" };
	@Autowired
	UserInfoService userInfoService;

	@Autowired
	CardInfoService cardInfoService;

	@Autowired
	UserTagService userTagService;

	@Autowired
	CardTagService cardTagService;

	@Autowired
	CardMemoService cardMemoService;

	@Autowired
	PossessionCardService possessionCardService;

	@Autowired
	GroupCompanyInfoService groupCompanyInfoService;

	@Autowired
	ContactHistoryService contactHistoryService;

	@Autowired
	EmailService emailService;

	@Autowired
	LogEventService logEventService;
	
	@Autowired
    MyCardService myCardService;

	@Value("${mail.server.from}")
	private String fromUser;

	@Value("${scp.hostname}")
	private String scpHostName;

	@Value("${scp.user}")
	private String scpUser;

	@Value("${scp.password}")
	private String scpPassword;

	@Value("${scp.port}")
	private String scpPort;

	@Value("${compliace.date}")
	private String compliaceDate;

	@Value("${msg.download.log}")
	private String msgDownloadLog;

	@Value("${saleforce.url}")
	private String saleForceUrl;
	
	@Value("${ip.address.lan}")
	private String ipAddressLan;
	
	@Value("${ip.address.global}")
	private String ipAddressGlobal;
	
	@Autowired
	SettingsInfoService settingsInfoService;

	@Autowired
	NotificationInfoService notificationInfoService;

	@Autowired
	SearchInfoService searchInfoService;

	@Value("${card.default.base64}")
	private String defaultImage64;
	
	@Autowired
	OldCardService oldCardService;
	
	@Autowired
	UserCardMemoService userCardMemoService;
	

	@RequestMapping("home")
	public ModelAndView home(HttpServletRequest request) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
		Long listTotalCardInfo = new Long(0);
		List<TagGroup> listTagGroup = null;
		List<CardInfoPCVo> lstCardInfoPCVo = new ArrayList<>();
		List<String> lstNameSort = new ArrayList<>();
		if (ecardUser != null) {
			// Get listNameSort [2015/10,2015/11, .... ]
			lstNameSort = cardInfoService.getListSortType(ecardUser.getUserId(), SearchConditions.CONTACT.getValue());
			
			listTagGroup = getCardTag();
			if(lstNameSort.size() > 0) {
					List<CardInfoUserVo> lstCardInfo = cardInfoService.getListPossesionCard(ecardUser.getUserId(), SearchConditions.CONTACT.getValue() ,lstNameSort.get(0), 0);
					listTotalCardInfo = cardInfoService.countPossessionCard(ecardUser.getUserId(),SearchConditions.CONTACT.getValue() ,lstNameSort.get(0));
					List<CardInfo> cardInfoDisp = new ArrayList<>();
					for (CardInfoUserVo cardInfo : lstCardInfo) {
						if (lstNameSort.get(0).trim().equals(cardInfo.getSortType().trim())) {
							cardInfoDisp.add(cardInfo.getCardInfo());
						}
					}
				 
					CardInfoPCVo cardInfoPCVo;
					try {
						if (cardInfoDisp.size() > 0) {
							cardInfoPCVo = new CardInfoPCVo(lstNameSort.get(0), CardInfoConverter.convertCardInforList(cardInfoDisp),listTagGroup);
							lstCardInfoPCVo.add(cardInfoPCVo);
						}
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
			}
		}
		appendCardId(listTagGroup);

		// UserSearchVO
		// u=(UserSearchVO)request.getSession().getAttribute("searchDetail");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("homePC");
		modelAndView.addObject("lstNameSort",lstNameSort);
		modelAndView.addObject("lstCardInfoPCVo", lstCardInfoPCVo);
		modelAndView.addObject("totalCardInfo", listTotalCardInfo.intValue());
		modelAndView.addObject("listTagGroup", listTagGroup);
		return modelAndView;

	}

	@RequestMapping(value = "search", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public DataPagingJsonVO<CardInfoPCVo> search(HttpServletRequest request, HttpSession session) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
//		int limit = parseIntParameter(request.getParameter("page"), 0);
		int searchType = parseIntParameter(request.getParameter("typeSearch"), 0);
		int typeSort = parseIntParameter(request.getParameter("typeSort"), 0);
		int page = parseIntParameter(request.getParameter("page"), 0);
		String valueSearch = request.getParameter("valueSearch");	
		DataPagingJsonVO<CardInfoPCVo> dataTableResponse = new DataPagingJsonVO<CardInfoPCVo>();
		List<CardInfoPCVo> cardInfoSearchResponses = new ArrayList<CardInfoPCVo>();
		List<String> lstNameSort = null;		
		List<CardInfoUserVo> lstCardInfo = null;
		Long listTotalCardInfo = new Long(0);
		List<TagGroup> listTagGroup = null;
		String tagId = null;
		// Search all
		// 
		if(searchType == 0) {
			lstNameSort = cardInfoService.getListSortType(ecardUser.getUserId(), typeSort);		
			listTagGroup = getCardTag();
			if(typeSort == SearchConditions.TAG.getValue() ){
				for(TagGroup tag : listTagGroup ){
					if(tag.getTagName().toString().trim().equals(lstNameSort.get(0).trim())){
						tagId = tag.getTagId().toString();
						break;
					}
				}
			}
			if(lstNameSort.size() > 0) {
				if(valueSearch == "" || valueSearch == null){	
					if(typeSort == SearchConditions.TAG.getValue() ){
						lstCardInfo = cardInfoService.getListPossesionCard(ecardUser.getUserId(), typeSort, tagId, page);
						listTotalCardInfo = cardInfoService.countPossessionCard(ecardUser.getUserId(), typeSort,tagId);
					}else{
						lstCardInfo = cardInfoService.getListPossesionCard(ecardUser.getUserId(), typeSort, lstNameSort.get(0).trim(), page);
						listTotalCardInfo = cardInfoService.countPossessionCard(ecardUser.getUserId(), typeSort,lstNameSort.get(0).trim());
					}
					
				} else {
					lstCardInfo = cardInfoService.getListPossesionCard(ecardUser.getUserId(), typeSort, valueSearch, page);
					listTotalCardInfo = cardInfoService.countPossessionCard(ecardUser.getUserId(), typeSort, valueSearch);
				}
				
				
				if (typeSort == SearchConditions.NAME.getValue()) {
					lstNameSort = lstNameSort.stream().map(str-> str!= "" ? str.substring(0, 1).toUpperCase() : str).collect(Collectors.toList());
				}
			}
			 
			/*if (typeSort == SearchConditions.TAG.getValue()) {
				if(lstNameSort.size() <= 0) {
					lstCardInfo = cardInfoService.getListPossesionCard(ecardUser.getUserId(), typeSort, "cardNoTag", page);
					listTotalCardInfo = cardInfoService.countPossessionCard(ecardUser.getUserId(), typeSort, "cardNoTag");
				}
				lstNameSort.add("cardNoTag");
			}*/
			
		}
		int check = 0;
		for (String nameSort : lstNameSort) {
			List<CardInfo> cardInfoDisp = new ArrayList<>();
			if (check == 0) {
				for (CardInfoUserVo cardInfo : lstCardInfo) {
					if(typeSort == SearchConditions.NAME.getValue()){
						if(!cardInfo.getSortType().trim().equals("")){
							if (nameSort.equals(cardInfo.getSortType().toUpperCase().substring(0,1))) {
								cardInfoDisp.add(cardInfo.getCardInfo());
							}
						} else {
							cardInfoDisp.add(cardInfo.getCardInfo());
						}
					} else {
						if (nameSort.equals(cardInfo.getSortType())) {
							cardInfoDisp.add(cardInfo.getCardInfo());
						}
					}
					
				}
				CardInfoPCVo cardInfoPCVo;
				try {
						if (cardInfoDisp.size() > 0) {
							cardInfoPCVo = new CardInfoPCVo(nameSort, CardInfoConverter.convertCardInforList(cardInfoDisp),listTagGroup);
							cardInfoSearchResponses.add(cardInfoPCVo);				
							check = 1;
						}
					
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}	
			} else {
				CardInfoPCVo cardInfoPCVo;
				try {
						cardInfoPCVo = new CardInfoPCVo(nameSort, CardInfoConverter.convertCardInforList(cardInfoDisp),listTagGroup);
						cardInfoSearchResponses.add(cardInfoPCVo);	
					
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}

		dataTableResponse.setData(cardInfoSearchResponses);
		dataTableResponse.setRecordsTotal(listTotalCardInfo);
		return dataTableResponse;

	}

	@RequestMapping("getImageFile")
	@ResponseBody
	public synchronized String getFileImageSCP(@RequestParam(value = "fileImage") String fileImage) {
		String fileNameFromSCP = UploadFileUtil.getImageFileFromSCP(fileImage, scpHostName, scpUser, scpPassword,
				Integer.parseInt(scpPort));
		return fileNameFromSCP;
	}

	@RequestMapping("download")
	public ModelAndView DownloadCSV(HttpServletRequest request) {
		int block = 1;
	    try {
	    	InetAddress inetAddress;
			inetAddress = InetAddress.getLocalHost();

		    String serverAddress = inetAddress.getHostAddress().toString();
		    if(!serverAddress.equals(this.ipAddressGlobal)){
		    	block = 1;
		    } else {
		    	block = 2;
		    }
		} catch (UnknownHostException e1) {
			
			e1.printStackTrace();
		}
	    if(block == 2){
	    	return new ModelAndView("redirect:/user/home");
	    }
	    
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
		UserInfo userInfo = userInfoService.getUserInfoByUserId(ecardUser.getUserId());

		List<DownloadCsv> downloadCSVHistory = userInfoService.getHistoryCSVDownload(ecardUser.getUserId());
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c2.setTime(new Date());
		for(DownloadCsv download : downloadCSVHistory){
			if(download.getCsvType() != CsvConstant.MY_CARDS){				
				c1.setTime(download.getRequestDate());
				int day = c2.get(Calendar.DATE) - c1.get(Calendar.DATE);
				if(day >= 7){
										
					try {
						deleteFileCSV(download.getCsvUrl());
						download.setCsvApprovalStatus(CsvConstant.IS_DOWNLOADED);
						cardInfoService.updateDownloadHistory(download.getCsvId());
					} catch (IOException e) {
						
						e.printStackTrace();
					}
					
				}
			}
			
			
		}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("download");
		modelAndView.addObject("roleAdminId", userInfo.getRoleAdminId());
		modelAndView.addObject("downloadCSVHistory", downloadCSVHistory);
		return modelAndView;
	}

	@RequestMapping(value = "/downloadCSV/{id:[\\d]+}", method = RequestMethod.GET)	
	public void downloadCSV(HttpServletResponse response, @PathVariable("id") int id) throws IOException {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			EcardUser  ecardUser =(EcardUser) authentication.getPrincipal();
			UserInfo userInfo = userInfoService.findUserByEmail(ecardUser.getUsername());

			DownloadCsv downloadCsvId = new DownloadCsv();
			downloadCsvId.setUserInfo(userInfo);
			downloadCsvId.setApprovalDate(new Date());
			downloadCsvId.setRequestDate(new Date());

			String current = new SimpleDateFormat("ddMMyyyyhhmmss").format(new Date());
			String fileName = new String();
			List<CardInfoCSV> listUserInfoCSV;
			if (id == 3 ) {
				listUserInfoCSV = null;
				if (userInfo.getGroupCompanyId() != null) {
					List<CardInfo> listCardInfo = cardInfoService.getGroupCompanyCard(userInfo.getGroupCompanyId());
					listUserInfoCSV = CardInfoConverter.convertCardInfoList(listCardInfo);
				} 
				fileName = "GroupCompanyCard_" + current + userInfo.getUserId() + ".csv";
				downloadCsvId.setCsvType(listUserInfoCSV.size());
				downloadCsvId.setCsvUrl(fileName);				
				createCSVFile(response, fileName, listUserInfoCSV, CsvConstant.DOWNLOAD_NOT_DIRECT);
				cardInfoService.saveDownloadHistory(downloadCsvId);
			} else if (id == 2) {
				listUserInfoCSV = null;
				if (userInfo.getGroupCompanyId() != null) {
					List<CardInfo> listCardInfo = cardInfoService.getCompanyCard(userInfo.getGroupCompanyId());
					listUserInfoCSV = CardInfoConverter.convertCardInfoList(listCardInfo);
				} 
				fileName = "CompanyCard_" + current + userInfo.getUserId() + ".csv";
				downloadCsvId.setCsvType(listUserInfoCSV.size());
				downloadCsvId.setCsvUrl(fileName);				
				createCSVFile(response, fileName, listUserInfoCSV, CsvConstant.DOWNLOAD_NOT_DIRECT);
				cardInfoService.saveDownloadHistory(downloadCsvId);
			} else {
				List<CardInfo> listCardInfo = cardInfoService.getListMyCard(userInfo.getUserId());
				listUserInfoCSV = CardInfoConverter.convertCardInfoList(listCardInfo);
				fileName = "MyCard_" + current + userInfo.getUserId() + ".csv";
				
				response.setContentType("application/force-download");
				String headerKey = "Content-Disposition";
				String headerValue = String.format("attachment; filename=\"%s\"", fileName);
				response.setCharacterEncoding("UTF-8");
				response.setHeader(headerKey, headerValue);
				OutputStream outputStream = response.getOutputStream();
				outputStream.write(0xEF);   // 1st byte of BOM
				outputStream.write(0xBB);
				outputStream.write(0xBF); 
				PrintWriter out = new PrintWriter(new OutputStreamWriter(outputStream,"UTF-8"));
				ICsvBeanWriter csvWriter = new CsvBeanWriter(out , CsvPreference.STANDARD_PREFERENCE);
				csvWriter.writeHeader(headerJapanse);
				for (CardInfoCSV aCard : listUserInfoCSV) {
					csvWriter.write(aCard, header);
				}
				csvWriter.close();
				
				
			}
			
			// Save history download
			ActionLogId actionLogId = new ActionLogId();
			actionLogId.setActionDate(new Date());
			actionLogId.setActionMessage(this.msgDownloadLog);
			actionLogId.setActionType(ActionLogType.DOWNLOAD.getValue());
			actionLogId.setUserId(userInfo.getUserId());

			ActionLog actionLog = new ActionLog();
			actionLog.setId(actionLogId);
			userInfoService.saveActionLog(actionLog);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@RequestMapping(value = "/downloadFileCSV/{id:[\\d]+}", method = RequestMethod.GET)
	@ResponseBody
	public void downloadFileCSV(HttpServletResponse response, HttpServletRequest request, @PathVariable("id") int id)
			throws IOException {
		try {
			DownloadCsv downloadCsv = cardInfoService.getDownloadCSV(id);
			// update History
			cardInfoService.updateDownloadHistory(downloadCsv.getCsvId());
			
			String fileName = downloadCsv.getCsvUrl();

			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserInfo userInfo = userInfoService.findUserByEmail(authentication.getName());
			String saveFileCSV = "/data/csv";
			// String saveFileCSV = System.getProperty("user.dir") + "/csv";
			File file = new File(saveFileCSV + "/" + fileName);

			// Set file response
			response.setContentType("application/force-download");
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", fileName);
			byte[] bufferData = new byte[1024];
			response.setHeader(headerKey, headerValue);

			InputStream inputStream = new FileInputStream(file);
			
			BufferedOutputStream outs = new BufferedOutputStream(response.getOutputStream());
			outs.write(0xef);
			outs.write(0xbb);
			outs.write(0xbf);
            int len;
            while ((len = inputStream.read(bufferData)) > 0) {
              outs.write(bufferData, 0, len);
            }
            outs.close();
            inputStream.close();
			/*try {
				int c;
				while ((c = inputStream.read(bufferData)) != -1) {
					os.write(bufferData, 0, c);
				}
				os.flush();
			} finally {
				if (inputStream != null)
					inputStream.close();
				os.close();
			}*/
			
			// send mail to roleAdminID
			List<UserInfo> listUser = userInfoService.getAllUserInfo();
			List<String> listUserId = new ArrayList<>();
			for (UserInfo userAdmin : listUser) {
				if (userAdmin.getRoleAdminId() == 7 && userAdmin.getUserId() != userInfo.getUserId()) {
					listUserId.add(userAdmin.getEmail());
				}
			}
			listUserId.add(userInfo.getEmail());

			Context ctx = new Context();
			ctx.setVariable("company", userInfo.getCompanyNameKana());
			ctx.setVariable("userDownload", userInfo.getNameKana());
			ctx.setVariable("dateDownload", downloadCsv.getApprovalDate());
			ctx.setVariable("emailDownload", userInfo.getEmail());
			// ctx.setVariable("recordNumber",answerText);
			sendMailDownload(listUserId, ctx);

			//
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void deleteFileCSV(String fileName) throws IOException {

//		String saveFileCSV = System.getProperty("user.dir") + "/csv";
		String saveFileCSV = "/data/csv";
		String fileExist = saveFileCSV + "/" + fileName;

		File myFile = new File(fileExist);
		if (myFile.exists()){
			myFile.delete();
			System.out.println("Delete success : "+myFile.getAbsolutePath());
		}
		else{
			System.out.println("Delete failed : "+myFile.getAbsolutePath());
		}
	}

	public void sendMailDownload(List<String> listUserId, Context ctx) throws IOException {

		try {
			emailService.sendMailContact(CommonConstants.USER_FROM_MAIL, listUserId,
					CommonConstants.TITLE_DOWNLOAD_MAIL, ctx, "mailtodownloadCSV");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public void createCSVFile(HttpServletResponse response, String fileName, List<CardInfoCSV> listUserInfoCSV,
			Integer typeCSV) throws IOException {
		String csvFileName = fileName;
//		String workingDirectory = System.getProperty("user.dir") + "/csv";
		String workingDirectory ="/data/csv";

		String absoluteFilePath = "";
		absoluteFilePath = workingDirectory + File.separator + csvFileName;

		if (typeCSV == CsvConstant.DOWNLOAD_DIRECT) {
			response.setContentType("application/force-download");
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", csvFileName);
			response.setHeader(headerKey, headerValue);
			OutputStreamWriter o = new OutputStreamWriter((OutputStream) response);
			o.write(0xef);
			o.write(0xbb);
			o.write(0xbf);
			ICsvBeanWriter csvWriter = new CsvBeanWriter(o, CsvPreference.STANDARD_PREFERENCE);
			csvWriter.writeHeader(headerJapanse);

			for (CardInfoCSV aCard : listUserInfoCSV) {
				csvWriter.write(aCard, header);
			}
			csvWriter.close();
		} else {
			response.setContentType("text/html");			
			ICsvBeanWriter csvWriter = new CsvBeanWriter(new FileWriter(absoluteFilePath),
					CsvPreference.STANDARD_PREFERENCE);
			csvWriter.writeHeader(headerJapanse);
			
			for (CardInfoCSV aCard : listUserInfoCSV) {				
				csvWriter.write(aCard, header);
			}
			csvWriter.close();
		}

	}

	@RequestMapping(value = "/card/detail/{id:[\\d]+}", method = RequestMethod.GET)
	private int parseIntParameter(String parameter, int defaultValue) {
		try {
			return Integer.parseInt(parameter);
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}

	@RequestMapping(value = "/card/details/{id:[\\d]+}", method = RequestMethod.GET)
	public ModelAndView detailPC(@PathVariable("id") int id, HttpSession session) {
		logger.debug("detailPC", UserController.class);

		ModelAndView modelAndView = new ModelAndView();
		CardInfo cardInfo = null;
		List<CardConnectModel> cardList = null;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
			Integer userId = ecardUser.getUserId();
			cardInfo = cardInfoService.getCardInfoDetail(id);
			String fileNameFromSCP = UploadFileUtil.getImageFileFromSCP(cardInfo.getImageFile(), scpHostName, scpUser,
					scpPassword, Integer.parseInt(scpPort));
			//cardInfo.setImageFile(fileNameFromSCP);
			modelAndView.addObject("imageFile", fileNameFromSCP);
			
			//Get user infor
			UserInfoVo userInfoDetail = cardInfoService.getUserInfoByCardId(id);
			if(userInfoDetail != null){
				modelAndView.addObject("userInfoDetail", userInfoDetail);
			}
			
			// List card connected
			cardList = cardInfoService.listCardConnect(cardInfo.getCardOwnerId(), cardInfo.getGroupCompanyId(),
					cardInfo.getName(), cardInfo.getCompanyName(), cardInfo.getEmail());

			List<CardInfoMemo> listCardMemo = getListCardMemo(cardInfo.getCardId());
			modelAndView.addObject("listCardMemo", listCardMemo);

			if (!cardInfo.getCardOwnerId().equals(userId)) {
				modelAndView.addObject("isMyCard", false);
			} else {
				modelAndView.addObject("isMyCard", true);
			}
			
			// Check compliance date
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date dateCompliance = sdf.parse(compliaceDate);

			if (cardInfo.getContactDate().before(dateCompliance)) {
				modelAndView.addObject("isExpried", true);
			} else {
				modelAndView.addObject("isExpried", false);
			}

			// Get user information
			UserInfo userInfo = userInfoService.getUserInfoByUserId(userId);
			if (userInfo.getSfManualLinkFlg() != 0) {
				modelAndView.addObject("sfManualLinkFlg", true);
			} else {
				modelAndView.addObject("sfManualLinkFlg", false);
			}

			PrusalHistory prusalHistory = new PrusalHistory();
			prusalHistory.setCardInfo(cardInfo);
			
			PrusalHistoryId prusalHistoryId = new PrusalHistoryId();
			prusalHistoryId.setCardId(cardInfo.getCardId());
			prusalHistoryId.setUserId(userId);
			prusalHistoryId.setPrusalDate(new Date());
			
			prusalHistory.setId(prusalHistoryId);
			
			LocalDate localContactDate= cardInfo.getContactDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate localCompliance= dateCompliance.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			
			boolean checkCompliance = false;
			if(localContactDate.isEqual(localCompliance) || localContactDate.isAfter(localCompliance)){
				checkCompliance = true;
			}
			
			if(!cardInfo.getCardOwnerId().equals(userId)){
				if((userInfo.getGroupCompanyId() == 1 || userInfo.getGroupCompanyId() == 2 || userInfo.getGroupCompanyId() == 3 
						|| userInfo.getGroupCompanyId() == 4 || userInfo.getGroupCompanyId() == 5)){
					if((cardInfo.getGroupCompanyId() == 1 || cardInfo.getGroupCompanyId() == 2 || cardInfo.getGroupCompanyId() == 3 
							|| cardInfo.getGroupCompanyId() == 4 || cardInfo.getGroupCompanyId() == 5) 
							|| ( (cardInfo.getGroupCompanyId() != 1 || cardInfo.getGroupCompanyId() != 2 || cardInfo.getGroupCompanyId() != 3 
									|| cardInfo.getGroupCompanyId() != 4 || cardInfo.getGroupCompanyId() != 5)  && checkCompliance)){
						//Save history
						cardInfoService.savePrusalHistory(prusalHistory);
					}
					else{
						return new ModelAndView("redirect:/user/home");
					}
				}else{
					if(cardInfo.getGroupCompanyId() == userInfo.getGroupCompanyId() 
							|| (cardInfo.getGroupCompanyId() != userInfo.getGroupCompanyId() && checkCompliance)){
						//Save history
						cardInfoService.savePrusalHistory(prusalHistory);
					}
					else{
						return new ModelAndView("redirect:/user/home");
					}
				}
			}

			//Save history
			cardInfoService.savePrusalHistory(prusalHistory);
			
			// Get old cards
			List<com.ecard.core.vo.CardInfo> listOldCard = cardInfoService.getListCardHistoryByCardId(cardInfo.getCardId());
			modelAndView.addObject("listOldCard", listOldCard);

			// set search detail session (homepc)
			if (session.getAttribute("searchDetail") != null) {
				UserSearchVO userSearch = (UserSearchVO) session.getAttribute("searchDetail");
				userSearch.setDetail(true);
				session.setAttribute("searchDetail", userSearch);
			}
			
			// set search detail session in overlap
			/*if (session.getAttribute("overlapSearchDetail") != null) {
				OverlapSearchDetail overlapSearchDetail=(OverlapSearchDetail)session.getAttribute("overlapSearchDetail");
				overlapSearchDetail.setDetail(true);
				session.setAttribute("overlapSearchDetail", overlapSearchDetail);
			}*/

			// Get contact history
			List<com.ecard.core.vo.ContactHistory> contactHistoryList = contactHistoryService
					.getListContactHistoryById(id);
			if (contactHistoryList.size() > 0) {
				modelAndView.addObject("contactHistoryList", contactHistoryList);
			}
			
		} catch (Exception ex) {
			logger.debug("Exception : ", ex.getMessage());
		}

		modelAndView.setViewName("detailPC");
		modelAndView.addObject("cardInfo", cardInfo);
		modelAndView.addObject("listCardConnect", cardList);

		return modelAndView;
	}

	@RequestMapping(value = "addContactHistory", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<com.ecard.core.vo.ContactHistory> addContactHistory(@RequestBody ContactHistory contactHistoryModel,
			HttpServletRequest request, HttpServletResponse response) {
		logger.debug("addContactHistory", UserController.class);

		com.ecard.core.model.ContactHistory contactHistModel = new com.ecard.core.model.ContactHistory();
		List<com.ecard.core.vo.ContactHistory> contactHistoryList = null;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
			Integer userId = ecardUser.getUserId();

			contactHistoryModel.setUserId(userId);

			com.ecard.core.model.ContactHistory contactHistory = contactHistoryService
					.saveContactHistory(contactHistoryModel);

			contactHistoryList = contactHistoryService.getListContactHistoryById(contactHistoryModel.getCardInfo().getCardId());
		} catch (Exception ex) {
			logger.debug("Exception : " + ex.getMessage(), UserController.class);
		}
		return contactHistoryList;
	}

	@RequestMapping(value = "deleteContactHistory", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<com.ecard.core.vo.ContactHistory> deleteContactHistory(@RequestBody ContactHistory contactHistory,
			HttpServletRequest request, HttpServletResponse response) {
		logger.debug("deleteContactHistory", UserController.class);

		List<com.ecard.core.vo.ContactHistory> contactHistoryList = null;
		try {
			contactHistoryService.deleteContactHistory(contactHistory.getContactHistoryId());
			contactHistoryList = contactHistoryService.getListContactHistoryById(contactHistory.getCardInfo().getCardId());
		} catch (Exception ex) {
			logger.debug("Exception : " + ex.getMessage(), UserController.class);
		}
		return contactHistoryList;
	}

	@RequestMapping(value = "loginSaleForce", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String loginSaleForce(@RequestBody CardInfoSaleforce cardInfo) {
		logger.debug("loginSaleForce", UserController.class);

		final String URI = this.saleForceUrl;

		RestTemplate restTemplate = new RestTemplate();
		String result = "";
		try {
			String addressFull = cardInfo.getAddress1() + " " + cardInfo.getAddress2() + " " + cardInfo.getAddress3()
					+ " " + cardInfo.getAddress4();
			String lastName = cardInfo.getLastname();
			String firstName = cardInfo.getFirstname();
			String positionName = cardInfo.getPositionName();
			String companyName = cardInfo.getCompanyName();
			String address2 = cardInfo.getAddress2();
			String address1 = cardInfo.getAddress1();
			String companyUrl = cardInfo.getCompanyUrl();
			String departmentName = cardInfo.getDepartmentName();

			if (addressFull.length() > 255) {
				addressFull = addressFull.substring(0, 255);
			}

			if (lastName.length() > 80) {
				lastName = lastName.substring(0, 80);
			}

			if (firstName.length() > 40) {
				firstName = firstName.substring(0, 40);
			}

			if (positionName.length() > 128) {
				positionName = positionName.substring(0, 128);
			}

			if (companyName.length() > 255) {
				companyName = companyName.substring(0, 255);
			}

			if (address2.length() > 40) {
				address2 = address2.substring(0, 40);
			}

			if (address1.length() > 80) {
				address1 = address1.substring(0, 80);
			}

			if (companyUrl.length() > 255) {
				companyUrl = companyUrl.substring(0, 255);
			}

			if (departmentName.length() > 255) {
				departmentName = departmentName.substring(0, 255);
			}

			//String password = cardInfo.getLogin_pass() + CommonConstants.tokenAuthen;
			String password = cardInfo.getLogin_pass();
			
			MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
			map.add("lastname", lastName);
			map.add("firstname", firstName);
			map.add("positionName", positionName);
			map.add("companyName", companyName);
			map.add("address1", address1);
			map.add("address2", address2);
			map.add("address3", cardInfo.getAddress3());
			map.add("address4", cardInfo.getAddress4());
			map.add("telNumberCompany", cardInfo.getTelNumberCompany());
			map.add("mobileNumber", cardInfo.getMobileNumber());
			map.add("faxNumber", cardInfo.getFaxNumber());
			map.add("email", cardInfo.getEmail());
			map.add("companyUrl", companyUrl);
			map.add("departmentName", departmentName);
			map.add("zipCode", cardInfo.getZipCode());
			map.add("login_id", cardInfo.getLogin_id());
			map.add("login_pass", password);

			restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
			result = restTemplate.postForObject(URI, map, String.class);
		} catch (Exception ex) {
			logger.debug("Exception : " + ex.getMessage(), UserController.class);
		}
		return result;
	}

	@RequestMapping(value = "profile/{id:[\\d]+}", method = RequestMethod.GET)
	public ModelAndView profileCardConnect(@PathVariable("id") Integer userId) {
		logger.debug("profileCardConnect", UserController.class);
		String company=" ";
		ModelAndView modelAndView = new ModelAndView();
		try {
			if (userId != null) {
				UserInfo user = userInfoService.getUserInfoByUserId(userId);
				UserInfoVO userVO = new UserInfoVO();
				company=groupCompanyInfoService.getCompanyById(user.getGroupCompanyId()).getGroupCompanyName();
				userVO.setCompanyName(StringUtils.isEmpty(company)?" ":company);
				userVO.setDepartmentName(StringUtils.isEmpty(user.getDepartmentName())? " ":user.getDepartmentName());
				userVO.setName(StringUtils.isEmpty(user.getName())?" ":user.getName());
				userVO.setPositionName(StringUtils.isEmpty(user.getPositionName())?" ": user.getPositionName());
				userVO.setEmail(user.getEmail());

				modelAndView.addObject("user", userVO);
				modelAndView.setViewName("cardConnectDetail");
				return modelAndView;
			}

		} catch (Exception ex) {
			logger.debug("Exception : " + ex.getMessage(), UserController.class);
		}
		return new ModelAndView("redirect:home");
	}

	@RequestMapping("profile")
	public ModelAndView profile() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String company=" ";
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
		if (ecardUser != null) {
			UserInfo user = userInfoService.getUserInfoByUserId(ecardUser.getUserId());
			UserInfoVO userVO = new UserInfoVO();
			company=groupCompanyInfoService.getCompanyById(user.getGroupCompanyId()).getGroupCompanyName();
			userVO.setCompanyName(StringUtils.isEmpty(company)?" ":company);
			userVO.setDepartmentName(StringUtils.isEmpty(user.getDepartmentName())? " ":user.getDepartmentName());
			userVO.setName(StringUtils.isEmpty(user.getName())?" ":user.getName());
			userVO.setPositionName(StringUtils.isEmpty(user.getPositionName())?" ": user.getPositionName());
			userVO.setEmail(user.getEmail());
			return new ModelAndView("profile", "user", userVO);
		}
		return new ModelAndView("redirect:home");
	}

	@RequestMapping("changepass")
	public ModelAndView changepass(HttpSession session) {
		session.setAttribute("isFirstLogin", true);
		return new ModelAndView("changepass");
	}

	@RequestMapping(value = "checkPass", method = RequestMethod.POST)
	@ResponseBody
	public boolean checkPass(String oldpass) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
		if (ecardUser != null) {
			UserInfo user = userInfoService.getUserInfoByUserId(ecardUser.getUserId());
			return ((new BCryptPasswordEncoder()).matches(oldpass, user.getPassword()));
		}
		return false;
	}

	@RequestMapping(value = "updatePass", method = RequestMethod.POST)
	@ResponseBody
	public boolean updatePass(@RequestParam(value = "newPass") String newPass) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
		if (ecardUser != null) {
			UserInfo user = userInfoService.getUserInfoByUserId(ecardUser.getUserId());
			user.setPassword((new BCryptPasswordEncoder().encode(newPass)));
			userInfoService.updateProfileAdminAllocation(user);
			//Set first login
            userInfoService.updateFisrtLogin(ecardUser.getUserId(), 1);
		}
		return true;
	}

	@RequestMapping("faq")
	public ModelAndView faq() {
		return new ModelAndView("faq");
	}

	@RequestMapping("contact")
	public ModelAndView mailbox() {
		return new ModelAndView("mailbox");
	}

	@RequestMapping(value = "mailbox", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView mailboxPost(@RequestParam(value = "contents") String contents) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
		InquiryInfo inquiryInfo = new InquiryInfo();
		UserInfo userInfo = userInfoService.getUserInfoByUserId(ecardUser.getUserId());

		inquiryInfo.setUserInfo(userInfo);
		inquiryInfo.setTitle("");
		inquiryInfo.setAnswerFlg(0);
		inquiryInfo.setAnswerText("");
		inquiryInfo.setCreateDate(new Date());
		inquiryInfo.setUpdateDate(new Date());
		inquiryInfo.setOperaterId(0);
		inquiryInfo.setInquiryText(contents);
		inquiryInfo.setInquiryType(1);
		settingsInfoService.sendInquiry(inquiryInfo);
		return new ModelAndView("redirect:home");
	}

	@RequestMapping(value = "/notificationDetail/{id:[\\d]+}", method = RequestMethod.GET)
	public ModelAndView notificationDetail(@PathVariable("id") int id) {
		UserNotification notify = new UserNotification();
		notify.setNoticeId(id);
		notify.setReadFlg(1);
		notificationInfoService.updateReadFlgById(notify);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
		List<NotificationList> listUpdate = notificationInfoService.listAllNofiticationUser(ecardUser.getUserId());
		NotificationList item = null;
		try {
			item = listUpdate.stream().filter(x -> x.getNotice_id() == id).findFirst().get();
		} catch (Exception e) {
			return new ModelAndView("redirect:../home");
		}

		return new ModelAndView("redirect:" + CommonConstants.REDIRECT_CARD_DETAIL + item.getCard_id());
	}

	@RequestMapping(value = "editCardInfo", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public boolean editCardInfo(@RequestBody CardInfo cardInfo, HttpServletRequest request) {
		logger.debug("editCardInfo", UserController.class);
		boolean result = false;
		
		try {
			String name = cardInfo.getLastName();
			String nameKana = cardInfo.getLastNameKana();
			String addressFull = "";

			if (cardInfo.getFirstName() != null) {
				name += " " + cardInfo.getFirstName();
			}

			if (cardInfo.getFirstNameKana() != null) {
				nameKana += " " + cardInfo.getFirstNameKana();
			}

			if (cardInfo.getAddress1() != null && cardInfo.getAddress2() != null && cardInfo.getAddress3() != null
					&& cardInfo.getAddress4() != null) {
				addressFull += addressFull = cardInfo.getAddress1() + " " + cardInfo.getAddress2() + " "
						+ cardInfo.getAddress3() + " " + cardInfo.getAddress4();
			} else if (cardInfo.getAddress1() != null && cardInfo.getAddress2() != null
					&& cardInfo.getAddress3() != null) {
				addressFull += addressFull = cardInfo.getAddress1() + " " + cardInfo.getAddress2() + " "
						+ cardInfo.getAddress3();
			} else if (cardInfo.getAddress1() != null && cardInfo.getAddress2() != null) {
				addressFull += addressFull = cardInfo.getAddress1() + " " + cardInfo.getAddress2();
			} else if (cardInfo.getAddress1() != null) {
				addressFull += addressFull = cardInfo.getAddress1();
			}

			if (cardInfo.getUpdateDate() == null) {
				cardInfo.setUpdateDate(new Date());
			}

			if (cardInfo.getContactDate() == null) {
				cardInfo.setContactDate(new Date());
			}
			cardInfo.setDeletDate(null);
			cardInfo.setDateEditting(new Date());

			cardInfo.setName(name);
			cardInfo.setNameKana(nameKana);
			cardInfo.setAddressFull(addressFull);

			CompanyInfo companyInfo = new CompanyInfo();
			companyInfo.setCompanyId(0);

			cardInfo.setCompanyInfo(companyInfo);

			if (cardInfoService.editCardInfo(cardInfo)) {
				result = true;
			} else {
				result = false;
			}
		} catch (Exception ex) {
			logger.debug("Exception : ", ex.getMessage());
			result = true;
		}
		
		return result;
	}

	@RequestMapping(value = "editContactDate", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView editContactDate(String contactDate, HttpServletRequest request) {
		logger.debug("editContactDate", UserController.class);

		ModelAndView modelAndView = new ModelAndView();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(contactDate);

			CardInfo cardInfo = new CardInfo();
			cardInfo.setContactDate(date);
			cardInfo.setCardId(Integer.parseInt(request.getParameter("cardId")));

			if (cardInfoService.updateContactDate(cardInfo) > 0) {
				modelAndView.addObject("isEdit", true);
			} else {
				modelAndView.addObject("isEdit", false);
			}
		} catch (Exception ex) {
			logger.debug("Exception : ", ex.getMessage());
		}

		modelAndView.setViewName("redirect:" + CommonConstants.REDIRECT_CARD_DETAIL + request.getParameter("cardId"));
		return modelAndView;
	}

	@RequestMapping(value = "addTag", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<TagGroup> addTag(@RequestBody CardAndUserTag cardAndUserTag, HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug("addTag", UserController.class);

		// ModelAndView modelAndView = new ModelAndView();

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
		Integer userId = ecardUser.getUserId();

		try {

			List<UserTagAndCardTag> listUserTagAndCardTag = userTagService.getListUserTagByUserId(userId);
			Integer tagId = 0;
			for (UserTagAndCardTag userTagTmp : listUserTagAndCardTag) {
				if (userTagTmp.getTagName().equals(cardAndUserTag.getTagName())) {
					tagId = userTagTmp.getTagId();
					break;
				}
			}
			Integer isSame = 0;
			if (tagId != 0) {
				List<Integer> cardId = cardTagService.listCardIdByTagId(tagId);
				for (Integer cardIdTmp : cardId) {
					if (cardAndUserTag.getCardId() != null) {
						if (cardAndUserTag.getCardId().equals(cardIdTmp)) {
							isSame = 1;
							break;
						}
					}
				}
				if (isSame != 1 && (cardAndUserTag.getCardId() != null)) {
					CardTagId cardTag = new CardTagId();
					cardTag.setCardId(cardAndUserTag.getCardId());
					cardTag.setTagId(tagId);
					cardTagService.addCardTag(cardTag);
				}
			} else {
				UserInfo userInfo = new UserInfo();
				userInfo.setUserId(userId);

				UserTag userTag = new UserTag();
				userTag.setTagName(cardAndUserTag.getTagName());
				userTag.setUserInfo(userInfo);
				tagId = userTagService.registerUserTag(userTag);

				if (cardAndUserTag.getCardId() != null) {
					CardTagId cardTag = new CardTagId();
					cardTag.setCardId(cardAndUserTag.getCardId());
					cardTag.setTagId(tagId);
					cardTagService.addCardTag(cardTag);
				}
			}

		} catch (Exception ex) {
			logger.debug("Exception :" + ex.getMessage(), UserController.class);
			response.setStatus(500, "Add tag error");
		}
		return getCardTag();
	}

	@RequestMapping(value = "addCardTag", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<TagGroup> addCardTag(@RequestBody CardTagId cardTag, HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug("addCardTag", UserController.class);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
		Integer userId = ecardUser.getUserId();
		try {
			cardTagService.addCardTag(cardTag);
		} catch (Exception ex) {
			logger.debug("Exception : " + ex.getMessage(), UserController.class);
		}
		return getCardTag();
	}

	@RequestMapping(value = "addCardMemo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<CardInfoMemo> addCardMemo(@RequestBody UserCardMemoId userCardMemo, HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug("addCardMemo", UserController.class);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
		Integer userId = ecardUser.getUserId();
		try {
			userCardMemo.setCreateDate(new Date());
			userCardMemo.setUserId(userId);

			//String memo = userCardMemo.getMemo().replace("\n", "<br/>");
			//userCardMemo.setMemo(memo);
			
			int seq;
			if (userCardMemo.getSeq() != 0) {
				cardMemoService.registerCardMemo(userCardMemo);
			} else {
				try {
					seq = cardMemoService.getMaxSeqByUserId(userId);
				} catch (Exception e) {
					seq = 1;
				}

				if (userCardMemo.getSeq() == 0) {
					userCardMemo.setSeq(seq);
				}
				cardMemoService.createCardMemo(userCardMemo);
			}
		} catch (Exception ex) {
			logger.debug("Exception : " + ex.getMessage(), UserController.class);
		}
		List<CardInfoMemo> listCardMemo = getListCardMemo(userCardMemo.getCardId());
		return listCardMemo;
	}

	@RequestMapping(value = "deleteCardMemo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<CardInfoMemo> deleteCardMemo(@RequestBody UserCardMemoId userCardMemo, HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug("deleteCardMemo", UserController.class);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
		Integer userId = ecardUser.getUserId();
		try {
			userCardMemo.setCreateDate(new Date());
			userCardMemo.setUserId(userId);
			cardMemoService.deleteCardMemo(userCardMemo);
		} catch (Exception ex) {
			logger.debug("Exception : " + ex.getMessage(), UserController.class);
		}
		List<CardInfoMemo> listCardMemo = getListCardMemo(userCardMemo.getCardId());
		return listCardMemo;
	}

	@RequestMapping(value = "deleteCardTag", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<TagGroup> deleteCardTag(@RequestBody TagForCard cardAndUserTag, HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug("deleteCardTag", UserController.class);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
		Integer userId = ecardUser.getUserId();
		try {
			cardTagService.deleteCardTag(cardAndUserTag.getCardId(), cardAndUserTag.getTagId());
		} catch (Exception ex) {
			logger.debug("Exception : " + ex.getMessage(), UserController.class);
		}
		// List<TagForCard> listTagForCard = getAllTagForCard(userId);
		return getCardTag();
	}

	@RequestMapping(value = "deleteTag", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<TagGroup> deleteTag(@RequestParam Integer tagId, HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug("deleteTag", UserController.class);

		try {
			cardTagService.deleteCardTagByTagId(tagId);
			userTagService.deleteUserTag(tagId);
		} catch (Exception ex) {
			logger.debug("Exception : " + ex.getMessage(), UserController.class);
		}

		return getCardTag();
	}
	
	@RequestMapping(value = "deleteTagHome", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<TagGroup> deleteTagHome(@RequestParam Integer tagId, HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug("deleteTag", UserController.class);

		try {
			cardTagService.deleteCardTagByTagId(tagId);
			userTagService.deleteUserTag(tagId);
		} catch (Exception ex) {
			logger.debug("Exception : " + ex.getMessage(), UserController.class);
		}
		List<TagGroup> listTagGroup = getCardTag();
		appendCardId(listTagGroup);
		return listTagGroup;
	}

	@RequestMapping(value = "delBusinessCard", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView delBusinessCard(PossessionCardId card, HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug("delBusinessCard", UserController.class);

		ModelAndView modelAndView = new ModelAndView();
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
			Integer userId = ecardUser.getUserId();

			possessionCardService.deletePossessionCard(userId, card.getCardId());
		} catch (Exception ex) {
			logger.debug("Exception : " + ex.getMessage(), UserController.class);
		}
		response.setStatus(200, "Remove card success");
		modelAndView.setViewName("redirect:" + CommonConstants.REDIRECT_CARD_DETAIL + card.getCardId());
		return modelAndView;
	}

	private List<TagForCard> getAllTagForCard(Integer userId) {
		List<TagForCard> listCardTag = null;
		try {
			listCardTag = cardTagService.listCardTagByCardId(userId);
		} catch (Exception ex) {
			logger.debug("Exception : " + ex.getMessage(), UserController.class);
		}
		return listCardTag;
	}

	private List<CardInfoMemo> getListCardMemo(Integer cardId) {
		List<CardInfoMemo> listCardMemo = null;
		try {
			listCardMemo = cardMemoService.getMemoCard(cardId);
		} catch (Exception ex) {
			logger.debug("Exception : " + ex.getMessage(), UserController.class);
		}
		for(CardInfoMemo cardInfoMemo : listCardMemo){
			String memo = nlToBR(cardInfoMemo.getMemo());
			cardInfoMemo.setMemo(memo);
		}
		return listCardMemo;
	}

	@RequestMapping(value = "addUserSearch", method = RequestMethod.POST)
	@ResponseBody
	public boolean addUserSearch(@RequestBody final UserSearchVO userSearchVO) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
		UserSearch us = new UserSearch();
		UserInfo u = new UserInfo();
		u.setUserId(ecardUser.getUserId());

		us.setUserId(ecardUser.getUserId());
		us.setUserInfo(u);
		us.setFreeText(userSearchVO.getFreeText());
		us.setOwner(userSearchVO.getOwner());
		us.setCompany(userSearchVO.getCompany());
		us.setDepartment(userSearchVO.getDepartment());
		us.setPosition(userSearchVO.getPosition());
		us.setName(userSearchVO.getName());
		us.setParameterFlg(userSearchVO.getParameterFlg());
		us.setTitle("");

		List<SearchInfo> listSearchInfo = searchInfoService.listSearchText(ecardUser.getUserId());
		List<Integer> seqList = new ArrayList<Integer>();
		for (SearchInfo s : listSearchInfo) {
			seqList.add(s.getSeq());
		}
		int seq = getSequeceFromList(seqList);
		if (seq >= 6) {
			return false;
		} else {
			us.setSeq(seq);
		}
		try {
			if (searchInfoService.registerSearchText(us) == 0)
				searchInfoService.createSearchText(us);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	private int getSequeceFromList(List<Integer> myCoord) {
		List<Integer> myCoords = new ArrayList<Integer>();
		myCoords.add(1);
		myCoords.add(2);
		myCoords.add(3);
		myCoords.add(4);
		myCoords.add(5);

		List<Integer> matches = new ArrayList<Integer>();
		int i = 1;
		for (Integer s : myCoords)
			matches.add(myCoord.contains(s) ? 1 : 0);
		int seq = myCoord.size() + 1;
		for (Integer s : matches) {
			if (s.equals(0)) {
				seq = i;
				return seq;
			}
			i++;
		}
		return seq;
	}

	@RequestMapping(value = "listSearchText", method = RequestMethod.POST)
	@ResponseBody
	public ObjectListSearchUsers listSearchText() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
		List<SearchInfo> listSearchInfo = searchInfoService.listSearchText(ecardUser.getUserId());
		ObjectListSearchUsers obj = new ObjectListSearchUsers();
		obj.setUserSearchs(listSearchInfo);
		obj.setHasData(listSearchInfo.size() > 0 ? true : false);
		return obj;
	}

	private List<TagGroup> convertCardTagIdList(List<TagForCard> cardTagModelList) {
		List<TagGroup> tagGroups = new ArrayList<TagGroup>();
		TagForCard cardTagId;

		if (cardTagModelList.size() != 0) {
			tagGroups = cardTagModelList.stream().collect(Collectors.groupingBy(g -> g.getTagId())).entrySet().stream()
					.map(t -> {
						TagGroup tg = new TagGroup();
						List<Integer> cardIds = new ArrayList<Integer>();

						tg.setTagId(t.getKey());
						tg.setUserId(t.getValue().stream().map(x -> x.getUserId()).findFirst().get());
						if (t.getValue().get(0).getCardId() != null) {
							tg.setListCardIds(
									t.getValue().stream().map(x -> x.getCardId()).collect(Collectors.toList()));
						} else {
							tg.setListCardIds(cardIds);
						}
						tg.setTagName(t.getValue().stream().map(x -> x.getTagName()).findFirst().get());
						return tg;
					}).collect(Collectors.toList());
		}

		return tagGroups;
	}

	@RequestMapping(value = "listCardTag", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<TagGroup> listCardTag() {
		return getCardTag();
	}

	private List<TagGroup> getCardTag() {
		List<TagGroup> listTagGroup = null;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
			Integer userId = ecardUser.getUserId();

			// List tag
			List<TagForCard> listCardTag = getAllTagForCard(userId);
			List<TagForCard> listUserTag = cardTagService.listUserTagByUserId(userId);
			if (listCardTag.size() == 0) {
				listTagGroup = convertCardTagIdList(listUserTag);
			} else {
				listTagGroup = convertCardTagIdList(listCardTag);
			}
		} catch (Exception ex) {
			logger.debug("Exception : ", ex.getMessage());
		}
		return listTagGroup;
	}

	@RequestMapping(value = "deleteUserSearch", method = RequestMethod.POST)
	@ResponseBody
	public ObjectListSearchUsers deleteUserSearch(@RequestParam(value = "id") String id) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
		List<Integer> liSeq = new ArrayList<Integer>();
		liSeq.add(Integer.parseInt(id));
		try {
			searchInfoService.deleteSearchText(ecardUser.getUserId(), liSeq);
		} catch (Exception e) {
			e.printStackTrace();
			ObjectListSearchUsers obj = new ObjectListSearchUsers();
			obj.setHasData(false);
			return obj;
		}

		List<SearchInfo> listSearchInfo = searchInfoService.listSearchText(ecardUser.getUserId());
		ObjectListSearchUsers obj = new ObjectListSearchUsers();
		obj.setUserSearchs(listSearchInfo);
		obj.setHasData(listSearchInfo.size() >= 0 ? true : false);
		return obj;
	}

	@RequestMapping(value = "addTagHome", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<TagGroup> addTagHome(@RequestBody CardAndUserTagHome cardAndUserTagHome, HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug("addTag", UserController.class);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
		UserInfo userInfo = new UserInfo();
		userInfo.setUserId(ecardUser.getUserId());
		Integer tagId = 0;
		UserTag userTag = new UserTag();
		userTag.setTagName(cardAndUserTagHome.getTagName());
		userTag.setUserInfo(userInfo);
		tagId = userTagService.registerUserTag(userTag);

		for (TagUserHome tagUserHome : cardAndUserTagHome.getListCardId()) {
			CardTagId cardTag = new CardTagId();
			cardTag.setCardId(tagUserHome.getCardId());
			cardTag.setTagId(tagId);
			cardTagService.addCardTag(cardTag);
		}

		List<TagGroup> listTagGroup = getCardTag();
		appendCardId(listTagGroup);
		return listTagGroup;
	}

	@RequestMapping(value = "searchCards", method = RequestMethod.POST)
	@ResponseBody
	public CardInfoLoadMoreVO searchCards(@RequestBody final UserSearchVO userSearchVO,
			HttpSession session) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
		UserInfo userInfo = userInfoService.getUserInfoByUserId(ecardUser.getUserId());
		List<com.ecard.core.vo.CardInfo> cardInfo = null;
		BigInteger count;
		CardInfoLoadMoreVO cardLoadMore=new CardInfoLoadMoreVO();
		if (!userSearchVO.getFreeText().equals("")) {
			userSearchVO.setCompany(null);
			userSearchVO.setName(null);
			userSearchVO.setPosition(null);
			userSearchVO.setDepartment(null);
			userSearchVO.setOwner(null);
		} else {
			userSearchVO.setFreeText(null);
			userSearchVO.setCompany(userSearchVO.getCompany().equals("")?null:userSearchVO.getCompany());
			userSearchVO.setName(userSearchVO.getName().equals("")?null:userSearchVO.getName());
			userSearchVO.setPosition(userSearchVO.getPosition().equals("")?null:userSearchVO.getPosition());
			userSearchVO.setDepartment(userSearchVO.getDepartment().equals("")?null:userSearchVO.getDepartment());
			userSearchVO.setOwner(userSearchVO.getOwner().equals("")?null:userSearchVO.getOwner());
		}

		if (userSearchVO.getParameterFlg() == 0) {
			cardInfo = cardInfoService.getListCardSearch(userInfo.getUserId(), userSearchVO.getFreeText(),
					userSearchVO.getName(), userSearchVO.getPosition(), userSearchVO.getDepartment(),
					userSearchVO.getCompany(), userSearchVO.getPage(), userInfo.getGroupCompanyId());
			count=cardInfoService.getTotalCardSearch(userInfo.getUserId(), userSearchVO.getFreeText(),userSearchVO.getName(), userSearchVO.getPosition(), userSearchVO.getDepartment(),
					userSearchVO.getCompany(),userInfo.getGroupCompanyId());
		} else {
			cardInfo = cardInfoService.getListCardSearchAll(userSearchVO.getOwner(), userSearchVO.getFreeText(),
					userSearchVO.getName(), userSearchVO.getPosition(), userSearchVO.getDepartment(),
					userSearchVO.getCompany(), userSearchVO.getPage(), userInfo.getGroupCompanyId());
			count=cardInfoService.getTotalCardSearchAll(userSearchVO.getOwner(), userSearchVO.getFreeText(),
					userSearchVO.getName(), userSearchVO.getPosition(), userSearchVO.getDepartment(),
					userSearchVO.getCompany(), userSearchVO.getPage(), userInfo.getGroupCompanyId());
		}
		userSearchVO.setDetail(false);
		session.setAttribute("searchDetail", userSearchVO);
		cardLoadMore.setCardInfo(cardInfo);
		cardLoadMore.setCount(count);
		return cardLoadMore;
	}

	@RequestMapping(value = "deleteListCard", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public int deleteListCard(@RequestBody final ListCardDelete listCardDelete) {
		List<Integer> listCard = new ArrayList<>();
		int result = 0;
		try {
			for (ObjectCards cardId : listCardDelete.getListCardId()) {
				listCard.add(Integer.parseInt(cardId.getCardId()));
			}
			result = cardInfoService.deleteListCard(listCard);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return result;
	}

	@RequestMapping("addBusinessCard")
	public ModelAndView addBusinessCard() {
		return new ModelAndView("add-business-card");
	}

	@RequestMapping(value = "saveBusinessCard", method = RequestMethod.POST)
	public ModelAndView saveBusinessCard(CardInfo cardInfo) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
		int userId = ecardUser.getUserId();
		UserInfo userInfo = userInfoService.getUserInfoByUserId(userId);
		// Set id for user login
		cardInfo.setCardOwnerId(userId);
		cardInfo.setCardOwnerName(ecardUser.getFullName());
		cardInfo.setOperaterId(userId);
		CompanyInfo companyInfo = new CompanyInfo();
		companyInfo.setCompanyId(0);

		cardInfo.setGroupCompanyId(userInfo.getGroupCompanyId());
		cardInfo.setCompanyInfo(companyInfo);
		cardInfo.setCreateDate(new Date());
		cardInfo.setUpdateDate(new Date());
		cardInfo.setApprovalStatus(1);
		if (cardInfo.getContactDate() == null || cardInfo.getContactDate().toString().equals("")) {
			cardInfo.setContactDate(new Date());
		}
		cardInfo.setNewestCardFlg(0);

		// handle address full
		List<String> listAddress = new ArrayList<String>(Arrays.asList(cardInfo.getAddressFull().trim().split(" ")));
		cardInfo.setAddress1(listAddress.get(0) != null ? listAddress.get(0) : "");
		cardInfo.setAddress2(listAddress.get(1) != null ? listAddress.get(1) : "");
		cardInfo.setAddress3(listAddress.get(2) != null ? listAddress.get(2) : "");
		for (int i = 0; i <= 2; i++) {
			if (listAddress.get(0) != null) {
				listAddress.remove(0);
			}
		}
		cardInfo.setAddress4(listAddress.stream().collect(Collectors.joining(" ")));
		cardInfo.setIsEditting(0);
		cardInfo.setDateEditting(new Date());

		cardInfo.setName(
				StringUtilsHelper.mergerStringEitherAWord(cardInfo.getLastName(), cardInfo.getFirstName(), " "));
		cardInfo.setNameKana(StringUtilsHelper.mergerStringEitherAWord(cardInfo.getLastNameKana(),
				cardInfo.getFirstNameKana(), " "));

		CardInfo cardInfoObject = cardInfoService.registerCardImageManualPCAdmin(cardInfo);

		PossessionCardId possessionCardId = new PossessionCardId();
		possessionCardId.setCardId(cardInfoObject.getCardId());
		possessionCardId.setUserId(userId);
		possessionCardId.setContactDate(new Date());
		possessionCardId.setCreateDate(new Date());

		PossessionCard posCard = new PossessionCard();
		posCard.setId(possessionCardId);
		posCard.setCardInfo(cardInfoObject);
		possessionCardService.registerPosCard(posCard);
		UploadCardThread uploadThread = new UploadCardThread(cardInfoObject);
		uploadThread.start();
		return new ModelAndView("redirect:../home");
	}

	@RequestMapping(value = "addCardTagHome", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<TagGroup> addCardTagHome(@RequestBody CardAndUserTagHome cardAndUserTagHome, HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug("addCardTag", UserController.class);
		for (TagUserHome tagUserHome : cardAndUserTagHome.getListCardId()) {
			CardTagId cardTag = new CardTagId();
			cardTag.setCardId(tagUserHome.getCardId());
			cardTag.setTagId(cardAndUserTagHome.getTagId());
			cardTagService.addCardTag(cardTag);
		}
		List<TagGroup> listTagGroup = getCardTag();
		appendCardId(listTagGroup);
		return listTagGroup;
	}

	@RequestMapping(value = "deleteCardTagHome", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<TagGroup> deleteCardTagHome(@RequestBody CardAndUserTagHome cardAndUserTagHome,
			HttpServletRequest request, HttpServletResponse response) {
		logger.debug("deleteCardTag", UserController.class);
		for (TagUserHome tagUserHome : cardAndUserTagHome.getListCardId()) {
			cardTagService.deleteCardTag(tagUserHome.getCardId(), cardAndUserTagHome.getTagId());
		}
		List<TagGroup> listTagGroup = getCardTag();
		appendCardId(listTagGroup);
		return listTagGroup;
	}

	private void appendCardId(List<TagGroup> listTagGroup) {
		for (TagGroup tagGroup : listTagGroup) {
			if (tagGroup.getListCardIds().size() > 0) {
				String str = "";
				for (Integer cardId : tagGroup.getListCardIds()) {
					str = str + "," + cardId;
				}
				tagGroup.setCardId(str.substring(1, str.length()));
			}
		}
	}

	@RequestMapping(value = "notificationDetailRibbon", method = RequestMethod.POST)
	@ResponseBody
	public boolean notificationDetailRibbon(@RequestParam(value = "id") int id) {
		UserNotification notify = new UserNotification();
		notify.setNoticeId(id);
		notify.setReadFlg(1);
		notificationInfoService.updateReadFlgById(notify);
		return true;
	}

	@RequestMapping("overlapcards")
	public ModelAndView list() {
		return new ModelAndView("overlapcards");
	}

	@RequestMapping(value = "searchOverLapCards", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public DataPagingJsonVO<com.ecard.core.vo.CardInfo> searchOverLapCards(HttpServletRequest request) {
		int limit = parseIntParameter(request.getParameter("length"), 0);
		int offset = parseIntParameter(request.getParameter("start"), 0);
		String criteriaSearch = request.getParameter("criteriaSearch");
		DataPagingJsonVO<com.ecard.core.vo.CardInfo> dataTableResponse = new DataPagingJsonVO<com.ecard.core.vo.CardInfo>();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
		UserInfo userInfo = userInfoService.getUserInfoByUserId(ecardUser.getUserId());
		List<com.ecard.core.vo.CardInfo> cards = cardInfoService.getListCardSearch(userInfo.getUserId(), criteriaSearch,
				null, null, null, null, offset / limit, userInfo.getGroupCompanyId());
		cards.forEach(c -> {
			c.setContactDateString(
					c.getContactDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString());
		});

		BigInteger totalRecord = cardInfoService.getTotalCardSearch(userInfo.getUserId(), criteriaSearch, null, null,
				null, null, userInfo.getGroupCompanyId());
		dataTableResponse.setDraw(parseIntParameter(request.getParameter("draw"), 0));
		dataTableResponse.setRecordsTotal(totalRecord.longValue());
		dataTableResponse.setRecordsFiltered(totalRecord.longValue());
		dataTableResponse.setData(cards);
		//HttpSession session= request.getSession();
		//back from detail PC
		/*if(!criteriaSearch.equals("")){
			
			OverlapSearchDetail overlapSearchDetail=(OverlapSearchDetail)session.getAttribute("overlapSearchDetail");
			//if(overlapSearchDetail.isDetail()==false){
				
				overlapSearchDetail.setSearch(criteriaSearch);
				overlapSearchDetail.setCards(null);
				overlapSearchDetail.setDetail(false);
				session.setAttribute("overlapSearchDetail", overlapSearchDetail);	
			//}
				
		}else{
			OverlapSearchDetail overlapSD=new OverlapSearchDetail();
			session.setAttribute("overlapSearchDetail", overlapSD);	
		}*/
		
		
		return dataTableResponse;
	}

	@RequestMapping(value = "listConnectCards", method = RequestMethod.POST)
	@ResponseBody
	public List<OwnerCards> listConnectCards(@RequestBody final com.ecard.core.vo.CardInfo cardInfo,
			HttpSession session) {
		List<OwnerCards> ownerCards = new ArrayList<OwnerCards>();
		OwnerCards ownerCard = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
		List<com.ecard.core.vo.CardInfo> cards = cardInfoService.getListConnectCards(cardInfo,ecardUser.getUserId());
		if (!CollectionUtils.isEmpty(cards)) {
			List<Integer> userIds = cards.stream().map(x -> x.getCardOwnerId()).collect(Collectors.toList());

			List<UserInfoVo> users = userInfoService.getUserInArrUserId(userIds);
			List<UserInfoVo> userTemps=null;
			UserInfoVo user = null;
			LocalDate contactDate = null;
			for (com.ecard.core.vo.CardInfo item : cards) {
				ownerCard = new OwnerCards();
				ownerCard.setCardId(item.getCardId());
				ownerCard.setAddressFull(item.getAddressFull());
				ownerCard.setCompanyName(item.getCompanyName());
				ownerCard.setContactDate(item.getContactDate());
				ownerCard.setDepartmentName(item.getDepartmentName());
				ownerCard.setEmail(item.getEmail());
				ownerCard.setName(item.getName());
				ownerCard.setPositionName(item.getPositionName());
				ownerCard.setTelNumberCompany(item.getTelNumberCompany());
				ownerCard.setImage(item.getImageFile());
				userTemps = users.stream().filter(x -> x.getUserId().intValue() == item.getCardOwnerId().intValue()).collect(Collectors.toList());
				if(!CollectionUtils.isEmpty(userTemps)){
					user = userTemps.stream().findFirst().get();
					ownerCard.setOwner(
							StringUtilsHelper.mergerStringEitherAWord(user.getLastName(), user.getFirstName(), " "));
					ownerCard.setContactDateString(
							ownerCard.getContactDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString());
					ownerCards.add(ownerCard);
				}
				

			}
			
		}
		
		/*if (session.getAttribute("overlapSearchDetail") != null) {
			OverlapSearchDetail overlapSearchDetail=(OverlapSearchDetail)session.getAttribute("overlapSearchDetail");
			overlapSearchDetail.setCards(cardInfo);
			session.setAttribute("overlapSearchDetail", overlapSearchDetail);
        }*/
		

		return ownerCards;
	}

	@RequestMapping(value = "handleConnectCards", method = RequestMethod.POST)
	@ResponseBody
	public boolean handleConnectCards(@RequestParam(value = "cardid1") int cardid1,
			@RequestParam(value = "cardid2") int cardid2) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
		UserInfo user = userInfoService.getUserInfoByUserId(ecardUser.getUserId());
		CardInfo cardOwner=cardInfoService.getCardInfoDetail(cardid2);
		CardInfo card1=cardInfoService.getCardInfoDetail(cardid1);
		CardInfo card2=cardInfoService.getCardInfoDetail(cardid2);
		/*return cardInfoService.handleConnectCards(cardid1, cardid2, ecardUser.getUserId(),
				StringUtilsHelper.mergerStringEitherAWord(user.getLastName(), user.getFirstName(), " "));*/
		try{
			int ownerUserId=card2.getCardOwnerId();
			String ownerName=card2.getCardOwnerName();
			int  ownerGroupCompanyId=card2.getGroupCompanyId();
			card1.setOldCardFlg(1);
			cardInfoService.updateCardInfoNotCreateIndex(card1);
			
			//int card_id=cardInfoService.connectCards(cardid1, cardid2, ecardUser.getUserId(), StringUtilsHelper.mergerStringEitherAWord(user.getLastName(), user.getFirstName(), " "));
			System.out.println("connectCards =============successfull==");
			System.out.println("=====================================connectCards =============successfull======================userid: "+ecardUser.getUserId().intValue()+" getCardOwnerId: "+cardOwner.getCardOwnerId().intValue());
			if(cardOwner.getCardOwnerId().intValue()!=ecardUser.getUserId().intValue()){
				card2.setCardOwnerId( ecardUser.getUserId());
				card2.setCardOwnerName( StringUtilsHelper.mergerStringEitherAWord(user.getLastName(), user.getFirstName(), " "));
				card2.setGroupCompanyId(card1.getGroupCompanyId());
				card2.setContactDate(card1.getContactDate());
				CardInfo newCard= cardInfoService.registerCardImageOverLapOfAdmin(setCardInfo(card2));
				
				card2.setCardOwnerId(ownerUserId);
				card2.setCardOwnerName(ownerName);
				card2.setGroupCompanyId(ownerGroupCompanyId);
				System.out.println("=====================================before: updateCardInfoNotCreateIndex===================================");
				cardInfoService.updateCardInfoNotCreateIndex(card2);
				System.out.println("=====================================after: updateCardInfoNotCreateIndex===================================: "+newCard.getCardId());
				CopyImage copy=new CopyImage(card2.getImageFile(),newCard.getImageFile());
				copy.start();
				
				
				OldCard oldcard=new OldCard();
				CardInfo newCardInfo=new CardInfo();
				newCardInfo.setCardId(newCard.getCardId());
				oldcard.setCardInfo(newCardInfo);
				
				OldCardId oldCardId=new OldCardId();
				oldCardId.setCardId(newCardInfo.getCardId());
				oldCardId.setCardOwnerId(ecardUser.getUserId());
				oldCardId.setSeq(0);
				oldCardId.setOldCardId(cardid1);
				oldcard.setId(oldCardId);
				System.out.println("=====================================before: owner insertOldCard===================================");
				oldcard=oldCardService.insertOldCard(oldcard);
				System.out.println("=====================================after: owner insertOldCard===================================");
				oldCardService.updateCardIdWithOldCard(newCard.getCardId(), cardid1);
				System.out.println("=====================================after:owner updateCardIdWithOldCard===================================");
				userCardMemoService.updateUserCardMemo(cardid1, ecardUser.getUserId().intValue(), newCard.getCardId());
				contactHistoryService.updateContactHistory(cardid1, ecardUser.getUserId().intValue(), newCard.getCardId());
			}else{
				userCardMemoService.updateUserCardMemo(cardid1, ecardUser.getUserId().intValue(), cardid2);
				contactHistoryService.updateContactHistory(cardid1, ecardUser.getUserId().intValue(), cardid2);
				
				OldCard oldcard=new OldCard();
				
				CardInfo cardInfor=new CardInfo();
				cardInfor.setCardId(cardid2);
				
				OldCardId oldCardId=new OldCardId();
				oldCardId.setCardId(cardid2);
				oldCardId.setCardOwnerId(ecardUser.getUserId());
				oldCardId.setSeq(0);
				oldCardId.setOldCardId(cardid1);
				oldcard.setCardInfo(cardInfor);
				
				oldcard.setId(oldCardId);
				System.out.println("=====================================before: insertOldCard===================================");
				oldcard=oldCardService.insertOldCard(oldcard);
				System.out.println("=================================after: insertOldCard===================================: ");
				oldCardService.updateCardIdWithOldCard(oldcard.getId().getCardId(), cardid1);
				System.out.println("=====================================after: updateCardIdWithOldCard===================================");
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("=====================================handleConnectCards error=================================="+e.getMessage());
		}
		
		return true;
	}

	class UploadCardThread extends Thread {
		CardInfo cardInfo;

		UploadCardThread(CardInfo cardInfo) {
			this.cardInfo = cardInfo;
		}

		public void run() {
			try {
				ClassLoader classLoader = DataProcessController.class.getClassLoader();
				File file = new File(classLoader.getResource("MSMINCHO.TTF").getFile());
				Font font = Font.createFont(Font.TRUETYPE_FONT, file);

				Thread.sleep(1000);
				BufferedImage image = UploadFileUtil.decodeToImage(defaultImage64);
				Graphics g = image.getGraphics();
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				font = font.deriveFont(Font.BOLD, 20f);
				// Font font = new Font(Font.SANS_SERIF, Font.BOLD, 20);
				g2.setFont(font);
				g2.setColor(Color.BLACK);
				g2.drawString(cardInfo.getCompanyName() != null
						? StringUtilsHelper.convertToUTF8(cardInfo.getCompanyName()) : StringUtils.EMPTY,
						CommonConstants.xCooder, CommonConstants.yCooder);
				g2.drawString(
						cardInfo.getDepartmentName() != null
								? StringUtilsHelper.convertToUTF8(cardInfo.getDepartmentName()) : StringUtils.EMPTY,
						CommonConstants.xCooder, CommonConstants.yCooder + 30);
				g2.drawString(cardInfo.getPositionName() != null
						? StringUtilsHelper.convertToUTF8(cardInfo.getPositionName()) : StringUtils.EMPTY,
						CommonConstants.xCooder, CommonConstants.yCooder + 60);

				g2.drawString(cardInfo.getName() != null ? StringUtilsHelper.convertToUTF8(cardInfo.getName())
						: StringUtils.EMPTY, CommonConstants.xCooder + 30, CommonConstants.yCooder + 150);
				g2.dispose();
				FileUploadModel fileUploadModel = UploadFileUtil.overrideImage(UploadFileUtil.encodeToString(image, "jpg"), scpHostName, scpUser,
						scpPassword, cardInfo.getImageFile());
				if(!fileUploadModel.isStatus()){
	            	UploadFileUtil.writeLostImage(UploadFileUtil.encodeToString(image, "jpg"), cardInfo.getImageFile());
				}
			} catch (Exception ex) {
				logger.error("Error upload default card image: " + ex.getMessage());
			}
		}
	}

	@RequestMapping(value = "companyTree")
	public ModelAndView companyTree(HttpServletRequest request) {
		logger.debug("companyTree", UserController.class);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("companyTree");
		return modelAndView;
	}

	@RequestMapping(value = "searchCompany", method = RequestMethod.POST)
	@ResponseBody
	public String searchCompany(@RequestBody com.ecard.core.vo.CardInfo cardInfo, HttpServletRequest request) {
		logger.debug("searchCompany", UserController.class);

		List<com.ecard.core.vo.CardInfo> cardList = null;
		String jsonObj = "";
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
			UserInfo userInfo = userInfoService.getUserInfoByUserId(ecardUser.getUserId());
			cardList = cardInfoService.searchCompanyTree(userInfo, cardInfo.getCompanyName());
			if (cardList.size() > 0) {
				jsonObj = new Gson().toJson(cardList);
			}
		} catch (Exception ex) {
			logger.debug("Exception : " + ex.getMessage(), UserController.class);
		}

		return jsonObj;
	}

	@RequestMapping(value = "searchDepartment", method = RequestMethod.POST, produces = "application/json;charset=utf-8", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String searchDepartment(@RequestBody com.ecard.core.vo.CardInfo cardInfo, HttpServletRequest request,
			HttpServletResponse response) {
		List<com.ecard.core.vo.CardInfo> cardList = null;
		String jsonObj = "";
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
			UserInfo userInfo = userInfoService.getUserInfoByUserId(ecardUser.getUserId());
			
			cardList = cardInfoService.searchDepartment(userInfo, cardInfo.getCompanyName());
			if (cardList.size() > 0) {
				jsonObj = new Gson().toJson(cardList);
			}
		} catch (Exception ex) {
			logger.debug("Exception : " + ex.getMessage(), UserController.class);
		}

		return jsonObj;
	}

	@RequestMapping(value = "searchCardInfo", method = RequestMethod.POST, produces = "application/json;charset=utf-8", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String searchCardInfo(@RequestBody com.ecard.core.vo.CardInfo cardInfo, HttpServletRequest request,
			HttpServletResponse response) {
		List<com.ecard.core.vo.CardInfo> cardList = null;
		String jsonObj = "";
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
			UserInfo userInfo = userInfoService.getUserInfoByUserId(ecardUser.getUserId());
			
			if(cardInfo.getDepartmentName() == null){
				cardInfo.setDepartmentName("");
			}
			cardList = cardInfoService.searchCardInfo(userInfo, cardInfo.getCompanyName(), cardInfo.getDepartmentName());
			if (cardList.size() > 0) {
				jsonObj = new Gson().toJson(cardList);
			}
		} catch (Exception ex) {
			logger.debug("Exception : " + ex.getMessage(), UserController.class);
		}

		return jsonObj;
	}
	
	@RequestMapping(value = "companyTree/list")
	public ModelAndView listCardByName(@RequestParam String compName, @RequestParam String deptName, @RequestParam String name, HttpServletRequest request) {
		logger.debug("listCardByName", UserController.class);

		ModelAndView modelAndView = new ModelAndView();
		try{
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
			UserInfo userInfo = userInfoService.getUserInfoByUserId(ecardUser.getUserId());
			
			List<CardInfo> cardList = cardInfoService.searchCardInfoByName(userInfo, compName, deptName, name);
			
			if(cardList.size() > 0){
				modelAndView.addObject("cardInfoList", cardList);
			}
		}
		catch(Exception ex){
			logger.debug("Exception : " + ex.getMessage(), UserController.class);
		}
		modelAndView.setViewName("listCardByName");
		return modelAndView;
	}
	
	@RequestMapping(value = "deleteAllNotify", method = RequestMethod.POST)
	@ResponseBody
	public boolean deleteAllNotify() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
		List<NotificationList> listUpdate = notificationInfoService.listAllNofiticationUser(ecardUser.getUserId());
		notificationInfoService.deleteAllNotify(listUpdate);
		return true;
	}

	private Date convertStringToDate(String strDate){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM");
		Date date = null;
		try {
			date = formatter.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	@RequestMapping(value = "getListPossesionCardRecent", method = RequestMethod.POST)
	@ResponseBody
    public List<com.ecard.core.vo.CardInfo> getListPossesionCardRecent( @RequestParam(required = false) Integer page,HttpServletRequest request) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
		List<com.ecard.core.vo.CardInfo> lstCardInfo = null;
		lstCardInfo = cardInfoService.getListPossesionCardRecentPaging(ecardUser.getUserId(),page);
	  return lstCardInfo;
	}
	
	@RequestMapping(value = "listCardRecent", method = RequestMethod.POST)
	@ResponseBody
    public List<com.ecard.core.vo.CardInfo> listCardRecent(HttpServletRequest request) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
		List<com.ecard.core.vo.CardInfo> lstCardInfo = null;
		lstCardInfo = myCardService.listCardRecent(ecardUser.getUserId());
	  return lstCardInfo;
	}
	
	@RequestMapping(value = "listCardPending", method = RequestMethod.POST)
	@ResponseBody
    public List<CardInfoAndPosCardVO> listCardPending(HttpServletRequest request) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
		List<CardInfoAndPosCard> lstCardInfo =  cardInfoService.listCardPending(ecardUser.getUserId());
		List<CardInfoAndPosCardVO> lstcardInfoAndPosCardVO = new ArrayList<>();
		
		for(CardInfoAndPosCard cardInfo : lstCardInfo){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
		    String date = formatter.format(cardInfo.getCreateDate());
			CardInfoAndPosCardVO cardInfoAndPosCardVO = new CardInfoAndPosCardVO(cardInfo.getCardId(), cardInfo.getApprovalStatus(), cardInfo.getImageFile(), date);
			lstcardInfoAndPosCardVO.add(cardInfoAndPosCardVO);
		}
	  return lstcardInfoAndPosCardVO;
	}

	@RequestMapping(value = "/overlapcardsbyname/{id:[\\d]+}", method = RequestMethod.GET)
	public ModelAndView overlapcardsbyname(@PathVariable("id") int id) {
		String name=StringUtils.EMPTY;
		
		CardInfo card= cardInfoService.getCardInfoDetail(id);
		if(card!=null){
			return new ModelAndView("overlapcards","fullname",card.getName()==null?StringUtilsHelper.mergerStringEitherAWord(card.getLastName(), card.getFirstName(), " "):card.getName());
		}
		
		return new ModelAndView("redirect:/user/home");
	}
	
	@RequestMapping(value = "listConnectUser", method = RequestMethod.POST)
	@ResponseBody
    public CardInfoConnectUserResponse listConnectUser(@RequestParam Integer recentFlg, @RequestParam(required = false) Integer page, HttpServletRequest request) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
		CardInfoConnectUserResponse response = new CardInfoConnectUserResponse();
		List<CardInfoConnectUser> cardInfo = cardInfoService.listConnectUser(ecardUser.getUserId(), recentFlg, page);      
        try {
			response.setCardList(CardInfoConverter.convertConnectCardList(cardInfo));
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return response;
	}
	
	@RequestMapping(value = "totalConnect", method = RequestMethod.POST)
	@ResponseBody
    public int countListConnectUser(@RequestParam Integer recentFlg, HttpServletRequest request) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
		BigInteger total = cardInfoService.totalListConnectUser(ecardUser.getUserId(), recentFlg);    
      
        return total.intValue();
	}
	
	@RequestMapping(value = "getNotitfyOfCurrentUser", method = RequestMethod.POST)
	@ResponseBody
    public List<NotificationList> getNotitfyOfCurrentUser(@RequestParam(value = "page") int page) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
		List<NotificationList> listUpdate = null;
		try{
			listUpdate = notificationInfoService.getListNotificationPaging(ecardUser.getUserId(),page);
			if(!CollectionUtils.isEmpty(listUpdate)){
				List<Integer> cardIds=listUpdate.stream().map(x->x.getCard_id()).collect(Collectors.toList());
				if(!CollectionUtils.isEmpty(cardIds)){
					List<NotificationList> notifies= cardInfoService.getImagesBy(cardIds);
					listUpdate.forEach(n->{
						List<NotificationList> matchImage=notifies.stream().filter(x->x.getCard_id().intValue()==n.getCard_id().intValue()).collect(Collectors.toList());
						if(CollectionUtils.isEmpty(matchImage)){
							n.setImage("");	
						}else{
							n.setImage(matchImage.stream().findFirst().get().getImage());
						}
						n.setNotice_date_local((n.getNotice_date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()).toString());
					});
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("=================getNotitfyOfCurrentUser error========================="+e.getMessage());
		}
		
		//listUpdate=UploadFileUtil.getImageFileFromSCPForNotification(listUpdate, scpHostName, scpUser, scpPassword, Integer.parseInt(scpPort));
        return listUpdate;
	}
	
	@RequestMapping(value = "totalRecent", method = RequestMethod.POST)
	@ResponseBody
    public int totalRecent(HttpServletRequest request) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
		BigInteger total = cardInfoService.totalListCardRecent(ecardUser.getUserId());    
        return total.intValue();
	}
	
	public static String nlToBR(String str) {
        if (str == null || "".equals(str)) {
          return "";
        }
        str = str.replaceAll("\r\n", "<br />");
        str = str.replaceAll("\n", "<br />");
        return str;
      }
	
	private CardInfo setCardInfo(CardInfo card){
		CardInfo newcard=new CardInfo();
		newcard.setCompanyInfo(card.getCompanyInfo());
		newcard.setCardType(card.getCardType());
	     newcard.setImageFile(card.getImageFile());
	     newcard.setCardBackImgFile(card.getCardBackImgFile());
	     newcard.setCompanyName(card.getCompanyName());
	     newcard.setCompanyNameKana(card.getCompanyNameKana());
	     newcard.setDepartmentName(card.getDepartmentName());
	     newcard.setPositionName(card.getPositionName());
	     newcard.setName(card.getName());
	     newcard.setLastName(card.getLastName());
	     newcard.setFirstName(card.getFirstName());
	     newcard.setNameKana(card.getNameKana());
	     newcard.setLastNameKana(card.getLastNameKana());
	     newcard.setFirstNameKana(card.getFirstNameKana());
	     newcard.setEmail(card.getEmail());
	     newcard.setZipCode(card.getZipCode());
	     newcard.setAddressFull(card.getAddressFull());
	     newcard.setAddress1(card.getAddress1());
	     newcard.setAddress2(card.getAddress2());
	     newcard.setAddress3(card.getAddress3());
	     newcard.setAddress4(card.getAddress4());
	     newcard.setTelNumberCompany(card.getTelNumberCompany());
	     newcard.setTelNumberDepartment(card.getTelNumberDepartment());
	     
	     
	     newcard.setTelNumberDirect(card.getTelNumberDirect());
	     newcard.setFaxNumber(card.getFaxNumber());
	     newcard.setMobileNumber(card.getMobileNumber());
	     newcard.setCompanyUrl(card.getCompanyUrl());
	     newcard.setSubAddressFull(card.getSubAddressFull());
	     newcard.setSubZipCode(card.getSubZipCode());
	     newcard.setSubAddress1(card.getSubAddress1());
	     newcard.setSubAddress2(card.getSubAddress2());
	     newcard.setSubAddress3(card.getSubAddress3());
	     newcard.setSubAddress4(card.getSubAddress4());
	     
	     newcard.setSubTelNumberCompany(card.getSubTelNumberCompany());
	     newcard.setSubTelNumberDepartment(card.getSubTelNumberDepartment());
	     newcard.setSubTelNumberDirect(card.getSubTelNumberDirect());
	     newcard.setSubFaxNumber(card.getSubFaxNumber());
	     newcard.setFileOutputFlg(card.getFileOutputFlg());
	     newcard.setHandMemo(card.getHandMemo());
	     newcard.setAutoMemo(card.getAutoMemo());
	     newcard.setMemo1(card.getMemo1());
	     newcard.setMemo2(card.getMemo2());
	     newcard.setMemo1(card.getMemo1());
	     
	     
	     newcard.setCardOwnerId(card.getCardOwnerId());
	     newcard.setPublishStatus(card.getPublishStatus());
	     newcard.setApprovalStatus(card.getApprovalStatus());
	     newcard.setOldCardFlg(card.getOldCardFlg());
	     newcard.setCreateDate(card.getCreateDate());
	     newcard.setUpdateDate(card.getUpdateDate());
	     newcard.setOperaterId(card.getOperaterId());
	     newcard.setDeletDate(card.getDeletDate());
	     newcard.setDeleteFlg(card.getDeleteFlg());
	     newcard.setCardOwnerName(card.getCardOwnerName());
	     newcard.setGroupCompanyId(card.getGroupCompanyId());
	     newcard.setNewestCardFlg(card.getNewestCardFlg());
	     newcard.setContactDate(card.getContactDate());
	     newcard.setCardIndexNo(card.getCardIndexNo());
	     
	     newcard.setSubMobileNumber(card.getSubMobileNumber());
	     newcard.setSubEmail(card.getSubEmail());
	     newcard.setSubCompanyUrl(card.getSubCompanyUrl());
	     newcard.setImportanceLevel(card.getImportanceLevel());
	     newcard.setIsEditting(card.getIsEditting());
	     newcard.setDateEditting(card.getDateEditting());
	     return newcard;
	}
	
	class CopyImage extends Thread {
		private String oldImage;
		private String newImage;

		public CopyImage(String oldImage,String newImage) {
			this.oldImage=oldImage;
			this.newImage=newImage;
		}

		public void run() {
			System.out.println("===============================card2: "+oldImage+" new card"+newImage);
			String oldImageData=UploadFileUtil.getImageFileFromSCP(oldImage, scpHostName, scpUser, scpPassword,Integer.parseInt(scpPort));
			//System.out.println("===================================oldImageData: "+oldImageData);
			try {
				UploadFileUtil.writeImage(oldImageData, newImage, scpHostName, scpUser, scpPassword);
				System.out.println("=====================================Copy image OK===================================");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("=====================================can not upload image connect card===================================");
			}
		}
	}
}
