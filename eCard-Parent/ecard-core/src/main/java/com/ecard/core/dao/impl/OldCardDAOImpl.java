package com.ecard.core.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ecard.core.dao.OldCardDAO;
import com.ecard.core.model.OldCard;

@Repository("oldCardDAO")
public class OldCardDAOImpl  extends GenericDao  implements OldCardDAO{
	@Override
	public boolean updateCardIdWithOldCard(int cardId,int oldcard){
		String sql = "UPDATE old_card SET card_id = :card_id WHERE card_id = :oldcard";
		Query query = getEntityManager().createNativeQuery(sql);
		query.setParameter("oldcard", oldcard);
		query.setParameter("card_id", cardId);
		try{
			int result = query.executeUpdate();
			if (result == 0) {
				return false;
			} else {
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("==================== Can not update updateCardIdWithOldCard");
			return false;
		}
		
	}

}
