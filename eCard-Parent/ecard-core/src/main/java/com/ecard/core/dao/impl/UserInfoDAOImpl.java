package com.ecard.core.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang.Validate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ecard.core.dao.UserInfoDAO;
import com.ecard.core.model.ActionLog;
import com.ecard.core.model.DownloadCsv;
import com.ecard.core.model.PushInfoId;
import com.ecard.core.model.Roles;
import com.ecard.core.model.UserInfo;
import com.ecard.core.model.UserMigration;
import com.ecard.core.model.UserNotification;
import com.ecard.core.vo.UserDownloadPermission;
import com.ecard.core.vo.UserInfoVo;
import com.ecard.core.vo.UserListContact;
import com.ecard.core.vo.UserProfile;

@Repository("userInfoDAO")
public class UserInfoDAOImpl extends GenericDao implements UserInfoDAO {

	@SuppressWarnings("unchecked")
	public List<UserInfo> findAllUsers() {
    	Query query = getEntityManager().createQuery("SELECT u FROM UserInfo u WHERE u.deleteFlg = 0  AND u.leaveFlg = 0 ORDER BY u.createDate DESC");
		return (List<UserInfo>) query.getResultList();
    }

    public UserInfo checkUserLogin(String email, String password) {
        Validate.notNull(email, "Email not null");
        Validate.notNull(password, "Password not null");

        Query q = getEntityManager().createQuery("SELECT u FROM UserInfo AS u WHERE u.email = :email and u.password = :password");
        q.setParameter("email", email);
        q.setParameter("password", password);

        return (UserInfo)q.getSingleResult();
    }

    public UserInfo findByEmail(String email){
        Validate.notNull(email, "Email not null");

        Query q = getEntityManager().createQuery("SELECT u FROM UserInfo AS u WHERE u.email = :email");
        q.setParameter("email", email);
        if(q.getResultList().size() == 0){
            UserInfo userInfo = new UserInfo();
            userInfo.setEmail(null);
            return userInfo;
        }
        return (UserInfo)q.getResultList().get(0);
    }

    public UserInfo findByUserId(int userId) {
        Validate.notNull(userId, "userId not null");
        
        Query q = getEntityManager().createQuery("SELECT u FROM UserInfo AS u WHERE u.userId = :userId");
        q.setParameter("userId", userId);

        return (UserInfo)q.getSingleResult();
    }

    public Roles findRoleByUserId(Integer userId){
        Validate.notNull(userId, "userId not null");
        
        Query q = getEntityManager().createQuery("SELECT u.roles FROM UserInfo u WHERE u.userId = :userId");
        q.setParameter("userId", userId);

        return (Roles)getOrNull(q);
    }
    public List<Integer> getPermisionTypeByUserId(Integer userId) {
        Validate.notNull(userId, "userId not null");
        
        Query query = getEntityManager().createNativeQuery("select r.permission_type "
													+" from roles r inner join user_info u on r.role_id = u.role_id or r.role_id = u.role_admin_id "
													+" where u.user_id = :user_id");
        query.setParameter("user_id", userId);
          
        return (List<Integer>) query.getResultList();
    }
    
    public int registerProfile(UserInfo userInfo){
        Integer userId = userInfo.getUserId();
        Query query = getEntityManager().createQuery("UPDATE UserInfo u SET u.name = :name, u.lastName = :lastName, u.firstName = :firstName, "
                + "u.firstNameKana = :firstNameKana, u.lastNameKana = :lastNameKana, u.nameKana = :nameKana, "
                + "u.companyName = :companyName, u.departmentName = :departmentName, u.positionName = :positionName, "
                //+ "u.email = :email, u.telNumberCompany = :telNumberCompany, u.telNumberDepartment = :telNumberDepartment, "
                //+ "u.telNumberDirect = :telNumberDirect, u.faxNumber = :faxNumber, u.mobileNumber = :mobileNumber, "
                //+ "u.zipCode = :zipCode, u.address1 = :address1, u.address2 = :address2, u.address3 = :address3, u.addressFull = :addressFull, u.companyUrl = :companyUrl, "
                + "u.memo1 = :memo1, u.memo2 = :memo2, u.groupCompanyId = :groupCompanyId "
                + "WHERE u.userId = :userId");
                                
        String name = userInfo.getLastName() + " " + userInfo.getFirstName();
        String nameKana = userInfo.getLastNameKana() + " " + userInfo.getFirstNameKana();
        //String addressFull = userInfo.getAddress1() + " " + userInfo.getAddress2() + " " + userInfo.getAddress3() + " " + userInfo.getAddress4();
        
        query.setParameter("userId", userId);
        query.setParameter("name", name);
        query.setParameter("lastName", userInfo.getLastName());
        query.setParameter("firstName", userInfo.getFirstName());
        query.setParameter("nameKana", nameKana);
        query.setParameter("firstNameKana", userInfo.getFirstNameKana());
        query.setParameter("lastNameKana", userInfo.getLastNameKana());
        query.setParameter("companyName", userInfo.getCompanyName());
        query.setParameter("departmentName", userInfo.getDepartmentName());
        query.setParameter("positionName", userInfo.getPositionName());
//        query.setParameter("email", userInfo.getEmail());
//        query.setParameter("telNumberCompany", userInfo.getTelNumberCompany());
//        query.setParameter("telNumberDepartment", userInfo.getTelNumberDepartment());
//        query.setParameter("telNumberDirect", userInfo.getTelNumberDirect());
//        query.setParameter("faxNumber", userInfo.getFaxNumber());
//        query.setParameter("mobileNumber", userInfo.getMobileNumber());
//        query.setParameter("zipCode", userInfo.getZipCode());
//        query.setParameter("addressFull", addressFull);
//        query.setParameter("address1", userInfo.getAddress1());
//        query.setParameter("address2", userInfo.getAddress2());
//        query.setParameter("address3", userInfo.getAddress3());
//        query.setParameter("companyUrl", userInfo.getCompanyUrl());
        query.setParameter("memo1", userInfo.getMemo1());
        query.setParameter("memo2", userInfo.getMemo2());
        query.setParameter("groupCompanyId", userInfo.getGroupCompanyId());
        
        return query.executeUpdate();
    }
    @Transactional
    public int registerProfileAdmin(UserInfo userInfo){
    	EntityManager em = getEntityManager();
        em.persist(userInfo);
        int id = userInfo.getUserId();
        em.close();
        return id;
    }
   
