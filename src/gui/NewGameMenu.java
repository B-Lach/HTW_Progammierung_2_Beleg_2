package gui;

import java.awt.EventQueue;
import java.awt.event.*;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;

public class NewGameMenu extends JFrame {
	public NewGameMenu(){
		GuiMain.btnNewButton.setText("PvP");
		GuiMain.btnNewButton_1.setText("Player vs AI");
		
		GuiMain.contentPane.add( GuiMain.btnNewButton_2 = new JButton(""));
		GuiMain.btnNewButton_2.setBounds(260, 383, 189, 82);
		GuiMain.contentPane.add(GuiMain.btnNewButton_2);
		GuiMain.btnNewButton_2.setText("back");
	}
}
