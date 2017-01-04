package logic;

/**
 * Class that represents a specific position on the board identified by it's x and y values
 * 
 * @author Benny Lach
 *
 */
public class FieldPosition {
	private int x;
	private int y;
	
	/**
	 * Constructor to create an instance based on a given x and y value 
	 * @param x The x value of the position
	 * @param y The y value of the position
	 */
	public FieldPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Method to get the x value of the position
	 * @return x value
	 */
	public int getX() {
		return x;
	}

	/**
	 * Method to get the y value of the position
	 * @return y position
	 */
	public int getY() {
		return y;
	}
	
	@Override
	public String toString() {
		return "\n(x: " + x + " y: " + y + ")";
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) { return true; }
		if (o == null) { return false; }
		if (this.getClass() != o.getClass()) { return false; }
		
		FieldPosition p = (FieldPosition) o;
		return this.x == p.x && this.y == p.y;
		
	}
}
