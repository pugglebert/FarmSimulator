package farmSimulator;

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
	
	Farm(Farmer newFarmer) {
		farmer = newFarmer;
	}
	
	public String getStatus() {
		String status = "Your farm has $" + money;
		return status;
	}
	
	public String getCropStatus() {
		String status = " ";
		return status;
	}
	
	public void setMoney(int newMoney) {
		if (money >= 0) {
			money = newMoney;
		} else {
			throw new IllegalArgumentException("money < 0");
		}
	}
	
	public int getMoney() {
		return money;
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
	
	public void setFarmer(Farmer newFarmer) {
		farmer = newFarmer;
	}
	
	public Farmer getFarmer() {
		return farmer;
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
		cropLimit = newCropLimit;
	}
	
	public int getCropLimit() {
		return cropLimit;
	}
	
	public void setGrowthBonus(int newGrowthBonus) {
		growthBonus = newGrowthBonus;
	}
	
	public int getGrowthBonus() {
		return growthBonus;
	}
	
	public void setHappinessBonus(int newHappinessBonus) {
		happinessBonus = newHappinessBonus;
	}
	
	public int getHappinessBonus() {
		return happinessBonus;
	}
}