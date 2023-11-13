package com.springboot.HotelBookingSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.HotelBookingSystem.exception.InvalidIdException;
import com.springboot.HotelBookingSystem.model.Executive;
import com.springboot.HotelBookingSystem.model.Hotel;
import com.springboot.HotelBookingSystem.model.Location;
import com.springboot.HotelBookingSystem.service.ExecutiveService;
import com.springboot.HotelBookingSystem.service.LocationService;

@RestController
@RequestMapping("/feelhome")
public class LocationController {

	@Autowired
	private LocationService locationService;

	@Autowired
	private ExecutiveService executiveService;

	

	@PostMapping("/location/add/{eid}")
	public ResponseEntity<?> addLocation(@PathVariable("eid") int eid,
			                             @RequestBody Location location) {
		try {
		Executive executive = executiveService.getById(eid);
		location.setExecutive(executive);

		location = locationService.insert(location);
		return ResponseEntity.ok().body(location);

	}
		catch(InvalidIdException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}
		

}
	
	
}
