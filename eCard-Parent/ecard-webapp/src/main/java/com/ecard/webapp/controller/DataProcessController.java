package com.ecard.webapp.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseDate;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.thymeleaf.context.Context;

import com.ecard.core.model.CardInfo;
import com.ecard.core.model.CardTag;
import com.ecard.core.model.CompanyInfo;
import com.ecard.core.model.GroupCompanyInfo;
import com.ecard.core.model.PossessionCard;
import com.ecard.core.model.PossessionCardId;
import com.ecard.core.model.Roles;
import com.ecard.core.model.UserInfo;
import com.ecard.core.model.UserMigration;
import com.ecard.core.model.UserMigrationId;
import com.ecard.core.model.UserTag;
import com.ecard.core.service.CardInfoService;
import com.ecard.core.service.CardTagService;
import com.ecard.core.service.DataIndexService;
import com.ecard.core.service.EmailService;
import com.ecard.core.service.GroupCompanyInfoService;
import com.ecard.core.service.ImportCsvDataService;
import com.ecard.core.service.MasterService;
import com.ecard.core.service.PossessionCardService;
import com.ecard.core.service.UserInfoService;
import com.ecard.core.service.UserTagService;
import com.ecard.webapp.constant.CommonConstants;
import com.ecard.webapp.security.EcardUser;
import com.ecard.webapp.security.RoleType;
import com.ecard.webapp.util.CsvFileReader;
import com.ecard.webapp.util.StringUtilsHelper;
import com.ecard.webapp.util.UploadFileUtil;
import com.ecard.webapp.vo.CardInfoCsvVo;
import com.ecard.webapp.vo.OperatorInfoFromCSV;
import com.ecard.webapp.vo.UserInfoFromCSV;

@Controller
@RequestMapping("/data/*")
public class DataProcessController {
    private static final Logger logger = LoggerFactory.getLogger(DataProcessController.class);
    private String[] values = new String[] {"application/vnd.ms-excel","application/excel","application/vnd.ms-excel","application/csv", "application/vnd.msexcel" , "application/force-download",
			"application/octet-stream","application/txt",
			"text/csv","text/comma-separated-values", "text/csv", "text/plain","text/anytext"};
    
    @Autowired
    ImportCsvDataService importCsvDataService;
	
    @Autowired
    GroupCompanyInfoService groupCompnayInfoService;

    @Autowired
    UserInfoService userInfoService;
    
    @Autowired
    CardInfoService cardInfoService;
    
    @Autowired
    CardTagService cardTagService;  
    
    @Autowired
    UserTagService userTagService;

    @Autowired
    PossessionCardService possessionCardService;
    
    @Autowired
    MasterService masterService;
    
    @Autowired
    EmailService emailService;
    
    @Value("${scp.hostname}")
    private String scpHostName;
	    
    @Value("${scp.user}")
    private String scpUser;
	    
    @Value("${scp.password}")
    private String scpPassword;

    @Value("${scp.port}")
    private String scpPort;
    
    @Value("${csv.import.success}")
    private String csvImportSuccess;
    
    @Value("${card.default.base64}")
    private String defaultImage64;
    
    @Autowired
    DataIndexService dataIndexService;

    @RequestMapping("importUserByCSV") 
    public ModelAndView importUserByCSV(HttpServletRequest request) {
		List<GroupCompanyInfo> listGroupCompany = groupCompnayInfoService.getListCompany();		
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("importUserByCSV");
    	modelAndView.addObject("listGroupCompany", listGroupCompany);
    	return modelAndView;
    }
    
    @RequestMapping("importOperatorByCSV") 
    public ModelAndView importOperatorByCSV(HttpServletRequest request) {
		List<GroupCompanyInfo> listGroupCompany = groupCompnayInfoService.getListCompany();		
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("importOperatorByCSV");
    	modelAndView.addObject("listGroupCompany", listGroupCompany);
    	return modelAndView;
    }
	
