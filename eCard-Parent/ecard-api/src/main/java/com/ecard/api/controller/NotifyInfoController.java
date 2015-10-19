/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecard.api.controller;

import com.ecard.api.controller.handler.RestExceptionHandler;
import com.ecard.api.controller.helper.Constants;
import com.ecard.core.datasource.SchemaContextHolder;
import com.ecard.core.datasource.type.SchemaType;
import com.ecard.core.model.AutoLogin;
import com.ecard.core.model.UserInfo;
import com.ecard.core.model.UserNotification;
import com.ecard.core.service.NotificationInfoService;
import com.ecard.core.service.UserInfoService;
import com.ecard.core.vo.NotificationList;
import com.ecard.core.vo.NotificationListManager;
import com.ecard.core.vo.NotificationListManagerResponse;
import com.ecard.core.vo.NotificationListResponse;
import com.ecard.core.vo.PushNotification;
import com.ecard.core.vo.StatusInfo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author HienTu
 */

@RestController
@RequestMapping(value = "/api")
public class NotifyInfoController extends RestExceptionHandler {
        
    private static final String HEADER_TOKEN = "token";
    
    @Autowired
    UserInfoService userInfoService;
        
    @Autowired
    NotificationInfoService notificationInfoService;
    
    @Value("${msg.token.invalid}")
    private String msgTokenInvalid;
    
    @Value("${msg.token.not.null}")
    private String msgTokenNotNull;
    
    @Value("${msg.get.user.success}")
    private String msgGetUserSuccess;
    
    @Value("${msg.notice.update.manager.success}")
    private String msgNoticeUpdateSuccess;
    
    @Value("${msg.no.content}")
    private String msgNoContent;
    
    @Value("${msg.update.flg.failed}")
    private String msgUpdateFlgFailed;
    
    @Value("${msg.update.flg.success}")
    private String msgUpdateFlgSuccess;
    
    @RequestMapping(value="/getUserUpdateInfo", method = RequestMethod.GET)
    public NotificationListResponse getUserUpdateInfo(HttpServletRequest request, @RequestParam(required = false) Integer page) throws IOException {
        NotificationListResponse userNotification = new NotificationListResponse();
        
        SchemaContextHolder.setSchemaType(SchemaType.USER);
        
        StatusInfo statusInfo = null;        
        String token = request.getHeader(HEADER_TOKEN);        
        Boolean checkResult = userInfoService.checkToken(token);
        if (!checkResult){
        	userNotification.setStatusInfo(new StatusInfo(Constants.ERROR, Constants.UNAUTHORIZED, this.msgTokenInvalid, token));
            return userNotification;
        }
        
        AutoLogin autoLogin = userInfoService.findByToken(token);
        Integer userId = autoLogin.getUserInfo().getUserId();
        List<NotificationList> listUpdate = new ArrayList<>();
        try {
        	if(page == null){
            	page = 0;
            }
            listUpdate = notificationInfoService.listAllNofiticationUserPaging(userId, page);
            if(listUpdate.size() != 0){
            	statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, this.msgGetUserSuccess, token);                
                //notificationInfoService.updateListAllNotificationUser(userId);
                userNotification.setUpdateInfoList(listUpdate);
            } else {                    
            	userNotification.setUpdateInfoList(listUpdate);
            	statusInfo = new StatusInfo(Constants.SUCCESS, Constants.NO_CONTENT, this.msgNoContent, token);                
            }            
        } catch (Exception ex) {
        	statusInfo = new StatusInfo(Constants.ERROR, Constants.SERVER_ERROR, ex.getMessage(), token);            
        }
        
