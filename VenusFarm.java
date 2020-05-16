package farmSimulatorGUI;

import java.util.ArrayList;

public class VenusFarm extends Farm {
	
	private int startCash = 1500;
	private int cropLimit = 10;
	private int happinessBonus = 0;
	private int growthBonus = 0;
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
		startCrops.add(new Pumpkin());
		startCrops.add(new Kale());
		for(Crop crop : startCrops) {
			crop.setBaseHarvestAge(growthBonus);
		}
		super.setCrops(startCrops);
	}
	
	public VenusFarm(Farmer newFarmer) {
		super(newFarmer, "Venus");
		initializeAnimals();
		initializeCrops();
		setMoney(startCash);
		setHappinessBonus(happinessBonus);
		setGrowthBonus(growthBonus);
		setCropLimit(cropLimit);
	}
}