package com.sapestore.service;

import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;
//import com.sapestore.vo.WishListVO;
import com.sapestore.vo.WishListUserVO;

/**
 * Service interface for Add to Wishlist functionality.
 * 
 * CHANGE LOG 
 * VERSION 	DATE 		AUTHOR 	MESSAGE 
 * 1.0 		30-10-2015 	SAPIENT Initial version
 */

public interface WishlistService {

	/**
	 * Adds books to wishlist
	 * @param wishlist
	 * @param isbn
	 * @param userId
	 * @return
	 * @throws SapeStoreSystemException
	 */
	
	public WishListUserVO addToWishlist(WishListUserVO wishlist, String userId, String isbn) throws SapeStoreException;
	public WishListUserVO removeFromWishlist( WishListUserVO wishlist,String userId, String isbn) throws SapeStoreException;
	public boolean checkBookIntoWishList(String userId, String isbn)throws SapeStoreException;
}
