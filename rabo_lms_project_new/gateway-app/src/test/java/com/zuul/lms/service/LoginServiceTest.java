/**
 * 
 */
package com.zuul.lms.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.zuul.lms.entity.User;
import com.zuul.lms.model.LoginRequest;
import com.zuul.lms.repo.LoginRepo;
import com.zuul.lms.security.Authorizer;
import com.zuul.lms.service.LoginService;





/**
 * @author ctsjava106
 *
 */

@ExtendWith(MockitoExtension.class)
class LoginServiceTest {
	
	@InjectMocks
	private LoginService loginService;
	
	@Mock
	private LoginRepo loginRepo;
	
	@Mock
	Authorizer authorizer;
	
	@Test
	void loginUser() {
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUserName("admin");
		loginRequest.setPassword("admin123");
		String expectedToken = "Token1";
		
		User user = new User();
		user.setPassword("admin123");
		user.setUserName("admin");
		user.setUserRole("admin");
		
		Mockito.when(loginRepo.findByUserName(loginRequest.getUserName())).thenReturn(user);
		Mockito.when(authorizer.generateToken(loginRequest.getUserName())).thenReturn(expectedToken);
		
		String token = loginService.loginUser(loginRequest);
		assertEquals(token, expectedToken);
		
	}
	
	@Test
	void loginUserNullPassword() {
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUserName("admin");
		loginRequest.setPassword(null);
		
		
		User user = new User();
		user.setPassword("admin123");
		user.setUserName("admin");
		user.setUserRole("admin");
		
		Mockito.when(loginRepo.findByUserName(loginRequest.getUserName())).thenReturn(user);
		
		String token = loginService.loginUser(loginRequest);
		assertEquals( null , token);
		
	}
	
	@Test
	void loginUserInvalidPassword() {
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUserName("admin");
		loginRequest.setPassword("test");
		
		
		User user = new User();
		user.setPassword("admin123");
		user.setUserName("admin");
		user.setUserRole("admin");
		
		Mockito.when(loginRepo.findByUserName(loginRequest.getUserName())).thenReturn(user);
		
		String token = loginService.loginUser(loginRequest);
		assertEquals(null, token);
		
	}
	
	
	@Test
	void getUserDetails() {
		
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUserName("admin");
		loginRequest.setPassword("admin123");
		
		User user = new User();
		user.setPassword("admin123");
		user.setUserName("admin");
		user.setUserRole("admin");
		
		Mockito.when(loginRepo.findByUserName(loginRequest.getUserName())).thenReturn(user);
		
		User actual = loginService.getUserDetails(loginRequest);
		
		assertEquals(user, actual);
		
		
	}
	
	
	@Test
	void getUserDetailsInvalidUserName() {
		
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUserName(null);
		loginRequest.setPassword("admin123");
		
		
		User actual = loginService.getUserDetails(loginRequest);
		
		assertEquals( null , actual);
		
		
	}
	
	

}
