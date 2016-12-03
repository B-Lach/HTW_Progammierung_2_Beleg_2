package tests;

import logic.*;

/**
 * Mock class to inject a custom state
 * Use only for testing!
 * @author Benny Lach
 *
 */
class LogicMock extends BoardLogic {
	public void useStateForTest(FieldType[][] stateToUse) {
		this.boardState = stateToUse;
	}
}
