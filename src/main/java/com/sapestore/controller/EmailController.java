package com.sapestore.controller;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.opensymphony.xwork2.ActionSupport;
import com.sapestore.common.ApplicationConstants;
import com.sapestore.common.SapeStoreLogger;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.service.BookService;
import com.sapestore.service.EditUserService;
import com.sapestore.util.ShareEmailGenerator;
import com.sapestore.vo.BookVO;
import com.sapestore.vo.RegisterVO;

/**
 * This is a controller class for the email functionality on Defaulters Report
 * page.
 *
 * CHANGE LOG VERSION DATE AUTHOR MESSAGE 1.0 20-06-2014 SAPIENT Initial version
 */

@Controller
public class EmailController {

	private final static SapeStoreLogger LOGGER = SapeStoreLogger
			.getLogger(EmailController.class.getName());

	@Autowired
	private EditUserService editUserService;

	@Autowired
	private BookService bookService;

	/**
	 * Sends email to book return defaulters.
	 * 
	 * @param ids
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/shareEmail", method = RequestMethod.POST)
    public void shareEmail(ModelMap modelMap, HttpSession session,
                  HttpServletRequest request,HttpServletResponse response) throws SapeStoreException {

           String to = request.getParameter("emailId");
           String name = request.getParameter("name");
           String isbn = request.getParameter("isbn");
           final SapeStoreLogger LOGGER = SapeStoreLogger
                        .getLogger(RegisterController.class.getName());
           System.out.println(to);
           System.out.println(name);
           System.out.println(isbn);
           StringBuffer url = request.getRequestURL();
           int m=url.lastIndexOf("/");
           int end=url.length();
           System.out.println(m);
           String str="bookDetails?isbn="+isbn;
           StringBuffer url1=url.replace(m+1, end, str);
           System.out.println(url1);
           final ShareEmailGenerator share = new ShareEmailGenerator();
           String loginName = (String) session.getAttribute("userLoginName");
           RegisterVO user = null;
           try {
                  user = editUserService.getUser(loginName); /*
                                                                                             * get the user details
                                                                                             * from database
                                                                                             */
           } catch (SapeStoreException sse) {
                  LOGGER.error(sse.getMessage());
           }
           String sender = user.getFullName();
           BookVO book = bookService.getBookDetailsForDate(isbn);

          
           response.setContentType("text/plain"); // Set content type of the
           // response so that jQuery knows
           // what it can expect.
           response.setCharacterEncoding("UTF-8"); // Encoding Type
           try {
                  response.getWriter().write("true");
           } catch (IOException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
           }
           share.sendEmail(name, to, url1, book, sender);
    }
	
	/**
	  * Sends email to book return defaulters.
	  * @ param ids
	  * @ param modelMap
	  * @ return
	  */
	  @RequestMapping(value = "/sendEmail", method = RequestMethod.GET)
	  public String sendEmail(@RequestParam("emailIds") String ids, ModelMap modelMap) {
		 
	    if (LOGGER.isDebugEnabled()) {
	      LOGGER.debug("sendEmail method: START");
	    }
	   
	    String []sepIds = ids.split(":");
	    for (int i = 0; i < sepIds.length; i++) {
	      String []details = sepIds[i].split("#");
	      String from = "admin@sapestore.com";
	      String host = "inrelaymail.sapient.com";
	      Properties properties = System.getProperties();
	      properties.setProperty("mail.smtp.host", host);
	      Session session = Session.getInstance(properties);
	     
	      try {
	        MimeMessage message = new MimeMessage(session); 
	        message.setFrom(new InternetAddress(from));
	        message.addRecipient(Message.RecipientType.TO, new InternetAddress(details[0]));
	        if (details[5].equals("RETURNED")) {
	          message.setSubject("Dear Customer, Your late book return has levied a late fee!!");
	          message.setText("Dear "
	                        + details[1]
	                         + ",\r\n"
	                       + "\r\nYou returned the rented "
	                       + details[2]
	                        + " post its due date i.e. "
	                       + details[3]
	                       + ". Hence you have been charged a late fee of $"
	                        + details[4]
	                         + ".\r\n"
	                        + "\r\nNote : In case of any query, please give a call to our customer "
	                        + "support at +1 2444448080.\r\n"
	                       + "\r\n" + "\r\nHappy Reading!!\r\n"
	                + "Sape Store Admin");
	         
	        } else {
	          message.setSubject("Dear Customer, Your book return is pending!!");
	          message.setText("Dear "
	              + details[1]
	              + ",\r\n"
	              + "\r\nYou have rented "
	              + details[2]
	              + " and its due date for return was "
	              + details[3]
	              + ". We have not received the book and you have been levied a late fee of $"
	              + details[4]
	              + ".\r\n"
	              + "Please return the book at the earliest to avoid further increase in "
	              + "late fee charge.\r\n"
	              + "\r\nNote In case of any query, please give a call to our customer "
	              + "support at +1 2444448080.\r\n"
	              + "\r\n" + "\r\nHappy Reading!!\r\n"
	              + "Sape Store Admin");

	        }

	        Transport transport = session.getTransport("smtp");
	        
	        // Enter your correct gmail UserID and Password
	        // if you have 2FA enabled then provide App Specific Password
	        transport.connect("inrelaymail.sapient.com", "", "");
	        Transport.send(message);
	        transport.close();
	      } catch (MessagingException mex) {

	        return ApplicationConstants.FAILURE;
	      }
	      
	    }
	    if (LOGGER.isDebugEnabled()) {
	      LOGGER.debug("sendEmail method: END");
	    }
	    return ActionSupport.NONE;
	  }
	  
	  @RequestMapping(value = "/admin/sendRentedEmail", method = RequestMethod.GET)
	      public String sendRentedEmail(@RequestParam("emailIds") String ids, ModelMap modelMap) {
	    if (LOGGER.isDebugEnabled()) {
	      LOGGER.debug("sendEmail method: START");
	    }
	    String []sepIds = ids.split(":");
	    for (int i = 0; i < sepIds.length; i++) {
	      String []details = sepIds[i].split("#");
	      String from = "admin@sapestore.com";
	      String host = "inrelaymail.sapient.com";
	      Properties properties = System.getProperties();

	      properties.setProperty("mail.smtp.host", host);
	      Session session = Session.getInstance(properties);
	      if (i == 0) {
	        try {

	          MimeMessage message = new MimeMessage(session);
	          message.setFrom(new InternetAddress(from));
	          message.addRecipient(Message.RecipientType.TO, new InternetAddress(details[0]));

	          message.setSubject("Dear Customer, Your late book return has levied a late fee!!");
	          message.setText("Dear "
	                  + details[1]
	                  + ",\r\n"
	                  + "\r\nYou have not returned the rented "
	                  + details[2].replace("\t", "")           
	                  + " post its due date i.e. "
	                  + details[3]
	                  + ". Please Return As Soon As Possible"
	                  + ".\r\n"
	                  + "\r\nNote : In case of any query, "
	                  + "please give a call to our customer support at +1 2444448080.\r\n"
	                  + "\r\n" + "\r\nHappy Reading!!\r\n"
	                  + "Sape Store Admin");

	          Transport transport = session.getTransport("smtp");

	          // Enter your correct gmail UserID and Password
	          // if you have 2FA enabled then provide App Specific Password
	          transport.connect("inrelaymail.sapient.com", "", "");

	          Transport.send(message);
	          transport.close(); 
	        
	        } catch (MessagingException mex) {

	          return ApplicationConstants.FAILURE;
	        }
	      }
	    }
	    if (LOGGER.isDebugEnabled()) {
	      LOGGER.debug("sendEmail method: END");
	    }
	    return ActionSupport.NONE;
	  }

}