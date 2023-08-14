package com.kerem.userman.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.kerem.userman.dto.EmailPayload;

import java.util.Properties;

public class SendingEmailUtils {
	public static final String senderEmail = "keremkeskin.test@gmail.com";
	public static final String senderPassword = "xprhosrbqqpyldnw";
	
	
	public static boolean sendingEmail(EmailPayload emailPayload) {
	    Properties properties = new Properties();
	    properties.put("mail.smtp.host", "smtp.gmail.com");
	    properties.put("mail.smtp.port", "587");
	    properties.put("mail.smtp.auth", "true");
	    properties.put("mail.smtp.starttls.enable", "true");
	    properties.put("mail.smtp.user", senderEmail);
	    properties.put("mail.smtp.password", senderPassword);

	    Session session = Session.getInstance(properties, new Authenticator() {
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(senderEmail, senderPassword);
	        }
	    });

	    Message message = new MimeMessage(session);
	    
	    try {
	    	message.setFrom(new InternetAddress(senderEmail));
		    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailPayload.getToEmail()));
		    message.setSubject(emailPayload.getTitle());
		    message.setText(emailPayload.getContext());

		    Transport.send(message);
		    
		    return true;
	    } catch (Exception e) {
	    	System.out.println(e.getMessage());
	    	return false;
	    }
	}
}
