package farmSimulatorGUI;

public class Sheep extends Animal{
	private static int baseReturn = 100;
	private static int buyPrice = 50;
	private static String name = "Sheep";
	
	Sheep(){
		super(name, baseReturn, buyPrice);
	}
	
	public int getBaseReturn() {
		return baseReturn;
	}
}