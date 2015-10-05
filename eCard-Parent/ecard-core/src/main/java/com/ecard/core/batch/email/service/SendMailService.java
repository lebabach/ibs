/*
 * SendMailService
 */
package com.ecard.core.batch.email.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.MailParseException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 *
 * @author vinhla
 */
public class SendMailService {
    
    private JavaMailSender mailSender;
    private SimpleMailMessage simpleMailMessage;

    public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
        this.simpleMailMessage = simpleMailMessage;
    }

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMail(String emailTo, String dear, String content) {

       MimeMessage message = mailSender.createMimeMessage();

       try{
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(simpleMailMessage.getFrom());
            helper.setTo(emailTo);
            helper.setSubject(simpleMailMessage.getSubject());
            helper.setText(String.format(simpleMailMessage.getText(), dear, content));

            //FileSystemResource file = new FileSystemResource("C:\\log.txt");
            //helper.addAttachment(file.getFilename(), file);

         }catch (MessagingException e) {
            throw new MailParseException(e);
         }
         mailSender.send(message);
     }
}
