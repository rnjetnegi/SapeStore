package com.sapestore.util;

import java.io.IOException;
import java.security.NoSuchProviderException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class EmailGenerator {
	static Properties mailServerProperties;
	static Session getMailSession;
	static MimeMessage generateMailMessage;

	public boolean sendEmail(String name, String email){
		System.out.println("\n 1st ===> setup Mail Server Properties..");
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "25");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");

		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		generateMailMessage = new MimeMessage(getMailSession);
		try {
			generateMailMessage.addRecipient(Message.RecipientType.TO,
					new InternetAddress(email));

			Resource resource = new ClassPathResource("/message_en.properties");
            Properties props = PropertiesLoaderUtils.loadProperties(resource);
			generateMailMessage.setSubject(props.getProperty("Subject"));
			/*String emailBody = "Hello, Welcome to SapeStore! Your profile has been created. Have a nice day"
					+ "<br><br> Regards, <br>SapeStore";*/
			generateMailMessage.setContent(props.getProperty("Message"), "text/html");

			Transport transport = getMailSession.getTransport("smtp");
		
 
	
	
		
 
			// Enter your correct gmail UserID and Password
			// if you have 2FA enabled then provide App Specific Password
			transport.connect("inrelaymail.sapient.com", "kmangla@sapient.com", "!!HalaMadrid07");
			transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
			transport.close();
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(IOException e){
			e.printStackTrace();
		}
		return true;
	}

}
