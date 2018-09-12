package com.thoughtworks.trains.calculators;

import com.thoughtworks.trains.model.Graph;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ShortRouteCalculatorTest
{
	private Graph graph;

	private ShortRouteCalculator src;

	@Before
	public void setup()
	{
		String graphStr = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
		graph = new Graph(graphStr);
		src = new ShortRouteCalculator(graph);
	}

	@Test
	public void shortestRouteDistanceFromAtoCShouldEqualNine()
	{
		assertTrue(src.calculateShortestRouteFromSourceToDest(graph.getTownByName("A"), graph.getTownByName("C"), true) == 9);
	}

	@Test
	public void shortestRouteDistanceFromBtoBShouldEqualNine()
	{
		assertTrue(src.calculateShortestRouteFromSourceToDest(graph.getTownByName("B"), graph.getTownByName("B"), true) == 9);
	}
}
