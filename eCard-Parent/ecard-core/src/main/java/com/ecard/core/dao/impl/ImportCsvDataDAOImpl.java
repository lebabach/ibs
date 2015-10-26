/**
 * 
 */
package com.ecard.core.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Query;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ecard.core.dao.ImportCsvDataDAO;
import com.ecard.core.model.CardInfo;
import com.ecard.core.model.CardTag;
import com.ecard.core.model.CardTagId;
import com.ecard.core.model.DataIndex;
import com.ecard.core.model.DataIndexId;
import com.ecard.core.model.PossessionCard;
import com.ecard.core.model.UserInfo;
import com.ecard.core.model.UserMigration;
import com.ecard.core.model.UserMigrationId;
import com.ecard.core.model.UserTag;
import com.ecard.core.model.enums.ActionTypeEnum;
import com.ecard.core.model.enums.IndexTypeEnum;
import com.ecard.core.model.enums.PropertyCodeEnum;
import com.ecard.core.model.enums.TableTypeEnum;
import com.ecard.core.util.DataIndexUtil;

/**
 * @author nhat.nguyen
 *
 */
@SuppressWarnings("rawtypes")
@Repository("importCsvDataDAO")
@Transactional
public class ImportCsvDataDAOImpl extends GenericDao implements ImportCsvDataDAO {
	
	@Transactional
	public int bulkInsertUserInfo(List<UserInfo> userInfoList, List<String> sansanIdList) {
		int totalInsertUser = 0;
		for (int i = 0; i < userInfoList.size(); i++) {
			totalInsertUser++;
			UserInfo userInfo = userInfoList.get(i);
			String indexNo = processingDataIndex(IndexTypeEnum.UserInfor, ActionTypeEnum.Insert, TableTypeEnum.User, PropertyCodeEnum.Migration, userInfo.getUserIndexNo(), userInfo.getGroupCompanyId());
			userInfo.setUserIndexNo(indexNo);
			getEntityManager().persist(userInfo);

			int id = userInfo.getUserId();

			UserMigration userMigration = new UserMigration();
			userMigration.setUserInfo(userInfo);
			UserMigrationId userMigrationId = new UserMigrationId();
			userMigrationId.setSansanUserId(sansanIdList.get(i));
			userMigrationId.setUserId(id);
			userMigration.setId(userMigrationId);
			getEntityManager().persist(userMigration);
		}
		getEntityManager().flush();
		getEntityManager().clear();
        return totalInsertUser;
	}
	
	@Transactional
	public void importListCardInfoFromCsv(List<CardInfo> cardInfoList) {
		
		for (CardInfo cardInfo : cardInfoList) {
			// card info
			/*String indexId = processingDataIndex(IndexTypeEnum.CardInfor, ActionTypeEnum.Insert, TableTypeEnum.CardInfor, PropertyCodeEnum.Migration, cardInfo.getCardIndexNo(), null);
			cardInfo.setCardIndexNo(indexId);
			cardInfo.setImageFile(DataIndexUtil.getIndexNoOfImageBy(TableTypeEnum.ImageInfor, indexId) + ".jpg");*/
			getEntityManager().persist(cardInfo);
			
			// possession Card
			if (cardInfo.getPossessionCards().size() > 0){
				PossessionCard possessionCard = cardInfo.getPossessionCards().iterator().next();
				if (possessionCard.getId() != null){
					possessionCard.getId().setCardId(cardInfo.getCardId());
					
					getEntityManager().persist(possessionCard);
				}
			}
			
			// card Tag
			if (cardInfo.getCardTags().size() > 0){
				for (CardTag cardTag : cardInfo.getCardTags()) {
					cardTag.setCardInfo(cardInfo);
					UserTag userTag = cardTag.getUserTag();	

					if (userTag != null){
						CardTagId cardTagId = new CardTagId();
						cardTagId.setCardId(cardInfo.getCardId());
						
						Integer tagId = findTagIdByUserTagName(cardInfo.getCardOwnerId(), userTag.getTagName());
						// If this tag name and userId is existing in UserTag table
						if (tagId != 0){
							cardTagId.setTagId(tagId);						
						} else {
							// user tag
							getEntityManager().persist(userTag);
							cardTagId.setTagId(userTag.getTagId());
						}
						
						cardTag.setId(cardTagId);
						cardTag.setUserTag(null);
						
						getEntityManager().persist(cardTag);
					}
				}
			}			
		}
		getEntityManager().flush();
		getEntityManager().clear();
	}
	
	private Integer findTagIdByUserTagName(Integer userId, String tagName){
		Query query = getEntityManager().createQuery("SELECT u FROM UserTag u WHERE u.userInfo.userId = :userId AND u.tagName = :tagName");
        query.setParameter("userId", userId);
        query.setParameter("tagName", tagName);
        @SuppressWarnings("unchecked")
		List<UserTag> utags =((List<UserTag>)query.getResultList());
        if(CollectionUtils.isNotEmpty(utags)){
        	return utags.get(0).getTagId();
        }
        return 0;
	}
	
