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
import com.example.demo.service.EmployeeService;
 
@RestController
public class EmpController {
 
	@Autowired
	EmployeeService service;
	
	
	@GetMapping("/employees")
	public Iterable<Employee> getEmployees(){
		return service.getEmployees();
	}
	
	 @GetMapping("/employees/{id}")
	 public Optional<Employee> getEmployeeById(@RequestParam int id){
		return service.getEmployeeById(id);
		
	 }
	
	 @PostMapping("/employees")
		public String insertEmployees(@RequestBody Employee e){
//		 if(dao.existsById(e.getEid())) {
//			 return "sorry the employee already exists, choose another ID";
//		 }
		 service.insertEmployees(e);
			return "Added new Employee Successfully !";
		}
	
	 @RequestMapping(path = "/update/{id}",method = {RequestMethod.PUT,RequestMethod.PATCH})
	 public String updateEmployee(@RequestBody Employee e, @PathVariable int id ) {
		 service.updateEmployee(e, id);
		 return "Updated record successfully";
	 }
	
	 @DeleteMapping("/employees/{id}")
	 public String deleteEmployee(@PathVariable int id ) {
		 service.deleteEmployee(id);
		 return "Successfully deleted employee record !";
	 }
	
	 @GetMapping("/employees/custom")
	 public List<Employee> getCustomQuery(String desig) {
		return service.getCustomQuery(desig);
		
	 }
	
	 @GetMapping("/employees/below")
	 public List<Employee> getEmployeeBelow(int age){
		 return service.getEmployeeBelow(age);
	 }
	 @GetMapping("/employees/above")
	 public List<Employee> getEmployeeAbove(int age){
		 return service.getEmployeeAbove(age);
	 }
	 @GetMapping("/employees/role")
	 public List<Employee> getEmployeeByDesig(String desig){
		 return service.getEmployeeByDesig(desig);
	 }
	
	
	
	
	
}