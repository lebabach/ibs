/*
 * BatchCollectNameTasklet
 */
package com.ecard.core.batch.tasklet;

import com.ecard.core.batch.beans.CardInfo;
import com.ecard.core.batch.beans.CardInfoCount;
import com.ecard.core.batch.beans.Roles;
import com.ecard.core.batch.beans.mapper.CardInfoCountMapper;
import com.ecard.core.batch.beans.mapper.CardInfoMapper;
import com.ecard.core.batch.beans.mapper.RolesMapper;
import com.ecard.core.batch.email.service.SendMailService;
import com.ecard.core.model.UserInfo;
import com.ecard.core.model.UserNotification;
import com.ecard.core.model.enums.NoticeType;
import com.ecard.core.model.enums.PermissionType;
import com.ecard.core.service.UserInfoService;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author vinhla
 */
public class BatchCollectNameTasklet implements Tasklet {
    private static final Logger logger = LoggerFactory.getLogger(BatchCollectNameTasklet.class);
    
    private DataSource dataSource;
    
    public DataSource getDataSource() {
        return dataSource;
    }
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    @Autowired
	UserInfoService userInfoService;
    
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        logger.debug("execute method", BatchCollectNameTasklet.class);
        
        String[] configMail = {"classpath:batch/spring-mail.xml"};
        ApplicationContext context = new ClassPathXmlApplicationContext(configMail);
    	 
    	SendMailService sendMailService = (SendMailService) context.getBean("sendMailService");
        
        String sql = "SELECT c.card_id, c.name, c.company_name, c.position_name, c.department_name, c.tel_number_company, c.address_1, c.email, u.user_id FROM card_info c " 
                   + " INNER JOIN possession_card po ON c.card_id = po.card_id LEFT JOIN user_info u ON po.user_id = u.user_id " 
                   + " WHERE c.email IN ( SELECT ca.email FROM card_info ca GROUP BY ca.email HAVING COUNT(ca.card_id) > 1) " 
                   + " AND c.approval_status = 1";
        
        List<CardInfo> cardList = new ArrayList<CardInfo>();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        cardList = jdbcTemplate.query(sql, new CardInfoMapper());
            
        Set<String> nameListDuplicates = new HashSet<String>();
        Set<String> companyNameListDuplicates = new HashSet<String>();
        Set<String> departmentNameListDuplicates = new HashSet<String>();
        Set<String> positionNameListDuplicates = new HashSet<String>();
        Set<String> telNumberPhoneListDuplicates = new HashSet<String>();
        Set<String> address1ListDuplicates = new HashSet<String>();
        Set<String> emailListDuplicates = new HashSet<String>();
        
