package com.sapestore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapestore.dao.AddressDao;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.hibernate.entity.Address;
import com.sapestore.hibernate.entity.Address2;
import com.sapestore.hibernate.entity.City;
import com.sapestore.hibernate.entity.State;
import com.sapestore.service.AddressService;
import com.sapestore.vo.AddressVO;


@Service("addressService")
public class AddressServiceImpl implements AddressService{

	@Autowired
	AddressDao addressDao ;
	
	
	
	
	
	
	@Override
	public List<City> getCities() throws SapeStoreException {
		// TODO Auto-generated method stub
		return addressDao.getCityList();
	}

	@Override
	public List<State> getStates() throws SapeStoreException {
		
		return addressDao.getStateList();
	}
	
	
	/*String username = (String) session.getAttribute("userLoginName");
	List<Address2> addressList = add.retrieveFromId(username);*/
	

	@Override
	public List<Address2> getAddress(String userId) {
		return addressDao.retrieveFromId(userId);
	}

	@Override
	public void addAddress(AddressVO address,String userId){
		try {
			address.setCountryId(1);
			address.setIsActive("y");
			addressDao.addAddress(address,userId);
		} catch (SapeStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public Address getAddressById(Integer addressId){
		return addressDao.getAddressById(addressId);
	}
	
	@Override
	public Integer getCityIdByName(String cityName){
		return addressDao.getCityIdByName(cityName);
	}
}
