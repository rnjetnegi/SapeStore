package com.sapestore.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;
import com.sapestore.hibernate.entity.BookCategory;
import com.sapestore.hibernate.entity.City;
import com.sapestore.hibernate.entity.State;
import com.sapestore.service.BookService;
import com.sapestore.service.EditUserService;
import com.sapestore.service.RegisterService;
import com.sapestore.vo.RegisterVO;

/**
 * This is a controller class for the registration functionality.
 *
 * CHANGE LOG
 *      VERSION    DATE         AUTHOR       MESSAGE               
 *        1.0    20-10-2015     SHIVAM      Initial version
 *      VERSION    DATE         AUTHOR       MESSAGE               
 *        2.0    23-10-2015     SHIVAM      editprofile controller is updated
 */
@Controller
public class EditProfileController {
	private final static SapeStoreLogger LOGGER = SapeStoreLogger.getLogger(RegisterController.class.getName());

	private List<BookCategory> catList;
	
	@Autowired
	private BookService bookService;

	public BookService getBookService() {
		return bookService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}
	
	public List<BookCategory> getCatList() {
		return catList;
	}

	public void setCatList(List<BookCategory> catList) {
		this.catList = catList;
	}
	
	@Autowired
	private RegisterService registerService;
       @Autowired
       private EditUserService editUserService;
       
       /**
        * This controller takes us to Edit Profile Page
        * @param model
        * @param session
        * @param request
        * @return EditProfile
        */
       @RequestMapping(value = "/preEditProfile", method = RequestMethod.POST)
       public String preRegister(ModelMap model,HttpSession session,HttpServletRequest request) {
			RegisterVO user=null;
    	  session= request.getSession(false);												/*gets session from request but doesn't creates it if not created*/
    	   String loginName=(String) session.getAttribute("userLoginName");
    	   try {
    		   this.setCatList(getCategoryList());
                  user=editUserService.getUser(loginName);									/*get the user details from database*/
		} catch (SapeStoreException sse) {
			LOGGER.error(sse.getMessage());
		}
    	   List<City> listCity=null;
   		List<State> listState=null;
   		try {
   			listCity=registerService.getCities();											/*to retrieve all the cities from the database*/
   		} catch (SapeStoreException sse) {
			LOGGER.error(sse.getMessage());
		}
   		try {
   			 listState =registerService.getStates();										/*to retrieve all the states from the database*/
   		} catch (SapeStoreException sse) {
			LOGGER.error(sse.getMessage());
		}
   		
   		model.addAttribute("cities", listCity);												/* Add cities to model */
   		model.addAttribute("states", listState);											/* Add states to model */
   		model.addAttribute("catList", this.getCatList());									/* Add catList to model */
    	model.addAttribute("registerUser", new RegisterVO());								/* Add registerUser Bean to model */
        model.addAttribute("user", user);													/* Add user Bean to model */
        
         return "EditProfile";																/* Redirects to EditProfile.jsp */
       }
       
       /**
        * This controller redirects to profile page after updating
        * @param register
        * @param session
        * @param request
        * @param result
        * @return redirect:/afterUpdate
        */
       @RequestMapping(value = "/editProfile", method = RequestMethod.POST)
       public String afterRegister(@ModelAttribute("registerUser") RegisterVO  register,HttpSession session,HttpServletRequest request,
                     BindingResult result, ModelMap model) {
    	   session= request.getSession(false);													/* gets session from request but doesn't creates it if not created*/
    	   String loginName=(String) session.getAttribute("userLoginName");						/* Retrieves userID from session */
              try {
            	  this.setCatList(getCategoryList());
                     editUserService.editUser(register,loginName);								/*To edit the details of the user.*/
           
              
              } catch (SapeStoreException sse) {
      			LOGGER.error(sse.getMessage());
      		}
              model.addAttribute("catList", this.getCatList());									/* Add catList to model */
              return "redirect:/afterUpdate";													/* Redirects to afterUpdate.jsp */
       }
       
       /**
   	 * This method returns the category of books.
   	 * 
   	 * @return
   	 */
   	private List<BookCategory> getCategoryList() throws SapeStoreException {
   		if (LOGGER.isDebugEnabled()) {
   			LOGGER.debug("getCategoryList method: START");
   		}

   		List<BookCategory> bookCategoryList = null;

   		try {
   			bookCategoryList = bookService.getCategoryList();

   		} catch (SapeStoreSystemException ex) {
   			LOGGER.error("getCategoryList method: ERROR: " + ex);
   			return null;
   		}

   		if (LOGGER.isDebugEnabled()) {
   			LOGGER.debug("getCategoryList method: END");
   		}
   		return bookCategoryList;
   	}
}
