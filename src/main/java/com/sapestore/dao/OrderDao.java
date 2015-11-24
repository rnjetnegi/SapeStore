package com.sapestore.dao;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;
import com.sapestore.hibernate.entity.Address;
import com.sapestore.hibernate.entity.Book;
import com.sapestore.hibernate.entity.CartItem;
import com.sapestore.hibernate.entity.OrderInfo;
import com.sapestore.hibernate.entity.OrderItemInfo;
import com.sapestore.vo.CartItemVO;
import com.sapestore.vo.DispatchSlip;
import com.sapestore.vo.OrderVO;
import com.sapestore.vo.PaymentSlip;
import com.sun.mail.iap.ConnectionException;

/**
 * DAO class for admin order management module
 */
@Repository
@Transactional
public class OrderDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	@Autowired
	private AddressDao addressDao;

	/**
	 * Logger for log messages.
	 */
	private final static SapeStoreLogger LOGGER = SapeStoreLogger
			.getLogger(OrderDao.class.getName());

	/*
	 * Returns all undispatched orders in database
	 */
	public List<OrderVO> getAllUndispatchedOrders() throws SapeStoreException {
		List<OrderVO> orders = new ArrayList<OrderVO>();
		List<OrderInfo> userOrdersList = new ArrayList<OrderInfo>();

		OrderInfo orderInfo;

		List<Integer> orderIdList = new ArrayList<Integer>();
		List<?> orderIds = getLists();

		for (int i = 0; i < orderIds.size(); i++) {
			@SuppressWarnings("unchecked")
			HashMap<String, Object> aRow = (HashMap<String, Object>) orderIds
					.get(i);

			BigDecimal bigOrderId = (BigDecimal) aRow.get("ORDER_ID");
			int orderId = bigOrderId.intValue();
			if (!orderIdList.contains(orderId)) {
				orderIdList.add(orderId);
			}
		}

		for (int orderId : orderIdList) {
			orderInfo = hibernateTemplate.get(OrderInfo.class, orderId);
			userOrdersList.add(orderInfo);
		}

		for (int i = 0; i < userOrdersList.size(); i++) {
			for (int j = 0; j < ((OrderInfo) userOrdersList.get(i))
					.getOrderItemInfoList().size(); j++) {
				OrderVO ovo = new OrderVO();
				ovo.setItemName(((OrderInfo) userOrdersList.get(i))
						.getOrderItemInfoList().get(j).getBookTitle());
				ovo.setPurchasePrice(((OrderInfo) userOrdersList.get(i))
						.getOrderItemInfoList().get(j).getBookPrice());
				ovo.setType(((OrderInfo) userOrdersList.get(i))
						.getOrderItemInfoList().get(j).getPurchaseType());
				ovo.setExpectedReturnDate(((OrderInfo) userOrdersList.get(i))
						.getOrderItemInfoList().get(j).getExpectedReturnDate());
				ovo.setRentAmount(((OrderInfo) userOrdersList.get(i))
						.getOrderItemInfoList().get(j).getRentPrice());
				ovo.setCustomerId(((OrderInfo) userOrdersList.get(i))
						.getUserId());
				ovo.setOrderNumber(((OrderInfo) userOrdersList.get(i))
						.getOrderId());
				ovo.setOrderItemNumber(((OrderInfo) userOrdersList.get(i))
						.getOrderItemInfoList().get(j).getOrderItemId());
				ovo.setPaymentStatus(((OrderInfo) userOrdersList.get(i))
						.getOrderItemInfoList().get(j).getPaymentStatus());
				ovo.setEmailId(((OrderInfo) userOrdersList.get(i))
						.getEmailAddress());
				ovo.setLateFee(((OrderInfo) userOrdersList.get(i))
						.getOrderItemInfoList().get(j).getLateFee());
				ovo.setReturnStatus(((OrderInfo) userOrdersList.get(i))
						.getOrderItemInfoList().get(j).getReturnStatus());

				ovo.setAddressLine1(((OrderInfo) userOrdersList.get(i))
						.getAddressLine1());

				ovo.setAddressLine2(((OrderInfo) userOrdersList.get(i))
						.getAddressLine2());
				ovo.setCityName(((OrderInfo) userOrdersList.get(i))
						.getCityName());
				ovo.setStateName(((OrderInfo) userOrdersList.get(i))
						.getStateName());
				ovo.setStatus(((OrderInfo) userOrdersList.get(i))
						.getOrderItemInfoList().get(j).getOrderStatus());
				orders.add(ovo);
			}
		}

		return orders;
	}

	public List<?> getLists() throws SapeStoreException {
		LOGGER.debug(" ProductDao.getPurchasedRentedAdminReport method: START ");

		List<?> finalList = null;

		try {
			String sqlQuery = "SELECT  order_info.order_id FROM (order_info INNER JOIN order_item_info ON order_item_info.ORDER_ID=order_info.order_id) where (order_item_info.PAYMENT_STATUS ='NOT RECEIVED' OR order_item_info.RETURN_STATUS = 'NOT RETURNED')";
			List<?> OrderedlistBook = hibernateTemplate
					.getSessionFactory()
					.getCurrentSession()
					.createSQLQuery(sqlQuery)
					.setResultTransformer(
							AliasToEntityMapResultTransformer.INSTANCE).list();

			return OrderedlistBook;

		} catch (SapeStoreSystemException se) {
			LOGGER.fatal(
					"A DB exception occured while getting the user profile", se);
		}
		LOGGER.debug(" ProductDao.getPurchasedRentedAdminReport method: END ");
		return finalList;
	}

	public List<OrderVO> getShippingAddressofParticularUser(Integer orderId) {

		List<OrderVO> orders = new ArrayList<OrderVO>();

		List userOrdersList = (List) hibernateTemplate
				.findByNamedQuery("OrderInfo.findUndispatchedOrders");
		for (int i = 0; i < userOrdersList.size(); i++) {
			for (int j = 0; j < ((OrderInfo) userOrdersList.get(i))
					.getOrderItemInfoList().size(); j++) {
				OrderVO ovo = new OrderVO();
				ovo.setItemName(((OrderInfo) userOrdersList.get(i))
						.getOrderItemInfoList().get(j).getBookTitle());
				ovo.setPurchasePrice(((OrderInfo) userOrdersList.get(i))
						.getOrderItemInfoList().get(j).getBookPrice());
				ovo.setType(((OrderInfo) userOrdersList.get(i))
						.getOrderItemInfoList().get(j).getPurchaseType());
				ovo.setExpectedReturnDate(((OrderInfo) userOrdersList.get(i))
						.getOrderItemInfoList().get(j).getExpectedReturnDate());
				ovo.setRentAmount(((OrderInfo) userOrdersList.get(i))
						.getOrderItemInfoList().get(j).getRentPrice());
				ovo.setCustomerId(((OrderInfo) userOrdersList.get(i))
						.getUserId());
				ovo.setOrderNumber(((OrderInfo) userOrdersList.get(i))
						.getOrderId());
				ovo.setOrderItemNumber(((OrderInfo) userOrdersList.get(i))
						.getOrderItemInfoList().get(j).getOrderItemId());
				ovo.setPaymentStatus(((OrderInfo) userOrdersList.get(i))
						.getOrderItemInfoList().get(j).getPaymentStatus());
				ovo.setEmailId(((OrderInfo) userOrdersList.get(i))
						.getEmailAddress());
				orders.add(ovo);
			}
		}

		return orders;
	}

	/**
	 * This method sets dispatch slip.
	 * 
	 * @param list1
	 *            List of all the ordersIds to be dispatched
	 * @return List of all the orders to be dispatched
	 * @throws ConnectionException
	 * @throws TransactionExecutionException
	 */
	public List<DispatchSlip> returnDispatchedSlips(List<Integer> list1)
			throws SapeStoreException {
		LOGGER.debug("returnDispatchedSlips method: START");
		List<DispatchSlip> dispatchSlipBeans = new ArrayList<DispatchSlip>();

		try {
			OrderItemInfo orderItemInfo = null;

			for (Integer orderItemId : list1) {
				orderItemInfo = hibernateTemplate.get(OrderItemInfo.class,
						orderItemId);
				orderItemInfo.setOrderStatus("DISPATCHED");
				orderItemInfo.setDispatchDate(new Date());
				hibernateTemplate.saveOrUpdate(orderItemInfo);
				LOGGER.debug("Orders are updated");
			}

			OrderInfo orderInfo = null;
			orderInfo = hibernateTemplate.get(OrderInfo.class,
					orderItemInfo.getOrderId());
			if (checkOrderDispatch(orderInfo.getOrderId())) {
				orderInfo.setOrderStatus("DISPATCHED");
				hibernateTemplate.saveOrUpdate(orderInfo);
			}
			DispatchSlip dispatchSlipBean = new DispatchSlip();

			dispatchSlipBean.setOrderNumber(orderInfo.getOrderId());
			dispatchSlipBean.setOrderItemNumber(orderItemInfo.getOrderItemId());
			dispatchSlipBean.setAddressLine1(orderInfo.getAddressLine1());
			dispatchSlipBean.setAddressLine2(orderInfo.getAddressLine2());
			dispatchSlipBean.setCityName(orderInfo.getCityName());
			dispatchSlipBean.setStateName(orderInfo.getStateName());
			dispatchSlipBean.setCustomerName(orderInfo.getUserId());
			dispatchSlipBeans.add(dispatchSlipBean);

		} catch (SapeStoreSystemException se) {
			LOGGER.fatal(
					"A DB exception occured while getting the dispatch orders list",
					se);
		}
		LOGGER.debug("returnDispatchedSlips method: END");
		return dispatchSlipBeans;
	}

	/**
	 * This method sets paid item
	 * 
	 * @param orderItemId
	 * @return OrderVO of paid item
	 */
	public List<PaymentSlip> setPaymentReceived(int orderItemId) {

		List<PaymentSlip> paymentSlipBeans = new ArrayList<PaymentSlip>();

		OrderItemInfo orderItemInfo = null;
		orderItemInfo = hibernateTemplate.get(OrderItemInfo.class, orderItemId);
		orderItemInfo.setPaymentStatus("RECEIVED");
		hibernateTemplate.saveOrUpdate(orderItemInfo);

		OrderInfo orderInfo = null;
		orderInfo = hibernateTemplate.get(OrderInfo.class,
				orderItemInfo.getOrderId());

		PaymentSlip paymentSlipBean = new PaymentSlip();

		paymentSlipBean.setOrderNumber(orderInfo.getOrderId());
		paymentSlipBean.setOrderItemNumber(orderItemInfo.getOrderItemId());
		paymentSlipBean.setCustomerName(orderInfo.getUserId());
		if (orderItemInfo.getPurchaseType().equalsIgnoreCase("purchased")) {
			paymentSlipBean.setPurchasePrice((orderItemInfo.getBookPrice()
					.intValue()));
			paymentSlipBean.setType("PURCHASED");
		} else {
			// paymentSlipBean.setPrice(orderItemInfo.getRentPrice().intValue());
			paymentSlipBean.setPurchasePrice((orderItemInfo.getBookPrice()
					.intValue()));
			paymentSlipBean.setType("RENTED");
		}

		paymentSlipBeans.add(paymentSlipBean);

		return paymentSlipBeans;
	}

	/**
	 * Sets order to dispatched if all order_items associated with it are
	 * dispatched
	 * 
	 * @param orderId
	 * @return
	 */
	private boolean checkOrderDispatch(int orderId) {
		OrderInfo orderInfo = null;
		orderInfo = hibernateTemplate.get(OrderInfo.class, orderId);
		List<OrderItemInfo> orderItemInfoList = orderInfo
				.getOrderItemInfoList();
		for (OrderItemInfo orderItemInfo : orderItemInfoList) {
			if (orderItemInfo.getOrderStatus().equalsIgnoreCase(
					"NOT DISPATCHED")) {
				return false;
			}
		}
		return true;
	}

	/**
	 * gets the rented status of the book currently unused
	 * 
	 * @return
	 * @throws ConnectionException
	 * @throws TransactionExecutionException
	 */
	/*
	 * @SuppressWarnings("unchecked") public List<OrderItemInfo>
	 * getRentedOrders() throws SapeStoreException {
	 * LOGGER.debug("InventoryDao.getBookDetails method: START");
	 * List<OrderItemInfo> orderItemInfoList = null; orderItemInfoList =
	 * (List<OrderItemInfo>) hibernateTemplate
	 * .findByNamedQuery("OrderItemInfo.findByPurchaseType"); return
	 * orderItemInfoList; }
	 */

	/**
	 * update dispatch update the status of dispatched books unused at the
	 * moment
	 * 
	 * @param orgListDispatch
	 * @param orgListReturn
	 * @param newListDispatch
	 * @param newListReturn
	 * @param orderNums
	 * @return
	 */
	/*
	 * public List<Integer> updateDispatch(List<Boolean> orgListDispatch,
	 * List<Boolean> orgListReturn, List<Boolean> newListDispatch, List<Boolean>
	 * newListReturn, List<Integer> orderNums) {
	 * 
	 * List<Integer> ordersToBeDispatched = null; List<Integer> ordersReturned =
	 * null;
	 * 
	 * if (orderNums != null && orderNums.size() > 0) { ordersToBeDispatched =
	 * new ArrayList<Integer>(); ordersReturned = new ArrayList<Integer>(); for
	 * (int i = 0; i < orderNums.size(); i++) { if (orgListDispatch.get(i) ==
	 * false && newListDispatch.get(i) == true) {
	 * ordersToBeDispatched.add(orderNums.get(i)); if (newListReturn.get(i) ==
	 * true) { ordersReturned.add(orderNums.get(i)); } } } } return
	 * ordersToBeDispatched; }
	 */

	/**
	 * Set status of rented book to returned unused at the moment
	 * 
	 * @param orgListDispatch
	 * @param orgListReturn
	 * @param newListDispatch
	 * @param newListReturn
	 * @param orderNums
	 */
	/*
	 * public void updateReturn(List<Boolean> orgListDispatch, List<Boolean>
	 * orgListReturn, List<Boolean> newListDispatch, List<Boolean>
	 * newListReturn, List<Integer> orderNums) {
	 * 
	 * List<Integer> ordersReturned = null; if (orderNums != null &&
	 * orderNums.size() > 0) { ordersReturned = new ArrayList<Integer>(); for
	 * (int i = 0; i < orderNums.size(); i++) { if (orgListDispatch.get(i) ==
	 * true && newListReturn.get(i) == true) {
	 * ordersReturned.add(orderNums.get(i)); } } } Date date = new Date(); //
	 * String d = new SimpleDateFormat("dd-MMM-yy").format(date);
	 * 
	 * 
	 * Update return_status and actual_return_date for list of orders returned :
	 * ordersReturned
	 * 
	 * OrderItemInfo orderItemInfo = new OrderItemInfo(); for (Integer
	 * orderItemId : ordersReturned) { orderItemInfo =
	 * hibernateTemplate.get(OrderItemInfo.class, orderItemId);
	 * orderItemInfo.setReturnStatus("RETURNED");
	 * orderItemInfo.setActualReturnDate(date);
	 * hibernateTemplate.saveOrUpdate(orderItemInfo); } }
	 */

	public void confrimedOrder(String userId, List cartList, int total,Address address) {
		 int cityId = 0;
		 int stateId = 0;
		// TODO Auto-generated method stub
		Date createdDate = new Date();
		Date updatedDate = new Date();
		Date orderDate = new Date();
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setCreatedDate(createdDate);
		orderInfo.setIsActive("Y");
		orderInfo.setOrderStatus("NOT DISPATCHED");
		orderInfo.setPaymentMode("COD");
		orderInfo.setTotalPayment(total);
		orderInfo.setUpdatedDate(updatedDate);
		orderInfo.setUserId(userId);
		orderInfo.setOrderDate(orderDate);

         if(address!=null){
        	orderInfo.setAddressLine1(address.getAddressLine1());
     		orderInfo.setAddressLine2(address.getAddressLine2()); 
     		cityId=address.getCityId();
     	    stateId=address.getStateId();
         }
	   
	    String cityName=addressDao.getCityName(cityId);
	    String stateName=addressDao.getStateName(stateId);
	    orderInfo.setCityName(cityName);
	    orderInfo.setStateName(stateName);
	   // orderInfo.setPostalCode(address.getPostalCode());
		hibernateTemplate.save(orderInfo);
		orderItemInfo(cartList, userId, orderInfo);
	}

	public void orderItemInfo(List cartList, String userId, OrderInfo orderInfo) {

		Date createdDate = new Date();
		Date dispatchDate = new Date();
		Date updatedDate = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, 15);

		int orderId = 0;
		int totalItems;
		int itemNo;

		if (orderInfo != null) {
			orderId = orderInfo.getOrderId();
		}
		totalItems = cartList.size();
		for (itemNo = 0; itemNo < totalItems; itemNo++) {
			OrderItemInfo orderItem = new OrderItemInfo();

			CartItemVO cartItemVo = (CartItemVO) cartList.get(itemNo);

			// Decrement quantity of book in Book Table - Shirish
			List book = (List)hibernateTemplate.findByNamedQueryAndNamedParam("Book.findByIsbn", "isbn", cartItemVo.getIsbn());
			
			((Book)book.get(0)).setQuantity(((Book)book.get(0)).getQuantity() - cartItemVo.getQuantity());
			hibernateTemplate.save((Book)book.get(0));
			// 
			
			if (cartItemVo.getType().equalsIgnoreCase("PURCHASED")) {
				if (cartItemVo.getBookPrice() != null) {
					orderItem
							.setBookPrice(cartItemVo.getBookPrice().intValue());
					orderItem.setReturnStatus("NA");
				}
			}
			if (cartItemVo.getType().equalsIgnoreCase("RENTED")) {
				if (cartItemVo.getRentPrice() != null) {
					orderItem
							.setBookPrice(cartItemVo.getRentPrice().intValue());
					orderItem.setExpectedReturnDate(c.getTime());
					orderItem.setActualReturnDate(c.getTime());
					orderItem.setReturnStatus("NOT RETURNED");
				}
			}
			orderItem.setCreatedDate(createdDate);
			orderItem.setDispatchDate(dispatchDate);
			orderItem.setIsbn(cartItemVo.getIsbn());
			orderItem.setIsActive("Y");
			orderItem.setOrderId(orderId);
			orderItem.setOrderQuantity(cartItemVo.getQuantity());
			orderItem.setOrderStatus("NOT DISPATCHED");
			orderItem.setPaymentStatus("NOT RECEIVED");
			orderItem.setPurchaseType(cartItemVo.getType());
			orderItem.setUpdatedDate(updatedDate);
			hibernateTemplate.save(orderItem);
			CartItem cartDelItem = new CartItem(cartItemVo);
			hibernateTemplate.delete(cartDelItem);
		}

	}
}