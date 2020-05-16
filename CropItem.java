package farmSimulatorGUI;

public class CropItem extends Item implements Buyable {
	
	private int growthBonus;
	
	public boolean isFoodItem() {
		return false;
	}
	
	public boolean isCropItem() {
		return true;
	}
	
	public String toString() {
		String growthStr = Integer.toString(growthBonus);
		String repr = super.getName() + ": crop item which speeds up growth time by " + growthStr + " days.";
		return repr;
	}
	
	public String toStringStore() {
		String growthStr = Integer.toString(growthBonus);
		String priceStr = Integer.toString(super.getBuyPrice());
		String repr = super.getName() + ": crop item which speeds up growth time by " + growthStr + " days and costs $" + priceStr;
		return repr;
	}
	
	
	public int getGrowthBonus() {
		return growthBonus;
	}
	
	public void setGrowthBonus(int newGrowthBonus) {
		growthBonus = newGrowthBonus;
	}
	
	CropItem(String newName, int newPrice, int newGrowthBonus) {
		super.setName(newName);
		super.setPrice(newPrice);
	    growthBonus = newGrowthBonus;
	}
	
} 
