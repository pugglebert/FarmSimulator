package farmSimulatorTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import CustomExceptions.InvalidFarmNameException;
import farmSimulatorGUI.*;
import java.util.ArrayList;

class FarmTest {
	
	private ArrayList<Farm> testFarms;
	private Farmer testFarmer;
	
	@BeforeEach
	final void SetUp() {
		testFarms = new ArrayList<Farm>();
		testFarmer = new Farmer();
		testFarms.add(new EarthFarm(testFarmer));
		testFarms.add(new JupiterFarm(testFarmer));
		testFarms.add(new MarsFarm(testFarmer));
		testFarms.add(new VenusFarm(testFarmer));
	}

	@Test
	final void testGetPlanetType() {
		assertEquals(testFarms.get(0).getPlanetType(), "Earth");
		assertEquals(testFarms.get(1).getPlanetType(), "Jupiter");
		assertEquals(testFarms.get(2).getPlanetType(), "Mars");
		assertEquals(testFarms.get(3).getPlanetType(), "Venus");
	}

	/**
	 * Test if setStartCash gives getMoney and startCash correct value for inputs of positive
	 * number, negative number and zero
	 */
	@Test
	final void testSetStartCash() {
		for (Farm testFarm : testFarms) {
			testFarm.setStartCash(1000);
			assertEquals(1000, testFarm.getMoney());
			assertEquals(1000, testFarm.getStartCash());
			testFarm.setStartCash(0);
			assertEquals(0, testFarm.getMoney());
			assertEquals(0, testFarm.getStartCash());
			try {
				testFarm.setStartCash(-1);
				fail("StartCash should not be set to negative number");
			}
			catch (IllegalArgumentException e) {
				assertEquals(0, testFarm.getMoney());
				assertEquals(0, testFarm.getStartCash());
			}
		}
	}
	
	/**
	 * Test if setStartCash gives getMoney and startCash correct value for inputs of positive
	 * number, negative number and zero
	 */
	@Test
	final void testSetMoney() {
		for (Farm testFarm : testFarms) {
			int startCash = testFarm.getStartCash();
			testFarm.setMoney(1000);
			assertEquals(1000, testFarm.getMoney());
			assertEquals(startCash, testFarm.getStartCash());
			testFarm.setMoney(0);
			assertEquals(0, testFarm.getMoney());
			assertEquals(startCash, testFarm.getStartCash());
			try {
				testFarm.setMoney(-1);
				fail("Money should not be set to negative number");
			}
			catch (IllegalArgumentException e) {
				assertEquals(0, testFarm.getMoney());
				assertEquals(startCash, testFarm.getStartCash());
			}
		}
	}
	
	/**
	 * Tests that when positive integer entered as param for earnMoney, money increases by
	 * amount of int, and startCash does not change.
	 * Tests that when zero entered as param, money and startCash do not change.
	 * Tests that when negative number entered as param, exception is thrown and money and 
	 * startCash do not change.
	 */
	@Test
	final void testEarnMoney() {
		for (Farm testFarm : testFarms) {
			testFarm.setStartCash(100);
			testFarm.earnMoney(30);
			assertEquals(testFarm.getMoney(), 130);
			assertEquals(testFarm.getStartCash(), 100);
			testFarm.earnMoney(0);
			assertEquals(testFarm.getMoney(), 130);
			assertEquals(testFarm.getStartCash(), 100);
			try {
				testFarm.earnMoney(-3);
				fail("Should not accept negative integer as parameter");
			}
			catch (IllegalArgumentException e) {
				assertEquals(testFarm.getMoney(), 130);
				assertEquals(testFarm.getStartCash(), 100);
			}
		}
	}

	/**
	 * Tests that Farm only accepts names 3-15 characters long with no 
	 * numbers or special characters
	 */
	@Test
	final void testSetName() {
		for (Farm testFarm : testFarms) {
			testFarm.setName("Cool Farm");
			assertEquals("Cool Farm", testFarm.getName());
			try {
				testFarm.setName("A");
				fail("Should not accept names less than 3 chars long");
			}
			catch (InvalidFarmNameException e) {
				assertEquals("Name must be at least 3 chars long.", e.getMessage());
			}
			try {
				testFarm.setName("1234");
				fail("Should not accept names containing numbers");
			} catch (InvalidFarmNameException e) {
				assertEquals("<html>Name must not contain numbers</br> or special characters.</html>", e.getMessage());
			}
			try {
				testFarm.setName("hello$");
				fail("Should not accept names containing special characters");
			} catch (InvalidFarmNameException e) {
				assertEquals("<html>Name must not contain numbers</br> or special characters.</html>", e.getMessage());
			}
			try {
				testFarm.setName("This is a very long name");
				fail("Should not accept names over 15 chars long");
			} catch (InvalidFarmNameException e) {
				assertEquals("Name must be at most 15 chars long.", e.getMessage());
			}
		}
	}

