package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Student;
import com.example.demo.repos.StudentDao;

@RestController
public class MyController {

	@Autowired
	StudentDao stu;
	
	@GetMapping("/")
	public List<Student> addData(Student s) {
		return stu.findAll();
	}
}