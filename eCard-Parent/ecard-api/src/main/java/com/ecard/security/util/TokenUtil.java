package com.ecard.security.util;

import com.ecard.api.controller.handler.InvalidLoginException;
import com.ecard.api.controller.handler.RestExceptionHandler;
import com.ecard.core.model.AutoLogin;
import com.ecard.core.service.UserInfoService;
import com.ecard.security.service.AuthenticationService;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author vinhla
 */
public class TokenUtil extends RestExceptionHandler{
    private static final Logger logger = LoggerFactory.getLogger(TokenUtil.class);
    private static final String HEADER_TOKEN    = "token";
    
    @Autowired
    private AuthenticationService authenticationService;
    
    @Autowired
    UserInfoService userInfoService;
    
    /** Returns true, if request contains valid authentication token. */
    public boolean checkToken(HttpServletRequest httpRequest, HttpServletResponse httpResponse) 
            throws IOException, NullPointerException, InvalidLoginException {
        String token = httpRequest.getHeader(HEADER_TOKEN);
        
        if (token == null) {
            return false;
        }

        if (authenticationService.checkToken(token)) {
            logger.debug("  *** " + HEADER_TOKEN + " valid for:", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            return true;
        } else {
            logger.debug("  *** Invalid " + HEADER_TOKEN, token);
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
    }
    
    public Boolean validateToken(HttpServletRequest httpRequest) throws NullPointerException{
        try{
            AutoLogin authToken = userInfoService.findByToken(httpRequest.getHeader(HEADER_TOKEN));
            if(!httpRequest.getHeader(HEADER_TOKEN).equals(authToken))
                return false;
            else
                return true;
        }
        catch(Exception ex){
            logger.debug("validateToken :"+ ex.getMessage(), TokenUtil.class);
            return false;
        }
    }
}
