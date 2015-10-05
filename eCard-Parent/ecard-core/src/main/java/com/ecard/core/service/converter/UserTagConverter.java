/*
 * UserTagConverter
 */
package com.ecard.core.service.converter;

import com.ecard.core.vo.UserTag;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;

/**
 *
 * @author vinhla
 */
public final class UserTagConverter {
    public static List<UserTag>  convertUserTagList(List<com.ecard.core.model.UserTag> userTagModelList) 
                                            throws IllegalAccessException, InvocationTargetException{
        List<UserTag> userTagList = new ArrayList<UserTag>();
        if (CollectionUtils.isNotEmpty(userTagModelList)){
            UserTag userTag;
            for (com.ecard.core.model.UserTag userTagModel : userTagModelList) {
                userTag = new UserTag();

                userTag.setTagId(userTagModel.getTagId());
                userTag.setTagName(userTagModel.getTagName());

                userTagList.add(userTag);
            }
        }
        return userTagList;
    }
}
