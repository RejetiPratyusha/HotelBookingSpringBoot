package com.springboot.HotelBookingSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.HotelBookingSystem.model.Room;

public interface RoomRepository extends JpaRepository<Room, Integer> {

	List<Room> findByHotelId(int hid);
	
	

	

}
