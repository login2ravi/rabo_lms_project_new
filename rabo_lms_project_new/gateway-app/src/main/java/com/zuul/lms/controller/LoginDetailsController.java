package com.zuul.lms.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zuul.lms.entity.User;
import com.zuul.lms.model.LoginRequest;
import com.zuul.lms.service.LoginService;

import lombok.extern.slf4j.Slf4j;




@CrossOrigin("*")
@RestController
@Slf4j
public class LoginDetailsController {
			 
			 
	@Autowired
	LoginService loginService;
	
	@PostMapping(value = "/login")
	public Map<String,String> validateUser(@RequestBody LoginRequest loginRequest) {
		HttpHeaders headers = new HttpHeaders();
		
        log.info("ZUULInside the validate user");
        
        String token= loginService.loginUser(loginRequest);
        log.debug("Token ==={}",token);
        
        Map<String,String> resultMap = new HashMap<String,String>();
        if(token != null) {
        	User user = loginService.getUserDetails(loginRequest);
        	resultMap.put("username", loginRequest.getUserName());
    		resultMap.put("userrole", user.getUserRole());
    		resultMap.put("token", token);
    		headers.add("Access-Control-Expose-Headers", "Access-Control-Allow-Origin,Authorization");
    		headers.add("Authorization", "bearer " + token);
        }
		
        return resultMap;
    }
}
