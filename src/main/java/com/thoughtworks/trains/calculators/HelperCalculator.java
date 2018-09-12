package com.thoughtworks.trains.calculators;

/*
The "HelperCalculator" provides helper methods to the calculators
 */
class HelperCalculator
{
	//A Constructor for the HelperCalculator class.
	HelperCalculator()
	{
	}

	/*
	Evaluates two integer values based on a given condition.
	 */
	boolean evaluateCondition(int x, int y, String condition)
	{
		boolean result;

		switch (condition)
		{
		case "=":
			result = x == y;
			break;

		case ">":
			result = x > y;
			break;

		case "<":
			result = x < y;
			break;

		case ">=":
			result = x >= y;
			break;

		case "<=":
			result = x <= y;
			break;

		default:
			result = false;
			break;
		}

		return result;
	}
}
