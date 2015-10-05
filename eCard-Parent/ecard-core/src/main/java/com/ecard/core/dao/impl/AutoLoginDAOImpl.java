package com.ecard.core.dao.impl;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ecard.core.dao.AutoLoginDAO;
import com.ecard.core.model.AutoLogin;

/**
 *
 * @author vinhla
 */
@Repository("autoLoginDAO")
public class AutoLoginDAOImpl extends GenericDao implements AutoLoginDAO{

    public AutoLogin findByToken(String token) {
        Query query = getEntityManager().createQuery("SELECT u from AutoLogin AS u where u.token = :token");
        query.setParameter("token", token);
        int result = query.getResultList().size();
        if(result == 0){
            AutoLogin autoLogin = new AutoLogin();
            autoLogin.setToken(null);
            return autoLogin;
        }
            
        return (AutoLogin)query.getSingleResult();
//        return result;
    }

    public void deleteTokenByUserId(int userId){
        Query query = getEntityManager().createQuery("delete from AutoLogin AS u where u.userInfo.userId = :userId");
        query.setParameter("userId", userId);
        query.executeUpdate();
    }

    public void deleteByToken(String token) {
        Query query = getEntityManager().createQuery("delete from AutoLogin where token = :token");
        query.setParameter("token", token);
        query.executeUpdate();
    }

    public void saveUserToken(AutoLogin autoLoginToken){
    	getEntityManager().persist(autoLoginToken);
    	getEntityManager().flush();
    }
    
    public Boolean checkToken(String token){
        Query query = getEntityManager().createQuery("SELECT u from AutoLogin AS u where u.token = :token");
        query.setParameter("token", token);
        int result = query.getResultList().size();
        if(result == 0){
            return false;
        }
        return true;
    }
}
