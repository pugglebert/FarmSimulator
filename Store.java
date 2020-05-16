package farmSimulatorGUI;

import java.util.ArrayList;

public class Store {
	
	private ArrayList<FoodItem> foodItems = new ArrayList<FoodItem>();
	private ArrayList<CropItem> cropItems = new ArrayList<CropItem>();
	private ArrayList<Animal> animals = new ArrayList<Animal>();
	private ArrayList<Crop> crops = new ArrayList<Crop>();
	
	public ArrayList<Buyable> getFoodItems() {
		ArrayList<Buyable> buyables = new ArrayList<Buyable>();
		for (int i = 0; i < foodItems.size(); i++) {
			Buyable buyableItem = (Buyable) foodItems.get(i);
			buyables.add(buyableItem);
		}
		return buyables;
	}
	
	public String[] getStoreArray(ArrayList<Buyable> buyables) {
		String[] buyArray = new String[buyables.size()];
		for (int i = 0; i < buyables.size(); i++) {
			buyArray[i] = buyables.get(i).toStringStore();
		}
		return buyArray;
	}
	
	public ArrayList<Buyable> getCropItems() {
		ArrayList<Buyable> buyables = new ArrayList<Buyable>();
		for (int i = 0; i < cropItems.size(); i++) {
			Buyable buyableItem = (Buyable) cropItems.get(i);
			buyables.add(buyableItem);
		}
		return buyables;
	}
	
	public ArrayList<Buyable> getAnimals() {
		ArrayList<Buyable> buyables = new ArrayList<Buyable>();
		for (int i = 0; i < animals.size(); i++) {
			Buyable buyableItem = (Buyable) animals.get(i);
			buyables.add(buyableItem);
		}
		return buyables;
	}
	
	public ArrayList<Buyable> getCrops() {
		ArrayList<Buyable> buyables = new ArrayList<Buyable>();
		for (int i = 0; i < crops.size(); i++) {
			Buyable buyableItem = (Buyable) crops.get(i);
			buyables.add(buyableItem);
		}
		return buyables;
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
	public Store() {
		initializeAnimals();
		initializeCrops();
		initializeFItems();
		initializeCItems();
	}
}
