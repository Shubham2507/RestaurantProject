package com.infogain.api.controller;



import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.infogain.api.config.TokenProvider;
import com.infogain.api.entity.security.Constants;
import com.infogain.api.entity.security.LoginUser;
import com.infogain.api.response.ResponseData;
import com.infogain.api.service.RedisService;




@CrossOrigin(origins = "*", maxAge = 3600)
@RestController

public class AuthenticationController {

	@Autowired
    private RedisTemplate<String,String> redisTemplate;
	
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

	@Autowired
	private RedisService redis;
  
/*public ResponseData getDept() {
		
		List<DepartmentsDto> dept = deptService.getAllDept();
		return new ResponseData("200", "List of Departments", dept);
		
	}*/
	
	 @RequestMapping(value = "/login", method = RequestMethod.POST)
	    public ResponseData register(@RequestBody LoginUser loginUser) throws AuthenticationException {
	    	ResponseData response=new ResponseData();
	    	try
	    	{
	    	 final Authentication authentication = authenticationManager.authenticate( new UsernamePasswordAuthenticationToken( loginUser.getUsername(),loginUser.getPassword()));
	    	/* UsernamePasswordAuthenticationToken authentication=(new UsernamePasswordAuthenticationToken( loginUser.getUsername(),loginUser.getPassword()));
	         User user1=user.findByUsername(loginUser.getUsername());
	    	 if (!user1.getPassword().equals(authentication.getCredentials())) {
	    		 
	    		 response.setCode("401");
	          	response.setMessage("Not Authorized");
	          	response.setResponse("");
	    		 throw new BadCredentialsException("Username / Password was not found");
	    		
	    	 }
	    	
	    	
	       
	     
	        
	        else {*/
	        	 SecurityContextHolder.getContext().setAuthentication(authentication);
	             final String token = jwtTokenUtil.generateToken(authentication);
	        redisTemplate.opsForValue().set(loginUser.getUsername(),token);
	        redisTemplate.expire(loginUser.getUsername(), Constants.ACCESS_TOKEN_VALIDITY_SECONDS, TimeUnit.SECONDS);
	        //return ResponseEntity.ok(new AuthToken(token));
	       // return new ResponseData("200", "Login Successfull", token);
	        //return ResponseEntity.ok(token);
	        response.setCode("200");
	    	response.setMessage("Login Successfull");
	    	response.setResponse(token);
	    	}
	    	catch(Exception e)
	    	{
	    		System.out.println(e);
	    		response.setCode("401");
	          	response.setMessage("Not Authorized");
	          	response.setResponse(loginUser.getUsername());
	    	}
	    
	        return response;
	        }
}
