package farmSimulatorTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import farmSimulatorGUI.*;

class CropItemTest {
	
	private static CropItem testItem;

	@BeforeEach
	void setUp() throws Exception {
		testItem = new CropItem("Test", 200, 1);
	}

	@Test
	final void testToString() {
		assertEquals("Test: crop item which speeds up growth time by 1 days (0 available)", testItem.toString());
	}

	@Test
	final void testToStringStore() {
		assertEquals("Test: crop item which speeds up growth time by 1 days and costs $200", testItem.toStringStore());
	}

	@Test
	final void testCropItem() {
		assertEquals(200, testItem.getBuyPrice());
		assertEquals("Test", testItem.getName());
		assertEquals(1, testItem.getGrowthBonus());
	}
	
	/**
	 * Test that when a positive number or zero is passed into setGrowthBonus, getGrowthBonus
	 * returns that number
	 * Test that when a negative value is passed into setGrowthBonus and error is thrown
	 */
	@Test
	final void testSetGrowthBonus() {
		testItem.setGrowthBonus(0);
		assertEquals(0, testItem.getGrowthBonus());
		testItem.setGrowthBonus(3);
		assertEquals(3, testItem.getGrowthBonus());
		try {
			testItem.setGrowthBonus(-3);
			fail("GrowthBonus cannot be a negative number");
		} catch (IllegalArgumentException e) {
			assertEquals(3, testItem.getGrowthBonus());
		}
	}

}
