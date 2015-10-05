/**
 * 
 */
package com.ecard.webapp.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;

import javax.mail.MessagingException;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.context.Context;

import com.ecard.core.model.UserInfo;
import com.ecard.core.service.EmailService;
import com.ecard.core.service.UserInfoService;
import com.ecard.webapp.constant.CommonConstants;

/**
 * @author nhat.nguyen
 *
 */
@Controller
public class UpdatePasswordController {
	
	@Autowired
    UserInfoService userInfoService;
	
	@Autowired
    EmailService emailService;
	
	@RequestMapping("/recoverpass*") 
    public ModelAndView ibsAccount() {  
        return new ModelAndView("ibsaccount");  
    }
    
    @RequestMapping("/checkemail*")
    @ResponseBody
    public JSONObject checkMail(@RequestParam(value = "email", required = false) String email) {  
		String password = new BigInteger(130, new SecureRandom()).toString(32);
		JSONObject obj = new JSONObject();

		String result;
		UserInfo userInfo = userInfoService.findUserByEmail(email);
		if (userInfo.getUserId() != null) {
			userInfoService.updatePassword(userInfo.getUserId(), new BCryptPasswordEncoder().encode(password));
			if (userInfo.getUseStopFlg() == 2) {
				userInfoService.updateUseStopFlg(userInfo.getUserId(), 0);
			}
			result = password;
		} else {
			result = "";
		}
		obj.put("status", result);
		obj.put("useStopFlg", userInfo.getUseStopFlg());
		obj.put("leaveFlg", userInfo.getLeaveFlg());
		obj.put("useDate", userInfo.getUseDate());
		obj.put("endDate", userInfo.getEndDate());
		return obj;
    }
    
    @RequestMapping("/resetPassword*")
    @ResponseBody
    public String restetPassword(@RequestParam(value = "email", required = false) String email,
    							  @RequestParam(value = "userId", required = false) Integer userId	) {  
		String password = new BigInteger(130, new SecureRandom()).toString(32);
		// Send new password to email
		String result;
		UserInfo userInfo = userInfoService.findUserByEmail(email);
		if (userInfo.getUserId() != null) {
			result = "New password was send to email";
			// Save new password to db
			userInfoService.updatePassword(userId, new BCryptPasswordEncoder().encode(password));
		} else {
			result = "New password wasn't send to email";
		}
		return result;
    }
    
	
    @RequestMapping(value = "/sendMailWebapp", method = RequestMethod.POST)
    @ResponseBody
    public String sendMailWebapp(@RequestParam("recipientName") final String recipientName,@RequestParam("password") final String password,
                                 @RequestParam("recipientEmail") final String recipientEmail) throws MessagingException, IOException {
		// emailService.sendMailWithInline(recipientName, recipientEmail);
    	UserInfo  userInfo = userInfoService.findUserByEmail(recipientEmail);    	
    	userInfoService.updateFisrtLogin(userInfo.getUserId(), 0);
		Context ctx = new Context();		
		ctx.setVariable("password", password);
		ctx.setVariable("recipientEmail", recipientEmail);
		emailService.sendMail(CommonConstants.USER_FROM_MAIL, recipientEmail, CommonConstants.TITLE_RECOVERPASS_MAIL, ctx, "recoverpass");
		return "Success";
    }
	
}
