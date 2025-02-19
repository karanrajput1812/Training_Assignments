package com.example.demo.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Mycontroller {

	@GetMapping(path = "/", produces ="text/html")
	public String abc() {
		return "<h1>Welcome to the REST App</h1>";
	}
	
	@GetMapping("/greet")
	public String greet1() {
		return "<h1>Hava a Good Day :) : Get</h1>";
	}
	
	@PostMapping("/greet")
	public String greet2() {
		return "<h1>Hava a Great Day ahead :) : Post</h1>";
	}
	@PutMapping("/greet")
	public String greet3() {
		return "<h1>Hava a Great Day ahead :) : Put</h1>";
	}
	@DeleteMapping("/greet")
	public String greet4() {
		return "<h1>Hava a Great Day ahead :) : Delete</h1>";
	}
	@PatchMapping("/greet")
	public String greet5() {
		return "<h1>Hava a Great Day ahead :) : Patch</h1>";
	}
}
