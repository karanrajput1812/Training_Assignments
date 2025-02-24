package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.demo.models.ClassTeacher;
import com.example.demo.models.Student;
import com.example.demo.services.StudentService;
import com.example.demo.services.TeacherService;

class TeacherControllerTest {
	@MockitoBean
	private TeacherService tecService;
	
	@Autowired
	private MockMvc mockMvc;

//	@Test
//	void testGetClassTeacher() {
//		mockMvc.perform(MockMvcRequestBuilders.get("/students/topper/10")).andExpect(MockMvcResultMatchers.status().is(200));
//		ClassTeacher cls;
//		Mockito.when(tecService.getClassTeacher(102)).thenReturn(cls);
//	}

}
