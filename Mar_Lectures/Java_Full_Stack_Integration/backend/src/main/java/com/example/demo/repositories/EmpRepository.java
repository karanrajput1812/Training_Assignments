package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Emp;

public interface EmpRepository extends JpaRepository<Emp, Integer>{
	
}
