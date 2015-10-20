package com.ecard.core.service.impl;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecard.core.dao.AutoLoginDAO;
import com.ecard.core.dao.DataIndexDAO;
import com.ecard.core.dao.UserInfoDAO;
import com.ecard.core.model.ActionLog;
import com.ecard.core.model.AutoLogin;
import com.ecard.core.model.DownloadCsv;
import com.ecard.core.model.MailDomainList;
import com.ecard.core.model.PushInfoId;
import com.ecard.core.model.Roles;
import com.ecard.core.model.UserInfo;
import com.ecard.core.model.UserMigration;
import com.ecard.core.model.UserNotification;
import com.ecard.core.model.enums.ActionTypeEnum;
import com.ecard.core.model.enums.IndexTypeEnum;
import com.ecard.core.model.enums.PropertyCodeEnum;
import com.ecard.core.model.enums.TableTypeEnum;
import com.ecard.core.service.UserInfoService;
import com.ecard.core.vo.ActionLogDownloadVo;
import com.ecard.core.vo.CardUpdateHistoryDownloadVo;
import com.ecard.core.vo.UserDownloadPermission;
import com.ecard.core.vo.UserInfoVo;
import com.ecard.core.vo.UserListContact;
import com.ecard.core.vo.UserProfile;

/**
 *
 * @author vinhla
 */
@Service("userInfoService")
@Transactional
public class UserInfoServiceImpl implements UserInfoService {
	@Value("${csv.import.batchSize}")
	private String importCsvBatchSize;
	
	@Autowired
	UserInfoDAO userInfoDAO;

	@Autowired
	AutoLoginDAO autoLoginDAO;

	@Autowired
    DataIndexDAO dataIndexDAO;
	
	public List<UserInfo> getAllUserInfo() {

		List<UserInfo> userInfoList = userInfoDAO.findAllUsers();

		return userInfoList;
	}

	public UserInfo checkUserLogin(String email, String password) {
		return userInfoDAO.checkUserLogin(email, password);
	}

	public UserInfo findUserByEmail(String email) {
		return userInfoDAO.findByEmail(email);
	}

	public AutoLogin findByToken(String token) {
		return autoLoginDAO.findByToken(token);
	}

	public void removeUserToken(String token) {
		autoLoginDAO.deleteByToken(token);
	}

	public AutoLogin createUserToken(int userId, String token) {
		// delete token when user logout
		autoLoginDAO.deleteTokenByUserId(userId);

		// Create new token
		UserInfo userInfo = userInfoDAO.findByUserId(userId);

		AutoLogin autoLogin = new AutoLogin();
		autoLogin.setCreateDate(new Date());
		autoLogin.setUserInfo(userInfo);
		autoLogin.setToken(token);

		autoLoginDAO.saveUserToken(autoLogin);

		return autoLogin;
	}

	public Roles findRoleByUserId(Integer userId) {
		return userInfoDAO.findRoleByUserId(userId);
	}

	public List<Integer> getPermisionTypeByUserId(Integer userId) {
		return userInfoDAO.getPermisionTypeByUserId(userId);
	}

	public int registerProfile(UserInfo userInfo) {
		return userInfoDAO.registerProfile(userInfo);
	}
	
	public int updateProfileAdmin(UserInfo userInfo) {
		/*if(userInfo.getUserIndexNo()!=null && userInfo.getUserIndexNo()!=""){
			String userIndexNo=dataIndexIdDAO.updateDataIndexBy(IndexTypeEnum.UserInfor, ActionTypeEnum.Update, TableTypeEnum.User, PropertyCodeEnum.ManualPC,userInfo.getUserIndexNo(),userInfo.getGroupCompanyId().toString());
			userInfo.setUserIndexNo(userIndexNo);	
		}*/
		String indexId = dataIndexDAO.insertOrUpdateDataIndexBy(IndexTypeEnum.UserInfor, ActionTypeEnum.Update, TableTypeEnum.User, PropertyCodeEnum.ManualPC, userInfo.getUserIndexNo(), userInfo.getGroupCompanyId().toString());
		userInfo.setUserIndexNo(indexId);	
		return userInfoDAO.updateProfileAdmin(userInfo);
	}
	
	public int updateProfileAdminAllocation(UserInfo userInfo) {
		return userInfoDAO.updateProfileAdmin(userInfo);
	}
	
