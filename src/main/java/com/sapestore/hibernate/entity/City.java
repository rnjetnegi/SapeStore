package com.sapestore.hibernate.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;


/**
 * This class maps SAPESTORE_CITIES Table in database to hibernate entity City.
 * @author Charul, Nihul, Shivam
 *
 */
@Entity
@Table(name="SAPESTORE_CITIES")
@NamedQueries({
		@NamedQuery(name = "City.findByCityId", query = "from City c where c.cityId = :cityId"),  	/* Named Query to fetch cities by their ID */
		@NamedQuery(name = "City.fetchAllCities", query="from City"),								/* Named Query to fetch all cities */
		@NamedQuery(name = "City.findByCityName", query = "from City c where c.cityName = :name")	/* Named Query to find cities by their name */
 		})
public class City implements Serializable{

	private static final long serialVersionUID = -3404555826437896288L;

	@Id
	@Column(name="CITY_ID")
	private Integer cityId;
		
	@Column(name="STATE_ID")
	private Integer stateId;
	
	@Column(name="CITY_NAME")
	private String cityName;

	/**
	 * @return the cityId
	 */
	public Integer getCityId() {
		return cityId;
	}

	/**
	 * @param cityId the cityId to set
	 */
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	/**
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * @param cityName the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * 
	 * @return stateId
	 */
	public Integer getStateId() {
		return stateId;
	}

	/**
	 * 
	 * @param stateId
	 */
	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	/**
	 * 
	 * @return serialVersionUID
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
