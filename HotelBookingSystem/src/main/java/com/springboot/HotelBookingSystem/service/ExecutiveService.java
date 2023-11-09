package com.springboot.HotelBookingSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.HotelBookingSystem.model.Executive;
import com.springboot.HotelBookingSystem.repository.ExecutiveRepository;



@Service
public class ExecutiveService {
	
	
	@Autowired
	private ExecutiveRepository executiveRepository;

	public Executive insertExecutive(Executive executive) {
		// TODO Auto-generated method stub
		return executiveRepository.save(executive);
	}

	public List<Executive> getAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return executiveRepository.findAll(pageable).getContent();
	}

	

	public Executive getById(int eid) {
		Optional<Executive> optional = executiveRepository.findById(eid);
		return optional.get();
	}

}
