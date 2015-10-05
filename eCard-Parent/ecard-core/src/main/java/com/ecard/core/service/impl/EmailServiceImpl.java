/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecard.core.service.impl;

import com.ecard.core.dao.EmailDAO;
import com.ecard.core.model.HistorySendEmail;
import com.ecard.core.service.EmailService;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

/**
 *
 * @author HienTu
 */
@Service("emailService")
@Transactional

public class EmailServiceImpl implements EmailService{
    @Autowired
    JavaMailSender mailSender;
    
    @Autowired
    TemplateEngine templateEngine;
    
    @Autowired
    EmailDAO emailDAO;
    
    public void sendMailWithInline(final String recipientName, final String recipientEmail) throws MessagingException{
        final Context ctx = new Context();
        ctx.setVariable("name", recipientName);
        ctx.setVariable("subscriptionDate", new Date());
        ctx.setVariable("hobbies", Arrays.asList("Cinema", "Sports", "Music"));

        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message =
            new MimeMessageHelper(mimeMessage, true, "UTF-8"); // true = multipart
        message.setSubject("Example HTML email with inline image");
        message.setFrom("IBS");
        message.setTo(recipientEmail);
               
        // Create the HTML body using Thymeleaf
        final String htmlContent = this.templateEngine.process("mailInline", ctx);
        
        message.setText(htmlContent, true); 
        
        this.mailSender.send(mimeMessage);
    }
    
    public void sendMail(final String mailFrom, final String mailTo, final String subject, Context ctx, String htmlFile) throws MessagingException {
        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8"); // true = multipart
        
        message.setSubject(subject);
        message.setFrom(mailFrom);
        message.setTo(mailTo);
                       
        // Create the HTML body using Thymeleaf
        final String htmlContent = this.templateEngine.process(htmlFile, ctx);
        
        message.setText(htmlContent, true); 
        
        this.mailSender.send(mimeMessage);
    }
    
    public void sendWebMail(final String mailFrom, final String mailTo, final String subject, WebContext ctx, String htmlFile) throws MessagingException {
    	ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
    	templateResolver.setTemplateMode("XHTML");
        templateResolver.setPrefix("/mail/");
        templateResolver.setSuffix(".html");
        templateResolver.setCacheTTLMs(3600000L);
        
        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8"); // true = multipart
        
        message.setSubject(subject);
        message.setFrom(mailFrom);
        message.setTo(mailTo);
                       
        this.templateEngine.setTemplateResolver(templateResolver);
        // Create the HTML body using Thymeleaf
        final String htmlContent = this.templateEngine.process(htmlFile, ctx);
        
        message.setText(htmlContent, true); 
        
        this.mailSender.send(mimeMessage);
    }
    public void sendMailContact(final String mailFrom, final List<String> mailTo, final String subject, Context ctx, String htmlFile) throws MessagingException {
    	final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8"); // true = multipart
        
        message.setSubject(subject);
        message.setFrom(mailFrom);
        message.setTo(mailTo.toArray(new String[mailTo.size()]));              
        // Create the HTML body using Thymeleaf
        final String htmlContent = this.templateEngine.process(htmlFile, ctx);
        
        message.setText(htmlContent, true); 
        
        this.mailSender.send(mimeMessage);
    }

    public List<HistorySendEmail> getAllEmail(){
    	return emailDAO.getAllEmail();
    }
}
