
package com.sapestore.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.sapestore.common.SapeStoreLogger;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;
import com.sapestore.hibernate.entity.BookCategory;
import com.sapestore.hibernate.entity.SearchBook;
import com.sapestore.partner.services.SSPartnerBooksListBean;
import com.sapestore.partner.services.SSPartnerWebService;
import com.sapestore.partner.services.SSPartnerWebServicePortType;
import com.sapestore.service.impl.BookServiceImpl;
import com.sapestore.service.impl.SearchServiceImpl;
import com.sapestore.service.impl.ShoppingCartServiceImpl;
import com.sapestore.service.BookService;
import com.sapestore.util.CookieCart;
import com.sapestore.vo.CartItemVO;
import com.sapestore.vo.CartVO;

/**
 * @author mku106
 * this is the controller  class for search functionality
 * CHANGE LOG
 *      VERSION    DATE          AUTHOR       MESSAGE               
 *        1.0    21-10-2015     SAPIENT      Initial version
 */

@Controller
public class SearchController {
	
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
			.getLogger(SearchController.class.getName());

	String emptyListInfo; //Can be used in multiple functions in future.
	
	@Autowired
	SearchServiceImpl searchServiceImpRef;
	
	
	


	public SearchController() {
		
	}
	
	/*@RequestMapping(value="/page",method=RequestMethod.GET)
	public String redirect(){
		return "searchBook";
	}*/
//	
//	@RequestMapping(value="/result",method=RequestMethod.GET)
//	public String resultPage(){
//		return "searchResult";
//	}


	/**
	 * Processes the search requests
	 * @param searchKeyword
	 * @param modelMap
	 * @param partnerStoreCheck
	 * @return
	 *
	 */

	@RequestMapping(value = "/searchBook", method = RequestMethod.POST)
	public String searchBook(
			@RequestParam("search") String searchKeyword,ModelMap model, HttpServletRequest request,HttpSession httpSession) {

		//if (!partnerStoreCheck) {
			
			List<SearchBook> resultBookList = searchServiceImpRef
					.searchBook(searchKeyword);
			/*List<SearchBook> partnerBookList = searchServiceImpRef
					.searchPartnerBook(searchKeyword);*/
//			resultBookList.addAll(partnerBookList);
			
			
			if (resultBookList == null || resultBookList.isEmpty()) {
				emptyListInfo = "Result not found";
				model.addAttribute("emptyListInfo", emptyListInfo);
			
			} else {
				emptyListInfo="Result found";
				model.addAttribute("resultBookList", resultBookList);
			}
			
			List<BookCategory> bookCategoryList = null;
			try {
				bookCategoryList = getCategoryList();
			} catch (SapeStoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (bookCategoryList != null) {
				model.addAttribute("catList", bookCategoryList);
			}

			//get user id. Set to empty if null
			String userId = (String) httpSession.getAttribute("userLoginName");
			if (userId == null)
				userId = "";
			
			//get cart, set book list attribute in model
			CartVO cart;
			cart = this.fetchCart(userId, request);
			List<String> booksInCart = this.getBookIsbnList(cart);
			model.addAttribute("booksInCart", booksInCart);
			
		return "searchResult";

	}


	/**
	 * Processes the predictive search  search requests
	 * 
	 * @return
	 *
	 */
	
	@RequestMapping(value = "/predictiveSearch", method = RequestMethod.POST)
	public void predictiveSearch(@RequestParam String term,
			
			HttpServletRequest request, HttpServletResponse response)
					 {
	
		response.setContentType("application/json");
		try {
			String searchKeyword = term;
			searchKeyword=searchKeyword.trim();
			ArrayList<String> resultBookList = searchServiceImpRef.searchText(searchKeyword);
			if (!resultBookList.isEmpty()) {
				String list=resultBookList.get(0);
			    LOGGER.info(list);
			} else {
				LOGGER.info("empty");
			}
			// ArrayList<String> list = dataDao.getFrameWork(term);		
			Gson gsonObj = new Gson();
			String finalSearchList = gsonObj.toJson(resultBookList);
			response.getWriter().write(finalSearchList);
		} catch (IOException ioExceptionRef) {
			LOGGER.error("IOException in predictive search method while using response in searchContoller ");
		}
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
		BookService bookService = getBookService();
		
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
