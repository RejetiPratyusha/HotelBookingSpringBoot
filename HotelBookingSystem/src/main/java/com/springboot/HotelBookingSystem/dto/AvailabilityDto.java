package com.springboot.HotelBookingSystem.dto;

public class AvailabilityDto {
	
	private boolean isAvailable;
	private int numberOfRoomsBooked;
	public boolean isAvailable() {
		return isAvailable;
	}
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	public int getNumberOfRoomsBooked() {
		return numberOfRoomsBooked;
	}
	public void setNumberOfRoomsBooked(int numberOfRoomsBooked) {
		this.numberOfRoomsBooked = numberOfRoomsBooked;
	}
	
	

}
