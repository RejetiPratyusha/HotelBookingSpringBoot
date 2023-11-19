package com.springboot.HotelBookingSystem.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.HotelBookingSystem.model.User;
import com.springboot.HotelBookingSystem.service.UserService;



@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/user/login")
	public User login(Principal principal) {
		String username = principal.getName();
		User user = (User)userService.loadUserByUsername(username);
		return user;
	}
	
//	@PostMapping("/user/add")
//	public User addUser(@RequestBody User user) {
//		user = userService.addUser(user);
//		return user;
//	}
	
	
}
