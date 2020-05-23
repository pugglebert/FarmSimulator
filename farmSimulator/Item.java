package farmSimulator;
/**
 * The abstract class Item can be bought and stored in a farmer's inventory
 * Its subclasses foodItem and cropItem are used to provide bonuses to crops
 * and animals respectively
 * @author David Frost, Ella Johnson
 *
 */
public abstract class Item implements Buyable {
	
	/**
	 * Items name used as part of string representation
	 */
	private String name;
	
	/**
	 * Price for which Item can be bought
	 */
	private int price;
	
	/**
	 * Number of a particular type of item which farmer has in their possession
	 */
	private int inventory_count = 0;
	
	/**
	 * String representation used in inventory screen
	 */
	public abstract String toString();
	
	/**
	 * String representation used in store screen
	 */
	public abstract String toStringStore();
	
	/**
	 * Increase amount of an Item which the farmer owns by 1
	 */
	public void addToInventory() {
		inventory_count++;
	}
	
	/**
	 * Decreases amount of the Item that the farmer owns by 1
	 */
	public void removeFromInventory() {
		if (inventory_count <= 0) {
			inventory_count = 0;
		} else {
			inventory_count--;
		}
	}
	
	/**
	 * Returns how many of a particular item are owned
	 * @return number of specific Item owned by farmer
	 */
	public int getInventoryCount() {
		return inventory_count;
	}
	
	/**
	 * Set price Item can be bought for to new value
	 * @param newPrice price Item can be bought for
	 */
    public void setPrice(int newPrice) {
    	if (newPrice < 0) {
    		throw new IllegalArgumentException("Price cannot be a negative number");
    	} else {
    		price = newPrice;
    	}
    }
    
    /**
     * Returns price Item can be bought for
     * @return price Item can be bought for
     */
    public int getBuyPrice() {
    	return price;
    }
    
    /**
     * Sets name of Item to new string
     * @param newName string to set name to
     */
    public void setName(String newName) {
    	if (newName.trim().length() > 0) {
    		name = newName;
    	} else {
    		throw new IllegalArgumentException("Item name must contain at least one non-whitespace character");
    	}
    }
    
    /**
     * Returns name of Item
     * @return name of Item
     */
    public String getName() {
    	return name;
    }
}
