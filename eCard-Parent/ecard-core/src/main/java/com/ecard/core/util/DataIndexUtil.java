package com.ecard.core.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import com.ecard.core.model.enums.ActionTypeEnum;
import com.ecard.core.model.enums.PropertyCodeEnum;
import com.ecard.core.model.enums.TableTypeEnum;

public class DataIndexUtil {
	public static String generateFormatIdWith(TableTypeEnum tableType, int lastNumberOfSequence,
			int lastNumberOfProperty, PropertyCodeEnum propertyCode, ActionTypeEnum actionType) {
		return tableType.getStatusCode() + getCurrentDateHardCode150930(propertyCode) + getSequenceCode(lastNumberOfSequence, actionType)
				+ getPropertyCode(lastNumberOfProperty, propertyCode, actionType, tableType);
	}
	
	/*public static String generateFormatIdWith(TableTypeEnum tableType, int lastNumberOfSequence,
			int lastNumberOfProperty, PropertyCodeEnum propertyCode, ActionTypeEnum actionType,int date) {
		return tableType.getStatusCode() + date + getSequenceCode(lastNumberOfSequence, actionType,convertToDateBy(date+"","yyMMdd"))
				+ getPropertyCode(lastNumberOfProperty, propertyCode, actionType, tableType);
	}*/
	
	public static String generateFormatIdWith(TableTypeEnum tableType, int lastNumberOfSequence,
			int lastNumberOfProperty, PropertyCodeEnum propertyCode, ActionTypeEnum actionType,int date,String indexNo) {
		return tableType.getStatusCode() + date + getSequenceCode(lastNumberOfSequence, actionType,convertToDateBy(getDateFromIndexNo(indexNo)+"","yyMMdd"))
				+ getPropertyCode(lastNumberOfProperty, propertyCode, actionType, tableType);
	}

	public static String getSequenceCode(int lastNumber, ActionTypeEnum actionType) {
		NumberFormat formatter = new DecimalFormat("#00000000");
		String code = "";
		if (actionType == ActionTypeEnum.Update) {
			code = formatter.format(lastNumber);
		} else if (actionType == ActionTypeEnum.Insert) {
			code = formatter.format(++lastNumber);
		}
		return code;
	}
	
	public static String getSequenceCode(int lastNumber, ActionTypeEnum actionType,Date beforeDate) {
		NumberFormat formatter = new DecimalFormat("#00000000");
		String code = "";
		if (actionType == ActionTypeEnum.Update) {
			code = formatter.format(lastNumber);
		} else if (actionType == ActionTypeEnum.Insert) {
			Date currentDate = new Date();
			LocalDate currentLocatlDate = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate beforeLocatlDate = beforeDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			if(!currentLocatlDate.isAfter(beforeLocatlDate)){
				code = formatter.format(++lastNumber);	
			}else{
				code = formatter.format(1);	
			}
			
		}
		return code;
	}

	public static String getPropertyCode(int lastNumber, PropertyCodeEnum propertyCode, ActionTypeEnum actionType) {
		NumberFormat formatter = new DecimalFormat("#00");
		String code = "";
		if (actionType == ActionTypeEnum.Update) {
			code = formatter.format(++lastNumber);
		} else if (actionType == ActionTypeEnum.Insert) {
			code = formatter.format(0);
		}
		return code + propertyCode.getStatusCode();
	}

	public static String getPropertyCode(int lastNumber, PropertyCodeEnum propertyCode, ActionTypeEnum actionType,
			TableTypeEnum tableType) {
		NumberFormat formatter = new DecimalFormat("#00");
		if (tableType == TableTypeEnum.User) {
			formatter = new DecimalFormat("#000");
		}
		String code = "";
		if (actionType == ActionTypeEnum.Update) {
			if (tableType == TableTypeEnum.User) {
				code = formatter.format(lastNumber);
			} else {
				lastNumber = lastNumber + 1;
				if (lastNumber <= 99) {
					code = formatter.format(lastNumber) + propertyCode.getStatusCode();
				} else {
					code = formatter.format(99) + propertyCode.getStatusCode();
				}

			}
		} else if (actionType == ActionTypeEnum.Insert) {
			if (tableType == TableTypeEnum.User) {
				code = formatter.format(lastNumber);
			} else {
				code = formatter.format(0) + propertyCode.getStatusCode();
			}

		}
		return code;
	}

	public static String getCurrentDate() {
		String s;
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = (new Date());
		formatter = new SimpleDateFormat("yyMMdd");
		s = formatter.format(date1);
		return s;
	}
	//hardcode: https://livepass.backlog.jp/view/MEISHI-699
	public static String getCurrentDateHardCode150930(PropertyCodeEnum propertyCode) {
		String s;
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = (new Date());
		formatter = new SimpleDateFormat("yyMMdd");
		s = formatter.format(date1);
//		if(propertyCode==PropertyCodeEnum.Migration){
//			s="150930";
//		}
		return s;
	}

	public static int getSequenceCodeFrom(String index_no_table) {
		int indexFirst = getCurrentDate().toString().length() + 1;
		return Integer.parseInt(index_no_table.substring(indexFirst, indexFirst + 8));
	}

	public static int getPropertyCodeFrom(String index_no_table) {
		int indexFirst = getCurrentDate().toString().length() + 1;
		return Integer.parseInt(index_no_table.substring(indexFirst + 8, index_no_table.length() - 1));
	}

	public static String getIndexNoOfImageBy(TableTypeEnum tableType, String indexNoOfCardInfor) {
		return tableType.getStatusCode() + indexNoOfCardInfor.substring(1);
	}
	
	public static int getDateFrom(String index_no_table,PropertyCodeEnum propertyCode,ActionTypeEnum actionType) {
		int indexFirst = getCurrentDate().toString().length() + 1;
	
		String date=index_no_table.substring(1, indexFirst);
		if(actionType==ActionTypeEnum.Insert){
			date=getCurrentDateHardCode150930(propertyCode);
		}
		return Integer.parseInt(date);
	}
	
	public static int getDateFromIndexNo(String index_no_table) {
		int indexFirst = getCurrentDate().toString().length() + 1;
		String date=index_no_table.substring(1, indexFirst);
		return Integer.parseInt(date);
	}
	
	public static Date convertToDateBy(String date,String formatDate) {
		SimpleDateFormat formatter= new SimpleDateFormat(formatDate);
		try {
			return (Date)formatter.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Date();
	}
	
	public static String setPropertyCodeFrom(String index_no_table,String newPropertyCode,TableTypeEnum tableType) {
		int indexFirst = getCurrentDate().toString().length() + 1;
		String idNoProperty=index_no_table.substring(0,indexFirst + 8);
		return tableType.getStatusCode() + (idNoProperty+newPropertyCode).substring(1);
	}
}
