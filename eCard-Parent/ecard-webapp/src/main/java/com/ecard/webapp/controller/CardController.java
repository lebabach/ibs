/*
 * HomeController
 */
package com.ecard.webapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ecard.core.contants.AppIdContants;
import com.ecard.core.model.CardInfo;
import com.ecard.core.model.CardUpdateHistory;
import com.ecard.core.model.CardUpdateHistoryId;
import com.ecard.core.model.CompanyInfo;
import com.ecard.core.model.PossessionCard;
import com.ecard.core.model.PossessionCardId;
import com.ecard.core.model.PushInfoId;
import com.ecard.core.model.UserInfo;
import com.ecard.core.model.UserNotification;
import com.ecard.core.model.enums.CardUpdateHistoryType;
import com.ecard.core.model.enums.NoticeType;
import com.ecard.core.model.enums.StatusCard;
import com.ecard.core.service.AdminPossessionCardService;
import com.ecard.core.service.CardInfoService;
import com.ecard.core.service.CardUpdateHistoryService;
import com.ecard.core.service.OcrCardImageService;
import com.ecard.core.service.PossessionCardService;
import com.ecard.core.service.TeamInfoService;
import com.ecard.core.service.UserInfoService;
import com.ecard.core.service.UserNotifyService;
import com.ecard.core.util.PairUtil;
import com.ecard.core.webservice.Status;
import com.ecard.webapp.constant.CommonConstants;
import com.ecard.webapp.security.EcardUser;
import com.ecard.webapp.security.RoleType;
import com.ecard.webapp.util.FileUploadModel;
import com.ecard.webapp.util.RoteImage;
import com.ecard.webapp.util.StringUtilsHelper;
import com.ecard.webapp.util.UploadFileUtil;
import com.ecard.webapp.vo.CardInfoResultVO;
import com.ecard.webapp.vo.CardInfoVO;
import com.ecard.webapp.vo.CardInfoWithRoteVO;
import com.ecard.webapp.vo.DataPagingJsonVO;
import com.ecard.webapp.vo.ListCardInfoVO;
import com.ecard.core.vo.CardUpdateHisAndUserInfo;
import com.ecard.core.vo.CardInfoNotifyChange;
import com.ecard.core.vo.PushNotification;
import com.ecard.core.vo.UserInfoVo;


@Controller
@RequestMapping("/cards/*")
public class CardController {
	private static final Logger logger = LoggerFactory.getLogger(CardController.class);
	@Autowired
	CardInfoService cardInfoService;
	
	@Autowired
	UserInfoService userInfoService;
	
	@Autowired
	AdminPossessionCardService adminProssessionCardService;
	
	@Autowired
    CardUpdateHistoryService cardUpdateHistoryService;
	
	@Autowired
	PossessionCardService possessionCardService;
	
	@Autowired
	OcrCardImageService ocrCardImageService;
	
	@Autowired
	UserNotifyService userNotifyService;
	
	@Autowired
    TeamInfoService teamInfoService;
	
	@Value("${scp.hostname}")
	private String scpHostName;
	    
	@Value("${scp.user}")
    private String scpUser;
	    
	@Value("${scp.password}")
	private String scpPassword;
	    
	@Value("${scp.port}")
	private String scpPort;
	
	@Value("${push.api}")
	private String pushApi;
	
