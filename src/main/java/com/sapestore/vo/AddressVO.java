/**
 * 
 */
package com.sapestore.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;


/**
 * Bean class for adding a book. 
 *
 * CHANGE LOG
 *      VERSION    DATE          AUTHOR       MESSAGE               
 *        1.0    20-06-2014     SAPIENT      Initial version
 */
public class AddressVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4042309704091154938L;

	/**
	 * 
	 */
		
	private String name;
	
	@NotEmpty
	private String userId;
	
	@NotEmpty
	private Integer addressId;
	
	@NotEmpty
	private Integer stateId;;
	
	@NotEmpty
	private Integer countryId;
	
	@NotEmpty
	private String addressLine1;
	
	private String addressLine2;
	
	@NotEmpty
	private Integer cityId;
	
	@NotEmpty
	private String postalCode;
	
	@NotEmpty
	private String mobileNumber;
	
	private String phone;
	
	private String isActive;
	
	private String oldAddressId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
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

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getOldAddressId() {
		return oldAddressId;
	}

	public void setOldAddressId(String oldAddressId) {
		this.oldAddressId = oldAddressId;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
}
