package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.demo.entities.Employee;
import com.example.demo.service.EmployeeService;

@WebMvcTest(controllers = {EmpController.class})
public class EmpControllerTest {

	@MockitoBean
	private EmployeeService empService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testGetEmployee() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.get("/employees")).andExpect(MockMvcResultMatchers.status().is(200));
		
		
	}
	
	
	@Test
	public void testGetEmployeeById() throws Exception
	{
		List<Employee> list = new ArrayList<Employee>();
		list.add(new Employee());
		Mockito.when(empService.getEmployeeBelow(26)).thenReturn(list);
	}

}
