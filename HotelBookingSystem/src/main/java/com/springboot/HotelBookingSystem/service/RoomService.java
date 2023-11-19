package com.springboot.HotelBookingSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.HotelBookingSystem.dto.AvailabilityDto;
import com.springboot.HotelBookingSystem.exception.InvalidIdException;
import com.springboot.HotelBookingSystem.model.Booking;
import com.springboot.HotelBookingSystem.model.Room;
import com.springboot.HotelBookingSystem.repository.BookingRepository;
import com.springboot.HotelBookingSystem.repository.RoomRepository;

@Service
public class RoomService {

	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private BookingRepository bookingRepository;
	
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
	 * public List<Room> getAllAvailableRoomsByHotelId(int hotelId, AvailabilityDto
	 * dates) { //List<Room> rooms = roomRepository.findByHotelId(hotelId);
	 * List<Room> rooms =
	 * roomRepository.getAvailableRooms(hotelId,dates.getCheckIn(),dates.getCheckOut
	 * ()); return rooms; }
	 */
	

}
