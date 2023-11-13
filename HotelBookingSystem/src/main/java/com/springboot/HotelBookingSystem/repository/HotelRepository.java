package com.springboot.HotelBookingSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.springboot.HotelBookingSystem.model.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Integer>{

	List<Hotel> findByExecutiveId(int eid);

	List<Hotel> findByLocationId(int lid);

}
