package farmSimulator;

import java.util.Scanner;
import java.util.InputMismatchException;

public class GameEnvironment {
	private int totalDays = 10;
	private int currentDay = 0;
	private Scanner keyboard = new Scanner(System.in);
	private Farmer farmer = new Farmer();
	private Farm farm;

	
	public void startup() {
		System.out.println("Welcome to Farm Simulator (space edition).");
		System.out.println("Your mission is to colonise a planet by building a succesful farm there.");
		enterTotalDays();
		enterFarmerName();
		enterFarmerAge();
		enterPlanet();
		enterFarmName();
		System.out.println("Great! You are now ready to start your farming adventure.");
	}
		
	public void enterTotalDays() {
		boolean valid = false;
		while (valid == false) {
			System.out.println("Enter how many days you would like the game to last (5-10):");
			try {
				int duration = keyboard.nextInt();
				if (duration > 4 && duration < 11) {
					totalDays = duration;
					valid = true;
			    } else {
			    	System.out.println("Invalid input - number of days must be 5 to 10.");
			    }
			} catch (InputMismatchException e) {
				System.out.println("Invalid input - please enter an integer");
				keyboard.nextLine();
			}
		}
	}
			
	public void enterFarmerName() {
		String farmerName = keyboard.nextLine();
		boolean valid = false;
		while (valid == false) {
			System.out.println("Enter a name for your farmer:");
			farmerName = keyboard.nextLine();
			try {
				farmer.setName(farmerName);
				valid = true;
			} catch (IllegalArgumentException e) {
				System.out.println(e.toString());
			}
		}
	}
		
	public void enterFarmerAge() {
		boolean valid = false;
		while (valid == false) {
			System.out.println("Enter your farmers age:");
			try {
				int farmerAge = keyboard.nextInt();
				if (farmerAge > 0 && farmerAge < 101) {
					farmer.setAge(farmerAge);
					valid = true;
				} else {
					System.out.println("Invalid input - age must be a number from 1 to 100.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input - please enter an integer.");
				keyboard.nextLine();
			}
		}
	}
	
	public void enterPlanet() {
		boolean valid = false;
		while (valid == false) {
			System.out.println("Enter a number to select which planet you would like to start your farm on:");
			System.out.println("1) Earth");
			System.out.println("2) Mars");
			System.out.println("3) Venus");
			System.out.println("4) Jupiter");
			try {
				int planetChoice = keyboard.nextInt();
				switch (planetChoice) {
				case 1:
					farm = new EarthFarm();
					valid = true;
					break;
				case 2:
					farm = new MarsFarm();
					valid = true;
					break;
				case 3:
					farm = new VenusFarm();
					valid = true;
					break;
				case 4:
					farm = new JupiterFarm();
					valid = true;
					break;
				default:
					System.out.println("Invalid input - enter number from 1 to 4.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input - enter an integer from 1 to 4.");
				keyboard.nextLine();
			}
		}
	}
	
	public void enterFarmName() {
		String farmName = keyboard.nextLine();
		boolean valid = false;
		while (valid == false) {
			System.out.println("Enter a name for your farm:");
			farmName = keyboard.nextLine();
			try {
				farm.setName(farmName);
				valid = true;
			} catch (IllegalArgumentException e) {
				System.out.println(e.toString());
			}
		}
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
		GameEnvironment game = new GameEnvironment();
		game.startup();
		while(game.currentDay <= game.totalDays) {
			game.day();
		}
		game.endGame();
		
	}
}
