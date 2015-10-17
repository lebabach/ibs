/*
 * CardInfoConverter class
 */
package com.ecard.core.service.converter;

import com.ecard.core.vo.CardConnectModel;
import com.ecard.core.vo.CardImage;
import com.ecard.core.vo.CardInfo;
import com.ecard.core.vo.CardInfoConnectUser;
import com.ecard.core.vo.CardUpdateHistory;
import com.ecard.core.vo.CompanyCardListCount;
import com.ecard.core.vo.CompanyCardModel;
import com.ecard.core.vo.MyCard;
import com.ecard.core.vo.MyCardAndCardInfo;
import com.ecard.core.vo.PossessionCard;
import com.ecard.core.vo.PrusalHistory;
import com.ecard.core.vo.CardInfoCSV;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;

/**
 *
 * @author vinhla
 */
public final class CardInfoConverter {
    public static List<CardInfo>  convertCardInforList(List<com.ecard.core.model.CardInfo> cardInfoModelList) 
            throws IllegalAccessException, InvocationTargetException{
        List<CardInfo> cardInfoList = new ArrayList<CardInfo>();
        if (CollectionUtils.isNotEmpty(cardInfoModelList)){
            CardInfo cardInfo;
            for (com.ecard.core.model.CardInfo cardInfoModel : cardInfoModelList) {
                cardInfo = new CardInfo();
                cardInfo.setCardId(cardInfoModel.getCardId());
                cardInfo.setName(cardInfoModel.getName());                
                cardInfo.setFirstName(cardInfoModel.getFirstName());
                cardInfo.setLastName(cardInfoModel.getLastName());
                cardInfo.setNameKana(cardInfoModel.getNameKana());
                cardInfo.setFirstNameKana(cardInfoModel.getFirstNameKana());
                cardInfo.setLastNameKana(cardInfoModel.getLastNameKana());
                cardInfo.setCompanyName(cardInfoModel.getCompanyName());
                cardInfo.setCompanyNameKana(cardInfoModel.getCompanyNameKana());
                cardInfo.setDepartmentName(cardInfoModel.getDepartmentName());
                cardInfo.setImageFile(cardInfoModel.getImageFile());
                cardInfo.setPositionName(cardInfoModel.getPositionName());
                cardInfo.setCreateDate(cardInfoModel.getCreateDate());
                cardInfo.setApprovalStatus(cardInfoModel.getApprovalStatus());  
                cardInfo.setTelNumberCompany(cardInfoModel.getTelNumberCompany());
                cardInfo.setEmail(cardInfoModel.getEmail());
                cardInfo.setUpdateDate(cardInfoModel.getUpdateDate());
                cardInfo.setContactDate(cardInfoModel.getContactDate());
                cardInfoList.add(cardInfo);
            }
        }
        return cardInfoList;
    }
    
    public static List<CardInfoConnectUser>  convertConnectCardList(List<com.ecard.core.vo.CardInfoConnectUser> cardInfoModelList) 
            throws IllegalAccessException, InvocationTargetException{
        List<CardInfoConnectUser> cardInfoList = new ArrayList<CardInfoConnectUser>();
        if (CollectionUtils.isNotEmpty(cardInfoModelList)){
            CardInfoConnectUser cardInfo;
            for (com.ecard.core.vo.CardInfoConnectUser cardInfoModel : cardInfoModelList) {
                cardInfo = new CardInfoConnectUser();
                cardInfo.setCardId(cardInfoModel.getCardId());   
                cardInfo.setName(cardInfoModel.getName());
                cardInfo.setLastName(cardInfoModel.getLastName());
                cardInfo.setFirstName(cardInfoModel.getFirstName());
                cardInfo.setNameKana(cardInfoModel.getNameKana());
                cardInfo.setFirstNameKana(cardInfoModel.getFirstNameKana());
                cardInfo.setLastNameKana(cardInfoModel.getLastNameKana());
                cardInfo.setCompanyName(cardInfoModel.getCompanyName());
                cardInfo.setDepartmentName(cardInfoModel.getDepartmentName());
                cardInfo.setImageFile(cardInfoModel.getImageFile());
                cardInfo.setPositionName(cardInfoModel.getPositionName());
                cardInfoList.add(cardInfo);
            }
        }
        return cardInfoList;
    }
    
