package gui;

import ai.*;
import logic.*;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.border.LineBorder;

/**
 * main class for the graphical user interface
 * 
 * @author Rico Stucke
 *
 */
public class GuiMain extends JFrame {

	static JPanel contentPane;
	public Difficulty difficulty;
	private static FieldType player = FieldType.Player1;
	private BoardLogic logic;
	private PaintBoard board;
	/**
	 * getter method for player
	 * 
	 * @return returns current player
	 */
	public static FieldType getPlayer() {
		return player;
	}

	/**
	 * setter method for player
	 * 
	 * @param play
	 *            new value for player
	 */
	public static void setPlayer(FieldType play) {
		player = play;
	}

	/**
	 * Create the frame.
	 */
	public GuiMain() {
		setTitle("Reversi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 731, 800);
		setResizable(false);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewGame = new JMenu("New Game");
		menuBar.add(mnNewGame);
		
		JMenu onePlayer = new JMenu("1 Player");
		JMenu twoPlayer = new JMenu("2 Player");
		mnNewGame.add(onePlayer);
		mnNewGame.add(twoPlayer);
		
		// Against AI
		JMenuItem mntmxBoard_AI = new JMenuItem("6x6 Board");
		onePlayer.add(mntmxBoard_AI);
		mntmxBoard_AI.addActionListener(new Board6(this, true));

		JMenuItem mntmxBoard_1AI = new JMenuItem("8x8 Board");
		onePlayer.add(mntmxBoard_1AI);
		mntmxBoard_1AI.addActionListener(new Board8(this, true));

		JMenuItem mntmxBoard_2AI = new JMenuItem("10x10 Board");
		onePlayer.add(mntmxBoard_2AI);
		mntmxBoard_2AI.addActionListener(new Board10(this, true));
		
		// Two Player
		JMenuItem mntmxBoard = new JMenuItem("6x6 Board");
		twoPlayer.add(mntmxBoard);
		mntmxBoard.addActionListener(new Board6(this, false));

		JMenuItem mntmxBoard_1 = new JMenuItem("8x8 Board");
		twoPlayer.add(mntmxBoard_1);
		mntmxBoard_1.addActionListener(new Board8(this, false));

		JMenuItem mntmxBoard_2 = new JMenuItem("10x10 Board");
		twoPlayer.add(mntmxBoard_2);
		mntmxBoard_2.addActionListener(new Board10(this, false));
		
		JMenu mnAiDifficulty = new JMenu("AI Difficulty");
		menuBar.add(mnAiDifficulty);

		JMenuItem mntmEasy = new JMenuItem("Easy");

		mnAiDifficulty.add(mntmEasy);
		mntmEasy.addActionListener(new EasyAI(this));

		JMenuItem mntmMedium = new JMenuItem("Medium");
		mnAiDifficulty.add(mntmMedium);
		mntmMedium.addActionListener(new MediumAI(this));

		JMenuItem mntmHard = new JMenuItem("Hard");
		mnAiDifficulty.add(mntmHard);
		mntmHard.addActionListener(new HardAI(this));

		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);

	}

	/**
	 * method to initialize the game board in the JFrame
	 * 
	 * @param size chosen size of the game board
 	 * @param againstAI Boolean to check if it's a game against the AI
	 */
	public void startGame(int size, Boolean againstAI) {
		// Reset the currentPlayer on game start
		player = FieldType.Player1;
		// guarantee that difficulty is null if againstAI is false
		if (!againstAI) {
			difficulty = null;
			}
		else {
			// If againstAI is set, make sure the difficulty is set to easy if it wasn't by player
			if (difficulty == null) { 
				difficulty = Difficulty.Easy;
			} 
		}
		try {
			logic = new BoardLogic(size);
			board = new PaintBoard(logic);
			board.setPlaying(true);
			board.setSize(contentPane.getSize().width, contentPane.getSize().width);
			contentPane.add(board);
			contentPane.addMouseListener(new MouseListener(this));
			board.repaint();
		} catch (Exception e) {
			System.out.println("Hier koennte man ein schoenes Bildchen fuer die Fehlermeldung anzeigen");
		}

	}

	/**
	 * method for making a turn for the current player
	 * 
	 * @param position
	 *            field position the player chose for his turn
	 */
	public void turn(FieldPosition position){
		
		if (logic.makeMove(player, position)) {

			board.repaint(logic);
			turnAI();
		}
	}
	/**
	 * Method for turns by the AI
	 */
	public void turnAI(){
		if(difficulty != null && player == FieldType.Player2){
			turn(AI.getNextMove(difficulty, logic, player));
			
		}
	}

	/**
	 * ActionListener for game board size 6x6
	 * 
	 * @author Rico Stucke
	 *
	 */
	class Board6 implements ActionListener {
		private GuiMain main;
		private Boolean againstAI;
		
		public Board6(GuiMain main, Boolean againstAI) {
			this.main = main;
			this.againstAI = againstAI;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			main.startGame(6, againstAI);
		}
	}

	/**
	 * ActionListener for game board size 8x8
	 * 
	 * @author Rico Stucke
	 *
	 */
	class Board8 extends Board6 {
		public Board8(GuiMain main, Boolean againstAI) {
			super(main, againstAI);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			super.main.startGame(8, super.againstAI);
		}
	}

	/**
	 * ActionListener for game board size 10x10
	 * 
	 * @author Rico Stucke
	 *
	 */
	class Board10 extends Board6 {
		public Board10(GuiMain main, Boolean againstAI) {
			super(main, againstAI);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			super.main.startGame(10, super.againstAI);
		}
	}

	/**
	 * ActionListener for easy AI button
	 * 
	 * @author Rico Stucke
	 *
	 */
	class EasyAI implements ActionListener {
		private GuiMain main;

		public EasyAI(GuiMain main) {
			this.main = main;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if( !board.getPlaying()) {
				main.difficulty = Difficulty.Easy;
			}
		}
	}

	/**
	 * ActionListener for medium AI button
	 * 
	 * @author Rico Stucke
	 *
	 */
	class MediumAI extends EasyAI {
		public MediumAI(GuiMain main) {
			super(main);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if( board == null || !board.getPlaying()) {
				super.main.difficulty = Difficulty.Medium;
			}
		}
	}

	/**
	 * ActionListener for hard AI button
	 * 
	 * @author Rico Stucke
	 *
	 */
	class HardAI extends EasyAI {
		public HardAI(GuiMain main) {
			super(main);
		}

		@Override

		public void actionPerformed(ActionEvent e) {
			if( !board.getPlaying()) {
				super.main.difficulty = Difficulty.Hard;
			}
			

		}
	}
}
