package com.sapestore.service;

import javax.servlet.http.HttpServletResponse;

import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;
import com.sapestore.util.CookieCart;
import com.sapestore.vo.CartVO;

/**
 * Service interface for Add to Cart functionality.
 * 
 * CHANGE LOG 
 * VERSION 	DATE 		AUTHOR 	MESSAGE 
 * 1.0 		20-06-2014 	SAPIENT Initial version
 */

public interface ShoppingCartService {
	
	/**
	 * Adds books to cart
	 * @param shoppingCart
	 * @param isbn
	 * @return
	 * @throws SapeStoreSystemException
	 */

	CartVO addToCart(CartVO shoppingCart, String isbn,String userId,String type) throws SapeStoreException;
     int checkQuantity (String isbn)  ;
     public boolean checkBookIntoCart(String userId,String isbn,String type);
     public CartVO addCookieToCart(CookieCart cookieCart, String userId,
 			HttpServletResponse httpServletResponse);
}
