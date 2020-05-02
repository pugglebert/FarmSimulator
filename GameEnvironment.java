package farmSimulator;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class GameEnvironment {
	private int totalDays = 10;
	private int currentDay = 1;
	private int actions = 0;
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
		System.out.println("The sun rises and marks the beginning of day " + currentDay + " of " + totalDays + ".");
		
		boolean sameDay = true;
		while(sameDay) {
			
			System.out.println("\nWhat would you like to do?");
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
				if (actions < 2) {
					actions++;
					action();
				}
				else {
					System.out.println("You can not perform any other actions, choose another option");
				}
				break;
			case "4":
				nextDay();
				sameDay = false;
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
		System.out.println("Enter a number to perform an action:");
		System.out.println("1) Tend to your crops");
		System.out.println("2) Play with your animals");
		System.out.println("3) Harvest your crops");
		System.out.println("4) Tend to the farm land");
		System.out.println("5) Feed the animals");
		String option = keyboard.nextLine();
		
		switch(option) {
			case "1": /*tend to crops*/
				System.out.println(farm.getCropStatus());
				System.out.println("Enter the name of the crop to tend to:");
				
				String cropType = keyboard.nextLine();
	
				System.out.println("Would you like to:  ");
				System.out.println("1) Water the crops");
				System.out.println("2) Use an item");
				option = keyboard.nextLine();
				int growthBonus = 1;
	
				if (option == "1") {
					growthBonus = 1;
				}
				else if (option == "2"){
					System.out.println("Which item would you like to use?");
					ArrayList<CropItem> items = farmer.getCropItems();
					int i = 1;
					for (CropItem item : items) {
						System.out.print("" + i + ") " + item.getName());
						i++;
					}
					String input = keyboard.nextLine();
					CropItem chosenItem = items.get(Integer.parseInt(input) - 1);
					growthBonus = chosenItem.getGrowthBonus();
					farmer.removeItem(chosenItem);				
				}
				
				/*Add error handling*/
	
				for (Crop crop : farm.getCrops()) {
					if (crop.getCropType().contentEquals(cropType.toLowerCase())) {
						crop.decreaseHarvestAge(growthBonus);
					}
				}
				System.out.println("You tended to all your " + cropType + " crops and reduced their time to grow by " + growthBonus + " days.");
				
				break;
				
			case "2": /*play with animals*/
				System.out.println("You played with all of your animals and increased their happiness.\n");
				for (Animal animal : farm.getAnimals()) {
					animal.increaseHappiness(2);
				}
				break;
				
			case "3": /*harvest crops*/
				int earnings = 0;
				boolean cropsToHarvest = false;
				for (Crop crop : farm.getCrops()) {
					if (crop.canHarvest()) {
						cropsToHarvest = true;
						earnings += crop.getSellPrice();
						farm.removeCrop(crop);
					}
				}
				if (cropsToHarvest) {
					System.out.println("You harvested all fully grown crops and earned $" + earnings + ".");
					farm.earnMoney(earnings);
				}
				else {
					System.out.println("You have no fully grown crops to harvest!");
					actions -= 1;
				}
				break;
				
			case "4": /*tend to farm land*/
				farm.setCropLimit(farm.getCropLimit() + 1);
				for (Animal animal : farm.getAnimals()) {
					animal.increaseHappiness(1);
				}
				System.out.println("You tended to the farm land and made it look pristine.\nYour animals are feeling happier and you can now grow up to " + farm.getCropLimit() + " crops.");
				break;
				
			case "5": /*feed animals*/
				ArrayList<FoodItem> items = farmer.getFoodItems();
				
				if (items.size() == 0) {
					System.out.println("You have no items to feed your animals.");
					System.out.println("Visit the shop and stock up before they get too hungry!");
					actions -= 1;
				}
				
				else {
					System.out.println("Which item would you like to use?");
					int i = 1;
					for (FoodItem item : items) {
						System.out.print("" + i + ") " + item.getName());
						i++;
					}
					String input = keyboard.nextLine();
					FoodItem chosenItem = items.get(Integer.parseInt(input) - 1);
					int healthBonus = chosenItem.getHealthGiven();
					farmer.removeItem(chosenItem);	
					
					for (Animal animal : farm.getAnimals()) {
						animal.increaseHealth(healthBonus);
					}
					System.out.println("You fed all your animals and increased their health.");
				}	
				break;
		}
	}
	
	public void nextDay() {
		for (Animal animal : farm.getAnimals()) {
			animal.advanceDay();
		}
		for (Crop crop : farm.getCrops()) {
			crop.advanceDay();
		}
		currentDay++;
		actions = 0;
		System.out.println("You retire to your home after a hard days work.\n");
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
