package com.ecard.core.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ecard.core.dao.LinkCardDAO;
import com.ecard.core.model.UserInfo;

/**
*
* @author vinhla
*/
@Repository("linkCardDAO")
@Transactional
public class LinkCardDAOImpl extends GenericDao implements LinkCardDAO {

	@Value("${compliance.recordDate}")
    private String complianceDate;

	@Transactional
	public int saveLinkCard(List<UserInfo> userInfoList){
		String sqlStr = "";
		Query query;    
		int result = 0;
		for(UserInfo userInfo : userInfoList){
			if (userInfo.getGroupCompanyId() == 1 || userInfo.getGroupCompanyId() ==2 || userInfo.getGroupCompanyId() ==3 || userInfo.getGroupCompanyId() == 4 || userInfo.getGroupCompanyId() == 5 ){
				sqlStr = "INSERT INTO link_card "
						+ "SELECT MC.card_id, MC.card_owner_id,MC.image_file,MC.company_name,MC.department_name,MC.name,MC.create_date,CI.create_date "
						+ "FROM (SELECT card_id,card_owner_id,image_file,email,name,company_name,department_name,create_date "
								+ "FROM card_info CI "
								+ "WHERE CI.card_owner_id = "+ userInfo.getUserId()
								+ " AND old_card_flg = 0 AND approval_status = 1 AND delete_flg = 0) MC "
						+ "INNER JOIN("
								+ "	SELECT card_id,card_owner_id,image_file,email,name,company_name,department_name,create_date "
								+ "FROM card_info CI WHERE NOT CI.card_owner_id = "+ userInfo.getUserId()
								+ " AND old_card_flg = 0 AND approval_status = 1 AND delete_flg = 0 "
								+ " AND (group_company_id IN(1,2,3,4,5) OR (group_company_id NOT IN(1,2,3,4,5) AND contact_date >= '"+ this.complianceDate +"'))) CI "
						+ "WHERE ((MC.email = CI.email AND MC.email <> '') OR (MC.name = CI.name AND MC.company_name = CI.company_name))"
						+ "GROUP BY MC.card_id, MC.card_owner_id, MC.image_file, MC.company_name, MC.department_name, MC.name, MC.create_date, CI.create_date "
						+ "HAVING count(*) >= 1";
			}
			else{
				sqlStr = "INSERT INTO link_card "
						+ "SELECT MC.card_id,MC.card_owner_id,MC.image_file,MC.company_name,MC.department_name,MC.name,MC.create_date,CI.create_date "
						+ "FROM ("
								+ "SELECT card_id,card_owner_id,image_file,email,name,company_name,department_name,create_date "
								+ "FROM card_info CI "
								+ "WHERE CI.card_owner_id = "+ userInfo.getUserId()
								+" AND old_card_flg = 0 AND approval_status = 1 AND delete_flg = 0) MC "
						+ "INNER JOIN("
								+ "SELECT card_id,card_owner_id,image_file,email,name,company_name,department_name,create_date "
								+ "FROM card_info CI "
								+ "WHERE NOT CI.card_owner_id = "+ userInfo.getUserId()
								+" AND old_card_flg = 0 AND approval_status = 1 AND delete_flg = 0 "
								+ "AND (group_company_id = "+ userInfo.getGroupCompanyId() +" OR (group_company_id <> "+ userInfo.getGroupCompanyId() +" AND contact_date >= '"+ this.complianceDate +"'))) CI "
						+ "WHERE ((MC.email = CI.email AND MC.email <> '') OR (MC.name = CI.name AND MC.company_name = CI.company_name)) "
						+ "GROUP BY MC.card_id, MC.card_owner_id, MC.image_file, MC.company_name, MC.department_name, MC.name, MC.create_date, CI.create_date "
						+ "HAVING count(*) >= 1";
			}
			query = getEntityManager().createNativeQuery(sqlStr);
			result = query.executeUpdate();
		}
		return result;
		
	}
	
	public int cleanLinkCardData(){
		Query query = getEntityManager().createNativeQuery("DELETE FROM link_card");
		return (int)query.executeUpdate();
	}
}
