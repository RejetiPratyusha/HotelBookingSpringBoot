package com.springboot.HotelBookingSystem.dto;

import java.time.LocalDate;

public class PriceCalculationDto {
	
	private LocalDate check_in;
	private LocalDate check_out;
	private int numberOfRooms;
    private int hotelId;
    private String roomType;
	public LocalDate getCheck_in() {
		return check_in;
	}
	public void setCheck_in(LocalDate check_in) {
		this.check_in = check_in;
	}
	public LocalDate getCheck_out() {
		return check_out;
	}
	public void setCheck_out(LocalDate check_out) {
		this.check_out = check_out;
	}
	public int getNumberOfRooms() {
		return numberOfRooms;
	}
	public void setNumberOfRooms(int numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}
	public int getHotelId() {
		return hotelId;
	}
	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
    
    

}
