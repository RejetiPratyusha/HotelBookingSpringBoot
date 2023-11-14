package com.springboot.HotelBookingSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.HotelBookingSystem.service.CustomerService;
import com.springboot.HotelBookingSystem.exception.InvalidIdException;
import com.springboot.HotelBookingSystem.model.Customer;
import com.springboot.HotelBookingSystem.model.customerRoom;
import com.springboot.HotelBookingSystem.model.Room;
import com.springboot.HotelBookingSystem.service.customerRoomService;
import com.springboot.HotelBookingSystem.service.RoomService;

@RestController
@RequestMapping("/feelhome")
public class customerRoomController {
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private customerRoomService customerRoomService;
	
	@PostMapping("/customerroom/add")
	public void insert(@PathVariable("cid") int cid,
						@PathVariable("rid") int rid,
			@RequestBody customerRoom customerroom) {
		try {
			Customer customer = customerService.getById(cid);
			customerroom.setCustomer(customer);
			
			Room room = roomService.getById(rid);
			customerroom.setRoom(room);
			
			customerroom = customerRoomService.insert(customerroom);
		} catch (InvalidIdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
