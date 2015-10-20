package com.ecard.api.controller;

import com.ecard.api.controller.handler.RestExceptionHandler;
import com.ecard.api.controller.helper.Constants;
import com.ecard.core.datasource.SchemaContextHolder;
import com.ecard.core.datasource.type.SchemaType;
import com.ecard.core.model.AutoLogin;
import com.ecard.core.model.UserInfo;
import com.ecard.core.service.BatchBackupAutoService;
import com.ecard.core.service.EmailService;
import com.ecard.core.service.HomeService;
import com.ecard.core.service.UserInfoService;
import com.ecard.core.vo.CardInfoName;
import com.ecard.core.vo.HomeResponse;
import com.ecard.core.vo.StatusInfo;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;

/**
 * Handles requests for the application home page.
 */
@RestController
@RequestMapping(value = "/api")
public class HomeController extends RestExceptionHandler{
	
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    private static final String HEADER_TOKEN = "token";

    @Autowired
    private HomeService homeService;

    @Autowired
    UserInfoService userInfoService;
    
    @Value("${msg.token.invalid}")
    private String msgTokenInvalid;
    
    @Value("${msg.list.data.success}")
    private String msgListDataSuccess;
    
//    @Value("${mail.address}")
//    private String mailFrom;
//    
    @Autowired
    EmailService emailService;
    
    @Autowired
    BatchBackupAutoService batchBackupAutoService;
    
    private final StatusInfo statusInfo = new StatusInfo();

    /**
     * Simply selects the home view to render by returning its name.
     * @param request
     * @return HomeResponse
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public HomeResponse home(HttpServletRequest request) {
        logger.info("home {}.", HomeController.class);
        
        HomeResponse response = new HomeResponse();

        // Check token - get token - return statusInfo
        SchemaContextHolder.setSchemaType(SchemaType.USER);
        
        StatusInfo statusInfo = null;        
        String token = request.getHeader(HEADER_TOKEN);        
        Boolean checkResult = userInfoService.checkToken(token);
        if (!checkResult){
        	response.setStatusInfo(new StatusInfo(Constants.ERROR, Constants.UNAUTHORIZED, this.msgTokenInvalid, token));
            return response;
        }      
                
        //Get userId nu token
        AutoLogin autoLogin = userInfoService.findByToken(token);
        Integer userId = autoLogin.getUserInfo().getUserId();
        UserInfo userInfo = userInfoService.getUserInfoByUserId(userId);
        try{
            
            CardInfoName myCard = homeService.getMyCardInfo(userId);            
            BigInteger recentCardCnt = homeService.countRecentCard(userId);
            Long possessionCardCnt = homeService.countPossessionCard(userId);
            Long inputWaitCnt = homeService.countInputWaitCard(userId);
            BigInteger connectCnt = homeService.countConnectCard(userId);
            BigInteger recentConnectCnt = homeService.countRecentConnectCard(userId);
            BigInteger noticeCnt = homeService.countNotificationCard(userId);
            BigInteger recentPossessionCardCnt = homeService.countRecentPossessionCard(userId);
//            response.setName(myCard[0].toString());
//            response.setImageFile(myCard[1].toString());
            response.setName(myCard.getName());
            response.setFirstName(myCard.getFirstName());
            response.setLastName(myCard.getLastName());
            response.setNameKana(myCard.getNameKana());
            response.setLastNameKana(myCard.getLastNameKana());
            response.setFirstNameKana(myCard.getFirstNameKana());
            response.setInputWaitCnt(inputWaitCnt);
            response.setPossessionCardCnt(possessionCardCnt);
            response.setRecentCardCnt(recentCardCnt);
            response.setNoticeCnt(noticeCnt);
            response.setRecentPossessionCardCnt(recentPossessionCardCnt);
            response.setConnectCnt(connectCnt);
            response.setRecentConnectCnt(recentConnectCnt);

            statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, this.msgListDataSuccess, token);            
        }
        catch(Exception ex){
        	statusInfo = new StatusInfo(Constants.ERROR, Constants.SERVER_ERROR, ex.getMessage(), token);            
        }

        response.setStatusInfo(statusInfo);
        return response;
    }
	
    @RequestMapping(value = "/sendMailWithInlineImage", method = RequestMethod.POST)
    public String sendMailWithInline(   @RequestParam("recipientName") final String recipientName,
                                        @RequestParam("recipientEmail") final String recipientEmail)
      throws MessagingException, IOException {
       
        //emailService.sendMailWithInline(recipientName, recipientEmail);
        Context ctx = new Context();
       ctx.setVariable("password", recipientName);
       ctx.setVariable("subscriptionDate", new Date());
        ctx.setVariable("hobbies", Arrays.asList("Cinema", "Sports", "Music"));
        emailService.sendMail("IBS",recipientEmail,"ReturnPassWord",ctx, "mailInline");
       return "Success";

    }
    
    @RequestMapping(value = "/sendMailAPI", method = RequestMethod.POST)
    public String sendMailWebapp(@RequestParam("recipientName") final String recipientName,
    							 @RequestParam("password") final String password,
                                 @RequestParam("recipientEmail") final String recipientEmail)
      throws MessagingException, IOException {
       
    	Context ctx = new Context();		
		ctx.setVariable("password", password);
		ctx.setVariable("recipientEmail", recipientEmail);
		emailService.sendMail("BCRIBBON", recipientEmail, "BC-RIBBON IDï¼�ãƒ‘ã‚¹ãƒ¯ãƒ¼ãƒ‰é€šçŸ¥", ctx, "recoverpass");
		return "Success";

    }

    @RequestMapping(value = "/startBatch", method = RequestMethod.GET)
    public HomeResponse startBatch(HttpServletRequest request) {
        logger.info("startBatch {}.", HomeController.class);
        
        HomeResponse response = new HomeResponse();
   
          
        try{
            
            batchBackupAutoService.backupManualDatabase();
            
            statusInfo.setStatus(0);
            statusInfo.setCode("200");
            statusInfo.setMsg(this.msgListDataSuccess);
            statusInfo.setToken("");
        }
        catch(Exception ex){
            statusInfo.setStatus(1);
            statusInfo.setCode("500");
            statusInfo.setMsg(ex.getMessage());
            statusInfo.setToken("");
        }

        response.setStatusInfo(statusInfo);
        return response;
    }
}
