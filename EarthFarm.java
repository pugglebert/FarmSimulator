package farmSimulator;

import java.util.ArrayList;

public class EarthFarm extends Farm {
	
	private int startCash = 1000;
	private int cropLimit = 6;
	private int happinessBonus = 1;
	private int growthBonus = 0;
	private ArrayList<Animal> startAnimals;
	private ArrayList<Crop> startCrops;
	
	public void initializeAnimals() {
		startAnimals.add(new Chicken());
		super.setAnimals(startAnimals);
	}
	
	public void initializeCrops() {
		startCrops.add(new Wheat());
		startCrops.add(new Barley());
		super.setCrops(startCrops);
	}
	
	public void setUp(String newName, Farmer newFarmer) {
		setUp(newName, newFarmer);
		setMoney(startCash);
		setGrowthBonus(growthBonus);
		setHappinessBonus(happinessBonus);
		setCropLimit(cropLimit);
		initializeAnimals();
		initializeCrops();
	}

}
