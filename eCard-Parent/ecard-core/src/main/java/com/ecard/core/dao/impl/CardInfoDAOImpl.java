/*
 * CardInfoDAOImpl class
 */
package com.ecard.core.dao.impl;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ecard.core.dao.CardInfoDAO;
import com.ecard.core.model.CardInfo;
import com.ecard.core.model.CardTagId;
import com.ecard.core.model.DownloadCsv;
import com.ecard.core.model.PrusalHistory;
import com.ecard.core.model.UserTag;
import com.ecard.core.model.enums.PermissionType;
import com.ecard.core.model.enums.SearchConditions;
import com.ecard.core.vo.CardConnectModel;
import com.ecard.core.vo.CardInfoAndPosCard;
import com.ecard.core.vo.CardInfoConnectUser;
import com.ecard.core.vo.CardInfoUserVo;
import com.ecard.core.vo.CompanyCardListCount;
import com.ecard.core.vo.CompanyCardModel;
import com.ecard.core.vo.TagUser;

/**
 *
 * @author vinhla
 */
@Repository("cardInfoDAO")
public class CardInfoDAOImpl extends GenericDao implements CardInfoDAO {
	@Value("${compliance.recordDate}")
	private String complianceDate;

	@Value("${record.maxResult}")
	private Integer maxResult;

	public List<CardInfo> listAllCardInfo() {
		Query query = getEntityManager().createQuery("SELECT c FROM CardInfo c WHERE c.deleteFlg = 0");
		return query.getResultList();
	}

	public List<CardInfo> listCardRecent() {
		// Query query = getEntityManager().createQuery("SELECT c FROM CardInfo
		// c WHERE c.publishStatus = 1 AND c.approvalStatus = 1 ORDER BY
		// c.createDate DESC");
		Query query = getEntityManager().createQuery(
				"SELECT c FROM PrusalHistory p " + " LEFT JOIN p.cardInfo c " + " WHERE p.id.userId = :userId "
						+ " AND p.id.prusalDate BETWEEN (NOW() - INTERVAL 1 WEEK) AND NOW() ");
		return query.getResultList();
	}

	public List<CardInfo> listAllCardHistory() {
		Query query = getEntityManager().createQuery(
				"SELECT chris.id.cardId, c.name, c.companyName, c.departmentName, c.positionName, c.imageFile "
						+ "FROM CardUpdateHistory chris LEFT JOIN CardInfo c " + "ON chris.id.cardId = c.cardId"
						+ "ORDER BY c.updateDate DESC");
		return query.getResultList();
	}

	public List<CardInfoConnectUser> listConnectUser(Integer userId, Integer groupCompanyId, Integer recentFlg,
			Integer pageNumber) {

		String sqlStr = "SELECT  "
				+ " MC.card_id, MC.name, MC.last_name, MC.first_name, MC.name_kana, MC.last_name_kana, "
				+ "MC.first_name_kana, MC.company_name, MC.department_name, MC.position_name, MC.image_file, MC.email, MC.create_date "
				+ "FROM " + "    ( " + "     SELECT  "
				+ "          card_id, name, last_name, first_name, name_kana, last_name_kana, first_name_kana, "
				+ "          company_name, department_name, position_name, image_file, email, create_date "
				+ "       FROM  " + "         card_info CI " + "      WHERE  " + "         CI.card_owner_id = :userId "
				+ "     AND old_card_flg = 0  " + "     AND approval_status = 1  " + "     AND delete_flg = 0 "
				+ ") MC " + "INNER JOIN( " + "    SELECT  " + "         card_id " + "        ,image_file "
				+ "        ,email " + "        ,name " + "        ,company_name "
				+ "        ,department_name, create_date " + "      FROM " + "        card_info CI " + "     WHERE  "
				+ "        NOT CI.card_owner_id = :userId " + "    AND old_card_flg = 0  "
				+ "    AND approval_status = 1  " + "    AND delete_flg = 0 ";
		if (groupCompanyId == 1 || groupCompanyId == 2 || groupCompanyId == 3 || groupCompanyId == 4
				|| groupCompanyId == 5) {
			sqlStr += "         AND ( " + "               group_company_id IN(1,2,3,4,5) "
					+ "           OR (group_company_id NOT IN(1,2,3,4,5) AND contact_date >= '" + this.complianceDate
					+ "') " + "         ) ";
		} else {
			sqlStr += "         AND ( " + "               group_company_id = :groupCompanyId "
					+ "           OR (group_company_id <> :groupCompanyId AND contact_date >= '" + this.complianceDate
					+ "') " + "         ) ";
		}
		sqlStr += ") CI " + "WHERE  " + "    ( " + "        (MC.email = CI.email AND MC.email <> '') "
				+ "     OR (MC.name = CI.name AND MC.company_name = CI.company_name) " + ") ";
		if (recentFlg != 0) {
			sqlStr += "AND (MC.create_date >=  (NOW() - INTERVAL 1 WEEK) OR CI.create_date >=  (NOW() - INTERVAL 1 WEEK)) ";
		}
		sqlStr += "GROUP BY MC.card_id, MC.image_file, MC.company_name, MC.department_name, MC.name "
				+ "HAVING count(*) >= 1";

		Query query = getEntityManager().createNativeQuery(sqlStr);
		query.setFirstResult(pageNumber * this.maxResult);
		query.setMaxResults(this.maxResult);

		query.setParameter("userId", userId);
		if (groupCompanyId != 1 && groupCompanyId != 2 && groupCompanyId != 3 && groupCompanyId != 4
				&& groupCompanyId != 5) {
			query.setParameter("groupCompanyId", groupCompanyId);
		}
		List<Object[]> rows = query.getResultList();
		List<CardInfoConnectUser> result = new ArrayList<>(rows.size());
		for (Object[] row : rows) {
			result.add(new CardInfoConnectUser((Integer) row[0], (String) row[1], (String) row[2], (String) row[3],
					(String) row[4], (String) row[5], (String) row[6], (String) row[7], (String) row[8],
					(String) row[9], (String) row[10]));
		}
		return result;
	}

	public List<CompanyCardListCount> listCompany(String permissionType, Integer userId) {
		Query query = null;

		if (permissionType.equals(StringUtils.lowerCase(PermissionType.getEnumNameForValue(1)))) {
			query = getEntityManager().createNativeQuery("SELECT "
					+ "c.card_id AS cardId, c.company_id AS companyId, c.company_name AS companyName, c.company_name_kana AS companyNameKana, COUNT(c.card_id) AS cardCnt "
					+ "FROM possession_card po LEFT JOIN card_info AS c " + "ON po.card_id = c.card_Id "
					+ "WHERE po.user_id = :userId AND c.approval_status = 1 AND c.contact_date IN (select c.contact_date from card_info c "
					+ "where contact_date  = " + "(select max(contact_date) from card_info where contact_date >= '"
					+ this.complianceDate + "')) AND c.delete_flg != 1 " + "GROUP BY c.company_id "
					+ "ORDER BY companyNameKana DESC");
		} else {
			query = getEntityManager().createNativeQuery(
					"SELECT c.card_id AS cardId, c.company_id AS companyId, c.company_name AS companyName, c.company_name_kana AS companyNameKana, COUNT(c.card_id) AS cardCnt "
							+ "FROM possession_card po LEFT JOIN card_info AS c " + "ON po.card_id = c.card_Id "
							+ "WHERE po.user_id = :userId AND c.approval_status = 1 AND c.delete_flg != 1 "
							+ "GROUP BY c.company_name " + "ORDER BY c.company_name_kana DESC");
		}

		query.setParameter("userId", userId);

		List<Object[]> rows = query.getResultList();
		List<CompanyCardListCount> result = new ArrayList<>(rows.size());
		for (Object[] row : rows) {
			result.add(new CompanyCardListCount((Integer) row[0], (Integer) row[1], (String) row[2], (String) row[3],
					(BigInteger) row[4]));
		}
		return result;
	}

	public List<CompanyCardModel> listCardWithCompany(String permissionType, Integer userId, String companyName) {
		Query query = null;
		if (permissionType.equals("USER")) {
			query = getEntityManager().createNativeQuery("SELECT "
					+ "c.card_id AS cardId, c.name AS name, c.last_name AS lastName, c.first_name AS firstName, c.name_kana AS nameKana, "
					+ " c.last_name_kana AS lastNameKana, c.first_name_kana AS firstNameKana, "
					+ " c.company_name AS companyName, c.department_name AS departmentName, c.position_name AS positionName, c.image_file AS imageFile "
					+ "FROM possession_card po LEFT JOIN card_info AS c " + "ON po.card_id = c.card_Id "
					+ "WHERE po.user_id = :userId AND c.approval_status = 1 AND c.delete_flg != 1 "
					+ "AND c.company_name = :companyName "
					+ "AND c.contact_date IN (select ci.contact_date from card_info ci " + "where ci.contact_date  = "
					+ "(select max(contact_date) from card_info where contact_date >= '" + this.complianceDate + "')) "
					+ "GROUP BY c.card_id " + "ORDER BY c.create_date DESC");
		} else {
			query = getEntityManager().createNativeQuery("SELECT "
					+ "c.card_id AS cardId, c.name AS name, c.last_name AS lastName, c.first_name AS firstName, c.name_kana AS nameKana, "
					+ " c.last_name_kana AS lastNameKana, c.first_name_kana AS firstNameKana, "
					+ "c.company_name AS companyName, c.department_name AS departmentName, c.position_name AS positionName, c.image_file AS imageFile "
					+ "FROM possession_card po LEFT JOIN card_info AS c " + "ON po.card_id = c.card_Id "
					+ "WHERE po.user_id = :userId AND c.approval_status = 1 AND c.delete_flg != 1 "
					+ "AND c.company_name = :companyName " + "GROUP BY c.card_id " + "ORDER BY c.create_date DESC");
		}
		query.setParameter("userId", userId);
		query.setParameter("companyName", companyName);

		List<Object[]> rows = query.getResultList();
		List<CompanyCardModel> result = new ArrayList<>(rows.size());
		for (Object[] row : rows) {
			result.add(new CompanyCardModel((Integer) row[0], (String) row[1], (String) row[2], (String) row[3],
					(String) row[4], (String) row[5], (String) row[6], (String) row[7], (String) row[8],
					(String) row[9], (String) row[10]));
		}

		return result;
	}

    public Boolean editCardInfo(CardInfo cardInfo){
    	
    	getEntityManager().merge(cardInfo);
    	getEntityManager().flush();
    	
    	return true;
    }
    
    public CardInfo getCardInfoDetail(Integer cardId) {
        
        Query query = getEntityManager().createQuery("SELECT c FROM CardInfo c "
                            + "WHERE c.cardId = :cardId "
                            + "ORDER BY c.createDate DESC");
        query.setParameter("cardId", cardId);
        return (CardInfo)query.getSingleResult();
    }
    
    public List<CardInfo> getListPossesionCard(Integer userId, String searchText, String sort, int pageNumber){
        String sqlStr = "SELECT c FROM CardInfo c "
                + "WHERE c.cardOwnerId = :userId AND c.approvalStatus = 1 AND c.deleteFlg = 0 ";
        Query query = null;
        if(searchText != null) {
            sqlStr += "AND ( c.name LIKE :searchText "
                    + "OR c.firstName LIKE :searchText "
                    + "OR c.lastName LIKE :searchText "
                    + "OR c.firstNameKana LIKE :searchText "
                    + "OR c.lastNameKana LIKE :searchText "
                    + "OR c.telNumberCompany LIKE :searchText "
                    + "OR c.addressFull LIKE :searchText "
                    + "OR c.mobileNumber LIKE :searchText "
                    + "OR c.nameKana LIKE :searchText "
                    + "OR c.companyName LIKE :searchText "
                    + "OR c.companyNameKana LIKE :searchText "
                    + "OR c.departmentName LIKE :searchText "
                    + "OR c.positionName LIKE :searchText "
                    + "OR c.email LIKE :searchText ) ";
            query = getEntityManager().createQuery(sqlStr);
            query.setParameter("searchText", "%" + searchText.toLowerCase() + "%");
        }
        else {
            if(sort.equals(StringUtils.lowerCase(SearchConditions.NAME.name()))) {
                sqlStr += "ORDER BY c.nameKana ASC ";
            }
            else if(sort.equals(StringUtils.lowerCase(SearchConditions.COMPANY.name()))) {
                sqlStr += "ORDER BY c.companyNameKana ASC ";
            }
            else if(sort.equals(StringUtils.lowerCase(SearchConditions.CONTACT.name()))) {
                sqlStr += "ORDER BY c.contactDate DESC ";
            }
            query = getEntityManager().createQuery(sqlStr);
        }
        query.setParameter("userId", userId);
        query.setFirstResult(pageNumber * this.maxResult);
        query.setMaxResults(this.maxResult);
        return query.getResultList();
    }
    
