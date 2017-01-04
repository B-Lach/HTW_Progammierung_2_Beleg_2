package tests;

import logic.*;

/**
 * Mock class of BoardLogic to inject custom code 
 * Use only for testing!
 * 
 * @author Benny Lach
 *
 */
class LogicMock extends BoardLogic {
	public boolean throwCloneException = false;
	
	/**
	 * Method to inject a custom state for testing
	 * @param stateToUse the state you want to use 
	 */
	public void useStateForTest(FieldType[][] stateToUse) {
		this.boardState = stateToUse;
	}
	
	/**
	 * Method to throw an error on cloning if desired
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		if (!throwCloneException) {
			return super.clone();
		}
		throw new CloneNotSupportedException("LogicMock: Somebody wanted to see an exception - here it is");
	}
}
