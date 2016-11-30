package ai;

import java.util.ArrayList;
import java.util.Random;

import logic.*;
/**
 * AI Class
 * @author Benny Lach
 *
 */
public class AI {
	/**
	 * Class function to get the next move for the committed player based on a given difficulty 
	 * @param difficulty The difficulty of the AI
	 * @param currentState The current state of the game
	 * @param player The current player
	 * @return The next move the computer wants to perform. Return statement is null if there is no move possible
	 */
	public static FieldPosition getNextMove(Difficulty difficulty, BoardLogic currentState, FieldType player) {
		if (player == FieldType.Empty) { System.out.println("Empty is not a valid player"); return null; }
		
		switch (difficulty) {
			case Easy:
				return makeEasyMove(currentState, player);
			case Medium:
				return makeMediumMove(currentState, player);
			default:
				return makeHardMove(currentState, player);
		}
	}
	
	/**
	 * Function to calculate the next move for the easy difficulty
	 * 
	 * @param currentState The current state of the game
	 * @param player The player that wants to make the move
	 * @return The next calculated move for the committed player. Return statement is null if there is no move possible
	 */
	private static FieldPosition makeEasyMove(BoardLogic currentState, FieldType player) {
		// Fetch all possible moves
		// Select randomly one of the moves
		// return the selected move
		ArrayList<FieldPosition> moves = currentState.getPossibleMoves(player);
		
		if (!moves.isEmpty()) {
			Random rn = new Random();
			// return one of the possible moves selected randomly
			return moves.get(rn.nextInt(moves.size()));
		}
		// There is no move available
		return null;
	}
	
	/**
	 * Function to calculate the next move for the medium difficulty
	 * 
	 * @param currentState The current state of the game
	 * @param player The player that wants to make the move
	 * @return The next calculated move for the committed player. Return statement is null if there is no move possible
	 */
	private static FieldPosition makeMediumMove(BoardLogic currentState, FieldType player) {
		// Fetch all possible moves
		// Perform each move on a copy of the current state to calculate the best possible move the computer is able to do
		// return that move 
		ArrayList<FieldPosition> moves = currentState.getPossibleMoves(player);
		
		if (!moves.isEmpty()) {
			// Track the score and made move to decide which move should be performed at the end
			int moveIndex = 0;
			int points = 0;
			
			for (int i = 0; i < moves.size(); i++) {
				// Make a copy of the current state to perform actions without side effects to the real board state
				try {
					BoardLogic stateCopy = (BoardLogic) currentState.clone();
					// get the move at index i
					FieldPosition moveToPerform = moves.get(i);
					// Perform a dry move on the copied state
					stateCopy.makeMove(player, moveToPerform);
					Score score = stateCopy.getScore();
					int _points = player == FieldType.Player1 ? score.p1() : score.p2();
					// if the points the computer will get are higher save the current index to track them
					if (points < _points) {
						moveIndex = i;
						points = _points;
					}
				} catch (Exception e) {
					System.err.println("Failed to copy the boardState");
					return null;
				}
			}
			// return the best move  
			return moves.get(moveIndex);
		}
		// There is no move available
		return null;
	}
	/**
	 * Function to calculate the next move for the medium difficulty
	 * 
	 * @param currentState The current state of the game
	 * @param player The player that wants to make the move
	 * @return The next calculated move for the committed player. Return statement is null if there is no move possible
	 */
	private static FieldPosition makeHardMove(BoardLogic currentState, FieldType player) {
		// Fetch all possible moves
		// Perform each move on a copy of the current state to calculate the best possible move for this round
		// Check all possible moves for the enemy after the move by the computer was made
		// return the move that's the best for the current state but also gives the enemy the least possible points afterwards
		ArrayList<FieldPosition> moves = currentState.getPossibleMoves(player);
		
		if (!moves.isEmpty()) {
			// Track the score and made move to decide which move should be performed at the end
			int moveIndex = 0;
			int points = player == FieldType.Player1 ? currentState.getScore().p1() : currentState.getScore().p2();
			int enemyPoints = player == FieldType.Player1 ? currentState.getScore().p2() : currentState.getScore().p1();
			FieldType enemy = player == FieldType.Player1 ? FieldType.Player2 : FieldType.Player1; 
			
			for (int i = 0; i < moves.size(); i++) {
				// Make a copy of the current state to perform actions without side effects to the real board state
				try {
					BoardLogic stateCopy = (BoardLogic) currentState.clone();
					// get the move at index i
					FieldPosition moveToPerform = moves.get(i);
					// Perform a dry move on the copied state
					stateCopy.makeMove(player, moveToPerform);
					// get the best move of the enemy based on the dry state
					Score score = stateCopy.getScore();
					int _points = player == FieldType.Player1 ? score.p1() : score.p2();
					int _enemyPoints = player == FieldType.Player1 ? score.p2() : score.p1();
					FieldPosition enemyMove = makeMediumMove(stateCopy, enemy);
					// We could perform a move where the enemy has no option to make a move afterwards 
					if (enemyMove != null ) {
						// Simulate the best move the enemy is able to do
						stateCopy.makeMove(enemy, enemyMove);

						// Get the points for both the computer and the enemy
						score = stateCopy.getScore();
						_points = player == FieldType.Player1 ? score.p1() : score.p2();
						_enemyPoints = player == FieldType.Player1 ? score.p2() : score.p1(); 
					}
					// if the points the computer will get are higher or the points of the enemy will get are lower save the current index to track the move
					if (points < _points || (_points >= points && _enemyPoints < enemyPoints)) {
						moveIndex = i;
						points = _points;
						enemyPoints = _enemyPoints;
					}
				} catch (Exception e) {
					System.err.println("Failed to copy the boardState: \n" + e);
					return null;
				}
			}
			// return the best move  
			return moves.get(moveIndex);
		}
		// There is no move available
		return null;
	}
}
