package com.sapestore.service;

import java.util.List;

import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;
import com.sapestore.hibernate.entity.City;
import com.sapestore.hibernate.entity.State;
import com.sapestore.vo.RegisterVO;
/**
 * Service interface for user login functionality.
 * 
 * CHANGE LOG 
 * VERSION 	DATE 		AUTHOR 	MESSAGE 
 * 1.0 		20-10-2014 	NIHUL Initial version
 */
public interface RegisterService {

	/**
	 * Performs user login authentication.
	 * @param userLogin
	 * @return
	 * @throws SapeStoreSystemException
	 */
	Boolean addUser(RegisterVO register) throws SapeStoreException;
	
	List<City> getCities() throws SapeStoreException;
	List<State> getStates() throws SapeStoreException;
}
