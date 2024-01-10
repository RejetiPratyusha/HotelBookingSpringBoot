package com.springboot.HotelBookingSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.HotelBookingSystem.model.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer>{

	List<Feedback> findByHotelId(int hid);
	
	

}
