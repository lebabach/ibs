/**
 * 
 */
package com.ecard.security.service;

import com.ecard.core.vo.StatusInfo;

/**
 * @author vinhla
 *
 */
public interface AuthenticationService {
	/**
	 * Authenticates the user and returns valid token. If anything fails, {@code null} is returned instead.
	 * Prepares {@link org.springframework.security.core.context.SecurityContext} if authentication succeeded.
	 */
	TokenInfo authenticate(String email, String password);

	/**
	 * Checks the authentication token and if it is valid prepares
	 * {@link org.springframework.security.core.context.SecurityContext} and returns true.
	 */
	boolean checkToken(String token);

	/** Logouts the user - token is invalidated/forgotten. */
	void logout(String token);
        
        public StatusInfo authLogin(String email, String password);

}
