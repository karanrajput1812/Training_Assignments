package com.example.models;

import org.springframework.stereotype.Component;

public class Employee {

	private String name;
	private int age;
	private int salary;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	private String designation;
	private Address address;
	
	public Employee() {
		System.out.println("Employee() object created");
	}
	
	public Employee(String name, int age, int salary, String designation, Address address) {
		super();
		this.name = name;
		this.age = age;
		this.salary = salary;
		this.designation = designation;
		this.address = address;
	}
	
	@Override
	public String toString() {
		System.out.println(address);
		System.out.println("Employee [name: " + name + ", age: " + age + ", Salary: " + salary + ", Designation: " + designation + " ]");
		return "";
	}
}
