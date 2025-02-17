package com.example.demo.models.lappy;

import org.springframework.stereotype.Component;

@Component("mac")
public class MacBook implements Laptop {
	
	public String toString() {
		return "Congrats ! You have got MacBook";
	}
}
