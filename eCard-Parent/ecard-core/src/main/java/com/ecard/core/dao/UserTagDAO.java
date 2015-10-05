/*
 * UserTagDAO
 */
package com.ecard.core.dao;

import com.ecard.core.model.UserTag;
import com.ecard.core.vo.UserTagAndCardTag;
import java.util.List;

/**
 *
 * @author vinhla
 */
public interface UserTagDAO {
    void editUserTag(UserTag userTag);
    
    public int registerUserTag(UserTag userTag);
    
    public int deleteUserTag(Integer tagId);
    
    public List<UserTagAndCardTag> getListUserTagByUserId(Integer userId);
    
    List<UserTag> listUserTag();
    
    public Integer checkUserTag(Integer userId, String tagName);
}
