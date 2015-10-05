/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecard.api.controller;

import com.ecard.api.controller.helper.Constants;
import com.ecard.core.datasource.SchemaContextHolder;
import com.ecard.core.datasource.type.SchemaType;
import com.ecard.core.model.AutoLogin;
import com.ecard.core.model.InquiryInfo;
import com.ecard.core.model.PushInfo;
import com.ecard.core.model.PushInfoId;
import com.ecard.core.model.UserInfo;
import com.ecard.core.service.SettingsInfoService;
import com.ecard.core.service.UserInfoService;
import com.ecard.core.vo.HelpInfoResponse;
import com.ecard.core.vo.MailSettings;
import com.ecard.core.vo.NotificationList;
import com.ecard.core.vo.NotificationListResponse;
import com.ecard.core.vo.SettingHelpInfo;
import com.ecard.core.vo.StatusInfo;
import java.util.Date;
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
 * @author HienTu
 */

@RestController
@RequestMapping(value = "/api")
public class SettingController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    private static final String HEADER_TOKEN = "token";
    
    @Autowired
    UserInfoService userInfoService;
    
    @Autowired
    SettingsInfoService settingsInfoService;
    
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
    
    @Value("${msg.get.help.info.success}")
    private String msgHelpInfoSuccess;
    
    @Value("${msg.register.pushId.success}")
    private String msgRegisterPushIdSuccess;
    
    @Value("${msg.update.pushId.success}")
    private String msgUpdatePushIdSuccess;
    
    @RequestMapping(value= "/updateMailSendInfo", method = RequestMethod.POST)
    public StatusInfo updateMailSendInfo(@RequestBody MailSettings mailSettings,HttpServletRequest request){
        
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
        try {
            SchemaContextHolder.setSchemaType(SchemaType.MANAGER);
            
            settingsInfoService.updateMailSendInfo(userId, mailSettings);
            statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, this.msgNoContent, token);            
        } catch (Exception ex){
        	statusInfo = new StatusInfo(Constants.ERROR, Constants.SERVER_ERROR, ex.getMessage(), token);            
        }
        return statusInfo;
    }

    @RequestMapping(value= "/registerPushId", method = RequestMethod.POST)
    public StatusInfo registerPushId(@RequestBody PushInfoId  pushInfo,HttpServletRequest request){

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
        try {
            
            pushInfo.setUserId(userId);
            pushInfo.setCreateDate(new Date());
            pushInfo.setUpdateDate(new Date());
            
            SchemaContextHolder.setSchemaType(SchemaType.MANAGER);
            Boolean result = settingsInfoService.registerPushId(pushInfo);
            if(result == false){
                settingsInfoService.createPushId(pushInfo);
                statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, this.msgUpdatePushIdSuccess, token);                
            }
            statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, this.msgRegisterPushIdSuccess, token);            
        } catch (Exception ex){
        	statusInfo = new StatusInfo(Constants.ERROR, Constants.SERVER_ERROR, ex.getMessage(), token);            
        }
        return statusInfo;
    }
    
    @RequestMapping(value= "/getHelpInfo", method = RequestMethod.GET)
    public HelpInfoResponse getHelpInfo(HttpServletRequest request){
        HelpInfoResponse helpInfoResponse = new HelpInfoResponse();
        
        SchemaContextHolder.setSchemaType(SchemaType.USER);
        
        StatusInfo statusInfo = null;        
        String token = request.getHeader(HEADER_TOKEN);        
        Boolean checkResult = userInfoService.checkToken(token);
        if (!checkResult){
        	helpInfoResponse.setStatusInfo(new StatusInfo(Constants.ERROR, Constants.UNAUTHORIZED, this.msgTokenInvalid, token));
            return helpInfoResponse;
        }

        try {
            List<SettingHelpInfo> listHelpInfo = settingsInfoService.getHelpInfo();
            helpInfoResponse.setHelpList(listHelpInfo);
            if(listHelpInfo.size() == 0){
            	statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, this.msgNoContent, token);                
            } else {
            	statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, this.msgHelpInfoSuccess, token);                
            }
        } catch(Exception ex) {
        	statusInfo = new StatusInfo(Constants.ERROR, Constants.SERVER_ERROR, ex.getMessage(), token);            
        }
        helpInfoResponse.setStatusInfo(statusInfo);
        return helpInfoResponse;
    }
    
    @RequestMapping(value= "/getSyncInfo", method = RequestMethod.GET)
    public NotificationListResponse getSyncInfo(HttpServletRequest request){
        SchemaContextHolder.setSchemaType(SchemaType.USER);
        
        NotificationListResponse response = new NotificationListResponse();
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
//            List<SettingHelpInfo> listHelpInfo = settingsInfoService.getHelpInfo();
            List<NotificationList> listUpdate = settingsInfoService.getSyncInfo(userId);
            response.setUpdateInfoList(listUpdate);
            if(listUpdate.size() == 0){
            	statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, this.msgNoContent, token);                
            } else {
            	statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, this.msgHelpInfoSuccess, token);                
            }
        } catch(Exception ex) {
        	statusInfo = new StatusInfo(Constants.ERROR, Constants.SERVER_ERROR, ex.getMessage(), token);            
        }
        response.setStatusInfo(statusInfo);
        return response; 
    }

    @RequestMapping(value= "/sendInquiry", method = RequestMethod.POST)
    public StatusInfo sendInquiry(@RequestBody InquiryInfo inquiryInfo,HttpServletRequest request){

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

        try {
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(userId);
            inquiryInfo.setUserInfo(userInfo);
            
            inquiryInfo.setTitle("");
            inquiryInfo.setAnswerFlg(0);
            inquiryInfo.setAnswerText("");
            inquiryInfo.setCreateDate(new Date());
            inquiryInfo.setUpdateDate(new Date());
            inquiryInfo.setOperaterId(0);
            
            settingsInfoService.sendInquiry(inquiryInfo);
            statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, this.msgNoContent, token);            
        } catch (Exception ex){
        	statusInfo = new StatusInfo(Constants.ERROR, Constants.SERVER_ERROR, ex.getMessage(), token);            
        }
        return statusInfo;
    }

}
