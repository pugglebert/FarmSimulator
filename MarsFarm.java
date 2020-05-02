package farmSimulator;

import java.util.ArrayList;

public class MarsFarm extends Farm {
	
	private int startCash = 800;
	private int cropLimit = 8;
	private int happinessBonus = 0;
	private int growthBonus = 1;
	private ArrayList<Animal> startAnimals;
	private ArrayList<Crop> startCrops;
	
	public void initializeAnimals() {
		startAnimals.add(new Sheep());
		super.setAnimals(startAnimals);
	}
	
	public void initializeCrops() {
		startCrops.add(new Maize());
		startCrops.add(new Potato());
		super.setCrops(startCrops);
	}
	
	MarsFarm(Farmer newFarmer) {
		super(newFarmer);
		initializeAnimals();
		initializeCrops();
		setMoney(startCash);
		setHappinessBonus(happinessBonus);
		setGrowthBonus(growthBonus);
		setCropLimit(cropLimit);
	}

}
