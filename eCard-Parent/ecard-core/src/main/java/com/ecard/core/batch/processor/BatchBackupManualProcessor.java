package com.ecard.core.batch.processor;

import com.ecard.core.batch.beans.CardInfoBackup;
import org.springframework.batch.item.ItemProcessor;


public class BatchBackupManualProcessor implements ItemProcessor<CardInfoBackup, CardInfoBackup>{

	
	@Override
	public CardInfoBackup process(CardInfoBackup result) throws Exception {
		System.out.println("Processing result :"+result);
		
		/*
		 * Only return results which are more than 80%
		 * 
		 */
//		if(result.getPercentage() < 80){
//			return null;
//		}
		
		return result;
	}

}
