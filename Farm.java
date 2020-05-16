package farmSimulatorGUI;

import java.util.ArrayList;

/**
 * This calss implements a Farm which can be one of four different types; Earth Farm, Mars Farm, Venus Farm, or Jupiter Farm
 * The Farm contains information about all the crops and animals and how much money is available
 * 
 * @author David Frost, Ella Johnson
 */
public class Farm {
	
	/**
	 * Name of the Farm
	 */
	private String name;
	
	/**
	 * Amount of money avaliable
	 */
	private int money;
	
	/**
	 * The farmer that is working on the Farm
	 */
	private Farmer farmer;
	
	/**
	 * List of all the animals currently on the farm
	 */
	private ArrayList<Animal> animals;
	
	/**
	 * List of all the crops currently growing on the farm
	 */
	private ArrayList<Crop> crops;
	
	/**
	 * Maximum number of crops that can be grown. Can be increased by tending to the farm land
	 */
	private int cropLimit;
	
	/**
	 * Amount of days the harvest age of Crops are reduced by
	 */
	private int growthBonus;
	
	/**
	 * Amount of bonus happiness each new animal gains
	 */
	private int happinessBonus;
	
	/**
	 * Amount of money the farm starts out with
	 */
	private int startCash;
	
	/**
	 * Name of the planet the farm is on
	 */
	private String planetType;
	
	/**
	 * Constructs the Farm on the specified planet, called by one of the four child classes
	 * @param newFarmer 
	 * @param newPlanetType		Name of the Planet the Farm is on
	 */
	Farm(Farmer newFarmer, String newPlanetType) {
		farmer = newFarmer;
		planetType = newPlanetType;
	}
	
	/**
	 * Returns the name of the planet the farm is on
	 * @return		The name of the planet
	 */
	public String getPlanetType() {
		return planetType;
	}
	
	/**
	 * Sets the amount of money the farm has
	 * @param newMoney		Amount of money the farm will have
	 */
	public void setMoney(int newMoney) {
		startCash = newMoney;
		money = newMoney;
	}
	
	/**
	 * Returns the amount of money the farm has
	 * @return		Amount of money the farm has
	 */
	public int getMoney() {
		return money;
	}
	
	/**
	 * Returns the amount of money the farm started out with
	 * @return		Starting amount of money
	 */
	public int getStartCash(){
		return startCash;
	}
	
	/**
	 * Increases the farm's money by the specified amount
	 * @param earnings		Amount of money the farm gains
	 */
	public void earnMoney(int earnings) {
		money += earnings;
	}
	
	public void setName(String newName) {
		name = newName;
	}
	
	/**
	 * Returns the name of the Farm
	 * @return The Farm's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the animals the farm contains
	 * @param newAnimals 		List of animals to set up
	 */
	public void setAnimals(ArrayList<Animal> newAnimals) {
		animals = newAnimals;
	}
	
	/**
	 * Returns the list of animals that the farm contains
	 * @return		List of all animals on the Farm
	 */
	public ArrayList<Animal> getAnimals() {
		return animals;
	}
	
	/**
	 * Adds an animal to the list of animals
	 * @param animalToAdd		New animal added to the list
	 */
	public void addAnimal(Animal animalToAdd) {
		animals.add(animalToAdd);
	}
	
	/**
	 * Removes an animal from the list of animals
	 * @param animalToRemove 	Animal to be removed from the list
	 */
	public void removeAnimal(Animal animalToRemove) {
		animals.remove(animalToRemove);
	}
	
	/**
	 * Sets the crops the farm contains
	 * @param newCrops		List of crops to set up
	 */
	public void setCrops(ArrayList<Crop> newCrops) {
		crops = newCrops;
	}
	
	/**
	 * Returns the list of all the crops that the farm contains
	 * @return		List of all crops on the farm
	 */
	public ArrayList<Crop> getCrops() {
		return crops;
	}
	
	/**
	 * Adds a crop to the list of crops
	 * @param cropToAdd		New crop added to the list
	 */
	public void addCrop(Crop cropToAdd) {
		crops.add(cropToAdd);
	}
	
	/**
	 * Removes a crop from the list of crops
	 * @param cropToRemove		Crop to be removed from the list
	 */
	public void removeCrop(Crop cropToRemove) {
		crops.remove(cropToRemove);
	}
	
	/**
	 * Sets the crop limit
	 * @param newCropLimit		Amount of crops that can be grown at one time
	 */
	public void setCropLimit(int newCropLimit) {
		cropLimit = newCropLimit;
		if (cropLimit > 16) { cropLimit = 16; }
	}
	
