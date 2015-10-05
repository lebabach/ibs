/*
 * EmailHelper
 */
package com.ecard.api.controller.helper;

import java.util.Locale;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 *
 * @author vinhla
 */
public class EmailHelper {

    /* 
     * Send HTML mail when register card success
     */
    public void sendMailRegisterCard(final String recipientFrom, final String recipientTo, String subject, final Locale locale) throws MessagingException{
        
        // Prepare the evaluation context
        final Context ctx = new Context(locale);
        
        JavaMailSender mailSender = new JavaMailSenderImpl();
        TemplateEngine templateEngine = new TemplateEngine();
        
        // Prepare message using a Spring helper
        final MimeMessage mimeMessage = mailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
        message.setSubject(subject);
        message.setFrom(recipientFrom);
        message.setTo(recipientTo);

        // Create the HTML body using Thymeleaf
        final String htmlContent = templateEngine.process("registerCard.html", ctx);
        message.setText(htmlContent, true /* isHtml */);
        //message.setText("????????{??}???{???????}?{?????????}???????");
        
        // Send email
        mailSender.send(mimeMessage);

    }
}
