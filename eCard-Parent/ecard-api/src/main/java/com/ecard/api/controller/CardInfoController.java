/*
 * CardInfoController class
 */
package com.ecard.api.controller;

import com.ecard.api.controller.handler.RestExceptionHandler;
import com.ecard.api.controller.helper.Constants;
import com.ecard.api.controller.helper.FileUploadModel;
import com.ecard.api.controller.helper.UploadFileUtil;
import com.ecard.core.datasource.SchemaContextHolder;
import com.ecard.core.datasource.type.SchemaType;
import com.ecard.core.model.AutoLogin;
import com.ecard.core.model.CardInfo;
import com.ecard.core.model.CompanyInfo;
import com.ecard.core.model.PossessionCard;
import com.ecard.core.model.PossessionCardId;
import com.ecard.core.model.UserInfo;
import com.ecard.core.service.CardInfoService;
import com.ecard.core.service.CardUpdateHistoryService;
import com.ecard.core.service.HomeService;
import com.ecard.core.service.MyCardService;
import com.ecard.core.service.OcrCardImageService;
import com.ecard.core.service.PossessionCardService;
import com.ecard.core.service.UserInfoService;
import com.ecard.core.service.UserNotifyService;
import com.ecard.core.service.converter.CardInfoConverter;
import com.ecard.core.service.converter.CardInfoDetailConverter;
import com.ecard.core.service.converter.UserInfoConverter;
import com.ecard.core.vo.CardConnectModel;
import com.ecard.core.vo.CardConnectResponse;
import com.ecard.core.vo.CardImage;
import com.ecard.core.vo.CardImageResponse;
import com.ecard.core.vo.CardInfoAndPosCard;
import com.ecard.core.vo.CardInfoConnectUser;
import com.ecard.core.vo.CardInfoConnectUserResponse;
import com.ecard.core.vo.CardInfoDetailResponse;
import com.ecard.core.vo.CardInfoPosCardResponse;
import com.ecard.core.vo.CardInfoResponse;
import com.ecard.core.vo.CompanyCardListCount;
import com.ecard.core.vo.CompanyCardListCountResponse;
import com.ecard.core.vo.CompanyCardModel;
import com.ecard.core.vo.CompanyCardModelResponse;
import com.ecard.core.vo.MyCardAndCardInfo;
import com.ecard.core.vo.MyCardAndCardInfoResponse;
import com.ecard.core.vo.StatusInfo;
import com.ecard.core.webservice.Status;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author vinhla
 */
