/**
 * 
 */
package com.ecard.security.service.impl;

import com.ecard.api.controller.handler.InvalidLoginException;
import com.ecard.api.controller.handler.RestExceptionHandler;
import com.ecard.core.model.AutoLogin;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.codec.Base64;

import com.ecard.core.model.UserInfo;
import com.ecard.core.service.UserInfoService;
import com.ecard.core.vo.StatusInfo;
import com.ecard.security.service.AuthenticationService;
import com.ecard.security.service.TokenInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Service responsible for all around authentication, token checks, etc.
 * This class does not care about HTTP protocol at all.
 * @author: nhat.nguyen
 */
public class AuthenticationServiceImpl extends RestExceptionHandler implements AuthenticationService {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);
    private static final String ECARD_USER_ROLE = "ROLE_USER";

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private UserInfoService userInfoService;


    @PostConstruct
    public void init() {
        logger.debug("*** AuthenticationServiceImpl.init with:", applicationContext);
    }

    @Override
    public TokenInfo authenticate(String email, String password) throws NullPointerException, InvalidLoginException{
        logger.debug(" *** AuthenticationServiceImpl.authenticate", AuthenticationServiceImpl.class);

        UserInfo userInfo = userInfoService.findUserByEmail(email);
        if (userInfo == null){
                return null;
        }

        try {		
            if (userInfo.getPassword().equalsIgnoreCase(password)){			
                // create new token
                String newToken = generateToken();
                // save token into database
                userInfoService.createUserToken(userInfo.getUserId(), newToken);

                // set authenticated
                GrantedAuthority role = new SimpleGrantedAuthority(ECARD_USER_ROLE);
                // Here principal=username, credentials=password
                Authentication authentication = new UsernamePasswordAuthenticationToken(email, password, Arrays.asList(role));

                SecurityContextHolder.getContext().setAuthentication(authentication);

                return new TokenInfo(newToken);
            }		
        } catch (Exception e) {
            logger.debug("*** AuthenticationServiceImpl.authenticate - FAILED:", e.getMessage());
            throw e;
        }
        return null;
    }

    @Override
    public boolean checkToken(String token) {
        logger.debug("*** AuthenticationServiceImpl.checkToken: ", AuthenticationServiceImpl.class);

        AutoLogin userToken = userInfoService.findByToken(token);
        if (userToken == null) {
                return false;
        }
        GrantedAuthority role = new SimpleGrantedAuthority(ECARD_USER_ROLE);

        UsernamePasswordAuthenticationToken securityToken = new UsernamePasswordAuthenticationToken(null, null, Arrays.asList(role));
        SecurityContextHolder.getContext().setAuthentication(securityToken);

        return true;
    }

    @Override
    public void logout(String token) {
        logger.debug("*** AuthenticationServiceImpl.logout:", AuthenticationServiceImpl.class);
        userInfoService.removeUserToken(token);
        SecurityContextHolder.clearContext();
    }

    private String generateToken() {
        byte[] tokenBytes = new byte[32];
        new SecureRandom().nextBytes(tokenBytes);
        return new String(Base64.encode(tokenBytes), StandardCharsets.UTF_8);
    }

    public StatusInfo authLogin(String email, String password){
        StatusInfo statusInfo = new StatusInfo();
        UserInfo userInfo = userInfoService.findUserByEmail(email);
        System.out.println("userInfoSerivce"+userInfo);
        if (userInfo.getEmail() == null || userInfo.getLeaveFlg() == 1 || userInfo.getDeleteFlg() == 1){
            statusInfo.setStatus(1);
            statusInfo.setCode("401");
            statusInfo.setMsg("User is not exist");
            statusInfo.setToken("");
            return statusInfo;
        }
        try {		
            if ((new BCryptPasswordEncoder()).matches(password, userInfo.getPassword())){
                // create new token
                String newToken = generateToken();
                // save token into database
                userInfoService.createUserToken(userInfo.getUserId(), newToken);

                // set authenticated
                GrantedAuthority role = new SimpleGrantedAuthority(ECARD_USER_ROLE);
                // Here principal=username, credentials=password
                Authentication authentication = new UsernamePasswordAuthenticationToken(email, userInfo.getPassword(), Arrays.asList(role));

                SecurityContextHolder.getContext().setAuthentication(authentication);

                statusInfo.setStatus(0);
                statusInfo.setCode("200");
                statusInfo.setMsg("Login sucessful");
                statusInfo.setToken(new TokenInfo(newToken).getToken());
                
            } else {
                statusInfo.setStatus(1);
                statusInfo.setCode("401");
                statusInfo.setMsg("Password is wrong");
                statusInfo.setToken("");
            }		
        } catch (Exception e) {
            logger.debug("*** AuthenticationServiceImpl.authenticate - FAILED:", e.getMessage());
            throw e;
        }
        return statusInfo;
    }
}
