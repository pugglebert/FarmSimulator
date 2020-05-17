package farmSimulatorGUI;

/**
 * This class implements a FoodItem, which is inherits the Item class
 * Food Items are used on Animals to increase their health
 * 
 * @author David Frost. Ella Johnson
 */
public class FoodItem extends Item implements Buyable {
	/**
	 * The amount of health given to each animal
	 */
	int healthGiven;
	
	/**
	 * Initializes Food Item based on the specified values
	 * @param newName			Name of the Food Item
	 * @param newPrice			Cost of buying the item from the store
	 * @param newHealthGiven	Amount of health the item gives to the animals
	 */
	public FoodItem(String newName, int newPrice, int newHealthGiven) {
		super.setName(newName);
		super.setPrice(newPrice);
		healthGiven = newHealthGiven;
	}


	/**
	 * String representation of the item used in the Inventory
	 * Gives information on the how much health is gained by the animals, and shows how much of that item is remaining
	 */
	public String toString() {
		String healthStr = Integer.toString(healthGiven);
		String count = Integer.toString(super.getInventoryCount());
		String repr = super.getName() + ": food item which gives " + healthStr + " health (" + count + " available";
		return repr;
	}
	
	/**
	 * String representation of the item used in the Store
	 * Shows name, bonus, and cost of the item
	 */
	public String toStringStore() {
		String healthStr = Integer.toString(healthGiven);
		String priceStr = Integer.toString(super.getBuyPrice());
		String repr = super.getName() + ": food item which gives " + healthStr + " health and costs $" + priceStr;
		return repr;
	}
	
	/**
	 * Returns the amount of health gained by the item
	 * @return		Amount of health the Animal gains from the item
	 */
	public int getHealthGiven() {
		return healthGiven;
	}
	
	/**
	 * Sets the amount of health gained by the animals when the item is used
	 * @param newHealthGiven	Amount of health the animals gain
	 */
	public void setHealthGiven(int newHealthGiven) {
		healthGiven = newHealthGiven;
	}
}