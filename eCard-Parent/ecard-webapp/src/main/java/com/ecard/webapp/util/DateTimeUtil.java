package com.ecard.webapp.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTimeUtil {

	public static Date getDateWith(String format,String date){
		Date newDate;
		try {
			newDate = (new SimpleDateFormat(format)).parse(date);
		} catch (ParseException e) {
			return new Date();
		}
		return newDate;
	}
	
	public static Date parseDate(String date){
		DateFormat df = DateFormat.getDateInstance(DateFormat.FULL, new Locale("ja"));
		Date dateParse = null ;
		try {
			dateParse = df.parse(date);
		} catch (ParseException e) {
			return new Date();
		}
		return dateParse;
	}
}
