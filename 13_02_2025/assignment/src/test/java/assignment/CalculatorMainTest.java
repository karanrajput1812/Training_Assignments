package assignment;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.verification.VerificationMode;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


@TestInstance(Lifecycle.PER_CLASS)
class CalculatorMainTest {

	static Calculator c1;
	@BeforeAll
	void createCalculator() {
		c1 = new Calculator();
		System.out.println("------ STARTED ------");
	}
	
	@AfterAll
	static void removeCalculator()
	{
		c1 = null;
		System.out.println("------ FINISHED ------");
	}
	
	
	@Test
    void testAdd() {
		
		assertEquals(30, c1.add(10, 20), ()->"Sum result is not right");
        
    }

	@Test
    void testSub() {
		assertEquals(-10, c1.sub(10, 20), ()->"Difference result is not right");
        
    }

	@Test
    void testMul() {
		assertEquals(200, c1.mul(10, 20), ()->"Multiply result is not right");
        
    }
	

}



