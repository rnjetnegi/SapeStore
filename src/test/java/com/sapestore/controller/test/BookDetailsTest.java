package com.sapestore.controller.test;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

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

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.service.BookService;

/*
* @Author Deepanshi
* This class is used to test BookDetailsController

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-application-context.xml" })
@WebAppConfiguration*/
public class BookDetailsTest {
	/*@Autowired
	private BookService bookServiceMock;
	@Autowired
	private MockHttpSession session;

                
                * logger initialization
                
                private final static SapeStoreLogger LOGGER = SapeStoreLogger.getLogger(BookDetailsTest.class.getName());
                
    private MockMvc mockMvc;
    
    
     * WebApplicationContext object initialization to build test for controller
     
    @Autowired
    private WebApplicationContext wac;
    
    
     * This is used to check bookDetails() function
     
                @Test
                public void bookDetails() throws SapeStoreException {
                               
                                try {
                                	
                                                LOGGER.info("entered testBeforeUpdating");
                                                mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
                                                final ResultActions RESULT=mockMvc.perform(get("/bookDetails?isbn=9988776688"));
                                                RESULT.andExpect(status().isOk());
                                                LOGGER.info("exited testBeforeUpdating");
                                               
                                } catch (Exception e) {
                                                e.printStackTrace();
                                }
                                
                }
                
                @Test
                
                * This is used to check commentPage() function
                
                public void commentPage() {
                                
                                try {
                                	           
                                                LOGGER.info("entered testAfterUpdating");
                                                mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
                                                final ResultActions RESULT=mockMvc.perform(post("/checkLogin"));
                                                RESULT.andExpect(status().isOk());
                                                LOGGER.info("exited testAfterUpdating");
                                } catch (Exception e) {
                                                e.printStackTrace();
                                }
                                
                }
                
                @Test
                /
                * This is used to check addComment() function
                 
                public void addComment() {
                                
                                try {
                                	            session.setAttribute("userLoginName", "chacha");
                                                LOGGER.info("entered testAfterUpdating");
                                                mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
                                                final ResultActions RESULT=mockMvc.perform(post("/comments").param("isbn", "9988776688").param("comment", "ABC").param("rating", "1").session(session));
                                                RESULT.andExpect(status().isOk());
                                                LOGGER.info("exited testAfterUpdating");
                                } catch (Exception e) {
                                                e.printStackTrace();
                                }
                                
                }
                
                @Test
                /
                * This is used to check bookReviews() function
                
               public void bookReviews() {
                                
                                try {
                                                LOGGER.info("entered testAfterUpdating");
                                                mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
                                                final ResultActions RESULT=mockMvc.perform(get("/bookReviews?isbn=9988776688&page=1"));
                                                RESULT.andExpect(status().isOk());
                                                LOGGER.info("exited testAfterUpdating");
                                } catch (Exception e) {
                                                e.printStackTrace();
                                }
                                
                }
*/
}
