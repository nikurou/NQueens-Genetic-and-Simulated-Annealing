----------------------------------------------
Program Description
----------------------------------------------
The following program solves the NQueen Pair problem using either a genetic algorithm or
simulated annealing and returns a solution, or in the case that it is unable to calculate the solution within
the given constraints, it returns the best board it did find. It also outputs the time spent to find the solution
and what generation it was on when it did.



----------------------------------------------
How to Compile and Run the Program
----------------------------------------------
 1.) Open Command Prompt (Start > Command Prompt)
 2.) Change your directory to the folder where you downloaded and saved this repo with the command cd <filepath>
 3.) Copy and paste the following command to compile
	javac Main.java Node.java NQueenGenetic.java NQueenSimulatedAnnealing.java QueenBoard.java
 4.) Now to run the program, type:
	java Main
 
--------------------------------------------
 Notes
--------------------------------------------
 - You may skip step 3 if you wish, I have included the compiled .class files in the folder.

 - The program runs Genetic Algorithm with a 20% mutation rate and forced mutation under certain conditions 
   and a population of 100 w/ 25 Queens per board

 - IF Genetic Algorithm does not return a fitness with 0, it's because it took longer than 30 seconds to find one, but
   that should not ever happen with the current values.  

 - Simulated Annealing has a initial temperature of 1000 and cooldowns with the following formuala temp *= 0.90
   w/ 25 Queens per board
