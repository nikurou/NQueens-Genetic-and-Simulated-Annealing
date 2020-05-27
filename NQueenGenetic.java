import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * CS 4200.01: Artificial Intelligence
 * 
 *
 * Programming Assignment #2
 *
 * < Description: Performs the Genetic Algorithm on an ArrayList of QueenBoard objects.>
 *
 * @author Dylan Chung 
 *   
 */

public class NQueenGenetic {
	//Local field variables
	ArrayList<QueenBoard> population;
	long start = System.currentTimeMillis();
	float elapsedTime;
	boolean isSolved;
	int numGenerations;
	
	
	//Constructor that accepts int n, the number of queens
	public NQueenGenetic(int n){
		population = generateQueenBoards(n);
		isSolved = true; //If it fails, then it will change to false. Otherwise, if it never fails, it never changes.
		numGenerations = 0;
		
	}

	// Generates the queenBoard arrayList and returns it. 
	// We generate 100 boards so each population is 100,
	private ArrayList<QueenBoard> generateQueenBoards(int n) {
		ArrayList<QueenBoard> pop = new ArrayList<QueenBoard>();
		
		for(int i = 0; i < 100; i++){
			QueenBoard board = new QueenBoard(n);
			pop.add(board);
		}
		Collections.sort(pop);
		elapsedTime = 0;
		return pop;
	}	
	
	// The Genetic Algorithm
	public QueenBoard geneticAlgorithm(){
		
		int numGenerations = 0;
		float timeLimit = 30; //seconds
		
		//Saves the best board from the previous generation
		//Initializes to an impossible board so that it won't accidentally match with legitimate boards
		QueenBoard previousChild = new QueenBoard(new int[]{-1});
		QueenBoard previousBestGen = new QueenBoard(new int[]{-1});
		
		while(population.get(0).getFitness() != 0 && elapsedTime<= timeLimit && numGenerations <= 500){ 
			ArrayList<QueenBoard> new_population = new ArrayList<QueenBoard>();
			for(int i = 0; i< population.size(); i++){
				QueenBoard x = randomSelection(population);
				QueenBoard y = randomSelection(population);
				QueenBoard child = reproduce(x,y);

				
				//20% chance of mutation OR always mutate when it's the exact same as previous child  OR if it's the same as the local best from the past generation OR parents are the same
				if(new Random().nextInt((100)+1) <= 20 || Arrays.equals(previousChild.getBoard(), child.getBoard())|| Arrays.equals(previousBestGen.getBoard(), child.getBoard()) || Arrays.equals(x.getBoard(), y.getBoard())  ){  
					child = mutate(child);
				}
				
				previousChild = child;
				// Add child to new population
				new_population.add(child);
				Collections.sort(new_population);
				
			}
			population = new_population;
			numGenerations++;
		
			
			previousBestGen = population.get(0);
			//System.out.println("Local best is " + population.get(0));
			setElapsedTime();
		}
		
		this.numGenerations =  numGenerations;
		
		if(elapsedTime > timeLimit || numGenerations > 500){
			isSolved = false;
		}
		
		printEndResult(timeLimit);
		//The best individual in the population is the first index, because population is sorted by fitness.s
		return population.get(0);
		

	}
	
	//Prints the end results 
	private void printEndResult(float timeLimit) {
		
		if(elapsedTime > timeLimit){
			System.out.println("Time limit of " + timeLimit + " seconds has been exceeded, and the algorithm will stop here.");
			
		}
		System.out.println("\nWith Genetic Algorithm");
		System.out.println("Generation : " + numGenerations);
		System.out.println("Best Board    : " + population.get(0));
		System.out.println("Elapsed Time: " + elapsedTime + " seconds");
		
	}

	//Getter method for the field ElapsedTime
	public float getElapsedTime() {
		return elapsedTime;
	}
	
	//Setter method for the field ElapsedTime
	private void setElapsedTime() {
		long end = System.currentTimeMillis();
		float sec = (end - start)/1000F;
		elapsedTime = sec;
	}

	//Mutates a board by changing one random index and returns it.
	private QueenBoard mutate(QueenBoard child) {
		int [] board = child.getBoard();
		int n = child.getPopulationSize();
		
		Random rand = new Random();
		int indexToMutate = rand.nextInt(n);
		int value = rand.nextInt(n);
		
		// If the value is equal to the current value we want to change
		// Re-roll the value
		while(value == board[indexToMutate]){
			value = rand.nextInt(n);
		}
		
		board[indexToMutate] = value;
		
		QueenBoard mutant = new QueenBoard(board);
		return mutant;
		
	}

	// Copies data from both parents to form a new child. 
	private QueenBoard reproduce(QueenBoard x, QueenBoard y) {
	
		int n = x.getPopulationSize();
		// A random number from 1 - n 
		int c = new Random().nextInt(n-1)+1;
		//System.out.println("Choosen split Index = " + c);
		int[] childBoard =  new int[n];
		
		//Copy parent X data up to index C.
		for(int i = 0; i<c; i++){
			childBoard[i] = x.getBoard()[i];
		}
		//Copy parent Y data from index c to last index..
		for(int j = c; j < n; j++){
			childBoard[j] = y.getBoard()[j];
		}
	
		//Create QueenBoard object out of that data.
		QueenBoard child = new QueenBoard(childBoard);
		return child;
	}

	// Randomly selects two parents from the list of boards. 
	// However, it is not completely random, as it only selects the top x percentage to be parents.  
	private QueenBoard randomSelection(ArrayList<QueenBoard> pop) {
		Random rand = new Random();
		int maxRange = (int)(pop.size()* 0.20); //Only choose top 20%
		//System.out.println("Max Range : " + maxRange);
		
		//Obtain a number between [0 - popsize-1]
		int randIndex = rand.nextInt((maxRange));
		//System.out.println("randIndex = " + randIndex);
		
		return pop.get(randIndex);
		
	}

}
