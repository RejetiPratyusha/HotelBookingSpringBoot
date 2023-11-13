package com.springboot.HotelBookingSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.HotelBookingSystem.exception.InvalidIdException;
import com.springboot.HotelBookingSystem.model.Booking;
import com.springboot.HotelBookingSystem.model.Customer;
import com.springboot.HotelBookingSystem.service.BookingService;
import com.springboot.HotelBookingSystem.service.CustomerService;

@RestController
@RequestMapping("/feelhome")
public class BookingController {


	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/booking/add/{cid}")
	public ResponseEntity<?> insert(@PathVariable("cid") int cid,
					   				@RequestBody Booking booking) {
		
		try {
			/*fetch customer ID*/
			Customer customer = customerService.getById(cid);
			/*set customer ID to booking*/
			booking.setCustomer(customer);
			/*insert booking into booking table*/
			booking = bookingService.insert(booking);
			/*return booking if successful*/
			return ResponseEntity.ok().body(booking);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
}
	
	@DeleteMapping("booking/cancel/{bid}")
	public ResponseEntity<?> deleteBooking(@PathVariable("bid") int bid) {
		
		try {
			/*fetch booking id*/
			Booking booking = bookingService.getById(bid);
			/*cancel booking after fetching ID*/
			bookingService.deleteBooking(booking);
			/*return message if cancellation is successful*/
			return ResponseEntity.ok().body("Booking cancelled successfully");

		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
