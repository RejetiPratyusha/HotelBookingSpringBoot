package com.springboot.HotelBookingSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.HotelBookingSystem.model.Transaction;
import com.springboot.HotelBookingSystem.repository.TransactionRepository;

@Service
public class TransactionService {
	@Autowired
	private TransactionRepository transactionRepository;
	
	public Transaction insert(Transaction transaction) {
		return transactionRepository.save(transaction);
	}
}
