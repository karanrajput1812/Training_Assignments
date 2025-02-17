package com.example.demo.models.lappy;

import org.springframework.stereotype.Component;

import jakarta.annotation.Priority;

@Component
@Priority(value = 2)
public class DellLaptop implements Laptop {

	public String toString() {
		return " Hey ! You have got Dell Laptop";
	}
}
