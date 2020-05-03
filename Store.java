package farmSimulator;

import java.util.ArrayList;

public class Store {
	
	private ArrayList<FoodItem> foodItems = new ArrayList<FoodItem>();
	private ArrayList<CropItem> cropItems = new ArrayList<CropItem>();
	private ArrayList<Animal> animals = new ArrayList<Animal>();
	private ArrayList<Crop> crops = new ArrayList<Crop>();
	
	public ArrayList<FoodItem> getFoodItems() {
		return foodItems;
	}
	
	public ArrayList<CropItem> getCropItems() {
		return cropItems;
	}
	
	public ArrayList<Animal> getAnimals() {
		return animals;
	}
	
	public ArrayList<Crop> getCrops() {
		return crops;
	}
	
	public String getFoodItemString() {
		String foodItemString = "The following food items are available for purchase:";
		for (int counter = 1; counter <= foodItems.size(); counter++) {
			foodItemString += "\n" + Integer.toString(counter) + ") " + foodItems.get(counter - 1).toStringStore();
		}
		return foodItemString;
	}
	
	public String getCropItemString() {
		String foodItemString = "The following crop items are available for purchase:";
		for (int counter = 1; counter <= cropItems.size(); counter++) {
			foodItemString += "\n" + Integer.toString(counter) + ") " + cropItems.get(counter - 1).toStringStore();
		}
		return foodItemString;
	}
	
	public String getAnimalString() {
		String animalString = "The following animals are available for purchase:";
		for (int counter = 1; counter <= animals.size(); counter++) {
			animalString += "\n" + Integer.toString(counter) + ") " + animals.get(counter - 1).toStringStore();
		}
		return animalString;
	}
	
	public String getCropString() {
		String cropString = "The following crops are available for purchase:";
		for (int counter = 1; counter <= crops.size(); counter++) {
			cropString += "\n" + Integer.toString(counter) + ") " + crops.get(counter - 1).toStringStore();
		}
		return cropString;
	}
	
	private void initializeAnimals() {
		animals.add(new Chicken());
		animals.add(new Cow());
		animals.add(new Sheep());
	}
	
	private void initializeCrops() {
		crops.add(new Barley());
		crops.add(new Kale());
		crops.add(new Maize());
		crops.add(new Potato());
		crops.add(new Pumpkin());
		crops.add(new Wheat());
	}
	
	private void initializeFItems() {
		foodItems.add(new FoodItem("Small treat", 100, 1));
		foodItems.add(new FoodItem("Big treat", 250, 3));
		foodItems.add(new FoodItem("Super treat", 400, 6));
	}
	
	private void initializeCItems() {
		cropItems.add(new CropItem("Small fertilizer", 200, 1));
		cropItems.add(new CropItem("Big fertilizer", 350, 2));
		cropItems.add(new CropItem("Super fertilizer", 500, 3));
	}
	Store() {
		initializeAnimals();
		initializeCrops();
		initializeFItems();
		initializeCItems();
	}
}
