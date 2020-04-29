import java.util.Scanner;

public class GameEnvironment {
	private int totalDays = 10;
	private int currentDay = 0;
	private Farmer farmer
	private Farm farm

	
	public void startup() {
		
	}
	
	public void day() {
		int actions = 0;
		
		while(actions <= 2) {
		
			System.out.print("Enter a number: ");
			Scanner scanner = new Scanner(System. in);
			String option = scanner. nextLine();

			switch(option) {
			case "1": /* view status farm*/
				break;
			case "2": /* view status crops*/
				break;
			case "3": /* view status animals*/
				break;
			case "4": /*visit store*/
				break;
			case "5": 
				nextDay();
				break;
			case "6": /*tend to crops*/
				actions++;
				break;
			case "7": /*play with animals*/
				actions++;
				break;
			case "8": /*harvest crops*/
				actions++;
				break;
			case "9": /*tend to farm land*/
				actions++;
				break;
			case "10": /*feed animals*/
				actions++;
				break;
			}
        }
	}
	
	public void nextDay() {
		
	}
	
	public void endGame() {
		
	}
	
	public static void main(String[] args) {
		startup();
		while(currentDay <= totalDays) {
			day();
		}
		endGame();
		
	}
}
