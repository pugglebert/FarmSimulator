package farmSimulator;

/**
 * This class implements the Maize variety of Crop
 * 
 * @author David Frost, Ella Johnson
 */
public class Maize extends Crop{
	/**
	 * The variety of crop
	 */
	private static String cropType = "Maize";
	/**
	 * The number of days until the crop is fully grown
	 */
	private static int harvestAge = 4;
	/**
	 * The amount of money required to purchase the crop from the store
	 */
	private static int buyPrice = 40;
	/**
	 * The amount of money gained by harvesting the crop once fully grown
	 */
	private static int sellPrice = 70;
	/**
	 * Initializes the Crop with the specified values 
	 */
	public Maize(){
		super(cropType, harvestAge, buyPrice, sellPrice);
	}
}