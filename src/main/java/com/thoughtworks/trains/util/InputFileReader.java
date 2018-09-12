package com.thoughtworks.trains.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*The "InputFileReader" is a utility class reads the input data of the Graph.*/
public class InputFileReader
{
	/*
	File name
	Will be initialized when a new instance of the InputFileReader class is created
	*/
	private String fileName;

	//A Constructor for the InputFileReader class which initializes the file name.
	public InputFileReader(String fileName)
	{
		this.fileName = fileName;
	}

	//Reads the input file data and returns a String representation of the Graph as an input to the application.
	public String getGraphString()
	{
		StringBuilder graphStr = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(fileName)))
		{
			String currentLine;
			while ((currentLine = br.readLine()) != null)
			{
				graphStr.append(currentLine);
			}
		}
		catch (IOException e)
		{
			System.out.println("404 input data file is not found!");
			System.exit(1);
		}

		return graphStr.toString();
	}
}
