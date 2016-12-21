package gui;

import ai.*;
import logic.*;
import java.awt.EventQueue;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.border.LineBorder;

public class GuiMain extends JFrame {

	static JPanel contentPane;
	public Difficulty difficulty;
	private static FieldType player = FieldType.Player1;
	private BoardLogic logic;
	private PaintBoard board;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiMain frame = new GuiMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static FieldType getPlayer(){
		return player;
	}
	
	public static void setPlayer(FieldType play){
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

		JMenuItem mntmxBoard = new JMenuItem("6x6 Board");
		mnNewGame.add(mntmxBoard);
		mntmxBoard.addActionListener(new Board6(this));

		JMenuItem mntmxBoard_1 = new JMenuItem("8x8 Board");
		mnNewGame.add(mntmxBoard_1);
		mntmxBoard_1.addActionListener(new Board8(this));

		JMenuItem mntmxBoard_2 = new JMenuItem("10x10 Board");
		mnNewGame.add(mntmxBoard_2);
		mntmxBoard_2.addActionListener(new Board10(this));

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

	public void startGame(int size) {

		try {
			logic = new BoardLogic(size);
			board = new PaintBoard(logic);
			board.setSize(contentPane.getSize().width, contentPane.getSize().width);
			contentPane.add(board);
			contentPane.addMouseListener(new MouseListener(this));
			board.repaint();
		} catch (Exception e) {
			System.out.println("Hier koennte man ein schoenes Bildchen fuer die Fehlermeldung anzeigen");
		}

	}

	public void turn(FieldPosition position) {
		System.out.println("Potition: " + position);
		if (logic.makeMove(player, position)) {
			
			board.repaint(logic);
		}
	}

	class Board6 implements ActionListener {
		GuiMain main;

		public Board6(GuiMain main) {
			this.main = main;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			main.startGame(6);
		}
	}

	class Board8 extends Board6 {
		public Board8(GuiMain main) {
			super(main);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			super.main.startGame(8);
		}
	}

	class Board10 extends Board6 {
		public Board10(GuiMain main) {
			super(main);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			super.main.startGame(10);
		}
	}

	class EasyAI implements ActionListener {
		private GuiMain main;

		public EasyAI(GuiMain main) {
			this.main = main;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			main.difficulty = Difficulty.Easy;
		}
	}

	class MediumAI extends EasyAI {
		public MediumAI(GuiMain main) {
			super(main);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			super.main.difficulty = Difficulty.Medium;
		}
	}

	class HardAI extends EasyAI {
		public HardAI(GuiMain main) {
			super(main);
		}

		@Override

		public void actionPerformed(ActionEvent e) {
			super.main.difficulty = Difficulty.Hard;

		}
	}
}
