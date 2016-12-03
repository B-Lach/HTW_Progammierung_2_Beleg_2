package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import ai.*;
import logic.*;

	public class AITests {

	@Test
	public void test_moveForEmpty() {
		BoardLogic logic = new BoardLogic();
		FieldPosition p = AI.getNextMove(Difficulty.Easy, logic, FieldType.Empty);
		assertEquals("Position for Easy is not null", null, p);
		p = AI.getNextMove(Difficulty.Medium, logic, FieldType.Empty);
		assertEquals("Position for Medium is not null", null, p);
		p = AI.getNextMove(Difficulty.Hard, logic, FieldType.Empty);
		assertEquals("Position for Hard is not null", null, p);
	}
	
	@Test
	public void test_validMoveEasyPlayer1() {
		LogicMock logic = new LogicMock();
		
		FieldPosition p1 = new FieldPosition(1,3);
		FieldPosition p2 = new FieldPosition(2,4);
		FieldPosition p3 = new FieldPosition(3,1);
		FieldPosition p4 = new FieldPosition(4,2);
		
		ArrayList<FieldPosition> expected = new ArrayList<FieldPosition>(); 
		expected.add(p1);
		expected.add(p2);
		expected.add(p3);
		expected.add(p4);
		
		for (int i = 0; i < 100; i++) {
			FieldPosition  result = AI.getNextMove(Difficulty.Easy, logic, FieldType.Player1);
			
			boolean isValid = false;
			
			for(FieldPosition p: expected) {
				isValid = p.equals(result);
				if(isValid) { break; }
			}
			assertEquals("Returned Position for Player1 is not valid", true, isValid);
		}
	}
	
	@Test
	public void test_validMoveEasyPlayer2() {
		LogicMock logic = new LogicMock();
		
		FieldPosition p1 = new FieldPosition(1,2);
		FieldPosition p2 = new FieldPosition(2,1);
		FieldPosition p3 = new FieldPosition(3,4);
		FieldPosition p4 = new FieldPosition(4,3);
		
		ArrayList<FieldPosition> expected = new ArrayList<FieldPosition>(); 
		expected.add(p1);
		expected.add(p2);
		expected.add(p3);
		expected.add(p4);
		
		for (int i = 0; i < 100; i++) {
			FieldPosition  result = AI.getNextMove(Difficulty.Easy, logic, FieldType.Player2);
			
			boolean isValid = false;
			
			for(FieldPosition p: expected) {
				isValid = p.equals(result);
				if(isValid) { break; }
			}
			assertEquals("Returned Position for Player2 is not valid", true, isValid);
		}
	}
	
	@Test
	public void test_validMoveMediumPlayer1() {
		LogicMock logic = new LogicMock();
		FieldType[][] state = logic.getBoardState();
		state[3][1] = FieldType.Player1;
		state[3][3] = FieldType.Player2;
		state[3][4] = FieldType.Player2;
		
		logic.useStateForTest(state);
		
		FieldPosition result = AI.getNextMove(Difficulty.Medium, logic, FieldType.Player1);
		FieldPosition expected = new FieldPosition(5, 3);
		
		assertTrue("Returned position is not equal to the expected position", result.equals(expected));
	}
	
	@Test
	public void test_validMoveMediumPlayer2() {
		LogicMock logic = new LogicMock();
		FieldType[][] state = logic.getBoardState();
		state[2][2] = FieldType.Player2;
		state[2][3] = FieldType.Player1;
		state[3][1] = FieldType.Player2;
		state[3][2] = FieldType.Player1;
		state[3][4] = FieldType.Player1;
		
		logic.useStateForTest(state);
		
		FieldPosition result = AI.getNextMove(Difficulty.Medium, logic, FieldType.Player2);
		FieldPosition expected = new FieldPosition(5, 3);
		
		assertTrue("Returned position is not equal to the expected position", result.equals(expected));
	}
	
	@Test
	public void test_validMoveHardPlayer1() {
		LogicMock logic = new LogicMock();
		FieldType[][] state = logic.getBoardState();
		state[3][1] = FieldType.Player1;
		state[3][3] = FieldType.Player2;
		state[3][4] = FieldType.Player2;
		
		logic.useStateForTest(state);
		
		FieldPosition result = AI.getNextMove(Difficulty.Hard, logic, FieldType.Player1);
		FieldPosition expected = new FieldPosition(5, 3);
		
		assertTrue("Returned position is not equal to the expected position", result.equals(expected));
	}
	
	@Test
	public void test_validHardMediumPlayer2() {
		LogicMock logic = new LogicMock();
		FieldType[][] state = logic.getBoardState();
		state[2][2] = FieldType.Player2;
		state[2][3] = FieldType.Player1;
		state[3][1] = FieldType.Player2;
		state[3][2] = FieldType.Player1;
		state[3][4] = FieldType.Player1;
		
		logic.useStateForTest(state);
		
		FieldPosition result = AI.getNextMove(Difficulty.Hard, logic, FieldType.Player2);
		FieldPosition expected = new FieldPosition(5, 3);
		
		assertTrue("Returned position is not equal to the expected position", result.equals(expected));
	}
	
	@Test
	public void test_noMoveEasy() {
		LogicMock logic = new LogicMock();
		FieldType[][] state = logic.getBoardState();
		state[2][3] = FieldType.Player1;
		state[3][2] = FieldType.Player1;
		
		logic.useStateForTest(state);
		
		FieldPosition p = AI.getNextMove(Difficulty.Easy, logic, FieldType.Player2);
		assertTrue("Position is not null", p == null);
	}
	
	@Test
	public void test_noMoveMedium() {
		LogicMock logic = new LogicMock();
		FieldType[][] state = logic.getBoardState();
		state[2][3] = FieldType.Player1;
		state[3][2] = FieldType.Player1;
		
		logic.useStateForTest(state);
		
		FieldPosition p = AI.getNextMove(Difficulty.Medium, logic, FieldType.Player2);
		assertTrue("Position is not null", p == null);
	}
	
	@Test
	public void test_noMoveHard() {
		LogicMock logic = new LogicMock();
		FieldType[][] state = logic.getBoardState();
		state[2][3] = FieldType.Player1;
		state[3][2] = FieldType.Player1;
		
		logic.useStateForTest(state);
		
		FieldPosition p = AI.getNextMove(Difficulty.Hard, logic, FieldType.Player2);
		assertTrue("Position is not null", p == null);
	}
	
	@Test
	public void test_exceptionTestMedium() {
		LogicMock logic = new LogicMock();
		logic.throwCloneException = true;
		
		FieldPosition p = AI.getNextMove(Difficulty.Medium, logic, FieldType.Player1);
		assertTrue("Result is not null", p == null);
	}
	
	@Test
	public void test_exceptionTestHard() {
		LogicMock logic = new LogicMock();
		logic.throwCloneException = true;
		
		FieldPosition p = AI.getNextMove(Difficulty.Hard, logic, FieldType.Player1);
		assertTrue("Result is not null", p == null);
	}

}
