/*
 * UploadFileUtil class
 */
package com.ecard.core.batch.util;


import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


/**
 *
 * @author vinhla
 */
public class UploadFileUtil {
    
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(UploadFileUtil.class);
    public static String localFileUploaded = System.getProperty("user.dir") + "/upload";
    private static String saveFileUploaded = "/data/photo/card";
    //private static String saveFileCSV = System.getProperty("user.dir") + "/csv";
    private static Connection conn = null;
        
    public static String encodeToString(BufferedImage image, String type) {
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
        //OutputStreamWriter out = new OutputStreamWriter(bos, "UTF-8");
        
            ImageIO.write(image, type, bos);
            byte[] imageBytes = bos.toByteArray();
            BASE64Encoder encoder = new BASE64Encoder();
            imageString = encoder.encode(imageBytes);

            bos.close();
        } catch (IOException e) {
            LOG.debug("encodeToString : "+ e.getMessage(), UploadFileUtil.class);
        }
        return imageString;
    }
    
    public static BufferedImage decodeToImage(String imageString) {

        BufferedImage image = null;
        byte[] imageByte;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            imageByte = decoder.decodeBuffer(imageString);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }
    
    private static boolean writeImage(String data, String fileName, String scpHostName, String scpUser, String scpPassword) throws IOException{
        try {
            conn = new Connection(scpHostName);
            conn.connect();
            boolean result = conn.authenticateWithPassword(scpUser, scpPassword);
        
            if(result){
                if (StringUtils.isNotEmpty(fileName)) {
                    BufferedImage bImgs=decodeToImage(data);
                    SCPClient scp = conn.createSCPClient();
                    if (bImgs != null) {
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        ImageIO.write(bImgs, "jpg", baos);
                        baos.flush();
                        byte[] imgBytes = baos.toByteArray();

                        scp.put(imgBytes, fileName, saveFileUploaded);
                        return true;
                    }
                    return false;            		
                }
                return true;
            }
        } catch (Exception e) {
            LOG.error("writeImage Exception:" + e.getMessage(), UploadFileUtil.class);
        }
        finally{
            conn.close();
        }
        return false;
    }
    
    public static FileUploadModel overrideImage(String data, String scpHostName, String scpUser, String scpPassword,String fileName) throws IOException{
        
        FileUploadModel fileUpload = new FileUploadModel();
        if (StringUtils.isNotEmpty(fileName)) {
            boolean status = writeImage(data, fileName, scpHostName, scpUser, scpPassword);
            fileUpload.setStatus(status);
        }
        fileUpload.setFileName(fileName);
        
        return fileUpload;
    }
    
}
