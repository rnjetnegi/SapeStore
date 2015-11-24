package com.sapestore.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.sapestore.hibernate.entity.Book;
import com.sapestore.hibernate.entity.CartItem;
import com.sapestore.hibernate.entity.WishListNew;


public class WishListVO implements Serializable{

	
public WishListVO(){
		
	}
	
	public WishListVO(WishListNew item){
		
		this.isbn = item.getIsbn();
		this.wishId = item.getWishId();
		this.userId = item.getUserId();
		this.bookAuthor = item.getBookAuthor();
		this.bookPrice = item.getBookPrice();
		this.bookThumbImage = item.getBookThumbImage();
		 this.bookTitle = item.getBookTitle();
	}
	
	
	public WishListVO(String userId, Book book){
		
		this.userId = userId;
		this.bookAuthor = book.getBookAuthor();
		this.bookPrice = book.getBookPrice();
		this.bookThumbImage = book.getBookThumbImage();
		this.isbn = book.getIsbn();
        this.bookTitle = book.getBookTitle();
		
	}
	
	
	private Integer wishId;
	private String userId;
	private String isbn;
	private String bookTitle;
	private String bookAuthor;
	private BigDecimal bookPrice;
	private String bookThumbImage;

	public Integer getWishId() {
		return wishId;
	}

	public void setWishId(Integer wishId) {
		this.wishId = wishId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public BigDecimal getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(BigDecimal bookPrice) {
		this.bookPrice = bookPrice;
	}

	public String getBookThumbImage() {
		return bookThumbImage;
	}

	public void setBookThumbImage(String bookThumbImage) {
		this.bookThumbImage = bookThumbImage;
	}

	
	
	
}



