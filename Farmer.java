package farmSimulator;

import java.util.ArrayList;

public class Farmer {
	
	private String name;
	private int age;
	private ArrayList<Item> items;
	
	public void setName(String newName) {
		name = newName;
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
