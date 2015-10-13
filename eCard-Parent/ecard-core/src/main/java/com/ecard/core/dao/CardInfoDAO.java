/*
 * CardInfoDAO class
 */
package com.ecard.core.dao;

import com.ecard.core.model.CardInfo;
import com.ecard.core.model.DownloadCsv;
import com.ecard.core.vo.CardConnectModel;
import com.ecard.core.vo.CardInfoAndPosCard;
import com.ecard.core.vo.CardInfoConnectUser;
import com.ecard.core.vo.CardInfoUserVo;
import com.ecard.core.vo.CompanyCardListCount;
import com.ecard.core.vo.CompanyCardModel;

import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author vinhla
 */
public interface CardInfoDAO {
	public List<CardInfo> listAllCardInfo();
	
    public List<CardInfo> listCardRecent();
    
    public List<CardInfo> listAllCardHistory();
    
    public List<CardInfoConnectUser> listConnectUser(Integer userId, Integer groupCompanyId, Integer recentFlg, Integer pageNumber);
    
    public List<CompanyCardListCount> listCompany(String permissionType, Integer userId);
    
    public List<CompanyCardModel> listCardWithCompany(String permissionType, Integer userId, String companyName);
    
    public Boolean editCardInfo(CardInfo cardInfo);
    
    public CardInfo getCardInfoDetail(Integer cardId);
    
    public List<CardInfo> getListPossesionCard(Integer userId, String searchText, String sort, int pageNumber);
    
    public List<com.ecard.core.vo.CardInfo> getListCardSearch(Integer userId, String searchText,String name,String position,String department,String company, int pageNumber, int groupCompanyId); 
    
    public BigInteger getTotalCardSearch(Integer userId, String searchText,String name,String position,String department,String company, int groupCompanyId); 
    
    public List<com.ecard.core.vo.CardInfo> getListCardSearchAll(String owner, String searchText,String name, String position,String department,String company, int pageNumber, int groupCompanyId); 
    
    public BigInteger getTotalCardSearchAll(String owner, String searchText,String name, String position,String department,String company, int pageNumber, int groupCompanyId); 
    
    //public List<com.ecard.core.vo.CardInfo> getListCardSearchAllUser(String searchText,String name, String position,String department,String company, int pageNumber, int groupCompanyId); 
    
    //public BigInteger getTotalCardSearchAllUser(String searchText,String name, String position,String department,String company, int pageNumber, int groupCompanyId); 
        
    public List<CardInfoAndPosCard> listCardPending(Integer userId);
    
    public List<com.ecard.core.vo.CardInfo> getListPossesionCardRecent(Integer userId);
    
    public CardInfo registerCardImage(CardInfo cardInfo);
    
    public int deleteCardInfo(Integer cardId);
    
    public List<CardConnectModel> listCardConnect(Integer cardOwnerId, Integer groupCompanyId, String name, String companyName, String email);
    
    public String getCardImage(Integer cardId);
    
    public List<com.ecard.core.vo.CardInfo> searchCard(String criteriaSearch, int status, List<Integer> listStatus, int limit, int offet);
    
    public int updateCardInfoAdmin(CardInfo cardInfo);
    
    public List<Integer> getUserIdByName(String name);
    
    public BigInteger countCardInfo(String criteriaSearch, int status, List<Integer> listStatus);
    
    public int updateStatusCard(CardInfo cardInfo);

    public List<CardInfo> getListMyCard(Integer userId);
    
    public List<CardInfo> getCompanyCard(Integer groupCompanyName);
    
    public void saveDownloadHistory(DownloadCsv downloadCsvId);
    
    public void registerPrusalHistory(Integer userId, Integer cardId);
    
//    public List<com.ecard.core.vo.CardInfo> getListCardSearchByMroonga(Integer groupCompanyId, List<Integer> listUserId, String searchText,String name, String position,String department,String company, int pageNumber);
//    
//    public List<com.ecard.core.vo.CardInfo> getListCardSearchAllUserByMroonga(Integer groupCompanyId, String searchText,String name, String position,String department,String company, int pageNumber, Integer userId);
//    
//    public BigInteger getTotalCardSearchAllUserByMroonga(Integer groupCompanyId, String searchText,String name, String position,String department,String company, int pageNumber, Integer userId);
//    
//    public BigInteger getTotalCardSearchAllByMroonga(Integer groupCompanyId, List<Integer> listUserId, String searchText,String name, String position,String department,String company, int pageNumber); 
    
    public List<CardInfoUserVo> getListPossesionCard(Integer userId, int pageNumber);
    
    public void updateOldCardInfo(CardInfo cardInfo);
    
    public CardInfo importCardInfoFromCsv(CardInfo cardInfo);
    
    public int getCardIdByCardIndexNo(String cardIndexNo);
    
    public List<CardInfo> getListCardInfoByUserId(Integer userId);
    
    public List<Integer> getListUserPushToByCard(CardInfo cardInfo);
    
    public List<Integer> getListUserPushFromByCard(CardInfo cardInfo);
    
    public int updateCardDeleted(Integer cardId);
    
    public List<com.ecard.core.vo.CardInfo> searchCardUser(String criteriaSearch, int status, List<Integer> listStatus, int limit, int offet, Integer userId);
    
    public BigInteger countCardInfoUser(String criteriaSearch, int status, List<Integer> listStatus, Integer userId);
    
    public void updateDateEditting(List<com.ecard.core.vo.CardInfo> cards);
    
    public void updateListCardInfor(List<CardInfo> cards);
    
    public int updateCardType();
    
    public List<CardInfo> listCardInfoByCardType(Integer cardType);
    
    public List<String> getListSortType(Integer userId);
    
    public int updateContactDate(CardInfo cardInfo);

    public Long countPossessionCard(Integer userId);

    public CardInfo getNewestCardInfo(CardInfo cardInfo);

    public List<CardInfo> getOldCardInfor();

    public int deleteListCard(List<Integer> listCard);
    
    public List<CardInfo> getListPossessionCardByTag(Integer userId, Integer tagId, String sort, int pageNumber);
    
    public List<CardInfoUserVo> getListPossessionCardByTag(Integer userId, Integer tagId, int pageNumber);
    
    public List<String> getListSortTypeByTag(Integer userId, Integer tagId);
    
    public void updateDownloadHistory(Integer downloadCsvId);
    
    public DownloadCsv getDownloadCSV(Integer csvId);

    public List<com.ecard.core.vo.CardInfo> searchCompanyTree(String companyName);
    
    public List<com.ecard.core.vo.CardInfo> searchDepartment(String companyName);
    
    public List<com.ecard.core.vo.CardInfo> searchCardInfo(String companyName, String departmentName);
}
