package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.ClassTeacher;
import com.example.demo.services.TeacherService;

@RestController
public class TeacherController {

	@Autowired
	TeacherService tse;
	
	@PostMapping("/addTeacher")
	public String AddTeacher(ClassTeacher cls) {
		return tse.AddTeacher(cls);
	}
	
	@GetMapping("/Teacher")
	public List<ClassTeacher> GetTeacher() {
		return tse.GetTeacher();
	}
	
    @GetMapping("/students/class_teacher")
    public ClassTeacher getClassTeacher(@RequestBody int univRegNo) {
    	return tse.getClassTeacher(univRegNo);
    }
}
