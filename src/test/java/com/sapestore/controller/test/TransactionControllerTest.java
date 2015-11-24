/*
 * 
 */

package com.sapestore.controller.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.sapestore.common.SapeStoreLogger;
import org.junit.Test;
import org.junit.runner.RunWith;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;


import org.springframework.test.web.servlet.ResultActions;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import org.springframework.web.context.WebApplicationContext;





@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-application-context.xml" })
@WebAppConfiguration
public class TransactionControllerTest {

  private final static SapeStoreLogger LOGGER = SapeStoreLogger
      .getLogger(TransactionControllerTest.class.getName());
 /**
 * The class <code>TransactionControllerTest</code> contains tests for the class
 * {@link <code>TransactionController</code>}
 *
 * @pattern JUnit Test Case
 *
 */
  private MockMvc mockMvc;

 /**
 * WebApplicationContext object initialization to build test for controller
 */
  
  @Autowired
  private WebApplicationContext wac;

  //This is used to check the getPurchasedBookDetails function
  
  @Test
  public void testGetPurchasedBookDetails() {
    /*try {
      LOGGER.info("entered testForBooksPurchased");
      mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
      final ResultActions Result = mockMvc.perform(get("/bookspurchased"));
      Result.andExpect(status().isOk());
      LOGGER.info("exited testForBooksPurchased");
    } catch (Exception e) {
      e.printStackTrace();
    }*/
  }

  //This is used to check the getRentedBookDetails function
  
  @Test
  public void testGetRentedBookDetails() {
    /*try {
      LOGGER.info("entered testForBooksRented");
      mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
      final ResultActions Result = mockMvc.perform(get("/booksrented"));
      Result.andExpect(status().isOk());
      LOGGER.info("exited testForBooksRented");
    } catch (Exception e) {
      e.printStackTrace();
    }*/
  }

}
