package com.sapestore.vo;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

/**
 * Bean class for keeping logged in or logging in user information. 
 *
 * CHANGE LOG
 *      VERSION    DATE          AUTHOR       MESSAGE               
 *        1.0    20-10-2015     CHARUL      Initial version
 */

public class RegisterVO implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = -2478987739343365211L;

@NotEmpty
private String fullName;

@NotEmpty
private String loginName;

@NotEmpty
@Size(min=8, max = 16)
private String password;

@NotEmpty
@Email
private String email;
@NotEmpty
private String addressLine1;

private String addressLine2;

@NotEmpty
private String city;

@NotEmpty
private String state;


private String country;

@NotEmpty
@Size(min=5,max=10)
private String postalCode;


private String phone;

@NotEmpty
@Size(min=10,max=10)
private String mobileNumber;

/**
 * 
 * @return fullName
 */
public String getFullName() {
	return fullName;
}

/**
 * 
 * @param fullName
 */
public void setFullName(String fullName) {
	this.fullName = fullName;
}

/**
 * 
 * @return loginName
 */
public String getLoginName() {
	return loginName;
}

/**
 * 
 * @param loginName
 */
public void setLoginName(String loginName) {
	this.loginName = loginName;
}

/**
 * 
 * @return password
 */
public String getPassword() {
	return password;
}

/**
 * 
 * @param password
 */
public void setPassword(String password) {
	this.password = password;
}

/**
 * 
 * @return email
 */
public String getEmail() {
	return email;
}

/**
 * 
 * @param email
 */
public void setEmail(String email) {
	this.email = email;
}

/**
 * 
 * @return city
 */
public String getCity() {
	return city;
}

/**
 * 
 * @param city
 */
public void setCity(String city) {
	this.city = city;
}

/**
 * 
 * @return state
 */
public String getState() {
	return state;
}

/**
 * 
 * @param state
 */
public void setState(String state) {
	this.state = state;
}

/**
 * 
 * @return country
 */
public String getCountry() {
	return country;
}

/**
 * 
 * @param country
 */
public void setCountry(String country) {
	this.country = country;
}

/**
 * 
 * @return postalCode
 */
public String getPostalCode() {
	return postalCode;
}

/**
 * 
 * @param postalCode
 */
public void setPostalCode(String postalCode) {
	this.postalCode = postalCode;
}

/**
 *  
 * @return mobileNumber
 */
public String getMobileNumber() {
	return mobileNumber;
}

/**
 * 
 * @param mobileNumber
 */
public void setMobileNumber(String mobileNumber) {
	this.mobileNumber = mobileNumber;
}

/**
 * 
 * @return phone
 */
public String getPhone() {
	return phone;
}

/**
 * 
 * @param phone
 */
public void setPhone(String phone) {
	this.phone = phone;
}

/**
 * 
 * @return addressLine2
 */
public String getAddressLine2() {
	return addressLine2;
}

/**
 * 
 * @param addressLine2
 */
public void setAddressLine2(String addressLine2) {
	this.addressLine2 = addressLine2;
}

/**
 * 
 * @return addressLine1
 */
public String getAddressLine1() {
	return addressLine1;
}

/**
 * 
 * @param addressLine1
 */
public void setAddressLine1(String addressLine1) {
	this.addressLine1 = addressLine1;
}

}
