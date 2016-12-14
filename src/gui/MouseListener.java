package gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import logic.*;

public class MouseListener extends MouseAdapter {

	private GuiMain main;
	
	public MouseListener(GuiMain main) {
		this.main = main;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int x, y, rowNum = -1, columNum = -1;
		x = e.getX();
		y = e.getY();

		if (x >= 50) {

			int pos1 = (int) Math.floor((double)(x / 60));

			switch (pos1) {
			case 0:
				rowNum = 0;
				break;
			case 1:
				rowNum = 1;
				break;
			case 2:
				rowNum = 2;
				break;
			case 3:
				rowNum = 3;
				break;
			case 4:
				rowNum = 4;
				break;
			case 5:
				rowNum = 5;
				break;
			case 6:
				rowNum = 6;
				break;
			case 7:
				rowNum = 7;
				break;
			case 8:
				rowNum = 8;
				break;
			case 9:
				rowNum = 9;
				break;
			default:
				System.out.println("hier ist was falsch gelaufen");
				break;
			}
		}
		if (y >= 50) {
			int pos2 = (int) Math.floor((double) (y / 60));

			switch (pos2) {
			case 0:
				columNum = 0;
				break;
			case 1:
				columNum = 1;
				break;
			case 2:
				columNum = 2;
				break;
			case 3:
				columNum = 3;
				break;
			case 4:
				columNum = 4;
				break;
			case 5:
				columNum = 5;
				break;
			case 6:
				columNum = 6;
				break;
			case 7:
				columNum = 7;
				break;
			case 8:
				columNum = 8;
				break;
			case 9:
				columNum = 9;
				break;
			default:
				System.out.println("hier ist was falsch gelaufen");
				break;
			}
		}
		try {

			if (rowNum >= 0 && columNum >= 0) {
				FieldPosition position = new FieldPosition((rowNum-1), (columNum-1));
				main.turn(position);
			}
		} catch (Exception d) {
			System.out.println("viele bunte fehler");
		}
	}
}
