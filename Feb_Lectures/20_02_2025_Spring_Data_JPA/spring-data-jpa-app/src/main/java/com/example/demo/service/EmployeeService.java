package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.Employee;
import com.example.demo.repos.EmpDao;


@Service
public class EmployeeService {
	@Autowired
	EmpDao dao;
	
	public Iterable<Employee> getEmployees(){
		return dao.findAll();
	}
	
	 public Optional<Employee> getEmployeeById(int id){
		return dao.findById(id);
		
	 }
	
		public String insertEmployees(Employee e){
		 if(dao.existsById(e.getEid())) {
			 return "sorry the employee already exists, choose another ID";
		 }
		 dao.save(e);
			return "Added new Employee Successfully !";
		}
	
	 public String updateEmployee(Employee e, int id ) {
		 if(!dao.existsById(id)) {
			 return "sorry the employee does not exists, choose another ID to update";
		 }
		 dao.save(e);
		 return "Updated record successfully";
	 }
	
	 public String deleteEmployee(int id ) {
		 if(!dao.existsById(id)) {
			 return "sorry the employee does not exists, choose another ID to delete";
		 }
		 dao.deleteById(id);
		 return "Successfully deleted employee record !";
	 }
	
	 public List<Employee> getCustomQuery(String desig) {
		return dao.myCustomQuery(desig);
		
	 }
	
	 public List<Employee> getEmployeeBelow(int age){
		 return dao.findByAgeLessThan(age);
	 }
	 
	 public List<Employee> getEmployeeAbove(int age){
		 return dao.findByAgeGreaterThan(age);
	 }
	 public List<Employee> getEmployeeByDesig(String desig){
		 return dao.getEmployeeByDesignation(desig);
	 }
}
