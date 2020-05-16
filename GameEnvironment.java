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

	public int getRemainingActions() {
		return mainScreen.getRemainingActions();
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