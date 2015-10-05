package com.ecard.core.dao;

import com.ecard.core.model.AutoLogin;

/**
 *
 * @author vinhla
 */
public interface AutoLoginDAO {
    AutoLogin findByToken(String token);
    
    void deleteTokenByUserId(int userId);
            
    void deleteByToken(String token);

    public void saveUserToken(AutoLogin autoLoginToken);
    
    public Boolean checkToken(String token);
}
