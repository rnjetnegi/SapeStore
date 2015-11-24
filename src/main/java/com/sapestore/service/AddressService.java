package com.sapestore.service;

import java.util.List;

import com.sapestore.exception.SapeStoreException;
import com.sapestore.hibernate.entity.Address;
import com.sapestore.hibernate.entity.Address2;
import com.sapestore.hibernate.entity.City;
import com.sapestore.hibernate.entity.State;
import com.sapestore.vo.AddressVO;

public interface AddressService {

	List<City> getCities() throws SapeStoreException;

	List<State> getStates() throws SapeStoreException;

	List<Address2> getAddress(String userId);

	void addAddress(AddressVO address,String userId);

	Address getAddressById(Integer addressId);

	Integer getCityIdByName(String cityName);

}
