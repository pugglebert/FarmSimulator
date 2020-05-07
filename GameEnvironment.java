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
	private Store store = new Store();

	
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
			System.out.println("	Starting Money: $1000");
			System.out.println("	Starting Animals: Chicken");
			System.out.println("	Starting Crops: Barley, Wheat");
			System.out.println("	Bonuses: Animal Happiness +1");
			System.out.println("2) Mars");
			System.out.println("	Starting Money: $800");
			System.out.println("	Starting Animals: Sheep");
			System.out.println("	Starting Crops: Maize, Potato");
			System.out.println("	Bonuses: Crop Harvest Time -1");
			System.out.println("3) Venus");
			System.out.println("	Starting Money: $1500");
			System.out.println("	Starting Animals: Sheep");
			System.out.println("	Starting Crops: Pumpkin, Kale");
			System.out.println("	Bonuses: N/A");
			System.out.println("4) Jupiter");
			System.out.println("	Starting Money: $500");
			System.out.println("	Starting Animals: Cow, Sheep");
			System.out.println("	Starting Crops: Wheat, Maize, Pumpkin");
			System.out.println("	Bonuses: Animal Happiness +1, Crop Harvest Time -1");
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
			default:
				System.out.println("Invalid input - enter number from 1 to 4.");
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
			break;
		case "2": 
			System.out.println(farm.getItemsStatus());
			break;
		case "3":
			System.out.println(farm.getAnimalStatus());
			break;
		case "4":
			System.out.println(farm.getCropStatus());
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
			System.out.println(farm.getItemsStatus());
			visitStore();
			break;
		case "4":
			System.out.println("Come back soon!");
			day();
			break;
		default:
			System.out.println("Invalid input");
			visitStore();
		}
	}
	
	public void viewMerchandise() {
		System.out.println("What sort of merchandise would you like to view?");
		System.out.println("1) View food items for sale");
		System.out.println("2) View crop items for sale");
		System.out.println("3) View animals for sale");
		System.out.println("4) View crops for sale");
		System.out.println("5) Go back");
		String option = keyboard.nextLine();
		switch (option) {
		case "1":
			System.out.println(store.getFoodItemString());
			buyFoodItem(store.getFoodItems());
			break;
		case "2":
			System.out.println(store.getCropItemString());
			buyCropItem(store.getCropItems());
			break;
		case "3":
			System.out.println(store.getAnimalString());
			buyAnimal(store.getAnimals());
			break;
		case "4":
			System.out.println(store.getCropString());
			buyCrop(store.getCrops());
			break;
		case "5" :
			visitStore();
			break;
		default:
			System.out.println("Invalid input");
			viewMerchandise();
		}
	}
	
	public void buyFoodItem(ArrayList<FoodItem> merchandise) {
		int backInt = merchandise.size() + 1;
		String backStr = Integer.toString(backInt);
		System.out.println("Enter the item's number to purchase it, or enter " + backStr + " to go back");
		String option = keyboard.nextLine();
		try {
			int optionInt = Integer.parseInt(option);
			if (optionInt <= merchandise.size() && optionInt > 0) {
				try {
					farm.buy(merchandise.get(optionInt - 1));
					System.out.println("Purchase successful!");
					buyFoodItem(merchandise);
				} catch (IllegalArgumentException e) {
					System.out.println(e.toString());
					buyFoodItem(merchandise);
				}
			} else if (optionInt == backInt) {
				viewMerchandise();
			}
		} catch (NumberFormatException e) {
			System.out.println("Invalid input - please enter an integer.");
			buyFoodItem(merchandise);
		}
	}
	
	public void buyCropItem(ArrayList<CropItem> merchandise) {
		int backInt = merchandise.size() + 1;
		String backStr = Integer.toString(backInt);
		System.out.println("Enter the item's number to purchase it, or enter " + backStr + " to go back");
		String option = keyboard.nextLine();
		try {
			int optionInt = Integer.parseInt(option);
			if (optionInt <= merchandise.size() && optionInt > 0) {
				try {
					farm.buy(merchandise.get(optionInt - 1));
					System.out.println("Purchase successful!");
					buyCropItem(merchandise);
				} catch (IllegalArgumentException e) {
					System.out.println(e.toString());
					buyCropItem(merchandise);
				}
			} else if (optionInt == backInt) {
				viewMerchandise();
			}
		} catch (NumberFormatException e) {
			System.out.println("Invalid input - please enter an integer.");
			buyCropItem(merchandise);
		}
	}
	
	public void buyAnimal(ArrayList<Animal> merchandise) {
		int backInt = merchandise.size() + 1;
		String backStr = Integer.toString(backInt);
		System.out.println("Enter the item's number to purchase it, or enter " + backStr + " to go back");
		String option = keyboard.nextLine();
		try {
			int optionInt = Integer.parseInt(option);
			if (optionInt <= merchandise.size() && optionInt > 0) {
				try {
					farm.buy(merchandise.get(optionInt - 1));
					System.out.println("Purchase successful!");
					buyAnimal(merchandise);
				} catch (IllegalArgumentException e) {
					System.out.println(e.toString());
					buyAnimal(merchandise);
				}
			} else if (optionInt == backInt) {
				viewMerchandise();
			}
		} catch (NumberFormatException e) {
			System.out.println("Invalid input - please enter an integer.");
			buyAnimal(merchandise);
		}
	}
	
	public void buyCrop(ArrayList<Crop> merchandise) {
		int backInt = merchandise.size() + 1;
		String backStr = Integer.toString(backInt);
		System.out.println("Enter the item's number to purchase it, or enter " + backStr + " to go back");
		String option = keyboard.nextLine();
		try {
			int optionInt = Integer.parseInt(option);
			if (optionInt <= merchandise.size() && optionInt > 0) {
				try {
					farm.buy(merchandise.get(optionInt - 1));
					System.out.println("Purchase successful!");
					buyCrop(merchandise);
				} catch (IllegalArgumentException e) {
					System.out.println(e.toString());
					buyCrop(merchandise);
				}
			} else if (optionInt == backInt) {
				viewMerchandise();
			}
		} catch (NumberFormatException e) {
			System.out.println("Invalid input - please enter an integer.");
			buyCrop(merchandise);
		}
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
				
				int growthBonus = 0;
				option = keyboard.nextLine();
				
				switch(option) {
					case "1":
						growthBonus = 1;
						break;
					case "2":
						ArrayList<CropItem> items = farmer.getCropItems();
						if (items.size() == 0) {
							System.out.println("You have no items to boost your crops.");
							System.out.println("Visit the shop to buy some more!");
							actions -= 1;
						}
						
						else {
							System.out.println("Which item would you like to use?");
							
							int i = 1;
							for (CropItem item : items) {
								System.out.println("" + i + ") " + item.getName());
								i++;
							}
							String input = keyboard.nextLine();
							CropItem chosenItem = items.get(Integer.parseInt(input) - 1);
							growthBonus = chosenItem.getGrowthBonus();
							farmer.removeItem(chosenItem);				
							break;
						}
				}
				
				/*Add error handling*/
				if (growthBonus > 0) {
					for (Crop crop : farm.getCrops()) {
						if (crop.getCropType().contentEquals(cropType.toLowerCase())) {
							crop.decreaseHarvestAge(growthBonus);
						}
					}
					System.out.println("You tended to all your " + cropType + " crops and reduced their time to grow by " + growthBonus + " days.");
				}
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
						System.out.println("" + i + ") " + item.getName());
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
			farm.earnMoney((int)animal.dailyReturn());
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
		System.out.println("Your farming adventure has come to an end!");
		System.out.println("You were on your farm, " + farm.getName() + ", for " + totalDays + " days.");
		int netProfit = farm.getMoney() - farm.getStartCash();
		System.out.println("You finished with a total of $" + farm.getMoney() + ", with a net profit of $" + netProfit);
		int score =calcScore();
		System.out.println("Your final score is " + Integer.toString(score));
	}
	
	public int calcScore() {
		int score = 0;
		score += farm.getMoney();
		for (int counter = 0; counter < farm.getAnimals().size(); counter ++) {
			score += 50 * farm.getAnimals().get(counter).getHappiness();
		}
		score += farm.getCrops().size() * 100;
		return score;
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
