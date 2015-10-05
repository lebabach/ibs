/*
 * BatchRestoreDBManualTasklet
 */
package com.ecard.core.batch.tasklet;

import com.ecard.core.batch.contants.BatchConstants;
import com.ecard.core.batch.util.ImportCsvUtil;
import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author vinhla
 */
public class BatchRestoreDBManualTasklet {
    private static final Logger logger = LoggerFactory.getLogger(BatchBackupDBAutoTasklet.class);
    
    @Value("${jdbc.second.username}")
    private String dbUser;
    
    @Value("${jdbc.second.password}")
    private String dbPassword;
    
    @Value("${jdbc.db.name}")
    private String dbName;
    
//    @Value("${scp.hostname}")
//    private String scpHostName;
//    
//    @Value("${scp.user}")
//    private String scpUserName;
//    
//    @Value("${scp.password}")
//    private String scpPassword;
//    
//    @Value("${scp.port}")
//    private int scpPort;
    
    public void performAction() {
        try{
            //Path currentRelativePath = Paths.get("");
            //String folderPath = currentRelativePath.toAbsolutePath().toString();
            //System.out.println("Current relative path is: " + filePath);
        
            //ImportCsvUtil importCsvUtil = new ImportCsvUtil();
            //importCsvUtil.importCSV(folderPath + BatchConstants.saveManualFolder, "card_info");
            File file = ImportCsvUtil.getNewestFile(BatchConstants.saveManualFolder, "sql");
            System.out.println("Path file restore : "+file.getAbsolutePath());
            ImportCsvUtil.restoreDB(dbName, dbUser, dbPassword, file.getAbsolutePath());
        }
        catch(Exception ex){
            logger.debug("Exception ", ex.getMessage());
        }
    }
}
