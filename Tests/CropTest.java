package farmSimulatorTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import farmSimulator.Crop;

class CropTest {
	Crop crop;
	
	@BeforeEach
	void init() {
		//Creates new crop with harvest age = 5, buy price = 50, sell price = 100
		//Crops are all created with age = 0 initially
		crop = new Crop("TestCrop", 5, 50, 100);	
	}
	
	@Test
	void advanceDayTest() {
		crop.advanceDay();
		assertEquals(1, crop.getAge());
	}
	
	@Test
	void advanceDayFullGrownTest() {
		crop.boostGrowth(5);
		crop.advanceDay();
		assertEquals(5, crop.getHarvestAge());
	}
	
	@Test
	void harvestableTest() {
		crop.boostGrowth(5);
		assertTrue(crop.canHarvest());
	}
	
	@Test
	void notHarvestableTest() {
		crop.boostGrowth(1);
		assertFalse(crop.canHarvest());
	}

	@Test
	void boostGrowthTest() {
		try {
			crop.boostGrowth(-1);
		} catch (IllegalArgumentException e) {
			assertEquals(0, crop.getAge());
		}
		crop.boostGrowth(1);
		assertEquals(1, crop.getAge());
	}
	
	@Test
	void boostGrowthMaxAgeTest() {
		crop.boostGrowth(10);
		assertEquals(5, crop.getAge());
	}
	
	@Test
	void reduceHarvestAgeTest() {
		try {
			crop.reduceHarvestAge(-1);
		} catch (IllegalArgumentException e) {
			assertEquals(5, crop.getHarvestAge());
		}
		
		crop.reduceHarvestAge(1);
		assertEquals(4, crop.getHarvestAge());
	}
	
	@Test
	void toStringTest() {
		assertEquals("TestCrop: costs $50   Growth Time: 5 days, sells for $100", crop.toStringStore());
	}
}
