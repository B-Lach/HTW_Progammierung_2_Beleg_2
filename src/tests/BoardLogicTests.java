package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import logic.*;

public class BoardLogicTests {

	@Test
	public void test_stateAfterDefaultInit() {
		BoardLogic logic = new BoardLogic();
		FieldType[][] expectedState = getDefaultState();
		
		assertEquals("y lengths are not the same", expectedState.length, logic.getBoardState().length);
		
		for (int i = 0; i < logic.getBoardSize(); i++) {
			assertEquals("Current state x length and expected state x length are not the same", logic.getBoardState()[i].length, expectedState[i].length);
			assertEquals("Current state xy value and expected state xy value are not the same", logic.getBoardState()[i][i], expectedState[i][i]);
		}	
	}
	
	@Test
	public void test_stateAfterCustomInit() {
		try {
			BoardLogic logic = new BoardLogic(8);
			
			FieldType[][] expectedState = {
					{FieldType.Empty,FieldType.Empty,FieldType.Empty,FieldType.Empty,FieldType.Empty,FieldType.Empty,FieldType.Empty,FieldType.Empty},
					{FieldType.Empty,FieldType.Empty,FieldType.Empty,FieldType.Empty,FieldType.Empty,FieldType.Empty,FieldType.Empty,FieldType.Empty},
					{FieldType.Empty,FieldType.Empty,FieldType.Empty,FieldType.Empty,FieldType.Empty,FieldType.Empty,FieldType.Empty,FieldType.Empty},
					{FieldType.Empty,FieldType.Empty,FieldType.Empty,FieldType.Player1,FieldType.Player2,FieldType.Empty,FieldType.Empty,FieldType.Empty},
					{FieldType.Empty,FieldType.Empty,FieldType.Empty,FieldType.Player2,FieldType.Player1,FieldType.Empty,FieldType.Empty,FieldType.Empty},
					{FieldType.Empty,FieldType.Empty,FieldType.Empty,FieldType.Empty,FieldType.Empty,FieldType.Empty,FieldType.Empty,FieldType.Empty},
					{FieldType.Empty,FieldType.Empty,FieldType.Empty,FieldType.Empty,FieldType.Empty,FieldType.Empty,FieldType.Empty,FieldType.Empty},
					{FieldType.Empty,FieldType.Empty,FieldType.Empty,FieldType.Empty,FieldType.Empty,FieldType.Empty,FieldType.Empty,FieldType.Empty}
					};
			assertEquals("Length is not the same", expectedState.length, logic.getBoardState().length);
			
			for (int i = 0; i < logic.getBoardSize(); i++) {
				assertEquals("Current state x length and expected state x length are not the same", logic.getBoardState()[i].length, expectedState[i].length);
				for (int j = 0; j < logic.getBoardSize(); j++) {
					assertEquals("Current state xy value and expected state xy value are not the same", logic.getBoardState()[i][j], expectedState[i][j]);
				}
			}
			
		} catch (Exception e) {
			fail("Failed to init the logic with a valid number");
		}	
	}
	
	@Test
	public void test_scoreAfterInit() {
		BoardLogic logic = new BoardLogic();
		
		assertEquals("Current score p1 value is not 2", 2, logic.getScore().p1());
		assertEquals("Current score p2 value is not 2", 2, logic.getScore().p2());
	}
	
	@Test
	public void test_validMove() {
		BoardLogic logic = new BoardLogic();
		FieldPosition p = new FieldPosition(1,3);
		
		assertTrue("Move should be valid but result is false",logic.makeMove(FieldType.Player1, p));
	}
	
	@Test
	public void test_invalidMove() {
		BoardLogic logic = new BoardLogic();
		FieldPosition p = new FieldPosition(0,0);
		
		assertFalse("Move should be invalid but result is true",logic.makeMove(FieldType.Player1, p));
	}
	
	@Test
	public void test_scoreAfterValidMove() {
		BoardLogic logic = new BoardLogic();
		FieldPosition p = new FieldPosition(1,3);
		
		logic.makeMove(FieldType.Player1, p);
		assertEquals("Current score p1 value is not 4", logic.getScore().p1(), 4);
		assertEquals("Current score p2 value is not 1", logic.getScore().p2(), 1);
	}
	
