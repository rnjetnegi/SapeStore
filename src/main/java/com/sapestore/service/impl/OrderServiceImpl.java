package com.sapestore.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapestore.dao.OrderDao;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.service.OrderService;
import com.sapestore.vo.DispatchSlip;
import com.sapestore.vo.OrderVO;
import com.sapestore.vo.PaymentSlip;

/**
 * Service class for updating rent information.
 * 
 * CHANGE LOG VERSION DATE AUTHOR MESSAGE 1.0 20-06-2014 jxu1 completed
 * functionality
 */

@Service("orderService")
public class OrderServiceImpl implements OrderService {

	// private final static SapeStoreLogger LOGGER =
	// SapeStoreLogger.getLogger(OrderServiceImpl.class.getName());

	private static Map<String, List<OrderVO>> undispatchedOrdersList = null;

	@Autowired
	private OrderDao orderDao;

	/**
	 * Return list of dispatched order <<<<<<< HEAD =======
	 * 
	 * @ param list of orders @ return list of dispatched orders >>>>>>>
	 * 422aaba3b2861c6ea5c034d3f29818eff99a8ee5
	 */
	@Override
	public List<DispatchSlip> getDispatchedOrders(List<Integer> list)
			throws SapeStoreException {
		List<DispatchSlip> dispatchList = orderDao.returnDispatchedSlips(list);

		String customerId = dispatchList.get(0).getCustomerName();
		List<OrderVO> itemsList = undispatchedOrdersList.get(customerId);

		for (OrderVO currentItem : itemsList) {
			if (currentItem.getOrderItemNumber() == list.get(0)) {
				currentItem.setDispatched(true);
				return dispatchList;
			}
		}

		return dispatchList;
	}

	/**
	 * Return list of undispatched orders
	 */
	@Override
	public Map<String, List<OrderVO>> getUndispatchedOrdersList()
			throws SapeStoreException {

		// if (undispatchedOrdersList == null) {
		undispatchedOrdersList = new HashMap<String, List<OrderVO>>();
		generateUndispatchedOrdersList();
		// }
		return undispatchedOrdersList;
	}

	// Add item to List<OrderVO> of toDisPatchOrderList map
	private void addToList(String mapKey, OrderVO item) {
		List<OrderVO> itemsList = undispatchedOrdersList.get(mapKey);

		// If list does not exist create it
		if (itemsList == null) {
			itemsList = new ArrayList<OrderVO>();
			if (item.getStatus().equalsIgnoreCase("DISPATCHED")) {
				item.setDispatched(true);
			} else {
				item.setDispatched(false);
			}
			if (item.getPaymentStatus().equalsIgnoreCase("RECEIVED")) {
				item.setPaid(true);
				if (item.getType().equalsIgnoreCase("PURCHASED")) {
					return;
				}
			} else {
				item.setPaid(false);
			}
			itemsList.add(item);
			undispatchedOrdersList.put(mapKey, itemsList);
		} else {
			// add if item is not already in list
			if (!itemsList.contains(item)) {
				if (item.getStatus().equalsIgnoreCase("DISPATCHED")) {
					item.setDispatched(true);
				} else {
					item.setDispatched(false);
				}
				if (item.getPaymentStatus().equalsIgnoreCase("RECEIVED")) {
					item.setPaid(true);
					if (item.getType().equalsIgnoreCase("PURCHASED")) {
						return;
					}
				} else {
					item.setPaid(false);
				}
				itemsList.add(item);
			}
		}
	}

