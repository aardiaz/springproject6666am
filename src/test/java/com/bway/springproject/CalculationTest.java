package com.bway.springproject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.test.Calculation;

public class CalculationTest {
	
	@org.junit.jupiter.api.Test
	public void testArea() {
		
		Calculation  cal = new Calculation();
		int res = cal.getArea(20, 10);
		
		assertEquals(200, res);
	}
	
	@org.junit.jupiter.api.Test
	public  void testSi() {
		
		Calculation  cal = new Calculation();
		int res = cal.getSi(5000, 10, 5);
		assertEquals(2500, res);
	}

}
