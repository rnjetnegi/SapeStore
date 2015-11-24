package com.sapestore.controller.test;


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






import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.vo.RegisterVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-application-context.xml" })
@WebAppConfiguration
public class RegisterControllerTest {
	
	public RegisterControllerTest()
	{
		
	}
	
	/*
	 * Logger initialisation
	 */
	private final static SapeStoreLogger LOGGER = SapeStoreLogger.getLogger(RegisterControllerTest.class.getName());
	
	/*
	 * To test spring controller
	 */
	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext wac;

	@Test
	public void testPreRegister() {
//		try {
//			LOGGER.info("entered testBeforeRegistration");
//			
//			mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
//			final ResultActions RESULT=mockMvc.perform(get("/preRegister"));
//			RESULT.andExpect(status().isOk());
//			LOGGER.info("exited testBeforeRegistration");
//			//System.out.println("exited testBeforeRegistration");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}	
		
	}

//	@Test
//	public void testAfterRegister() {
//		try {
//			LOGGER.info("entered testRegistration");
//
//			mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
//			RegisterVO register=new RegisterVO();
//			register.setAddressLine1("jkk");
//			register.setAddressLine2("kl");
//			register.setCity("Los Angeles");
//			register.setEmail("kkkll@gmail.com");
//			register.setFullName("kri");
//			register.setLoginName("rii");
//			register.setPhone("8989887878");
//			register.setMobileNumber("9090909090");
//			register.setState("indiana");
//			register.setPostalCode("12345");
//			register.setPassword("lklkkllk");
//			
//			final ResultActions RESULT=mockMvc.perform(post("/register").sessionAttr("confirmUser", register));
//			RESULT.andExpect(status().isOk());
//			
//			LOGGER.info("exited Registration");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//	}
	
//	@Test
//    public void testCheckRegistration() {
//                    try {
//                                    LOGGER.info("entered testBeforeRegistration");
//                                    
//                                    mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
//                                    final ResultActions RESULT=mockMvc.perform(get("/checkRegistrationDetails"));
//                                    RESULT.andExpect(status().isOk());
//                                    LOGGER.info("exited testBeforeRegistration");
//                                    //System.out.println("exited testBeforeRegistration");
//                    } catch (Exception e) {
//                                    e.printStackTrace();
//                    }              
//                    
//    }
//    
//    @Test
//    public void testValidateRegistration() {
//                    try {
//                                    LOGGER.info("entered testRegistration");
//
//                                    mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
//                                    final ResultActions RESULT=mockMvc.perform(post("/confirmRegistrationDetails"));
//                                    RESULT.andExpect(status().isOk());
//                                    
//                                    LOGGER.info("exited Registration");
//                    } catch (Exception e) {
//                                    e.printStackTrace();
//                    }
//                    
//    }


}
