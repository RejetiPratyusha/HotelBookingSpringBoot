package com.springboot.HotelBookingSystem.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.HotelBookingSystem.model.Executive;
import com.springboot.HotelBookingSystem.model.HotelAdmin;
import com.springboot.HotelBookingSystem.model.User;
import com.springboot.HotelBookingSystem.repository.ExecutiveRepository;

@Service
public class ExecutiveService {

	@Autowired
	private ExecutiveRepository executiveRepository;

	public HotelAdmin insert(HotelAdmin hotelAdmin) {
		
		return executiveRepository.save(hotelAdmin);
	}

	
	
	
	

}
