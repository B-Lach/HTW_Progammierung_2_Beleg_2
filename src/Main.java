import java.util.ArrayList;

import logic.*;

public class Main {

	public static void main(String[] args) {
		consoleExample();
	}
	
	private static void consoleExample() {
		try {
			BoardLogic logic = new BoardLogic();
			Boolean p1hasMove = true;
			Boolean p2hasMove = true;
			FieldType currentPlayer = FieldType.Player1;
			
			while( p1hasMove || p2hasMove) {
				logic.printBoardState();
				System.out.println("\n");
				System.out.println(logic.getScore());
				System.out.println("\n\n");
				ArrayList<FieldPosition> moves = logic.getPossibleMoves(currentPlayer);
				System.out.println("Possible Moves for " + currentPlayer);
				System.out.println(moves + "\n\n");
				if (!moves.isEmpty()) {
					FieldPosition p = moves.get(0);
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
