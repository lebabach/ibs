/*
 * CardUpdateHistoryConverter
 */
package com.ecard.core.service.converter;

import com.ecard.core.vo.CardUpdateHisAndUserInfo;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;

/**
 *
 * @author vinhla
 */
public class CardUpdateHistoryConverter {
    
    public static List<CardUpdateHisAndUserInfo>  convertCardConnectUserList(List<com.ecard.core.vo.CardUpdateHisAndUserInfo> cardInfoModelList) 
            throws IllegalAccessException, InvocationTargetException{
        List<CardUpdateHisAndUserInfo> cardInfoList = new ArrayList<CardUpdateHisAndUserInfo>();
        if (CollectionUtils.isNotEmpty(cardInfoModelList)){
            CardUpdateHisAndUserInfo cardInfo;
            for (com.ecard.core.vo.CardUpdateHisAndUserInfo cardInfoModel : cardInfoModelList) {
                cardInfo = new CardUpdateHisAndUserInfo();
                cardInfo.setCardId(cardInfoModel.getCardId());
                cardInfo.setParamText(cardInfoModel.getParamText());
                cardInfo.setOldData(cardInfoModel.getOldData());
                cardInfo.setNewData(cardInfoModel.getNewData());
                cardInfo.setCreateDate(cardInfoModel.getCreateDate());
                cardInfo.setOperaterId(cardInfoModel.getOperaterId());
                cardInfo.setOperaterName(cardInfoModel.getOperaterName());
                cardInfoList.add(cardInfo);
            }
        }
        return cardInfoList;
    }
}
