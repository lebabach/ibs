/*
 * HomeServiceImpl
 */
package com.ecard.core.service.impl;

import com.ecard.core.dao.HomeDAO;
import com.ecard.core.service.HomeService;
import com.ecard.core.vo.CardInfoName;
import java.math.BigInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vinhla
 */
@Service("homeService")
@Transactional
public class HomeServiceImpl implements HomeService{
    @Autowired
    HomeDAO homeDAO;
    
    public CardInfoName getMyCardInfo(Integer userId) {
        return homeDAO.getMyCardInfo(userId);
    }
    
    public BigInteger countRecentCard(Integer userId) {
        return homeDAO.countRecentCard(userId);
    }
    
    public Long countPossessionCard(Integer userId) {
        return homeDAO.countPossessionCard(userId);
    }
    
    public Long countInputWaitCard(Integer userId) {
        return homeDAO.countInputWaitCard(userId);
    }
    
    public BigInteger countConnectCard(Integer userId) {
        return homeDAO.countConnectCard(userId);
    }
    
    public BigInteger countRecentConnectCard(Integer userId) {
        return homeDAO.countRecentConnectCard(userId);
    }
    
    public BigInteger countNotificationCard(Integer userId){
        return homeDAO.countNotificationCard(userId);
    }
    
    public BigInteger countRecentPossessionCard(Integer userId){
        return homeDAO.countRecentPossessionCard(userId);
    }
}
