/**
 * 
 */
package com.ecard.core.service.impl;

import java.rmi.RemoteException;
import java.text.Normalizer;
import java.util.Date;

import org.apache.axis2.AxisFault;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecard.core.dao.CardInfoDAO;
import com.ecard.core.dao.UserNotifyDAO;
import com.ecard.core.datasource.SchemaContextHolder;
import com.ecard.core.datasource.type.SchemaType;
import com.ecard.core.model.CardInfo;
import com.ecard.core.model.CompanyInfo;
import com.ecard.core.model.UserInfo;
import com.ecard.core.service.OcrCardImageService;
import com.ecard.core.service.UserInfoService;
import com.ecard.core.util.StringUtilsHelper;
import com.ecard.core.webservice.CardDetail;
import com.ecard.core.webservice.CardInfoData;
import com.ecard.core.webservice.GetCardImageDetail;
import com.ecard.core.webservice.GetCardImageDetailResponse;
import com.ecard.core.webservice.OcrWebServiceStub;
import com.ecard.core.webservice.Status;
import java.nio.charset.Charset;
import javax.ws.rs.HttpMethod;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * @author nhat.nguyen
 *
 */
@Service("cardImageService")
@Transactional
public class OcrCardImageServiceImpl implements OcrCardImageService {
	
	@Value("${ocr.endpoint}")
	private String ocrServiceTargetEndpoint;
	
	@Autowired
	CardInfoDAO cardInfoDAO;
	
	@Autowired
	UserNotifyDAO userNotifyDAO;
	
	@Autowired
    UserInfoService userInfoService;

	@Override
	public CardDetail getCardImageDetail(Integer cardId, String base64CardData) {
		GetCardImageDetail cardImage = new GetCardImageDetail();
		cardImage.setCardID(String.valueOf(cardId));
		cardImage.setBase64(base64CardData);

		CardDetail cardDetail = null;
		try {
			OcrWebServiceStub service = new OcrWebServiceStub(this.ocrServiceTargetEndpoint);
			GetCardImageDetailResponse response = service.getCardImageDetail(cardImage);
			cardDetail = response.getGetCardImageDetailResult();

		} catch (AxisFault e) {
			cardDetail = errorReadCardFromOcr(e);
		} catch (RemoteException e) {
			cardDetail = errorReadCardFromOcr(e);
		}
		return cardDetail;
	}

	@Override
	public Status processCardImageDetail(Integer userId, Integer cardId, String base64CardData, CardInfo card) {
		GetCardImageDetail cardImage = new GetCardImageDetail();
		cardImage.setCardID(String.valueOf(cardId));
		cardImage.setBase64(base64CardData);

		CardDetail cardDetail = null;
		Status status = null;
		try {
			SchemaContextHolder.setSchemaType(SchemaType.USER);

			OcrWebServiceStub service = new OcrWebServiceStub(this.ocrServiceTargetEndpoint);
			GetCardImageDetailResponse response = service.getCardImageDetail(cardImage);
			cardDetail = response.getGetCardImageDetailResult();

			if (cardDetail != null) {
				if (cardDetail.getStatus() != null && cardDetail.getStatus().getCode() == 0) {
					CardInfoData cardInfoData = cardDetail.getCardInfoData();
					if (cardInfoData != null) {
						// Update card information
						CardInfo cardInfo = convertCardInfo(cardInfoData, card);
						cardInfo.setCardId(cardId);	
						cardInfo.setGroupCompanyId(card.getGroupCompanyId());
						String lastNameKana = cardInfo.getLastNameKana();
						String fisrtNameKana = cardInfo.getFirstNameKana();
						String companyNameKana = cardInfo.getCompanyNameKana();
						cardInfo.setLastNameKana(lastNameKana);
						cardInfo.setFirstNameKana(fisrtNameKana);
						cardInfo.setCompanyNameKana(companyNameKana);
						cardInfo.setCreateDate(new Date());
						cardInfo.setUpdateDate(new Date());
						cardInfo.setContactDate(new Date());
						cardInfo.setNameKana(StringUtilsHelper.mergerStringEitherAWord(lastNameKana, fisrtNameKana, " "));
						cardInfo.setAddressFull(StringUtilsHelper.mergerStringEitherAWord(cardInfo.getAddress1(), cardInfo.getAddress2(), " "));
						SchemaContextHolder.setSchemaType(SchemaType.MANAGER);
						cardInfoDAO.editCardInfo(cardInfo);
					}
				}
				return cardDetail.getStatus();
			} else {
				status = getErrorStatus("Error connect to webservice");
			}
		} catch (AxisFault e) {
			status = getErrorStatus(e.getMessage());
		} catch (RemoteException e) {
			status = getErrorStatus(e.getMessage());
		}
		return status;
	}

