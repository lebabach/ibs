/*
 * CardTagController
 */
package com.ecard.api.controller;

import com.ecard.api.controller.handler.RestExceptionHandler;
import com.ecard.api.controller.helper.Constants;
import com.ecard.core.datasource.SchemaContextHolder;
import com.ecard.core.datasource.type.SchemaType;
import com.ecard.core.model.AutoLogin;
import com.ecard.core.model.CardTagId;
import com.ecard.core.service.CardTagService;
import com.ecard.core.service.UserInfoService;
import com.ecard.core.service.converter.CardTagConverter;
import com.ecard.core.vo.CardTagAndCompany;
import com.ecard.core.vo.CardTagAndCompanyResponse;
import com.ecard.core.vo.CardTagIdResponse;
import com.ecard.core.vo.StatusInfo;
import com.ecard.core.vo.TagForCard;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.Validate;
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
 * @author vinhla
 */
@RestController
@RequestMapping(value = "/api")
public class CardTagController extends RestExceptionHandler{
    private static final Logger logger = LoggerFactory.getLogger(UserTagController.class);
    
    private static final String HEADER_TOKEN = "token";
    
    @Autowired
    UserInfoService userInfoService;
    
    @Autowired
    CardTagService cardTagService;
    
    @Value("${msg.token.invalid}")
    private String msgTokenInvalid;
    
    @Value("${msg.token.not.null}")
    private String msgTokenNotNull;
    
    
    @Value("${msg.list.tag.success}")
    private String msgListTagSuccess;
    
    @Value("${msg.delete.card.tag.success}")
    private String msgDeleteCardTagSuccess;
    
    @Value("${msg.no.content}")
    private String msgNoContent;
    
    @Value("${msg.userId.not.null}")
    private String msgUserIdNotNull;
    
    /**
     *
     * @param cardId
     * @param request
     * @return
     */
    @RequestMapping(value = "/listTagForCard", method = RequestMethod.GET)
    public CardTagIdResponse listCardTagByCardId(HttpServletRequest request) {
        logger.debug("listUserTag :", UserTagController.class);
        CardTagIdResponse cardTagResponse = new CardTagIdResponse();
        SchemaContextHolder.setSchemaType(SchemaType.USER);
        
        StatusInfo statusInfo = null;        
        String token = request.getHeader(HEADER_TOKEN);        
        Boolean checkResult = userInfoService.checkToken(token);
        if (!checkResult){
        	cardTagResponse.setStatusInfo(new StatusInfo(Constants.ERROR, Constants.UNAUTHORIZED, this.msgTokenInvalid, token));
            return cardTagResponse;
        }
        
        AutoLogin autoLogin = userInfoService.findByToken(token);
        Integer userId = autoLogin.getUserInfo().getUserId();

        
        try {
            List<TagForCard> listCardTag = cardTagService.listCardTagByCardId(userId);
            List<TagForCard> listUserTag = cardTagService.listUserTagByUserId(userId);
            
            if(listCardTag.size() == 0){
                cardTagResponse.setTagList(CardTagConverter.convertCardTagIdList(listUserTag));
            }
            else{
                cardTagResponse.setTagList(CardTagConverter.convertCardTagIdList(listCardTag));
            }
            statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, this.msgListTagSuccess, token);            
        }
        catch(Exception ex) {
        	statusInfo = new StatusInfo(Constants.ERROR, Constants.SERVER_ERROR, ex.getMessage(), token);
        }
        cardTagResponse.setStatusInfo(statusInfo);
        return cardTagResponse;
    }
    
    @RequestMapping(value = "/listCardWhereTag", method = RequestMethod.GET)
    public CardTagAndCompanyResponse listCardWhereTag(@RequestParam Integer tagId, HttpServletRequest request) {
        logger.debug("listUserTag :", UserTagController.class);
        
        Validate.notNull(tagId, "companyId is not null");
        
        SchemaContextHolder.setSchemaType(SchemaType.USER);
        CardTagAndCompanyResponse cardTagResponse = new CardTagAndCompanyResponse();
        
        StatusInfo statusInfo = null;        
        String token = request.getHeader(HEADER_TOKEN);        
        Boolean checkResult = userInfoService.checkToken(token);
        if (!checkResult){
        	cardTagResponse.setStatusInfo(new StatusInfo(Constants.ERROR, Constants.UNAUTHORIZED, this.msgTokenInvalid, token));
            return cardTagResponse;
        }
                
        try {
            CardTagAndCompany listCardTag = cardTagService.listCardWhereTag(tagId);
            if(listCardTag.getCardInfo().size() == 0){
            	statusInfo = new StatusInfo(Constants.SUCCESS, Constants.NO_CONTENT, this.msgNoContent, token);
            } else {
            	statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, this.msgListTagSuccess, token);                
            }
            cardTagResponse.setCardList(listCardTag.getCardInfo());           
            cardTagResponse.setTagName(listCardTag.getTagName());            
        }
        catch(Exception ex) {
        	statusInfo = new StatusInfo(Constants.ERROR, Constants.SERVER_ERROR, ex.getMessage(), token);            
        }
        cardTagResponse.setStatusInfo(statusInfo);
        return cardTagResponse;
    }
    
    @RequestMapping(value = "/deleteCardTag", method = RequestMethod.POST)
    public StatusInfo deleteCardTag(@RequestBody CardTagId cardTag, HttpServletRequest request) {
        logger.debug("deleteCardTag :", UserTagController.class);
        StatusInfo statusInfo = null;        
        String token = request.getHeader(HEADER_TOKEN);        
        Boolean checkResult = userInfoService.checkToken(token);
        if (!checkResult){
        	statusInfo = new StatusInfo(Constants.ERROR, Constants.UNAUTHORIZED, this.msgTokenInvalid, token);
            return statusInfo;
        }

        try{
            SchemaContextHolder.setSchemaType(SchemaType.MANAGER);            
            cardTagService.deleteCardTag(cardTag.getCardId(),cardTag.getTagId());
//            cardTagService.deleteCardTag(cardTag.getId().getCardId(), cardTag.getId().getTagId());
            statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, this.msgDeleteCardTagSuccess, token);            
        }
        catch(Exception ex){
        	statusInfo = new StatusInfo(Constants.ERROR, Constants.SERVER_ERROR, ex.getMessage(), token);
        }        
        return statusInfo;
    }
    
}
