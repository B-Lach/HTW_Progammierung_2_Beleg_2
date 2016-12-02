package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;


public class GUI_Main_Test extends JFrame {
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new GUI_Main_Test();
	}
	Reversi_Game_Board B;
	int ende = 0;
	
	public void paint(Graphics g) {
		super.paintComponents(g);
		char ST;
		int erg;
		for (int i = 0; i < B.boardSize; i++)
			for (int j = 0; j < B.boardSize; j++) {
				if (B.gameBoard[i][j] == 0) {
					g.setColor(Color.yellow);
					ST = ' ';
				} else if (B.gameBoard[i][j] == 1) {
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
		erg = B.gewonnen();
		switch (erg) {
		case -1:
			g.drawString("Das Spiel endet remis", 50, 280);
			ende = 1;
			break;
		case 0:
			if (B.playerNum == 1)
				g.drawString("Spieler X ist am Zug", 50, 280);
			else
				g.drawString("Spieler O ist am Zug", 50, 280);
			break;
		case 1:
			g.drawString("Spieler X hat gewonnen", 50, 280);
			ende = 1;
			break;
		case 2:
			g.drawString("Spieler O hat gewonnen", 50, 280);
			ende = 1;
			break;
		}
	}

	/**
	 * Create the frame.
	 */
	public GUI_Main_Test() {

		setTitle("Reversi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 499, 420);
		setLayout(null);
		addMouseListener(new CMeinMausAdapter());
		setVisible(true);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Options");
		mnOptions.add(mntmNewMenuItem_1);

		JMenuItem menuItem = new JMenuItem("Difficulty");
		mnOptions.add(menuItem);

		JMenuItem mntmNewMenuItem = new JMenuItem("Fun Zone");
		mnOptions.add(mntmNewMenuItem);

		JMenu mnNewGame = new JMenu("New Game");
		menuBar.add(mnNewGame);

		JMenuItem mntmPlayerVsAi = new JMenuItem("Player vs AI");
		mnNewGame.add(mntmPlayerVsAi);
		mntmPlayerVsAi.addActionListener(new Action());

		JMenuItem mntmPlayerVsPlayer = new JMenuItem("Player vs Player");
		mnNewGame.add(mntmPlayerVsPlayer);
		getContentPane().setLayout(null);
		B = new Reversi_Game_Board();
		
		
	}
	
	//muss noch umgeschrieben werden damit felder ausserhalb von 3x3 erkannt werden
	class CMeinMausAdapter extends MouseAdapter {
		// x und y sind mauszeiger koordinaten
		public void mousePressed(MouseEvent e) {
			int x, y, colum = 0, row = 0;
			x = e.getX();
			y = e.getY();
			if (x > 50 && x < 110)
				row = 1;
			else if (x > 110 && x < 170)
				row = 2;
			else if (x > 170 && x < 230)
				row = 3;
			if (y > 50 && y < 110)
				colum = 1;
			else if (y > 110 && y < 170)
				colum = 2;
			else if (y > 170 && y < 230)
				colum = 3;

			if (colum > 0 && colum > 0 && (ende == 0))
				B.turn(colum, row);
			repaint();
		}
	}
	
}

class Action implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("Hat geklappt");

	}

}
