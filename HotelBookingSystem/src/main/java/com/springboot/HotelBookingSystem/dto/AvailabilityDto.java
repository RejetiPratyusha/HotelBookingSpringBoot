package com.springboot.HotelBookingSystem.dto;

import java.time.LocalDate;

public class AvailabilityDto {
	
	private LocalDate checkIn;
	private LocalDate checkOut;
	public LocalDate getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(LocalDate checkIn) {
		this.checkIn = checkIn;
	}
	public LocalDate getCheckOut() {
		return checkOut;
	}
	public void setCheckOut(LocalDate checkOut) {
		this.checkOut = checkOut;
	}
	@Override
	public String toString() {
		return "AvailabilityDto [checkIn=" + checkIn + ", checkOut=" + checkOut + "]";
	}
	
	

}
