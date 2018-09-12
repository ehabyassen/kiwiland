package com.thoughtworks.trains.util;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class InputFileReaderTest
{
	private InputFileReader inputFileReader;

	@Before
	public void init()
	{
		String fileName = "data/input.txt";
		inputFileReader = new InputFileReader(fileName);
	}

	@Test
	public void shouldFindInputData()
	{
		assertTrue(!inputFileReader.getGraphString().isEmpty());
	}
}
