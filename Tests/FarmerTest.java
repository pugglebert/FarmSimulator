package farmSimulatorTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import farmSimulator.CropItem;
import farmSimulator.Farmer;
import farmSimulator.FoodItem;
import farmSimulator.Item;
import java.util.ArrayList;

class FarmerTest {
	
	private Farmer testFarmer;

	@BeforeEach
	void setUp() throws Exception {
		testFarmer = new Farmer();
	}

	/**
	 * Test that name can be set to a string 3-15 characters long made up of letters
	 * and spaces
	 * Test that strings which do not meet these criteria will not be accepted
	 */
	@Test
	final void testSetName() {
		testFarmer.setName("Bob");
		assertEquals("Bob", testFarmer.getName());
		try {
			testFarmer.setName("AA");
			fail("Should not accept names less than 3 chars long");
		}
		catch (IllegalArgumentException e) {
			assertEquals("Name must be at least 3 chars long.", e.getMessage());
		}
		try {
			testFarmer.setName("1234");
			fail("Should not accept names containing numbers");
		} catch (IllegalArgumentException e) {
			assertEquals("<html>Name must not contain numbers</br> or special characters.</html>", e.getMessage());
		}
		try {
			testFarmer.setName("hello$");
			fail("Should not accept names containing special characters");
		} catch (IllegalArgumentException e) {
			assertEquals("<html>Name must not contain numbers</br> or special characters.</html>", e.getMessage());
		}
		try {
			testFarmer.setName("SixteenCharacter");
			fail("Should not accept names over 15 chars long");
		} catch (IllegalArgumentException e) {
			assertEquals("Name must be at most 15 chars long.", e.getMessage());
		}
	}

	/**
	 * Test that age can be set to an int from one to 100, but not to an int outside
	 * of that range
	 */
	@Test
	final void testSetAge() {
		testFarmer.setAge("90");
		assertEquals(90, testFarmer.getAge());
		try {
			testFarmer.setAge("0");
			fail("Age cannot be set to 0");
		} catch (IllegalArgumentException e) {
			assertEquals("Age must be from 1 to 100", e.getMessage());
		}
		try {
			testFarmer.setAge("-10");
			fail("Age cannot be set to a negative number");
		} catch (IllegalArgumentException e) {
			assertEquals("Age must be from 1 to 100", e.getMessage());
		}
		try {
			testFarmer.setAge("101");
			fail("Age cannot be set to over 100");
		} catch (IllegalArgumentException e) {
			assertEquals("Age must be from 1 to 100", e.getMessage());
		}
		try {
			testFarmer.setAge("abc");
			fail("Age cannot a non integer");
		} catch (IllegalArgumentException e) {
			assertEquals("Age must be from 1 to 100", e.getMessage());
		}
	}
	
	/**
	 * Test that items can be set to an ArrayList containing food and crop items
	 */
	@Test
	final void testSetItems() {
		ArrayList<Item> testItems = new ArrayList<Item>();
		testItems.add(new FoodItem("Test1", 1, 1));
		testItems.add(new CropItem("Test2", 1, 1));
		testFarmer.setItems(testItems);
		assertEquals(testItems, testFarmer.getItems());
	}

	/**
	 * Test that getFoodItems returns only the items in Farmer.items which are
	 * food items
	 */
	@Test
	final void testGetFoodItems() {
		ArrayList<Item> testItems = new ArrayList<Item>();
		testItems.add(new FoodItem("Test1", 1, 1));
		testItems.add(new CropItem("Test2", 1, 1));
		testFarmer.setItems(testItems);
		assertEquals(1, testFarmer.getFoodItems().size());
		assertTrue(testFarmer.getFoodItems().get(0) instanceof FoodItem);
	}

	/**
	 * Test that getCropItems returns only the items in Farmer.items which are
	 * crop items
	 */
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

	/**
	 * Test that both food and crop items can be added to Farmer.items with the
	 * addItems method
	 */
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

	/**
	 * Test that if removeItem is called with an item in Farmer.items, that item
	 * will be removed, and that no change will occur if it is called with an item
	 * not in Farmer.items
	 */
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
		testFarmer.removeItem(new CropItem("Test3", 1, 1));
		assertEquals(1, testFarmer.getItems().size());
	}

}