    public List<com.ecard.core.vo.CardInfo> getListCardSearch(Integer userId, String searchText,String name, String position,String department,String company, int pageNumber, int groupCompanyId) {                
        String sqlStr = "SELECT c.* FROM card_info c WHERE c.old_card_flg = 0 "
                + "AND c.approval_status = 1 AND c.delete_flg = 0 AND c.card_owner_id = :userId ";
        
        /*if (groupCompanyId == 1 || groupCompanyId ==2 || groupCompanyId ==3 || groupCompanyId == 4 || groupCompanyId == 5){
             sqlStr += " AND (c.group_company_id IN(1,2,3,4,5) OR (c.group_company_id NOT IN(1,2,3,4,5) AND c.contact_date >= '"+ this.complianceDate +"')) ";
        } else {
             sqlStr += " AND (c.group_company_id = " + groupCompanyId + " OR (c.group_company_id <> " + groupCompanyId 
                     + "  AND c.contact_date >= '"+ this.complianceDate +"')) ";
        }*/
                
        if(position != null) {
            String params[] = { "'*W1:0,2:0,3:0,4:0,5:0,6:1,7:0,8:0,9:0,10:0,11:0,12:0,13:0,14:0 ", position.toLowerCase(), "*'"};
        	
            sqlStr += "AND MATCH(c.company_name,c.company_name_kana,c.name,c.name_kana,c.department_name,c.position_name,c.email,c.zip_code,c.address_full,"
            		+"c.tel_number_company,c.tel_number_department,c.fax_number,c.mobile_number,c.card_owner_name) "
                    + "AGAINST(%s %s %s IN BOOLEAN MODE)";
            
            sqlStr = String.format(sqlStr, params);
        }
        if(name  != null) {
        	String params[] = { "'*W1:1,2:1,3:1,4:1,5:0,6:0,7:0,8:0,9:0,10:0,11:0,12:0,13:0,14:0 ", name.toLowerCase(), "*'"};
        	
            sqlStr += "AND MATCH(c.company_name,c.company_name_kana,c.name,c.name_kana,c.department_name,c.position_name,c.email,c.zip_code,c.address_full,"
            		+"c.tel_number_company,c.tel_number_department,c.fax_number,c.mobile_number,c.card_owner_name) "
                    + "AGAINST(%s %s %s IN BOOLEAN MODE)";
            sqlStr = String.format(sqlStr, params);
        }
        if(department  != null) {
        	String params[] = { "'*W1:0,2:0,3:0,4:0,5:1,6:0,7:0,8:0,9:0,10:0,11:0,12:0,13:0,14:0 ", department.toLowerCase(), "*'"};
        	
            sqlStr += "AND MATCH(c.company_name,c.company_name_kana,c.name,c.name_kana,c.department_name,c.position_name,c.email,c.zip_code,c.address_full,"
            		+"c.tel_number_company,c.tel_number_department,c.fax_number,c.mobile_number,c.card_owner_name) "
                    + "AGAINST(%s %s %s IN BOOLEAN MODE)";
            sqlStr = String.format(sqlStr, params);
        }
        if(company  != null) {
        	String params[] = { "'*W1:1,2:0,3:0,4:0,5:0,6:0,7:0,8:0,9:0,10:0,11:0,12:0,13:0,14:0 ", company.toLowerCase(), "*'"};
        	
            sqlStr += "AND MATCH(c.company_name,c.company_name_kana,c.name,c.name_kana,c.department_name,c.position_name,c.email,c.zip_code,c.address_full,"
            		+"c.tel_number_company,c.tel_number_department,c.fax_number,c.mobile_number,c.card_owner_name) "
                    + "AGAINST(%s %s %s IN BOOLEAN MODE)";
            sqlStr = String.format(sqlStr, params);
        }
        if(name == null || position == null || department == null || company ==  null){
            if(searchText != null) {
                List<String> listSearchText= new ArrayList<String>( Arrays.asList(searchText.trim().split(" ")));
                String searchTxt = "";
                for(int i=0; i < listSearchText.size(); i++){
                    searchTxt += (listSearchText.get(i) != null) ? " +\"" + listSearchText.get(i) + "\" " : "";
                }
                
            	//String params[] = { "'+{", searchText.toLowerCase(), "}'"};
                String params[] = { "'*W1:1,2:1,3:1,4:1,5:0,6:0,7:1,8:0,9:0,10:0,11:0,12:0,13:0,14:0 ", searchTxt, " '"};
            	
                sqlStr += "AND MATCH(c.company_name,c.company_name_kana,c.name,c.name_kana,c.department_name,c.position_name,c.email,c.zip_code,c.address_full,"
                		+"c.tel_number_company,c.tel_number_department,c.fax_number,c.mobile_number,c.card_owner_name) "
                        + "AGAINST(%s %s %s IN BOOLEAN MODE)";
                sqlStr = String.format(sqlStr, params);
            }
        }
        
      
        Query query = getEntityManager().createNativeQuery(sqlStr);
        
        query.setParameter("userId", userId);
        //query.setParameter("groupCompanyId", groupCompanyId);
        query.setFirstResult(pageNumber * this.maxResult);
        query.setMaxResults(this.maxResult);
        
        List<Object[]> rows = query.getResultList();
        List<com.ecard.core.vo.CardInfo> result = new ArrayList<>(rows.size());
        for (Object[] row : rows) {
            result.add(new com.ecard.core.vo.CardInfo((Integer)row[0], (String)row[9], (String)row[11], (String)row[10], (String)row[12], (String)row[14],
                    (String)row[13], (String)row[5], (String)row[7], (String)row[2],(String)row[8], (Date)row[47], (Integer)row[45], (String)row[22], (String)row[15], (Integer)row[4], (Integer)row[53], (String)row[17],(Date)row[55]));
        }
        
        return result;
    }
    
    public BigInteger getTotalCardSearch(Integer userId, String searchText,String name, String position,String department,String company, int groupCompanyId) {                
        String sqlStr = "SELECT COUNT(*) FROM card_info c WHERE c.old_card_flg = 0 AND c.approval_status = 1 "
                + "AND c.delete_flg = 0 AND c.card_owner_id = :userId ";
        
       /* if (groupCompanyId == 1 || groupCompanyId ==2 || groupCompanyId ==3 || groupCompanyId == 4 || groupCompanyId == 5 ){
             sqlStr += " AND (c.group_company_id IN(1,2,3,4,5) OR (c.group_company_id NOT IN(1,2,3,4,5) AND c.contact_date >= '"+ this.complianceDate +"')) ";
        } else {
             sqlStr += " AND (c.group_company_id = " + groupCompanyId + " OR (c.group_company_id <> " + groupCompanyId 
                     + "  AND c.contact_date >= '"+ this.complianceDate +"')) ";
        }*/
                
        if(position != null) {
            String params[] = { "'*W1:0,2:0,3:0,4:0,5:0,6:1,7:0,8:0,9:0,10:0,11:0,12:0,13:0,14:0 ", position.toLowerCase(), "*'"};
        	
            sqlStr += "AND MATCH(c.company_name,c.company_name_kana,c.name,c.name_kana,c.department_name,c.position_name,c.email,c.zip_code,c.address_full,"
            		+"c.tel_number_company,c.tel_number_department,c.fax_number,c.mobile_number,c.card_owner_name) "
                    + "AGAINST(%s %s %s IN BOOLEAN MODE)";
            
            sqlStr = String.format(sqlStr, params);
        }
        if(name  != null) {
        	String params[] = { "'*W1:1,2:1,3:1,4:1,5:0,6:0,7:0,8:0,9:0,10:0,11:0,12:0,13:0,14:0 ", name.toLowerCase(), "*'"};
        	
            sqlStr += "AND MATCH(c.company_name,c.company_name_kana,c.name,c.name_kana,c.department_name,c.position_name,c.email,c.zip_code,c.address_full,"
            		+"c.tel_number_company,c.tel_number_department,c.fax_number,c.mobile_number,c.card_owner_name) "
                    + "AGAINST(%s %s %s IN BOOLEAN MODE)";
            sqlStr = String.format(sqlStr, params);
        }
        if(department  != null) {
        	String params[] = { "'*W1:0,2:0,3:0,4:0,5:1,6:0,7:0,8:0,9:0,10:0,11:0,12:0,13:0,14:0 ", department.toLowerCase(), "*'"};
        	
            sqlStr += "AND MATCH(c.company_name,c.company_name_kana,c.name,c.name_kana,c.department_name,c.position_name,c.email,c.zip_code,c.address_full,"
            		+"c.tel_number_company,c.tel_number_department,c.fax_number,c.mobile_number,c.card_owner_name) "
                    + "AGAINST(%s %s %s IN BOOLEAN MODE)";
            sqlStr = String.format(sqlStr, params);
        }
        if(company  != null) {
        	String params[] = { "'*W1:1,2:0,3:0,4:0,5:0,6:0,7:0,8:0,9:0,10:0,11:0,12:0,13:0,14:0 ", company.toLowerCase(), "*'"};
        	
            sqlStr += "AND MATCH(c.company_name,c.company_name_kana,c.name,c.name_kana,c.department_name,c.position_name,c.email,c.zip_code,c.address_full,"
            		+"c.tel_number_company,c.tel_number_department,c.fax_number,c.mobile_number,c.card_owner_name) "
                    + "AGAINST(%s %s %s IN BOOLEAN MODE)";
            sqlStr = String.format(sqlStr, params);
        }
        if(name == null || position == null || department == null || company ==  null){
            if(searchText != null) {
                List<String> listSearchText= new ArrayList<String>( Arrays.asList(searchText.trim().split(" ")));
                String searchTxt = "";
                for(int i=0; i < listSearchText.size(); i++){
                    searchTxt += (listSearchText.get(i) != null) ? " +\"" + listSearchText.get(i) + "\" " : "";
                }
            	//String params[] = { "'+{", searchText.toLowerCase(), "}'"};
                String params[] = { "'*W1:1,2:1,3:1,4:1,5:0,6:0,7:1,8:0,9:0,10:0,11:0,12:0,13:0,14:0 ", searchTxt.toLowerCase(), " '"};
            	
                sqlStr += "AND MATCH(c.company_name,c.company_name_kana,c.name,c.name_kana,c.department_name,c.position_name,c.email,c.zip_code,c.address_full,"
                		+"c.tel_number_company,c.tel_number_department,c.fax_number,c.mobile_number,c.card_owner_name) "
                        + "AGAINST(%s %s %s IN BOOLEAN MODE)";
                sqlStr = String.format(sqlStr, params);
            }
        }
        
        Query query = getEntityManager().createNativeQuery(sqlStr);
        
        query.setParameter("userId", userId);
        return (BigInteger)getOrNull(query);
    }
    
