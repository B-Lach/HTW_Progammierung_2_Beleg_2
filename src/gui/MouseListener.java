package gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import logic.*;

public class MouseListener extends MouseAdapter {

	GuiMain main;
	private FieldType[][] board;
	private FieldType player = FieldType.Empty;
	private FieldPosition position;

	public MouseListener(BoardLogic logic) {
		board = logic.getBoardState();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int x, y, rowNum = -1, columNum = -1;
		x = e.getX();
		y = e.getY();

		if (x >= 50) {

			int pos1 = x / 60;

			switch (pos1) {
			case 0:
				rowNum = 0;
				break;
			case 1:
				rowNum = 1;
				break;
			case 2:
				rowNum = 2;
				break;
			case 3:
				rowNum = 3;
				break;
			case 4:
				rowNum = 4;
				break;
			case 5:
				rowNum = 5;
				break;
			case 6:
				rowNum = 6;
				break;
			case 7:
				rowNum = 7;
				break;
			case 8:
				rowNum = 8;
				break;
			case 9:
				rowNum = 9;
				break;
			default:
				System.out.println("hier ist was falsch gelaufen");
				break;
			}
		}
		if (y >= 50) {
			int pos2 = y / 60;

			switch (pos2) {
			case 0:
				columNum = 0;
				break;
			case 1:
				columNum = 1;
				break;
			case 2:
				columNum = 2;
				break;
			case 3:
				columNum = 3;
				break;
			case 4:
				columNum = 4;
				break;
			case 5:
				columNum = 5;
				break;
			case 6:
				columNum = 6;
				break;
			case 7:
				columNum = 7;
				break;
			case 8:
				columNum = 8;
				break;
			case 9:
				columNum = 9;
				break;
			default:
				System.out.println("hier ist was falsch gelaufen");
				break;
			}
		}
		try {

			if (rowNum >= 0 && columNum >= 0 && board[rowNum][columNum] == FieldType.Empty) {
				if (player == FieldType.Empty) {
					player = FieldType.Player1;
					position = new FieldPosition(rowNum, columNum);
					boolean move;
					makeMove(player, position);
					player = FieldType.Player2;
				} else if (player == FieldType.Player1) {
					position = new FieldPosition(rowNum, columNum);
					makeMove(player, position);
					player = FieldType.Player2;

				} else if (player == FieldType.Player2) {
					position = new FieldPosition(rowNum, columNum);
					makeMove(player, position);
					player = FieldType.Player1;
				}
			}
		} catch (Exception d) {
			System.out.println("viele bunte fehler");
		}
	}
}
