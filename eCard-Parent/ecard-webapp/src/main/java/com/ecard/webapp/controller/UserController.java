package com.ecard.webapp.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.servlet.ServletOutputStream;
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
import com.ecard.core.model.PossessionCard;
import com.ecard.core.model.PossessionCardId;
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
import com.ecard.core.service.PossessionCardService;
import com.ecard.core.service.SearchInfoService;
import com.ecard.core.service.SettingsInfoService;
import com.ecard.core.service.UserInfoService;
import com.ecard.core.service.UserTagService;
import com.ecard.core.service.converter.CardInfoConverter;
import com.ecard.core.vo.CardAndUserTag;
import com.ecard.core.vo.CardConnectModel;
import com.ecard.core.vo.CardInfoAndPosCard;
import com.ecard.core.vo.CardInfoCSV;
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
import com.ecard.webapp.util.StringUtilsHelper;
import com.ecard.webapp.util.UploadFileUtil;
import com.ecard.webapp.vo.CardAndUserTagHome;
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

	@Autowired
	SettingsInfoService settingsInfoService;

	@Autowired
	NotificationInfoService notificationInfoService;

	@Autowired
	SearchInfoService searchInfoService;

	@Value("${card.default.base64}")
	private String defaultImage64;

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
			
			listTotalCardInfo = cardInfoService.countPossessionCard(ecardUser.getUserId());
			listTagGroup = getCardTag();
			
				List<CardInfoUserVo> lstCardInfo = cardInfoService.getListPossesionCard(ecardUser.getUserId(), SearchConditions.CONTACT.getValue() ,lstNameSort.get(0));
				List<CardInfo> cardInfoDisp = new ArrayList<>();
				for (CardInfoUserVo cardInfo : lstCardInfo) {
					if (lstNameSort.get(0).trim().equals(cardInfo.getSortType().trim())) {
						cardInfoDisp.add(cardInfo.getCardInfo());
					}
				}
				CardInfoPCVo cardInfoPCVo;
				try {
					if (cardInfoDisp.size() > 0) {
						cardInfoPCVo = new CardInfoPCVo(lstNameSort.get(0), CardInfoConverter.convertCardInforList(cardInfoDisp));
						lstCardInfoPCVo.add(cardInfoPCVo);
					}
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			
		}
		appendCardId(listTagGroup);

		// UserSearchVO
		// u=(UserSearchVO)request.getSession().getAttribute("searchDetail");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("homePC");
		modelAndView.addObject("lstNameSort",lstNameSort);
		modelAndView.addObject("lstCardInfoPCVo", lstCardInfoPCVo);
		modelAndView.addObject("totalCardInfo", listTotalCardInfo);
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
		String valueSearch = request.getParameter("page");		
		DataPagingJsonVO<CardInfoPCVo> dataTableResponse = new DataPagingJsonVO<CardInfoPCVo>();
		List<CardInfoPCVo> cardInfoSearchResponses = new ArrayList<CardInfoPCVo>();
		List<String> lstNameSort = null;
		List<CardInfo> listCardSortNameCompany = null;
		List<CardInfoUserVo> lstCardInfo = null;
		
		// Search all
		// 
		if(searchType == 0) {
			lstNameSort = cardInfoService.getListSortType(ecardUser.getUserId(), typeSort);
			if(valueSearch == "" || valueSearch == null){
				lstCardInfo = cardInfoService.getListPossesionCard(ecardUser.getUserId(), typeSort, lstNameSort.get(0).substring(0,1));
			} else {
				lstCardInfo = cardInfoService.getListPossesionCard(ecardUser.getUserId(), typeSort, valueSearch);
			}
			
			
			if (typeSort == SearchConditions.NAME.getValue()) {
				lstNameSort = lstNameSort.stream().map(str->str.substring(0, 1).toUpperCase()).collect(Collectors.toList());
			}
			
		}
		/*if (searchType == 0) {
			if (typeSort == SearchConditions.CONTACT.getValue()) {
				lstNameSort = cardInfoService.getListSortType(ecardUser.getUserId());
				lstCardInfo = cardInfoService.getListPossesionCard(ecardUser.getUserId(), "10/2015");
			} else if (typeSort == SearchConditions.NAME.getValue()) {
				listCardSortNameCompany = cardInfoService.getListPossesionCard(ecardUser.getUserId(), null, SearchConditions.NAME.name().toLowerCase(), 0);
				lstCardInfo = new ArrayList<>();
				lstNameSort = new ArrayList<>();
				for (CardInfo cardInfoModel : listCardSortNameCompany) {
					if (!cardInfoModel.getNameKana().equals("")) {
						sortType = cardInfoModel.getNameKana().substring(0, 1);
					}
					lstNameSort.add(sortType.toUpperCase());
					CardInfoUserVo cardInfoUserVo = new CardInfoUserVo(sortType.toUpperCase(), cardInfoModel);
					lstCardInfo.add(cardInfoUserVo);

				}
			} else {
				listCardSortNameCompany = cardInfoService.getListPossesionCard(ecardUser.getUserId(), null, SearchConditions.COMPANY.name().toLowerCase(), 0);
				lstCardInfo = new ArrayList<>();
				lstNameSort = new ArrayList<>();
				for (CardInfo cardInfoModel : listCardSortNameCompany) {
					if (!cardInfoModel.getCompanyNameKana().equals("")) {
						sortType = cardInfoModel.getCompanyNameKana().substring(0, 1);
					}
					lstNameSort.add(sortType.toUpperCase());
					CardInfoUserVo cardInfoUserVo = new CardInfoUserVo(sortType.toUpperCase(), cardInfoModel);
					lstCardInfo.add(cardInfoUserVo);

				}
			}

		} else {			
			if (typeSort == SearchConditions.CONTACT.getValue()) {
				lstCardInfo = cardInfoService.getListPossessionCardByTag(ecardUser.getUserId(), searchType, 0);
				lstNameSort = cardInfoService.getListSortTypeByTag(ecardUser.getUserId(), searchType);
				// lstCardInfo =
				// cardInfoService.getListPossesionCard(ecardUser.getUserId(),
				// limit);
			} else if (typeSort == SearchConditions.NAME.getValue()) {
				listCardSortNameCompany = cardInfoService.getListPossessionCardByTag(ecardUser.getUserId(), searchType,
						SearchConditions.NAME.name().toLowerCase(), 0);
				lstCardInfo = new ArrayList<>();
				lstNameSort = new ArrayList<>();
				for (CardInfo cardInfoModel : listCardSortNameCompany) {
					if (!cardInfoModel.getNameKana().equals("")) {
						sortType = cardInfoModel.getNameKana().substring(0, 1);
					}
					lstNameSort.add(sortType.toUpperCase());
					CardInfoUserVo cardInfoUserVo = new CardInfoUserVo(sortType.toUpperCase(), cardInfoModel);
					lstCardInfo.add(cardInfoUserVo);
				}
			} else {
				listCardSortNameCompany = cardInfoService.getListPossessionCardByTag(ecardUser.getUserId(), searchType,
						SearchConditions.COMPANY.name().toLowerCase(), 0);
				lstCardInfo = new ArrayList<>();
				lstNameSort = new ArrayList<>();
				for (CardInfo cardInfoModel : listCardSortNameCompany) {
					if (!cardInfoModel.getCompanyNameKana().equals("")) {
						sortType = cardInfoModel.getCompanyNameKana().substring(0, 1);
					}
					lstNameSort.add(sortType.toUpperCase());
					CardInfoUserVo cardInfoUserVo = new CardInfoUserVo(sortType.toUpperCase(), cardInfoModel);
					lstCardInfo.add(cardInfoUserVo);
				}
			}
		}*/
