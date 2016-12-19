package gui;

import logic.*;
import ai.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class PaintBoard extends JPanel {
	
	private int boardSize;
	private FieldType[][] board;
	private ArrayList<FieldPosition> possibleMoves;
	private FieldType player = FieldType.Player1;
	private Score score1;

	public PaintBoard(BoardLogic logic) {
		boardSize = logic.getBoardSize();
		board = logic.getBoardState();	
		possibleMoves = logic.getPossibleMoves(player);
		score1 = logic.getScore();
	}

	public void repaint(BoardLogic logic) {
		score1 = logic.getScore();
		board = logic.getBoardState();
		
		if(player == FieldType.Player1){
			player = FieldType.Player2;
		}else if(player == FieldType.Player2){
			player = FieldType.Player1;
		}
		possibleMoves = logic.getPossibleMoves(player);
		
		repaint();
	}

	public void paint(Graphics g) {
		super.paint(g);
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
			System.out.println(possibleMoves.size());
			System.out.println(possibleMoves.toString());
			FieldPosition position;
			System.out.println(player);
			position = possibleMoves.get(k);
			
			int x2 = position.getX();
			int y2 = position.getY();
			
			g.setColor(Color.red);
			g.fillOval(65 + x2 * 60, 65 + y2 * 60, 30, 30);
			g.drawOval(65 + x2 * 60, 65 + y2 * 60, 30, 30);
		}
		g.setColor(Color.white);
		g.fillRect(50, 680, 200, 30);
		g.setColor(Color.black);
		g.setFont(new Font("Serif", Font.BOLD, 20));
		g.drawString(player + " ist am Zug", 50, 700);
		
		g.setColor(Color.white);
		g.fillRect(300, 680, 400, 30);
		g.setColor(Color.black);
		g.setFont(new Font("Serif", Font.BOLD, 20));
		g.drawString(score1.toString(), 300, 700);
	}
}
