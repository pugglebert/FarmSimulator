package farmSimulatorGUI;

import java.util.ArrayList;

/**
 * This class sets up the main game environment used in the application.
 * This class controls the launching of the setup window, main window, and end window and also contains information about the state of the game
 * 
 * @author David Frost, Ella Johnson
 */
public class GameEnvironment {
	/**
	 * Total number of days the game is to last for
	 */
	private int totalDays;
	
	/**
	 * Day number that the game is currently in, default at day 1
	 */
	private int currentDay = 1;
	
	/**
	 * The remaining number of actions the user can still perform in that day
	 */
	private int remainingActions = 2;
	
	/**
	 * The farmer working on the farm
	 */
	private Farmer farmer = new Farmer();
	
	/**
	 * The Farm object that holds all the animals and crops
	 */
	private Farm farm;
	
	/**
	 * Store where every Buyable object can be purchased
	 */
	private Store store = new Store();
	
	/**
	 * Main GUI application window
	 */
	private MainScreen mainScreen;
	
	/**
	 * Launces the setup screen, allowing the user to set up the Farm, Farmer, and length of the game
	 */
	public void launchSetupScreen() {
		SetupScreen setupWindow = new SetupScreen(this);
	}
	
	/**
	 * Closes the setup window and launches the main window
	 * @param setupWindow		GUI window for setting up the game
	 */
	public void closeSetupScreen(SetupScreen setupWindow) {
		setupWindow.closeWindow();
		launchMainScreen();
	}
	
	/**
	 * Launches the Main window, a hub for all actions during the game
	 */
	public void launchMainScreen() {
		mainScreen = new MainScreen(this);
	}
	
	/**
	 * Closes the Main window and opens the End game screen
	 * @param mainScreen		GUI window that controls the main functions of the game
	 */
	public void closeMainScreen(MainScreen mainScreen) {
		mainScreen.closeWindow();
		launchEndScreen();
	}
	
	/**
	 * Launches the End game screen, displaying a summary of the users game and final score
	 */
	public void launchEndScreen() {
		EndScreen endWindow = new EndScreen(this);
	}
	
	/**
	 * Closes the End game screen, and the application
	 * @param endWindow		GUI window that displays the End game summary
	 */
	public void closeEndScreen(EndScreen endWindow) {
		endWindow.closeWindow();
	}
	
	/**
	 * Returns the Main screen
	 * @return	Main GUI window
	 */
	public MainScreen getMainScreen() {
		return mainScreen;
	}
	
	/**
	 * Sets up the farmer according to the user's inputs
	 * @param name		Name of the farmer
	 * @param age		Age of the farmer
	 */
	public void initiateFarmer(String name, int age) {
		farmer.setName(name);
		farmer.setAge(age);
	}
	
	/**
	 * Sets up the farm according to the user's inputs
	 * @param farmType		Planet on which the farm is located
	 * @param farmName		Name of the farm
	 */
	public void initiateFarm(String farmType, String farmName) {
		switch(farmType) {
		case "Earth":
			farm = new EarthFarm(farmer);
			break;
		case "Mars":
			farm = new MarsFarm(farmer);
			break;
		case "Venus":
			farm = new VenusFarm(farmer);
			break;
		case "Jupiter":
			farm = new JupiterFarm(farmer);
			break;
		}	
		farm.setName(farmName);
	}
	
	/**
	 * Sets the length of the game
	 * @param days		Length of the game in days
	 */
	public void setTotalDays(int days) {
		totalDays = days;
	}
		
	/**
	 * Returns the Farm
	 * @return	The Farm object
	 */
	public Farm getFarm() {
		return farm;
	}
	
	/**
	 * Returns the Farmer
	 * @return 	The Farmer object
	 */
	public Farmer getFarmer() {
		return farmer;
	}
	
	/**
	 * Returns the length of the game
	 * @return		Length of the game in days
	 */
	public int getTotalDays() {
		return totalDays;
	}
	
	/**
	 * Returns the current day
	 * @return		Number of days the game has gone through at the current state
	 */
	public int getCurrentDay() {
		return currentDay;
	}
	
	/**
	 * Returns the sore used to buy items, animals, and crops
	 * @return		The Store object
	 */
	public Store getStore() {
		return store;
	}

