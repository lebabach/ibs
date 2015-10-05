/*
 * UploadFileUtil class
 */
package com.ecard.api.controller.helper;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.ConnectionInfo;
import ch.ethz.ssh2.SCPClient;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author vinhla
 */
public class UploadFileUtil {
    
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(UploadFileUtil.class);
    public static String localFileUploaded = System.getProperty("user.dir") + "/upload";
    private static final String saveFileUploaded = "/data/photo/card";

    private static Connection conn = null;
    private static final Session session = null;
    private static final Channel channel = null;
    private static final ChannelSftp sftpChannel = null;
        
    public static String getImageFileFromSCP(String fileNameFromDB, String scpHostName, String scpUser, String scpPassword, Integer scpPort) {
        String fileNameBase64 = "";
        conn = new Connection(scpHostName);
        ByteArrayOutputStream outputStream = null;
        InputStream inputStream = null;
        try{
            conn.connect();
            boolean result = conn.authenticateWithPassword(scpUser, scpPassword);
            
            outputStream = new ByteArrayOutputStream();
            
            if(result){
                SCPClient scpClient = conn.createSCPClient();
                scpClient.get(saveFileUploaded + "/" + fileNameFromDB, outputStream);
                
                inputStream = new ByteArrayInputStream(outputStream.toByteArray());
                
                BufferedImage img = ImageIO.read(inputStream);
                fileNameBase64 = encodeToString(img, "jpg");
            }                        
        } catch (Exception e) {
            LOG.debug("getImageFileFromSCP : " + e.getMessage());
        } finally {
            conn.close();        	
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                LOG.debug("Can not close stream file");
            }
        }
        return fileNameBase64;
    }
    
    /**
     * Encode image to string
     * @param image The image to encode
     * @param type jpeg, bmp, ...
     * @return encoded string
     */
    public static String encodeToString(BufferedImage image, String type) {
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            ImageIO.write(image, type, bos);
            byte[] imageBytes = bos.toByteArray();

			// BASE64Encoder encoder = new BASE64Encoder();
			// imageString = encoder.encode(imageBytes);
            Base64 encoder = new Base64();
            imageString = encoder.encodeToString(imageBytes);

            bos.close();
        } catch (IOException e) {
            LOG.debug("encodeToString : "+ e.getMessage(), UploadFileUtil.class);
        }
        return imageString;
    }
            
    public static String encodeBase64(String data){
        byte[] byteArray = data.getBytes();
        
        Base64 decoder = new Base64();
        byte[] imgBytes = decoder.encode(byteArray);
        return new String(imgBytes);
    }
    
    private boolean writeImage(String data, String fileName, String scpHostName, String scpUser, String scpPassword) throws IOException{
        try {
            conn = new Connection(scpHostName);
            ConnectionInfo info = conn.connect();
            boolean result = conn.authenticateWithPassword(scpUser, scpPassword);
        
            if(result){
                if (!"".equalsIgnoreCase(fileName)) {
                	BufferedImage image64=base64StringToImg(data);
                	BufferedImage bImgs=resize(image64, image64.getWidth(null), image64.getHeight(null),40);
                	SCPClient scp = conn.createSCPClient();
                	if(bImgs!=null){
            			ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    	ImageIO.write(bImgs, "jpg", baos );
                    	baos.flush();
                    	byte[] imgBytes = baos.toByteArray();
                        
                        scp.put(imgBytes, fileName, saveFileUploaded);
                        return true;
            		}else{
            			return false;
            		}
                }
                return true;
            }
            else{
                return false;
            }
        } catch (Exception e) {
            LOG.error("writeImage Exception:" + e.getMessage(), UploadFileUtil.class);
            return false;
        }
        finally{
            conn.close();
        }
    }

    public static String randomFileNameJPG() {
        return StringUtilsHelper.randomCode(6) + "-" + String.valueOf(System.currentTimeMillis()) + ".jpg";
    }

    public static String randomFileName(String ext) {
        return StringUtilsHelper.randomCode(6) + "-" + String.valueOf(System.currentTimeMillis()) + "." + ext;
    }

    public FileUploadModel uploadImage(String data, String scpHostName, String scpUser, String scpPassword) throws IOException{
        
        FileUploadModel fileUpload = new FileUploadModel();
        String fileName = randomFileNameJPG();
       
        File dir = getCurentPath();
        fileName = getFileName(dir.getAbsolutePath(), fileName, true);
        if (!"".equalsIgnoreCase(fileName)) {
            boolean status = writeImage(data, fileName, scpHostName, scpUser, scpPassword);
            fileUpload.setStatus(status);
        }
        //String fullPath =dir.getAbsolutePath().replace(saveFileUploaded + "/", "") ;
        fileUpload.setFile(dir);
        //fileUpload.setFileName(fullPath + "/"+fileName);
        fileUpload.setFileName(fileName);
        
        return fileUpload;
    }
    
