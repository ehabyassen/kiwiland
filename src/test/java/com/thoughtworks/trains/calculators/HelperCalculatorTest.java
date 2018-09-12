package com.thoughtworks.trains.calculators;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HelperCalculatorTest
{
	private HelperCalculator hc;

	@Before
	public void init()
	{
		hc = new HelperCalculator();
	}

	@Test
	public void shouldEqualTrue()
	{
		assertTrue(hc.evaluateCondition(3, 5, "<"));
	}

	@Test
	public void shouldEqualFalse()
	{
		assertFalse(hc.evaluateCondition(3, 2, "<"));
	}
}
