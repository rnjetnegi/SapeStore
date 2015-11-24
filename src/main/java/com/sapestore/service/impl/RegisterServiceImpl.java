package com.sapestore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapestore.dao.EditUserDao;
import com.sapestore.dao.RegisterDao;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.hibernate.entity.City;
import com.sapestore.hibernate.entity.State;
import com.sapestore.service.RegisterService;
import com.sapestore.vo.RegisterVO;
/**
 * Service class for user registration functionality.
 * 
 * CHANGE LOG 
 * VERSION 	DATE 		AUTHOR 	MESSAGE 
 * 1.0 		20-10-2015 	NIHUL Initial version
 */
@Service("registerService")
public class RegisterServiceImpl implements RegisterService {
	
	@Autowired
	private RegisterDao registerDao;
	private EditUserDao editUserDao;
	
	@Override
	public Boolean addUser(RegisterVO register) throws SapeStoreException {
		 return registerDao.addUserDetails(register);
		
	}

	

	/* (non-Javadoc)
	 * @see com.sapestore.service.RegisterService#getCities()
	 */
	@Override
	public List<City> getCities() throws SapeStoreException {
		// TODO Auto-generated method stub
		return registerDao.getCityList();
	}

	@Override
	public List<State> getStates() throws SapeStoreException {
		
		return registerDao.getStateList();
	}

}
