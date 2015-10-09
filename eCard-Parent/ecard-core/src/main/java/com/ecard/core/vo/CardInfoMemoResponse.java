/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecard.core.vo;
import java.util.List;
/**
 *
 * @author admin
 */
public class CardInfoMemoResponse extends AbstractCommonRes{
    private List<CardInfoMemo> searchTextList;
    private int seq;

//    /**
//     * @return the searchTextList
//     */
//    public List<CardInfoMemo> getCardList() {
//        return getSearchTextList();
//    }
//
//    /**
//     * @param searchTextList the searchTextList to set
//     */
//    public void setCardList(List<CardInfoMemo> searchTextList) {
//        this.setSearchTextList(searchTextList);
//    }

    /**
     * @return the searchTextList
     */
    public List<CardInfoMemo> getSearchTextList() {
        return searchTextList;
    }

    /**
     * @param searchTextList the searchTextList to set
     */
    public void setSearchTextList(List<CardInfoMemo> searchTextList) {
        this.searchTextList = searchTextList;
    }

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}
    
    
}
