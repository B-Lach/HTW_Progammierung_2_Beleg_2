package tests;

import static org.junit.Assert.*;

import org.junit.Test;
import logic.Score;

/**
 * Test class for the Score implementation
 * 
 * @author Benny Lach
 *
 */
public class ScoreTests {

	@Test
	public void test_initScore() {
		Score score = new Score(2, 1);
		
		assertEquals("Score for Player 1 is not 2", 2, score.p1());
		assertEquals("Score for Player 2 is not 1", 1, score.p2());
	}
	
	@Test
	public void test_toString() {
		Score score = new Score(2, 1);
		
		assertEquals("toString is not correct", "Score for Player 1: 2 - Score for Player 2: 1", score.toString());
	}

}
