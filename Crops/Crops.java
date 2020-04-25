
public class Crops {
	private int harvestAge;
	private int age = 0;
	
	public void advanceDay() {
		age++;
	}
	
	public boolean canHarvest() {
		if (age == harvestAge) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void boostGrowth(int days) {
		age += days;
	}	
}
