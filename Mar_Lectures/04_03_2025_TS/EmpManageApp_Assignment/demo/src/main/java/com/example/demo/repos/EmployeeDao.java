package com.example.demo.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Integer>{
	
	@Query("UPDATE Employee e SET e.salary = ?2 WHERE e.eid = ?1")
    public Employee updateSalary(int eid, float salary);

	public List<Employee> findByName(String name);

	public List<Employee> findByDesignation(String designation);
	
	public List<Employee> findByDepartment(String department);
}
 