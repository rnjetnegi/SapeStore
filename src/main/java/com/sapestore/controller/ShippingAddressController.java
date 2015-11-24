package com.sapestore.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.dao.AccountDao;
import com.sapestore.dao.AddressDao;
import com.sapestore.dao.OrderDao;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.hibernate.entity.Address;
import com.sapestore.hibernate.entity.Address2;
import com.sapestore.hibernate.entity.City;
import com.sapestore.hibernate.entity.State;
import com.sapestore.service.AddressService;
import com.sapestore.service.ShoppingCartService;
import com.sapestore.service.impl.AddressServiceImpl;
import com.sapestore.service.impl.ShoppingCartServiceImpl;
import com.sapestore.vo.AddressVO;
import com.sapestore.vo.CartVO;


	
	/**
	 * @author vgar11
	 *
	 */
	@Controller
	public class ShippingAddressController {
		private final static SapeStoreLogger LOGGER = SapeStoreLogger
				.getLogger(OrderDao.class.getName());

		@Autowired
		AddressService addressService;
		
		@Autowired
		private AddressDao addressDao;
		
		@Autowired
		ShoppingCartServiceImpl shoppingCartServiceImpl;
		
		@RequestMapping(value="/shipAddress",method=RequestMethod.GET)
		public String shipaddressget(ModelMap modelMap, HttpSession session) throws SapeStoreException
		{
			/*String username = (String) session.getAttribute("userLoginName");
			List<Address2> addressList = add.retrieveFromId(username);
			
			List<Address2> addressList = addressDao.retrieveFromId("Bihari3");
			List<City> listCity=addressDao.getCityList();
			List<State> listState=addressDao.getStateList();*/
			
			
			List<Address2> addressList = addressService.getAddress((String)session.getAttribute("userLoginName"));
			List<City> listCity =	addressService.getCities();
			List<State> listState = addressService.getStates();

			modelMap.addAttribute("cities", listCity);
			modelMap.addAttribute("states", listState);
			modelMap.addAttribute("newAddress", new Address());
            modelMap.addAttribute("addressList", addressList);
          
			return "ShippingAddress";
		}
		
		/*@RequestMapping(value="/confirmorder",method=RequestMethod.GET)
		public String confOrder(ModelMap modelMap, HttpSession session) throws SapeStoreException
		{			
			return "SuccessfulOrder";
		}*/
		
		@RequestMapping(value="/confirmexistingaddress",method=RequestMethod.GET)
		public String confAddress(@RequestParam String addressId, ModelMap modelMap, HttpServletRequest request) throws SapeStoreException
		{
			Address address = addressService.getAddressById(Integer.parseInt(addressId));
			HttpSession session = request.getSession();
			session.setAttribute("shippingAddress", address);
			String userId=(String) session.getAttribute("userLoginName");
			int cityId = 0;
			int stateId = 0;			
			if(address!=null){
				 cityId=address.getCityId();
				 stateId=address.getStateId();
			}
			 String cityName=addressDao.getCityName(cityId);
			 String stateName=addressDao.getStateName(stateId);
			 CartVO cart = shoppingCartServiceImpl.getCartItems(userId);
			 List cartList = null;
			 if(cart!=null){
				 cartList=cart.getCartItems();
			 }
			 modelMap.addAttribute("user",userId);
			 String addressLine1 = null;
			 String addressLine2 = null;
			 if(address!=null){
				addressLine1=address.getAddressLine1();
				addressLine2=address.getAddressLine2();
			 }
			 if(addressLine2!=null){
				 addressLine1=addressLine1+addressLine2;
			 }
			 int total=(int) session.getAttribute("total");
			 modelMap.addAttribute("cart",cartList);
			 modelMap.addAttribute("addressLine1",addressLine1);
			 modelMap.addAttribute("cityName",cityName);
			 modelMap.addAttribute("stateName",stateName);
			 modelMap.addAttribute("total",total);
			return "OrderConfirmation";
		}

		/**
		 * @param address
		 * @param result
		 * @param modelMap
		 * @return
		 * @throws SapeStoreException 
		 */
		@RequestMapping(value="/addaddress",method=RequestMethod.POST)
		public String shipaddresspost(AddressVO address, BindingResult result, ModelMap modelMap, HttpSession session, HttpServletRequest request) throws SapeStoreException {  
	        
			address.setCountryId(1);
			address.setIsActive("y");
			String userId=(String) session.getAttribute("userLoginName");
			String cityName=null;
			Cookie cookies[] = request.getCookies();
			if(cookies != null){
				for(Cookie cookie : cookies){
				    if("cityId".equals(cookie.getName())){
				    	cityName = cookie.getValue();
				    }
				}
			}
		
			
			/*if (result.hasErrors()){
				modelMap.addAttribute("newAddress", address);
				return "ShippingAddress";
			}
			else {*/
					address.setCityId(addressService.getCityIdByName(cityName));
					addressService.addAddress(address,userId);
					
					/*addressDao.addAddress(address);*/
				//}
					Address sessionAddress=new Address();
				    sessionAddress.setAddressLine1(address.getAddressLine1());
				    sessionAddress.setAddressLine1(address.getAddressLine2());
				    sessionAddress.setCityId(addressService.getCityIdByName(cityName));
				    sessionAddress.setStateId(address.getStateId());
				    session.setAttribute("shippingAddress", sessionAddress);
				
					int cityId = 0;
					int stateId = 0;			
					if(address!=null){
						 cityId=1;//address.getCityId();
						 stateId=address.getStateId();
					}
					 //String cityName=addressDao.getCityName(cityId);
					 String stateName=addressDao.getStateName(stateId);
					 CartVO cart = shoppingCartServiceImpl.getCartItems(userId);
					 List cartList = null;
					 if(cart!=null){
						 cartList=cart.getCartItems();
					 }
					 modelMap.addAttribute("user",address.getName());
					 
					 String addressLine1 = null;
					 String addressLine2 = null;
					 if(address!=null){
						addressLine1=address.getAddressLine1();
						addressLine2=address.getAddressLine2();
					 }
					 if(addressLine2!=null){
						 addressLine1=addressLine1+addressLine2;
					 }
					 int total=(int) session.getAttribute("total");
					 modelMap.addAttribute("cart",cartList);
					 modelMap.addAttribute("addressLine1",addressLine1);
					 modelMap.addAttribute("cityName",cityName);
					 modelMap.addAttribute("stateName",stateName);
					 modelMap.addAttribute("total",total);
			return "OrderConfirmation"; 	
		}
	}

