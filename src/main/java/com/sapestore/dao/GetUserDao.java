package com.sapestore.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.hibernate.entity.Address;
import com.sapestore.hibernate.entity.City;
import com.sapestore.hibernate.entity.State;
import com.sapestore.hibernate.entity.User;
import com.sapestore.vo.RegisterVO;


/**
 * DAO class to retrieve user details
 * 
 * CHANGE LOG 
 * 	VERSION 		DATE   			AUTHOR   		MESSAGE 
 * 	1.0 			21-10-2015 		CHARUL,NIHUL   	Initial version
 */
@Repository
public class GetUserDao {
	@Autowired
	private HibernateTemplate hibernateTemplate;
	/**
	 * Logger for log messages.
	 */
	private final static SapeStoreLogger LOGGER = SapeStoreLogger.getLogger(RegisterDao.class.getName());
	
	/**
	 * 
	 * @param loginName
	 * @return register
	 */
	public RegisterVO getUser(String loginName){
		String[] namedParams = {"loginName"};
		Object[] paramValues = {loginName};	
		String cityName=null;
		String stateName=null;
		String postalCode=null;
		RegisterVO register=new RegisterVO();
		List listUser =  hibernateTemplate.findByNamedQueryAndNamedParam("User.findByUserId", namedParams, paramValues);
		
		if(!listUser.isEmpty())
		{
			User user=(User) listUser.get(0);
			Integer addId=user.getAddressId();
			String[] namedParams1 = {"addressId"};
			Object[] paramValues1 = {addId};	
			List listAddress=hibernateTemplate.findByNamedQueryAndNamedParam("Address.findByAddressId", namedParams1, paramValues1);
			if(!listAddress.isEmpty())
			{
				Address address=(Address) listAddress.get(0);
				Integer cityId=address.getCityId();
				Integer stateId=address.getStateId();
				String[] namedParams2 = {"cityId"};
				Object[] paramValues2 = {cityId};
				List listCity=hibernateTemplate.findByNamedQueryAndNamedParam("City.findByCityId", namedParams2, paramValues2);
				String[] namedParams3 = {"stateId"};
				Object[] paramValues3 = {stateId};
				
				List listState=hibernateTemplate.findByNamedQueryAndNamedParam("State.findByStateId", namedParams3, paramValues3);
				String addressLine1=address.getAddressLine1();
				String addressLine2=address.getAddressLine2();
				postalCode=address.getPostalCode();
				if(!listCity.isEmpty())
				{
					City city=(City) listCity.get(0);
					cityName=city.getCityName();
				}
				if(!listState.isEmpty())
				{
					State state= (State) listState.get(0);
				stateName=state.getStateName();
				}
				
				register.setAddressLine1(addressLine1);
				register.setAddressLine2(addressLine2);
				register.setCity(cityName);
				register.setState(stateName);
				register.setFullName(user.getName());
				register.setLoginName(user.getUserId());
				register.setEmail(user.getEmailAddress());
				register.setMobileNumber(user.getMobileNumber());
				register.setPhone(user.getPhone());
				register.setPostalCode(postalCode);
				
			}
			return register;
		}
		
		return null;
	
	}
	}
	


