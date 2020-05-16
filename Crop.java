package farmSimulatorGUI;

public class Crop implements Buyable {
	private int harvestAge;
	private int age = 0;
	private int buyPrice;
	private int sellPrice;
	private String cropType;
	
	public Crop(String newCropType, int newHarvestAge, int newBuyPrice, int newSellPrice){
		cropType = newCropType;
		harvestAge = newHarvestAge;
		buyPrice = newBuyPrice;
		sellPrice = newSellPrice;
	}
	
	Crop(Crop another) {
		this.cropType = another.cropType;
		this.harvestAge = another.harvestAge;
		this.buyPrice = another.buyPrice;
		this.sellPrice = another.sellPrice;
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
	
	public int getAge() {
		return age;
	}
	
	public void reduceHarvestAge(int bonus) {
		harvestAge -= bonus;
	}
	
	public void boostGrowth(int amount) {
		age += amount;
		
		if (age > harvestAge) {
			age = harvestAge;
		}
	}
	
	public void advanceDay() {
		if (age < harvestAge) {
			age++;
		}
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
	
	public String toStringStore() {
		return cropType.substring(0, 1).toUpperCase() + cropType.substring(1) + ": costs $" + Integer.toString(buyPrice);
	}
}
