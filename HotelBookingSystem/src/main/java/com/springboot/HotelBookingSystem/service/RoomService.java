package com.springboot.HotelBookingSystem.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.HotelBookingSystem.dto.RoomAvailabilityResponse;
import com.springboot.HotelBookingSystem.exception.InvalidIdException;
import com.springboot.HotelBookingSystem.model.CustomerRoom;
import com.springboot.HotelBookingSystem.model.Room;
import com.springboot.HotelBookingSystem.repository.CustomerRoomRepository;
import com.springboot.HotelBookingSystem.repository.RoomRepository;

@Service
public class RoomService {

	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private CustomerRoomRepository bookingRepository;
	
	public Room getById(int rid) throws InvalidIdException {
		// TODO Auto-generated method stub
		Optional<Room> optional = roomRepository.findById(rid);
		if(!optional.isPresent()) {
			throw new InvalidIdException("Room ID invalid");
		}
		return optional.get();
	}

	public Room insert(Room room) {
		// TODO Auto-generated method stub
		return roomRepository.save(room);
	}

	public List<Room> getRoomsByHid(int hid) {
		// TODO Auto-generated method stub
		return roomRepository.findByHotelId(hid);
	}

	public Room insertRoom(Room oldRoom) {
		// TODO Auto-generated method stub
		return roomRepository.save(oldRoom);
	}

	public void deleteRoom(Room room) {
		// TODO Auto-generated method stub
		roomRepository.delete(room);
	}

	public List<Room> getAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return roomRepository.findAll(pageable).getContent();
	}

	/*
	 * public boolean getAllAvailableRoomsByHotelId(int hotelId, String roomType,
	 * LocalDate checkIn, LocalDate checkOut) { Room room =
	 * roomRepository.findByHotelIdAndRoomType(hotelId,roomType); boolean
	 * isAvailable = false; int noOfBookings = 0; List<CustomerRoom> customerRooms =
	 * bookingRepository.findNumberOfBookings(room.getId(),checkIn, checkOut);
	 * if(!customerRooms.isEmpty()) { for(CustomerRoom cr : customerRooms) {
	 * noOfBookings+=cr.getNumberOfRooms(); } } if(room.getTotalRooms() >
	 * noOfBookings) { isAvailable=true; } else {
	 * System.out.println("No rooms available of type :::" + roomType); } return
	 * isAvailable; }
	 */

	public List<Room> getRoomsByIds(List<Integer> roomIds) throws InvalidIdException {
	    List<Room> rooms = roomRepository.findAllById(roomIds);
	    if (rooms.isEmpty()) {
	        throw new InvalidIdException("No valid room IDs provided");
	    }
	    return rooms;
	}

	public Room getRoomsByHidAndRoomType(int hid, String roomType) {
		return roomRepository.findByHotelIdAndRoomType(hid, roomType);
	}
	
	public RoomAvailabilityResponse getAllAvailableRoomsByHotelId(int hotelId, String roomType, LocalDate checkIn, LocalDate checkOut) { 
		RoomAvailabilityResponse response = new RoomAvailabilityResponse();
		 Room room = roomRepository.findByHotelIdAndRoomType(hotelId,roomType);
		 int noOfBookings = 0;
			 List<CustomerRoom> customerRooms = bookingRepository.findNumberOfBookings(room.getId(),checkIn, checkOut);
			 if(!customerRooms.isEmpty()) {
			 for(CustomerRoom cr : customerRooms) {
				 noOfBookings+=cr.getNumberOfRooms();
			 }
			 }
			 if(room.getTotalRooms() > noOfBookings) {
				 response.setIsavailable(true);	
				 response.setRoomsAvailable(room.getTotalRooms() - noOfBookings);
				 }
		/*
		 * else { System.out.println("No rooms available of type :::" + roomType); }
		 */
		return response;
	}

}
