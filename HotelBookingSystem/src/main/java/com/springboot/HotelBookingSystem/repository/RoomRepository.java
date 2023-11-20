package com.springboot.HotelBookingSystem.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.HotelBookingSystem.model.Room;

public interface RoomRepository extends JpaRepository<Room, Integer> {

	List<Room> findByHotelId(int hid);
	
	/*
	 * @Query("select r.id, r.room_type, r.price from Room r,Booking b where r.hotel.id = :hotelId AND b.hotel.id = :hotelId AND b.check_in <= :checkOut AND b.check_out >= :checkIn"
	 * ) List<Room> getAvailableRooms(int hotelId, LocalDate checkIn, LocalDate
	 * checkOut);
	 */

	

}
