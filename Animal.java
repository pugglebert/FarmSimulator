package farmSimulator;

public class Animal {
	private int health = 10;
	private int happiness = 5;
	private int baseReturn;
	private String animalType;
	private int buyPrice;
	
	Animal(String newAnimalType, int newBuyPrice, int newBaseReturn) {
		animalType = newAnimalType;
		buyPrice = newBuyPrice;
		baseReturn = newBaseReturn;
	}
	
	public void advanceDay() {
		if (health <= 3) {
			happiness -= 3;
		}
		else if (health <= 6) {
			happiness -= 2;
		}
		else {
			happiness -= 1;
		}
		
		if (happiness < 0) {
			happiness = 0;
		}
		
		if (health > 0) {
			health--;
		}
	}
	
	public int getBuyPrice() {
		return buyPrice;
	}
	
	public int getHappiness() {
		return happiness;
	}
		
	public void setBaseHappiness(int bonus) {
		happiness += bonus;
	}
	
	public void increaseHappiness(int amount) {
		happiness += amount;
		if (happiness > 10) {
			happiness = 10;
		}
	}
	
	public void increaseHealth(int amount) {
		health += amount;
		if (health > 10) {
			health = 10;
		}
	}
	
	
	
	public double dailyReturn() {
		if (happiness <= 3) {
			return baseReturn * 0.5;
		}
		else if (happiness <= 6) {
			return baseReturn;
		}
		else {
			return baseReturn * 1.5;
		}
	}
	
	public String toString() {
		return animalType.substring(0, 1).toUpperCase() + animalType.substring(1) + ": " + happiness + "/10 Happiness, " + health + "/10 Health";
	}
	
	public String toStringStore() {
		return animalType.substring(0, 1).toUpperCase() + animalType.substring(1) + ": costs $" + Integer.toString(buyPrice);
	}
}