    public List<CardInfoAndPosCard> listCardPending(Integer userId) {

		Query query = getEntityManager().createQuery(
				"SELECT new com.ecard.core.vo.CardInfoAndPosCard(d.id.cardId, c.approvalStatus, c.imageFile, c.createDate) FROM PossessionCard d "
						+ " LEFT JOIN d.cardInfo c " + " WHERE c.approvalStatus != 1 AND c.deleteFlg != 1 "
						+ " AND d.id.userId = :userId " + " GROUP BY d.id.cardId " + " ORDER BY c.createDate DESC ");
		query.setParameter("userId", userId);
		return query.getResultList();
	}

	public List<com.ecard.core.vo.CardInfo> getListPossesionCardRecent(Integer userId) {
		String sqlStr = "SELECT * FROM card_info WHERE old_card_flg = 0 " + " AND approval_status = 1 "
				+ " AND delete_flg = 0 " + " AND create_date >= (NOW() - INTERVAL 1 WEEK) "
				+ " AND card_owner_id = :userId " + " ORDER BY create_date DESC";

		Query query = getEntityManager().createNativeQuery(sqlStr);

		query.setParameter("userId", userId);
		query.setFirstResult(0);
		query.setMaxResults(this.maxResult);

		List<Object[]> rows = query.getResultList();
		List<com.ecard.core.vo.CardInfo> result = new ArrayList<>(rows.size());
		for (Object[] row : rows) {
			result.add(new com.ecard.core.vo.CardInfo((Integer) row[0], (String) row[9], (String) row[11],
					(String) row[10], (String) row[12], (String) row[14], (String) row[13], (String) row[5],
					(String) row[6], (String) row[7], (String) row[2], (String) row[8], (Date) row[47],
					(Integer) row[45], (String) row[22], (String) row[15]));
		}

		return result;
	}

	public CardInfo registerCardImage(CardInfo cardInfo) {
		getEntityManager().persist(cardInfo);
		getEntityManager().flush();

		return cardInfo;
	}

	public int deleteCardInfo(Integer cardId) {
		Validate.notNull(cardId, "CardId is not null");
		Query query = getEntityManager().createQuery(
				"UPDATE CardInfo c SET c.deleteFlg = 1, c.deletDate = :deletDate WHERE c.cardId = :cardId");
		query.setParameter("cardId", cardId);
		query.setParameter("deletDate", new Date());
		return query.executeUpdate();
	}

	public List<CardConnectModel> listCardConnect(Integer cardOwnerId, Integer groupCompanyId, String name,
			String companyName, String email) {
		Validate.notNull(cardOwnerId, "cardOwnerId is not null");
		Validate.notNull(groupCompanyId, "groupCompanyId is not null");

		/*
		 * String sqlStr =
		 * "SELECT u.*, c.card_id FROM user_info AS u INNER JOIN " +
		 * "(SELECT ci.card_owner_id FROM card_info AS ci WHERE ci.old_card_flg = 0 AND ci.approval_status = 1 "
		 * + "AND ci.delete_flg = 0 AND ci.card_owner_id <> :cardOwnerId ";
		 */
		String sqlStr = "SELECT u.* FROM user_info AS u INNER JOIN "
				+ "(SELECT ci.card_owner_id FROM card_info AS ci WHERE ci.approval_status = 1 "
				+ "AND ci.delete_flg = 0 AND ci.card_owner_id <> :cardOwnerId ";

		if (groupCompanyId == 1 || groupCompanyId == 2 || groupCompanyId == 3 || groupCompanyId == 4
				|| groupCompanyId == 5) {
			sqlStr += "AND ((ci.email = :email AND ci.email <> '') OR (ci.name = :name AND ci.company_name = :companyName)) "
					+ "AND (ci.group_company_id IN(1,2,3,4,5) OR (ci.group_company_id NOT IN(1,2,3,4,5) AND contact_date >= '"
					+ this.complianceDate + "')) " + "GROUP BY card_owner_id";
		} else {
			sqlStr += "AND ((ci.email = :email AND ci.email <> '') OR (ci.name = :name AND ci.company_name = :companyName)) "
					+ "AND (ci.group_company_id = " + groupCompanyId + " OR (ci.group_company_id <> " + groupCompanyId
					+ " AND contact_date >= '" + this.complianceDate + "')) " + "GROUP BY card_owner_id";
		}
		sqlStr += ") AS c ON u.user_id = c.card_owner_id AND u.delete_flg = 0";

		Query query = getEntityManager().createNativeQuery(sqlStr);
		query.setParameter("cardOwnerId", cardOwnerId);
		query.setParameter("name", name);
		query.setParameter("companyName", companyName);
		query.setParameter("email", email);

		List<Object[]> rows = query.getResultList();
		List<CardConnectModel> result = new ArrayList<>(rows.size());
		for (Object[] row : rows) {
			result.add(new CardConnectModel(0, (String) row[15], (String) row[16], (String) row[17], (String) row[18],
					(String) row[19], (String) row[20], (String) row[22], (String) row[24], (String) row[25],
					(String) row[32], (String) row[4], (String) row[23], (Integer) row[0]));
		}

		return result;
	}

	public String getCardImage(Integer cardId) {
		Validate.notNull(cardId, "CardId is not null");
		Query query = getEntityManager().createQuery("SELECT c.imageFile FROM CardInfo c WHERE c.cardId = :cardId");
		query.setParameter("cardId", cardId);

		return (String) getOrNull(query);
	}

	@SuppressWarnings("unchecked")
	public List<com.ecard.core.vo.CardInfo> searchCard(String criteriaSearch, int status, List<Integer> listStatus,
			int limit, int offet) {
		String sqlQuery = "SELECT * FROM card_info AS u WHERE ";
		if (!criteriaSearch.isEmpty())
			sqlQuery += "(u.name REGEXP  :criteriaSearch " + "OR u.last_name REGEXP :criteriaSearch "
					+ "OR u.first_name REGEXP :criteriaSearch " + "OR u.last_name_kana REGEXP :criteriaSearch "
					+ "OR u.department_name REGEXP :criteriaSearch " + "OR u.tel_number_company REGEXP :criteriaSearch "
					+ "OR u.email REGEXP :criteriaSearch " + "OR u.position_name REGEXP :criteriaSearch "
					+ "OR u.address_full REGEXP :criteriaSearch " + "OR u.first_name_kana REGEXP :criteriaSearch "
					+ "OR u.company_name REGEXP :criteriaSearch " + "OR u.company_name_kana REGEXP :criteriaSearch "
					+ "OR u.mobile_number REGEXP :criteriaSearch) AND ";
		if (status > 0)
			sqlQuery += "u.approval_status = :status AND ";
		sqlQuery += "u.delete_flg = 0 AND u.approval_status IN (:listStatus) ORDER BY u.create_date DESC";
		Query query = null;
		if (limit > -1 && offet > -1) {
			query = getEntityManager().createNativeQuery(sqlQuery).setFirstResult(offet).setMaxResults(limit);
		} else {
			query = getEntityManager().createNativeQuery(sqlQuery);
		}
		if (!criteriaSearch.isEmpty()) {
			query.setParameter("criteriaSearch", criteriaSearch);
		}
		if (status > 0) {
			query.setParameter("status", status);
		}
		query.setParameter("listStatus", listStatus);
		List<Object[]> rows = query.getResultList();
		List<com.ecard.core.vo.CardInfo> result = new ArrayList<>(rows.size());

		for (Object[] row : rows) {
			result.add(new com.ecard.core.vo.CardInfo((Integer) row[0], (String) row[9], (String) row[10],
					(String) row[11], (String) row[12], (String) row[13], (String) row[14], (String) row[5],
					(String) row[7], (String) row[8], (String) row[2], (Date) row[47], (Integer) row[4],
					(Integer) row[1], (String) row[6], (String) row[15], (String) row[16], (String) row[17],
					(String) row[18], (String) row[19], (String) row[20], (String) row[22], (String) row[23],
					(String) row[24], (String) row[25], (String) row[26], (String) row[27], (String) row[28],
					(Integer) row[43], (Integer) row[44], (Integer) row[45], (Date) row[48], (Integer) row[49],
					(Date) row[50], (Integer) row[51], (String) row[56], (int) row[61]));
		}

		return result;

	}

	public BigInteger countCardInfo(String criteriaSearch, int status, List<Integer> listStatus) {
		String sqlQuery = "SELECT COUNT(*) FROM card_info u WHERE ";
		if (!criteriaSearch.isEmpty())
			sqlQuery += "(u.name REGEXP  :criteriaSearch " + "OR u.last_name REGEXP :criteriaSearch "
					+ "OR u.first_name REGEXP :criteriaSearch " + "OR u.last_name_kana REGEXP :criteriaSearch "
					+ "OR u.department_name REGEXP :criteriaSearch " + "OR u.tel_number_company REGEXP :criteriaSearch "
					+ "OR u.email REGEXP :criteriaSearch " + "OR u.position_name REGEXP :criteriaSearch "
					+ "OR u.address_full REGEXP :criteriaSearch " + "OR u.first_name_kana REGEXP :criteriaSearch "
					+ "OR u.company_name REGEXP :criteriaSearch " + "OR u.company_name_kana REGEXP :criteriaSearch "
					+ "OR u.mobile_number REGEXP :criteriaSearch) AND ";
		if (status > 0) {
			sqlQuery += "u.approval_status = :status AND ";
		}
		sqlQuery += "u.delete_flg = 0 AND u.approval_status IN (:listStatus) ORDER BY u.delet_date DESC";
		Query query = getEntityManager().createNativeQuery(sqlQuery);
		if (!criteriaSearch.isEmpty()) {
			query.setParameter("criteriaSearch", criteriaSearch);
		}
		if (status > 0) {
			query.setParameter("status", status);
		}
		query.setParameter("listStatus", listStatus);
		return (BigInteger) query.getSingleResult();
	}

	public int updateCardInfoAdmin(CardInfo cardInfo) {
		{
			String sql = "UPDATE CardInfo c SET c.firstName = :firstName, c.lastName = :lastName, c.firstNameKana = :firstNameKana, "
					+ "c.lastNameKana = :lastNameKana, c.telNumberCompany = :telNumberCompany, c.telNumberDepartment = :telNumberDepartment, c.telNumberDirect = :telNumberDirect, "
					+ "c.mobileNumber = :mobileNumber, c.faxNumber = :faxNumber, c.email = :email, c.companyName = :companyName, "
					+ "c.companyNameKana = :companyNameKana, c.departmentName = :departmentName, c.positionName = :positionName, "
					+ "c.addressFull = :addressFull, c.name = :name, c.nameKana = :nameKana, c.cardIndexNo = :cardIndexNo,c.updateDate = :updateDate,"
					+ "c.companyUrl = :companyUrl, c.zipCode = :zipCode, c.address1 = :address1, c.address2 = :address2, c.address3 = :address3,c.address4 = :address4, c.memo1 = :memo1, c.memo2 = :memo2, c.approvalStatus = :approvalStatus,"
					+ "c.updateDate = :updateDate, c.newestCardFlg = :newestCardFlg, c.contactDate = :contactDate, c.isEditting = :isEditting, c.dateEditting = :dateEditting "
					+ "WHERE c.cardId = :cardId";
			Query query = getEntityManager().createQuery(sql);

			query.setParameter("firstName", cardInfo.getFirstName());
			query.setParameter("lastName", cardInfo.getLastName());
			query.setParameter("firstNameKana", cardInfo.getFirstNameKana());
			query.setParameter("lastNameKana", cardInfo.getLastNameKana());
			query.setParameter("telNumberCompany", cardInfo.getTelNumberCompany());
			query.setParameter("telNumberDepartment", cardInfo.getTelNumberDepartment());
			query.setParameter("telNumberDirect", cardInfo.getTelNumberDirect());
			query.setParameter("mobileNumber", cardInfo.getMobileNumber());
			query.setParameter("faxNumber", cardInfo.getFaxNumber());
			query.setParameter("email", cardInfo.getEmail());
			query.setParameter("companyName", cardInfo.getCompanyName());
			query.setParameter("companyNameKana", cardInfo.getCompanyNameKana());
			query.setParameter("departmentName", cardInfo.getDepartmentName());
			query.setParameter("positionName", cardInfo.getPositionName());
			query.setParameter("companyUrl", cardInfo.getCompanyUrl());
			query.setParameter("zipCode", cardInfo.getZipCode());
			query.setParameter("address1", cardInfo.getAddress1());
			query.setParameter("address2", cardInfo.getAddress2());
			query.setParameter("address3", cardInfo.getAddress3());
			query.setParameter("address4", cardInfo.getAddress4());
			query.setParameter("address4", cardInfo.getAddress4());
			query.setParameter("addressFull", cardInfo.getAddressFull());
			query.setParameter("memo1", cardInfo.getMemo1());
			query.setParameter("memo2", cardInfo.getMemo2());
			query.setParameter("approvalStatus", cardInfo.getApprovalStatus());
			query.setParameter("cardId", cardInfo.getCardId());
			query.setParameter("updateDate", cardInfo.getUpdateDate());
			query.setParameter("name", cardInfo.getName());
			query.setParameter("nameKana", cardInfo.getNameKana());
			query.setParameter("cardIndexNo", cardInfo.getCardIndexNo());
			query.setParameter("updateDate", new Date());
			query.setParameter("newestCardFlg", cardInfo.getNewestCardFlg());
			query.setParameter("contactDate", cardInfo.getContactDate());
			query.setParameter("isEditting", cardInfo.getIsEditting());
			query.setParameter("dateEditting", cardInfo.getDateEditting());
			System.out.println(
					"===================debug=======update======cardinfor============" + printSql(sql, cardInfo));
			return query.executeUpdate();
		}
	}

