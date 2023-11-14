package com.springboot.HotelBookingSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.HotelBookingSystem.model.customerRoom;

public interface customerRoomRepository extends JpaRepository<customerRoom, Integer>{

}
