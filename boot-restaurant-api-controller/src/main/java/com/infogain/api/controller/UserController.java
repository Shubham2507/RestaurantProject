package com.infogain.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.infogain.api.dto.UserDto;
import com.infogain.api.entity.User;
import com.infogain.api.response.ResponseData;
import com.infogain.api.service.UserService;
import com.infogain.api.service.UserServiceImpl;





@CrossOrigin(origins = "*", maxAge = 3600)
@RestController("signUpController")

public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ResponseData saveUser(@RequestBody UserDto user,HttpServletResponse httpResponse) {
		String name= user.getUsername();;
UserServiceImpl impl= new UserServiceImpl();
try {
		
		
			user.setUsername(name);
			System.out.println("username ="+user.getUsername());
			String fname=user.getFirstname();
			String lname=user.getLastname();
			
			String address=user.getAddress();
		
			httpResponse.setStatus(HttpServletResponse.SC_OK);
			return new ResponseData("200", "Sign-Up Successfull", userService.save(user));
}
catch(Exception e)
{
	 httpResponse.setStatus(HttpServletResponse.SC_CONFLICT);
	 ResponseData response= new ResponseData();
	 response.setCode("409");
 	response.setMessage("Sign up not Successfull");
 	response.setResponse(name);
	return response;

}

		}
}