/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecard.core.batch.util;

import com.ecard.core.batch.contants.BatchConstants;
import com.ecard.core.model.CardInfo;
import com.ecard.core.model.enums.TableTypeEnum;
import com.ecard.core.service.CardInfoService;
import com.ecard.core.util.DataIndexUtil;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author Windows
 */
public class WriteCardImage {
    private static final Logger logger = LoggerFactory.getLogger(WriteCardImage.class);
    
    @Value("${card.default.base64}")
    private String defaultImage64;
    
    @Value("${scp.hostname}")
    private String scpHostName;
    
    @Value("${scp.user}")
    private String scpUser;
    
    @Value("${scp.password}")
    private String scpPassword;
    
    @Autowired
    CardInfoService cardInfoService;
    
    public void writeCardImage(List<CardInfo> cardInfoList){
		 
		if (CollectionUtils.isEmpty(cardInfoList) || StringUtils.isEmpty(defaultImage64)) {
			return;
		}
		logger.info("Thread start uploading card");
		try {
			
		    ClassLoader classLoader = WriteCardImage.class.getClassLoader();
			File file = new File(classLoader.getResource("MSMINCHO.TTF").getFile());
			Font font = Font.createFont(Font.TRUETYPE_FONT, file);
			
			for (CardInfo cardInfo : cardInfoList) {
				if(cardInfo.equals("card_04.jpg")){
					cardInfo.setImageFile(DataIndexUtil.setPropertyCodeFrom(cardInfo.getCardIndexNo(), "00M",TableTypeEnum.ImageInfor)+".jpg");
					Thread.sleep(1000);
					BufferedImage image = UploadFileUtil.decodeToImage(defaultImage64);
					Graphics g = image.getGraphics();
					Graphics2D g2 = (Graphics2D)g;
					 g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
						        RenderingHints.VALUE_ANTIALIAS_ON);
					font = font.deriveFont(Font.BOLD, 20f);
					//Font font = new Font(Font.SANS_SERIF, Font.BOLD, 20);
					g2.setFont(font);
					g2.setColor(Color.BLACK);
					g2.drawString(cardInfo.getCompanyName()!= null ? StringUtilsHelper.convertToUTF8(cardInfo.getCompanyName()) : StringUtils.EMPTY, BatchConstants.xCooder, BatchConstants.yCooder);
					g2.drawString(cardInfo.getDepartmentName() != null ? StringUtilsHelper.convertToUTF8(cardInfo.getDepartmentName()): StringUtils.EMPTY, BatchConstants.xCooder, BatchConstants.yCooder+ 30);
					g2.drawString(cardInfo.getPositionName() != null ? StringUtilsHelper.convertToUTF8(cardInfo.getPositionName()) : StringUtils.EMPTY, BatchConstants.xCooder, BatchConstants.yCooder+ 60);
					
					g2.drawString(cardInfo.getName() != null ? StringUtilsHelper.convertToUTF8(cardInfo.getName()) : StringUtils.EMPTY,BatchConstants.xCooder + 30, BatchConstants.yCooder + 150);
					g2.dispose();
					UploadFileUtil.overrideImage(UploadFileUtil.encodeToString(image, "jpg"), scpHostName, scpUser, scpPassword, cardInfo.getImageFile());
				}
			}
			
			cardInfoService.updateCardType();
			cardInfoService.updateCardInfoNoIndex(cardInfoList);
		} catch (Exception ex) {
			logger.error("Error upload default card image: " + ex.getMessage());
		}
    }
}