	@Override
	public Status processCardImageDetail(Integer userId, Integer cardId, String base64CardData) {
		GetCardImageDetail cardImage = new GetCardImageDetail();
		cardImage.setCardID(String.valueOf(cardId));
		cardImage.setBase64(base64CardData);

		CardDetail cardDetail = null;
		Status status = null;
		try {
			SchemaContextHolder.setSchemaType(SchemaType.USER);

			OcrWebServiceStub service = new OcrWebServiceStub(this.ocrServiceTargetEndpoint);
			GetCardImageDetailResponse response = service.getCardImageDetail(cardImage);
			cardDetail = response.getGetCardImageDetailResult();

			if (cardDetail != null) {
				if (cardDetail.getStatus() != null && cardDetail.getStatus().getCode() == 0) {
					CardInfoData cardInfoData = cardDetail.getCardInfoData();
					if (cardInfoData != null) {
						// Update card information
						CardInfo cardInfo = convertCardInfo(cardInfoData, null);
						cardInfo.setCardId(cardId);

						UserInfo userInfo = userInfoService.getUserInfoByUserId(userId);
						cardInfo.setGroupCompanyId(userInfo.getGroupCompanyId());
						String lastNameKana = cardInfo.getLastNameKana();
						String fisrtNameKana = cardInfo.getFirstNameKana();
						String companyNameKana = cardInfo.getCompanyNameKana();
						cardInfo.setLastNameKana(lastNameKana);
						cardInfo.setFirstNameKana(fisrtNameKana);
						cardInfo.setCompanyNameKana(companyNameKana);
						cardInfo.setCreateDate(new Date());
						cardInfo.setUpdateDate(new Date());
						cardInfo.setContactDate(new Date());
						cardInfo.setNameKana(StringUtilsHelper.mergerStringEitherAWord(lastNameKana, fisrtNameKana, " "));
						cardInfo.setAddressFull(StringUtilsHelper.mergerStringEitherAWord(cardInfo.getAddress1(), cardInfo.getAddress2(), " "));
						SchemaContextHolder.setSchemaType(SchemaType.MANAGER);
						cardInfoDAO.editCardInfo(cardInfo);
					}
				}
				return cardDetail.getStatus();
			} else {
				status = getErrorStatus("Error connect to webservice");
			}
		} catch (AxisFault e) {
			status = getErrorStatus(e.getMessage());
		} catch (RemoteException e) {
			status = getErrorStatus(e.getMessage());
		}
		return status;
	}

	private CardDetail errorReadCardFromOcr(Exception ex) {
		CardDetail cardDetail = new CardDetail();
		cardDetail.setStatus(getErrorStatus(ex.getMessage()));
		return cardDetail;
	}

	private Status getErrorStatus(String errorMessage) {
		Status status = new Status();
		status.setCode(1);
		status.setErrorMess(errorMessage);
		return status;
	}

