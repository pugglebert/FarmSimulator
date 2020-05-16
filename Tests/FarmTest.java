package farmSimulatorTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import farmSimulatorGUI.*;
import java.util.ArrayList;

class FarmTest {

	@Test
	final void testGetPlanetType() {
		Farm testFarm = new Farm(new Farmer(), "Test");
		assertEquals(testFarm.getPlanetType(), "Test");
	}

	@Test
	final void testSetMoney() {
		Farm testFarm = new Farm(new Farmer(), "Test");
		testFarm.setMoney(1000);
		assertEquals(testFarm.getMoney(), 1000);
		assertEquals(testFarm.getStartCash(), 1000);
		try{
			testFarm.setMoney(-500);
			fail("Money was set to " + Integer.toString(testFarm.getMoney()));
		} catch (IllegalArgumentException e) {
			assertEquals(testFarm.getMoney(), 1000);
			assertEquals(testFarm.getStartCash(), 1000);
		}
	}

	@Test
	final void testGetMoney() {
		Farm testFarm = new Farm(new Farmer(), "Test");
		assertEquals(testFarm.getMoney(), 0);
		testFarm.setMoney(1347);
		assertEquals(testFarm.getMoney(), 1347);
	}

	@Test
	final void testGetStartCash() {
		Farm testFarm = new Farm(new Farmer(), "Test");
		assertEquals(testFarm.getStartCash(), 0);
		testFarm.setMoney(1347);
		assertEquals(testFarm.getStartCash(), 1347);
	}

	@Test
	final void testEarnMoney() {
		Farm testFarm = new Farm(new Farmer(), "Test");
		testFarm.setMoney(100);
		testFarm.earnMoney(30);
		assertEquals(testFarm.getMoney(), 130);
		assertEquals(testFarm.getStartCash(), 100);
		try {
			testFarm.earnMoney(-3);
			fail("Should not accept negative integer as param");
		}
		catch (IllegalArgumentException e) {
			assertEquals(testFarm.getMoney(), 130);
			assertEquals(testFarm.getStartCash(), 100);
		}
	}

	@Test
	final void testSetName() {
		Farm testFarm = new Farm(new Farmer(), "Test");
		testFarm.setName("Cool Farm");
		assertEquals(testFarm.getName(), "Cool Farm");
		try {
			testFarm.setName("A");
			fail("Should not accept names less than 3 chars long");
		}
		catch (IllegalArgumentException e) {
			assertEquals(testFarm.getName(), "Cool Farm");
		}
		try {
			testFarm.setName("1234");
			fail("Should not accept names containing numbers");
		} catch (IllegalArgumentException e) {
			assertEquals(testFarm.getName(), "Cool Farm");
		}
		try {
			testFarm.setName("hello$");
			fail("Should not accept names containing special characters");
		} catch (IllegalArgumentException e) {
			assertEquals(testFarm.getName(), "Cool Farm");
		}
		try {
			testFarm.setName("This is a very long name");
			fail("Should not accept names over 15 chars long");
		} catch (IllegalArgumentException e) {
			assertEquals(testFarm.getName(), "Cool Farm");
		}
	}

