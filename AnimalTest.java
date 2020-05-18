package farmSimulatorTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import farmSimulatorGUI.*;

class AnimalTest {
	String animalType = "Cow";
	int buyPrice = 50;
	int baseReturn = 100;
	
	@ParameterizedTest
	@ValueSource(ints = {0, 1, 2, 3})
	void lowHealthAdvanceDayTest(int health) {
		for (int happiness = 0; happiness <= 10; happiness ++) {
			Animal animal = new Animal(animalType, baseReturn, buyPrice);
			animal.setHappiness(happiness);
			animal.setHealth(health);
			int oldHealth = animal.getHealth();
			int oldHappiness = animal.getHappiness();
			animal.advanceDay();
			
			if (oldHappiness <= 3) {
				assertEquals(0, animal.getHappiness());
			}
			else {
				assertEquals(oldHappiness -= 3, animal.getHappiness());
			}
			
			if (oldHealth == 0) {
				assertEquals(0, animal.getHealth());
			}
			else {
				assertEquals(oldHealth -= 1, animal.getHealth());
			}
		}
	}
	
	@ParameterizedTest
	@ValueSource(ints = {4, 5, 6})
	void midHealthAdvanceDayTest(int health) {
		for (int happiness = 0; happiness <= 10; happiness ++) {
			Animal animal = new Animal(animalType, baseReturn, buyPrice);
			animal.setHappiness(happiness);
			animal.setHealth(health);
			int oldHealth = animal.getHealth();
			int oldHappiness = animal.getHappiness();
			animal.advanceDay();
			
			if (oldHappiness <= 2) {
				assertEquals(0, animal.getHappiness());
			}
			else {
				assertEquals(oldHappiness -= 2, animal.getHappiness());
			}
			assertEquals(oldHealth -= 1, animal.getHealth());
			
		}
		
	}
	
	@ParameterizedTest
	@ValueSource(ints = {7, 8, 9, 10})
	void highHealthAdvanceDayTest(int health) {
		for (int happiness = 0; happiness <= 10; happiness ++) {
			Animal animal = new Animal(animalType, baseReturn, buyPrice);
			animal.setHappiness(happiness);
			animal.setHealth(health);
			int oldHealth = animal.getHealth();
			int oldHappiness = animal.getHappiness();
			animal.advanceDay();
			
			if (oldHappiness <= 1) {
				assertEquals(0, animal.getHappiness());
			}
			else {
				assertEquals(oldHappiness -= 1, animal.getHappiness());
			}
			assertEquals(oldHealth -= 1, animal.getHealth());
		}
	}
	
	@Test
	void getBuyPriceTest() {
		Animal animal = new Animal("TestAnimal", 1, 100);
		assertEquals(100, animal.getBuyPrice());
	}
	
	@Test
	void getHappinessTest() {
		Animal animal = new Animal("TestAnimal", 1, 1);
		animal.setHappiness(5);
		assertEquals(5, animal.getHappiness());
	}
	
	@Test
	void setHappinessTest() {
		Animal animal = new Animal("TestAnimal", 1, 1);
		
		animal.setHappiness(5);
		assertEquals(5, animal.getHappiness());
		
		animal.setHappiness(100);
		assertEquals(10, animal.getHappiness());
		
		try {
			animal.setHappiness(-1);
		}
		catch (IllegalArgumentException e) {
			assertEquals(10, animal.getHappiness());
		}
	}
	
	@Test
	void getHealthTest() {
		Animal animal = new Animal("TestAnimal", 1, 1);
		animal.setHealth(5);
		assertEquals(5, animal.getHealth());
	}
	
	@Test
	void setHealthTest() {
		Animal animal = new Animal("TestAnimal", 1, 1);
		
		animal.setHealth(5);
		assertEquals(5, animal.getHealth());
		
		animal.setHealth(100);
		assertEquals(10, animal.getHealth());
		
		try {
			animal.setHealth(-1);
		}
		catch (IllegalArgumentException e) {
			assertEquals(10, animal.getHealth());
		}
	}
	
	@Test
	void increaseHappinessTest() {
		Animal animal = new Animal(animalType, buyPrice, baseReturn);
		animal.setHappiness(5);
		
		try {
			animal.increaseHappiness(-1);
		}
		catch (IllegalArgumentException e) {
			assertEquals(5, animal.getHappiness());
		}
		
		animal.increaseHappiness(3);
		assertEquals(8, animal.getHappiness());
	}
	
	@Test
	void increaseHealthTest() {
		Animal animal = new Animal(animalType, buyPrice, baseReturn);
		animal.setHealth(5);
		
		try {
			animal.increaseHealth(-1);
		}
		catch (IllegalArgumentException e) {
			assertEquals(5, animal.getHealth());
		}
		
		animal.increaseHealth(3);
		assertEquals(8, animal.getHealth());
	}
	
	@Test 
	void maxHappinessCapTest() {
		Animal animal = new Animal(animalType, buyPrice, baseReturn);
		animal.setHappiness(10);
		animal.increaseHappiness(5);
		
		assertEquals(10, animal.getHappiness());
	}
	
	@Test 
	void maxHealthCapTest() {
		Animal animal = new Animal(animalType, buyPrice, baseReturn);
		animal.setHealth(10);
		animal.increaseHealth(5);
		
		assertEquals(10, animal.getHealth());
	}
	
	@Test
	void getBaseReturnTest() {
		Animal animal = new Animal("TestAnimal", 100, 1);
		assertEquals(100, animal.getBaseReturn());
	}
	
	@Test
	void toStringStoreTest() {
		Animal animal = new Animal("TestAnimal", 50, 100);
		assertEquals("TestAnimal: costs $100", animal.toStringStore());
	}
	
	
	@ParameterizedTest
	@ValueSource(ints = {0, 1, 2, 3})
	void lowHappinessReturnTest(int happiness) {
		Animal animal = new Animal(animalType, baseReturn, buyPrice);
		animal.setHappiness(happiness);
		assertEquals(baseReturn * 0.5, animal.dailyReturn());
	}
	
	@ParameterizedTest
	@ValueSource(ints = {4, 5, 6})
	void midHappinessReturnTest(int happiness) {
		Animal animal = new Animal(animalType, baseReturn, buyPrice);
		animal.setHappiness(happiness);
		assertEquals(baseReturn, animal.dailyReturn());
	}
	
	@ParameterizedTest
	@ValueSource(ints = {7, 8, 9, 10})
	void highHappinessReturnTest(int happiness) {
		Animal animal = new Animal(animalType, baseReturn, buyPrice);
		animal.setHappiness(happiness);
		assertEquals(baseReturn * 1.5, animal.dailyReturn());
	}
}
