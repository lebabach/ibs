/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import com.ecard.core.service.CardInfoService;
import com.ecard.core.service.MyCardService;
import com.ecard.core.service.UserInfoService;
import com.ecard.core.vo.MyCardAndUserInfo;
import com.ecard.core.vo.StatusInfo;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Administrator
 */
@RestController
@RequestMapping(value = "/api")
public class MyCardController extends RestExceptionHandler{
    private static final Logger logger = LoggerFactory.getLogger(CardInfoController.class);
    
    private static final String HEADER_TOKEN = "token";
    
    @Autowired
    MyCardService myCardService;
    
    @Autowired
    UserInfoService userInfoService;
    
    @Autowired
    CardInfoService cardInfoService;
    
    @Value("${msg.token.invalid}")
    private String msgTokenInvalid;
    
    @Value("${msg.token.not.null}")
    private String msgTokenNotNull;
    
    @Value("${msg.upload.image.failed}")
    private String msgUploadImageFailed;
    
    @Value("${msg.register.card.success}")
    private String msgRegisterCardSuccess;
    
    @Value("${msg.userId.not.null}")
    private String msgUserIdNotNull;
    
    @Value("${msg.update.card.seq.success}")
    private String msgUpdateCardSeqSuccess;
    
    @Value("${msg.update.card.seq.failed}")
    private String msgUpdateCardSeqFailed;
    
    @Value("${msg.delete.card.success}")
    private String msgDeleteCardSucess;
    
    @Value("${scp.hostname}")
    private String scpHostName;
    
    @Value("${scp.user}")
    private String scpUser;
    
    @Value("${scp.password}")
    private String scpPassword;    
    
