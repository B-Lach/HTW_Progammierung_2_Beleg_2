package gui;

import logic.*;
import ai.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * Class for drawing the game board
 * 
 * @author Rico Stucke
 *
 */
public class PaintBoard extends JPanel {

	private int boardSize;
	private FieldType[][] board;
	private ArrayList<FieldPosition> possibleMoves;
	private Score score1;
	private Boolean playing = false;
	
	// indicator for victory condition, is 1 when game is over
	private int victory = 0;

	/**
	 * constructor for the graphical display of the game board
	 * 
	 * @param logic
	 *            gets the current board state
	 */
	public PaintBoard(BoardLogic logic) {
		// variable for chosen board size
		boardSize = logic.getBoardSize();
		// array with the current board state
		board = logic.getBoardState();
		// arraylist with possible moves for the current player
		possibleMoves = logic.getPossibleMoves(GuiMain.getPlayer());
		// the game score
		score1 = logic.getScore();
	}
	

	public void setPlaying(Boolean playing) {
		this.playing = playing;
	}
	
	public Boolean getPlaying() {
		return playing;
	}
	
	/**
	 * method for repainting the board
	 * @param logic the logic to use
	 */
	public void repaint(BoardLogic logic) {
		score1 = logic.getScore();
		board = logic.getBoardState();

		// switches player after each turn
		if (GuiMain.getPlayer() == FieldType.Player1) {
			GuiMain.setPlayer(FieldType.Player2);
		} else if (GuiMain.getPlayer() == FieldType.Player2) {
			GuiMain.setPlayer(FieldType.Player1);
		}

		// gets possible moves for current player and checks if any
		// moves are possible and ends the turn if no turn is possible
		possibleMoves = logic.getPossibleMoves(GuiMain.getPlayer());
		if (possibleMoves.isEmpty()) {
			if (GuiMain.getPlayer() == FieldType.Player1) {
				GuiMain.setPlayer(FieldType.Player2);
				possibleMoves = logic.getPossibleMoves(GuiMain.getPlayer());
				if (possibleMoves.isEmpty()) {
					victory = 1;
				}
			} else if (GuiMain.getPlayer() == FieldType.Player2) {
				GuiMain.setPlayer(FieldType.Player1);
				possibleMoves = logic.getPossibleMoves(GuiMain.getPlayer());
				if (possibleMoves.isEmpty()) {
					victory = 1;
				}
			}
		}
		repaint();
	}

	/**
	 * paint method for the GUI
	 */
	public void paint(Graphics g) {
		super.paint(g);

		// initial painting of the board grid
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				// Background
				g.setColor(new Color(98, 185, 127));
				g.fillRect(50 + j * 60, 50 + i * 60, 60, 60);
				g.setColor(Color.black);
				g.drawRect(50 + j * 60, 50 + i * 60, 60, 60);

				if (board[i][j] == FieldType.Player1) {
					// Player 1 Chip
					g.setColor(Color.WHITE);
					g.fillOval(57 + j * 60, 57 + i * 60, 45, 45);
					g.drawOval(57 + j * 60, 57 + i * 60, 45, 45);
				} else if (board[i][j] == FieldType.Player2) {
					// Player 2 Chip
					g.setColor(Color.BLACK);
					g.fillOval(57 + j * 60, 57 + i * 60, 45, 45);
					g.drawOval(57 + j * 60, 57 + i * 60, 45, 45);
				}

			}

		}

		// highlight for board fields where a move is possible
		for (int k = 0; k < possibleMoves.size(); k++) {
			FieldPosition position;
			position = possibleMoves.get(k);

			int x2 = position.getX();
			int y2 = position.getY();

			g.setColor(Color.red);
			g.fillOval(72 + x2 * 60, 72 + y2 * 60, 15, 15);
			g.drawOval(72 + x2 * 60, 72 + y2 * 60, 15, 15);
		}

		// shows current player
		g.setColor(Color.white);
		g.fillRect(50, 680, 200, 30);
		g.setColor(Color.black);
		g.setFont(new Font("Serif", Font.BOLD, 20));
		g.drawString(GuiMain.getPlayer() + "'s turn", 50, 700);

		// shows current game score
		g.setColor(Color.white);
		g.fillRect(300, 680, 400, 30);
		g.setColor(Color.black);
		g.setFont(new Font("Serif", Font.BOLD, 20));
		g.drawString(score1.toString(), 300, 700);

		// check for game over condition and prints GAME OVER
		if (victory == 1) {
			g.setColor(Color.red);
			g.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
			g.drawString("GAME OVER", 200, 40);
			playing = false;
		}
	}
}
