package com.springboot.HotelBookingSystem.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.springboot.HotelBookingSystem.exception.InvalidIdException;
import com.springboot.HotelBookingSystem.model.Room;
import com.springboot.HotelBookingSystem.repository.RoomRepository;

public class RoomService {

	@Autowired
	private RoomRepository roomRepository;
	
	public Room getById(int rid) throws InvalidIdException {
		// TODO Auto-generated method stub
		Optional<Room> optional = roomRepository.findById(rid);
		if(!optional.isPresent()) {
			throw new InvalidIdException("Room ID invalid");
		}
		return optional.get();
	}

	public Room insert(Room room) {
		// TODO Auto-generated method stub
		return roomRepository.save(room);
	}

}
