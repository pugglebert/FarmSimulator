package farmSimulator;

public class Item {
	
	private String name;
	private int price;
	
    public void setPrice(int newPrice) {
    	price = newPrice;
    }
    
    public int getPrice() {
    	return price;
    }
    
    public void setName(String newName) {
    	name = newName;
    }
    
    public String getName() {
    	return name;
    }
    
}
