package farmSimulatorTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import farmSimulatorGUI.*;

class ItemTest {

	private FoodItem testItem1;
	private FoodItem testItem2;
	
	@BeforeEach
	void Setup() {
		testItem1 = new FoodItem("Test1", 1, 1);
		testItem2 = new FoodItem("Test2", 2, 2);
	}
	
	/**
	 * Test that you can add an item to inventory when inventory is empty and when inventory 
	 * already contains an item
	 * Test that you can add multiple occurrences of the same item to inventory
	 */
	@Test
	final void testAddToInventory() {
		testItem1.addToInventory();
		testItem1.addToInventory();
		testItem2.addToInventory();
		assertEquals(2, testItem1.getInventoryCount());
		assertEquals(1, testItem2.getInventoryCount());
	}

	/**
	 * Test that you can remove an item from inventory when only one of that item is contained in
	 * inventory
	 * Test that when multiple occurrences of one item are in inventory, only one is removed by
	 * removeFromInventory
	 * Test that if removeFrom inventory is called with an item not in inventory then inventoryCount
	 * doesn't change
	 */
	@Test
	final void testRemoveFromInventory() {
		testItem1.addToInventory();
		testItem1.removeFromInventory();
		testItem2.addToInventory();
		testItem2.addToInventory();
		testItem2.removeFromInventory();
		assertEquals(0, testItem1.getInventoryCount());
		assertEquals(1, testItem2.getInventoryCount());
		testItem1.removeFromInventory();
		assertEquals(0, testItem1.getInventoryCount());
	}
	
	/**
	 * Test that when setPrice is called with a positive integer or zero, getBuyPrice returns that
	 * integer
	 * Tests that when setPrice is called with a negative number an exception is thrown
	 */
	@Test
	final void testSetPrice() {
		testItem1.setPrice(300);
		assertEquals(300, testItem1.getBuyPrice());
		testItem1.setPrice(0);
		assertEquals(0, testItem1.getBuyPrice());
		try {
			testItem1.setPrice(-300);
			fail("Cannot set price to negative number");
		} catch (IllegalArgumentException e) {
			assertEquals(0, testItem1.getBuyPrice());
		}
	}
	
	/**
	 * Test that when setName is called with a string containing one or more non-whitespace character
	 * getName returns that string
	 * Test that when setName is called with a string which is empty or all whitespace an exception
	 * is thrown
	 */
	@Test
	final void testSetName() {
		testItem1.setName("1");
		assertEquals("1", testItem1.getName());
		testItem1.setName("ABCDE FGHIJ");
		assertEquals("ABCDE FGHIJ", testItem1.getName());
		try {
			testItem1.setName("");
			fail("Cannot set name to empty string");
		} catch (IllegalArgumentException e) {
			assertEquals("ABCDE FGHIJ", testItem1.getName());
		}
		try {
			testItem1.setName("    ");
			fail("Cannot set name to all whitespace");
		} catch (IllegalArgumentException e) {
			assertEquals("ABCDE FGHIJ", testItem1.getName());
		}
	}
}