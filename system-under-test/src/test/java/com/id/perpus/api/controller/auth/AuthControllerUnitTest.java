package com.id.perpus.api.controller.auth;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

//@WebAppConfiguration
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {PerpustakaanWebAppApplication.class})
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations="classpath:application.properties")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AuthControllerUnitTest {
	
	@Autowired
	private AuthController authController;
	

	@Test
	public void validateUser_Test_Positive() {
		String result =  authController.index();
		assertNotNull(result);
		assertEquals(result,"app.katalog");
	}

}
