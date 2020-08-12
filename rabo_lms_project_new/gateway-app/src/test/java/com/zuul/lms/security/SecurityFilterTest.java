/**
 * 
 */
package com.zuul.lms.security;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zuul.lms.exception.ErrorCode;
import com.zuul.lms.model.ErrorResponse;
import com.zuul.lms.security.Authorizer;
import com.zuul.lms.security.SecurityFilter;

import io.jsonwebtoken.Claims;

/**
 * @author ctsjava106
 *
 */
@ExtendWith(MockitoExtension.class)
class SecurityFilterTest {
	
	@InjectMocks
	SecurityFilter securityFilter;
	
    @Mock
    Authorizer authorizer;

    @Mock
    ObjectMapper objectMapper;

    
    @Test
    void doFilterInternalForOptions() throws ServletException, IOException {
    	
    	HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
        	HttpServletResponse httpServletResponse = Mockito.mock(HttpServletResponse.class);
        
        FilterChain filterChain = Mockito.mock(FilterChain.class);
        
        Mockito.when(httpServletRequest.getMethod()).thenReturn("OPTIONS");
        
        securityFilter.doFilterInternal(httpServletRequest, httpServletResponse, filterChain);
        verify(filterChain,times(1)).doFilter(httpServletRequest, httpServletResponse);
    	
    }
    
    @Test
    void doFilterInternalForValidToken() throws ServletException, IOException {
    
    	HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
    	HttpServletResponse httpServletResponse = Mockito.mock(HttpServletResponse.class);
         FilterChain filterChain = Mockito.mock(FilterChain.class);
         Claims claims = Mockito.mock(Claims.class);
         
         
         Mockito.when(httpServletRequest.getMethod()).thenReturn("GET");
         
         String jwtToken = "Bearer jwtTocken";
         
       // Claims claims= getClims();
         Mockito.when(httpServletRequest.getHeader("Authorization")).thenReturn(jwtToken);
         Mockito.when(authorizer.validateToken(jwtToken)).thenReturn(claims);
         Mockito.when(claims.getExpiration()).thenReturn(new Date(System.currentTimeMillis() + 3600 * 1000));
         
         securityFilter.doFilterInternal(httpServletRequest, httpServletResponse, filterChain);
         
         verify(filterChain,times(1)).doFilter(httpServletRequest, httpServletResponse);
         
    	
    	
    }
    
    
    
    
    public void doFilterInternalForExpiredToken() throws ServletException, IOException {
    
    	HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
    	HttpServletResponse httpServletResponse = Mockito.mock(HttpServletResponse.class);
         FilterChain filterChain = Mockito.mock(FilterChain.class);
         Claims claims = Mockito.mock(Claims.class);
         
         
         //lenient().when(httpServletRequest).getMethod().thenReturn("GET");
         Mockito.when(httpServletRequest.getMethod()).thenReturn("GET");
         
         String jwtToken = "Bearer jwtTocken";
         PrintWriter pw = new PrintWriter("fname");
         
         ErrorResponse errorResponse = new ErrorResponse(ErrorCode.INVALID_HEADER.toString(), "Authorization header is invalid/not found");
         String errorResponseStr = "ErrorResponse";
         
       // Claims claims= getClims();
         lenient().when(httpServletRequest.getHeader("Authorization")).thenReturn(jwtToken);
         lenient().when(authorizer.validateToken(jwtToken)).thenReturn(claims);
         lenient().when(claims.getExpiration()).thenReturn(new Date(System.currentTimeMillis() - 3600 * 1000));
         Mockito.when(httpServletResponse.getWriter()).thenReturn(pw);
         
         
         //Mockito.when(objectMapper.writeValueAsString(any(ErrorResponse.class))).thenReturn(errorResponseStr);
        // Mockito.doNothing().when(objectMapper).writeValueAsString(any(ErrorResponse.class));
        
         Mockito.doNothing().when(pw).write(errorResponseStr);
        
         
         
         securityFilter.doFilterInternal(httpServletRequest, httpServletResponse, filterChain);
         
         verify(httpServletResponse,times(1)).getWriter();
         verify(filterChain,times(1)).doFilter(httpServletRequest, httpServletResponse);
         
    	
    	
    }
    
    
    private Claims getClims() {
    	
    	
    	Claims claims = new Claims() {
			
			@Override
			public Collection<Object> values() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int size() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public Object remove(Object key) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void putAll(Map<? extends String, ? extends Object> m) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public Object put(String key, Object value) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Set<String> keySet() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean isEmpty() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public Object get(Object key) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Set<Entry<String, Object>> entrySet() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean containsValue(Object value) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean containsKey(Object key) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void clear() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public Claims setSubject(String sub) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Claims setNotBefore(Date nbf) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Claims setIssuer(String iss) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Claims setIssuedAt(Date iat) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Claims setId(String jti) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Claims setExpiration(Date exp) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Claims setAudience(String aud) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getSubject() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Date getNotBefore() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getIssuer() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Date getIssuedAt() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getId() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Date getExpiration() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getAudience() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public <T> T get(String claimName, Class<T> requiredType) {
				// TODO Auto-generated method stub
				return null;
			}
		};
		
		claims.setExpiration(new Date());
		return claims;
    }
}
