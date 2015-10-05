package com.ecard.core.service.impl;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;

import com.ecard.core.dao.DataIndexIdDAO;
import com.ecard.core.dao.UserInfoDAO;
import com.ecard.core.model.DataIndexId;
import com.ecard.core.model.enums.*;
import com.fasterxml.jackson.databind.util.Converter;


public class CommonServiceImpl {
	
	
	@Autowired
	DataIndexIdDAO dataIndexDAO;
	
	private DataIndexId getDataIndexBy(IndexTypeEnum indexType, Date updatedDate) {
		List<DataIndexId> dataindexs = dataIndexDAO.findAll(DataIndexId.class);
		DataIndexId dataindex = dataindexs.stream()
				.filter(d -> d.getIndexType() == indexType.getStatusCode() && d.getCreateDate().compareTo(updatedDate) == 0)
				.sorted(Comparator.comparing(DataIndexId::getCreateDate).reversed()).findFirst().get();
		return dataindex;
	}

	private String generateFormatIdWith(TableTypeEnum tableType, int lastNumberOfSequence, int lastNumberOfProperty,
			PropertyCodeEnum propertyCode, ActionTypeEnum actionType) {
		return tableType.getStatusCode() + getCurrentDate() + getSequenceCode(lastNumberOfSequence, actionType)
				+ getPropertyCode(lastNumberOfProperty, propertyCode, actionType);
	}

	private String getSequenceCode(int lastNumber, ActionTypeEnum actionType) {
		NumberFormat formatter = new DecimalFormat("#00000000");
		String code = "";
		if (actionType == ActionTypeEnum.Update) {
			code = formatter.format(lastNumber);
		} else if (actionType == ActionTypeEnum.Insert) {
			code = formatter.format(++lastNumber);
		}
		return code;
	}

	private String getPropertyCode(int lastNumber, PropertyCodeEnum propertyCode, ActionTypeEnum actionType) {
		NumberFormat formatter = new DecimalFormat("#00");
		String code = "";
		if (actionType == ActionTypeEnum.Update) {
			code = formatter.format(++lastNumber);
		} else if (actionType == ActionTypeEnum.Insert) {
			code = formatter.format(lastNumber);
		}
		return code + propertyCode.getStatusCode();
	}

	private String getCurrentDate() {
		String s;
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = (new Date());
		formatter = new SimpleDateFormat("yyMMdd");
		s = formatter.format(date1);
		return s;
	}

	private String getSequenceCodeFrom(String index_no_table) {
		int indexFirst = getCurrentDate().toString().length() + 1;
		return index_no_table.substring(indexFirst, indexFirst + 8);
	}
}
