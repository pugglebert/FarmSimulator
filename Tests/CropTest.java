package farmSimulatorTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import farmSimulatorGUI.*;

class CropTest {
	Crop crop;
	
	@BeforeEach
	void init() {
		crop = new Crop("Wheat", 5, 100, 100);	
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
		crop.boostGrowth(crop.getHarvestAge());
		assertTrue(crop.canHarvest());
	}
	
	@Test
	void notHarvestableTest() {
		assertFalse(crop.canHarvest());
	}

	@Test
	void boostGrowthTest() {
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
		crop.reduceHarvestAge(1);
		assertEquals(4, crop.getHarvestAge());
	}
}