	public int registerProfileAdmin(UserInfo userInfo) {
		/*String userIndexNo=dataIndexIdDAO.insertDataIndexBy(IndexTypeEnum.UserInfor, ActionTypeEnum.Insert, TableTypeEnum.User, PropertyCodeEnum.ManualPC,userInfo.getGroupCompanyId().toString());
		userInfo.setUserIndexNo(userIndexNo);*/
		String indexId = dataIndexDAO.insertOrUpdateDataIndexBy(IndexTypeEnum.UserInfor, ActionTypeEnum.Insert, TableTypeEnum.User, PropertyCodeEnum.ManualPC,userInfo.getUserIndexNo(), userInfo.getGroupCompanyId().toString());
		userInfo.setUserIndexNo(indexId);	
		return userInfoDAO.registerProfileAdmin(userInfo);
	}
	
	public int registerCoverImage(String coverImage, Integer userId) {
		return userInfoDAO.registerCoverImage(coverImage, userId);
	}

	public String getCoverImage(Integer userId) {
		return userInfoDAO.getCoverImage(userId);
	}

	public Boolean checkToken(String token) {
		return autoLoginDAO.checkToken(token);
	}

	public UserInfo getUserInfoByUserId(Integer userId) {
		return userInfoDAO.getUserInfoByUserId(userId);
	}

	public Boolean checkPassword(Integer userId, String password) {
		return userInfoDAO.checkPassword(userId, password);
	}

	public Boolean updatePassword(Integer userId, String password) {
		return userInfoDAO.updatePassword(userId, password);
	}

	public Boolean updateEmail(Integer userId, String email) {
		return userInfoDAO.updateEmail(userId, email);
	}

	public List<UserListContact> getUserListContact(Integer cardId) {
		return userInfoDAO.getUserListContact(cardId);
	}

	public List<UserInfoVo> searchUser(String criteriaSearch, int limit, int offet) {
		return userInfoDAO.searchUser(criteriaSearch, limit, offet);
	}

	public List<UserListContact> getUserListContactByDate(Integer cardId, Date fromDate, Date toDate) {
		return userInfoDAO.getUserListContactByDate(cardId, fromDate, toDate);
	}
	
	public void deleteUser(int userId) {
		userInfoDAO.deleteUser(userId);
	}

	@Override
	public List<UserInfo> getUserInfoNotInAnyTeam() {
		return userInfoDAO.getUserInfoNotInAnyTeam();
	}
	

	@Override
	public boolean updateTeamId(int userId, int teamID) {
		return userInfoDAO.updateTeamId(userId,teamID);
	}
	
	@Override
	public boolean updateTeamId(int userId, Integer teamID) {
		return userInfoDAO.updateTeamId(userId,teamID);
	}
	
	@Override
	public Boolean checkEmailExist(String email) {
		return userInfoDAO.checkEmailExist(email);
	}

    public UserProfile getMailSettingInfo(Integer userId){
        return userInfoDAO.getMailSettingInfo(userId);
    }

    public String getPasswordByUserId(Integer userId){
        return userInfoDAO.getPasswordByUserId(userId);
    }
    
    public void userLeave(int userId){
    	userInfoDAO.userLeave(userId);
    }

	@Override
	public BigInteger countUser(String criteriaSearch) {
		return userInfoDAO.countUser(criteriaSearch);
	}
    public UserDownloadPermission getPermisionDownloadByUserId(Integer userId){
    	return userInfoDAO.getPermisionDownloadByUserId(userId);
    }
    public List<DownloadCsv> getHistoryCSVDownload(Integer userId){
    	return userInfoDAO.getHistoryCSVDownload(userId);
    }

	@Override
	public List<UserInfoVo> getAllUserSearchInfo() {
		List<UserInfoVo> userInfoList = userInfoDAO.getAllUserSearchInfo();

		return userInfoList;
	}
	
	public PushInfoId getPushNotification(Integer userId){
		return userInfoDAO.getPushNotification(userId);
	}

	public void saveHistoryNotification(UserNotification userNotifcation){
		userInfoDAO.saveHistoryNotification(userNotifcation);
	}
	
	public List<DownloadCsv> getAllHistoryDownload(){
		return userInfoDAO.getAllHistoryDownload();
	}
	