	/*
	 * Return list user to sort card of user.
	 * if user have role is operator then only get list of user have the same team of user login
	 * else then get list all user
	 */
	@RequestMapping("list")
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView();		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
		UserInfo userInfo = userInfoService.getUserInfoByUserId(ecardUser.getUserId());		
		Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		List<UserInfoVo> listUser = null;		
		if(roles.contains(RoleType.ROLE_OPERATOR.name())){
			if(userInfo.getTeamInfo() != null){
			   listUser = userInfoService.getListUserofTeam(userInfo.getTeamInfo().getTeamId());
			}else{
				UserInfoVo userVo = new UserInfoVo(userInfo.getUserId(), userInfo.getTeamDivideCnt(), userInfo.getName(), userInfo.getLastName(), userInfo.getFirstName(), userInfo.getCompanyName(), userInfo.getDepartmentName(), userInfo.getPositionName());
				listUser= new ArrayList<>();
				listUser.add(userVo);
			}
		} else { 
			listUser = userInfoService.getListUserAllTeam();
		}
		modelAndView.addObject("userId",ecardUser.getUserId());
		modelAndView.addObject("listUser", listUser);
		modelAndView.setViewName("cardmanage");
		return modelAndView;
	}
	/*
	 * Return list card
	 * Search with status card . 
	 * -If user have role Supervisor then default is list all card.
	 * -If user have role Leader then default get all list card have status is 4 (waiting approve)
	 * -If user have role Operator then default get all list card have status is 2(waiting input)
	 * Search with user
	 * Search with company name,department,position,email,name (criteriaSearch)
	 * 
	 */
	@RequestMapping(value = "search", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public DataPagingJsonVO<CardInfoResultVO> search(HttpServletRequest request, HttpSession session) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		int userId = 0;
		int status = 0;
		userId = parseIntParameter(request.getParameter("userId"),0);		
		if(request.getParameter("status")!= null){
			status = parseIntParameter(request.getParameter("status"), 0);
		}else{
			if(roles.contains(RoleType.ROLE_LEADER.name())){
				status = parseIntParameter(request.getParameter("status"), 4);
			}else if (roles.contains(RoleType.ROLE_OPERATOR.name())){
				status = parseIntParameter(request.getParameter("status"), 2);
			}
		}
    	
		DataPagingJsonVO<CardInfoResultVO> dataTableResponse = new DataPagingJsonVO<CardInfoResultVO>();
		List<CardInfoResultVO> cardInfoSearchResponses = new ArrayList<CardInfoResultVO>();
		  String criteriaSearch ="";
		if(request.getParameter("criteriaSearch") != null){
		    criteriaSearch = request.getParameter("criteriaSearch");
		}
		
		int limit = parseIntParameter(request.getParameter("length"), 0);
		int offset = parseIntParameter(request.getParameter("start"), 0);
		String textSearch = criteriaSearch.trim().replaceAll(" +", "|");
		// Process with roles for user
		List<Integer> listStatus = listPermissionViewCardWithUser(roles.iterator().next());
		List<com.ecard.core.vo.CardInfo> cardInfoList = null;
		BigInteger count;
		
		if(userId == 0){

			cardInfoList = cardInfoService.searchCard(textSearch, status, listStatus, limit, offset);
			count = cardInfoService.countCardInfo(textSearch, status, listStatus);
		} else {
			cardInfoList = cardInfoService.searchCardUser(textSearch, status, listStatus, limit, offset, userId);
			count = cardInfoService.countCardInfoUser(textSearch, status, listStatus, userId);
		}

		long totalrecord = count.longValue();
		cardInfoList=UploadFileUtil.getImageFileFromSCP(cardInfoList, scpHostName, scpUser, scpPassword, Integer.parseInt(scpPort));
		for (com.ecard.core.vo.CardInfo info : cardInfoList) {
			//String fileNameFromSCP = UploadFileUtil.getImageFileFromSCP(info.getImageFile(), scpHostName, scpUser, scpPassword, Integer.parseInt(scpPort));
			String fileNameFromSCP = info.getCardBackImgFile();//UploadFileUtil.getImageFileFromSCP(info.getImageFile(), scpHostName, scpUser, scpPassword, Integer.parseInt(scpPort));
			String approvalStatus = StatusCard.getEnumNameForValue(info.getApprovalStatus());
			CardInfoResultVO cardInfoSearchResponse = new CardInfoResultVO(info.getCardId(),fileNameFromSCP, info.getName(), info.getCompanyName(), info.getPositionName(), info.getEmail(), info.getMobileNumber(), info.getCreateDate().toString(), approvalStatus,info.getFirstName(),info.getLastName(),info.getIndexId());
			cardInfoSearchResponse.setIs_editting(info.getIs_editting());
			cardInfoSearchResponses.add(cardInfoSearchResponse);
		}
		dataTableResponse.setDraw(parseIntParameter(request.getParameter("draw"), 0));
		dataTableResponse.setRecordsTotal(totalrecord);
		dataTableResponse.setRecordsFiltered(totalrecord);
		dataTableResponse.setData(cardInfoSearchResponses);
		return dataTableResponse;
	}
	
	/*
	 * Return screen confirm card
	 */
	@RequestMapping ("confirm/{id:[\\d]+}")
	public ModelAndView detail(@PathVariable("id") int id) {
		CardInfo cardInfo = null;
		try {
			cardInfo = cardInfoService.getCardInfoDetail(id);
			String fileNameFromSCP = UploadFileUtil.getImageFileFromSCP(cardInfo.getImageFile(), scpHostName, scpUser, scpPassword, Integer.parseInt(scpPort));
			cardInfo.setImageFile(fileNameFromSCP);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ModelAndView("cardconfirm", "cardInfo", cardInfo);
	}
	
	/*
	 * Return screen edit card 
	 * If user login have role diff Supervisor and isEdititng is 1 then redirect to screen list card
	 * Else forward to screen edit
	 */
	@RequestMapping (value = "edit/{id:[\\d]+}", method = RequestMethod.GET)
	public ModelAndView editView(@PathVariable("id") int id,HttpSession session) {
		CardInfo cardInfo = null;
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("cardedit");
		String fileNameFromSCP = "";

		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			EcardUser ecardUser = (EcardUser) authentication.getPrincipal();			
			if ((CardInfo) session.getAttribute("cardInfo" + id) != null) {
				cardInfo = (CardInfo) session.getAttribute("cardInfo" + id);
				String imageFile = cardInfo.getImageFile().substring(cardInfo.getImageFile().indexOf(',') + 1);
				cardInfo.setImageFile(imageFile);
				cardInfo.setCardBackImgFile(cardInfo.getCardBackImgFile());
				session.setAttribute("cardInfo" + id, null);
			} else {
				//check is editting? https://livepass.backlog.jp/view/MEISHI-788
				cardInfo = cardInfoService.getCardInfoDetail(id);
				Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
				List<String> RolesHasSupper= roles.stream().distinct().filter(r->r.equals(RoleType.ROLE_SUPERVISOR.toString())).collect(Collectors.toList());
				if(!(RolesHasSupper!=null && RolesHasSupper.size()>0)){
					if(cardInfo.getIsEditting()==1 ){
						return new ModelAndView("redirect:../list");
					}
				}
				
				cardInfo.setIsEditting(1);
				cardInfo.setDateEditting(new Date());
				cardInfoService.editCardInfoNoIndexNo(cardInfo);
				
				fileNameFromSCP = UploadFileUtil.getImageFileFromSCP(cardInfo.getImageFile(), scpHostName, scpUser, scpPassword, Integer.parseInt(scpPort));
				cardInfo.setCardBackImgFile(cardInfo.getImageFile());
				cardInfo.setImageFile(fileNameFromSCP);
				
			}
			boolean permissionEdit = adminProssessionCardService.checkPermissionEdit(ecardUser.getUserId(), id);
			modelAndView.addObject("cardInfo", cardInfo);
			modelAndView.addObject("permissionEdit", permissionEdit);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	@RequestMapping (value = "zipCodeSearch", method = RequestMethod.POST)
	@ResponseBody
	public List<String> zipCodeSearch(@RequestParam(value = "zipCode", required = false) String zipCode) throws ParseException {
		ResponseEntity<String> result = null;
		String uri = "http://zipcloud.ibsnet.co.jp/api/search?zipcode=" + zipCode;
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "text/plain; charset=utf-8");
		HttpEntity entity = new HttpEntity(headers);
		result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
		org.json.simple.JSONObject object = (org.json.simple.JSONObject) new JSONParser().parse(result.getBody());
		org.json.simple.JSONArray arrJson = (org.json.simple.JSONArray) object.get("results");
		List<String> listaddress = new ArrayList<>();
		if (arrJson != null) {
			for (int i = 0; i < arrJson.size(); i++) {
				org.json.simple.JSONObject childJson = (org.json.simple.JSONObject) arrJson.get(i);
				listaddress.add(childJson.get("address1").toString());
				listaddress.add(childJson.get("address2").toString());
				listaddress.add(childJson.get("address3").toString());
			}
		}
		return listaddress;
	}
	
	/*
	 * Return screen waiting edit submit card
	 */
	@RequestMapping (value = "edit/{id:[\\d]+}", method = RequestMethod.POST)
	public ModelAndView edit(CardInfo cardInfo,int rote,HttpSession session) {
		//review to wait for submit information
		String img64 = "";
		List<String> listAddress = new ArrayList<String>(Arrays.asList(cardInfo.getAddressFull().trim().split(" ")));
		cardInfo.setAddress1(listAddress.get(0) != null ? listAddress.get(0) : "");
		cardInfo.setAddress2(listAddress.get(1) != null ? listAddress.get(1) : "");
		cardInfo.setAddress3(listAddress.get(2) != null ? listAddress.get(2) : "");
		if (listAddress.get(0) != null) {
			listAddress.remove(0);
		}
		if (listAddress.get(0) != null) {
			listAddress.remove(0);
		}
		if (listAddress.get(0) != null) {
			listAddress.remove(0);
		}

		String imageFile = cardInfo.getImageFile().substring(cardInfo.getImageFile().indexOf(',') + 1);
		try {
			img64 = RoteImage.ConvertByteArrayToString64(RoteImage.roteImageWithArc(imageFile, rote));
		} catch (IOException e) {
			e.printStackTrace();
		}
		cardInfo.setImageFile(img64);
		cardInfo.setCardBackImgFile(cardInfo.getCardBackImgFile());
		cardInfo.setAddress4(listAddress.stream().collect(Collectors.joining(" ")));
		session.setAttribute("cardInfo" + cardInfo.getCardId(), cardInfo);
		
		CardInfoWithRoteVO cardInfoWithRoteVO=new CardInfoWithRoteVO();
		cardInfoWithRoteVO.setCardInfo(cardInfo);
		cardInfoWithRoteVO.setRote(rote);
		return new ModelAndView("cardeditwaitsubmit", "cardInfoWithRoteVO", cardInfoWithRoteVO);
	}
	
	@RequestMapping (value = "editDirect", method = RequestMethod.POST)
	public ModelAndView editSave(CardInfo cardInfo,int rote) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
		UserInfo userOperatorInfo = userInfoService.getUserInfoByUserId(ecardUser.getUserId());
		//review to wait for submit information
		//bach.le rote image
		String imageData = getCardImageFile(cardInfo.getImageFile());
		FileUploadModel uploadModel = null;
		int result = 0;
		List<UserInfo> listUserInfo = userInfoService.getAllUserInfo();
		try {
			double rotation = RoteImage.getRotation(rote);
			if(rotation!=0){
				uploadModel = UploadFileUtil.overrideImage(imageData, this.scpHostName, this.scpUser, this.scpPassword, rote, cardInfo.getCardBackImgFile());	
			}else{
				uploadModel = new FileUploadModel();
				uploadModel.setStatus(true);
			}
		} catch (IOException e) {
			return new ModelAndView("cardedit", "cardInfo", cardInfo);
		}
		if (uploadModel.isStatus()) {
            
			if(!"".equals(cardInfo.getLastName().trim()) || !"".equals(cardInfo.getFirstName().trim())){
			     cardInfo.setName(StringUtilsHelper.mergerStringEitherAWord(cardInfo.getLastName(), cardInfo.getFirstName(), " "));
			}
			if(!"".equals(cardInfo.getLastNameKana().trim())|| !"".equals(cardInfo.getFirstNameKana().trim())){
		      	cardInfo.setNameKana(StringUtilsHelper.mergerStringEitherAWord(cardInfo.getLastNameKana(), cardInfo.getFirstNameKana(), " "));
			}
			
			// bach.le https://livepass.backlog.jp/view/MEISHI-575
			if(!"".equals(cardInfo.getAddressFull().trim())){
				List<String> listAddress = new ArrayList<String>(Arrays.asList(cardInfo.getAddressFull().trim().split(" ")));
				if(listAddress.size() ==3){
					cardInfo.setAddress1(listAddress.get(0) != null ? listAddress.get(0) : "");
					cardInfo.setAddress2(listAddress.get(1) != null ? listAddress.get(1) : "");
					cardInfo.setAddress3(listAddress.get(2) != null ? listAddress.get(2) : "");
					if (listAddress.get(0) != null) {
						listAddress.remove(0);
					}
					if (listAddress.get(0) != null) {
						listAddress.remove(0);
					}
					if (listAddress.get(0) != null) {
						listAddress.remove(0);
					}
				}else if(listAddress.size() ==2) {
					cardInfo.setAddress1(listAddress.get(0) != null ? listAddress.get(0) : "");
					cardInfo.setAddress2(listAddress.get(1) != null ? listAddress.get(1) : "");
					if (listAddress.get(0) != null) {
						listAddress.remove(0);
					}
					if (listAddress.get(0) != null) {
						listAddress.remove(0);
					}
					
				}else if(listAddress.size() ==1) {
					cardInfo.setAddress1(listAddress.get(0) != null ? listAddress.get(0) : "");
					if (listAddress.get(0) != null) {
						listAddress.remove(0);
					}
					
				}
	
				cardInfo.setAddress4(listAddress.stream().collect(Collectors.joining(" ")));
			}

			CardInfo nCardInfo = cardInfoService.getCardInfoDetail(cardInfo.getCardId());
			cardInfo.setCardIndexNo(nCardInfo.getCardIndexNo());
			cardInfo.setIsEditting(0);
			cardInfo.setDateEditting(new Date());
			cardInfo.setGroupCompanyId(nCardInfo.getGroupCompanyId());
			result = cardInfoService.updateCardInfoAdmin(cardInfo);

			UserInfo userInfo = null;
			try {
				userInfo = userInfoService.getUserInfoByUserId(cardInfo.getCardOwnerId());
			} catch (Exception ex) {
				ex.printStackTrace();
				return new ModelAndView("redirect:list");
			}
			if(nCardInfo.getApprovalStatus() != cardInfo.getApprovalStatus()){
				// Update card history			
				CardUpdateHistory cardUpdateHistory = new CardUpdateHistory();
				CardUpdateHistoryId cardUpdateHistoryId = new CardUpdateHistoryId();
				cardUpdateHistoryId.setParamType(cardInfo.getApprovalStatus());
				cardUpdateHistoryId.setOldData(null);
				cardUpdateHistoryId.setNewData(null);
				cardUpdateHistoryId.setCreateDate(new Date());
				cardUpdateHistoryId.setUpdateDate(new Date());
				cardUpdateHistoryId.setOperaterId(userOperatorInfo.getUserId());
				cardUpdateHistoryId.setCardId(cardInfo.getCardId());
				cardUpdateHistory.setId(cardUpdateHistoryId);
				cardUpdateHistoryService.registerCardUpdateHistory(cardUpdateHistory);
			}
			
			Long sameCardInfoByOwner = cardInfoService.countSameCardInfoByOwner(cardInfo);
			if(sameCardInfoByOwner <= 0){
				// Push to other users				
				List<PairUtil<Integer,Integer>> pairListOwner = cardInfoService.getListUserPushToByCard(cardInfo);
//				List<PairUtil<Integer,Integer>> pairList = new ArrayList<PairUtil<Integer,Integer>>();
//				List<Integer> listOwnerId = cardInfoService.getListUserPushToByCard(cardInfo);
				UserInfo noticeUser = new UserInfo();
				for (PairUtil<Integer,Integer> listOwner : pairListOwner) {
					if(listUserInfo.stream().filter(u-> u.getUserId() == listOwner.getL()).collect(Collectors.toList()).size()<=0){
						continue;
					}				
					System.out.println("UserId = "+listOwner + " ======================= PUSH NOTIFICATION TO OTHER USERS ============== :"+userInfo.getName());				
					String strPushFROM = cardInfo.getName() + " さんの名刺を通して、" + userInfo.getName() + " さんと繋がりました。";
					pushNoticeConnectUser(listOwner.getL(), listOwner.getR(),strPushFROM, 2);
					UserNotification userNotification = new UserNotification();
					noticeUser.setUserId(listOwner.getL());
					userNotification.setUserInfo(noticeUser);
					userNotification.setCardId(listOwner.getR());
					userNotification.setNoticeDate(new Date());
					userNotification.setReadFlg(0);				
	            	userNotification.setChangeParamType(1);            	
	            	userNotification.setNoticeType(NoticeType.NOTIFICATION_TO_OTHER_USER.getValue());
	            	userNotification.setNotifyMessage(strPushFROM);            	
	            	userInfoService.saveHistoryNotification(userNotification);
				}
				
				//	Push to me
				List<Integer> listOwnerId = cardInfoService.getListUserPushFromByCard(cardInfo);
				noticeUser = new UserInfo();
				for (Integer listOwner : listOwnerId) {
					if(listUserInfo.stream().filter(u-> u.getUserId() == listOwner).collect(Collectors.toList()).size()<=0){
						continue;
					}				
					UserInfo userInfoPush = listUserInfo.stream().filter(u-> u.getUserId() == listOwner).findFirst().get();
					System.out.println("======================= PUSH NOTIFICATION TO ME ============== :"+userInfoPush.getName());
					String strPushTO =  cardInfo.getName() + " さんの名刺を通して、" + userInfoPush.getName() + " さんと繋がりました。";								
					pushNoticeConnectUser(userInfo.getUserId(), cardInfo.getCardId(),strPushTO, 2);
					UserNotification userNotificationTOME = new UserNotification();
					noticeUser.setUserId(userInfo.getUserId());
					userNotificationTOME.setUserInfo(noticeUser);
					userNotificationTOME.setCardId(cardInfo.getCardId());
					userNotificationTOME.setNoticeDate(new Date());
					userNotificationTOME.setReadFlg(0);				
					userNotificationTOME.setChangeParamType(1);            	
					userNotificationTOME.setNoticeType(NoticeType.NOTIFICATION_TO_OTHER_USER.getValue());
					userNotificationTOME.setNotifyMessage(strPushTO);            	
		        	userInfoService.saveHistoryNotification(userNotificationTOME);				
				}
			} else {
				// Push notification when card change information about address full, department, tel number company, position
				List<CardInfoNotifyChange> listCardSameInfo = cardInfoService.getListCardInfoNotifyChange(cardInfo);
				UserInfo noticeUser = new UserInfo();
				for(CardInfoNotifyChange cardSameInfo : listCardSameInfo){
					String strPush = cardInfo.getName()+"さん";
					int isPush = 0;
					//  {name} さんの住所が{address_full}に、電話番号が{tel_number_company}に、 部署が{department_name}に、役職が{position_name}に変わりました。
					if(!cardSameInfo.getAddressFull().toLowerCase().trim().equals(cardInfo.getAddressFull().toLowerCase().trim())) {
						strPush += "の住所が"+cardInfo.getAddressFull() + "に、";
						isPush = 1;
					} 
					if(!cardSameInfo.getTelNumberCompany().toLowerCase().trim().equals(cardInfo.getTelNumberCompany().toLowerCase().trim())) {
						strPush += "電話番号が "+cardInfo.getTelNumberCompany() + "に、";						
						isPush = 2;
					}
					
					if(!cardSameInfo.getDepartmentName().toLowerCase().trim().equals(cardInfo.getDepartmentName().toLowerCase().trim())) {
						strPush += " 部署が"+cardInfo.getDepartmentName() + "に、";
						isPush = 3;
					}
					
					if(!cardSameInfo.getPositionName().toLowerCase().trim().equals(cardInfo.getPositionName().toLowerCase().trim())) {
						strPush += "役職が "+cardInfo.getPositionName() + "に、";
						isPush = 4;
					}
					
					strPush = strPush.substring(0, strPush.length() - 1);
					strPush += "変わりました。";
					if(isPush != 0){
						pushNoticeConnectUser(cardSameInfo.getCardOwnerId(), cardInfo.getCardId(),strPush, 2);
						UserNotification userNotification = new UserNotification();
						noticeUser.setUserId(cardSameInfo.getCardOwnerId());
						userNotification.setUserInfo(noticeUser);
						userNotification.setCardId(cardInfo.getCardId());
						userNotification.setNoticeDate(new Date());
						userNotification.setReadFlg(0);				
		            	userNotification.setChangeParamType(1);            	
		            	userNotification.setNoticeType(NoticeType.REGISTRATION_CARD_CHANGE.getValue());
		            	userNotification.setNotifyMessage(strPush);            	
		            	userInfoService.saveHistoryNotification(userNotification);
					}
				}
			}
			
			
			
			CardInfo newestCardInfo = cardInfoService.getNewestCardInfo(cardInfo);
			System.out.println("AAA = "+newestCardInfo.getCardId());
			cardInfoService.updateOldCardInfo(newestCardInfo);
		}
		if (result == 1)
			return new ModelAndView("redirect:list");
		return new ModelAndView("cardedit", "cardInfo", cardInfo);
	}
	
	@RequestMapping (value = "editStatus", method = RequestMethod.POST)
	public ModelAndView upadteStatus(CardInfo cardInfo) {
		//review to wait for submit information
		cardInfo.setIsEditting(0);
		cardInfo.setDateEditting(new Date());
		int result = cardInfoService.updateStatusCard(cardInfo);
		if(result == 1)	
			return new ModelAndView("redirect:list");
		return new ModelAndView("cardedit", "cardInfo", cardInfo);
	}
	
	
	@RequestMapping (value = "editSuccess", method = RequestMethod.POST)
	public ModelAndView editSubmit(CardInfo cardInfo,int rote) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
		UserInfo userOperatorInfo = userInfoService.getUserInfoByUserId(ecardUser.getUserId());
		
		List<String> listAddress = new ArrayList<String>(Arrays.asList(cardInfo.getAddressFull().trim().split(" ")));
		cardInfo.setAddress1(listAddress.get(0) != null ? listAddress.get(0) : "");
		cardInfo.setAddress2(listAddress.get(1) != null ? listAddress.get(1) : "");
		cardInfo.setAddress3(listAddress.get(2) != null ? listAddress.get(2) : "");
		if (listAddress.get(0) != null) {
			listAddress.remove(0);
		}
		if (listAddress.get(0) != null) {
			listAddress.remove(0);
		}
		if (listAddress.get(0) != null) {
			listAddress.remove(0);
		}

		cardInfo.setAddress4(listAddress.stream().collect(Collectors.joining(" ")));
		CardInfo nCardInfo = cardInfoService.getCardInfoDetail(cardInfo.getCardId());
		cardInfo.setCardIndexNo(nCardInfo.getCardIndexNo());
		cardInfo.setIsEditting(0);
		cardInfo.setDateEditting(new Date());
		String imageData = getCardImageFile(cardInfo.getImageFile());
		FileUploadModel uploadModel = null;
		try {
			double rotation = RoteImage.getRotation(rote);
			if(rotation != 0){
				uploadModel = UploadFileUtil.overrideImage(imageData, this.scpHostName, this.scpUser, this.scpPassword, 0, cardInfo.getCardBackImgFile());	
			}else{
				uploadModel = new FileUploadModel();
				uploadModel.setStatus(true);
			}
		} catch (IOException e) {
			return new ModelAndView("cardedit", "cardInfo", cardInfo);
		}
		
		int result = cardInfoService.updateCardInfoAdmin(cardInfo);
		// Update card history
		CardUpdateHistory cardUpdateHistory = new CardUpdateHistory();
		CardUpdateHistoryId cardUpdateHistoryId = new CardUpdateHistoryId();
		cardUpdateHistoryId.setParamType(cardInfo.getApprovalStatus());
		cardUpdateHistoryId.setOldData(null);
		cardUpdateHistoryId.setNewData(null);
		cardUpdateHistoryId.setCreateDate(new Date());
		cardUpdateHistoryId.setUpdateDate(new Date());
		cardUpdateHistoryId.setOperaterId(userOperatorInfo.getUserId());
		cardUpdateHistoryId.setCardId(cardInfo.getCardId());
		cardUpdateHistory.setId(cardUpdateHistoryId);
		cardUpdateHistoryService.registerCardUpdateHistory(cardUpdateHistory);
		if (result == 1)
			return new ModelAndView("redirect:list");
		return new ModelAndView("cardeditwaitsubmit", "cardInfo", cardInfo);
	}

	@RequestMapping("scancard")
	public ModelAndView scancard() {
		return new ModelAndView("scancard");
    } 

	@RequestMapping("listuser")
	@ResponseBody
	public List<UserInfoVo> searchUser(@RequestParam(value = "criteriaSearch", required = false) String criteriaSearch) {
		String textSearch = criteriaSearch.trim().replaceAll(" +", "|");
		List<UserInfoVo> userInfoVOs = null;
		if (criteriaSearch == null || criteriaSearch.isEmpty()){
			userInfoVOs = userInfoService.getAllUserSearchInfo();
		}else{
			userInfoVOs = userInfoService.searchUser(textSearch, -1, -1);
		}
		return userInfoVOs;
    } 

	@RequestMapping("delete")
	@ResponseBody
	public int delete(@RequestParam(value = "cardId") int cardId){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
		UserInfo userInfo = userInfoService.getUserInfoByUserId(ecardUser.getUserId());
		
		try {
			cardInfoService.deleteCardInfo(cardId);
			
			// Update card history
			CardUpdateHistory cardUpdateHistory = new CardUpdateHistory();
			CardUpdateHistoryId cardUpdateHistoryId = new CardUpdateHistoryId();
			cardUpdateHistoryId.setParamType(CardUpdateHistoryType.DELETE.getValue());
			cardUpdateHistoryId.setOldData(null);
			cardUpdateHistoryId.setNewData(null);
			cardUpdateHistoryId.setCreateDate(new Date());
			cardUpdateHistoryId.setUpdateDate(new Date());
			cardUpdateHistoryId.setOperaterId(userInfo.getUserId());	
			cardUpdateHistoryId.setCardId(cardId);
			cardUpdateHistory.setId(cardUpdateHistoryId);
			
			cardUpdateHistoryService.registerCardUpdateHistory(cardUpdateHistory);
		} catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	
	@RequestMapping(value = "/registerCardImage", method = RequestMethod.POST)
	@ResponseBody
	public int registerCardImage(@RequestBody ListCardInfoVO Cards, HttpServletRequest request) throws IOException{
        logger.debug("registerCardImage", CardController.class);

        int statusOfResult = 0;
		CardInfo cardInfo = null;
		UserInfo userInfo = null;
		CompanyInfo companyInfo = null;
		
        try {
        	List<CardInfoVO> cardsVO=Cards.getListCards().stream().map(x->x.getCard()).collect(Collectors.toList());
        	for (CardInfoVO cardInfoVo : cardsVO) {
        		String imageData = cardInfoVo.getImageFile();
				String imageFile = imageData.substring(imageData.indexOf(',') + 1);
				if(imageFile.length()>0){
					cardInfo = new CardInfo();
					Integer userId = cardInfoVo.getUserId();
					if (userId == null) {
						statusOfResult = 2;
						break;
					}
					
					userInfo = userInfoService.getUserInfoByUserId(userId);
		            //Set id for user login
		            cardInfo.setCardOwnerId(userId);
		            cardInfo.setOperaterId(userId);
		            companyInfo = new CompanyInfo();
		            companyInfo.setCompanyId(0);
		           
		            cardInfo.setGroupCompanyId(userInfo.getGroupCompanyId());
		            cardInfo.setCompanyInfo(companyInfo);
		            cardInfo.setCreateDate(new Date());
		            cardInfo.setUpdateDate(new Date());
		            cardInfo.setApprovalStatus(2);
		            
		            cardInfo.setContactDate(new Date());
					cardInfo.setNewestCardFlg(0);
					
		            CardInfo cardInfoObject = cardInfoService.registerCardImageOfAdmin(cardInfo);
		            FileUploadModel fileUploadModel=UploadFileUtil.uploadImageDefault(imageFile, cardInfoObject.getImageFile(), this.scpHostName, this.scpUser, this.scpPassword,0); 
		            if(!fileUploadModel.isStatus()){
		            	statusOfResult=UploadFileUtil.writeLostImage(imageFile, cardInfoObject.getImageFile());
		            	if(statusOfResult==3){
		            		cardInfoService.deleteCardInfo(cardInfoObject.getCardId());
		            		return statusOfResult;
		            	}
					}
		            
		            PossessionCardId possessionCardId = new PossessionCardId();
		            possessionCardId.setCardId(cardInfoObject.getCardId());
		            possessionCardId.setUserId(userId);
		            possessionCardId.setContactDate(new Date());
		            possessionCardId.setCreateDate(new Date());
		            
		            PossessionCard posCard = new PossessionCard();
		            posCard.setId(possessionCardId);
		            posCard.setCardInfo(cardInfoObject);
		            possessionCardService.registerPosCard(posCard);   
		            
					// Create new thread to processing card image
					OcrProcessCardThread newThread = new OcrProcessCardThread(userId, cardInfoObject.getCardId(), imageFile,cardInfoObject);
					newThread.start();
				}
        		
        	}
            
        } catch(Exception ex) {	          
        	return 1;
        }
        return 0;	       
    }
		
	private int parseIntParameter(String parameter, int defaultValue) {
		try {
			return Integer.parseInt(parameter);
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}
	
	private List<Integer> listPermissionViewCardWithUser(String role){
		List<Integer> listStatus = new ArrayList<Integer>();
		Integer[] otherList;
		if (role.equals(RoleType.ROLE_OPERATOR.name())){
			otherList = new Integer[] {2,3,4,5};
			listStatus.addAll(Arrays.asList(otherList));
		} else {
			otherList = new Integer[] {1,2,3,4,5};
			listStatus.addAll(Arrays.asList(otherList));
		}
		return listStatus;
	}
	
	private String getCardImageFile(String data){
		return data.substring(data.indexOf(",") + 1);
	}
	
	@RequestMapping("updateIsEditting")
	@ResponseBody
	public int updateIsEditting(@RequestParam(value = "Id") int Id){
		try {
			CardInfo cardInfo=cardInfoService.getCardInfoDetail(Id);
			cardInfo.setIsEditting(0);
			cardInfo.setDateEditting(new Date());
			cardInfoService.editCardInfo(cardInfo);
		} catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
	
	
	private void pushNoticeConnectUser(Integer cardOwnerId,Integer cardId,String msg, Integer type){
		PushInfoId pushInfoId = userInfoService.getPushNotification(cardOwnerId);
		
		if (pushInfoId.getDeviceToken() != "" && cardOwnerId != null) {
			try {
				
				//ResponseEntity<String> result = null;
				PushInfoId pushInfoID = new PushInfoId();
				UserInfo userInfoPush = new UserInfo();
				userInfoPush.setUserId(cardOwnerId);
				pushInfoID = userInfoService.getPushNotification(cardOwnerId);
				RestTemplate restTemplate = new RestTemplate();
				
				
				
				String appId = null;
				Integer badge = 1;
				String title = "Approval this card";
				String deviceToken = pushInfoID.getDeviceToken();

				if (pushInfoID.getDeviceType().toLowerCase().equals("android")) {
					appId = AppIdContants.ANDROID_APP_ID;
				} else {
					appId = AppIdContants.IOS_APP_ID;
				}
				
				com.ecard.core.vo.PushNotification pushNotification = new PushNotification();
				pushNotification.setAppId(appId);
				pushNotification.setBadge(1);
				pushNotification.setDeviceToken(deviceToken);
				pushNotification.setTitle(title);
				pushNotification.setAlert(msg);
				
				String result = restTemplate.postForObject(this.pushApi, pushNotification, String.class);
				System.out.println("CardOwnerID = "+cardOwnerId +",TOKEN = "+pushInfoId.getDeviceToken()+", type = "+pushInfoId.getDeviceType()+ ", userId="+pushInfoId.getUserId()+" ,AppID ="+appId);
				System.out.println("RESULT = "+ result);
				/*String jsonStr = " {" 
						+"\"title\":\"" + title + "\"," 
						+"\"type\":\"Simple\"," 
						+"\"alert\":\"" + msg + "\"," 
						+ "\"audience\":{\"app\": [\"" + appId + "\"], \"uuid\": [\"" + deviceToken + "\"]},"
						+ "\"throttle\":0," 
						+ "\"draft\": false,"
						+ "\"extra\": {\"user\": {\"sdk_notify_pattern\": -1} ,\"option\":{\"notification_type\": \"simple\"}, \"aps\": {\"badge\":"+ badge + ", \"content-available\": 1}}}";
				JSONParser parser = new JSONParser();
				JSONObject json = null;
				try {
					json = (JSONObject) parser.parse(jsonStr);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				System.out.println("JSON PUSH NOTIFICATION = "+json);
				String uri = "http://api.livepasspush.com/api/msgs/";
				RestTemplate restTemplate = new RestTemplate();
				HttpHeaders headers = new HttpHeaders();
				headers.set("Authorization", "authkey 8hxoez1oas4l25wst7mw4x46lhx0hr1fb226sd22cqdpa50k; context=363b5a7b-2a66-431b-b269-ddea3037d657");
				headers.setContentType(MediaType.APPLICATION_JSON);
				HttpEntity entity = new HttpEntity(json, headers);
				String result = restTemplate.postForObject(uri, entity, String.class);
				System.out.println("Result for PUSH = "+ result);*/
				/*if(!result.getStatusCode().is2xxSuccessful()){
					System.out.println("Push Notification error :"+ result.toString());
				}*/				
			} catch (Exception ex) {
				StringWriter errors = new StringWriter();
				ex.printStackTrace(new PrintWriter(errors));				
				System.out.println("Push Notification error :"+ errors.toString());
			}
		}
	}
	
	/**
	 * New thread class to process card image via OCR web service.
	 * 
	 * @author nhat.nguyen
	 */
	class OcrProcessCardThread extends Thread {
		private Integer userId;
		private Integer cardId;
		private String cardData;
		private CardInfo card;

		public OcrProcessCardThread(Integer userId, Integer cardId, String cardData, CardInfo card) {
			this.userId = userId;
			this.cardId = cardId;
			this.cardData = cardData;
			this.card = card;
		}

		public void run() {
			if (StringUtils.isEmpty(this.cardData)) {
				return;
			}
			logger.info("Thread start processing card image for cardId " + this.cardId);
			try {
				// get card information via web service
	    		Status status = ocrCardImageService.processCardImageDetail(this.userId, this.cardId, this.cardData,this.card);
				if (status != null && status.getCode() != 0){
					logger.error("Error read card information from OCR service: " + status.getErrorMess());
				}
			} catch (Exception ex) {
				logger.error("Error processing card image: " + ex.getMessage());
			}
		}
	}
}
