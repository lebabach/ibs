/*
 * CardUpdateHistoryController
 */
package com.ecard.api.controller;

import com.ecard.api.controller.handler.RestExceptionHandler;
import com.ecard.api.controller.helper.Constants;
import com.ecard.core.datasource.SchemaContextHolder;
import com.ecard.core.datasource.type.SchemaType;
import com.ecard.core.model.AutoLogin;
import com.ecard.core.service.CardUpdateHistoryService;
import com.ecard.core.service.UserInfoService;
import com.ecard.core.service.converter.CardUpdateHistoryConverter;
import com.ecard.core.vo.CardUpdateHisAndUserInfo;
import com.ecard.core.vo.CardUpdateHisAndUserInfoResponse;
import com.ecard.core.vo.CardUpdateHistoryResponse;
import com.ecard.core.vo.StatusInfo;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author vinhla
 */
@RestController
@RequestMapping(value = "/api")
public class CardUpdateHistoryController extends RestExceptionHandler{
    private static final Logger logger = LoggerFactory.getLogger(CardUpdateHistoryController.class);
    
    private static final String HEADER_TOKEN = "token";
    
    @Autowired
    CardUpdateHistoryService cardUpdateHistoryService;
    
    @Autowired
    UserInfoService userInfoService;
    
    @Value("${msg.list.card.success}")
    private String msgListCardSuccess;
    
    @Value("${msg.no.content}")
    private String msgNoContent;
    
    @Value("${msg.token.invalid}")
    private String msgTokenInvalid;
    
    @RequestMapping(value = "/getListCardUpdateHistory", method = RequestMethod.GET)
    public CardUpdateHisAndUserInfoResponse getListCardUpdateHistory(@RequestParam Integer cardId, HttpServletRequest request) throws IllegalAccessException, InvocationTargetException {
        logger.debug("listAllCard", CardUpdateHistoryController.class);
        SchemaContextHolder.setSchemaType(SchemaType.USER);
        CardUpdateHisAndUserInfoResponse response = new CardUpdateHisAndUserInfoResponse();
        
        StatusInfo statusInfo = null;        
        String token = request.getHeader(HEADER_TOKEN);        
        Boolean checkResult = userInfoService.checkToken(token);
        if (!checkResult){
        	response.setStatusInfo(new StatusInfo(Constants.ERROR, Constants.UNAUTHORIZED, this.msgTokenInvalid, token));
            return response;
        }
        
        try{
            List<CardUpdateHisAndUserInfo> cardList = cardUpdateHistoryService.getListCardUpdateHistory(cardId);
            if(cardList.size() > 0){
                response.setCardList(CardUpdateHistoryConverter.convertCardConnectUserList(cardList));
                statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, this.msgListCardSuccess, token);                
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
}
