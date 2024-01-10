package com.springboot.HotelBookingSystem.dto;

public class priceResponse {
	
	private boolean isAvailable;
	private double price;
	private double totalBookingPrice;
	private int availableRooms;
	private int numberOfDays;
	private double cgst;
	private double sgst;
	private double gstPrice;
	private int numberOfRoomsBooked;
	
	
	
	
	
	
	public int getNumberOfRoomsBooked() {
		return numberOfRoomsBooked;
	}
	public void setNumberOfRoomsBooked(int numberOfRoomsBooked) {
		this.numberOfRoomsBooked = numberOfRoomsBooked;
	}
	public double getGstPrice() {
		return gstPrice;
	}
	public void setGstPrice(double gstPrice) {
		this.gstPrice = gstPrice;
	}
	public double getCgst() {
		return cgst;
	}
	public void setCgst(double cgst) {
		this.cgst = cgst;
	}
	public double getSgst() {
		return sgst;
	}
	public void setSgst(double sgst) {
		this.sgst = sgst;
	}
	public int getNumberOfDays() {
		return numberOfDays;
	}
	public void setNumberOfDays(int numberOfDays) {
		this.numberOfDays = numberOfDays;
	}
	public boolean isAvailable() {
		return isAvailable;
	}
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public double getTotalBookingPrice() {
		return totalBookingPrice;
	}
	public void setTotalBookingPrice(double totalBookingPrice) {
		this.totalBookingPrice = totalBookingPrice;
	}
	public int getAvailableRooms() {
		return availableRooms;
	}
	public void setAvailableRooms(int availableRooms) {
		this.availableRooms = availableRooms;
	}
	
	
	
	
	
	

}
