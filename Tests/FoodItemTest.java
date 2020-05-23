package farmSimulatorTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import farmSimulator.FoodItem;

class FoodItemTest {
	
	private static FoodItem testItem;

	@BeforeEach
	final void setUp() {
		testItem = new FoodItem("Test", 150, 1);
	}

	@Test
	final void testToString() {
		assertEquals("Test: Gives +1 health to all animals (0 available)", testItem.toString());
	}

	@Test
	final void testToStringStore() {
		assertEquals("Test: $150   Gives +1 health to all animals", testItem.toStringStore());
	}

	@Test
	final void testFoodItem() {
		assertEquals(1, testItem.getHealthGiven());
		assertEquals(150, testItem.getBuyPrice());
		assertEquals("Test", testItem.getName());
	}
	
	/**
	 * Test that when a positive number or zero is passed into setHealthGiven, getHealthGiven
	 * returns that number
	 * Test that when a negative value is passed into setHealthGiven and error is thrown
	 */
	@Test
	final void testSetHealthGiven() {
		testItem.setHealthGiven(0);
		assertEquals(0, testItem.getHealthGiven());
		testItem.setHealthGiven(3);
		assertEquals(3, testItem.getHealthGiven());
		try {
			testItem.setHealthGiven(-3);
			fail("GrowthBonus cannot be a negative number");
		} catch (IllegalArgumentException e) {
			assertEquals(3, testItem.getHealthGiven());
		}
	}

}
