package com.sapestore.dao;

import java.sql.Date;
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
 * 	VERSION 		DATE   			AUTHOR   	MESSAGE 
 * 	1.0 			21-10-2015 		CHARUL   	Initial version
 */
@Repository
public class EditUserDao {
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * Logger for log messages.
	 */
	private final static SapeStoreLogger LOGGER = SapeStoreLogger
			.getLogger(RegisterDao.class.getName());
	private SessionFactory factory;

	/**
	 * Method to edit user details based on the parameters provided.
	 * 
	 * 
	 * @param register
	 *            (containing details modified by user)
	 * @return
	 * @throws ConnectionException
	 * @throws TransactionExecutionException
	 */
	public void editUserDetails(RegisterVO register,String userId) throws SapeStoreException {

		User user=null ;
		Address adr = new Address();
		Integer cityId = null;
		Integer stateId = null;
		Integer countryId = null;
		Integer addressId= null;
		 Login login=null;

		String[] namedParams1 = { "name" };
		Object[] paramValues1 = { register.getCity() };

		List listCity = hibernateTemplate.findByNamedQueryAndNamedParam(
				"City.findByCityName", namedParams1, paramValues1);
		if(!listCity.isEmpty()){
			 City city = (City) listCity.get(0);
			 cityId = city.getCityId();
		     stateId = city.getStateId();
		}


		String[] namedParams2 = { "stateId" };
		Object[] paramValues2 = { stateId };

		List listState = hibernateTemplate.findByNamedQueryAndNamedParam(
				"State.findByStateId", namedParams2, paramValues2);
		if(!listState.isEmpty()){
			State state = (State) listState.get(0);  
			countryId = state.getCountryId();
		}

		Date created = new Date(Calendar.getInstance().getTime().getTime());
		Date updated = new Date(Calendar.getInstance().getTime().getTime());
	    
		String[] namedParams3 = { "userId" };
		Object[] paramValues3 = { userId };
		
		List listAddressId = hibernateTemplate.findByNamedQueryAndNamedParam(
				"Address.findByUserId", namedParams3, paramValues3);
		if (!listAddressId.isEmpty()) {
			 adr = (Address) listAddressId.get(0);
			addressId = adr.getAddressId();
		}

        adr.setCityId(cityId);
        adr.setStateId(stateId);
        adr.setCountryId(countryId);
        adr.setAddressLine1(register.getAddressLine1());
        adr.setAddressLine2(register.getAddressLine2());
        adr.setUserId(userId);
        adr.setPostalCode(register.getPostalCode());
        adr.setCreatedDate(created);
        adr.setUpdatedDate(updated);
        adr.setIsActive("y");
        hibernateTemplate.update(adr);
        
        
        String[] namedParams4 = { "loginName" };
		Object[] paramValues4 = { userId };
        List listUser= hibernateTemplate.findByNamedQueryAndNamedParam(
        		"User.findByUserId", namedParams4, paramValues4);
		if (!listUser.isEmpty()) {
			 user = (User) listUser.get(0);
		}
        
        
	    user.setName(register.getFullName());
	    user.setPassword(register.getPassword());
	    user.setPhone(register.getPhone());
	    user.setMobileNumber(register.getMobileNumber());
	    user.setCreatedDate(created);
	    user.setUpdatedDate(updated);
	    user.setIsActive("y");
	    user.setIsAdmin("No");
	    user.setUserStatus("Available");
	   
		hibernateTemplate.update(user);
	    
		String[] namedParams5 = { "userId" };
		Object[] paramValues5 = { userId };
        List listLogin= hibernateTemplate.findByNamedQueryAndNamedParam(
        		"Login.findByUserId", namedParams5, paramValues5);
        if (!listLogin.isEmpty()) {
			 login = (Login) listLogin.get(0);
        }
	   
	   
	    login.setName(register.getFullName());
	    login.setPassword(register.getPassword());
	   
	    hibernateTemplate.update(login);
	}
}