        try{
            String emailSupervisor = getEmailRoleSupervisorByUserId();
            Long count = countDuplicateRow();
            List<UserInfo> users= userInfoService.getAllUserInfo();
            String msg="The problem had of duplicate names. Please handle with function name colect";
            for(int i = 0; i < cardList.size(); i++) {
                String name = cardList.get(i).getName();
                String companyName = cardList.get(i).getCompanyName();
                String departmentName = cardList.get(i).getDepartmentName();
                String positionName = cardList.get(i).getPositionName();
                String telNumberPhone = cardList.get(i).getTelNumberCompany();
                String address1 = cardList.get(i).getAddress1();
                String email = cardList.get(i).getEmail();
                Integer userId=cardList.get(i).getUserId();
                if(email == null)
                    continue;

                if (!nameListDuplicates.add(name)) {
//                    updateNotifyType(users.stream().filter(user->user.getUserId().intValue()==userId.intValue()).findFirst().get()
//                    		, 1, cardList.get(i).getCardId(), 1, 0, msg);
//                    
//                    sendMailService.sendMail(email, "Mr/Mrs", name + " was changed his office.");
//                    sendMailService.sendMail(emailSupervisor, "Mr/Mrs", msg);
                }
                
                if(!nameListDuplicates.add(name) && !companyNameListDuplicates.add(companyName)){
//                    updateNotifyType(users.stream().filter(user->user.getUserId().intValue()==userId.intValue()).findFirst().get()
//                    		, 1, cardList.get(i).getCardId(), 1, 0, msg);
//                    
//                    //Send mail
//                    sendMailService.sendMail(email, "Mr/Mrs", name + " was changed his office.");
//                    sendMailService.sendMail(emailSupervisor, "Mr/Mrs", msg);
                }
                
                if(!nameListDuplicates.add(name) && !companyNameListDuplicates.add(companyName) && !departmentNameListDuplicates.add(departmentName)){
//                    updateNotifyType(users.stream().filter(user->user.getUserId().intValue()==userId.intValue()).findFirst().get()
//                    		, 1, cardList.get(i).getCardId(), 1, 0, msg);
//                    
//                    //Send mail
//                    sendMailService.sendMail(email, "Mr/Mrs", name + " was changed his office.");
//                    sendMailService.sendMail(emailSupervisor, "Mr/Mrs", msg);
                }
                
                if(!nameListDuplicates.add(name) && !companyNameListDuplicates.add(companyName) && !departmentNameListDuplicates.add(departmentName) 
                        && !positionNameListDuplicates.add(positionName)){
//                    updateNotifyType(users.stream().filter(user->user.getUserId().intValue()==userId.intValue()).findFirst().get()
//                    		, 1, cardList.get(i).getCardId(), 1, 0, msg);
//                    
//                    //Send mail
//                    sendMailService.sendMail(email, "Mr/Mrs", name + " was changed his office.");
//                    sendMailService.sendMail(emailSupervisor, "Mr/Mrs", msg);
                }
                
                if(!nameListDuplicates.add(name) && !companyNameListDuplicates.add(companyName) && !departmentNameListDuplicates.add(departmentName) 
                        && !positionNameListDuplicates.add(positionName) && !telNumberPhoneListDuplicates.add(telNumberPhone)){
//                    updateNotifyType(users.stream().filter(user->user.getUserId().intValue()==userId.intValue()).findFirst().get()
//                    		, 1, cardList.get(i).getCardId(), 1, 0, msg);
//                    
//                    //Send mail
//                    sendMailService.sendMail(email, "Mr/Mrs", name + " was changed his office.");
//                    sendMailService.sendMail(emailSupervisor, "Mr/Mrs", msg);
                }
                
                if(!nameListDuplicates.add(name) && !companyNameListDuplicates.add(companyName) && !departmentNameListDuplicates.add(departmentName) 
                        && !positionNameListDuplicates.add(positionName) && !telNumberPhoneListDuplicates.add(telNumberPhone) && !address1ListDuplicates.add(address1)){
                    updateNotifyType(users.stream().filter(user->user.getUserId().intValue()==userId.intValue()).findFirst().get()
                    		, 1, cardList.get(i).getCardId(), 1, 0, msg);
                    
                    //Send mail
                    sendMailService.sendMail(email, "Mr/Mrs", name + " was changed his office.");
                    sendMailService.sendMail(emailSupervisor, "Mr/Mrs", msg);
                }
                
                if(!nameListDuplicates.add(name) && !companyNameListDuplicates.add(companyName) && !departmentNameListDuplicates.add(departmentName)
                        && !positionNameListDuplicates.add(positionName) && !telNumberPhoneListDuplicates.add(telNumberPhone) && !address1ListDuplicates.add(address1)
                        && !emailListDuplicates.add(email)){
                    if(count > 1){
                        //Delete card duplicate
                        deleteDuplicateCardData(cardList.get(i).getCardId());
                    }
                    else{
                        break;
                    }
                }
            }
        }
        catch(Exception ex){
            logger.debug("Exception : ", ex.getMessage());
        }
        return RepeatStatus.FINISHED;

    }
    
    private void updateNotifyType(Integer userId){
        /*String sqlStr = "UPDATE user_notification u SET u.notice_type = " + NoticeType.REGISTRATION_CARD_CHANGE.getValue() 
                    + " WHERE u.user_id = " + userId;
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        jdbcTemplate.execute(sqlStr);*/
    	
    }
    
    private String getEmailRoleSupervisorByUserId(){
        String sqlStr = "SELECT u.email, r.role_name FROM user_info u INNER JOIN roles r ON u.role_id = r.role_id";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        List<Roles> roleList = new ArrayList<Roles>();
        roleList = jdbcTemplate.query(sqlStr, new RolesMapper());
        String emailSupervisor = "";
        for(Roles role : roleList){
            if(PermissionType.ROLE_SUPERVISOR.name().equals(role.getRoleName())){
               emailSupervisor = role.getEmail();
               break;
            }
        }
        return emailSupervisor;
    }
    
    private void deleteDuplicateCardData(Integer cardId){
        String sqlStr = "DELETE FROM card_info WHERE card_id = " + cardId;
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        jdbcTemplate.execute(sqlStr);
    }

    private Long countDuplicateRow(){
        String sqlStr = "SELECT COUNT(*) as cnt FROM card_info c \n" +
                        "INNER JOIN possession_card po ON c.card_id = po.card_id LEFT JOIN user_info u ON po.user_id = u.user_id \n" +
                        "WHERE c.email IN ( SELECT ca.email FROM card_info ca GROUP BY ca.email HAVING COUNT(ca.card_id) > 1 ) \n" +
                        "AND c.approval_status = 1";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        List<CardInfoCount> cardCntList = new ArrayList<CardInfoCount>();
        cardCntList = jdbcTemplate.query(sqlStr, new CardInfoCountMapper());
        
        return cardCntList.get(0).getCardCnt();
    }
    
    private void updateNotifyType(UserInfo user, Integer noticeType, Integer cardId, Integer changeParamType,Integer readFlg,String msg){
             	//
    	if(user!=null){
    		UserNotification userNotification = new UserNotification();
        	userNotification.setCardId(cardId);
        	userNotification.setChangeParamType(changeParamType);
        	userNotification.setUserInfo(user);
        	userNotification.setNoticeDate(new Date());
        	userNotification.setNoticeType(noticeType);
        	userNotification.setNotifyMessage(msg);
        	userNotification.setReadFlg(readFlg);
        	userInfoService.saveHistoryNotification(userNotification);
    	}
    	
    }
}
