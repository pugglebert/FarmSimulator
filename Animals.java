
public class Animals {
	private int health = 10;
	private int happiness = 5;
	private int baseReturn;
	
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
}
