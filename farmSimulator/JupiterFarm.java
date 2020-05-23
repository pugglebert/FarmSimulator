package farmSimulator;

import java.util.ArrayList;

/**
 * This class implements the Jupiter version of the Farm
 * Each type of Farm has different starting money, crops and animals, as well certain bonuses
 * 
 * @author David Frost, Ella Johnson
 */
public class JupiterFarm extends Farm {
	
	/**
	 * Amount of money the farm starts with
	 */
	private int startCash = 500;
	
	/**
	 * Maximum number of crops that can be grown, can be increased by tending to the land
	 */
	private int cropLimit = 6;
	
	/**
	 * Extra happiness each new animal receives
	 */
	private int happinessBonus = 1;
	
	/**
	 * Boost to growth by reducing number of days required to be fully grown
	 */
	private int growthBonus = 1;
	
	/**
	 * List of animals the farm has to begin with
	 */
	private ArrayList<Animal> startAnimals = new ArrayList<Animal>();
	
	/**
	 * List of crops the farm has to begin with
	 */
	private ArrayList<Crop> startCrops = new ArrayList<Crop>();
	
	/**
	 * Adds unique animals to the starting list, and sets their happiness based on bonuses given
	 */
	public void initializeAnimals() {
		startAnimals.add(new Cow());
		startAnimals.add(new Sheep());
		for(Animal animal : startAnimals) {
			animal.increaseHappiness(happinessBonus);
		}
		super.setAnimals(startAnimals);
	}
	
	/**
	 * Adds unique crops to the starting list, and reduces their harvest based on bonuss given
	 */
	public void initializeCrops() {
		startCrops.add(new Wheat());
		startCrops.add(new Maize());
		startCrops.add(new Pumpkin());
		for(Crop crop : startCrops) {
			crop.reduceHarvestAge(growthBonus);
		}
		super.setCrops(startCrops);
	}
	
	/**
	 * Constructs the JupiterFarm using the Farm parent class
	 * @param farmer 	Farmer that works on the farm
	 */
	public JupiterFarm(Farmer farmer) {
		super(farmer, "Jupiter");
		initializeAnimals();
		initializeCrops();
		setStartCash(startCash);
		setHappinessBonus(happinessBonus);
		setGrowthBonus(growthBonus);
		setCropLimit(cropLimit);
	}
}
