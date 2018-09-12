package com.thoughtworks.trains.calculators;

import com.thoughtworks.trains.model.Graph;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class RoutesCalculatorTest
{
	private Graph graph;

	private RoutesCalculator rc;

	@Before
	public void setup()
	{
		String graphStr = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
		graph = new Graph(graphStr);
		rc = new RoutesCalculator(graph);
	}

	@Test
	public void routesNumberFromCtoCWithDistanceLessThanThirtyShouldEqualSeven()
	{
		assertTrue(rc.calculateRoutesFromSourceToDest(graph.getTownByName("C"), graph.getTownByName("C"), "<", 30) == 7);
	}
}
