/*
 * CardInfoServiceImpl class
 */
package com.ecard.core.service.impl;

import java.math.BigInteger;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecard.core.dao.CardInfoDAO;
import com.ecard.core.dao.DataIndexIdDAO;
import com.ecard.core.model.CardInfo;
import com.ecard.core.model.DownloadCsv;
import com.ecard.core.model.enums.ActionTypeEnum;
import com.ecard.core.model.enums.IndexTypeEnum;
import com.ecard.core.model.enums.PropertyCodeEnum;
import com.ecard.core.model.enums.TableTypeEnum;
import com.ecard.core.service.CardInfoService;
import com.ecard.core.util.DataIndexUtil;
import com.ecard.core.vo.CardConnectModel;
import com.ecard.core.vo.CardInfoAndPosCard;
import com.ecard.core.vo.CardInfoConnectUser;
import com.ecard.core.vo.CompanyCardListCount;
import com.ecard.core.vo.CompanyCardModel;

/**
 *
 * @author vinhla
 */
@Service("cardInfoService")
@Transactional
public class CardInfoServiceImpl implements CardInfoService {
    
    @Autowired
    CardInfoDAO cardInfoDAO;
    
    @Autowired
    DataIndexIdDAO dataIndexIdDAO;

    public List<CardInfo> listAllCardInfo(){
    	return cardInfoDAO.listAllCardInfo();
    }
    
    public List<CardInfo> listCardRecent() {
        return cardInfoDAO.listCardRecent();
    }
    
    public List<CardInfo> listAllCardHistory(){
        return cardInfoDAO.listAllCardHistory();
    }
    
    public List<CardInfoConnectUser> listConnectUser(Integer userId, Integer groupCompanyId, Integer recentFlg, Integer pageNumber) {
        return cardInfoDAO.listConnectUser(userId, groupCompanyId, recentFlg, pageNumber);
    }
    
    public List<CompanyCardListCount> listCompany(String permissionType, Integer userId) {
        return cardInfoDAO.listCompany(permissionType, userId);
    }

    public List<CompanyCardModel> listCardWithCompany(String permissionType, Integer userId, String companyName) {
        return cardInfoDAO.listCardWithCompany(permissionType, userId, companyName);
    }
    
    public Boolean editCardInfo(CardInfo cardInfo){
    	if(cardInfo.getCardIndexNo()!=null && cardInfo.getCardIndexNo()!=""){
    		//String indexId=dataIndexIdDAO.updateDataIndexBy(IndexTypeEnum.CardInfor, ActionTypeEnum.Update, TableTypeEnum.CardInfor, PropertyCodeEnum.ShootingApp,cardInfo.getCardIndexNo());
    		/*String indexId=DataIndexUtil.generateFormatIdWith(TableTypeEnum.CardInfor, DataIndexUtil.getSequenceCodeFrom(cardInfo.getCardIndexNo()), 
					DataIndexUtil.getPropertyCodeFrom(cardInfo.getCardIndexNo()),PropertyCodeEnum.findByName(cardInfo.getCardIndexNo().substring(cardInfo.getCardIndexNo().length()-1)),  ActionTypeEnum.Update);*/
    		String indexId=dataIndexIdDAO.insertOrUpdateDataIndexBy(IndexTypeEnum.CardInfor, ActionTypeEnum.Update, TableTypeEnum.CardInfor, PropertyCodeEnum.ShootingApp,cardInfo.getCardIndexNo());
        	cardInfo.setCardIndexNo(indexId);
    	}
        return cardInfoDAO.editCardInfo(cardInfo);
    }
    
    public Boolean editCardInfoNoIndexNo(CardInfo cardInfo){
        return cardInfoDAO.editCardInfo(cardInfo);
    }
    
    public CardInfo getCardInfoDetail(Integer cardId){
        return cardInfoDAO.getCardInfoDetail(cardId);
    }
    
    public List<CardInfoAndPosCard> listCardPending(Integer userId){
        return cardInfoDAO.listCardPending(userId);
    }
    
    public List<CardInfo> getListPossesionCard(Integer userId, String searchText, String sort, int pageNumber){
        return cardInfoDAO.getListPossesionCard(userId, searchText, sort, pageNumber);
    }
    
    public List<com.ecard.core.vo.CardInfo> getListCardSearch(Integer userId, String searchText,String name,String position,String department,String company, int pageNumber, int groupCompanyId) {
        return cardInfoDAO.getListCardSearch(userId, searchText,name, position, department, company, pageNumber, groupCompanyId);
    }
    
    public BigInteger getTotalCardSearch(Integer userId, String searchText,String name,String position,String department,String company, int groupCompanyId){
        return cardInfoDAO.getTotalCardSearch(userId, searchText, name, position, department, company, groupCompanyId);
    }
    
	public List<com.ecard.core.vo.CardInfo> getListCardSearchAll(String owner, String searchText,String name, String position,String department,String company, int pageNumber, int groupCompanyId) {
		return cardInfoDAO.getListCardSearchAll(owner, searchText, name, position, department, company,
				pageNumber, groupCompanyId);
	}

