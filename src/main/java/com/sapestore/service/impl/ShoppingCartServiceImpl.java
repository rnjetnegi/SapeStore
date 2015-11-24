package com.sapestore.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.dao.CartDao;
import com.sapestore.dao.ProductDao;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;
import com.sapestore.service.ShoppingCartService;
import com.sapestore.util.CookieCart;
import com.sapestore.vo.CartVO;

/**
 * Service class for Add to Cart functionality.
 * 
 * CHANGE LOG VERSION DATE AUTHOR MESSAGE 1.0 20-06-2014 SAPIENT Initial version
 */

@Service("shoppingCartService")
public class ShoppingCartServiceImpl implements ShoppingCartService {

	private final static SapeStoreLogger LOGGER = SapeStoreLogger
			.getLogger(ShoppingCartServiceImpl.class.getName());

	@Autowired
	private ProductDao productDao;
	@Autowired
	private CartDao cartDao;

	/**
	 * Adds books to cart
	 */

	/**
	 * Get book information on the basis of the ISBN provided
	 * 
	 * @param isbn
	 * @return
	 * @throws SapeStoreSystemException
	 */
	/*
	 * private BookVO getBookInfo(String isbn) throws SapeStoreException {
	 * LOGGER.debug("getBookInfo method: START"); BookVO addToCartbean = null;
	 * addToCartbean = productDao.getBookDetails(isbn);
	 * LOGGER.debug("getBookInfo method: END"); return addToCartbean;
	 * 
	 * }
	 */

	/**
	 * Get cart information by passing the userID
	 * 
	 * @param userId
	 * @return
	 * @throws SapeStoreSystemException
	 */
	public CartVO getCartItems(String userId) throws SapeStoreException {
		LOGGER.debug("getCartItems method: START");
		CartVO viewCartbean = null;
		viewCartbean = cartDao.viewCart(userId);
		LOGGER.debug("getBookInfo method: END");
		return viewCartbean;
	}

	/**
	 * Add Book To Cart by passing userId, isbn and type whether buy or rent
	 * 
	 * @param shoppingCart
	 * @param userId
	 * @param isbn
	 * @param type
	 * @return
	 * @throws SapeStoreSystemException
	 */
	public CartVO addToCart(CartVO shoppingCart, String userId, String isbn,
			String type) throws SapeStoreException {
		LOGGER.debug("addToCart method: START");

		CartVO cart = cartDao.addToCart(shoppingCart, userId, isbn, type);
		LOGGER.debug("addToCart method: END");
		return cart;
	}

	public CartVO removeFromCart(CartVO shoppingCart, String userId,
			String type, String isbn) throws SapeStoreException {
		LOGGER.debug("removeFromCart method: START");
		shoppingCart = cartDao.removeFromCart(shoppingCart, userId, type, isbn);
		LOGGER.debug("addToCart method: END");
		return shoppingCart;
	}

	/*
	 * public CartVO getCart(String userId){ return cartDao.getCart(userId); }
	 */
	public int checkQuantity(String isbn) {
		return cartDao.checkQuantity(isbn);
	}

	@Override
	public boolean checkBookIntoCart(String userId, String isbn,
			String type) {
		return cartDao.checkBookIntoCart(userId, isbn, type);
	}

	public CartVO addCookieToCart(CookieCart cookieCart, String userId,
			HttpServletResponse httpServletResponse) {
		return cartDao.addCookieToCart(cookieCart, userId, httpServletResponse);

	}

	public void updateQuantity(String userId, String isbn, String type, int quantity) {
		// TODO Auto-generated method stub
		cartDao.updateQuantity(userId, isbn, type, quantity);
	}

	

}