public FileUploadModel uploadImageDefault(String data, String fileNameDefault, String scpHostName, String scpUser, String scpPassword) throws IOException{
        
		FileUploadModel fileUpload = new FileUploadModel();
		//String fileName = randomFileNameJPG();

		if (!"".equalsIgnoreCase(fileNameDefault)) {
			boolean status = writeImage(data, fileNameDefault, scpHostName, scpUser, scpPassword);
			fileUpload.setStatus(status);
		}

		return fileUpload;
    }

    private void uploadFile(MultipartFile multipartFile, File dir, String fileName, FileUploadModel fileUpload) {
        try {
            multipartFile.transferTo(new File(dir.getAbsolutePath() + File.separator + fileName));
            fileUpload.setStatus(true);
            fileUpload.setMessage("Upload file name :" + fileName + " to success.");
        } catch (IOException ex) {
            fileUpload.setMessage(ex.getMessage());
            LOG.error("uploadFile IOException: {}:", ex);
        } catch (IllegalStateException ex) {
            fileUpload.setMessage(ex.getMessage());
            LOG.error("uploadFile IllegalStateException: {}:", ex);
        }
    }

    public FileUploadModel uploadFile(MultipartFile multipartFile, String pathName) {
        FileUploadModel fileUpload = new FileUploadModel();
        String fileName = multipartFile.getOriginalFilename();
        LOG.debug("fileUpload.getOriginalFilename() : {}", fileName);
        File dir = getPath(pathName);

        fileName = getFileName(dir.getAbsolutePath(), fileName, false);
        if (!"".equalsIgnoreCase(fileName)) {
            uploadFile(multipartFile, dir, fileName, fileUpload);
        }
        LOG.debug("fileUpload saved : {}", fileName);
        fileUpload.setFile(dir);
        fileUpload.setFileName(fileName);
        return fileUpload;
    }

    public static File getCurentPath() {
        Date date = new Date();
        String nameYear = new SimpleDateFormat("yyyy").format(date);
        File dir = new File(saveFileUploaded + File.separator + nameYear);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String nameMonth = new SimpleDateFormat("MM").format(date);
        dir = new File(saveFileUploaded + File.separator + nameYear + File.separator + nameMonth);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }

    public static File getPath(String name) {
        File dir = new File(saveFileUploaded + File.separator + name);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }

    public String getFileName(String path, String fileName, boolean overWrite) {
        boolean status = true;
        Long i = new Long(1);
        fileName = fileName.replaceAll("[^A-Za-z0-9_\\.]", "-");
        if (!overWrite) {
            File dir = new File(path + File.separator + fileName);
            while (status) {
                if (dir.exists()) {
                    fileName = i.toString() + "-" + fileName;
                    dir = new File(path + File.separator + fileName);
                } else {
                    status = false;
                }
            }
        }
        return fileName;
    }

    public static String makeRandomFileName(String filename) {
        return System.currentTimeMillis() + filename.substring(filename.lastIndexOf(".")).toLowerCase();
    }

    public static boolean deleteFolder(String pathName) throws FileNotFoundException {
        File dir = getPath(pathName);
        if (dir.exists()) {
            return deleteRecursive(dir);
        }
        return false;
    }

    private static boolean deleteRecursive(File path) throws FileNotFoundException {
        if (!path.exists()) {
            throw new FileNotFoundException(path.getAbsolutePath());
        }
        boolean ret = true;
        if (path.isDirectory()) {
            for (File f : path.listFiles()) {
                ret = ret && deleteRecursive(f);
            }
        }
        return ret && path.delete();
    }
    
    public static BufferedImage resize(BufferedImage img, int newW, int newH, int reduce) { 
		newW = newW - newW * reduce / 100;
		newH = newH - newH * reduce / 100;
		Image tmp = img.getScaledInstance(newW, newH, img.TYPE_INT_ARGB);
		BufferedImage dimg = new BufferedImage(newW, newH, img.getType());
		Graphics2D g2d = dimg.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();

		return dimg;
	}   
	
    public static BufferedImage base64StringToImg(final String base64String) {
	    try {
	        return ImageIO.read(new ByteArrayInputStream( Base64.decodeBase64(base64String)));
	    } catch (final IOException ioe) {
	        throw new UncheckedIOException(ioe);
	    }
	}
}
