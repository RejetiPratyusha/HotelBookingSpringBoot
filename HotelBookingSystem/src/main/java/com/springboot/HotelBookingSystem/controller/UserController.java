package com.springboot.HotelBookingSystem.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.HotelBookingSystem.model.User;
import com.springboot.HotelBookingSystem.service.UserService;



@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private Logger logger;
	
	@GetMapping("/user/login")
	public User login(Principal principal) {
		String username = principal.getName();
		User user = (User)userService.loadUserByUsername(username);
		logger.info("User Succesfully Logged");
		return user;
	}
	
//	@PostMapping("/user/add")
//	public User addUser(@RequestBody User user) {
//		user = userService.addUser(user);
//		return user;
//	}
	
	
}
