/*
 * CardInfoDetailConverter class
 */
package com.ecard.core.service.converter;

import com.ecard.core.vo.CardInfoDetail;

/**
 *
 * @author vinhla
 */
public final class CardInfoDetailConverter {
    public static CardInfoDetail  convertCardInforDetail(com.ecard.core.model.CardInfo cardInfoModel, Boolean isSameCompany) {
        CardInfoDetail response = new CardInfoDetail();
        response.setCardId(cardInfoModel.getCardId());
        response.setAddress1(cardInfoModel.getAddress1());
        response.setAddress2(cardInfoModel.getAddress2());
        response.setAddress3(cardInfoModel.getAddress3());
        response.setAddress4(cardInfoModel.getAddress4());
        response.setCompanyName(cardInfoModel.getCompanyName());
        response.setCompanyNameKana(cardInfoModel.getCompanyNameKana());
        response.setCompanyUrl(cardInfoModel.getCompanyUrl());
        response.setDepartmentName(cardInfoModel.getDepartmentName());
        response.setEmail(cardInfoModel.getEmail());
        response.setFaxNumber(cardInfoModel.getFaxNumber());
        response.setImageFile(cardInfoModel.getImageFile());
        response.setMobileNumber(cardInfoModel.getMobileNumber());
        response.setName(cardInfoModel.getName());
        response.setFirstName(cardInfoModel.getFirstName());
        response.setLastName(cardInfoModel.getLastName());
        response.setNameKana(cardInfoModel.getNameKana());
        response.setFirstNameKana(cardInfoModel.getFirstNameKana());
        response.setLastNameKana(cardInfoModel.getLastNameKana());
        response.setPositionName(cardInfoModel.getPositionName());
        response.setTelNumberCompany(cardInfoModel.getTelNumberCompany());
        response.setTelNumberDepartment(cardInfoModel.getTelNumberDepartment());
        response.setTelNumberDirect(cardInfoModel.getTelNumberDirect());
        response.setMemo1(cardInfoModel.getMemo1());
        response.setMemo2(cardInfoModel.getMemo2());
        response.setCardOwnerId(cardInfoModel.getCardOwnerId());
        response.setCreateDate(cardInfoModel.getCreateDate());
        response.setUpdateDate(cardInfoModel.getUpdateDate());
        response.setContactDate(cardInfoModel.getContactDate());
        response.setGroupCompanyId(cardInfoModel.getGroupCompanyId());
        response.setZipCode(cardInfoModel.getZipCode());
        
        if(isSameCompany)
            response.setSameCompany(1);
        else
            response.setSameCompany(0);
        
        return response;
    }
            
}
