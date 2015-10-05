package com.ecard.api.controller;

import com.ecard.api.controller.handler.InvalidLoginException;
import com.ecard.api.controller.handler.RestExceptionHandler;
import com.ecard.api.controller.helper.Constants;
import com.ecard.api.controller.helper.FileUploadModel;
import com.ecard.api.controller.helper.UploadFileUtil;
import com.ecard.core.datasource.SchemaContextHolder;
import com.ecard.core.datasource.type.SchemaType;
import com.ecard.core.model.ActionLog;
import com.ecard.core.model.ActionLogId;
import com.ecard.core.model.AutoLogin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ecard.core.model.UserInfo;
import com.ecard.core.model.enums.ActionLogType;
import com.ecard.core.service.EmailService;
import com.ecard.core.service.HomeService;
import com.ecard.core.service.SettingsInfoService;
import com.ecard.core.service.UserInfoService;
import com.ecard.core.service.converter.UserInfoConverter;
import com.ecard.core.vo.CardInfoName;
import com.ecard.core.vo.MyCoverImageResponse;
import com.ecard.core.vo.StatusInfo;
import com.ecard.core.vo.UpdatePassword;
import com.ecard.core.vo.UpdateUserEmail;
import com.ecard.core.vo.UserInfoDetailResponse;
import com.ecard.core.vo.UserInfoResponse;
import com.ecard.core.vo.UserListContact;
import com.ecard.core.vo.UserListContactResponse;
import com.ecard.core.vo.UserProfile;
import com.ecard.security.service.AuthenticationService;
import com.ecard.security.util.TokenUtil;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.List;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;

/**
 * Handles requests for the application home page.
 */
@RestController
@RequestMapping(value = "/api")
public class UserInfoController extends RestExceptionHandler{
	
    private static final Logger logger = LoggerFactory.getLogger(UserInfoController.class);
    
    private static final String HEADER_TOKEN = "token";
    
    @Autowired
    private UserInfoService userInfoService;
    
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    SettingsInfoService settingsInfoService;
    
    @Autowired
    private HomeService homeService;
    
    @Autowired
    EmailService emailService;
    
    private StatusInfo statusInfo = new StatusInfo();
    private UserInfoResponse userInfoRes = new UserInfoResponse();
    
    @Value("${msg.no.content}")
    private String msgNoContent;
    
    @Value("${msg.token.invalid}")
    private String msgTokenInvalid;
    
    @Value("${msg.token.not.null}")
    private String msgTokenNotNull;
    
    @Value("${msg.userId.not.null}")
    private String msgUserIdNotNull;
    
    @Value("${msg.list.user.success}")
    private String msgListUserSuccess;
    
    @Value("${msg.invalid.login}")
    private String msgInvalidLogin;
    
    @Value("${msg.register.profile.success}")
    private String msgRegisterProfileSuccess;
    
    @Value("${msg.upload.cover.image.failed}")
    private String msgUploadCoverImgFailed;
    
    @Value("${msg.register.card.success}")
    private String msgRegisterCardSuccess;
    
    @Value("${msg.get.cover.image.success}")
    private String msgGetCoverImgSuccess;
    
    @Value("${msg.get.user.success}")
    private String msgUserSuccess;
    
    @Value("${msg.password.wrong}")
    private String msgPasswordWrong;
    
    @Value("${msg.can.not.update.password}")
    private String msgCanNotUpdatePass;
    
    @Value("${msg.update.password.success}")
    private String msgUpdatePassSuccess;
    
    @Value("${msg.email.not.match}")
    private String msgEmailNotMatch;
    
    @Value("${msg.can.not.update.email}")
    private String msgCanNotUpdateEmail;
    
    @Value("${msg.update.email.success}")
    private String msgUpdateEmailSuccess;
    
    @Value("${msg.list.user.contact.success}")
    private String msgListUserContactSuccess;
    
    @Value("${msg.email.not.null}")
    private String msgEmailNotNull;
    
    @Value("${msg.password.not.null}")
    private String msgPasswordNotNull;
    
    @Value("${msg.password.not.match}")
    private String msgPasswordNotMatch;
    
    @Value("${scp.hostname}")
    private String scpHostName;
    
    @Value("${scp.user}")
    private String scpUser;
    
    @Value("${scp.password}")
    private String scpPassword;
    
    @Value("${pwd.expried.date}")
    private int expriedDate;
    
    @Value("${msg.account.locked}")
    private String msgAccountLocked;
    
