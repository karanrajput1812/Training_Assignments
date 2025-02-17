package com.example.demo.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.example.demo.models.lappy.Laptop;

@Component("Employee")
//@Lazy
@Scope("prototype")
public class Employee implements Person {
	@Value("Rajesh")
	private String name;
	@Value("25")
	private int age;
	@Value("25000")
	private int salary;
	@Value("Intern")
	private String designation;
	@Autowired
	private Address address;
	@Autowired
	@Qualifier("mac")
	private Laptop laptop;
	
	public Employee() {
		System.out.println("Employee Object Created");
	}
	
	@Override
	public String toString() {
		System.out.println("Employee [name: " + name + ", age: " + age + ", Salary: " + salary + ", Designation: " + designation + " ]");
		System.out.println(address);
		System.out.println(laptop);
		return "";
	}
}
