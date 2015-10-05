/*
 * GroupCompanyInfoController
 */
package com.ecard.api.controller;

import com.ecard.api.controller.handler.RestExceptionHandler;
import com.ecard.api.controller.helper.Constants;
import com.ecard.core.datasource.SchemaContextHolder;
import com.ecard.core.datasource.type.SchemaType;
import com.ecard.core.model.AutoLogin;
import com.ecard.core.model.GroupCompanyDepartment;
import com.ecard.core.model.GroupCompanyInfo;
import com.ecard.core.service.GroupCompanyInfoService;
import com.ecard.core.service.UserInfoService;
import com.ecard.core.service.converter.GroupCompanyInfoConverter;
import com.ecard.core.vo.GroupCompanyDepartmentResponse;
import com.ecard.core.vo.GroupCompanyInfoResponse;
import com.ecard.core.vo.GroupDepartmentVO;
import com.ecard.core.vo.StatusInfo;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.Validate;
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
public class GroupCompanyInfoController extends RestExceptionHandler{
    private static final Logger logger = LoggerFactory.getLogger(GroupCompanyInfoController.class);
    
    private static final String HEADER_TOKEN = "token";
        
    @Autowired
    GroupCompanyInfoService groupCompanyInfoService;
    
    @Autowired
    UserInfoService userInfoService;
    
    @Value("${msg.token.invalid}")
    private String msgTokenInvalid;
    
    @Value("${msg.list.group.company.success}")
    private String msgListGroupCompanySuccess;
    
    @RequestMapping(value = "/groupCompanyInfoList", method = RequestMethod.GET) 
    public GroupCompanyInfoResponse groupCompanyInfoList(HttpServletRequest request) {
        logger.debug("groupCompanyInfoList", GroupCompanyInfoController.class);
        SchemaContextHolder.setSchemaType(SchemaType.USER);
        GroupCompanyInfoResponse groupCompanyInfoResponse = new GroupCompanyInfoResponse();
        //Validate token

        StatusInfo statusInfo = null;        
        String token = request.getHeader(HEADER_TOKEN);        
        Boolean checkResult = userInfoService.checkToken(token);
        if (!checkResult){
        	groupCompanyInfoResponse.setStatusInfo(new StatusInfo(Constants.ERROR, Constants.UNAUTHORIZED, this.msgTokenInvalid, token));
            return groupCompanyInfoResponse;
        }
        
        try{
            List<GroupCompanyInfo> groupCompanyInfoList = groupCompanyInfoService.getListCompany();
            groupCompanyInfoResponse.setGroupCompanyInfoList(GroupCompanyInfoConverter.convertGroupCompanyInfoList(groupCompanyInfoList));
            statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, this.msgListGroupCompanySuccess, token);            
        }
        catch(Exception ex){
            logger.debug("Exception : ", ex.getMessage());
            statusInfo = new StatusInfo(Constants.ERROR, Constants.SERVER_ERROR, ex.getMessage(), token);            
        }
        groupCompanyInfoResponse.setStatusInfo(statusInfo);
        
        return groupCompanyInfoResponse;
    }
    
    @RequestMapping(value = "/groupCompanyDepartmentById", method = RequestMethod.GET) 
    public GroupCompanyDepartmentResponse groupCompanyDepartmentById(@RequestParam Integer groupCompanyId, HttpServletRequest request) {
        logger.debug("groupCompanyDepartmentById", GroupCompanyInfoController.class);
        
        SchemaContextHolder.setSchemaType(SchemaType.USER);
        
        Validate.notNull(groupCompanyId, "groupCompanyId is not null");
        GroupCompanyDepartmentResponse companyDepartmentResponse = new GroupCompanyDepartmentResponse();
        //Validate token
        StatusInfo statusInfo = null;        
        String token = request.getHeader(HEADER_TOKEN);        
        Boolean checkResult = userInfoService.checkToken(token);
        if (!checkResult){
        	companyDepartmentResponse.setStatusInfo(new StatusInfo(Constants.ERROR, Constants.UNAUTHORIZED, this.msgTokenInvalid, token));
            return companyDepartmentResponse;
        }
        
        try{
            List<GroupDepartmentVO> groupDepartmentVoList = groupCompanyInfoService.getCompanyDepartmentById(groupCompanyId);
            companyDepartmentResponse.setCompanyDepartmentList(groupDepartmentVoList);
            statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, this.msgListGroupCompanySuccess, token);            
        }
        catch(Exception ex){
            logger.debug("Exception : ", ex.getMessage());
            statusInfo = new StatusInfo(Constants.ERROR, Constants.SERVER_ERROR, ex.getMessage(), token);            
        }
        companyDepartmentResponse.setStatusInfo(statusInfo);
        
        return companyDepartmentResponse;
    }
}