	public BigInteger getTotalCardSearchAll(String owner, String searchText,String name, String position,String department,String company, int pageNumber, int groupCompanyId) {
		return cardInfoDAO.getTotalCardSearchAll(owner, searchText, name, position, department, company, pageNumber, groupCompanyId);
	}

//	public List<com.ecard.core.vo.CardInfo> getListCardSearchAllUser(String searchText,String name, String position,String department,String company, int pageNumber, int groupCompanyId) {
//		return cardInfoDAO.getListCardSearchAllUser(searchText, name, position, department, company, pageNumber,groupCompanyId);
//	}
//
//	public BigInteger getTotalCardSearchAllUser(String searchText,String name, String position,String department,String company, int pageNumber, int groupCompanyId) {
//		return cardInfoDAO.getTotalCardSearchAllUser(searchText, name, position, department, company, pageNumber, groupCompanyId);
//	}
    
    public List<com.ecard.core.vo.CardInfo> getListPossesionCardRecent(Integer userId) {
        return cardInfoDAO.getListPossesionCardRecent(userId);
    }
    
    public CardInfo registerCardImage(CardInfo cardInfo) {
    	//String indexId=dataIndexIdDAO.insertDataIndexBy(IndexTypeEnum.CardInfor, ActionTypeEnum.Insert, TableTypeEnum.CardInfor, PropertyCodeEnum.ShootingApp);
    	String indexId=dataIndexIdDAO.insertOrUpdateDataIndexBy(IndexTypeEnum.CardInfor, ActionTypeEnum.Insert, TableTypeEnum.CardInfor, PropertyCodeEnum.ShootingApp,cardInfo.getCardIndexNo());
    	cardInfo.setCardIndexNo(indexId);
    	if(!StringUtils.isEmpty(indexId)){
			cardInfo.setImageFile(DataIndexUtil.getIndexNoOfImageBy(TableTypeEnum.ImageInfor, indexId)+".jpg");
		}
        return cardInfoDAO.registerCardImage(cardInfo);
    }
    
    public CardInfo registerCardImageOfAdmin(CardInfo cardInfo) {
    	//String indexId=dataIndexIdDAO.insertDataIndexBy(IndexTypeEnum.CardInfor, ActionTypeEnum.Insert, TableTypeEnum.CardInfor, PropertyCodeEnum.Scanner);
    	String indexId=dataIndexIdDAO.insertOrUpdateDataIndexBy(IndexTypeEnum.CardInfor, ActionTypeEnum.Insert, TableTypeEnum.CardInfor, PropertyCodeEnum.Scanner,cardInfo.getCardIndexNo());
    	cardInfo.setCardIndexNo(indexId);
    	if(!StringUtils.isEmpty(indexId)){
			cardInfo.setImageFile(DataIndexUtil.getIndexNoOfImageBy(TableTypeEnum.ImageInfor, indexId)+".jpg");
		}
        return cardInfoDAO.registerCardImage(cardInfo);
    }
    
    public int deleteCardInfo(Integer cardId){
        return cardInfoDAO.deleteCardInfo(cardId);
    }
    
    public List<CardConnectModel> listCardConnect(Integer cardOwnerId, Integer groupCompanyId, String name, String companyName, String email){
        return cardInfoDAO.listCardConnect(cardOwnerId, groupCompanyId, name, companyName, email);
    }
    
    public String getCardImage(Integer cardId){
        return cardInfoDAO.getCardImage(cardId);
    }
    
    public List<com.ecard.core.vo.CardInfo> searchCard(String criteriaSearch, int status, List<Integer> listStatus, int limit, int offet){
    	return cardInfoDAO.searchCard(criteriaSearch, status, listStatus, limit, offet);
    }
    
    public int updateCardInfoAdmin(CardInfo cardInfo){
		//String indexId=dataIndexIdDAO.updateDataIndexBy(IndexTypeEnum.CardInfor, ActionTypeEnum.Update, TableTypeEnum.CardInfor, PropertyCodeEnum.Scanner,cardInfo.getCardIndexNo());
		/*String indexId=DataIndexUtil.generateFormatIdWith(TableTypeEnum.CardInfor, DataIndexUtil.getSequenceCodeFrom(cardInfo.getCardIndexNo()), 
				DataIndexUtil.getPropertyCodeFrom(cardInfo.getCardIndexNo()),PropertyCodeEnum.findByName(cardInfo.getCardIndexNo().substring(cardInfo.getCardIndexNo().length()-1)),  ActionTypeEnum.Update);*/
		String indexId=dataIndexIdDAO.insertOrUpdateDataIndexBy(IndexTypeEnum.CardInfor, ActionTypeEnum.Update, TableTypeEnum.CardInfor, PropertyCodeEnum.Scanner,cardInfo.getCardIndexNo());
    	cardInfo.setCardIndexNo(indexId);	
        return cardInfoDAO.updateCardInfoAdmin(cardInfo);
    }
    
    public List<Integer> getUserIdByName(String name){
        return cardInfoDAO.getUserIdByName(name);
    }
	public BigInteger countCardInfo(String criteriaSearch, int status, List<Integer> listStatus) {
		return cardInfoDAO.countCardInfo(criteriaSearch, status, listStatus);
	}

