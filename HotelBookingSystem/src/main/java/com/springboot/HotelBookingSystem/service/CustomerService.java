package com.springboot.HotelBookingSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.HotelBookingSystem.model.Customer;
import com.springboot.HotelBookingSystem.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	public Customer insert(Customer customer) {
		// TODO Auto-generated method stub
		return customerRepository.save(customer);
	}

}
