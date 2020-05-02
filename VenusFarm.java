package farmSimulator;

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
		super.setAnimals(startAnimals);
	}

	public void initializeCrops() {
		startCrops.add(new Pumpkin());
		startCrops.add(new Kale());
		super.setCrops(startCrops);
	}
	
	VenusFarm(Farmer newFarmer) {
		super(newFarmer);
		initializeAnimals();
		initializeCrops();
		setMoney(startCash);
		setHappinessBonus(happinessBonus);
		setGrowthBonus(growthBonus);
		setCropLimit(cropLimit);
	}

}
