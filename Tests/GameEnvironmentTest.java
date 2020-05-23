package farmSimulatorTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import farmSimulatorGUI.*;

class GameEnvironmentTest {
	GameEnvironment game;
	
	@BeforeEach
	void init() {
		game = new GameEnvironment();
		game.initiateFarm("Earth", "Farm");
	}
	
	@Test
	void getTotalDaysTest() {
		game.setTotalDays(5);
		assertEquals(5, game.getTotalDays());
	}
	
	@Test
	void getCurrentTest() {
		assertEquals(1, game.getCurrentDay());
		game.nextDay();
		assertEquals(2, game.getCurrentDay());
	}
		
	/**
	 * Tests that each type of farm is initiated correctly from the setup screen. Farm name is tested with various inputs
	 * in the SetupTest class
	 */
	@Test
	void initiateFarmTest() {	
		game.initiateFarm("Earth", "Farm");
		assertEquals("Earth", game.getFarm().getPlanetType());
		assertEquals("Farm", game.getFarm().getName());
		
		game.initiateFarm("Mars", "Farm");
		assertEquals("Mars", game.getFarm().getPlanetType());
		assertEquals("Farm", game.getFarm().getName());
		
		game.initiateFarm("Venus", "Farm");
		assertEquals("Venus", game.getFarm().getPlanetType());
		assertEquals("Farm", game.getFarm().getName());
		
		game.initiateFarm("Jupiter", "Farm");
		assertEquals("Jupiter", game.getFarm().getPlanetType());
		assertEquals("Farm", game.getFarm().getName());
	}
	
	/**
	 * Tests the use of actions, throws an error when trying to use an action when there are none left. Resets back
	 * to 2 after each day
	 */
	@Test
	void useAndResetActionsTest() {
		assertEquals(2, game.getRemainingActions());
		
		game.useAction();
		assertEquals(1, game.getRemainingActions());
		
		game.useAction();
		assertEquals(0, game.getRemainingActions());
		
		try{
			game.useAction();
		} catch (IllegalArgumentException e) {
			assertEquals(0, game.getRemainingActions());
		}
		
		game.resetRemainingActions();
		assertEquals(2, game.getRemainingActions());
	}
	
	/**
	 * Tests the nextDay() method. This method calls the advanceDay() method for crops and animals which have tests
	 * with various inputs in the respective classes
	 */
	@Test
	void nextDayTest() {
		Crop crop = new Barley();
		Crop oldCrop = new Barley();
		
		Animal animal = new Cow();
		Animal oldAnimal = new Cow();
		
		game.getFarm().addCrop(crop);
		game.getFarm().addAnimal(animal);
		
		game.nextDay();
		oldCrop.advanceDay();
		oldAnimal.advanceDay();
		
		assertEquals(oldCrop.getAge(), crop.getAge());
		assertEquals(oldAnimal.getHealth(), animal.getHealth());
		assertEquals(oldAnimal.getHappiness(), animal.getHappiness());
	}
	
	/**
	 * Tests that playing with the animals increases the happiness by 2. The playWithAnimals() method calls
	 * the increaseHappiness(int amount) method of the Animal, which is tested with various inputs
	 */
	@Test
	void playWithAnimalsTest() {
		Animal animal = new Cow();
		Animal oldAnimal = new Cow();
		
		game.getFarm().addAnimal(animal);
		
		game.playWithAnimals();
		assertEquals(oldAnimal.getHappiness() + 2, animal.getHappiness());
	}
	
	/**
	 * Tests that all animals have their happiness increased by 1 and the crop limit is increased by 1
	 * tendLand() calls farm.increaseCropLimit() and animal.increaseHappiness(int amount) which have both been tested with
	 * various inputs
	 */
	@Test
	void tendLandTest() {
		Animal animal = new Cow();
		Animal oldAnimal = new Cow();
		
		game.getFarm().addAnimal(animal);
		
		int oldCropLimit = game.getFarm().getCropLimit();
		
		game.tendLand();
		assertEquals(oldAnimal.getHappiness() + 1, animal.getHappiness());
		assertEquals(oldCropLimit + 1, game.getFarm().getCropLimit());		
	}
	
