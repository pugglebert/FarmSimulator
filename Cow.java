package farmSimulatorGUI;

public class Cow extends Animal{
	private static int baseReturn = 100;
	private static int buyPrice = 50;
	private static String name = "Cow";
	
	public Cow(){
		super(name, baseReturn, buyPrice);
	}
	
	public int getBaseReturn() {
		return baseReturn;
	}
}