package logic;

import java.util.ArrayList;
/**
 * Class for handling the logic of a reversi game
 * @author Benny Lach
 *
 */
public class BoardLogic implements Cloneable {
	protected FieldType[][] boardState;
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
	 * Method to get a cloned version of the current state
	 * @return The current state
	 */
	public FieldType[][] getBoardState() {
		return getStateClone();
	}
	
	/**
	 * Method to get the size of the board
	 * @return The size
	 */
	public int getBoardSize() {
		return size;
	}
	
	/**
	 * Method to get all possible moves for a player based on the current state of the board
	 * @param player The player you want to get the moves for 
	 * @return An array containing all possible moves. The array will be empty if there is no move possible
	 */
	public ArrayList<FieldPosition> getPossibleMoves(FieldType player) {
		ArrayList<FieldPosition> moves = new ArrayList<FieldPosition>();
		if (player == FieldType.Empty) {
			System.out.println("Empty is not a valid player");
			return moves;
		}
		// iterate over board and search for all possible moves
		
		
		for (int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				FieldType type = boardState[i][j];
				if (type == FieldType.Empty) {
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
	
	/**
	 * Method to perform a move by a given player and a given position
	 * @param player The player the move is related to
	 * @param p The position of the player
	 * @return Boolean to reference if the move was made 
	 */
	public Boolean makeMove(FieldType player, FieldPosition p) {
		// Empty is not a valid player
		if (player == FieldType.Empty) { System.out.println("Empty is not a valid player"); return false;}
		
		// We have to check first which moves are possible from the given position 
		Boolean hbp = horizontalBackwardMovePossible(player, p.getX(), p.getY());
		Boolean hfp = horizontalForwardMovePossible(player, p.getX(), p.getY());
		Boolean vbp = verticalBackwardMovePossible(player, p.getX(), p.getY());
		Boolean vfp = verticalForwardMovePossible(player, p.getX(), p.getY());
		Boolean dbup = diagonalBackwardMoveUpPossible(player, p.getX(), p.getY());
		Boolean dbdp = diagonalBackwardMoveDownPossible(player, p.getX(), p.getY());
		Boolean dfp = diagonalForwardMovePossible(player, p.getX(), p.getY());
		
		// horizontal backward move possible
		if (hbp) { makeHorizontalBackwardMove(player, p); }
		// horizontal forward move possible
		if (hfp) { makeHorizontalForwardMove(player, p); }
		// vertical backward move possible
		if (vbp) { makeVerticalBackwardMove(player, p); }
		// vertical forward move possible
		if (vfp) { makeVerticalForwardMove(player, p); }
		// diagonal backward up move possible
		if (dbup) { makeDiagonalBackwardUpMove(player, p); }
		// diagonal backward down move possible
		if (dbdp) { makeDiagonalBackwardDownMove(player, p); }
		// diagonal forward move possible
		if (dfp) { makeDiagonalForwardMove(player, p); }
		
		
		return hbp || hfp || vbp || vfp || dbup || dfp; 
	}
	
	/**
	 * Method to get the current score of the board
	 * @return The current score 
	 */
	public Score getScore() {
		int p1score = 0;
		int p2score = 0;
		
		// iterate over the board and allocate the current score
		for(int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				FieldType type = boardState[i][j];
				
				switch(type) {
				case Player1:
					p1score++;
					break;
				case Player2:
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
				if (boardState[i][j] == FieldType.Player1) { value = p1; }
				if (boardState[i][j] == FieldType.Player2) { value = p2; }
						
				System.out.print("["+ value + "]");
			}
			System.out.println();
		}
	}
	
	/**
	 * Method to make cloning possible
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		// Clone the state object
		BoardLogic clone = (BoardLogic) super.clone();
		// We have to initialize a new FieldType array. 
		// Using clone.boardstate.clone() does generate a clone BUT the second dimension is still a reference to the original object 
		// -> We would change the original state but we don't want to 
		clone.boardState = getStateClone();
        
		return clone;
    }
	
	/**
	 * Method to get a clone of the current state
	 * Used to guarantee immutability of the internal state
	 * @return cloned state
	 */
	private FieldType[][] getStateClone() {
		FieldType[][] state = new FieldType[this.size][this.size];

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				state[i][j] = this.boardState[i][j];
			}
		}
		return state;
	}
	
	/**
	 * Prepares the board for usage after initialization
	 */
	private void boardSetup() {
		boardState = new FieldType[size][size];
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				boardState[i][j] = FieldType.Empty;
			}
		}
		