	/**
	 * This test verifies that nothing happens when there are no crops to harvest
	 */
	@Test
	void emptyHarvestCropsTest() {
		ArrayList<Crop> crops = game.getFarm().getCrops();
		int numberOfCrops = crops.size();
		for (Crop crop : crops){
			assertFalse(crop.canHarvest());
		}
		int earnings = game.harvestCrops();
		assertEquals(0, earnings);
		assertEquals(numberOfCrops, crops.size());	
	}
	
	/**
	 * This tests verifies that the correct amount of money is earned from one crop harvested, and that the
	 * crop is removed after harvesting
	 */
	@Test
	void harvestOneCropTest() {
		Crop newCrop = new Wheat();
		newCrop.boostGrowth(100);
		game.getFarm().addCrop(newCrop);
		int numberOfHarvestableCrops = 0;
		ArrayList<Crop> crops = game.getFarm().getCrops();
		int numberOfCrops = crops.size();
		for (Crop crop : crops){
			if (crop.canHarvest()) {
				numberOfHarvestableCrops++;
			}
		}
		int earnings = game.harvestCrops();
		assertEquals(newCrop.getSellPrice(), earnings);
		assertEquals(numberOfCrops - numberOfHarvestableCrops, crops.size());	
	}
	
	/**
	 * This test verifies that the correct amount of money is earned for all crops that are harvested, and that they are
	 * all removed
	 */
	@Test
	void harvestMultipleCropsTest() {
		Crop newCrop1 = new Wheat();
		Crop newCrop2 = new Barley();
		Crop newCrop3 = new Potato();
		newCrop1.boostGrowth(100);
		newCrop2.boostGrowth(100);
		newCrop3.boostGrowth(100);
		
		game.getFarm().addCrop(newCrop1);
		game.getFarm().addCrop(newCrop2);
		game.getFarm().addCrop(newCrop3);
		
		int expectedEarnings = 0;
		int numberOfHarvestableCrops = 0;
		ArrayList<Crop> crops = game.getFarm().getCrops();
		int numberOfCrops = crops.size();
		for (Crop crop : crops){
			if (crop.canHarvest()) {
				expectedEarnings += crop.getSellPrice();
				numberOfHarvestableCrops++;
			}
		}
		int earnings = game.harvestCrops();
		assertEquals(expectedEarnings, earnings);
		assertEquals(numberOfCrops - numberOfHarvestableCrops, crops.size());	
	}

	/**
	 * Tests that watering the crops increases the growth of crops by 1
	 */
	@Test
	void waterCropsTestSingleType() {
		ArrayList<Crop> crops = new ArrayList<Crop>();
		for (int i = 0; i < 5; i++) {
			crops.add(new Wheat());
			assertEquals(0, crops.get(i).getAge());
		}
		game.getFarm().setCrops(crops);
		
		game.waterCrops("Wheat");
		
		for (int i = 0; i < game.getFarm().getCrops().size(); i++) {
			assertEquals(1, game.getFarm().getCrops().get(i).getAge());
		}
	}
	
	/**
	 * Tests that watering the crops increases the growth of all crops of the selected type by 1
	 */
	@Test
	void waterCropsTestMultipleTypes() {
		ArrayList<Crop> crops = new ArrayList<Crop>();
		crops.add(new Wheat());
		crops.add(new Barley());
		crops.add(new Kale());
		crops.add(new Maize());
		crops.add(new Potato());
		
		for (Crop crop : crops) {
			assertEquals(0, crop.getAge());
		}
		
		game.getFarm().setCrops(crops);
				
		game.waterCrops("Wheat");
		
		for (Crop crop : game.getFarm().getCrops()) {
			if (crop.getCropType() == "Wheat"){
				assertEquals(1, crop.getAge());
			}
			else {
				assertEquals(0, crop.getAge());
			}
		}
	}
	
	/**
	 * Tests that Net Profit gives the correct representation for positive values
	 */
	@Test
	void getNetProfitPositiveTest() {
		game.getFarm().setStartCash(1000);
		game.getFarm().earnMoney(500);
		
		assertEquals("$500", game.getNetProfit());		
	}
	
