package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Reversi_Game_Board {
	public int boardSize = 6;
	public int [][] gameBoard;
	public int playerNum = 1;
	
	
	//aufbau des Spielfelds
	Reversi_Game_Board(){
		gameBoard = new int [boardSize][boardSize];
		for(int i = 0; i < boardSize; i++){
			for(int j = 0; j < boardSize; j++){
				gameBoard[i][j] = 0;
			}
		}
	}
	void turn(int row, int colum){
		if (gameBoard[row - 1][colum - 1] == 0) {
			gameBoard[row - 1][colum - 1] = playerNum;
			if (playerNum == 1)
				playerNum = 2;
			else
				playerNum = 1;
		}
	}
	
	// ist erst mal nur drin damits laeuft wird spaeter natuerlich entfernt
	int gewonnen() {
		int w = 0;
		// Test auf 3 Steine
		for (int i = 0; i < boardSize; i++) {
			if ((w == 0) && (gameBoard[i][0] == gameBoard[i][1])
					&& (gameBoard[i][1] == gameBoard[i][2]))
				w = gameBoard[i][0];
		}
		for (int i = 0; i < boardSize; i++) {
			if ((w == 0) && (gameBoard[0][i] == gameBoard[1][i])
					&& (gameBoard[1][i] == gameBoard[2][i]))
				w = gameBoard[0][i];
		}
		if ((w == 0) && (gameBoard[0][0] == gameBoard[1][1])
				&& (gameBoard[1][1] == gameBoard[2][2]))
			w = gameBoard[0][0];
		if ((w == 0) && (gameBoard[2][0] == gameBoard[1][1])
				&& (gameBoard[1][1] == gameBoard[0][2]))
			w = gameBoard[2][0];
		// Test ob Spielfeld voll - unentschieden
		int remis = 1;
		for (int i = 0; i < boardSize; i++)
			for (int j = 0; j < boardSize; j++)
				if (gameBoard[i][j] == 0)
					remis = 0;
		if (remis == 1 && w == 0) {
			w = -1;
			return w;
		}
		return w;
	}
}
