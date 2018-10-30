package com.infogain.api.controller;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.infogain.api.entity.User;
import com.infogain.api.entity.security.LoginUser;

public class AuthenticationControllerTest {
	@Autowired
	private AuthenticationController authenticationController;
	
	@MockBean
	  private AuthenticationManager authenticationManager;
	

	@Test
	public void testLoginSuccess() throws AuthenticationException {
		
LoginUser user= new LoginUser();
		user.setUsername("admin");
		user.setPassword("admin");
		Authentication authentication=authenticationManager.
				authenticate( new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));


		assertEquals("admin", user.getUsername());
		assertEquals("admin", user.getPassword());
		}

}