    @Value("${msg.login.log}")
    private String msgLog;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public UserInfoResponse fetchAllUser(HttpServletRequest request)
    {
        SchemaContextHolder.setSchemaType(SchemaType.USER);
        
        StatusInfo statusInfo = null;        
        String token = request.getHeader(HEADER_TOKEN);        
        Boolean checkResult = userInfoService.checkToken(token);
        if (!checkResult){
        	statusInfo = new StatusInfo(Constants.ERROR, Constants.UNAUTHORIZED, this.msgTokenInvalid, token);            
        }

        try{
            List<UserInfo> userInfoList = userInfoService.getAllUserInfo();
            statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, this.msgListUserSuccess, token);            
        }catch(Exception ex){
        	statusInfo = new StatusInfo(Constants.ERROR, Constants.SERVER_ERROR, ex.getMessage(), token);            
        }
        userInfoRes.setStatusInfo(statusInfo);

        return userInfoRes;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public UserProfile authenticationUser(@RequestBody UserInfo userInfo, 
            HttpServletRequest request, HttpServletResponse response) 
            throws IOException, InvalidLoginException, NullPointerException {
        logger.debug("authenticationUser :", UserInfoController.class);
        UserProfile userProfileResponse = new UserProfile();
        TokenUtil tokenUtil = new TokenUtil();
        StatusInfo statusInfo = null;        
        try{
            SchemaContextHolder.setSchemaType(SchemaType.USER);
            
            boolean authenticated = tokenUtil.checkToken(request, response);
            
            if(!authenticated) {
                //Check user stop flag
                UserInfo userInfor = userInfoService.checkUserStopFlg(userInfo.getEmail());
                if(userInfor != null && userInfor.getUseStopFlg() != 0){
                	statusInfo = new StatusInfo(Constants.ERROR, Constants.UNAUTHORIZED, this.msgAccountLocked, "");                    
                    userProfileResponse.setStatusInfo(statusInfo);                    
                    return userProfileResponse;
                }
                
                //Check password is expried
                userProfileResponse = checkPasswordExpried(userInfo);
                Integer resetPasswordFlg = 0;
                if(userProfileResponse.getStatusInfo().getStatus() == 1){
                    resetPasswordFlg = 1;
                } else {
                    resetPasswordFlg = 0;
                }
                
                statusInfo = checkLogin(userInfo, request, response);
                if(statusInfo.getCode().equals("200") && statusInfo.getStatus().equals(0)){       
                    
                    AutoLogin token = userInfoService.findByToken(statusInfo.getToken());
                    Integer userId = token.getUserInfo().getUserId();
                    
                    //Save history
                    ActionLogId actionLogId = new ActionLogId();
                    actionLogId.setActionDate(new Date());
                    actionLogId.setActionMessage(this.msgLog);
                    actionLogId.setActionType(ActionLogType.LOGIN.getValue());
                    actionLogId.setUserId(userId);
                    
                    ActionLog actionLog = new ActionLog();
                    actionLog.setId(actionLogId);
                    userInfoService.saveActionLog(actionLog);
                    
                    userProfileResponse = userInfoService.getMailSettingInfo(userId);                    
                    CardInfoName myCard = homeService.getMyCardInfo(userId);
                    userProfileResponse.setCardInfoName(myCard);
                    userProfileResponse.setUserId(userId);
                    userProfileResponse.setResetPassword(resetPasswordFlg);
                    
                    UserInfo user = userInfoService.getUserInfoByUserId(userId);
                    userProfileResponse.setFirstLoginF(user.getFirstLoginF());
                    /*
                    UserInfo user = userInfoService.getUserInfoByUserId(userId);
                    if(user.getFirstLoginF() == 0){
                        userProfileResponse.setFirstLoginF(user.getFirstLoginF());
                        userInfoService.updateFisrtLogin(userId, 1);
                    }
                    else{
                        userProfileResponse.setFirstLoginF(user.getFirstLoginF());
                    }*/
                }
            }
        }
        catch(Exception ex){
            logger.debug("authenticationUser :", ex.getMessage());
            statusInfo = new StatusInfo(Constants.ERROR, Constants.UNAUTHORIZED, this.msgInvalidLogin, "");
        }
        userProfileResponse.setStatusInfo(statusInfo);
        return userProfileResponse;
    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public StatusInfo logoutUser(HttpServletRequest httpRequest){
        authenticationService.logout(httpRequest.getHeader(HEADER_TOKEN));
        StatusInfo statusInfo = null;
        statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, this.msgNoContent, "");        
        return statusInfo;
    }
    
