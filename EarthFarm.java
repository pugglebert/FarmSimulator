package farmSimulatorGUI;

import java.util.ArrayList;

public class EarthFarm extends Farm {
	
	private int startCash = 1000;
	private int cropLimit = 6;
	private int happinessBonus = 1;
	private int growthBonus = 0;
	private ArrayList<Animal> startAnimals = new ArrayList<Animal>();
	private ArrayList<Crop> startCrops = new ArrayList<Crop>();
	
	public void initializeAnimals() {
		startAnimals.add(new Chicken());
		for(Animal animal : startAnimals) {
			animal.setBaseHappiness(happinessBonus);
		}
		super.setAnimals(startAnimals);
	}
	
	public void initializeCrops() {
		startCrops.add(new Wheat());
		startCrops.add(new Barley());
		for(Crop crop : startCrops) {
			crop.setBaseHarvestAge(growthBonus);
		}
		super.setCrops(startCrops);
	}
	
	public EarthFarm(Farmer newFarmer) {
		super(newFarmer);
		setMoney(startCash);
		setGrowthBonus(growthBonus);
		setHappinessBonus(happinessBonus);
		setCropLimit(cropLimit);
		initializeAnimals();
		initializeCrops();
	}

}