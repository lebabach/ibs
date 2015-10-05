package com.ecard.webapp.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.context.Context;

import com.ecard.core.service.ContactNotificationService;
import com.ecard.core.service.EmailService;
import com.ecard.core.vo.ContactNotification;
import com.ecard.webapp.constant.CommonConstants;
import com.ecard.webapp.vo.ContactNotificationVO;

@Controller
@RequestMapping("/contacts/*")
public class ContactNotificationController {
	
	@Autowired
	ContactNotificationService contactNotificationService;
	@Autowired
    EmailService emailService;
	@Value("${mail.server.from}")
    private String fromUser;
	
	@RequestMapping("listcontact")
	public ModelAndView contact(){
		List<ContactNotification> listContactNotification = new ArrayList<>();
		listContactNotification = contactNotificationService.getAllContactNotification();
		BigInteger numberMessageUnread = contactNotificationService.getTotalMailNotRead();
		ContactNotificationVO contactNotificationVO = new ContactNotificationVO(listContactNotification, numberMessageUnread);
		return new ModelAndView("contact","contactNotificationVO",contactNotificationVO);
	}
	
    @RequestMapping("delete")
	@ResponseBody
	public int delete(@RequestParam(value = "inquiryId") int inquiryId) {
		   try {
					contactNotificationService.delete(inquiryId);
		    } catch (Exception e) {
			e.printStackTrace();
			return 1;
		  }
		return 0;
	}
    
    @RequestMapping(value = "displayReply", method = RequestMethod.GET)
  	public ModelAndView displayReply(@RequestParam(value = "inquiryId") int inquiryId) {
    	ContactNotification contactNotification = new ContactNotification();
    	contactNotification = contactNotificationService.getContactNotification(inquiryId);
     	return new ModelAndView("reply","contactNotification",contactNotification);
    	//return new ModelAndView("reply");
    }
    
    
    @RequestMapping(value = "reply",method = RequestMethod.POST)
  	public String displayReply(ContactNotification contactNotification) {
    	contactNotification.setUpdateDate(new Date());
    	boolean result = contactNotificationService.replyMessage(contactNotification);
    	Context ctx = new Context();
    	String answerText = contactNotification.getAnswerText().replaceAll("(\r\n|\n)", " <br />");
		ctx.setVariable("content",answerText);
		try {
			emailService.sendMail(CommonConstants.USER_FROM_MAIL, contactNotification.getEmail(),CommonConstants.TITLE_MAIL, ctx, "mailtocontact");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
    	if(result){
    		return "redirect:listcontact";
    	}
     	return "redirect:displayReply/"+contactNotification.getInquiryId();
    }
    @RequestMapping("displaySendNotification")
	public ModelAndView sendNotification(){
		//List<ContactNotification> listContactNotification = new ArrayList<>();
		//listContactNotification = contactNotificationService.getAllContactNotification();
		return new ModelAndView("displaySendNotification");
    }
}