	private CardInfo convertCardInfo(CardInfoData cardInfoData, CardInfo cardInfoParam) {
		CardInfo cardInfo = cardInfoParam;
		if (cardInfo == null){
			cardInfo = new CardInfo();
		}
		
		if (StringUtils.isNotEmpty(cardInfoData.getCompanyName())) {
			cardInfo.setCompanyName(cardInfoData.getCompanyName());
		}
		cardInfo.setFirstName(cardInfoData.getGivenNames());
		cardInfo.setLastName(cardInfoData.getSurname());
		cardInfo.setPositionName(cardInfoData.getPosition());
		if (StringUtils.isNotEmpty(cardInfoData.getEmailAddress1())) {
			cardInfo.setEmail(Normalizer.normalize(cardInfoData.getEmailAddress1(), Normalizer.Form.NFKC));
		} else if (StringUtils.isNotEmpty(cardInfoData.getEmailAddress2())) {
			cardInfo.setEmail(Normalizer.normalize(cardInfoData.getEmailAddress2(), Normalizer.Form.NFKC));
		}

		if (StringUtils.isNotEmpty(cardInfoData.getPostalCode1())) {
			cardInfo.setZipCode(Normalizer.normalize(cardInfoData.getPostalCode1(), Normalizer.Form.NFKC));
		} else if (StringUtils.isNotEmpty(cardInfoData.getPostalCode2())) {
			cardInfo.setZipCode(Normalizer.normalize(cardInfoData.getPostalCode2(), Normalizer.Form.NFKC));
		}
		cardInfo.setAddress1(cardInfoData.getStreetAddress1());
		cardInfo.setAddress2(cardInfoData.getStreetAddress2());

		if (StringUtils.isNotEmpty(cardInfoData.getPhoneNumber1())) {
			cardInfo.setTelNumberCompany(Normalizer.normalize(cardInfoData.getPhoneNumber1(), Normalizer.Form.NFKC));
		} else if (StringUtils.isNotEmpty(cardInfoData.getPhoneNumber2())) {
			cardInfo.setTelNumberCompany(Normalizer.normalize(cardInfoData.getPhoneNumber2(), Normalizer.Form.NFKC));
		}

		if (StringUtils.isNotEmpty(cardInfoData.getFaxNumber1())) {
			cardInfo.setFaxNumber(Normalizer.normalize(cardInfoData.getFaxNumber1(), Normalizer.Form.NFKC));
		} else if (StringUtils.isNotEmpty(cardInfoData.getFaxNumber2())) {
			cardInfo.setFaxNumber(Normalizer.normalize(cardInfoData.getFaxNumber2(), Normalizer.Form.NFKC));
		}

		if (StringUtils.isNotEmpty(cardInfoData.getURL1())) {
			cardInfo.setCompanyUrl(Normalizer.normalize(cardInfoData.getURL1(), Normalizer.Form.NFKC));
		} else if (StringUtils.isNotEmpty(cardInfoData.getURL2())) {
			cardInfo.setCompanyUrl(Normalizer.normalize(cardInfoData.getURL2(), Normalizer.Form.NFKC));
		}

		if (StringUtils.isNotEmpty(cardInfoData.getMobileNumber1())) {
			cardInfo.setMobileNumber(Normalizer.normalize(cardInfoData.getMobileNumber1(), Normalizer.Form.NFKC));
		} else if (StringUtils.isNotEmpty(cardInfoData.getMobileNumber2())) {
			cardInfo.setMobileNumber(Normalizer.normalize(cardInfoData.getMobileNumber2(), Normalizer.Form.NFKC));
		}
		CompanyInfo company = new CompanyInfo();
		company.setCompanyId(0);
		cardInfo.setCompanyInfo(company);
		cardInfo.setApprovalStatus(2);		
		return cardInfo;
	}

	private static String convertHirakanaToKatakana(String name) {
		final String uri = "http://yomi-tan.jp/api/yomi.php?ic=UTF-8&oc=UTF-8&k=k&n=1&t=" + name;

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
		String result = restTemplate.getForObject(uri, String.class, HttpMethod.GET);
		return result;

	}
}
