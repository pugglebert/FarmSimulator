package farmSimulatorGUI;

/**
 * This class implements the Pumpkin variety of Crop
 * 
 * @author David Frost, Ella Johnson
 */
public class Pumpkin extends Crop{
	/**
	 * The variety of crop
	 */
	private static String cropType = "Pumpkin";
	/**
	 * The number of days until the crop is fully grown
	 */
	private static int harvestAge = 3;
	/**
	 * The amount of money required to purchase the crop from the store
	 */
	private static int buyPrice = 70;
	/**
	 * The amount of money gained by harvesting the crop once fully grown
	 */
	private static int sellPrice = 100;
	/**
	 * Initializes the Crop with the specified values 
	 */
	public Pumpkin(){
		super(cropType, harvestAge, buyPrice, sellPrice);
	}
}