    @RequestMapping(value = "/uploadUserCSV", method = RequestMethod.POST)
    public  ModelAndView uploadUserCSV( @RequestParam("file") MultipartFile file, HttpServletRequest request) {		
        String[] HEADER_FOR_USER = { "sansanId", "department", "userName", "email", "email2",
                    "useDate", "language", "formSendMail", "idConnectAD", "roleId", "roleAdminId", "data",
                    "groupDataDlRequestFlg", "allDataDlRequestFlg", "mailSystem", "sfManualLinkFlg",
                    "ownerOtherUser", "reserve1", "reserve2", "reserve3", "useStopFlg", "connectZapier",
                    "position" };
        

		if(!Arrays.asList(values).contains(file.getContentType())){
			return new ModelAndView("redirect:importCardCSV", "error", "formatCSV");
		}
		if (file.isEmpty()){
			return new ModelAndView("redirect:importCardCSV", "error", "Error");
		}
		
		Integer groupCompanyId = Integer.parseInt(request.getParameter("groupCompanyId").toString());
		GroupCompanyInfo groupCompanyInfo = groupCompnayInfoService.getCompanyById(groupCompanyId);
		int recordCnt = 0;

		try {
			UserInfoFromCSV userInfoFromCSV = new UserInfoFromCSV();
			List<UserInfoFromCSV> listUserInfoCSV;
			try {
				final CellProcessor[] processors = getProcessorsUser();
				InputStreamReader inputStreamReader = new InputStreamReader(file.getInputStream());
				
				listUserInfoCSV = (List<UserInfoFromCSV>) (Object) CsvFileReader.readCsvMapHeader(inputStreamReader, userInfoFromCSV, processors, HEADER_FOR_USER);
			} catch (Exception e) {
				e.printStackTrace();
				return new ModelAndView("redirect:importCardCSV", "error", "validationData");
			}
			List<UserInfo> userInfoList = new ArrayList<UserInfo>();
			List<String> sansanIdList = new ArrayList<String>();
			for (UserInfoFromCSV currentUser : listUserInfoCSV) {
				recordCnt++;
				if (currentUser.getUserName() == null) {
					logger.debug("uploadCardCsv err=emptyName line=" + recordCnt);
					continue;
				}
				if (currentUser.getEmail() != null && userInfoService.checkEmailExist(currentUser.getEmail())) {
					logger.debug("uploadCardCsv err=checkEmailExist line=" + recordCnt);
					continue;
				}

				boolean isUserMigration = userInfoService.checkUserMigration(currentUser.getSansanId());
				if (isUserMigration){
					continue;
				}
				UserInfo userInfo = new UserInfo();
				userInfo.setDepartmentName(currentUser.getDepartment());
				userInfo.setName(currentUser.getUserName());

				List<String> listAddress = new ArrayList<String>(
						Arrays.asList(currentUser.getUserName().trim().split(" ")));
				userInfo.setLastName(listAddress.get(0));
				if (listAddress.size() > 1) {
					userInfo.setFirstName(listAddress.get(1));
				}

				userInfo.setEmail(currentUser.getEmail());
				userInfo.setUseDate(new SimpleDateFormat("yyyy-MM-dd").parse(CommonConstants.DEFAULT_USEDATE));// userInfo.setUseDate(listUser.getUseDate());

				// userInfo.setGroupDataDlRequestFlg(checkFlg(listUser.getGroupDataDlRequestFlg()));
				// userInfo.setAllDataDlRequestFlg(checkFlg(listUser.getAllDataDlRequestFlg()));
				// userInfo.setSfManualLinkFlg(checkFlg(listUser.getSfManualLinkFlg()));
				userInfo.setGroupDataDlRequestFlg(0);
				userInfo.setAllDataDlRequestFlg(0);
				userInfo.setSfManualLinkFlg(checkFlg(currentUser.getSfManualLinkFlg()));

				// userInfo.setUseStopFlg(listUser.getUseStopFlg());
				userInfo.setUseStopFlg(1);
				userInfo.setGroupCompanyId(groupCompanyId);
				userInfo.setCompanyName(groupCompanyInfo.getGroupCompanyName());
				userInfo.setCompanyNameKana(groupCompanyInfo.getGroupCompanyNameKana());
				userInfo.setPositionName(currentUser.getPosition());
				String password = new BigInteger(130, new SecureRandom()).toString(32);
				userInfo.setPassword(new BCryptPasswordEncoder().encode(password));
				userInfo.setEndDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(CommonConstants.MAX_TIME_STAMP));

				Roles role = new Roles();
				role.setRoleId(getRoleId(currentUser.getRoleId()));
				userInfo.setRoles(role);
				userInfo.setRoleAdminId(getRoleAdminId(currentUser.getRoleAdminId()));
				userInfo.setMailSendFlg(1);
				userInfo.setMailNewsFlg(1);
				userInfo.setMailNoticeFlg(1);
				userInfo.setMailUseAssistFlg(1);
				userInfo.setFirstLoginF(0);
				userInfo.setLeaveFlg(0);
				userInfo.setCreateDate(new Date());
				userInfo.setUpdateDate(new Date());
				userInfo.setOperaterId(0);
				userInfo.setLastChangePasswordDate(new Date());
				userInfo.setDeleteFlg(0);
				
				userInfoList.add(userInfo);
				sansanIdList.add(currentUser.getSansanId());//add SanSanId of currentUser at current Index
				
			}
			if(CollectionUtils.isNotEmpty(userInfoList)){
				importCsvDataService.importListUserInfo(userInfoList, sansanIdList);
			}
			return new ModelAndView("redirect:/operators/list");
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("uploadUserCSV ErrType=ErrorSystem line=" + e.getStackTrace()[0].getLineNumber());
			return new ModelAndView("redirect:importCardCSV", "error", "ErrorSystem");
		}
    }

	@RequestMapping(value = "/uploadOperatorCSV", method = RequestMethod.POST)
    public  ModelAndView uploadOperatorCSV( @RequestParam("file") MultipartFile file, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		List<GroupCompanyInfo> listGroupCompany = groupCompnayInfoService.getListCompany();
		List<UserInfo> listUserInfo = userInfoService.getAllUserInfo();
		int recordCnt = 0;
		int recordEmpty = 0;
		int recordSuccess = 0;
		int recordError = 0;
		
		if(!Arrays.asList(values).contains(file.getContentType())){
			return new ModelAndView("redirect:importOperatorByCSV", "error", "formatCSV");
		}
		if (file.isEmpty()){
			return new ModelAndView("redirect:importOperatorByCSV", "error", "Error");
		}
				
		Integer groupCompanyId = Integer.parseInt(request.getParameter("groupCompanyId").toString());
		GroupCompanyInfo groupCompanyInfo =  groupCompnayInfoService.getCompanyById(groupCompanyId);
		try {
			InputStreamReader inputStreamReader = new InputStreamReader(file.getInputStream());
			OperatorInfoFromCSV operatorInfoFromCSV = new OperatorInfoFromCSV();
			List<UserInfo> userInfoList = new ArrayList<UserInfo>();
			List<OperatorInfoFromCSV> listUserInfoCSV;
			try {
				final CellProcessor[] processors = getProcessorsOperator();
//				final String[] headerUser = new String[] {"email", "department", "position", "lastName", "firstName", "lastNameKana","firstNameKana","useDate", "groupDataDlFlg", "allDataDlFlg",
//						"groupDataDlRequestFlg", "allDataDlRequestFlg", "helpdeskFlg", "sfManualLinkFlg",
//						"adminFlg", "supervisor", "leader", "sansanId" };
				final String[] headerUser = new String[] {"email", "department", "position", "lastName", "firstName", "lastNameKana", "firstNameKana", "useDate", "endDate", 
						"roleID", "roleAdminID", "salesforce" };

				listUserInfoCSV = (List<OperatorInfoFromCSV>) (Object) CsvFileReader.readCsvMapHeader(inputStreamReader, operatorInfoFromCSV, processors, headerUser);
			} catch (Exception e) {
				e.printStackTrace();
				return new ModelAndView("redirect:importOperatorByCSV", "error", "insertData");
			}

			for (OperatorInfoFromCSV listUser : listUserInfoCSV) {
				recordCnt++;
				
				if (listUser.getEmail() == null || userInfoService.checkEmailExist(listUser.getEmail())) {
					recordEmpty++;
					System.out.println("uploadCardCsv err=checkEmailExist line=" + recordCnt);
					continue;
				}

				UserInfo userInfo = new UserInfo();
				userInfo.setEmail(listUser.getEmail());				
				userInfo.setDepartmentName(listUser.getDepartment());
				userInfo.setPositionName(listUser.getPosition());
				userInfo.setLastName(listUser.getLastName());
				userInfo.setFirstName(listUser.getFirstName());
				userInfo.setLastNameKana(listUser.getLastNameKana());
				userInfo.setFirstNameKana(listUser.getFirstNameKana());
								
				try {
					userInfo.setUseDate(new SimpleDateFormat("yyyy/MM/dd").parse(listUser.getUseDate()));
				} catch (ParseException ex) {
					ex.printStackTrace();
					recordError++;						
					continue;
				}
				
				if (StringUtils.isNotBlank(listUser.getEndDate())){
					try {
						userInfo.setEndDate(new SimpleDateFormat("yyyy/MM/dd").parse(listUser.getEndDate()));
					} catch (ParseException ex) {
						ex.printStackTrace();
						recordError++;						
						continue;
					}
				} else {
					userInfo.setEndDate(new SimpleDateFormat("yyyy/MM/dd").parse("2030/12/31"));
				}
				
				
				
				String password = new BigInteger(130, new SecureRandom()).toString(32);
				userInfo.setPassword(new BCryptPasswordEncoder().encode(password));
				// Set roleID and roleAdminID
				
				Roles role = new Roles();
				role.setRoleId(getRoleId(listUser.getRoleID()));
				userInfo.setRoles(role);
				userInfo.setRoleAdminId(getRoleAdminId(listUser.getRoleAdminID()));
				
				// End set roleID
				userInfo.setSfManualLinkFlg(checkFlg(listUser.getSalesforce()));
				
				userInfo.setName(listUser.getLastName()+ ' '+ listUser.getFirstName());
				userInfo.setNameKana(listUser.getLastNameKana()+ ' '+listUser.getLastNameKana());
				
				userInfo.setGroupCompanyId(groupCompanyId);				
				userInfo.setCompanyName(groupCompanyInfo.getGroupCompanyName());
				userInfo.setCompanyNameKana(groupCompanyInfo.getGroupCompanyNameKana());
				
				userInfo.setMailSendFlg(1);
				userInfo.setMailNewsFlg(1);
				userInfo.setMailNoticeFlg(1);
				userInfo.setMailUseAssistFlg(1);
				userInfo.setFirstLoginF(0);
				userInfo.setLeaveFlg(0);
				userInfo.setUseStopFlg(0);
				userInfo.setCreateDate(new Date());
				userInfo.setUpdateDate(new Date());
				userInfo.setOperaterId(0);
				userInfo.setLastChangePasswordDate(new Date());
				userInfo.setDeleteFlg(0);
				userInfoList.add(userInfo);
			}
			if(CollectionUtils.isNotEmpty(userInfoList)){				
    			List<UserInfo> subUserInfoList = importCsvDataService.importListOperatorInfo(userInfoList);
    			recordSuccess = subUserInfoList.size();
                recordError += recordCnt - recordSuccess - recordEmpty;
             // send mail function here
    			sendMailResgisterOperator(subUserInfoList);
			}
			
			modelAndView.addObject("recordSuccess", recordSuccess);
			modelAndView.addObject("recordError", recordError);
			modelAndView.addObject("recordEmpty", recordEmpty);
			modelAndView.addObject("msgImportSuccess", this.csvImportSuccess);
			modelAndView.setViewName("importOperatorByCSV");
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("redirect:importOperatorByCSV", "error", "ErrorSystem");
		}
		modelAndView.addObject("listUserInfo", listUserInfo);
		modelAndView.addObject("listGroupCompany", listGroupCompany);
		return modelAndView;
    }
	
    @RequestMapping("importCardCSV") 
    public ModelAndView importCSV(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			List<GroupCompanyInfo> listGroupCompany = groupCompnayInfoService.getListCompany();
			List<UserInfo> listUserInfo = userInfoService.getAllUserInfo();
			modelAndView.addObject("listUserInfo", listUserInfo);
			modelAndView.addObject("listGroupCompany", listGroupCompany);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		modelAndView.setViewName("viewImportCSV");
		return modelAndView;
    }
    
    public static <T> Predicate<T> distinctByKey(Function<? super T,Object> keyExtractor) {
        Map<Object,Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
    
    @RequestMapping(value = "/uploadCardCsv", method = RequestMethod.POST)
    public ModelAndView uploadCardCsv(@RequestParam("file") MultipartFile file, HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		int recordEmpty = 0;
		int recordSuccess = 0;
		int recordError = 0;
		//int recordCnt = 0;
		//String errorLineNo = "";
		
		/*if (!file.getContentType().equals("application/vnd.ms-excel") && !file.getContentType().equals("application/excel") && !file.getContentType().equals("application/vnd.ms-excel") 
				&& !file.getContentType().equals("application/vnd.msexcel") && !file.getContentType().equals("application/force-download")  && !file.getContentType().equals("application/csv") 
				&& !file.getContentType().equals("text/csv") && !file.getContentType().equals("text/comma-separated-values") && !file.getContentType().equals("text/anytext") ) {*/
		if(!Arrays.asList(values).contains(file.getContentType())){
			return new ModelAndView("redirect:importCardCSV", "error", "formatCSV");
		}
		if (file.isEmpty()){
			return new ModelAndView("redirect:importCardCSV", "error", "Error");
		}

		//Integer groupCompanyId = Integer.parseInt(request.getParameter("hGroupCompanyId").toString());
		Integer userIdDefault = Integer.parseInt(request.getParameter("huserId").toString());
		List<GroupCompanyInfo> listGroupCompany = groupCompnayInfoService.getListCompany();
		List<UserInfo> listUserInfo = userInfoService.getAllUserInfo();

		try {
			EcardUser ecardUser = (EcardUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			InputStreamReader inputStreamReader = new InputStreamReader(file.getInputStream (), "UTF-8");

			CardInfoCsvVo cardInfoCsvVo = new CardInfoCsvVo();
			List<CardInfoCsvVo> listCardInfoCsv;

			try {
				final CellProcessor[] processors = getProcessorsCardInfo();
				/*String[] headerColumns = new String[] { "companyName", "companyNameEnglish", "companyNameKana",
						"departmentName", "positionName", "name", "lastName", "firstName", "nameKana", "lastNameKana",
						"firstNameKana", "zipCode", "addressFull", "address1", "address2", "address3", "address4",
						"telNumberCompany", "telNumberDepartment", "faxNumber", "mobileNumber", "email", "companyUrl",
						"subAddressFull", "subZipCode", "subAddress1", "subAddress2", "subAddress3", "subAddress4",
						"subTelNumberCompany", "subTelNumberDepartment", "subFaxNumber", "subMobileNumber", "subEmail",
						"subCompanyUrl", "importanceLevel", "VP", "CP", "fileOutputFlg", "userTag", "createDate",
						"posContactDate", "address", "tel", "fax", "e_mail", "memo1", "autoMemo", "cardOwnerId",
						"cardOwnerName", "contact_date", "contact_type", "title", "place", "contact_memo", "company_id",
						"userIndexNo", "cardIndexNo", "blank" };*/

				String[] headerColumns = new String[] { "companyName", "companyNameEnglish", "companyNameKana",
						"departmentName", "positionName", "name", "lastName", "firstName", "nameKana", "lastNameKana",
						"firstNameKana", "zipCode", "addressFull", "address1", "address2", "address3", "address4",
						"telNumberCompany", "telNumberDepartment", "faxNumber", "mobileNumber", "email", "companyUrl",
						"subAddressFull", "subZipCode", "subAddress1", "subAddress2", "subAddress3", "subAddress4",
						"subTelNumberCompany", "subTelNumberDepartment", "subFaxNumber", "subMobileNumber", "subEmail",
						"subCompanyUrl", "importanceLevel", "VP", "CP", "fileOutputFlg", "userTag", "createDate",
						"contact_date", "address", "tel", "fax", "e_mail", "memo1", "autoMemo", "cardOwnerId",
						"cardOwnerName", "memo2", "contact_type", "title", "place", "contact_memo", "company_id",
						"userIndexNo", "cardIndexNo", "blank" };
				
				listCardInfoCsv = (List<CardInfoCsvVo>) (Object) CsvFileReader.readCsvMapHeader(inputStreamReader, cardInfoCsvVo, processors, headerColumns);
			} catch (Exception e) {
				e.printStackTrace();
				return new ModelAndView("redirect:importCardCSV", "error", "validationData");
			}
			
			List<CardInfo> cardModelList = null;
			if (CollectionUtils.isNotEmpty(listCardInfoCsv)) {
				
				UserInfo userInfo;
				CardInfo cardModel;
				int userId = 0;
				
				cardModelList = new ArrayList<CardInfo>();
				for (CardInfoCsvVo cardInfo : listCardInfoCsv) {
					//recordCnt++;

					if (cardInfo.getName() == null) {
						recordEmpty++;
						//errorLineNo += "<br>" +" [Line : " + recordCnt + "," + "Name is empty" + "]";
						continue;
					}
					
					userId = 0;
					// SansanId
					if (cardInfo.getCardOwnerId() != null){
						userId = userInfoService.getUserIdByUserIndexNo(cardInfo.getCardOwnerId());
					}
					if (userId == 0){
						userId = userIdDefault;
					}
					
					final int userFinalId = userId;
					userInfo = listUserInfo.stream().filter(u -> u.getUserId().equals(userFinalId)).findFirst().get();
					if (userInfo == null){
						recordError++;
						//errorLineNo += "<br>"+ " [Line : " + recordCnt + "," + "User not found" + "]";
						continue;
					}
					
					// card information data
					cardModel = new CardInfo();
					
					Date createDate = new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(cardInfo.getCreateDate());
					Date complianceDate = new SimpleDateFormat("yyyy/MM/dd").parse(CommonConstants.COMPLIANCE_DATE);
					if (createDate.getTime() >= complianceDate.getTime()) {
						Calendar c = Calendar.getInstance();
						c.setTime(createDate);
						c.add(Calendar.DATE, -1);
						createDate.setTime(c.getTime().getTime());
					}
					cardModel.setCreateDate(createDate);
					/*
					if (StringUtils.isNotBlank(cardInfo.getCreateDate())){
						try {
							Date createDate = new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(cardInfo.getCreateDate());
							Date complianceDate = new SimpleDateFormat("yyyy/MM/dd").parse(CommonConstants.COMPLIANCE_DATE);
							if (createDate.getTime() >= complianceDate.getTime()) {
								Calendar c = Calendar.getInstance();
								c.setTime(createDate);
								c.add(Calendar.DATE, -1);
								createDate.setTime(c.getTime().getTime());
							}
							cardModel.setCreateDate(createDate);
						} catch (ParseException ex) {
							ex.printStackTrace();
							recordError++;
							errorLineNo += "<br>" + "[Line : " + recordCnt + "," + "createDate" + "]";
							continue;
						}
					}*/
					
					if (StringUtils.isNotBlank(cardInfo.getContact_date())){
						try {
//							MEISHI-826
							cardModel.setContactDate(new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(cardInfo.getContact_date()));
//							cardModel.setContactDate(new SimpleDateFormat("yyyy/MM/dd").parse(CommonConstants.COMPLIANCE_DATE));
						} catch (ParseException ex) {
							ex.printStackTrace();
							recordError++;
							//errorLineNo += "<br>" + "[Line : " + recordCnt + "," + "contactDate" + "]";
							continue;
						}
					} else {
						cardModel.setContactDate(cardModel.getCreateDate());
					}
					// cardModel.setImageFile(CsvConstant.IMAGE_DEFAUT);
					cardModel.setCompanyName(cardInfo.getCompanyName());// companyName,
					cardModel.setCompanyNameKana(cardInfo.getCompanyNameKana());// companyNameKana,
					cardModel.setDepartmentName(cardInfo.getDepartmentName());// departmentName,
					cardModel.setPositionName(cardInfo.getPositionName());// positionName,
					cardModel.setName(cardInfo.getName());// name,
					cardModel.setLastName(cardInfo.getLastName());// lastName,
					cardModel.setFirstName(cardInfo.getFirstName());// firstName,
					cardModel.setNameKana(cardInfo.getNameKana());// nameKana,
					cardModel.setLastNameKana(cardInfo.getLastNameKana());// lastNameKana,
					cardModel.setFirstNameKana(cardInfo.getFirstNameKana());// firstNameKana,
					// Split positive value
					if (cardInfo.getZipCode() != null) {
						cardModel.setZipCode(cardInfo.getZipCode().trim().replace("-", "")); // zipCode,
					}
					cardModel.setAddressFull(cardInfo.getAddressFull());// addressFull,
					cardModel.setAddress1(cardInfo.getAddress1());// address1,
					cardModel.setAddress2(cardInfo.getAddress2());// address2,
					cardModel.setAddress3(cardInfo.getAddress3());// address3,
					cardModel.setAddress4(cardInfo.getAddress4());// address4,
					cardModel.setTelNumberCompany(cardInfo.getTelNumberCompany());// telNumberCompany,
					cardModel.setTelNumberDepartment(cardInfo.getTelNumberDepartment());// telNumberDepartment,
					cardModel.setFaxNumber(cardInfo.getFaxNumber());// faxNumber,
					cardModel.setMobileNumber(cardInfo.getMobileNumber());// mobileNumber,
					cardModel.setEmail(cardInfo.getEmail());// email,
					cardModel.setCompanyUrl(cardInfo.getCompanyUrl());// companyUrl,
					cardModel.setSubAddressFull(cardInfo.getSubAddressFull());// subAddressFull,
					cardModel.setSubZipCode(cardInfo.getSubZipCode());// subZipCode,
					cardModel.setSubAddress1(cardInfo.getSubAddress1());// subAddress1,
					cardModel.setSubAddress1(cardInfo.getSubAddress2());// subAddress2,
					cardModel.setSubAddress1(cardInfo.getSubAddress3());// subAddress3,
					cardModel.setSubAddress1(cardInfo.getSubAddress4());// subAddress4,
					cardModel.setSubTelNumberCompany(cardInfo.getSubTelNumberCompany());// subTelNumberCompany,
					cardModel.setTelNumberDepartment(cardInfo.getTelNumberDepartment());// subTelNumberDepartment,
					cardModel.setSubFaxNumber(cardInfo.getSubFaxNumber());// subFaxNumber,
					cardModel.setSubMobileNumber(cardInfo.getSubMobileNumber());
					cardModel.setSubEmail(cardInfo.getSubEmail());
					cardModel.setSubCompanyUrl(cardInfo.getSubCompanyUrl());
					cardModel.setImportanceLevel("");
					cardModel.setGroupCompanyId(userInfo.getGroupCompanyId()); // groupCompany
					cardModel.setNewestCardFlg(1);
					cardModel.setMemo1("");
					cardModel.setMemo2(cardInfo.getMemo2());
					cardModel.setPublishStatus(0);
					cardModel.setApprovalStatus(1);
					cardModel.setOldCardFlg(0);
					cardModel.setUpdateDate(new Date());
					cardModel.setOperaterId(ecardUser.getUserId());
					cardModel.setDeletDate(null);
					cardModel.setDeleteFlg(0);
					
					CompanyInfo companyInfo = new CompanyInfo();
					companyInfo.setCompanyId(0);
					cardModel.setCompanyInfo(companyInfo);
					cardModel.setCardType(1);
					cardModel.setCardOwnerId(userId);
					cardModel.setCardOwnerName(userInfo.getName());					
					cardModel.setAutoMemo(cardInfo.getAutoMemo());

					PossessionCardId possessionMyCardId = new PossessionCardId();
					possessionMyCardId.setUserId(userId);
					possessionMyCardId.setContactDate(new Date());
					possessionMyCardId.setCreateDate(new Date());
					PossessionCard possessionMyCard = new PossessionCard();
					possessionMyCard.setId(possessionMyCardId);

					cardModel.getPossessionCards().add(possessionMyCard);

					if (cardInfo.getUserTag() != null) {
						CardTag cardTag = new CardTag();
						UserTag userTag = new UserTag();
						userTag.setUserInfo(userInfo);
						userTag.setTagName(cardInfo.getUserTag());
						cardTag.setUserTag(userTag);
						cardModel.getCardTags().add(cardTag);
					}
					
					cardModelList.add(cardModel);
				}				

				// import data to database
				if (CollectionUtils.isNotEmpty(cardModelList)){
                        List<CardInfo> importSuccessList = importCsvDataService.importListCardInfoFromCsv(cardModelList);
                        recordSuccess = importSuccessList.size();
                        recordError += cardModelList.size() - recordSuccess;

                        // Start new thread to upload default card for list of success importing card
                        //UploadDefaultCardThread uploadThread = new UploadDefaultCardThread();
                        //uploadThread.start();
				}
                                    
				modelAndView.addObject("recordSuccess", recordSuccess);
				modelAndView.addObject("recordError", recordError);
				modelAndView.addObject("recordEmpty", recordEmpty);
				//modelAndView.addObject("errorLineNo", errorLineNo);
				modelAndView.addObject("msgImportSuccess", this.csvImportSuccess);
				modelAndView.setViewName("viewImportCSV");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ModelAndView("redirect:importCardCSV", "error", "ErrorSystem");
		}		
		
		modelAndView.addObject("listUserInfo", listUserInfo);
		modelAndView.addObject("listGroupCompany", listGroupCompany);
		modelAndView.setViewName("viewImportCSV");
		if(recordError != 0 || recordEmpty != 0){
                    System.out.println("uploadCardCsv SUCCESS_CNT=" + recordSuccess + " ERROR_CNT=" + recordError + " EMPTY_CNT=" + recordEmpty);
                    //System.out.println("uploadCardCsv ErrDate=" + errorLineNo);
                    logger.debug("uploadCardCsv SUCCESS_CNT=" + recordSuccess + " ERROR_CNT=" + recordError + " EMPTY_CNT=" + recordEmpty);
                    //logger.debug("uploadCardCsv ErrDate=" + errorLineNo);
		}

		return modelAndView;
    }
    
    private static CellProcessor[] getProcessorsUser(){
        return new CellProcessor[] {
            new NotNull() , // sansanId
            null, // department            
            new NotNull(), // userName
            new NotNull(), // email
            null, //email2
            //new ParseDate ("MM/dd/yyyy"), // useDate
            null, // useDate
            null, // language
            null, // formSendMail
            null, // idConnectAD
            null, // roleId
            null, // roleAdminId
            null, //data
            null, // groupDataDlRequestFlg
            null, // allDataDlRequestFlg
            null, // mailSystem
            null, // slManualLinkFlg
            null, // ownerOtherUser
            null, // reserve1 (case)
            null, // reserve2 
            null, // reserve3 (api)
            null, // useStopFlg            
            null, // connectZapier
            null // position   
        };
    }
    
    private static CellProcessor[] getProcessorsOperator(){
        return new CellProcessor[] {
            new NotNull(), // email
            null, // department
            null, // position
            null, // lastName
            null, // firstName
            null, // lastNameKana
            null, // firstNameKana
            null, // useDate
            null, // endDate
            null, // roleID
            null, // roleAdminID
            null // salesforce
            
        };
    }
    
    private static CellProcessor[] getProcessorsCardInfo(){
        return new CellProcessor[] {
            null, //companyName,
            null, //companyNameEn,
            null, //companyNameKana,
            null, //departmentName,
            null, //positionName,
            null, //name,
            null, //lastName,
            null, //firstName,
            null, //nameKana,
            null, //lastNameKana,
            null, //firstNameKana,
            null, //zipCode,
            null, //addressFull,
            null, //address1,
            null, //address2,
            null, //address3,
            null, //address4,
            null, //telNumberCompany,
            null, //telNumberDepartment,
            null, //faxNumber,
            null, //mobileNumber,
            null, //email,
            null, //companyUrl,
            null, //subAddressFull,
            null, //subZipCode,
            null, //subAddress1,
            null, //subAddress2,
            null, //subAddress3,
            null, //subAddress4,
            null, //subTelNumberCompany,
            null, //subTelNumberDepartment,
            null, //subFaxNumber
            null, //subMobileNumber
            null, //subEmail
            null, //subCompanyUrl
            null, //importanceLevel
            null, //VP
            null, //CP
            null, //fileOutputFlg
            null, //userTag
            //new ParseDate ("yyyy/MM/dd HH:mm",true), //createDate            
            null, //createDate
            //new ParseDate ("yyyy/MM/dd HH:mm",true), // contactDate
            null, // possession contactDate
            null, //address
            null, //tel
            null, //fax
            null, //e_mail
            null, //memo1
            null, //autoMemo
            null, //cardOwnerId
            null, //cardOwnerName
            //new ParseDate ("yyyy/MM/dd",true), //contact_date
            null, //contact_date => memo2
            null, //contact_type
            null, //title
            null, //place
            null, //contact_memo
            null, //company_id
            null, //userIndexNo
            null, //cardIndexNo
            null, //blank
        };
    }
    
    private int getRoleId(String roleId){
    	int role = 0;
    	switch(roleId){
	    	case "スーパーバイザー":
	    		role = 6;
	    		break;
	    	case "承認者":
	    		role = 5;
	    		break;
	    	case "オペレーター":
	    		role = 4;
	    		break;
	    	case "権限なし":
	    		role = 1;
	    		break;
	    	default:
	    		role = 1;
	    		break;
    	}    	
    	return role;
    }
    
    private int getRoleAdminId(String roleAdminId){
    	int role = 0;
    	switch(roleAdminId){
	    	case "推進管理者":
	    		role = 7;
	    		break;
	    	case "保守担当者":
	    		role = 3;
	    		break;
	    	case "社内管理者":
	    		role = 2;
	    		break;
	    	case "権限なし":
	    		role = 1;
	    		break;
	    	default:
	    		role = 1;
	    		break;
    	}    	
    	return role;
    }
    private int checkFlg(String str){
    	int flg = 0;
    	switch (str) {
		case "利用可":
			flg = 1;
			break;
		case "利用不可":
			flg = 0;
			break;
		default:
			flg = 0;
			break;
		}
    	return flg;
    }
    
    private void sendMailResgisterOperator(List<UserInfo> listUserInfo){
    	for(UserInfo userInfo : listUserInfo){
    		Context ctx = new Context();
			ctx.setVariable("password", userInfo.getPassword());
			ctx.setVariable("recipientEmail", userInfo.getEmail());
			try {
				emailService.sendMail(CommonConstants.USER_FROM_MAIL, userInfo.getEmail() ,CommonConstants.TITLE_RECOVERPASS_MAIL, ctx, "newpassword");
			} catch (MessagingException e) {
				e.printStackTrace();
			}
    	}
    }
    
    private int getRoleIdByPermissionType(List<Roles> listRoles, String permissionType){
    	if (CollectionUtils.isNotEmpty(listRoles)){
	    	for (Roles role : listRoles) {
				if (permissionType.equals(role.getPermissionType())){
					return role.getRoleId();
				}
			}
    	}
    	return 0;
    }
    
    /**
	 * New thread class to upload default card after importing card CSV file.
	 * 
	 * @author nhat.nguyen
	 */
    class UploadDefaultCardThread extends Thread {

		public void run() {
			List<CardInfo> cardInfoList = cardInfoService.listCardInfoByCardType(1);
			 
			if (CollectionUtils.isEmpty(cardInfoList) || StringUtils.isEmpty(defaultImage64)) {
				return;
			}
			logger.info("Thread start uploading card");
			try {
				
			    ClassLoader classLoader = DataProcessController.class.getClassLoader();
				File file = new File(classLoader.getResource("MSMINCHO.TTF").getFile());
				Font font = Font.createFont(Font.TRUETYPE_FONT, file);
				
				for (CardInfo cardInfo : cardInfoList) {
					Thread.sleep(1000);
					BufferedImage image = UploadFileUtil.decodeToImage(defaultImage64);
					Graphics g = image.getGraphics();
					Graphics2D g2 = (Graphics2D)g;
					 g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
						        RenderingHints.VALUE_ANTIALIAS_ON);
					font = font.deriveFont(Font.BOLD, 20f);
					//Font font = new Font(Font.SANS_SERIF, Font.BOLD, 20);
					g2.setFont(font);
					g2.setColor(Color.BLACK);
					g2.drawString(cardInfo.getCompanyName()!= null ? StringUtilsHelper.convertToUTF8(cardInfo.getCompanyName()) : StringUtils.EMPTY, CommonConstants.xCooder, CommonConstants.yCooder);
					g2.drawString(cardInfo.getDepartmentName() != null ? StringUtilsHelper.convertToUTF8(cardInfo.getDepartmentName()): StringUtils.EMPTY, CommonConstants.xCooder, CommonConstants.yCooder+ 30);
					g2.drawString(cardInfo.getPositionName() != null ? StringUtilsHelper.convertToUTF8(cardInfo.getPositionName()) : StringUtils.EMPTY, CommonConstants.xCooder, CommonConstants.yCooder+ 60);
					
					g2.drawString(cardInfo.getName() != null ? StringUtilsHelper.convertToUTF8(cardInfo.getName()) : StringUtils.EMPTY,CommonConstants.xCooder + 30, CommonConstants.yCooder + 150);
					g2.dispose();
					UploadFileUtil.overrideImage(UploadFileUtil.encodeToString(image, "jpg"), scpHostName, scpUser, scpPassword, cardInfo.getImageFile());
					
				}
				
				cardInfoService.updateCardType();
			} catch (Exception ex) {
				logger.error("Error upload default card image: " + ex.getMessage());
			}
		}
	}
}

