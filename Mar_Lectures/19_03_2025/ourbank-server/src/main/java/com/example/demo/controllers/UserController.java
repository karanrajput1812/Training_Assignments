package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.User;
import com.example.demo.services.UserServices;

@RestController
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	UserServices userService;
	
	@GetMapping("/")
	public List<User> getCustomer() {
		return userService.getCustomer();
	}
	
	@PostMapping("/register")
	public String registerUser(@RequestBody User u) {
		return userService.registerUser(u);
	}
	
	@PostMapping("/login")
	public User loginUser(@RequestBody User u) {
		return userService.loginUser(u.getUsername(), u.getPassword());
	}
	
	@GetMapping("/checkReceiver/{id}")
	public User checkReceiver(@PathVariable int id) {
		return userService.checkReceiver(id);
	}
	
	@GetMapping("/checkBalance/{id}/{amount}")
	public boolean checkBalance(@PathVariable int id, @PathVariable float amount) {
		return userService.checkBalance(id,amount);
	}
	
	@GetMapping("/checkBalance/{id}")
	public float checkBalanceById(@PathVariable int id) {
		return userService.checkBalanceById(id);
	}
}	
