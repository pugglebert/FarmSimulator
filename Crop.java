package farmSimulatorGUI;

/**
 * This class implements a Crop (Barley, Kale, Maize, Potato, Pumpkin, Wheat)
 * Once fully grown, Crops can be harvested and sold for money
 * 
 * @author David Frost, Ella Johnson
 */
public class Crop implements Buyable {
	/**
	 * The number of days required for the Crop to be fully grown
	 */
	private int harvestAge;
	
	/**
	 * The current age of the Crop, minimum = 0, maximum = harvestAge
	 */
	private int age = 0;
	
	/**
	 * Cost of buying the Crop from the Store
	 */
	private int buyPrice;
	
	/**
	 * The amount of money gained from harvesting the crop once fully grown
	 */
	private int sellPrice;
	
	/**
	 * The variety of Crop
	 */
	private String cropType;
	
	/**
	 * Constructor for the Crop class initialized through its child classes
	 * @param newCropType		The variety of crop
	 * @param newHarvestAge		The number of days to fully grow
	 * @param newBuyPrice		Cost of purchasing from the store
	 * @param newSellPrice		Money gained from harvesting the Crop
	 */
	public Crop(String newCropType, int newHarvestAge, int newBuyPrice, int newSellPrice){
		cropType = newCropType;
		harvestAge = newHarvestAge;
		buyPrice = newBuyPrice;
		sellPrice = newSellPrice;
	}
	
	/**
	 * Returns the variety of Crop
	 * @return The crop variety
	 */
	public String getCropType() {
		return cropType;
	}
	
	/**
	 * Returns the cost of buying the Crop from the store
	 * @return 		Cost of purchasing the item
	 */
	public int getBuyPrice() {
		return buyPrice;
	}

	/**
	 * Returns the money earned by harvesting the crop
	 * @return		Money gained from harvesting
	 */
	public int getSellPrice() {
		return sellPrice;
	}

	/**
	 * Returns the number of days required to become fully grown
	 * @return  	Number of days until fully grown 
	 */
	public int getHarvestAge() {
		return harvestAge;
	}
	
	/**
	 * Returns the current age of the Crop
	 * @return		Age of the crop in days
	 */
	public int getAge() {
		return age;
	}
	
	/**
	 * Reduces the number of days required for the crop to grow
	 * @param bonus		Amount of days the harvest age is reduced by, comes as a bonus from the type of farm selected
	 */
	public void reduceHarvestAge(int bonus) {
		if (bonus < 0) {
			throw new IllegalArgumentException("Bonus can not be negative");
		} else {
			harvestAge -= bonus;
		}
	}
	
	/**
	 * Boosts the growth of the Crop by increasing its age
	 * @param amount	Amount of days the Crop's age is increased by
	 */
	public void boostGrowth(int amount) {
		if (amount >= 0) {
			age += amount;
			
			if (age > harvestAge) {
				age = harvestAge;
			}
		} else {
			throw new IllegalArgumentException("amount can not be negative");
		}
	}
	
	/**
	 * Increases the age of the Crop by one day
	 */
	public void advanceDay() {
		if (age < harvestAge) {
			age++;
		}
	}
	
	/**
	 * Checks if the Crop is fully grown
	 * @return Returns true if fully grown, false otherwise
	 */
	public boolean canHarvest() {
		if (age >= harvestAge) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Converts the Crop onject to a String to be used by the shop
	 * Displays the type of the Crop and how much it costs to buy
	 */
	public String toStringStore() {
		return cropType.substring(0, 1).toUpperCase() + cropType.substring(1) + ": costs $" + Integer.toString(buyPrice);
	}
}
