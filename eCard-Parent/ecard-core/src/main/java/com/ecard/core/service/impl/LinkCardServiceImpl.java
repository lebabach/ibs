package com.ecard.core.service.impl;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecard.core.dao.LinkCardDAO;
import com.ecard.core.model.UserInfo;
import com.ecard.core.service.LinkCardService;

@Service("linkCardService")
@Transactional
public class LinkCardServiceImpl implements LinkCardService {
	private static final Logger logger = LoggerFactory.getLogger(LinkCardServiceImpl.class);
	
	@Autowired
	LinkCardDAO linkCardDAO;
	
	/*private final int MAX_BATCH_SIZE = 100;

	@Value("${csv.import.batchSize}")
	private String importCsvBatchSize;
	
	public int saveLinkCard(List<UserInfo> userInfoList){
		int batchSize = Integer.parseInt(this.importCsvBatchSize);
		// to synchronize data with database and prevent OutOfMemory
		// the batch size should less than or equals 100
		if (batchSize > MAX_BATCH_SIZE){
			batchSize = MAX_BATCH_SIZE;
		}
		
		int result = 0;
		int totalRecords = userInfoList.size();
	    int startIndex = 0;
	    int lastIndex;
	    
	    if (batchSize > 1){
	    	// import data with batchSize record for one time
		    for (int i = 0; i < ((float) totalRecords / batchSize); i++) {
		    	startIndex = i * batchSize;
				lastIndex = (i + 1) * batchSize;
				if (lastIndex > totalRecords) {
				    lastIndex = totalRecords;
				}
				
				List<UserInfo> subList = userInfoList.subList(startIndex, lastIndex);
				try{
					result = linkCardDAO.saveLinkCard(subList);
				}
				catch(Exception ex){
					logger.debug("Exception : "+ex.getMessage(), LinkCardServiceImpl.class);
				}
		    }
	    }
	    else{
	    	for(UserInfo userInfo : userInfoList){
	    		try{
	    			List<UserInfo> subList = Arrays.asList(userInfo);
	    			result = linkCardDAO.saveLinkCard(subList);
	    		}
	    		catch(Exception ex){
					logger.debug("Exception : "+ex.getMessage(), LinkCardServiceImpl.class);
				}
	    	}
	    }
		return result;
	}*/

	public int saveLinkCard(List<UserInfo> userInfoList){
		return linkCardDAO.saveLinkCard(userInfoList);
	}
	
	public int cleanLinkCardData(){
		return linkCardDAO.cleanLinkCardData();
	}
}
