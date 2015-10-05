package com.ecard.webapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ecard.core.service.BatchBackupAutoService;
import com.ecard.core.service.BatchRestoreService;


@Controller
@RequestMapping("/backupdatas/*")
public class BackupController {
	private static final Logger logger = LoggerFactory.getLogger(BackupController.class);
	
	@Autowired
	BatchBackupAutoService batchBackupAutoService;
	
	@Autowired
	BatchRestoreService batchRestoreService;
	
	@RequestMapping("backup")
	public ModelAndView displayBackup() {			
	  return new  ModelAndView("backup");
    } 
	
	@RequestMapping("backupData")
	@ResponseBody
	public int backupData(@RequestParam(value = "timebackup", required = false) String timebackup) {		
		
		try {
			if (timebackup == null || timebackup.isEmpty()){
				batchBackupAutoService.backupManualDatabase();
			}else{
				batchBackupAutoService.backupAutoDatabase(timebackup);	
			}	
			return 0;
			
		} catch (Exception e) {
			logger.error("Error processing card image: " + e.getMessage());
		}					
		return  1;
    } 
	
	@RequestMapping("restoredata")
	@ResponseBody
	public int restoreData() {
		try {
			batchRestoreService.restoreDB();
			return 0;			
		} catch (Exception e) {
			logger.error("Error processing card image: " + e.getMessage());			
		}					
		return  1;
	}
	
 
}
