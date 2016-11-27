import java.util.ArrayList;

import logic.*;

public class Main {

	public static void main(String[] args) {
		try {
			BoardLogic logic = new BoardLogic();
			Boolean p1hasMove = true;
			Boolean p2hasMove = true;
			FieldType currentPlayer = FieldType.player1;
			
			while( p1hasMove || p2hasMove) {
				logic.printBoardState();
				System.out.println("\n\n");
				ArrayList<FieldPosition> moves = logic.getPossibleMoves(currentPlayer);
				System.out.println("Possible Moves for " + currentPlayer);
				System.out.println(moves + "\n\n");
				if (!moves.isEmpty()) {
					FieldPosition p = moves.get(0);
					System.out.println("Player " + currentPlayer + " will make move: " + p + "\n\n");
					logic.makeMove(currentPlayer, p);
					
					if (currentPlayer == FieldType.player1) { p1hasMove = true;}
					else { p2hasMove = true; }
				} else {
					if (currentPlayer == FieldType.player1) { p1hasMove = false;}
					else { p2hasMove = false; }
				}
				if (currentPlayer == FieldType.player1) { currentPlayer = FieldType.player2;}
				else { currentPlayer = FieldType.player1; }
			}
			logic.printBoardState();
			
		} catch (Exception e) {
			System.out.println("Failed to init logic: " + e);
		}

	}

}
