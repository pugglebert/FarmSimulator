package farmSimulatorGUI;

/**
 * Interface Buyable allows objects which implement it to be displayed
 * and bought in store
 * @author David Frost, Ella Johnson
 *
 */
public interface Buyable {
	
	/**
	 * Return price for which object can be purchased
	 * @return purchase price
	 */
	public int getBuyPrice();
	
	/**
	 * Returns string of object description and price to be displayed
	 * in store screen
	 * @return String of object description and price
	 */
	public String toStringStore();
	
}
