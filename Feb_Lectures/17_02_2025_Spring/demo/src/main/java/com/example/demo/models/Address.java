package com.example.demo.models;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Address {
	private String state;
	private String city;
	private int pin;
	
	Address() {
		System.out.println("dsdf");
	}
	
	@Override
	public String toString() {
		return "Employee [state: " + state + ", city: " + city + ", Pin: " + pin + " ]";
	}
}
