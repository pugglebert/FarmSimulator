package farmSimulatorGUI;

import java.util.ArrayList;

public class MarsFarm extends Farm {
	
	private int startCash = 800;
	private int cropLimit = 8;
	private int happinessBonus = 0;
	private int growthBonus = 1;
	private ArrayList<Animal> startAnimals = new ArrayList<Animal>();
	private ArrayList<Crop> startCrops = new ArrayList<Crop>();
	
	public void initializeAnimals() {
		startAnimals.add(new Sheep());
		for(Animal animal : startAnimals) {
			animal.increaseHappiness(happinessBonus);
		}
		super.setAnimals(startAnimals);
	}
	
	public void initializeCrops() {
		startCrops.add(new Maize());
		startCrops.add(new Potato());
		for(Crop crop : startCrops) {
			crop.reduceHarvestAge(growthBonus);
		}
		super.setCrops(startCrops);
	}
	
	MarsFarm(Farmer newFarmer) {
		super(newFarmer, "Mars");
		initializeAnimals();
		initializeCrops();
		setMoney(startCash);
		setHappinessBonus(happinessBonus);
		setGrowthBonus(growthBonus);
		setCropLimit(cropLimit);
	}
}