**Notes & Steps to build, run & test the application**

**Notes:**
- Input data file can be found under "data/input.txt".
- Input data must be in the form: RouteDistance[Two Alphabet Characters & Positive Number], RouteDistance,..etc
  
  example: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7
  
- Attached Documents: Trains Challenge, Trains Class Model, Trains Documentation, Trains Graph Example
- Trains Class Model .uml file can be found under "src/model/TrainsClassModel.uml".

**Building Steps:**
- Open a command window in the project directory.
- To clean the project, run the command ...\Trains> mvn clean
- To build the project, run the command ...\Trains> mvn install																	'runs the tests also'
- To update and build the project, run the command ...\Trains> mvn install -U													'runs the tests also'
- To clean, update and build the project, run the command ...\Trains> mvn clean install -U										'runs the tests also'
- To clean, update and build the project and skip the tests, run the command ...\Trains> mvn clean install -U -DskipTests

**Testing Steps:**
- Open a command window in the project directory.
- To run the project's tests, run the command ...\Trains> mvn test

**Running Steps:**
- Provide input data in a right form.
- Open a command window in the project directory.
- To clean, update and build the project, run the command ...\Trains> mvn clean install -U
- To run the project, run the command ...\Trains> mvn clean compile exec:java
**********************************************************************************************************************
