package com.sapestore.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;
import com.sapestore.hibernate.entity.Book;
import com.sapestore.hibernate.entity.BookCategory;
import com.sapestore.service.BookService;
import com.sapestore.service.impl.ShoppingCartServiceImpl;
import com.sapestore.util.CookieCart;
import com.sapestore.vo.CartItemVO;
import com.sapestore.vo.CartVO;
import com.sapestore.vo.UserVO;
import com.sapestore.vo.HomeVO;

/**
 * This is a controller class for landing and post customer login pages.
 *
 * CHANGE LOG
 *      VERSION    DATE          AUTHOR       MESSAGE               
 *        1.0    20-06-2014     SAPIENT      Initial version
 */

@Controller
@SessionAttributes("checkMe")
public class HomePageController {

	private List<Book> bookList;
	private List<BookCategory> catList;
	private String categoryName;
	private boolean checkMe;

	@Autowired
	private BookService bookService;
	@Autowired
	ShoppingCartServiceImpl shoppingCartServiceImpl;

	public BookService getBookService() {
		return bookService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	private final static SapeStoreLogger LOGGER = SapeStoreLogger
			.getLogger(HomePageController.class.getName());

	public boolean isCheckMe() {
		return checkMe;
	}

	public void setCheckMe(boolean checkMe) {
		this.checkMe = checkMe;
	}

	public List<Book> getBookList() {
		return bookList;
	}

	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}

	public List<BookCategory> getCatList() {
		return catList;
	}

	public void setCatList(List<BookCategory> catList) {
		this.catList = catList;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * Processes the home page requests.
	 * @param checkMe
	 * @param modelMap
	 * @return
	 * @throws SapeStoreSystemException
	 */
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String welcome(@RequestParam(value="checkMe",required=false) boolean checkMe,
			ModelMap modelMap,HttpServletRequest httpServletRequest,HttpSession httpSession) throws SapeStoreException {
		
		if (httpSession.getAttribute("isAdmin") != null && (boolean) httpSession.getAttribute("isAdmin") == true) {
			return "redirect:/admin/manageInventory";
		}
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("welcome method: START");
		}
		
		List<Book> bookList = null; 

		try {
			this.setCatList(getCategoryList());
			Object checkMeFromSession = httpSession.getAttribute("checkMe");
			bookList = getBooksList(checkMeFromSession);
			
            if (checkMeFromSession!=null && (boolean) checkMeFromSession) {
            	bookList.addAll(bookService.getBookFromWebService(0));
            }
			this.setBookList(bookList);
			this.setCategoryName("Popular");
			modelMap.addAttribute("bookList", this.getBookList());
			modelMap.addAttribute("catList", this.getCatList());
			if(httpSession.getAttribute("checkMe")!=null){
				modelMap.addAttribute("checkMe", httpSession.getAttribute("checkMe"));
			}
			else
			{
				modelMap.addAttribute("checkMe",false);
			}
			modelMap.addAttribute("categoryName", getCategoryName());
			modelMap.addAttribute("userlogin", new UserVO());
			modelMap.addAttribute("categoryId", 0);
			modelMap.addAttribute("welcome", new HomeVO());
		} catch (SapeStoreSystemException e) {
			LOGGER.error("welcome method: ERROR: " + e);
			modelMap.addAttribute("errorMessage",
					"Error in opening the welcome page.");
			return "redirect:/errorPage";
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("welcome method: ModelMap: " + modelMap);
			LOGGER.debug("welcome method: END");
		}
		
		//get user id. Set to empty if null
		String userId = (String) httpSession.getAttribute("userLoginName");
		if (userId == null)
			userId = "";
		
		//get cart, set book list attribute in model
		CartVO cart;
		cart = this.fetchCart(userId, httpServletRequest);
		List<String> booksInCart = this.getBookIsbnList(cart);
		modelMap.addAttribute("booksInCart", booksInCart);

		return "index";
	}

