package com.example.demo.conf;

import java.util.Scanner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import com.example.display.DisplayByAge;
import com.example.display.DisplayByDesignation;
import com.example.display.DisplayByEmployeeId;
import com.example.display.DisplayByName;
import com.example.display.DisplayBySalary;
import com.example.model.Address;
import com.example.model.CEO;
import com.example.model.Clerk;
import com.example.model.Manager;
import com.example.model.Programmer;

@Configuration
public class MyJavaConfiguration {
	
	@Bean("address")
	@Scope("prototype")
	public Address getAddress() {
		Address a1 = new Address();
		String State = new Scanner(System.in).next();
		a1.setState("Karnataka");
		a1.setCity("Bengaluru");
		a1.setPin(345555);
		return a1;
	}
	
	@Bean("ceo")
	@Lazy
	public CEO getCEO() {
		CEO.setCEO(true);
		return new CEO(getAddress());
	}
	
	@Bean("clerk")
	@Scope("prototype")
	public Clerk getCLerk() {
		return new Clerk(getAddress());
	}
	
	@Bean("programmer")
	@Scope("prototype")
	public Programmer getProgrammer() {
		return new Programmer(getAddress());
	}
	
	@Bean("manager")
	@Scope("prototype")
	public Manager getManager() {
		return new Manager(getAddress());
	}
	
	@Bean("ceo_status")
	@Scope("prototype")
	public boolean getStatus() {
		return CEO.isCEO();
	}
	
	@Bean("display_by_age")
	public DisplayByAge getDisplayByAge() {
		return new DisplayByAge();
	}
	
	@Bean("display_by_designation")
	public DisplayByDesignation getDisplayByDesignation() {
		return new DisplayByDesignation();
	}
	
	@Bean("display_by_name")
	public DisplayByName getDisplayByName() {
		return new DisplayByName();
	}
	
	@Bean("display_by_salary")
	public DisplayBySalary getDisplayBySalary() {
		return new DisplayBySalary();
	}
	
	@Bean("display_by_employeeid")
	public DisplayByEmployeeId getDisplayByEmployeeId() {
		return new DisplayByEmployeeId();
	}
}