	/**
	 * Returns the number of actions the user has left to perform during that day
	 * @return		Number of actions remaining
	 */
	public int getRemainingActions() {
		return remainingActions;
	}
	
	/**
	 * Resets the number of actions back to the maximum of 2 per day
	 */
	public void resetRemainingActions() {
		remainingActions = 2;
	}
	
	/**
	 * Consumes an action
	 */
	public void useAction() {
		if (remainingActions > 0) {
			remainingActions --;
		}
		else {
			throw new IllegalArgumentException("You have already used your two actions for the day");
		}
	}
	
	/**
	 * Sends the game into the next day, updating all the animals and crops
	 */
	public void nextDay() {
		for (Animal animal : farm.getAnimals()) {
			farm.earnMoney((int)animal.dailyReturn());
			animal.advanceDay();
		}
		for (Crop crop : farm.getCrops()) {
			crop.advanceDay();
		}
		currentDay++;
	}
	
	/**
	 * Increases the happiness of all animals by a fixed amount
	 */
	public void playWithAnimals() {
		boolean hasEffect = false;
		for (Animal animal : farm.getAnimals()) {
			if (animal.getHappiness() < 10) {
				animal.increaseHappiness(2);
				hasEffect = true;
			}
		}
		if (!hasEffect) {
			throw new IllegalArgumentException("All your animals are at maximum happiness already");
		}
	}
	
	/**
	 * Increases the limit on the number of crops able to be planted, and increases the happiness of all animals
	 */
	public void tendLand() {
		boolean hasEffect = false;
		if (farm.getCropLimit() < 16) {
			farm.increaseCropLimit();
			hasEffect = true;
		}
		for (Animal animal : farm.getAnimals()) {
			if (animal.getHappiness() < 10) {
				animal.increaseHappiness(1);
				hasEffect = true;
			}
		}
		if (!hasEffect) {
			throw new IllegalArgumentException("You have no more room to expand and all your animals are at maximum happiness already");
		}
	}
	
	/**
	 * Harvests all fully grown crops and returns the money earned
	 * @return		Amount of money gained by harvesting the crops
	 */
	public int harvestCrops() {
		int earnings = 0;
		ArrayList<Crop> crops = farm.getCrops();
		for (int counter = crops.size() - 1; counter >= 0; counter --) {
			Crop crop = crops.get(counter);
			if (crop.canHarvest()) {
				earnings += crop.getSellPrice();
				farm.removeCrop(crop);
			}
		}
		return earnings;
	}
	
	/**
	 * Waters all of the crops of the specified type
	 * @param cropType		Defines which crop is watered
	 */
	public void waterCrops(String cropType) {
		for (Crop crop : farm.getCrops()) {
			if (crop.getCropType() == cropType) {
				crop.boostGrowth(1);
			}
		}
	}

	/**
	 * Returns the overall profit of the Farm (Total money - starting money)
	 * @return		The profit that the Farm earned, can be negative
	 */
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

	/**
	 * Returns the score gained by the amount of money that the Farm has
	 * @return		Score based on the amount of money / length of the game
	 */
	public int getMoneyScore() {
		return farm.getMoney() / totalDays;
	}

	/**
	 * Returns the score gained by all the animals on the farm, relative to their overall happiness
	 * @return		Score based on all animals and their happiness
	 */
	public int getAnimalScore() {
		int animalScore = 0;

		for (Animal animal : farm.getAnimals()) {
			animalScore += 50 * animal.getHappiness();
		}
		return animalScore / totalDays;
	}
	
	/**
	 * Gets the score gained by all unharvest crops based on their stage of growth
	 * @return 		Score based on the state of all unharvested crops
	 */
	public int getCropScore() {
		int cropScore = 0;
		for (Crop crop : farm.getCrops()) {
			cropScore += 50 * crop.getAge();
		}
		return cropScore / totalDays;
	}

	/**
	 * Returns the score as a sum of moneyScore, animalScore, and cropScore
	 * @return		Returns the total score
	 */
	public int calcScore() {
		return (getMoneyScore() + getAnimalScore() + getCropScore()) ;
	}
	
	public static void main(String[] args) {
		GameEnvironment game = new GameEnvironment();
		game.launchSetupScreen();
		
	}
}