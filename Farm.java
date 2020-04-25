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
	
	public String getStatus() {
		String status = "Your farm has $" + money;
		return status;
	}
	
	public void setMoney(int newMoney) {
		money = newMoney;
	}
	
	public int getMoney() {
		return money;
	}
	
	public void earnMoney(int earnings) {
		money += earnings;
	}
	
	public void spendMoney(int spendings) {
		money -= spendings;
	}
	
	public void setName(String newName) {
		name = newName;
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
	
	public void setUp(String newName, Farmer newFarmer) {
		setName(newName);
		setFarmer(newFarmer);
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