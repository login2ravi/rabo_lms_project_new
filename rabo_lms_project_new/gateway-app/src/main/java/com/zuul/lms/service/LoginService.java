package com.zuul.lms.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zuul.lms.entity.User;
import com.zuul.lms.model.LoginRequest;
import com.zuul.lms.repo.LoginRepo;
import com.zuul.lms.security.Authorizer;



@Service
public class LoginService {
	
	Logger logger = LoggerFactory.getLogger(LoginService.class);
	
	@Autowired
	private LoginRepo loginRepo;
	
	@Autowired
	  Authorizer authorizer;
	
	public String loginUser(LoginRequest loginRequest) {
		
		logger.debug("username ={}",loginRequest.getUserName());
		logger.debug("password ={}",loginRequest.getPassword());
		User user = loginRepo.findByUserName(loginRequest.getUserName());
		
		
		if(user != null && user.getPassword().equals(loginRequest.getPassword())) {
			logger.info("Inside user ");	
			return authorizer.generateToken(loginRequest.getUserName());	
		}
		logger.info("Inside user NULL");
		return null;
	}
	
	
	public User getUserDetails(LoginRequest loginRequest) {
		
		User user = loginRepo.findByUserName(loginRequest.getUserName());
		
		if(user != null && user.getPassword().equals(loginRequest.getPassword())) { 
			return user;	
		}
		return null;
	}

}
