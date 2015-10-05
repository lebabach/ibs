/*
 * HomeDAO class
 */
package com.ecard.core.dao;

import com.ecard.core.vo.CardInfoName;
import java.math.BigInteger;

/**
 *
 * @author vinhla
 */
public interface HomeDAO {
    
    public CardInfoName getMyCardInfo(Integer userId);
    
    public BigInteger countRecentCard(Integer userId);
    
    public Long countPossessionCard(Integer userId);
    
    public Long countInputWaitCard(Integer userId);
    
    public BigInteger countConnectCard(Integer userId, Integer groupCompanyId);
    
    public BigInteger countRecentConnectCard(Integer userId, Integer groupCompanyId);
    
    public BigInteger countNotificationCard(Integer userId);
    
    public BigInteger countRecentPossessionCard(Integer userId);
}
