package com.example.demo.models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("Employee")
public class Employee implements Person {
	
	@Value("Rajesh")
	private String name;
	@Value("25")
	private int age;
	@Value("25000")
	private int salary;
	@Value("Intern")
	private String designation;
	
	@Override
	public String toString() {
		return "Employee [name: " + name + ", age: " + age + ", Salary: " + salary + ", Designation: " + designation + " ]"; 
	}
}