    public static List<PrusalHistory>  convertMyCardRecentList(List<com.ecard.core.model.PrusalHistory> myCardRecentModelList) 
            throws IllegalAccessException, InvocationTargetException{
        List<PrusalHistory> cardRecentList = new ArrayList<PrusalHistory>();
        if (CollectionUtils.isNotEmpty(myCardRecentModelList)){
            PrusalHistory prusalHistory;
            for (com.ecard.core.model.PrusalHistory cardRecentModel : myCardRecentModelList) {
                prusalHistory = new PrusalHistory();
                prusalHistory.setCardId(cardRecentModel.getCardInfo().getCardId());
                prusalHistory.setName(cardRecentModel.getCardInfo().getName());
                prusalHistory.setCompanyName(cardRecentModel.getCardInfo().getCompanyName());
                prusalHistory.setDepartmentName(cardRecentModel.getCardInfo().getDepartmentName());
                prusalHistory.setImageFile(cardRecentModel.getCardInfo().getImageFile());
                prusalHistory.setPositionName(cardRecentModel.getCardInfo().getPositionName());
                
                cardRecentList.add(prusalHistory);
            }
        }
        return cardRecentList;
    }
    
//    public static List<MyCard>  convertMyCardInforList(List<com.ecard.core.model.MyCard> myCardModelList) 
//            throws IllegalAccessException, InvocationTargetException{
//        List<MyCard> cardInfoList = new ArrayList<MyCard>();
//        if (CollectionUtils.isNotEmpty(myCardModelList)){
//            MyCard myCard;
//            for (com.ecard.core.model.MyCard cardInfoModel : myCardModelList) {
//                myCard = new MyCard();
//                myCard.setCardId(cardInfoModel.getCardInfo().getCardId());
//                myCard.setName(cardInfoModel.getCardInfo().getName());
//                myCard.setCompanyName(cardInfoModel.getCardInfo().getCompanyName());
//                myCard.setDepartmentName(cardInfoModel.getCardInfo().getDepartmentName());
//                myCard.setImageFile(cardInfoModel.getCardInfo().getImageFile());
//                myCard.setPositionName(cardInfoModel.getCardInfo().getPositionName());
//                
//                //BeanUtils.copyProperties(myCard, cardInfoModel);
//                cardInfoList.add(myCard);
//            }
//        }
//        return cardInfoList;
//    }
    
    public static List<CardUpdateHistory>  convertCardUpdateHistoryList(List<com.ecard.core.model.CardUpdateHistory> cardUpdateHistoryList) 
            throws IllegalAccessException, InvocationTargetException{
        List<CardUpdateHistory> cardInfoList = new ArrayList<CardUpdateHistory>();
        if (CollectionUtils.isNotEmpty(cardUpdateHistoryList)){
            CardUpdateHistory cardUpdateHistory;
            for (com.ecard.core.model.CardUpdateHistory cardUpdateHistoryModel : cardUpdateHistoryList) {
                cardUpdateHistory = new CardUpdateHistory();
                cardUpdateHistory.setCardId(cardUpdateHistoryModel.getCardInfo().getCardId());
                cardUpdateHistory.setName(cardUpdateHistoryModel.getCardInfo().getName());
                cardUpdateHistory.setCompanyName(cardUpdateHistoryModel.getCardInfo().getCompanyName());
                cardUpdateHistory.setDepartmentName(cardUpdateHistoryModel.getCardInfo().getDepartmentName());
                cardUpdateHistory.setImageFile(cardUpdateHistoryModel.getCardInfo().getImageFile());
                cardUpdateHistory.setPositionName(cardUpdateHistoryModel.getCardInfo().getPositionName());
                //BeanUtils.copyProperties(cardUpdateHistory, cardUpdateHistoryModel);
                cardInfoList.add(cardUpdateHistory);
            }
        }
        return cardInfoList;
    }

    
    public static List<PossessionCard>  convertPossesionCardList(List<com.ecard.core.model.PossessionCard> cardModelList) 
            throws IllegalAccessException, InvocationTargetException{
        List<PossessionCard> cardList = new ArrayList<PossessionCard>();
        if (CollectionUtils.isNotEmpty(cardModelList)){
            PossessionCard posCard;
            for (com.ecard.core.model.PossessionCard cardModel : cardModelList) {
                posCard = new PossessionCard();

                posCard.setCardId(cardModel.getId().getCardId());
                posCard.setName(cardModel.getCardInfo().getName());
                posCard.setCompanyName(cardModel.getCardInfo().getCompanyName());
                posCard.setDepartmentName(cardModel.getCardInfo().getDepartmentName());
                posCard.setImageFile(cardModel.getCardInfo().getImageFile());
                posCard.setPositionName(cardModel.getCardInfo().getPositionName());
                cardList.add(posCard);
            }
        }
        return cardList;
    }
    
