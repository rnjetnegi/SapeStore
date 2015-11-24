package com.sapestore.controller.test;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.controller.OrderController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-application-context.xml" })
@WebAppConfiguration

/*Junit Test Class for Order Controller Class*/
public class OrderControllerTest {
  private static final SapeStoreLogger LOGGER = SapeStoreLogger
      .getLogger(TransactionControllerTest.class.getName());

  private MockMvc mockMvc;

  @Autowired
  private WebApplicationContext wac;
  @Autowired
  MockHttpSession session;

  OrderController orderController = new OrderController();
  /*Method for testing the DispatchSlip Request mapping
  */
  @Test
  public void testDispatchSlipBeans() {
    /*List dispatchSlips = new ArrayList();
    dispatchSlips.add("Admin");
    dispatchSlips.add("home");
    orderController.setDispatchSlipBeans(dispatchSlips);
    List expected = dispatchSlips;
    List actual = orderController.getDispatchSlipBeans();
    assertEquals(expected,actual);*/
  }
  /*Method for testing the ManageOrders Request mapping
   */
  @Test
  public void testManageOrders() {
    /*try {
      mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
      LOGGER.info("entered testForManageOrders");
      final ResultActions Result = mockMvc.perform(get("/manageOrders"));
      Result.andExpect(status().isOk());
      LOGGER.info("exited testForManageOrders");
    } catch (Exception e) {
      e.printStackTrace();
    }*/
  }
  /*Method for testing the DispatchSlip Request mapping
   */
  @Test
  public void testDispatchSlip() {
   /* try {
      mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
      LOGGER.info("entered testForDispatchSlip");
      final ResultActions Result = mockMvc.perform(post("/dispatchSlip?dispatchList=356"));
      Result.andExpect(status().isOk());
      LOGGER.info("exited testForDispatchSlip");
    } catch (Exception e) {
      e.printStackTrace();
    }*/
  }
  /*Method for testing the PaymentSlip Request mapping
   */
  @Test
  public void testPaymentSlip() {
    /*try {
      mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
      LOGGER.info("entered testForPaymentSlip");
      final ResultActions Result = mockMvc.perform(post("/paymentSlip?paymentId=356"));
      Result.andExpect(status().isOk());
      LOGGER.info("exited testForPaymentSlip");
    } catch (Exception e) {
      e.printStackTrace();
    }*/
  }
}
