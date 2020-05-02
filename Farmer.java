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
	
	public ArrayList<Item> getFoodItems() {
		ArrayList<FoodItem> foodItems = new ArrayList<FoodItem>();
		for (int counter = 0; counter < items.size(); counter++) {
			if (items.get(counter).isFoodItem()) {
				foodItems.add(items.get(counter));
			}
		}
		return foodItems;
	}
	
	public ArrayList<Item> getCropItems() {
		ArrayList<CropItem> cropItems = new ArrayList<CropItem>();
		for (int counter = 0; counter < items.size(); counter++) {
			if (items.get(counter).isCropItem()) {
				cropItems.add(items.get(counter));
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
