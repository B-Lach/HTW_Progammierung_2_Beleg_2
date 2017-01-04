package tests;

import static org.junit.Assert.*;

import org.junit.Test;
import logic.*;
/**
 * Test class for BoardLogic initialization code
 * 
 * @author Benny Lach
 *
 */
public class BoardLogicInitTests {

	@Test
	public void test_defaultInitializer() {
		BoardLogic logic = new BoardLogic();
		
		assertEquals("Size is not 6", 6, logic.getBoardSize());
	}
	
	@Test
	public void test_initWithValidCustomSize() {
		try {
			BoardLogic logic = new BoardLogic(8);
			assertEquals("Size is not 8", 8, logic.getBoardSize());
			
		} catch (Exception e) {
			fail("Initialize the board did fail");
		}
	}
	
	@Test
	public void test_initWithOddSize() {
		try {
			BoardLogic logic = new BoardLogic(7);
			fail("Init the board with an odd number should throw an exception");
			
		} catch (Exception e) {
			assertEquals("The message of the exception is not correct", "The size is odd", e.getMessage());
		}
	}
	
	@Test
	public void test_initWithSizeToHigh() {
		try {
			BoardLogic logic = new BoardLogic(11);
			fail("Init the board with a number > 8 should throw an exception");
			
		} catch (Exception e) {
			assertEquals("The message of the exception is not correct", "Size needs to be between 6 and 8", e.getMessage());
		}
	}
	
	@Test
	public void test_initWithSizeToSmall() {
		try {
			BoardLogic logic = new BoardLogic(5);
			fail("Init the board with a number < 6 should throw an exception");
			
		} catch (Exception e) {
			assertEquals("The message of the exception is not correct", "Size needs to be between 6 and 8", e.getMessage());
		}
	}

}
