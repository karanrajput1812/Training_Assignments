package com;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

import static org.junit.jupiter.api.Assumptions.*;

@DisplayName("Testing Arithmetic Operation")
@TestInstance(Lifecycle.PER_METHOD)
class CalculatorTest {
	
//	CalculatorTest() {
//		System.out.println("<======== CalculatorTest() Object Created .....");
//	}
	
	static Calculator c1;
	static int x = 2;
	static boolean condition = false;

	@BeforeAll
	static void createCalculator()
	{
		c1 = new Calculator();
		System.out.println("------ STARTED ------");
		if(x!=0) {
			condition = true;
		}
	}
	
	@AfterAll
	static void removeCalculator()
	{
		c1 = null;
		System.out.println("------ FINISHED ------");
	}
	
	@BeforeEach
	void abc()
	{
		System.out.println("..... Before Each Test Case .....");
	}
	
	@AfterEach
	void xyz()
	{
		System.out.println("..... After Each Test Case .....");
	}
	
	@Test
	@Disabled
	@Tag("demo")
	void test() {
		assertTrue(true);
	}
	
	@Test
	@DisplayName("Testing Addition Operation")
	@Tag("math")
	void testAdd() {
		assertEquals(30, c1.add(10, 20), ()->"Sum result is not right");
		assertEquals(50, c1.add(30, 20), ()->"Sum result is not right");
		assertEquals(-10, c1.add(10, -20), ()->"Sum result is not right");
		System.out.println("From testAdd() method");
	}
	
	@Test
	@DisplayName("Testing Division Operation")
	@Tag("math")
	void testDiv() throws ArithmeticException{
		assertEquals(2, c1.div(10,5));
		assertThrows(ArithmeticException.class, ()-> c1.div(10, 0));
		System.out.println("From testDiv() method");
	}
	
	@Test
	@DisplayName("Testing Multiply Operation")
	@Tag("math")
	void testMul() {
		System.out.println("From testMulStart() method");
		assertAll(	
		() -> assertEquals(50, c1.mul(10,5)),
		() -> assertEquals(100, c1.mul(20,5)),
		() -> assertEquals(60, c1.mul(10,6)),
		() -> assertEquals(90, c1.mul(10,9)),
		() -> assertEquals(100, c1.mul(20,5)),
		() -> assertEquals(30, c1.mul(6,5))
		);
		System.out.println("From testMulEnd() method");
	}
	
	@Test
	@Tag("math")
	void testMod() {
		assertAll(	
				() -> assertEquals(0, c1.mod(10,5)),
				() -> assertEquals(0, c1.mod(20,5)),
				() -> assertEquals(4, c1.mod(10,6)),
				() -> assertEquals(1, c1.mod(10,9)),
				() -> assertEquals(2, c1.mod(20,6)),
				() -> assertEquals(0, c1.mod(6,3))
				);
	}
	
	@Test
	@EnabledOnJre(value = JRE.JAVA_8)
	@Tag("demo")
	void testLambda() {
		System.out.println("Testing for Lambda expressions");
	}
	
	@Test
	@EnabledOnOs(OS.WINDOWS)
	@Tag("os")
	void testDll()
	{
		System.out.println("Testing DLL files");
	}
	
	@Test
	@EnabledOnOs(value = {OS.LINUX, OS.MAC})
	@Tag("os")
	void testShellScripts()
	{
		System.out.println("Testing Shell Scripts");
	}
	
	@Test
	@Tag("demo")
	@Tag("math")
	void testDynamically()
	{
		System.out.println("Testing Dynamically started");
		assertEquals(5, c1.div(10,x));
		assumeTrue(condition);
		assertEquals(15, c1.div(30,x));
		assertEquals(5, c1.div(10,x));
		System.out.println("Testing Dynamically finished");
	}
	
	@Test
	@Tag("db")
	void testInsertEmp()
	{
		System.out.println("Testing emp record insertion");
	}
	
	@Test
	@Tag("db")
	void testUpdateEmp()
	{
		System.out.println("Testing emp record updation");
	}
	
	@Test
	@Tag("db")
	void testDeleteEmp()
	{
		System.out.println("Testing emp record removal");
	}
	
	@Test
	@Tag("db")
	void testSelectEmp()
	{
		System.out.println("Testing emp record selection");
	}
	
}
