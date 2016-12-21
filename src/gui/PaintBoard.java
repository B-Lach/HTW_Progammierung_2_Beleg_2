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
	private Score score1;
	private int victory = 0;

	public PaintBoard(BoardLogic logic) {
		boardSize = logic.getBoardSize();
		board = logic.getBoardState();
		possibleMoves = logic.getPossibleMoves(GuiMain.getPlayer());
		score1 = logic.getScore();
	}

	public void repaint(BoardLogic logic) {
		score1 = logic.getScore();
		board = logic.getBoardState();
		System.out.println(GuiMain.getPlayer());
		if (GuiMain.getPlayer() == FieldType.Player1) {
			GuiMain.setPlayer(FieldType.Player2);
		} else if (GuiMain.getPlayer() == FieldType.Player2) {
			GuiMain.setPlayer(FieldType.Player1);
		}
		possibleMoves = logic.getPossibleMoves(GuiMain.getPlayer());
		if(possibleMoves.isEmpty()){
			if (GuiMain.getPlayer() == FieldType.Player1) {
				GuiMain.setPlayer(FieldType.Player2);
				possibleMoves = logic.getPossibleMoves(GuiMain.getPlayer());
				if(possibleMoves.isEmpty()){
					victory = 1;
				}
			} else if (GuiMain.getPlayer() == FieldType.Player2) {
				GuiMain.setPlayer(FieldType.Player1);
				possibleMoves = logic.getPossibleMoves(GuiMain.getPlayer());
				if(possibleMoves.isEmpty()){
					victory = 1;
					
				}
			}
		}
		repaint();
	}

	public void paint(Graphics g) {
		super.paint(g);

		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				if (board[i][j] == FieldType.Empty) {
					g.setColor(Color.green);

				} else if (board[i][j] == FieldType.Player1) {
					g.setColor(Color.green);

				} else {
					g.setColor(Color.green);

				}

				g.fillRect(50 + j * 60, 50 + i * 60, 60, 60);
				g.setColor(Color.black);
				g.drawRect(50 + j * 60, 50 + i * 60, 60, 60);
				if (board[i][j] == FieldType.Player1) {
					g.setColor(Color.WHITE);
					g.fillOval(57 + j * 60, 57 + i * 60, 45, 45);
					g.drawOval(57 + j * 60, 57 + i * 60, 45, 45);
				} else if (board[i][j] == FieldType.Player2) {
					g.setColor(Color.BLACK);
					g.fillOval(57 + j * 60, 57 + i * 60, 45, 45);
					g.drawOval(57 + j * 60, 57 + i * 60, 45, 45);
				}

			}

		}
		for (int k = 0; k < possibleMoves.size(); k++) {
			FieldPosition position;
			position = possibleMoves.get(k);

			int x2 = position.getX();
			int y2 = position.getY();

			g.setColor(Color.red);
			g.fillOval(72 + x2 * 60, 72 + y2 * 60, 15, 15);
			g.drawOval(72 + x2 * 60, 72 + y2 * 60, 15, 15);
		}
		g.setColor(Color.white);
		g.fillRect(50, 680, 200, 30);
		g.setColor(Color.black);
		g.setFont(new Font("Serif", Font.BOLD, 20));
		g.drawString(GuiMain.getPlayer() + "'s turn", 50, 700);

		g.setColor(Color.white);
		g.fillRect(300, 680, 400, 30);
		g.setColor(Color.black);
		g.setFont(new Font("Serif", Font.BOLD, 20));
		g.drawString(score1.toString(), 300, 700);
		if(victory == 1){
			g.setColor(Color.red);
			g.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
			g.drawString("GAME OVER" , 200, 40 );
		}
	}
}
