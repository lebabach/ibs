package com.ecard.webapp.controller;

import java.io.ByteArrayInputStream;
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

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import com.ecard.core.model.InquiryInfo;
import com.ecard.core.model.UserInfo;
import com.ecard.core.service.CardInfoService;
import com.ecard.core.service.GroupCompanyInfoService;
import com.ecard.core.service.SettingsInfoService;
import com.ecard.core.service.UserInfoService;
import com.ecard.core.service.converter.CardInfoConverter;
import com.ecard.core.vo.CardConnectModel;
import com.ecard.core.vo.CardInfoCSV;
import com.ecard.core.vo.CardInfoUserVo;
import com.ecard.core.vo.UserDownloadPermission;
import com.ecard.webapp.security.EcardUser;
import com.ecard.webapp.util.UploadFileUtil;
import com.ecard.webapp.vo.CardInfoPCVo;
import com.ecard.webapp.vo.DataPagingJsonVO;
import com.ecard.webapp.vo.UserInfoVO;

@Controller
@RequestMapping("/user/*")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserInfoService userInfoService;

	@Autowired
	CardInfoService cardInfoService;

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

	@RequestMapping("home")
	public ModelAndView home() {
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
					// String fileNameFromSCP =
					// UploadFileUtil.getImageFileFromSCP(cardInfo.getCardInfo().getImageFile(),
					// scpHostName, scpUser,
					// scpPassword,Integer.parseInt(scpPort));
					if (nameSort.trim().equals(cardInfo.getSortType().trim())) {
						// cardInfo.getCardInfo().setImageFile(fileNameFromSCP);
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
		System.out.println("BBB = " + limit);
		DataPagingJsonVO<CardInfoPCVo> dataTableResponse = new DataPagingJsonVO<CardInfoPCVo>();
		List<CardInfoPCVo> cardInfoSearchResponses = new ArrayList<CardInfoPCVo>();

		List<String> lstNameSort = cardInfoService.getListSortType(ecardUser.getUserId());
		List<CardInfoUserVo> lstCardInfo = cardInfoService.getListPossesionCard(ecardUser.getUserId(), limit);

		for (String nameSort : lstNameSort) {
			List<CardInfo> cardInfoDisp = new ArrayList<>();
			for (CardInfoUserVo cardInfo : lstCardInfo) {
				// String fileNameFromSCP =
				// UploadFileUtil.getImageFileFromSCP(cardInfo.getCardInfo().getImageFile(),
				// scpHostName, scpUser, scpPassword,Integer.parseInt(scpPort));
				if (nameSort.trim().equals(cardInfo.getSortType().trim())) {
					// cardInfo.getCardInfo().setImageFile(fileNameFromSCP);
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
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

	private int parseIntParameter(String parameter, int defaultValue) {
		try {
			return Integer.parseInt(parameter);
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}

	@RequestMapping(value = "/detail/{id:[\\d]+}", method = RequestMethod.GET)
	public ModelAndView detailPC(@PathVariable("id") int id) {
		logger.debug("detailPC", UserController.class);

		ModelAndView modelAndView = new ModelAndView();
		CardInfo cardInfo = null;
		List<CardConnectModel> cardList = null;
		try {
			cardInfo = cardInfoService.getCardInfoDetail(id);
			String fileNameFromSCP = UploadFileUtil.getImageFileFromSCP(cardInfo.getImageFile(), scpHostName, scpUser,
					scpPassword, Integer.parseInt(scpPort));
			cardInfo.setImageFile(fileNameFromSCP);

			// List card connected
			cardList = cardInfoService.listCardConnect(cardInfo.getCardOwnerId(), cardInfo.getGroupCompanyId(),
					cardInfo.getName(), cardInfo.getCompanyName(), cardInfo.getEmail());
		} catch (Exception ex) {
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

}
