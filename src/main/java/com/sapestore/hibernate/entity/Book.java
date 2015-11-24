package com.sapestore.hibernate.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name="SAPESTORE_BOOKS")
@NamedQueries(value = {
		@NamedQuery(name = "Book.findAll", query = "from Book b where b.quantity > 0 and (b.isFromPartnerStore is null OR b.isFromPartnerStore = 'N')"),
		@NamedQuery(name = "Book.findByCategoryId", query = "from Book b where b.categoryId = :categoryId and b.quantity > 0 and (b.isFromPartnerStore is null OR b.isFromPartnerStore = 'N')"),
		@NamedQuery(name = "Book.findAllInventory", query = "from Book b order by b.categoryId asc"),
		
		@NamedQuery(name="Book.findByIsbn",query="from Book b where b.isbn=:isbn")
		})
public class Book implements Serializable{

	/**
	 * 
	 */
	//
	private static final long serialVersionUID = 8956504917482103414L;
	
	@Id
	@Column(name="ISBN")
	private String isbn;
		
	@Column(name="PUBLISHER_NAME")
	private String publisherName;	
	
	@Column(name="CATEGORY_ID")
	private Integer categoryId;	
	
	@OneToOne
	@JoinColumn(name = "CATEGORY_ID", referencedColumnName = "CATEGORY_ID", insertable = false, updatable = false)
	private BookCategory bookCategory;			
	
	@Transient
	private String categoryName;		

	@Column(name="BOOK_TITLE")
	private String bookTitle;	
	
	@Column(name="QUANTITY")
	private Integer quantity;
	
	@Column(name="BOOK_AUTHOR")
	private String bookAuthor;	
	
	@Column(name="BOOK_THUMB_IMAGE")
	private String bookThumbImage;	
		
	@Column(name="BOOK_FULL_IMAGE")
	private String bookFullImage;
	
	@Column(name="BOOK_SHORT_DESCRIPTION")
	private String bookShortDescription;	
	
	@Column(name="BOOK_DETAIL_DESCRIPTION")
	private String bookDetailDescription;	
	
	@Column(name="CREATED_DATE")
	private Date createdDate;
	
	@Column(name="UPDATED_DATE")
	private Date updatedDate;
	
	@Column(name="IS_ACTIVE")
	private String isActive;
	
	@Column(name="RENT_AVAILABILITY")
	private String rentAvailability;	
		
	@Column(name="RENT_PRICE")
	private BigDecimal rentPrice;

	@Column(name="LATE_FEE")
	private BigDecimal lateFee;
	
	@Column(name="IS_FROM_PARTNER_STORE")
	private String isFromPartnerStore;
	
	@Column(name="AVERAGE_RATING")
	private BigDecimal averageRating;
	
	@Column(name="BOOK_PRICE")
	private BigDecimal bookPrice;

	/**
	 * @return the isbn
	 */
	public String getIsbn() {
		return isbn;
	}

	/**
	 * @param isbn the isbn to set
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	/**
	 * @return the publisherName
	 */
	public String getPublisherName() {
		return publisherName;
	}

	/**
	 * @param publisherName the publisherName to set
	 */
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	/**
	 * @return the categoryId
	 */
	public Integer getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	
	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return bookCategory.getCategoryName();
	}

	/**
	 * @return the bookTitle
	 */
	public String getBookTitle() {
		return bookTitle;
	}

	/**
	 * @param bookTitle the bookTitle to set
	 */
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	/**
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the bookAuthor
	 */
	public String getBookAuthor() {
		return bookAuthor;
	}

	/**
	 * @param bookAuthor the bookAuthor to set
	 */
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	/**
	 * @return the bookThumbImage
	 */
	public String getBookThumbImage() {
		return bookThumbImage;
	}

	/**
	 * @param bookThumbImage the bookThumbImage to set
	 */
	public void setBookThumbImage(String bookThumbImage) {
		this.bookThumbImage = bookThumbImage;
	}

	/**
	 * @return the bookFullImage
	 */
	public String getBookFullImage() {
		return bookFullImage;
	}

	/**
	 * @param bookFullImage the bookFullImage to set
	 */
	public void setBookFullImage(String bookFullImage) {
		this.bookFullImage = bookFullImage;
	}

	/**
	 * @return the bookShortDescription
	 */
	public String getBookShortDescription() {
		return bookShortDescription;
	}

	/**
	 * @param bookShortDescription the bookShortDescription to set
	 */
	public void setBookShortDescription(String bookShortDescription) {
		this.bookShortDescription = bookShortDescription;
	}

	/**
	 * @return the bookDetailDescription
	 */
	public String getBookDetailDescription() {
		return bookDetailDescription;
	}

	/**
	 * @param bookDetailDescription the bookDetailDescription to set
	 */
	public void setBookDetailDescription(String bookDetailDescription) {
		this.bookDetailDescription = bookDetailDescription;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the updatedDate
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * @return the isActive
	 */
	public String getIsActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	/**
	 * @return the rentAvailability
	 */
	public String getRentAvailability() {
		return rentAvailability;
	}

	/**
	 * @param rentAvailability the rentAvailability to set
	 */
	public void setRentAvailability(String rentAvailability) {
		this.rentAvailability = rentAvailability;
	}

	/**
	 * @return the rentPrice
	 */
	public BigDecimal getRentPrice() {
		return rentPrice;
	}

	/**
	 * @param rentPrice the rentPrice to set
	 */
	public void setRentPrice(BigDecimal rentPrice) {
		this.rentPrice = rentPrice;
	}

	/**
	 * @return the lateFee
	 */
	public BigDecimal getLateFee() {
		return lateFee;
	}

	/**
	 * @param lateFee the lateFee to set
	 */
	public void setLateFee(BigDecimal lateFee) {
		this.lateFee = lateFee;
	}

	/**
	 * @return the isFromPartnerStore
	 */
	public String getIsFromPartnerStore() {
		return isFromPartnerStore;
	}

	/**
	 * @param isFromPartnerStore the isFromPartnerStore to set
	 */
	public void setIsFromPartnerStore(String isFromPartnerStore) {
		this.isFromPartnerStore = isFromPartnerStore;
	}

	/**
	 * @return the averageRating
	 */
	public BigDecimal getAverageRating() {
		return averageRating;
	}

	/**
	 * @param averageRating the averageRating to set
	 */
	public void setAverageRating(BigDecimal averageRating) {
		this.averageRating = averageRating;
	}

	/**
	 * @return the bookPrice
	 */
	public BigDecimal getBookPrice() {
		return bookPrice;
	}

	/**
	 * @param bookPrice the bookPrice to set
	 */
	public void setBookPrice(BigDecimal bookPrice) {
		this.bookPrice = bookPrice;
	}
	
		
}