	/**
	 * Tests that Net Profit gives the correct representation for negative values
	 */
	@Test
	void getNetProfitNegativeTest() {
		game.getFarm().setStartCash(1000);
		game.getFarm().setMoney(500);
		
		assertEquals("-$500", game.getNetProfit());		
	}
	
	/**
	 * Tests that money score is correct for different money values
	 */
	@Test
	void getMoneyScoreTest() {
		game.setTotalDays(7);
		game.getFarm().setMoney(1000);
		assertEquals(142, game.getMoneyScore());
		
		game.getFarm().setMoney(5000);
		assertEquals(714, game.getMoneyScore());
		
	}
	
	/**
	 * Tests that all animals return the correct amount 
	 * of score at max happiness
	 */
	@Test 
	void getAnimalScoreTestMaxHappiness(){
		game.setTotalDays(7);
		ArrayList<Animal> animals = new ArrayList<Animal>();
		animals.add(new Animal("TestAnimal1", 1, 1));
		animals.add(new Animal("TestAnimal2", 1, 1));
		animals.add(new Animal("TestAnimal3", 1, 1));
		animals.add(new Animal("TestAnimal4", 1, 1));
		animals.add(new Animal("TestAnimal5", 1, 1));
		
		for (Animal animal : animals) {
			animal.setHappiness(10);
		}
		game.getFarm().setAnimals(animals);
	
		assertEquals(357, game.getAnimalScore());
	}
	
	/**
	 * Tests that the score is calculated correctly for varying happiness
	 */
	@Test
	void getAnimalScoreTestVaryingHappiness() {
		game.setTotalDays(7);
		ArrayList<Animal> animals = new ArrayList<Animal>();
		animals.add(new Animal("TestAnimal1", 1, 1));
		animals.get(0).setHappiness(0);
		animals.add(new Animal("TestAnimal2", 1, 1));
		animals.get(1).setHappiness(5);
		animals.add(new Animal("TestAnimal3", 1, 1));
		animals.get(2).setHappiness(10);
		
		game.getFarm().setAnimals(animals);
	
		assertEquals(107, game.getAnimalScore());
	}
	
	/**
	 * Tests that crops give the correct score based on varying ages
	 */
	@Test
	void getCropScoreTest() {
		game.setTotalDays(7);
		ArrayList<Crop> crops = new ArrayList<Crop>();
		crops.add(new Crop("TestCrop1", 10, 1, 1));
		crops.add(new Crop("TestCrop2", 10, 1, 1));
		crops.add(new Crop("TestCrop3", 10, 1, 1));
		
		crops.get(0).boostGrowth(10);
		crops.get(1).boostGrowth(5);
		
		game.getFarm().setCrops(crops);
		
		assertEquals(107, game.getCropScore());
	}
	
	/**
	 * Tests that calcScore() gets all the correct scores based on money, crops, and animals. THen checks that
	 * dividing this value returns the correct int value, i.e the floored value of combined scores / total days
	 */
	@Test
	void calcScoreTest() {
		game.setTotalDays(7);
		game.getFarm().setMoney(1000);
		assertEquals(142, game.getMoneyScore());
		
		ArrayList<Animal> animals = new ArrayList<Animal>();
		animals.add(new Animal("TestAnimal1", 1, 1));
		animals.get(0).setHappiness(0);
		animals.add(new Animal("TestAnimal2", 1, 1));
		animals.get(1).setHappiness(5);
		animals.add(new Animal("TestAnimal3", 1, 1));
		animals.get(2).setHappiness(10);
		
		game.getFarm().setAnimals(animals);
		assertEquals(107, game.getAnimalScore());
		
		ArrayList<Crop> crops = new ArrayList<Crop>();
		crops.add(new Crop("TestCrop1", 10, 1, 1));
		crops.add(new Crop("TestCrop2", 10, 1, 1));
		crops.add(new Crop("TestCrop3", 10, 1, 1));
		
		crops.get(0).boostGrowth(10);
		crops.get(1).boostGrowth(5);
		
		game.getFarm().setCrops(crops);
		assertEquals(107, game.getCropScore());
		
		assertEquals(356, game.calcScore());
	}
	
}
