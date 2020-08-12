/**
 * 
 */
package com.zuul.lms.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.zuul.lms.entity.User;
import com.zuul.lms.model.LoginRequest;
import com.zuul.lms.service.LoginService;





/**
 * @author ctsjava106
 *
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc

class LoginDetailsControllerTest {

	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	WebApplicationContext context;

	
	 @MockBean
	 private LoginService loginService;
	 
	 
	 @BeforeEach
	 public void setup() {
		 mockMvc = MockMvcBuilders.webAppContextSetup(context)
				 .apply(springSecurity())
				 .build();
	 }
	
	 @WithMockUser
	 @Test
	 void validateAdminUser() throws Exception{
		 
		 LoginRequest loginRequest = new LoginRequest();
		 loginRequest.setUserName("admin");
		 loginRequest.setPassword("admin123");
		 
		 		  
		  User user = User.builder().userName("admin").password("admin123").userRole("admin").build();
		  
		  String token = "Token123";

	        Mockito.when(loginService.loginUser(loginRequest)).thenReturn(token);
	        Mockito.when(loginService.getUserDetails(loginRequest)).thenReturn(user);
	        
	        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/login")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content("{\"userName\":\"admin\",\"password\":\"admin123\"}");
	        
	        mockMvc.perform(builder).andExpect(status().isOk())
	        .andExpect(jsonPath("$.username", is("admin")))
	        .andExpect(jsonPath("$.userrole", is("admin")))
	        .andExpect(jsonPath("$.token", is("Token123")));		
		 
	 }
	 
	 @WithMockUser
	 @Test
	 void validateTestUser() throws Exception{
		 
		 LoginRequest loginRequest = new LoginRequest();
		 loginRequest.setUserName("test");
		 loginRequest.setPassword("test123");
		 
		 		  
		  User user = User.builder().userName("test").password("test123").build();
		  
		  String token = "Token123";

	        Mockito.when(loginService.loginUser(loginRequest)).thenReturn(token);
	        Mockito.when(loginService.getUserDetails(loginRequest)).thenReturn(user);
	        
	        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/login")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content("{\"userName\":\"test\",\"password\":\"test123\"}");
	        
	        mockMvc.perform(builder).andExpect(status().isOk())
	        .andExpect(jsonPath("$.username", is("test")))
	        .andExpect(jsonPath("$.userrole").doesNotExist())
	        .andExpect(jsonPath("$.token", is("Token123")));		
		 
	 }

	 
	 @WithMockUser
	 @Test
	 void validateWithoutTokenUser() throws Exception{
		 
		 LoginRequest loginRequest = new LoginRequest();
		 loginRequest.setUserName("test");
		 loginRequest.setPassword("test123");
		 
		 		  
		  User user = User.builder().userName("test").password("test123").build();
		  
		  String token = null;

	        Mockito.when(loginService.loginUser(loginRequest)).thenReturn(token);
	        Mockito.when(loginService.getUserDetails(loginRequest)).thenReturn(user);
	        
	        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/login")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content("{\"userName\":\"test\",\"password\":\"test123\"}");
	        
	        mockMvc.perform(builder).andExpect(status().isOk())
	        
	        .andExpect(jsonPath("$.token").doesNotExist());		
		 
	 }


	
}
