package farmSimulatorGUI;

import java.util.ArrayList;

public class Farm {
	
	private String name;
	private int money;
	private Farmer farmer;
	private ArrayList<Animal> animals;
	private ArrayList<Crop> crops;
	private int cropLimit;
	private int growthBonus;
	private int happinessBonus;
	private int startCash;
	private String planetType;
	
	public Farm(Farmer newFarmer, String newPlanetType) {
		farmer = newFarmer;
		planetType = newPlanetType;
	}
	
	public Farmer getFarmer() {
		return farmer;
	}
	
	public String getPlanetType() {
		return planetType;
	}
	
	public void setMoney(int newMoney) {
		if (newMoney >= 0) {
			money = newMoney;
			startCash = money;
		} else {
			throw new IllegalArgumentException("money < 0");
		}
	}
	
	public int getMoney() {
		return money;
	}
	
	public int getStartCash(){
		return startCash;
	}
	
	public void earnMoney(int earnings) {
		if (earnings >= 0) {
			money += earnings;
		} else {
			throw new IllegalArgumentException("earnings < 0");
		}
	}
	
	public void spendMoney(int spendings) {
		if (spendings >= 0 && (money - spendings >= 0)) {
			money -= spendings;
		} else if (spendings < 0) {
			throw new IllegalArgumentException("spendings < 0");
		} else {
			throw new IllegalArgumentException("insufficient funds");
		}
	}
	
	public void setName(String newName) {
		if (newName.length() < 3) {
			throw new IllegalArgumentException("Name must be at least 3 chars long.");
		} else if (newName.length() > 15) {
			throw new IllegalArgumentException("Name must be at most 15 chars long.");
		} else if (newName.matches("[a-zA-Z ]+")){
		    name = newName;
		} else {
			throw new IllegalArgumentException("Name must not contain numbers or special characters.");
		}
	}
	
	public String getName() {
		return name;
	}
	
	public void setAnimals(ArrayList<Animal> newAnimals) {
		animals = newAnimals;
	}
	
	public ArrayList<Animal> getAnimals() {
		return animals;
	}
	
	public void addAnimal(Animal animalToAdd) {
		animals.add(animalToAdd);
	}
	
	public void removeAnimal(Animal animalToRemove) {
		animals.remove(animalToRemove);
	}
	
	public void setCrops(ArrayList<Crop> newCrops) {
		crops = newCrops;
	}
	
	public ArrayList<Crop> getCrops() {
		return crops;
	}
	
	public void addCrop(Crop cropToAdd) {
		crops.add(cropToAdd);
	}
	
	public void removeCrop(Crop cropToRemove) {
		crops.remove(cropToRemove);
	}
	
	public void setCropLimit(int newCropLimit) {
		if (newCropLimit < 0) {
			throw new IllegalArgumentException("Crop limit cannot be a negative number.");
		} else {
			cropLimit = newCropLimit;
			if (cropLimit > 16) { cropLimit = 16; }
		}
	}
	
	public int getCropLimit() {
		return cropLimit;
	}
	
	public void setGrowthBonus(int newGrowthBonus) {
		if (newGrowthBonus < 0) {
			throw new IllegalArgumentException("Growth bonus must be a positive integer.");
		} else {
			growthBonus = newGrowthBonus;
		}
	}
	
	public int getGrowthBonus() {
		return growthBonus;
	}
	
    public void setHappinessBonus(int newHappinessBonus) {
    	if (newHappinessBonus < 0) {
    		throw new IllegalArgumentException("Happiness bonus must be a positive integer.");
    	} else {
 		happinessBonus = newHappinessBonus;
    	}
	}
	public int getHappinessBonus() {
		return happinessBonus;
	}
	
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
				if (merchandise instanceof Cow) {
					Cow cow = new Cow();
					cow.increaseHappiness(getHappinessBonus());
					addAnimal(cow);
				}
				else if (merchandise instanceof Sheep) {
					Sheep sheep = new Sheep();
					sheep.increaseHappiness(getHappinessBonus());
					addAnimal(sheep);
				}
				else if (merchandise instanceof Chicken) {
					Chicken chicken = new Chicken();
					chicken.increaseHappiness(getHappinessBonus());
					addAnimal(chicken);
				}
			} else if (merchandise instanceof Crop) {
				if (crops.size() >= cropLimit) {
					money += price;
					throw new IllegalArgumentException("Your farm already has the maximum number of crops.\n"
							+ "Tend the land to get space to grow more.");
				} else {
					Crop merchCrop = (Crop) merchandise;
					merchCrop.setBaseHarvestAge(getGrowthBonus());
					addCrop(new Crop(merchCrop));
				}
			}
		} else {
			throw new IllegalArgumentException("Your farmer does not have enough money to buy this.");
		}
	}
	
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