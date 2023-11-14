package com.springboot.HotelBookingSystem.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private LocalDate check_in;
	private LocalDate check_out;
	private int noOfAdults;
	private int noOfChildren;
	
	@ManyToOne
	private Customer customer;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getCheck_in() {
		return check_in;
	}

	public void setCheck_in(LocalDate check_in) {
		this.check_in = check_in;
	}

	public LocalDate getCheck_out() {
		return check_out;
	}

	public void setCheck_out(LocalDate localDate) {
		this.check_out = localDate;
	}

	public int getNoOfAdults() {
		return noOfAdults;
	}

	public void setNoOfAdults(int noOfAdults) {
		this.noOfAdults = noOfAdults;
	}

	public int getNoOfChildren() {
		return noOfChildren;
	}

	public void setNoOfChildren(int noOfChildren) {
		this.noOfChildren = noOfChildren;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Booking [id=" + id + ", check_in=" + check_in + ", check_out=" + check_out + ", noOfAdults="
				+ noOfAdults + ", noOfChildren=" + noOfChildren + ", customer=" + customer + "]";
	}
	
	
	
}
