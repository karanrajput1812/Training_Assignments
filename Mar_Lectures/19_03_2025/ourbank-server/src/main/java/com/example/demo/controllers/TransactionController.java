package com.example.demo.controllers;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Transactions;
import com.example.demo.services.TransactionServices;
import com.example.demo.services.UserServices;

@RestController
@CrossOrigin("*")
public class TransactionController {
	
	@Autowired
	TransactionServices transService;
	
	@Autowired
	UserServices userService;
	
	@GetMapping("/ministatement/{id}")
	public List<Transactions> getTransaction(@PathVariable int id) {
		return transService.getTransactions(id);
	}
	
	@PostMapping("/bankTransfer")
	public Transactions addTransaction(@RequestBody Transactions ts) {
		userService.addAmount(ts.getReceiver().getId(), ts.getAmount());
		userService.reduceAmount(ts.getSender().getId(), ts.getAmount());
		return transService.addTransaction(ts);
	}
	
	
}
