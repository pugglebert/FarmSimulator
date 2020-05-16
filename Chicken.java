package farmSimulatorGUI;

/**
 * This class implements the Chicken variety of Animal
 * 
 * @author David Frost, Ella Johnson
 */
public class Chicken extends Animal{
	/**
	 * The amount of money returned by the animal per day as a base
	 */
	private static int baseReturn = 100;
	/**
	 * The amount of money required to purchase a Chicken from the store
	 */
	private static int buyPrice = 50;
	/**
	 * The variety of Animal
	 */
	private static String name = "Chicken";
	
	/**
	 * Initializes the Animal with the specified values
	 */
	Chicken(){
		super(name, baseReturn, buyPrice);
	}
}