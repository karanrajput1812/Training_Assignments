package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Student;
import com.example.demo.repos.StudentDao;

@Service
public class StudentService {

	@Autowired
	StudentDao stu;

	public List<Student> getStudents() {
		return stu.findAll();
	}
	
	public String addStudents(Student s) {
		stu.save(s);
		return "Student Added Successfully";
	}
	
	public String deleteStudentsById(int rollNo) {
		stu.deleteById(rollNo);
		return "Student Deleted Successfully";
	}
	
	public String addDemoStudents(List<Student> list) {
		stu.saveAll(list);
		return "Demo Student Added Successfully";
	}
	
	public Student getStudentByRollNo(int rollNo) {
		return stu.getById(rollNo);
	}
	
	
	public List<Student> getStudentBySchoolName(String school) {
		return stu.getStudentBySchool(school);
	}
	
	public List<Student> getStudentByPassFail(boolean pass) {
		if(pass==true) {
			return stu.findByPercentageGreaterThan(40);
		}
		return stu.findByPercentageLessThan(40);
	}
	
	public long getStudentByCount(int std) {
		return stu.getCountByStandard(std);
	}
	
	public long getstrengthBySchool(String school) {
		return stu.getstrengthBySchool(school);
	}
	
	public List<Student> findTop5ByOrderByPercentageDesc() {
		return stu.findTop5ByOrderByPercentageDesc();
	}
	
	public List<Student> findTopperByStandard(int std) {
		return stu.findTopperByStandard(std);
	}
}	
