package com.springboot.HotelBookingSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.HotelBookingSystem.dto.ExecutiveDto;
import com.springboot.HotelBookingSystem.enumerate.Role;
import com.springboot.HotelBookingSystem.exception.InvalidIdException;
import com.springboot.HotelBookingSystem.model.Executive;
import com.springboot.HotelBookingSystem.model.Hotel;
import com.springboot.HotelBookingSystem.model.User;
import com.springboot.HotelBookingSystem.service.ExecutiveService;
import com.springboot.HotelBookingSystem.service.UserService;



@RestController
@RequestMapping("/executive")
public class ExecutiveController {
	
	@Autowired
	private ExecutiveService executiveService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/add")
    public Executive insertExecutive(@RequestBody Executive executive) {
		
		User user = executive.getUser();
		String passwordPlain = user.getPassword();
		String encodedPassword = passwordEncoder.encode(passwordPlain);
		user.setPassword(encodedPassword);
		user.setRole(Role.EXECUTIVE);
		user = userService.insert(user);
		executive.setUser(user);
		return executiveService.insertExecutive(executive);
	}
	
	@PutMapping("/updateExecutive/{eid}")
	public ResponseEntity<?> updateExecutive(@PathVariable("eid") int eid, @RequestBody ExecutiveDto newExecutive) {
		try {
			
			// Getting the executive details by Id
			Executive oldExecutive = executiveService.getById(eid);
			
			//Checking the values in new object and setting to old object
			if(newExecutive.getName() != null)
				oldExecutive.setName(newExecutive.getName());
			if(newExecutive.getEmail() != null)
				oldExecutive.setEmail(newExecutive.getEmail());
			
			//Inserting the updated values to executive
			oldExecutive = executiveService.insertExecutive(oldExecutive);
			
			return ResponseEntity.ok().body(oldExecutive);

		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	} 
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteExecutive(@PathVariable("id") int id) {
		try {
			
			// Getting the executive details by Id
			Executive executive = executiveService.getById(id);
			
			// delete
			executiveService.deleteExecutive(executive);
			
			return ResponseEntity.ok().body("executive deleted successfully");

		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("/get/{eid}")
	public ResponseEntity<?> getById(@PathVariable("eid") int eid) {
//		fetch executive by id
		try {
			Executive executive = executiveService.getByExecutiveId(eid);
			return ResponseEntity.ok().body(executive);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	
	
	
}
