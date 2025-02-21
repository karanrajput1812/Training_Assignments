package com.example.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.ClassTeacher;

public interface TeacherDao extends JpaRepository<ClassTeacher, Integer>{

    @Query("SELECT ct FROM ClassTeacher ct JOIN Student s ON ct.std = s.standard WHERE s.univRegNo = ?1")
    public ClassTeacher findClassTeacherByUnivReg(int univRegNo);
}