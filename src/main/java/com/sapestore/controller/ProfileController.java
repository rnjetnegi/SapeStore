package com.sapestore.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;
import com.sapestore.hibernate.entity.BookCategory;
import com.sapestore.service.BookService;
import com.sapestore.service.EditUserService;
import com.sapestore.service.RegisterService;
import com.sapestore.vo.RegisterVO;

/**
 * This is a controller class for viewing profile functionality.
 * 
 * CHANGE LOG VERSION DATE AUTHOR MESSAGE 1.0 20-10-2015 SHIVAM Initial version
 * 
 * VERSION DATE AUTHOR MESSAGE 2.0 23-10-2015 SHIVAM afterUpdate controller is
 * added
 */
@Controller
public class ProfileController {
	private final static SapeStoreLogger LOGGER = SapeStoreLogger
			.getLogger(RegisterController.class.getName());
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
	 * This controller returns the profile page of user on clicking Profile link.
	 * If user is nor logged in it returns to Home page
	 * 
	 * @param model
	 * @param session
	 * @param request
	 * @return Profile/redirect:/welcome
	 */
	@RequestMapping(value = "/Profile", method = RequestMethod.GET)
	public String seeProfile(ModelMap model, HttpSession session,
			HttpServletRequest request) {

		RegisterVO user = new RegisterVO();
		session = request.getSession(false);												/* Gets a new session but don't create a new one */
		
		/* This block is accessed only if user is logged in. So, userLoginName attribute of session is checked */
		if (session.getAttribute("userLoginName") != null) {
			String loginName = (String) session.getAttribute("userLoginName");
			try {
				this.setCatList(getCategoryList());
				user = editUserService.getUser(loginName);									/* Edit User Details in database by calling DAO */
															 
			} catch (SapeStoreException sse) {

				LOGGER.error(sse.getMessage());
			}
			model.addAttribute("user", user);												/* Add user to Model */
			model.addAttribute("catList", this.getCatList());								/* Adds catList to model */
			return "Profile";
		} else {
			return "redirect:/welcome";
		}

	}

	/**
	 * This controller returns the updated profile page of user after updating
	 * profile.
	 * 
	 * @param model
	 * @param session
	 * @param request
	 * @return UpdatedProfile
	 */

	@RequestMapping(value = "/afterUpdate", method = RequestMethod.GET)
	public String seeUpdatedProfile(ModelMap model, HttpSession session,
			HttpServletRequest request) {

		RegisterVO user = new RegisterVO();
		session = request.getSession(false);												/* Gets a new session but don't create a new one */
		if (session.getAttribute("userLoginName") != null) {
			String loginName = (String) session.getAttribute("userLoginName");

			try {
				this.setCatList(getCategoryList());
				user = editUserService.getUser(loginName); 									/* To edit user details */
			} catch (SapeStoreException sse) {

				LOGGER.error(sse.getMessage());
			}
			model.addAttribute("user", user);												/* Add user to Model */
			model.addAttribute("catList", this.getCatList());								/* Adds catList to model */
			
			return "Profile";																/* Redirects to Profile.jsp */
			
		} else {
			
			return "redirect:/welcome";														/* Redirects to Home Page */
		}

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
