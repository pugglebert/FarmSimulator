package farmSimulator;

/**
 * This class implements the Cow variety of Animal
 * 
 * @author David Frost, Ella Johnson
 */
public class Cow extends Animal{
	/**
	 * The amount of money returned by the animal per day as a base
	 */
	private static int baseReturn = 100;
	/**
	 * The amount of money required to purchase a Cow from the store
	 */
	private static int buyPrice = 200;
	/**
	 * The variety of Animal
	 */
	private static String name = "Cow";
	
	/**
	 * Initializes the Animal with the specified values
	 */
	public Cow(){
		super(name, baseReturn, buyPrice);
	}
}
