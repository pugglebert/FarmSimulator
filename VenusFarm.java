package farmSimulator;

import java.util.ArrayList;

public class VenusFarm extends Farm {
	
	private int startCash = 1500;
	private int cropLimit = 10;
	private int happinessBonus = 0;
	private int growthBonus = 0;
	private ArrayList<Animal> startAnimals;
	private ArrayList<Crop> startCrops;
	
	public void initializeAnimals() {
		startAnimals.add(new Sheep());
		super.setAnimals(startAnimals);
	}

	public void initializeCrops() {
		startCrops.add(new Pumpkin());
		startCrops.add(new Kale());
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
