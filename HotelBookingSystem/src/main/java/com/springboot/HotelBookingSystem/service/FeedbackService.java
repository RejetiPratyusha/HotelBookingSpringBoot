package com.springboot.HotelBookingSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.HotelBookingSystem.model.Feedback;
import com.springboot.HotelBookingSystem.repository.FeedbackRepository;

@Service
public class FeedbackService {
	
	@Autowired
	private FeedbackRepository repository;
	
	

	public Feedback addFeedback(Feedback feedback) {
		return repository.save(feedback);
		
	}

	public List<Feedback> getFeebackForHotel(int hid) {
		return repository.findByHotelId(hid);
	}

}
