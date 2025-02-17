package com.example.demo.models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("Student")
public class Student implements Person {
	
	@Value("${rno}")
	int rollNo;
	
	@Value("${sname}")
	String name;
	
	@Value("${std}")
	int standard;
	
	@Override
	public String toString() {
		return "Student [rollNo: " + rollNo + ", name: " + name + ", Standard" + standard + " ]";  
	}
}
