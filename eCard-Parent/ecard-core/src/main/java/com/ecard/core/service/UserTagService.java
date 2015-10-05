/*
 * UserTagService class
 */
package com.ecard.core.service;

import com.ecard.core.model.UserTag;
import com.ecard.core.vo.UserTagAndCardTag;
import java.util.List;

/**
 *
 * @author vinhla
 */
public interface UserTagService {
    void editUserTag(UserTag userTag);
    
    int registerUserTag(UserTag userTag);
    
    int deleteUserTag(Integer tagId);
    
    public List<UserTagAndCardTag> getListUserTagByUserId(Integer userId);
    
    public List<UserTag> listUserTag();
    
    public Integer checkUserTag(Integer userId, String tagName);
}