	@Test
	public void test_scoreAfterInvalidMove() {
		BoardLogic logic = new BoardLogic();
		FieldPosition p = new FieldPosition(0,0);
		
		logic.makeMove(FieldType.Player1, p);
		assertEquals("Current score p1 value is not 2", logic.getScore().p1(), 2);
		assertEquals("Current score p2 value is not 2", logic.getScore().p2(), 2);
	}
	
	@Test
	public void test_possibleMoveCountEmpty() {
		BoardLogic logic = new BoardLogic();
		ArrayList<FieldPosition> moves = logic.getPossibleMoves(FieldType.Empty);
		
		assertEquals("Possible moves are not 0", 0, moves.size());
	}
	
	@Test
	public void test_possibleMoveCountPlayer1() {
		BoardLogic logic = new BoardLogic();
		ArrayList<FieldPosition> moves = logic.getPossibleMoves(FieldType.Player1);
		
		assertEquals("Move count is not 4", 4, moves.size());
	}
	
	@Test
	public void test_possibleMovesPlayer1() {
		BoardLogic logic = new BoardLogic();
		
		FieldPosition p1 = new FieldPosition(3,1);
		FieldPosition p2 = new FieldPosition(4,2);
		FieldPosition p3 = new FieldPosition(1,3);
		FieldPosition p4 = new FieldPosition(2,4);
		
		ArrayList<FieldPosition> expectedResult = new ArrayList<FieldPosition>();
		expectedResult.addAll(Arrays.asList(p1, p2, p3, p4));
		
		ArrayList<FieldPosition> moves = logic.getPossibleMoves(FieldType.Player1);
		
		assertEquals("not the same", expectedResult, moves);
	}
	
	@Test
	public void test_possibleMoveCountPlayer2() {
		BoardLogic logic = new BoardLogic();
		ArrayList<FieldPosition> moves = logic.getPossibleMoves(FieldType.Player2);
		
		assertEquals("Move count is not 4", 4, moves.size());
	}
	
	@Test
	public void test_possibleMovesPlayer2() {
		BoardLogic logic = new BoardLogic();
		
		FieldPosition p1 = new FieldPosition(2,1);
		FieldPosition p2 = new FieldPosition(1,2);
		FieldPosition p3 = new FieldPosition(4,3);
		FieldPosition p4 = new FieldPosition(3,4);
		
		ArrayList<FieldPosition> expectedResult = new ArrayList<FieldPosition>();
		expectedResult.addAll(Arrays.asList(p1, p2, p3, p4));
		
		ArrayList<FieldPosition> moves = logic.getPossibleMoves(FieldType.Player2);
		
		assertEquals("not the same", expectedResult, moves);
	}
	
	@Test
	public void test_objectEqualityAfterCloning() {
		BoardLogic logic = new BoardLogic();
		BoardLogic clone;
		
		try {
			clone = (BoardLogic) logic.clone();
			
			assertEquals("Classes are not equal", logic.getClass(), clone.getClass());
			assertNotEquals("Objects shouldn't reference to the same memory address", logic, clone);
		} catch (Exception e) {
			fail("Cloning should not throw an exception");
		}
	}
	
	@Test
	public void test_sizeEqualityAfterCloning() {
		BoardLogic logic = new BoardLogic();
		BoardLogic clone;
		
		try {
			clone = (BoardLogic) logic.clone();
			
			assertEquals("Sizes are not equal", logic.getBoardSize(), clone.getBoardSize());			
		} catch (Exception e) {
			fail("Cloning should not throw an exception");
		}
	}
	
	@Test
	public void test_stateEqualityAfterCloning() {
		BoardLogic logic = new BoardLogic();
		BoardLogic clone;
		
		try {
			clone = (BoardLogic) logic.clone();

			assertNotEquals("States shouldn't reference to the same memory address", logic.getBoardState(), clone.getBoardState());
			
			int size = logic.getBoardSize();
			
			for (int i = 0; i < size; i++) {
				assertNotEquals("Second dimension on index "+ i + " shouldn't reference to the same memory address", logic.getBoardState()[i], clone.getBoardState()[i]);
				for(int j = 0; j < size; j++) {
					assertEquals("State values are not equal", logic.getBoardState()[i][j], clone.getBoardState()[i][j]);
				}
			}
		} catch (Exception e) {
			fail("Cloning should not throw an exception");
		}
	}
	