	@Override
	public int updateStatusCard(CardInfo cardInfo) {
		return cardInfoDAO.updateStatusCard(cardInfo);
	}

	public List<CardInfo> getListMyCard(Integer userId) {
		return cardInfoDAO.getListMyCard(userId);
	}

	public List<CardInfo> getCompanyCard(Integer groupCompanyName) {
		return cardInfoDAO.getCompanyCard(groupCompanyName);
	}

	public void saveDownloadHistory(DownloadCsv downloadCsvId) {
		cardInfoDAO.saveDownloadHistory(downloadCsvId);
	}

	public void registerPrusalHistory(Integer userId, Integer cardId) {
		cardInfoDAO.registerPrusalHistory(userId, cardId);
	}

//	public List<com.ecard.core.vo.CardInfo> getListCardSearchByMroonga(Integer groupCompanyId, List<Integer> listUserId,
//			String searchText, String name, String position, String department, String company, int pageNumber) {
//		return cardInfoDAO.getListCardSearchByMroonga(groupCompanyId, listUserId, searchText, name, position,
//				department, company, pageNumber);
//	}
//
//	public List<com.ecard.core.vo.CardInfo> getListCardSearchAllUserByMroonga(Integer groupCompanyId, String searchText,
//			String name, String position, String department, String company, int pageNumber, Integer userId) {
//		return cardInfoDAO.getListCardSearchAllUserByMroonga(groupCompanyId, searchText, name, position, department,
//				company, pageNumber, userId);
//	}
//
//	public BigInteger getTotalCardSearchAllUserByMroonga(Integer groupCompanyId, String searchText, String name,
//			String position, String department, String company, int pageNumber, Integer userId) {
//		return cardInfoDAO.getTotalCardSearchAllUserByMroonga(groupCompanyId, searchText, name, position, department,
//				company, pageNumber, userId);
//	}
//
//	public BigInteger getTotalCardSearchAllByMroonga(Integer groupCompanyId, List<Integer> listUserId,
//			String searchText, String name, String position, String department, String company, int pageNumber) {
//		return cardInfoDAO.getTotalCardSearchAllByMroonga(groupCompanyId, listUserId, searchText, name, position,
//				department, company, pageNumber);
//	}

	@Override
	public List<CardInfo> getListPossesionCard(Integer userId) {
		return cardInfoDAO.getListPossesionCard(userId);
	}
	public void updateOldCardInfo (CardInfo cardInfo){
		cardInfoDAO.updateOldCardInfo(cardInfo);
	}
    
    public CardInfo importCardInfoFromCsv(CardInfo cardInfo){
    	/*String cardIndexNo=dataIndexIdDAO.insertDataIndexBy(IndexTypeEnum.CardInfor, ActionTypeEnum.Insert, TableTypeEnum.CardInfor, PropertyCodeEnum.Migration);
		cardInfo.setCardIndexNo(cardIndexNo);*/
    	String indexId=dataIndexIdDAO.insertOrUpdateDataIndexBy(IndexTypeEnum.CardInfor, ActionTypeEnum.Insert, TableTypeEnum.CardInfor, PropertyCodeEnum.Migration,cardInfo.getCardIndexNo());
    	cardInfo.setCardIndexNo(indexId);
        cardInfo.setImageFile(DataIndexUtil.getIndexNoOfImageBy(TableTypeEnum.ImageInfor, indexId)+".jpg");
    	
        return cardInfoDAO.importCardInfoFromCsv(cardInfo);
    }
    
    public int getCardIdByCardIndexNo(String cardIndexNo){
        return cardInfoDAO.getCardIdByCardIndexNo(cardIndexNo);
    }
    
    public List<CardInfo> getListCardInfoByUserId(Integer userId){
        return cardInfoDAO.getListCardInfoByUserId(userId);
    }
    
    public List<Integer> getListOwnerIdByCard(CardInfo cardInfo){
    	return cardInfoDAO.getListOwnerIdByCard(cardInfo);
    }
    
    public int updateCardDeleted(Integer cardId){
        return cardInfoDAO.updateCardDeleted(cardId);
    }
    
    public List<com.ecard.core.vo.CardInfo> searchCardUser(String criteriaSearch, int status, List<Integer> listStatus, int limit, int offet, Integer userId){
    	return cardInfoDAO.searchCardUser(criteriaSearch, status, listStatus, limit, offet, userId);
    }
    
    public BigInteger countCardInfoUser(String criteriaSearch, int status, List<Integer> listStatus, Integer userId){
    	return cardInfoDAO.countCardInfoUser(criteriaSearch, status, listStatus, userId);
    }
    
    public void updateDateEditting(List<com.ecard.core.vo.CardInfo> cards){
    	cardInfoDAO.updateDateEditting(cards);
    }
    
    public int updateCardTypeById(Integer cardId, Integer cardType){
        return cardInfoDAO.updateCardTypeById(cardId, cardType);
    }
    
    public List<CardInfo> listCardInfoByCardType(Integer cardType){
        return cardInfoDAO.listCardInfoByCardType(cardType);
    }
}
