package com.springboot.HotelBookingSystem.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.HotelBookingSystem.model.Location;
import com.springboot.HotelBookingSystem.repository.LocationRepository;

@Service
public class LocationService {

	
	@Autowired
	private LocationRepository locationRepository;
	
	public Location insert(Location location) {
		
		return locationRepository.save(location);
	}

}
