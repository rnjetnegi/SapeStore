package com.sapestore.util;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.sapestore.vo.BookVO;

public class ShareEmailGenerator {
	static Properties mailServerProperties;
	static Session getMailSession;
	static MimeMessage generateMailMessage;
	
	public boolean sendEmail(String name, String email,StringBuffer url,BookVO book,String sender){
		System.out.println("\n 1st ===> setup Mail Server Properties..");
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "25");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
		
 
	
	
		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		generateMailMessage = new MimeMessage(getMailSession);
		try {
			generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
			
			generateMailMessage.setSubject("Book Shared by your friend");
			String emailBody = "Dear "+name+",<br>" + "We would like to inform you that this book has been shared to you by your friend"+sender+". The link "
					+ "for the book has been provided below:<br>"+url+".<br><br> The details of book are:<br>"+
					
					"<img src=\"cid:AbcXyz123\">"+"<b>Title:</b>"+book.getBookTitle()+"<b>Author:</b>"+book.getBookAuthor() +"<b>Price:</b>"+book.getBookPrice()
					+"  <br><br> Regards, <br>SapeStore";
			MimeBodyPart bodyPart = new MimeBodyPart();
			bodyPart.setContent(emailBody, "text/html");
			Multipart multipart = new MimeMultipart();
	        multipart.addBodyPart(bodyPart);
	        String contentId="AbcXyz123";
	        
	        MimeBodyPart imagePart = new MimeBodyPart();
            imagePart.setHeader("Content-ID", "<" + contentId + ">");
            imagePart.setDisposition(MimeBodyPart.INLINE);
            
            String imageFilePath="D:"+"\\"+"SapeStore"+"\\"+"sapestore-aug-ggn"+"\\"+"SapeStoreApp"+"\\"+"src"+"\\"+"main"+"webapp"+book.getFullPath();
            
            try {
                imagePart.attachFile(imageFilePath);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            multipart.addBodyPart(imagePart);
			
			generateMailMessage.setContent(multipart);
			
 
			
			Transport transport = getMailSession.getTransport("smtp");
 
			// Enter your correct gmail UserID and Password
			// if you have 2FA enabled then provide App Specific Password
			transport.connect("inrelaymail.sapient.com", "kmangla@sapient.com", "--password--");
			transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
			transport.close();
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;

	}

}
