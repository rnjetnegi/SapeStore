package com.sapestore.vo;

import java.io.Serializable;

/**
 * Bean class for Book Dispatch Slip. 
 *
 * CHANGE LOG
 *      VERSION    DATE          AUTHOR       MESSAGE               
 *        1.0    20-06-2014     SAPIENT      Initial version
 */
public class DispatchSlip implements Serializable{

	private static final long serialVersionUID = 1L;
	private int orderNumber;
	private int orderItemNumber;
	private String customerName;
	private String addressLine1;
	private String addressLine2;
	private String cityName;
	private String stateName;
	
	
	/**
	 * @return orderID
	 */
	public int getOrderNumber() {
		return orderNumber;
	}
	/**
	 * @param orderNumber sets orderID
	 */
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}
	/**
	 * @return customer name
	 */
	public String getCustomerName() {
		return customerName;
	}
	/**
	 * @param customerName sets customer name
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	/**
	 * @return shipping address for the order
	 */

	public int getOrderItemNumber() {
		return orderItemNumber;
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
	public void setOrderItemNumber(int orderItemNumber) {
		this.orderItemNumber = orderItemNumber;
	}
	
}