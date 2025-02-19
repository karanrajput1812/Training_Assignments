package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Person;

@RestController
public class ParameterType {

	@GetMapping("/query")
	public String queryParameterDemo(@RequestParam("user") String name,@RequestParam int age) {
//		Both above has same functionality "user" takes all user= from the url as a name
		System.out.println(" *** Query Parameter ***");
		System.out.println(" Name: " + name);
		System.out.println(" Age: " + age);
		return "Welcome !! " + name + " and you are " + age + " years old. Query Sucessful";
	}
	
	
	@GetMapping("/path/{name}/{age}")
	public String pathParameterDemo(@PathVariable String name, @PathVariable int age) {
		System.out.println(" *** Path Parameter ***");
		System.out.println(" Name: " + name);
		System.out.println(" Age: " + age);
		return "Welcome !! " + name + " and you are " + age + " years old. Path Sucessful";
	}
	
	@GetMapping("/body")
	public String bodyParameterDemo(@RequestBody Person p) {
		System.out.println(" *** Body Parameter ***");
		System.out.println(" Name: " + p.getName());
		System.out.println(" Age: " + p.getAge());
		return "Welcome !! " + p.getName() + " and you are " + p.getAge() + " years old. Body Sucessful";
	}
	 
	@GetMapping(path="/persons", produces = "application/xml")
	public List<Person> getpersons() {
		List<Person> list = new ArrayList<Person>();
		list.add(new Person("Pooja",25));
		list.add(new Person("Sammer",37));
		list.add(new Person("Karan",33));
		list.add(new Person("Madhav",31));
		list.add(new Person("Sanat",20));
		list.add(new Person("John",29));
		
		return list;
	}
}
