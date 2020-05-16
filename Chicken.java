package farmSimulatorGUI;

public class Chicken extends Animal{
	private static int baseReturn = 100;
	private static int buyPrice = 50;
	private static String name = "Chicken";
	
	public Chicken(){
		super(name, baseReturn, buyPrice);
	}
	
	public int getBaseReturn() {
		return baseReturn;
	}
}