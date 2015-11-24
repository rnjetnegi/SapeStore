package com.sapestore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapestore.dao.EditUserDao;
import com.sapestore.dao.GetUserDao;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.service.EditUserService;
import com.sapestore.vo.RegisterVO;
/**
 * Service class for modify user details functionality.
 * 
 * CHANGE LOG 
 * VERSION 	DATE 		AUTHOR 	MESSAGE 
 * 1.0 		21-10-2015 	CHARUL Initial version
 */
@Service("editUserService")
public class EditUserServiceImpl implements EditUserService {
	@Autowired
	private EditUserDao editUserDao;
	@Autowired
	private GetUserDao getUserDao;
	
	
	/**
	 * 	This method calls editUserDAO class
	 */
	@Override
	public void editUser(RegisterVO register, String userId) throws SapeStoreException {
		editUserDao.editUserDetails(register, userId);
	}
	
	/**
	 * This method calls getUserDAO class
	 */
	@Override
	 public RegisterVO getUser(String loginName) throws SapeStoreException{
		RegisterVO user=getUserDao.getUser(loginName);
		return user;
	
		
	}
}
