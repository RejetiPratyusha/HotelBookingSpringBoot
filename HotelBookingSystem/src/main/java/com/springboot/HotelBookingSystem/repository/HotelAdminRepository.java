package com.springboot.HotelBookingSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.HotelBookingSystem.model.HotelAdmin;

public interface HotelAdminRepository extends JpaRepository<HotelAdmin, Integer>{

}
