package com.springboot.HotelBookingSystem.dto;

public class RoomAvailabilityResponse {
	
	private boolean isavailable;
	private int roomsAvailable;
	public boolean isIsavailable() {
		return isavailable;
	}
	public void setIsavailable(boolean isavailable) {
		this.isavailable = isavailable;
	}
	public int getRoomsAvailable() {
		return roomsAvailable;
	}
	public void setRoomsAvailable(int roomsAvailable) {
		this.roomsAvailable = roomsAvailable;
	}
	
	

}
