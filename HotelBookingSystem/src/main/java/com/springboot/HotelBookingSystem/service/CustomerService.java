package com.springboot.HotelBookingSystem.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.HotelBookingSystem.model.Customer;
import com.springboot.HotelBookingSystem.repository.CustomerRepository;
import com.springboot.HotelBookingSystem.exception.InvalidIdException;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	public Customer insert(Customer customer) {
		// TODO Auto-generated method stub
		return customerRepository.save(customer);
	}

	public Customer getById  (int cid) throws InvalidIdException {
		// TODO Auto-generated method stub
		Optional <Customer> optional = customerRepository.findById(cid);
		if(!optional.isPresent()) {
			throw new InvalidIdException("Customer ID invalid...");
		}
		return optional.get();
		
	}
}
