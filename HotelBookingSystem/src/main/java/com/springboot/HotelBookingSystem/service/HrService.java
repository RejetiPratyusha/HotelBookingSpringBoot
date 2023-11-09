package com.springboot.HotelBookingSystem.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.HotelBookingSystem.model.Executive;
import com.springboot.HotelBookingSystem.repository.HrRepository;


@Service
public class HrService {
	
	@Autowired
	private HrRepository hrRepository;

	public Executive insert(Executive executive) {
		// TODO Auto-generated method stub
		return hrRepository.save(executive);
	}

	public Executive getById(int eid) {
		Optional<Executive> optional = hrRepository.findById(eid);
				return optional.get();
	}

}
