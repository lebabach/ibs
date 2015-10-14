package com.ecard.core.dao;

import com.ecard.core.model.ActionLog;
import com.ecard.core.model.DownloadCsv;
import com.ecard.core.model.PushInfoId;
import com.ecard.core.model.Roles;
import java.util.List;

import com.ecard.core.model.UserInfo;
import com.ecard.core.model.UserMigration;
import com.ecard.core.model.UserNotification;
import com.ecard.core.vo.UserDownloadPermission;
import com.ecard.core.vo.UserInfoVo;
import com.ecard.core.vo.UserListContact;
import com.ecard.core.vo.UserProfile;

import java.math.BigInteger;
import java.util.Date;

public interface UserInfoDAO extends IGenericDao{
	
    List<UserInfo> findAllUsers();
	
    public UserInfo checkUserLogin(String email, String password); 
        
    public UserInfo findByEmail(String email);
    
    public UserInfo findByUserId(int userId);
    
    public Roles findRoleByUserId(Integer userId);
    
    public List<Integer> getPermisionTypeByUserId(Integer userId);
	
    public int registerProfile(UserInfo userInfo);
    
    public int updateProfileAdmin(UserInfo userInfo);
    
    public int registerProfileAdmin(UserInfo userInfo);
    
    public Boolean checkEmailExist(String email);
    
    public int registerCoverImage(String coverImage, Integer userId);
    
    public String getCoverImage(Integer userId);
    
    public UserInfo getUserInfoByUserId(Integer userId);
    
    public Boolean checkPassword(Integer userId, String password);
    
    public Boolean updatePassword(Integer userId, String password);
    
    public Boolean updateEmail(Integer userId, String email);
    
    public List<UserListContact> getUserListContact(Integer cardId);
    
    public List<UserInfoVo> searchUser(String criteriaSearch, int limit, int offet);

    public List<UserListContact> getUserListContactByDate(Integer cardId, Date fromDate, Date toDate);
    
    public void deleteUser(int userId);
    
    public List<UserInfo> getUserInfoNotInAnyTeam();

	public Boolean updateTeamId(Integer userId, Integer teamId);
	
	public Integer removeTeamInfoByTeamId(Integer teamId);
	
	public UserProfile getMailSettingInfo(Integer userId);
    
    public String getPasswordByUserId(Integer userId);
    
    public void userLeave(int userId);
    
    public BigInteger countUser(String criteriaSearch);

    public UserDownloadPermission getPermisionDownloadByUserId(Integer userId);	
    
    public List<DownloadCsv> getHistoryCSVDownload(Integer userId);
    
    public List<UserInfoVo> getAllUserSearchInfo();
    
    public PushInfoId getPushNotification(Integer userId);
    
    public void saveHistoryNotification(UserNotification userNotifcation);
    
    public List<DownloadCsv> getAllHistoryDownload();
    
    public boolean updateRecognitionDownload(Integer csvId, int csvStatus, int operaterId);
    
    public int updateFisrtLogin(Integer userId, int fisrtLogin);
    
    public UserInfo getLastModifyDateByEmail(String email);

    public List<UserInfoVo> getListUserofTeam(int teamId) ;

    
    public void updateUseStopFlg(Integer userId, Integer useStopFlg);
    
    public UserInfo checkUserStopFlg(String email);
    
    public void registerUserMigration(UserMigration userMigration);
    public List<UserInfoVo> getUserInArrUserId(List<Integer> lstUserId);

    public List<UserInfoVo> searchUserOfMyCompany(String criteriaSearch, int limit, int offet, int groupCompanyInfoId);
    public BigInteger countUser(String criteriaSearch, int groupCompanyInfoId) ;

    
    public int getUserIdByUserIndexNo(String userIndexNo);
    
    public boolean checkUserMigration(String sansanId);
    public List<DownloadCsv> getAllHistoryDownloadByCompany(List<Integer> userId);
    public List<String> getListEmail(List<Integer> listId,int sendType);
    public void updateTeamDivideCnt(List<Integer> listUser);
    public List<UserInfoVo> getListUserAllTeam();
    public void saveActionLog(ActionLog actionLog);
    
    public boolean checkUseDateEndDate(String email);
    public List<UserInfoVo> searchUserForList(String criteriaSearch, int limit, int offet);
    public BigInteger countUserForList(String criteriaSearch);
    public List<UserInfoVo> searchUserOfMyCompanyForList(String criteriaSearch, int limit, int offet, int groupCompanyInfoId);
    public BigInteger countUserForList(String criteriaSearch, int groupCompanyInfoId);
    public List<UserInfoVo> getAllUserOfCompany(int groupCompanyInfoId);
 }
