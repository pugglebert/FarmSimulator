package farmSimulatorGUI;

import java.util.ArrayList;

public class JupiterFarm extends Farm {
	
	private int startCash = 500;
	private int cropLimit = 6;
	private int happinessBonus = 1;
	private int growthBonus = 1;
	private ArrayList<Animal> startAnimals = new ArrayList<Animal>();
	private ArrayList<Crop> startCrops = new ArrayList<Crop>();
	
	public void initializeAnimals() {
		startAnimals.add(new Cow());
		startAnimals.add(new Sheep());
		for(Animal animal : startAnimals) {
			animal.increaseHappiness(happinessBonus);
		}
		super.setAnimals(startAnimals);
	}

	public void initializeCrops() {
		startCrops.add(new Wheat());
		startCrops.add(new Maize());
		startCrops.add(new Pumpkin());
		for(Crop crop : startCrops) {
			crop.reduceHarvestAge(growthBonus);
		}
		super.setCrops(startCrops);
	}
	
	JupiterFarm(Farmer newFarmer) {
		super(newFarmer, "Jupiter");
		initializeAnimals();
		initializeCrops();
		setMoney(startCash);
		setHappinessBonus(happinessBonus);
		setGrowthBonus(growthBonus);
		setCropLimit(cropLimit);
	}
}