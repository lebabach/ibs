/*
 * SearchInfoResponse
 */
package com.ecard.core.vo;

import java.util.List;

/**
 *
 * @author vinhla
 */
public class SearchInfoResponse extends AbstractCommonRes{
    private List<SearchInfo> searchTextList;

    /**
     * @return the searchTextList
     */
    public List<SearchInfo> getSearchTextList() {
        return searchTextList;
    }

    /**
     * @param searchTextList the searchTextList to set
     */
    public void setSearchTextList(List<SearchInfo> searchTextList) {
        this.searchTextList = searchTextList;
    }
}
