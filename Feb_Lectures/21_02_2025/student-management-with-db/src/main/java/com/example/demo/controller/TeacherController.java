package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.models.ClassTeacher;
import com.example.demo.services.TeacherService;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @PostMapping("/add")
    public String addTeacher(@RequestBody ClassTeacher cls) {
        return teacherService.addTeacher(cls);
    }

    @GetMapping("/all")
    public List<ClassTeacher> getTeacher() {
        return teacherService.getTeacher();
    }

    @GetMapping("/student_class_teacher")
    public ClassTeacher getClassTeacher(@RequestParam int univRegNo) {
        return teacherService.getClassTeacher(univRegNo);
    }
}
