/*
 * BatchBackupDBManualTasklet
 */
package com.ecard.core.batch.tasklet;

import com.ecard.core.batch.util.ExportCSVUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author vinhla
 */
public class BatchBackupDBManualTasklet {
    private static final Logger logger = LoggerFactory.getLogger(BatchBackupDBAutoTasklet.class);
    
    @Value("${jdbc.second.url}")
    private String dbURL;
    
    @Value("${jdbc.second.username}")
    private String dbUser;
    
    @Value("${jdbc.second.password}")
    private String dbPassword;
    
    @Value("${jdbc.db.name}")
    private String dbName;
    
    public void performAction(String saveFolder) {
        try{
            ExportCSVUtil exportCSVUtil = new ExportCSVUtil();
            //Connection conn = dbConnect.connect(dbURL, dbUser, dbPassword);
            //dbConnect.exportData(conn, saveFolder);
            boolean result = exportCSVUtil.backupDB(dbName, dbUser, dbPassword, saveFolder);
            if(result){
                System.out.println("Backup success. File saved on : "+saveFolder);
            }
            else{
                System.out.println("Backup failed..");
            }
        }
        catch(Exception ex){
            logger.debug("Exception ", ex.getMessage());
        }
    }
}
