package com.sapestore.dao;


/**
 * @author vgar11
 *
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.impl.Log4JLogger;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;
import com.sapestore.hibernate.entity.Address;
import com.sapestore.hibernate.entity.Address2;
import com.sapestore.hibernate.entity.City;
import com.sapestore.hibernate.entity.OrderItemInfo;
import com.sapestore.hibernate.entity.State;
import com.sapestore.hibernate.entity.User;
import com.sapestore.vo.AddressVO;
import com.sapestore.vo.DispatchSlip;
import com.sun.mail.iap.ConnectionException;



/**
 * DAO class to fetch and add shipping address details
 * 
 * 
 * VERSION 	DATE 		
 * 1.0 		20-10-2015 	
 */

@Repository
@Transactional
public class AddressDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * Logger for log messages.
	 */
	static Logger log = Logger.getLogger(AddressDao.class.getName());
	
	
	private final static SapeStoreLogger LOGGER = SapeStoreLogger
			.getLogger(OrderDao.class.getName());

	/*
	 * Method for Retrieving the Existing Addresses of the User From the Database
	 */
	
	public List<Address2> retrieveFromId(String userId) {
		
		List<Address> listUser = (List<Address>) hibernateTemplate.findByNamedQueryAndNamedParam("Address.findByUserId", "userId", userId);
		
		List<Address2> listUser2 = new ArrayList<Address2>();
		
		
		    for(int i=0;i<listUser.size();i++){
			Address2 add = new Address2(); 
			add.setAddressId(listUser.get(i).getAddressId());
			add.setAddressLine1(listUser.get(i).getAddressLine1());
			add.setAddressLine2(listUser.get(i).getAddressLine2());
			add.setCityId(listUser.get(i).getCityId());
			add.setCountryId(listUser.get(i).getCountryId());
			add.setCreatedDate(listUser.get(i).getCreatedDate());
			add.setIsActive(listUser.get(i).getIsActive());
			add.setName(listUser.get(i).getName());
			add.setPhone(listUser.get(i).getPhone());
			add.setPostalCode(listUser.get(i).getPostalCode());
			add.setUpdatedDate(listUser.get(i).getUpdatedDate());
			add.setUserId(listUser.get(i).getUserId());
			add.setStateId(listUser.get(i).getStateId());
			listUser2.add(add);
		    }
			
		    
		    /*
			 *Method for fetching CityName using cityId
			 */
		    for(int i=0;i<listUser.size();i++){
				List<?> cityList = (List<?>) hibernateTemplate.findByNamedQueryAndNamedParam("City.findByCityId", "cityId",listUser.get(i).getCityId() );
				if(cityList.size()>0){
					City city = (City) cityList.get(0);
					String cityName = city.getCityName();
					(listUser2.get(i)).setCityName(cityName);
				}
			}
			/*
			 * Method for fetching StateName using stateId
			 */
			for(int i=0;i<listUser.size();i++){
				List<?> stateList = (List<?>) hibernateTemplate.findByNamedQueryAndNamedParam("State.findByStateId", "stateId",listUser.get(i).getStateId() );
				if(stateList.size()>0){
					State state = (State) stateList.get(0);
					String stateName = state.getStateName();
					(listUser2.get(i)).setStateName(stateName);
				}
			}
		
		
		
		return listUser2;

	}
	
	/*
	 * Adding a new Shipping Address 
	 */
	public void addAddress(AddressVO addAddressBean,String userId) throws SapeStoreException {
		
		//addAddressBean.setAddressId(123456);
		LOGGER.debug(" AddressDao.addNewAddress method: START");
		try{
			Address address = new Address();
			address.setAddressId(addAddressBean.getAddressId());
			address.setUserId(userId);
			address.setStateId(addAddressBean.getStateId());
			address.setCityId(addAddressBean.getCityId());
			address.setCountryId(addAddressBean.getCountryId());
			address.setAddressLine1(addAddressBean.getAddressLine1());
			address.setAddressLine2(addAddressBean.getAddressLine2());
			address.setPostalCode(addAddressBean.getPostalCode());
			address.setCreatedDate(new java.util.Date());
			address.setUpdatedDate(new java.util.Date());
			address.setPhone(addAddressBean.getPhone());
			address.setIsActive(addAddressBean.getIsActive());
			address.setName(addAddressBean.getName());
			
			
			
			hibernateTemplate.save(address);
			LOGGER.debug(" Address is added ");
		}		
		catch (SapeStoreSystemException se) {
				LOGGER.fatal("A DB exception occured while inserting the address's information", se);
			}
		LOGGER.debug(" AddressDao.addNewAddresss method: END ");
	}
	
	public List<City> getCityList(){
		List<City> cities = new ArrayList<City>();
		
		cities = (List<City>) hibernateTemplate.findByNamedQuery("City.fetchAllCities");
		
		return cities;

	}
	
	public List<State> getStateList(){
		List<State> states= new ArrayList<State>();
	
		states = (List<State>) hibernateTemplate.findByNamedQuery("State.findAllStates");
		
		return states;
	}
	public String getCityName(int id){
		String cityName=null;
		City city = null;
		List result=hibernateTemplate.findByNamedQueryAndNamedParam("City.findByCityId","cityId" ,id);
		if(result!=null && result.size()>0){
			city=(City) result.get(0);
		}
		if(city!=null){
			cityName=city.getCityName();
		}
		return cityName;
	}
	public String getStateName(int id){
		String stateName=null;
		State state = null;
		List result=hibernateTemplate.findByNamedQueryAndNamedParam("State.findByStateId","stateId" ,id);
		if(result!=null && result.size()>0){
			state=(State) result.get(0);
		}
		if(state!=null){
			stateName=state.getStateName();
		}
		return stateName;
	}
	public Address getAddressById(Integer addressId){
		List addressList = (List<Address>)hibernateTemplate.findByNamedQueryAndNamedParam("Address.findByAddressId", "addressId", addressId);
		Address address = (Address) addressList.get(0);
		return address;
	}

	public Integer getCityIdByName(String cityName) {
		// TODO Auto-generated method stub
		List cityId = (List<City>)hibernateTemplate.findByNamedQueryAndNamedParam("City.findByCityName", "name", cityName);
		if(cityId != null){
			return ((City)cityId.get(0)).getCityId();
		}
		return 0;
	}
	
	

}
