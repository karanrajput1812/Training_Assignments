package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.ClassTeacher;
import com.example.demo.models.Student;
import com.example.demo.repos.StudentDao;
import com.example.demo.repos.TeacherDao;

@Service
public class TeacherService {
    
    @Autowired
    private TeacherDao teacherDao;

    public String addTeacher(ClassTeacher cls) {
        teacherDao.save(cls);
        return "Teacher Added Successfully";
    }

    public List<ClassTeacher> getTeacher() {
        return teacherDao.findAll();
    }

//    public ClassTeacher getClassTeacher(int univRegNo) {
//        return teacherDao.findClassTeacherByUnivReg(univRegNo);
//    }
    
    @Autowired
    StudentDao studentDao;
    
    public ClassTeacher getClassTeacher(int univRegNo) {
        Student student = studentDao.findByUnivRegNo(univRegNo);
        if (student == null || student.getClassTeacher() == null) {
            throw new RuntimeException("Class Teacher not found for student with UnivRegNo: " + univRegNo);
        }
        return student.getClassTeacher();
    }
}
