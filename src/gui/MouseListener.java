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
		int x;
		int y;
		int rowNum = -1;
		int columNum = -1;

		x = e.getX();
		y = e.getY();

		if (x >= 50) {

			int pos1 = x;
			System.out.println(pos1);

			if (pos1 >= 50 && pos1 <= 110)
				rowNum = 0;
			if (pos1 >= 111 && pos1 <= 170)
				rowNum = 1;

			if (pos1 >= 172 && pos1 <= 230)
				rowNum = 2;

			if (pos1 >= 232 && pos1 <= 290)
				rowNum = 3;

			if (pos1 >= 292 && pos1 <= 350)
				rowNum = 4;

			if (pos1 >= 352 && pos1 <= 410)
				rowNum = 5;

			if (pos1 >= 412 && pos1 <= 470)
				rowNum = 6;

			if (pos1 >= 472 && pos1 <= 530)
				rowNum = 7;

			if (pos1 >= 532 && pos1 <= 590)
				rowNum = 8;

			if (pos1 >= 592 && pos1 <= 650)
				rowNum = 9;

		}
		if (y >= 50) {
			int pos2 = y;
			System.out.println(pos2);
			if (pos2 >= 50 && pos2 <= 110)
				columNum = 0;
			
			if (pos2 >= 111 && pos2 <= 170)
				columNum = 1;

			if (pos2 >= 172 && pos2 <= 230)
				columNum = 2;

			if (pos2 >= 232 && pos2 <= 290)
				columNum = 3;

			if (pos2 >= 292 && pos2 <= 350)
				columNum = 4;

			if (pos2 >= 352 && pos2 <= 410)
				columNum = 5;

			if (pos2 >= 412 && pos2 <= 470)
				columNum = 6;

			if (pos2 >= 472 && pos2 <= 530)
				columNum = 7;

			if (pos2 >= 532 && pos2 <= 590)
				columNum = 8;

			if (pos2 >= 592 && pos2 <= 650)
				columNum = 9;
		}
		try {

			if (rowNum >= 0 && columNum >= 0) {
				FieldPosition position = new FieldPosition((rowNum), (columNum));
				main.turn(position);
			}
		} catch (Exception d) {
			System.out.println("viele bunte fehler");
		}
	}
}
