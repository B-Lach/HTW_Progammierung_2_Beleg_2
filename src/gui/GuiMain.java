package gui;

import java.awt.EventQueue;
import java.awt.event.*;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;

public class GuiMain extends JFrame {

	static JPanel contentPane;
	static JButton btnNewButton;
	static JButton btnNewButton_1;
	static JButton btnNewButton_2;

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
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		add( btnNewButton = new JButton("New Game"));
		btnNewButton.setBounds(260, 81, 189, 82);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new newGameAction());
		
		add( btnNewButton_1 = new JButton("Quit Program"));
		btnNewButton_1.setBounds(260, 232, 189, 82);
		contentPane.add(btnNewButton_1);
		
		
		

	}
	class newGameAction implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			NewGameMenu.NewGameMenu();
			
		}
	}
}
