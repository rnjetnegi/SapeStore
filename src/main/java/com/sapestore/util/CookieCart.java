package com.sapestore.util;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sapestore.vo.CartItemVO;

/**
 * Util class to manipulate cart cookies for guest user 
 * @author ilisze
 *
 */
public class CookieCart {
	
	private List<CartItemVO> cartItems;
	private Cookie cartCookie;
	
	/**
	 * Take HttpServletRequest var and retrieve cookie and cart list from cookie
	 * @param request
	 */
	public CookieCart(HttpServletRequest request){
		Cookie cookie = this.getCookie(request, "SapeStoreCookie");
		this.cartCookie = cookie;
		if(cookie != null){
	    	Type type = new TypeToken<List<CartItemVO>>(){}.getType();
		    List<CartItemVO> cartList = new Gson().fromJson(cookie.getValue(), type);
		    this.cartItems = cartList;
		}
		else {
			this.cartItems = new ArrayList<CartItemVO>();
		}
	}
	
	public CookieCart(){
		
	}
	
	public Cookie getCartCookie() {
		return cartCookie;
	}

	public void setCartCookie(Cookie cartCookie) {
		this.cartCookie = cartCookie;
	}

	public List<CartItemVO> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItemVO> cartItems) {
		this.cartItems = cartItems;
	}
	
	/**
	 * Take CartItemVO and add it to class var
	 * @param item
	 */ 
	public void addToCartItems(CartItemVO item){
		cartItems.add(item);
	}

	/**
	 * take item and response. Add item to cart (if not already in there) and then store cookie
	 * @param response
	 * @param item
	 */
	public void addToCart(HttpServletResponse response, CartItemVO item){
		String bookType = item.getType();
		String currUserId = item.getUserId();
		String bookIsbn = item.getIsbn();
		if(this.getCartItem(bookType, currUserId, bookIsbn) == null){
			cartItems.add(item);
			this.storeCartCookie(response);
		}
	}

	/**
	 * take item, remove from cart list if possible, save with response var
	 * @param response
	 * @param item
	 * @return boolean for successful remove
	 */
	public boolean removeFromCart(HttpServletResponse response, CartItemVO item){
		CartItemVO cartItem = this.getCartItem(item);
		if(cartItem != null){
			cartItems.remove(cartItem);
			this.storeCartCookie(response);
			return true;
		}
		return false;
	}

	/**
	 * get item type, userId, and bookIsbn, and remove from cart list if possible
	 * then store cookie
	 * @param response
	 * @param bookType
	 * @param currUserId
	 * @param bookIsbn
	 * @return boolean for success
	 */
	public boolean removeFromCart(HttpServletResponse response, String bookType, String currUserId, String bookIsbn){
		CartItemVO cartItem = this.getCartItem(bookType, currUserId, bookIsbn);
		if(cartItem != null){
			cartItems.remove(cartItem);
			this.storeCartCookie(response);
			return true;
		}
		return false;
	}
	
	/**
	 * take original item and new item, remove original item and add the new one
	 * then use response to add the cookie
	 * @param response
	 * @param original
	 * @param newItem
	 * @return
	 */
	public boolean updateCartItem(HttpServletResponse response, CartItemVO original, CartItemVO newItem){
		CartItemVO cartItem = this.getCartItem(original);
		if(cartItem != null){
			cartItems.remove(cartItem);
			this.addToCartItems(newItem);
			this.storeCartCookie(response);
			return true;
		}
		return false;
	}

	/**
	 * using the composite key - bookType, userId, and bookIsbn - find the CartItem from the list
	 * @param bookType
	 * @param currUserId
	 * @param bookIsbn
	 * @return
	 */
	private CartItemVO getCartItem(String bookType, String currUserId, String bookIsbn){
		ListIterator<CartItemVO> cartIter = cartItems.listIterator();
		while(cartIter.hasNext()){
			CartItemVO currItem = cartIter.next();
			if(currItem.equals(bookType, currUserId, bookIsbn)){
				return currItem;
			}
		}
		return null;
	}
	
	/**
	 * iterate through cartItems to find and return a CartItemVO that matches the 
	 * param item
	 * @param item
	 * @return CartItemVO found or NULL if none found
	 */
	private CartItemVO getCartItem(CartItemVO item){
		ListIterator<CartItemVO> cartIter = cartItems.listIterator();
		while(cartIter.hasNext()){
			CartItemVO currItem = cartIter.next();
			if(currItem.equals(item.getType(),  item.getUserId(), item.getIsbn())){
				return currItem;
			}
		}
		return null;
	}
	
	/**
	 * create Cookie attributes, check if cookie exists already, and then update
	 * or create cookie. Use response to add cookie to HttpServletResponse 
	 * @param response
	 */
	public void storeCartCookie(HttpServletResponse response){
	    final String cookieName = "SapeStoreCookie";
	    final Boolean useSecureCookie = new Boolean(false);
	    final int expiryTime = 60 * 60 * 24;  // 24h in seconds
	    final String cookiePath = "/";
	    
		//convert cartItems list into JSON string to store in cookie value
		String cookieJson = new Gson().toJson(this.cartItems);

	    final String cookieValue = cookieJson;
		//if cookie exists in page already, update value
		if(this.cartCookie != null){
			this.cartCookie.setValue(cookieValue);
		}
		//create new cookie
		else{
		    this.cartCookie = new Cookie(cookieName, cookieValue);
		}
	    this.cartCookie.setSecure(useSecureCookie.booleanValue());  // determines whether the cookie should only be sent using a secure protocol, such as HTTPS or SSL
	    this.cartCookie.setMaxAge(expiryTime);  // A negative value means that the cookie is not stored persistently and will be deleted when the Web browser exits. A zero value causes the cookie to be deleted.
	    this.cartCookie.setPath(cookiePath);  // The cookie is visible to all the pages in the directory you specify, and all the pages in that directory's subdirectories

		response.addCookie(this.cartCookie);
	}
	
	/**
	 * get Cookie from HttpServletRequest based on title 
	 * @param request
	 * @param title
	 * @return cookie if found else null
	 */
	public Cookie getCookie(HttpServletRequest request, String title){
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for(Cookie cookie : cookies){
			    if(title.equals(cookie.getName())){
			    	return cookie;
			    }
			}
		}
			return null;

	}
	
	/**
	 * remove cookie from HttpServletResponse
	 * @param response
	 */
	public void removeCartCookie(HttpServletResponse response){
		//if cookie exists in page already remove it
	    final Boolean useSecureCookie = new Boolean(false);
	    final String cookiePath = "/";
		if(this.cartCookie != null){
			this.cartCookie.setValue(null);
			this.cartCookie.setMaxAge(0);
			this.cartCookie.setSecure(useSecureCookie.booleanValue());  // determines whether the cookie should only be sent using a secure protocol, such as HTTPS or SSL
		    this.cartCookie.setPath(cookiePath);  
			response.addCookie(this.cartCookie);
		}
	}
}