	private List<Book> getBooksList(Object checkMeFromSession) throws SapeStoreException{
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getBooksList method: START");
		}
		List<Book> bookList = null;
		try {
			try {
				bookList = bookService.getBookList(0,checkMeFromSession);
			} catch (SapeStoreSystemException e) {
				LOGGER.error("getBooksList method: ERROR: " + e);
			}
			this.setBookList(bookList);
		} catch (SapeStoreSystemException ex) {
			LOGGER.error("welcome method: ERROR: " + ex);
			return null;
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getBooksList method: END");
		}

		return bookList;
	}

	/**
	 * Processes the requests to pull book list by category
	 * @param categoryId
	 * @param categoryName
	 * @param checkMe
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/bookListByCat", method = RequestMethod.GET)
	public String getBooksListByCat(@ModelAttribute("welcome") HomeVO welcome,@RequestParam("categoryId") int categoryId,
			@RequestParam("categoryName") String categoryName,
			ModelMap modelMap, HttpServletRequest httpServletRequest, HttpSession httpSession)
			throws Exception {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getBooksListByCat method: END");
		}
		
		List<Book> list = null;
		try {
			this.setCheckMe(welcome.isCheckMe());
			list = bookService.getBookList(categoryId,this.isCheckMe());
            if (this.isCheckMe()) {
            	list.addAll(bookService.getBookFromWebService(categoryId));
            }
			this.setBookList(list);
			this.setCatList(getCategoryList());
		} catch (SapeStoreSystemException ex) {
			LOGGER.error("getBooksListByCat method: END" + ex);
			modelMap.addAttribute("errorMessage",
					"Error in getting book list by category");
			return "redirect:/errorPage";
		}
		modelMap.addAttribute("bookList", list);
		modelMap.addAttribute("catList", this.getCatList());
		modelMap.addAttribute("categoryName", categoryName);
		modelMap.addAttribute("checkMe", this.checkMe);
		modelMap.addAttribute("userlogin", new UserVO());
		modelMap.addAttribute("categoryId", categoryId);
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getBooksListByCat method: END");
		}
		
		String userId = (String) httpSession.getAttribute("userLoginName");
		if (userId == null)
			userId = "";
		
		//get cart, set book list attribute in model
		CartVO cart;
		cart = this.fetchCart(userId, httpServletRequest);
		List<String> booksInCart = this.getBookIsbnList(cart);
		modelMap.addAttribute("booksInCart", booksInCart);
		
		return "index";
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
	
	/**
	 * fetch Cart from cookies or DB table depending on if logged in or not
	 * 
	 * @param userId
	 * @param request
	 * @return CartVO item for user
	 */
	public CartVO fetchCart(String userId, HttpServletRequest request) {
		CartVO cart = new CartVO();
		if (userId == null || userId.equals("")) {
			CookieCart cookieCart = new CookieCart(request);
			cart = new CartVO(userId, cookieCart.getCartItems());
		} else {
			try {
				cart = shoppingCartServiceImpl.getCartItems(userId);
			} catch (SapeStoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return cart;
	}

	/**
	 * iterate through cart and check if book/book type for user is in cart
	 * already
	 * 
	 * @param cart
	 * @param isbn
	 * @param type
	 * @param userId
	 * @return is book/book type in cart already
	 */
	public boolean checkInCart(CartVO cart, String isbn, String type,
			String userId) {
		List<CartItemVO> cartList = cart.getCartItems();
		ListIterator<CartItemVO> cartIter = cartList.listIterator();
		while (cartIter.hasNext()) {
			CartItemVO item = cartIter.next();
			if (item.equals(type, userId, isbn))
				return true;
		}
		return false;
	}
	
	public List<String> getBookIsbnList(CartVO cart){
		List<CartItemVO> cartList = cart.getCartItems();
		ListIterator<CartItemVO> cartIter = cartList.listIterator();
		List<String> bookArr = new ArrayList<String>();
		while(cartIter.hasNext()){
			CartItemVO item = cartIter.next();
			bookArr.add(item.getIsbn());
		}
		return bookArr;
	}
}
