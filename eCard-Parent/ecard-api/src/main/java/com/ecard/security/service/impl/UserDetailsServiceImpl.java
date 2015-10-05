package com.ecard.security.service.impl;

import com.ecard.core.dao.UserInfoDAO;
import com.ecard.core.model.Roles;
import com.ecard.core.model.UserInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserInfoDAO userInfoDao;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo user = userInfoDao.findByEmail(username);

        if(user!=null){
            String password = user.getPassword();
            //additional information on the security object
            boolean enabled = true;
            boolean accountNonExpired = true;
            boolean credentialsNonExpired = true;
            boolean accountNonLocked = true;

            Roles role = userInfoDao.findRoleByUserId(user.getUserId());
            GrantedAuthority authority = new GrantedAuthorityImpl(role.getPermissionType());

            //Create Spring Security User object
            org.springframework.security.core.userdetails.User securityUser = new 
            org.springframework.security.core.userdetails.User(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, Arrays.asList(authority));
            return securityUser;
        }else{
            throw new UsernameNotFoundException("User Not Found!!!");
        }
    }

}