	/**
	 * Increases the amount of crops that can be grown. Maximum of 16
	 */
	public void increaseCropLimit() {
		if (cropLimit < 16) { cropLimit++; }
	}
	
	/**
	 * Rertuns the amount of crops that can be grown at one time	
	 * @return		Amount of crops that can be grown
	 */
	public int getCropLimit() {
		return cropLimit;
	}
	
	/**
	 * Sets the growth bonus that each new crops will receive
	 * @param newGrowthBonus		Amount of days that each crop's harvest age will decrease by
	 */
	public void setGrowthBonus(int newGrowthBonus) {
		growthBonus = newGrowthBonus;
	}
	
	/**
	 * Returns the growth bonus that each crop receives
	 * @return		Amount of days that each crop's harvest age is reduced by
	 */
	public int getGrowthBonus() {
		return growthBonus;
	}
	
	/**
	 * Sets the amount of happiness each new animal gains
	 * @param newHappinessBonus		Amount of health each animal gains
	 */
    public void setHappinessBonus(int newHappinessBonus) {
		happinessBonus = newHappinessBonus;
	}
    
    /**
     * Returns the amount of happiness each new animal gains
     * @return		The amount of extra happiness each animal gains
     */
	public int getHappinessBonus() {
		return happinessBonus;
	}
	
	/**
	 * Buys an item using the Buyable interface
	 * Determines the instance of the bought item and casts it as the desired type.
	 * Bought items are added to the inventory, crops are added to the crop list, and animals are added to the animal list
	 * @param merchandise		The bought object, could be an Item, Animal, or Crop
	 */
	public void buy(Buyable merchandise) {
		int price = merchandise.getBuyPrice();
		if (money >= price) {
			money -= price;
			if (merchandise instanceof Item) {
				if(!farmer.getItems().contains(merchandise)) {
					farmer.addItem((Item) merchandise);
				}
				((Item) merchandise).addToInventory();
			}
			else if (merchandise instanceof Animal) {	
				Animal animal = null;
				if (merchandise instanceof Cow) {
					animal = new Cow();
				}
				else if (merchandise instanceof Sheep) {
					animal = new Sheep();
				}
				else if (merchandise instanceof Chicken) {
					animal = new Chicken();					
				}
				animal.increaseHappiness(getHappinessBonus());
				addAnimal(animal);
			} 
			else if (merchandise instanceof Crop){
				if (crops.size() >= cropLimit) {
					throw new IllegalArgumentException("Your farm already has the maximum number of crops.\n"
							+ "Tend the land to get space to grow more.");
				} else if (merchandise instanceof Crop){
					String cropType = ((Crop) merchandise).getCropType();
					Crop crop = null;
					switch(cropType) {
					case "Barley":
						crop = new Barley();
						break;
					case "Kale":
						crop = new Kale();
						break;
					case "Maize":
						crop = new Maize();
						break;
					case "Potato":
						crop = new Potato();
						break;
					case "Pumpkin":
						crop = new Pumpkin();
						break;
					case "Wheat":
						crop = new Wheat();
						break;
					}
					crop.reduceHarvestAge(getGrowthBonus());
					addCrop(crop);
				}	
			} 
		} else {
			throw new IllegalArgumentException("Your farmer does not have enough money to buy this.");
		}
	}
	
	/**
	 * Uses a Food Item and increases all animal's health by the given amount, removes the item from the inventory after use
	 * @param chosenItem		Food Item being used
	 */
	public void useItem(FoodItem chosenItem) {
		int healthBonus = chosenItem.getHealthGiven();
		chosenItem.removeFromInventory();
		if (chosenItem.getInventoryCount() == 0) {
			farmer.removeItem(chosenItem);
		}
		
		for (Animal animal : animals) {
			animal.increaseHealth(healthBonus);
		}
	}
	
	/**
	 * Uses a Crop item and increases all chosen crop's age by the given amount, removes the item from the inventory after use
	 * @param chosenItem		Crop item being used
	 * @param cropType			Variety of crop that is being boosted
	 */
	public void useItem(CropItem chosenItem, String cropType) {
		growthBonus = chosenItem.getGrowthBonus();
		chosenItem.removeFromInventory();
		if (chosenItem.getInventoryCount() == 0) {
			farmer.removeItem(chosenItem);
		}
		for (Crop crop : crops) {
			if (cropType == crop.getCropType()) {
				crop.boostGrowth(growthBonus);
			}
		}
	}
}