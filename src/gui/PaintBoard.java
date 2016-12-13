package gui;

import logic.*;
import ai.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PaintBoard extends JPanel {
	private int boardSize;
	private FieldType[][] board;
	private BoardLogic logic;
	
	public PaintBoard(BoardLogic logic) {
		boardSize = logic.getBoardSize();
		board = logic.getBoardState();
	}
	
	public void repaint(BoardLogic logic) {
		board = logic.getBoardState();
		repaint();
	}
	
	public void paint(Graphics g) {
		super.paintComponents(g);
		char ST;
		for (int i = 0; i < boardSize; i++)
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
}
