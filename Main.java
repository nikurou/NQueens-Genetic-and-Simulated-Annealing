/**
 * CS 4200.01: Artificial Intelligence
 * 
 *
 * Programming Assignment #2
 *
 * < Description: Holds the main class that creates NQueenGenetic objects and NQueenSimulatedAnnealing Objects to start 
 *                and calls their respective functions to start the algorithms to solve a N Queen problem using either 
 *                simulated annealing or genetics algorithm.>
 *
 * @author Dylan Chung 
 *   
 */

public class Main {
	
	public static void main(String[]args){
		
		//forAnalysisOfGenetic();
		//forAnalysisOfAnnealing();
		
		NQueenGenetic n1 = new NQueenGenetic(25);
		n1.geneticAlgorithm();
		
		
		NQueenSimulatedAnnealing n2 = new NQueenSimulatedAnnealing(25);
		n2.simulatedAnnealing();
		
	}

	private static void forAnalysisOfAnnealing() {
		int avgSolved = 0;
		int avgGeneration = 0;
		float avgRunTime= 0;
		
		for(int i = 0; i<400; i++) {
			NQueenSimulatedAnnealing n2 = new NQueenSimulatedAnnealing(25);
			n2.simulatedAnnealing();
			
			avgSolved += n2.isSolved ? 1:0;
			avgGeneration += n2.numGeneration;
			avgRunTime += n2.elapsedTime;
		}
		
		System.out.println("\n\nA 25N Queen Solution (Annealing) at 400 Instances with Initial Temp: 1000 and CoolDown Rate at 0.95 ");
		System.out.println("Avg Solved : " + ((double)avgSolved/400.0)* 100 + "%");
		System.out.println("Avg Generations : "  + (double)avgGeneration/400+ " generations");
		System.out.println("AvgRunTime : " + avgRunTime/400 + " seconds");
	}

	private static void forAnalysisOfGenetic() {
		int avgSolved = 0;
		int avgGeneration = 0;
		float avgRunTime= 0;
		
		for(int i = 0; i<400; i++) {
			NQueenGenetic n1 = new NQueenGenetic(25);
			n1.geneticAlgorithm();
			
			avgSolved += n1.isSolved ? 1:0;
			avgGeneration += n1.numGenerations;
			avgRunTime += n1.getElapsedTime();
		}
		
		System.out.println("\n\nA 25N Queen Solution (Genetic) at 400 Instances with a generational limit of 500 generations. ");
		System.out.println("Avg Solved : " + ((double)avgSolved/400.0)* 100 + "%");
		System.out.println("Avg Generations : "  + (double)avgGeneration/400+ " generations");
		System.out.println("AvgRunTime : " + avgRunTime/400 + " seconds");
		
	}
}