@RestController
@RequestMapping(value = "/api")
public class CardInfoController extends RestExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(CardInfoController.class);
    
    private static final String HEADER_TOKEN = "token";
    
    @Autowired
    MyCardService myCardService;
    
    @Autowired
    CardUpdateHistoryService cardUpdateHistoryService;
    
    @Autowired
    OcrCardImageService ocrCardImageService;
    
    @Autowired
    UserInfoService userInfoService;
    
    @Autowired
    CardInfoService cardInfoService;
    
    @Autowired
    PossessionCardService possessionCardService;
    
    @Autowired
    UserNotifyService userNotifyService;
    
    @Autowired
    private HomeService homeService;
    
    @Value("${msg.list.card.success}")
    private String msgListCardSuccess;
    
    @Value("${msg.no.content}")
    private String msgNoContent;
    
    @Value("${msg.token.invalid}")
    private String msgTokenInvalid;
    
    @Value("${msg.token.not.null}")
    private String msgTokenNotNull;
    
    @Value("${msg.userId.not.null}")
    private String msgUserIdNotNull;
    
    @Value("${msg.register.card.success}")
    private String msgRegisterCardSuccess;
    
    @Value("${msg.register.card.failed}")
    private String msgRegisterCardFailed;
    
    @Value("${msg.list.connect.user.success}")
    private String msgListConnectUserSuccess;
    
    @Value("${msg.list.company.success}")
    private String msgListCompanySuccess;
    
    @Value("${msg.list.company.card.success}")
    private String msgListCompanyCardSuccess;
    
    @Value("${msg.upload.image.failed}")
    private String msgUploadImageFailed;
    
    @Value("${msg.delete.card.success}")
    private String msgDeleteCardSucess;
    
    @Value("${msg.delete.card.failed}")
    private String msgDeleteCardFailed;
    
    @Value("${msg.get.image.card.success}")
    private String msgGetImageCardSuccess;
    
    @Value("${msg.no.user.name}")
    private String msgNoUserFound;
    
    @Value("${scp.hostname}")
    private String scpHostName;
    
    @Value("${scp.user}")
    private String scpUser;
    
    @Value("${scp.password}")
    private String scpPassword;
    
    @Value("${scp.port}")
    private String scpPort;
    
    @RequestMapping(value = "/listCardRecent", method = RequestMethod.GET)
    public CardInfoResponse listCardRecent(HttpServletRequest request) 
            throws IllegalAccessException, InvocationTargetException, DataIntegrityViolationException {
        logger.debug("listCardRecent", CardInfoController.class);
        CardInfoResponse cardInfoResponse = new CardInfoResponse();
        StatusInfo statusInfo = null;
        SchemaContextHolder.setSchemaType(SchemaType.USER);
        String token = request.getHeader(HEADER_TOKEN);
        
        Boolean checkResult = userInfoService.checkToken(token);
        if (!checkResult){
            cardInfoResponse.setStatusInfo(new StatusInfo(Constants.ERROR, Constants.UNAUTHORIZED, this.msgTokenInvalid, token));
            return cardInfoResponse;
        }
        
        AutoLogin autoLogin = userInfoService.findByToken(token);
        //Get userId nu token
        Integer userId = autoLogin.getUserInfo().getUserId();

        try {
            List<com.ecard.core.vo.CardInfo> listCards = myCardService.listCardRecent(userId);

            if(listCards.size() > 0) {
                cardInfoResponse.setListCardInfo(listCards);
                statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, this.msgListCardSuccess, token);
            }
            else{
                statusInfo = new StatusInfo(Constants.SUCCESS, Constants.NO_CONTENT, this.msgNoContent, token);
            }
        }
        catch(Exception ex) {
            statusInfo = new StatusInfo(Constants.ERROR, Constants.SERVER_ERROR, ex.getMessage(), token);
        }
        cardInfoResponse.setStatusInfo(statusInfo);
        return cardInfoResponse;
    }
    
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public CardInfoResponse listAllCard(@RequestParam String sort, HttpServletRequest request) throws IllegalAccessException, InvocationTargetException {
        logger.debug("listAllCard", CardInfoController.class);
        SchemaContextHolder.setSchemaType(SchemaType.USER);
        
        StatusInfo statusInfo = null;
        CardInfoResponse cardInfoResponse = new CardInfoResponse();
        String token = request.getHeader(HEADER_TOKEN);        
        Boolean checkResult = userInfoService.checkToken(token);
        if (!checkResult){
            cardInfoResponse.setStatusInfo(new StatusInfo(Constants.ERROR, Constants.UNAUTHORIZED, this.msgTokenInvalid, token));
            return cardInfoResponse;
        }

        List<com.ecard.core.model.CardInfo> listCards = cardUpdateHistoryService.listAllCardHistory(sort);

        if(listCards.size() > 0){
            cardInfoResponse.setListCardInfo(CardInfoConverter.convertCardInforList(listCards));
            statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, this.msgListCardSuccess, token);            
        }
        else{
        	statusInfo = new StatusInfo(Constants.SUCCESS, Constants.NO_CONTENT, this.msgNoContent, token);
        }
        
        cardInfoResponse.setStatusInfo(statusInfo);        
        return cardInfoResponse;
    }
    
    @RequestMapping(value = "/listMyCard", method = RequestMethod.GET)
    public MyCardAndCardInfoResponse listAllMyCard(HttpServletRequest request) throws IllegalAccessException, InvocationTargetException {
        logger.debug("listAllMyCard : ", CardInfoController.class);
        
        SchemaContextHolder.setSchemaType(SchemaType.USER);        
        MyCardAndCardInfoResponse myCardInfoResponse = new MyCardAndCardInfoResponse();        
        StatusInfo statusInfo = null;
        SchemaContextHolder.setSchemaType(SchemaType.USER);
        String token = request.getHeader(HEADER_TOKEN);
        Boolean checkResult = userInfoService.checkToken(token);
        if (!checkResult){
        	myCardInfoResponse.setStatusInfo(new StatusInfo(Constants.ERROR, Constants.UNAUTHORIZED, this.msgTokenInvalid, token));
            return myCardInfoResponse;
        }
        
        AutoLogin autoLogin = userInfoService.findByToken(token);
        //Get userId nu token
        Integer userId = autoLogin.getUserInfo().getUserId();
                
        try{
            List<MyCardAndCardInfo> listCards = myCardService.listAllMyCard(userId);
            //myCardInfoResponse.setCardList(CardInfoConverter.convertMyCardCardInforList(listCards));            
            myCardInfoResponse.setCardList(listCards);
            statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, this.msgListCardSuccess, token);
        }
        catch(Exception ex){
        	statusInfo = new StatusInfo(Constants.ERROR, Constants.SERVER_ERROR, ex.getMessage(), token);
        }
        
        myCardInfoResponse.setStatusInfo(statusInfo);
        return myCardInfoResponse;
    }
    
    @RequestMapping(value = "/registerCard", method = RequestMethod.POST)
    public StatusInfo registerCard(@RequestBody CardInfo cardInfo, HttpServletRequest request) throws IOException {
        logger.debug("authenticationUser :", CardInfoController.class);
        
        SchemaContextHolder.setSchemaType(SchemaType.USER);
        StatusInfo statusInfo = null;
        String token = request.getHeader(HEADER_TOKEN);
        
        //CardInfoResponse cardInfoResponse = new CardInfoResponse();
        Boolean checkResult = userInfoService.checkToken(token);
        if (!checkResult){
        	statusInfo = new StatusInfo(Constants.ERROR, Constants.UNAUTHORIZED, this.msgTokenInvalid, token);
            return statusInfo;
        }
        
        
        try {
        	AutoLogin autoLogin = userInfoService.findByToken(token);
            Integer userId = autoLogin.getUserInfo().getUserId();
            UserInfo userInfo = userInfoService.getUserInfoByUserId(userId);
            //Check possesionCard
            List<PossessionCard> listPosCard = possessionCardService.getListPossessionCardById(userId, cardInfo.getCardId());
            if(listPosCard.size() > 0) {
                SchemaContextHolder.setSchemaType(SchemaType.MANAGER);
                
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
                                                
                CardInfo cardInfoDetail = cardInfoService.getCardInfoDetail(cardInfo.getCardId());
                
                CompanyInfo companyInfo = new CompanyInfo();
                companyInfo.setCompanyId(0);                
                cardInfo.setCompanyInfo(companyInfo);
                cardInfo.setGroupCompanyId(userInfo.getGroupCompanyId());
                cardInfo.setApprovalStatus(cardInfoDetail.getApprovalStatus());
                cardInfo.setNewestCardFlg(cardInfoDetail.getNewestCardFlg());
                
                if(cardInfoService.editCardInfo(cardInfo)) {
                    
                    /*UserInfo userInfo = new UserInfo();
                    userInfo.setUserId(userId);
                    
                    UserNotification userNotification = new UserNotification();
                    userNotification.setCardId(cardInfo.getCardId());
                    userNotification.setUserInfo(userInfo);
                    userNotification.setNoticeType(NoticeType.REGISTRATION_CARD_CHANGE.getValue());
                    userNotification.setChangeParamType(0);
                    userNotification.setReadFlg(0);
                    userNotification.setNotifyMessage("");
                    userNotification.setNoticeDate(new Date());
                    
                    userNotifyService.createUserNotify(userNotification);*/
                    statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, this.msgRegisterCardSuccess, token);
                }
            }
            else{
            	statusInfo = new StatusInfo(Constants.ERROR, Constants.SERVER_ERROR, this.msgRegisterCardFailed, token);
            }
        }
        catch(Exception ex){
        	statusInfo = new StatusInfo(Constants.ERROR, Constants.SERVER_ERROR, ex.getMessage(), token);
        }
        //cardInfoResponse.setStatusInfo(statusInfo);
        return statusInfo;
    }
    
    @RequestMapping(value = "/getCardInfo", method = RequestMethod.GET)
    public CardInfoDetailResponse getCardInfo(@RequestParam Integer cardId, HttpServletRequest request) {
        logger.debug("getCardInfo", CardInfoController.class);
        SchemaContextHolder.setSchemaType(SchemaType.USER);
        
        CardInfoDetailResponse cardInfoDetailResponse = new CardInfoDetailResponse();
        
        StatusInfo statusInfo = null;
        //CardInfoResponse cardInfoResponse = new CardInfoResponse();
        String token = request.getHeader(HEADER_TOKEN);        
        Boolean checkResult = userInfoService.checkToken(token);
        if (!checkResult){
            cardInfoDetailResponse.setStatusInfo(new StatusInfo(Constants.ERROR, Constants.UNAUTHORIZED, this.msgTokenInvalid, token));
            return cardInfoDetailResponse;
        }
//        
//        Boolean tokenResponse = userInfoService.checkToken(request.getHeader(HEADER_TOKEN));
//        if(tokenResponse == true){
//            token = userInfoService.findByToken(request.getHeader(HEADER_TOKEN));
//        } else {            
//            statusInfo.setStatus(1);
//            statusInfo.setCode("401");
//            statusInfo.setMsg(this.msgTokenInvalid);
//            statusInfo.setToken(token.getToken());
//            cardInfoDetailResponse.setStatusInfo(statusInfo);
//            return cardInfoDetailResponse;
//        }
        
        try{
            CardInfo cardInfo = cardInfoService.getCardInfoDetail(cardId);
            AutoLogin autoLogin = userInfoService.findByToken(token);
            Integer userId = autoLogin.getUserInfo().getUserId();
            cardInfoService.registerPrusalHistory(userId,cardId);
            
            UserInfo userInfo = new UserInfo();
            userInfo = userInfoService.getUserInfoByUserId(userId);
            
            if(cardInfo.getCardOwnerId() != null && cardInfo.getCardOwnerId() != 0){
                userInfo = userInfoService.getUserInfoByUserId(cardInfo.getCardOwnerId());
                if(userInfo != null){
                	cardInfoDetailResponse.setOwnerInfoDetail(UserInfoConverter.convertUserInforDetailList(userInfo));
                }
            } else {
                Integer userOwnerId = possessionCardService.getUserIdByCardId(cardId);                                
                if(userOwnerId == 0){                    
                    cardInfo.setCardOwnerId(0);
                } else {
                    userInfo = userInfoService.getUserInfoByUserId(userOwnerId);
                    cardInfo.setCardOwnerId(userOwnerId);
                }
            }
            
            //Check user same group company
            if(userInfo != null){
	            if(cardInfo.getGroupCompanyId() == userInfo.getGroupCompanyId()){
	                cardInfoDetailResponse.setCardInfoDetail(CardInfoDetailConverter.convertCardInforDetail(cardInfo, Boolean.TRUE));
	            }
	            else{
	                cardInfoDetailResponse.setCardInfoDetail(CardInfoDetailConverter.convertCardInforDetail(cardInfo, Boolean.FALSE));
	            }
            }
            else{
                cardInfoDetailResponse.setCardInfoDetail(CardInfoDetailConverter.convertCardInforDetail(cardInfo, Boolean.FALSE));
            }
            
            cardInfoDetailResponse.setOwnerInfoDetail(UserInfoConverter.convertUserInforDetailList(userInfo));
            statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, this.msgListCardSuccess, token);
        }
        catch(Exception ex){
        	statusInfo = new StatusInfo(Constants.ERROR, Constants.SERVER_ERROR, ex.getMessage(), token);
        }
        cardInfoDetailResponse.setStatusInfo(statusInfo);
        return cardInfoDetailResponse;
    }
    
    @RequestMapping(value = "/listConnectUser", method = RequestMethod.GET)
    public CardInfoConnectUserResponse listConnectUser(@RequestParam Integer recentFlg, @RequestParam(required = false) Integer page, HttpServletRequest request) {
        logger.debug("listConnectUser", CardInfoController.class);
        SchemaContextHolder.setSchemaType(SchemaType.USER);
        //Validate token
        CardInfoConnectUserResponse response = new CardInfoConnectUserResponse();
        
        StatusInfo statusInfo = null;        
        String token = request.getHeader(HEADER_TOKEN);        
        Boolean checkResult = userInfoService.checkToken(token);
        if (!checkResult){
        	response.setStatusInfo(new StatusInfo(Constants.ERROR, Constants.UNAUTHORIZED, this.msgTokenInvalid, token));
            return response;
        }
        
        if(page == null){
            page = 0;
        }
        
        try {        	
            AutoLogin autoLogin = userInfoService.findByToken(token);
            Integer userId = autoLogin.getUserInfo().getUserId();
            //UserInfo userInfo = userInfoService.getUserInfoByUserId(userId);
            //List<CardInfoConnectUser> cardInfo = cardInfoService.listConnectUser(userId, recentFlg);
            List<CardInfoConnectUser> cardInfo = cardInfoService.listConnectUser(userId, recentFlg, page);
            
            response.setCardList(CardInfoConverter.convertConnectCardList(cardInfo));
            if(cardInfo.size() <= 0){
            	statusInfo = new StatusInfo(Constants.SUCCESS, Constants.NO_CONTENT, this.msgNoContent, token);
            } else {
            	statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, this.msgListConnectUserSuccess, token);
            }            
        }
        catch(Exception ex) {
        	statusInfo = new StatusInfo(Constants.ERROR, Constants.SERVER_ERROR, ex.getMessage(), token);
        }
        response.setStatusInfo(statusInfo);
        return response;
    }
    
    /**
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/listCompany", method = RequestMethod.GET)
    public CompanyCardListCountResponse listCompany(HttpServletRequest request) {
        logger.debug("listCompany", CardInfoController.class);
        SchemaContextHolder.setSchemaType(SchemaType.USER);
        
        CompanyCardListCountResponse response = new CompanyCardListCountResponse();
        //Validate token
        StatusInfo statusInfo = null;        
        String token = request.getHeader(HEADER_TOKEN);        
        Boolean checkResult = userInfoService.checkToken(token);
        if (!checkResult){
        	response.setStatusInfo(new StatusInfo(Constants.ERROR, Constants.UNAUTHORIZED, this.msgTokenInvalid, token));
            return response;
        }
        
        AutoLogin autoLogin = userInfoService.findByToken(token);
        Integer userId = autoLogin.getUserInfo().getUserId();
        
        try {
            //Get roles
            //Roles roles = userInfoService.findRoleByUserId(userId);
            List<Integer> permissionType = userInfoService.getPermisionTypeByUserId(userId);
            
            List<CompanyCardListCount> cardInfo = cardInfoService.listCompany(permissionType.get(0).toString(), userId);
            response.setCompanyList(CardInfoConverter.convertCardInforCompanyList(cardInfo));
            statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, this.msgListCompanySuccess, token);
        }
        catch(Exception ex) {
        	statusInfo = new StatusInfo(Constants.ERROR, Constants.SERVER_ERROR, ex.getMessage(), token);
        }
        response.setStatusInfo(statusInfo);
        return response;
    }
    
    @RequestMapping(value = "/listCompanyCard", method = RequestMethod.GET)
    public CompanyCardModelResponse listCompanyCard(@RequestParam String companyName, HttpServletRequest request) {
        logger.debug("listCompanyCard", CardInfoController.class);
        SchemaContextHolder.setSchemaType(SchemaType.USER);
        //Validate token        
        CompanyCardModelResponse cardInfoResponse = new CompanyCardModelResponse();
        StatusInfo statusInfo = null;        
        String token = request.getHeader(HEADER_TOKEN);        
        Boolean checkResult = userInfoService.checkToken(token);
        if (!checkResult){
        	cardInfoResponse.setStatusInfo(new StatusInfo(Constants.ERROR, Constants.UNAUTHORIZED, this.msgTokenInvalid, token));
            return cardInfoResponse;
        }
        
        AutoLogin autoLogin = userInfoService.findByToken(token);
        Integer userId = autoLogin.getUserInfo().getUserId();     
        try {
            //Get roles
            //Roles roles = userInfoService.findRoleByUserId(userId);
            List<Integer> permissionType = userInfoService.getPermisionTypeByUserId(userId);
            
            List<CompanyCardModel> cardInfo = cardInfoService.listCardWithCompany(permissionType.get(0).toString(), userId, companyName);
            cardInfoResponse.setCardList(CardInfoConverter.convertCompanyList(cardInfo));
            statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, this.msgListCompanyCardSuccess, token);            
        }
        catch(Exception ex) {
        	statusInfo = new StatusInfo(Constants.ERROR, Constants.SERVER_ERROR, ex.getMessage(), token);
        }
        cardInfoResponse.setStatusInfo(statusInfo);
        return cardInfoResponse;
    }
    
    @RequestMapping(value = "/getListPossesionCard", method = RequestMethod.GET)
    public CardInfoResponse getListPossesionCard(@RequestParam(required = false) String searchText, @RequestParam String sort, HttpServletRequest request, @RequestParam int page) {
        logger.debug("getListPossesionCard", CardInfoController.class);
        SchemaContextHolder.setSchemaType(SchemaType.USER);
        //Validate token        
        CardInfoResponse cardInfoResponse = new CardInfoResponse();
        StatusInfo statusInfo = null;        
        String token = request.getHeader(HEADER_TOKEN);        
        Boolean checkResult = userInfoService.checkToken(token);
        if (!checkResult){
        	cardInfoResponse.setStatusInfo(new StatusInfo(Constants.ERROR, Constants.UNAUTHORIZED, this.msgTokenInvalid, token));
            return cardInfoResponse;
        }
        
        AutoLogin autoLogin = userInfoService.findByToken(token);
        Integer userId = autoLogin.getUserInfo().getUserId();

        try{
            List<CardInfo> cardInfo = cardInfoService.getListPossesionCard(userId, searchText, sort, page);
            cardInfoResponse.setListCardInfo(CardInfoConverter.convertCardInforList(cardInfo));
            statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, this.msgListCardSuccess, token);
        }
        catch(Exception ex){
        	statusInfo = new StatusInfo(Constants.ERROR, Constants.SERVER_ERROR, ex.getMessage(), token);            
        }
        cardInfoResponse.setStatusInfo(statusInfo);
        return cardInfoResponse;
    }
    
    @RequestMapping(value = "/getListCardSearch", method = RequestMethod.GET) 
    public CardInfoResponse getListCardSearch(@RequestParam int page, @RequestParam(required = false) String name,
            @RequestParam(required = false) String position,
            @RequestParam(required = false) String department,
            @RequestParam(required = false) String company,
            @RequestParam(required = false) String searchText, HttpServletRequest request) {
        logger.debug("getListPossesionCard", CardInfoController.class);
        
        SchemaContextHolder.setSchemaType(SchemaType.USER);
        CardInfoResponse cardInfoResponse = new CardInfoResponse();
        //Validate token                
        StatusInfo statusInfo = null;        
        String token = request.getHeader(HEADER_TOKEN);        
        Boolean checkResult = userInfoService.checkToken(token);
        if (!checkResult){
        	cardInfoResponse.setStatusInfo(new StatusInfo(Constants.ERROR, Constants.UNAUTHORIZED, this.msgTokenInvalid, token));
            return cardInfoResponse;
        }
        
        AutoLogin autoLogin = userInfoService.findByToken(token);
        Integer userId = autoLogin.getUserInfo().getUserId();
        
        try{
            UserInfo userInfo = userInfoService.getUserInfoByUserId(userId);
            
            List<com.ecard.core.vo.CardInfo> cardInfo = cardInfoService.getListCardSearch(userId, searchText, name, position, department, company, page, userInfo.getGroupCompanyId());           
            BigInteger totalRecord = cardInfoService.getTotalCardSearch(userId, searchText, name, position, department, company, userInfo.getGroupCompanyId());
            
            cardInfoResponse.setListCardInfo(CardInfoConverter.convertCardInforListMroonga(cardInfo));
            cardInfoResponse.setTotalRecord(Long.parseLong(totalRecord.toString()));
            statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, this.msgListCardSuccess, token);            
        }
        catch(Exception ex){
        	statusInfo = new StatusInfo(Constants.ERROR, Constants.SERVER_ERROR, ex.getMessage(), token);
        }
        cardInfoResponse.setStatusInfo(statusInfo);
        return cardInfoResponse;
    }
    
    @RequestMapping(value = "/getListCardSearchAll", method = RequestMethod.GET)
    public CardInfoResponse getListCardSearchAll(@RequestParam int page, @RequestParam(required = false) String owner,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String position,
            @RequestParam(required = false) String department,
            @RequestParam(required = false) String company,
            @RequestParam(required = false) String searchText, HttpServletRequest request) {
        logger.debug("getListCardSearchAll", CardInfoController.class);
        
        SchemaContextHolder.setSchemaType(SchemaType.USER);
        CardInfoResponse cardInfoResponse = new CardInfoResponse();
        //Validate token        
        StatusInfo statusInfo = null;        
        String token = request.getHeader(HEADER_TOKEN);        
        Boolean checkResult = userInfoService.checkToken(token);
        if (!checkResult){
        	cardInfoResponse.setStatusInfo(new StatusInfo(Constants.ERROR, Constants.UNAUTHORIZED, this.msgTokenInvalid, token));
            return cardInfoResponse;
        }
        
        AutoLogin autoLogin = userInfoService.findByToken(token);
        Integer userId = autoLogin.getUserInfo().getUserId();

        
        try{
            UserInfo userInfo = userInfoService.getUserInfoByUserId(userId);
            
            List<com.ecard.core.vo.CardInfo> cardInfo = cardInfoService.getListCardSearchAll(owner, searchText, name, position, department, company, page, userInfo.getGroupCompanyId());           
            BigInteger totalRecord = cardInfoService.getTotalCardSearchAll(owner, searchText, name, position, department, company, page, userInfo.getGroupCompanyId());
            cardInfoResponse.setListCardInfo(CardInfoConverter.convertCardInforListMroonga(cardInfo));
            cardInfoResponse.setTotalRecord(Long.parseLong(totalRecord.toString()));
            statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, this.msgListCardSuccess, token);
        }
        catch(Exception ex){
        	statusInfo = new StatusInfo(Constants.ERROR, Constants.SERVER_ERROR, ex.getMessage(), token);            
        }
        cardInfoResponse.setStatusInfo(statusInfo);
        return cardInfoResponse;
    }
    
    /**
     * 
     * @param request
     * @return 
     */
    @RequestMapping(value = "/listCardPending", method = RequestMethod.GET)
    public CardInfoPosCardResponse listCardPending (HttpServletRequest request){
        SchemaContextHolder.setSchemaType(SchemaType.USER);
        
       //Validate token        
        CardInfoPosCardResponse cardInfoResponse = new CardInfoPosCardResponse();
        StatusInfo statusInfo = null;        
        String token = request.getHeader(HEADER_TOKEN);        
        Boolean checkResult = userInfoService.checkToken(token);
        if (!checkResult){
        	cardInfoResponse.setStatusInfo(new StatusInfo(Constants.ERROR, Constants.UNAUTHORIZED, this.msgTokenInvalid, token));
            return cardInfoResponse;
        }
        
        AutoLogin autoLogin = userInfoService.findByToken(token);
        Integer userId = autoLogin.getUserInfo().getUserId();

        try {
            List<CardInfoAndPosCard> cardInfo = cardInfoService.listCardPending(userId);
            cardInfoResponse.setCardList(cardInfo);
            statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, this.msgListCardSuccess, token);                        
        } catch (Exception ex) {
        	statusInfo = new StatusInfo(Constants.ERROR, Constants.SERVER_ERROR, ex.getMessage(), token);
        }
        cardInfoResponse.setStatusInfo(statusInfo);
        return cardInfoResponse;
    }
    
    @RequestMapping(value = "/getListPossesionCardRecent", method = RequestMethod.GET)
    public CardInfoResponse getListPossesionCardRecent(HttpServletRequest request) {
        logger.debug("getListPossesionCardRecent", CardInfoController.class);
        SchemaContextHolder.setSchemaType(SchemaType.USER);
        CardInfoResponse cardInfoResponse = new CardInfoResponse();
        //Validate token
        StatusInfo statusInfo = null;        
        String token = request.getHeader(HEADER_TOKEN);        
        Boolean checkResult = userInfoService.checkToken(token);
        if (!checkResult){
        	cardInfoResponse.setStatusInfo(new StatusInfo(Constants.ERROR, Constants.UNAUTHORIZED, this.msgTokenInvalid, token));
            return cardInfoResponse;
        }
        
        AutoLogin autoLogin = userInfoService.findByToken(token);
        Integer userId = autoLogin.getUserInfo().getUserId();
        
        try{
            List<com.ecard.core.vo.CardInfo> cardInfo = cardInfoService.getListPossesionCardRecent(userId);
            cardInfoResponse.setListCardInfo(CardInfoConverter.convertCardRecentList(cardInfo));
            cardInfoResponse.setTotalRecord(homeService.countRecentPossessionCard(userId).longValue());
            statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, this.msgListCardSuccess, token);            
        }
        catch(Exception ex){
        	statusInfo = new StatusInfo(Constants.ERROR, Constants.SERVER_ERROR, ex.getMessage(), token);            
        }
        cardInfoResponse.setStatusInfo(statusInfo);
        return cardInfoResponse;
    }
    
    @RequestMapping(value = "/registerCardImage", method = RequestMethod.POST)
    public StatusInfo registerCardImage(@RequestBody CardInfo cardInfo, HttpServletRequest request) throws IOException{
        logger.debug("registerCardImage", CardInfoController.class);
        
        SchemaContextHolder.setSchemaType(SchemaType.USER);
        
        //Validate token
        StatusInfo statusInfo = null;        
        String token = request.getHeader(HEADER_TOKEN);        
        Boolean checkResult = userInfoService.checkToken(token);
        if (!checkResult){
        	statusInfo = new StatusInfo(Constants.ERROR, Constants.UNAUTHORIZED, this.msgTokenInvalid, token);
            return statusInfo;
        }
        
        AutoLogin autoLogin = userInfoService.findByToken(token);
        Integer userId = autoLogin.getUserInfo().getUserId();  
        Validate.notNull(userId, this.msgUserIdNotNull);
        
        UploadFileUtil uploadFile = new UploadFileUtil();
        String imageData = cardInfo.getImageFile();
        
        try {
            	UserInfo userInfo = userInfoService.getUserInfoByUserId(userId);
            
           
                SchemaContextHolder.setSchemaType(SchemaType.MANAGER);
                
                //Set id for user login
                cardInfo.setCardOwnerId(userId);
                cardInfo.setCardOwnerName(userInfo.getName());
                cardInfo.setOperaterId(userId);
                //cardInfo.setImageFile(uploadModel.getFileName());
                
                CompanyInfo companyInfo = new CompanyInfo();
                companyInfo.setCompanyId(0);
                
                cardInfo.setCompanyInfo(companyInfo);
                cardInfo.setGroupCompanyId(userInfo.getGroupCompanyId());
                cardInfo.setCreateDate(new Date());
                cardInfo.setUpdateDate(new Date());
                cardInfo.setContactDate(new Date());
                cardInfo.setDeletDate(null);
                cardInfo.setApprovalStatus(2);
                CardInfo cardInfoObject = cardInfoService.registerCardImage(cardInfo);
                uploadFile.uploadImageDefault(imageData, cardInfoObject.getImageFile(), this.scpHostName, this.scpUser, this.scpPassword);      
                PossessionCardId possessionCardId = new PossessionCardId();
                possessionCardId.setCardId(cardInfoObject.getCardId());
                possessionCardId.setUserId(userId);
                possessionCardId.setContactDate(new Date());
                possessionCardId.setCreateDate(new Date());
                
                PossessionCard posCard = new PossessionCard();
                posCard.setId(possessionCardId);
                posCard.setCardInfo(cardInfoObject);
                possessionCardService.registerPosCard(posCard);
                statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, this.msgRegisterCardSuccess, token);
                // Create new thread to processing card image
                OcrProcessCardThread newThread = new OcrProcessCardThread(userId, cardInfoObject.getCardId(), imageData, this.ocrCardImageService);
                newThread.start();
        }
        catch(Exception ex) {
        	statusInfo = new StatusInfo(Constants.ERROR, Constants.SERVER_ERROR, ex.getMessage(), token);            
        }
        return statusInfo;
    }
    
    @RequestMapping(value = "/getCardImage", method = RequestMethod.GET)
    public CardImageResponse getCardImage(@RequestParam Integer cardId, HttpServletRequest request) throws IOException{
        logger.debug("getCardImage", CardInfoController.class);
        
        SchemaContextHolder.setSchemaType(SchemaType.USER);
        
        CardImageResponse response = new CardImageResponse();
        //Validate token
        StatusInfo statusInfo = null;        
        String token = request.getHeader(HEADER_TOKEN);        
        Boolean checkResult = userInfoService.checkToken(token);
        if (!checkResult){
        	response.setStatusInfo(new StatusInfo(Constants.ERROR, Constants.UNAUTHORIZED, this.msgTokenInvalid, token));
            return response;
        }
        
        CardImage cardImage = new CardImage();
        try{
            String fileNameFromDB = cardInfoService.getCardImage(cardId);
            String fileNameFromSCP = UploadFileUtil.getImageFileFromSCP(fileNameFromDB, scpHostName, scpUser, scpPassword, Integer.parseInt(scpPort));
            if(fileNameFromSCP != ""){
                cardImage.setImageFile(fileNameFromSCP);
            }
            
            if(cardImage.getImageFile() != null){
                response.setImageFile(CardInfoConverter.convertCardImage(cardImage));
                statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, this.msgGetImageCardSuccess, token);                
            }
            else{
            	statusInfo = new StatusInfo(Constants.SUCCESS, Constants.NO_CONTENT, this.msgNoContent, token);
            }
        }
        catch(Exception ex){
        	statusInfo = new StatusInfo(Constants.ERROR, Constants.SERVER_ERROR, ex.getMessage(), token);        	            
        }
        response.setStatusInfo(statusInfo);
        return response;
    }
    
    @RequestMapping(value = "/deletePossessionCard", method = RequestMethod.POST)
    public StatusInfo deletePossessionCard(@RequestBody PossessionCardId cardId, HttpServletRequest request) throws IOException{
        logger.debug("deletePossessionCard", CardInfoController.class);
        SchemaContextHolder.setSchemaType(SchemaType.USER);
        //Validate token
        StatusInfo statusInfo = null;        
        String token = request.getHeader(HEADER_TOKEN);        
        Boolean checkResult = userInfoService.checkToken(token);
        if (!checkResult){
        	statusInfo = new StatusInfo(Constants.ERROR, Constants.UNAUTHORIZED, this.msgTokenInvalid, token);
            return statusInfo;
        }
        
        AutoLogin autoLogin = userInfoService.findByToken(token);
        Integer userId = autoLogin.getUserInfo().getUserId();
        Validate.notNull(userId, this.msgUserIdNotNull);
        
        try {
            List<PossessionCard> listPosCard = possessionCardService.getListPossessionCardById(userId, cardId.getCardId());
            
            if(listPosCard.size() > 0){            
                SchemaContextHolder.setSchemaType(SchemaType.MANAGER);
                if(possessionCardService.deletePossessionCard(userId, cardId.getCardId()) != 0){
                	statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, this.msgDeleteCardSucess, token);                    
                }
                else{
                	statusInfo = new StatusInfo(Constants.ERROR, Constants.SERVER_ERROR, this.msgDeleteCardFailed, token);                    
                }
            }
            else{
            	statusInfo = new StatusInfo(Constants.SUCCESS, Constants.NO_CONTENT, this.msgNoContent, token);
            }
        }
        catch(Exception ex) {
        	statusInfo = new StatusInfo(Constants.ERROR, Constants.SERVER_ERROR, this.msgDeleteCardFailed, token);
        }
        return statusInfo;
    }
    
    @RequestMapping(value = "/listCardConnect", method = RequestMethod.GET)
    public CardConnectResponse listCardConnect(@RequestParam Integer cardId, @RequestParam Integer cardOwnerId, HttpServletRequest request) {
        logger.debug("listCardConnect", CardInfoController.class);
        SchemaContextHolder.setSchemaType(SchemaType.USER);
        
        //Validate token        
        CardConnectResponse cardConnectResponse = new CardConnectResponse();
        
        StatusInfo statusInfo = null;        
        String token = request.getHeader(HEADER_TOKEN);        
        Boolean checkResult = userInfoService.checkToken(token);
        if (!checkResult){
        	cardConnectResponse.setStatusInfo(new StatusInfo(Constants.ERROR, Constants.UNAUTHORIZED, this.msgTokenInvalid, token));
            return cardConnectResponse;
        }
        
        try{
            //AutoLogin authToken = userInfoService.findByToken(token);
            //Integer userId = authToken.getUserInfo().getUserId();
            
            //UserInfo userInfo = userInfoService.getUserInfoByUserId(userId);
            CardInfo cardInfo = cardInfoService.getCardInfoDetail(cardId);
            
            List<CardConnectModel> cardList = cardInfoService.listCardConnect(cardInfo.getCardOwnerId(), cardInfo.getGroupCompanyId(), cardInfo.getName(), cardInfo.getCompanyName(), cardInfo.getEmail());
            if(cardList.size() > 0){
                cardConnectResponse.setCardList(CardInfoConverter.convertCardConnectUserList(cardList));
                statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, this.msgListCardSuccess, token);                
            }
            else{
            	statusInfo = new StatusInfo(Constants.SUCCESS, Constants.NO_CONTENT, this.msgNoContent, token);                
            }
        }
        catch(Exception ex){
        	statusInfo = new StatusInfo(Constants.ERROR, Constants.SERVER_ERROR, ex.getMessage(), token);            
        }
        cardConnectResponse.setStatusInfo(statusInfo);
        return cardConnectResponse;
    }
    
    /*@RequestMapping(value = "/getListCardSearchByMroonga", method = RequestMethod.GET) 
    public CardInfoResponse getListCardSearchByMroonga(@RequestParam Integer groupCompanyId, @RequestParam int page, @RequestParam(required = false) String owner,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String position,
            @RequestParam(required = false) String department,
            @RequestParam(required = false) String company,
            @RequestParam(required = false) String searchText, HttpServletRequest request) {
        logger.debug("getListPossesionCard", CardInfoController.class);
        
        SchemaContextHolder.setSchemaType(SchemaType.USER);
        
        //Validate token
        AutoLogin token = userInfoService.findByToken(request.getHeader(HEADER_TOKEN));
        CardInfoResponse cardInfoResponse = new CardInfoResponse();
        Boolean tokenResponse = userInfoService.checkToken(request.getHeader(HEADER_TOKEN));
        if(tokenResponse == true){
            token = userInfoService.findByToken(request.getHeader(HEADER_TOKEN));
        } else {            
            statusInfo.setStatus(1);
            statusInfo.setCode("401");
            statusInfo.setMsg(this.msgTokenInvalid);
            statusInfo.setToken(token.getToken());
            cardInfoResponse.setStatusInfo(statusInfo);
            return cardInfoResponse;
        }        
        
        Integer userId = token.getUserInfo().getUserId();        
        Validate.notNull(userId, this.msgUserIdNotNull);
        
        try{
            if(name == null){
                name = "";
            }
            if(position == null){
                position = "";
            }
            if(department == null){
                department = "";
            }
            if(company == null){
                company = "";
            }
            if(owner == null){
                owner = "";
                List<com.ecard.core.vo.CardInfo> cardInfo = cardInfoService.getListCardSearchAllUserByMroonga(groupCompanyId, searchText, name, position, department,
        				company, page, userId);           
                BigInteger totalRecord = cardInfoService.getTotalCardSearchAllUserByMroonga(groupCompanyId, searchText, name, position, department,
        				company, page, userId);
                cardInfoResponse.setListCardInfo(CardInfoConverter.convertCardInforListMroonga(cardInfo));
                cardInfoResponse.setTotalRecord(Long.parseLong(totalRecord.toString()));
                statusInfo.setStatus(0);
                statusInfo.setCode("200");
                statusInfo.setMsg(this.msgListCardSuccess);
                statusInfo.setToken(token.getToken());
            } else {
                List<Integer> listUserId = cardInfoService.getUserIdByName(owner);
                if(listUserId.size() != 0){
                    List<com.ecard.core.vo.CardInfo> cardInfo = cardInfoService.getListCardSearchByMroonga(groupCompanyId, listUserId, searchText, name, position,
            				department, company, page);           
                    BigInteger totalRecord = cardInfoService.getTotalCardSearchAllByMroonga(groupCompanyId, listUserId, searchText, name, position,
            				department, company, page);
                    cardInfoResponse.setListCardInfo(CardInfoConverter.convertCardInforListMroonga(cardInfo));
                    cardInfoResponse.setTotalRecord(Long.parseLong(totalRecord.toString()));
                    statusInfo.setStatus(0);
                    statusInfo.setCode("200");
                    statusInfo.setMsg(this.msgListCardSuccess);
                    statusInfo.setToken(token.getToken());
                } else {
                    List<com.ecard.core.vo.CardInfo> listCardInfo = null;
                    cardInfoResponse.setListCardInfo(CardInfoConverter.convertCardInforListMroonga(listCardInfo));                    
                    cardInfoResponse.setTotalRecord(new Long(0));
                    statusInfo.setStatus(0);
                    statusInfo.setCode("200");
                    statusInfo.setMsg(this.msgNoUserFound);
                    statusInfo.setToken(token.getToken());
                }
            }                
        }
        catch(Exception ex){
            statusInfo.setStatus(1);
            statusInfo.setCode("500");
            statusInfo.setMsg(ex.getMessage());
            statusInfo.setToken(token.getToken());
        }
        cardInfoResponse.setStatusInfo(statusInfo);
        return cardInfoResponse;
    }*/
    
    /**
     * New thread class to process card image via OCR web service.
     * 
     * @author nhat.nguyen
     */
    static class OcrProcessCardThread extends Thread {
    	private Integer userId;
    	private Integer cardId;
    	private String cardData;
    	private OcrCardImageService ocrCardImageService;    	
    	
    	public OcrProcessCardThread(Integer userId, Integer cardId, String cardData, OcrCardImageService ocrCardImageService){
    		this.userId = userId;
    		this.cardId = cardId;
    		this.cardData = cardData;
    		this.ocrCardImageService = ocrCardImageService;
    	}
    	
    	public void run(){
    		if (StringUtils.isEmpty(this.cardData)){
    			return;
    		}
    		
    		processingCard(this.userId, this.cardId, this.cardData, this.ocrCardImageService);
    	}
    	
    	/**
         * synchronized to prevent concurrency accessing.
         */
    	private static synchronized void processingCard(Integer userId, Integer cardId, String cardData, OcrCardImageService ocrCardImageService){
    		logger.info("Thread start processing card image for cardId " + cardId);
    		try{
    			Thread.sleep(1000);
    			
    			// get card information via web service
	    		Status status = ocrCardImageService.processCardImageDetail(userId, cardId, cardData);
				if (status != null && status.getCode() != 0){
					logger.error("Error read card information from OCR service: " + status.getErrorMess());
				}
    		} catch (InterruptedException ex) {
    			logger.error(ex.getMessage());
	        } catch (Exception ex){
    			logger.error("Error processing card image: " + ex.getMessage());
    		}
    	}
    }

}
