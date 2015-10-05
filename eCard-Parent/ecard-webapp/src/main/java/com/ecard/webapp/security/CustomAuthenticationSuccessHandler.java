/**
 * 
 */
package com.ecard.webapp.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @author nhat.nguyen
 *
 */
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler  {
	
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
		 
		String redirectUrl = RoleType.getRedirectUrlByRoleName(mainRoles.get(0));
		String rootPath = request.getContextPath();
        if (redirectUrl == null){
        	 response.sendRedirect(rootPath + "/login");
        	 return;
        }
        
        response.sendRedirect(rootPath + redirectUrl);		
	}
		
}
