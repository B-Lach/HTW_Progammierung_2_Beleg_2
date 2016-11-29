package logic;

/**
 * Because Java has no implementation of tuples we have to build a custom class
 * to hold the current score for the player
 * 
 * @author Benny Lach
 *
 */
public class Score {
	private int p1;
	private int p2;

	public Score(int p1, int p2) {
		this.p1 = p1;
		this.p2 = p2;
	}

	public int p1() {
		return p1;
	}

	public int p2() {
		return p2;
	}

	@Override
	public String toString() {
		return "Score for Player 1: " + p1 + " - Score for Player 2: " + p2;
	}
}
