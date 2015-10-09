package com.ecard.webapp.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.ecard.core.model.CardInfo;
import com.ecard.core.model.CardTagId;
import com.ecard.core.model.CompanyInfo;
import com.ecard.core.model.DownloadCsv;
import com.ecard.core.model.InquiryInfo;
import com.ecard.core.model.PossessionCardId;
import com.ecard.core.model.UserCardMemoId;
import com.ecard.core.model.UserInfo;
import com.ecard.core.model.UserNotification;
import com.ecard.core.model.UserSearch;
import com.ecard.core.model.UserTag;
import com.ecard.core.model.enums.SearchConditions;
import com.ecard.core.service.CardInfoService;
import com.ecard.core.service.CardMemoService;
import com.ecard.core.service.CardTagService;
import com.ecard.core.service.GroupCompanyInfoService;
import com.ecard.core.service.NotificationInfoService;
import com.ecard.core.service.PossessionCardService;
import com.ecard.core.service.SearchInfoService;
import com.ecard.core.service.SettingsInfoService;
import com.ecard.core.service.UserInfoService;
import com.ecard.core.service.UserTagService;
import com.ecard.core.service.converter.CardInfoConverter;
import com.ecard.core.vo.CardAndUserTag;
import com.ecard.core.vo.CardConnectModel;
import com.ecard.core.vo.CardInfoCSV;
import com.ecard.core.vo.CardInfoMemo;
import com.ecard.core.vo.CardInfoUserVo;
import com.ecard.core.vo.NotificationList;
import com.ecard.core.vo.SearchInfo;
import com.ecard.core.vo.TagForCard;
import com.ecard.core.vo.TagGroup;
import com.ecard.core.vo.UserDownloadPermission;
import com.ecard.core.vo.UserTagAndCardTag;
import com.ecard.webapp.constant.CommonConstants;
import com.ecard.webapp.security.EcardUser;
import com.ecard.webapp.util.UploadFileUtil;
import com.ecard.webapp.vo.CardInfoPCVo;
import com.ecard.webapp.vo.DataPagingJsonVO;
import com.ecard.webapp.vo.ListCardDelete;
import com.ecard.webapp.vo.ObjectCards;
import com.ecard.webapp.vo.ObjectListSearchUsers;
import com.ecard.webapp.vo.ObjectTeamVO;
import com.ecard.webapp.vo.UserInfoVO;
import com.ecard.webapp.vo.UserSearchVO;

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

	@Value("${scp.hostname}")
	private String scpHostName;

	@Value("${scp.user}")
	private String scpUser;

	@Value("${scp.password}")
	private String scpPassword;

	@Value("${scp.port}")
	private String scpPort;

	@Autowired
	SettingsInfoService settingsInfoService;

	@Autowired
    NotificationInfoService notificationInfoService;
	
	@Autowired
    SearchInfoService searchInfoService;
	
	@RequestMapping("home")
	public ModelAndView home(HttpServletRequest request) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
		Long listTotalCardInfo = new Long(0);
		List<CardInfoPCVo> lstCardInfoPCVo = new ArrayList<>();
		if (ecardUser != null) {
			List<String> lstNameSort = cardInfoService.getListSortType(ecardUser.getUserId());
			List<CardInfoUserVo> lstCardInfo = cardInfoService.getListPossesionCard(ecardUser.getUserId(), 0);
			listTotalCardInfo = cardInfoService.countPossessionCard(ecardUser.getUserId());

			for (String nameSort : lstNameSort) {
				List<CardInfo> cardInfoDisp = new ArrayList<>();
				for (CardInfoUserVo cardInfo : lstCardInfo) {
					if (nameSort.trim().equals(cardInfo.getSortType().trim())) {
						cardInfoDisp.add(cardInfo.getCardInfo());
					}
				}
				CardInfoPCVo cardInfoPCVo;
				try {
					if (cardInfoDisp.size() > 0) {
						cardInfoPCVo = new CardInfoPCVo(nameSort, CardInfoConverter.convertCardInforList(cardInfoDisp));
						lstCardInfoPCVo.add(cardInfoPCVo);
					}
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}

		}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("homePC");
		modelAndView.addObject("lstCardInfoPCVo", lstCardInfoPCVo);
		modelAndView.addObject("totalCardInfo", listTotalCardInfo);
		return modelAndView;

	}

	@RequestMapping(value = "search", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public DataPagingJsonVO<CardInfoPCVo> search(HttpServletRequest request, HttpSession session) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
		int limit = parseIntParameter(request.getParameter("page"), 0);
		int typeSort = parseIntParameter(request.getParameter("typeSort"), 0);
		DataPagingJsonVO<CardInfoPCVo> dataTableResponse = new DataPagingJsonVO<CardInfoPCVo>();
		List<CardInfoPCVo> cardInfoSearchResponses = new ArrayList<CardInfoPCVo>();
		List<String> lstNameSort = null;
		List<CardInfo> listCardSortNameCompany = null;
		List<CardInfoUserVo> lstCardInfo = null;
		if(typeSort == SearchConditions.CONTACT.getValue()){
		  lstNameSort = cardInfoService.getListSortType(ecardUser.getUserId());
		  lstCardInfo = cardInfoService.getListPossesionCard(ecardUser.getUserId(), limit);
		}else if (typeSort == SearchConditions.NAME.getValue()){
			listCardSortNameCompany = cardInfoService.getListPossesionCard(ecardUser.getUserId(),null, SearchConditions.NAME.name().toLowerCase(), limit);
			lstCardInfo = new ArrayList<>();
			lstNameSort = new ArrayList<>();
			 for(CardInfo cardInfoModel :listCardSortNameCompany ){
				 String sortType = cardInfoModel.getNameKana().substring(0,  1);
				 lstNameSort.add(sortType.toUpperCase());
				 CardInfoUserVo cardInfoUserVo = new CardInfoUserVo(sortType.toUpperCase(), cardInfoModel);
				 lstCardInfo.add(cardInfoUserVo);
			 }
		}else{
			listCardSortNameCompany = cardInfoService.getListPossesionCard(ecardUser.getUserId(),null, SearchConditions.COMPANY.name().toLowerCase(), limit);
			lstCardInfo = new ArrayList<>();
			lstNameSort = new ArrayList<>();
			 for(CardInfo cardInfoModel :listCardSortNameCompany ){
				 String sortType = cardInfoModel.getCompanyNameKana().substring(0,  1);
				 lstNameSort.add(sortType.toUpperCase());
				 CardInfoUserVo cardInfoUserVo = new CardInfoUserVo(sortType.toUpperCase(), cardInfoModel);
				 lstCardInfo.add(cardInfoUserVo);
			 }
		}
		lstNameSort = lstNameSort.stream().distinct().sorted().collect(Collectors.toList());
		for (String nameSort : lstNameSort) {
			List<CardInfo> cardInfoDisp = new ArrayList<>();
			for (CardInfoUserVo cardInfo : lstCardInfo) {
				if (nameSort.trim().equals(cardInfo.getSortType().trim())) {
					cardInfoDisp.add(cardInfo.getCardInfo());
				}
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
	public synchronized  String getFileImageSCP(@RequestParam(value = "fileImage") String fileImage) {
		String fileNameFromSCP = UploadFileUtil.getImageFileFromSCP(fileImage, scpHostName, scpUser, scpPassword,
				Integer.parseInt(scpPort));
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

	@RequestMapping(value = "/downloadCSV/{id:[\\d]+}", method = RequestMethod.GET)
	@ResponseBody
	public void downloadCSV(HttpServletResponse response, @PathVariable("id") int id) throws IOException {
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

	@RequestMapping(value = "/downloadFileCSV/{fileName}", method = RequestMethod.GET)
	@ResponseBody
	public void downloadFileCSV(HttpServletResponse response, @PathVariable("fileName") String fileName)
			throws IOException {
		try {
			fileName = fileName + ".csv";
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
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void createCSVFile(HttpServletResponse response, String fileName, List<CardInfoCSV> listUserInfoCSV,
			Integer typeCSV) throws IOException {
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
			ICsvBeanWriter csvWriter = new CsvBeanWriter(new FileWriter(absoluteFilePath),
					CsvPreference.STANDARD_PREFERENCE);
			csvWriter.writeHeader(header);

			for (CardInfoCSV aCard : listUserInfoCSV) {
				csvWriter.write(aCard, header);
			}
			csvWriter.close();
		}

	}
	
	@RequestMapping(value = "/card/detail/{id:[\\d]+}",  method = RequestMethod.GET)
	private int parseIntParameter(String parameter, int defaultValue) {
		try {
			return Integer.parseInt(parameter);
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}

	@RequestMapping(value = "/card/details/{id:[\\d]+}",  method = RequestMethod.GET)
	public ModelAndView detailPC(@PathVariable("id") int id) {
		logger.debug("detailPC", UserController.class);
		
		ModelAndView modelAndView = new ModelAndView();
		CardInfo cardInfo = null;
		List<CardConnectModel> cardList = null;
		try{
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
			Integer userId = ecardUser.getUserId();
			
			cardInfo = cardInfoService.getCardInfoDetail(id);
			String fileNameFromSCP = UploadFileUtil.getImageFileFromSCP(cardInfo.getImageFile(), scpHostName, scpUser, scpPassword, Integer.parseInt(scpPort));
			cardInfo.setImageFile(fileNameFromSCP);
			
			//List card connected
			cardList = cardInfoService.listCardConnect(cardInfo.getCardOwnerId(), cardInfo.getGroupCompanyId(), cardInfo.getName(), cardInfo.getCompanyName(), cardInfo.getEmail());
			
            List<CardInfoMemo> listCardMemo = getListCardMemo(cardInfo.getCardId());
            modelAndView.addObject("listCardMemo", listCardMemo);
            
            if(!cardInfo.getCardOwnerId().equals(userId)){
            	modelAndView.addObject("isMyCard", false);
            }
            else{
            	modelAndView.addObject("isMyCard", true);
            }
		}
		catch(Exception ex){
			logger.debug("Exception : ", ex.getMessage());
		}

		modelAndView.setViewName("detailPC");
		modelAndView.addObject("cardInfo", cardInfo);
		modelAndView.addObject("listCardConnect", cardList);
		
		return modelAndView;
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

	@RequestMapping("mailbox")
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
		UserNotification notify=new UserNotification();
		notify.setNoticeId(id);
		notify.setReadFlg(1);
		notificationInfoService.updateReadFlgById(notify);
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
		List<NotificationList> listUpdate = notificationInfoService.listAllNofiticationUser(ecardUser.getUserId());
		NotificationList item=null;
		try{
			item=listUpdate.stream().filter(x->x.getNotice_id()==id).findFirst().get();	
		}catch(Exception e){
			return new ModelAndView("redirect:../home");
		}
		
		return new ModelAndView("redirect:" + CommonConstants.REDIRECT_CARD_DETAIL + item.getCard_id());
	}

	@RequestMapping (value = "editCardInfo", method = RequestMethod.POST)
	public ModelAndView editCardInfo(CardInfo cardInfo) {
		logger.debug("editCardInfo", UserController.class);
		
		ModelAndView modelAndView = new ModelAndView();
		try{
			String name = cardInfo.getLastName();
            String nameKana = cardInfo.getLastNameKana();
            String addressFull = "";
                            
            if(cardInfo.getFirstName() != null){
                name += " " + cardInfo.getFirstName();
            }
            
            if(cardInfo.getFirstNameKana() != null){
                nameKana += " " + cardInfo.getFirstNameKana();
            }
            
            if(cardInfo.getAddress1() != null && cardInfo.getAddress2() != null 
                    && cardInfo.getAddress3() != null && cardInfo.getAddress4() != null){
                addressFull += addressFull = cardInfo.getAddress1() + " " + cardInfo.getAddress2() + " " + cardInfo.getAddress3() + " " + cardInfo.getAddress4();
            }
            else if(cardInfo.getAddress1() != null && cardInfo.getAddress2() != null && cardInfo.getAddress3() != null){
                addressFull += addressFull = cardInfo.getAddress1() + " " + cardInfo.getAddress2() + " " + cardInfo.getAddress3();
            }
            else if(cardInfo.getAddress1() != null && cardInfo.getAddress2() != null){
                addressFull += addressFull = cardInfo.getAddress1() + " " + cardInfo.getAddress2();
            }
            else if(cardInfo.getAddress1() != null){
                addressFull += addressFull = cardInfo.getAddress1();
            }
            
            if(cardInfo.getUpdateDate() == null){
                cardInfo.setUpdateDate(new Date());
            }
            
            if(cardInfo.getContactDate() == null){
                cardInfo.setContactDate(new Date());
            }
            cardInfo.setDeletDate(null);
            
            cardInfo.setName(name);
            cardInfo.setNameKana(nameKana);
            cardInfo.setAddressFull(addressFull);
          
            CompanyInfo companyInfo = new CompanyInfo();
            companyInfo.setCompanyId(0);
            
            cardInfo.setCompanyInfo(companyInfo);
            
            if(cardInfoService.editCardInfo(cardInfo)){
            	modelAndView.addObject("isEdit", true);
            }
            else{
            	modelAndView.addObject("isEdit", false);
            }
		}
		catch(Exception ex){
			logger.debug("Exception : ", ex.getMessage());
		}
		modelAndView.setViewName("redirect:" + CommonConstants.REDIRECT_CARD_DETAIL + cardInfo.getCardId());
		return modelAndView;
	}
	
	@RequestMapping (value = "editContactDate", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView editContactDate(String contactDate, HttpServletRequest request) {
		logger.debug("editContactDate", UserController.class);
		
		ModelAndView modelAndView = new ModelAndView();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(contactDate);
			
			CardInfo cardInfo = new CardInfo();
			cardInfo.setContactDate(date);
			cardInfo.setCardId(Integer.parseInt(request.getParameter("cardId")));
			
			if(cardInfoService.updateContactDate(cardInfo) > 0){
				modelAndView.addObject("isEdit", true);
			}
			else{
				modelAndView.addObject("isEdit", false);
			}
		}
		catch(Exception ex){
			logger.debug("Exception : ", ex.getMessage());
		}
		
		modelAndView.setViewName("redirect:" + CommonConstants.REDIRECT_CARD_DETAIL + request.getParameter("cardId"));
		return modelAndView;
	}
	
	@RequestMapping (value = "addTag", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<TagForCard> addTag(@RequestBody CardAndUserTag cardAndUserTag, HttpServletRequest request, HttpServletResponse response) {
		logger.debug("addTag", UserController.class);
		
		//ModelAndView modelAndView = new ModelAndView();
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
		Integer userId = ecardUser.getUserId();
		
		try{
			List<UserTagAndCardTag> listUserTagAndCardTag = userTagService.getListUserTagByUserId(userId);
	        Integer tagId = 0;
	        for(UserTagAndCardTag userTagTmp : listUserTagAndCardTag){
	            if(userTagTmp.getTagName().equals(cardAndUserTag.getTagName())){
	                tagId = userTagTmp.getTagId();
	                break;
	            }
	        }                
	        Integer isSame = 0;
	        if(tagId != 0){
	            List<Integer> cardId = cardTagService.listCardIdByTagId(tagId);                    
	            for(Integer cardIdTmp : cardId){
	                if(cardAndUserTag.getCardId() != null){
	                    if(cardAndUserTag.getCardId().equals(cardIdTmp)){
	                        isSame = 1;
	                        break;
	                    }
	                }
	            }
	            if(isSame != 1 && (cardAndUserTag.getCardId() != null)){
	                CardTagId cardTag = new CardTagId();
	                cardTag.setCardId(cardAndUserTag.getCardId());
	                cardTag.setTagId(tagId);
	                cardTagService.addCardTag(cardTag);
	            }                    
	        } else {                    
	            UserInfo userInfo = new UserInfo();
	            userInfo.setUserId(userId);
	
	            UserTag userTag =new UserTag();
	            userTag.setTagName(cardAndUserTag.getTagName());
	            userTag.setUserInfo(userInfo);
	            tagId = userTagService.registerUserTag(userTag);
	            
	            if (cardAndUserTag.getCardId() != null){
	                CardTagId cardTag = new CardTagId();
	                cardTag.setCardId(cardAndUserTag.getCardId());
	                cardTag.setTagId(tagId);
	                cardTagService.addCardTag(cardTag);
	            }
	        }
		}
		catch(Exception ex){
			logger.debug("Exception :"+ ex.getMessage(), UserController.class);
			response.setStatus(500, "Add tag error");
		}
		List<TagForCard> listTagForCard = getAllTagForCard(userId);
		return listTagForCard;
	}
	
	@RequestMapping (value = "addCardTag", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<TagGroup> addCardTag(@RequestBody CardTagId cardTag, HttpServletRequest request, HttpServletResponse response) {
		logger.debug("addCardTag", UserController.class);
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
		Integer userId = ecardUser.getUserId();
		try{
			cardTagService.addCardTag(cardTag);
		}
		catch(Exception ex){
			logger.debug("Exception : " + ex.getMessage(), UserController.class);
		}
		return getCardTag();
	}
	
	@RequestMapping (value = "addCardMemo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<CardInfoMemo> addCardMemo(@RequestBody UserCardMemoId userCardMemo, HttpServletRequest request, HttpServletResponse response) {
		logger.debug("addCardMemo", UserController.class);
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
		Integer userId = ecardUser.getUserId();
		try{
			userCardMemo.setCreateDate(new Date());
			userCardMemo.setUserId(userId);
			cardMemoService.createCardMemo(userCardMemo);
		}
		catch(Exception ex){
			logger.debug("Exception : " + ex.getMessage(), UserController.class);
		}
		List<CardInfoMemo> listCardMemo = getListCardMemo(userCardMemo.getCardId());
		return listCardMemo;
	}
	
	@RequestMapping (value = "deleteCardMemo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<CardInfoMemo> deleteCardMemo(@RequestBody UserCardMemoId userCardMemo, HttpServletRequest request, HttpServletResponse response) {
		logger.debug("deleteCardMemo", UserController.class);
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
		Integer userId = ecardUser.getUserId();
		try{
			userCardMemo.setCreateDate(new Date());
			userCardMemo.setUserId(userId);
			cardMemoService.deleteCardMemo(userCardMemo);
		}
		catch(Exception ex){
			logger.debug("Exception : " + ex.getMessage(), UserController.class);
		}
		List<CardInfoMemo> listCardMemo = getListCardMemo(userCardMemo.getCardId());
		return listCardMemo;
	}
	
	@RequestMapping (value = "deleteCardTag", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<TagGroup> deleteCardTag(@RequestBody TagForCard cardAndUserTag, HttpServletRequest request, HttpServletResponse response) {
		logger.debug("deleteCardTag", UserController.class);
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
		Integer userId = ecardUser.getUserId();
		try{
			cardTagService.deleteCardTag(cardAndUserTag.getCardId(), cardAndUserTag.getTagId());
		}
		catch(Exception ex){
			logger.debug("Exception : " + ex.getMessage(), UserController.class);
		}
		//List<TagForCard> listTagForCard = getAllTagForCard(userId);
		return getCardTag();
	}
	
	@RequestMapping (value = "deleteTag", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView deleteTag(CardTagId cardTag, HttpServletRequest request, HttpServletResponse response) {
		logger.debug("deleteTag", UserController.class);
		
		ModelAndView modelAndView = new ModelAndView();
		try{
			cardTagService.deleteCardTagByTagId(cardTag.getTagId());
			userTagService.deleteUserTag(cardTag.getTagId());
		}
		catch(Exception ex){
			logger.debug("Exception : " + ex.getMessage(), UserController.class);
		}
		response.setStatus(200, "Remove tag success");
		modelAndView.setViewName("redirect:" + CommonConstants.REDIRECT_CARD_DETAIL + cardTag.getCardId());
		return modelAndView;
	}
	
	@RequestMapping (value = "delBusinessCard", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView delBusinessCard(PossessionCardId card, HttpServletRequest request, HttpServletResponse response) {
		logger.debug("delBusinessCard", UserController.class);
		
		ModelAndView modelAndView = new ModelAndView();
		try{
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
			Integer userId = ecardUser.getUserId();
			
			possessionCardService.deletePossessionCard(userId, card.getCardId());
		}
		catch(Exception ex){
			logger.debug("Exception : " + ex.getMessage(), UserController.class);
		}
		response.setStatus(200, "Remove card success");
		modelAndView.setViewName("redirect:" + CommonConstants.REDIRECT_CARD_DETAIL + card.getCardId());
		return modelAndView;
	}
	
	private List<TagForCard> getAllTagForCard(Integer userId){
		List<TagForCard> listCardTag = null;
		try{
			listCardTag = cardTagService.listCardTagByCardId(userId);
		}
		catch(Exception ex){
			logger.debug("Exception : " + ex.getMessage(), UserController.class);
		}
		return listCardTag;
	}
	
	private List<CardInfoMemo> getListCardMemo(Integer cardId){
		List<CardInfoMemo> listCardMemo = null;
		try{
			listCardMemo = cardMemoService.getMemoCard(cardId);
		}
		catch(Exception ex){
			logger.debug("Exception : " + ex.getMessage(), UserController.class);
		}
		return listCardMemo;
	}

	@RequestMapping(value = "addUserSearch", method = RequestMethod.POST)
	@ResponseBody
	public boolean addUserSearch(@RequestBody final  UserSearchVO userSearchVO) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
		UserSearch us=new UserSearch();
		UserInfo u=new UserInfo();
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
        for (SearchInfo s : listSearchInfo){
            seqList.add(s.getSeq());
        }
        int seq = getSequeceFromList(seqList);
        if(seq >= 6){                
            return false;
        } else {
            us.setSeq(seq);
        }      
        try{
            if(searchInfoService.registerSearchText(us)==0)
                searchInfoService.createSearchText(us);
        }catch(Exception e){
        	e.printStackTrace();
        	return false;
        }
		
		return true;
	}
	
	private int getSequeceFromList(List<Integer> myCoord){
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
        int seq = myCoord.size()+1;
        for (Integer s : matches){            
            if(s.equals(0)){
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
		ObjectListSearchUsers obj=new ObjectListSearchUsers();
		obj.setUserSearchs(listSearchInfo);
		obj.setHasData(listSearchInfo.size()>0?true:false);
		return obj;
	}
	
	private List<TagGroup> convertCardTagIdList(List<TagForCard> cardTagModelList){
		List<TagGroup> tagGroups = new ArrayList<TagGroup>();
        TagForCard cardTagId;
        
        if (cardTagModelList.size() != 0){            
            tagGroups= cardTagModelList.stream().collect(Collectors.groupingBy(g->g.getTagId())).entrySet().stream().map(t->{
               TagGroup tg = new TagGroup();
               List<Integer> cardIds = new ArrayList<Integer>();
               
               tg.setTagId(t.getKey());
               tg.setUserId(t.getValue().stream().map(x->x.getUserId()).findFirst().get());
               if(t.getValue().get(0).getCardId() != null){
                   tg.setListCardIds(t.getValue().stream().map(x->x.getCardId()).collect(Collectors.toList()));
               } 
               else{
                   tg.setListCardIds(cardIds);
               }
               tg.setTagName(t.getValue().stream().map(x->x.getTagName()).findFirst().get());               
            return tg;  
           }).collect(Collectors.toList());           
        }
        
        return tagGroups;
	}
	
	@RequestMapping (value = "listCardTag", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<TagGroup> listCardTag(){
		return getCardTag();
	}
	
	private List<TagGroup> getCardTag(){
		List<TagGroup> listTagGroup = null;
		try{
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
			Integer userId = ecardUser.getUserId();
			
			//List tag
			List<TagForCard> listCardTag = getAllTagForCard(userId);
			List<TagForCard> listUserTag = cardTagService.listUserTagByUserId(userId);
            if(listCardTag.size() == 0){
            	listTagGroup = convertCardTagIdList(listUserTag);
            }
            else{
            	listTagGroup = convertCardTagIdList(listCardTag);
            }
		}
		catch(Exception ex){
			logger.debug("Exception : ", ex.getMessage());
		}
		return listTagGroup;
	}
	
	@RequestMapping(value = "deleteUserSearch", method = RequestMethod.POST)
	@ResponseBody
	public ObjectListSearchUsers deleteUserSearch(@RequestParam(value = "id") String id) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
		List<Integer> liSeq=new ArrayList<Integer>();
		liSeq.add(Integer.parseInt(id));
		try{
			searchInfoService.deleteSearchText(ecardUser.getUserId(), liSeq);	
		}catch(Exception e){
			e.printStackTrace();
			ObjectListSearchUsers obj=new ObjectListSearchUsers();
			obj.setHasData(false);
			return obj;
		}
		
		List<SearchInfo> listSearchInfo = searchInfoService.listSearchText(ecardUser.getUserId());
		ObjectListSearchUsers obj=new ObjectListSearchUsers();
		obj.setUserSearchs(listSearchInfo);
		obj.setHasData(listSearchInfo.size()>0?true:false);
		return obj;
	}
	@RequestMapping(value = "deleteListCard", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public int deleteListCard(@RequestBody final  ListCardDelete listCardDelete){
		System.out.println("I am here");	
		List<Integer> listCard = new ArrayList<>();
		int result = 0;
		try{
			for(ObjectCards cardId : listCardDelete.getListCardId()){
				System.out.println("BBBB = "+cardId.getCardId());
				listCard.add(Integer.parseInt(cardId.getCardId()));				
			}
			result = cardInfoService.deleteListCard(listCard);
		} catch(Exception e){
			e.printStackTrace();
			return 0;
		}
		
		return result;
	}
}


