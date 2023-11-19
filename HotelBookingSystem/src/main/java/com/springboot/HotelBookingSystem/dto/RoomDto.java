package com.springboot.HotelBookingSystem.dto;

public class RoomDto {
	private int id;
	private String room_type;
	private double price;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
		return "RoomDto [id=" + id + ", room_type=" + room_type + ", price=" + price + "]";
	}
	
}
