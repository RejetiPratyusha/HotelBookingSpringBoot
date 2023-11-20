package com.springboot.HotelBookingSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.HotelBookingSystem.exception.InvalidIdException;
import com.springboot.HotelBookingSystem.model.Customer;
import com.springboot.HotelBookingSystem.model.CustomerFamily;
import com.springboot.HotelBookingSystem.service.CustomerService;
import com.springboot.HotelBookingSystem.service.CustomerFamilyService;

@RestController
@RequestMapping("/feelhome")
public class CustomerFamilyController {
	

		@Autowired
		private CustomerFamilyService customerFamilyService;
		
		@Autowired
		private CustomerService customerService;
		
		@PostMapping("/family/add/{cid}")
		public ResponseEntity<?> insertFamily( @PathVariable("cid") int cid,
									@RequestBody CustomerFamily family) {
			
			try {
				/*fetch customer details by ID*/
				Customer customer = customerService.getById(cid);
				/*set customer ID to family*/
				family.setCustomer(customer);
				/*insert customer family into db*/
				family = customerFamilyService.insert(family);
				return ResponseEntity.ok().body(family);
				
			} catch (InvalidIdException e) {
				// TODO Auto-generated catch block
				return ResponseEntity.badRequest().body(e.getMessage());
			}
			
			
		}
	}

