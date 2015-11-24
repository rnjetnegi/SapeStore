package com.sapestore.controller.test;

import static org.junit.Assert.*;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.controller.RegistrationController;
import com.sapestore.dao.AccountDao;

/*
 * @Author - kkmangla
 * This is used to test the com.sapient.controller.RegistrationController
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-application-context.xml" })
@WebAppConfiguration
public class RegistrationControllerTest {
	
	/*
	 * Logger initialisation
	 */
	private final static SapeStoreLogger LOGGER = SapeStoreLogger.getLogger(RegistrationControllerTest.class.getName());
	
	/*
	 * To test spring controller
	 */
	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext wac;
	
	private RegistrationController registerTest=new RegistrationController();
	
	/*
	 * To test the before registration or clicking on register  
	 */
	@Test
	public void testShowRegistrationPage() {
		try {
			LOGGER.info("entered testBeforeRegistration");
			//System.out.println("entered testBeforeRegistration");
			mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
			mockMvc.perform(get("/register"))
			.andExpect(status().isOk());
			LOGGER.info("exited testBeforeRegistration");
			//System.out.println("exited testBeforeRegistration");
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}
	/*
	 * To test whether form action is working or not
	 */
	@Test
	public void testRegisterUserAccount() {
		try {
			LOGGER.info("entered testRegistration");
			
			mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
			mockMvc.perform(post("/registerPage"))
			.andExpect(status().isOk());
			LOGGER.info("exited Registration");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
