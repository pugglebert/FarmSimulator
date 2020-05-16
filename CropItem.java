package farmSimulatorGUI;

/**
 * This class implements a CropItem, which is inherits the Item class
 * Crop Items are used on Crops to boost their growth
 * 
 * @author David Frost. Ella Johnson
 */
public class CropItem extends Item implements Buyable {
	/**
	 * The amount of days extra growth the item gives
	 */
	private int growthBonus;
	
	/**
	 * Initializes Crop Item based on the specified values
	 * @param newName			Name of the Crop Item
	 * @param newPrice			Cost of buying the item from the store
	 * @param newGrowthBonus	Amount of days growth the item gives to crops
	 */
	CropItem(String newName, int newPrice, int newGrowthBonus) {
		super.setName(newName);
		super.setPrice(newPrice);
	    growthBonus = newGrowthBonus;
	}
	
	/**
	 * String representation of the item used in the Inventory
	 * Gives information on the how many days the Crops are boosted by, and shows how much of that item is remaining
	 */
	public String toString() {
		String growthStr = Integer.toString(growthBonus);
		String count = Integer.toString(super.getInventoryCount());
		String repr = super.getName() + ": crop item which speeds up growth time by " + growthStr + " days (" + count + " available)";
		return repr;
	}
	
	/**
	 * String representation of the item used in the Store
	 * Shows name, bonus, and cost of the item
	 */
	public String toStringStore() {
		String growthStr = Integer.toString(growthBonus);
		String priceStr = Integer.toString(super.getBuyPrice());
		String repr = super.getName() + ": crop item which speeds up growth time by " + growthStr + " days and costs $" + priceStr;
		return repr;
	}
	
	/**
	 * Returns the amount of days growth the item gives
	 * @return 		Amount of days the growth of crops is boosted by
	 */
	public int getGrowthBonus() {
		return growthBonus;
	}
	
	/**
	 * Sets the amount of days growth the item gives
	 * @param newGrowthBonus	Amount of days crops are boosted by
	 */
	public void setGrowthBonus(int newGrowthBonus) {
		growthBonus = newGrowthBonus;
	}
	
} 
