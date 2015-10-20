/*
 * CardInfoServiceImpl class
 */
package com.ecard.core.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecard.core.dao.CardInfoDAO;
import com.ecard.core.dao.ContactHistoryDAO;
import com.ecard.core.dao.DataIndexDAO;
import com.ecard.core.dao.OldCardDAO;
import com.ecard.core.dao.UserCardMemoDAO;
import com.ecard.core.dao.UserInfoDAO;
import com.ecard.core.model.AdminPossessionCard;
import com.ecard.core.model.CardInfo;
import com.ecard.core.model.CompanyInfo;
import com.ecard.core.model.ContactHistory;
import com.ecard.core.model.DownloadCsv;
import com.ecard.core.model.OldCard;
import com.ecard.core.model.OldCardId;
import com.ecard.core.model.PossessionCard;
import com.ecard.core.model.PrusalHistory;
import com.ecard.core.model.UserCardMemo;
import com.ecard.core.model.UserInfo;
import com.ecard.core.model.enums.ActionTypeEnum;
import com.ecard.core.model.enums.IndexTypeEnum;
import com.ecard.core.model.enums.PropertyCodeEnum;
import com.ecard.core.model.enums.TableTypeEnum;
import com.ecard.core.service.CardInfoService;
import com.ecard.core.service.ContactHistoryService;
import com.ecard.core.util.DataIndexUtil;
import com.ecard.core.util.PairUtil;
import com.ecard.core.util.StringUtilsHelper;
import com.ecard.core.vo.CardConnectModel;
import com.ecard.core.vo.CardInfoAndPosCard;
import com.ecard.core.vo.CardInfoConnectUser;
import com.ecard.core.vo.CardInfoNotifyChange;
import com.ecard.core.vo.CardInfoUserVo;
import com.ecard.core.vo.CompanyCardListCount;
import com.ecard.core.vo.CompanyCardModel;
import com.ecard.core.vo.TagUser;

/**
 *
 * @author vinhla
 */
@Service("cardInfoService")
@Transactional
public class CardInfoServiceImpl implements CardInfoService {

	@Autowired
    OldCardDAO oldCardDAO;
	
    @Autowired
    CardInfoDAO cardInfoDAO;
        
    @Autowired
    DataIndexDAO dataIndexDAO;
    
    @Autowired
    UserInfoDAO userInfoDAO;
    
    @Autowired
    UserCardMemoDAO userCardMemoDAO;
    
    @Autowired
    ContactHistoryDAO contactHistoryDAO;

    public List<CardInfo> listAllCardInfo(){
    	return cardInfoDAO.listAllCardInfo();
    }
    
    public List<CardInfo> listCardRecent() {
        return cardInfoDAO.listCardRecent();
    }
    
    public List<CardInfo> listAllCardHistory(){
        return cardInfoDAO.listAllCardHistory();
    }
    
    public List<CardInfoConnectUser> listConnectUser(Integer userId, Integer recentFlg, Integer pageNumber) {
        return cardInfoDAO.listConnectUser(userId, recentFlg, pageNumber);
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
    		String indexId=dataIndexDAO.insertOrUpdateDataIndexBy(IndexTypeEnum.CardInfor, ActionTypeEnum.Update, TableTypeEnum.CardInfor, PropertyCodeEnum.ShootingApp,cardInfo.getCardIndexNo());
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
    	String indexId=dataIndexDAO.insertOrUpdateDataIndexBy(IndexTypeEnum.CardInfor, ActionTypeEnum.Insert, TableTypeEnum.CardInfor, PropertyCodeEnum.ShootingApp,cardInfo.getCardIndexNo());
    	cardInfo.setCardIndexNo(indexId);
    	if(!StringUtils.isEmpty(indexId)){
			cardInfo.setImageFile(DataIndexUtil.getIndexNoOfImageBy(TableTypeEnum.ImageInfor, indexId)+".jpg");
		}
        return cardInfoDAO.registerCardImage(cardInfo);
    }
    
    public CardInfo registerCardImageOfAdmin(CardInfo cardInfo) {
    	//String indexId=dataIndexIdDAO.insertDataIndexBy(IndexTypeEnum.CardInfor, ActionTypeEnum.Insert, TableTypeEnum.CardInfor, PropertyCodeEnum.Scanner);
    	String indexId=dataIndexDAO.insertOrUpdateDataIndexBy(IndexTypeEnum.CardInfor, ActionTypeEnum.Insert, TableTypeEnum.CardInfor, PropertyCodeEnum.Scanner,cardInfo.getCardIndexNo());
    	cardInfo.setCardIndexNo(indexId);
    	if(!StringUtils.isEmpty(indexId)){
			cardInfo.setImageFile(DataIndexUtil.getIndexNoOfImageBy(TableTypeEnum.ImageInfor, indexId)+".jpg");
		}
        return cardInfoDAO.registerCardImage(cardInfo);
    }
    
