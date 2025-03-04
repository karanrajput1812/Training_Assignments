package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Employee;
import com.example.demo.services.EmployeeServices;

@RestController
public class EmployeeController {	

	@Autowired
	EmployeeServices empService;
	
	@GetMapping("/allEmployees")
	public List<Employee> getAllEmployees(){
		return empService.getAllEmployee();
	}
	
	@PostMapping("/saveEmployee")
	public String saveEmployee(@RequestBody Employee emp) { 
		return empService.saveEmployee(emp);
	}
	
	@DeleteMapping("/deleteEmployee/{eid}")
	public String deleteEmployee(@PathVariable int eid) {
		return empService.deleteEmployee(eid);
	}
	@PutMapping("/updateSalary")
	public String updateSalary(@RequestParam int eid, @RequestParam float salary) {
		return empService.updateSalary(eid, salary);
	}
	}
	
	@GetMapping("/getById/{eid}")
	public Optional<Employee> getById(@PathVariable int eid){
		return empService.getById(eid);
	}
	
	@GetMapping("/getByName/{name}")
	public List<Employee> getByName(@PathVariable String name){
		return empService.getByName(name);
	}
	
	@GetMapping("/getByDesignation/{designation}")
	public List<Employee> getByDesignation(@PathVariable String designation){
		return empService.getByDesignation(designation);
	}
	
	@GetMapping("/getByDepartment/{department}")
	public List<Employee> getByDepartment(@PathVariable String department){
		return empService.getByDepartment(department);
	}
} 
