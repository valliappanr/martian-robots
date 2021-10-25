# martian-robots

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
  * sample cmd java -jar target/martian-robots-0.0.1-SNAPSHOT-shaded.jar  src/test/resources/validInput.txt

## Improvements
  * Final output to be redirected to a file
  * Avoiding reading entire input file at once
  * Functional libraries to handle exceptions
  * Generating events when state change happens
 