	@Test
	final void testGetName() {
		Farm testFarm = new Farm(new Farmer(), "Test");
		testFarm.setName("Cool Farm");
		assertEquals(testFarm.getName(), "Cool Farm");
	}

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
		assertEquals(testFarm.getAnimals(), testAnimals);
	}

	@Test
	final void testGetAnimals() {
		Farm testFarm = new Farm(new Farmer(), "Test");
		ArrayList<Animal> testAnimals = new ArrayList<Animal>();
		testFarm.setAnimals(testAnimals);
		assertEquals(testFarm.getAnimals(), testAnimals);
		testAnimals.add(new Cow());
		testAnimals.add(new Sheep());
		testAnimals.add(new Chicken());
		testFarm.setAnimals(testAnimals);
		assertEquals(testFarm.getAnimals(), testAnimals);
	}

	@Test
	final void testAddAnimal() {
		Farm testFarm = new Farm(new Farmer(), "Test");
		ArrayList<Animal> testAnimals = new ArrayList<Animal>();
		testFarm.setAnimals(testAnimals);
		Cow testCow = new Cow();
		testFarm.addAnimal(testCow);
		testAnimals.add(testCow);
		assertEquals(testFarm.getAnimals(), testAnimals);
	}

	@Test
	final void testRemoveAnimal() {
		Farm testFarm = new Farm(new Farmer(), "Test");
		ArrayList<Animal> testAnimals = new ArrayList<Animal>();
		testFarm.setAnimals(testAnimals);
		testFarm.removeAnimal(new Cow());
		assertEquals(testFarm.getAnimals(), testAnimals);
		Sheep testSheep1 = new Sheep();
		Sheep testSheep2 = new Sheep();
		testFarm.addAnimal(testSheep1);
		testFarm.addAnimal(testSheep2);
		testFarm.removeAnimal(testSheep2);
		testAnimals.add(testSheep1);
		assertEquals(testFarm.getAnimals(), testAnimals);
	}

	@Test
	final void testSetCrops() {
		Farm testFarm = new Farm(new Farmer(), "Test");
		ArrayList<Crop> testCrops = new ArrayList<Crop>();
		testFarm.setCrops(testCrops);
		assertEquals(testFarm.getCrops(), testCrops);
		testCrops.add(new Pumpkin());
		testCrops.add(new Wheat());
		testCrops.add(new Kale());
		testFarm.setCrops(testCrops);
		assertEquals(testFarm.getCrops(), testCrops);
	}

	@Test
	final void testGetCrops() {
		Farm testFarm = new Farm(new Farmer(), "Test");
		ArrayList<Crop> testCrops = new ArrayList<Crop>();
		testFarm.setCrops(testCrops);
		assertEquals(testFarm.getCrops(), testCrops);
		testCrops.add(new Pumpkin());
		testCrops.add(new Wheat());
		testCrops.add(new Kale());
		testFarm.setCrops(testCrops);
		assertEquals(testFarm.getCrops(), testCrops);
	}

	@Test
	final void testAddCrop() {
		Farm testFarm = new Farm(new Farmer(), "Test");
		ArrayList<Crop> testCrops = new ArrayList<Crop>();
		testFarm.setCrops(testCrops);
		Maize testMaize = new Maize();
		testFarm.addCrop(testMaize);
		testCrops.add(testMaize);
		assertEquals(testFarm.getCrops(), testCrops);
	}

	@Test
	final void testRemoveCrop() {
		Farm testFarm = new Farm(new Farmer(), "Test");
		ArrayList<Crop> testCrops = new ArrayList<Crop>();
		testFarm.setCrops(testCrops);
		testFarm.removeCrop(new Wheat());
		assertEquals(testFarm.getCrops(), testCrops);
		Barley testBarley1 = new Barley();
		Barley testBarley2 = new Barley();
		testFarm.addCrop(testBarley1);
		testFarm.addCrop(testBarley2);
		testFarm.removeCrop(testBarley2);
		testCrops.add(testBarley1);
		assertEquals(testFarm.getCrops(), testCrops);
	}

	@Test
	final void testSetCropLimit() {
		Farm testFarm = new Farm(new Farmer(), "Test");
		testFarm.setCropLimit(10);
		assertEquals(testFarm.getCropLimit(), 10);
		testFarm.setCropLimit(20);
		assertEquals(testFarm.getCropLimit(), 16);
		try {
			testFarm.setCropLimit(-5);
			fail("Crop limit cannot be a negative number.");
		} catch (IllegalArgumentException e){
			assertEquals(testFarm.getCropLimit(), 16);
		}
	}

	@Test
	final void testGetCropLimit() {
		Farm testFarm = new Farm(new Farmer(), "Test");
		testFarm.setCropLimit(10);
		assertEquals(testFarm.getCropLimit(), 10);
	}

	@Test
	final void testSetGrowthBonus() {
		Farm testFarm = new Farm(new Farmer(), "Test");
		testFarm.setGrowthBonus(10);
		assertEquals(testFarm.getGrowthBonus(), 10);
		try {
			testFarm.setGrowthBonus(-5);
			fail("Growth bonus cannot be a negative number.");
		} catch (IllegalArgumentException e){
			assertEquals(testFarm.getGrowthBonus(), 10);
		}
	}

	@Test
	final void testGetGrowthBonus() {
		Farm testFarm = new Farm(new Farmer(), "Test");
		testFarm.setGrowthBonus(10);
		assertEquals(testFarm.getGrowthBonus(), 10);
	}

	@Test
	final void testSetHappinessBonus() {
		Farm testFarm = new Farm(new Farmer(), "Test");
		testFarm.setHappinessBonus(10);
		assertEquals(testFarm.getHappinessBonus(), 10);
		try {
			testFarm.setHappinessBonus(-5);
			fail("Happiness bonus cannot be a negative number.");
		} catch (IllegalArgumentException e){
			assertEquals(testFarm.getHappinessBonus(), 10);
		}
	}

	@Test
	final void testGetHappinessBonus() {
		Farm testFarm = new Farm(new Farmer(), "Test");
		testFarm.setHappinessBonus(10);
		assertEquals(testFarm.getHappinessBonus(), 10);
	}

	@Test
	final void testBuy() {
		Farmer testFarmer = new Farmer();
		Farm testFarm = new Farm(testFarmer, "Test");
		testFarm.setHappinessBonus(1);
		testFarm.setMoney(90);
		testFarm.setAnimals(new ArrayList<Animal>());
		Cow testCow = new Cow();
		try {
			testFarm.buy(testCow);
			fail("Farmer should not have been able to buy item they could not afford");
		} catch (IllegalArgumentException e) {
			assertEquals(testFarm.getMoney(), 90);
			assertEquals(testFarm.getAnimals(), new ArrayList<Animal>());
		}
		testFarm.setMoney(1000);
		testFarm.buy(testCow);
		assertEquals(testFarm.getMoney(), 900);
		assertTrue(testFarm.getAnimals().get(0) instanceof Cow);
		assertTrue(testFarm.getAnimals().get(0).getHappiness() == 6);
		testFarm.setCrops(new ArrayList<Crop>());
		testFarm.setCropLimit(1);
		testFarm.setGrowthBonus(1);
		testFarm.buy(new Kale());
		assertEquals(testFarm.getMoney(), 800);
		//System.out.println(testFarm.getCrops().get(0));
		//assertTrue(testFarm.getCrops().get(0) instanceof Kale);
		assertTrue(testFarm.getCrops().get(0).getHarvestAge() == 9);
		try {
			testFarm.buy(new Kale());
			fail("Crop limit surpassed");
		} catch (IllegalArgumentException e) {
			assertEquals(testFarm.getMoney(), 800);
			assertEquals(testFarm.getCrops().size(), 1);
		}
		FoodItem testItem = new FoodItem("Test", 100, 1);
		testFarm.buy(testItem);
		assertEquals(testFarm.getMoney(), 700);
		assertEquals(testFarmer.getItems().size(), 1);
		//assertEquals(testFarmer.getItems().get(0).getInventoryCount(), 1);
		testFarm.buy(testItem);
		assertEquals(testFarm.getMoney(), 600);
		assertEquals(testFarmer.getItems().size(), 1);
		//assertEquals(testFarmer.getItems().get(0).getInventoryCount(), 2);
	}

	@Test
	final void testUseItemFoodItem() {
		Farmer testFarmer = new Farmer();
		Farm testFarm = new Farm(testFarmer, "Test");
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

	@Test
	final void testUseItemCropItemString() {
		Farmer testFarmer = new Farmer();
		Farm testFarm = new Farm(testFarmer, "Test");
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
