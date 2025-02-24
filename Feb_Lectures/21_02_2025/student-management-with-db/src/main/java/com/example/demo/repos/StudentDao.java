package com.example.demo.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.Student;

public interface StudentDao extends JpaRepository<Student, Integer>{

	public List<Student> getStudentBySchool(String school);

	public List<Student> findByPercentageGreaterThan(int percent);
	public List<Student> findByPercentageLessThan(int percent);
	
	@Query("SELECT COUNT(s) FROM Student s WHERE s.standard = ?1")
	public long getCountByStandard(int std);
	
	@Query("SELECT COUNT(s) FROM Student s WHERE s.school = ?1")
	public long getstrengthBySchool(String school);
	
	
	@Query("FROM Student ORDER BY percentage DESC LIMIT 5")
	public List<Student> findTop5ByOrderByPercentageDesc();

	@Query("FROM Student WHERE standard = ?1 ORDER BY percentage DESC")
	public List<Student> findTopperByStandard(int std);
	
	public Student findByUnivRegNo(int univRegNo);
}
