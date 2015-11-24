package com.sapestore.controller.test;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.sapestore.common.SapeStoreLogger;


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




/*Junit class for the Email Controller class
 * */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-application-context.xml" })
@WebAppConfiguration
public class EmailControllerTest {
  private static final SapeStoreLogger LOGGER = SapeStoreLogger
             .getLogger(TransactionControllerTest.class.getName());

  private MockMvc mockMvc;

  @Autowired
  private WebApplicationContext wac;
  @Autowired
  MockHttpSession session;
  /*Method to test request mapping send email
  */
  @Test
  public void testSendEmail() {
    /*try {
      mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
      LOGGER.info("entered testForDispatchSlip");
      final ResultActions Result = mockMvc.perform(get("/sendEmail?emailIds=admin@sapient.com"));
      Result.andExpect(status().isOk());
      LOGGER.info("exited testForDispatchSlip");
    } catch (Exception e) {
      e.printStackTrace();
    }*/
  }
  /*Method to test request mapping rentedSendEmail
  */
  @Test
    public void testRentedSendEmail() {
    /*try {
      mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
      LOGGER.info("entered testForDispatchSlip");
      final ResultActions Result = mockMvc.perform(
             get("/sendRentedEmail?emailIds=admin@sapient.com"));
      Result.andExpect(status().isOk());
      LOGGER.info("exited testForDispatchSlip");
    } catch (Exception e) {
      e.printStackTrace();
    }*/
  }

}
