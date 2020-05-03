package farmSimulator;

abstract class Item implements Buyable {
	
	private String name;
	private int price;
	
	public abstract boolean isFoodItem();
	public abstract boolean isCropItem();
	public abstract String toString();
	public abstract String toStringStore();
	
	
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
