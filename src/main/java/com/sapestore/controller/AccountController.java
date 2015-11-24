package com.sapestore.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;
import com.sapestore.hibernate.entity.User;
import com.sapestore.service.AccountService;
import com.sapestore.service.ShoppingCartService;
import com.sapestore.service.impl.ShoppingCartServiceImpl;
import com.sapestore.service.impl.WishlistServiceImpl;
import com.sapestore.util.CookieCart;
import com.sapestore.vo.CartVO;
import com.sapestore.vo.WishListUserVO;
import com.sapestore.vo.WishListVO;

/**
 * This is a controller class for the login functionality.
 *
 * CHANGE LOG
 *      VERSION    DATE          AUTHOR       MESSAGE               
 *        1.0    20-06-2014     SAPIENT      Initial version
 */

@Controller
@SessionAttributes(value = {"userId", "username"})
public class AccountController {
	
	private final static SapeStoreLogger LOGGER = SapeStoreLogger.getLogger(AccountController.class.getName());
	
	@Autowired
	private AccountService accountService;
	@Autowired
	WishlistServiceImpl wishListService;
	@Autowired
	ShoppingCartServiceImpl shoppingCartService;
	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String beforeLogin(ModelMap modelMap) throws SapeStoreException
	{
		LOGGER.debug(" AccountController.beforeLogin method: START ");
		modelMap.addAttribute("user", new User());
		LOGGER.debug(" AccountController.beforeLogin method: END ");
		return "index";
	}
	
