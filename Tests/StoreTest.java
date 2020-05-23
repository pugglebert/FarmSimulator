package farmSimulatorTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import farmSimulator.Animal;
import farmSimulator.Buyable;
import farmSimulator.Cow;
import farmSimulator.Crop;
import farmSimulator.CropItem;
import farmSimulator.FoodItem;
import farmSimulator.Sheep;
import farmSimulator.Store;
import farmSimulator.Wheat;

import java.util.ArrayList;

class StoreTest {

	@Test
	final void testGetStoreArray() {
		Store testStore = new Store();
		ArrayList<Buyable> testBuyables = new ArrayList<Buyable>();
		Buyable testCow = new Cow();
		testBuyables.add(testCow);
		Buyable testItem = new CropItem("Test", 1, 1);
		testBuyables.add(testItem);
		String[] testArray = testStore.getStoreArray(testBuyables);
		assertEquals(2, testArray.length);
		assertEquals(testCow.toStringStore(), testArray[0]);
	}

	@Test
	final void testGetFoodItems() {
		Store testStore = new Store();
		assertEquals(3, testStore.getFoodItems().size());
		FoodItem testItem = new FoodItem("Big treat", 250, 3);
		assertEquals(testItem.toString(), testStore.getFoodItems().get(1).toString());
	}

	@Test
	final void testGetCropItems() {
		Store testStore = new Store();
		assertEquals(3, testStore.getCropItems().size());
		CropItem testItem = new CropItem("Small fertilizer", 200, 2);
		assertEquals(testItem.toString(), testStore.getCropItems().get(0).toString());
	}

	@Test
	final void testGetAnimals() {
		Store testStore = new Store();
		assertEquals(3, testStore.getAnimals().size());
		Animal testAnimal = new Sheep();
		assertEquals(testAnimal.toStringStore(), testStore.getAnimals().get(2).toStringStore());
	}

	@Test
	final void testGetCrops() {
		Store testStore = new Store();
		assertEquals(6, testStore.getCrops().size());
		Crop testCrop = new Wheat();
		assertEquals(testCrop.toStringStore(), testStore.getCrops().get(5).toStringStore());
	}

}
