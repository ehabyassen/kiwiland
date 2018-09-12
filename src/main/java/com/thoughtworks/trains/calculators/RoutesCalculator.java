package com.thoughtworks.trains.calculators;

import com.thoughtworks.trains.model.Graph;
import com.thoughtworks.trains.model.Town;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
The "RoutesCalculator" class which solves problem number 10 using the Depth First Traversal algorithm to find all routes from a source node
in a graph to a destination node.
*/
public class RoutesCalculator
{
	//Reference of a Graph will be initialized after by the constructor.
	private Graph graph;

	//Reference of a HelperCalculator will be initialized after by the constructor.
	private HelperCalculator helperCalculator;

	//Reference of a RouteDistanceCalculator will be initialized after by the constructor.
	private RouteDistanceCalculator routeDistanceCalculator;

	//A Constructor for the RoutesCalculator class which initializes the Graph instance.
	public RoutesCalculator(Graph graph)
	{
		this.graph = graph;
		helperCalculator = new HelperCalculator();
		routeDistanceCalculator = new RouteDistanceCalculator(graph.getRoutesDistancesMap());
	}

	/*
	- Calculates the number of route between a source town to a destination town based on a route distance condition.
	- Initialization steps:
				- Initializes a list of lists which will store the list of routes between a source town and a destination town and every route is a
				list of	towns which are located on it.
				- Initialized a list of strings which will store the routes between a source town and a destination town. Each route in a String.
				- A string builder is used in transforming the list of list of towns to list of string routes.
				- A routesCounter which will store the all possible routes between the source town and the destination town based on the distance
				condition.
	- Main job:
				- Calls the method that calculates basic unique routes between tow towns and adds every calculated route to the basic routes list.
				- 1st for loop is all about building list of strings which represents the routes from the list of list of towns.
				- Next steps is to count all basic routes and the unique combinations between them to get the all number of routes.

	- The idea -applying the input data from the example-:
				- After finding the basic unique routes between a source town to a destination town, it starts counting the number of a unique
				combination of routes which satisfy the distance condition. In the given problem example we want to calculate the distance between
				"C" and "C". After calculating the basic route from "C" to "C" we will find 3 basic unique routes: [C-D-C], [C-D-E-B-C], [C-E-B-C]
				the length of [C-D-C] is [16], the length of [C-D-E-B-C] is [21], the length of [C-E-B-C] is [9].
				We can represent them by X=16, Y=21, Z=9 in order. Iterating these three routes we can count all basic routes and routes combination
				that satisfy the distance condition by initializing a counter for all routes to be 0 and starting the iteration:
					- checks if the current basic routes satisfies the condition. if yes, increment the routes counter. if not, continue to next
					iteration as it will make no sense to make combination route including this route.
					- starts the nested loop.
					- stores the distance of the basic route in a variable and repeats the following steps as long as the condition is met:
								- adds the distance of the current route in the nested loop to the distance of the current basic route.
								- if the distance condition is met, increments the routes counter. if not, go to next route and so on.

				- After executing this we will end up with these routes:
					X       ->      C-D-C                       -> 16
					XZ      ->      C-D-C-E-B-C                 -> 25
					Y       ->      C-D-E-B-C                   -> 21
					Z       ->      C-E-B-C                     -> 9
					ZX      ->      C-E-B-C-D-C                 -> 25
					ZZ      ->      C-E-B-C-E-B-C               -> 18
					ZZZ     ->      C-E-B-C-E-B-C-E-B-C         -> 27
					-------------------------------------------------
					7 unique routes from "C" to "C"
	 */
	public int calculateRoutesFromSourceToDest(Town source, Town destination, String condition, int conditionDistance)
	{
		List<List<String>> basicRoutes = new ArrayList<>();
		List<String> basicRoutesStrList = new ArrayList<>();
		StringBuilder basicRouteSb;
		int allRoutesCounter = 0;

		calculateBasicRoutesFromSourceToDest(source, destination, basicRoutes);

		for (List<String> basicRoute : basicRoutes)
		{
			if (source.equals(destination))
			{
				basicRoute.add(0, source.getName());
			}

			basicRouteSb = new StringBuilder();
			for (String townName : basicRoute)
			{
				basicRouteSb.append(townName);
			}
			basicRoutesStrList.add(basicRouteSb.toString());
		}

		for (String basicRoute : basicRoutesStrList)
		{
			if (helperCalculator.evaluateCondition(routeDistanceCalculator.calculateRouteDistance(basicRoute), conditionDistance, condition))
			{
				allRoutesCounter++;
			}
			else
			{
				continue;
			}

			for (String route : basicRoutesStrList)
			{
				int distance = routeDistanceCalculator.calculateRouteDistance(basicRoute);
				do
				{
					distance += routeDistanceCalculator.calculateRouteDistance(route);

					if (helperCalculator.evaluateCondition(distance, conditionDistance, condition))
					{
						allRoutesCounter++;
					}
				}
				while (helperCalculator.evaluateCondition(distance, conditionDistance, condition));
			}
		}

		return allRoutesCounter;
	}

