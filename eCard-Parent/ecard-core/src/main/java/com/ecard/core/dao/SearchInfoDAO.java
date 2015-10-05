/*
 * SearchInfoDAO class
 */
package com.ecard.core.dao;

import com.ecard.core.model.UserSearch;
import com.ecard.core.vo.SearchInfo;
import java.util.List;

/**
 *
 * @author vinhla
 */
public interface SearchInfoDAO {
    Integer registerSearchText(UserSearch searchInfo);
    
    void createSearchText(UserSearch searchInfo);
    
    void deleteSearchText(Integer userId, List<Integer> seqArray);
    
    List<SearchInfo> listSearchText(Integer userId);
}