    public CardInfo registerCardImageManualPCOfAdmin(CardInfo cardInfo) {
    	//String indexId=dataIndexIdDAO.insertDataIndexBy(IndexTypeEnum.CardInfor, ActionTypeEnum.Insert, TableTypeEnum.CardInfor, PropertyCodeEnum.Scanner);
    	String indexId=dataIndexDAO.insertOrUpdateDataIndexBy(IndexTypeEnum.CardInfor, ActionTypeEnum.Insert, TableTypeEnum.CardInfor, PropertyCodeEnum.ManualPC,cardInfo.getCardIndexNo());
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
		String indexId=dataIndexDAO.insertOrUpdateDataIndexBy(IndexTypeEnum.CardInfor, ActionTypeEnum.Update, TableTypeEnum.CardInfor, PropertyCodeEnum.Scanner,cardInfo.getCardIndexNo());
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
	public List<CardInfoUserVo> getListPossesionCard(Integer userId,Integer typeSort, String valueSearch) {
		return cardInfoDAO.getListPossesionCard(userId, typeSort, valueSearch);
	}
	public void updateOldCardInfo (CardInfo cardInfo){
		cardInfoDAO.updateOldCardInfo(cardInfo);
	}
	
	public void updateCardInfoNotCreateIndex (CardInfo cardInfo){
		cardInfoDAO.saveOrUpdate(cardInfo);
	}
    
    public CardInfo importCardInfoFromCsv(CardInfo cardInfo){
    	/*String cardIndexNo=dataIndexIdDAO.insertDataIndexBy(IndexTypeEnum.CardInfor, ActionTypeEnum.Insert, TableTypeEnum.CardInfor, PropertyCodeEnum.Migration);
		cardInfo.setCardIndexNo(cardIndexNo);*/
    	String indexId=dataIndexDAO.insertOrUpdateDataIndexBy(IndexTypeEnum.CardInfor, ActionTypeEnum.Insert, TableTypeEnum.CardInfor, PropertyCodeEnum.Migration,cardInfo.getCardIndexNo());
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
    
    public List<PairUtil<Integer,Integer>> getListUserPushToByCard(CardInfo cardInfo){
    	return cardInfoDAO.getListUserPushToByCard(cardInfo);    
    }
    
    public List<Integer> getListUserPushFromByCard(CardInfo cardInfo){
    	return cardInfoDAO.getListUserPushFromByCard(cardInfo);
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
    
    public int updateCardType(){
        return cardInfoDAO.updateCardType();
    }
    
    public void updateCardInfoNoIndex(List<CardInfo> cardInfoList){
    	cardInfoDAO.updateListCardInfors(cardInfoList);
    }
    public List<CardInfo> listCardInfoByCardType(Integer cardType){
        return cardInfoDAO.listCardInfoByCardType(cardType);
    }

	@Override
	public List<String> getListSortType(Integer userId, Integer sortType) {
		return cardInfoDAO.getListSortType(userId, sortType);
	}
	
	public int updateContactDate(CardInfo cardInfo){
		return cardInfoDAO.updateContactDate(cardInfo);
	}
	
	public Long countPossessionCard(Integer userId){
		return cardInfoDAO.countPossessionCard(userId);
	}

	public CardInfo getNewestCardInfo(CardInfo cardInfo){
    	return cardInfoDAO.getNewestCardInfo(cardInfo);
    }
	
	public List<CardInfo> getOldCardInfor(){
		return cardInfoDAO.getOldCardInfor();
	}

	public int deleteListCard(List<Integer> listCard){
		return cardInfoDAO.deleteListCard(listCard);
	}
	
	public List<CardInfo> getListPossessionCardByTag(Integer userId, Integer tagId, String sort, int pageNumber){
		return cardInfoDAO.getListPossessionCardByTag(userId, tagId, sort, pageNumber);
	}
	
	public List<CardInfoUserVo> getListPossessionCardByTag(Integer userId, Integer tagId, int pageNumber){
		return cardInfoDAO.getListPossessionCardByTag(userId, tagId, pageNumber);
	}
	
	public List<String> getListSortTypeByTag(Integer userId, Integer tagId){
		return cardInfoDAO.getListSortTypeByTag(userId, tagId);
	}
	
	public void updateDownloadHistory(Integer downloadCsvId) {
		cardInfoDAO.updateDownloadHistory(downloadCsvId);
	}
	
	public DownloadCsv getDownloadCSV(Integer csvId) {
		return cardInfoDAO.getDownloadCSV(csvId);
	}
	
	public List<com.ecard.core.vo.CardInfo> searchCompanyTree(String companyName){
		return cardInfoDAO.searchCompanyTree(companyName);
	}
	
	public List<com.ecard.core.vo.CardInfo> searchDepartment(String companyName){
		return cardInfoDAO.searchDepartment(companyName);
	}
	
	public List<com.ecard.core.vo.CardInfo> searchCardInfo(String companyName, String departmentName){
		return cardInfoDAO.searchCardInfo(companyName, departmentName);
	}
	
	public List<CardInfo> searchCardInfoByName(String companyName, String departmentName){
		return cardInfoDAO.searchCardInfoByName(companyName, departmentName);
	}

	@Override
	public List<com.ecard.core.vo.CardInfo> getListCardAllocationUser(int userId,int tagId,int limit,int offset) {
		// TODO Auto-generated method stub
		return cardInfoDAO.getListCardAllocationUser(userId,tagId,limit,offset);
	}

	@Override
	public BigInteger countListCardAllocationUser(int userId, int tagId) {
		// TODO Auto-generated method stub
		return cardInfoDAO.countListCardAllocationUser(userId,tagId);
	}

	@Override
	public List<TagUser> getAllTagUser(int userId) {
		// TODO Auto-generated method stub
		return cardInfoDAO.getAllTagUser(userId);
	}
	
	public List<com.ecard.core.vo.CardInfo> getListConnectCards(com.ecard.core.vo.CardInfo card){
		return cardInfoDAO.getListConnectCards(card);
	}
	
	public boolean handleConnectCards(int cardid1,int cardid2, int currentUserId, String name){
		try{
			CardInfo card1=this.getCardInfoDetail(cardid1);
			CardInfo card2=this.getCardInfoDetail(cardid2);
			//detach object
			
			int ownerUserId=card2.getCardOwnerId();
			String ownerName=card2.getCardOwnerName();
			card1.setOldCardFlg(1);
			this.updateCardInfoAdmin(card1);
			if(ownerUserId!=currentUserId){
				card2.setCardOwnerId(currentUserId);
				card2.setCardOwnerName(name);
				CardInfo newCard= this.registerCardImageManualPCOfAdmin(setCardInfo(card2));
				OldCard oldcard=new OldCard();
				oldcard.setCardInfo(newCard);
				
				OldCardId oldCardId=new OldCardId();
				oldCardId.setCardId(newCard.getCardId());
				oldCardId.setCardOwnerId(currentUserId);
				oldCardId.setSeq(0);
				oldCardId.setOldCardId(card2.getCardId());
				oldcard.setId(oldCardId);
			
				oldCardDAO.saveOrUpdate(oldcard);
				card2.setCardOwnerId(ownerUserId);
				card2.setCardOwnerName(ownerName);
				this.updateCardInfoNotCreateIndex(card2);
				
			}else{
				OldCard oldcard=new OldCard();
				CardInfo cardInfor=new CardInfo();
				cardInfor.setCardId(card2.getCardId());
				OldCardId oldCardId=new OldCardId();
				oldCardId.setCardId(card2.getCardId());
				oldCardId.setCardOwnerId(currentUserId);
				oldCardId.setSeq(0);
				oldCardId.setOldCardId(cardid1);
				oldcard.setCardInfo(cardInfor);
				
				oldcard.setId(oldCardId);
				oldCardDAO.persist(oldcard);
				userCardMemoDAO.updateUserCardMemo(cardid1, currentUserId, cardid2);
				contactHistoryDAO.updateContactHistory(cardid1, currentUserId, cardid2);
				
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private CardInfo setCardInfo(CardInfo card){
		CardInfo newcard=new CardInfo();
		newcard.setCompanyInfo(card.getCompanyInfo());
		newcard.setCardType(card.getCardType());
	     newcard.setImageFile(card.getImageFile());
	     newcard.setCardBackImgFile(card.getCardBackImgFile());
	     newcard.setCompanyName(card.getCompanyName());
	     newcard.setCompanyNameKana(card.getCompanyNameKana());
	     newcard.setDepartmentName(card.getDepartmentName());
	     newcard.setPositionName(card.getPositionName());
	     newcard.setName(card.getName());
	     newcard.setLastName(card.getLastName());
	     newcard.setFirstName(card.getFirstName());
	     newcard.setNameKana(card.getNameKana());
	     newcard.setLastNameKana(card.getLastNameKana());
	     newcard.setFirstNameKana(card.getFirstNameKana());
	     newcard.setEmail(card.getEmail());
	     newcard.setZipCode(card.getZipCode());
	     newcard.setAddressFull(card.getAddressFull());
	     newcard.setAddress1(card.getAddress1());
	     newcard.setAddress2(card.getAddress2());
	     newcard.setAddress3(card.getAddress3());
	     newcard.setAddress4(card.getAddress4());
	     newcard.setTelNumberCompany(card.getTelNumberCompany());
	     newcard.setTelNumberDepartment(card.getTelNumberDepartment());
	     
	     
	     newcard.setTelNumberDirect(card.getTelNumberDirect());
	     newcard.setFaxNumber(card.getFaxNumber());
	     newcard.setMobileNumber(card.getMobileNumber());
	     newcard.setCompanyUrl(card.getCompanyUrl());
	     newcard.setSubAddressFull(card.getSubAddressFull());
	     newcard.setSubZipCode(card.getSubZipCode());
	     newcard.setSubAddress1(card.getSubAddress1());
	     newcard.setSubAddress2(card.getSubAddress2());
	     newcard.setSubAddress3(card.getSubAddress3());
	     newcard.setSubAddress4(card.getSubAddress4());
	     
	     newcard.setSubTelNumberCompany(card.getSubTelNumberCompany());
	     newcard.setSubTelNumberDepartment(card.getSubTelNumberDepartment());
	     newcard.setSubTelNumberDirect(card.getSubTelNumberDirect());
	     newcard.setSubFaxNumber(card.getSubFaxNumber());
	     newcard.setFileOutputFlg(card.getFileOutputFlg());
	     newcard.setHandMemo(card.getHandMemo());
	     newcard.setAutoMemo(card.getAutoMemo());
	     newcard.setMemo1(card.getMemo1());
	     newcard.setMemo2(card.getMemo2());
	     newcard.setMemo1(card.getMemo1());
	     
	     
	     newcard.setCardOwnerId(card.getCardOwnerId());
	     newcard.setPublishStatus(card.getPublishStatus());
	     newcard.setApprovalStatus(card.getApprovalStatus());
	     newcard.setOldCardFlg(card.getOldCardFlg());
	     newcard.setCreateDate(card.getCreateDate());
	     newcard.setUpdateDate(card.getUpdateDate());
	     newcard.setOperaterId(card.getOperaterId());
	     newcard.setDeletDate(card.getDeletDate());
	     newcard.setDeleteFlg(card.getDeleteFlg());
	     newcard.setCardOwnerName(card.getCardOwnerName());
	     newcard.setGroupCompanyId(card.getGroupCompanyId());
	     newcard.setNewestCardFlg(card.getNewestCardFlg());
	     newcard.setContactDate(card.getContactDate());
	     newcard.setCardIndexNo(card.getCardIndexNo());
	     
	     newcard.setSubMobileNumber(card.getSubMobileNumber());
	     newcard.setSubEmail(card.getSubEmail());
	     newcard.setSubCompanyUrl(card.getSubCompanyUrl());
	     newcard.setImportanceLevel(card.getImportanceLevel());
	     newcard.setIsEditting(card.getIsEditting());
	     newcard.setDateEditting(card.getDateEditting());
	     return newcard;
	}

	@Override
	public Integer updateUserCard(List<Integer> listCardUser, Integer userLeave, Integer userAssign,
			String nameAssign) {
		// TODO Auto-generated method stub
		return cardInfoDAO.updateUserCard(listCardUser,userLeave,userAssign,nameAssign);
	}

	public void savePrusalHistory(PrusalHistory prusalHistory){
		cardInfoDAO.savePrusalHistory(prusalHistory);
	}
	
	public List<com.ecard.core.vo.CardInfo> getListCardHistoryByCardId(Integer cardId){
		return cardInfoDAO.getListCardHistoryByCardId(cardId);
	}
	
	public List<com.ecard.core.vo.CardInfo> searchCompanyTrees(String searchText){
		return cardInfoDAO.searchCompanyTrees(searchText);
	}

	public Long countSameCardInfoByOwner(CardInfo cardInfo){
		return cardInfoDAO.countSameCardInfoByOwner(cardInfo);
	}
	
	public List<CardInfoNotifyChange> getListCardInfoNotifyChange(CardInfo cardInfo){
		return cardInfoDAO.getListCardInfoNotifyChange(cardInfo);
	}
}
