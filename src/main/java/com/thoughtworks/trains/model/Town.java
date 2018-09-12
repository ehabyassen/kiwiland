package com.thoughtworks.trains.model;

import java.util.*;

/*
The "Town" class represents a town in the design model of the solution of the trains problem.
A town has the following information:
- A name.
- List of Towns which are located on & represents the shortest path from a source town to this town.
- The distance from the source town to this town.
- A {key, value} map that represents the neighbor towns and the distance between them and this town.
*/
public class Town
{
	/*
	Town name
	Will be initialized when a new instance of the Town class is created
	*/
	private String name;

	/*
	List of towns that represent the shortest path to a town
	Empty list in the initial state of the application.
	Will be manipulated while calculating the shortest path to a Town.
   */
	private List<Town> shortestPath = new LinkedList<>();

	/*
	Numerical distance to the town from the source town.
	In the initial setup, all towns are not discovered yet to the distance to each town is unknown/infinity.
	Distance of a source town is 0
	Distance of a destination town is calculated while discovering the towns.
	*/
	private Integer distance = Integer.MAX_VALUE;

	/*
	Key value map that represents the direct neighbors of a town and the distance from this town to them.
	The map is empty at the initial state of the application.
	Neighbor Towns of a -being processed town- will be added to the map while discovering/processing the town.
	*/
	private Map<Town, Integer> neighbors = new HashMap<>();

	//A Constructor for the Town class which initializes the town name.
	Town(String name)
	{
		setName(name);
	}

	//Method to be used to add a neighbor town & distance to it to the neighbors map while discovering a town.
	void addNeighbor(Town neighbor, int distance)
	{
		if (neighbor == null)
		{
			System.out.println("Ghost neighbors are not allowed!");
			System.exit(1);
		}
		else if (distance < 0)
		{
			System.out.println("Distance is always a positive value!");
			System.exit(1);
		}
		else
		{
			neighbors.put(neighbor, distance);
		}
	}

	//<Getters and Setters>
	public String getName()
	{
		return name;
	}

	private void setName(String name)
	{
		if (name.isEmpty())
		{
			System.out.println("Town name can't be blank! Fix the input data!");
			System.exit(1);
		}
		this.name = name;
	}

	public List<Town> getShortestPath()
	{
		return shortestPath;
	}

	public void setShortestPath(List<Town> shortestPath)
	{
		this.shortestPath = shortestPath;
	}

	public Integer getDistance()
	{
		return distance;
	}

	public void setDistance(Integer distance)
	{
		if (distance < 0)
		{
			System.out.println("Distance is always a positive value!");
			System.exit(1);
		}
		this.distance = distance;
	}

	public Map<Town, Integer> getNeighbors()
	{
		return neighbors;
	}
	//</Getters and Setters>

	@Override
	public String toString()
	{
		return "[" + name + "]" + ((neighbors != null) ? neighbors : "");
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == this)
		{
			return true;
		}
		if (!(obj instanceof Town))
		{
			return false;
		}
		Town town = (Town) obj;
		return Objects.equals(name, town.getName());
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(name);
	}
}
