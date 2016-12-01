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
	public void test_equality() {
		FieldPosition p = new FieldPosition(1, 3);
		FieldPosition p2 = new FieldPosition(1, 3);
		FieldPosition p3 = new FieldPosition(1,1);
		
		assertEquals("Equality Check with self is not true", true, p.equals(p));
		assertEquals("Equality Check with null is not false", false, p.equals(null));
		assertEquals("Equality Check with another class is not false", false, p.equals(3));
		assertEquals("Equality Check with same values is not true", true, p.equals(p2));
		assertEquals("Equality Check with different values is not false", false, p.equals(p3));
	}

}
