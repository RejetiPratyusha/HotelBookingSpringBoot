package com.springboot.HotelBookingSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.HotelBookingSystem.model.customerRoom;
import com.springboot.HotelBookingSystem.repository.customerRoomRepository;

@Service
public class customerRoomService {
	@Autowired
	private customerRoomRepository customerRoomRepository;
	public customerRoom insert(customerRoom customerroom) {
		// TODO Auto-generated method stub
		return customerRoomRepository.save(customerroom);
	}

}
