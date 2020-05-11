package farmSimulator;

abstract class Item implements Buyable {
	
	private String name;
	private int price;
	private int inventory_count = 0;
	
	public abstract boolean isFoodItem();
	public abstract boolean isCropItem();
	public abstract String toString();
	public abstract String toStringStore();
	
	
	public void addToInventory() {
		inventory_count++;
	}
	
	public void removeFromInventory() {
		inventory_count--;
	}
	
	public int getInventoryCount() {
		return inventory_count;
	}
	
    public void setPrice(int newPrice) {
    	price = newPrice;
    }
    
    public int getBuyPrice() {
    	return price;
    }
    
    public void setName(String newName) {
    	name = newName;
    }
    
    public String getName() {
    	return name;
    }
    
}
