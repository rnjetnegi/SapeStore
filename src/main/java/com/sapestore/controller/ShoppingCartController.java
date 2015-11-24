package com.sapestore.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

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
import com.sapestore.dao.OrderDao;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.hibernate.entity.Address;
import com.sapestore.service.BookService;
import com.sapestore.service.ShoppingCartService;
import com.sapestore.service.impl.ShoppingCartServiceImpl;
import com.sapestore.util.CookieCart;
import com.sapestore.vo.BookVO;
import com.sapestore.vo.CartItemVO;
import com.sapestore.vo.CartVO;

/**
 * This is a controller class for the shopping cart.
 *
 * CHANGE LOG VERSION DATE AUTHOR MESSAGE 1.0 20-06-2014 SAPIENT Initial version
 */

@Controller
@SessionAttributes("ShoppingCart")
public class ShoppingCartController {

	private final static SapeStoreLogger LOGGER = SapeStoreLogger
			.getLogger(ShoppingCartController.class.getName());

	@Autowired(required = true)
	public ShoppingCartService shoppingCartService;
	@Autowired
	ShoppingCartServiceImpl shoppingCartServiceImpl;
	@Autowired
	private BookService bookService;
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private HomePageController welcomeRef;


	/**
	 * Processes the Add to Cart requests.
	 * 
	 * @param categoryId
	 * @param categoryName
	 * @param checkMe
	 * @param isbn
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	/**
	 * Processes the Add to Cart requests.
	 * 
	 * @param isbn
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addToCart", method = RequestMethod.GET)
	public void addToCart(@RequestParam String isbn, @RequestParam String type,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap modelMap) throws SapeStoreException {
		HttpSession session = request.getSession();
		session.setAttribute("url","viewCart");
		String userId = (String) session.getAttribute("userLoginName");

		CartVO shoppingCart = null;
		if (userId == null || userId.equals("")) {
			CookieCart cookieCart = new CookieCart(request);
			BookVO book = bookService.getBookDetails(isbn);
			CartItemVO newCartItem = new CartItemVO(book, "", type);
			cookieCart.addToCart(response, newCartItem);
			shoppingCart = new CartVO("", cookieCart.getCartItems());

		} else {
			//shoppingCart = (CartVO) modelMap.get("ShoppingCart");
			// shoppingCart=(CartVO) session.getAttribute("ShoppingCart");
			if (shoppingCart == null || shoppingCart.equals("")) {
				shoppingCart = new CartVO();
			}
			boolean bookInDB = shoppingCartServiceImpl.checkBookIntoCart(userId,
					isbn, type);
			if (bookInDB ==false) {
				shoppingCart = shoppingCartServiceImpl.addToCart(shoppingCart,
						userId, isbn, type);
			}
			modelMap.addAttribute("size", shoppingCart.getCartItems().size());
		}
		modelMap.addAttribute("ShoppingCart", shoppingCart);
	}

	@RequestMapping(value = "/viewCart", method = RequestMethod.GET)
	public String viewCart(ModelMap map, HttpSession httpSession,
			HttpServletRequest request, HttpServletResponse response)
			throws SapeStoreException {

		String userId = (String) httpSession.getAttribute("userLoginName");
		CartVO cart;

		if (userId == null || userId.equals("")) {
			CookieCart cookieCart = new CookieCart(request);
			cart = new CartVO();
			cart.setUserId("");
			cart.setCartItems(cookieCart.getCartItems());
//			for (int i = 0; i < cart.getCartItems().size(); i++) {
//				cart.getCartItems().get(i).setTitle(cart.getCartItems().get(i).getTitle().replace("ttttttttt",""));
//			}
		} else {
			cart = shoppingCartServiceImpl.getCartItems(userId);
//			for (int i = 0; i < cart.getCartItems().size(); i++) {
//				cart.getCartItems().get(i).setTitle(cart.getCartItems().get(i).getTitle().replace("ttttttttt",""));
//			}
		}
		
		map.addAttribute("ShoppingCart", cart);
		map.addAttribute("cartSize", cart.getCartItems().size());
		return "DisplayShoppingCart";
	}

	@RequestMapping(value = "/checkout", method = RequestMethod.GET)
	public String checkOut(HttpServletRequest request,
			HttpServletResponse response, ModelMap model)
			throws SapeStoreException {
		
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userLoginName");
		if (userId == null || userId.equals("")) {
			//session.setAttribute("Carturl", "viewCart");
			CookieCart cookieCart = new CookieCart(request);
			CartVO cart = new CartVO();
			cart.setUserId("");
			cart.setCartItems(cookieCart.getCartItems());
			if(cart.getCartItems().size()==0){
				return "redirect:/welcome";
				//return "index";
			}else{
				return "login";
			}
		} else {

			CartVO cart = shoppingCartServiceImpl.getCartItems(userId);
			if(cart.getCartItems().size() == 0){
				return "redirect:/welcome";
				//return "index";
			}
			
			model.addAttribute("ShoppingCart", cart.getCartItems());
			int total = 0;
			for(int i=0;i<cart.getCartItems().size();i++){
				if(cart.getCartItems().get(i).getBookPrice() == null)
					cart.getCartItems().get(i).setBookPrice(new BigDecimal(1));
				total += cart.getCartItems().get(i).getQuantity() * cart.getCartItems().get(i).getBookPrice().intValue();
				cart.getCartItems().get(i).setTotal(new BigDecimal(cart.getCartItems().get(i).getQuantity() * cart.getCartItems().get(i).getBookPrice().intValue()));
			}
			for (int i = 0; i < cart.getCartItems().size(); i++) {
				cart.getCartItems().get(i).setTitle(cart.getCartItems().get(i).getTitle().replace("ttttttttt",""));
			}
			model.addAttribute("total", total);
			session.setAttribute("total", total);
		}
		return "BookOrderConfirmation";
	}

	@RequestMapping(value = "/confirmaddress", method = RequestMethod.GET)
	public String confirmAddress(ModelMap map) throws SapeStoreException {
		return "OrderConfirmation";
	}

	// @RequestMapping("/confirmOrder")

	@RequestMapping(value = "/addrConfirm", method = RequestMethod.GET)
	public String confirmAddr(ModelMap map) throws SapeStoreException {
		return "redirect:/shipAddress";
	}

	@RequestMapping(value = "/removeFromCart", method = RequestMethod.POST)
	public String removeFromCart(@RequestParam String isbn,
			@RequestParam String type, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userLoginName");
		CartVO shoppingCart;

		if (userId == null || userId.equals("")) {
			CookieCart cookieCart = new CookieCart(request);
			cookieCart.removeFromCart(response, type, "", isbn);
			shoppingCart = new CartVO();
			shoppingCart.setUserId("");
			shoppingCart.setCartItems(cookieCart.getCartItems());
			model.addAttribute("ShoppingCart", shoppingCart);
		} else {
			shoppingCart = (CartVO) model.get("ShoppingCart");
			try {
				shoppingCart = shoppingCartServiceImpl.removeFromCart(
						shoppingCart, userId, type, isbn);
				model.addAttribute("ShoppingCart", shoppingCart);
			} catch (SapeStoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "DisplayShoppingCart";
	}
	
	@RequestMapping(value="/confirmOrder",method=RequestMethod.GET)
    public String confirmedOrder(HttpServletRequest request,
			HttpServletResponse response, ModelMap model)
			throws SapeStoreException {
    	HttpSession session = request.getSession();
    
		String userId = (String) session.getAttribute("userLoginName");
		Address address = (Address)session.getAttribute("shippingAddress");
		CartVO cart = shoppingCartServiceImpl.getCartItems(userId);
		int total = 0;
		for(int i=0;i<cart.getCartItems().size();i++){
			if(cart.getCartItems().get(i).getBookPrice() == null)
				cart.getCartItems().get(i).setBookPrice(new BigDecimal(1));
			total += cart.getCartItems().get(i).getQuantity() * cart.getCartItems().get(i).getBookPrice().intValue();
			cart.getCartItems().get(i).setTotal(new BigDecimal(cart.getCartItems().get(i).getQuantity() * cart.getCartItems().get(i).getBookPrice().intValue()));
		}
		List cartList = null;
		if(cart!=null){
			cartList=cart.getCartItems();
		}		
	   orderDao.confrimedOrder(userId,cartList,total,address);
	   return "redirect:/welcome";
	   /*HomePageController home = new HomePageController();
	   if(userId == null || userId.equals(""))
		   return home.welcome(false, model, request, request.getSession());
	   else{
		   return home.welcome(false, model, request, request.getSession());
	   }*/
    }

	@RequestMapping(value = "/varyQuantity", method = RequestMethod.POST)
	public void checkQuantity(@RequestParam String isbn, @RequestParam String type,
			@RequestParam int quantity, HttpServletRequest request,
			HttpServletResponse response) {
		int bookQuantity = shoppingCartServiceImpl.checkQuantity(isbn);
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userLoginName");
		
		//	If user is not logged in, any quantity can be selected
		if(userId == null){
			try {
				response.getWriter().write("true");
				return;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				LOGGER.error("RESPONSE CAN NOT BE SENT");
			}
		}
		
		bookQuantity = bookQuantity - 1;
		if (quantity > bookQuantity) {
			try {
				response.getWriter().write(""+bookQuantity);
				shoppingCartServiceImpl.updateQuantity(userId,isbn,type,bookQuantity);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				LOGGER.error("RESPONSE CAN NOT BE SENT");
			}
		} else {
			shoppingCartServiceImpl.updateQuantity(userId,isbn,type,quantity);
			try {
				response.getWriter().write("true");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				LOGGER.error("RESPONSE CAN NOT BE SENT");
			}
		}
		return;
	}

}
