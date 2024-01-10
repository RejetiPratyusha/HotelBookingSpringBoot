package com.springboot.HotelBookingSystem.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.HotelBookingSystem.model.Hotel;
import com.springboot.HotelBookingSystem.model.HotelAdmin;

public interface HotelRepository extends JpaRepository<Hotel, Integer>{

	List<Hotel> findByExecutiveId(int eid);

	List<Hotel> findByLocationId(int lid);

	Hotel findByHotelAdminId(int aid);

	@Query("select h from Hotel h where h.location.name = :name")
	List<Hotel> findHotelsByLocationName(String name);

	@Query("select cr.room.hotel from CustomerRoom cr where cr.check_in=?1 AND cr.check_out=?2 And cr.room.hotel.location.name=?3")
	List<Hotel> getHotelsByCheckinCheckoutLname(LocalDate checkIn, LocalDate checkOut, String lname);

	

}
