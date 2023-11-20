package com.springboot.HotelBookingSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.HotelBookingSystem.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer>{

	List<Booking> findByCustomer(int cid);


}
