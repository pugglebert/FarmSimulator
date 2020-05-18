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

}
