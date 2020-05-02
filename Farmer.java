package farmSimulator;

import java.util.ArrayList;

public class Farmer {
	
	private String name;
	private int age;
	private ArrayList<Item> items = new ArrayList<Item>();
	
	public void setName(String newName) {
		if (newName.length() > 15 || newName.length() < 3) {
			throw new IllegalArgumentException("Invalid input - farmer's name must be 3 to 15 characters long.");
		} else if (newName.matches("[a-zA-Z ]+")) {
			name = newName;
		} else {
			throw new IllegalArgumentException("Invalid input - farmers name cannot contain any numbers or special characters");
		}
	}
	
	public String getName() {
		return name;
	}
	
	public void setAge(int newAge) {
		age = newAge;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setItems(ArrayList<Item> newItems) {
		items = newItems;
	}
	
	public ArrayList<Item> getItems() {
		return items;
	}
	
	public ArrayList<FoodItem> getFoodItems() {
		ArrayList<FoodItem> foodItems = new ArrayList<FoodItem>();
		for (Item item : items) {
			if (item instanceof FoodItem) {
				foodItems.add((FoodItem) item);
			}
		}
		return foodItems;
	}
	
	public ArrayList<CropItem> getCropItems() {
		ArrayList<CropItem> cropItems = new ArrayList<CropItem>();
		for (Item item : items) {
			if (item instanceof CropItem) {
				cropItems.add((CropItem) item);
			}
		}
		return cropItems;
	}
	
	public void addItem(Item itemToAdd) {
		items.add(itemToAdd);
	}
	
	public void removeItem(Item itemToRemove) {
		items.remove(itemToRemove);
	}
	
	public void setUp(String newName, int newAge, ArrayList<Item> newItems) {
		setName(newName);
		setAge(newAge);
		setItems(newItems);
	}
}
