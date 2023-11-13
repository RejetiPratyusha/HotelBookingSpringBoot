package com.springboot.HotelBookingSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.HotelBookingSystem.enumerate.Role;
import com.springboot.HotelBookingSystem.model.Customer;
import com.springboot.HotelBookingSystem.model.User;
import com.springboot.HotelBookingSystem.service.CustomerService;
import com.springboot.HotelBookingSystem.service.UserService;

@RestController
@RequestMapping("/feelhome")
	public class CustomerController {
		@Autowired
		private CustomerService customerService;
		
		@Autowired
		private UserService userService;
		
		@Autowired
		private PasswordEncoder passwordEncoder;
		
		@PostMapping("/customer/add")
		public Customer insertCustomer(@RequestBody Customer customer) {
			/* step1 get user information*/
			User user = customer.getUser();
			/*Step2 get user password and attach to variable*/
			String plainPassword = user.getPassword();
			/*Step3 encode the user password*/
			String encodedPassword = passwordEncoder.encode(plainPassword);
			/*Step4 set encoded password to user*/
			user.setPassword(encodedPassword);
			/*Step5 set user role as HR*/
			user.setRole(Role.CUSTOMER);
			user = userService.insert(user);
			
			return customerService.insert(customer);
		}
	}