	private String printSql(String sql, CardInfo cardInfo) {
		sql = sql.replace(":firstName", cardInfo.getFirstName() + "");
		sql = sql.replace(":lastName", cardInfo.getLastName() + "");
		sql = sql.replace(":firstNameKana", cardInfo.getFirstNameKana() + "");
		sql = sql.replace(":lastNameKana", cardInfo.getLastNameKana() + "");
		sql = sql.replace(":telNumberCompany", cardInfo.getTelNumberCompany() + "");
		sql = sql.replace(":telNumberDepartment", cardInfo.getTelNumberDepartment() + "");
		sql = sql.replace(":telNumberDirect", cardInfo.getTelNumberDirect() + "");
		sql = sql.replace(":mobileNumber", cardInfo.getMobileNumber() + "");
		sql = sql.replace(":faxNumber", cardInfo.getFaxNumber() + "");
		sql = sql.replace(":email", cardInfo.getEmail() + "");
		sql = sql.replace(":companyName", cardInfo.getCompanyName() + "");
		sql = sql.replace(":companyNameKana", cardInfo.getCompanyNameKana() + "");
		sql = sql.replace(":departmentName", cardInfo.getDepartmentName() + "");
		sql = sql.replace(":positionName", cardInfo.getPositionName() + "");
		sql = sql.replace(":companyUrl", cardInfo.getCompanyUrl() + "");
		sql = sql.replace(":subZipCode", cardInfo.getSubZipCode() + "");
		sql = sql.replace(":address1", cardInfo.getAddress1() + "");
		sql = sql.replace(":address2", cardInfo.getAddress2() + "");
		sql = sql.replace(":address3", cardInfo.getAddress3() + "");
		sql = sql.replace(":address4", cardInfo.getAddress4() + "");
		sql = sql.replace(":address4", cardInfo.getAddress4() + "");
		sql = sql.replace(":addressFull", cardInfo.getAddressFull() + "");
		sql = sql.replace(":memo1", cardInfo.getMemo1() + "");
		sql = sql.replace(":memo2", cardInfo.getMemo2() + "");
		sql = sql.replace(":approvalStatus", cardInfo.getApprovalStatus() + "");
		sql = sql.replace(":cardId", cardInfo.getCardId() + "");
		sql = sql.replace(":updateDate", cardInfo.getUpdateDate() + "");
		sql = sql.replace(":name", cardInfo.getName() + "");
		sql = sql.replace(":nameKana", cardInfo.getNameKana() + "");
		sql = sql.replace(":cardIndexNo", cardInfo.getCardIndexNo() + "");
		sql = sql.replace(":updateDate", (new Date()) + "");
		sql = sql.replace(":newestCardFlg", cardInfo.getNewestCardFlg() + "");
		sql = sql.replace(":contactDate", cardInfo.getContactDate() + "");
		return sql;
	}

	public int updateStatusCard(CardInfo cardInfo) {
		Query query = getEntityManager().createQuery(
				"UPDATE CardInfo c SET c.approvalStatus = :approvalStatus,c.updateDate = :updateDate,c.isEditting = :isEditting,c.dateEditting = :dateEditting "
						+ "WHERE c.cardId = :cardId");
		query.setParameter("approvalStatus", cardInfo.getApprovalStatus());
		query.setParameter("cardId", cardInfo.getCardId());
		query.setParameter("updateDate", cardInfo.getUpdateDate());
		query.setParameter("isEditting", cardInfo.getIsEditting());
		query.setParameter("dateEditting", cardInfo.getDateEditting());
		return query.executeUpdate();
	}

	public List<Integer> getUserIdByName(String name) {
		Query query = getEntityManager().createNativeQuery("SELECT ui.user_id FROM user_info ui"
				+ " WHERE (ui.last_name LIKE :name " + " OR ui.name LIKE :name OR ui.first_name LIKE :name "
				+ " OR ui.name_kana LIKE :name OR ui.last_name_kana LIKE :name OR ui.first_name_kana LIKE :name)");
		query.setParameter("name", "%" + name.toLowerCase() + "%");
		List<Integer> rows = query.getResultList();
		List<Integer> result = new ArrayList<>(rows.size());
		for (Integer row : rows) {
			result.add(row);
		}
		return result;
	}

	public List<CardInfo> getListMyCard(Integer userId) {
		Query query = getEntityManager().createQuery(
				"SELECT c FROM CardInfo c WHERE c.cardOwnerId = :userId "
						+ " AND c.approvalStatus = 1 AND c.deleteFlg = 0");
		query.setParameter("userId", userId);
		return query.getResultList();
	}
	// SELECT * FROM card_info ci
	// INNER JOIN user_info ui
	// ON ui.company_name = ci.company_name
	// WHERE ui.user_id = 2 AND ci.delete_flg = 0 AND ci.approval_status = 1

	public List<CardInfo> getCompanyCard(Integer groupCompanyName) {
		/*
		 * Query query = getEntityManager().createQuery(
		 * "SELECT p.cardInfo FROM PossessionCard p LEFT JOIN p.cardInfo c WHERE p.id.userId IN "
		 * +
		 * " (SELECT ui.userId FROM UserInfo ui WHERE ui.groupCompanyId = :groupCompanyName)"
		 * + " AND c.approvalStatus = 1 AND c.deleteFlg = 0");
		 */
		Query query = getEntityManager()
				.createQuery("SELECT c FROM CardInfo c WHERE c.groupCompanyId = :groupCompanyName"
						+ " AND c.approvalStatus = 1 AND c.deleteFlg = 0");

		query.setParameter("groupCompanyName", groupCompanyName);
		return query.getResultList();
	}

	public void saveDownloadHistory(DownloadCsv downloadCsvId) {
		/*
		 * Query query = getEntityManager().createNativeQuery(
		 * "INSERT INTO download_csv (user_id, csv_type, request_date, csv_approval_status, approval_date, csv_url) "
		 * +
		 * "VALUES(:userId, :csvType, :requestDate, :csvApprovalStatus, :approvalDate, :csvUrl)"
		 * ); query.setParameter("userId",
		 * downloadCsvId.getUserInfo().getUserId());
		 * query.setParameter("csvType", downloadCsvId.getCsvType());
		 * query.setParameter("requestDate", downloadCsvId.getRequestDate());
		 * query.setParameter("csvApprovalStatus",
		 * downloadCsvId.getCsvApprovalStatus());
		 * query.setParameter("approvalDate", downloadCsvId.getApprovalDate());
		 * query.setParameter("csvUrl", downloadCsvId.getCsvUrl()); return
		 * query.executeUpdate();
		 */
		getEntityManager().persist(downloadCsvId);
		getEntityManager().flush();
	}

	public void registerPrusalHistory(Integer userId, Integer cardId) {
		Query query = getEntityManager().createNativeQuery(
				"INSERT INTO prusal_history (user_id, card_id, prusal_date) " + "VALUES(:userId, :cardId, NOW())");
		query.setParameter("userId", userId);
		query.setParameter("cardId", cardId);
		query.executeUpdate();
	}

	public List<com.ecard.core.vo.CardInfo> getListCardSearchAll(String owner, String searchText, String name,
			String position, String department, String company, int pageNumber, int groupCompanyId) {

		String sqlStr = "SELECT c.* FROM card_info c WHERE c.old_card_flg = 0 AND c.approval_status = 1 AND c.delete_flg = 0 ";

		if (groupCompanyId == 1 || groupCompanyId == 2 || groupCompanyId == 3 || groupCompanyId == 4
				|| groupCompanyId == 5) {
			sqlStr += " AND (c.group_company_id IN(1,2,3,4,5) OR (c.group_company_id NOT IN(1,2,3,4,5) AND c.contact_date >= '"
					+ this.complianceDate + "')) ";
		} else {
			sqlStr += " AND (c.group_company_id = " + groupCompanyId + " OR (c.group_company_id <> " + groupCompanyId
					+ "  AND c.contact_date >= '" + this.complianceDate + "')) ";
		}

		if (owner != null) {
			String params[] = { "'*W1:0,2:0,3:0,4:0,5:0,6:0,7:0,8:0,9:0,10:0,11:0,12:0,13:0,14:1 ", owner.toLowerCase(),
					"*'" };

			sqlStr += "AND MATCH(c.company_name,c.company_name_kana,c.name,c.name_kana,c.department_name,c.position_name,c.email,c.zip_code,c.address_full,"
					+ "c.tel_number_company,c.tel_number_department,c.fax_number,c.mobile_number,c.card_owner_name) "
					+ "AGAINST(%s %s %s IN BOOLEAN MODE)";

			sqlStr = String.format(sqlStr, params);
		}

		if (position != null) {
			String params[] = { "'*W1:0,2:0,3:0,4:0,5:0,6:1,7:0,8:0,9:0,10:0,11:0,12:0,13:0,14:0 ",
					position.toLowerCase(), "*'" };

			sqlStr += "AND MATCH(c.company_name,c.company_name_kana,c.name,c.name_kana,c.department_name,c.position_name,c.email,c.zip_code,c.address_full,"
					+ "c.tel_number_company,c.tel_number_department,c.fax_number,c.mobile_number,c.card_owner_name) "
					+ "AGAINST(%s %s %s IN BOOLEAN MODE)";

			sqlStr = String.format(sqlStr, params);
		}
		if (name != null) {
			String params[] = { "'*W1:1,2:1,3:1,4:1,5:0,6:0,7:0,8:0,9:0,10:0,11:0,12:0,13:0,14:0 ", name.toLowerCase(),
					"*'" };

			sqlStr += "AND MATCH(c.company_name,c.company_name_kana,c.name,c.name_kana,c.department_name,c.position_name,c.email,c.zip_code,c.address_full,"
					+ "c.tel_number_company,c.tel_number_department,c.fax_number,c.mobile_number,c.card_owner_name) "
					+ "AGAINST(%s %s %s IN BOOLEAN MODE)";
			sqlStr = String.format(sqlStr, params);
		}
		if (department != null) {
			String params[] = { "'*W1:0,2:0,3:0,4:0,5:1,6:0,7:0,8:0,9:0,10:0,11:0,12:0,13:0,14:0 ",
					department.toLowerCase(), "*'" };

			sqlStr += "AND MATCH(c.company_name,c.company_name_kana,c.name,c.name_kana,c.department_name,c.position_name,c.email,c.zip_code,c.address_full,"
					+ "c.tel_number_company,c.tel_number_department,c.fax_number,c.mobile_number,c.card_owner_name) "
					+ "AGAINST(%s %s %s IN BOOLEAN MODE)";
			sqlStr = String.format(sqlStr, params);
		}
		if (company != null) {
			String params[] = { "'*W1:1,2:0,3:0,4:0,5:0,6:0,7:0,8:0,9:0,10:0,11:0,12:0,13:0,14:0 ",
					company.toLowerCase(), "*'" };

			sqlStr += "AND MATCH(c.company_name,c.company_name_kana,c.name,c.name_kana,c.department_name,c.position_name,c.email,c.zip_code,c.address_full,"
					+ "c.tel_number_company,c.tel_number_department,c.fax_number,c.mobile_number,c.card_owner_name) "
					+ "AGAINST(%s %s %s IN BOOLEAN MODE)";
			sqlStr = String.format(sqlStr, params);
		}
		if (name == null || position == null || department == null || company == null) {
			if (searchText != null) {
				List<String> listSearchText = new ArrayList<String>(Arrays.asList(searchText.trim().split(" ")));
				String searchTxt = "";
				for (int i = 0; i < listSearchText.size(); i++) {
					searchTxt += (listSearchText.get(i) != null) ? " +\"" + listSearchText.get(i) + "\" " : "";
				}
				// String params[] = { "'+{", searchText.toLowerCase(), "}'"};
				String params[] = { "'*W1:1,2:1,3:1,4:1,5:0,6:0,7:1,8:0,9:0,10:0,11:0,12:0,13:0,14:0 ",
						searchTxt.toLowerCase(), " '" };

				sqlStr += "AND MATCH(c.company_name,c.company_name_kana,c.name,c.name_kana,c.department_name,c.position_name,c.email,c.zip_code,c.address_full,"
						+ "c.tel_number_company,c.tel_number_department,c.fax_number,c.mobile_number,c.card_owner_name) "
						+ "AGAINST(%s %s %s IN BOOLEAN MODE)";
				sqlStr = String.format(sqlStr, params);
			}
		}

		Query query = getEntityManager().createNativeQuery(sqlStr);

		// query.setParameter("listUserId", listUserId);
		// query.setParameter("groupCompanyId", groupCompanyId);
		query.setFirstResult(pageNumber * this.maxResult);
		query.setMaxResults(this.maxResult);

		List<Object[]> rows = query.getResultList();
		List<com.ecard.core.vo.CardInfo> result = new ArrayList<>(rows.size());
		for (Object[] row : rows) {
			result.add(new com.ecard.core.vo.CardInfo((Integer) row[0], (String) row[9], (String) row[11],
					(String) row[10], (String) row[12], (String) row[14], (String) row[13], (String) row[5],
					(String) row[7], (String) row[2], (String) row[8], (Date) row[47], (Integer) row[45],
					(String) row[22], (String) row[15], (Integer) row[4], (Integer) row[53]));
		}

		return result;
	}

