package com;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
class AbcTest {
	
	@Test
	void testInterfaceMethod() {
		
//		2.  How to test an interface without having an access to its implementation
		A a1 = mock(A.class);
//		System.out.println(a1);
		
//		1.	How to test a method which has return type void
//		verify(a1, times(1)).xyz();
	
//		3.	How to test for the method been called exactly for 3 times
		a1.xyz();
		a1.xyz();
		a1.xyz();
		verify(a1, times(3)).xyz();
		
	}
}
