# KLM Assignment task #

This project solves the Aircraft Refuelling Puzzle.


## Task Description ##
Situation:
* 9 aircraft are parked at an airport and need to be refuelled before they can take off.
* Fuel trucks are used to refuel the aircraft.

Task:
* Place fuel trucks in the grid.
* Each aircraft should have one fuel truck next to it (horizontally or vertically).
* Fuel trucks do not touch each other, not even diagonally.
* The numbers outside the grid show the total number of fuel trucks in the corresponding row
or column.

## Solution ##

The solution to this puzzle can be found in `klm.Assignment` class.
To solve this problem I have used dynamic programming and backtracking.
To develop the algorithm, following properties need to be initialized: 
* represent the airport as a 2-d array
* store the position of the planes in the airport in a 2-d array
* represent the numbers of trucks per row in an array.
* represent the numbers of trucks per column in an array.
* store the diagonal direction in a 2-d array
* store the neighbors directions in a 2-a array

The algorithm descriptions is as follows:
* Iterate over the array of plane coordinates and for each plane, we check if we can place a truck next to it
    * in order to place a truck, te following checks need to be done:
    * are the coordinates legal (inside the airport)
    * is it an empty space
    * does the row and column still have room for a truck
    * check if in the diagonal positions is a truck already there
* once the above conditions are fullfileld and we can place a truck next to the plane, we move on to the next plane and do the same checks
* when we hit a dead end, and no longer can place trucks, we backtrack to the previous plane