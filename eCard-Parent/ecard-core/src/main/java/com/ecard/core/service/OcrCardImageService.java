/**
 * 
 */
package com.ecard.core.service;

import com.ecard.core.model.CardInfo;
import com.ecard.core.webservice.CardDetail;
import com.ecard.core.webservice.Status;

/**
 * @author nhat.nguyen
 *
 */
public interface OcrCardImageService {
	
	CardDetail getCardImageDetail(Integer cardId, String base64CardData);
	
	Status processCardImageDetail(Integer userId, Integer cardId, String base64CardData,CardInfo card);
	Status processCardImageDetail(Integer userId, Integer cardId, String base64CardData);

}