	/**
	 * Generate static collection list undispatchedOrdersList
	 */
	private void generateUndispatchedOrdersList() {
		List<OrderVO> undispatchedOrders = null;
		try {
			undispatchedOrders = orderDao.getAllUndispatchedOrders();
		} catch (SapeStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (OrderVO currentItem : undispatchedOrders) {
			addToList(currentItem.getCustomerId(), currentItem);
		}
	}

	/**
	 * Return list of payment slip
	 */
	@Override
	public List<PaymentSlip> setPaymentReceived(int orderItemId)
			throws SapeStoreException {
		List<PaymentSlip> paymentSlip = orderDao
				.setPaymentReceived(orderItemId);
		String customerId = paymentSlip.get(0).getCustomerName();

		List<OrderVO> itemsList = undispatchedOrdersList.get(customerId);

		for (OrderVO current : itemsList) {
			if (current.getOrderItemNumber() == orderItemId
					&& current.getType().equalsIgnoreCase("purchased")) {
				itemsList.remove(current);
				return paymentSlip;
			} else if (current.getOrderItemNumber() == orderItemId
					&& current.getType().equalsIgnoreCase("rented")) {
				current.setPaid(true);
				return paymentSlip;
			}
		}

		return paymentSlip;
	}

	/**
	 * set the status of rented books from the admin console unused at the
	 * moment
	 * 
	 * @param list
	 * @return beans
	 */
	/*
	 * private List<OrderVO> setRentedOrders(List<OrderItemInfo>
	 * orderItemInfoList) { List<OrderVO> beans = null;
	 * 
	 * if (orderItemInfoList != null && !orderItemInfoList.isEmpty()) { beans =
	 * new ArrayList<OrderVO>(); for (int i = 0; i < orderItemInfoList.size();
	 * i++) { OrderVO tempList = new OrderVO();
	 * tempList.setOrderNumber(orderItemInfoList.get(i).getOrderId());
	 * tempList.setItemName(orderItemInfoList.get(i).getBookTitle());
	 * tempList.setRentAmount(orderItemInfoList.get(i).getRentPrice());
	 * 
	 * String sd = orderItemInfoList.get(i).getOrderStatus(); if
	 * (sd.equalsIgnoreCase("Dispatched")) { tempList.setOrderStatus(true); }
	 * else { tempList.setOrderStatus(false); } String sr =
	 * orderItemInfoList.get(i).getReturnStatus(); if
	 * (sr.equalsIgnoreCase("Returned")) { tempList.setReturnReceived(true); }
	 * else { tempList.setReturnReceived(false); } if
	 * (orderItemInfoList.get(i).getExpectedReturnDate() == null) {
	 * 
	 * } else { tempList.setExpectedReturnDate(orderItemInfoList.get(i)
	 * .getExpectedReturnDate()); } if
	 * (orderItemInfoList.get(i).getActualReturnDate() == null) {
	 * 
	 * } else { tempList.setActualReturnDate(orderItemInfoList.get(i)
	 * .getExpectedReturnDate()); }
	 * tempList.setLateFee(orderItemInfoList.get(i).getLateFee());
	 * beans.add(tempList); } } return beans; }
	 */

	/**
	 * Unused at the moment
	 */
	/*
	 * @Override public List<Integer> updateDispatch(List<RentedUpdate>
	 * rentedUpdateList) throws SapeStoreException { if
	 * (LOGGER.isDebugEnabled()) { LOGGER.debug("updateDispatch method: START");
	 * } List<OrderItemInfo> orderItemInfoList = orderDao.getRentedOrders();
	 * List<OrderVO> rentedOrderBeans = setRentedOrders(orderItemInfoList);
	 * <<<<<<< HEAD
	 * 
	 * List<Integer> orderNums = null; List<Boolean> orgListDispatch = null;
	 * List<Boolean> orgListReturn = null; List<Boolean> newListDispatch = null;
	 * List<Boolean> newListReturn = null;
	 * 
	 * if (rentedOrderBeans != null && rentedOrderBeans.size() > 0) { orderNums
	 * = new ArrayList<Integer>(); orgListDispatch = new ArrayList<Boolean>();
	 * orgListReturn = new ArrayList<Boolean>(); for (OrderVO r :
	 * rentedOrderBeans) { orgListDispatch.add(r.isOrderStatus());
	 * orgListReturn.add(r.isReturnReceived());
	 * orderNums.add(r.getOrderNumber()); } } if (rentedUpdateList != null &&
	 * rentedUpdateList.size() > 0) { newListDispatch = new
	 * ArrayList<Boolean>(); newListReturn = new ArrayList<Boolean>(); for
	 * (RentedUpdate r : rentedUpdateList) {
	 * newListDispatch.add(r.getDispatchStatus());
	 * newListReturn.add(r.getReturnStatus()); } } List<Integer>
	 * dispatchedOrders = orderDao.updateDispatch( orgListDispatch,
	 * orgListReturn, newListDispatch, newListReturn, orderNums); if
	 * (LOGGER.isDebugEnabled()) { LOGGER.debug("updateDispatch method: END"); }
	 * return dispatchedOrders; }
	 */

	/*
	 * @Override public void updateReturn(List<RentedUpdate> rentedUpdateList)
	 * throws SapeStoreException { List<OrderItemInfo> orderItemInfoList =
	 * orderDao.getRentedOrders(); List<OrderVO> rentedOrderBeans =
	 * setRentedOrders(orderItemInfoList);
	 * 
	 * List<Integer> orderNums = new ArrayList<Integer>(); List<Boolean>
	 * orgListDispatch = new ArrayList<Boolean>(); List<Boolean> orgListReturn =
	 * new ArrayList<Boolean>(); List<Boolean> newListDispatch = new
	 * ArrayList<Boolean>(); List<Boolean> newListReturn = new
	 * ArrayList<Boolean>();
	 * 
	 * =======
	 * 
	 * List<Integer> orderNums = null; List<Boolean> orgListDispatch = null;
	 * List<Boolean> orgListReturn = null; List<Boolean> newListDispatch = null;
	 * List<Boolean> newListReturn = null;
	 * 
	 * if (rentedOrderBeans != null && rentedOrderBeans.size() > 0) { orderNums
	 * = new ArrayList<Integer>(); orgListDispatch = new ArrayList<Boolean>();
	 * orgListReturn = new ArrayList<Boolean>(); for (OrderVO r :
	 * rentedOrderBeans) { orgListDispatch.add(r.isOrderStatus());
	 * orgListReturn.add(r.isReturnReceived());
	 * orderNums.add(r.getOrderNumber()); } } if (rentedUpdateList != null &&
	 * rentedUpdateList.size() > 0) { newListDispatch = new
	 * ArrayList<Boolean>(); newListReturn = new ArrayList<Boolean>(); for
	 * (RentedUpdate r : rentedUpdateList) {
	 * newListDispatch.add(r.getDispatchStatus());
	 * newListReturn.add(r.getReturnStatus()); } } List<Integer>
	 * dispatchedOrders = orderDao.updateDispatch( orgListDispatch,
	 * orgListReturn, newListDispatch, newListReturn, orderNums); if
	 * (LOGGER.isDebugEnabled()) { LOGGER.debug("updateDispatch method: END"); }
	 * return dispatchedOrders; }
	 */

	/*
	 * @Override public void updateReturn(List<RentedUpdate> rentedUpdateList)
	 * throws SapeStoreException { List<OrderItemInfo> orderItemInfoList =
	 * orderDao.getRentedOrders(); List<OrderVO> rentedOrderBeans =
	 * setRentedOrders(orderItemInfoList);
	 * 
	 * List<Integer> orderNums = new ArrayList<Integer>(); List<Boolean>
	 * orgListDispatch = new ArrayList<Boolean>(); List<Boolean> orgListReturn =
	 * new ArrayList<Boolean>(); List<Boolean> newListDispatch = new
	 * ArrayList<Boolean>(); List<Boolean> newListReturn = new
	 * ArrayList<Boolean>();
	 * 
	 * >>>>>>> 422aaba3b2861c6ea5c034d3f29818eff99a8ee5 for (OrderVO r :
	 * rentedOrderBeans) { orgListDispatch.add(r.isOrderStatus());
	 * orgListReturn.add(r.isReturnReceived());
	 * orderNums.add(r.getOrderNumber()); } for (RentedUpdate r :
	 * rentedUpdateList) { newListDispatch.add(r.getDispatchStatus());
	 * newListReturn.add(r.getReturnStatus()); }
	 * orderDao.updateReturn(orgListDispatch, orgListReturn, newListDispatch,
	 * newListReturn, orderNums); }
	 */
}
