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
import org.junit.jupiter.api.Test;

@DisplayName("Testing Arithmetic Operation")
class CalculatorTest {
	static Calculator c1;

	@BeforeAll
	static void createCalculator()
	{
		c1 = new Calculator();
		System.out.println("------ STARTED ------");
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
	void test() {
		assertTrue(true);
	}
	
	@Test
	@DisplayName("Testing Addition Operation")
	void testAdd() {
		assertEquals(30, c1.add(10, 20), ()->"Sum result is not right");
		assertEquals(50, c1.add(30, 20), ()->"Sum result is not right");
		assertEquals(-10, c1.add(10, -20), ()->"Sum result is not right");
		System.out.println("From testAdd() method");
	}
	
	@Test
	@DisplayName("Testing Division Operation")
	void testDiv() throws ArithmeticException{
		assertEquals(2, c1.div(10,5));
		assertThrows(ArithmeticException.class, ()-> c1.div(10, 0));
		System.out.println("From testDiv() method");
	}
	
	@Test
	@DisplayName("Testing Multiply Operation")
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

}
