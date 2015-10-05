/**
 * 
 */
package com.ecard.webapp.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author nhat.nguyen
 *
 */
public class EcardUser implements UserDetails {
	
	private String userName;
	private String password;
	private List<String> roles;
	private Integer userId;	
	private Integer groupCompanyId;
	private String fullName;
	
	public EcardUser(String userName, String password, List<String> roles, Integer userId, Integer groupCompanyId, String fullName){
		this.userName = userName;
		this.password = password;
		this.roles = roles;
		this.userId = userId;
		this.groupCompanyId = groupCompanyId;
		this.fullName = fullName;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(this.roles)){
			for (String role : this.roles) {
				authorities.add(new SimpleGrantedAuthority(role));
			}
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}	

	public Integer getUserId() {
		return userId;
	}

	public String getFullName() {
		return fullName;
	}

	public Integer getGroupCompanyId() {
		return groupCompanyId;
	}

	public void setGroupCompanyId(Integer groupCompanyId) {
		this.groupCompanyId = groupCompanyId;
	}

}