    public int updateProfileAdmin(UserInfo userInfo){
        Integer userId = userInfo.getUserId();
        String sql = "UPDATE UserInfo u SET u.lastName = :lastName, u.firstName = :firstName, "
                + "u.firstNameKana = :firstNameKana, u.lastNameKana = :lastNameKana, "
        		+ "u.email = :email, u.password = :password, "
                + "u.companyName = :companyName, u.departmentName = :departmentName, u.positionName = :positionName, "
                + "u.email = :email, u.telNumberCompany = :telNumberCompany, u.telNumberDepartment = :telNumberDepartment, "
                + "u.telNumberDirect = :telNumberDirect, u.faxNumber = :faxNumber, u.mobileNumber = :mobileNumber, "
                + "u.zipCode = :zipCode, u.address1 = :address1, u.address2 = :address2, u.address3 = :address3, "
                + "u.companyUrl = :companyUrl, u.memo1 = :memo1, u.memo2 = :memo2, u.userIndexNo = :userIndexNo,"
                + "u.groupDataDlRequestFlg = :groupDataDlRequestFlg, u.allDataDlRequestFlg = :allDataDlRequestFlg, "
                + "u.companyNameKana = :companyNameKana, u.groupCompanyId = :groupCompanyId, u.nameKana = :nameKana, u.name = :name,"
                + "u.groupDataDlFlg = :groupDataDlFlg, u.allDataDlFlg = :allDataDlFlg, u.sfManualLinkFlg = :sfManualLinkFlg, u.helpdeskFlg = :helpdeskFlg, "
                + "u.useStopFlg = :useStopFlg, u.useDate =:useDate, u.endDate =:endDate ,u.teamDivideCnt =:teamDivideCnt,u.roleAdminId= :roleAdminId ";
               if(userInfo.getRoles() != null && !"".equals(userInfo.getRoles().getRoleId())){
            	   sql  = sql + " ,u.roles.roleId = :roleId ";
               }
                sql = sql + " WHERE u.userId = :userId ";
        
        
        
        Query query = getEntityManager().createQuery(sql);
        
        query.setParameter("userId", userId);
        query.setParameter("email", userInfo.getEmail());
        query.setParameter("password", userInfo.getPassword());
        query.setParameter("lastName", userInfo.getLastName());
        query.setParameter("firstName", userInfo.getFirstName());
        query.setParameter("firstNameKana", userInfo.getFirstNameKana());
        query.setParameter("lastNameKana", userInfo.getLastNameKana());
        query.setParameter("companyName", userInfo.getCompanyName());
        query.setParameter("departmentName", userInfo.getDepartmentName());
        query.setParameter("positionName", userInfo.getPositionName());
        query.setParameter("telNumberCompany", userInfo.getTelNumberCompany());
        query.setParameter("telNumberDepartment", userInfo.getTelNumberDepartment());
        query.setParameter("telNumberDirect", userInfo.getTelNumberDirect());
        query.setParameter("faxNumber", userInfo.getFaxNumber());
        query.setParameter("mobileNumber", userInfo.getMobileNumber());
        query.setParameter("zipCode", userInfo.getZipCode());
        query.setParameter("address1", userInfo.getAddress1());
        query.setParameter("address2", userInfo.getAddress2());
        query.setParameter("address3", userInfo.getAddress3());
        query.setParameter("companyUrl", userInfo.getCompanyUrl());
        query.setParameter("memo1", userInfo.getMemo1());
        query.setParameter("memo2", userInfo.getMemo2());
        query.setParameter("groupDataDlFlg", userInfo.getGroupDataDlFlg());
        query.setParameter("allDataDlFlg", userInfo.getAllDataDlFlg());
        query.setParameter("sfManualLinkFlg", userInfo.getSfManualLinkFlg());
        query.setParameter("helpdeskFlg", userInfo.getHelpdeskFlg());
        query.setParameter("companyNameKana", userInfo.getCompanyNameKana());
        query.setParameter("groupCompanyId", userInfo.getGroupCompanyId());
        query.setParameter("nameKana", userInfo.getNameKana());
        query.setParameter("name", userInfo.getName());
        query.setParameter("groupDataDlRequestFlg", userInfo.getGroupDataDlRequestFlg());
        query.setParameter("allDataDlRequestFlg", userInfo.getAllDataDlRequestFlg());
        query.setParameter("useStopFlg", userInfo.getUseStopFlg());
        query.setParameter("useDate", userInfo.getUseDate());
        query.setParameter("endDate", userInfo.getEndDate());
        query.setParameter("teamDivideCnt", userInfo.getTeamDivideCnt());
        query.setParameter("userIndexNo", userInfo.getUserIndexNo());
        query.setParameter("roleAdminId", userInfo.getRoleAdminId());
        
        if(userInfo.getRoles() != null && !"".equals(userInfo.getRoles().getRoleId())){
         query.setParameter("roleId",userInfo.getRoles().getRoleId());
        }
        
        return query.executeUpdate();
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
    
    public int registerCoverImage(String coverImage, Integer userId){
        Validate.notNull(coverImage, "Cover image is not null");
        Query query = getEntityManager().createQuery("UPDATE UserInfo u SET u.coverImage = :coverImage WHERE u.userId = :userId");
        query.setParameter("coverImage", coverImage);
        query.setParameter("userId", userId);
        return query.executeUpdate();
    }
    
    public String getCoverImage(Integer userId){
        Validate.notNull(userId, "UserId is not null");
        Query query = getEntityManager().createQuery("SELECT u.coverImage FROM UserInfo u WHERE u.userId = :userId");
        query.setParameter("userId", userId);
        
        return (String)getOrNull(query);
    }
    public UserInfo getUserInfoByUserId(Integer userId){
        Validate.notNull(userId, "UserId is not null");
        
        Query query = getEntityManager().createQuery("SELECT u FROM UserInfo u WHERE u.userId = :userId");
        query.setParameter("userId", userId);
        
        return (UserInfo)getOrNull(query);
    }  
    
    public String getPasswordByUserId(Integer userId){
        Query query = getEntityManager().createQuery("SELECT ui.password FROM UserInfo ui WHERE ui.userId = :userId");        
        query.setParameter("userId", userId);
        return (String)getOrNull(query);        
    }
    
    public Boolean checkPassword(Integer userId ,String password){
        Query query = getEntityManager().createQuery("SELECT ui.userId FROM UserInfo ui WHERE ui.password = :password AND ui.userId = :userId");
        query.setParameter("password", password);
        query.setParameter("userId", userId);
        
        if(query.getResultList().size() == 0){
            return false;
        } else {
            return true;
        }
    }
    
    public Boolean updatePassword(Integer userId, String password) {
        Query query = getEntityManager().createQuery("UPDATE UserInfo AS u SET u.password = :password, u.lastChangePasswordDate = NOW() WHERE u.userId = :userId");
        query.setParameter("password", password);
        query.setParameter("userId", userId);
        int result = query.executeUpdate();
        if(result == 0){
            return false;
        } else {
            return true;
        }
    }
    
    public Boolean updateEmail(Integer userId, String email){
        Query query = getEntityManager().createQuery("UPDATE UserInfo AS u SET u.email = :email WHERE u.userId = :userId");
        query.setParameter("email", email);
        query.setParameter("userId", userId);
        int result = query.executeUpdate();
        if(result == 0){
            return false;
        } else {
            return true;
        }
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<UserInfoVo> searchUser(String criteriaSearch, int limit, int offet) {
		String sqlQuery = "SELECT * FROM user_info u WHERE ";
		if(!criteriaSearch.isEmpty())
			sqlQuery += "(u.name REGEXP  :criteriaSearch "
					+ " OR u.last_name_kana REGEXP :criteriaSearch "
					+ " OR u.first_name REGEXP :criteriaSearch "
					+ " OR u.last_name REGEXP :criteriaSearch "
					+ " OR u.first_name_kana REGEXP :criteriaSearch "
					+ " OR u.company_name REGEXP :criteriaSearch "
					+ " OR u.company_name_kana REGEXP :criteriaSearch "
					+ " OR u.department_name REGEXP :criteriaSearch "
					+ " OR u.email REGEXP :criteriaSearch "
					+ " OR u.mobile_number REGEXP :criteriaSearch "
					+ " OR u.tel_number_company REGEXP :criteriaSearch "
					+ " OR u.address_full REGEXP :criteriaSearch "
					+ " OR u.position_name REGEXP :criteriaSearch) AND ";
		sqlQuery += "u.delete_flg = 0 and u.leave_flg = 0  ORDER BY u.create_date DESC";
		Query query = null;
		if (limit > -1 && offet > -1)
			query = getEntityManager(). createNativeQuery(sqlQuery).setFirstResult(offet).setMaxResults(limit);
		else
			query = getEntityManager().createNativeQuery(sqlQuery);
		if(!criteriaSearch.isEmpty())
			query.setParameter("criteriaSearch",criteriaSearch);
		
		 List<Object[]> rows = query.getResultList();
	     List<UserInfoVo> result = new ArrayList<>(rows.size());
	        
	        for (Object[] row : rows) {
	            result.add(new UserInfoVo((Integer)row[0], (Integer)row[1],(Integer) row[2],(String) row[4], (String)row[15],(String) row[16],(String) row[17],
	            		(String)row[19],(String) row[20],(String) row[22], (String)row[23],(String) row[24],(String) row[25],(String) row[26],(String) row[27],
	            		(String) row[28],(String) row[29],(String) row[30],(String) row[32],(String) row[33],(String) row[34], 
	            		(String)row[35],(String) row[36], (Integer)row[42],(Date) row[48],(Date) row[49], (Date)row[51],(Integer)row[52],(String)row[56]));
	        }
		
		return result;
	}
	
	@Override
	public BigInteger countUser(String criteriaSearch) {
		String sqlQuery = "SELECT COUNT(*) FROM user_info u WHERE ";
		if(!criteriaSearch.isEmpty())
			sqlQuery += "(u.name REGEXP  :criteriaSearch "
					+ "OR u.last_name_kana REGEXP :criteriaSearch "
					+ "OR u.first_name REGEXP :criteriaSearch "
					+ "OR u.last_name REGEXP :criteriaSearch "
					+ "OR u.first_name_kana REGEXP :criteriaSearch "
					+ "OR u.company_name REGEXP :criteriaSearch "
					+ "OR u.company_name_kana REGEXP :criteriaSearch "
					+ "OR u.department_name REGEXP :criteriaSearch "
					+ " OR u.email REGEXP :criteriaSearch "
					+ " OR u.mobile_number REGEXP :criteriaSearch "
					+ " OR u.tel_number_company REGEXP :criteriaSearch "
					+ " OR u.address_full REGEXP :criteriaSearch "
					+ "OR u.position_name REGEXP :criteriaSearch) AND ";
		sqlQuery += "u.delete_flg = 0 and u.leave_flg = 0  ORDER BY u.create_date DESC";
		Query query = getEntityManager().createNativeQuery(sqlQuery);
		if(!criteriaSearch.isEmpty())
			query.setParameter("criteriaSearch", criteriaSearch);
		return (BigInteger) query.getSingleResult();
	}
    
    @SuppressWarnings("unchecked")
    public List<UserListContact> getUserListContact(Integer cardId) {
        Query query = getEntityManager().createNativeQuery("SELECT mc.card_id, c.name, c.company_name, c.department_name, c.position_name, c.image_file, po.contact_date "
                                        + "FROM my_card mc INNER JOIN card_info c ON mc.card_id = c.card_id " 
                                        + "INNER JOIN possession_card po ON c.card_id = po.card_id " 
                                        + "WHERE po.user_id IN (SELECT po.user_id FROM possession_card po WHERE po.card_id = :cardId) "
                                        + "AND mc.seq = 1 "
                                        + "ORDER BY po.contact_date DESC");
        
        query.setParameter("cardId", cardId);
        List<Object[]> rows = query.getResultList();
        List<UserListContact> result = new ArrayList<>(rows.size());
        
        for (Object[] row : rows) {
            result.add(new UserListContact((Integer)row[0], (String)row[1], (String)row[2], (String)row[3], (String)row[4], (String)row[5], (Date)row[6]));
        }
        
        return result;
    }

    @SuppressWarnings("unchecked")
    public List<UserListContact> getUserListContactByDate(Integer cardId, Date fromDate, Date toDate){

        Query query = getEntityManager().createNativeQuery("SELECT mc.card_id, ci.name, ci.company_name, ci.department_name, ci.position_name, ci.image_file, pc.contact_date"
                                        + " FROM card_info ci "
                                        + " INNER JOIN my_card mc ON ci.card_id = mc.card_id " 
                                        + " INNER JOIN possession_card pc ON pc.user_id = mc.user_id" 
                                        + " WHERE pc.card_id = :cardId AND pc.contact_date > :fromDate "
                                        + " AND pc.contact_date <= :toDate" 
                                        + " AND mc.seq = 1"
                                        + " GROUP BY mc.card_id, pc.contact_date" 
                                        + " ORDER BY pc.contact_date");

        query.setParameter("cardId", cardId);
        query.setParameter("fromDate", fromDate);
        query.setParameter("toDate", toDate);
    //        query.setParameter("fromDate", new java.sql.Timestamp(fromDate.getTime()));
    //        query.setParameter("toDate", new java.sql.Timestamp(toDate.getTime()));
        List<Object[]> rows = query.getResultList();
        List<UserListContact> result = new ArrayList<>(rows.size());

        for (Object[] row : rows) {
            result.add(new UserListContact((Integer)row[0], (String)row[1], (String)row[2], (String)row[3], (String)row[4], (String)row[5], (Date)row[6]));
        }

        return result;
    }
    
	/***
	 * Delete logic user.
	 * 
	 * @author Livepass.ThienDP
	 * @Date 2015/08/13
	 * @param userId
	 */
	public void deleteUser(int userId) {
		Query query = getEntityManager().createQuery("UPDATE UserInfo AS u SET u.deleteFlg = 1, u.deleteDate = :deleteDate WHERE u.userId = :userId");
		query.setParameter("deleteDate", new Date());
		query.setParameter("userId", userId);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserInfo> getUserInfoNotInAnyTeam() {
		List<UserInfo> userInfos = null;
		Query query = getEntityManager().createNativeQuery("SELECT * FROM user_info WHERE team_id = 0 OR team_id is NULL");
        List<Object[]> rows = query.getResultList();
        userInfos = new ArrayList<>(rows.size());
        Roles role=null;
        for (Object[] row : rows) {
        	UserInfo userInfo = new UserInfo();
        	userInfo.setUserId((Integer)row[0]);
        	userInfo.setLastName((String)row[16]);
        	userInfo.setFirstName((String)row[17]);
        	userInfo.setCompanyName((String)row[22]);
        	userInfo.setDepartmentName((String)row[24]);
        	role=new Roles();
        	role.setRoleId((Integer)row[1]);
        	userInfo.setRoles(role);
        	userInfos.add(userInfo);
        }
        
        return userInfos;
	}
	public Boolean updateTeamId(Integer userId,Integer teamID){
        Query query = getEntityManager().createNativeQuery("UPDATE user_info SET team_id = :teamID WHERE user_id = :userId");
        query.setParameter("teamID", teamID);
        query.setParameter("userId", userId);
        int result = query.executeUpdate();
        if(result == 0){
            return false;
        } else {
            return true;
        }
    }

	@Override
	public Integer removeTeamInfoByTeamId(Integer teamId) {
		//Update member.teamId =0
		Query q1 = getEntityManager().createNativeQuery("UPDATE user_info u SET u.team_id=NULL WHERE u.team_id = :teamId");
		q1.setParameter("teamId", teamId);
		return q1.executeUpdate();
				
	}
    public UserProfile getMailSettingInfo(Integer userId){
        Query query = getEntityManager().createNativeQuery("SELECT ui.mail_notice_flg, ui.mail_use_assist_flg, ui.mail_news_flg, ui.mail_send_flg, ui.sf_manual_link_flg " 
                + " FROM user_info ui" 
                + " WHERE ui.user_id = :userId");
        
        query.setParameter("userId", userId);
        List<Object[]> rows = query.getResultList();
        List<UserProfile> result = new ArrayList<>(rows.size());
        
        for (Object[] row : rows) {
            result.add(new UserProfile((Integer)row[0], (Integer)row[1], (Integer)row[2], (Integer)row[3], (Integer)row[4]));
        }
        return result.get(0);        
    }
    
    public void userLeave(int userId) {
		Query query = getEntityManager().createQuery("UPDATE UserInfo AS u SET u.leaveFlg = 1  WHERE u.userId = :userId");
		query.setParameter("userId", userId);
		query.executeUpdate();
	}
    
    public UserDownloadPermission getPermisionDownloadByUserId(Integer userId){
    	Query query = getEntityManager().createNativeQuery("SELECT ui.group_data_dl_flg, ui.all_data_dl_flg, ui.group_data_dl_request_flg, ui.all_data_dl_request_flg "
    			+ " FROM user_info ui "
    			+ " WHERE ui.user_id = :userId");
    	query.setParameter("userId", userId);
    	List<Object[]> rows = query.getResultList();
        List<UserDownloadPermission> result = new ArrayList<>(rows.size());
        for (Object[] row : rows) {
            result.add(new UserDownloadPermission((Integer)row[0], (Integer)row[1], (Integer)row[2], (Integer)row[3]));
        }
        return result.get(0);
    }
    
    
    public List<DownloadCsv> getHistoryCSVDownload(Integer userId){
//    	Query query = getEntityManager().createNativeQuery("SELECT * FROM download_csv AS dc WHERE dc.user_id = :userId AND dc.csv_approval_status = 1  ORDER BY  dc.approval_date DESC");
    	Query query = getEntityManager().createNativeQuery("SELECT * FROM download_csv AS dc WHERE dc.user_id = :userId ORDER BY  dc.approval_date DESC");
    	query.setParameter("userId", userId);
        
        List<Object[]> rows = query.getResultList();
        List<DownloadCsv> result = new ArrayList<>(rows.size());
        for (Object[] row : rows) {
        	UserInfo userInfo = new UserInfo();
        	userInfo.setUserId((Integer)row[1]);
            result.add(new DownloadCsv((Integer)row[0],(UserInfo)userInfo, (int)row[2], (Date)row[3], (int)row[4], (Date)row[5], (String)row[6]));
        }
        
        return result;
    
    }

	@Override
	public List<UserInfoVo> getAllUserSearchInfo() {
		// TODO Auto-generated method stub
		Query query = getEntityManager().createNativeQuery("SELECT * FROM user_info u WHERE u.delete_flg = 0  AND u.leave_flg = 0 ORDER BY u.create_date DESC");
		List<Object[]> rows = query.getResultList();
        List<UserInfoVo> result = new ArrayList<>(rows.size());

        for (Object[] row : rows) {
            result.add(new UserInfoVo((Integer)row[0],(String) row[16],(String) row[17], (String)row[22],(String) row[24],(String) row[25]));
        }
        return result;		
	}
	
	public PushInfoId getPushNotification(Integer userId){
		Query query = getEntityManager().createNativeQuery("SELECT * FROM push_info pi WHERE pi.user_id = :userId");
		query.setParameter("userId", userId);
		List<Object[]> rows = query.getResultList();
        List<PushInfoId> result = new ArrayList<>(rows.size());
        if(rows.size() != 0){
        	for (Object[] row : rows) {
                result.add(new PushInfoId((Integer)row[0],(String) row[1], (String)row[2], (Date)row[3], (Date)row[4]));
            }
        	return result.get(0);
        } else {
        	PushInfoId pushInfoId = new PushInfoId();
        	pushInfoId.setUserId(userId);
        	pushInfoId.setDeviceToken("");
        	pushInfoId.setDeviceType("");
        	pushInfoId.setCreateDate(null);
        	pushInfoId.setUpdateDate(null);
        	return pushInfoId;
        }        
	}
	
	public void saveHistoryNotification(UserNotification userNotifcation){
		getEntityManager().persist(userNotifcation);
		getEntityManager().flush();
	}
	
	public List<DownloadCsv> getAllHistoryDownload(){
		Query query = getEntityManager().createNativeQuery("SELECT * FROM download_csv dc ORDER BY dc.request_date DESC");    	
        
        List<Object[]> rows = query.getResultList();
        List<DownloadCsv> result = new ArrayList<>(rows.size());
        
        for (Object[] row : rows) {
        	UserInfo userInfo = new UserInfo();
        	userInfo.setUserId((Integer)row[1]);
            result.add(new DownloadCsv((Integer)row[0],(UserInfo)userInfo, (int)row[2], (Date)row[3], (int)row[4], (Date)row[5], (String)row[6], (int)row[7]));
        }
        
        return result;
	}
	
	public boolean updateRecognitionDownload(Integer csvId, int csvStatus, int operaterId){
		Query query = getEntityManager().createNativeQuery("UPDATE download_csv As dc SET dc.csv_approval_status = :csvStatus, "
				+ " dc.approval_date = NOW(),"
				+ " dc.operater_id = :operaterId "
				+ "WHERE dc.csv_id = :csvId");
				/*+ "dc.approval_date = NOW(), "
				+ "dc.csv_approval_status = :csvStatus  "
				+ "WHERE dc.csv_id = :csvId");*/
		query.setParameter("csvStatus", csvStatus);
		query.setParameter("csvId", csvId);
		query.setParameter("operaterId", operaterId);
		int result = query.executeUpdate();
        if(result == 0){
            return false;
        } else {
            return true;
        }
		
	}
    
    public int updateFisrtLogin(Integer userId, int fisrtLogin){
        Query query = getEntityManager().createQuery("UPDATE UserInfo u SET u.firstLoginF = :fisrtLogin WHERE u.userId = :userId");
        query.setParameter("userId", userId);
        query.setParameter("fisrtLogin", fisrtLogin);
        return (int)query.executeUpdate();
    }
    
    public UserInfo getLastModifyDateByEmail(String email){
        Query query = getEntityManager().createQuery("SELECT u FROM UserInfo u WHERE u.email = :email");
        query.setParameter("email", email);
        return (UserInfo)getOrNull(query);
    }


	@Override
	public List<UserInfoVo> getListUserofTeam(int teamId) {
		// TODO Auto-generated method stub
		Query query = getEntityManager().createNativeQuery("select u.user_id, count(*) as team_cntt, u.name, u.last_name, u.first_name, u.company_name, u.department_name, u.position_name "
				+ "	from admin_possession_card ad inner join user_info u on u.user_id = ad.user_id and u.team_id= :team_id "
				+ "	group by u.user_id "
				+ "	union "
				+ "	select u.user_id, 0 as team_cntt, u.name, u.last_name, u.first_name, u.company_name, u.department_name, u.position_name "
				+ " from user_info u "
				+ "	where  u.user_id not in (select user_id from admin_possession_card) and team_id = :team_id");
        query.setParameter("team_id", teamId);
        List<Object[]> rows = query.getResultList();
        List<UserInfoVo> result = new ArrayList<>(rows.size());

        for (Object[] row : rows) {
            //result.add(new UserInfoVo((Integer)row[0],(Integer)row[3] ,(String) row[15],(String) row[16],(String) row[17], (String)row[22],(String) row[24],(String) row[25]));
        	result.add(new UserInfoVo((Integer)row[0],((BigInteger)row[1]).intValue() ,(String) row[2],(String) row[3],(String) row[4], (String)row[5],(String) row[6],(String) row[7]));
        }
        return result;	
        
		
	}

    
    public void updateUseStopFlg(Integer userId, Integer useStopFlg){
        Query query = getEntityManager().createQuery("UPDATE UserInfo u SET u.useStopFlg = :useStopFlg WHERE u.userId = :userId");
        query.setParameter("userId", userId);
        query.setParameter("useStopFlg", useStopFlg);
        query.executeUpdate();
    }
    
    public UserInfo checkUserStopFlg(String email){
        Query query = getEntityManager().createQuery("SELECT u FROM UserInfo u WHERE u.email = :email AND u.useStopFlg = 1");
        query.setParameter("email", email);
        return (UserInfo)getOrNull(query);
    }

    public void registerUserMigration(UserMigration userMigration){
    	getEntityManager().persist(userMigration);
		getEntityManager().flush();
    }

	@Override
	public List<UserInfoVo> getUserInArrUserId(List<Integer> lstUserId) {
		String sqlQuery = "SELECT * FROM user_info u WHERE u.delete_flg = 0 AND u.leave_flg = 0 and u.user_id in (:user_id) ORDER BY u.create_date DESC ";
		 Query query = getEntityManager().createNativeQuery(sqlQuery);
		 query.setParameter("user_id", lstUserId);
		 List<Object[]> rows = query.getResultList();
	     List<UserInfoVo> result = new ArrayList<>(rows.size());
	        
	        for (Object[] row : rows) {
	            result.add(new UserInfoVo((Integer)row[0], (Integer)row[1],(Integer) row[2],(String) row[4], (String)row[15],(String) row[16],(String) row[17],
	            		(String)row[19],(String) row[20],(String) row[22], (String)row[23],(String) row[24],(String) row[25],(String) row[26],(String) row[27],
	            		(String) row[28],(String) row[29],(String) row[30],(String) row[32],(String) row[33],(String) row[34], 
	            		(String)row[35],(String) row[36], (Integer)row[42],(Date) row[48],(Date) row[49], (Date)row[51],(Integer)row[52] ));
	        }
		
		return result;
	}


	@Override
	public List<UserInfoVo> searchUserOfMyCompany(String criteriaSearch, int limit, int offet, int groupCompanyInfoId) {
		// TODO Auto-generated method stub
		String sqlQuery = "SELECT * FROM user_info u WHERE ";
		if(!criteriaSearch.isEmpty())
			sqlQuery += "(u.name REGEXP  :criteriaSearch "
					+ " OR u.last_name_kana REGEXP :criteriaSearch "
					+ " OR u.first_name REGEXP :criteriaSearch "
					+ " OR u.last_name REGEXP :criteriaSearch "
					+ " OR u.first_name_kana REGEXP :criteriaSearch "
					+ " OR u.company_name REGEXP :criteriaSearch "
					+ " OR u.company_name_kana REGEXP :criteriaSearch "
					+ " OR u.department_name REGEXP :criteriaSearch "
					+ " OR u.email REGEXP :criteriaSearch "
					+ " OR u.mobile_number REGEXP :criteriaSearch "
					+ " OR u.tel_number_company REGEXP :criteriaSearch "
					+ " OR u.address_full REGEXP :criteriaSearch "
					+ " OR u.position_name REGEXP :criteriaSearch) AND ";
		sqlQuery += "u.delete_flg = 0 AND u.leave_flg = 0  AND u.group_company_id = :group_company_id  ORDER BY u.create_date DESC";
		Query query = null;
		if (limit > -1 && offet > -1)
			query = getEntityManager(). createNativeQuery(sqlQuery).setFirstResult(offet).setMaxResults(limit);
		else
			query = getEntityManager().createNativeQuery(sqlQuery);
		if(!criteriaSearch.isEmpty())
			query.setParameter("criteriaSearch",criteriaSearch);
		
		
		query.setParameter("group_company_id",groupCompanyInfoId);
		 List<Object[]> rows = query.getResultList();
	     List<UserInfoVo> result = new ArrayList<>(rows.size());
	        
	        for (Object[] row : rows) {
	            result.add(new UserInfoVo((Integer)row[0], (Integer)row[1],(Integer) row[2],(String) row[4], (String)row[15],(String) row[16],(String) row[17],
	            		(String)row[19],(String) row[20],(String) row[22], (String)row[23],(String) row[24],(String) row[25],(String) row[26],(String) row[27],
	            		(String) row[28],(String) row[29],(String) row[30],(String) row[32],(String) row[33],(String) row[34], 
	            		(String)row[35],(String) row[36], (Integer)row[42],(Date) row[48],(Date) row[49], (Date)row[51],(Integer)row[52],(String)row[56]));
	        }
		
		return result;
	}

	@Override
	public BigInteger countUser(String criteriaSearch, int groupCompanyInfoId) {
		// TODO Auto-generated method stub
		String sqlQuery = "SELECT COUNT(*) FROM user_info u WHERE ";
		if(!criteriaSearch.isEmpty())
			sqlQuery += "(u.name REGEXP  :criteriaSearch "
					+ "OR u.last_name_kana REGEXP :criteriaSearch "
					+ "OR u.first_name REGEXP :criteriaSearch "
					+ "OR u.last_name REGEXP :criteriaSearch "
					+ "OR u.first_name_kana REGEXP :criteriaSearch "
					+ "OR u.company_name REGEXP :criteriaSearch "
					+ "OR u.company_name_kana REGEXP :criteriaSearch "
					+ "OR u.department_name REGEXP :criteriaSearch "
					+ " OR u.email REGEXP :criteriaSearch "
					+ " OR u.mobile_number REGEXP :criteriaSearch "
					+ " OR u.tel_number_company REGEXP :criteriaSearch "
					+ " OR u.address_full REGEXP :criteriaSearch "
					+ "OR u.position_name REGEXP :criteriaSearch) AND ";
		sqlQuery += "u.delete_flg = 0 AND u.leave_flg = 0  AND u.group_company_id = :group_company_id ORDER BY u.create_date DESC";
		Query query = getEntityManager().createNativeQuery(sqlQuery);
		if(!criteriaSearch.isEmpty())
			query.setParameter("criteriaSearch", criteriaSearch);
		
		query.setParameter("group_company_id", groupCompanyInfoId);
		return (BigInteger) query.getSingleResult();
	}

    public int getUserIdByUserIndexNo(String userIndexNo){
        Query query = getEntityManager().createNativeQuery("SELECT mu.user_id FROM user_migration mu WHERE mu.sansan_user_id = :userIndexNo");        
        query.setParameter("userIndexNo", userIndexNo);
        List<Object> results = query.getResultList();
        int response = 0;
        if(results != null && results.size() > 0){
        	response = (int)results.get(0);
        }
        return response;
    }
    
    public boolean checkUserMigration(String sansanId){
    	Query query = getEntityManager().createNativeQuery("SELECT mu.user_id FROM user_migration mu WHERE mu.sansan_user_id = :sansanId");
    	query.setParameter("sansanId", sansanId);
    	int result = query.getResultList().size();    	
        if(result == 0){
            return false;
        } else {
            return true;
        }
    }

	@Override
	public List<DownloadCsv> getAllHistoryDownloadByCompany(List<Integer> userId) {
		// TODO Auto-generated method stub
        Query query = getEntityManager().createNativeQuery("SELECT * FROM download_csv dc WHERE dc.user_id in (:user_id) ORDER BY dc.request_date DESC");    	
          query.setParameter("user_id", userId);
        List<Object[]> rows = query.getResultList();
        List<DownloadCsv> result = new ArrayList<>(rows.size());
        
        for (Object[] row : rows) {
        	UserInfo userInfo = new UserInfo();
        	userInfo.setUserId((Integer)row[1]);
            result.add(new DownloadCsv((Integer)row[0],(UserInfo)userInfo, (int)row[2], (Date)row[3], (int)row[4], (Date)row[5], (String)row[6], (int)row[7]));
        }
        
        return result;
	}

	@Override
	public List<String> getListEmail(List<Integer> listId,int sendType) {
		String sqlQuery = "SELECT u.email FROM user_info u  WHERE u.leave_flg != 1 and u.delete_flg != 1 ";
		if(sendType == 2){
			sqlQuery += " and u.group_company_id in (:group_company_id)";
		}else if(sendType == 3){
			sqlQuery += " and u.user_id in (:user_id)";
		}
		
		Query query = getEntityManager().createNativeQuery(sqlQuery);
		if(sendType ==2){
			query.setParameter("group_company_id", listId);
		}else if(sendType == 3){
			query.setParameter("user_id", listId);
		}
	
		return query.getResultList();
	}
	
	public void updateTeamDivideCnt(List<Integer> listUser){
        Query query = getEntityManager().createNativeQuery("UPDATE user_info SET team_divide_cnt = 0 WHERE user_id IN (:userId)");
        query.setParameter("userId", listUser);
        query.executeUpdate();
	}
        
    public void saveActionLog(ActionLog actionLog) {
        getEntityManager().merge(actionLog);
        getEntityManager().flush();
    }
    public List<UserInfoVo> getListUserAllTeam(){
		Query query = getEntityManager().createNativeQuery("SELECT * FROM user_info u WHERE u.delete_flg = 0  AND u.leave_flg = 0 ");        
        List<Object[]> rows = query.getResultList();
        List<UserInfoVo> result = new ArrayList<>(rows.size());

        for (Object[] row : rows) {
            result.add(new UserInfoVo((Integer)row[0],(Integer)row[3] ,(String) row[15],(String) row[16],(String) row[17], (String)row[22],(String) row[24],(String) row[25]));
        }
        return result;	
	}
    
    public boolean checkUseDateEndDate(String email){
    	Query query = getEntityManager().createNativeQuery("SELECT * FROM user_info u WHERE u.email = :email AND (DATE_FORMAT(NOW(),'%Y-%m-%d') BETWEEN DATE_FORMAT(u.use_date,'%Y-%m-%d') AND DATE_FORMAT(u.end_date,'%Y-%m-%d'))");
    	query.setParameter("email", email.trim());
    	
    	int result = query.getFirstResult();
    	if(result == 1)
    		return true;
    	else
    		return false;
    }
    
    @SuppressWarnings("unchecked")
	@Override
	public List<UserInfoVo> searchUserForList(String criteriaSearch, int limit, int offet) {
		String sqlQuery = "SELECT * FROM user_info u WHERE ";
		if(!criteriaSearch.isEmpty())
			sqlQuery += "(u.name REGEXP  :criteriaSearch "
					+ " OR u.last_name_kana REGEXP :criteriaSearch "
					+ " OR u.first_name REGEXP :criteriaSearch "
					+ " OR u.last_name REGEXP :criteriaSearch "
					+ " OR u.first_name_kana REGEXP :criteriaSearch "
					+ " OR u.company_name REGEXP :criteriaSearch "
					+ " OR u.company_name_kana REGEXP :criteriaSearch "
					+ " OR u.department_name REGEXP :criteriaSearch "
					+ " OR u.email REGEXP :criteriaSearch "
					+ " OR u.mobile_number REGEXP :criteriaSearch "
					+ " OR u.tel_number_company REGEXP :criteriaSearch "
					+ " OR u.address_full REGEXP :criteriaSearch "
					+ " OR u.position_name REGEXP :criteriaSearch) AND ";
		sqlQuery += "u.delete_flg = 0  ORDER BY u.create_date DESC";
		Query query = null;
		if (limit > -1 && offet > -1)
			query = getEntityManager(). createNativeQuery(sqlQuery).setFirstResult(offet).setMaxResults(limit);
		else
			query = getEntityManager().createNativeQuery(sqlQuery);
		if(!criteriaSearch.isEmpty())
			query.setParameter("criteriaSearch",criteriaSearch);
		
		 List<Object[]> rows = query.getResultList();
	     List<UserInfoVo> result = new ArrayList<>(rows.size());
	        
	        for (Object[] row : rows) {
	            result.add(new UserInfoVo((Integer)row[0], (Integer)row[1],(Integer) row[2],(String) row[4], (String)row[15],(String) row[16],(String) row[17],
	            		(String)row[19],(String) row[20],(String) row[22], (String)row[23],(String) row[24],(String) row[25],(String) row[26],(String) row[27],
	            		(String) row[28],(String) row[29],(String) row[30],(String) row[32],(String) row[33],(String) row[34], 
	            		(String)row[35],(String) row[36], (Integer)row[42],(Date) row[48],(Date) row[49], (Date)row[51],(Integer)row[52],(String)row[56]));
	        }
		
		return result;
	}
	
	@Override
	public BigInteger countUserForList(String criteriaSearch) {
		String sqlQuery = "SELECT COUNT(*) FROM user_info u WHERE ";
		if(!criteriaSearch.isEmpty())
			sqlQuery += "(u.name REGEXP  :criteriaSearch "
					+ "OR u.last_name_kana REGEXP :criteriaSearch "
					+ "OR u.first_name REGEXP :criteriaSearch "
					+ "OR u.last_name REGEXP :criteriaSearch "
					+ "OR u.first_name_kana REGEXP :criteriaSearch "
					+ "OR u.company_name REGEXP :criteriaSearch "
					+ "OR u.company_name_kana REGEXP :criteriaSearch "
					+ "OR u.department_name REGEXP :criteriaSearch "
					+ " OR u.email REGEXP :criteriaSearch "
					+ " OR u.mobile_number REGEXP :criteriaSearch "
					+ " OR u.tel_number_company REGEXP :criteriaSearch "
					+ " OR u.address_full REGEXP :criteriaSearch "
					+ "OR u.position_name REGEXP :criteriaSearch) AND ";
		sqlQuery += "u.delete_flg = 0 ORDER BY u.create_date DESC";
		Query query = getEntityManager().createNativeQuery(sqlQuery);
		if(!criteriaSearch.isEmpty())
			query.setParameter("criteriaSearch", criteriaSearch);
		return (BigInteger) query.getSingleResult();
	}
	@Override
	public List<UserInfoVo> searchUserOfMyCompanyForList(String criteriaSearch, int limit, int offet, int groupCompanyInfoId) {
		// TODO Auto-generated method stub
		String sqlQuery = "SELECT * FROM user_info u WHERE ";
		if(!criteriaSearch.isEmpty())
			sqlQuery += "(u.name REGEXP  :criteriaSearch "
					+ " OR u.last_name_kana REGEXP :criteriaSearch "
					+ " OR u.first_name REGEXP :criteriaSearch "
					+ " OR u.last_name REGEXP :criteriaSearch "
					+ " OR u.first_name_kana REGEXP :criteriaSearch "
					+ " OR u.company_name REGEXP :criteriaSearch "
					+ " OR u.company_name_kana REGEXP :criteriaSearch "
					+ " OR u.department_name REGEXP :criteriaSearch "
					+ " OR u.email REGEXP :criteriaSearch "
					+ " OR u.mobile_number REGEXP :criteriaSearch "
					+ " OR u.tel_number_company REGEXP :criteriaSearch "
					+ " OR u.address_full REGEXP :criteriaSearch "
					+ " OR u.position_name REGEXP :criteriaSearch) AND ";
		sqlQuery += "u.delete_flg = 0  AND u.group_company_id = :group_company_id  ORDER BY u.create_date DESC";
		Query query = null;
		if (limit > -1 && offet > -1)
			query = getEntityManager(). createNativeQuery(sqlQuery).setFirstResult(offet).setMaxResults(limit);
		else
			query = getEntityManager().createNativeQuery(sqlQuery);
		if(!criteriaSearch.isEmpty())
			query.setParameter("criteriaSearch",criteriaSearch);
		
		
		query.setParameter("group_company_id",groupCompanyInfoId);
		 List<Object[]> rows = query.getResultList();
	     List<UserInfoVo> result = new ArrayList<>(rows.size());
	        
	        for (Object[] row : rows) {
	            result.add(new UserInfoVo((Integer)row[0], (Integer)row[1],(Integer) row[2],(String) row[4], (String)row[15],(String) row[16],(String) row[17],
	            		(String)row[19],(String) row[20],(String) row[22], (String)row[23],(String) row[24],(String) row[25],(String) row[26],(String) row[27],
	            		(String) row[28],(String) row[29],(String) row[30],(String) row[32],(String) row[33],(String) row[34], 
	            		(String)row[35],(String) row[36], (Integer)row[42],(Date) row[48],(Date) row[49], (Date)row[51],(Integer)row[52],(String)row[56]));
	        }
		
		return result;
	}

	@Override
	public BigInteger countUserForList(String criteriaSearch, int groupCompanyInfoId) {
		// TODO Auto-generated method stub
		String sqlQuery = "SELECT COUNT(*) FROM user_info u WHERE ";
		if(!criteriaSearch.isEmpty())
			sqlQuery += "(u.name REGEXP  :criteriaSearch "
					+ "OR u.last_name_kana REGEXP :criteriaSearch "
					+ "OR u.first_name REGEXP :criteriaSearch "
					+ "OR u.last_name REGEXP :criteriaSearch "
					+ "OR u.first_name_kana REGEXP :criteriaSearch "
					+ "OR u.company_name REGEXP :criteriaSearch "
					+ "OR u.company_name_kana REGEXP :criteriaSearch "
					+ "OR u.department_name REGEXP :criteriaSearch "
					+ " OR u.email REGEXP :criteriaSearch "
					+ " OR u.mobile_number REGEXP :criteriaSearch "
					+ " OR u.tel_number_company REGEXP :criteriaSearch "
					+ " OR u.address_full REGEXP :criteriaSearch "
					+ "OR u.position_name REGEXP :criteriaSearch) AND ";
		sqlQuery += "u.delete_flg = 0  AND u.group_company_id = :group_company_id ORDER BY u.create_date DESC";
		Query query = getEntityManager().createNativeQuery(sqlQuery);
		if(!criteriaSearch.isEmpty())
			query.setParameter("criteriaSearch", criteriaSearch);
		
		query.setParameter("group_company_id", groupCompanyInfoId);
		return (BigInteger) query.getSingleResult();
	}
}
