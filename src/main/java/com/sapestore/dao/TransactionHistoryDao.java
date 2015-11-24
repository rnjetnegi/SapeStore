/**
 * 
 */
package com.sapestore.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;


import org.springframework.stereotype.Repository;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.hibernate.entity.OrderInfo;
import com.sapestore.hibernate.entity.OrderItemInfo;
import com.sapestore.hibernate.entity.User;
import com.sapestore.vo.OrderVO;

/**
 * DAO class for transaction history.
 * 
 * CHANGE 	LOG 
 * VERSION 	DATE 			AUTHOR MESSAGE 
 * 1.0 		23-10-2015 		Iteration 1 implementation
 */

@Repository
public class TransactionHistoryDao {
	private String userId;
	private OrderInfo order; 
	
	private final static SapeStoreLogger LOGGER = SapeStoreLogger.getLogger(TransactionHistoryDao.class.getName());

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	public List<OrderVO> getUserAllOrders(String uId) {
			String[] namedParams = {"userId".trim()};
			Object[] paramValues = {uId.trim()};	
			
			List userOrdersList = (List)hibernateTemplate.findByNamedQueryAndNamedParam("OrderInfo.findUserOrders", namedParams, paramValues);
			List<OrderVO> orders= new ArrayList<>();
			
			for(int i=0;i<userOrdersList.size();i++){
				for(int j=0; j<((OrderInfo)userOrdersList.get(i)).getOrderItemInfoList().size(); j++){
					OrderVO ovo = new OrderVO();
					ovo.setItemName(((OrderInfo)userOrdersList.get(i)).getOrderItemInfoList().get(j).getBookTitle());
					ovo.setIsbn(((OrderInfo)userOrdersList.get(i)).getOrderItemInfoList().get(j).getIsbn());
					ovo.setAuthor(((OrderInfo)userOrdersList.get(i)).getOrderItemInfoList().get(j).getBookAuthor());
					ovo.setPurchaseDate(((OrderInfo)userOrdersList.get(i)).getOrderItemInfoList().get(j).getUpdatedDate());
					ovo.setPurchasePrice(((OrderInfo)userOrdersList.get(i)).getOrderItemInfoList().get(j).getBookPrice());
					ovo.setImage(((OrderInfo)userOrdersList.get(i)).getOrderItemInfoList().get(j).getBook().getBookThumbImage());
					ovo.setStatus(((OrderInfo)userOrdersList.get(i)).getOrderStatus());
					ovo.setType(((OrderInfo)userOrdersList.get(i)).getOrderItemInfoList().get(j).getPurchaseType());
					ovo.setExpectedReturnDate(((OrderInfo)userOrdersList.get(i)).getOrderItemInfoList().get(j).getExpectedReturnDate());
					ovo.setActualReturnDate(((OrderInfo)userOrdersList.get(i)).getOrderItemInfoList().get(j).getActualReturnDate());
					ovo.setRentAmount(((OrderInfo)userOrdersList.get(i)).getOrderItemInfoList().get(j).getRentPrice());
					if((((OrderInfo)userOrdersList.get(i)).getOrderItemInfoList().get(j).getExpectedReturnDate())!=null && (((OrderInfo)userOrdersList.get(i)).getOrderItemInfoList().get(j).getActualReturnDate())!=null){
						ovo.setLateFee(calculateRentPrice(((OrderInfo)userOrdersList.get(i)).getOrderItemInfoList().get(j).getExpectedReturnDate(), ((OrderInfo)userOrdersList.get(i)).getOrderItemInfoList().get(j).getActualReturnDate(),((OrderInfo)userOrdersList.get(i)).getOrderItemInfoList().get(j).getBookPrice() ));
					}
					orders.add(ovo);
				}
			}
			
			return orders;
		}
	
	
public BigDecimal calculateRentPrice(Date expectedReturnDate,Date actualReturnDate, int price){
		
		BigDecimal diffInDays = new BigDecimal(
				(actualReturnDate.getTime() - expectedReturnDate.getTime())
						/ (1000 * 60 * 60 * 24));
		BigDecimal bigDecimal1000 = new BigDecimal("1000");
		if(diffInDays.doubleValue()>0){
			
			BigDecimal d1 = diffInDays.divide(bigDecimal1000);
			BigDecimal d2 = d1.multiply(new BigDecimal(price));
			return d2;
		}else{
			
			return new BigDecimal("0");
		}
		
	}
}
