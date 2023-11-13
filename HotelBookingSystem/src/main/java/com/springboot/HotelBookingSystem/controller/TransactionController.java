package com.springboot.HotelBookingSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.HotelBookingSystem.model.Transaction;
import com.springboot.HotelBookingSystem.service.TransactionService;

@RestController
@RequestMapping("/feelhome")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	
	@PostMapping("/transaction/add")
	public Transaction insert(@RequestBody Transaction transaction) {
		/*insert transaction into db*/
		return transactionService.insert(transaction);
	}
	
}
