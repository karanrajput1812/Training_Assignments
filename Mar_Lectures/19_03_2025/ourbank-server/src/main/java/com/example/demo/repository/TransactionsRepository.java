package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.Transactions;

public interface TransactionsRepository extends JpaRepository<Transactions, Integer>{

	@Query(value = "SELECT t FROM Transactions t WHERE (t.sender.user_id = :id OR t.receiver.user_id = :id) ORDER BY t.timestamp DESC")
	public List<Transactions> getTransactions(int id);
}
