package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Student;
import com.example.demo.repos.StudentDao;
//import com.example.demo.repos2.UniversityDao;

@RestController
public class MyController {

	@Autowired
	StudentDao stu;
	
//	@Autowired
//	UniversityDao utu;
	
	@GetMapping("/")
	public List<Student> addData(Student s) {
		return stu.findAll();
	}
	
	 @PostMapping("/students")
		public String insertEmployees(@RequestBody Student s) {
		 stu.save(s);
		 	return "Added new Employee Successfully !";
	}
}