package com.springboot.HotelBookingSystem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Hotel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String address;
	private String email;
	private String phone_number;
	
	
	
//	select cr.room.hotel from CustomerRoom cr where cr.check_in=?1 AND cr.check_out=?2 And cr.room.hotel.location.name=?3
	@ManyToOne
	private Executive executive;
	
	@ManyToOne
	private Location location;
	
	@OneToOne
	private HotelAdmin hotelAdmin;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public Executive getExecutive() {
		return executive;
	}

	public void setExecutive(Executive executive) {
		this.executive = executive;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public HotelAdmin getHotelAdmin() {
		return hotelAdmin;
	}

	public void setHotelAdmin(HotelAdmin hotelAdmin) {
		this.hotelAdmin = hotelAdmin;
	}

	@Override
	public String toString() {
		return "Hotel [id=" + id + ", name=" + name + ", address=" + address + ", email=" + email + ", phone_number="
				+ phone_number + ", executive=" + executive + ", location=" + location + ", hotelAdmin=" + hotelAdmin
				+ "]";
	}
	
	
}
