package com.ecard.webapp.controller;

import java.util.Date;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.ecard.core.contants.AppIdContants;
import com.ecard.core.model.PushInfoId;
import com.ecard.core.model.UserInfo;
import com.ecard.core.model.UserNotification;
import com.ecard.core.service.HomeService;
import com.ecard.core.service.UserInfoService;

@Controller
@RequestMapping("/notification/*")
public class PushNotification {  
	@Autowired
	UserInfoService userInfoService;
    
	@Autowired
    private HomeService homeService;
	
    @RequestMapping(value="push" , method = RequestMethod.POST)
    @ResponseBody
    public String pushNotification(	@RequestParam(value = "cardOwnerId", required = false) Integer cardOwnerId,
    								@RequestParam(value = "cardId", required = false) Integer cardId,
    								@RequestParam(value = "msg", required = false) String msg,
    								@RequestParam(value = "type", required = false) Integer type) {  
        // Check with cardOwnerId
    	String msgResponse = "";
    	
    	if(cardOwnerId == null){
    		msgResponse = "This card has NOT owner";
    		return msgResponse;    				
    	}
    	PushInfoId pushInfoId = userInfoService.getPushNotification(cardOwnerId);

    	if(pushInfoId.getDeviceToken() == null || pushInfoId.getDeviceToken() == ""){
    		msgResponse = "Owner card has NOT registered device for push";
    		return msgResponse; 
    	}
    		
    	
	
    	if(pushInfoId.getDeviceToken() != "" && cardOwnerId != null){
    		try {
    	    	ResponseEntity<String> result = null;
        	            
                /*String jsonStr = " {"+
    	  	      "\"title\":\""+pushNotification.getTitle()+"\","+
    	  	      "\"type\":\"simple\","+
    	  		  "\"alert\":\""+ pushNotification.getAlert()+"\","+
    	  		  "\"audience\":{\"app\": [\""+pushNotification.getAppId()+"\"], \"uuid\": [\""+pushNotification.getDeviceToken()+"\"]},"+
    	  		  "\"throttle\":0,"+
    	  		  "\"draft\": false,"+
    	  	  	  "\"extra\": {\"user\": {\"sdk_notify_pattern\": -1}},"+
    	  	  	  "\"extra\": {\"option\":{\"notification_type\": \"simple\"}, \"aps\": {\"badge\":"+pushNotification.getBadge()+", \"content-available\": 1}}}";    	  	  	 
    	  	  	 */
    	    	
                PushInfoId pushNotification = new PushInfoId();
                UserInfo userInfoPush = new UserInfo();
                userInfoPush.setUserId(cardOwnerId);
                pushNotification = userInfoService.getPushNotification(cardOwnerId);
                String appId = null;
                Integer badge = homeService.countNotificationCard(cardOwnerId).intValue()+1;
                String title = "Approval this card";
                String deviceToken = pushNotification.getDeviceToken();
                
                if(pushNotification.getDeviceType().toLowerCase().equals("android")){
                	appId = AppIdContants.ANDROID_APP_ID;
                } else {
                	appId = AppIdContants.IOS_APP_ID;
                }
                String jsonStr = " {"+
          	  	      "\"title\":\""+title+"\","+
          	  	      "\"type\":\"simple\","+
          	  		  "\"alert\":\""+ msg+"\","+
          	  		  "\"audience\":{\"app\": [\""+appId+"\"], \"uuid\": [\""+deviceToken+"\"]},"+
          	  		  "\"throttle\":0,"+
          	  		  "\"draft\": false,"+
          	  	  	  "\"extra\": {\"user\": {\"sdk_notify_pattern\": -1} ,\"option\":{\"notification_type\": \"simple\"}, \"aps\": {\"badge\":"+badge+", \"content-available\": 1}}}";
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
                // Save to user_notification
                //userInfoService.saveHistoryNotification(UserNotification)
                if(result.getStatusCode().toString().equals("200")){
                	Integer noticeType =  type;
                	Integer changeParamType = 1;
                	Integer readFlg = 0;            	//
                	//UserNotification userNotification = new UserNotification(userInfoPush,noticeType,cardId,changeParamType,readFlg,msg,new Date());
                	try {
                		UserNotification userNotification = new UserNotification();
                    	userNotification.setCardId(cardId);
                    	userNotification.setChangeParamType(changeParamType);
                    	userNotification.setUserInfo(userInfoPush);
                    	userNotification.setNoticeDate(new Date());
                    	userNotification.setNoticeType(noticeType);
                    	userNotification.setNotifyMessage(msg);
                    	userNotification.setReadFlg(readFlg);
                    	userInfoService.saveHistoryNotification(userNotification);
                    	msgResponse = "One message was pushed to owner card";
                	} catch (Exception ex){
                		ex.printStackTrace();
                	}
                	
                }
            } catch (Exception ex){
                ex.printStackTrace();
            }
    	}
    	return msgResponse;
    }  

}
