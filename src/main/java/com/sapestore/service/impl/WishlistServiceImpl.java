package com.sapestore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.dao.CartDao;
import com.sapestore.dao.WishListDao;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;
import com.sapestore.service.WishlistService;
import com.sapestore.vo.CartVO;
import com.sapestore.vo.WishListUserVO;
import com.sapestore.vo.WishListVO;


/**
 * Service class for Add to Cart functionality.
 * 
 * CHANGE LOG 
 * VERSION 	DATE 		AUTHOR 	MESSAGE 
 * 1.0 		30-10-2015 	SAPIENT Initial version
 */

@Service("wishlistService")
public class WishlistServiceImpl implements WishlistService {

	private final static SapeStoreLogger LOGGER = SapeStoreLogger.getLogger(ShoppingCartServiceImpl.class.getName());

	@Autowired
	private WishListDao wishlistDao;

	/**
	 * Adds books to wishlist
	 */

	
	/**
	 * Get wishlist information by passing the userID
	 * @param userId
	 * @return wishlistVO
	 * @throws SapeStoreSystemException
	 */
	public WishListUserVO getWishlistItems(String  userId) throws SapeStoreException {		
		LOGGER.debug("getWishlistItems method: START");
		WishListUserVO viewlistbean = null;
		viewlistbean = wishlistDao.viewWishlist(userId);
		LOGGER.debug("getWishlistItems method: END");
		return viewlistbean;
	}
	
	/**
	 * Add Book To Wishlist by passing userId, isbn
	 * @param wishlist
	 * @param userId
	 * @param isbn
	 * @return wishlist
	 * @throws SapeStoreSystemException
	 */
	public WishListUserVO addToWishlist(WishListUserVO wishlist, String userId, String isbn) throws SapeStoreException {
		LOGGER.debug("addToWishlist method: START");
		WishListUserVO list = wishlistDao.addToWishlist(wishlist, userId, isbn);
		LOGGER.debug("addToWishlist method: END");
		return list;	
		}
	
	/**
	 * Remove Book From Wishlist by passing userId, isbn
	 * @param wishlist
	 * @param userId
	 * @param isbn
	 * @return wishlist
	 * @throws SapeStoreSystemException
	 */
	public WishListUserVO removeFromWishlist(WishListUserVO wishlist, String userId, String isbn) throws SapeStoreException {
		LOGGER.debug("removeFromWishlist method: START");
		wishlist = wishlistDao.removeFromWishlist(wishlist, userId, isbn);
		LOGGER.debug("removeFromWishlist method: END");
		return wishlist;	
		}

	public WishListUserVO moveFromWishlist (WishListUserVO wishlist, String userId, String isbn) throws SapeStoreException {
		LOGGER.debug("moveFromWishlist method: START");
		wishlist = wishlistDao.moveFromWishlist(wishlist, userId, isbn);
		LOGGER.debug("moveFromWishlist method: END");
		return wishlist;	
		}

	public boolean checkBookIntoWishList(String userId, String isbn) {
		// TODO Auto-generated method stub
		return wishlistDao.checkBookIntoWishList(userId,isbn);
	}

}
