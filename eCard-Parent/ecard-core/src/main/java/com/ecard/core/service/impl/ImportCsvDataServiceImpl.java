/**
 * 
 */
package com.ecard.core.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ecard.core.dao.ImportCsvDataDAO;
import com.ecard.core.model.CardInfo;
import com.ecard.core.model.UserInfo;
import com.ecard.core.service.ImportCsvDataService;

/**
 * @author nhat.nguyen
 *
 */
@Service("importCsvDataService")
public class ImportCsvDataServiceImpl implements ImportCsvDataService {
	
	private final int MAX_BATCH_SIZE = 100;

	@Value("${csv.import.batchSize}")
	private String importCsvBatchSize;
	
	@Autowired
	ImportCsvDataDAO importCsvDataDAO;	
	
	/* 
	 * Import list data from CSv file with pagination
	 * @author nhat.nguyen
	 */
	@Override
	public List<CardInfo> importListCardInfoFromCsv(List<CardInfo> cardInfoList) {

		int batchSize = Integer.parseInt(this.importCsvBatchSize);
		// to synchronize data with database and prevent OutOfMemory
		// the batch size should less than or equals 100
		if (batchSize > MAX_BATCH_SIZE){
			batchSize = MAX_BATCH_SIZE;
		}
		
		int totalRecords = cardInfoList.size();
	    int startIndex = 0;
	    int lastIndex;
	    List<CardInfo> importSuccessList = new ArrayList<CardInfo>();
	    if (batchSize > 1){	    	
		    // import data with batchSize record for one time
		    for (int i = 0; i < ((float) totalRecords / batchSize); i++) {
				startIndex = i * batchSize;
				lastIndex = (i + 1) * batchSize;
				if (lastIndex > totalRecords) {
				    lastIndex = totalRecords;
				}
				
				List<CardInfo> subList = cardInfoList.subList(startIndex, lastIndex);
				try {
					importCsvDataDAO.importListCardInfoFromCsv(subList);
					importSuccessList.addAll(subList);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		    }
	    } else {
	    	for (CardInfo cardInfo : cardInfoList) {
	    		try {
	    			List<CardInfo> subList = Arrays.asList(cardInfo);
					importCsvDataDAO.importListCardInfoFromCsv(subList);
					importSuccessList.addAll(subList);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
	    }
		return importSuccessList;
	}

	@Override
	public int importListUserInfo(List<UserInfo> userInfoList, List<String> sansanIdList) {
		int batchSize = Integer.parseInt(this.importCsvBatchSize);
		// to synchronize data with database and prevent OutOfMemory
		// the batch size should less than or equals 100
		if (batchSize > MAX_BATCH_SIZE){
			batchSize = MAX_BATCH_SIZE;
		}
				
		int totalRecords = userInfoList.size();
	    int startIndex = 0;
	    int lastIndex;
	    int successCount = 0;
	    
	    if (batchSize > 1){	
		    // import data with batchSize record for one time
		    for (int i = 0; i < ((float) totalRecords / batchSize); i++) {
				startIndex = i * batchSize;
				lastIndex = (i + 1) * batchSize;
				if (lastIndex > totalRecords) {
				    lastIndex = totalRecords;
				}
				
				List<UserInfo> subUserInfoList = userInfoList.subList(startIndex, lastIndex);
				List<String> subSanSanIdList = sansanIdList.subList(startIndex, lastIndex);
				try {
					int subSuccessCount = importCsvDataDAO.bulkInsertUserInfo(subUserInfoList, subSanSanIdList);
					successCount += subSuccessCount;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		    }
	    } else {
	    	for (int i = 0; i < totalRecords; i++) {
	    		try {
					int subSuccessCount = importCsvDataDAO.bulkInsertUserInfo(Arrays.asList(userInfoList.get(i)), Arrays.asList(sansanIdList.get(i)));
					successCount += subSuccessCount;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
	    	}
	    }
		return successCount;
	}
	
	@Override
	public int importListOperatorInfo(List<UserInfo> userInfoList) {
		int batchSize = Integer.parseInt(this.importCsvBatchSize);
		// to synchronize data with database and prevent OutOfMemory
		// the batch size should less than or equals 100
		if (batchSize > MAX_BATCH_SIZE){
			batchSize = MAX_BATCH_SIZE;
		}
				
		int totalRecords = userInfoList.size();
	    int startIndex = 0;
	    int lastIndex;
	    int successCount = 0;
	    
	    if (batchSize > 1){	
		    // import data with batchSize record for one time
		    for (int i = 0; i < ((float) totalRecords / batchSize); i++) {
				startIndex = i * batchSize;
				lastIndex = (i + 1) * batchSize;
				if (lastIndex > totalRecords) {
				    lastIndex = totalRecords;
				}
				
				List<UserInfo> subUserInfoList = userInfoList.subList(startIndex, lastIndex);				
				try {
					int subSuccessCount = importCsvDataDAO.bulkInsertOperatorInfo(subUserInfoList);
					successCount += subSuccessCount;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		    }
	    } else {
	    	for (int i = 0; i < totalRecords; i++) {
	    		try {
					int subSuccessCount = importCsvDataDAO.bulkInsertOperatorInfo(Arrays.asList(userInfoList.get(i)));
					successCount += subSuccessCount;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
	    	}
	    }
		return successCount;
	}
}
