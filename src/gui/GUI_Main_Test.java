package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import javax.swing.AbstractAction;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Choice;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTable;
import java.awt.Canvas;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

public class GUI_Main_Test extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_Main_Test frame = new GUI_Main_Test();
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
	public GUI_Main_Test() {
		
		setTitle("Reversi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

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
		
		contentPane = new JPanel();
		contentPane.setForeground(Color.RED);
		contentPane.setBackground(Color.MAGENTA);
		contentPane.setBorder(new EmptyBorder(100, 100, 100, 100));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
	}

}

class Action implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("Hat geklappt");
		
	}

}
