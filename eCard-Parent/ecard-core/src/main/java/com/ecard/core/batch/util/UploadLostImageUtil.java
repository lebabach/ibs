package com.ecard.core.batch.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
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
	
    private String scpHostName;
    private String scpUser;
    private String scpPassword;
    
    private static final String directoryFrom = "/data/backup/lost_image";
    private static final String directoryBackup = "/data/photo/card";
    
    @Value("${sftp.hostname}")
    private String sftpHostName;
    
    public UploadLostImageUtil(){}
    
    public UploadLostImageUtil(String scpHostName, String scpUser, String scpPassword){
    	this.scpHostName = scpHostName;
    	this.scpUser = scpUser;
    	this.scpPassword = scpPassword;
    }
    
	public void uploadLostImageFile() throws JSchException, SftpException{
		//String directoryTo = "ssh://"+ BatchConstants.scpUserName +":"+ BatchConstants.scpPassword +"@"+ BatchConstants.scpHostName + directoryBackup;
		System.out.println("SFTP connection, scpUser="+this.scpUser+", scpHost ="+this.scpHostName);

	    File dir = new File(directoryFrom);
	    File[] files = dir.listFiles();
	    System.out.println("Number of files = "+files.length + ", folder of Local = "+ directoryFrom);
	    for (int i = 0; i < files.length; i++) {
	        File file = files[i];
	        uploadFile(file);
	        System.out.println("Delete File Name= "+ file.getName() + ", file path = "+ file.getAbsolutePath());
    		if(file.exists()){
    			System.out.println("Delete file success");
    			file.delete();
    		} else {
    			System.out.println("Delete file UN success");
    		}
	    }
        System.out.println("Upload lost card image successfull");
	}
	
	public void uploadFile(File file) throws JSchException, SftpException {
		java.util.Properties config = new java.util.Properties();
        config.put("StrictHostKeyChecking", "no");
		JSch ssh = new JSch();
        Session session = ssh.getSession(this.scpUser, this.scpHostName, 22);
        session.setConfig(config);
        session.setPassword(this.scpPassword);
        session.connect();
        System.out.println("folder of SCP = "+ directoryBackup);
        Channel channel = session.openChannel("sftp");
        channel.connect();
        ChannelSftp sftp = (ChannelSftp) channel;
        
        try (FileInputStream fis = new FileInputStream(file)) {
    		sftp.cd(directoryBackup);
    		sftp.put(fis, directoryBackup+"/"+file.getName());
    		    			
    	} catch (FileNotFoundException e) {
    		logger.debug("FileNotFoundException : "+e.getMessage(), UploadLostImageUtil.class);
		} catch (IOException e) {
			logger.debug("IOException : "+e.getMessage(), UploadLostImageUtil.class);
		}
	}
	
	
}