	/**
	 * Tests that the ArrayList returned by getAnimals matches the ArrayList
	 * passed into setAnimals
	 * Tests that this works even for an empty ArrayList
	 */
	@Test
	final void testSetAnimals() {
		Farm testFarm = new Farm(new Farmer(), "Test");
		ArrayList<Animal> testAnimals = new ArrayList<Animal>();
		testFarm.setAnimals(testAnimals);
		assertEquals(testFarm.getAnimals(), testAnimals);
		testAnimals.add(new Cow());
		testAnimals.add(new Sheep());
		testAnimals.add(new Chicken());
		testFarm.setAnimals(testAnimals);
		assertEquals(testAnimals, testFarm.getAnimals());
		ArrayList<Animal> testAnimals2 = new ArrayList<Animal>();
		testFarm.setAnimals(testAnimals2);
		assertEquals(testAnimals2, testFarm.getAnimals());
	}

	/**
	 * Tests that addAnimal works for all four farm types, and that it
	 * works when the Farm.animals is empty
	 */
	@Test
	final void testAddAnimal() {
		for (Farm testFarm : testFarms) {
			Animal testAnimal = new Cow();
			testFarm.addAnimal(testAnimal);
			int size = testFarm.getAnimals().size();
			assertEquals(testAnimal, testFarm.getAnimals().get(size - 1));
		}
		Farm testFarm = new Farm(new Farmer(), "Test");
		testFarm.setAnimals(new ArrayList<Animal>());
		Animal testAnimal2 = new Sheep();
		testFarm.addAnimal(testAnimal2);
		assertEquals(testAnimal2, testFarm.getAnimals().get(0));
		assertEquals(1, testFarm.getAnimals().size());
	}
	
	/**
	 * Tests that removeAnimal removes and element from animal for all farm types
	 * Tests that when one animal is removed, identical but animals are not removed
	 * Tests that when removeAnimal is called with an Animal not in Farm.animals as
	 * the input it does not change Farm.animals
	 */
	@Test
	final void testRemoveAnimal() {
		for (Farm testFarm : testFarms) {
			int size = testFarm.getAnimals().size();
			Animal testAnimal = testFarm.getAnimals().get(0);
			testFarm.removeAnimal(testAnimal);
			assertEquals(size - 1, testFarm.getAnimals().size());
		}
		Farm testFarm = new Farm(new Farmer(), "Test");
		ArrayList<Animal> initialAnimals = new ArrayList<Animal>();
		initialAnimals.add(new Chicken());
		initialAnimals.add(new Cow());
		testFarm.setAnimals(initialAnimals);
		Animal testAnimal = new Cow();
		testFarm.addAnimal(testAnimal);
		testFarm.removeAnimal(testAnimal);
		assertEquals(initialAnimals, testFarm.getAnimals());
		testFarm.removeAnimal(new Sheep());
		assertEquals(initialAnimals, testFarm.getAnimals());
	}

	/**
	 * Tests that the ArrayList passed into setCrops is the same as that returned
	 * by getCrops
	 * Tests that this is true even when the ArrayList is empty
	 */
	@Test
	final void testSetCrops() {
		Farm testFarm = new Farm(new Farmer(), "Test");
		ArrayList<Crop> testCrops = new ArrayList<Crop>();
		testFarm.setCrops(testCrops);
		assertEquals(testFarm.getCrops(), testCrops);
		testCrops.add(new Pumpkin());
		testCrops.add(new Kale());
		testCrops.add(new Barley());
		testFarm.setCrops(testCrops);
		assertEquals(testCrops, testFarm.getCrops());
		ArrayList<Crop> testCrops2 = new ArrayList<Crop>();
		testFarm.setCrops(testCrops2);
		assertEquals(testCrops2, testFarm.getCrops());
	}

