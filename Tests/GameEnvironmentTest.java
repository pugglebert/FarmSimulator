package farmSimulatorTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import farmSimulatorGUI.*;

class gameEnvironmentTest {
	GameEnvironment game;
	
	@BeforeEach
	void init() {
		game = new GameEnvironment();
		game.initiateFarm("Earth", "Farm");
	}
	
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
	
	@Test
	void initiateFarmerTest() {
		game.initiateFarmer("Bob", 25);
		assertEquals("Bob", game.getFarmer().getName());
		assertEquals(25, game.getFarmer().getAge());
	}
	
	@Test
	void useAndResetActionsTest() {
		game.useAction();
		assertEquals(1, game.getRemainingActions());
		
		game.useAction();
		assertEquals(0, game.getRemainingActions());
		
		game.useAction();
		assertEquals(0, game.getRemainingActions());
		
		game.resetRemainingActions();
		assertEquals(2, game.getRemainingActions());
	}
	
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
	
	@Test
	void playWithAnimalsTest() {
		Animal animal = new Cow();
		Animal oldAnimal = new Cow();
		
		game.getFarm().addAnimal(animal);
		
		game.playWithAnimals();
		assertEquals(oldAnimal.getHappiness() + 2, animal.getHappiness());
	}
	
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
		
		for (int i = 0; i < game.getFarm().getCrops().size(); i++) {
			if (game.getFarm().getCrops().get(i).getCropType() == "Wheat"){
				assertEquals(1, game.getFarm().getCrops().get(i).getAge());
			}
			else {
				assertEquals(0, game.getFarm().getCrops().get(i).getAge());
			}
		}
	}
	
	@Test
	void getNetProfitPositiveTest() {
		game.getFarm().setStartCash(1000);
		game.getFarm().earnMoney(500);
		
		assertEquals("$500", game.getNetProfit());		
	}
	
	@Test
	void getNetProfitNegativeTest() {
		game.getFarm().setStartCash(1000);
		game.getFarm().setMoney(500);
		
		assertEquals("-$500", game.getNetProfit());		
	}
	
	@Test
	void getMoneyScoreTest() {
		game.getFarm().setMoney(1000);
		game.setTotalDays(10);
		assertEquals(100, game.getMoneyScore());
		
		game.setTotalDays(7);
		assertEquals(142, game.getMoneyScore());
		
		game.setTotalDays(5);
		assertEquals(200, game.getMoneyScore());
		
	}
	
	@Test 
	void getAnimalScoreTestMaxHappiness(){
		game.setTotalDays(10);
		ArrayList<Animal> animals = new ArrayList<Animal>();
		animals.add(new Cow());
		animals.add(new Cow());
		animals.add(new Chicken());
		animals.add(new Sheep());
		animals.add(new Sheep());
		
		for (Animal animal : animals) {
			animal.setHappiness(10);
		}
		game.getFarm().setAnimals(animals);
	
		assertEquals(250, game.getAnimalScore());
	}
	
	@Test
	void getAnimalScoreTestVaryingHappiness() {
		game.setTotalDays(10);
		ArrayList<Animal> animals = new ArrayList<Animal>();
		animals.add(new Cow());
		animals.get(0).setHappiness(0);
		animals.add(new Sheep());
		animals.get(1).setHappiness(5);
		animals.add(new Chicken());
		animals.get(2).setHappiness(10);
		
		game.getFarm().setAnimals(animals);
	
		assertEquals(75, game.getAnimalScore());
	}
	
	@Test
	void getCropScoreTest() {
		game.setTotalDays(5);
		ArrayList<Crop> crops = new ArrayList<Crop>();
		crops.add(new Wheat());
		crops.add(new Wheat());
		crops.add(new Wheat());
		
		crops.get(0).boostGrowth(10);
		crops.get(1).boostGrowth(5);
		
		game.getFarm().setCrops(crops);
		
		assertEquals(150, game.getCropScore());
	}
}
