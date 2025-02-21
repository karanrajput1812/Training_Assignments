package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.ClassTeacher;
import com.example.demo.repos.TeacherDao;

@Service
public class TeacherService {

	@Autowired
	TeacherDao teacher;
	
	public String AddTeacher(ClassTeacher cls) {
		teacher.save(cls);
		return "Teacher Added Succesfully";
	}
	
	public List<ClassTeacher> GetTeacher() {
		return teacher.findAll();
	}
	
	public ClassTeacher getClassTeacher(int univRegNo) {
		return teacher.findClassTeacherByUnivReg(univRegNo);
	}
}