	@Test
	public void test_horizontalForwardMove() {
		BoardLogic logic = new BoardLogic();
		FieldPosition p = new FieldPosition(1, 2);
		
		logic.makeMove(FieldType.Player2, p);
		
		FieldType[][] expected = getDefaultState();
		expected[2][1] = FieldType.Player2;
		expected[2][2] = FieldType.Player2;
		
		FieldType[][] result = logic.getBoardState();
		
		for (int i = 0; i < logic.getBoardSize(); i++) {
			for (int j = 0; j < logic.getBoardSize(); j++ ) {
				assertEquals("Current state xy value and expected state xy value are not the same", result[i][j], expected[i][j]);
			}
		}
	}
	
	@Test
	public void test_horizontalBackwardMove() {
		BoardLogic logic = new BoardLogic();
		FieldPosition p = new FieldPosition(4, 2);
		
		logic.makeMove(FieldType.Player1, p);
		
		FieldType[][] expected = getDefaultState();
		expected[2][3] = FieldType.Player1;
		expected[2][4] = FieldType.Player1;
		
		FieldType[][] result = logic.getBoardState();
		
		for (int i = 0; i < logic.getBoardSize(); i++) {
			for (int j = 0; j < logic.getBoardSize(); j++ ) {
				assertEquals("Current state xy value and expected state xy value are not the same", result[i][j], expected[i][j]);
			}
		}
	}
	
	@Test
	public void test_verticalBackwardMove() {
		BoardLogic logic = new BoardLogic();
		FieldPosition p = new FieldPosition(2, 4);
		
		logic.makeMove(FieldType.Player1, p);
		
		FieldType[][] expected = getDefaultState();
		expected[3][2] = FieldType.Player1;
		expected[4][2] = FieldType.Player1;
		
		FieldType[][] result = logic.getBoardState();
		
		for (int i = 0; i < logic.getBoardSize(); i++) {
			for (int j = 0; j < logic.getBoardSize(); j++ ) {
				assertEquals("Current state xy value and expected state xy value are not the same", result[i][j], expected[i][j]);
			}
		}
	}
	
	@Test
	public void test_verticalForwardMove() {
		BoardLogic logic = new BoardLogic();
		FieldPosition p = new FieldPosition(3, 1);
		
		logic.makeMove(FieldType.Player1, p);
		
		FieldType[][] expected = getDefaultState();
		expected[1][3] = FieldType.Player1;
		expected[2][3] = FieldType.Player1;
		
		FieldType[][] result = logic.getBoardState();
		
		for (int i = 0; i < logic.getBoardSize(); i++) {
			for (int j = 0; j < logic.getBoardSize(); j++ ) {
				assertEquals("Current state xy value and expected state xy value are not the same", result[i][j], expected[i][j]);
			}
		}
	}

	@Test
	public void test_diagonalBackwardMoveUp() {
		BoardLogic logic = new BoardLogic();
		FieldPosition p = new FieldPosition(3, 4);
		
		FieldType[][] expected = getDefaultState();
		expected[4][3] = FieldType.Player2;
		expected[4][4] = FieldType.Player1;
		
		logic.makeMove(FieldType.Player2, p);
		
		p = new FieldPosition(4, 4);
		logic.makeMove(FieldType.Player1, p);

		FieldType[][] result = logic.getBoardState();

		for (int i = 0; i < logic.getBoardSize(); i++) {
			for (int j = 0; j < logic.getBoardSize(); j++ ) {
				assertEquals("Current state xy value and expected state xy value are not the same", result[i][j], expected[i][j]);
			}
		}
	}
	
	@Test
	public void test_diagonalBackwardMoveDown() {
		FieldType[][] state = getDefaultState();
		state[2][3] = FieldType.Player1;
		
		FieldType[][] expected = getDefaultState();
		expected[1][4] = FieldType.Player2;
		
		LogicMock logic = new LogicMock();
		logic.useStateForTest(state);
		logic.makeMove(FieldType.Player2, new FieldPosition(4,1));
		
		FieldType[][] result = logic.getBoardState();

		for (int i = 0; i < logic.getBoardSize(); i++) {
			for (int j = 0; j < logic.getBoardSize(); j++ ) {
				assertEquals("Current state xy value and expected state xy value are not the same", result[i][j], expected[i][j]);
			}
		}
	}
	
