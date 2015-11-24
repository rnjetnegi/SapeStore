package com.sapestore.controller.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

import com.sapestore.controller.HomePageController;

/**
 * The class <code>SapeStoreHomeControllerTest</code> contains tests for the
 * class {@link <code>SapeStoreHomeController</code>}
 *
 * @pattern JUnit Test Case
 *
 * @generatedBy CodePro at 7/12/14 11:25 AM
 *
 * @author kmedir
 *
 * @version $Revision$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-application-context.xml" })
@WebAppConfiguration
public class HomePageControllerTest {
	
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

	/**
	 * The object that is being tested.
	 *
	 * @see com.sapestore.controller.SapeStoreHomeController
	 */
	private HomePageController fixture = new HomePageController();

	/**
	 * Construct new test instance
	 *
	 * @param name the test name
	 */
	public HomePageControllerTest() {
	}

	/**
	 * Return the object that is being tested.
	 *
	 * @return the test fixture
	 *
	 * @see com.sapestore.controller.SapeStoreHomeController
	 */
	public HomePageController getFixture() {
		return fixture;
	}

	/**
	 * Set the object that is being tested.
	 *
	 * @param fixture the test fixture
	 */
	public void setFixture(HomePageController fixture) {
		this.fixture = fixture;
	}

	/**
	 * Run the String getBooksListByCat(int, String, boolean, ModelMap) method
	 * test
	 */
	@Test
	public void testGetBooksListByCat() {
		try {
			System.out.println("entered testGetBooksListByCat test");
			mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
			mockMvc.perform(get("/bookListByCat?categoryId=1&categoryName=Academic%20and%20Professional&checkMe=false"))
			.andExpect(status().isOk());
			System.out.println("exited testGetBooksListByCat test");
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	/**
	 * Run the String welcome(boolean, ModelMap) method test
	 */
	@Test
	public void testWelcome() {
		try {
			System.out.println("entered welcome test");
			mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
			mockMvc.perform(get("/welcome?checkMe=false"))
			.andExpect(status().isOk());
			System.out.println("exited welcome test");
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}

/*$CPS$ This comment was generated by CodePro. Do not edit it.
 * patternId = com.instantiations.assist.eclipse.pattern.testCasePattern
 * strategyId = com.instantiations.assist.eclipse.pattern.testCasePattern.junitTestCase
 * additionalTestNames = 
 * assertTrue = false
 * callTestMethod = true
 * createMain = false
 * createSetUp = false
 * createTearDown = false
 * createTestFixture = true
 * createTestStubs = false
 * methods = getBookList(),getBookService(),getBooksListByCat(I!QString;!Z!QModelMap;),getCategoryName(),getCatList(),isCheckMe(),setBookList(QList<QBook;>;),setBookService(QBookService;),setCategoryName(QString;),setCatList(QList<QBookCategory;>;),setCheckMe(Z),welcome(Z!QModelMap;)
 * package = com.sapestore.controller
 * package.sourceFolder = SapeStore/src/main/java
 * superclassType = junit.framework.TestCase
 * testCase = SapeStoreHomeControllerTest
 * testClassType = com.sapestore.controller.SapeStoreHomeController
 */