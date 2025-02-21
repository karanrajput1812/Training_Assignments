package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Student;
import com.example.demo.services.StudentService;

@RestController
public class StudentController {

	@Autowired
	StudentService studentService;
	
		
	@GetMapping("/students")
	public List<Student> getStudents() {
		return studentService.getStudents();
	}
	
	@PostMapping("/students")
	public String addStudents(@RequestBody Student s) {
		return studentService.addStudents(s);
	}
	
	@DeleteMapping("/students/{rollNo}")
	public String addStudents(@PathVariable int univRegNo) {
		return studentService.deleteStudentsById(univRegNo);
	}
	
	@PostMapping("/demostudents")
	public String addDemoStudents(@RequestBody List<Student> list) {
		return studentService.addDemoStudents(list);
	}
	
	
    @GetMapping("/student/{rollNo}")
    public Student getStudentByRollNo(@PathVariable int univRegNo) {
        return studentService.getStudentByRollNo(univRegNo);
    }
    
    @GetMapping("/students/school")
    public List<Student> getStudentBySchoolName(@RequestParam String name) {
        return studentService.getStudentBySchoolName(name);
    }
    
    @GetMapping("/students/result")
    public List<Student> getStudentByPassFail(@RequestParam boolean pass) {
    	return studentService.getStudentByPassFail(pass);
    }
    @GetMapping("/students/{std}/count")
    public long getStudentByCount(@PathVariable int std) {
       		return studentService.getStudentByCount(std);
    }
    
    @GetMapping("/students/strength")
    public long getTotalStrengthBySchool(@RequestParam String school) {
            return studentService.getstrengthBySchool(school);
    }
    	
    @GetMapping("/students/toppers")
    public List<Student> getTopperStudents() { 
        return studentService.findTop5ByOrderByPercentageDesc();
    }
	
    @GetMapping("/students/topper/{std}")
    public List<Student> getTopperByStandard(@PathVariable int std) {
        return studentService.findTopperByStandard(std);
    }
    

	
}
