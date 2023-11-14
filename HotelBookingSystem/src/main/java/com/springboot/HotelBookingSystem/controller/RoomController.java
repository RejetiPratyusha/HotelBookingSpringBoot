package com.springboot.HotelBookingSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.HotelBookingSystem.exception.InvalidIdException;
import com.springboot.HotelBookingSystem.model.Hotel;
import com.springboot.HotelBookingSystem.model.Room;
import com.springboot.HotelBookingSystem.service.HotelService;
import com.springboot.HotelBookingSystem.service.RoomService;
@RestController
@RequestMapping("/feelhome")
public class RoomController {

	@Autowired
	private RoomService roomService;
	
	@Autowired
	private HotelService hotelService;
	
	@PostMapping("/room/add/{hid}")
	public ResponseEntity<?> insert(@PathVariable("hid") int hid,
					   @RequestBody Room room) {
		
		try {
			Hotel hotel = hotelService.getOne(hid);
			room.setHotel(hotel);
			room = roomService.insert(room);
			return ResponseEntity.ok().body(room);
			
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
}
}
