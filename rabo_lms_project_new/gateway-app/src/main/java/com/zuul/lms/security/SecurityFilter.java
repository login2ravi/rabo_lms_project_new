package com.zuul.lms.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zuul.lms.exception.BusinessException;
import com.zuul.lms.exception.ErrorCode;
import com.zuul.lms.model.ErrorResponse;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SecurityFilter extends OncePerRequestFilter {
	
	
	static final String AUTH_HEADER_NOT_FOUND_MSG =  "Authorization header is invalid/not found";
	static final String AUTH_HEADER_INVALID_MSG =  "Authorization header is invalid";
	
	
    @Autowired
    Authorizer authorizer;

    @Autowired
    ObjectMapper objectMapper;

    @Override
	public void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
    	logger.info("SecurityFilter.doFilterInternal begins");

        if(httpServletRequest.getMethod().equals("OPTIONS")) {
        	filterChain.doFilter(httpServletRequest, httpServletResponse);
        }else {
            final String jwtToken = httpServletRequest.getHeader("Authorization");
        	try {
                if (jwtToken != null && jwtToken.startsWith("Bearer ")) {
                    Claims claims = authorizer.validateToken(jwtToken);

                    if (isTokenExpired(claims)) {
                        throw new BusinessException(ErrorCode.INVALID_TOKEN, AUTH_HEADER_INVALID_MSG);
                    	
                    }

                    httpServletRequest.setAttribute("claims", claims);
                    filterChain.doFilter(httpServletRequest, httpServletResponse);
                } else {
                	
                    throw new BusinessException(ErrorCode.INVALID_HEADER, AUTH_HEADER_NOT_FOUND_MSG);
                	
                }
            } catch (Exception e) {
            	
                logger.error(AUTH_HEADER_NOT_FOUND_MSG, e);
                	ErrorResponse errorResponse = new ErrorResponse(ErrorCode.INVALID_HEADER.toString(), AUTH_HEADER_NOT_FOUND_MSG);

                httpServletResponse.setContentType("application/json");
                httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                httpServletResponse.getWriter().write(objectMapper.writeValueAsString(errorResponse));
            }
	
        }
                
    }

    private boolean isTokenExpired(Claims claims) {
        Date expirationDate = claims.getExpiration();
        Date currentDate = new Date();
        return expirationDate.before(currentDate);
    }
}