	/**
	 * Tests that addCrop works for all four farm types, and that it
	 * works when the Farm.crops is empty
	 */
	@Test
	final void testAddCrop() {
		for (Farm testFarm : testFarms) {
			Crop testCrop = new Maize();
			testFarm.addCrop(testCrop);
			int size = testFarm.getCrops().size();
			assertEquals(testCrop, testFarm.getCrops().get(size - 1));
		}
		Farm testFarm = new Farm(new Farmer(), "Test");
		testFarm.setCrops(new ArrayList<Crop>());
		Crop testCrop2 = new Wheat();
		testFarm.addCrop(testCrop2);
		assertEquals(testCrop2, testFarm.getCrops().get(0));
		assertEquals(1, testFarm.getCrops().size());
	}
	
	/**
	 * Tests that removeCrop removes an element from crops for all farm types
	 * Tests that when identical elements are present in crops remove only removes
	 * one of them
	 * Tests that when removeCrop is called with a Crop not in Farm.crops as the
	 * input it does not change Farm.crops
	 */
	@Test
	final void testRemoveCrop() {
		for (Farm testFarm : testFarms) {
			int size = testFarm.getCrops().size();
			Crop testCrop = testFarm.getCrops().get(0);
			testFarm.removeCrop(testCrop);
			assertEquals(size - 1, testFarm.getCrops().size());
		}
		Farm testFarm = new Farm(new Farmer(), "Test");
		ArrayList<Crop> initialCrops = new ArrayList<Crop>();
		initialCrops.add(new Barley());
		initialCrops.add(new Potato());
		testFarm.setCrops(initialCrops);
		Crop testCrop = new Potato();
		testFarm.addCrop(testCrop);
		testFarm.removeCrop(testCrop);
		assertEquals(initialCrops, testFarm.getCrops());
		testFarm.removeCrop(new Pumpkin());
		assertEquals(initialCrops, testFarm.getCrops());
	}

	/** 
	 * Tests that if setCropLimit called with positive integer less than 16 or with zero, getCropLimit
	 * returns that value.
	 * Tests that if setCropLimit called with a negative number it throw an exception
	 * Tests that if setCropLimit is called with an integer greater than 16, getCropLimit returns 16
	 */
	@Test
	final void testSetCropLimit() {
		for (Farm testFarm : testFarms) {
			testFarm.setCropLimit(10);
			assertEquals(10, testFarm.getCropLimit());
			testFarm.setCropLimit(0);
			assertEquals(0, testFarm.getCropLimit());
			testFarm.setCropLimit(20);
			assertEquals(16, testFarm.getCropLimit());
			try {
				testFarm.setCropLimit(-5);
				fail("Crop limit cannot be a negative number.");
			} catch (IllegalArgumentException e){
				assertEquals(16, testFarm.getCropLimit());
			}
		}
	}
	
	/**
	 * Tests that increaseCropLimit increases cropLimit by 1 if cropLimit is 0 or a positive int
	 * <=16, and that cropLimit doesn't change if cropLimit is already 16
	 */
	@Test
	final void testIncreaseCropLimit() {
		for (Farm testFarm : testFarms) {
			testFarm.setCropLimit(0);
			testFarm.increaseCropLimit();
			assertEquals(1, testFarm.getCropLimit());
			testFarm.setCropLimit(10);
			testFarm.increaseCropLimit();
			assertEquals(11, testFarm.getCropLimit());
			testFarm.setCropLimit(16);
			testFarm.increaseCropLimit();
			assertEquals(16, testFarm.getCropLimit());
		}
		
	}

	/**
	 * Test that growth bonus can be set to zero or a positive integer
	 * Test that an exception is thrown if setGrowthBonus is called with an negative int
	 */
	@Test
	final void testSetGrowthBonus() {
		for (Farm testFarm : testFarms) {
			testFarm.setGrowthBonus(10);
			assertEquals(10, testFarm.getGrowthBonus());
			testFarm.setGrowthBonus(0);
			assertEquals(0, testFarm.getGrowthBonus());
			try {
				testFarm.setGrowthBonus(-5);
				fail("Growth bonus cannot be a negative number.");
			} catch (IllegalArgumentException e){
				assertEquals(testFarm.getGrowthBonus(), 0);
			}
		}
	}

