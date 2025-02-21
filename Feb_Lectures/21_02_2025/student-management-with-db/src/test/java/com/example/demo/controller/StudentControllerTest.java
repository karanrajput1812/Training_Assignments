package com.example.demo.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.demo.models.Student;
import com.example.demo.services.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = {StudentController.class})
class StudentControllerTest {

	@MockitoBean
	private StudentService stuService;
	
	@Autowired
	private MockMvc mockMvc;
	
	
	@Test
	void testGetStudents() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/students")).andExpect(MockMvcResultMatchers.status().is(200));
		List<Student> list = new ArrayList<Student>();
		list.add(new Student());
		Mockito.when(stuService.getStudents()).thenReturn(list);
	}

//	@Test
//	void testAddStudents() throws Exception {
//		Student student = new Student();
//		Mockito.when(stuService.addStudents(Mockito.any(Student.class))).thenReturn("Student Added Successfully");
//
//		mockMvc.perform(MockMvcRequestBuilders.post("/students")).andExpect(MockMvcResultMatchers.status().is(200));
//	}
//
//	@Test
//	void testAddDemoStudents() throws Exception {
//		List<Student> students = Arrays.asList(new Student(), new Student());
//		Mockito.when(stuService.addDemoStudents(Mockito.anyList())).thenReturn("Demo Student Added Successfully");
//
//		mockMvc.perform(MockMvcRequestBuilders.post("/demostudents")).andExpect(status().isOk());
//	}


	@Test
	void testGetStudentByRollNo() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/student/101")).andExpect(MockMvcResultMatchers.status().is(200));
		Student stu = new Student();
		Mockito.when(stuService.getStudentByRollNo(26)).thenReturn(stu);
	}

	@Test
	void testGetStudentBySchoolName() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/students/school").param("name", "PVM")).andExpect(MockMvcResultMatchers.status().is(200));
		List<Student> list = new ArrayList<Student>();
		list.add(new Student());
		Mockito.when(stuService.getStudentBySchoolName("PVM")).thenReturn(list);
	}

	@Test
	void testGetStudentByPassFail() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/students/result").param("pass", "true")).andExpect(MockMvcResultMatchers.status().is(200));
		List<Student> list = new ArrayList<Student>();
		list.add(new Student());
		Mockito.when(stuService.getStudentByPassFail(true)).thenReturn(list);
	}

	@Test
	void testGetStudentByCount() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/students/10/count")).andExpect(MockMvcResultMatchers.status().is(200));
		long count = 1;
		Mockito.when(stuService.getStudentByCount(9)).thenReturn(count);
	}

	@Test
	void testGetTotalStrengthBySchool() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/students/strength").param("school", "PVM")).andExpect(MockMvcResultMatchers.status().is(200));
		long strength = 2;
		Mockito.when(stuService.getstrengthBySchool("PVM")).thenReturn(strength);
	}

	@Test
	void testGetTopperStudents() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/students/toppers")).andExpect(MockMvcResultMatchers.status().is(200));
		List<Student> list = new ArrayList<Student>();
		list.add(new Student());
		Mockito.when(stuService.findTop5ByOrderByPercentageDesc()).thenReturn(list);
	}

	@Test
	void testGetTopperByStandard() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/students/topper/10")).andExpect(MockMvcResultMatchers.status().is(200));
		List<Student> list = new ArrayList<Student>();
		list.add(new Student());
		Mockito.when(stuService.findTopperByStandard(5)).thenReturn(list);
	}

}
