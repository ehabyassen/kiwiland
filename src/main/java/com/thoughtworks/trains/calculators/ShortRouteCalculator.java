package com.thoughtworks.trains.calculators;

import com.thoughtworks.trains.model.Graph;
import com.thoughtworks.trains.model.Town;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/*
The "RouteDistanceCalculator" class which solves the problems 8, 9 by applying Dijkstra algorithm to traverse
a Graph starting from a given source node and calculates the shortest path distance of all nodes.
*/
public class ShortRouteCalculator
{
	//Reference of a Graph will be initialized after by the constructor.
	private Graph graph;

	//A Constructor for the ShortRouteCalculator class which initializes the Graph instance.
	public ShortRouteCalculator(Graph graph)
	{
		this.graph = graph;
	}

	/*
	Applying Dijkstra shortest path algorithm to calculate the shortest route from a source town to a destination town.
	Following these steps:
	- Initialization steps:
					- sets the distance of the source town to 0 since and the distance of other towns is infinity by default.
					- creates two sets of visited and unvisited towns.
					- adds the source town to the unvisited towns.
	- Repetitive steps -repeat as long as there are unvisited towns-:
					- initializes a currentTown to be the nearest town from the unvisited towns.
					- removes it from the unvisited towns as it will be processed in this iteration.
					- iterates the neighbors of the current town and calculates the sub-route distance to the neighbor,
					the minimum distance and the shortest path info of this neighbor, adds it to the unvisited towns to
					be processed in the following iterations.
					- adds the processed current town to the visited towns.
					-repeat
	*When the iteration is done, all node will have the distance, shortest path info*
	- sets the distance of the destination town from the graph towns list before resetting the graph.
	- Resets the graph to clear all the nodes from the graph and rebuild it.
	- If the destination town and the source town are the same, delegates the calculation to "calculateShortestRouteFromToSameTown()"
	- If not, return the distance of the destination town.
	 */
	public int calculateShortestRouteFromSourceToDest(Town source, Town destination, boolean resetGraph)
	{
		Set<Town> visitedTowns = new HashSet<>();
		Set<Town> unvisitedTowns = new HashSet<>();

		source.setDistance(0);
		unvisitedTowns.add(source);

		while (unvisitedTowns.size() != 0)
		{
			Town currentTown = getLowestDistanceTown(unvisitedTowns);
			unvisitedTowns.remove(currentTown);
			for (Town t : currentTown.getNeighbors().keySet())
			{
				Town neighbor = graph.getTownByName(t.getName());
				Integer subRouteDistance = currentTown.getNeighbors().get(neighbor);
				if (!visitedTowns.contains(neighbor))
				{
					calculateMinimumDistance(neighbor, subRouteDistance, currentTown);
					unvisitedTowns.add(neighbor);
				}
			}
			visitedTowns.add(currentTown);
		}

		destination.setDistance(graph.getTownByName(destination.getName()).getDistance());

		if (resetGraph)
		{
			graph.reset();
		}

		if (source.equals(destination))
		{
			return calculateShortestRouteFromToSameTown(visitedTowns, destination);
		}

		return destination.getDistance();
	}

	//Calculates the nearest neighbor from a set of unvisited neighbors by comparing their current distances.
	private Town getLowestDistanceTown(Set<Town> unvisitedTowns)
	{
		Town lowestDistanceTown = null;
		int lowestDistance = Integer.MAX_VALUE;
		for (Town town : unvisitedTowns)
		{
			int townDistance = town.getDistance();
			if (townDistance < lowestDistance)
			{
				lowestDistance = townDistance;
				lowestDistanceTown = town;
			}
		}
		return lowestDistanceTown;
	}

	/*
	Calculates the minimum distance of the neighbors of the current source town and updates the short path info for each neighbor by:
	- setting the distance of the current source town to a variable sourceDistance
	- checks if the sum of sourceDistance + the distance from the current source town to the neighbor -processed town- is less than
	the pre-calculated distance of the neighbor -processed town-
	- if yes, sets the distance of the neighbor -processed town- to be the calculated sum.
	- gets the shortestPath info of the current source town and stores it in a list
	- adds the source town to the shortestPath list
	- sets the shortestPath list to the neighbor -processed town-
	 */
	private void calculateMinimumDistance(Town processedTown, Integer subRouteDistance, Town sourceTown)
	{
		Integer sourceDistance = sourceTown.getDistance();

		if (sourceDistance + subRouteDistance < processedTown.getDistance())
		{
			processedTown.setDistance(sourceDistance + subRouteDistance);
			List<Town> shortestPath = new LinkedList<>(sourceTown.getShortestPath());
			shortestPath.add(sourceTown);
			processedTown.setShortestPath(shortestPath);
		}
	}

	/*
	Calculates the distance from a town to itself by:
	- Initialization steps:
				- initializes a lowestDistance variable by infinity.
				- initializes two variable for the lowestBackDistance to the original source and the cachedTownDistance for
				the current processed town from the original source town.
				- remove the destination town from the visited towns that will be processed.
	- Repetitive steps:
				- iterate the visited towns.
				- caches the current processed town distance from the previous processing -distance from the original source town to it-
				- calculates the shortest route distance from current processed to to the original destination town and sets the value to
				lowestBackDistance.
				- checks if the cachedTownDistance + lowestBackDistance -which is the full distance from the original source town to it self-
				 is less than the lowestDistance.
				- if yes, sets the lowestDistance to be the sum of them "cachedTownDistance + lowestBackDistance"
				- check the next town -the next neighbor of the original source town-

	- returns the calculated lowestDistance.
	*/
	private int calculateShortestRouteFromToSameTown(Set<Town> visitedTowns, Town destination)
	{
		int lowestDistance = Integer.MAX_VALUE;
		int lowestBackDistance;
		int cachedTownDistance;

		if (visitedTowns.contains(destination))
		{
			visitedTowns.remove(destination);
		}

		for (Town town : visitedTowns)
		{
			cachedTownDistance = town.getDistance();
			lowestBackDistance = calculateShortestRouteFromSourceToDest(town, destination, true);
			if (cachedTownDistance + lowestBackDistance < lowestDistance)
			{
				lowestDistance = cachedTownDistance + lowestBackDistance;
			}
		}
		return lowestDistance;
	}
}