		boardState[(size/2) - 1][(size/2) - 1] = FieldType.Player1;
		boardState[(size/2) - 1][(size/2) ] = FieldType.Player2;
		boardState[(size/2)][(size/2) - 1] = FieldType.Player2;
		boardState[(size/2)][(size/2) ] = FieldType.Player1;
	}
	
	/**
	 * Method to get the enemy based on a given player
	 * @param player The player
	 * @return The enemy
	 */
	private FieldType getEnemy(FieldType player) {
		if (player == FieldType.Player1) { return FieldType.Player2; }
		return FieldType.Player1;
	}
	
	/**
	 * Method to make a horizontal backward move
	 * @param player The player who wants to perform the move
	 * @param p The start position of the move
	 */
	private void makeHorizontalBackwardMove(FieldType player, FieldPosition p) {
		FieldType enemy = getEnemy(player);
		int x = p.getX();
		int y = p.getY();
		
		boardState[y][x] = player;
		x--;
		
		while (enemy != player) {
			enemy = boardState[y][x];
			boardState[y][x] = player;
			x--;
		}
	}
	
	/**
	 * Method to make a horizontal forward move
	 * @param player The player who wants to perform the move
	 * @param p The start position of the move
	 */
	private void makeHorizontalForwardMove(FieldType player, FieldPosition p) {
		FieldType enemy = getEnemy(player);
		int x = p.getX();
		int y = p.getY();
		
		boardState[y][x] = player;
		x++;
		
		while (enemy != player) {
			enemy = boardState[y][x];
			boardState[y][x] = player;
			x++;
		}
	}
	
	/**
	 * Method to make a vertical backward move
	 * @param player The player who wants to perform the move
	 * @param p The start position of the move
	 */
	private void makeVerticalBackwardMove(FieldType player, FieldPosition p) {
		FieldType enemy = getEnemy(player);
		int x = p.getX();
		int y = p.getY();
		
		boardState[y][x] = player;
		y--;
		
		while (enemy != player) {
			enemy = boardState[y][x];
			boardState[y][x] = player;
			y--;
		}
	}
	
	/**
	 * Method to make a vertical forward move
	 * @param player The player who wants to perform the move
	 * @param p The start position of the move
	 */
	private void makeVerticalForwardMove(FieldType player, FieldPosition p) {
		FieldType enemy = getEnemy(player);
		int x = p.getX();
		int y = p.getY();
		
		boardState[y][x] = player;
		y++;
		
		while (enemy != player) {
			enemy = boardState[y][x];
			boardState[y][x] = player;
			y++;
		}
	}
	
	/**
	 * Method to make a diagonal backward up move
	 * @param player The player who wants to perform the move
	 * @param p The start position of the move
	 */
	private void makeDiagonalBackwardUpMove(FieldType player, FieldPosition p) {
		FieldType enemy = getEnemy(player);
		int x = p.getX();
		int y = p.getY();
		
		boardState[y][x] = player;
		x--;
		y--;
		
		while (enemy != player) {
			enemy = boardState[y][x];
			boardState[y][x] = player;
			x--;
			y--;
		}
	}
	
	/**
	 * Method to make a diagonal backward down move
	 * @param player The player who wants to perform the move
	 * @param p The start position of the move
	 */
	private void makeDiagonalBackwardDownMove(FieldType player, FieldPosition p) {
		FieldType enemy = getEnemy(player);
		int x = p.getX();
		int y = p.getY();
		
		boardState[y][x] = player;
		x--;
		y++;
		
		while (enemy != player) {
			enemy = boardState[y][x];
			boardState[y][x] = player;
			x--;
			y++;
		}
	}
	
	/**
	 * Method to make a diagonal forward move
	 * @param player The player who wants to perform the move
	 * @param p The start position of the move
	 */
	private void makeDiagonalForwardMove(FieldType player, FieldPosition p) {
		FieldType enemy = getEnemy(player);
		int x = p.getX();
		int y = p.getY();
		
		boardState[y][x] = player;
		x++;
		y++;
		
		while (enemy != player) {
			enemy = boardState[y][x];
			boardState[y][x] = player;
			x++;
			y++;
		}
	}
	
	/**
	 * Method to check if a given position is valid for a horizontal move made by a given player
	 * @param player The player the check is made for
	 * @param x X-Position of the board 
	 * @param y Y-Position of the board
	 * @return Returns true if position is valid otherwise false
	 */
	private Boolean horizontalMovePossible(FieldType player, int x, int y) {
		Boolean backward = horizontalBackwardMovePossible(player, x, y);
		Boolean forward = horizontalForwardMovePossible(player, x, y);
		
		return backward || forward;
	}
	
	/**
	 * Method to check if a given position is valid for a horizontal forward move made by a given player
	 * @param player The player the check is made for
	 * @param x X-Position of the board 
	 * @param y Y-Position of the board
	 * @return Returns true if position is valid otherwise false
	 */
	private Boolean horizontalForwardMovePossible(FieldType player, int x, int y) {
		// if the given position is not empty return false
		if (boardState[y][x] != FieldType.Empty) { return false; }
		// if the given position x value == size-1 return false
		if (x == size-1) { return false; }
		// if the given position x value+1 == player or is empty return false 
		if (boardState[y][x+1] == player || boardState[y][x+1] == FieldType.Empty) { return false; }

		int playerCount = 0;
		int emptyCount = 0;
		// forward iteration from the fields x position to 0
		//     |
		// [ ][ ][o][x][ ][ ]
		for(int i = x+2; i < size; i++) {
			if (boardState[y][i] == player) {
				playerCount++; 
			} 
			if (boardState[y][i] == FieldType.Empty) { 
				emptyCount++; 
			}
			// if an empty field was found exit the loop 
			if (emptyCount > 0) { i = size; } 
		}
		return playerCount > 0;
	}
	
	/**
	 * Method to check if a given position is valid for a horizontal backward move made by a given player
	 * @param player The player the check is made for
	 * @param x X-Position of the board 
	 * @param y Y-Position of the board
	 * @return Returns true if position is valid otherwise false
	 */
	private Boolean horizontalBackwardMovePossible(FieldType player, int x, int y) {
		// if the given position is not empty return false
		if (boardState[y][x] != FieldType.Empty) { return false; }
		// if the given position x value == 0 return false
		if (x == 0) { return false; }
		// if the given position x value-1 == player or is empty return false 
		if (boardState[y][x-1] == player || boardState[y][x-1] == FieldType.Empty) { return false; }
		
		int playerCount = 0;
		int emptyCount = 0;
		// backward iteration from the fields x position to 0
		//           |
		// [ ][x][o][ ][ ][ ]
		for(int i = x-2; i >= 0; i--) {
			if (boardState[y][i] == player) {
				playerCount++; 
			} 
			if (boardState[y][i] == FieldType.Empty) { 
				emptyCount++; 
			}
			// if an empty field was found exit the loop 
			if (emptyCount > 0) { i = -1; } 
		}
		// only if we found at least on player item, the move is valid
		return playerCount > 0;
	}
	
	/**
	 * Method to check if a given position is valid for a vertical move made by a given player
	 * @param player The player the check is made for
	 * @param x X-Position of the board 
	 * @param y Y-Position of the board
	 * @return Returns true if position is valid otherwise false
	 */
	private Boolean verticalMovePossible(FieldType player, int x, int y) {
		Boolean backward = verticalBackwardMovePossible(player, x, y);
		Boolean forward = verticalForwardMovePossible(player, x, y);
		
		return backward || forward;
	}
	
	/**
	 * Method to check if a given position is valid for a vertical backward move made by a given player
	 * @param player The player the check is made for
	 * @param x X-Position of the board 
	 * @param y Y-Position of the board
	 * @return Returns true if position is valid otherwise false
	 */
	private Boolean verticalBackwardMovePossible(FieldType player, int x, int y) {
		// if the given position is not empty return false
		if (boardState[y][x] != FieldType.Empty) { return false; }
		// if the given position y value == 0 return false
		if (y == 0) { return false; }
		// if the given position y value-1 == player or is empty return false 
		if (boardState[y-1][x] == player || boardState[y-1][x] == FieldType.Empty) { return false; }

		int playerCount = 0;
		int emptyCount = 0;
		// forward iteration from the fields x position to 0
		for(int i = y-2; i >= 0; i--) {
			if (boardState[i][x] == player) {
				playerCount++; 
			} 
			if (boardState[i][x] == FieldType.Empty) { 
				emptyCount++; 
			}
			// if an empty field was found exit the loop 
			if (emptyCount > 0) { i = -1; } 
		}
		return playerCount > 0;
	}
	
	/**
	 * Method to check if a given position is valid for a vertical forward move made by a given player
	 * @param player The player the check is made for
	 * @param x X-Position of the board 
	 * @param y Y-Position of the board
	 * @return Returns true if position is valid otherwise false
	 */
	private Boolean verticalForwardMovePossible(FieldType player, int x, int y) {
		// if the given position is not empty return false
		if (boardState[y][x] != FieldType.Empty) { return false; }
		// if the given position y value == size-1 return false
		if (y == size-1) { return false; }
		// if the given position y value+1 == player or is empty return false 
		if (boardState[y+1][x] == player || boardState[y+1][x] == FieldType.Empty) { return false; }

		int playerCount = 0;
		int emptyCount = 0;
		// forward iteration from the fields x position to 0
		//     |
		// [ ][ ][o][x][ ][ ]
		for(int i = y+2; i < size; i++) {
			if (boardState[i][x] == player) {
				playerCount++; 
			} 
			if (boardState[i][x] == FieldType.Empty) { 
				emptyCount++; 
			}
			// if an empty field was found exit the loop 
			if (emptyCount > 0) { i = size; } 
		}
		return playerCount > 0;
		
		
	}
	
	/**
	 * Method to check if a given position is valid for a diagonal move made by a given player
	 * @param player The player the check is made for
	 * @param x X-Position of the board 
	 * @param y Y-Position of the board
	 * @return Returns true if position is valid otherwise false
	 */
	private Boolean diagonalMovePossible(FieldType player, int x, int y) {
		Boolean backwardUp = diagonalBackwardMoveUpPossible(player, x, y);
		Boolean backwardDown = diagonalBackwardMoveDownPossible(player, x, y);
		
		Boolean forward = diagonalForwardMovePossible(player, x, y);
		
		return backwardUp || backwardDown || forward;
	}
	
	/**
	 * Method to check if a given position is valid for a diagonal backward up move made by a given player
	 * @param player The player the check is made for
	 * @param x X-Position of the board 
	 * @param y Y-Position of the board
	 * @return Returns true if position is valid otherwise false
	 */
	private Boolean diagonalBackwardMoveUpPossible(FieldType player, int x, int y) {
		// if the given position is not empty return false
		if (boardState[y][x] != FieldType.Empty) { return false; }
		// if the given position y value == 0 or x value == 0 return false
		if (y == 0 || x == 0) { return false; }
		// if the given position -1 == player or is empty return false 
		if (boardState[y-1][x-1] == player || boardState[y-1][x-1] == FieldType.Empty) { return false; }

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
				if (boardState[currentY][i] == FieldType.Empty) { emptyCount++; }
				if (emptyCount > 0) { i = -1; }
				currentY--;
			}
		} else {
			currentX -= 2;
			for (int i = currentY-2; i >= 0; i--) {
				if (boardState[i][currentX] == player) { playerCount++; }
				if (boardState[i][currentX] == FieldType.Empty) { emptyCount++; }
				if (emptyCount > 0) { i = -1; }
				currentX--;
			}
		}	
		return playerCount > 0;
	}
	
	/**
	 * Method to check if a given position is valid for a diagonal backward down move made by a given player
	 * @param player The player the check is made for
	 * @param x X-Position of the board 
	 * @param y Y-Position of the board
	 * @return Returns true if position is valid otherwise false
	 */
	private Boolean diagonalBackwardMoveDownPossible(FieldType player, int x, int y) {
		System.out.println("x: " + x + " y: " + y);
		// if the given position is not empty return false
		if (boardState[y][x] != FieldType.Empty) { return false; }
		// if the given position y value == size-1 or x value == 0 return false
		if (y == size-1 || x == 0) { return false; }
		// if the given position -1 == player or is empty return false 
		if (boardState[y+1][x-1] == player || boardState[y+1][x-1] == FieldType.Empty) { return false; }

		int playerCount = 0;
		int emptyCount = 0;
		
		int currentX = x;
		int currentY = y;
		// if the current x position is smaller iterate over x position
		// otherwise iterate over y position
		if (currentX < size-(currentY+1)) {
			currentY += 2;
			for(int i = currentX-2; i >= 0; i--) {
				if (boardState[currentY][i] == player) { playerCount++; }
				if (boardState[currentY][i] == FieldType.Empty) { emptyCount++; }
				if (emptyCount > 0) { i = -1; }
				currentY++;
			}
		} else {
			currentX -= 2;
			for (int i = currentY+2; i < size; i++) {
				System.out.println("In loop - x: " + currentX + " y: " + i);
				if (boardState[i][currentX] == player) { playerCount++; }
				if (boardState[i][currentX] == FieldType.Empty) { emptyCount++; }
				if (emptyCount > 0) { i = size; }
				currentX--;
			}
		}	
		return playerCount > 0;
	}
	
	/**
	 * Method to check if a given position is valid for a diagonal forward move made by a given player
	 * @param player The player the check is made for
	 * @param x X-Position of the board 
	 * @param y Y-Position of the board
	 * @return Returns true if position is valid otherwise false
	 */
	private Boolean diagonalForwardMovePossible(FieldType player, int x, int y) {
		// if the given position is not empty return false
		if (boardState[y][x] != FieldType.Empty) { return false; }
		// if the given position y value == size-1 or x value == size-1 return false
		if (y == size-1 || x == size-1) { return false; }
		// if the given position +1 == player or is empty return false 
		if (boardState[y+1][x+1] == player || boardState[y+1][x+1] == FieldType.Empty) { return false; }

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
				if (boardState[currentY][i] == FieldType.Empty) { emptyCount++; }
				if (emptyCount > 0) { i = size; }
				currentY++;
			}
		} else {
			currentX += 2;
			for (int i = currentY+2; i < size; i++) {
				if (boardState[i][currentX] == player) { playerCount++; }
				if (boardState[i][currentX] == FieldType.Empty) { emptyCount++; }
				if (emptyCount > 0) { i = size; }
				currentX++;
			}
		}
		return playerCount > 0;
	}
}
