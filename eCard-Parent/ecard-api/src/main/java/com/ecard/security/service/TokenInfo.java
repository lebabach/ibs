/**
 * 
 */
package com.ecard.security.service;

import java.util.Date;

/**
 * @author Windows
 *
 */
public class TokenInfo {
	private final long created = System.currentTimeMillis();
	private final String token;
	// TODO expiration etc

	public TokenInfo(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	@Override
	public String toString() {
		return "TokenInfo{" +
			"token='" + token + '\'' +
			", created=" + new Date(created) +
			'}';
	}
}
