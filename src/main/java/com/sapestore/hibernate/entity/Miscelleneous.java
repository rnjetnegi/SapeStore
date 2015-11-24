package com.sapestore.hibernate.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name="MISCELLANEOUS")
@NamedQueries(value = {
		@NamedQuery(name = "Miscelleneous.findAll", query = "from Miscelleneous m")
		})
public class Miscelleneous implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2228400959309372457L;

	@Id @GeneratedValue
	   @Column(name = "id")
	   private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="CONTACTUS")
	private String contactUs;	
    
	
	
	@Column(name="POLICY")
	private String policy;

	/**
	 * @return the contactUs
	 */
	public String getContactUs() {
	
		return contactUs;
	}

	/**
	 * @param contactUs the contactUs to set
	 */
	public void setContactUs(String contactUs) {
		this.contactUs = contactUs;
		System.out.println(contactUs);
	}

	/**
	 * @return the policy
	 */
	public String getPolicy() {
		return policy;
	}

	/**
	 * @param policy the policy to set
	 */
	public void setPolicy(String policy) {
		this.policy = policy;
	}		

}
