package farmSimulatorTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import farmSimulatorGUI.*;

class ItemTest {
	
	@Test
	final void testAddToInventory() {
		FoodItem testItem1 = new FoodItem("Test1", 1, 1);
		FoodItem testItem2 = new FoodItem("Test2", 2, 2);
		testItem1.addToInventory();
		testItem1.addToInventory();
		testItem2.addToInventory();
		assertEquals(2, testItem1.getInventoryCount());
		assertEquals(1, testItem2.getInventoryCount());
	}

	@Test
	final void testRemoveFromInventory() {
		FoodItem testItem1 = new FoodItem("Test1", 1, 1);
		FoodItem testItem2 = new FoodItem("Test2", 2, 2);
		testItem1.addToInventory();
		testItem1.removeFromInventory();
		testItem2.addToInventory();
		testItem2.addToInventory();
		testItem2.removeFromInventory();
		assertEquals(0, testItem1.getInventoryCount());
		assertEquals(1, testItem2.getInventoryCount());
	}
}