    public static List<MyCardAndCardInfo> convertMyCardCardInforList(List<com.ecard.core.vo.MyCardAndCardInfo> myCardModelList) 
            throws IllegalAccessException, InvocationTargetException{
        List<MyCardAndCardInfo> cardInfoList = new ArrayList<MyCardAndCardInfo>();
        if (CollectionUtils.isNotEmpty(myCardModelList)){
            MyCardAndCardInfo myCard;
            for (com.ecard.core.vo.MyCardAndCardInfo cardInfoModel : myCardModelList) {
                myCard = new MyCardAndCardInfo();
                myCard.setCompanyName(cardInfoModel.getCompanyName());
                myCard.setDepartmentName(cardInfoModel.getDepartmentName());
                myCard.setPositionName(cardInfoModel.getPositionName());
                myCard.setImageFile(cardInfoModel.getImageFile());
                
                cardInfoList.add(myCard);
            }
        }
        return cardInfoList;
    }
    
    public static List<PrusalHistory>  convertPrusalCardInforList(List<com.ecard.core.model.PrusalHistory> cardInfoModelList) 
            throws IllegalAccessException, InvocationTargetException{
        List<PrusalHistory> cardInfoList = new ArrayList<PrusalHistory>();
        if (CollectionUtils.isNotEmpty(cardInfoModelList)){
            PrusalHistory cardInfo;
            for (com.ecard.core.model.PrusalHistory cardInfoModel : cardInfoModelList) {
                cardInfo = new PrusalHistory();
                cardInfo.setCardId(cardInfoModel.getId().getCardId());
                cardInfo.setName(cardInfoModel.getCardInfo().getName());
                cardInfo.setCompanyName(cardInfoModel.getCardInfo().getCompanyName());
                cardInfo.setDepartmentName(cardInfoModel.getCardInfo().getDepartmentName());
                cardInfo.setImageFile(cardInfoModel.getCardInfo().getImageFile());
                cardInfo.setPositionName(cardInfoModel.getCardInfo().getPositionName());
                cardInfoList.add(cardInfo);
            }
        }
        return cardInfoList;
    }
    
    public static List<CompanyCardListCount>  convertCardInforCompanyList(List<com.ecard.core.vo.CompanyCardListCount> cardInfoModelList) 
            throws IllegalAccessException, InvocationTargetException{
        List<CompanyCardListCount> cardInfoList = new ArrayList<CompanyCardListCount>();
        if (CollectionUtils.isNotEmpty(cardInfoModelList)){
            CompanyCardListCount cardInfo;
            for (com.ecard.core.vo.CompanyCardListCount cardInfoModel : cardInfoModelList) {
                cardInfo = new CompanyCardListCount();

                cardInfo.setCardId(cardInfoModel.getCardId());
                cardInfo.setCompanyId(cardInfoModel.getCompanyId());
                cardInfo.setCompanyName(cardInfoModel.getCompanyName());
                cardInfo.setCompanyNameKana(cardInfoModel.getCompanyNameKana());
                cardInfo.setCardCnt(cardInfoModel.getCardCnt());
                cardInfoList.add(cardInfo);
            }
        }
        return cardInfoList;
    }
    
