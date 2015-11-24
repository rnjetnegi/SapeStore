package com.sapestore.hibernate.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;


@Entity
@Table(name="SAPESTORE_WISHLIST_NEW")
@NamedQueries(value = {
		@NamedQuery(name = "WishListBook.view", query = "from WishListNew w where w.userId =:userId"),
		@NamedQuery(name = "WishListBook.remove", query = "from WishListNew w where w.userId =:userId and w.isbn=:isbn"),
		@NamedQuery(name = "WishListBook.moveToCart", query = "from WishListNew w where w.userId =:userId and w.isbn=:isbn")
 		})


public class WishListNew implements Serializable{
	
	@Id
	@Column(name="ISBN")
	private String isbn;
	
	@Column(name="BOOK_TITLE")
	private String bookTitle;	
	
	@Column(name="BOOK_AUTHOR")
	private String bookAuthor;	
	
	@Column(name="BOOK_THUMB_IMAGE")
	private String bookThumbImage;	
	
	
	@Column(name="BOOK_PRICE")
	private BigDecimal bookPrice;
	
	@Id
	@Column(name="USER_ID")
	private String userId;
	
	@Column(name="WISH_ID")
	private Integer wishId;
	
	


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


	public String getBookThumbImage() {
		return bookThumbImage;
	}


	public void setBookThumbImage(String bookThumbImage) {
		this.bookThumbImage = bookThumbImage;
	}


	public BigDecimal getBookPrice() {
		return bookPrice;
	}


	public void setBookPrice(BigDecimal bookPrice) {
		this.bookPrice = bookPrice;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public Integer getWishId() {
		return wishId;
	}


	public void setWishId(Integer wishId) {
		this.wishId = wishId;
	}
	
	
	

}
