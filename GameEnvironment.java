package farmSimulatorGUI;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class GameEnvironment {
	private int totalDays;
	private int currentDay = 1;
	private int actions = 0;
	private Scanner keyboard = new Scanner(System.in);
	private Farmer farmer = new Farmer();
	private Farm farm;
	private Store store = new Store();
	private MainScreen mainScreen;
	private StoreScreen storeScreen;
	private InventoryScreen inventoryScreen;
	
	public void launchSetupScreen() {
		SetupScreen setupWindow = new SetupScreen(this);
	}
	
	public void closeSetupScreen(SetupScreen setupWindow) {
		setupWindow.closeWindow();
		launchMainScreen();
	}
	
	public void launchMainScreen() {
		mainScreen = new MainScreen(this);
		storeScreen = new StoreScreen(this);
		inventoryScreen = new InventoryScreen(this);
	}
	
	public void endMainGame() {
		storeScreen.finishedWindow();
		inventoryScreen.finishedWindow();
		launchEndScreen();
	}
	
	public void launchEndScreen() {
		EndScreen endWindow = new EndScreen(this);
	}
	
	public void closeEndScreen(EndScreen endWindow) {
		endWindow.closeWindow();
	}
	
	public void initiateFarmer(String name, int age) {
		farmer.setName(name);
		farmer.setAge(age);
	}
	
	public void initiateFarm(String farmType, String farmName) {
		switch(farmType) {
		case "earth":
			farm = new EarthFarm(farmer);
			break;
		case "mars":
			farm = new MarsFarm(farmer);
			break;
		case "venus":
			farm = new VenusFarm(farmer);
			break;
		case "jupiter":
			farm = new JupiterFarm(farmer);
			break;
		}	
		farm.setName(farmName);
	}
		public void setTotalDays(int days) {
		totalDays = days;
	}
		
	public Farm getFarm() {
		return farm;
	}
	
	public Farmer getFarmer() {
		return farmer;
	}
	
	public int getTotalDays() {
		return totalDays;
	}
	
	public int getCurrentDay() {
		return currentDay;
	}
	
	public Store getStore() {
		return store;
	}
	
	public MainScreen getMainScreen() {
		return mainScreen;
	}
	
	public void openStoreScreen() {
		storeScreen.launch();
	}
	
	public void openMainScreen() {
		mainScreen.launch();
	}
	
	public void openInventoryScreen() {
		inventoryScreen.launch();
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
	
	public int getRemainingActions() {
		return mainScreen.getRemainingActions();
	}
	
	public void day() {
		mainScreen.newDay();
		
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
			buyMerchandise(store.getFoodItems());
			break;
		case "2":
			System.out.println(store.getCropItemString());
			buyMerchandise(store.getCropItems());
			break;
		case "3":
			System.out.println(store.getAnimalString());
			buyMerchandise(store.getAnimals());
			break;
		case "4":
			System.out.println(store.getCropString());
			buyMerchandise(store.getCrops());
			break;
		case "5" :
			visitStore();
			break;
		default:
			System.out.println("Invalid input");
			viewMerchandise();
		}
	}
	
	public void buyMerchandise(ArrayList<Buyable> merchandise) {
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
					buyMerchandise(merchandise);
				} catch (IllegalArgumentException e) {
					System.out.println(e.toString());
					buyMerchandise(merchandise);
				}
			} else if (optionInt == backInt) {
				viewMerchandise();
			}
		} catch (NumberFormatException e) {
			System.out.println("Invalid input - please enter an integer.");
			buyMerchandise(merchandise);
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
								System.out.println("" + i + ") " + item.getName() + " (" + item.getInventoryCount() + " Available)");
								i++;
							}
							String input = keyboard.nextLine();
							CropItem chosenItem = items.get(Integer.parseInt(input) - 1);
							growthBonus = chosenItem.getGrowthBonus();
							chosenItem.removeFromInventory();
							if (chosenItem.getInventoryCount() == 0) {
								farmer.removeItem(chosenItem);
							}				
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
						System.out.println("" + i + ") " + item.getName() + " (" + item.getInventoryCount() + " Available)");
						i++;
					}
					String input = keyboard.nextLine();
					FoodItem chosenItem = items.get(Integer.parseInt(input) - 1);
					int healthBonus = chosenItem.getHealthGiven();
					chosenItem.removeFromInventory();
					if (chosenItem.getInventoryCount() == 0) {
						farmer.removeItem(chosenItem);
					}
					
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
	
	public String getNetProfit() {
		int netProfit = farm.getMoney() - farm.getStartCash();
		String netProfitString;
		if (netProfit < 0) {
			netProfitString = "-$" + Math.abs(netProfit);
		}
		else {
			netProfitString = "$" + netProfit;
		}
		return netProfitString;
	}

	public int getMoneyScore() {
		return farm.getMoney() / totalDays;
	}

	public int getAnimalScore() {
		int animalScore = 0;

		for (Animal animal : farm.getAnimals()) {
			animalScore += 50 * animal.getHappiness();
		}
		return animalScore / totalDays;
	}
	
	public int getCropScore() {
		return farm.getCrops().size() * 100 / totalDays;
	}

	public int calcScore() {
		return getMoneyScore() + getAnimalScore() + getCropScore();
	}
	
	public static void main(String[] args) {
		GameEnvironment game = new GameEnvironment();
		game.launchSetupScreen();
		
	}
}