    public static List<CompanyCardModel>  convertCompanyList(List<com.ecard.core.vo.CompanyCardModel> cardInfoModelList) 
            throws IllegalAccessException, InvocationTargetException{
        List<CompanyCardModel> cardInfoList = new ArrayList<CompanyCardModel>();
        if (CollectionUtils.isNotEmpty(cardInfoModelList)){
            CompanyCardModel cardInfo;
            for (com.ecard.core.vo.CompanyCardModel cardInfoModel : cardInfoModelList) {
                cardInfo = new CompanyCardModel();

                cardInfo.setCardId(cardInfoModel.getCardId());
                cardInfo.setName(cardInfoModel.getName());
                cardInfo.setLastName(cardInfoModel.getLastName());
                cardInfo.setFirstName(cardInfoModel.getFirstName());
                cardInfo.setNameKana(cardInfoModel.getNameKana());
                cardInfo.setLastNameKana(cardInfoModel.getLastNameKana());
                cardInfo.setFirstNameKana(cardInfoModel.getFirstNameKana());
                cardInfo.setCompanyName(cardInfoModel.getCompanyName());
                cardInfo.setDepartmentName(cardInfoModel.getDepartmentName());
                cardInfo.setPositionName(cardInfoModel.getPositionName());
                cardInfo.setImageFile(cardInfoModel.getImageFile());
                cardInfoList.add(cardInfo);
            }
        }
        return cardInfoList;
    }
    
    public static Integer  convertCardId(List<com.ecard.core.vo.CompanyCardListCount> cardInfoModelList) 
            throws IllegalAccessException, InvocationTargetException{
        List<Integer> cardIdList = new ArrayList<Integer>();
        if (CollectionUtils.isNotEmpty(cardInfoModelList)){
            CompanyCardModel cardInfo;
            for (com.ecard.core.vo.CompanyCardListCount cardInfoModel : cardInfoModelList) {
                cardInfo = new CompanyCardModel();

                cardInfo.setCardId(cardInfoModel.getCardId());
                
                cardIdList.add(cardInfo.getCardId());
            }
        }
        return cardIdList.get(0);
    }
    
    public static String  convertCompanyName(List<com.ecard.core.vo.CompanyCardModel> cardInfoModelList) 
            throws IllegalAccessException, InvocationTargetException{
        List<String> cardIdList = new ArrayList<String>();
        if (CollectionUtils.isNotEmpty(cardInfoModelList)){
            CompanyCardModel cardInfo;
            for (com.ecard.core.vo.CompanyCardModel cardInfoModel : cardInfoModelList) {
                cardInfo = new CompanyCardModel();

                cardInfo.setCompanyName(cardInfoModel.getCompanyName());
                
                cardIdList.add(cardInfo.getCompanyName());
            }
        }
        return cardIdList.get(0);
    }
    
    public static List<CardConnectModel>  convertCardConnectUserList(List<com.ecard.core.vo.CardConnectModel> cardInfoModelList) 
            throws IllegalAccessException, InvocationTargetException{
        List<CardConnectModel> cardInfoList = new ArrayList<CardConnectModel>();
        if (CollectionUtils.isNotEmpty(cardInfoModelList)){
            CardConnectModel cardInfo;
            for (com.ecard.core.vo.CardConnectModel cardInfoModel : cardInfoModelList) {
                cardInfo = new CardConnectModel();
                cardInfo.setCardId(cardInfoModel.getCardId());
                cardInfo.setName(cardInfoModel.getName());
                cardInfo.setLastName(cardInfoModel.getLastName());
                cardInfo.setFirstName(cardInfoModel.getFirstName());
                cardInfo.setNameKana(cardInfoModel.getNameKana());
                cardInfo.setLastNameKana(cardInfoModel.getLastNameKana());
                cardInfo.setFirstNameKana(cardInfoModel.getFirstNameKana());
                cardInfo.setCompanyName(cardInfoModel.getCompanyName());
                cardInfo.setDepartmentName(cardInfoModel.getDepartmentName());
                cardInfo.setPositionName(cardInfoModel.getPositionName());
                cardInfo.setTelNumberCompany(cardInfoModel.getTelNumberCompany());
                cardInfo.setEmail(cardInfoModel.getEmail());
                cardInfo.setCompanyNameKana(cardInfoModel.getCompanyNameKana());
                cardInfoList.add(cardInfo);
            }
        }
        return cardInfoList;
    }
    
