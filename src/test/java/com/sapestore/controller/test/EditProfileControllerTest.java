package com.sapestore.controller.test;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

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

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.vo.RegisterVO;

/*
 * @Author kmangl
 * This class<EditProfileControllerTest> is used to test the EditProfileController
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-application-context.xml" })
@WebAppConfiguration
public class EditProfileControllerTest {
	
	/*
	 * logger initialization
	 */
	private final static SapeStoreLogger LOGGER = SapeStoreLogger.getLogger(RegisterControllerTest.class.getName());
	
    private MockMvc mockMvc;
    
    /*
     * WebApplicationContext object initialization to build test for controller
     */
    @Autowired
    private WebApplicationContext wac;
    
    /*
     * This is used to check preRegister() function
     */
	@Test
	public void testPreRegister() {
		
		try {
			LOGGER.info("entered testBeforeUpdating");
			mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
			final ResultActions RESULT=mockMvc.perform(post("/preEditProfile"));
			RESULT.andExpect(status().isOk());
			LOGGER.info("exited testBeforeUpdating");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//@Test
	/*
	 * This is used to check afterRegister() function
	 */
//	public void testAfterRegister() {
//		
//		try {
//			LOGGER.info("entered testAfterUpdating");
//			mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
//			
//			RegisterVO register=new RegisterVO();
//			register.setAddressLine1("jkk");
//			register.setAddressLine2("kl");
//			register.setCity("Los Angeles");
//			register.setEmail("kkkll@gmail.com");
//			register.setFullName("kri");
//			register.setLoginName("Charul");
//			register.setPhone("8989887878");
//			register.setMobileNumber("9090909090");
//			register.setState("indiana");
//			register.setPostalCode("12345");
//			register.setPassword("lklkkllk");
//			
//			final ResultActions RESULT=mockMvc.perform(post("/editProfile").sessionAttr("userLoginName", "Charul").("registerUser", register));
//			RESULT.andExpect(status().isOk());
//			LOGGER.info("exited testAfterUpdating");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//	}

}
