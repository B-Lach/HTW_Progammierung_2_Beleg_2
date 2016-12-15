package gui;

import logic.*;
import ai.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PaintBoard extends JPanel {

	private int boardSize;
	private FieldType[][] board;
	private BoardLogic logic;
	private ArrayList<FieldPosition> possibleMoves;
	private FieldType player;

	public PaintBoard(BoardLogic logic) {

		boardSize = logic.getBoardSize();
		board = logic.getBoardState();
		player = GuiMain.getPlayer();
		possibleMoves = logic.getPossibleMoves(player);
	}

	public void repaint(BoardLogic logic) {
		board = logic.getBoardState();
		possibleMoves = logic.getPossibleMoves(player);
		
		repaint();
	}

	public void paint(Graphics g) {
		super.paintComponents(g);
		char ST;
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				if (board[i][j] == FieldType.Empty) {
					g.setColor(Color.yellow);
					ST = ' ';
					
				} else if (board[i][j] == FieldType.Player1) {
					g.setColor(Color.green);
					ST = 'X';
				} else {
					g.setColor(Color.magenta);
					ST = 'O';
				}

				g.fillRect(50 + j * 60, 50 + i * 60, 60, 60);
				g.setColor(Color.black);
				g.drawRect(50 + j * 60, 50 + i * 60, 60, 60);
				g.setFont(new Font("Serif", Font.BOLD, 30));
				g.drawString("" + ST, 70 + j * 60, 90 + i * 60);
				
			}
			
		}
		for (int k = 0; k < possibleMoves.size(); k++) {
			FieldPosition position;
			position = possibleMoves.get(k);
			int x2 = position.getX();
			int y2 = position.getY();
			if (board[x2][y2] == FieldType.Empty) {
				g.setColor(Color.red);
				g.fillOval(50 + y2 * 60, 50 + x2 * 60, 30, 30);

				g.drawOval(50 + y2 * 60, 50 + x2 * 60, 30, 30);
			}
		}
	}
}
