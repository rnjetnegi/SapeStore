package com.sapestore.controller;

import java.io.IOException;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.dao.ProductDao;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.hibernate.entity.BookRatingComments;
import com.sapestore.service.BookService;
import com.sapestore.service.impl.BookServiceImpl;
import com.sapestore.service.impl.ShoppingCartServiceImpl;
import com.sapestore.util.CookieCart;
import com.sapestore.vo.AddCommentRating;
import com.sapestore.vo.BookRatingCommentsVO;
import com.sapestore.vo.BookVO;
import com.sapestore.vo.CartItemVO;
import com.sapestore.vo.CartVO;

/**
 * @author ssi228
 *
 */
@Controller
public class BookDetails {

	@Autowired
	private BookService bookService;
	@Autowired
	ShoppingCartServiceImpl shoppingCartServiceImpl;

	/**
	 * Logger for log messages.
	 */
	private final static SapeStoreLogger LOGGER = SapeStoreLogger
			.getLogger(ProductDao.class.getName());

	private final String EMPTY = "";

	/**
	 * Method to view book details
	 * 
	 * @param isbn
	 *            - Book isbn
	 * @param model
	 * @return
	 * @throws SapeStoreException
	 */
	@RequestMapping(value = "/bookDetails", method = RequestMethod.GET)
	public String bookDetails(@RequestParam String isbn,@RequestParam(required=false) String tab, ModelMap model,
			HttpServletRequest request, HttpSession httpSession)
			throws SapeStoreException {

		if (isbn == null || isbn.equals(""))
			return "index";

		BookVO book = bookService.getBookDetailsForDate(isbn);

		if (book == null)
			return "index";

		// get userId from session. If no session set to empty
		String userId = (String) httpSession.getAttribute("userLoginName");
		if (userId == null)
			userId = "";

		CartVO cart;

		// fetch cart
		cart = this.fetchCart(userId, request);
		model.addAttribute("ShoppingCart", cart);

		// add booleans to model to enable/disable cart in JSP

		boolean buyAddCartDisabled = this.checkInCart(cart, isbn, "PURCHASED",
				userId);
		boolean rentAddCartDisabled = this.checkInCart(cart, isbn, "RENTED",

				userId);
		model.addAttribute("buyAddCartDisabled", buyAddCartDisabled);
		model.addAttribute("rentAddCartDisabled", rentAddCartDisabled);

		model.addAttribute("bookModel", book);
		/**
		 * To check the number of comments on first page to be 3 only
		 */
		book.getCommentList().size();
		model.addAttribute("list", book.getCommentList());

		String day = "";
		String month = "";
		String year = "";
		String months[] = { "January", "February", "March", "April", "May",
				"June", "July", "August", "September", "October", "November",
				"December" };

		for (int i = 0; i < book.getCommentList().size(); i++) {
			String date = ((BookRatingCommentsVO) book.getCommentList().get(i))
					.getBookCommentDate().toString();// date
			year = date.substring(0, 4);
			month = date.substring(5, 7);
			day = date.substring(8, 10);
			date = day + dateSufix(day) + " "
					+ months[Integer.parseInt(month) - 1] + " " + year;
			book.getCommentList().get(i).setDate(date);
		}

		model.addAttribute("catList", bookService.getCategoryList());
		model.addAttribute("number_Ratings", book.getCommentList().size());

		double rating = 0.0;
		for (int i = 0; i < book.getCommentList().size(); i++) {
			rating = rating + book.getCommentList().get(i).getBookRating();
		}

		if (book.getCommentList().size() > 0){ // Removing Arithematic exception
			double number = ((double) rating / book.getCommentList().size());
			number = Math.round(number * 10);
			number = number/10;
			model.addAttribute("rating",number);
		}
		model.addAttribute("isbn", isbn);
		model.addAttribute("userId",request.getSession().getAttribute("userLoginName"));
		return "BookDetail";
	}

	private String dateSufix(String sDay) {
		String stringDay = new String();
		int day = Integer.parseInt(sDay);
		if (day == 3 || day == 23) {
			stringDay += "rd";
		} else if (day == 2 || day == 22) {
			stringDay += "nd";
		} else if (day == 1 || day == 21) {
			stringDay += "st";
		} else {
			stringDay += "th";
		}
		return stringDay;
	}

