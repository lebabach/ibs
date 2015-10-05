/*
 * PossessionCardInfoMapperResp
 */
package com.ecard.core.vo;

import com.ecard.core.vo.AbstractCommonRes;
import java.util.List;

/**
 *
 * @author vinhla
 */
public class PossessionCardInfoResp extends AbstractCommonRes {
    private List<PosessionCardInfo> cardList;

    /**
     * @return the cardList
     */
    public List<PosessionCardInfo> getCardList() {
        return cardList;
    }

    /**
     * @param cardList the cardList to set
     */
    public void setCardList(List<PosessionCardInfo> cardList) {
        this.cardList = cardList;
    }
}
