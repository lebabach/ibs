package com.ecard.core.service;

import com.ecard.core.model.ActionLog;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import com.ecard.core.model.AutoLogin;
import com.ecard.core.model.DownloadCsv;
import com.ecard.core.model.MailDomainList;
import com.ecard.core.model.PushInfoId;
import com.ecard.core.model.Roles;
import com.ecard.core.model.UserInfo;
import com.ecard.core.model.UserMigration;
import com.ecard.core.model.UserNotification;
import com.ecard.core.vo.UserDownloadPermission;
import com.ecard.core.vo.UserInfoVo;
import com.ecard.core.vo.UserListContact;
import com.ecard.core.vo.UserProfile;

/**
 *
 * @author vinhla
 */
public interface UserInfoService {
	public List<UserInfo> getAllUserInfo();

	public UserInfo checkUserLogin(String email, String password);

	public UserInfo findUserByEmail(String email);

	AutoLogin findByToken(String token);

	void removeUserToken(String token);

	AutoLogin createUserToken(int userId, String token);

	public Roles findRoleByUserId(Integer userId);

	public List<Integer> getPermisionTypeByUserId(Integer userId);

	public int registerProfile(UserInfo userInfo);
	
	public int updateProfileAdmin(UserInfo userInfo);

	public int registerProfileAdmin(UserInfo userInfo);
	
	public int registerCoverImage(String coverImage, Integer userId);

	public String getCoverImage(Integer userId);

	public Boolean checkToken(String token);

	public UserInfo getUserInfoByUserId(Integer userId);

	public Boolean checkPassword(Integer userId, String password);

	public Boolean updatePassword(Integer userId, String password);

	public Boolean updateEmail(Integer userId, String email);

	public List<UserListContact> getUserListContact(Integer cardId);

	public List<UserInfoVo> searchUser(String criteriaSearch, int limit, int offet);
	
	public BigInteger countUser(String criteriaSearch);

	public List<UserListContact> getUserListContactByDate(Integer cardId, Date fromDate, Date toDate);
	
	public void deleteUser(int userId);
	
	public List<UserInfo> getUserInfoNotInAnyTeam();

	public boolean updateTeamId(int userId, int teamID);

	public Boolean checkEmailExist(String email);
        
    public UserProfile getMailSettingInfo(Integer userId);
    
    public String getPasswordByUserId(Integer userId);
    
    public void userLeave(int userId);
    
    public UserDownloadPermission getPermisionDownloadByUserId(Integer userId);
    
    public List<DownloadCsv> getHistoryCSVDownload(Integer userId);
    
    public List<UserInfoVo> getAllUserSearchInfo();
    
    public PushInfoId getPushNotification(Integer userId);

    boolean updateTeamId(int userId, Integer teamID);

    public void saveHistoryNotification(UserNotification userNotifcation);	
    
    public List<DownloadCsv> getAllHistoryDownload();

    public boolean updateRecognitionDownload(Integer csvId, int csvStatus, int operaterId);
    
    public int updateFisrtLogin(Integer userId, int fisrtLogin);
    
    public UserInfo getLastModifyDateByEmail(String email);

    public List<UserInfoVo> getListUserofTeam(int teamId);

    public void updateUseStopFlg(Integer userId, Integer useStopFlg);
    
    public UserInfo checkUserStopFlg(String email);

    public void registerUserMigration(UserMigration userMigration);
    public List<UserInfoVo> getUserInArrUserId(List<Integer>lstUserId);

    public List<UserInfoVo> searchUserOfMyCompany(String criteriaSearch, int limit, int offet,int groupCompanyInfoId);
    
    public BigInteger countUser(String criteriaSearch,int groupCompanyInfoId);
    
    public int getUserIdByUserIndexNo(String userIndexNo);
    
    public boolean checkUserMigration(String sansanId);
    
    public List<DownloadCsv> getAllHistoryDownloadByCompany(List<Integer> userId);
    
    public int updateProfileAdminAllocation(UserInfo userInfo);
    public List<String> getListEmail(List<Integer> listId,int sendType);
    
    public void updateTeamDivideCnt(List<Integer> listUser);
    
    public void saveActionLog(ActionLog actionLog);

    public List<UserInfoVo> getListUserAllTeam();
    
    public boolean checkUseDateEndDate(String email);
    
    public List<UserInfoVo> searchUserForList(String criteriaSearch, int limit, int offet);
    public BigInteger countUserForList(String criteriaSearch);
    public List<UserInfoVo> searchUserOfMyCompanyForList(String criteriaSearch, int limit, int offet, int groupCompanyInfoId);
    public BigInteger countUserForList(String criteriaSearch, int groupCompanyInfoId);

    public List<UserInfo> getAllListUser();

    public List<UserInfoVo> getAllUserOfCompany(int groupCompanyInfoId);
    public MailDomainList getDomainUser(String domain);

}