    @RequestMapping(value = "/registerMyCard", method = RequestMethod.POST)
    public StatusInfo registerMyCard(@RequestBody CardInfo cardInfo, HttpServletRequest request) throws Exception {
        logger.debug("registerMyCard", MyCardController.class);
       SchemaContextHolder.setSchemaType(SchemaType.USER);
       
       StatusInfo statusInfo = null;        
       String token = request.getHeader(HEADER_TOKEN);        
       Boolean checkResult = userInfoService.checkToken(token);
       if (!checkResult){
    	   statusInfo = new StatusInfo(Constants.ERROR, Constants.UNAUTHORIZED, this.msgTokenInvalid, token);
           return statusInfo;
       }
        
        UploadFileUtil uploadFile = new UploadFileUtil();
        try {
            FileUploadModel uploadModel = uploadFile.uploadImage(cardInfo.getImageFile(), this.scpHostName, this.scpUser, this.scpPassword);            
            if(!uploadModel.isStatus()) {
            	statusInfo = new StatusInfo(Constants.ERROR, Constants.SERVER_ERROR, this.msgUpdateCardSeqFailed, "");                
            }
            else {
                SchemaContextHolder.setSchemaType(SchemaType.MANAGER);
                
                com.ecard.core.model.CompanyInfo companyInfo = new com.ecard.core.model.CompanyInfo();
                companyInfo.setCompanyId(cardInfo.getCompanyInfo().getCompanyId());
                cardInfo.setImageFile(uploadModel.getFileName());
                cardInfo.setCompanyInfo(companyInfo);
                
                myCardService.registerMyCard(cardInfo);
                statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, this.msgRegisterCardSuccess, token);
            }
        }
        catch(Exception ex) {
        	statusInfo = new StatusInfo(Constants.ERROR, Constants.SERVER_ERROR, ex.getMessage(), token);            
        }
        return statusInfo;
    }
    
    /*@RequestMapping(value = "/registerMyCardSequence", method = RequestMethod.POST)
    public StatusInfo registerMyCardSequence(@RequestBody MyCard myCard, HttpServletRequest request) throws Exception{
        logger.debug("registerMyCardSequence", MyCardController.class);
        
        AutoLogin token = new AutoLogin();
        Boolean tokenResponse = userInfoService.checkToken(request.getHeader(HEADER_TOKEN));
        if(tokenResponse == true){
            token = userInfoService.findByToken(request.getHeader(HEADER_TOKEN));
        } else {            
            statusInfo.setStatus(1);
            statusInfo.setCode("401");
            statusInfo.setMsg(this.msgTokenInvalid);
            statusInfo.setToken(token.getToken());
            return statusInfo;
        }
        
        Integer userId = token.getUserInfo().getUserId();
        Validate.notNull(token, this.msgTokenNotNull);
        Validate.notNull(userId, this.msgUserIdNotNull);
        
        MyCardId mycardId = new MyCardId();
        PrusalHistoryId prusalHistoryId = new PrusalHistoryId();
        PossessionCardId possessionCardId = new PossessionCardId();
        
        PrusalHistory prusalHistory = new PrusalHistory();
        PossessionCard possessionCard = new PossessionCard();
        try {
            mycardId.setUserId(userId);
            prusalHistoryId.setUserId(userId);
            possessionCardId.setUserId(userId);
            
            prusalHistory.setId(prusalHistoryId);
            possessionCard.setId(possessionCardId);
            
            int result = myCardService.updateMyCardSeq(myCard.getId().getCardId(), myCard.getId().getSeq());
            
            if(result != 0){
                myCardService.updateCardIdForPosCard(myCard.getId().getCardId());
                myCardService.updateCardIdForPrusalHis(myCard.getId().getCardId());
                myCardService.updateCardIdForCardTag(myCard.getId().getCardId());
                
                if(myCard.getId().getSeq() == 1){
                    myCardService.updateNewCardFlg(myCard.getId().getCardId());
                }
                else {
                    myCardService.updateOldCardFlg(myCard.getId().getCardId());
                }
                
                statusInfo.setStatus(0);
                statusInfo.setCode("200");
                statusInfo.setMsg(this.msgUpdateCardSeqSuccess);
                statusInfo.setToken(token.getToken());
            }
            else {
                statusInfo.setStatus(0);
                statusInfo.setCode("200");
                statusInfo.setMsg(this.msgUpdateCardSeqFailed);
                statusInfo.setToken(token.getToken());
            }
        }
        catch(Exception ex){
            statusInfo.setStatus(1);
            statusInfo.setCode("500");
            statusInfo.setMsg(ex.getMessage());
            statusInfo.setToken(token.getToken());
        }
        
        return statusInfo;
    }*/
    
    @RequestMapping(value = "/deleteMyCard/{cardId}", method = RequestMethod.POST)
    public StatusInfo deleteMyCard(@PathVariable Integer cardId, HttpServletRequest request) throws Exception{
        logger.debug("registerMyCardSequence", MyCardController.class);
        SchemaContextHolder.setSchemaType(SchemaType.USER);
        
        StatusInfo statusInfo = null;        
        String token = request.getHeader(HEADER_TOKEN);        
        Boolean checkResult = userInfoService.checkToken(token);
        if (!checkResult){
        	statusInfo = new StatusInfo(Constants.ERROR, Constants.UNAUTHORIZED, this.msgTokenInvalid, token);
            return statusInfo;
        }
        
        AutoLogin autoLogin = userInfoService.findByToken(token);
        Integer userId = autoLogin.getUserInfo().getUserId();
        
        try{
            List<MyCardAndUserInfo> myCardList = myCardService.getListMyCardById(userId, cardId);
            Boolean isDelete = false;
            if(myCardList.size() > 0) {
                for(MyCardAndUserInfo mycard : myCardList){
                    if(mycard.getSeq() != 1){
                        SchemaContextHolder.setSchemaType(SchemaType.MANAGER);
                        
                        if(myCardService.deleteMyCard(userId, cardId) == 1) {
                            cardInfoService.deleteCardInfo(cardId);
                            isDelete = true;
                        }
                    }
                }
                if(isDelete){
                	statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, this.msgDeleteCardSucess, token);                    
                }
            }
        }
        catch(Exception ex){
        	statusInfo = new StatusInfo(Constants.ERROR, Constants.SERVER_ERROR, ex.getMessage(), token);            
        }
        
        return statusInfo;
    }
}
