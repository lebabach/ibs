/*
 * UploadFileUtil class
 */
package com.ecard.webapp.util;


import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;

import com.ecard.webapp.vo.NotificationOfUserVO;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import sun.misc.BASE64Decoder;


/**
 *
 * @author vinhla
 */
public class UploadFileUtil {
    
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(UploadFileUtil.class);
    public static String localFileUploaded = System.getProperty("user.dir") + "/upload";
    private static String saveFileUploaded = "/data/photo/card";
	private static String saveFileCSV = System.getProperty("user.dir") + "/csv";
    private static Connection conn = null;
    private static String lostImageFileUploaded = "/data/backup/lost_image";
    
	public static String getImageFileFromSCP(String fileNameFromDB, String scpHostName, String scpUser, String scpPassword, Integer scpPort) {
		String fileNameBase64 = processingCard(fileNameFromDB, scpHostName, scpUser, scpPassword, scpPort);
		return fileNameBase64;
	}
    private static String processingCard(String fileNameFromDB, String scpHostName, String scpUser, String scpPassword, Integer scpPort){
    	String fileNameBase64 = "";
    	try{
			conn = new Connection(scpHostName);
			ByteArrayOutputStream outputStream = null;
			InputStream inputStream = null;
			try {
				conn.connect();
				boolean result = conn.authenticateWithPassword(scpUser, scpPassword);

				outputStream = new ByteArrayOutputStream();

				if (result) {
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
		       
    	} catch (Exception ex){
			LOG.error("Error processing card image: " + ex.getMessage());
		}
    	 return fileNameBase64;
    }
    
    public static List<com.ecard.core.vo.CardInfo> getImageFileFromSCP(List<com.ecard.core.vo.CardInfo> files, String scpHostName, String scpUser, String scpPassword, Integer scpPort) {
		return processingCard(files, scpHostName, scpUser, scpPassword, scpPort);
	}
    private static List<com.ecard.core.vo.CardInfo> processingCard(List<com.ecard.core.vo.CardInfo> files, String scpHostName, String scpUser, String scpPassword, Integer scpPort){
    	try{
			conn = new Connection(scpHostName);
			try {
				conn.connect();
				boolean result = conn.authenticateWithPassword(scpUser, scpPassword);

				
				if (result) {
					SCPClient scpClient = conn.createSCPClient();
					files.forEach(file->{
	                	try {
	                		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
							scpClient.get(saveFileUploaded + "/" + file.getImageFile(), outputStream);
							InputStream inputStream  = new ByteArrayInputStream(outputStream.toByteArray());
			                
			                BufferedImage img = ImageIO.read(inputStream);
			                String fileNameBase64 = encodeToString(img, "jpg");
			                file.setCardBackImgFile(fileNameBase64);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                });
				}
			} catch (Exception e) {
				LOG.debug("getImageFileFromSCP : " + e.getMessage());
			} finally {
				conn.close();
			}
		       
    	} catch (Exception ex){
			LOG.error("Error processing card image: " + ex.getMessage());
		}
    	 return files;
    }
    
    public static List<NotificationOfUserVO> getImageFileFromSCPForNotification(List<NotificationOfUserVO> files, String scpHostName, String scpUser, String scpPassword, Integer scpPort) {
		return processingCardForNotification(files, scpHostName, scpUser, scpPassword, scpPort);
	}
    
    private static List<NotificationOfUserVO> processingCardForNotification(List<NotificationOfUserVO> files, String scpHostName, String scpUser, String scpPassword, Integer scpPort){
    	try{
			conn = new Connection(scpHostName);
			try {
				conn.connect();
				boolean result = conn.authenticateWithPassword(scpUser, scpPassword);

				
				if (result) {
					SCPClient scpClient = conn.createSCPClient();
					files.forEach(file->{
	                	try {
	                		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
							scpClient.get(saveFileUploaded + "/" + file.getImage(), outputStream);
							InputStream inputStream  = new ByteArrayInputStream(outputStream.toByteArray());
			                
			                BufferedImage img = ImageIO.read(inputStream);
			                String fileNameBase64 = encodeToString(img, "jpg");
			                file.setImage(fileNameBase64);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                });
				}
			} catch (Exception e) {
				LOG.debug("getImageFileFromSCP : " + e.getMessage());
			} finally {
				conn.close();
			}
		       
    	} catch (Exception ex){
			LOG.error("Error processing card image: " + ex.getMessage());
		}
    	 return files;
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
        //OutputStreamWriter out = new OutputStreamWriter(bos, "UTF-8");
        
            ImageIO.write(image, type, bos);
            byte[] imageBytes = bos.toByteArray();
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
    
    public static boolean writeImage(String data, String fileName, String scpHostName, String scpUser, String scpPassword, double rote) throws IOException{
        try {
            conn = new Connection(scpHostName);
            conn.connect();
            boolean result = conn.authenticateWithPassword(scpUser, scpPassword);
        
			if (result) {
				if (!"".equalsIgnoreCase(fileName)) {
					// bach.le Image rote
					double rotation = RoteImage.getRotation(rote);
					SCPClient scp = conn.createSCPClient();
					byte[] imgBytes = RoteImage.roteImageWithArc(data, rotation);
					if (imgBytes != null) {
						scp.put(imgBytes, fileName, saveFileUploaded);
						return true;
					} else {
						return false;
					}
				}
				return true;
			}
        } catch (Exception e) {
            LOG.error("writeImage Exception:" + e.getMessage());
        }
        return false;
    }
    
    public static String randomFileNameJPG() {
        return StringUtilsHelper.randomCode(6) + "-" + String.valueOf(System.currentTimeMillis()) + ".jpg";
    }

    public static String randomFileName(String ext) {
        return StringUtilsHelper.randomCode(6) + "-" + String.valueOf(System.currentTimeMillis()) + "." + ext;
    }

    public FileUploadModel uploadImage(String data, String scpHostName, String scpUser, String scpPassword, double rote) throws IOException{
        
		FileUploadModel fileUpload = new FileUploadModel();
		String fileName = randomFileNameJPG();

		File dir = getCurentPath();
		fileName = getFileName(dir.getAbsolutePath(), fileName, true);
		if (StringUtils.isNotEmpty(fileName)) {
			boolean status = writeImage(data, fileName, scpHostName, scpUser, scpPassword, rote);
			fileUpload.setStatus(status);
		}
		fileUpload.setFile(dir);
		// fileUpload.setFileName(fullPath + "/" + fileName);
		fileUpload.setFileName(fileName);

		return fileUpload;
    }
    
    public static FileUploadModel uploadImageDefault(String data, String fileNameDefault, String scpHostName, String scpUser, String scpPassword, double rote) throws IOException{
        
		FileUploadModel fileUpload = new FileUploadModel();
		//String fileName = randomFileNameJPG();

		if (StringUtils.isNotEmpty(fileNameDefault)) {
			boolean status = writeImage(data, fileNameDefault, scpHostName, scpUser, scpPassword, rote);
			fileUpload.setStatus(status);
		}

		return fileUpload;
    }
    
    public static int writeLostImage(String data, String fileName) throws IOException{
    	String absoluteFilePath = lostImageFileUploaded + File.separator + fileName;
    	try{
        	//ImageIO.write(decodeToImage(data), "jpg",new File(absoluteFilePath));
        	ImageIO.write(decodeToImage(data), "jpg",new File("E:\\images\\"+fileName));
    	}catch(Exception e){
    		e.printStackTrace();
    		System.out.println("=================error: write images to lost image folder");
    		return 3;
    	}
        return 0;
    }
    
    public static FileUploadModel overrideImage(String data, String scpHostName, String scpUser, String scpPassword, double rote,String fileName) throws IOException{
        
        FileUploadModel fileUpload = new FileUploadModel();
        if (StringUtils.isNotEmpty(fileName)) {
            boolean status = writeImage(data, fileName, scpHostName, scpUser, scpPassword, rote);
            fileUpload.setStatus(status);
        }
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
    
    public static byte[] getFileCSVFromSCP(String fileNameFromDB, String scpHostName, String scpUser, String scpPassword, Integer scpPort) {    	
    	byte[] bytes = new byte[16384];
    	conn = new Connection(scpHostName);
        ByteArrayOutputStream outputStream = null;
        InputStream inputStream = null;
        try{
            conn.connect();
            boolean result = conn.authenticateWithPassword(scpUser, scpPassword);
            
            outputStream = new ByteArrayOutputStream();
            
            if(result){
                SCPClient scpClient = conn.createSCPClient();
                scpClient.get(saveFileCSV + "/" + fileNameFromDB, outputStream);
                
                inputStream = new ByteArrayInputStream(outputStream.toByteArray());
                bytes = IOUtils.toByteArray(inputStream);
                
            }                        
        } catch (Exception e) {
            LOG.debug("getFileCSVFromSCP : " + e.getMessage());
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
        return bytes;
    }
    
    /**
     * Encode image to string
     * @param image The image to encode
     * @param type jpeg, bmp, ...
     * @return encoded string
     */
    
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
