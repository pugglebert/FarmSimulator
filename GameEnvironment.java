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
					farm = new EarthFarm(farmer);
					valid = true;
					break;
				case 2:
					farm = new MarsFarm(farmer);
					valid = true;
					break;
				case 3:
					farm = new VenusFarm(farmer);
					valid = true;
					break;
				case 4:
					farm = new JupiterFarm(farmer);
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
			
			System.out.println("What would you like to do?");
			System.out.println("1) View the status of your farm");
			System.out.println("2) Visit the county store");
			System.out.println("3) Perform an action");
			System.out.println("4) Go to the next day");
			System.out.println("Enter a number: ");
			String option = keyboard.nextLine();

			switch(option) {
			case "1":
				viewFarmStatus();
				break;
			case "2":
				System.out.println("Welcome to the store!");
				visitStore();
				break;
			case "3":
				action();
				break;
			case "4":
				nextDay();
				break;
			}
        }
	}
	
	public void viewFarmStatus() {
		System.out.println("View status of...");
		System.out.println("1) Money");
		System.out.println("2) Items");
		System.out.println("3) Animals");
		System.out.println("4) Crops");
		
		String option = keyboard.nextLine();
		switch (option) {
		case "1": 
			System.out.println(farm.getMoneyStatus());
			day();
			break;
		case "2": 
			System.out.println(farm.getItemsStatus());
			day();
			break;
		case "3":
			System.out.println(farm.getAnimalStatus());
			day();
			break;
		case "4":
			System.out.println(farm.getCropStatus());
			day();
			break;
		default:
			System.out.println("Invalid input - enter a number from 1 to 4.");
			viewFarmStatus();
			break;
		}
	}
	
	public void visitStore() {
		System.out.println("What would you like to do?");
		System.out.println("1) View merchandise for sale");
		System.out.println("2) See how much money your farm has");
		System.out.println("3) View items you farmer currently owns");
		System.out.println("4) Leave the store");
		
		String option = keyboard.nextLine();
		switch (option) {
		case "1":
			viewMerchandise();
			break;
		case "2":
			System.out.println(farm.getMoneyStatus());
			visitStore();
			break;
		case "3":
			System.out.println(farm.getMoneyStatus());
			visitStore();
			break;
		case "4":
			System.out.println("Come back soon!");
			day();
		}
	}
	
	public void viewMerchandise() {
		
	}
	
	public void action() {
		System.out.println("Enter an action:");
		option = keyboard.nextLine();
		case "1": /*tend to crops*/
			System.out.print("Enter a crop to tend to:");
			cropType = keyboard.nextLine();

			System.out.println("Would you like to water the crops or use an item?");
			option = keyboard.nextLine();
			scanner.close();
			int growthBonus;

			if (option == "water") {
				growthBonus = 1;
			}
			else {
				/*Implement method of getting Item*/
				CropItem item = new CropItem(); /*temporary*/
				growthBonus = item.getGrowthBonus();
			}

			for (Crop crop : farm.getCrops()) {
				if (crop.getCropType() == cropType.toLowerCase()) {
					crop.decreaseHarvestAge(growthBonus);
				}
			}
			actions++;
			break;
		case "2": /*play with animals*/
			for (Animal animal : farm.getAnimals()) {
				animal.increaseHappiness(2);
			}
			actions++;
			break;
		case "3": /*harvest crops*/
			for (Crop crop : farm.getCrops()) {
				if (crop.canHarvest()) {
					farm.earnMoney(crop.getSellPrice());
					farm.removeCrop(crop);
				}
			}
			actions++;
			break;
		case "4": /*tend to farm land*/
			farm.setCropLimit(farm.getCropLimit() + 2);
			for (Animal animal : farm.getAnimals()) {
				animal.increaseHappiness(1);
			}
			actions++;
			break;
		case "5": /*feed animals*/
			/*Implement method of getting Item*/
			FoodItem item2 = new FoodItem(); /*temporary*/
			for (Animal animal : farm.getAnimals()) {
				animal.increaseHealth(item2.getHealthGiven());
				/*remove item from inventory*/
			}
			actions++;
			break;
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