	public BigInteger getTotalCardSearchAll(String owner, String searchText, String name, String position,
			String department, String company, int pageNumber, int groupCompanyId) {

		String sqlStr = "SELECT COUNT(*) FROM card_info c WHERE c.old_card_flg = 0 AND c.approval_status = 1 AND c.delete_flg = 0 ";

		if (groupCompanyId == 1 || groupCompanyId == 2 || groupCompanyId == 3 || groupCompanyId == 4
				|| groupCompanyId == 5) {
			sqlStr += " AND (c.group_company_id IN(1,2,3,4,5) OR (c.group_company_id NOT IN(1,2,3,4,5) AND c.contact_date >= '"
					+ this.complianceDate + "')) ";
		} else {
			sqlStr += " AND (c.group_company_id = " + groupCompanyId + " OR (c.group_company_id <> " + groupCompanyId
					+ "  AND c.contact_date >= '" + this.complianceDate + "')) ";
		}

		if (owner != null) {
			String params[] = { "'*W1:0,2:0,3:0,4:0,5:0,6:0,7:0,8:0,9:0,10:0,11:0,12:0,13:0,14:1 ", owner.toLowerCase(),
					"*'" };

			sqlStr += "AND MATCH(c.company_name,c.company_name_kana,c.name,c.name_kana,c.department_name,c.position_name,c.email,c.zip_code,c.address_full,"
					+ "c.tel_number_company,c.tel_number_department,c.fax_number,c.mobile_number,c.card_owner_name) "
					+ "AGAINST(%s %s %s IN BOOLEAN MODE)";

			sqlStr = String.format(sqlStr, params);
		}

		if (position != null) {
			String params[] = { "'*W1:0,2:0,3:0,4:0,5:0,6:1,7:0,8:0,9:0,10:0,11:0,12:0,13:0,14:0 ",
					position.toLowerCase(), "*'" };

			sqlStr += "AND MATCH(c.company_name,c.company_name_kana,c.name,c.name_kana,c.department_name,c.position_name,c.email,c.zip_code,c.address_full,"
					+ "c.tel_number_company,c.tel_number_department,c.fax_number,c.mobile_number,c.card_owner_name) "
					+ "AGAINST(%s %s %s IN BOOLEAN MODE)";

			sqlStr = String.format(sqlStr, params);
		}
		if (name != null) {
			String params[] = { "'*W1:1,2:1,3:1,4:1,5:0,6:0,7:0,8:0,9:0,10:0,11:0,12:0,13:0,14:0 ", name.toLowerCase(),
					"*'" };

			sqlStr += "AND MATCH(c.company_name,c.company_name_kana,c.name,c.name_kana,c.department_name,c.position_name,c.email,c.zip_code,c.address_full,"
					+ "c.tel_number_company,c.tel_number_department,c.fax_number,c.mobile_number,c.card_owner_name) "
					+ "AGAINST(%s %s %s IN BOOLEAN MODE)";
			sqlStr = String.format(sqlStr, params);
		}
		if (department != null) {
			String params[] = { "'*W1:0,2:0,3:0,4:0,5:1,6:0,7:0,8:0,9:0,10:0,11:0,12:0,13:0,14:0 ",
					department.toLowerCase(), "*'" };

			sqlStr += "AND MATCH(c.company_name,c.company_name_kana,c.name,c.name_kana,c.department_name,c.position_name,c.email,c.zip_code,c.address_full,"
					+ "c.tel_number_company,c.tel_number_department,c.fax_number,c.mobile_number,c.card_owner_name) "
					+ "AGAINST(%s %s %s IN BOOLEAN MODE)";
			sqlStr = String.format(sqlStr, params);
		}
		if (company != null) {
			String params[] = { "'*W1:1,2:0,3:0,4:0,5:0,6:0,7:0,8:0,9:0,10:0,11:0,12:0,13:0,14:0 ",
					company.toLowerCase(), "*'" };

			sqlStr += "AND MATCH(c.company_name,c.company_name_kana,c.name,c.name_kana,c.department_name,c.position_name,c.email,c.zip_code,c.address_full,"
					+ "c.tel_number_company,c.tel_number_department,c.fax_number,c.mobile_number,c.card_owner_name) "
					+ "AGAINST(%s %s %s IN BOOLEAN MODE)";
			sqlStr = String.format(sqlStr, params);
		}
		if (name == null || position == null || department == null || company == null) {
			if (searchText != null) {
				List<String> listSearchText = new ArrayList<String>(Arrays.asList(searchText.trim().split(" ")));
				String searchTxt = "";
				for (int i = 0; i < listSearchText.size(); i++) {
					searchTxt += (listSearchText.get(i) != null) ? " +\"" + listSearchText.get(i) + "\" " : "";
				}

				// String params[] = { "'+{", searchText.toLowerCase(), "}'"};
				String params[] = { "'*W1:1,2:1,3:1,4:1,5:0,6:0,7:1,8:0,9:0,10:0,11:0,12:0,13:0,14:0 ",
						searchTxt.toLowerCase(), " '" };

				sqlStr += "AND MATCH(c.company_name,c.company_name_kana,c.name,c.name_kana,c.department_name,c.position_name,c.email,c.zip_code,c.address_full,"
						+ "c.tel_number_company,c.tel_number_department,c.fax_number,c.mobile_number,c.card_owner_name) "
						+ "AGAINST(%s %s %s IN BOOLEAN MODE)";
				sqlStr = String.format(sqlStr, params);
			}
		}

		Query query = getEntityManager().createNativeQuery(sqlStr);

		// query.setParameter("groupCompanyId", groupCompanyId);
		// query.setParameter("listUserId", listUserId);
		return (BigInteger) getOrNull(query);
	}

	@Override
	public List<CardInfoUserVo> getListPossesionCard(Integer userId, Integer sortType, String valueSearch) {
		String sqlStr = "";
		 
		if (sortType == SearchConditions.CONTACT.getValue()) {
			sqlStr = "SELECT DATE_FORMAT(c.contactDate,'%Y/%m') AS groupDate, c FROM CardInfo c WHERE c.cardOwnerId = :userId AND c.approvalStatus = 1 AND c.deleteFlg = 0 "
					+ " AND (c.contactDate is not null AND c.contactDate <> '') "
					+ "AND date_format(date(c.contactDate),'%Y/%m') = :valueSearch";
			
		} else if (sortType == SearchConditions.NAME.getValue()) {
			sqlStr = "SELECT c.nameKana AS groupDate, c FROM CardInfo c WHERE c.cardOwnerId = :userId AND c.approvalStatus = 1 AND c.deleteFlg = 0 "
					+" AND (c.nameKana is not null AND c.nameKana <> '') "
					+ "AND c.nameKana LIKE :valueSearch";
			
		} else if (sortType == SearchConditions.COMPANY.getValue()) {			
			sqlStr = "SELECT c.companyNameKana AS groupDate, c FROM CardInfo c WHERE c.cardOwnerId = :userId AND c.approvalStatus = 1 AND c.deleteFlg = 0 "
					+ " AND (c.companyNameKana is not null AND c.companyNameKana <> '') "
					+ "AND c.companyNameKana = :valueSearch";
			
		} else if (sortType == SearchConditions.TAG.getValue()) {
			if (valueSearch.equals("cardNoTag")) {
				sqlStr = "SELECT 'cardNoTag' AS groupDate, c FROM CardInfo c"
						+" WHERE c.cardId NOT IN ( SELECT ct.id.cardId FROM CardTag ct INNER JOIN ct.userTag ut"
						+ " 						WHERE ut.userInfo.userId = :userId GROUP BY ct.id.cardId)  "
						+ "AND c.cardOwnerId = :userId AND c.approvalStatus = 1 AND c.deleteFlg = 0";
			} else {
				sqlStr = "SELECT ut.tagName AS groupDate, c FROM CardTag ct INNER JOIN ct.userTag ut INNER JOIN ct.cardInfo c"
						+" WHERE ut.userInfo.userId = :userId AND c.cardOwnerId = :userId AND c.approvalStatus = 1 AND c.deleteFlg = 0"
						+" AND (ut.tagName is not null AND ut.tagName <> '') "
						+" AND ut.tagName = :valueSearch";
			}
			
		} else {
			sqlStr = "SELECT DATE_FORMAT(c.contactDate,'%Y/%m') AS groupDate, c FROM CardInfo c WHERE c.cardOwnerId = :userId AND c.approvalStatus = 1 AND c.deleteFlg = 0 "
					+ " AND (c.contactDate is not null AND c.contactDate <> '') "
					+ " AND date_format(date(c.contactDate),'%Y/%m') = :valueSearch";
		}
		
		Query query = getEntityManager().createQuery(sqlStr);
		query.setParameter("userId", userId);
		
		if (sortType == SearchConditions.NAME.getValue()) {
			if(valueSearch != "" || valueSearch != null)
				query.setParameter("valueSearch", valueSearch.substring(0, 1).toLowerCase() + "%");
			else 
				query.setParameter("valueSearch", valueSearch + "%");
		} else {
			if(!valueSearch.equals("cardNoTag")){
			  query.setParameter("valueSearch", valueSearch);
			}
		}
		
		
		/*String sqlStr = "SELECT DATE_FORMAT(c.contactDate,'%m/%Y') AS groupDate, c FROM CardInfo c "
				+ "WHERE c.cardOwnerId = :userId AND c.approvalStatus = 1 AND c.deleteFlg = 0 "
				+ "AND date_format(date(c.contactDate),'%m/%Y') = :strDate";
		Query query = getEntityManager().createQuery(sqlStr);
		query.setParameter("userId", userId);		
		query.setParameter("strDate", valueSearch);*/
		List<Object[]> listObj = query.getResultList();
		List<CardInfoUserVo> lstcardInfoUserVo = new ArrayList<>();
		for (Object[] object : listObj) {
			CardInfoUserVo cardInfoVo = new CardInfoUserVo((String) object[0], (CardInfo) object[1]);
			lstcardInfoUserVo.add(cardInfoVo);
		}
		return lstcardInfoUserVo;
	}