/*		if (typeSort == SearchConditions.NAME.getValue() || typeSort == SearchConditions.COMPANY.getValue() || typeSort == SearchConditions.TAG.getValue()) {
			lstNameSort = lstNameSort.stream().distinct().sorted().collect(Collectors.toList());
		}*/
		for (String nameSort : lstNameSort) {
			List<CardInfo> cardInfoDisp = new ArrayList<>();
			for (CardInfoUserVo cardInfo : lstCardInfo) {
				//if (nameSort.trim().equals(cardInfo.getSortType().trim())) {
					cardInfoDisp.add(cardInfo.getCardInfo());
				//}
			}
			CardInfoPCVo cardInfoPCVo;
			try {
				if (cardInfoDisp.size() > 0) {
					cardInfoPCVo = new CardInfoPCVo(nameSort, CardInfoConverter.convertCardInforList(cardInfoDisp));
					cardInfoSearchResponses.add(cardInfoPCVo);
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}

		dataTableResponse.setData(cardInfoSearchResponses);
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
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
		UserInfo userInfo = userInfoService.getUserInfoByUserId(ecardUser.getUserId());

		List<DownloadCsv> downloadCSVHistory = userInfoService.getHistoryCSVDownload(ecardUser.getUserId());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("download");
		modelAndView.addObject("roleAdminId", userInfo.getRoleAdminId());
		modelAndView.addObject("downloadCSVHistory", downloadCSVHistory);
		return modelAndView;
	}

	@RequestMapping(value = "/downloadCSV/{id:[\\d]+}", method = RequestMethod.GET)
	@ResponseBody
	public void downloadCSV(HttpServletResponse response, @PathVariable("id") int id) throws IOException {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserInfo userInfo = userInfoService.findUserByEmail(authentication.getName());

			DownloadCsv downloadCsvId = new DownloadCsv();
			downloadCsvId.setUserInfo(userInfo);
			downloadCsvId.setApprovalDate(new Date());
			downloadCsvId.setRequestDate(new Date());

			String current = new SimpleDateFormat("ddMMyyyyhhmmss").format(new Date());
			String fileName = new String();
			List<CardInfoCSV> listUserInfoCSV;
			if (id == 2) {
				listUserInfoCSV = null;
				if (userInfo.getGroupCompanyId() != null) {
					List<CardInfo> listCardInfo = cardInfoService.getCompanyCard(userInfo.getGroupCompanyId());
					listUserInfoCSV = CardInfoConverter.convertCardInfoList(listCardInfo);
				} else {
					List<CardInfo> listCardInfo = cardInfoService.getListMyCard(userInfo.getUserId());
					listUserInfoCSV = CardInfoConverter.convertCardInfoList(listCardInfo);
				}
				fileName = "CompanyCard_" + current + userInfo.getUserId() + ".csv";
				downloadCsvId.setCsvType(CsvConstant.COMPANY_CARDS);
				downloadCsvId.setCsvUrl(fileName);
				createCSVFile(response, fileName, listUserInfoCSV, CsvConstant.DOWNLOAD_NOT_DIRECT);
				cardInfoService.saveDownloadHistory(downloadCsvId);
			} else {
				List<CardInfo> listCardInfo = cardInfoService.getListMyCard(userInfo.getUserId());
				listUserInfoCSV = CardInfoConverter.convertCardInfoList(listCardInfo);
				fileName = "MyCard_" + current + userInfo.getUserId() + ".csv";
				downloadCsvId.setCsvApprovalStatus(CsvConstant.IS_DOWNLOADED);
				downloadCsvId.setCsvType(CsvConstant.MY_CARDS);
				createCSVFile(response, fileName, listUserInfoCSV, CsvConstant.DOWNLOAD_DIRECT);
				cardInfoService.saveDownloadHistory(downloadCsvId);
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
			String fileName = downloadCsv.getCsvUrl();

			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserInfo userInfo = userInfoService.findUserByEmail(authentication.getName());
			// Connect to SCP and download File
			byte[] myBytes = UploadFileUtil.getFileCSVFromSCP(fileName, scpHostName, scpUser, scpPassword,
					Integer.parseInt(scpPort));
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
			// update History
			cardInfoService.updateDownloadHistory(downloadCsv.getCsvId());
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
			// ctx.setVariable("recordNumber",answerText);
			sendMailDownload(listUserId, ctx);

			//
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void deleteFileCSV(String fileName) throws IOException {

		String saveFileCSV = System.getProperty("user.dir") + "/csv";
		String fileExist = saveFileCSV + "/" + fileName;

		File myFile = new File(fileExist);
		if (myFile.exists())
			myFile.delete();
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

		String workingDirectory = System.getProperty("user.dir") + "/csv";

		String absoluteFilePath = "";
		absoluteFilePath = workingDirectory + File.separator + csvFileName;

		String[] header = { "cardId", "companyName", "companyNameKana", "departmentName", "positionName", "lastName",
				"firstName", "lastNameKana", "firstNameKana", "email", "zipCode", "addressFull", "address1", "address2",
				"address3", "telNumberCompany", "telNumberDepartment", "telNumberDirect", "faxNumber", "mobileNumber",
				"companyUrl", "subAddressFull", "subZipCode", "subAddress1", "subAddress2", "subAddress3",
				"subTelNumberCompany", "subTelNumberDepartment", "subTelNumberDirect", "subFaxNumber", "memo1", "memo2",
				"createDate", "updateDate" };

		if (typeCSV == CsvConstant.DOWNLOAD_DIRECT) {
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
			ICsvBeanWriter csvWriter = new CsvBeanWriter(new FileWriter(absoluteFilePath),
					CsvPreference.STANDARD_PREFERENCE);
			csvWriter.writeHeader(header);

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
			cardInfo.setImageFile(fileNameFromSCP);

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

			// Get user information
			UserInfo userInfo = userInfoService.getUserInfoByUserId(userId);
			if (userInfo.getSfManualLinkFlg() != 0) {
				modelAndView.addObject("sfManualLinkFlg", true);
			} else {
				modelAndView.addObject("sfManualLinkFlg", false);
			}

			// Get old cards
			// List<CardInfo> listOldCard = cardInfoService.getOldCardInfor();
			// modelAndView.addObject("listOldCard", listOldCard);

			// set search detail session
			if (session.getAttribute("searchDetail") != null) {
				UserSearchVO userSearch = (UserSearchVO) session.getAttribute("searchDetail");
				userSearch.setDetail(true);
				session.setAttribute("searchDetail", userSearch);
			}

			// Check compliance date
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date dateCompliance = sdf.parse(compliaceDate);

			if (cardInfo.getContactDate().before(dateCompliance)) {
				modelAndView.addObject("isExpried", true);
			} else {
				modelAndView.addObject("isExpried", false);
			}

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
			int rs = contactHistoryService.deleteContactHistory(contactHistory.getContactHistoryId());
			if (rs > 0) {
				contactHistoryList = contactHistoryService.getListContactHistoryById(contactHistory.getCardInfo().getCardId());
			}
		} catch (Exception ex) {
			logger.debug("Exception : " + ex.getMessage(), UserController.class);
		}
		return contactHistoryList;
	}

	@RequestMapping(value = "loginSaleForce", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String loginSaleForce(@RequestBody CardInfoSaleforce cardInfo) {
		logger.debug("loginSaleForce", UserController.class);

		final String URI = "https://bc-ribbon.temp-holdings.co.jp/api/";

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
			map.add("login_pass", cardInfo.getLogin_pass());

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

		ModelAndView modelAndView = new ModelAndView();
		try {
			if (userId != null) {
				UserInfo user = userInfoService.getUserInfoByUserId(userId);
				UserInfoVO userVO = new UserInfoVO();
				userVO.setCompanyName(
						groupCompanyInfoService.getCompanyById(user.getGroupCompanyId()).getGroupCompanyName());
				userVO.setDepartmentName(user.getDepartmentName());
				userVO.setName(user.getName());
				userVO.setPositionName(user.getPositionName());
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
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
		if (ecardUser != null) {
			UserInfo user = userInfoService.getUserInfoByUserId(ecardUser.getUserId());
			UserInfoVO userVO = new UserInfoVO();
			userVO.setCompanyName(
					groupCompanyInfoService.getCompanyById(user.getGroupCompanyId()).getGroupCompanyName());
			userVO.setDepartmentName(user.getDepartmentName());
			userVO.setName(user.getName());
			userVO.setPositionName(user.getPositionName());
			userVO.setEmail(user.getEmail());
			return new ModelAndView("profile", "user", userVO);
		}
		return new ModelAndView("redirect:home");
	}

	@RequestMapping("changepass")
	public ModelAndView changepass() {
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

	@RequestMapping(value = "editCardInfo", method = RequestMethod.POST)
	public ModelAndView editCardInfo(CardInfo cardInfo) {
		logger.debug("editCardInfo", UserController.class);

		ModelAndView modelAndView = new ModelAndView();
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
				modelAndView.addObject("isEdit", true);
			} else {
				modelAndView.addObject("isEdit", false);
			}
		} catch (Exception ex) {
			logger.debug("Exception : ", ex.getMessage());
		}
		modelAndView.setViewName("redirect:" + CommonConstants.REDIRECT_CARD_DETAIL + cardInfo.getCardId());
		return modelAndView;
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
		if (session.getAttribute("searchDetail") == null) {
			userSearchVO.setDetail(false);
			session.setAttribute("searchDetail", userSearchVO);
		}
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

		CardInfo cardInfoObject = cardInfoService.registerCardImageManualPCOfAdmin(cardInfo);

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

		return dataTableResponse;
	}

	@RequestMapping(value = "listConnectCards", method = RequestMethod.POST)
	@ResponseBody
	public List<OwnerCards> listConnectCards(@RequestBody final com.ecard.core.vo.CardInfo cardInfo,
			HttpSession session) {
		List<OwnerCards> ownerCards = new ArrayList<OwnerCards>();
		OwnerCards ownerCard = null;
		List<com.ecard.core.vo.CardInfo> cards = cardInfoService.getListConnectCards(cardInfo);
		if (!CollectionUtils.isEmpty(cards)) {
			List<Integer> userIds = cards.stream().map(x -> x.getCardOwnerId()).collect(Collectors.toList());

			List<UserInfoVo> users = userInfoService.getUserInArrUserId(userIds);

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
				;
				ownerCard.setPositionName(item.getPositionName());
				ownerCard.setTelNumberCompany(item.getTelNumberCompany());
				user = users.stream().filter(x -> x.getUserId() == item.getCardOwnerId()).findFirst().get();
				ownerCard.setOwner(
						StringUtilsHelper.mergerStringEitherAWord(user.getLastName(), user.getFirstName(), " "));
				ownerCard.setContactDateString(
						ownerCard.getContactDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString());
				ownerCards.add(ownerCard);

			}
		}

		return ownerCards;
	}

	@RequestMapping(value = "handleConnectCards", method = RequestMethod.POST)
	@ResponseBody
	public boolean handleConnectCards(@RequestParam(value = "cardid1") int cardid1,
			@RequestParam(value = "cardid2") int cardid2) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
		UserInfo user = userInfoService.getUserInfoByUserId(ecardUser.getUserId());
		return cardInfoService.handleConnectCards(cardid1, cardid2, ecardUser.getUserId(),
				StringUtilsHelper.mergerStringEitherAWord(user.getLastName(), user.getFirstName(), " "));
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
				UploadFileUtil.overrideImage(UploadFileUtil.encodeToString(image, "jpg"), scpHostName, scpUser,
						scpPassword, cardInfo.getImageFile());
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
			cardList = cardInfoService.searchCompanyTree(cardInfo.getCompanyName());
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
			cardList = cardInfoService.searchDepartment(cardInfo.getCompanyName());
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
			cardList = cardInfoService.searchCardInfo(cardInfo.getCompanyName(), cardInfo.getDepartmentName());
			if (cardList.size() > 0) {
				jsonObj = new Gson().toJson(cardList);
			}
		} catch (Exception ex) {
			logger.debug("Exception : " + ex.getMessage(), UserController.class);
		}

		return jsonObj;
	}
	
	@RequestMapping(value = "companyTree/list")
	public ModelAndView listCardByName(@RequestParam String name, HttpServletRequest request) {
		logger.debug("listCardByName", UserController.class);

		ModelAndView modelAndView = new ModelAndView();
		try{
			List<CardInfo> cardList = cardInfoService.searchCardInfoByName(name);
			
			String fileNameFromSCP = "";
			
			if(cardList.size() > 0){
				for(CardInfo cardInfo : cardList){
					fileNameFromSCP = UploadFileUtil.getImageFileFromSCP(cardInfo.getImageFile(), scpHostName, scpUser,
							scpPassword, Integer.parseInt(scpPort));
				}
				modelAndView.addObject("cardInfoList", cardList);
				modelAndView.addObject("imageFile", cardList);
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
    public List<com.ecard.core.vo.CardInfo> getListPossesionCardRecent(HttpServletRequest request) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
		List<com.ecard.core.vo.CardInfo> lstCardInfo = null;
		lstCardInfo = cardInfoService.getListPossesionCardRecent(ecardUser.getUserId());
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
    public List<CardInfoAndPosCard> listCardPending(HttpServletRequest request) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
		List<CardInfoAndPosCard> lstCardInfo = null;
		lstCardInfo = cardInfoService.listCardPending(ecardUser.getUserId());
	  return lstCardInfo;
	}

}
