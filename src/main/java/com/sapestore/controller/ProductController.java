package com.sapestore.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sapestore.common.ApplicationConstants;
import com.sapestore.common.SapeStoreLogger;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;
import com.sapestore.service.BookService;
import com.sapestore.service.InventoryService;
import com.sapestore.validations.FileValidator;
import com.sapestore.vo.BookVO;

/**
 * This is a controller class for loading the Add Books page.
 *
 * CHANGE LOG VERSION DATE AUTHOR MESSAGE 1.0 20-06-2014 SAPIENT Initial version
 */

@Controller
public class ProductController {

	private final static SapeStoreLogger LOGGER = SapeStoreLogger
			.getLogger(ProductController.class.getName());

	@Autowired
	private BookService bookService;

	@Autowired
	private InventoryService inventoryService;

	@Autowired(required = false)
	ServletContext servletContext;

	/**
	 * Returns the add book page for the admin.
	 * 
	 * @param modelMap
	 * @param response 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/admin/addBooksAdmin", method = RequestMethod.POST)
	public String addBooksAdmin(ModelMap modelMap, HttpSession session, HttpServletResponse response)
			throws SapeStoreException {
		
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		
		if (session.getAttribute("userLoginName") != null
				&& session.getAttribute("userLoginName") != "") {
			LOGGER.debug("addBooksAdmin method: START");
			modelMap.addAttribute("categoryList", bookService.getCategoryList());
			modelMap.addAttribute("addBooks", new BookVO());
			LOGGER.debug("addBooksAdmin method: END");
			
			return "admin/addBooks";
		} else {
			return "admin/login";
		}
	}

	/**
	 * Processes the add book requests.
	 * 
	 * @param addBooks
	 * @param bindingResult
	 * @param modelMap
	 * @param response 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/admin/addBooks", method = RequestMethod.POST)
	public String addBooks(@Valid @ModelAttribute("addBooks") BookVO addBooks,
			BindingResult bindingResult, ModelMap modelMap, HttpSession session, HttpServletResponse response)
			throws Exception {
		
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		
		if (session.getAttribute("userLoginName") != null
				&& session.getAttribute("userLoginName") != "") {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("addBooks method: START");
			}

			String forwardStr = null;
			String thumbPath = null;
			String fullPath = null;
			/*
			 * String thumbImageFileName = null; String fullImageFileName =
			 * null;
			 */
			File thumbUploadDir = null;
			File fullUploadDir = null;

			try {
				BookVO addBooks2 = (BookVO) addBooks;

				FileValidator fileValidator = new FileValidator();
				fileValidator.validate(addBooks, bindingResult);

				if (bindingResult.hasErrors()) {
					modelMap.addAttribute("categoryList",
							bookService.getCategoryList());
					modelMap.addAttribute("addBooks", addBooks2);
					return "admin/addBooks";
				}
				thumbPath = servletContext
						.getRealPath(ApplicationConstants.THUMB_IMG_URL);
				fullPath = servletContext
						.getRealPath(ApplicationConstants.FULL_IMG_URL);
				thumbUploadDir = new File(thumbPath);
				fullUploadDir = new File(fullPath);
				if (thumbUploadDir.exists() == false) {
					thumbUploadDir.mkdirs();
				}
				if (fullUploadDir.exists() == false) {
					fullUploadDir.mkdirs();
				}
				if (null != addBooks2) {
					/*
					 * thumbImageFileName = addBooks2.getThumbImageFileName();
					 * fullImageFileName = addBooks2.getFullImageFileName();
					 * 
					 * System.out.println(
					 * "---------------------thumbImageFileName--------------->"
					 * +thumbImageFileName); System.out.println(
					 * "---------------------fullImageFileName--------------->"
					 * +fullImageFileName);
					 */

					if (null != addBooks2.getThumbImage()) {
						File thumbFile = new File(thumbPath, addBooks2
								.getThumbImage().getOriginalFilename());
						byte[] bytes = addBooks2.getThumbImage().getBytes();
						BufferedOutputStream stream = new BufferedOutputStream(
								new FileOutputStream(thumbFile));
						stream.write(bytes);
						stream.close();
						addBooks2
								.setThumbPath(ApplicationConstants.THUMB_IMG_URL
										+ addBooks2.getThumbImage()
												.getOriginalFilename());
					}
					if (null != addBooks2.getFullImage()) {
						File largeFile = new File(fullPath, addBooks2
								.getFullImage().getOriginalFilename());
						byte[] bytes = addBooks2.getFullImage().getBytes();
						BufferedOutputStream stream = new BufferedOutputStream(
								new FileOutputStream(largeFile));
						stream.write(bytes);
						stream.close();
						addBooks2.setFullPath(ApplicationConstants.FULL_IMG_URL
								+ addBooks2.getFullImage()
										.getOriginalFilename());
					}
					bookService.addBooks(addBooks2);
					forwardStr = "redirect:/admin/manageInventory";
				} else {
					forwardStr = ApplicationConstants.FAILURE;
				}
			} catch (SapeStoreSystemException ex) {
				LOGGER.error("addBooks method: ERROR: " + ex);
				forwardStr = ApplicationConstants.FAILURE;
			} catch (Exception e) {
				LOGGER.error("addBooks method: ERROR: " + e);
				forwardStr = ApplicationConstants.FAILURE;
			}

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("addBooks method: END");
			}
			return forwardStr;
		} else {
			return "admin/login";
		}

	}

	@RequestMapping(value = "/admin/manageInv", method = RequestMethod.GET)
	public String deleteBookRedirect(ModelMap modelMap, HttpSession session, HttpServletResponse response)
			throws SapeStoreSystemException {
		
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		
		if (session.getAttribute("userLoginName") != null
				&& session.getAttribute("userLoginName") != "") {
			return "redirect:/admin/manageInventory";
		} else {
			return "admin/login";
		}

	}

	/**
	 * Processes the request for book edit page and returns the data for the
	 * book selected for edit operation
	 * 
	 * @param updateInventory
	 * @param modelMap
	 * @param response 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/admin/editBooks", method = RequestMethod.POST, params = "editBook")
	public String editBooks(@ModelAttribute("updateBooks") BookVO updateInventory, ModelMap modelMap, HttpSession session, HttpServletResponse response) throws SapeStoreException {

		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		
		if (session.getAttribute("userLoginName") != null
				&& session.getAttribute("userLoginName") != "") {
			LOGGER.debug("editBooks method: START");
			modelMap.addAttribute("categoryList", bookService.getCategoryList());
			modelMap.addAttribute("updateBooks", updateInventory);
			modelMap.addAttribute("updateInv", new BookVO());
			LOGGER.debug("editBooks method: END");
			return "admin/EditResult";
		} else {
			return "admin/login";
		}
	}

	/*@RequestMapping(value = "/admin/editBooks", method = RequestMethod.POST, params = "delSubmit")
	public String deleteBooksRedirect(
			@ModelAttribute("updateBooks") BookVO updateInventory,
			ModelMap modelMap, HttpSession session, HttpServletResponse response) throws SapeStoreException {
		
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		
		if (session.getAttribute("userLoginName") != null
				&& session.getAttribute("userLoginName") != "") {
			LOGGER.debug("deleteBooksRedirect method: START");
			bookService.deleteBook(updateInventory.getIsbn());
			modelMap.addAttribute("adminInventoryList",
					inventoryService.getAdminInventory());
			LOGGER.debug("deleteBooksRedirect method: END");
			return "admin/ManageInventory";
		} else {
			return "admin/login";
		}
	}*/

}
