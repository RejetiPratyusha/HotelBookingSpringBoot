package com.springboot.HotelBookingSystem.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.HotelBookingSystem.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer>{

	List<Booking> findByCustomer(int cid);

	@Query("select COUNT(b) from Booking b where b.hotel.id = :hid AND b.room.id = :rid AND  b.check_in = :checkIn AND b.check_out = :checkOut")
	int findNumberOfBookings(int hid,int rid, LocalDate checkIn, LocalDate checkOut);
}
