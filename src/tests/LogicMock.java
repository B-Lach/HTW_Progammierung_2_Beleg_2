package tests;

import logic.*;

/**
 * Mock class to inject a custom state
 * Use only for testing!
 * @author Benny Lach
 *
 */
class LogicMock extends BoardLogic {
	public boolean throwCloneException = false;
	
	public void useStateForTest(FieldType[][] stateToUse) {
		this.boardState = stateToUse;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		if (!throwCloneException) {
			return super.clone();
		}
		throw new CloneNotSupportedException("LogicMock: Somebody wanted to see an exception - here it is");
	}
}
