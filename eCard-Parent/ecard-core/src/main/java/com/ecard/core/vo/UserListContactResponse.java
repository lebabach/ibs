/*
 * UserListContactResponse
 */
package com.ecard.core.vo;

import java.util.List;

/**
 *
 * @author vinhla
 */
public class UserListContactResponse extends AbstractCommonRes{
    private List<UserListContact> cardList;

    /**
     * @return the cardList
     */
    public List<UserListContact> getCardList() {
        return cardList;
    }

    /**
     * @param cardList the cardList to set
     */
    public void setCardList(List<UserListContact> cardList) {
        this.cardList = cardList;
    }
}
