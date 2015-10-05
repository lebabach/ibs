/**
 * 
 */
package com.ecard.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.ecard.security.service.AuthenticationService;
import com.ecard.security.service.TokenInfo;

/**
 * @author nhat.nguyen
 *
 */
public class CustomTokenAuthenticationFilter extends GenericFilterBean {
    private static final String HEADER_TOKEN = "X-Auth-Token";
    private static final String HEADER_USERNAME = "X-Username";
    private static final String HEADER_PASSWORD = "X-Password";

    /** Request attribute that indicates that this filter will not continue with the chain. Handy after login/logout, etc. */
    private boolean isContinueWithChain = true;

    private final String logoutLink;

    @Autowired
    private AuthenticationService authenticationService;

    public CustomTokenAuthenticationFilter(String logoutLink) {
            this.logoutLink = logoutLink;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println(" *** CustomTokenAuthenticationFilter.doFilter");
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        this.isContinueWithChain = true;
        boolean authenticated = checkToken(httpRequest, httpResponse);

        if (this.isContinueWithChain && httpRequest.getMethod().equals("POST")) {
            // If we're not authenticated, we don't bother with logout at all.
            // Logout does not work in the same request with login - this does not make sense, because logout works with token and login returns it.
            if (authenticated) {
                if (currentLink(httpRequest).equals(logoutLink)) {
                    checkLogout(httpRequest);
                }
            } else {
                // Login works just fine even when we provide token that is valid up to this request, because then we get a new one.
                checkLogin(httpRequest, httpResponse);
            }
        }

        if (this.isContinueWithChain) {
            chain.doFilter(request, response);
        }
        System.out.println(" === AUTHENTICATION: " + SecurityContextHolder.getContext().getAuthentication());
    }

    private void checkLogin(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {
        String username = httpRequest.getHeader(HEADER_USERNAME);
        String password = httpRequest.getHeader(HEADER_PASSWORD);

        if (username != null && password != null) {
            checkUsernameAndPassword(username, password, httpResponse);
            this.isContinueWithChain = false;
        }
    }

    private void checkUsernameAndPassword(String username, String password, HttpServletResponse httpResponse) throws IOException {
        TokenInfo tokenInfo = authenticationService.authenticate(username, password);
        if (tokenInfo != null) {
            httpResponse.setHeader(HEADER_TOKEN, tokenInfo.getToken());
            // TODO set other token information possible: IP, ...
        } else {
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    /** Returns true, if request contains valid authentication token. */
    private boolean checkToken(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {
        String token = httpRequest.getHeader(HEADER_TOKEN);
        if (token == null) {
                return false;
        }

        if (authenticationService.checkToken(token)) {
                System.out.println(" *** " + HEADER_TOKEN + " valid for: " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
                return true;
        } else {
                System.out.println(" *** Invalid " + HEADER_TOKEN + ' ' + token);
                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                this.isContinueWithChain = false;
        }
        return false;
    }

    private void checkLogout(HttpServletRequest httpRequest) {		
        String token = httpRequest.getHeader(HEADER_TOKEN);
        // we go here only authenticated, token must not be null
        authenticationService.logout(token);
        this.isContinueWithChain = false;
    }

    // or use Springs util instead: new UrlPathHelper().getPathWithinApplication(httpRequest)
    // shame on Servlet API for not providing this without any hassle :-(
    private String currentLink(HttpServletRequest httpRequest) {
        if (httpRequest.getPathInfo() == null) {
                return httpRequest.getServletPath();
        }
        return httpRequest.getServletPath() + httpRequest.getPathInfo();
    }
}
