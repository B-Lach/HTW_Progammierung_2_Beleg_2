package tests;

import static org.junit.Assert.*;

import org.junit.Test;
import logic.FieldPosition;

public class FieldPositionTests {


	@Test
	public void test_FieldPositionInit() {
		FieldPosition p = new FieldPosition(1, 3);
		assertEquals("x value is not 1", 1, p.getX());
		assertEquals("y value is not 3", 3, p.getY());
	}
	
	@Test
	public void test_equalityTrue() {
		FieldPosition p = new FieldPosition(1, 3);
		FieldPosition p2 = new FieldPosition(1, 3);
		
		assertEquals("Positions are not equal", p, p2);
	}
	
	@Test
	public void test_equalityFalse() {
		FieldPosition p = new FieldPosition(1, 3);
		FieldPosition p2 = new FieldPosition(2, 3);
		
		assertNotEquals("Positions are not equal", p, p2);
	}

}
