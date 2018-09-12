package com.thoughtworks.trains.calculators;

import com.thoughtworks.trains.model.Graph;
import com.thoughtworks.trains.model.Town;

import java.util.ArrayList;
import java.util.List;

/*
The "TripsCalculator" class which solves the problems 6, 7 by traversing the graph starting from a given source town
and counts the stop at each town till the given condition to stop is met then it filters the numbers of stops at the given destination
based on the given condition and returns the number of trips that matches the condition of number times of stops.
*/
public class TripsCalculator
{
	//Reference of a Graph will be initialized after by the constructor.
	private Graph graph;

	//Reference of a HelperCalculator will be initialized after by the constructor.
	private HelperCalculator helperCalculator;

	//A Constructor for the TripsCalculator class which initializes the Graph instance.
	public TripsCalculator(Graph graph)
	{
		this.graph = graph;
		helperCalculator = new HelperCalculator();
	}

	/*
	Calculates the number of trips from a source town to a destination town based on a condition of number of stops.
	Following these steps:
	- Initialization steps:
				- Initialize a stopsCounter to 0.
				- Initialize an empty list of towns to represent the trips to the destination town.
	- Delegates the work to the exploreNeighbors method to traverse the towns and finds the number of trips using
	recursion.
	- Returns the the number of trips.
	 */
	public int getNumberOfTripsBetweenTwoTowns(Town source, Town destination, String condition, int stops)
	{
		int stopsCounter = 0;
		List<Town> trips = new ArrayList<>();

		exploreNeighbors(destination, source, stopsCounter, trips, stops, condition);

		return trips.size();
	}

	/*
	Executes the following steps to explore the graph starting from a given source town:
	- increments the stops counter.
	- iterates the neighbors of the current town -in the first iteration, the source town-
	- Repetitive steps -repeat as long as the stop condition is not met-:
				- Initialize a processedTown variable with the current neighbor in the current iteration.
				- adds the processedTown to the trips list if the destination is reached and the stop condition is met.
				- makes a recursive call if the stopsCounter didn't reach the stops yet.
	- at the end of the iterations, the trips list will be filled with the trips to the destination town that met the
	given condition.
	 */
	private void exploreNeighbors(Town destination, Town currentTown, int stopsCounter, List<Town> trips, int stops, String condition)
	{
		stopsCounter++;

		for (Town t : currentTown.getNeighbors().keySet())
		{
			Town processedTown = graph.getTownByName(t.getName());

			if (processedTown.equals(destination) && helperCalculator.evaluateCondition(stopsCounter, stops, condition))
			{
				trips.add(processedTown);
			}

			if (stopsCounter != stops)
			{
				exploreNeighbors(destination, processedTown, stopsCounter, trips, stops, condition);
			}
		}
	}

}
