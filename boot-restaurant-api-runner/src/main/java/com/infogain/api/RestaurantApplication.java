package com.infogain.api;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestaurantApplication {

	public static void main(String[] args) {
		
		BasicConfigurator.configure();
		
		final Logger logger = LoggerFactory.getLogger(RestaurantApplication.class);
		logger.info("Restaurant Application Started!!!!!!!!!!");
	    logger.warn("this is a warn message");
	      logger.error("this is a error message");
		   
	  
	      
	      
	      
		SpringApplication.run(RestaurantApplication.class, args);
		
		
	}
}
