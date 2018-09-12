package com.thoughtworks.trains.model;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

/*
The "Graph" class represents the towns and the routes between them in the design model of the solution of the trains problem
using in a form of an adjacency list and applies Dijkstra algorithm.
A Graph has the following parameters:
- Set of the towns with information about each town and its neighbors.
- A {key, value} map that represents the routes between the towns and the distance of each route.
*/
public class Graph
{
	/*
	String representation of input data to build a Graph
	 */
	private String graphStr;

	/*
	Towns set.
	Empty set in the initial state of the application.
	Will be manipulated to add towns while initializing the Graph.
	*/
	private Set<Town> towns = new HashSet<>();

	/*
	Routes map represents the routes in the graph and the distance of each route.
	Empty map in the initial state of the application.
	Will be manipulated to add routes while initializing the Graph.
	*/
	private Map<String, Integer> routesDistancesMap;

	//A Constructor for the Graph class. Delegates the graph building to the init method.
	public Graph(String graphStr)
	{
		this.graphStr = graphStr;
		init();
	}

	//Delegates the building of the RouteDistanceMap and the Towns to the respective methods.
	private void init()
	{
		buildRoutesDistancesMap(graphStr);
		buildTowns();
	}

	//Builds the {routes, distances} map.
	private void buildRoutesDistancesMap(String graphStr)
	{
		try
		{
			routesDistancesMap = stream(graphStr.split(", ")).collect(
				Collectors.toMap(x -> x.substring(0, 2), x -> Integer.parseInt(x.substring(2))));
		}
		catch (Exception e)
		{
			System.out.println("Input data is not in the right form!");
			System.exit(1);
		}
	}

	//Builds the towns & neighbors.
	private void buildTowns()
	{
		Town town;
		Town neighbor;

		for (String townName : getTownsNames())
		{
			town = new Town(townName);

			for (String route : routesDistancesMap.keySet())
			{
				if (route.startsWith(townName))
				{
					neighbor = new Town(route.substring(1));
					town.addNeighbor(neighbor, routesDistancesMap.get(route));
				}
			}

			towns.add(town);
		}
	}

	//Returns a distinct, sorted list of Strings of the towns names in a Graph.
	private List<String> getTownsNames()
	{
		List<String> townsList = Arrays.asList(String.join("", new ArrayList<>(routesDistancesMap.keySet())).split(""));
		Set<String> townsSet = new LinkedHashSet<>(townsList);
		townsList = new ArrayList<>(townsSet);
		Collections.sort(townsList);
		return townsList;
	}

	//Resets the info of the towns in a Graph
	public void reset()
	{
		towns.clear();
		init();
	}

	//<Getters>
	public Town getTownByName(String name)
	{
		for (Town t : towns)
		{
			if (t.getName().equals(name))
			{
				return t;
			}
		}
		return null;
	}

	public Map<String, Integer> getRoutesDistancesMap()
	{
		return routesDistancesMap;
	}
	//</Getters>
}
