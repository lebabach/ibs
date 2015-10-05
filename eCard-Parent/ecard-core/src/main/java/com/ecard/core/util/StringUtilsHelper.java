/*
 * StringUtilsHelper
 */
package com.ecard.core.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.mysql.fabric.xmlrpc.base.Array;

/**
 *
 * @author bach.le
 */
public class StringUtilsHelper {
    private static final String formatDate = "M/dd/yyyy hh:mm:ss a";

    public static String randomCode(String input) {
        String result = getMD5(input + new Date());
        return result;
    }

    public static String randomCode(String input, int length) {
        String result = randomCode(input);
        if (result.length() <= length) {
            return result;
        }
        return splitByNumber(result, length);
    }

    public static String randomCode(int length) {
        Date now = new Date();
        String result = randomCode(String.valueOf(now.getTime()));
        if (result.length() <= length) {
            return result;
        }
        return splitByNumber(result, length);
    }

    private static String splitByNumber(String text, int number) {
        if (text.length() <= number) {
            return text;
        }
        return text.substring(0, number);
    }

    public static String getMD5(String input) {

        String md5 = null;

        if (null == input) {
            return null;
        }
        try {
            //Create MessageDigest object for MD5
            MessageDigest digest = MessageDigest.getInstance("MD5");

            //Update input string in message digest
            digest.update(input.getBytes(), 0, input.length());

            //Converts message digest value in base 16 (hex) 
            md5 = new BigInteger(1, digest.digest()).toString(16);

        } catch (NoSuchAlgorithmException e) {
        }
        return md5;
    }

    public static Date convertDateToDefault(Date oldDate, Locale locale) {
        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat(formatDate, locale);
            String strDate = dateFormat.format(oldDate);
            Date dd = dateFormat.parse(strDate);

            SimpleDateFormat dateFormatLocal = new SimpleDateFormat(formatDate, Locale.getDefault());
            String strNewdate = dateFormatLocal.format(dd);

            return dateFormatLocal.parse(strNewdate);

        } catch (ParseException ex) {
            Logger.getLogger(StringUtilsHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Date convertDate(String strDate, String formatString) {
        SimpleDateFormat format = new SimpleDateFormat(formatString, Locale.US);
        Date date = null;
        try {
            date = format.parse(strDate);
        } catch (ParseException ex) {
            Logger.getLogger(StringUtilsHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return date;
    }
    
    //bach.le
	public static String mergerStringEitherAWord(String firstName, String lastName, String word) {
		List<String> list = new ArrayList<String>();
		list.add(firstName);
		list.add(lastName);
		return list.stream().filter(x -> x != null).collect(Collectors.joining(word));
	}

	public static String mergerArrayStringEitherAWord(String[] arrString, String word) {
		return Arrays.asList(arrString).stream().filter(x -> x != null).collect(Collectors.joining(word));
	}

	public static String mergerListStringEitherAWordL(List<String> liString, String word) {
		return liString.stream().filter(x -> x != null).collect(Collectors.joining(word));
	}

	public static String convertIdToString(int id) {
		String str = "";
		if (id > 0 && id <= 9) {
			str = "00" + id;
		} else if (id >= 10 && id <= 99) {
			str = "0" + id;
		} else if (id >= 100 && id <= 999) {
			str += id;

		}
		return str;
	}
}
