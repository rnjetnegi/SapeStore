package com.sapestore.controller;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.service.BookService;
import com.sapestore.service.PageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This is a controller class for the Manage Pages page.
 * 
 * @author pgup78 Date : 10-29-2015
 *
 */
@Controller
public class PagesController {

	private static final SapeStoreLogger LOGGER = SapeStoreLogger
			.getLogger(PagesController.class.getName());

	@Autowired
	private PageService pageService; // Object of Interface PageService

	@Autowired
	private BookService bookService; // Object of Interface BookService

	/*
	 * Redirects to ConfirmShippingAddress
	 * 
	 * @param modelMap
	 * 
	 * @return
	 * 
	 * @throws SapeStoreSystemException
	 */
	@RequestMapping(value = "/ship", method = RequestMethod.GET)
	public String tempPages(ModelMap modelMap, HttpSession session,
			HttpServletResponse response) throws SapeStoreException {

		response.setHeader("Cache-Control",
				"no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.

		if (session.getAttribute("userLoginName") != null
				&& session.getAttribute("userLoginName") != "") {
			return "ConfirmShippingAddress";
		} else {
			return "login";
		}
	}

	/*
	 * Redirects to administrator login page
	 * 
	 * @param response
	 * 
	 * @return
	 * 
	 * @throws SapeStoreSystemException
	 */
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminLoginPage(HttpServletRequest httpServletRequest,
			HttpSession httpSession, HttpServletResponse response)
			throws SapeStoreException {

		response.setHeader("Cache-Control",
				"no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.

		if (httpSession.getAttribute("userLoginName") != null
				&& httpSession.getAttribute("userLoginName") != "") {
			if (httpSession.getAttribute("isAdmin") != null
					&& (boolean) httpSession.getAttribute("isAdmin") == true) {
				return "redirect:/admin/manageInventory";
			} else if (httpSession.getAttribute("isAdmin") != null
					&& (boolean) httpSession.getAttribute("isAdmin") == false) {
				return "redirect:/welcome";
			}
		} 
		return "admin/login";
	}

	/*
	 * Redirects to ManagePages section for the administrator
	 * 
	 * @param modelMap
	 * 
	 * @return
	 * 
	 * @throws SapeStoreSystemException
	 */
	@RequestMapping(value = "/admin/managePages", method = RequestMethod.GET)
	public String managePages(ModelMap modelMap, HttpSession session,
			HttpServletResponse response) throws SapeStoreException {

		response.setHeader("Cache-Control",
				"no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.

		if (session.getAttribute("userLoginName") != null
				&& session.getAttribute("userLoginName") != "") {
			return "admin/ManagePages";
		} else {
			return "admin/login";
		}
	}

	/*
	 * Redirects to Contact Us tab for the administrator
	 * 
	 * @param modelMap
	 * 
	 * @return
	 * 
	 * @throws SapeStoreSystemException
	 */
	@RequestMapping(value = "/admin/contactUsEdit", method = RequestMethod.GET)
	public String contactUsEdit(ModelMap modelMap, HttpSession session,
			HttpServletResponse response) throws SapeStoreException {

		response.setHeader("Cache-Control",
				"no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.

		if (session.getAttribute("userLoginName") != null
				&& session.getAttribute("userLoginName") != "") {
			LOGGER.debug("contactUsEdit method: START");
			modelMap.addAttribute("contactText", pageService.getContactUs());
			return "admin/ContactUsAdmin";
		} else {
			return "admin/login";
		}
	}

	/*
	 * Saves the Contact Us text
	 * 
	 * @param contactText
	 * 
	 * @param modelMap
	 * 
	 * @return
	 * 
	 * @throws SapeStoreSystemException
	 */
	@RequestMapping(value = "/admin/contactUs", method = RequestMethod.GET)
	public String contactUsEdit(
			@RequestParam("contactText") String contactText, ModelMap modelMap,
			HttpSession session, HttpServletResponse response)
			throws SapeStoreException {

		response.setHeader("Cache-Control",
				"no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.

		if (session.getAttribute("userLoginName") != null
				&& session.getAttribute("userLoginName") != "") {
			pageService.setContactUs(contactText);
			return "redirect:/admin/managePages";
		} else {
			return "admin/login";
		}

	}

	/*
	 * Redirects to Policy tab for the administrator
	 * 
	 * @param modelMap
	 * 
	 * @return
	 * 
	 * @throws SapeStoreSystemException
	 */
	@RequestMapping(value = "/admin/policyEdit", method = RequestMethod.GET)
	public String policyEdit(ModelMap modelMap, HttpSession session,
			HttpServletResponse response) throws SapeStoreException {

		response.setHeader("Cache-Control",
				"no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.

		if (session.getAttribute("userLoginName") != null
				&& session.getAttribute("userLoginName") != "") {
			modelMap.addAttribute("policyText", pageService.getPolicy());

			return "admin/PolicyAdmin";
		} else {
			return "admin/login";
		}
	}

	/*
	 * Saves the Privacy Policy Page text
	 * 
	 * @param policyText
	 * 
	 * @param modelMap
	 * 
	 * @return
	 * 
	 * @throws SapeStoreSystemException
	 */
	@RequestMapping(value = "/admin/policy", method = RequestMethod.GET)
	public String setPolicy(@RequestParam("policyText") String policyText,
			ModelMap modelMap, HttpSession session, HttpServletResponse response)
			throws SapeStoreException {

		response.setHeader("Cache-Control",
				"no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.

		if (session.getAttribute("userLoginName") != null
				&& session.getAttribute("userLoginName") != "") {
			pageService.setPolicy(policyText);

			return "redirect:/admin/managePages";
		} else {
			return "admin/login";
		}
	}

	/*
	 * Returns the privacy policy text for the customer.
	 * 
	 * @param modelMap
	 * 
	 * @return
	 * 
	 * @throws SapeStoreSystemException
	 */
	@RequestMapping(value = "/policyCustomer", method = RequestMethod.GET)
	public String policyCustomer(ModelMap modelMap,
			HttpServletRequest httpServletRequest, HttpSession httpSession)
			throws SapeStoreException {
		LOGGER.debug("policyCustomer method: START");
		modelMap.addAttribute("catList", bookService.getCategoryList());
		modelMap.addAttribute("policyText", pageService.getPolicy());
		modelMap.addAttribute("checkMe", httpSession.getAttribute("checkMe"));
		LOGGER.debug("policyCustomer method: END");
		return "PolicyCustomer";
	}

	/*
	 * Processes the request for Contact Us page for the customer.
	 * 
	 * @param modelMap
	 * 
	 * @return
	 * 
	 * @throws SapeStoreSystemException
	 */
	@RequestMapping(value = "/contactUsCustomer", method = RequestMethod.GET)
	public String contactUsCustomer(ModelMap modelMap,
			HttpServletRequest httpServletRequest, HttpSession httpSession)
			throws SapeStoreException {
		LOGGER.debug("contactUsCustomer method: START");
		modelMap.addAttribute("catList", bookService.getCategoryList());
		modelMap.addAttribute("contactUsText", pageService.getContactUs());
		modelMap.addAttribute("checkMe", httpSession.getAttribute("checkMe"));
		LOGGER.debug("contactUsCustomer method: END");
		return "ContactUsCustomer";
	}

}
