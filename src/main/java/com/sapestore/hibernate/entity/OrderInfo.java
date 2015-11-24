package com.sapestore.hibernate.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.Criteria;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name="ORDER_INFO")
@NamedQueries(value = {@NamedQuery(name = "OrderInfo.findAll", query = "from OrderInfo o"),
		@NamedQuery(name = "OrderInfo.findUserOrders", query = "from OrderInfo o where o.userId=:userId"),
		@NamedQuery(name = "OrderInfo.findUndispatchedOrders", query ="from OrderInfo o where o.orderStatus = 'NOT DISPATCHED' "),
		@NamedQuery(name = "OrderInfo.findParticularOrderInfo", query ="from OrderInfo o where o.orderId =:orderId")
	    })
public class OrderInfo implements Serializable{
			
	/**
	 * 
	 */
	//  
	private static final long serialVersionUID = -2687916517368691592L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_SEQUENCE")
	@SequenceGenerator(name = "ORDER_SEQUENCE", sequenceName = "ORDER_SEQUENCE", allocationSize = 1)
	@Column(name="ORDER_ID")
	private Integer orderId;	
		
	@Column(name="USER_ID")
	private String userId;

	@Column(name="ORDER_DATE")
	private Date orderDate;	
	
	@Column(name="TOTAL_PAYMENT")
	private Integer totalPayment;
	
	@Column(name="PAYMENT_MODE")
	private String paymentMode;	
	
	@Column(name="CREATED_DATE")
	private Date createdDate;	
	
	@Column(name="UPDATED_DATE")
	private Date updatedDate;	
		
	@Column(name="IS_ACTIVE")
	private String isActive;
	
	@Column(name="ORDER_STATUS")
	private String orderStatus;
	
	@Column(name="ADDRESS_LINE1")
	private String addressLine1;
	
	@Column(name="ADDRESS_LINE2")
	private String addressLine2;
	
	@Column(name="CITY_NAME")
	private String cityName;
	
	@Column(name="STATE_NAME")
	private String stateName;
	
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name = "ORDER_ID")	
	private List<OrderItemInfo> orderItemInfoList;		

	/**
	 * @return the orderItemInfoList
	 */
	public List<OrderItemInfo> getOrderItemInfoList() {
		return orderItemInfoList;
	}

	/**
	 * @param orderItemInfoList the orderItemInfoList to set
	 */
	public void setOrderItemInfoList(List<OrderItemInfo> orderItemInfoList) {
		this.orderItemInfoList = orderItemInfoList;
	}
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="USER_ID", referencedColumnName="USER_ID", insertable=false, updatable=false)
	private User user;	
		
	@Transient
	private String name;	
	
	@Transient
	private String emailAddress;	
	
	/**
	 * @return the name
	 */
	public String getName() {
		return user.getName();
	}

	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return user.getEmailAddress();
	}

	/**
	 * @return the orderId
	 */
	public Integer getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the orderDate
	 */
	public Date getOrderDate() {
		return orderDate;
	}

	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * @return the totalPayment
	 */
	public Integer getTotalPayment() {
		return totalPayment;
	}

	/**
	 * @param totalPayment the totalPayment to set
	 */
	public void setTotalPayment(Integer totalPayment) {
		this.totalPayment = totalPayment;
	}

	/**
	 * @return the paymentMode
	 */
	public String getPaymentMode() {
		return paymentMode;
	}

	/**
	 * @param paymentMode the paymentMode to set
	 */
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
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
	 * @return the orderStatus
	 */
	public String getOrderStatus() {
		return orderStatus;
	}

	/**
	 * @param orderStatus the orderStatus to set
	 */
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
}