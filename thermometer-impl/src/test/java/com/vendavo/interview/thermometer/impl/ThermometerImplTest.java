package com.vendavo.interview.thermometer.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.vendavo.interview.thermometer.Color;
import com.vendavo.interview.thermometer.Thermometer;

class ThermometerImplTest {

	Thermometer therm;
	
	@BeforeEach
	void setup() {
		therm = new ThermometerImpl();
	}
	
	@Test
	void testError() {
		assertThrows(IllegalAccessException.class, () -> therm.measure(Double.NaN));
	}
	
	@Test
	void testRed() throws IllegalAccessException {
		assertEquals(Color.RED, therm.measure(123456789d));
	}
	
	@Test
	void testOrange() throws IllegalAccessException {
		assertEquals(Color.ORANGE, therm.measure(36d));
	}
	
	@Test
	void testYellow() throws IllegalAccessException {
		assertEquals(Color.YELLOW, therm.measure(30d));
	}
	
	@Test
	void testGreen() throws IllegalAccessException {
		assertEquals(Color.GREEN, therm.measure(15d));
	}
	
	@Test
	void testLightBlue() throws IllegalAccessException {
		assertEquals(Color.LIGHT_BLUE, therm.measure(7d));
	}
	
	@Test
	void testBlue() throws IllegalAccessException {
		assertEquals(Color.BLUE, therm.measure(0d));
	}
	
	@Test
	void testDarkBlue() throws IllegalAccessException {
		assertEquals(Color.DARK_BLUE, therm.measure(-123456789d));
	}

}
