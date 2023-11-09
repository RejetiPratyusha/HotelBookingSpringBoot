package com.springboot.HotelBookingSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.HotelBookingSystem.enumerate.Role;
import com.springboot.HotelBookingSystem.model.Executive;
import com.springboot.HotelBookingSystem.model.User;
import com.springboot.HotelBookingSystem.service.HrService;
import com.springboot.HotelBookingSystem.service.UserService;


@RestController
@RequestMapping("/feelhome")
public class HrController {
	@Autowired
	private HrService hrService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@PostMapping("/add/executive")
	public Executive insertExecutive(@RequestBody Executive executive) {
		User user = executive.getUser();
		String passwordPlain = user.getPassword();
		String encodedPassword = passwordEncoder.encode(passwordPlain);
		user.setPassword(encodedPassword);
		user.setRole(Role.EXECUTIVE);
		user = userService.insert(user);
		executive.setUser(user);
		
		
		return hrService.insert(executive);
	} 
}