	private String processingDataIndex(IndexTypeEnum indexType, ActionTypeEnum actionType, TableTypeEnum tableType, PropertyCodeEnum propertyCode, String indexNo, Integer groupCompanyId) {
		String formatIdAfterGenerating = StringUtils.EMPTY;
		@SuppressWarnings("unchecked")
		List<DataIndex> dataIndexs = (List<DataIndex>) findAll(DataIndex.class);
		dataIndexs = dataIndexs.stream().filter(x -> x.getId().getIndexType() == indexType.getStatusCode()).collect(Collectors.toList());
		DataIndex dataIndex = null;
		if (CollectionUtils.isNotEmpty(dataIndexs)){
			dataIndex = dataIndexs.get(dataIndexs.size() - 1);
			if (!StringUtils.isEmpty(indexNo)) {
				//No check groupCompanyId is NULL
				formatIdAfterGenerating = DataIndexUtil.generateFormatIdWith(tableType,
						DataIndexUtil.getSequenceCodeFrom(indexNo), DataIndexUtil.getPropertyCodeFrom(indexNo),
						PropertyCodeEnum.findByName(indexNo.substring(indexNo.length() - 1)), actionType,
						DataIndexUtil.getDateFrom(indexNo, propertyCode, actionType),dataIndex.getId().getIndexNo());
			} else {
				if(groupCompanyId == null){
					formatIdAfterGenerating = DataIndexUtil.generateFormatIdWith(tableType,
							DataIndexUtil.getSequenceCodeFrom(dataIndex.getId().getIndexNo()), DataIndexUtil.getPropertyCodeFrom(dataIndex.getId().getIndexNo()), 
							propertyCode, actionType,
							DataIndexUtil.getDateFrom(dataIndex.getId().getIndexNo(), propertyCode, actionType),dataIndex.getId().getIndexNo());
				}else{
					formatIdAfterGenerating = DataIndexUtil.generateFormatIdWith(tableType,
							DataIndexUtil.getSequenceCodeFrom(dataIndex.getId().getIndexNo()), groupCompanyId, 
							propertyCode, actionType,
							DataIndexUtil.getDateFrom(dataIndex.getId().getIndexNo(), propertyCode, actionType),dataIndex.getId().getIndexNo());
				}
			}
			Date date=new Date();
			Query query = getEntityManager().createQuery("UPDATE DataIndex t SET t.id.indexNo = :newIndexNo, t.id.createDate = :createDate WHERE t.id.indexType = :indexType");
			query.setParameter("newIndexNo", formatIdAfterGenerating);
			query.setParameter("indexType", indexType.getStatusCode());
			query.setParameter("createDate", date);
			query.executeUpdate();
		} else {
			if(groupCompanyId == null){
				formatIdAfterGenerating = DataIndexUtil.generateFormatIdWith(tableType, 0, 0, propertyCode, actionType);
			}else {
				formatIdAfterGenerating = DataIndexUtil.generateFormatIdWith(tableType, 0, groupCompanyId, propertyCode, actionType);
			}
			dataIndex = new DataIndex(new DataIndexId(indexType.getStatusCode(), formatIdAfterGenerating, new Date()));
			getEntityManager().persist(dataIndex);
		}
		
		return formatIdAfterGenerating;
	}
	
	@Transactional
	public int bulkInsertOperatorInfo(List<UserInfo> userInfoList) {
		int totalInsertUser = 0;
		for (int i = 0; i < userInfoList.size(); i++) {			
			UserInfo userInfo = userInfoList.get(i);
			if(checkEmailExist(userInfo.getEmail())){
				System.out.println("uploadCardCsv err=checkEmailExist importDAO ");
				continue;
			}
			totalInsertUser++;
			String indexNo = processingDataIndex(IndexTypeEnum.UserInfor, ActionTypeEnum.Insert, TableTypeEnum.User, PropertyCodeEnum.Migration, userInfo.getUserIndexNo(), userInfo.getGroupCompanyId());
			userInfo.setUserIndexNo(indexNo);
			getEntityManager().persist(userInfo);
		}
		getEntityManager().flush();
		getEntityManager().clear();
        return totalInsertUser;
	}
	
	public Boolean checkEmailExist(String email){
        Validate.notNull(email, "Email not null");
        Query query = getEntityManager().createQuery("SELECT u FROM UserInfo u WHERE u.email = :email");
        query.setParameter("email", email);
        
        Object result = getOrNull(query);
        if(result!=null){
            return true;
        }
        else{
            return false;
        }
    }
}
