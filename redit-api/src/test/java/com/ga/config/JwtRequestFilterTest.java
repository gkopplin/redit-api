package com.ga.config;

import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;

import com.ga.service.UserServiceImpl;
import com.ga.util.JwtUtil;
import com.ga.util.SecurityContextUtil;

@RunWith(MockitoJUnitRunner.class)
public class JwtRequestFilterTest {
	@InjectMocks
	JwtRequestFilter jwtRequestFilter;
	
	@Mock
	HttpServletRequest request;
	
	@Mock
	HttpServletResponse response;
	
	@Mock
	FilterChain chain;
	
	@Mock
	JwtUtil jwtUtil;
	
	@Mock
	SecurityContext ctx;
	
	@Mock
	SecurityContextUtil securityContextUtil;
	
	@Mock
	UserServiceImpl userService;
	
	@Mock
	Authentication auth;
	
	@InjectMocks
	UserDetails userDetails = new org.springframework.security.core.userdetails.User("name3", "pass",
            true, true, true, true, new ArrayList<GrantedAuthority>());		
	
	@Test
	public void doFilterInternal_FilterRequest_Success() throws ServletException, IOException {
		String token = "token";
		when(request.getHeader(any())).thenReturn("Bearer " + token);
		when(jwtUtil.getUsernameFromToken(any())).thenReturn("name3");
		when(securityContextUtil.getAuth()).thenReturn(auth);
        
		try {
			jwtRequestFilter.doFilterInternal(request, response, chain);			
		} catch(Exception e) {
			fail("Should not have thrown exception");
		} 
	}
}