	@Test
	public void test_diagonalForwardMoveDown() {
		BoardLogic logic = new BoardLogic();
		FieldPosition p = new FieldPosition(2, 1);
		
		logic.makeMove(FieldType.Player2, p);
		p = new FieldPosition(1, 1);
		logic.makeMove(FieldType.Player1, p);
		
		FieldType[][] expected = getDefaultState();
		expected[1][1] = FieldType.Player1;
		expected[1][2] = FieldType.Player2;
		
		FieldType[][] result = logic.getBoardState();
		
		for (int i = 0; i < logic.getBoardSize(); i++) {
			for (int j = 0; j < logic.getBoardSize(); j++ ) {
				assertEquals("Current state xy value and expected state xy value are not the same", result[i][j], expected[i][j]);
			}
		}
	}
	
	@Test
	public void test_diagonalForwardMoveUp() {
		FieldType[][] state = getDefaultState();
		state[3][2] = FieldType.Player1;
		
		LogicMock logic = new LogicMock();
		logic.useStateForTest(state);
		
		logic.makeMove(FieldType.Player2, new FieldPosition(1,4));

		FieldType[][] expected = getDefaultState();
		expected[4][1] = FieldType.Player2;
		
		
		FieldType[][] result = logic.getBoardState();
		
		for (int i = 0; i < logic.getBoardSize(); i++) {
			for (int j = 0; j < logic.getBoardSize(); j++ ) {
				assertEquals("Current state xy value and expected state xy value are not the same", result[i][j], expected[i][j]);
			}
		}
	}
	
	@Test
	public void test_multipleMoves() {	
		FieldType[][] state = getDefaultState();
		state[2][2] = FieldType.Player1;
		state[2][3] = FieldType.Player1;
		state[2][4] = FieldType.Player1;
		state[3][2] = FieldType.Player2;
		state[3][3] = FieldType.Player2;
		state[3][4] = FieldType.Player2;
		
		LogicMock logic = new LogicMock();
		logic.useStateForTest(state);
		
		FieldType[][] expected = getDefaultState();
		expected[1][2] = FieldType.Player2;
		expected[2][2] = FieldType.Player2;
		expected[2][3] = FieldType.Player2;
		expected[2][4] = FieldType.Player1;
		expected[3][2] = FieldType.Player2;
		expected[3][3] = FieldType.Player2;
		expected[3][4] = FieldType.Player2;
		
		logic.makeMove(FieldType.Player2, new FieldPosition(2,1));
		
		FieldType[][] result = logic.getBoardState();
		
		for(int i = 0; i < logic.getBoardSize(); i++) {
			for(int j = 0; j < logic.getBoardSize(); j++) {
				assertTrue("Result is not as aspected", result[i][j] == expected[i][j]);
			}
		}
	}
	
	@Test
	public void test_stateImmutabillity() {
		BoardLogic logic = new BoardLogic();
		FieldType[][] state = logic.getBoardState();
		state[0][0] = FieldType.Player1;
		
		assertTrue("State is mutable from outside", logic.getBoardState()[0][0] == FieldType.Empty);
	}
	
	private FieldType[][] getDefaultState() {
		FieldType[][] state = {
				{FieldType.Empty,FieldType.Empty,FieldType.Empty,FieldType.Empty,FieldType.Empty,FieldType.Empty},
				{FieldType.Empty,FieldType.Empty,FieldType.Empty,FieldType.Empty,FieldType.Empty,FieldType.Empty},
				{FieldType.Empty,FieldType.Empty,FieldType.Player1,FieldType.Player2,FieldType.Empty,FieldType.Empty},
				{FieldType.Empty,FieldType.Empty,FieldType.Player2,FieldType.Player1,FieldType.Empty,FieldType.Empty},
				{FieldType.Empty,FieldType.Empty,FieldType.Empty,FieldType.Empty,FieldType.Empty,FieldType.Empty},
				{FieldType.Empty,FieldType.Empty,FieldType.Empty,FieldType.Empty,FieldType.Empty,FieldType.Empty}
				};
		
		return state;
	}
}
