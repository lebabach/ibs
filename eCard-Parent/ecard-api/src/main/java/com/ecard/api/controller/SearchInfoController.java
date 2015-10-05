/*
 * SearchInfoController
 */
package com.ecard.api.controller;

import com.ecard.api.controller.helper.Constants;
import com.ecard.core.datasource.SchemaContextHolder;
import com.ecard.core.datasource.type.SchemaType;
import com.ecard.core.model.AutoLogin;
import com.ecard.core.model.UserInfo;
import com.ecard.core.model.UserSearch;
import com.ecard.core.service.SearchInfoService;
import com.ecard.core.service.UserInfoService;
import com.ecard.core.vo.SearchInfo;
import com.ecard.core.vo.SearchInfoResponse;
import com.ecard.core.vo.SequenceArray;
import com.ecard.core.vo.StatusInfo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
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
public class SearchInfoController {
    private static final Logger logger = LoggerFactory.getLogger(CardInfoController.class);
    
    private static final String HEADER_TOKEN = "token";
    
    @Autowired
    UserInfoService userInfoService;
    
    @Autowired
    SearchInfoService searchInfoService;
    
    @Value("${msg.token.invalid}")
    private String msgTokenInvalid;
    
    @Value("${msg.token.not.null}")
    private String msgTokenNotNull;
    
    @Value("${msg.register.search.text.success}")
    private String msgRegisterSearchTextSuccess;
    
    @Value("${msg.userId.not.null}")
    private String msgUserIdNotNull;
    
    @Value("${msg.get.search.text.success}")
    private String msgGetSearchTextSuccess;
    
    @Value("${msg.delete.search.text.success}")
    private String msgDeleteSearchTextSuccess;
  
    @Value("${msg.no.add.more}")
    private String msgNoAddMore;
    
    @Value("${msg.no.content}")
    private String msgNoContent;
    
    @RequestMapping(value = "/registerSearchText", method = RequestMethod.POST)
    public StatusInfo registerSearchText(@RequestBody UserSearch userSearch, HttpServletRequest request) throws IOException {
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
//        SearchInfoResponse searchInfoRes = new SearchInfoResponse();
       
        try {
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(userId);
            userSearch.setUserId(userId);
            userSearch.setUserInfo(userInfo);
            if(userSearch.getTitle() == null)
                userSearch.setTitle("");
            if(userSearch.getOwner() == null)
                userSearch.setOwner("");
            if(userSearch.getCompany() == null)
                userSearch.setCompany("");
            if(userSearch.getDepartment() == null)
                userSearch.setDepartment("");  
            if(userSearch.getPosition() == null)
                userSearch.setPosition(""); 
            if(userSearch.getName() == null)
                userSearch.setName("");
            if(userSearch.getSeq() == 0){
                List<SearchInfo> listSearchInfo = searchInfoService.listSearchText(userId);
                List<Integer> seqList = new ArrayList<Integer>();
                for (SearchInfo s : listSearchInfo){
                    seqList.add(s.getSeq());
                }
                int seq = getSequeceFromList(seqList);
                if(seq >= 6){
                	statusInfo = new StatusInfo(Constants.SUCCESS, Constants.NO_CONTENT, this.msgNoAddMore, token);                    
                    return statusInfo;
                } else {
                    userSearch.setSeq(seq);
                }                
            }
            
            SchemaContextHolder.setSchemaType(SchemaType.MANAGER);
            int response = searchInfoService.registerSearchText(userSearch);
            if(response == 0)
                searchInfoService.createSearchText(userSearch);
            statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, this.msgRegisterSearchTextSuccess, token);            
        } catch(Exception ex){
        	statusInfo = new StatusInfo(Constants.ERROR, Constants.SERVER_ERROR, ex.getMessage(), token);            
        }
//        searchInfoRes.setStatusInfo(statusInfo);
        return statusInfo;
        
    }
    
    @RequestMapping(value = "/listSearchText", method = RequestMethod.GET) 
    public SearchInfoResponse listSearchText(HttpServletRequest request) {
        SchemaContextHolder.setSchemaType(SchemaType.USER);
        
        SearchInfoResponse response = new SearchInfoResponse();
        //Validate token
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
            List<SearchInfo> listSearchInfo = searchInfoService.listSearchText(userId);
            response.setSearchTextList(listSearchInfo);
            statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, this.msgGetSearchTextSuccess, token);
        }
        catch(Exception ex) {
        	statusInfo = new StatusInfo(Constants.ERROR, Constants.SERVER_ERROR, ex.getMessage(), token);            
        }
        response.setStatusInfo(statusInfo);
        return response;
    }

    @RequestMapping(value = "/deleteSearchText", method = RequestMethod.POST)
    public StatusInfo deleteSearchText(@RequestBody SequenceArray seq, HttpServletRequest request) throws IOException {
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
            List<Integer> seqArray = convertToInteger(seq.getSeq());
            
            SchemaContextHolder.setSchemaType(SchemaType.MANAGER);
            searchInfoService.deleteSearchText(userId, seqArray);
            statusInfo = new StatusInfo(Constants.SUCCESS, Constants.STATUS_200, this.msgDeleteSearchTextSuccess, token);            
        } catch(Exception ex){
        	statusInfo = new StatusInfo(Constants.ERROR, Constants.SERVER_ERROR, ex.getMessage(), token);            
        }
        return statusInfo;        
    }

    private List<Integer> convertToInteger(String seq){      
        String[] seqStr = seq.split(",");
        int i=0;
        Integer[] intarray=new Integer[seqStr.length];
        for (String myStr : seqStr) {
            try {
                intarray[i]=Integer.parseInt(myStr);
                i++;
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Not a number: " + myStr + " at index " + i, e);
            }
        }
         List<Integer> seqList = Arrays.asList(intarray);
        return seqList;
    }
    
    private int getSequeceFromList(List<Integer> myCoord){
        List<Integer> myCoords = new ArrayList<Integer>();
        myCoords.add(1);
        myCoords.add(2);
        myCoords.add(3);
        myCoords.add(4);
        myCoords.add(5);   

        List<Integer> matches = new ArrayList<Integer>();
        int i = 1;
        for (Integer s : myCoords)
            matches.add(myCoord.contains(s) ? 1 : 0);        
        int seq = myCoord.size()+1;
        for (Integer s : matches){            
            if(s.equals(0)){
                seq = i;
                return seq;
            }
            i++;
        }  
        return seq;
    }
}
