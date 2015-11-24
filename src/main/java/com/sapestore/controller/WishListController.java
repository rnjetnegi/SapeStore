package com.sapestore.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.service.ShoppingCartService;
import com.sapestore.service.WishlistService;
import com.sapestore.service.impl.ShoppingCartServiceImpl;
import com.sapestore.service.impl.WishlistServiceImpl;
import com.sapestore.vo.CartVO;
import com.sapestore.vo.WishListUserVO;

/**
 * This is a controller class for the user wishlist.
 *
 * CHANGE LOG VERSION DATE AUTHOR MESSAGE 1.0 30-10-2015 SAPIENT Initial version
 */

@Controller
@SessionAttributes("WishList")
public class WishListController {

	private final static SapeStoreLogger LOGGER = SapeStoreLogger
			.getLogger(ShoppingCartController.class.getName());

	@Autowired(required = true)
	public WishlistService wishlistService;
	@Autowired
	WishlistServiceImpl wishlistServiceImpl;
	@Autowired(required = true)
	public ShoppingCartService shoppingCartService;
	@Autowired
	ShoppingCartServiceImpl shoppingCartServiceImpl;

	/**
	 * Processes the Add to Wishlist requests.
	 * 
	 * @param isbn
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addToWishlist", method = RequestMethod.GET)
	public void addToCart(@RequestParam String isbn,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap modelMap) throws SapeStoreException {
		HttpSession session = request.getSession();
		
       
		String userId = (String) session.getAttribute("userLoginName");
		WishListUserVO wishlist;
		if (userId == null || userId.equals("")) {
			try {
				 session.setAttribute("url","bookDetails?isbn="+isbn);
				response.getWriter().write("false");
				
				return;
			
			} catch (IOException e) {
				LOGGER.error("ADD TO WISHLIST LOGIN CHECK CONTROLLER FAIL");
			}
		} else {
			try {
				wishlist = new WishListUserVO();
				// wishlist = (WishListUserVO) modelMap.get("Wishlist");
				if (wishlist == null || wishlist.equals("")) {
					wishlist = new WishListUserVO();
				}
				response.getWriter().write("true");
				boolean bookInWishList = wishlistServiceImpl
						.checkBookIntoWishList(userId, isbn);
				if (bookInWishList == false) {
					wishlist = wishlistServiceImpl.addToWishlist(wishlist,
							userId, isbn);

				}
				response.getWriter().write("Item already in Wishlist!");
				if (wishlist != null) {
					modelMap.addAttribute("size", wishlist.getWishListItems()
							.size());
					modelMap.addAttribute("Wishlist", wishlist);
					return;

				} else {
					return;
				}
			} catch (IOException e) {
				LOGGER.error("ADD TO Wishlist LOGIN CHECK CONTROLLER PASS");
			}
		}
		try {
			response.getWriter().write(isbn);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return;
	}

	/**
	 * Processes the View Wishlist requests.
	 * 
	 * @param userId
	 * @param modelMap
	 * @return wishlist
	 * @throws Exception
	 */
	@RequestMapping(value = "/viewWishlist", method = RequestMethod.GET)
	public String viewWishlist(ModelMap map, HttpServletRequest request,
			HttpSession httpSession) throws SapeStoreException {

		String userId = (String) httpSession.getAttribute("userLoginName");
		if (userId == null || userId.equals("")) {

			httpSession = request.getSession(false);
			httpSession.setAttribute("url", "viewWishlist");
			return "login";
		} else {
			WishListUserVO wishlist = wishlistServiceImpl
					.getWishlistItems(userId);
			map.addAttribute("Wishlist", wishlist);
			map.addAttribute("wishListSize", wishlist.getWishListItems().size());
		}
		return "DisplayWishlist";
	}

	/**
	 * Processes the Remove from Wishlist requests.
	 * 
	 * @param isbn
	 * @param modelMap
	 * @return wishlist
	 * @throws SapeStoreException
	 * @throws Exception
	 */
	@RequestMapping(value = "/removeFromWishlist", method = RequestMethod.GET)
	public String removeFromWishlist(@RequestParam String isbn,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws SapeStoreException {
		HttpSession session = request.getSession();

		String userId = (String) session.getAttribute("userLoginName");
		WishListUserVO wishlist;
		wishlist = wishlistServiceImpl.getWishlistItems(userId);
		try {
			wishlist = wishlistServiceImpl.removeFromWishlist(wishlist, userId,
					isbn);
			model.addAttribute("Wishlist", wishlist);
		} catch (SapeStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "DisplayWishlist";
	}

	/**
	 * Processes the Add to Cart From Wishlist requests.
	 * 
	 * @param isbn
	 * @param modelMap
	 * @return cart
	 * @throws Exception
	 */
	@RequestMapping(value = "/moveToCartFromWishlist", method = RequestMethod.GET)
	public String moveToCartFromWishlist(@RequestParam String isbn,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap modelMap) throws SapeStoreException {

		HttpSession session = request.getSession();

		String userId = (String) session.getAttribute("userLoginName");
		WishListUserVO wishlist;
		CartVO shoppingCart;

		response.setContentType("text/plain"); // Set content type of the
												// response so that jQuery knows
												// what it can expect.
		response.setCharacterEncoding("UTF-8"); // Encoding Type

		if (userId == null || userId.equals("")) {
			try {
				response.getWriter().write("false");
				return "login";
			} catch (IOException e) {
				LOGGER.error("ADD TO CART FROM WISHLIST LOGIN CHECK CONTROLLER FAIL");
			}
		} else {
			try {
				// shoppingCart = new CartVO();
				shoppingCart = (CartVO) session.getAttribute("ShoppingCart");
				wishlist = wishlistServiceImpl.getWishlistItems(userId);
				if (shoppingCart == null) {
					shoppingCart = new CartVO();
				} else {
					response.getWriter().write("true");
					// shoppingCart =
					// shoppingCartServiceImpl.addToCart(shoppingCart,userId,
					// isbn, "purchase");
					boolean bookInDB = shoppingCartServiceImpl
							.checkBookIntoCart(userId, isbn, "PURCHASED");
					if (bookInDB == false) {
						boolean bookInCart = shoppingCartServiceImpl
								.checkBookIntoCart(userId, isbn, "PURCHASED");
						if (bookInCart == false) {
							wishlist = wishlistServiceImpl.moveFromWishlist(
									wishlist, userId, isbn);
						} else {
							wishlist = wishlistService.removeFromWishlist(
									wishlist, userId, isbn);
							modelMap.addAttribute("Wishlist", wishlist);
							response.getWriter().write("Item already in Cart!");
						}
						modelMap.addAttribute("size", shoppingCart
								.getCartItems().size());
						modelMap.addAttribute("ShoppingCart", shoppingCart);
						return "DisplayShoppingCart";
					}
				}
			} catch (IOException e) {
				LOGGER.error("ADD TO CART FROM WISHLIST LOGIN CHECK CONTROLLER PASS");
			}
		}
		try {
			response.getWriter().write(isbn);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return "DisplayWishlist";

}

}