	public void updateOldCardInfo(CardInfo cardInfo) {
		Query query = getEntityManager().createQuery(
				"UPDATE CardInfo ci SET ci.newestCardFlg = (CASE WHEN ci.cardId = :cardId  THEN 1 ELSE 0 END) "
						+ " WHERE ci.email = :email AND ci.companyName = :companyName AND ci.positionName = :positionName "
						+ " AND ci.departmentName = :departmentName AND ci.mobileNumber = :mobileNumber "
						+ " AND ci.addressFull = :addressFull");
		query.setParameter("email", cardInfo.getEmail());
		query.setParameter("companyName", cardInfo.getCompanyName());
		query.setParameter("positionName", cardInfo.getPositionName());
		query.setParameter("departmentName", cardInfo.getDepartmentName());
		query.setParameter("mobileNumber", cardInfo.getMobileNumber());
		query.setParameter("addressFull", cardInfo.getAddressFull());
		query.setParameter("cardId", cardInfo.getCardId());
		query.executeUpdate();

	}

	public CardInfo importCardInfoFromCsv(CardInfo cardInfo) {
		getEntityManager().persist(cardInfo);
		getEntityManager().flush();

		return cardInfo;
	}

	public int getCardIdByCardIndexNo(String cardIndexNo) {
		Query query = getEntityManager()
				.createQuery("SELECT c.cardId FROM CardInfo c WHERE c.cardIndexNo = :cardIndexNo");
		query.setParameter("cardIndexNo", cardIndexNo);
		return query.getFirstResult();
	}

	public List<CardInfo> getListCardInfoByUserId(Integer userId) {
		Query query = getEntityManager().createQuery("SELECT c FROM CardInfo c WHERE c.cardOwnerId = :userId");
		query.setParameter("userId", userId);
		return query.getResultList();
	}

