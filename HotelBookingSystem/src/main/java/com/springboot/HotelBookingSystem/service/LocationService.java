package com.springboot.HotelBookingSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.HotelBookingSystem.exception.InvalidIdException;
import com.springboot.HotelBookingSystem.model.Hotel;
import com.springboot.HotelBookingSystem.model.Location;
import com.springboot.HotelBookingSystem.repository.LocationRepository;

@Service
public class LocationService {

	@Autowired
	private LocationRepository locationRepository;

	public Location insert(Location location) {

		return locationRepository.save(location);
	}

	public Location getLocationById(int lid) throws InvalidIdException {
		Optional<Location> optional = locationRepository.findById(lid);
		if (!optional.isPresent())
			throw new InvalidIdException("Location id invalid");
		return optional.get();
	}

	public Location getOne(int lid) throws InvalidIdException {
		Optional<Location> optional = locationRepository.findById(lid);
		if (!optional.isPresent()) {
			throw new InvalidIdException("executive id invalid");
		}
		return optional.get();
	}

//	public List<Hotel> getAllByExecutive(int eid) {
//		// TODO Auto-generated method stub
//		return locationRepository.findByLocationId(eid);
//	}
}
