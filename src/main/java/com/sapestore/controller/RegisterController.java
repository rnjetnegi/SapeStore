package com.sapestore.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
import com.sapestore.service.RegisterService;
import com.sapestore.util.EmailGenerator;
import com.sapestore.vo.RegisterVO;
/**
 * This is a controller class for the registration functionality.
 *
 * CHANGE LOG
 *      VERSION    DATE          AUTHOR       MESSAGE               
 *        1.0    20-10-2015     SHIVAM      Initial version
 */
@Controller
public class RegisterController {
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
	
	/**
	 * This controller is for jumping to registration page.
	 * @param model
	 * @return register
	 */
	@RequestMapping(value = "/preRegister", method = RequestMethod.POST)
	public String preRegister(ModelMap model,HttpSession session,HttpServletRequest request) {
		
		
		LOGGER.debug(" RegisterController.preRegister method: START");
		
		List<City> listCity=null;												/* List to contain cities */
		List<State> listState=null;												/* List to contain states */
		
		session=request.getSession();											/* A new session is created at this stage */
		
		/**
		 * This if block is called only if session doesn't contain userLoginName
		 */
		if(session.getAttribute("userLoginName") == null || session.getAttribute("userLoginName") == ""){
			
			try {
				listCity=registerService.getCities();							/*To retrieve all the cities from the database*/
				this.setCatList(getCategoryList());								/* Set Category List of Books as these are to Displayed on Left Hand Side of every page */
			} catch (SapeStoreException sse) {
			
				LOGGER.error(sse.getMessage());
			}
			try {
				listState =registerService.getStates();							/*To retrieve all the states from the database*/
				this.setCatList(getCategoryList());
			} catch (SapeStoreException sse) {
				LOGGER.error(sse.getMessage());
			}
		
		
			session.setAttribute("cities", listCity);							/* Adds cities based on state selected by user to session to display on registration form  */
			session.setAttribute("states", listState);							/* Adds states to session after fetching from Database */
		
			
			model.addAttribute("catList", this.getCatList());					/* Adds catList to model */
			model.addAttribute("registerUser", new RegisterVO());				/* Adds registerUser to Model */		
			LOGGER.debug(" RegisterController.preRegister method: END");
			return "register";
		} else {
			
			return "redirect:/bookListByCat?categoryId=0&categoryName=Top Rated&checkMe="+session.getAttribute("checkMe");  /* Redirects to search books by category page */ 
		}
	}

	/**
	 * Processes the register requests
	 * @param register
	 * @param model
	 * @return SuccessfulRegistration
	 * @throws SapeStoreSystemException
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String afterRegister(ModelMap model, HttpSession session,HttpServletRequest request)
			 {
		LOGGER.debug("register method: START");
		Boolean status;																	/* Status is set as true if user is registered successfully else false */
		session=request.getSession(false);												/* Gets a new session but don't create a new one */
		RegisterVO registerUser = null;
		EmailGenerator emailGenerator = null;
		try {
			emailGenerator = new EmailGenerator(); 										/* Generates email to sent users confirmation of registration */
			 registerUser = (RegisterVO) session.getAttribute("confirmUser");
			 status=registerService.addUser(registerUser);								/* Adding the details of the new user in the database.*/
			 
			 /* This if block is used if user is registered successfully */
			 if(status)																		
			 {
				 this.setCatList(getCategoryList());
				session.setAttribute("userLoginName", registerUser.getLoginName());		/* Add userId to session */
			 }
		} catch (SapeStoreException sse) {
			
			LOGGER.error(""+sse.getMessage());
		}
		emailGenerator.sendEmail(registerUser.getFullName(), registerUser.getEmail());	/* Send confirmation email to Users */
		model.addAttribute("catList", this.getCatList());								/* Adds catList to model */
		LOGGER.debug("register method: END");
		return "SuccessfulRegistration";												/* Redirects users to SuccessfulRegistration.jsp */
	}
	
	/**
	 * This controller is activated when user clicks confirms button on validate page.
	 * Redirects to same page if it has errors.
	 * Redirects to ValidateProfile.jsp if all entries are correct.
	 * @param register
	 * @param session
	 * @param request
	 * @return ValidateProfile/Register
	 */
	@RequestMapping(value = "/confirmRegistrationDetails", method = RequestMethod.POST)
	public String validateRegistration(@Valid @ModelAttribute("registerUser")RegisterVO register, BindingResult result,HttpSession session, HttpServletRequest request,  ModelMap model){
		LOGGER.debug("validateRegister method: START");
		session=request.getSession(false);												/* Gets a new session but don't create a new one */
		
		/* This block is used if there is no errors in entries on registration page */
		if(!result.hasErrors())
		{
			try {
				this.setCatList(getCategoryList());										/* Set Category List of Books as these are to Displayed on Left Hand Side of every page */
			} catch (SapeStoreException e) {
				e.printStackTrace();
			}
				session.setAttribute("confirmUser", register);							/* Add confirmUser model to session */
				LOGGER.debug("validateRegister method: END");
		
				return "ValidateProfile";
			}
			else
			{
				model.addAttribute("registerUser", register);							/* Add registerUser Model */
				model.addAttribute("catList", this.getCatList());						/* Adds catList to model */
				
				return "register";														/* Redirects to register.jsp */
		
	}
	}
	
	/**
	 * This controller is activated when user clicks back button on validate page and wants to change any details before login.
	 * It redirects back to registration page.
	 * @param register
	 * @param httpSession
	 * @return redirect:/preRegister
	 */
	@RequestMapping(value = "/checkRegistrationDetails", method = RequestMethod.GET)
	public String checkRegistration(@ModelAttribute("registerUser") RegisterVO register,HttpSession httpSession, ModelMap model){
		LOGGER.debug("validateRegister method: START");
		try {
			this.setCatList(getCategoryList());											/* Set Category List of Books as these are to Displayed on Left Hand Side of every page */
		} catch (SapeStoreException e) {
			e.printStackTrace();
		}
		LOGGER.debug("validateRegister method: END");
		model.addAttribute("catList", this.getCatList());								/* Adds catList to model */
		return "redirect:/preRegister";													/* Redirects to register page */
		
	}
	
	/**
	 * This controller redirects users to Home page after all registration process is over.
	 * Session is invalidated before redirecting to prevent users to get auto login after registration.
	 * @param register
	 * @param result
	 * @param session
	 * @param request
	 * @param model
	 * @return redirect:/welcome
	 */
	@RequestMapping(value = "/afterRegistration", method = RequestMethod.POST)
	public String afterRegistration(@Valid @ModelAttribute("registerUser")RegisterVO register, BindingResult result,HttpSession session, HttpServletRequest request,  ModelMap model){
		LOGGER.debug("afterRegistration method: START");
		session=request.getSession(false);
		
			try {
				this.setCatList(getCategoryList());										/* Set Category List of Books as these are to Displayed on Left Hand Side of every page */
			} catch (SapeStoreException e) {
				e.printStackTrace();
			}
		
			/* Session is invalidated before redirecting to prevent users to get auto login after registration. */
			if(session != null){
				session.invalidate();
			}
			
		LOGGER.debug("afterRegistration: END");
		/* Redirects to Home Page */
//      return "redirect:/welcome?checkMe=false";
		return "redirect:/welcome";		
	
	}
	

	/**
	 * This method returns the category of books.
	 * 
	 * @return bookCategoryList
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
