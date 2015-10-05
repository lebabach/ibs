/*
 * ExportCSVUtil
 */
package com.ecard.core.batch.util;

import ch.ethz.ssh2.SCPClient;
import com.ecard.core.batch.contants.BatchConstants;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author vinhla
 */
public class ExportCSVUtil {
    private static final Logger LOG = LoggerFactory.getLogger(ExportCSVUtil.class);
    
    public ExportCSVUtil() {}
     
    public Connection connect(String db_connect_str, String db_userid, String db_password) {
        Connection conn;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(db_connect_str, db_userid, db_password);
            //conn = DBConnection.getConnection();
             
        } catch(Exception e) {
            LOG.debug("Exception : ", e.getMessage());
            conn = null;
        }
        return conn;
    }
    
    public void exportCSV(Connection connection, String folderPath) throws SQLException{
        try 
        {
            String DATE_FORMAT = "yyyyMMdd";
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            String fileFormat = BatchConstants.saveManualFolder + "/" + sdf.format(new Date()) + "_" + sdf.format(new Date().getSeconds()) + "_db_back.csv";
            fileFormat = fileFormat.replace("\\", "/");
            
            File file = new File(fileFormat);
            
            System.out.println("fileFormat : "+fileFormat+" and file :"+file.getAbsoluteFile());
            String sqlExport = "SELECT 'card_id','card_type','image_file','card_back_img_file','company_id','company_name','company_name_kana','department_name','position_name','name','last_name','first_name','name_kana'," +
                            "'last_name_kana','first_name_kana','email','zip_code','address_full','address_1','address_2','address_3','address_4','tel_number_company','tel_number_department','tel_number_direct','fax_number'," +
                            "'mobile_number','company_url','sub_address_full','sub_zip_code','sub_address_1','sub_address_2','sub_address_3','sub_address_','sub_tel_number_company','sub_tel_number_department','sub_tel_number_direct'," +
                            "'sub_fax_number','file_output_flg','hand_memo','auto_memo','memo1','memo2','card_owner_id','publish_status','approval_status','old_card_flg','create_date','update_date','operater_id','delet_date','delete_flg' " +
                            "FROM card_info " +
                            "UNION " +
                            "SELECT card_id, card_type,REPLACE( IFNULL(image_file, ''), '\r\n' , '\n' ), REPLACE( IFNULL(card_back_img_file, ''), '\r\n' , '\n' ), company_id," +
                            "REPLACE( IFNULL(company_name, ''), '\r\n' , '\n' ), REPLACE( IFNULL(company_name_kana, ''), '\r\n' , '\n' ), REPLACE( IFNULL(department_name, ''), '\r\n' , '\n' )," +
                            "REPLACE( IFNULL(position_name, ''), '\r\n' , '\n' ), REPLACE( IFNULL(name, ''), '\r\n' , '\n' ), REPLACE( IFNULL(last_name, ''), '\r\n' , '\n' )," +
                            "REPLACE( IFNULL(first_name, ''), '\r\n' , '\n' ), REPLACE( IFNULL(name_kana, ''), '\r\n' , '\n' ), REPLACE( IFNULL(last_name_kana, ''), '\r\n' , '\n' )," +
                            "REPLACE( IFNULL(first_name_kana, ''), '\r\n' , '\n' ), REPLACE( IFNULL(email, ''), '\r\n' , '\n' ), REPLACE( IFNULL(zip_code, ''), '\r\n' , '\n' )," +
                            "REPLACE( IFNULL(address_full, ''), '\r\n' , '\n' ), REPLACE( IFNULL(address_1, ''), '\r\n' , '\n' ), REPLACE( IFNULL(address_2, ''), '\r\n' , '\n' )," +
                            "REPLACE( IFNULL(address_3, ''), '\r\n' , '\n' ), REPLACE( IFNULL(address_4, ''), '\r\n' , '\n' ), REPLACE( IFNULL(tel_number_company, ''), '\r\n' , '\n' )," +
                            "REPLACE( IFNULL(tel_number_department, ''), '\r\n' , '\n' ), REPLACE( IFNULL(tel_number_direct, ''), '\r\n' , '\n' ), REPLACE( IFNULL(fax_number, ''), '\r\n' , '\n' )," +
                            "REPLACE( IFNULL(mobile_number, ''), '\r\n' , '\n' ), REPLACE( IFNULL(company_url, ''), '\r\n' , '\n' ), REPLACE( IFNULL(sub_address_full, ''), '\r\n' , '\n' )," +
                            "REPLACE( IFNULL(sub_zip_code, ''), '\r\n' , '\n' ), REPLACE( IFNULL(sub_address_1, ''), '\r\n' , '\n' ), REPLACE( IFNULL(sub_address_2, ''), '\r\n' , '\n' )," +
                            "REPLACE( IFNULL(sub_address_3, ''), '\r\n' , '\n' ), REPLACE( IFNULL(sub_address_4, ''), '\r\n' , '\n' ), REPLACE( IFNULL(sub_tel_number_company, ''), '\r\n' , '\n' )," +
                            "REPLACE( IFNULL(sub_tel_number_department, ''), '\r\n' , '\n' ), REPLACE( IFNULL(sub_tel_number_direct, ''), '\r\n' , '\n' ), REPLACE( IFNULL(sub_fax_number, ''), '\r\n' , '\n' )," +
                            "file_output_flg, REPLACE( IFNULL(hand_memo, ''), '\r\n' , '\n' ), REPLACE( IFNULL(auto_memo, ''), '\r\n' , '\n' ), REPLACE( IFNULL(memo1, ''), '\r\n' , '\n' ), " +
                            "REPLACE( IFNULL(memo2, ''), '\r\n' , '\n' ), card_owner_id, publish_status, approval_status, old_card_flg, create_date, REPLACE( IFNULL(update_date, ''), '\r\n' , '\n' ), operater_id," +
                            "REPLACE( IFNULL(delet_date, ''), '\r\n' , '\n' ), delete_flg " +
                            "FROM card_info INTO OUTFILE '"+ file.getAbsoluteFile().toString().replace("\\", "/") +"' FIELDS TERMINATED BY ',' ENCLOSED BY '\"' LINES TERMINATED BY '\r\n'";
            
            LOG.debug("exportCSV DB Query : ", sqlExport);
            Statement stmt = connection.createStatement();
            stmt.execute(sqlExport);
        }
        catch (Exception e)
        {
            LOG.debug("Exception : ", e.getMessage());
            System.out.println("exportCSV Exception: " + e.getMessage());
        }
    }
    
    public boolean backupDB(String dbName, String dbUserName, String dbPassword, String path) {
        String DATE_FORMAT = "yyyyMMdd";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        String fileFormat = sdf.format(new Date()) + "_db_back.sql";

        File file = new File(path);
        if(!file.exists())
            file.mkdirs();
        
        //String absolutePath = file.getAbsolutePath().replace("\\", "/");
        String executeCmd = "mysqldump -u " + dbUserName + " -p" + dbPassword + " --add-drop-database -B " + dbName + " -r " + file.getAbsolutePath() + "/" + fileFormat;
        System.out.println(executeCmd);
        
        Process runtimeProcess;
        try {
 
            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();
 
            if (processComplete == 0) {
                System.out.println("Backup created successfully");
                return true;
            } else {
                System.out.println("Could not create the backup");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
 
        return false;
    }
    
    /*
    public void exportData(Connection conn, String folderName) throws FileNotFoundException, UnsupportedEncodingException {
        //FileWriter fw ;
        try{
            Statement st = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            st.setFetchSize(Integer.MAX_VALUE);

            //this query gets all the tables in your database(put your db name in the query)
            ResultSet res = st.executeQuery("SELECT table_name FROM INFORMATION_SCHEMA.TABLES WHERE table_schema = 'new_ecard' ");

            //Preparing List of table Names
            List <String> tableNameList = new ArrayList<String>();
            while(res.next())
            {
                tableNameList.add(res.getString(1));
            }

            //path to the folder where you will save your csv files
            //String filename = "D:/db2csv/";

            //star iterating on each table to fetch its data and save in a .csv file
            for(String tableName:tableNameList){
                //int k=0; int j=1;
                
                System.out.println(tableName);

                //List<String> columnsNameList  = new ArrayList<String>();

                //select all data from table
                res = st.executeQuery("select * from xcms."+tableName);

                //colunm count is necessay as the tables are dynamic and we need to figure out the numbers of columns
                int colunmCount = getColumnCount(res);

                 try {
                    String DATE_FORMAT = "yyyyMMdd";
                    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
                    String fileFormat = tableName + "_" + sdf.format(new Date()) + "_db_back.csv";

                    File file = new File(folderName + "/" + fileFormat);
                    FileOutputStream fileStream = new FileOutputStream(file);
                    OutputStreamWriter fw = new OutputStreamWriter(fileStream, "UTF-8");
                    
                    //this loop is used to add column names at the top of file , if you do not need it just comment this loop
                    for(int i=1 ; i<= colunmCount ;i++)
                    {
                        fw.append(res.getMetaData().getColumnName(i));
                        fw.append(",");
                    }

                    fw.append(System.getProperty("line.separator"));

                    while(res.next())
                    {
                        for(int i=1;i<=colunmCount;i++)
                        {
                            //you can update it here by using the column type but i am fine with the data so just converting 
                            //everything to string first and then saving
                            if(res.getObject(i)!=null)
                            {
                                String data= res.getObject(i).toString();
                                fw.append(data) ;
                                fw.append(",");
                            }
                            else
                            {
                                String data= "null";
                                fw.append(data) ;
                                fw.append(",");
                            }
                        }
                        //new line entered after each row
                        fw.append(System.getProperty("line.separator"));
                    }

                    fw.flush();
                    fw.close();

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            conn.close();
        }
        catch(SQLException ex){
            System.err.println("SQLException information");
        }
    }
    
    //to get numbers of rows in a result set 
    public static int  getRowCount(ResultSet res) throws SQLException
    {
          res.last();
          int numberOfRows = res.getRow();
          res.beforeFirst();
          return numberOfRows;
    }
 
    //to get no of columns in result set
    public static int  getColumnCount(ResultSet res) throws SQLException
    {
        return res.getMetaData().getColumnCount();
    }*/
    
    public static void writeFileToSCP(String savefolder, File file) throws SftpException, FileNotFoundException{
        System.out.println("savefolder : " + savefolder);
        ch.ethz.ssh2.Connection conn = null;
        try{
            conn = new ch.ethz.ssh2.Connection(BatchConstants.scpHostName);
            conn.connect();
            boolean result = conn.authenticateWithPassword(BatchConstants.scpUserName, BatchConstants.scpPassword);
            
            if(result){
                System.out.println("Connected to SCP successfully");
                
                byte[] bytes = ConvertByteArrayUtil.readBytesFromFile(file);
                
                SCPClient scp = conn.createSCPClient();
                scp.put(bytes, file.getName(), savefolder);
            }
            System.out.println("Export file success...");
        } catch (Exception e) {
            LOG.error("writeImage Exception:" + e.getMessage(), ExportCSVUtil.class);
            System.out.println("writeFileToSCP Exception : " + e.getMessage());
        }
        finally{
            conn.close();
        }
    }
    
    public static void writeFileToSCPByJSch(String savefolder, File file) throws SftpException, FileNotFoundException{
        JSch jsch = new JSch();
        Session session = null;
        try {
            session = jsch.getSession(BatchConstants.scpUserName, BatchConstants.scpHostName, BatchConstants.scpPort);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword(BatchConstants.scpPassword);
            session.connect();
            System.out.println("Connected to session successfully");
            Channel channel = session.openChannel("sftp");
            channel.connect();
            System.out.println("Connected to Channel successfully");
            ChannelSftp sftpChannel = (ChannelSftp) channel;
            System.out.println("savefolder : " + savefolder);
            // this will read file with the name test.txt and write to remote directory
            sftpChannel.cd(savefolder);
            sftpChannel.put(new FileInputStream(file), file.getName()); 
            
            System.out.println("Backup DB to Channel successfully");
            sftpChannel.exit();
            session.disconnect();
        } catch (JSchException e) {
            System.out.println("writeFileToSCPByJSch Exception : " + e.getMessage());
        }
    }
}
