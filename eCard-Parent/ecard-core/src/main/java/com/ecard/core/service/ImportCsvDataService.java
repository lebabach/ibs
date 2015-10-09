/**
 * 
 */
package com.ecard.core.service;

import java.util.List;

import com.ecard.core.model.CardInfo;
import com.ecard.core.model.UserInfo;

/**
 * @author nhat.nguyen
 *
 */
public interface ImportCsvDataService {
	
	public int importListUserInfo(List<UserInfo> userInfoList,List<String> sansanIdList);
	
	public int importListOperatorInfo(List<UserInfo> userInfoList);
	
	public List<CardInfo> importListCardInfoFromCsv(List<CardInfo> cardInfoList);
}
