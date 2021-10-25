# martian-robots
This repository is about a sample application, to navigate robots on a rectangular terrain. Basic set of Requirements are,
  * Terrain is a fixed boundary
  * Robot can be navigated with simple instructions (R - Turn Right, L - Turn Left, F- Forward). Forward movement is moved by one co-ordinate on the direction the robot in.
  * If the Robot's new position after navigation is outside the boundary, the robot is marked lost and the co-ordinates where the robot lost are marked so that new robot can use that information to skip moving forward.
  * Maximum instructions for the robot can be 100
  * Maximum value for the boundary of the terrain is 50.

## Assumptions
  * Not clear of the Off instruction, the current behaviour is if an instruction "O" is passed as input, it would skip checking for co-ordinates which were marked by previously failed robots.
  * Board Co-ordinates starting point is (0,0)
  * Robot navigation is sequential 

## Requirements
  * JAVA11
  * Maven

## Execution
  * Build using mvn package
  * Run java -jar target/martian-robots-0.0.1-SNAPSHOT-shaded.jar   <input_file>
  * sample cmd java -jar target/martian-robots-<version>-shaded.jar  src/test/resources/validInput.txt

## Improvements
  * Final output to be redirected to a file
  * Avoiding reading entire input file at once
  * Functional libraries to handle exceptions
  * Generating events when state change happens
 
