package gui;

import logic.*;
import ai.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;

public class PaintBoard extends JFrame {

	int boardSize = BoardLogic.getBoardSize();
	FieldType[][] board = new FieldType[boardSize][boardSize];
	
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
		g.setColor(Color.white);
		g.fillRect(50, 260, 200, 30);
		g.setColor(Color.black);
		g.setFont(new Font("Serif", Font.BOLD, 20));

	}
}
