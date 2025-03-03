package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Employee;
import com.example.demo.repos.EmployeeDao;

@Service
public class EmployeeServices {

	@Autowired
	EmployeeDao emp;
	
	public List<Employee> getAllEmployee(){
		return emp.findAll();
	}
	
	public String saveEmployee(Employee e) {
		emp.save(e);
		return "Employee Added Succesfully";
	}
	
	public String deleteEmployee(int eid) {
		emp.deleteById(eid);
		return "Employee Deleted Succesfully";
	}
	public String updateSalary(int eid, float salary) {
		emp.updateSalary(eid, salary);
		return "Employee doesn't exist";
	}
	public Optional<Employee> getById(int eid) {
		return emp.findById(eid);
	}
	
	public List<Employee> getByName(String name) {
		return emp.findByName(name);
	}
	
	public List<Employee> getByDesignation(String designation) {
		return emp.findByDesignation(designation);
	}
	
	public List<Employee> getByDepartment(String department) {
		return emp.findByDepartment(department);
	}
	
}