	/**
	 * Test that happiness bonus can be set to zero or a positive integer
	 * Test that an exception is thrown if setHappinessBonus is called with a negative integer
	 */
	@Test
	final void testSetHappinessBonus() {
		for (Farm testFarm : testFarms) {
			testFarm.setHappinessBonus(0);
			assertEquals(testFarm.getHappinessBonus(), 0);
			testFarm.setHappinessBonus(10);
			assertEquals(testFarm.getHappinessBonus(), 10);
			try {
				testFarm.setHappinessBonus(-5);
				fail("Happiness bonus cannot be a negative number.");
			} catch (IllegalArgumentException e){
				assertEquals(testFarm.getHappinessBonus(), 10);
			}
		}
	}

	/**
	 * Tests that an exception is thrown when buy is called for an Animal with a buyPrice
	 * greater than the Farm's money
	 * Tests that if the Farm's money is greater than or equal to buyPrice the Animal will be
	 * added to the Farm.animals and the Farm's money will decrease by buyPrice
	 * Tests that happinessBonus is added when an Animal is purchased
	 */
	@Test
	final void testBuyAnimal() {
		for (Farm testFarm : testFarms) {
			testFarm.setHappinessBonus(1);
			testFarm.setMoney(90);
			int size = testFarm.getAnimals().size();
			Animal testAnimal = new Cow();
			try {
				testFarm.buy(testAnimal);
				fail("Farmer should not have been able to buy item they could not afford");
			} catch (IllegalArgumentException e) {
				assertEquals(90, testFarm.getMoney());
				assertEquals(size, testFarm.getAnimals().size());
			}
			testFarm.setMoney(200);
			testFarm.buy(testAnimal);
			assertEquals(0, testFarm.getMoney());
			assertEquals(size + 1, testFarm.getAnimals().size());
			assertEquals(6, testFarm.getAnimals().get(size).getHappiness());
			testFarm.setMoney(1000);
			testFarm.buy(testAnimal);
			assertEquals(800, testFarm.getMoney());
			assertEquals(size + 2, testFarm.getAnimals().size());
			assertEquals(6, testFarm.getAnimals().get(size + 1).getHappiness());
		}
		
		Farm farm = new Farm(new Farmer(), "TestFarm");
		farm.setMoney(10000);
		farm.setAnimals(new ArrayList<Animal>());
		for (int i = 1; i <= 10; i++) {
			farm.addAnimal(new Cow());
			farm.addAnimal(new Chicken());
			farm.addAnimal(new Sheep());
		}
		assertEquals(30, farm.getAnimals().size());
		try {
			 farm.buy(new Cow());
		 } catch (IllegalArgumentException e) {
			 assertEquals("You already have the maximum number of Cows!", e.getMessage());
			 assertEquals(30, farm.getAnimals().size());
		 }
		
		try {
			 farm.buy(new Chicken());
		 } catch (IllegalArgumentException e) {
			 assertEquals("You already have the maximum number of Chickens!", e.getMessage());
			 assertEquals(30, farm.getAnimals().size());
		 }
		
		try {
			 farm.buy(new Sheep());
		 } catch (IllegalArgumentException e) {
			 assertEquals("You already have the maximum number of Sheep!", e.getMessage());
			 assertEquals(30, farm.getAnimals().size());
		 }
		
	}
			
