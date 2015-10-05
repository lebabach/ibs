/**
 * 
 */
package com.ecard.webapp.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ecard.core.model.UserInfo;
import com.ecard.core.service.UserInfoService;

/**
 * @author nhat.nguyen
 *
 */
public class EcardUserDetailServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserInfoService userInfoService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserInfo userInfo = userInfoService.findUserByEmail(username);
		
		if (userInfo == null || userInfo.getDeleteFlg() == 1 || userInfo.getUseStopFlg() == 1) {
			throw new UsernameNotFoundException("User " + username + " not found");
		}

		List<Integer> permissionTypes = userInfoService.getPermisionTypeByUserId(userInfo.getUserId());
		List<String> roleNames = new ArrayList<String>();
		for(Integer permissionType : permissionTypes) {
			String roleName = RoleType.getEnumNameForValue(permissionType.intValue());
			roleNames.add(roleName);
		}
		
        
        UserDetails user = new EcardUser(userInfo.getEmail(), userInfo.getPassword(), roleNames, userInfo.getUserId(), userInfo.getGroupCompanyId(), null);       

		return user;
	}

}
