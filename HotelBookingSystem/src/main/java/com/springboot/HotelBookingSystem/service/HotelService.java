package com.springboot.HotelBookingSystem.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.HotelBookingSystem.exception.InvalidIdException;
import com.springboot.HotelBookingSystem.model.Hotel;
import com.springboot.HotelBookingSystem.model.HotelAdmin;
import com.springboot.HotelBookingSystem.repository.HotelRepository;

@Service
public class HotelService {

	@Autowired
	private HotelRepository hotelRepository;

	public Hotel postHotel(Hotel hotel) {
		return hotelRepository.save(hotel);
	}

	public List<Hotel> getAllHotels(Pageable pageable) {
		return hotelRepository.findAll(pageable).getContent();
	}

	public List<Hotel> getAllHotelsByEid(int eid) {
		return hotelRepository.findByExecutiveId(eid);
	}

	public List<Hotel> getAllHotelsByLocation(int lid) {
		return hotelRepository.findByLocationId(lid);
	}

	public Hotel getHotelsByHid(int hid) throws InvalidIdException {
		Optional<Hotel> optional = hotelRepository.findById(hid);
		if (!optional.isPresent()) {
			throw new InvalidIdException("location id invalid");
		}
		return optional.get();
	}

	public Hotel getOne(int id) throws InvalidIdException {
		Optional<Hotel> optional = hotelRepository.findById(id);
		if (!optional.isPresent()) {
			throw new InvalidIdException("hotel id invalid");
		}
		return optional.get();
	}

	public void deleteHotel(Hotel hotel) {
		hotelRepository.delete(hotel);
	}

	public Hotel getByAdmin(int aid) {
		// TODO Auto-generated method stub
		return hotelRepository.findByHotelAdminId(aid);
	}

	public List<Hotel> getHotelsByLocationName(String name) {
		// TODO Auto-generated method stub
		return hotelRepository.findHotelsByLocationName(name);
	}

	public List<Hotel> getHotelsByCheckinCheckoutLname(LocalDate checkIn, LocalDate checkOut, String lname) {
		// TODO Auto-generated method stub
		return hotelRepository.getHotelsByCheckinCheckoutLname(checkIn,checkOut,lname);
	}


	
	
	
	

}
