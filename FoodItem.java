package farmSimulator;

public class FoodItem extends Item {
	
	int healthGiven;
	
	public boolean isFoodItem() {
		return true;
	}
	
	public boolean isCropItem() {
		return false;
	}
	
	public String toString() {
		String healthStr = Integer.toString(healthGiven);
		String repr = super.getName() + ": food item which gives " + healthStr + " health.";
		return repr;
	}
	
	public int getHealthGiven() {
		return healthGiven;
	}
	
	public void setHealthGiven(int newHealthGiven) {
		healthGiven = newHealthGiven;
	}

}
