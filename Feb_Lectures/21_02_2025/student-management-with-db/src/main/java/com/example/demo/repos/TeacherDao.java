package com.example.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.ClassTeacher;

@Repository
public interface TeacherDao extends JpaRepository<ClassTeacher, Integer> {
    @Query("SELECT ct FROM ClassTeacher ct WHERE ct.std = (SELECT s.standard FROM Student s WHERE s.univRegNo = ?1)")
    public ClassTeacher findClassTeacherByUnivReg(int univRegNo);
}