    public static CardImage  convertCardImage(com.ecard.core.vo.CardImage cardImageModel) 
            throws IllegalAccessException, InvocationTargetException{
        
        CardImage cardImage = new CardImage();
        if (cardImageModel != null){
            cardImage.setImageFile(cardImageModel.getImageFile());
        }
        return cardImage;
    }
    
    public static List<CardInfoCSV> convertCardInfoList(List<com.ecard.core.model.CardInfo> cardInfoList){
		List<CardInfoCSV> listCardInfoCSV = new ArrayList<CardInfoCSV>();
		CardInfoCSV cardInfoCSV;
		for(com.ecard.core.model.CardInfo cardInfo : cardInfoList){
			cardInfoCSV = new CardInfoCSV();
			cardInfoCSV.setCardIndexNo(cardInfo.getCardIndexNo());						
			cardInfoCSV.setCompanyName(cardInfo.getCompanyName());
			cardInfoCSV.setCompanyNameKana(cardInfo.getCompanyNameKana());
			cardInfoCSV.setDepartmentName(cardInfo.getDepartmentName());
			cardInfoCSV.setPositionName(cardInfo.getPositionName());			
			cardInfoCSV.setLastName(cardInfo.getLastName());
			cardInfoCSV.setFirstName(cardInfo.getFirstName());			
			cardInfoCSV.setLastNameKana(cardInfo.getLastNameKana());
			cardInfoCSV.setFirstNameKana(cardInfo.getFirstNameKana());
			cardInfoCSV.setEmail(cardInfo.getEmail());
			cardInfoCSV.setZipCode(cardInfo.getZipCode());
			cardInfoCSV.setAddressFull(cardInfo.getAddressFull());
			cardInfoCSV.setAddress1(cardInfo.getAddress1());
			cardInfoCSV.setAddress2(cardInfo.getAddress2());
			cardInfoCSV.setAddress3(cardInfo.getAddress3());
			cardInfoCSV.setTelNumberCompany(cardInfo.getTelNumberCompany());
			cardInfoCSV.setTelNumberDepartment(cardInfo.getTelNumberDepartment());
			cardInfoCSV.setTelNumberDirect(cardInfo.getTelNumberDirect());
			cardInfoCSV.setFaxNumber(cardInfo.getFaxNumber());
			cardInfoCSV.setMobileNumber(cardInfo.getMobileNumber());
			cardInfoCSV.setCompanyUrl(cardInfo.getCompanyUrl());
			cardInfoCSV.setSubAddressFull(cardInfo.getSubAddressFull());
			cardInfoCSV.setSubAddress1(cardInfo.getSubAddress1());
			cardInfoCSV.setSubAddress2(cardInfo.getSubAddress2());
			cardInfoCSV.setSubAddress3(cardInfo.getSubAddress3());
			cardInfoCSV.setSubZipCode(cardInfo.getSubZipCode());
			cardInfoCSV.setSubTelNumberCompany(cardInfo.getSubTelNumberCompany());
			cardInfoCSV.setSubTelNumberDepartment(cardInfo.getSubTelNumberDepartment());
			cardInfoCSV.setSubTelNumberDirect(cardInfo.getSubTelNumberDirect());
			cardInfoCSV.setSubFaxNumber(cardInfo.getSubFaxNumber());			
			cardInfoCSV.setCreateDate(cardInfo.getCreateDate());
			cardInfoCSV.setUpdateDate(cardInfo.getUpdateDate());
			cardInfoCSV.setContactDate(cardInfo.getContactDate());
			listCardInfoCSV.add(cardInfoCSV);			
		}
			
		return listCardInfoCSV;
	}
    
