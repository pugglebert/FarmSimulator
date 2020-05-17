package farmSimulatorGUI;

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
	private static int harvestAge = 10;
	/**
	 * The amount of money required to purchase the crop from the store
	 */
	private static int buyPrice = 100;
	/**
	 * The amount of money gained by harvesting the crop once fully grown
	 */
	private static int sellPrice = 100;
	/**
	 * Initializes the Crop with the specified values 
	 */
	public Barley(){
		super(cropType, harvestAge, buyPrice, sellPrice);
	}
}