package farmSimulator;

/**
 * This class implements the Chicken variety of Animal
 * 
 * @author David Frost, Ella Johnson
 */
public class Chicken extends Animal{
	/**
	 * The amount of money returned by the animal per day as a base
	 */
	private static int baseReturn = 30;
	/**
	 * The amount of money required to purchase a Chicken from the store
	 */
	private static int buyPrice = 100;
	/**
	 * The variety of Animal
	 */
	private static String name = "Chicken";
	
	/**
	 * Initializes the Animal with the specified values
	 */
	public Chicken(){
		super(name, baseReturn, buyPrice);
	}
}