package com.sapestore.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Bean class for Rented orders.
 *
 * CHANGE LOG VERSION DATE AUTHOR MESSAGE 1.0 20-06-2014 SAPIENT Initial version
 */
public class OrderVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private int orderNumber; // ORDER_ID from ORDER_ITEM_INFO
	private boolean returnReceived; // RETURN_STATUS from ORDER_ITEM_INFO
	private String itemName; // BOOK_TITLE from SAPESTORE_BOOKS
	private BigDecimal rentAmount; // RENT_PRICE from SAPESTORE_BOOKS
	private boolean orderStatus; // ORDER_STATUS from ORDER_INFO
	private Date expectedReturnDate; // EXPECTED_RETURN_DATE from
										// ORDER_ITEM_INFO
	private Date actualReturnDate; // ACTUAL_RETURN_DATE from ORDER_ITEM_INFO
	private BigDecimal lateFee; // LATE_FEE from SAPESTORE_BOOKS
	private String author; // BOOK_AUTHOR SAPESTORE_BOOKS
	private Date purchaseDate; // PURCHASE_DATE from SAPESTORE_BOOKS
	private Integer purchasePrice; // PURCHASE_PRICE from SAPESTORE_BOOKS
	private String image; // BOOK_IMAGE from SAPESTORE_BOOKS
	private String status;
	private String type;
	private String isbn;
	private String customerId;
	private int orderItemNumber; // ORDER_ITEM_ID from ORDER_ITEM_INFO
	private String paymentStatus;
	private String emailId;
	private String addressLine1;
	private String addressLine2;
	private String cityName;
	private String stateName;
	private boolean dispatched = false;
	private boolean paid = false;
	private String returnStatus;
		

	public boolean isDispatched() {
		return dispatched;
	}

	public void setDispatched(boolean dispatched) {
		this.dispatched = dispatched;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public String getReturnStatus() {
		return returnStatus;
	}

	public void setReturnStatus(String returnStatus) {
		this.returnStatus = returnStatus;
	}
	
	
	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	
	
	
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public int getOrderItemNumber() {
		return orderItemNumber;
	}

	public void setOrderItemNumber(int orderItemNumber) {
		this.orderItemNumber = orderItemNumber;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(boolean orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Integer purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return orderId
	 */
	public int getOrderNumber() {
		return orderNumber;
	}

	/**
	 * @param orderNumber
	 *            sets orderId
	 */
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	/**
	 * @return book title/name
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * @param itemName
	 *            sets book title/name
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * @return the rentAmount
	 */
	public BigDecimal getRentAmount() {
		return rentAmount;
	}

	/**
	 * @param rentAmount
	 *            the rentAmount to set
	 */
	public void setRentAmount(BigDecimal rentAmount) {
		this.rentAmount = rentAmount;
	}

	/**
	 * @return book return status(returned/pending)
	 */
	public boolean isReturnReceived() {
		return returnReceived;
	}

	/**
	 * @param returnReceived
	 *            sets book return status(returned/pending)
	 */
	public void setReturnReceived(boolean returnReceived) {
		this.returnReceived = returnReceived;
	}

	/**
	 * @return order status(dispatched/pending)
	 */
	public boolean isOrderStatus() {
		return orderStatus;
	}

	/**
	 * @return rented book expected return date
	 */
	public Date getExpectedReturnDate() {
		return expectedReturnDate;
	}

	/**
	 * @param expectedReturnDate
	 *            rented book expected return date
	 */
	public void setExpectedReturnDate(Date expectedReturnDate) {
		this.expectedReturnDate = expectedReturnDate;
	}

	/**
	 * @return rented book actual return date
	 */
	public Date getActualReturnDate() {
		return actualReturnDate;
	}

	/**
	 * @param actualReturnDate
	 *            sets rented book actual return date
	 */
	public void setActualReturnDate(Date actualReturnDate) {
		this.actualReturnDate = actualReturnDate;
	}

	/**
	 * @return the lateFee
	 */
	public BigDecimal getLateFee() {
		return lateFee;
	}

	/**
	 * @param lateFee
	 *            the lateFee to set
	 */
	public void setLateFee(BigDecimal lateFee) {
		this.lateFee = lateFee;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

}