package farmSimulatorGUI;

import java.util.ArrayList;

/**
 * This class implements a Farmer. The Farmer owns a farm, money and items, and can be
 * customized with a name and age
 * @author David Frost, Ella Johnson
 *
 */
public class Farmer {
	
	/**
	 * Name of the farmer
	 */
	private String name;
	
	/**
	 * The farmer's age
	 */
	private int age;
	
	/**
	 * An ArrayList of all the items the Farmer owns
	 */
	private ArrayList<Item> items = new ArrayList<Item>();
	
	/**
	 * Change Farmer's name
	 * @param newName string to change name to
	 */
	public void setName(String newName) {
		name = newName;
	}
	
	/**
	 * Returns Farmer's name
	 * @return Farmer's name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Change Farmer's age to new value
	 * @param newAge value to change name to
	 */
	public void setAge(int newAge) {
		age = newAge;
	}
	
	/**
	 * Return's Farmer's age
	 * @return Farmer's age
	 */
	public int getAge() {
		return age;
	}
	
	/**
	 * Sets list of Items in Farmer's possession to input
	 * @param newItems ArrayList of Items to be owned by Farmer
	 */
	public void setItems(ArrayList<Item> newItems) {
		items = newItems;
	}
	
	/**
	 * Returns list Items in Farmer's possession
	 * @return List of Items Farmer owns
	 */
	public ArrayList<Item> getItems() {
		return items;
	}
	
	/**
	 * Returns list of Items owned by Farmer which are of class FoodItem
	 * @return List of FoodItems owned by Farmer
	 */
	public ArrayList<FoodItem> getFoodItems() {
		ArrayList<FoodItem> foodItems = new ArrayList<FoodItem>();
		for (Item item : items) {
			if (item instanceof FoodItem) {
				foodItems.add((FoodItem) item);
			}
		}
		return foodItems;
	}
	
	/**
	 * Returns list of Items owned by Farmer which are of class CropItem
	 * @return List of CropItems owned by Farmer
	 */
	public ArrayList<CropItem> getCropItems() {
		ArrayList<CropItem> cropItems = new ArrayList<CropItem>();
		for (Item item : items) {
			if (item instanceof CropItem) {
				cropItems.add((CropItem) item);
			}
		}
		return cropItems;
	}
	
	/**
	 * Add an item which the Farmer does not already own to their list of Items
	 * @param itemToAdd Item to be added to list
	 */
	public void addItem(Item itemToAdd) {
		items.add(itemToAdd);
	}
	
	/**
	 * Remove an Item which the farmer owns 0 of from their list of Items
	 * @param itemToRemove Item to be removed from list
	 */
	public void removeItem(Item itemToRemove) {
		items.remove(itemToRemove);
	}
}