        userNotification.setStatusInfo(statusInfo);
        return userNotification;
    }

    @RequestMapping(value="/getManagerNotice", method = RequestMethod.GET)
    public NotificationListManagerResponse getManagerNotice(HttpServletRequest request) throws IOException {
        SchemaContextHolder.setSchemaType(SchemaType.USER);
        
        NotificationListManagerResponse managerNotification = new NotificationListManagerResponse();
        StatusInfo statusInfo = null;        
        String token = request.getHeader(HEADER_TOKEN);        
        Boolean checkResult = userInfoService.checkToken(token);
        if (!checkResult){
        	managerNotification.setStatusInfo(new StatusInfo(Constants.ERROR, Constants.UNAUTHORIZED, this.msgTokenInvalid, token));
            return managerNotification;
        }
        

        try {
//            List<NotificationList> listUpdate = notificationInfoService.listAllNofiticationUser(userId);
            List<NotificationListManager> listUpdate = notificationInfoService.listAllNofiticationManager();
//            notificationInfoService.updateListAllNotificationUser(userId);            
            managerNotification.setUpdateInfoList(listUpdate);
            
            statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, this.msgNoticeUpdateSuccess, token);            
        } catch (Exception ex) {
        	statusInfo = new StatusInfo(Constants.ERROR, Constants.SERVER_ERROR, ex.getMessage(), token);            
        }
        
        managerNotification.setStatusInfo(statusInfo);
        return managerNotification;
    }

    @RequestMapping(value="/pushNotification", method = RequestMethod.POST)
    public String pushNotification(HttpServletRequest request,@RequestBody PushNotification pushNotification) throws IOException{
    	/*
    	 * {
	      "title": TITLE,
	      "type": "simple",
		  "alert": ALERT,
		  "audience": {"app": [APPID]},
		  "throttle": 0,
		  "draft": false,
	  	  "extra": {"user": {"sdk_notify_pattern": -1}},
	  	  "extra": {"option": {"id": UUID, "notification_type": "simple"}, "aps": {"badge": BADGE, "content-available": 1}}
	  	  }
    	 * 
    	 */
        SchemaContextHolder.setSchemaType(SchemaType.USER);
        
        ResponseEntity<String> result = null;
        StatusInfo statusInfo = null;        
        String token = request.getHeader(HEADER_TOKEN);        
        Boolean checkResult = userInfoService.checkToken(token);
        if (!checkResult){
        	statusInfo = new StatusInfo(Constants.ERROR, Constants.UNAUTHORIZED, this.msgTokenInvalid, token);            
        }
        
    	try {
            
            String jsonStr = " {"+
  	      "\"title\":\""+pushNotification.getTitle()+"\","+
  	      "\"type\":\"simple\","+
  		  "\"alert\":\""+ pushNotification.getAlert()+"\","+
  		  "\"audience\":{\"app\": [\""+pushNotification.getAppId()+"\"], \"uuid\": [\""+pushNotification.getDeviceToken()+"\"]},"+
  		  "\"throttle\":0,"+
  		  "\"draft\": false,"+
  	  	  "\"extra\": {\"user\": {\"sdk_notify_pattern\": -1}},"+
  	  	  "\"extra\": {\"option\":{\"notification_type\": \"simple\"}, \"aps\": {\"badge\":"+pushNotification.getBadge()+", \"content-available\": 1}}}";    	
            JSONParser parser = new JSONParser();
            JSONObject json = null;
            try {
                            json = (JSONObject) parser.parse(jsonStr);		
                    } catch (ParseException e) {
                            e.printStackTrace();
                    }

            String uri = "http://api.livepasspush.com/api/msgs/";
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "authkey 8hxoez1oas4l25wst7mw4x46lhx0hr1fb226sd22cqdpa50k; context=363b5a7b-2a66-431b-b269-ddea3037d657");  	     	    		 
            HttpEntity entity = new HttpEntity(json, headers);
            result = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
        } catch (Exception ex){
        	statusInfo = new StatusInfo(Constants.ERROR, Constants.SERVER_ERROR, ex.getMessage(), token);            
        }
        return result.toString();
    }
    
    @RequestMapping(value="/updateReadFlg", method = RequestMethod.POST)
    public StatusInfo updateReadFlg(@RequestBody UserNotification notify, HttpServletRequest request){
        SchemaContextHolder.setSchemaType(SchemaType.USER);
                
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(0);
        notify.setUserInfo(userInfo);
        notify.setCardId(0);
        notify.setChangeParamType(0);
        notify.setNoticeDate(new Date());
        notify.setNoticeType(0);
        notify.setNotifyMessage("");
        notify.setNoticeId(notify.getNoticeId());
        notify.setReadFlg(1);
        
        StatusInfo statusInfo = new StatusInfo();
        String token = request.getHeader(HEADER_TOKEN);        
        Boolean checkResult = userInfoService.checkToken(token);
        if (!checkResult){
            statusInfo = new StatusInfo(Constants.ERROR, Constants.UNAUTHORIZED, this.msgTokenInvalid, token);
        }
        
        try{
            if(notificationInfoService.updateReadFlgById(notify) == 1){
                statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, this.msgUpdateFlgSuccess, token);
            }
            else{
                statusInfo = new StatusInfo(Constants.ERROR, Constants.SERVER_ERROR, this.msgUpdateFlgFailed, token);
            }
        }
        catch (Exception ex){
            statusInfo = new StatusInfo(Constants.ERROR, Constants.SERVER_ERROR, ex.getMessage(), token);            
        }
        return statusInfo;
    }
}