	public List<Integer> getListUserPushToByCard(CardInfo cardInfo) {
		String sqlStr = "SELECT DISTINCT ci.card_owner_id" + " FROM card_info ci "
				+ "	WHERE ((ci.email = :email AND ci.email <> '') OR (ci.name = :name AND ci.company_name = :companyName))"
				+ " AND ci.old_card_flg = 0  AND ci.approval_status = 1 AND ci.delete_flg = 0 AND ci.card_owner_id <> :cardOwnerId ";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date comDate = new Date();
		try {
			comDate = formatter.parse(this.complianceDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (cardInfo.getContactDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
				.isBefore(comDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())) {
			if (cardInfo.getGroupCompanyId() == 1 || cardInfo.getGroupCompanyId() == 2
					|| cardInfo.getGroupCompanyId() == 3 || cardInfo.getGroupCompanyId() == 4
					|| cardInfo.getGroupCompanyId() == 5) {
				sqlStr += "AND ( ci.group_company_id IN (1,2,3,4,5) OR ( ci.group_company_id NOT IN(1,2,3,4,5) AND ci.contact_date >= '"
						+ this.complianceDate + "' ))";
			} else {
				sqlStr += "AND ( ci.group_company_id = " + cardInfo.getGroupCompanyId() + ""
						+ " OR ( ci.group_company_id <> " + cardInfo.getGroupCompanyId() + " AND ci.contact_date >= '"
						+ this.complianceDate + "' ))";
			}
		}

		Query query = getEntityManager().createNativeQuery(sqlStr);
		query.setParameter("name", cardInfo.getName());
		query.setParameter("email", cardInfo.getEmail());
		query.setParameter("companyName", cardInfo.getCompanyName());
		query.setParameter("cardOwnerId", cardInfo.getCardOwnerId());

		return query.getResultList();
	}

	public List<Integer> getListUserPushFromByCard(CardInfo cardInfo) {
		String sqlStr = "SELECT DISTINCT ci.card_owner_id" + " FROM card_info ci "
				+ "	WHERE ((ci.email = :email AND ci.email <> '') OR (ci.name = :name AND ci.company_name = :companyName))"
				+ " AND ci.old_card_flg = 0  AND ci.approval_status = 1 AND ci.delete_flg = 0 AND ci.card_owner_id <> :cardOwnerId ";
		if (cardInfo.getGroupCompanyId() == 1 || cardInfo.getGroupCompanyId() == 2 || cardInfo.getGroupCompanyId() == 3
				|| cardInfo.getGroupCompanyId() == 4 || cardInfo.getGroupCompanyId() == 5) {
			sqlStr += "AND ( ci.group_company_id IN (1,2,3,4,5)  OR ( ci.group_company_id NOT IN(1,2,3,4,5) AND ci.contact_date >= '"
					+ this.complianceDate + "' ))";
		} else {
			sqlStr += "AND ( ci.group_company_id = " + cardInfo.getGroupCompanyId() + " OR ( ci.group_company_id <> "
					+ cardInfo.getGroupCompanyId() + " AND ci.contact_date >= '" + this.complianceDate + "' ))";
		}

		Query query = getEntityManager().createNativeQuery(sqlStr);
		query.setParameter("name", cardInfo.getName());
		query.setParameter("email", cardInfo.getEmail());
		query.setParameter("companyName", cardInfo.getCompanyName());
		query.setParameter("cardOwnerId", cardInfo.getCardOwnerId());

		return query.getResultList();
	}

	public int updateCardDeleted(Integer cardId) {
		Query query = getEntityManager()
				.createQuery("UPDATE CardInfo c SET c.deleteFlg = 1, c.deletDate = NOW() WHERE c.cardId = :cardId");
		query.setParameter("cardId", cardId);
		return (int) query.executeUpdate();
	}

	public List<com.ecard.core.vo.CardInfo> searchCardUser(String criteriaSearch, int status, List<Integer> listStatus,
			int limit, int offet, Integer userId) {
		String sqlQuery = "SELECT u.* FROM card_info AS u " + "INNER JOIN admin_possession_card AS ps "
				+ " ON u.card_id = ps.card_id WHERE ";
		if (!criteriaSearch.isEmpty())
			sqlQuery += "(u.name REGEXP  :criteriaSearch " + "OR u.last_name REGEXP :criteriaSearch "
					+ "OR u.first_name REGEXP :criteriaSearch " + "OR u.last_name_kana REGEXP :criteriaSearch "
					+ "OR u.department_name REGEXP :criteriaSearch " + "OR u.tel_number_company REGEXP :criteriaSearch "
					+ "OR u.email REGEXP :criteriaSearch " + "OR u.position_name REGEXP :criteriaSearch "
					+ "OR u.address_full REGEXP :criteriaSearch " + "OR u.first_name_kana REGEXP :criteriaSearch "
					+ "OR u.company_name REGEXP :criteriaSearch " + "OR u.company_name_kana REGEXP :criteriaSearch "
					+ "OR u.mobile_number REGEXP :criteriaSearch) AND ";
		if (status > 0)
			sqlQuery += "u.approval_status = :status AND ";
		sqlQuery += "u.delete_flg = 0 AND u.approval_status IN (:listStatus) AND ps.user_id = :userId ORDER BY u.create_date DESC";
		Query query = null;
		if (limit > -1 && offet > -1) {
			query = getEntityManager().createNativeQuery(sqlQuery).setFirstResult(offet).setMaxResults(limit);
		} else {
			query = getEntityManager().createNativeQuery(sqlQuery);
		}
		if (!criteriaSearch.isEmpty()) {
			query.setParameter("criteriaSearch", criteriaSearch);
		}
		if (status > 0) {
			query.setParameter("status", status);
		}
		query.setParameter("listStatus", listStatus);
		query.setParameter("userId", userId);
		List<Object[]> rows = query.getResultList();
		List<com.ecard.core.vo.CardInfo> result = new ArrayList<>(rows.size());

		for (Object[] row : rows) {
			result.add(new com.ecard.core.vo.CardInfo((Integer) row[0], (String) row[9], (String) row[10],
					(String) row[11], (String) row[12], (String) row[13], (String) row[14], (String) row[5],
					(String) row[7], (String) row[8], (String) row[2], (Date) row[47], (Integer) row[4],
					(Integer) row[1], (String) row[6], (String) row[15], (String) row[16], (String) row[17],
					(String) row[18], (String) row[19], (String) row[20], (String) row[22], (String) row[23],
					(String) row[24], (String) row[25], (String) row[26], (String) row[27], (String) row[28],
					(Integer) row[43], (Integer) row[44], (Integer) row[45], (Date) row[48], (Integer) row[49],
					(Date) row[50], (Integer) row[51], (String) row[56], (int) row[61]));
		}

		return result;

	}

	public BigInteger countCardInfoUser(String criteriaSearch, int status, List<Integer> listStatus, Integer userId) {
		String sqlQuery = "SELECT COUNT(*) FROM card_info AS u "
				+ "INNER JOIN admin_possession_card AS ps ON u.card_id = ps.card_id WHERE ";
		if (!criteriaSearch.isEmpty())
			sqlQuery += "(u.name REGEXP  :criteriaSearch " + "OR u.last_name REGEXP :criteriaSearch "
					+ "OR u.first_name REGEXP :criteriaSearch " + "OR u.last_name_kana REGEXP :criteriaSearch "
					+ "OR u.department_name REGEXP :criteriaSearch " + "OR u.tel_number_company REGEXP :criteriaSearch "
					+ "OR u.email REGEXP :criteriaSearch " + "OR u.position_name REGEXP :criteriaSearch "
					+ "OR u.address_full REGEXP :criteriaSearch " + "OR u.first_name_kana REGEXP :criteriaSearch "
					+ "OR u.company_name REGEXP :criteriaSearch " + "OR u.company_name_kana REGEXP :criteriaSearch "
					+ "OR u.mobile_number REGEXP :criteriaSearch) AND ";
		if (status > 0)
			sqlQuery += "u.approval_status = :status AND ";
		sqlQuery += "u.delete_flg = 0 AND u.approval_status IN (:listStatus) AND ps.user_id = :userId ORDER BY u.create_date DESC";
		Query query = getEntityManager().createNativeQuery(sqlQuery);
		if (!criteriaSearch.isEmpty()) {
			query.setParameter("criteriaSearch", criteriaSearch);
		}
		if (status > 0) {
			query.setParameter("status", status);
		}
		query.setParameter("listStatus", listStatus);
		query.setParameter("userId", userId);
		return (BigInteger) query.getSingleResult();
	}

	public void updateDateEditting(List<com.ecard.core.vo.CardInfo> cards) {
		if (cards != null && cards.size() > 0) {
			List<CardInfo> edittingCards = this.listAllCardInfo().stream().filter(
					c -> cards.stream().map(cv -> cv.getCardId()).collect(Collectors.toList()).contains(c.getCardId()))
					.collect(Collectors.toList());
			LocalDate currentDate = LocalDate.now();
			edittingCards = edittingCards.stream()
					.filter(x -> x.getDateEditting() != null
							&& currentDate.isAfter(
									x.getDateEditting().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
					&& x.getIsEditting() == 1).collect(Collectors.toList());
			if (edittingCards != null && edittingCards.size() > 0) {
				updateListCardInfor(edittingCards);
			}

		}
	}

	@Transactional
	public void updateListCardInfor(List<CardInfo> cards) {
		cards.forEach(c -> {
			c.setIsEditting(0);
			c.setDateEditting(new Date());
			this.saveOrUpdate(c);
		});
		this.getEntityManager().flush();
		this.getEntityManager().clear();
	}

	public int updateCardType() {
		Query query = getEntityManager().createQuery("UPDATE CardInfo c SET c.cardType = 0 WHERE c.cardType = 1");
		return (int) query.executeUpdate();
	}

	public List<CardInfo> listCardInfoByCardType(Integer cardType) {
		Query query = getEntityManager().createQuery("SELECT c FROM CardInfo c WHERE c.cardType = :cardType");
		query.setParameter("cardType", cardType);
		return (List<CardInfo>) query.getResultList();
	}

	@Override
	public List<String> getListSortType(Integer userId, Integer sortType) {
		String sqlStr = "";
		 
		if (sortType == SearchConditions.CONTACT.getValue()) {
			sqlStr = "SELECT DATE_FORMAT(c.contactDate,'%Y/%m') AS groupDate FROM CardInfo c "
					+ " WHERE c.cardOwnerId = :userId AND c.approvalStatus = 1 AND c.deleteFlg = 0 "
					+ " AND (c.contactDate is not null AND c.contactDate <> '') "
					+ " GROUP BY DATE_FORMAT(c.contactDate,'%Y/%m') ORDER BY c.contactDate DESC ";
			
		} else if (sortType == SearchConditions.NAME.getValue()) {
			sqlStr = "SELECT c.nameKana  AS groupDate FROM CardInfo c "
					+ "WHERE c.cardOwnerId = :userId AND c.approvalStatus = 1 AND c.deleteFlg = 0 "
					+ " AND (c.nameKana is not null AND c.nameKana <> '') "
					+ "GROUP BY SUBSTR((c.nameKana),1,1) ORDER BY c.nameKana ASC ";
			
		} else if (sortType == SearchConditions.COMPANY.getValue()) {
			sqlStr = "SELECT c.companyNameKana AS groupDate FROM CardInfo c "
					+ "WHERE c.cardOwnerId = :userId AND c.approvalStatus = 1 AND c.deleteFlg = 0 "
					+ " AND (c.companyNameKana is not null AND c.companyNameKana <> '') "
					+ "GROUP BY c.companyNameKana ORDER BY c.companyNameKana ASC ";
			
		} else if (sortType == SearchConditions.TAG.getValue()) {
			sqlStr = "SELECT ut.tagName AS groupDate FROM CardTag ct INNER JOIN ct.userTag ut INNER JOIN ct.cardInfo c"
					+" WHERE ut.userInfo.userId = :userId AND c.cardOwnerId = :userId  AND c.deleteFlg = 0 AND c.approvalStatus = 1"
					+" AND (ut.tagName is not null AND ut.tagName <> '') "
					+" GROUP BY ut.tagName ORDER BY ut.tagName ASC" ;
					
			
		} else {
			sqlStr = "SELECT DATE_FORMAT(c.contactDate,'%Y/%m') AS groupDate FROM CardInfo c "
					+ "WHERE c.cardOwnerId = :userId AND c.approvalStatus = 1 AND c.deleteFlg = 0 "
					+ " AND (c.contactDate is not null AND c.contactDate <> '') "
					+ "GROUP BY DATE_FORMAT(c.contactDate,'%Y/%m') ORDER BY c.contactDate DESC ";
		}
		
		Query query = getEntityManager().createQuery(sqlStr);
		query.setParameter("userId", userId);
		return query.getResultList();

	}

	public int updateContactDate(CardInfo cardInfo) {
		Query query = getEntityManager()
				.createQuery("UPDATE CardInfo c SET c.contactDate = :contactDate WHERE c.cardId = :cardId");
		query.setParameter("contactDate", cardInfo.getContactDate());
		query.setParameter("cardId", cardInfo.getCardId());

		return (int) query.executeUpdate();
	}

	public Long countPossessionCard(Integer userId) {
		String sqlStr = "SELECT count(*) FROM CardInfo c "
				+ "WHERE c.cardOwnerId = :userId AND c.approvalStatus = 1 AND c.deleteFlg = 0";
		Query query = getEntityManager().createQuery(sqlStr);
		query.setParameter("userId", userId);
		return (Long) getOrNull(query);

	}

	public CardInfo getNewestCardInfo(CardInfo cardInfo) {
		Query query = getEntityManager().createQuery("SELECT c FROM CardInfo c "
				+ " WHERE c.email = :email AND c.companyName = :companyName AND c.positionName = :positionName AND c.departmentName = :departmentName"
				+ " AND c.mobileNumber = :mobileNumber AND c.addressFull = :addressFull"
				+ " ORDER BY c.contactDate DESC, c.createDate DESC");
		query.setParameter("email", cardInfo.getEmail());
		query.setParameter("companyName", cardInfo.getCompanyName());
		query.setParameter("positionName", cardInfo.getPositionName());
		query.setParameter("departmentName", cardInfo.getDepartmentName());
		query.setParameter("mobileNumber", cardInfo.getMobileNumber());
		query.setParameter("addressFull", cardInfo.getAddressFull());
		query.setMaxResults(1);
		return (CardInfo) query.getSingleResult();
	}

	public List<CardInfo> getOldCardInfor() {
		Query query = getEntityManager()
				.createQuery("SELECT c FROM OldCard oc INNER JOIN oc.cardInfo c ORDER BY oc.seq DESC");
		return (List<CardInfo>) query.getResultList();
	}

	public int deleteListCard(List<Integer> listCard) {
		Query query = getEntityManager().createNativeQuery("UPDATE card_info ci "
				+ " SET ci.delete_flg = 1, ci.delet_date = NOW() " + " WHERE ci.card_id in (:listCard)");
		query.setParameter("listCard", listCard);
		return query.executeUpdate();
	}

	public List<CardInfo> getListPossessionCardByTag(Integer userId, Integer tagId, String sort, int pageNumber) {
		String sqlStr = "SELECT c FROM  CardTag ct " + " INNER JOIN ct.userTag ut" + " INNER JOIN  ct.cardInfo c"
				+ " WHERE ct.id.tagId = :tagId AND ut.userInfo.userId = :userId AND c.cardOwnerId = :userId"
				+ " AND c.deleteFlg = 0 AND c.approvalStatus = 1";
		if (sort.equals(StringUtils.lowerCase(SearchConditions.NAME.name()))) {
			sqlStr += " ORDER BY c.nameKana ASC ";
		} else if (sort.equals(StringUtils.lowerCase(SearchConditions.COMPANY.name()))) {
			sqlStr += " ORDER BY c.companyNameKana ASC ";
		} else if (sort.equals(StringUtils.lowerCase(SearchConditions.CONTACT.name()))) {
			sqlStr += " ORDER BY c.contactDate DESC ";
		}
		Query query = getEntityManager().createQuery(sqlStr);
		query.setParameter("userId", userId);
		query.setParameter("tagId", tagId);
		query.setFirstResult(pageNumber * this.maxResult).setMaxResults(this.maxResult);
		return query.getResultList();
	}

	public List<CardInfoUserVo> getListPossessionCardByTag(Integer userId, Integer tagId, int pageNumber) {
		/*
		 * String sqlStr =
		 * "SELECT DATE_FORMAT(c.contactDate,'%m/%Y') AS groupDate, c FROM CardInfo c "
		 * +
		 * "WHERE c.cardOwnerId = :userId AND c.approvalStatus = 1 AND c.deleteFlg = 0 ORDER BY c.contactDate DESC "
		 * ;
		 */
		String sqlStr = "SELECT DATE_FORMAT(c.contactDate,'%m/%Y') AS groupDate, c FROM  CardTag ct "
				+ " INNER JOIN ct.userTag ut" + " INNER JOIN  ct.cardInfo c"
				+ " WHERE ct.id.tagId = :tagId AND ut.userInfo.userId = :userId AND c.cardOwnerId = :userId"
				+ " AND c.deleteFlg = 0 AND c.approvalStatus = 1 ORDER BY c.contactDate DESC";
		Query query = getEntityManager().createQuery(sqlStr);
		query.setParameter("userId", userId);
		query.setParameter("tagId", tagId);
		query.setFirstResult(pageNumber * this.maxResult).setMaxResults(this.maxResult);
		List<Object[]> listObj = query.getResultList();
		List<CardInfoUserVo> lstcardInfoUserVo = new ArrayList<>();
		for (Object[] object : listObj) {
			CardInfoUserVo cardInfoVo = new CardInfoUserVo((String) object[0], (CardInfo) object[1]);
			lstcardInfoUserVo.add(cardInfoVo);
		}

		return lstcardInfoUserVo;
	}

	public List<String> getListSortTypeByTag(Integer userId, Integer tagId) {
		String sqlStr = "SELECT DATE_FORMAT(c.contactDate,'%m/%Y') AS groupDate FROM  CardTag ct "
				+ " INNER JOIN ct.userTag ut" + " INNER JOIN  ct.cardInfo c"
				+ " WHERE ct.id.tagId = :tagId AND ut.userInfo.userId = :userId AND c.cardOwnerId = :userId"
				+ " AND c.deleteFlg = 0 AND c.approvalStatus = 1 GROUP BY DATE_FORMAT(c.contactDate,'%m/%Y') ORDER BY c.contactDate DESC";
		Query query = getEntityManager().createQuery(sqlStr);
		query.setParameter("userId", userId);
		query.setParameter("tagId", tagId);
		query.setFirstResult(0 * this.maxResult).setMaxResults(this.maxResult);
		return query.getResultList();
	}

	public void updateDownloadHistory(Integer downloadCsvId) {
		Query query = getEntityManager().createQuery(
				"UPDATE DownloadCsv c SET c.csvApprovalStatus = 1 , c.approvalDate = NOW() WHERE c.csvId = :csvId");
		query.setParameter("csvId", downloadCsvId);
		query.executeUpdate();
	}

	public DownloadCsv getDownloadCSV(Integer csvId) {
		Query query = getEntityManager().createQuery("SELECT c FROM DownloadCsv c WHERE c.csvId = :csvId");
		query.setParameter("csvId", csvId);
		return (DownloadCsv) getOrNull(query);
	}

	@Override
	public List<com.ecard.core.vo.CardInfo> getListCardAllocationUser(int userId, int tagId,int limit,int offset) {

		String sqlStr = "SELECT c.card_id,name,first_name,last_name,name_kana,first_name_kana,last_name_kana,  company_name,company_name_kana,department_name,image_file, "
				+ " position_name,  c.create_date,approval_status,tel_number_company,email ,ut.tag_name "
				+ " FROM card_info c LEFT JOIN card_tag ct ON c.card_id = ct.card_id "
				+ " LEFT JOIN user_tag ut ON ct.tag_id = ut.tag_id "
				+ " WHERE c.card_owner_id = :userId  and  c.delete_flg = 0 ";

		if (tagId > 0) {
			sqlStr += " AND ut.tag_id = :tagId ";
		}
		sqlStr += "  GROUP BY c.card_id ";

		Query query = null;
		if (limit > -1 && offset > -1) {
			query = getEntityManager().createNativeQuery(sqlStr).setFirstResult(offset).setMaxResults(limit);
		}else{
			query = getEntityManager().createNativeQuery(sqlStr);
		}
		query.setParameter("userId", userId);
		if (tagId > 0) {
			query.setParameter("tagId", tagId);
		}
		
		List<Object[]> lstObj = query.getResultList();
		List<com.ecard.core.vo.CardInfo> lstCardInfoVo = new ArrayList<>();
		for (Object[] obj : lstObj) {
			com.ecard.core.vo.CardInfo cardInfoVo = new com.ecard.core.vo.CardInfo((Integer) obj[0], (String) obj[1],
					(String) obj[2], (String) obj[3], (String) obj[4], (String) obj[5], (String) obj[6],
					(String) obj[7], (String) obj[8], (String) obj[9], (String) obj[10], (String) obj[11],
					(Date) obj[12], (Integer) obj[13], (String) obj[14], (String) obj[15], (String) obj[16]);
			lstCardInfoVo.add(cardInfoVo);
		}

		return lstCardInfoVo;
	}

	public List<com.ecard.core.vo.CardInfo> searchCompanyTree(String companyName) {
		String sqlStr = "SELECT c.company_name AS companyName, count(*) AS cnt, c.card_id AS cardId FROM card_info AS c "
				+ "WHERE c.approval_status = 1 AND c.old_card_flg = 0 AND c.delete_flg = 0 AND c.newest_card_flg = 1 ";

		String params[] = { "'*W1:1,2:1,3:0,4:0,5:0,6:0,7:1,8:0,9:0,10:0,11:0,12:0,13:0,14:0 +",
				"\"" + companyName.toLowerCase() + "\"", "'" };

		sqlStr += "AND MATCH(c.company_name,c.company_name_kana,c.name,c.name_kana,c.department_name,c.position_name,c.email,c.zip_code,c.address_full,c.tel_number_company,c.tel_number_department,c.fax_number,c.mobile_number,c.card_owner_name) "
				+ "AGAINST(%s %s %s IN BOOLEAN MODE) " + "GROUP BY c.company_name";
		sqlStr = String.format(sqlStr, params);

		Query query = getEntityManager().createNativeQuery(sqlStr);

		List<Object[]> listObj = query.getResultList();
		List<com.ecard.core.vo.CardInfo> cardInfoList = new ArrayList<>();
		for (Object[] object : listObj) {
			com.ecard.core.vo.CardInfo cardInfoVo = new com.ecard.core.vo.CardInfo((String) object[0],
					(BigInteger) object[1], (Integer) object[2]);
			cardInfoList.add(cardInfoVo);
		}

		return cardInfoList;
	}

	public List<com.ecard.core.vo.CardInfo> searchDepartment(String companyName) {
		String sqlStr = "SELECT c.department_name AS departmentName, count(*) AS cnt, c.card_id AS cardId FROM card_info AS c "
				+ "WHERE c.approval_status = 1 AND c.old_card_flg = 0 AND c.delete_flg = 0 AND c.newest_card_flg = 1 AND c.company_name = :companyName GROUP BY department_name";
		Query query = getEntityManager().createNativeQuery(sqlStr);
		query.setParameter("companyName", companyName);

		List<Object[]> listObj = query.getResultList();
		List<com.ecard.core.vo.CardInfo> cardInfoList = new ArrayList<>();
		for (Object[] object : listObj) {
			com.ecard.core.vo.CardInfo cardInfoVo = new com.ecard.core.vo.CardInfo((String) object[0],
					(BigInteger) object[1], (Integer) object[2]);
			cardInfoList.add(cardInfoVo);
		}

		return cardInfoList;
	}

	public List<com.ecard.core.vo.CardInfo> searchCardInfo(String companyName, String departmentName) {
		String sqlStr = "SELECT c.name AS name, count(*) AS cnt, c.card_id AS cardId FROM card_info AS c WHERE c.approval_status = 1 AND c.old_card_flg = 0 AND c.delete_flg = 0 AND c.newest_card_flg = 1 "
				+ "AND c.company_name = :companyName AND c.department_name = :departmentName";
		Query query = getEntityManager().createNativeQuery(sqlStr);
		query.setParameter("companyName", companyName);
		query.setParameter("departmentName", departmentName);

		List<Object[]> listObj = query.getResultList();
		List<com.ecard.core.vo.CardInfo> cardInfoList = new ArrayList<>();
		for (Object[] object : listObj) {
			com.ecard.core.vo.CardInfo cardInfoVo = new com.ecard.core.vo.CardInfo((String) object[0],
					(BigInteger) object[1], (Integer) object[2]);
			cardInfoList.add(cardInfoVo);
		}

		return cardInfoList;
	}
	
	public List<com.ecard.core.vo.CardInfo> getListConnectCards(com.ecard.core.vo.CardInfo card) {
        String sqlStr = "SELECT * FROM card_info"+
		        		" WHERE"+
		        		" old_card_flg = 0"+
		        		" AND delete_flg = 0"+
		        		" AND approval_status = 1"+
		        		" AND newest_card_flg = 1"+
		        		" AND ("+
		        		"         (address_full <> '"+card.getAddressFull()+"')"+
		        		"      OR (tel_number_company <> '"+card.getTelNumberCompany()+"')"+
		        		"      OR (department_name <> '"+card.getDepartmentName()+"')"+
		        		"      OR (position_name <> '"+card.getPositionName()+"')"+
		        		")";
        if (card.getGroupCompanyId() == 1 || card.getGroupCompanyId() ==2 || card.getGroupCompanyId() ==3 || card.getGroupCompanyId() == 4 || card.getGroupCompanyId() == 5 ){
            sqlStr += "AND ((email = '"+card.getEmail()+"' AND email <> '') OR (name = '"+card.getFirstName()+"' AND company_name =  '"+card.getCompanyName()+"')) " 
                    + "AND (group_company_id IN(1,2,3,4,5) OR (group_company_id NOT IN(1,2,3,4,5) AND contact_date >= '"+ this.complianceDate +"')) " ;
        }
        else{
            sqlStr += "AND ((email = '"+card.getEmail()+"' AND email <> '') OR (name = '"+card.getFirstName()+"' AND company_name =  '"+card.getCompanyName()+"')) " 
                    + "AND (group_company_id = "+ card.getGroupCompanyId() +" OR (group_company_id <> "+ card.getGroupCompanyId() +" AND contact_date >= '"+ this.complianceDate +"')) ";
        }
        Query query = getEntityManager().createNativeQuery(sqlStr);
       
        List<Object[]> rows = query.getResultList();
        List<com.ecard.core.vo.CardInfo> result = new ArrayList<>(rows.size());
        for (Object[] row : rows) {
            result.add(new com.ecard.core.vo.CardInfo((Integer)row[0], (String)row[9], (String)row[11], (String)row[10], (String)row[12], (String)row[14],
                    (String)row[13], (String)row[5], (String)row[7], (String)row[2],(String)row[8], (Date)row[47], (Integer)row[45], (String)row[22], (String)row[15], (Integer)row[4], (Integer)row[53], (String)row[17],(Date)row[55], (Integer)row[43]));
        }
        
        return result;
    }
@Override
	public BigInteger countListCardAllocationUser(int userId, int tagId) {
		String sqlStr = "SELECT count(DISTINCT c.card_id) " 
	            + " FROM card_info c LEFT JOIN card_tag ct ON c.card_id = ct.card_id "
				+ " LEFT JOIN user_tag ut ON ct.tag_id = ut.tag_id "
				+ " WHERE c.card_owner_id = :userId  and  c.delete_flg = 0 ";

		if (tagId > 0) {
			sqlStr += " AND ut.tag_id = :tagId ";
		}
		Query query = getEntityManager().createNativeQuery(sqlStr);
		query.setParameter("userId", userId);
		if (tagId > 0) {
			query.setParameter("tagId", tagId);
		}

		return (BigInteger) query.getSingleResult();
		
	}

	@Override
	public List<TagUser> getAllTagUser(int userId) {
		String sql = " select ct.card_id,ut.tag_name "
					+ " from  card_tag ct inner join user_tag ut on ct.tag_id = ut.tag_id "
					+ " where ut.user_id = :userId ";
		Query query = getEntityManager().createNativeQuery(sql);
		query.setParameter("userId", userId);
		List<Object[]> lstCardTag = query.getResultList();
		List<TagUser> lstTag = new ArrayList<>();
		for (Object[] obj : lstCardTag) {
			TagUser tag = new TagUser((Integer)obj[0],(String) obj[1]);
			lstTag.add(tag);
		}		
		return lstTag;
	}

	@Override
	public Integer updateUserCard(List<Integer> listCardUser, Integer userLeave, Integer userAssign,
			String nameAssign) {
		String sql = "UPDATE card_info c SET  c.card_owner_id = :userAssign,c.card_owner_name= :nameAssign  WHERE  c.card_id IN (:listCardUser)";
		Query query = getEntityManager().createNativeQuery(sql);
		query.setParameter("userAssign", userAssign);
		query.setParameter("nameAssign", nameAssign);
		query.setParameter("listCardUser", listCardUser);
		return query.executeUpdate();
	}
	
	public List<CardInfo> searchCardInfoByName(String companyName, String departmentName){
		String sqlStr = "SELECT c FROM CardInfo c WHERE c.approvalStatus = 1 AND c.oldCardFlg = 0 AND c.deleteFlg = 0 AND c.newestCardFlg = 1 "
				+ "AND c.companyName = :companyName AND c.departmentName = :departmentName";
		Query query = getEntityManager().createQuery(sqlStr);
		query.setParameter("companyName", companyName);
		query.setParameter("departmentName", departmentName);
		return (List<CardInfo>)query.getResultList();
	}
	
	public void savePrusalHistory(PrusalHistory prusalHistory){
		getEntityManager().persist(prusalHistory);
		getEntityManager().flush();
	}
	
	public List<com.ecard.core.vo.CardInfo> getListCardHistoryByCardId(Integer cardId){
		String sqlStr = "SELECT C.contact_date, C.company_name, C.department_name FROM card_info AS C "
				+ "INNER JOIN old_card AS O "
				+ "ON O.old_card_id = C.card_id AND O.card_id = " + cardId
				+ " WHERE approval_status = 1 AND delete_flg = 0 ORDER BY contact_date DESC";
		
		Query query = getEntityManager().createNativeQuery(sqlStr);
		List<Object[]> listObj = query.getResultList();
		List<com.ecard.core.vo.CardInfo> cardInfoList = new ArrayList<>();
		for (Object[] object : listObj) {
			com.ecard.core.vo.CardInfo cardInfoVo = new com.ecard.core.vo.CardInfo((Date)object[0], (String) object[1], (String) object[2]);
			cardInfoList.add(cardInfoVo);
		}

		return cardInfoList;
	}
	
	public List<com.ecard.core.vo.CardInfo> searchCompanyTrees(String searchText){
		String sqlStr = "SELECT card_id, company_name, department_name, name, count(*) FROM card_info "
				+ "WHERE MATCH(company_name, company_name_kana, name, name_kana, department_name, position_name, email, "
				+ "zip_code, address_full, tel_number_company, tel_number_department, fax_number, mobile_number, card_owner_name) "
				+ "AGAINST (%s %s %s IN BOOLEAN MODE)";
		
		String params[] = { "'*W1:1,2:1,3:0,4:0,5:0,6:0,7:0,8:0,9:0,10:0,11:0,12:0,13:0,14:0 ", "\""+searchText.toLowerCase()+"\"", "'"};
		sqlStr = String.format(sqlStr, params);
		
		Query query = getEntityManager().createNativeQuery(sqlStr);
		List<Object[]> listObj = query.getResultList();
		List<com.ecard.core.vo.CardInfo> cardInfoList = new ArrayList<>();
		for (Object[] object : listObj) {
			com.ecard.core.vo.CardInfo cardInfoVo = new com.ecard.core.vo.CardInfo((Integer)object[0], (String) object[1], (String) object[2], 
					(String) object[3], (BigInteger) object[4]);
			cardInfoList.add(cardInfoVo);
		}

		return cardInfoList;
	}
}
