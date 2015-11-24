package com.sapestore.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;




public class WishListUserVO implements Serializable{
	
private String userId;

private String wishId;

private List<WishListVO> wishListItems = new ArrayList<WishListVO>();

	
	public String getUserId() {
	return userId;
}

public void setUserId(String userId) {
	this.userId = userId;
}

public String getWishId() {
	return wishId;
}

public void setWishId(String wishId) {
	this.wishId = wishId;
}

public List<WishListVO> getWishListItems() {
	return wishListItems;
}

public void setWishListItems(List<WishListVO> wishListItems) {
	this.wishListItems = wishListItems;
}

	
	

	
	

}
