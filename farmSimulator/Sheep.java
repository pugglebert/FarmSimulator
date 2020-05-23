package farmSimulator;

/**
 * This class implements the Sheep variety of Animal
 * 
 * @author David Frost, Ella Johnson
 */
public class Sheep extends Animal{
	/**
	 * The amount of money returned by the animal per day as a base
	 */
	private static int baseReturn = 70;
	/**
	 * The amount of money required to purchase a Sheep from the store
	 */
	private static int buyPrice = 150;
	/**
	 * The variety of Animal
	 */
	private static String name = "Sheep";
	
	/**
	 * Initializes the Animal with the specified values
	 */
	public Sheep(){
		super(name, baseReturn, buyPrice);
	}
}
