package com.springboot.HotelBookingSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.HotelBookingSystem.model.CustomerFamily;
import com.springboot.HotelBookingSystem.repository.CustomerFamilyRepository;

@Service
public class CustomerFamilyService {
	@Autowired
	private CustomerFamilyRepository customerFamilyRepository;
	
	public CustomerFamily insert(CustomerFamily family) {
		// TODO Auto-generated method stub
		return customerFamilyRepository.save(family);
	}

}
