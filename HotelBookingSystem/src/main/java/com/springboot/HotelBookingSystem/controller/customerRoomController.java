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
import com.springboot.HotelBookingSystem.model.CustomerRoom;
import com.springboot.HotelBookingSystem.model.Customer;
import com.springboot.HotelBookingSystem.model.Hotel;
import com.springboot.HotelBookingSystem.model.Room;
import com.springboot.HotelBookingSystem.service.CustomerRoomService;
import com.springboot.HotelBookingSystem.service.CustomerService;
import com.springboot.HotelBookingSystem.service.HotelService;
import com.springboot.HotelBookingSystem.service.RoomService;

@RestController
@RequestMapping("/feelhome")
public class CustomerRoomController {

	@Autowired
	private CustomerRoomService customerRoomService;

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private HotelService hotelService;
	
	@Autowired
	private RoomService roomService;

	    @PostMapping("/booking/bookMoreRooms/{cid}")
	    public ResponseEntity<?> bookMoreRooms(@PathVariable("cid") int cid,
	            @RequestBody List<Integer> roomIds) {
	        try {
	            Customer customer = customerService.getById(cid);

	            // Retrieve rooms based on the provided roomIds
	            List<Room> rooms = roomService.getRoomsByIds(roomIds);

	            // Book more rooms for the customer
	            for (Room room : rooms) {
	                CustomerRoom booking = new CustomerRoom();
	                booking.setCustomer(customer);
	                booking.setRoom(room);
	                // Set other booking details as needed
	                customerRoomService.insert(booking);
	            }

	            return ResponseEntity.ok().body("Rooms booked successfully.");
	        } catch (InvalidIdException e) {
	            return ResponseEntity.badRequest().body(e.getMessage());
	        }
	    }

	    // API to calculate total price for rooms booked by a customer
	    @GetMapping("/booking/totalPrice/{cid}")
	    public ResponseEntity<?> calculateTotalPrice(@PathVariable("cid") int cid) {
	        try {
	            // Assuming you have a method to calculate total price in customerRoomService
	            double totalPrice = customerRoomService.calculateTotalPrice(cid);
	            return ResponseEntity.ok().body("Total Price for Rooms: " + totalPrice);
	        } catch (InvalidIdException e) {
	            return ResponseEntity.badRequest().body(e.getMessage());
	        }
	    }



	@GetMapping("booking/getone/{bid}")
	public ResponseEntity<?> getOne(@PathVariable("bid") int bid) {
		try {
			CustomerRoom booking = customerRoomService.getById(bid);
			return ResponseEntity.ok().body(booking);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@DeleteMapping("booking/cancel/{bid}")
	public ResponseEntity<?> deleteBooking(@PathVariable("bid") int bid) {

		try {
			/* fetch booking id */
			CustomerRoom booking = customerRoomService.getById(bid);
			/* cancel booking after fetching ID */
			customerRoomService.deleteBooking(booking);
			/* return message if cancellation is successful */
			return ResponseEntity.ok().body("Booking cancelled successfully");

		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/booking/getall")
	public List<CustomerRoom> getAll(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "1000000") Integer size) {
		Pageable pageable = PageRequest.of(page, size);
		return customerRoomService.getAll(pageable);

	}

	@PutMapping("/booking/update/{bid}")
	public ResponseEntity<?> updateBooking(@PathVariable("bid") int bid, @RequestBody BookingDto newBooking) {
		try {
			CustomerRoom oldBooking = customerRoomService.getById(bid);
			if (newBooking.getCheck_in() != null)
				oldBooking.setCheck_in(newBooking.getCheck_in());
			if (newBooking.getCheck_out() != null)
				oldBooking.setCheck_out(newBooking.getCheck_out());
			if (newBooking.getNoOfAdults() != 0)
				oldBooking.setNoOfAdults(newBooking.getNoOfAdults());
			if (newBooking.getNoOfChildren() != 0)
				oldBooking.setNoOfChildren(newBooking.getNoOfChildren());

			oldBooking = customerRoomService.insert(oldBooking);
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
			List<CustomerRoom> list = customerRoomService.getByCustomer(cid);
			return ResponseEntity.ok().body(list);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}
}
