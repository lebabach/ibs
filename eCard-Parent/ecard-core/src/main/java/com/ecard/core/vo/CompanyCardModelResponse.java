/*
 * CompanyCardModelMapRes
 */
package com.ecard.core.vo;

import java.util.List;

/**
 *
 * @author vinhla
 */
public class CompanyCardModelResponse extends AbstractCommonRes{
    private List<CompanyCardModel> cardList;

    /**
     * @return the cardList
     */
    public List<CompanyCardModel> getCardList() {
        return cardList;
    }

    /**
     * @param cardList the cardList to set
     */
    public void setCardList(List<CompanyCardModel> cardList) {
        this.cardList = cardList;
    }

}
