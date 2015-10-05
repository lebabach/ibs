package com.ecard.webapp.util;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

public class CsvFileReader {

    public static List<Object> readCsv(String csvFileName, Object o, CellProcessor[] processors) throws IOException {

		ICsvBeanReader beanReader = null;
		try {
			beanReader = new CsvBeanReader(new FileReader(csvFileName), CsvPreference.STANDARD_PREFERENCE);
			
			// the header elements are used to map the values to the bean (names must match)
			final String[] header = beanReader.getHeader(true);
			// final CellProcessor[] processors = getProcessors();
			List<Object> li = new ArrayList<Object>();
			Object newObject;
			while ((newObject = beanReader.read(o.getClass(), header, processors)) != null) {
				// process course
				li.add(newObject);
			}
			return li;
		} finally {
			if (beanReader != null) {
				beanReader.close();
			}
		}
    }
    public static List<Object> readCsvStream(InputStreamReader inputStreamReader, Object o, CellProcessor[] processors) throws IOException {

		ICsvBeanReader beanReader = null;
		try {
			beanReader = new CsvBeanReader(inputStreamReader, CsvPreference.STANDARD_PREFERENCE);
			
			// the header elements are used to map the values to the bean (names must match)
			final String[] header = beanReader.getHeader(true);
			// final CellProcessor[] processors = getProcessors();
			List<Object> li = new ArrayList<Object>();
			Object newObject;
			while ((newObject = beanReader.read(o.getClass(), header, processors)) != null) {
				// process course
				li.add(newObject);
			}
			return li;
		} finally {
			if (beanReader != null) {
				beanReader.close();
			}
		}
    }
    public static List<Object> readCsvIgoreColumn(String csvFileName, Object o, CellProcessor[] processors) throws IOException {

		ICsvBeanReader beanReader = null;
		try {
			beanReader = new CsvBeanReader(new FileReader(csvFileName), CsvPreference.STANDARD_PREFERENCE);

			// the header elements are used to map the values to the bean (names must match)
			final String[] header = beanReader.getHeader(true);

			String[] headerColumns = new String[] { "companyName", "companyNameEn", "companyNameKana", "departmentName",
					"positionName", "name", "lastName", "firstName", "nameKana", "lastNameKana", "firstNameKana",
					"zipCode", "addressFull", "address1", "address2", "address3", "address4", "telNumberCompany",
					"telNumberDepartment", "faxNumber", "mobileNumber", "email", "companyUrl", "subAddressFull",
					"subZipCode", "subAddress1", "subAddress2", "subAddress3", "subAddress4", "subTelNumberCompany",
					"subTelNumberDepartment", "subFaxNumber", "subMobileNumber", "subEmail", "subCompanyUrl",
					"importanceLevel", "VP", "CP", "fileOutputFlg", "userTag", "createDate", "contactDate",
					"posContactDate", "address", "tel", "fax", "e_mail", "memo1", "autoMemo", "cardOwnerId",
					"cardOwnerName", "contact_date", "contact_type", "title", "place", "contact_memo", "company_id",
					"user_id", "card_id", "blank" };

			final String[] headerNames = Arrays.copyOf(headerColumns, header.length);
			// final CellProcessor[] processors = getProcessors();
			List<Object> li = new ArrayList<Object>();
			Object newObject;
			while ((newObject = beanReader.read(o.getClass(), headerNames, processors)) != null) {
				// process course
				li.add(newObject);
			}
			return li;
		} finally {
			if (beanReader != null) {
				beanReader.close();
			}
		}
    }
    
    public static List<Object> readCsvMapHeader(InputStreamReader inputStreamReader, Object o, CellProcessor[] processors, String[] headerTemp) throws IOException {

		ICsvBeanReader beanReader = null;
		try {
			beanReader = new CsvBeanReader(inputStreamReader, CsvPreference.STANDARD_PREFERENCE);

			// the header elements are used to map the values to the bean (names must match)
			final String[] header = beanReader.getHeader(true);

			final String[] headerNames = Arrays.copyOf(headerTemp, header.length);
			// final CellProcessor[] processors = getProcessors();
			List<Object> li = new ArrayList<Object>();
			Object newObject;
			while ((newObject = beanReader.read(o.getClass(), headerNames, processors)) != null) {
				// process course
				li.add(newObject);
			}
			return li;
		} finally {
			if (beanReader != null) {
				beanReader.close();
			}
		}
    }
    public static List<Object> readCsvMapHeader(String csvFileName, Object o, CellProcessor[] processors, String[] headerTemp) throws IOException {

		ICsvBeanReader beanReader = null;
		try {
			beanReader = new CsvBeanReader(new FileReader(csvFileName), CsvPreference.STANDARD_PREFERENCE);

			// the header elements are used to map the values to the bean (names must match)
			final String[] header = beanReader.getHeader(true);

			final String[] headerNames = Arrays.copyOf(headerTemp, header.length);
			// final CellProcessor[] processors = getProcessors();
			List<Object> li = new ArrayList<Object>();
			Object newObject;
			while ((newObject = beanReader.read(o.getClass(), headerNames, processors)) != null) {
				// process course
				li.add(newObject);
			}
			return li;
		} finally {
			if (beanReader != null) {
				beanReader.close();
			}
		}
    }
}
