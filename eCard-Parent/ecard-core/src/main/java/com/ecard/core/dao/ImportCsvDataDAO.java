/**
 * 
 */
package com.ecard.core.dao;

import java.util.List;

import com.ecard.core.model.CardInfo;
import com.ecard.core.model.UserInfo;

/**
 * @author nhat.nguyen
 *
 */
public interface ImportCsvDataDAO {
	
	public void importListCardInfoFromCsv(List<CardInfo> cardInfoList);

	public int bulkInsertUserInfo(List<UserInfo> subUserInfoList, List<String> subSanSanIdList);
}
