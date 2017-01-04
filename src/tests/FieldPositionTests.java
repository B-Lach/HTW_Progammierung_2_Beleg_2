package tests;

import static org.junit.Assert.*;

import org.junit.Test;
import logic.FieldPosition;
/**
 * Test class for the FieldPosition implementation
 * 
 * @author Benny Lach
 *
 */
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
		FieldPosition p4 = new FieldPosition(3, 1);
		
		assertEquals("Equality Check with self is not true", true, p.equals(p));
		assertEquals("Equality Check with null is not false", false, p.equals(null));
		assertEquals("Equality Check with another class is not false", false, p.equals(3));
		assertEquals("Equality Check with same values is not true", true, p.equals(p2));
		assertEquals("Equality Check with different y value is not false", false, p.equals(p3));
		assertEquals("Equality Check wihh different x value is not false", false, p.equals(p4));
	}
	
	@Test
	public void test_toStringMethod() {
		FieldPosition p = new FieldPosition(1, 3);
		
		assertEquals("toString() is not correct", "\n(x: 1 y: 3)", p.toString());
	}
}
