
import java.util.Random;

/**
 * CS 4200.01: Artificial Intelligence
 * 
 *
 * Programming Assignment #2
 *
 * < Description: NQueenSimulatedAnnealing class performs the Simulated Annealing algorithm on generated Nodes.>
 *
 * @author Dylan Chung 
 *   
 */

public class NQueenSimulatedAnnealing {
	
	//Local field variables
	Node current; 
	Node original; 
	int n; //population size
	int numGeneration = 0;
	int limit = 500; 
	boolean isSolved; 
	
	float elapsedTime;
	long start = System.currentTimeMillis();
	
	// Constructor that accepts n, the number of queens
	public NQueenSimulatedAnnealing(int n){
		this.n = n;
		Node curr = new Node(n);
		original = curr;
		current = curr;
		elapsedTime = 0;
		//Will remain true UNLESS it fails.
		isSolved = true;
	}
	
	// Simulated Annealing Algorithnm
	public Node simulatedAnnealing(){
		
		while(current.getFitness() != 0 ){

			numGeneration++;
			
			current.coolDown(numGeneration);
			double temp = current.getTemperature();
			
			if(temp <= 0 ){
				if(current.getFitness() != 0 ){
					isSolved = false;
					System.out.println("Cooled down to 0 without solving");
				}
				System.out.print("\nWith Simulated Annealing");
				System.out.print("\nGeneration : " + numGeneration );
				System.out.println("\nBest Board    : " + current.getQueenBoard());
				System.out.println("OriginalBoard : " + original.getQueenBoard());
				setElapsedTime();
				System.out.println("Elapsed Time: " + elapsedTime);
				return current;
			}
			
			Node next = getSuccessorNode(current, current.getFitness());
			current = next;
	
		}
		
		setElapsedTime();
		printStats();
		
		return current;
	}

	// Prints the final output statistics 
	private void printStats() {
		System.out.print("\nWith Simulated Annealing"); 
		
		System.out.print("\nGeneration : " + numGeneration);
		System.out.println("\nBest Board    : " + current.getQueenBoard());
		System.out.println("OriginalBoard : " + original.getQueenBoard());
		
		System.out.println("Elapsed Time: " + elapsedTime + " seconds");
		
	}

	// Calculates the probability of acceptence which follows the formula e^(deltaFitness/temperature)
	private double calcAcceptanceProbability(int curr_fitness, int next_fitness, double temp) {
		
		// If the next node has a better fitness, set that to current.
		if(next_fitness <= curr_fitness){
			return 1;
		}
		else{
			int deltaFitness = curr_fitness - next_fitness;
			return Math.exp(deltaFitness/temp);
		}
	}

	// Returns a successor node by continually generating successor nodes until an acceptable one is found.
	private Node getSuccessorNode(Node curr, int fitness) {
		int[] currBoard = curr.getQueenBoard().getBoard();		
		int n = curr.getPopulationSize();
		
		int[] temporaryBoard = new int[n];
		System.arraycopy(currBoard, 0, temporaryBoard, 0, n);
		
		// Continually generate nodes until one is accepted.
		while(true){
			
			int randRow = new Random().nextInt(n);
			int randQueen = new Random().nextInt(n);
			temporaryBoard[randQueen] = randRow;
			QueenBoard next = new QueenBoard(temporaryBoard);
			
			double prob = calcAcceptanceProbability(curr.getFitness(), next.getFitness(), curr.getTemperature());
			double chance = Math.random();
			
			// Return a node with a better fitness 
			if(next.getFitness() <= fitness){
				Node successor = new Node(next);
				successor.setTemperature(curr.getTemperature());
				/*
				System.out.format(" Random: %.2f", chance);
				System.out.format("  Temperature: %.2f ", curr.getTemperature());
				System.out.format("  E^("+ (curr.getFitness() - next.getFitness()) +"/" + curr.getTemperature() +")  = %.2f", prob);
				
				System.out.println("");
				System.out.println(successor.getQueenBoard());
				*/
				
				return successor;
			}
			
		
			// Return a node with worst fitness for the hope that it gets better
			// IFF the calculated chance is within range of the acceptanceProbability.
			if(chance < prob && next.getFitness() > fitness){
				Node successor = new Node(next);
				successor.setTemperature(curr.getTemperature());
				/*
				System.out.format(" Random: %.2f", chance);
				System.out.format("  Temperature: " + curr.getTemperature());
				System.out.format("  E^("+ (curr.getFitness() - next.getFitness()) +"/" + curr.getTemperature() +")  = %.2f", prob);
				System.out.print(" (Selected Worse) ");
				System.out.println("");
				System.out.println(successor.getQueenBoard());
				*/
				return successor;
			}
			
			//Reset the board to what it was earlier so we can try again.
			System.arraycopy(currBoard, 0, temporaryBoard, 0, n);
		}
	}
	
	// Set the field ElapsedTime to the amount of time passed.
	private void setElapsedTime() {
		long end = System.currentTimeMillis();
		float sec = (end - start)/1000F;
		elapsedTime = sec;
	}

	

}
