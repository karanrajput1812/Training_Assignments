package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserServices {
	
	@Autowired
	UserRepository userRepo;
	
	public List<User> getCustomer(){
		return userRepo.findAll();
	}
	
	public String registerUser(User u) {
		userRepo.save(u);
		return "User Registered Successfully";
	}
	
	public User loginUser(String username, String password) {
		User user = userRepo.findByUsername(username);
		int id = user.getId();
		user = userRepo.findById(id).orElse(null);
		System.out.println(user);
		if (user != null && user.getPassword().equals(password)) {
			return user;
		}
		return null;
	}
	
	public User checkReceiver(int id) {
		User user = userRepo.findById(id).orElse(null);
		return user;
	}
	
	public boolean checkBalance(int id, float transferAmount) {
		User user = userRepo.findById(id).orElse(null);
		if(user.getAmount()>=transferAmount) {
			return true;
		}
		return false;
	}
	
	public float checkBalanceById(int id) {
		User user = userRepo.findById(id).orElse(null);
		return user.getAmount();
	}
	
	public void addAmount(int id, float amount) {
		userRepo.addAmount(id, amount);
	}
	
	public void reduceAmount(int id, float amount) {
		userRepo.reduceAmount(id, amount);
	}
}

