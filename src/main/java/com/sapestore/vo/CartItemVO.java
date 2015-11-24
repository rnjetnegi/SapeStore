package com.sapestore.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import com.sapestore.hibernate.entity.Book;
import com.sapestore.hibernate.entity.CartItem;


/**
 * Bean class for adding a book. 
 *
 * CHANGE LOG
 *      VERSION    DATE          AUTHOR       MESSAGE               
 *        1.0    20-06-2014     SAPIENT      Initial version
 */
public class CartItemVO implements Serializable {
	
	private static final long serialVersionUID = -3972545417811168092L;
	
	private String isbn;

	private String userId;
	
	private String type;
	
	private int quantity;
	
	private String title;
	
	private String author;
	
	private BigDecimal bookPrice;
	
	private BigDecimal rentPrice;
	
	private String image;
	
	private BigDecimal total;
	
	/**
	 * 
	 */
	public CartItemVO(){
		
	}
	
	public CartItemVO(BookVO book, String userId, String purchaseType){
		this.author = book.getBookAuthor().replace("\t", "");
		this.bookPrice = new BigDecimal(book.getBookPrice());
		this.image = book.getThumbPath();
		this.isbn = book.getIsbn();
		this.rentPrice = book.getRentPrice();
		this.title = book.getBookTitle().replace("\t", "");
		this.type = purchaseType;
		this.userId = userId;
		this.quantity = 1;
	}
	
	public CartItemVO(String userId, Book book, String type){
		this.userId = userId;
		this.type = type;
		this.author = book.getBookAuthor().replace("\t", "");
		this.bookPrice = book.getBookPrice();
		this.image = book.getBookThumbImage();
		this.isbn = book.getIsbn();
		this.quantity = 1;
		this.rentPrice = book.getRentPrice();
		this.title = book.getBookTitle().replace("\t", "");
	}
	
	public CartItemVO(CartItem item){
		this.author = item.getAuthor();
		this.bookPrice = item.getBookPrice();
		this.image = item.getImage();
		this.isbn = item.getIsbn();
		this.quantity = item.getQuantity();
		this.rentPrice = item.getRentPrice();
		this.title = item.getTitle();
		this.type = item.getType();
		this.userId = item.getUserId();
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "isbn = " + this.isbn + "userId = " + this.userId + "type = " + this.type + "quantity = " + this.quantity + 
				"title = " + this.title + "author = " + this.author + "bookPrice = " + this.bookPrice.toString() + 
				"rentPrice = " + this.rentPrice.toString() + "image = " + this.image;
	}
	
	public boolean equals(String bookType, String currUserId, String bookIsbn){
		return (this.type.equals(bookType) 
				&& this.userId.equals(currUserId) 
				&& this.isbn.equals(bookIsbn));
	}
	
	public String getIsbn() {
		return isbn;
	}
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public BigDecimal getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(BigDecimal bookPrice) {
		this.bookPrice = bookPrice;
	}

	public BigDecimal getRentPrice() {
		return rentPrice;
	}

	public void setRentPrice(BigDecimal rentPrice) {
		this.rentPrice = rentPrice;
	}

	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
}
