/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecard.core.service;

import java.util.List;

import javax.mail.MessagingException;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.WebContext;

import com.ecard.core.model.HistorySendEmail;

/**
 *
 * @author HienTu
 */
public interface EmailService {
	
    public void sendMailWithInline(final String recipientName, final String recipientEmail) throws MessagingException ;
    
    public void sendMail(final String mailFrom, final String mailTo, final String subject, Context ctx, String htmlFile) throws MessagingException ;
    public void sendWebMail(final String mailFrom, final String mailTo, final String subject, WebContext ctx, String htmlFile) throws MessagingException ;

    public void sendMailContact(final String mailFrom, final List<String> mailTo, final String subject, Context ctx, String htmlFile) throws MessagingException ;

    public List<HistorySendEmail> getAllEmail();

}
