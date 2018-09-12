package com.thoughtworks.trains.calculators;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//The "RouteDistanceCalculator" class which solves the problems 1, 2, 3, 4, 5.
public class RouteDistanceCalculator
{
	/*
	Routes map represents the routes in the graph and the distance of each route.
	Empty map in the initial state of the application.
	Will be manipulated to add routes while initializing the Graph.
	*/
	private Map<String, Integer> routesDistancesMap;

	//A Constructor for the RouteDistanceCalculator class which initializes the RoutesDistances map.
	public RouteDistanceCalculator(Map<String, Integer> routesDistancesMap)
	{
		this.routesDistancesMap = routesDistancesMap;
	}

	//Returns the String representation of a route distance.
	public String getRouteDistance(String route)
	{
		int routeDistance = calculateRouteDistance(route);
		return (routeDistance > 0) ? String.valueOf(routeDistance) : "NO SUCH ROUTE";
	}

	//Calculates the distance of a given route by validating the sub-routes and summing their distance values.
	int calculateRouteDistance(String route)
	{
		int routeDistance = 0;

		for (String subRoute : getSubRoutes(route))
		{
			if (checkIfRouteExists(subRoute))
			{
				routeDistance += routesDistancesMap.get(subRoute);
			}
			else
			{
				return -1;
			}
		}
		return routeDistance;
	}

	//Breaks the full route into sub-routes.
	private List<String> getSubRoutes(String route)
	{
		List<String> subRoutes = new ArrayList<>();
		String cleanRoute = route.replaceAll("-", "");

		for (int i = 0; i < cleanRoute.length() - 1; i++)
		{
			subRoutes.add(cleanRoute.substring(i, i + 2));
		}

		return subRoutes;
	}

	//Validates that a sub-route -direct route between two towns- exists.
	private boolean checkIfRouteExists(String subRoute)
	{
		return routesDistancesMap.containsKey(subRoute);
	}
}
