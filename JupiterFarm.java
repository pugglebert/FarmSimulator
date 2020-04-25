package farmSimulator;

import java.util.ArrayList;

public class JupiterFarm extends Farm {
	
	private int startCash = 500;
	private int cropLimit = 6;
	private int happinessBonus = 1;
	private int growthBonus = 1;
	private ArrayList<Animal> startAnimals;
	private ArrayList<Crop> startCrops;
	
	public void initializeAnimals() {
		startAnimals.add(new Cow());
		startAnimals.add(new Sheep());
		super.setAnimals(startAnimals);
	}

	public void initializeCrops() {
		startCrops.add(new Wheat());
		startCrops.add(new Maize());
		startCrops.add(new Pumpkin());
		super.setCrops(startCrops);
	}
	
	public void setUp(String newName, Farmer newFarmer) {
		super.setUp(newName, newFarmer);
		initializeAnimals();
		initializeCrops();
		setMoney(startCash);
		setHappinessBonus(happinessBonus);
		setGrowthBonus(growthBonus);
		setCropLimit(cropLimit);
	}

}
