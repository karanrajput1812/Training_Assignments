package com;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CalculatorTest {

    @Test
    public void testAdd() {
        Calculator c1 = new Calculator();
	assertEquals(4, c1.add(2,2), "The sum of 2 and 2 is not resulting in 4");
	assertEquals(8, c1.add(4,4), "The sum of 4 and 4 is not resulting in 8");

    }

    @Test
    public void testSub() {
        Calculator c1 = new Calculator();
	assertEquals(0, c1.sub(2,2), "The difference of 2 and 2 is not resulting in 0");
    }

}
