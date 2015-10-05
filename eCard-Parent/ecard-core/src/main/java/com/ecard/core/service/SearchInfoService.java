/*
 * SearchInfoService
 */
package com.ecard.core.service;

import com.ecard.core.model.UserSearch;
import com.ecard.core.vo.SearchInfo;
import java.util.List;

/**
 *
 * @author vinhla
 */
public interface SearchInfoService {
    public Integer registerSearchText(UserSearch searchInfo);
    
    public void createSearchText(UserSearch searchInfo);
    
    public void deleteSearchText(Integer userId, List<Integer> seqArray);
    
    public List<SearchInfo> listSearchText(Integer userId);
}
