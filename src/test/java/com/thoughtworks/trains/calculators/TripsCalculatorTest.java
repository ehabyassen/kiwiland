package com.thoughtworks.trains.calculators;

import com.thoughtworks.trains.model.Graph;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TripsCalculatorTest
{
	private Graph graph;

	private TripsCalculator tc;

	@Before
	public void init()
	{
		String graphStr = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
		graph = new Graph(graphStr);
		tc = new TripsCalculator(graph);
	}

	@Test
	public void tripsFromCtoCWithMaxNumberOfThreeStopsShouldEqualTwo()
	{
		assertTrue(tc.getNumberOfTripsBetweenTwoTowns(graph.getTownByName("C"), graph.getTownByName("C"), "<=", 3) == 2);
	}

	@Test
	public void tripsFromAtoCWithNumberOfFourStopsShouldEqualThree()
	{
		assertTrue(tc.getNumberOfTripsBetweenTwoTowns(graph.getTownByName("A"), graph.getTownByName("C"), "=", 4) == 3);
	}
}
