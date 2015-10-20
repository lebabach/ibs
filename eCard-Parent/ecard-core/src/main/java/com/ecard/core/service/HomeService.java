/*
 * HomeService
 */
package com.ecard.core.service;

import com.ecard.core.vo.CardInfoName;
import java.math.BigInteger;

/**
 *
 * @author vinhla
 */
public interface HomeService {
    public CardInfoName getMyCardInfo(Integer userId);
    
    public BigInteger countRecentCard(Integer userId);
    
    public Long countPossessionCard(Integer userId);
    
    public Long countInputWaitCard(Integer userId);
    
    public BigInteger countConnectCard(Integer userId);
    
    public BigInteger countRecentConnectCard(Integer userId);
    
    public BigInteger countNotificationCard(Integer userId);
    
    public BigInteger countRecentPossessionCard(Integer userId);
}
