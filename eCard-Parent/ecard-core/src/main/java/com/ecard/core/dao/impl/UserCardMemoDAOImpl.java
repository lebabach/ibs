package com.ecard.core.dao.impl;

import java.util.Date;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ecard.core.dao.OldCardDAO;
import com.ecard.core.dao.UserCardMemoDAO;
import com.ecard.core.model.enums.IndexTypeEnum;
@Repository("userCardMemoDAO")
public class UserCardMemoDAOImpl extends GenericDao  implements UserCardMemoDAO{
	
	@Override
	public boolean updateUserCardMemo(int cardId1,int userId,int cardId2){
		String sql = "UPDATE user_card_memo t SET t.card_id = :card_id2 WHERE t.card_id = :card_id1 AND t.user_id =:user_id";
		Query query = getEntityManager().createNativeQuery(sql);
		query.setParameter("card_id2", cardId2);
		query.setParameter("user_id", userId);
		query.setParameter("card_id1", cardId1);
		try{
			int result = query.executeUpdate();
			if (result == 0) {
				return false;
			} else {
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("==================== Can not update ContactHistory");
			return false;
		}
		
	}
}