    public static List<com.ecard.core.vo.CardInfo>  convertCardInforListMroonga(List<com.ecard.core.vo.CardInfo> cardInfoModelList) 
            throws IllegalAccessException, InvocationTargetException{
        List<com.ecard.core.vo.CardInfo> cardInfoList = new ArrayList<com.ecard.core.vo.CardInfo>();
        if (CollectionUtils.isNotEmpty(cardInfoModelList)){
            CardInfo cardInfo;
            for (com.ecard.core.vo.CardInfo cardInfoModel : cardInfoModelList) {
                cardInfo = new CardInfo();
                cardInfo.setCardId(cardInfoModel.getCardId());
                cardInfo.setName(cardInfoModel.getName());                
                cardInfo.setFirstName(cardInfoModel.getFirstName());
                cardInfo.setLastName(cardInfoModel.getLastName());
                cardInfo.setNameKana(cardInfoModel.getNameKana());
                cardInfo.setFirstNameKana(cardInfoModel.getFirstNameKana());
                cardInfo.setLastNameKana(cardInfoModel.getLastNameKana());
                cardInfo.setCompanyName(cardInfoModel.getCompanyName());
                cardInfo.setDepartmentName(cardInfoModel.getDepartmentName());
                cardInfo.setImageFile(cardInfoModel.getImageFile());
                cardInfo.setPositionName(cardInfoModel.getPositionName());
                cardInfo.setCreateDate(cardInfoModel.getCreateDate());
                cardInfo.setApprovalStatus(cardInfoModel.getApprovalStatus());   
                cardInfo.setTelNumberCompany(cardInfoModel.getTelNumberCompany());
                cardInfo.setEmail(cardInfoModel.getEmail());
                cardInfo.setCompany_id(cardInfoModel.getCompany_id());
                cardInfo.setGroupCompanyId(cardInfoModel.getGroupCompanyId());
                cardInfoList.add(cardInfo);
            }
        }
        return cardInfoList;
    }
    
    public static List<com.ecard.core.vo.CardInfo>  convertCardRecentList(List<com.ecard.core.vo.CardInfo> cardInfoModelList) 
            throws IllegalAccessException, InvocationTargetException{
        List<CardInfo> cardInfoList = new ArrayList<CardInfo>();
        if (CollectionUtils.isNotEmpty(cardInfoModelList)){
            CardInfo cardInfo;
            for (com.ecard.core.vo.CardInfo cardInfoModel : cardInfoModelList) {
                cardInfo = new CardInfo();
                cardInfo.setCardId(cardInfoModel.getCardId());
                cardInfo.setName(cardInfoModel.getName());                
                cardInfo.setFirstName(cardInfoModel.getFirstName());
                cardInfo.setLastName(cardInfoModel.getLastName());
                cardInfo.setNameKana(cardInfoModel.getNameKana());
                cardInfo.setFirstNameKana(cardInfoModel.getFirstNameKana());
                cardInfo.setLastNameKana(cardInfoModel.getLastNameKana());
                cardInfo.setCompanyName(cardInfoModel.getCompanyName());
                cardInfo.setCompanyNameKana(cardInfoModel.getCompanyNameKana());
                cardInfo.setDepartmentName(cardInfoModel.getDepartmentName());
                cardInfo.setImageFile(cardInfoModel.getImageFile());
                cardInfo.setPositionName(cardInfoModel.getPositionName());
                cardInfo.setCreateDate(cardInfoModel.getCreateDate());
                cardInfo.setApprovalStatus(cardInfoModel.getApprovalStatus());  
                cardInfo.setTelNumberCompany(cardInfoModel.getTelNumberCompany());
                cardInfo.setEmail(cardInfoModel.getEmail());
                cardInfoList.add(cardInfo);
            }
        }
        return cardInfoList;
    }
}
