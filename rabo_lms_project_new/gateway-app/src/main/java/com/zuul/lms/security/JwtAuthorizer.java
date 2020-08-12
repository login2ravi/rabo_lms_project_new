package com.zuul.lms.security;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.zuul.lms.exception.BusinessException;
import com.zuul.lms.exception.ErrorCode;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



@Component
public class JwtAuthorizer implements Authorizer{

	

	
	@Override
	public Claims  validateToken(String jwtToken) {
		try {
            String token = jwtToken.replace("Bearer ", "");
            return Jwts.parser().setSigningKey("rabo").parseClaimsJws(token).getBody();
            
        } catch (Exception ex) {
        	 throw new BusinessException (ErrorCode.INVALID_HEADER, "Invalid Authorization header", ex);
        }
	}

	@Override
	public String generateToken(String userName) {
		 return Jwts.builder().setSubject(userName)
	                .claim("roles", "user")
	                .setIssuedAt(new Date())
	                .signWith(SignatureAlgorithm.HS256, "rabo")
	                .setExpiration(new Date(System.currentTimeMillis() + 1200 * 1000))
	                .compact();
	        
	}

	
	
}