	/**
	 * Function to Check whether if the user has logged in or not.
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @throws SapeStoreException
	 */
	@RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
	public void commentPage(HttpServletRequest request,
			HttpServletResponse response) throws SapeStoreException {

		HttpSession session = request.getSession();
		String user = (String) session.getAttribute("userLoginName");
		String isbn = request.getParameter("isbn");

		response.setContentType("text/plain"); // Set content type of the
												// response so that jQuery knows
												// what it can expect.
		response.setCharacterEncoding("UTF-8"); // Encoding Type

		if (user == null || user.equals("")) {
			try {
				response.getWriter().write("false");
			} catch (IOException e) {
				LOGGER.error("BOOK LOGIN CHECK CONTROLLER FAIL");
			}
		} else {
			try {
				BookRatingCommentsVO bookRatingVo = bookService.getBookComment(isbn, user);
				response.getWriter().write("true");
				response.getWriter().write(bookRatingVo.getBookComments());
				response.getWriter().write(bookRatingVo.getBookRating() + "");
			} catch (IOException e) {
				LOGGER.error("BOOK LOGIN CHECK CONTROLLER FAIL");
			}
		}
	}

	/**
	 * Method to add user comment to a book
	 * 
	 * @param commentRate
	 * @param request
	 * @return
	 * @throws SapeStoreException
	 */
	@RequestMapping(value = "/comments", method = RequestMethod.GET)
	public String addComment(@RequestParam String isbn, @RequestParam String comment, @RequestParam String rating,
			HttpServletRequest request) throws SapeStoreException {
		//String isbn = request.getParameter("isbn");
		//String comment = request.getParameter("comment");
		//String rating = request.getParameter("rating");
		HttpSession session = request.getSession();
		String user = (String) session.getAttribute("userLoginName");
		// isbn=commentRate.getIsbn();
		// comment=commentRate.getBookComments();
		bookService.addBookComment(isbn, user, comment,
				Double.parseDouble(rating));
		return "login";
	}

	/**
	 * Method to handle request to traverse through book reviews
	 * 
	 * @param isbn
	 *            - Book isbn
	 * @param model
	 * @return
	 * @throws SapeStoreException
	 */
	@RequestMapping(value = "/bookReviewsForDate", method = RequestMethod.GET)
	public String bookReviewsDate(@RequestParam String isbn, ModelMap model)
			throws SapeStoreException {

		/**
		 * Check if ISBN is empty
		 */
		if (isbn == null || isbn.equals(""))
			return "index";

		BookVO book = bookService.getBookDetailsForDate(isbn);

		/**
		 * Check if ISBN is invalid
		 */
		if (book == null)
			return "index";

		model.addAttribute("bookModel", book);

		/**
		 * Check if page Number is valid
		 */

		String day = "";
		String month = "";
		String year = "";
		String months[] = { "January", "February", "March", "April", "May",
				"June", "July", "August", "September", "October", "November",
				"December" };

		for (int i = 0; i < book.getCommentList().size(); i++) {
			String date = ((BookRatingCommentsVO) book.getCommentList().get(i))
					.getBookCommentDate().toString();// date
			year = date.substring(0, 4);
			month = date.substring(5, 7);
			day = date.substring(8, 10);
			date = day + dateSufix(day) + " "
					+ months[Integer.parseInt(month) - 1] + " " + year;
			book.getCommentList().get(i).setDate(date);
		}

		model.addAttribute("catList", bookService.getCategoryList());
		model.addAttribute("number_Ratings", book.getCommentList().size());
		model.addAttribute("rating", book.getRating());
		model.addAttribute("isbn", isbn);
		model.addAttribute("list", book.getCommentList());

		return "BookDetail";
	}

	@RequestMapping(value = "/bookReviewsForRatings", method = RequestMethod.GET)
	public String bookReviewsRatings(@RequestParam String isbn, ModelMap model)
			throws SapeStoreException {

		/**
		 * Check if ISBN is empty
		 */
		if (isbn == null || isbn.equals(""))
			return "index";

		BookVO book = bookService.getBookDetailsForRatings(isbn);

		/**
		 * Check if ISBN is invalid
		 */
		if (book == null)
			return "index";

		model.addAttribute("bookModel", book);

		/**
		 * Check if page Number is valid
		 */

		String day = "";
		String month = "";
		String year = "";
		String months[] = { "January", "February", "March", "April", "May",
				"June", "July", "August", "September", "October", "November",
				"December" };

		for (int i = 0; i < book.getCommentList().size(); i++) {
			String date = ((BookRatingCommentsVO) book.getCommentList().get(i))
					.getBookCommentDate().toString();// date
			year = date.substring(0, 4);
			month = date.substring(5, 7);
			day = date.substring(8, 10);
			date = day + dateSufix(day) + " "
					+ months[Integer.parseInt(month) - 1] + " " + year;
			book.getCommentList().get(i).setDate(date);
		}
		model.addAttribute("list", book.getCommentList());
		model.addAttribute("catList", bookService.getCategoryList());
		model.addAttribute("number_Ratings", book.getCommentList().size());
		model.addAttribute("rating", book.getRating());
		model.addAttribute("isbn", isbn);

		return "BookDetail";
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

}