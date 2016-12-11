package gui;

import ai.*;
import logic.*;
import java.awt.EventQueue;
import java.awt.event.*;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.BorderLayout;
import javax.swing.border.LineBorder;

public class GuiMain extends JFrame {

	static JPanel contentPane;
	Difficulty difficulty;

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

	/**
	 * Create the frame.
	 */
	public GuiMain() {
		setTitle("Reversi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 731, 521);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewGame = new JMenu("New Game");
		menuBar.add(mnNewGame);
		
		JMenuItem mntmxBoard = new JMenuItem("6x6 Board");
		mnNewGame.add(mntmxBoard);
		mntmxBoard.addActionListener(new Board6());
		
		JMenuItem mntmxBoard_1 = new JMenuItem("8x8 Board");
		mnNewGame.add(mntmxBoard_1);
		mntmxBoard_1.addActionListener(new Board8());
		
		JMenuItem mntmxBoard_2 = new JMenuItem("10x10 Board");
		mnNewGame.add(mntmxBoard_2);
		mntmxBoard_2.addActionListener(new Board10());
		
		JMenu mnAiDifficulty = new JMenu("AI Difficulty");
		menuBar.add(mnAiDifficulty);
		
		JMenuItem mntmEasy = new JMenuItem("Easy");
		mnAiDifficulty.add(mntmEasy);
		mntmEasy.addActionListener(new EasyAI());
		
		JMenuItem mntmMedium = new JMenuItem("Medium");
		mnAiDifficulty.add(mntmMedium);
		mntmMedium.addActionListener(new MediumAI());
		
		JMenuItem mntmHard = new JMenuItem("Hard");
		mnAiDifficulty.add(mntmHard);
		mntmHard.addActionListener(new HardAI());
		
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

	}
	class Board6 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
			try {
				new BoardLogic(6);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	class Board8 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
			try {
				new BoardLogic(8);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	class Board10 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
			try {
				new BoardLogic(10);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	class EasyAI implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
			Difficulty difficulty = Difficulty.Easy;
		}
	}
	class MediumAI implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
			Difficulty difficulty = Difficulty.Medium;
		}
	}
	class HardAI implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
			Difficulty difficulty = Difficulty.Hard;
		}
	}
}
