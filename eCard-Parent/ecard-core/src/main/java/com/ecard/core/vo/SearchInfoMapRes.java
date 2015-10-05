/*
 * SearchInfoMapRes
 */
package com.ecard.core.vo;

import com.ecard.core.vo.AbstractCommonRes;
import java.util.List;

/**
 *
 * @author vinhla
 */
public class SearchInfoMapRes extends AbstractCommonRes{
    private List<SearchInfoMap> searchTextList;

    /**
     * @return the searchTextList
     */
    public List<SearchInfoMap> getSearchTextList() {
        return searchTextList;
    }

    /**
     * @param searchTextList the searchTextList to set
     */
    public void setSearchTextList(List<SearchInfoMap> searchTextList) {
        this.searchTextList = searchTextList;
    }
}
