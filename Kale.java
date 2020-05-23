package farmSimulator;

/**
 * This class implements the Kale variety of Crop
 * 
 * @author David Frost, Ella Johnson
 */
public class Kale extends Crop{
	/**
	 * The variety of crop
	 */
	private static String cropType = "Kale";
	/**
	 * The number of days until the crop is fully grown
	 */
	private static int harvestAge = 3;
	/**
	 * The amount of money required to purchase the crop from the store
	 */
	private static int buyPrice = 80;
	/**
	 * The amount of money gained by harvesting the crop once fully grown
	 */
	private static int sellPrice = 120;
	/**
	 * Initializes the Crop with the specified values 
	 */
	public Kale(){
		super(cropType, harvestAge, buyPrice, sellPrice);
	}
}