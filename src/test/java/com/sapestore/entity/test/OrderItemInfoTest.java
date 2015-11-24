package com.sapestore.entity.test;

import static org.junit.Assert.assertEquals;

import com.sapestore.hibernate.entity.OrderItemInfo;

import org.junit.Test;

import java.sql.Date;

public class OrderItemInfoTest {

  OrderItemInfo orderItemInfo = new OrderItemInfo();

  @Test
  public void testSetBook() {

  }

  //This is used to check the orderId implementation
  @Test
  public void testOrderId() {
    Integer orderId = 1234;
    orderItemInfo.setOrderId(orderId);
    Integer actual = orderItemInfo.getOrderId();
    assertEquals(orderId, actual);
  }

  //This is used to check the orderItemId implementation
  @Test
  public void testOrderItemId() {
    Integer orderItemId = 123;
    orderItemInfo.setOrderItemId(orderItemId);
    Integer actual = orderItemInfo.getOrderItemId();
    assertEquals(orderItemId, actual);
  }

  //This is used to check the Isbn implementation
  @Test
  public void testIsbn() {
    String isbn = "1234234234";
    orderItemInfo.setIsbn(isbn);
    String actual = orderItemInfo.getIsbn();
    assertEquals(isbn, actual);
  }

  //This is used to check the BookPrice implementation
  @Test
  public void testBookPrice() {
    Integer bookPrice = 500;
    orderItemInfo.setBookPrice(bookPrice);
    Integer actual = orderItemInfo.getBookPrice();
    assertEquals(bookPrice, actual);
  }

  //This is used to check the OrderQuantity implementation
  @Test
  public void testOrderQuantity() {
    Integer orderQuantity = 500;
    orderItemInfo.setOrderQuantity(orderQuantity);
    Integer actual = orderItemInfo.getOrderQuantity();
    assertEquals(orderQuantity, actual);
  }

  //This is used to check the CreatedDate implementation
  @Test
  public void testCreatedDate() {
    Date createdDate = new Date(23 / 10 / 2015);
    orderItemInfo.setCreatedDate(createdDate);
    Date actual = (Date) orderItemInfo.getCreatedDate();
    assertEquals(createdDate, actual);
  }

  //This is used to check the UpdatedDate implementation
  @Test
  public void testUpdatedDate() {
    Date updatedDate = new Date(23 / 10 / 2015);
    orderItemInfo.setUpdatedDate(updatedDate);
    Date actual = (Date) orderItemInfo.getUpdatedDate();
    assertEquals(updatedDate, actual);
  }

  //This is used to check the isActive implementation
  @Test
  public void testIsActive() {
    String isActive = "Y";
    orderItemInfo.setIsActive(isActive);
    String actual = orderItemInfo.getIsActive();
    assertEquals(isActive, actual);
  }

  //This is used to check the ExpectedReturnDate implementation
  @Test
  public void testExpectedReturnDate() {
    Date expectedReturnDate = new Date(23 / 10 / 2015);
    orderItemInfo.setExpectedReturnDate(expectedReturnDate);
    Date actual = (Date) orderItemInfo.getExpectedReturnDate();
    assertEquals(expectedReturnDate, actual);
  }

  //This is used to check the ActualReturnDate implementation
  @Test
  public void testActualReturnDate() {
    Date actualReturnDate = new Date(23 / 10 / 2015);
    orderItemInfo.setActualReturnDate(actualReturnDate);
    Date actual = (Date) orderItemInfo.getActualReturnDate();
    assertEquals(actualReturnDate, actual);
  }

  //This is used to check the PurchaseType implementation
  @Test
  public void testPurchaseType() {
    String purchaseType = "Purchased";
    orderItemInfo.setPurchaseType(purchaseType);
    String actual = orderItemInfo.getPurchaseType();
    assertEquals(purchaseType, actual);
  }

  //This is used to check the ReturnStatus implementation
  @Test
  public void testReturnStatus() {
    String returnStatus = "Returned";
    orderItemInfo.setReturnStatus(returnStatus);
    String actual = orderItemInfo.getReturnStatus();
    assertEquals(returnStatus, actual);
  }

  //This is used to check the OrderStatus implementation
  @Test
  public void testOrderStatus() {
    String orderStatus = "Dispatched";
    orderItemInfo.setOrderStatus(orderStatus);
    String actual = orderItemInfo.getOrderStatus();
    assertEquals(orderStatus, actual);
  }

  //This is used to check the DispatchDate implementation
  @Test
  public void testDispatchDate() {
    Date dispatchDate = new Date(23 / 10 / 2015);
    orderItemInfo.setDispatchDate(dispatchDate);;
    Date actual = (Date) orderItemInfo.getDispatchDate();
    assertEquals(dispatchDate, actual);
  }

  //This is used to check the PaymentStatus implementation
  @Test
  public void testPaymentStatus() {
    String paymentStatus = "Received";
    orderItemInfo.setPaymentStatus(paymentStatus);
    String actual = orderItemInfo.getPaymentStatus();
    assertEquals(paymentStatus, actual);
  }

}
