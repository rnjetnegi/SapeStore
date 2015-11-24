package com.sapestore.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.hibernate.entity.Address;
import com.sapestore.hibernate.entity.City;
import com.sapestore.hibernate.entity.Login;
import com.sapestore.hibernate.entity.State;
import com.sapestore.hibernate.entity.User;
import com.sapestore.vo.RegisterVO;
import com.sun.mail.iap.ConnectionException;
/**
 * DAO class to store new user details
 * 
 * CHANGE LOG 
 * VERSION 	DATE 		AUTHOR 	MESSAGE 
 * 1.0 		20-10-2015 	NIHUL Initial version
 */
@Repository
public class RegisterDao {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	/**
	 * Logger for log messages.
	 */
	private final static SapeStoreLogger LOGGER = SapeStoreLogger.getLogger(RegisterDao.class.getName());
	private SessionFactory factory;
	/**
	 * Method to fetch user login details based on the parameters provided.
	 * 
	
	 * @param register(containing details entered by new user)
	 * @return
	 * @throws ConnectionException
	 * @throws TransactionExecutionException
	 */
	
	public Boolean addUserDetails(RegisterVO register) throws SapeStoreException{
		LOGGER.debug("");
	    User user=new User();
	    Address address=new Address();
	    Integer cityId=null;
	    Integer stateId=null;
	    Integer countryId=null;
	    
	    String[] namedParams = {"email"};
		Object[] paramValues = {register.getEmail()};	
	   
		List listEmail = hibernateTemplate.findByNamedQueryAndNamedParam("User.findByEmail", namedParams, paramValues);
		if(listEmail.isEmpty()){
	    
	    String[] namedParams1 = {"name"};
		Object[] paramValues1 = {register.getCity()};	
	   
		List listCity = hibernateTemplate.findByNamedQueryAndNamedParam("City.findByCityName", namedParams1, paramValues1);
		if(!listCity.isEmpty()){
			 City city = (City) listCity.get(0);
			 cityId = city.getCityId();
		     stateId = city.getStateId();
			
		}
	    
		String[] namedParams2 = {"stateId"};
		Object[] paramValues2 = {stateId};	
	   
		List listState = hibernateTemplate.findByNamedQueryAndNamedParam("State.findByStateId", namedParams2, paramValues2);
		if(!listState.isEmpty()){
			State state = (State) listState.get(0);  
			countryId = state.getCountryId();
		}
	   
		Date created = new Date(Calendar.getInstance().getTime().getTime());
		Date updated = new Date(Calendar.getInstance().getTime().getTime());
	    
        address.setCityId(cityId);
        address.setStateId(stateId);
        address.setCountryId(countryId);
        address.setAddressLine1(register.getAddressLine1());
        address.setAddressLine2(register.getAddressLine2());
        address.setUserId(register.getLoginName());
        address.setPostalCode(register.getPostalCode());
        address.setCreatedDate(created);
        address.setUpdatedDate(updated);
        address.setIsActive("y");
        Integer addressId= (Integer) hibernateTemplate.save(address);
        
        user.setUserId(register.getLoginName());
        user.setPassword(register.getPassword());
        user.setAddressId(addressId);
	    user.setName(register.getFullName());
	    user.setEmailAddress(register.getEmail());
	    user.setPhone(register.getPhone());
	    user.setMobileNumber(register.getMobileNumber());
	    user.setCreatedDate(created);
	    user.setUpdatedDate(updated);
	    user.setIsActive("y");
	    user.setIsAdmin("No");
	    user.setUserStatus("Available");
	    
	    /**
	     * Setting Data in Login Table
	     */
	    Login login=new Login();
	    login.setUserId(register.getLoginName());
	    login.setName(register.getFullName());
	    login.setPassword(register.getPassword());
	    hibernateTemplate.save(login);
	    hibernateTemplate.save(user);
	  
		}
		return true;
		}
	
	/**
	 * This method fetches all cities and return it in city list
	 * @return cities
	 */
	public List<City> getCityList(){
		List<City> cities = new ArrayList<City>();
		
		cities = (List<City>) hibernateTemplate.findByNamedQuery("City.fetchAllCities");
		
		return cities;
		
		
		
	}
	/**
	 * This method fetches all states and return it in state list
	 * @return states
	 */
	public List<State> getStateList(){
		List<State> states= new ArrayList<State>();
	
		states = (List<State>) hibernateTemplate.findByNamedQuery("State.findAllStates");
		
		return states;
		
		
		
	}
}
