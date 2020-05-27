
import java.util.Arrays;
import java.util.Random;


/**
 * CS 4200.01: Artificial Intelligence
 * 
 *
 * Programming Assignment #2
 *
 * < Description: A class that holds the n x n board of n queens in an single dimensional array, the value of n, and the fitness
 *                of the board as well as various other functions to calculate, populate, and overriden Comparable functions
 *                for use later with sorting an ArrayList using fitness.>
 *
 * @author Dylan Chung 
 *   
 */

public class QueenBoard implements Comparable<QueenBoard> {
	//Local field variables
	int n; //Queen Size
	int [] board; // Array where the value of each index represents the row the Queen belongs to, and the index represents the column it belongs to.
	int h; //Number of pairs of queens that are attacking each other.
	
	public QueenBoard(int n){
		this.n = n;
		//board = new int[n][n];
		board = new int[n];
		populateBoard();
		h = calcFitnessVal(board);
		
	}
	
	public QueenBoard(int[] childBoard) {
		n = childBoard.length;
		board = childBoard;
		h = calcFitnessVal(childBoard);
	}

	// Generate the Queens and add them to the board.
	// Only one Queen per column 
	public void populateBoard(){
		
		Random rand = new Random();
		
		//Generate random number between 0 - 25.
		for( int i = 0 ; i < n; i++){
			board[i] = rand.nextInt(n+1);	
		}
	}
	
	
	// Returns the Number of Queens that are attacking each other. 
	// The lower, the better.
	private int calcFitnessVal(int [] queens){
		int numAttackPairs = 0; 
		
		for(int i = 0; i < queens.length; i++ ){ 
			for(int j = i+1; j<queens.length; j++){
					int rise = Math.abs(i-j);
					int run = Math.abs(queens[i]-queens[j]);
					
					if(rise == run || run == 0){
						numAttackPairs++;
					}
			}
		}
		return numAttackPairs;
		
	}
	
	public int[] getBoard(){
		return board;
	}
	
	//Return the fitness value
	public Integer getFitness(){
		return h;
	}

	@Override 
	public String toString(){
		return Arrays.toString(board) + "  Fitness: " + getFitness();
	}
	
	@Override
	public int compareTo(QueenBoard o){
		return this.getFitness().compareTo(o.getFitness());
	}

	public int getPopulationSize() {
		return n;
	}
}