    @RequestMapping(value = "/registerProfile", method = RequestMethod.POST)
    public StatusInfo registerProfile(@RequestBody UserInfo userInfo, HttpServletRequest request) throws Exception {
        logger.debug("authenticationUser :", UserInfoController.class);
        
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
            userInfo.setUserId(userId);
            
            SchemaContextHolder.setSchemaType(SchemaType.MANAGER);
            if(userInfoService.registerProfile(userInfo) == 1)
            {
            	statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, this.msgRegisterProfileSuccess, token);                
            }
        }
        catch(Exception ex){
        	statusInfo = new StatusInfo(Constants.ERROR, Constants.SERVER_ERROR, ex.getMessage(), token);            
        }
        return statusInfo;
    }
    
    @RequestMapping(value = "/registerCoverImage", method = RequestMethod.POST)
    public StatusInfo registerCoverImage(@RequestBody UserInfo userInfo, HttpServletRequest request) throws IOException, NullPointerException{
        logger.debug("registerCoverImage", UserInfoController.class);
        
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
        
        UploadFileUtil uploadFile = new UploadFileUtil();
        
        try {
            FileUploadModel uploadModel = uploadFile.uploadImage(userInfo.getCoverImage(), this.scpHostName, this.scpUser, this.scpPassword);            
            if(!uploadModel.isStatus()) {
            	statusInfo = new StatusInfo(Constants.ERROR, Constants.SERVER_ERROR, this.msgUploadCoverImgFailed, token);
            }
            else {
                SchemaContextHolder.setSchemaType(SchemaType.MANAGER);
                
                userInfoService.registerCoverImage(uploadModel.getFileName(), userId);
                statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, this.msgRegisterCardSuccess, token);                
            }
        }
        catch(Exception ex) {
        	statusInfo = new StatusInfo(Constants.ERROR, Constants.SERVER_ERROR, ex.getMessage(), token);            
        }
        return statusInfo;
    }
    
    @RequestMapping(value = "/getMyCoverImage", method = RequestMethod.GET)
    public MyCoverImageResponse getMyCoverImage(HttpServletRequest request) throws NullPointerException{
        logger.debug("registerCoverImage", UserInfoController.class);
        //Validate token        
        MyCoverImageResponse response = new MyCoverImageResponse();
        
        SchemaContextHolder.setSchemaType(SchemaType.USER);
        
        StatusInfo statusInfo = null;        
        String token = request.getHeader(HEADER_TOKEN);        
        Boolean checkResult = userInfoService.checkToken(token);
        if (!checkResult){
        	response.setStatusInfo(new StatusInfo(Constants.ERROR, Constants.UNAUTHORIZED, this.msgTokenInvalid, token));
            return response;
        }
        
        AutoLogin autoLogin = userInfoService.findByToken(token);
        Integer userId = autoLogin.getUserInfo().getUserId();
        
        try{
            response.setCoverImage(userInfoService.getCoverImage(userId));
            statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, this.msgGetCoverImgSuccess, token);
        }
        catch(Exception ex){
        	statusInfo = new StatusInfo(Constants.ERROR, Constants.SERVER_ERROR, ex.getMessage(), token);            
        }
        response.setStatusInfo(statusInfo);
        return response;
    }
    
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    public UserInfoDetailResponse getUserInfo(HttpServletRequest request) throws Exception{
        logger.debug("getUserInfo", UserInfoController.class);
        
        SchemaContextHolder.setSchemaType(SchemaType.USER);
                
        UserInfoDetailResponse response = new UserInfoDetailResponse();
        StatusInfo statusInfo = null;        
        String token = request.getHeader(HEADER_TOKEN);        
        Boolean checkResult = userInfoService.checkToken(token);
        if (!checkResult){
        	response.setStatusInfo(new StatusInfo(Constants.ERROR, Constants.UNAUTHORIZED, this.msgTokenInvalid, token));
            return response;
        }
        
        AutoLogin autoLogin = userInfoService.findByToken(token);
        Integer userId = autoLogin.getUserInfo().getUserId();
        
        try{
        	
            UserInfo userInfo = userInfoService.getUserInfoByUserId(userId);
            response.setUserInfo(UserInfoConverter.convertUserInforDetailList(userInfo));
            statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, this.msgUserSuccess, "");            
        }
        catch(Exception ex){
        	statusInfo = new StatusInfo(Constants.ERROR, Constants.SERVER_ERROR, ex.getMessage(), token);            
        }
        
        response.setStatusInfo(statusInfo);
        return response;
    }

    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public StatusInfo updatePassword(@RequestBody UpdatePassword updatePassword, HttpServletRequest request){
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
            String scpPassword = userInfoService.getPasswordByUserId(userId);
            Boolean result = new BCryptPasswordEncoder().matches(updatePassword.getPassword(), scpPassword);
            if(result == false) {
            	statusInfo = new StatusInfo(Constants.ERROR, Constants.UNAUTHORIZED, this.msgPasswordWrong, token);                
                return statusInfo;
            }
            
            result = checkStringMatch(updatePassword.getNewPassword(),updatePassword.getNewPasswordRetype());
            if(result == false){
            	statusInfo = new StatusInfo(Constants.ERROR, Constants.UNAUTHORIZED, this.msgPasswordNotMatch, token);                      
                return statusInfo;
            }
            
            SchemaContextHolder.setSchemaType(SchemaType.MANAGER);
            
            result = userInfoService.updatePassword(userId, new BCryptPasswordEncoder().encode(updatePassword.getNewPassword()));
            if(result == false){
            	statusInfo = new StatusInfo(Constants.ERROR, Constants.UNAUTHORIZED, this.msgCanNotUpdatePass, token);                
                return statusInfo;
            } else {
                //Set first login
                userInfoService.updateFisrtLogin(userId, 1);
            	statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, this.msgUpdatePassSuccess, token);                
            }
        } catch(Exception ex){
        	statusInfo = new StatusInfo(Constants.ERROR, Constants.SERVER_ERROR, ex.getMessage(), token);            
        }
        
        return statusInfo;
    }
    
    @RequestMapping(value = "/resetPassword", method = RequestMethod.GET)    
    public StatusInfo resetPassword(HttpServletRequest request,@RequestParam("email") final String email) throws MessagingException, IOException{
        SchemaContextHolder.setSchemaType(SchemaType.USER);

        UserInfo userInfo = userInfoService.findUserByEmail(email);
        StatusInfo statusInfo = null;
        try {
            String password = new BigInteger(130, new SecureRandom()).toString(32);
            userInfoService.updatePassword(userInfo.getUserId(), new BCryptPasswordEncoder().encode(password));
            userInfoService.updateUseStopFlg(userInfo.getUserId(), 2);
            /*Context ctx = new Context();
            ctx.setVariable("password", password);
            ctx.setVariable("recipientEmail", email);
            
            emailService.sendMail("IBS",email,"Reset Password",ctx, "recoverpass");*/
            statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, "New password will be send to "+email, password);            
        } catch(Exception ex){
        	statusInfo = new StatusInfo(Constants.ERROR, Constants.SERVER_ERROR, ex.getMessage(), "");            
        }
        
        return statusInfo;
    } 
    
    @RequestMapping(value = "/updateUserEmail", method = RequestMethod.POST)
    public StatusInfo updateUserEmail(@RequestBody UpdateUserEmail updateUserEmail, HttpServletRequest request){
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
            String scpPassword = userInfoService.getPasswordByUserId(userId);
            Boolean result = new BCryptPasswordEncoder().matches(updateUserEmail.getPassword(), scpPassword);
//            Boolean result = userInfoService.checkPassword(userId, updateUserEmail.getPassword());
            if(result == false) {
            	statusInfo = new StatusInfo(Constants.ERROR, Constants.UNAUTHORIZED, this.msgPasswordWrong, token);                
                return statusInfo;
            }
            
            result = checkStringMatch(updateUserEmail.getEmail(),updateUserEmail.getEmailRetype());
            if(result == false){
            	statusInfo = new StatusInfo(Constants.ERROR, Constants.BAD_REQUEST, this.msgEmailNotMatch, token);                
                return statusInfo;
            }
            
            SchemaContextHolder.setSchemaType(SchemaType.MANAGER);
            
            result = userInfoService.updateEmail(userId, updateUserEmail.getEmail());
            if(result == false){
            	statusInfo = new StatusInfo(Constants.ERROR, Constants.BAD_REQUEST, this.msgCanNotUpdateEmail, token);                
                return statusInfo;
            } else {
            	statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, this.msgUpdateEmailSuccess, token);                
            }
        } catch(Exception ex){
        	statusInfo = new StatusInfo(Constants.ERROR, Constants.SERVER_ERROR, ex.getMessage(), token);            
        }        
        return statusInfo;
    }
    
    @RequestMapping(value = "/getUserListContact", method = RequestMethod.GET)
    public UserListContactResponse getUserListContact(@RequestParam Integer cardId, HttpServletRequest request){
        logger.debug("getUserListContact", UserInfoController.class);
        
        SchemaContextHolder.setSchemaType(SchemaType.USER);
        
        UserListContactResponse response = new UserListContactResponse();
        
        StatusInfo statusInfo = null;        
        String token = request.getHeader(HEADER_TOKEN);        
        Boolean checkResult = userInfoService.checkToken(token);
        if (!checkResult){
        	response.setStatusInfo(new StatusInfo(Constants.ERROR, Constants.UNAUTHORIZED, this.msgTokenInvalid, token));
            return response;
        }

        try{
            List<UserListContact> userListContact = userInfoService.getUserListContact(cardId);
            
            if(userListContact.size() > 0) {
                response.setCardList(userListContact);
                statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, this.msgListUserContactSuccess, token);                
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
    
    @RequestMapping(value="/getUserListContactByDate" , method=RequestMethod.GET)
    public UserListContactResponse getUserListContactByDate(@RequestParam("cardId") Integer cardId, 
                                               @RequestParam("from") @DateTimeFormat(pattern="yyyy-MM-dd") Date fromDate,
                                               @RequestParam("to") @DateTimeFormat(pattern="yyyy-MM-dd") Date toDate,
                                               HttpServletRequest request) {
        SchemaContextHolder.setSchemaType(SchemaType.USER);
        
        UserListContactResponse response = new UserListContactResponse();
        StatusInfo statusInfo = null;        
        String token = request.getHeader(HEADER_TOKEN);        
        Boolean checkResult = userInfoService.checkToken(token);
        if (!checkResult){
        	response.setStatusInfo(new StatusInfo(Constants.ERROR, Constants.UNAUTHORIZED, this.msgTokenInvalid, token));
            return response;
        }

        try {
            List<UserListContact> userListContact = userInfoService.getUserListContactByDate(cardId,fromDate,toDate);
                       
            if(userListContact.size() > 0) {
                response.setCardList(userListContact);
                statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, this.msgListUserContactSuccess, token);                
            } else {
            	statusInfo = new StatusInfo(Constants.SUCCESS, Constants.NO_CONTENT, this.msgNoContent, token);                
            }
        } catch(Exception ex){
        	statusInfo = new StatusInfo(Constants.ERROR, Constants.SERVER_ERROR, ex.getMessage(), token);            
        }
        response.setStatusInfo(statusInfo);
        return response;
    }
    
    
    private StatusInfo checkLogin(UserInfo userInfo, HttpServletRequest httpRequest, HttpServletResponse httpResponse) 
            throws IOException, NullPointerException, InvalidLoginException{
        logger.debug("checkLogin :", UserInfoController.class);
        StatusInfo statusInfo = null;
        Validate.notNull(userInfo.getEmail(), this.msgEmailNotNull);
        Validate.notNull(userInfo.getPassword(), this.msgPasswordNotNull);
        
        try{
           statusInfo = authenticationService.authLogin(userInfo.getEmail(), userInfo.getPassword());
        }
        catch(Exception ex){
        	statusInfo = new StatusInfo(Constants.ERROR, Constants.UNAUTHORIZED, ex.getMessage() , "");
        }
        
        return statusInfo;
    }
    
    private Boolean checkStringMatch(String newStr, String newStrRetype){
        if(newStr.equals(newStrRetype)){
            return true;
        } else {
            return false;
        }
    }
    
    private UserProfile checkPasswordExpried(UserInfo userInfo){
        UserProfile userProfileResponse = new UserProfile();
        StatusInfo statusInfo = null;
        try{
            UserInfo user = userInfoService.getLastModifyDateByEmail(userInfo.getEmail());
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            String createDate = sdf.format(user.getLastChangePasswordDate());
            
            //System.out.println("createDate : "+createDate);
            Date fromDatabase = sdf.parse(createDate);
            Date now = new Date();
            long daysExpriedCondtion = TimeUnit.DAYS.convert(now.getTime() - fromDatabase.getTime(), TimeUnit.MILLISECONDS);
            //System.out.println("daysExpried : "+daysExpriedCondtion);
            int expriedDate = this.expriedDate;
            if(daysExpriedCondtion > expriedDate){
                userInfoService.updateFisrtLogin(userInfo.getUserId(), 0);                
//                userProfileResponse.setFirstLoginF(userInfo.getFirstLoginF());  
                statusInfo = new StatusInfo(Constants.ERROR, Constants.UNAUTHORIZED, "Password is expried" , "");                
            } else {
            	statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, "Password is not expried", "");                
            }
            
        }
        catch(Exception ex){
            logger.debug("checkPasswordExpried : ", ex.getMessage());
            statusInfo = new StatusInfo(Constants.ERROR, Constants.SERVER_ERROR, ex.getMessage(), "");
        }
        
        userProfileResponse.setStatusInfo(statusInfo);        
        return userProfileResponse;
    }
}
