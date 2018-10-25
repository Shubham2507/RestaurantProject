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
		
		
		
		/*if(name.matches(user.getUsername()))
		{
			return new ResponseData("403", "Username already exists!!", name);
		}
		else {*/
			user.setUsername(name);
			System.out.println("username ="+user.getUsername());
			String fname=user.getFirstname();
			String lname=user.getLastname();
			/*if(fname.matches(user.getFirstname())&&lname.matches(user.getLastname()))
			{
				return new ResponseData("403", "User is already Registered!!", name);
			}*/
			String address=user.getAddress();
			/*return userService.save(user);*/
			httpResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
			return new ResponseData("200", "Sign-Up Successfull", userService.save(user));
}
catch(Exception e)
{
	 httpResponse.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
	return new ResponseData("401", "Sign-Up Not Successfull", name);
}

		}
}