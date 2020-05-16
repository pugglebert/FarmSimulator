package test;

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
			Animal animal = new Animal(animalType, buyPrice, baseReturn);
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
			Animal animal = new Animal(animalType, buyPrice, baseReturn);
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
			Animal animal = new Animal(animalType, buyPrice, baseReturn);
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
	
	@ParameterizedTest
	@ValueSource(ints = {0, 1, 2, 3})
	void lowHappinessReturnTest(int happiness) {
		Animal animal = new Animal(animalType, buyPrice, baseReturn);
		animal.setHappiness(happiness);
		assertEquals(baseReturn * 0.5, animal.dailyReturn());
	}
	
	@ParameterizedTest
	@ValueSource(ints = {4, 5, 6})
	void midHappinessReturnTest(int happiness) {
		Animal animal = new Animal(animalType, buyPrice, baseReturn);
		animal.setHappiness(happiness);
		assertEquals(baseReturn, animal.dailyReturn());
	}
	
	@ParameterizedTest
	@ValueSource(ints = {7, 8, 9, 10})
	void highHappinessReturnTest(int happiness) {
		Animal animal = new Animal(animalType, buyPrice, baseReturn);
		animal.setHappiness(happiness);
		assertEquals(baseReturn * 1.5, animal.dailyReturn());
	}
}
