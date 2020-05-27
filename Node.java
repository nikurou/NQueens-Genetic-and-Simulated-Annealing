/**
 * CS 4200.01: Artificial Intelligence
 * 
 *
 * Programming Assignment #2
 *
 * < Description: A class which holds the current Node board, a QueenBoard object, the temperature, n, and the fitness.
 *                To be used with NQueenSimulatedAnnealing class>
 *
 * @author Dylan Chung 
 *   
 */

public class Node {
	//Local field variables
	Node current;
	QueenBoard board;
	double temp;
	int n; 
	int h;

	// Constructor for a specific board
	public Node(QueenBoard board){
		this.board = board;
		n = board.getPopulationSize();
		h = board.getFitness();
		temp = 1000; 
	}
	
	// Constructor for random board
	public Node(int n){
		board = new QueenBoard(n);
		this.n = n;
		h = board.getFitness();
		temp = 1000;
	}
	

	// Constructor for random board with temp
	public Node(int n, double temp){
		board = new QueenBoard(n);
		this.n = n;
		h = board.getFitness();
		this.temp = temp;
	}
	
	public void setTemperature(double temp){
		this.temp = temp;	
	}
	
	public void coolDown(int numGen) {
		double coolDownRate = 0.90;
		temp *= coolDownRate;
		
	}
	
	public double getTemperature(){
		return temp;
	}
	
	public int getPopulationSize(){
		return n;
	}
	
	public int getFitness(){
		return h;
	}

	public QueenBoard getQueenBoard() {
		return board;
	}
}
