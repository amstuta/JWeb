package com.jweb.mail;

import java.util.List;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.http.HttpServletRequest;

/*
 *  sudo systemctl start postfix.service
 *  journalctl --since "2015-12-11 15:32"
 */

public class SendMail {
	
	public void send(HttpServletRequest request, List<String> clients, String adminID, String msg) {

		// Sender's email ID needs to be mentioned
		String from = adminID + "@jweb.com";

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.setProperty("mail.smtp.host", "localhost");
		properties.setProperty("mail.smtp.port", "4000");

		// Get the default Session object.
		//Session session = Session.getDefaultInstance(properties);
		Session session = Session.getInstance(properties);

		for (String to : clients)
		{
			try{
				// Create a default MimeMessage object.
				MimeMessage message = new MimeMessage(session);

				// Set From: header field of the header.
				message.setFrom(new InternetAddress(from));

				// Set To: header field of the header.
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

				// Set Subject: header field
				message.setSubject("JWeb newsletter!");

				// Now set the actual message
				message.setText(msg);

				// Send message
				Transport.send(message);
				
				request.setAttribute("returnSend", "Sent message successfully");
			}
			catch (MessagingException e) {
				request.setAttribute("returnSend", e.getMessage());
			}
		}
	}
}
