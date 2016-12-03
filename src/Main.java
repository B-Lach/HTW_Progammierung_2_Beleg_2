import java.util.ArrayList;

import ai.AI;
import ai.Difficulty;
import logic.*;

public class Main {

	public static void main(String[] args) {
		// simulate a match with console logging
//		consoleExampleWithOutput();
		// test our AI implementation 
		difficultyTest();
	}
	
	/**
	 * Method to test our ai implementation
	 */
	private static void difficultyTest() {
		int p1WinsFinal = 0;
		int p2WinsFinal = 0;
		int drawsFinal = 0;
		
		for (int i = 0; i < 100; i++) {
			int p1Wins = 0;
			int p2Wins = 0;
			int draws = 0;
			
			for (int j = 0; j < 100; j++) {
				Score score = consoleExampleWithoutOutput();
				if (score != null) {
					if (score.p1() > score.p2()) { p1Wins++; }
					if (score.p1() < score.p2()) { p2Wins++; }
					if (score.p1() == score.p2()) { draws++; }
				}
			}
			if (p1Wins > p2Wins) { p1WinsFinal++; }
			if (p1Wins < p2Wins) { p2WinsFinal++; }
			if (p1Wins == p2Wins) { drawsFinal++; }
			System.out.println("P1: " + p1Wins + " P2: " + p2Wins + " Draws: " + draws);
		}
		
		System.out.println("\nFinal result:\n\nP1: " + p1WinsFinal + " P2: " + p2WinsFinal + " Draws: " + drawsFinal);
	}
	
	/**
	 * Method to simulate a game using our AI. Change the difficulty for each player to test different cases
	 * @return Result of the match
	 */
	private static Score consoleExampleWithoutOutput() {
		try {
			BoardLogic logic = new BoardLogic();
			Boolean p1hasMove = true;
			Boolean p2hasMove = true;
			
			FieldType currentPlayer = FieldType.Player1;
			
			Difficulty p1Dif = Difficulty.Medium;
			Difficulty p2Dif = Difficulty.Hard;
			
			while( p1hasMove || p2hasMove) {
				FieldPosition p = AI.getNextMove(currentPlayer == FieldType.Player1 ? p1Dif : p2Dif, logic, currentPlayer);
				
				if (p != null) {
					logic.makeMove(currentPlayer, p);
					
					if (currentPlayer == FieldType.Player1) { p1hasMove = true;}
					else { p2hasMove = true; }
				} else {
					if (currentPlayer == FieldType.Player1) { p1hasMove = false;}
					else { p2hasMove = false; }
				}
				if (currentPlayer == FieldType.Player1) { currentPlayer = FieldType.Player2;}
				else { currentPlayer = FieldType.Player1; }
			}
			return logic.getScore();
			 
		} catch (Exception e) {
			System.out.println("Failed to init logic: " + e);
			return null;
		}
	}
	
	/**
	 * Method to simulate a game using our AI. State updates are logged after each move
	 */
	private static void consoleExampleWithOutput() {
		try {
			BoardLogic logic = new BoardLogic();
			Boolean p1hasMove = true;
			Boolean p2hasMove = true;
			
			FieldType currentPlayer = FieldType.Player1;
			
			Difficulty p1Dif = Difficulty.Medium;
			Difficulty p2Dif = Difficulty.Medium;
			
			while( p1hasMove || p2hasMove) {
				logic.printBoardState();
				System.out.println("\n");
				System.out.println(logic.getScore());
				System.out.println("\n\n");
				
				FieldPosition p = AI.getNextMove(currentPlayer == FieldType.Player1 ? p1Dif : p2Dif, logic, currentPlayer);
				
				if (p != null) {
					System.out.println("Player " + currentPlayer + " will make move: " + p + "\n\n");
					logic.makeMove(currentPlayer, p);
					
					if (currentPlayer == FieldType.Player1) { p1hasMove = true;}
					else { p2hasMove = true; }
				} else {
					if (currentPlayer == FieldType.Player1) { p1hasMove = false;}
					else { p2hasMove = false; }
				}
				if (currentPlayer == FieldType.Player1) { currentPlayer = FieldType.Player2;}
				else { currentPlayer = FieldType.Player1; }
			}
			logic.printBoardState();
			System.out.println("\n");
			System.out.println(logic.getScore());
			
		} catch (Exception e) {
			System.out.println("Failed to init logic: " + e);
		}
	}
}
