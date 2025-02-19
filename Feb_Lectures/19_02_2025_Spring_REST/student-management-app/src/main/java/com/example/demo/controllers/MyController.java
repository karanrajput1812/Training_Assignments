package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Student;

import jakarta.websocket.server.PathParam;

@RestController
public class MyController {
	
	List<Student> list = new ArrayList<Student>();
	public MyController() {
		Random random = new Random();
        String[] names = { "Karan", "Madhav", "Sanat", "Crystal", "Johny", "Omkar", "Sarvesh", "Sanchit", "Ramesh", "Rohan" };
        String[] schools = { "PVM", "CRCE", "DPS", "VVMS", "KV" };
        for (int i = 0; i < 20; i++) {
            Student student = new Student();
            student.setName(names[random.nextInt(names.length)]);
            student.setRollNo(random.nextInt(30));
            student.setStandard(random.nextInt(12) + 1);
            student.setSchool(schools[random.nextInt(schools.length)]);
            student.setPercentage(random.nextFloat() * 100);
            list.add(student);
        }
        System.out.println(list);
	}
	
//	/students			 - get all students
//	/student/rollNo			 - get specific student with given rollNo
//	/students/school?name=DPS	 - get all students of that school
//	/students/result?pass=true/false - all students above 40% / below 40%
//	/students/5/count		 - how many students in 5th standard
//	/students/strength?school=DPS	 - total strength for given school name
//	/students/toppers		 - top 5 percentage students
//	/students/topper/3		 - topper of 3rd standard
	
	
	@GetMapping("/")
	public List<Student> getStudents() {
		return list;
	}
	
	
    @GetMapping("/student/{rollNo}")
    public Student getStudentByRollNo(@PathVariable int rollNo) {
        return list.stream()
            .filter(student -> student.getRollNo() == rollNo)
            .findFirst()
            .orElse(null);
    }
    
    @GetMapping("/students/school")
    public List<Student> getStudentBySchoolName(@RequestParam String name) {
        return list.stream()
                .filter(student -> student.getSchool().equals(name))
                .collect(Collectors.toList());
    }
    
    @GetMapping("/students/result")
    public List<Student> getStudentByPassFail(@RequestParam boolean pass) {
    	if(pass=true)
    	{
    		return list.stream()
    				.filter(student -> student.getPercentage() >= 40)
    				.collect(Collectors.toList());
    	}
    	return list.stream()
				.filter(student -> student.getPercentage() < 40)
				.collect(Collectors.toList());
    }
    
    @GetMapping("/students/{std}/count")
    public List<Student> getStudentByPassFail(@PathVariable int std) {
       		return list.stream()
    				.filter(student -> student.getStandard() == std)
    				.collect(Collectors.toList());
    }
    
    @GetMapping("/students/strength")
    public long getTotalStrengthBySchool(@RequestParam String school) {
            return list.stream()
                    .filter(student -> student.getSchool().equals(school))
                    .collect(Collectors.counting());
    }
    	
    @GetMapping("/students/toppers")
    public List<Student> getTopperStudents() {
        return list.stream()
                    .sorted(Comparator.comparingDouble(Student::getPercentage).reversed())
                    .limit(5)
                    .collect(Collectors.toList());
    }
	
    @GetMapping("/students/topper/{std}")
    public Student getTopperByStandard(@PathVariable int std) {
        return list.stream()
                  .filter(student -> student.getStandard() == std)
                  .sorted(Comparator.comparingDouble(Student::getPercentage).reversed())
                  .findFirst()
                  .orElse(null);
    }
}
