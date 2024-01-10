package com.springboot.HotelBookingSystem.controller;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.springboot.HotelBookingSystem.dto.PriceCalculationDto;
import com.springboot.HotelBookingSystem.dto.RoomAvailabilityResponse;
import com.springboot.HotelBookingSystem.dto.priceResponse;
import com.springboot.HotelBookingSystem.enumerate.BookingStatus;
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
@CrossOrigin(origins = { "http://localhost:3000" })
public class CustomerRoomController {

	@Autowired
	private CustomerRoomService customerRoomService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private HotelService hotelService;

	@Autowired
	private RoomService roomService;
	
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
	public ResponseEntity<?> cancelBooking(@PathVariable("bid") int bid) {

		try {
			/* fetch booking id */
			CustomerRoom booking = customerRoomService.getById(bid);
			/* updating the booking status to cancelled after fetching ID */
			if (booking.getBookingStatus() != null)
				booking.setBookingStatus(BookingStatus.CANCELLED.toString());
			customerRoomService.insert(booking);
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
			List<CustomerRoom> updatedList = new ArrayList<>();
			for(CustomerRoom cr : list) {
				System.out.println(LocalDate.now());
				System.out.println(cr.getCheck_out());
				if(LocalDate.now().compareTo(cr.getCheck_out()) > 0) {
					cr.setBookingStatus(BookingStatus.COMPLETED.toString());
				}
				customerRoomService.insert(cr);
				updatedList.add(cr);
			}
			return ResponseEntity.ok().body(updatedList);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@PostMapping("/book/{cid}/{rid}")
	public ResponseEntity<?> insert(@PathVariable("cid") int cid, @PathVariable("rid") int rid,
			@RequestBody CustomerRoom customerRoom) {
		// get customer by id
		try {
			Customer customer = customerService.getById(cid);
			Room room = roomService.getById(rid);
			customerRoom.setCustomer(customer);
			customerRoom.setRoom(room);
			Period intervalPeriod = Period.between(customerRoom.getCheck_in(), customerRoom.getCheck_out());
			int dateDifference = intervalPeriod.getDays();
			double totalPrice = 0.0;
			totalPrice += room.getPrice() * customerRoom.getNumberOfRooms() * dateDifference;
			customerRoom.setTotalPrice(totalPrice);
			customerRoom.setBookingStatus(BookingStatus.BOOKED.toString());
			customerRoom = customerRoomService.insert(customerRoom);
			return ResponseEntity.ok().body(customerRoom);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}
	
	
	@PostMapping("/getPrice")
	public ResponseEntity<?> calculatePrice(@RequestBody PriceCalculationDto priceDto) {
		priceResponse response = new priceResponse();
		Room room = roomService.getRoomsByHidAndRoomType(priceDto.getHotelId(), priceDto.getRoomType());
		RoomAvailabilityResponse availability  = roomService.getAllAvailableRoomsByHotelId(priceDto.getHotelId(), priceDto.getRoomType(), priceDto.getCheck_in(), priceDto.getCheck_out());

		Period intervalPeriod = Period.between(priceDto.getCheck_in(), priceDto.getCheck_out());
		int dateDifference = intervalPeriod.getDays();
		double basicPrice = 0.0;
		double totalPrice = 0.0;
		double gst = 0.0;
		if(availability.isIsavailable() && priceDto.getNumberOfRooms() <= availability.getRoomsAvailable()) {
			basicPrice += room.getPrice() * priceDto.getNumberOfRooms() * dateDifference; 
			response.setAvailable(true);
			response.setPrice(basicPrice);
			response.setNumberOfDays(dateDifference);
			if(basicPrice < 7500) {
				response.setCgst(6);
				response.setSgst(6);
			}
			if(basicPrice > 7500) {
				response.setCgst(9);
				response.setSgst(9);
			}
			double gstPrice = (basicPrice*response.getCgst())/100;
			gst = response.getCgst() + response.getSgst();
			totalPrice = basicPrice + (basicPrice*gst)/100;
			response.setTotalBookingPrice(totalPrice);
			response.setGstPrice(gstPrice);
			response.setNumberOfRoomsBooked(priceDto.getNumberOfRooms());
		}else {
			response.setAvailable(false);
		}
		response.setAvailableRooms(availability.getRoomsAvailable());
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping("/bookingsByHotelId/{hid}")
	public ResponseEntity<?> getByHotel(@PathVariable("hid") int hid) {
//	fetch customer details by id
		try {
			Hotel hotel = hotelService.getHotelsByHid(hid);
			List<CustomerRoom> list = customerRoomService.getByHotel(hid);
			List<CustomerRoom> updatedList = new ArrayList<>();
			for(CustomerRoom cr : list) {
				System.out.println(LocalDate.now());
				System.out.println(cr.getCheck_out());
				if(LocalDate.now().compareTo(cr.getCheck_out()) > 0) {
					cr.setBookingStatus(BookingStatus.COMPLETED.toString());
				}
				customerRoomService.insert(cr);
				updatedList.add(cr);
			}
			return ResponseEntity.ok().body(updatedList);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}
	 

}
