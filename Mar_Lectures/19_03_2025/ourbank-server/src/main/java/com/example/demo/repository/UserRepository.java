package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.models.User;

import jakarta.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, Integer>{

	public User findByUsername(String username);
	
	@Query("UPDATE User u SET u.amount = u.amount + :amount WHERE u.user_id = :id")
	@Modifying
	@Transactional
	public void addAmount(int id, float amount);

	@Query("UPDATE User u SET u.amount = u.amount - :amount WHERE u.user_id = :id")
	@Modifying
	@Transactional
	public void reduceAmount(int id, float amount);

}
