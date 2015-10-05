/*
 * UserTagController class
 */
package com.ecard.api.controller;

import com.ecard.api.controller.handler.RestExceptionHandler;
import com.ecard.api.controller.helper.Constants;
import com.ecard.core.datasource.SchemaContextHolder;
import com.ecard.core.datasource.type.SchemaType;
import com.ecard.core.model.AutoLogin;
import com.ecard.core.model.CardTagId;
import com.ecard.core.model.UserInfo;
import com.ecard.core.model.UserTag;
import com.ecard.core.service.CardTagService;
import com.ecard.core.service.UserInfoService;
import com.ecard.core.service.UserTagService;
import com.ecard.core.vo.CardAndUserTag;
import com.ecard.core.vo.StatusInfo;
import com.ecard.core.vo.UserTagAndCardTag;
import com.ecard.core.vo.UserTagAndCardTagResponse;
import com.ecard.core.vo.UserTagIdResponse;
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
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author vinhla
 */
@RestController
@RequestMapping(value = "/api")
public class UserTagController extends RestExceptionHandler{
    private static final Logger logger = LoggerFactory.getLogger(UserTagController.class);
    
    private static final String HEADER_TOKEN = "token";
    
    @Autowired
    UserTagService userTagService;
    
    @Autowired
    UserInfoService userInfoService;
    
    @Autowired
    CardTagService cardTagService;
        
    @Value("${msg.token.invalid}")
    private String msgTokenInvalid;
    
    @Value("${msg.userId.not.null}")
    private String msgUserIdNotNull;
    
    @Value("${msg.register.tag.success}")
    private String msgRegisterTagSuccess;
    
    @Value("${msg.tag.name.empty}")
    private String msgTagNameEmpty;            
    
    @Value("${msg.delete.tag.success}")
    private String msgDeleteTagSuccess;
    
    @Value("${msg.tagId.not.exist}")
    private String msgTagIdNotExist;
    
    @Value("${msg.create.tag.success}")
    private String msgCreateTagSuccess;
    
    @Value("${msg.list.tag.success}")
    private String msgListTagSuccess;
    
    @RequestMapping(value = "/registerUserTag", method = RequestMethod.POST)
    public UserTagIdResponse editUserTag(@RequestBody CardAndUserTag cardAndUserTag, HttpServletRequest request)
    {
        SchemaContextHolder.setSchemaType(SchemaType.USER);
        
        UserTagIdResponse response = new UserTagIdResponse();
        StatusInfo statusInfo = null;        
        String token = request.getHeader(HEADER_TOKEN);        
        Boolean checkResult = userInfoService.checkToken(token);
        if (!checkResult){
        	response.setStatusInfo(new StatusInfo(Constants.ERROR, Constants.UNAUTHORIZED, this.msgTokenInvalid, token));
            return response;
        }
        
        AutoLogin autoLogin = userInfoService.findByToken(token);
        Integer userId = autoLogin.getUserInfo().getUserId();        
        Validate.notNull(userId, this.msgUserIdNotNull);
                
        try {
            if(cardAndUserTag.getTagName() == null || cardAndUserTag.getTagName() == ""){
                response.setTagId(0);
                statusInfo = new StatusInfo(Constants.SUCCESS, Constants.NO_CONTENT, this.msgTagNameEmpty, token);                
            } else {
                SchemaContextHolder.setSchemaType(SchemaType.MANAGER);
                
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
                response.setTagId(tagId);
                statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, this.msgRegisterTagSuccess, token);                
            }
            
        }
        catch(Exception ex) {
        	statusInfo = new StatusInfo(Constants.ERROR, Constants.SERVER_ERROR, ex.getMessage(), token);            
        }
        response.setStatusInfo(statusInfo);
        return response;
    }
    
    @RequestMapping(value = "/deleteUserTag", method = RequestMethod.POST)
    public StatusInfo deleteUserTag(@RequestBody UserTag userTag, HttpServletRequest request) {
        logger.debug("deleteUserTag :", UserTagController.class);
        
        SchemaContextHolder.setSchemaType(SchemaType.USER);
        
        StatusInfo statusInfo = null;        
        String token = request.getHeader(HEADER_TOKEN);        
        Boolean checkResult = userInfoService.checkToken(token);
        if (!checkResult){
        	statusInfo = new StatusInfo(Constants.ERROR, Constants.UNAUTHORIZED, this.msgTokenInvalid, token);
            return statusInfo;
        }
        
        try {
            SchemaContextHolder.setSchemaType(SchemaType.MANAGER);
            // Delete UserTag, CardTag with TagId
            if((cardTagService.deleteCardTagByTagId(userTag.getTagId()) == 1) || 
                    (userTagService.deleteUserTag(userTag.getTagId()))== 1){
            	statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, this.msgDeleteTagSuccess, token);                
            } else {
            	statusInfo = new StatusInfo(Constants.ERROR, Constants.BAD_REQUEST, this.msgTagIdNotExist, token);
            }
        }
        catch(Exception ex) {
        	statusInfo = new StatusInfo(Constants.ERROR, Constants.SERVER_ERROR, ex.getMessage(), token);            
        }
