package com.sapestore.service;

import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;
import com.sapestore.vo.RegisterVO;
/**
 * Service interface for user login functionality.
 * 
 * CHANGE LOG 
 * VERSION 	DATE 		AUTHOR 	MESSAGE 
 * 1.0 		20-06-2014 	CHARUL Initial version
 */

public interface EditUserService {

	/**
	 * Performs user login authentication.
	 * @param userLogin
	 * @return
	 * @throws SapeStoreSystemException
	 */
	void editUser(RegisterVO register,String userId) throws SapeStoreException;
	RegisterVO getUser(String loginName) throws SapeStoreException;
}
