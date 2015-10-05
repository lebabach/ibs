package com.ecard.api.controller;

import com.ecard.api.controller.handler.RestExceptionHandler;
import com.ecard.api.controller.helper.Constants;
import com.ecard.core.datasource.SchemaContextHolder;
import com.ecard.core.datasource.type.SchemaType;
import com.ecard.core.model.AutoLogin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ecard.core.model.UserInfo;
import com.ecard.core.service.UserInfoService;
import com.ecard.core.service.converter.UserInfoConverter;
import com.ecard.core.vo.StatusInfo;
import com.ecard.core.vo.UserInfoDetailResponse;
import com.ecard.core.vo.UserInfoResponse;
import com.ecard.security.service.AuthenticationService;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles requests for the application home page.
 */
@RestController
@RequestMapping(value = "/api")
public class TestSchemaController extends RestExceptionHandler{
	
    private static final Logger logger = LoggerFactory.getLogger(TestSchemaController.class);
    
    private static final String HEADER_TOKEN = "token";
    
    @Autowired
    private UserInfoService userInfoService;
    
    @Value("${msg.token.invalid}")
    private String msgTokenInvalid;

    /**
     * 
     * @param schema = USER or MANAGER
     * @param request
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "/checkSchema/{schema}", method = RequestMethod.POST)
    public UserInfoDetailResponse checkSchema(@PathVariable String schema, HttpServletRequest request) throws Exception{
        
        UserInfoDetailResponse response = new UserInfoDetailResponse();
                
//        String permissionType = userInfoService.getPermisionTypeByUserId("USER");
        if(schema.equals(StringUtils.lowerCase(SchemaType.USER.name()))){
            SchemaContextHolder.setSchemaType(SchemaType.USER);
        }
        else{
            SchemaContextHolder.setSchemaType(SchemaType.MANAGER);
        }
        
        StatusInfo statusInfo = null;        
        String token = request.getHeader(HEADER_TOKEN);        
        Boolean checkResult = userInfoService.checkToken(token);
        if (!checkResult){
        	response.setStatusInfo(new StatusInfo(Constants.ERROR, Constants.UNAUTHORIZED, this.msgTokenInvalid, token));
            return response;
        }
        
        AutoLogin autoLogin = userInfoService.findByToken(token);
        Integer userId = autoLogin.getUserInfo().getUserId();
        
        UserInfo userInfo = userInfoService.getUserInfoByUserId(userId);
        response.setUserInfo(UserInfoConverter.convertUserInforDetailList(userInfo));
        statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, "List user success.", token);
        response.setStatusInfo(statusInfo);
        return response;
    }
}
