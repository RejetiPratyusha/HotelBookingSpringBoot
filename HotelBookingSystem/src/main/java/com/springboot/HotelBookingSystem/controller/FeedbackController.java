package com.springboot.HotelBookingSystem.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.HotelBookingSystem.exception.InvalidIdException;
import com.springboot.HotelBookingSystem.model.Customer;
import com.springboot.HotelBookingSystem.model.CustomerRoom;
import com.springboot.HotelBookingSystem.model.Feedback;
import com.springboot.HotelBookingSystem.model.Hotel;
import com.springboot.HotelBookingSystem.service.CustomerRoomService;
import com.springboot.HotelBookingSystem.service.CustomerService;
import com.springboot.HotelBookingSystem.service.FeedbackService;
import com.springboot.HotelBookingSystem.service.HotelService;

@RestController
@CrossOrigin(origins = { "http://localhost:3000" })
public class FeedbackController {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private FeedbackService feedbackService;
	@Autowired
	private HotelService hotelService;

	@Autowired
	private CustomerRoomService customerRoomService;

	@PostMapping("/feedback/{cid}/{hid}")
	public ResponseEntity<?> WriteFeedback(@PathVariable("cid") int cid, @PathVariable("hid") int hid,
			@RequestBody Feedback feedback) {

		try {
			Customer customer = customerService.getById(cid);
			Hotel hotel = hotelService.getHotelsByHid(hid);
			feedback.setCustomer(customer);
			feedback.setHotel(hotel);
			feedback.setDate(LocalDate.now());
			feedback = feedbackService.addFeedback(feedback);
			return ResponseEntity.ok().body(feedback);

		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/feedbackForHotel/{hid}")
	public ResponseEntity<?> getFeedback(@PathVariable("hid") int hid) {
		try {
			Hotel hotel = hotelService.getHotelsByHid(hid);

			List<Feedback> feedbackList = feedbackService.getFeebackForHotel(hid);
			return ResponseEntity.ok().body(feedbackList);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	public static boolean isCustomerSame(CustomerRoom booking, Feedback feedback) {
		return Objects.equals(booking.getCustomer().getId(), feedback.getCustomer().getId());
	}

}
