package com.ecard.core.batch.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.ecard.core.batch.contants.BatchConstants;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class UploadLostImageUtil {
	private static final Logger logger = LoggerFactory.getLogger(UploadLostImageUtil.class);
	
	@Value("${scp.hostname}")
    private String scpHostName;
    
    @Value("${scp.user}")
    private String scpUser;
    
    @Value("${scp.password}")
    private String scpPassword;
    
    private static final String directoryFrom = "/data/backup/lost_image";
    private static final String directoryBackup = "/data/photo/card";
    
	public void uploadLostImageFile() throws JSchException, SftpException{
		//String directoryTo = "ssh://"+ BatchConstants.scpUserName +":"+ BatchConstants.scpPassword +"@"+ BatchConstants.scpHostName + directoryBackup;
		
		java.util.Properties config = new java.util.Properties();
        config.put("StrictHostKeyChecking", "no");

        JSch ssh = new JSch();
        Session session = ssh.getSession(BatchConstants.scpUserName, BatchConstants.scpHostName, 22);
        session.setConfig(config);
        session.setPassword(BatchConstants.scpPassword);
        session.connect();

        Channel channel = session.openChannel("sftp");
        channel.connect();
        ChannelSftp sftp = (ChannelSftp) channel;
        sftp.cd(directoryFrom);
        
        @SuppressWarnings("unchecked")
		Vector<LsEntry> files = sftp.ls("*");
        
        System.out.printf("Found %d files in dir %s%n", files.size(), directoryFrom);
        //ArrayList<String> list = new ArrayList<String>();
        for (LsEntry entry : files) {
            if(entry.getFilename().toLowerCase().endsWith(".jpg")) {
                //list.add(entry.getFilename());
            	File file = new File(directoryFrom+"/"+entry.getFilename());
            	try (FileInputStream fis = new FileInputStream(file)) {
            		sftp.cd(directoryBackup);
            		sftp.put(fis, directoryBackup+"/"+entry.getFilename());
            		
            		if(file.exists())
            			file.delete();
            	} catch (FileNotFoundException e) {
            		logger.debug("FileNotFoundException : "+e.getMessage(), UploadLostImageUtil.class);
				} catch (IOException e) {
					logger.debug("IOException : "+e.getMessage(), UploadLostImageUtil.class);
				}
            }
        }
        System.out.println("Upload lost card image successfull");
        logger.info("Upload lost card image successfull");
        
        channel.disconnect();
        session.disconnect();
	}
}
