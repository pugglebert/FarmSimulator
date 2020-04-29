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
	        case 1: /* view status farm*/
	        case 2: /* view status crops*/
	        case 3: /* view status animals*/
	        case 4: /*visit store*/
	        case 5: nextDay();
	        case 6: /*tend to crops*/
	        	actions++;
	        case 7: /*play with animals*/
	        	actions++;
	        case 8: /*harvest crops*/
	        	actions++;
	        case 9: /*tend to farm land*/
	        	actions++;
	        case 10: /*feed animals*/
	        	actions++;
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
