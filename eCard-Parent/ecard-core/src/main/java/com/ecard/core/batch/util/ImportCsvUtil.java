/*
 * ImportCsvUtil
 */
package com.ecard.core.batch.util;

import au.com.bytecode.opencsv.CSVReader;
import com.ecard.core.batch.contants.BatchConstants;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author vinhla
 */
public class ImportCsvUtil {
    private static final Logger LOG = LoggerFactory.getLogger(ImportCsvUtil.class);
    private static final String SQL_INSERT = "INSERT INTO ${table}(${keys}) VALUES(${values})";
    private static final String TABLE_REGEX = "\\$\\{table\\}";
    private static final String KEYS_REGEX = "\\$\\{keys\\}";
    private static final String VALUES_REGEX = "\\$\\{values\\}";
 
    private Connection connection;
    private char seprator;
    
//    @Value("${scp.hostname}")
//    private static String scpHostName;
//    
//    @Value("${scp.user}")
//    private static String scpUserName;
//    
//    @Value("${scp.password}")
//    private static String scpPassword;
//    
//    @Value("${scp.port}")
//    private static int scpPort;

    public static boolean restoreDB(String dbName, String dbUserName, String dbPassword, String source) {
        
        String[] executeCmd = new String[]{"mysql", "--user=" + dbUserName, "--password=" + dbPassword, dbName,"-e", "source "+source};
        
        Process runtimeProcess;
        try {
 
            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();
 
            if (processComplete == 0) {
                System.out.println("Backup restored successfully");
                return true;
            } else {
                System.out.println("Could not restore the backup");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
 
        return false;
    }
    
    /*public void importCSV(String folderPath, String tableName) throws SQLException{
        try 
        {
            connection = DBConnection.getConnection();

            //File file = getTheNewestFile(folderPath, "csv");
            String csvFile = getCsvFromSCP();
            
            //String csvFile = file.getAbsolutePath();
            //csvFile = csvFile.replace("\\", "/");
            
            String loadQuery = "LOAD DATA LOCAL INFILE '" + csvFile + "' IGNORE INTO TABLE "+ tableName +" FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '\"' LINES TERMINATED BY '\r\n' "
                            + "IGNORE 1 LINES (card_id,card_type,image_file,card_back_img_file,company_id,company_name,company_name_kana,department_name,\n " 
                            + "position_name,name,last_name,first_name,name_kana,last_name_kana,first_name_kana,email,zip_code,address_full,address_1,address_2,address_3,address_4,\n " 
                            + "tel_number_company,tel_number_department,tel_number_direct,fax_number,mobile_number,company_url,sub_address_full,sub_zip_code,sub_address_1,\n " 
                            + "sub_address_2,sub_address_3,sub_address_4,sub_tel_number_company,sub_tel_number_department,sub_tel_number_direct,sub_fax_number,file_output_flg,hand_memo,\n "
                            + "auto_memo,memo1,memo2,card_owner_id,publish_status,approval_status,old_card_flg,@create_date,@update_date,operater_id,@delet_date,delete_flg) \n " 
                            + "SET create_date = STR_TO_DATE(@create_date, '%Y-%m-%d %h:%i:%s'), update_date = STR_TO_DATE(@update_date, '%Y-%m-%d %h:%i:%s'), delet_date = STR_TO_DATE(@delet_date, '%Y-%m-%d %h:%i:%s')";
            LOG.debug("Restore DB Query : ", loadQuery);
            Statement stmt = connection.createStatement();
            stmt.execute(loadQuery);
        }
        catch (Exception e)
        {
            LOG.debug("Exception : ", e.getMessage());
        }
    }*/
    
    public static String getFileFromSCP(String scpUserName, String scpHostName, Integer scpPort, String scpPassword){
        JSch jsch = new JSch();
        Session session = null;
        ChannelSftp.LsEntry lsEntry = null;
        String filePath = "";
        try{
            session = jsch.getSession(scpUserName, scpHostName, scpPort);
            session.setPassword(scpPassword);
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp channelSftp = (ChannelSftp)channel;
            //Cd to folder
            channelSftp.cd(BatchConstants.saveManualFolder);
            
            Vector filelist = channelSftp.ls(BatchConstants.saveManualFolder);
            lsEntry = (ChannelSftp.LsEntry) filelist.lastElement();
            
            filePath = channelSftp.realpath(".") + "/" + lsEntry.getFilename();
            System.out.println("File path : "+filePath);

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return filePath;
    }
    
    /* Get the newest file for a specific extension */
    public static File getNewestFile(String filePath, String ext) {
        File theNewestFile = null;
        File dir = new File(filePath);
        FileFilter fileFilter = new WildcardFileFilter("*." + ext);
        File[] files = dir.listFiles(fileFilter);

        if (files.length > 0) {
            /** The newest file comes first **/
            Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
            theNewestFile = files[0];
        }

        return theNewestFile;
    }
     
    /**
     * Parse CSV file using OpenCSV library and load in 
     * given database table. 
     * @param csvFile Input CSV file
     * @param tableName Database table name to import data
     * @param truncateBeforeLoad Truncate the table before inserting 
     *          new records.
     * @throws Exception
     */
    public void readCsvUsingLoad(String csvFile, String tableName,
            boolean truncateBeforeLoad) throws Exception {
 
        CSVReader csvReader = null;
        if(null == this.connection) {
            throw new Exception("Not a valid connection.");
        }
        try {
             
            csvReader = new CSVReader(new FileReader(csvFile), this.seprator);
 
        } catch (Exception e) {
//            e.printStackTrace();
            throw new Exception("Error occured while executing file. "
                    + e.getMessage());
        }
 
        String[] headerRow = csvReader.readNext();
        
        if (null == headerRow) {
            throw new FileNotFoundException(
                "No columns defined in given CSV file." +
                "Please check the CSV file format.");
        }
 
        String questionmarks = StringUtils.repeat("?,", headerRow.length);
        questionmarks = (String) questionmarks.subSequence(0, questionmarks.length() - 1);
 
        String query = SQL_INSERT.replaceFirst(TABLE_REGEX, tableName);
        query = query.replaceFirst(KEYS_REGEX, StringUtils.join(headerRow, ","));
        query = query.replaceFirst(VALUES_REGEX, questionmarks);
 
        System.out.println("Query: " + query);
 
        String[] nextLine;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = this.connection;
            con.setAutoCommit(false);
            ps = con.prepareStatement(query);
 
            if(truncateBeforeLoad) {
                //delete data from table before loading csv
                con.createStatement().execute("DELETE FROM " + tableName);
            }
 
            final int batchSize = 1000;
            int count = 0;
            Date date = null;
            while ((nextLine = csvReader.readNext()) != null) {
 
                if (null != nextLine) {
                    int index = 1;
                    for (String string : nextLine) {
                        date = DateUtil.convertToDate(string);
                        if (null != date) {
                            ps.setDate(index++, new java.sql.Date(date.getTime()));
                        } else {
                            ps.setString(index++, string);
                        }
                        //ps.setString(index++, string);
                    }
                    ps.addBatch();
                }
                if (++count % batchSize == 0) {
                    ps.executeBatch();
                }
            }
            ps.executeBatch(); // insert remaining records
            con.commit();
        } catch (Exception e) {
            con.rollback();
            e.printStackTrace();
            throw new Exception(
                    "Error occured while loading data from file to database."
                            + e.getMessage());
        } finally {
            if (null != ps)
                ps.close();
            if (null != con)
                con.close();
 
            csvReader.close();
        }
    }
 
    public char getSeprator() {
        return seprator;
    }
 
    public void setSeprator(char seprator) {
        this.seprator = seprator;
    }
}

class DateUtil {
 
    // List of all date formats that we want to parse.
    // Add your own format here.
    private static List<SimpleDateFormat> dateFormats = new ArrayList<SimpleDateFormat>() {{
            add(new SimpleDateFormat("M/dd/yyyy"));
            add(new SimpleDateFormat("dd.M.yyyy"));
            add(new SimpleDateFormat("M/dd/yyyy hh:mm:ss a"));
            add(new SimpleDateFormat("M/dd/yyyy h:mm:ss a"));
            add(new SimpleDateFormat("dd.M.yyyy hh:mm:ss a"));
            add(new SimpleDateFormat("dd.MMM.yyyy"));
            add(new SimpleDateFormat("dd-MMM-yyyy"));
            add(new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss"));
        }
    };
 
    /**
     * Convert String with various formats into java.util.Date
     * 
     * @param input
     * Date as a string
     * @return java.util.Date object if input string is parsed 
     *          successfully else returns null
     */
    public static Date convertToDate(String input) {
        Date date = null;
        if(null == input) {
            return null;
        }
        for (SimpleDateFormat format : dateFormats) {
            try {
                format.setLenient(false);
                date = format.parse(input);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (date != null) {
                break;
            }
        }
 
        return date;
    }
};
