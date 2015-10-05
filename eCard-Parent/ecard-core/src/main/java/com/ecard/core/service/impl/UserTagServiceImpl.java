/*
 * UserTagServiceImpl class
 */
package com.ecard.core.service.impl;

import com.ecard.core.dao.UserTagDAO;
import com.ecard.core.model.UserTag;
import com.ecard.core.service.UserTagService;
import com.ecard.core.vo.UserTagAndCardTag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vinhla
 */
@Service("userTagService")
@Transactional
public class UserTagServiceImpl implements UserTagService {
    
    @Autowired
    UserTagDAO userTagDAO;
    
    public void editUserTag(UserTag userTag) {
        userTagDAO.editUserTag(userTag);
    }
    
    public int registerUserTag(UserTag userTag){
        return userTagDAO.registerUserTag(userTag);
    }
    
    public int deleteUserTag(Integer tagId) {
        return userTagDAO.deleteUserTag(tagId);
    }
    
    public List<UserTagAndCardTag> getListUserTagByUserId(Integer userId) {
        return userTagDAO.getListUserTagByUserId(userId);
    }
    
    public List<UserTag> listUserTag() {
        return userTagDAO.listUserTag();
    }
    
    public Integer checkUserTag(Integer userId, String tagName){
    	return userTagDAO.checkUserTag(userId, tagName);
    }
    
}
