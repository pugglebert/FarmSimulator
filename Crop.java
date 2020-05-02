package farmSimulator;

public class Crop {
	private int harvestAge;
	private int age = 0;
	private int buyPrice;
	private int sellPrice;
	private String cropType;
	
	Crop(String newCropType, int newHarvestAge, int newBuyPrice, int newSellPrice){
		cropType = newCropType;
		harvestAge = newHarvestAge;
		buyPrice = newBuyPrice;
		sellPrice = newSellPrice;
	}
	
	public String getCropType() {
		return cropType;
	}
	
	public int getBuyPrice() {
		return buyPrice;
	}

	public int getSellPrice() {
		return sellPrice;
	}

	public int getHarvestAge() {
		return harvestAge;
	}
	
	public void decreaseHarvestAge(int amount) {
		harvestAge -= amount;
		
		if (harvestAge < 1) {
			harvestAge = 1;
		}
	}
	
	public void advanceDay() {
		age++;
	}
	
	public boolean canHarvest() {
		if (age >= harvestAge) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public String toString() {
		return cropType.substring(0, 1).toUpperCase() + cropType.substring(1) + ": Growth Progress " + age + "/" + harvestAge;
	}
}
