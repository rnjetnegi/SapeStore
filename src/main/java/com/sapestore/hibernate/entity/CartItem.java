package com.sapestore.hibernate.entity;
import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import com.sapestore.vo.CartItemVO;


@Entity
@Table(name="CART_ITEM")
@NamedQueries(value = {
		@NamedQuery(name = "CartItem.findAll", query = "from CartItem c where c.userId =:userId"),
		@NamedQuery(name = "CartItem.UniqueBookformCartItemTable", query = "from CartItem c where c.userId =:userId and c.isbn=:isbn and c.type=:type")
		})

public class CartItem implements Serializable{

	/**
	 * 
	 */
	//
	private static final long serialVersionUID = 8956504917482103414L;
	
	@Id
	@Column(name="ISBN")
	private String isbn;
		
	@Id
	@Column(name="USER_ID")
	private String userId;	
	
	@Column(name="QUANTITY")
	private Integer quantity;	
	
	@Id
	@Column(name="TYPE")
	private String type;

	@Column(name="BOOK_THUMB_IMAGE")
	private String image; 

	@Column(name="BOOK_AUTHOR")
	private String author;

	@Column(name="BOOK_TITLE")
	private String title;
	
	@Column(name="RENT_PRICE")
	private BigDecimal rentPrice;
	
	@Column(name="BOOK_PRICE")
	private BigDecimal bookPrice ;
	
	public CartItem(){
		
	}

	public CartItem(CartItemVO item){
		this.setAuthor(item.getAuthor());
		this.setBookPrice(item.getBookPrice());
		this.setImage(item.getImage());
		this.setIsbn(item.getIsbn());
		this.setQuantity(item.getQuantity());
		this.setRentPrice(item.getRentPrice());
		this.setTitle(item.getTitle());
		this.setType(item.getType());
		this.setUserId(item.getUserId());
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BigDecimal getRentPrice() {
		return rentPrice;
	}

	public void setRentPrice(BigDecimal rentPrice) {
		this.rentPrice = rentPrice;
	}

	public BigDecimal getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(BigDecimal bookPrice) {
		this.bookPrice = bookPrice;
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

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}		
}
