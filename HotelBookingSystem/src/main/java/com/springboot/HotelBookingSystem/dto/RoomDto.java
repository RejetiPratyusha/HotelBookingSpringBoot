package com.springboot.HotelBookingSystem.dto;

public class RoomDto {

	private String room_type;
	private double price;
	
	public String getRoom_type() {
		return room_type;
	}
	public void setRoom_type(String room_type) {
		this.room_type = room_type;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "RoomDto [room_type=" + room_type + ", price=" + price + ", getRoom_type()=" + getRoom_type()
				+ ", getPrice()=" + getPrice() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
}
