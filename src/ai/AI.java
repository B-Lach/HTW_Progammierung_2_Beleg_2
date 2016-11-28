package ai;

import logic.*;
/**
 * AI Class
 * @author Benny Lach
 *
 */
public class AI {
	/**
	 * Class function to get the next move for the computer
	 * @param difficulty The difficulty of the AI
	 * @param currentState The current state of the game
	 * @return Will return the next move made by the computer
	 */
	public static FieldPosition makeMove(Difficulty difficulty, BoardLogic currentState) {
		switch (difficulty) {
		case Easy:
			return makeEasyMove(currentState);
		default:
			return makeMediumMove(currentState);
		}
		
	}
	
	/**
	 * Function to calculate the next move for the easy difficulty
	 * 
	 * @param currentState The current state of the game
	 * @return Will return the next move made by the computer
	 */
	private static FieldPosition makeEasyMove(BoardLogic currentState) {
		// Get all possible moves and select randomly one that will be performed
	}
	
	/**
	 * Function to calculate the next move for the medium difficulty
	 * 
	 * @param currentState The current state of the game
	 * @return Will return the next move made by the computer
	 */
	private static FieldPosition makeMediumMove(BoardLogic currentState) {
		// Get all possible moves and select the best move for the current state
		// based on the made points
		
	}
}