	/**
	 * Tests that an exception is thrown when buy is called for an Crop with a buyPrice
	 * greater than the Farm's money
	 * Tests that if the Farm's money is greater than or equal to buyPrice the Crop will be
	 * added to the Farm.crops and the Farm's money will decrease by buyPrice
	 * Tests that growthBonus is added when an Crop is purchased
	 */
	@Test
	 final void testBuyCrop() {
		for (Farm testFarm : testFarms) {
			testFarm.setGrowthBonus(1);
			testFarm.setMoney(50);
			int size = testFarm.getCrops().size();
			try {
				testFarm.buy(new Crop("Wheat", 5, 60, 130));
				fail("Farmer should not have been able to buy item they could not afford");
			} catch (IllegalArgumentException e) {
				assertEquals(50, testFarm.getMoney());
				assertEquals(size, testFarm.getCrops().size());
			}
			testFarm.setMoney(60);
			testFarm.buy(new Crop("Wheat", 5, 60, 130));
			assertEquals(0, testFarm.getMoney());
			assertEquals(size + 1, testFarm.getCrops().size());
			assertEquals(5 - testFarm.getGrowthBonus(), testFarm.getCrops().get(size).getHarvestAge());
			testFarm.setMoney(1000);
			testFarm.buy(new Crop("Wheat", 5, 60, 130));
			assertEquals(940, testFarm.getMoney());
			assertEquals(size + 2, testFarm.getCrops().size());
			assertEquals(5 - testFarm.getGrowthBonus(), testFarm.getCrops().get(size + 1).getHarvestAge());
			testFarm.setCropLimit(testFarm.getCrops().size());
			try {
				testFarm.buy(new Crop("Wheat", 5, 60, 130));
				fail("Crop limit surpassed");
			} catch (IllegalArgumentException e) {
				assertEquals(940, testFarm.getMoney());
				assertEquals(size + 2, testFarm.getCrops().size());
			}
		}
	 }
	 
	/**
	 * Tests that when an item not owned is purchased, it is added to the farmers inventory
	 * with a count of 1 and the Farm's money decreases by the buyPrice of the item
	 * Test that when an item which is already owned is purchased, the item is not added to
	 * the farmers inventory but the inventory count for the relevant item is increased
	 * and the Farms money decreases by the buyPrice of the item
	 * Tests that items with a buyPrice greater than the farm's money cannot be bought
	 */
	@Test
	final void testBuyItem() {
		Farm testFarm = testFarms.get(0);
		testFarm.setMoney(1000);
		FoodItem testItem = new FoodItem("Test", 100, 1);
		testFarm.buy(testItem);
		assertEquals(testFarm.getMoney(), 900);
		assertEquals(1, testFarmer.getItems().size());
		assertEquals(1, testFarmer.getItems().get(0).getInventoryCount());
		testFarm.buy(testItem);
		assertEquals(testFarm.getMoney(), 800);
		assertEquals(testFarmer.getItems().size(), 1);
		assertEquals(2, testFarmer.getItems().get(0).getInventoryCount());
		CropItem testItem2 = new CropItem("Test", 1000, 1);
		try {
			testFarm.buy(testItem2);
			fail("Farmer should not have been able to buy item they could not afford");
		} catch (IllegalArgumentException e) {
			assertEquals(800, testFarm.getMoney());
			assertEquals(1, testFarmer.getItems().size());
		}
	}

	/**
	 * Tests that if an animal has less than 10 health, using a food item will increase its health
	 * by 1
	 * Tests that if it already has 10 health, using a food item won't affect its health
	 */
	@Test
	final void testUseItemFoodItem() {
		for (Farm testFarm : testFarms) {
			testFarm.setMoney(1000);
			ArrayList<Animal> testAnimals = new ArrayList<Animal>();
			Cow testCow = new Cow();
			testCow.advanceDay();
			testAnimals.add(testCow);
			testAnimals.add(new Sheep());
			testFarm.setAnimals(testAnimals);
			FoodItem testItem =  new FoodItem("Test", 100, 1);
			testFarm.buy(testItem);
			testFarm.useItem(testItem);
			assertEquals(testFarm.getAnimals().get(0).getHealth(), 10);
			assertEquals(testFarm.getAnimals().get(1).getHealth(), 10);
			assertEquals(testFarmer.getItems().size(), 0);
		}
	}

	/**
	 * Tests that when a crop item is used, it increases the age of crops of the specified
	 * type and does not affect the age of crops of other types
	 */
	@Test
	final void testUseItemCropItemString() {
		for (Farm testFarm : testFarms) {
			testFarm.setMoney(1000);
			ArrayList<Crop> testCrops = new ArrayList<Crop>();
			testCrops.add(new Kale());
			testCrops.add(new Pumpkin());
			testFarm.setCrops(testCrops);
			CropItem testItem =  new CropItem("Test", 100, 1);
			testFarm.buy(testItem);
			testFarm.useItem(testItem, "Kale");
			assertEquals(testFarm.getCrops().get(0).getAge(), 1);
			assertEquals(testFarm.getCrops().get(1).getAge(), 0);
			assertEquals(testFarmer.getItems().size(), 0);
		}
	}

}