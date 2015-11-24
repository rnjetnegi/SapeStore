package com.sapestore.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CartVO implements Serializable{
	
	private String userId;
	
	private List<CartItemVO> cartItems = new ArrayList<CartItemVO>();
	
	private Integer totalQuantity;
	
	

	public Integer getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(Integer totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public CartVO(String uid, List<CartItemVO> list){
		this.userId = uid;
		this.cartItems = list;
		this.totalQuantity = list.size();
	}
	
	public CartVO(){
		
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<CartItemVO> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItemVO> cartItems) {
		this.cartItems = cartItems;
	}
}
