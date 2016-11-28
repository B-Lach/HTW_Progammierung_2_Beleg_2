package logic;

import java.util.ArrayList;

/**
 * 
 * @author Benny Lach
 *
 */

public class BoardLogic {
	private FieldType[][] boardState;
	private int size;
	
	/**
	 * Constructor to initialize the board with a default size of 6x6
	 */
	public BoardLogic() {
		size = 6;
		
		boardSetup();
	}
	
	/**
	 * Constructor to initialize the board with a given size
	 * @param size Size of the board
	 * @throws Exception The exception will be thrown if size is odd or if the size is not between 6 and 8
	 */
	public BoardLogic(int size) throws Exception {
		if (size % 2 != 0) {
			throw new Exception("The size is odd");
		}
		if (size < 6 || size > 8) {
			throw new Exception("Size needs to be between 6 and 8");
		}
		
		this.size = size;
		
		boardSetup();
	}
	
	/**
	 * Function to get the current state
	 * @return The current state
	 */
	public FieldType[][] getBoardState() {
		return boardState;
	}
	
	/**
	 * Prepares the board for usage after initialization
	 */
	private void boardSetup() {
		boardState = new FieldType[size][size];
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				boardState[i][j] = FieldType.empty;
			}
		}
		
		boardState[(size/2) - 1][(size/2) - 1] = FieldType.player1;
		boardState[(size/2) - 1][(size/2) ] = FieldType.player2;
		boardState[(size/2)][(size/2) - 1] = FieldType.player2;
		boardState[(size/2)][(size/2) ] = FieldType.player1;
	}
	
	public ArrayList<FieldPosition> getPossibleMoves(FieldType player) {
		ArrayList<FieldPosition> moves = new ArrayList<FieldPosition>();
		if (player == FieldType.empty) {
			System.out.println("Empty is not a valid player");
			return moves;
		}
		// iterate over board and search for all possible moves
		
		
		for (int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				FieldType type = boardState[i][j];
				if (type == FieldType.empty) {
					Boolean horizontal = horizontalMovePossible(player, j, i); 
					Boolean vertical = verticalMovePossible(player, j, i);
					Boolean diagonal = diagonalMovePossible(player, j, i);
					
					// if one of three is true, we've found a valid position to make a move
					if (horizontal || vertical || diagonal) {
						FieldPosition p = new FieldPosition(j, i);
						moves.add(p);
					}
				}
			}
		}
		return moves;
	}
	
	public Boolean makeMove(FieldType player, FieldPosition p) {
		// Empty is not a valid player
		if (player == FieldType.empty) { System.out.println("Empty is not a valid player"); return false;}
		
		// We have to check first which moves are possible from the given position 
		Boolean hbp = horizontalBackwardMovePossible(player, p.getX(), p.getY());
		Boolean hfp = horizontalForwardMovePossible(player, p.getX(), p.getY());
		Boolean vbp = verticalBackwardMovePossible(player, p.getX(), p.getY());
		Boolean vfp = verticalForwardMovePossible(player, p.getX(), p.getY());
		Boolean dbp = diagonalBackwardMovePossible(player, p.getX(), p.getY());
		Boolean dfp = diagonalForwardMovePossible(player, p.getX(), p.getY());
		
		// horizontal backward move possible
		if (hbp) { makeHorizontalBackwardMove(player, p); }
		// horizontal forward move possible
		if (hfp) { makeHorizontalForwardMove(player, p); }
		// vertical backward move possible
		if (vbp) { makeVerticalBackwardMove(player, p); }
		// vertical forward move possible
		if (vfp) { makeVerticalForwardMove(player, p); }
		// diagonal backward move possible
		if (dbp) { makeDiagonalBackwardMove(player, p); }
		// diagonal forward move possible
		if (dfp) { makeDiagonalForwardMove(player, p); }
		
		
		return hbp || hfp || vbp || vfp || dbp || dfp; 
	}
	
	public Score getScore() {
		int p1score = 0;
		int p2score = 0;
		
		// iterate over the board and allocate the current score
		for(int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				FieldType type = boardState[i][j];
				
				switch(type) {
				case player1:
					p1score++;
					break;
				case player2:
					p2score++;
					break;
				default:
					break;
				}
			}
		}
		Score currentScore = new Score(p1score, p2score);
		
		return currentScore;
	}
	
	private FieldType getEnemy(FieldType player) {
		if (player == FieldType.player1) { return FieldType.player2; }
		return FieldType.player1;
	}
	
	private void makeHorizontalBackwardMove(FieldType player, FieldPosition p) {
		FieldType enemy = getEnemy(player);
		int x = p.getX();
		int y = p.getY();
		
		while (enemy != player) {
			enemy = boardState[y][x];
			boardState[y][x] = player;
			x--;
		}
	}
	
	private void makeHorizontalForwardMove(FieldType player, FieldPosition p) {
		FieldType enemy = getEnemy(player);
		int x = p.getX();
		int y = p.getY();
		
		while (enemy != player) {
			enemy = boardState[y][x];
			boardState[y][x] = player;
			x++;
		}
	}
	
	private void makeVerticalBackwardMove(FieldType player, FieldPosition p) {
		FieldType enemy = getEnemy(player);
		int x = p.getX();
		int y = p.getY();
		
		while (enemy != player) {
			enemy = boardState[y][x];
			boardState[y][x] = player;
			y--;
		}
	}
	
	private void makeVerticalForwardMove(FieldType player, FieldPosition p) {
		FieldType enemy = getEnemy(player);
		int x = p.getX();
		int y = p.getY();
		
		while (enemy != player) {
			enemy = boardState[y][x];
			boardState[y][x] = player;
			y++;
		}
	}
	
	private void makeDiagonalBackwardMove(FieldType player, FieldPosition p) {
		FieldType enemy = getEnemy(player);
		int x = p.getX();
		int y = p.getY();
		
		while (enemy != player) {
			enemy = boardState[y][x];
			boardState[y][x] = player;
			x--;
			y--;
		}
	}
	
	private void makeDiagonalForwardMove(FieldType player, FieldPosition p) {
		FieldType enemy = getEnemy(player);
		int x = p.getX();
		int y = p.getY();
		
		while (enemy != player) {
			enemy = boardState[y][x];
			boardState[y][x] = player;
			x++;
			y++;
		}
	}
	
	private Boolean horizontalMovePossible(FieldType player, int x, int y) {
		Boolean backward = horizontalBackwardMovePossible(player, x, y);
		Boolean forward = horizontalForwardMovePossible(player, x, y);
		
		return backward || forward;
	}
	
	/**
	 * Method to check if a given position is valid for a horizontal forward move made by the player
	 * @param player The player the check is made for
	 * @param x X-Position of the board 
	 * @param y Y-Position of the board
	 * @return Returns true if position is valid otherwise false
	 */
	private Boolean horizontalForwardMovePossible(FieldType player, int x, int y) {
		// if the given position is not empty return false
		if (boardState[y][x] != FieldType.empty) { return false; }
		// if the given position x value == size-1 return false
		if (x == size-1) { return false; }
		// if the given position x value+1 == player or is empty return false 
		if (boardState[y][x+1] == player || boardState[y][x+1] == FieldType.empty) { return false; }

		int playerCount = 0;
		int emptyCount = 0;
		// forward iteration from the fields x position to 0
		//     |
		// [ ][ ][o][x][ ][ ]
		for(int i = x+2; i < size; i++) {
			if (boardState[y][i] == player) {
				playerCount++; 
			} 
			if (boardState[y][i] == FieldType.empty) { 
				emptyCount++; 
			}
			// if an empty field was found exit the loop 
			if (emptyCount > 0) { i = size; } 
		}
		return playerCount > 0;
	}
	
	/**
	 * Method to check if a given position is valid for a horizontal forward move made by the player
	 * @param player The player the check is made for
	 * @param x X-Position of the board 
	 * @param y Y-Position of the board
	 * @return Returns true if position is valid otherwise false
	 */
	private Boolean horizontalBackwardMovePossible(FieldType player, int x, int y) {
		// if the given position is not empty return false
		if (boardState[y][x] != FieldType.empty) { return false; }
		// if the given position x value == 0 return false
		if (x == 0) { return false; }
		// if the given position x value-1 == player or is empty return false 
		if (boardState[y][x-1] == player || boardState[y][x-1] == FieldType.empty) { return false; }
		
		int playerCount = 0;
		int emptyCount = 0;
		// backward iteration from the fields x position to 0
		//           |
		// [ ][x][o][ ][ ][ ]
		for(int i = x-2; i >= 0; i--) {
			if (boardState[y][i] == player) {
				playerCount++; 
			} 
			if (boardState[y][i] == FieldType.empty) { 
				emptyCount++; 
			}
			// if an empty field was found exit the loop 
			if (emptyCount > 0) { i = -1; } 
		}
		// only if we found at least on player item, the move is valid
		return playerCount > 0;
	}
	
	private Boolean verticalMovePossible(FieldType player, int x, int y) {
		Boolean backward = verticalBackwardMovePossible(player, x, y);
		Boolean forward = verticalForwardMovePossible(player, x, y);
		
		return backward || forward;
	}
	/**
	 * Method to check if a given position is valid for a vertical move made by the player
	 * @param player The player the check is made for
	 * @param x X-Position of the board 
	 * @param y Y-Position of the board
	 * @return Returns true if position is valid otherwise false
	 */
	private Boolean verticalBackwardMovePossible(FieldType player, int x, int y) {
		// if the given position is not empty return false
		if (boardState[y][x] != FieldType.empty) { return false; }
		// if the given position y value == 0 return false
		if (y == 0) { return false; }
		// if the given position y value-1 == player or is empty return false 
		if (boardState[y-1][x] == player || boardState[y-1][x] == FieldType.empty) { return false; }

		int playerCount = 0;
		int emptyCount = 0;
		// forward iteration from the fields x position to 0
		//     |
		// [ ][ ][o][x][ ][ ]
		for(int i = y-2; i >= 0; i--) {
			if (boardState[i][x] == player) {
				playerCount++; 
			} 
			if (boardState[i][x] == FieldType.empty) { 
				emptyCount++; 
			}
			// if an empty field was found exit the loop 
			if (emptyCount > 0) { i = -1; } 
		}
		return playerCount > 0;
	}
	
	private Boolean verticalForwardMovePossible(FieldType player, int x, int y) {
		// if the given position is not empty return false
		if (boardState[y][x] != FieldType.empty) { return false; }
		// if the given position y value == size-1 return false
		if (y == size-1) { return false; }
		// if the given position y value+1 == player or is empty return false 
		if (boardState[y+1][x] == player || boardState[y+1][x] == FieldType.empty) { return false; }

		int playerCount = 0;
		int emptyCount = 0;
		// forward iteration from the fields x position to 0
		//     |
		// [ ][ ][o][x][ ][ ]
		for(int i = y+2; i < size; i++) {
			if (boardState[i][x] == player) {
				playerCount++; 
			} 
			if (boardState[i][x] == FieldType.empty) { 
				emptyCount++; 
			}
			// if an empty field was found exit the loop 
			if (emptyCount > 0) { i = size; } 
		}
		return playerCount > 0;
		
		
	}
	
	private Boolean diagonalMovePossible(FieldType player, int x, int y) {
		Boolean backward = diagonalBackwardMovePossible(player, x, y);
		Boolean forward = diagonalForwardMovePossible(player, x, y);
		
		return backward || forward;
	}
	
	/**
	 * Method to check if a given position is valid for a diagonal backward move made by the player
	 * @param player The player the check is made for
	 * @param x X-Position of the board 
	 * @param y Y-Position of the board
	 * @return Returns true if position is valid otherwise false
	 */
	private Boolean diagonalBackwardMovePossible(FieldType player, int x, int y) {
		// if the given position is not empty return false
		if (boardState[y][x] != FieldType.empty) { return false; }
		// if the given position y value == 0 or x value == 0 return false
		if (y == 0 || x == 0) { return false; }
		// if the given position -1 == player or is empty return false 
		if (boardState[y-1][x-1] == player || boardState[y-1][x-1] == FieldType.empty) { return false; }

		int playerCount = 0;
		int emptyCount = 0;
		
		int currentX = x;
		int currentY = y;
		// if the current x position is smaller iterate over x position
		// otherwise iterate over y position
		if (currentX < currentY) {
			currentY -= 2;
			for(int i = currentX-2; i >= 0; i--) {
				if (boardState[currentY][i] == player) { playerCount++; }
				if (boardState[currentY][i] == FieldType.empty) { emptyCount++; }
				if (emptyCount > 0) { i = -1; }
				currentY--;
			}
		} else {
			currentX -= 2;
			for (int i = currentY-2; i >= 0; i--) {
				if (boardState[i][currentX] == player) { playerCount++; }
				if (boardState[i][currentX] == FieldType.empty) { emptyCount++; }
				if (emptyCount > 0) { i = -1; }
				currentX--;
			}
		}	
		return playerCount > 0;
	}
	
	/**
	 * Method to check if a given position is valid for a diagonal forward move made by the player
	 * @param player The player the check is made for
	 * @param x X-Position of the board 
	 * @param y Y-Position of the board
	 * @return Returns true if position is valid otherwise false
	 */
	private Boolean diagonalForwardMovePossible(FieldType player, int x, int y) {
		// if the given position is not empty return false
		if (boardState[y][x] != FieldType.empty) { return false; }
		// if the given position y value == size-1 or x value == size-1 return false
		if (y == size-1 || x == size-1) { return false; }
		// if the given position +1 == player or is empty return false 
		if (boardState[y+1][x+1] == player || boardState[y+1][x+1] == FieldType.empty) { return false; }

		int playerCount = 0;
		int emptyCount = 0;
		
		int currentX = x;
		int currentY = y;
		// if the current x position is bigger iterate over x position
		// otherwise iterate over y position
		if (currentX > currentY) {
			currentY += 2;
			for(int i = currentX+2; i < size; i++) {
				if (boardState[currentY][i] == player) { playerCount++; }
				if (boardState[currentY][i] == FieldType.empty) { emptyCount++; }
				if (emptyCount > 0) { i = size; }
				currentY++;
			}
		} else {
			currentX += 2;
			for (int i = currentY+2; i < size; i++) {
				if (boardState[i][currentX] == player) { playerCount++; }
				if (boardState[i][currentX] == FieldType.empty) { emptyCount++; }
				if (emptyCount > 0) { i = size; }
				currentX++;
			}
		}
		return playerCount > 0;
	}
	/**
	 * Log the current state of the board on the console
	 * Mainly used for debugging
	 */
	public void printBoardState() {
		String p1 = "o";
		String p2 = "x";
		System.out.println("Player 1: "+ p1+ " - Player 2: " + p2 + "\n\n");
		System.out.print("   ");
		for (int i = 0; i < size; i++) {
			System.out.print(" "+i+" ");
		}
		System.out.println();
		for (int i = 0; i < size; i++) {
			System.out.print(" "+i+" ");
			for (int j = 0; j < size; j++) {
				String value = " ";
				if (boardState[i][j] == FieldType.player1) { value = p1; }
				if (boardState[i][j] == FieldType.player2) { value = p2; }
						
				System.out.print("["+ value + "]");
			}
			System.out.println();
		}
	}
}
