package farmSimulator;

public class CropItem extends Item {
	
	private int growthBonus;
	
	public boolean isFoodItem() {
		return false;
	}
	
	public boolean isCropItem() {
		return true;
	}
	
	public int getGrowthBonus() {
		return growthBonus;
	}
	
	public void setGrowthBonus(int newGrowthBonus) {
		growthBonus = newGrowthBonus;
	}
	
} 
