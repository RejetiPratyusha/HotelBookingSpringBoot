package com.springboot.HotelBookingSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.HotelBookingSystem.exception.InvalidIdException;
import com.springboot.HotelBookingSystem.model.Booking;
import com.springboot.HotelBookingSystem.repository.BookingRepository;

@Service
public class BookingService {

	@Autowired
	private BookingRepository bookingRepository;
	
	public Booking insert(Booking booking) {
		// TODO Auto-generated method stub
		return bookingRepository.save(booking);
	}
	
	
	public Booking getById(int bid) throws InvalidIdException {
		// TODO Auto-generated method stub
		Optional<Booking> optional = bookingRepository.findById(bid);
		if(!optional.isPresent()) {
			throw new InvalidIdException("Booking ID invalid....");
		}
		return optional.get();
	}
	public void deleteBooking(Booking booking) {
			bookingRepository.delete(booking);
		
	}


	public List<Booking> getAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return bookingRepository.findAll(pageable).getContent();
	}

}
