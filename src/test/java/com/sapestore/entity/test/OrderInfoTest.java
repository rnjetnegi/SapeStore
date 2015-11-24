package com.sapestore.entity.test;

import static org.junit.Assert.assertEquals;

import com.sapestore.hibernate.entity.OrderInfo;

import org.junit.Test;

import java.sql.Date;



public class OrderInfoTest {

  OrderInfo orderInfo = new OrderInfo();
  
  @Test
public void testOrderItemInfoList() {
//List<OrderItemInfo> orderItemInfo = new ArrayList();
//orderItemInfo.add(1,new OrderItemInfo().setIsActive("Y"););
  }
  
  //This is used to check the orderId implementation
  @Test
 public void testOrderId() {
    Integer orderId = 1234;
    orderInfo.setOrderId(orderId);
    Integer actual = orderInfo.getOrderId();
    assertEquals(orderId, actual);
  }
  
  //This is used to check the userId implementation
  @Test
  public void testUserId() {
    String userId = "A123";
    orderInfo.setUserId(userId);
    String actual = orderInfo.getUserId();
    assertEquals(userId, actual);
  }

  //This is used to check the orderDate implementation
  @Test
  public void testOrderDate() {
    Date orderDate = new Date(23 / 10 / 2015);
    orderInfo.setOrderDate(orderDate);
    Date actual = (Date) orderInfo.getOrderDate();
    assertEquals(orderDate, actual);
  }

  //This is used to check the totalPayment implementation
  @Test
  public void testTotalPayment() {
    Integer totalPayment = 1600;
    orderInfo.setTotalPayment(totalPayment);
    Integer actual = orderInfo.getTotalPayment();
    assertEquals(totalPayment, actual);
  }

  //This is used to check the paymentMode implementation
  @Test
  public void testPaymentMode() {
    String paymentMode = "By Cash";
    orderInfo.setPaymentMode(paymentMode);
    String actual = orderInfo.getPaymentMode();
    assertEquals(paymentMode, actual);
  }

  //This is used to check the createdDate implementation
  @Test
  public void testCreatedDate() {
    Date createdDate = new Date(23 / 9 / 2015);
    orderInfo.setCreatedDate(createdDate);
    Date actual = (Date) orderInfo.getCreatedDate();
    assertEquals(createdDate, actual);
  }

  //This is used to check the updatedDate implementation
  @Test
  public void testUpdatedDate() {
    Date updatedDate = new Date(23 / 11 / 2015);
    orderInfo.setUpdatedDate(updatedDate);
    Date actual = (Date) orderInfo.getUpdatedDate();
    assertEquals(updatedDate, actual);
  }

  //This is used to check the isActive implementation
  @Test
  public void testIsActive() {
    String isActive = "Y";
    orderInfo.setIsActive(isActive);
    String actual = orderInfo.getIsActive();
    assertEquals(isActive, actual);
  }

  //This is used to check the orderStatus implementation
  @Test
  public void testOrderStatus() {
    String orderStatus = "In Transit";
    orderInfo.setOrderStatus(orderStatus);
    String actual = orderInfo.getOrderStatus();
    assertEquals(orderStatus, actual);
  }

}
