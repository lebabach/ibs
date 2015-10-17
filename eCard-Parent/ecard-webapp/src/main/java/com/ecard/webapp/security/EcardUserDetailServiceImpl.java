/**
 * 
 */
package com.ecard.webapp.security;

import java.security.AccessControlException;
import java.util.ArrayList;
import java.util.Date;
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
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, AccessControlException {
		
		UserInfo userInfo = userInfoService.findUserByEmail(username);
		
		Date curDate = new Date();
        boolean isValidDate = false;
        
        if(userInfo.getUseDate().getTime() <= curDate.getTime() && curDate.getTime() <= userInfo.getEndDate().getTime()){
        	isValidDate = true;
        }
        
		if (userInfo == null || userInfo.getDeleteFlg() == 1 || userInfo.getUseStopFlg() == 1 || userInfo.getLeaveFlg() == 1) {
			throw new UsernameNotFoundException("User " + username + " not found");
		}
		
		if (!isValidDate) {
			throw new AccessControlException("Access denied");
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
