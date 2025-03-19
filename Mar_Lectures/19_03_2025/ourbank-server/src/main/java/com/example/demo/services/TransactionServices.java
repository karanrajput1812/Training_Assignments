package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Transactions;
import com.example.demo.repository.TransactionsRepository;

@Service
public class TransactionServices {
	
	@Autowired
	TransactionsRepository transRepo;
	
	public List<Transactions> getTransactions(int id) {
		return transRepo.findAll();
	}
	
	public Transactions addTransaction(Transactions ts) {
		return transRepo.save(ts);
	}
}
