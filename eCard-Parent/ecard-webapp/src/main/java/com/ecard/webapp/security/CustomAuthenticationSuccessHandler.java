/**
 * 
 */
package com.ecard.webapp.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.ecard.core.model.ActionLog;
import com.ecard.core.model.ActionLogId;
import com.ecard.core.model.enums.ActionLogType;
import com.ecard.core.service.UserInfoService;

/**
 * @author nhat.nguyen
 *
 */
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler  {
	@Autowired
    private UserInfoService userInfoService;
	
//	@Value("${msg.login.log}")
//    private String msgLog;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

		List<String> mainRoles = new ArrayList<String>();
		
		// if user has an administrator role in roles then redirect to manage page
		if (roles.size() > 1 && roles.contains("ROLE_USER")){
			for (String role : roles) {
				if (!"ROLE_USER".equals(role)){
					mainRoles.add(role);
					break;
				}
			}
		} else {
			mainRoles.addAll(roles);
		}

		 //Save history
		EcardUser ecardUser = (EcardUser) authentication.getPrincipal();
        ActionLogId actionLogId = new ActionLogId();
        actionLogId.setActionDate(new Date());
        actionLogId.setActionMessage("ログイン");
        actionLogId.setActionType(ActionLogType.LOGIN.getValue());
        actionLogId.setUserId(ecardUser.getUserId());
        
        ActionLog actionLog = new ActionLog();
        actionLog.setId(actionLogId);
        userInfoService.saveActionLog(actionLog);
        
		String redirectUrl = RoleType.getRedirectUrlByRoleName(mainRoles.get(0));
		String rootPath = request.getContextPath();
        if (redirectUrl == null){
        	 response.sendRedirect(rootPath + "/login");
        	 return;
        }
        
        response.sendRedirect(rootPath + redirectUrl);		
	}
		
}
