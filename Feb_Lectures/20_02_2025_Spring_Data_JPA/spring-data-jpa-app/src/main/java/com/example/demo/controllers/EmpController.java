package com.example.demo.controllers;
 
import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
 
import com.example.demo.entities.Employee;
import com.example.demo.repos.EmpDao;
 
@RestController
public class EmpController {
 
	@Autowired
	EmpDao dao;
	@GetMapping("/employees")
	public Iterable<Employee> getEmployees(){
		return dao.findAll();
	}
	
	 @GetMapping("/employees/{id}")
	 public Optional<Employee> getEmployeeById(@RequestParam int id){
		return dao.findById(id);
		
	 }
	
	 @PostMapping("/employees")
		public String insertEmployees(@RequestBody Employee e){
		 if(dao.existsById(e.getEid())) {
			 return "sorry the employee already exists, choose another ID";
		 }
		 dao.save(e);
			return "Added new Employee Successfully !";
		}
	
	 @RequestMapping(path = "/update/{id}",method = {RequestMethod.PUT,RequestMethod.PATCH})
	 public String updateEmployee(@RequestBody Employee e, @PathVariable int id ) {
		 if(!dao.existsById(id)) {
			 return "sorry the employee does not exists, choose another ID to update";
		 }
		 dao.save(e);
		 return "Updated record successfully";
	 }
	
	 @DeleteMapping("/employees/{id}")
	 public String deleteEmployee(@PathVariable int id ) {
		 if(!dao.existsById(id)) {
			 return "sorry the employee does not exists, choose another ID to delete";
		 }
		 dao.deleteById(id);
		 return "Successfully deleted employee record !";
	 }
	
	 @GetMapping("/employees/custom")
	 public List<Employee> getCustomQuery(String desig) {
		return dao.myCustomQuery(desig);
		
	 }
	
	 @GetMapping("/employees/below")
	 public List<Employee> getEmployeeBelow(int age){
		 return dao.findByAgeLessThan(age);
	 }
	 @GetMapping("/employees/above")
	 public List<Employee> getEmployeeAbove(int age){
		 return dao.findByAgeGreaterThan(age);
	 }
	 @GetMapping("/employees/role")
	 public List<Employee> getEmployeeByDesig(String desig){
		 return dao.getEmployeeByDesignation(desig);
	 }
	
	
	
	
	
}