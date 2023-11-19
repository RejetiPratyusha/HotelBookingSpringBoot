package com.springboot.HotelBookingSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.HotelBookingSystem.dto.BookingDto;
import com.springboot.HotelBookingSystem.exception.InvalidIdException;
import com.springboot.HotelBookingSystem.model.Booking;
import com.springboot.HotelBookingSystem.model.Customer;
import com.springboot.HotelBookingSystem.model.Hotel;
import com.springboot.HotelBookingSystem.model.Room;
import com.springboot.HotelBookingSystem.service.BookingService;
import com.springboot.HotelBookingSystem.service.CustomerService;
import com.springboot.HotelBookingSystem.service.HotelService;
import com.springboot.HotelBookingSystem.service.RoomService;

@RestController
@RequestMapping("/feelhome")
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private HotelService hotelService;
	
	@Autowired
	private RoomService roomService;

	@PostMapping("/booking/add/{cid}/{hid}/{rid}")
	public ResponseEntity<?> insert(@PathVariable("cid") int cid,
			                        @PathVariable("hid") int hid,
			                        @PathVariable("rid") int rid, 
			                        @RequestBody Booking booking) {

		try {
			/* fetch customer ID */
			Customer customer = customerService.getById(cid);
			 Hotel hotel = hotelService.getHotelsByHid(hid);
			  Room room = roomService.getById(rid);
			/* set customer ID to booking */
			booking.setCustomer(customer);
			booking.setHotel(hotel);
			booking.setRoom(room);
			/* insert booking into booking table */
			booking = bookingService.insert(booking);
			
			/* return booking if successful */
			return ResponseEntity.ok().body(booking);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("booking/getone/{bid}")
	public ResponseEntity<?> getOne(@PathVariable("bid") int bid) {
		try {
			Booking booking = bookingService.getById(bid);
			return ResponseEntity.ok().body(booking);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@DeleteMapping("booking/cancel/{bid}")
	public ResponseEntity<?> deleteBooking(@PathVariable("bid") int bid) {

		try {
			/* fetch booking id */
			Booking booking = bookingService.getById(bid);
			/* cancel booking after fetching ID */
			bookingService.deleteBooking(booking);
			/* return message if cancellation is successful */
			return ResponseEntity.ok().body("Booking cancelled successfully");

		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/booking/getall")
	public List<Booking> getAll(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "1000000") Integer size) {
		Pageable pageable = PageRequest.of(page, size);
		return bookingService.getAll(pageable);

	}

	@PutMapping("/booking/update/{bid}")
	public ResponseEntity<?> updateBooking(@PathVariable("bid") int bid, @RequestBody BookingDto newBooking) {
		try {
			Booking oldBooking = bookingService.getById(bid);
			if (newBooking.getCheck_in() != null)
				oldBooking.setCheck_in(newBooking.getCheck_in());
			if (newBooking.getCheck_out() != null)
				oldBooking.setCheck_out(newBooking.getCheck_out());
			if (newBooking.getNoOfAdults() != 0)
				oldBooking.setNoOfAdults(newBooking.getNoOfAdults());
			if (newBooking.getNoOfChildren() != 0)
				oldBooking.setNoOfChildren(newBooking.getNoOfChildren());

			oldBooking = bookingService.insert(oldBooking);
			return ResponseEntity.ok().body(oldBooking);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@GetMapping("/booking/{cid}")
	public ResponseEntity<?> getByCustomer(@PathVariable("cid") int cid) {
//	fetch customer details by id
		try {
			Customer customer = customerService.getById(cid);
			List<Booking> list = bookingService.getByCustomer(cid);
			return ResponseEntity.ok().body(list);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}
}
