/*
 * CardMemoController class
 */
package com.ecard.api.controller;

import com.ecard.api.controller.handler.RestExceptionHandler;
import com.ecard.api.controller.helper.Constants;
import com.ecard.core.datasource.SchemaContextHolder;
import com.ecard.core.datasource.type.SchemaType;
import com.ecard.core.model.AutoLogin;
import com.ecard.core.model.UserCardMemoId;
import com.ecard.core.model.UserInfo;
import com.ecard.core.service.CardMemoService;
import com.ecard.core.service.PossessionCardService;
import com.ecard.core.service.UserInfoService;
import com.ecard.core.vo.CardInfoMemo;
import com.ecard.core.vo.CardInfoMemoResponse;
import com.ecard.core.vo.MyCardResponse;
import com.ecard.core.vo.StatusInfo;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author admin
 */

@RestController
@RequestMapping(value = "/api")
public class CardMemoController extends RestExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(CardMemoController.class);
    
    private static final String HEADER_TOKEN = "token";
    
    @Autowired
    UserInfoService userInfoService;
    
    @Autowired
    CardMemoService cardMemoService;
    
    @Autowired
    PossessionCardService possessionCardService;
    
    @Value("${msg.token.invalid}")
    private String msgTokenInvalid;
    
    @Value("${msg.list.memo.card.success}")
    private String msgListMemoCardSuccess;
    
    @Value("${msg.register.memo.card.success}")
    private String msgRegisterMemoCardSuccess;
    
    @Value("${msg.delete.memo.card.success}")
    private String msgDeleteMemoCardSuccess;
    
    @RequestMapping(value = "/getListCardMemo", method = RequestMethod.GET)
    public CardInfoMemoResponse listCardMemo(@RequestParam Integer cardId, HttpServletRequest request){
        SchemaContextHolder.setSchemaType(SchemaType.USER);
        CardInfoMemoResponse cardInfoMemoResponse = new CardInfoMemoResponse();
        //Validation token
        StatusInfo statusInfo = null;        
        String token = request.getHeader(HEADER_TOKEN);        
        Boolean checkResult = userInfoService.checkToken(token);
        if (!checkResult){
        	cardInfoMemoResponse.setStatusInfo(new StatusInfo(Constants.ERROR, Constants.UNAUTHORIZED, this.msgTokenInvalid, token));
            return cardInfoMemoResponse;
        }

        try{
            List<CardInfoMemo> cardMemo = cardMemoService.getMemoCard(cardId);
                      
            cardInfoMemoResponse.setSearchTextList(cardMemo);
            statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, this.msgListMemoCardSuccess, token);            
        }
        catch(Exception ex){
        	statusInfo = new StatusInfo(Constants.ERROR, Constants.SERVER_ERROR, ex.getMessage(), token);
        }
        cardInfoMemoResponse.setStatusInfo(statusInfo);
        return cardInfoMemoResponse;
    }
    
    @RequestMapping(value = "/registerCardMemo", method = RequestMethod.POST)
    public CardInfoMemoResponse registerCardMemo(@RequestBody UserCardMemoId cardMemo, HttpServletRequest request) throws IOException {  
        SchemaContextHolder.setSchemaType(SchemaType.USER);
        CardInfoMemoResponse cardMemoResponse = new CardInfoMemoResponse();

        StatusInfo statusInfo = null;        
        String token = request.getHeader(HEADER_TOKEN);        
        Boolean checkResult = userInfoService.checkToken(token);
        if (!checkResult){
        	cardMemoResponse.setStatusInfo(new StatusInfo(Constants.ERROR, Constants.UNAUTHORIZED, this.msgTokenInvalid, token));
            return cardMemoResponse;
        }
        
        AutoLogin autoLogin = userInfoService.findByToken(token);
        Integer userId = autoLogin.getUserInfo().getUserId();
       
        try {
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(userId);            
            cardMemo.setUserId(userId); 
            cardMemo.setCreateDate(new Date());
            
            SchemaContextHolder.setSchemaType(SchemaType.MANAGER);
            
            int seq;
            if(cardMemo.getSeq() != 0){
            	cardMemoService.registerCardMemo(cardMemo);
            }
            else{
            	try{
            		seq = cardMemoService.getMaxSeqByUserId(userId);
            	}
            	catch(Exception e){
            		seq = 0;
            	}
            	
            	if(cardMemo.getSeq() == 0){
                	cardMemo.setSeq(seq);
                }
                cardMemoService.createCardMemo(cardMemo);
                cardMemoResponse.setSeq(seq);
            }
            
            statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, this.msgRegisterMemoCardSuccess, token);            
        }
        catch(Exception ex){
        	statusInfo = new StatusInfo(Constants.ERROR, Constants.SERVER_ERROR, ex.getMessage(), token);            
        }
        cardMemoResponse.setStatusInfo(statusInfo);
        return cardMemoResponse;
    }

    @RequestMapping(value = "/deleteCardMemo", method = RequestMethod.POST)
    public CardInfoMemoResponse deleteCardMemo(@RequestBody UserCardMemoId cardMemo, HttpServletRequest request) throws IOException {    
        SchemaContextHolder.setSchemaType(SchemaType.USER);
        CardInfoMemoResponse cardMemoResponse = new CardInfoMemoResponse();
        
        StatusInfo statusInfo = null;        
        String token = request.getHeader(HEADER_TOKEN);        
        Boolean checkResult = userInfoService.checkToken(token);
        if (!checkResult){
        	cardMemoResponse.setStatusInfo(new StatusInfo(Constants.ERROR, Constants.UNAUTHORIZED, this.msgTokenInvalid, token));
            return cardMemoResponse;
        }
        
        try {
            SchemaContextHolder.setSchemaType(SchemaType.MANAGER);
            cardMemoService.deleteCardMemo(cardMemo);
            statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, this.msgDeleteMemoCardSuccess, token);            
        }
        catch(Exception ex){
        	statusInfo = new StatusInfo(Constants.ERROR, Constants.SERVER_ERROR, ex.getMessage(), token);            
        }
        cardMemoResponse.setStatusInfo(statusInfo);
        return cardMemoResponse;
    }
}
