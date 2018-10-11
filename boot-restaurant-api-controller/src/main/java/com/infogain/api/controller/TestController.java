package com.infogain.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infogain.api.entity.Test;

@RestController
@RequestMapping("/test")
public class TestController {
	
	
	
	@GetMapping("/{name}")
	public String sayHello(@PathVariable String name) {
		return "Hello "+name+" ! ";
	}
	
	@PostMapping()
	public String sayHello(@RequestBody Test test) {
		return "Hello "+test.getName()+" !";
	}

}
