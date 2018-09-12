package com.thoughtworks.trains.calculators;

import com.thoughtworks.trains.model.Graph;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class RouteDistanceCalculatorTest
{
	private RouteDistanceCalculator rdc;

	@Before
	public void setup()
	{
		String graphStr = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
		Graph graph = new Graph(graphStr);
		rdc = new RouteDistanceCalculator(graph.getRoutesDistancesMap());
	}

	@Test
	public void routeABCDistanceShouldEqualNine()
	{
		assertTrue(rdc.getRouteDistance("A-B-C").equals("9"));
	}

	@Test
	public void routeADDistanceShouldEqualFive()
	{
		assertTrue(rdc.getRouteDistance("A-D").equals("5"));
	}

	@Test
	public void routeADCDistanceShouldEqualThirteen()
	{
		assertTrue(rdc.getRouteDistance("A-D-C").equals("13"));
	}

	@Test
	public void routeAEBCDDistanceShouldEqualTwentyTwo()
	{
		assertTrue(rdc.getRouteDistance("A-E-B-C-D").equals("22"));
	}

	@Test
	public void shouldNotFindRouteAED()
	{
		assertTrue(rdc.getRouteDistance("A-E-D").equals("NO SUCH ROUTE"));
	}
}