//        userTagResponse.setStatusInfo(statusInfo);
        return statusInfo;
    }
    
    @RequestMapping(value = "/registerCardTag", method = RequestMethod.POST)
    public StatusInfo registerCardTag(@RequestBody CardTagId cardTag, HttpServletRequest request) {
        logger.debug("addUserTag :", UserTagController.class);
        SchemaContextHolder.setSchemaType(SchemaType.USER);
        
        StatusInfo statusInfo = null;        
        String token = request.getHeader(HEADER_TOKEN);        
        Boolean checkResult = userInfoService.checkToken(token);
        if (!checkResult){
        	statusInfo = new StatusInfo(Constants.ERROR, Constants.UNAUTHORIZED, this.msgTokenInvalid, token);
            return statusInfo;
        }

        
        try {
            SchemaContextHolder.setSchemaType(SchemaType.MANAGER);
//            int response = cardTagService.registerCardTag(cardTag.getCardId(), cardTag.getTagId());
//            if(response == 0)
            cardTagService.addCardTag(cardTag);
            statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, this.msgCreateTagSuccess, token);            
        }
        catch(Exception ex) {
        	statusInfo = new StatusInfo(Constants.ERROR, Constants.SERVER_ERROR, ex.getMessage(), token);            
        }
//        userTagResponse.setStatusInfo(statusInfo);
        return statusInfo;
    }
    
    @RequestMapping(value = "/listUserTag", method = RequestMethod.GET)
    public UserTagAndCardTagResponse listUserTag(HttpServletRequest request) {
        logger.debug("listUserTag :", UserTagController.class);        
        SchemaContextHolder.setSchemaType(SchemaType.USER);
        
        UserTagAndCardTagResponse userTagResponse = new UserTagAndCardTagResponse();
        StatusInfo statusInfo = null;        
        String token = request.getHeader(HEADER_TOKEN);        
        Boolean checkResult = userInfoService.checkToken(token);
        if (!checkResult){
        	userTagResponse.setStatusInfo(new StatusInfo(Constants.ERROR, Constants.UNAUTHORIZED, this.msgTokenInvalid, token));
            return userTagResponse;
        }
        
        AutoLogin autoLogin = userInfoService.findByToken(token);
        Integer userId = autoLogin.getUserInfo().getUserId();

        try {
            List<UserTagAndCardTag> listUserTag = userTagService.getListUserTagByUserId(userId);
//            userTagResponse.setListUserTag(UserTagConverter.convertUserTagList(listUserTag));
            userTagResponse.setTagList(listUserTag);
            statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, this.msgListTagSuccess, token);            
        }
        catch(Exception ex) {
        	statusInfo = new StatusInfo(Constants.ERROR, Constants.SERVER_ERROR, ex.getMessage(), token);            
        }
        userTagResponse.setStatusInfo(statusInfo);
        return userTagResponse;
    }
}
