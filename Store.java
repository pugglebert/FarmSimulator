package farmSimulatorGUI;

import java.util.ArrayList;

/**
 * The store class provides lists Buyables which can be purchased using the buy method
 * in the farm class.
 * 
 * @author David Frost, Ella Johnson
 *
 */

public class Store {
	
	/**
	 * List of food items available for purchase
	 */
	private ArrayList<Buyable> foodItems = new ArrayList<Buyable>();
	
	/**
	 * List of crop items available for purchase
	 */
	private ArrayList<Buyable> cropItems = new ArrayList<Buyable>();
	
	/**
	 * List of animals available for purchase
	 */
	private ArrayList<Buyable> animals = new ArrayList<Buyable>();
	
	/**
	 * List of crops available for purchase
	 */
	private ArrayList<Buyable> crops = new ArrayList<Buyable>();
	
	/**
	 * Converts arraylist of buyables to string representation
	 * @param buyables one of the attributes of the store
	 * @return array of string representations of buyables
	 */
	public String[] getStoreArray(ArrayList<Buyable> buyables) {
		String[] buyArray = new String[buyables.size()];
		for (int i = 0; i < buyables.size(); i++) {
			buyArray[i] = buyables.get(i).toStringStore();
		}
		return buyArray;
	}	
	
	/**
	 * Returns ArrayList of food items that can be purchased cast as Buyable
	 * @return list of food items for purchase
	 */
	public ArrayList<Buyable> getFoodItems() {
		return foodItems;
	}
	
	/**
	 * Returns ArrayList of crop items that can be purchased cast as Buyable
	 * @return list of crop items for purchase
	 */
	public ArrayList<Buyable> getCropItems() {
		return cropItems;
	}
	
	/**
	 * Returns ArrayList of animals that can be purchased cast as Buyable
	 * @return list of animals for purchase
	 */
	public ArrayList<Buyable> getAnimals() {
		return animals;
	}
	
	/**
	 * Returns ArrayList of crops that can be purchased cast as Buyable
	 * @return list of crops for purchase
	 */
	public ArrayList<Buyable> getCrops() {
		return crops;
	}
	
	/**
	 * Called by constructor, adds Animal objects to animals attribute
	 */
	private void initializeAnimals() {
		animals.add(new Chicken());
		animals.add(new Cow());
		animals.add(new Sheep());
	}
	
	/**
	 * Called by constructor, adds Crop objects to crops attribute
	 */
	private void initializeCrops() {
		crops.add(new Barley());
		crops.add(new Kale());
		crops.add(new Maize());
		crops.add(new Potato());
		crops.add(new Pumpkin());
		crops.add(new Wheat());
	}
	
	/**
	 * Called by constructor, adds FoodItem objects to foodItems attribute
	 */
	private void initializeFItems() {
		foodItems.add(new FoodItem("Small treat", 100, 1));
		foodItems.add(new FoodItem("Big treat", 250, 3));
		foodItems.add(new FoodItem("Super treat", 400, 6));
	}
	
	/**
	 * Called by constructor, adds CropItem objects to cropItems attribute
	 */
	private void initializeCItems() {
		cropItems.add(new CropItem("Small fertilizer", 200, 1));
		cropItems.add(new CropItem("Big fertilizer", 350, 2));
		cropItems.add(new CropItem("Super fertilizer", 500, 3));
	}
	
	/**
	 * Store constructor, adds objects to empty ArrayList for each attribute
	 */
	public Store() {
		initializeAnimals();
		initializeCrops();
		initializeFItems();
		initializeCItems();
	}
}