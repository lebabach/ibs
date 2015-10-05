/*
 * SearchInfoServiceImpl
 */
package com.ecard.core.service.impl;

import com.ecard.core.dao.SearchInfoDAO;
import com.ecard.core.model.UserSearch;
import com.ecard.core.service.SearchInfoService;
import com.ecard.core.vo.SearchInfo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vinhla
 */
@Service("searchInfoService")
@Transactional
public class SearchInfoServiceImpl implements SearchInfoService{
    @Autowired
    SearchInfoDAO searchInfoDAO;
    
    public Integer registerSearchText(UserSearch searchInfo) {
        return searchInfoDAO.registerSearchText(searchInfo);
    }
    
    public void createSearchText(UserSearch searchInfo){
        searchInfoDAO.createSearchText(searchInfo);
    }
    
    public void deleteSearchText(Integer userId, List<Integer> seqArray){
        searchInfoDAO.deleteSearchText(userId, seqArray);
    }
      
    public List<SearchInfo> listSearchText(Integer userId) {
        return searchInfoDAO.listSearchText(userId);
    }
}
