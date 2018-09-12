package com.thoughtworks.trains.main;

import com.thoughtworks.trains.calculators.RouteDistanceCalculator;
import com.thoughtworks.trains.calculators.RoutesCalculator;
import com.thoughtworks.trains.calculators.ShortRouteCalculator;
import com.thoughtworks.trains.calculators.TripsCalculator;
import com.thoughtworks.trains.model.Graph;
import com.thoughtworks.trains.util.InputFileReader;

//The "Main" class which initializes ad runs the application.
public class Main
{
	//The application's main method that initializes and runs the application.
	public static void main(String[] args)
	{
		//Instantiate a new instance of the InputFileReader class with the relative path of the data input file.
		InputFileReader ifr = new InputFileReader("data/input.txt");

		//Reads the input data of the graph.
		String graphStr = ifr.getGraphString();

		//Validates the input data.
		if (graphStr.isEmpty())
		{
			System.out.println("Input data is empty and Kiwiland as well!");
			System.exit(1);
		}

		//Instantiates a new instance of the Graph class using the graphStr.
		Graph graph = new Graph(graphStr);

		//Instantiates a new instance of the RouteDistanceCalculator class using the routesDistancesMap which is built by the Graph class.
		RouteDistanceCalculator crd = new RouteDistanceCalculator(graph.getRoutesDistancesMap());

		//1- The distance of the route A-B-C.
		System.out.println("Output #1: " + crd.getRouteDistance("A-B-C"));

		//2- The distance of the route A-D.
		System.out.println("Output #2: " + crd.getRouteDistance("A-D"));

		//3- The distance of the route A-D-C.
		System.out.println("Output #3: " + crd.getRouteDistance("A-D-C"));

		//4- The distance of the route A-E-B-C-D.
		System.out.println("Output #4: " + crd.getRouteDistance("A-E-B-C-D"));

		//5- The distance of the route A-E-D.
		System.out.println("Output #5: " + crd.getRouteDistance("A-E-D"));

		//Instantiates a new instance of the TripsCalculator class using the graph instance.
		TripsCalculator tc = new TripsCalculator(graph);

		//6- The number of trips starting at C and ending at C with a maximum of 3 stops.
		System.out.println("Output #6: " + tc.getNumberOfTripsBetweenTwoTowns(graph.getTownByName("C"), graph.getTownByName("C"), "<=", 3));

		//7- The number of trips starting at A and ending at C with exactly 4 stops.
		System.out.println("Output #7: " + tc.getNumberOfTripsBetweenTwoTowns(graph.getTownByName("A"), graph.getTownByName("C"), "=", 4));

		//Instantiates a new instance of the ShortRouteCalculator class using the graph instance.
		ShortRouteCalculator src = new ShortRouteCalculator(graph);

		//8- The length of the shortest route (in terms of distance to travel) from A to C.
		System.out.println("Output #8: " + src.calculateShortestRouteFromSourceToDest(graph.getTownByName("A"), graph.getTownByName("C"), true));

		//9- The length of the shortest route (in terms of distance to travel) from B to B.
		System.out.println("Output #9: " + src.calculateShortestRouteFromSourceToDest(graph.getTownByName("B"), graph.getTownByName("B"), true));

		//Instantiates a new instance of the RoutesCalculator class using the graph instance.
		RoutesCalculator rc = new RoutesCalculator(graph);

		//10- The number of different routes from C to C with a distance of less than 30.
		System.out.println("Output #10: " + rc.calculateRoutesFromSourceToDest(graph.getTownByName("C"), graph.getTownByName("C"), "<", 30));
	}
}