	/**
	 * Processes the login requests
	 * @param userlogin
	 * @param modelMap
	 * @return forwardStr
	 * @throws SapeStoreSystemException
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(@ModelAttribute("user") User user,  ModelMap modelMap,HttpServletRequest httpServletRequest,HttpSession httpSession, HttpServletResponse response) throws SapeStoreException
	{
		LOGGER.debug("login method: START");
        String forwardStr = null;
        User localUserlogin = null;
        User userLogin = (User) user;
        String userId = null;
        httpSession = httpServletRequest.getSession(false);
        if(httpSession==null)
        {
        	 httpSession = httpServletRequest.getSession();
        }
        String url=(String) httpSession.getAttribute("url");
      
        httpSession.setAttribute("url",null);
      
       
        
        localUserlogin = accountService.authenticate(userLogin);    
        /**
         * Redirect to Manage Inventory in case of Admin. 
         * Redirect to bookListByCat in case of User.
         * Redirect to index in case of any error. 
         */
		if (localUserlogin != null && localUserlogin.getIsAdmin()!=null) {
			CookieCart cookieCart=new CookieCart(httpServletRequest);
			
			 CartVO cartVO=shoppingCartService.addCookieToCart(cookieCart, user.getUserId(), response);
			 httpSession.setAttribute("userLoginName", user.getUserId());
			 CartVO cart=shoppingCartService.getCartItems(user.getUserId());
			 WishListUserVO wishList=wishListService.getWishlistItems(user.getUserId());
			 httpSession.setAttribute("checkMe", false);
			 httpSession.setAttribute("ShoppingCart", cart);
			 httpSession.setAttribute("WishList",wishList);
			 httpSession.setAttribute("isAdmin", false);

			 modelMap.addAttribute("ShoppingCart",cart);
		    if (localUserlogin.getIsAdmin().equalsIgnoreCase("Y")) {
		    	httpSession.setAttribute("isAdmin", true);
		        forwardStr = "redirect:/logout";
		    }
		    else {
		        forwardStr = "redirect:/welcome";
		    }
		    /**
		     * Add userId and userName to model for further use.
		     */
			userId = localUserlogin.getUserId();
			modelMap.addAttribute("userId", userId);
			modelMap.addAttribute("username", localUserlogin.getName());
		} else {
		    forwardStr = "redirect:/welcome";
		}
		LOGGER.debug("login method: END");
        return forwardStr;																	/* Redirects to required page */
	}
	
	/**
	 * Processes the Logout requests
	 * @param request
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(WebRequest request, SessionStatus status,ModelMap modelMap,HttpServletRequest httpServletRequest,HttpSession httpSession) throws SapeStoreException {
		
		LOGGER.debug("logout method: START");
		status.setComplete();
		
		/* Removes attributes from session and request */
		request.removeAttribute("userId", WebRequest.SCOPE_SESSION);
		request.removeAttribute("ShoppingCart", WebRequest.SCOPE_SESSION);
		
		httpSession.removeAttribute("checkMe");
		httpSession.removeAttribute("userLoginName");
		httpSession.removeAttribute("username");
		httpSession.removeAttribute("isAdmin");

		request.removeAttribute("userId", WebRequest.SCOPE_SESSION);
		request.removeAttribute("username", WebRequest.SCOPE_SESSION);
		
		httpSession = httpServletRequest.getSession(false);
		
		if(httpSession != null){
			httpSession.invalidate();														/* Invalidates existing session */
		}
		LOGGER.debug("logout method: END");
		
		return "redirect:/welcome";															/* Redirects to Home Page */
	}
	@RequestMapping(value = "/loginCheck", method = RequestMethod.POST)
	public void commentPage(HttpServletRequest request,
			HttpServletResponse response) throws SapeStoreException {

		HttpSession session = request.getSession();
		String user = request.getParameter("userId");
		String password = request.getParameter("password");

		response.setContentType("text/plain"); // Set content type of the
												// response so that jQuery knows
												// what it can expect.
		response.setCharacterEncoding("UTF-8"); // Encoding Type
		
		User localUserlogin = new User();
		localUserlogin.setUserId(user);
		localUserlogin.setPassword(password);
		localUserlogin = accountService.authenticate(localUserlogin);
		
		if (localUserlogin == null) {
			try {
				response.getWriter().write("false");
			} catch (IOException e) {
				LOGGER.error(" LOGIN CHECK CONTROLLER FAIL");
			}
		} else {
			try {
				response.getWriter().write("true");
			} catch (IOException e) {
				LOGGER.error(" LOGIN CHECK CONTROLLER FAIL");
			}
		}
	}
	
	/**
	 * Processes the login requests
	 * @param userlogin
	 * @param modelMap
	 * @return forwardStr
	 * @throws SapeStoreSystemException
	 */
	@RequestMapping(value="/adminLogin",method=RequestMethod.POST)
	public String adminLogin(@ModelAttribute("user") User user,  ModelMap modelMap,HttpServletRequest httpServletRequest,HttpSession httpSession, HttpServletResponse response) throws SapeStoreException
	{
		LOGGER.debug("login method: START");
        String forwardStr = null;
        User localUserlogin = null;
        User userLogin = (User) user;
        String userId = null;
        httpSession = httpServletRequest.getSession(false);
        if(httpSession==null)
        {
        	 httpSession = httpServletRequest.getSession();
        }
        String url=(String) httpSession.getAttribute("url");
        httpSession.setAttribute("url",null);
        localUserlogin = accountService.authenticate(userLogin);    
        /**
         * Redirect to Manage Inventory in case of Admin. 
         * Redirect to bookListByCat in case of User.
         * Redirect to index in case of any error. 
         */
		if (localUserlogin != null && localUserlogin.getIsAdmin()!=null && localUserlogin.getIsAdmin().equalsIgnoreCase("Y")) {
			CookieCart cookieCart=new CookieCart(httpServletRequest);
			
			CartVO cartVO=shoppingCartService.addCookieToCart(cookieCart, user.getUserId(), response);
			httpSession.setAttribute("userLoginName", user.getUserId());
			CartVO cart=shoppingCartService.getCartItems(user.getUserId());
			WishListUserVO wishList=wishListService.getWishlistItems(user.getUserId());
			httpSession.setAttribute("checkMe", false);
			httpSession.setAttribute("ShoppingCart", cart);
			httpSession.setAttribute("WishList",wishList);
			httpSession.setAttribute("isAdmin", true);
			modelMap.addAttribute("ShoppingCart",cart);
			userId = localUserlogin.getUserId();
			modelMap.addAttribute("userId", userId);
			modelMap.addAttribute("username", localUserlogin.getName());
			forwardStr = "redirect:/admin/manageInventory";
		} else {
		    forwardStr = "admin/login";
		}
		LOGGER.debug("login method: END");
        return forwardStr;																	/* Redirects to required page */
	}

}
