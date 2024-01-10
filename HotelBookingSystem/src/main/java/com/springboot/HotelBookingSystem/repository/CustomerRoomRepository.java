package com.springboot.HotelBookingSystem.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.HotelBookingSystem.model.CustomerRoom;

public interface CustomerRoomRepository extends JpaRepository<CustomerRoom, Integer>{

	List<CustomerRoom> findByCustomerId(int cid);

	@Query("select cr from CustomerRoom cr where cr.room.id = :rid AND  cr.check_in = :checkIn AND cr.check_out = :checkOut AND cr.bookingStatus = 'BOOKED'")
	List<CustomerRoom> findNumberOfBookings(int rid, LocalDate checkIn, LocalDate checkOut);
	
	@Query("select COUNT(cr) from CustomerRoom cr where cr.room.hotel.id = :rid AND  cr.check_in = :checkIn AND cr.check_out = :checkOut AND cr.bookingStatus = 'BOOKED'")
	int findNumberOfBookingsForHotel(int rid, LocalDate checkIn, LocalDate checkOut);
	
	@Query("select cr from CustomerRoom cr where cr.room.hotel.id = :hid")
	List<CustomerRoom> findByHotelId(int hid);
}
