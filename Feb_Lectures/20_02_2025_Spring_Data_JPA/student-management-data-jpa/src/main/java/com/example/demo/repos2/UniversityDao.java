package com.example.demo.repos2;
 
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities2.UniversityStudent;

 
public interface UniversityDao extends JpaRepository<UniversityStudent, Integer>{
	
}