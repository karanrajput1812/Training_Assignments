package com.example.demo.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.example.demo.Student;
import com.example.models.Address;
import com.example.models.Employee;

@Configuration
public class MyJavaConfiguration {
	
	@Bean("emp")
	@Scope("prototype")
	public Employee getEmployee() {
		return new Employee("Raju",25,25000,"Tester", getAddress());
	}
	
	@Bean("std")
	public Student getStudent() {
		return new Student();
	}
	
	@Bean("addr_c")
	public Address getAddress() {
		return new Address("Maharashtra", "Mumbai", 400001);
	}
	
	@Bean("emp_s")
	public Employee getEmployee2() {
		Employee e = new Employee();
		e.setName("Ramesh");
		e.setAge(26);
		e.setSalary(23044);
		e.setDesignation("Clerk");
		e.setAddress(getAddress2());
		return e;
	}
	
	@Bean("addr_s")
	public Address getAddress2() {
		Address a1 = new Address();
		a1.setState("Karnataka");
		a1.setCity("Bengaluru");
		a1.setPin(345555);
		return a1;
	}
}
