package farmSimulatorTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import farmSimulatorGUI.*;
import java.util.ArrayList;

class FarmerTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	final void testSetName() {
		Farmer testFarmer = new Farmer();
		testFarmer.setName("Bob");
		assertEquals("Bob", testFarmer.getName());
	}

	@Test
	final void testSetAge() {
		Farmer testFarmer = new Farmer();
		testFarmer.setAge(90);
		assertEquals(90, testFarmer.getAge());
	}

	@Test
	final void testSetItems() {
		Farmer testFarmer = new Farmer();
		ArrayList<Item> testItems = new ArrayList<Item>();
		testItems.add(new FoodItem("Test1", 1, 1));
		testItems.add(new CropItem("Test2", 1, 1));
		testFarmer.setItems(testItems);
		assertEquals(testItems, testFarmer.getItems());
	}

	@Test
	final void testGetFoodItems() {
		Farmer testFarmer = new Farmer();
		ArrayList<Item> testItems = new ArrayList<Item>();
		testItems.add(new FoodItem("Test1", 1, 1));
		testItems.add(new CropItem("Test2", 1, 1));
		testFarmer.setItems(testItems);
		assertEquals(1, testFarmer.getFoodItems().size());
		assertTrue(testFarmer.getFoodItems().get(0) instanceof FoodItem);
	}

	@Test
	final void testGetCropItems() {
		Farmer testFarmer = new Farmer();
		ArrayList<Item> testItems = new ArrayList<Item>();
		testItems.add(new FoodItem("Test1", 1, 1));
		testItems.add(new CropItem("Test2", 1, 1));
		testFarmer.setItems(testItems);
		assertEquals(1, testFarmer.getCropItems().size());
		assertTrue(testFarmer.getCropItems().get(0) instanceof CropItem);
	}

	@Test
	final void testAddItem() {
		Farmer testFarmer = new Farmer();
		ArrayList<Item> testItems = new ArrayList<Item>();
		testFarmer.setItems(testItems);
		testFarmer.addItem(new FoodItem("Test1", 1, 1));
		assertEquals(1, testFarmer.getItems().size());
		testFarmer.addItem(new CropItem("Test2", 1, 1));
		assertEquals(2, testFarmer.getItems().size());
	}

	@Test
	final void testRemoveItem() {
		Farmer testFarmer = new Farmer();
		ArrayList<Item> testItems = new ArrayList<Item>();
		FoodItem test1 = new FoodItem("Test1", 1, 1);
		CropItem test2 = new CropItem("Test2", 1, 1);
		testItems.add(test1);
		testItems.add(test2);
		testFarmer.setItems(testItems);
		testFarmer.removeItem(test1);
		assertEquals(1, testFarmer.getItems().size());
		assertTrue(testFarmer.getItems().get(0) instanceof CropItem);
	}

}