	public boolean updateRecognitionDownload(Integer csvId, int csvStatus, int operaterId){
		return userInfoDAO.updateRecognitionDownload(csvId, csvStatus, operaterId);
	}
    
    public int updateFisrtLogin(Integer userId, int fisrtLogin){
        return userInfoDAO.updateFisrtLogin(userId, fisrtLogin);
    }
    
    public UserInfo getLastModifyDateByEmail(String email){
        return userInfoDAO.getLastModifyDateByEmail(email);
    }


	@Override
	public List<UserInfoVo> getListUserofTeam(int teamId) {
		return userInfoDAO.getListUserofTeam(teamId);
	}

    
    public void updateUseStopFlg(Integer userId, Integer useStopFlg){
        userInfoDAO.updateUseStopFlg(userId, useStopFlg);
    }
    
    public UserInfo checkUserStopFlg(String email){
        return userInfoDAO.checkUserStopFlg(email);
    }

    public void registerUserMigration(UserMigration userMigration){
    	userInfoDAO.registerUserMigration(userMigration);
    }

	@Override
	public List<UserInfoVo> getUserInArrUserId(List<Integer> lstUserId) {
		return userInfoDAO.getUserInArrUserId(lstUserId);
	}


	@Override
	public List<UserInfoVo> searchUserOfMyCompany(String criteriaSearch, int limit, int offet, int groupCompanyInfoId) {
		 return userInfoDAO.searchUserOfMyCompany(criteriaSearch, limit, offet,groupCompanyInfoId);
	}

	@Override
	public BigInteger countUser(String criteriaSearch, int groupCompanyInfoId) {
		return userInfoDAO.countUser(criteriaSearch,groupCompanyInfoId);
	}

        
    public int getUserIdByUserIndexNo(String userIndexNo){
        return userInfoDAO.getUserIdByUserIndexNo(userIndexNo);
    }
    
    public boolean checkUserMigration(String sansanId){
    	return userInfoDAO.checkUserMigration(sansanId);
    }

	@Override
	public List<DownloadCsv> getAllHistoryDownloadByCompany(List<Integer> userId) {
		return userInfoDAO.getAllHistoryDownloadByCompany(userId);
	}

	@Override
	public List<String> getListEmail(List<Integer> listId,int sendType) {
		return userInfoDAO.getListEmail(listId,sendType);
	}

	public void updateTeamDivideCnt(List<Integer> listUser){
		userInfoDAO.updateTeamDivideCnt(listUser);
	}
    
    public void saveActionLog(ActionLog actionLog){
        userInfoDAO.saveActionLog(actionLog);
    }

    public List<UserInfoVo> getListUserAllTeam(){
		return userInfoDAO.getListUserAllTeam();
	}
    
    public boolean checkUseDateEndDate(String email){
    	return userInfoDAO.checkUseDateEndDate(email);
    }
    public List<UserInfoVo> searchUserForList(String criteriaSearch, int limit, int offet){
    	return userInfoDAO.searchUserForList(criteriaSearch, limit, offet);
    }
    public BigInteger countUserForList(String criteriaSearch){
    	return userInfoDAO.countUserForList(criteriaSearch);
    }
    public List<UserInfoVo> searchUserOfMyCompanyForList(String criteriaSearch, int limit, int offet, int groupCompanyInfoId){
    	return userInfoDAO.searchUserOfMyCompanyForList(criteriaSearch, limit, offet, groupCompanyInfoId);
    }
    public BigInteger countUserForList(String criteriaSearch, int groupCompanyInfoId){
    	return userInfoDAO.countUserForList(criteriaSearch, groupCompanyInfoId);
    }
    
    public List<UserInfo> getAllListUser(){
    	return userInfoDAO.getAllListUser();
    }

	@Override
	public List<UserInfoVo> getAllUserOfCompany(int groupCompanyInfoId) {
		// TODO Auto-generated method stub
		return userInfoDAO.getAllUserOfCompany(groupCompanyInfoId);
	}

	@Override
	public MailDomainList getDomainUser(String domain) {
		// TODO Auto-generated method stub
		return userInfoDAO.getDomainUser(domain);
	}
public List<ActionLogDownloadVo> getListActionLog(){
    	return userInfoDAO.getListActionLog();
    }
    
    public List<CardUpdateHistoryDownloadVo> getListCardUpdateHistory(){
    	return userInfoDAO.getListCardUpdateHistory();
    }

}
