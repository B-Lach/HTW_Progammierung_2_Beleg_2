package logic;

public class FieldPosition {
	private int x;
	private int y;
	
	public FieldPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	@Override
	public String toString() {
		return "\n(x: " + x + " y: " + y + ")";
	}
}
