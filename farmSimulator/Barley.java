package farmSimulator;

/**
 * This class implements the Barley variety of Crop
 * 
 * @author David Frost, Ella Johnson
 */
public class Barley extends Crop{
	/**
	 * The variety of crop
	 */
	private static String cropType = "Barley";
	/**
	 * The number of days until the crop is fully grown
	 */
	private static int harvestAge = 4;
	/**
	 * The amount of money required to purchase the crop from the store
	 */
	private static int buyPrice = 50;
	/**
	 * The amount of money gained by harvesting the crop once fully grown
	 */
	private static int sellPrice = 90;
	/**
	 * Initializes the Crop with the specified values 
	 */
	public Barley(){
		super(cropType, harvestAge, buyPrice, sellPrice);
	}
}
