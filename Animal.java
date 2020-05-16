package farmSimulatorGUI;

/**
 * This class implements an Animal being a Cow, Chicken, or Sheep
 * Animals return a certain amount of money each day depending on how happy they are
 * @author David Frost, Ella Johnson
 */

public class Animal implements Buyable {
	/**
	 * The Animal's health, range from 0 to 10 inclusive
	 */
	private int health = 10;
	
	/**
	 * The Animal's happiness, range from 0 to 10 inclusive
	 */
	private int happiness = 5;
	
	/**
	 * The amount of money the Animal returns as a base, before additional modifiers
	 */
	private int baseReturn;
	
	/**
	 * The type of Animal, either Cow, Chicken, or Sheep
	 */
	private String animalType;
	
	/**
	 * How much the Animal costs when bought from the store
	 */
	private int buyPrice;
	
	/**
	 * Constructer for the Animal class initialized through its child classes
	 * @param newAnimalType		Type of Animal
	 * @param newBuyPrice		Cost to buy from the store
	 * @param newBaseReturn		Base return of money per day
	 */
	public Animal(String newAnimalType, int newBuyPrice, int newBaseReturn) {
		animalType = newAnimalType;
		buyPrice = newBuyPrice;
		baseReturn = newBaseReturn;
	}
	
	/**
	 * Advances the day for the Animal
	 * Happiness decreases based on how healthy the Animal is, with unhealthy Animals losing happiness faster
	 * Health decreases by one each day
	 */
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
	
	/**
	 * Returns the cost of buying the Animal
	 * @return The cost to buy the Animal
	 */
	public int getBuyPrice() {
		return buyPrice;
	}
	
	/**
	 * Returns the Animal's happiness
	 */
	public int getHappiness() {
		return happiness;
	}
	
	/**
	 * Sets the happiness of the Animal
	 * @param newHappiness	Animal's happiness is set to this value
	 */
	public void setHappiness(int newHappiness) {
		happiness = newHappiness;
	}
	
	/**
	 * Returns the Animal's health
	 * @return The Animal's health
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * Sets the health of the Animal
	 * @param newHealth		Animal's health is set to this value
	 */
	public void setHealth(int newHealth) {
		health = newHealth;
	}
	
	/**
	 * Animal's happiness is increased by the specified amount
	 * @param amount		The amount of happiness to increase
	 */
	public void increaseHappiness(int amount) {
		happiness += amount;
		if (happiness > 10) {
			happiness = 10;
		}
	}
	
	/**
	 * Animal's health is increased by the specified amount
	 * @param amount		The amount of health to increase
	 */
	public void increaseHealth(int amount) {
		health += amount;
		if (health > 10) {
			health = 10;
		}
	}
	
	/**
	 * Returns the amount of money the Animal gives per day, influenced by its happiness as a multiplier on its base returns
	 * @return The amount of money earned by the Animal
	 */
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
	
	/**
	 * Returns the amount of money gained by the Animal each day
	 * @return Base money received
	 */
	public int getBaseReturn() {
		return baseReturn;
	}
	
	/**
	 * Converts the Animal onject to a String to be used by the shop
	 * Displays the type of the Animal and how much it costs to buy
	 */
	public String toStringStore() {
		return animalType + ": costs $" + Integer.toString(buyPrice);
	}
}