	/*
	- Calculates the basic routes from a source to destination and it can find a route from a source to different destination and also can find a
	route from a source to itself.
	- Initialization Steps:
				- initializes a string list to represent the towns names on each built basic route.
				- initialized a set of towns to mark the visited towns to prevent any cyclic traversal.

	- Main job:
				- adds the source town name to the currently built basic route.
				- checks if the source equals the destination.
				- if yes, then finds the basic routes from the neighbors to the town and by adding the town to the start of each route  in the first
				step, we can calculate the routes from the town to itself.
				- if not, start finding the basic routes between source and destination.
	 */
	private void calculateBasicRoutesFromSourceToDest(Town source, Town destination, List<List<String>> basicRoutes)
	{
		List<String> towns = new ArrayList<>();
		Set<Town> visitedTowns = new HashSet<>();

		towns.add(source.getName());

		if (source.equals(destination))
		{
			for (Town neighbor : source.getNeighbors().keySet())
			{
				neighbor = graph.getTownByName(neighbor.getName());
				calculateBasicRoutesFromSourceToDest(neighbor, destination, basicRoutes);
			}
		}
		else
		{
			findNewBasicRoute(source, destination, towns, visitedTowns, basicRoutes);
		}
	}

	/*
	- Finds a basic unique route from a source town to a destination town & adds it to the basic routes from the source town to the destination town.
	- Applies Depth First Traversal by starting from a given source town -current town- and keeps visiting towns in the graph and adding them to the
	current calculates basic route. When a basic route is completed, it adds it to the list of basic routes.
	- Initialization Step:
				- adds the current source town to the visited towns set to avoid going in a cycle.
				- if destination is reached, copies the towns names list -which represents the towns on the route from a source to a destination- to
				the basic route list then adds the list to the basic routes list.
	- Repetitive Steps:
				- iterates the neighbors of the current town.
				- checks if the neighbor is not visited yet.
				- if yes, adds the neighbor to the current built route and make a recursive call to find a the next town in the currently built route.
	- when all neighbors of the current town are visited, remove the town from the visited towns as a new basic route will be calculated.
	 */
	private void findNewBasicRoute(Town currentTown, Town destination, List<String> towns, Set<Town> visitedTowns, List<List<String>> basicRoutes)
	{
		visitedTowns.add(currentTown);

		if (currentTown.equals(destination))
		{
			List<String> basicRoute = new ArrayList<>();
			basicRoute.addAll(towns);
			basicRoutes.add(basicRoute);
		}

		for (Town neighbor : currentTown.getNeighbors().keySet())
		{
			neighbor = graph.getTownByName(neighbor.getName());
			if (!visitedTowns.contains(neighbor))
			{
				towns.add(neighbor.getName());
				findNewBasicRoute(neighbor, destination, towns, visitedTowns, basicRoutes);

				towns.remove(neighbor.getName());
			}
		}

		visitedTowns.remove(currentTown);
	}
}
