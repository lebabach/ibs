package com.ecard.core.util;

import java.util.Arrays;
import java.util.List;

public class SearchIndexUtil {
	public static List<String> replace(String textSearch) {
		String seq = textSearch.trim().replaceAll(" +", " ");
		String[] seqStr = seq.split(" ");
		int i = 0;
		String[] stringArray = new String[seqStr.length];
		for (String myStr : seqStr) {
			try {
				stringArray[i] = myStr;
				i++;
			} catch (NumberFormatException e) {
				throw new IllegalArgumentException("Not a number: " + myStr + " at index " + i, e);
			}
		}
		List<String> seqList = Arrays.asList(stringArray);
		return seqList;
	}

	public static String convertHiragana2Katakana(String str) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			char code = str.charAt(i);
			if ((code >= 0x3041) && (code <= 0x3093)) {
				buf.append((char) (code + 0x60));
			} else {
				buf.append(code);
			}
		}
		return buf.toString();
	}
}
