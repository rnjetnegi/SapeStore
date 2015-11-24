//package com.sapestore.controller.test;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import com.sapestore.common.SapeStoreLogger;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mock.web.MockHttpSession;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:test-application-context.xml" })
//@WebAppConfiguration
//
///*Junit Test Class For the Page Controler class
// */
//public class PagesControllerTest {
//
//  private static final SapeStoreLogger LOGGER = SapeStoreLogger
//        .getLogger(TransactionControllerTest.class.getName());
//
//  private MockMvc mockMvc;
//
//  @Autowired
//  private WebApplicationContext wac;
//  @Autowired
//  MockHttpSession session;
//  /*Method for Testing ManagePages request mapping
//   */
//  @Test
// 
//  public void testManagePages() {
//    try {
//      mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
//      LOGGER.info("entered testForManagePages");
//      final ResultActions Result = mockMvc.perform(get("/managePages"));
//      Result.andExpect(status().isOk());
//      LOGGER.info("exited testForManagePages");
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//  }
//  /*Method for Testing ContactUs Request Mapping
//   */
//  @Test
//  public void testContactUsEditModelMap() {
//    try {
//      mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
//      LOGGER.info("entered testForContactUsForEdit");
//      final ResultActions Result = mockMvc.perform(get("/contactUsEdit"));
//      Result.andExpect(status().isOk());
//      LOGGER.info("exitesd testForContactUsForEdit");
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//  }
//  /*Method for Testing Policy Edit Request Mapping
//   */
//  @Test
//  public void testPolicyEdit() {
//    try {
//      mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
//      LOGGER.info("entered testForPolicyForEdit");
//      final ResultActions Result = mockMvc.perform(get("/policyEdit"));
//      Result.andExpect(status().isOk());
//      LOGGER.info("exited testForPolicyForEdit");
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//  }
//  /*Method for Testing PolicyCustomer Request Mapping
//   */
//  @Test
//  public void testPolicyCustomer() {
//    try {
//      mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
//      LOGGER.info("entered testForPolicyForCustomer");
//      final ResultActions Result = mockMvc.perform(get("/policyCustomer"));
//      Result.andExpect(status().isOk());
//      LOGGER.info("exited testForPolicyForCustomer");
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//  }
//  /*Method for Testing ContactUsCustomer Request Mapping
//   */
//  @Test
//  public void testContactUsCustomer() {
//    try {
//      mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
//      LOGGER.info("entered testForContactUsForCustomer");
//      final ResultActions Result = mockMvc.perform(get("/contactUsCustomer"));
//      Result.andExpect(status().isOk());
//      LOGGER.info("exited testForContactUsForCustomer");
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//  }
//}
