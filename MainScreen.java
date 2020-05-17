package farmSimulatorGUI;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JOptionPane;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class MainScreen {

	private JFrame frmFarmSimulator;
	private ArrayList<JLabel> cropSpaces = new ArrayList<JLabel>();
	private GameEnvironment game;
	private JButton nextDayButton;
	private JLabel dayLabel;
	private JLabel moneyLabel;
	private JLabel actionLabel;
	private JLabel cowCountLabel;
	private JLabel sheepCountLabel;
	private JLabel chickenCountLabel;
	
	/**
	 * Create the application.
	 */
	public MainScreen(GameEnvironment newGame) {
		game = newGame;
		initialize();
		updateCropDisplay();
		updateAnimalDisplay();
		frmFarmSimulator.setVisible(true);
	}
	
	public void closeWindow() {
		frmFarmSimulator.dispose();
	}
	
	public void finishedWindow() {
		game.closeMainScreen(this);
	}
	
	public void launchStoreWindow() {
		frmFarmSimulator.setVisible(false);
		StoreScreen storeWindow = new StoreScreen(game);
	}
	
	public void closeStoreWindow(StoreScreen storeWindow) {
		storeWindow.closeWindow();
		frmFarmSimulator.setVisible(true);
	}
	
	public void launchInventoryWindow() {
		frmFarmSimulator.setVisible(false);
		InventoryScreen inventoryWindow = new InventoryScreen(game);
	}
	
	public void closeInventoryWindow(InventoryScreen inventoryWindow) {
		inventoryWindow.closeWindow();
		frmFarmSimulator.setVisible(true);
	}
	
	public void launchAnimalWindow() {
		frmFarmSimulator.setVisible(false);
		AnimalStatusScreen animalWindow = new AnimalStatusScreen(game);	
	}
	
	public void closeAnimalWindow(AnimalStatusScreen animalWindow) {
		animalWindow.closeWindow();
		frmFarmSimulator.setVisible(true);
	}
	
	public void useAction() {
		game.useAction();
		actionLabel.setText(Integer.toString(game.getRemainingActions()) + " actions remaining");
	}
	
	public void newDay() {
		if (game.getCurrentDay() == game.getTotalDays()) {
			nextDayButton.setText("End Game");
			for (ActionListener l : nextDayButton.getActionListeners()) {
				nextDayButton.removeActionListener(l);
			}
			nextDayButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					finishedWindow();
				}
			});
		}
		game.resetRemainingActions();
		updateCropDisplay();
		updateAnimalDisplay();
		dayLabel.setText("Day " + Integer.toString(game.getCurrentDay()) + "/" + Integer.toString(game.getTotalDays()));
		moneyLabel.setText("You have $" + game.getFarm().getMoney());
		actionLabel.setText(Integer.toString(game.getRemainingActions()) + " actions remaining");
	}
	
	public void updateCropDisplay() {
		ArrayList<Crop> crops = game.getFarm().getCrops();
		for (int counter = crops.size() - 1; counter >= 0; counter --) {
			JLabel space = cropSpaces.get(counter);
			Crop crop = crops.get(counter);
			int daysUntilHarvest = crop.getHarvestAge() - crop.getAge();
			if (crop.canHarvest()) {
				space.setText(crop.getCropType() + ": ready to harvest!");
			} else {
				space.setText(crop.getCropType() + ": " + daysUntilHarvest + " days until harvest");
			}
			space.setBackground(Color.GREEN);
		}
		int cropLimit = game.getFarm().getCropLimit();
		for (int counter = crops.size(); counter < cropLimit; counter ++) {
			JLabel space = cropSpaces.get(counter);
			space.setText("Fertile");
			space.setBackground(Color.WHITE);
		}
	}
	
	public void updateAnimalDisplay() {
		int cows = 0;
		int sheep = 0;
		int chickens = 0;
		for (Animal animal : game.getFarm().getAnimals()) {
			if (animal instanceof Cow) { 
				cows ++; 
			}
			else if (animal instanceof Sheep) { 
				sheep ++; 
			}
			else if (animal instanceof Chicken) { 
				chickens ++; 
			}
		}
	    cowCountLabel.setText("Owned: " + cows);
	    sheepCountLabel.setText("Owned: " + sheep);
	    chickenCountLabel.setText("Owned: " + chickens);
	}
	
	public void harvestCrops() {
		Farm farm = game.getFarm();
		boolean cropsToHarvest = false;
		for (Crop crop : farm.getCrops()) {
			if (crop.canHarvest()) {
				cropsToHarvest = true;
				break;
			}
		}
		if (game.getRemainingActions() <= 0) {
			JOptionPane.showMessageDialog(frmFarmSimulator, "You have already used your two actions for the day");
		} else if (cropsToHarvest){
			int earnings = game.harvestCrops();
			JOptionPane.showMessageDialog(frmFarmSimulator, "You harvested all fully grown crops and earned $" + earnings + ".");
			farm.earnMoney(earnings);
			useAction();
		} else {
			JOptionPane.showMessageDialog(frmFarmSimulator, "You have no fully grown crops to harvest!");
		}
		updateCropDisplay();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFarmSimulator = new JFrame();
		frmFarmSimulator.getContentPane().setBackground(new Color(0, 128, 0));
		frmFarmSimulator.setTitle("Farm Simulator");
		frmFarmSimulator.setBounds(100, 100, 700, 500);
		frmFarmSimulator.setLocationRelativeTo(null);
		frmFarmSimulator.setResizable(false);
		frmFarmSimulator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFarmSimulator.getContentPane().setLayout(null);
		
		JButton storeButton = new JButton("Store");
		storeButton.setBounds(10, 354, 112, 75);
		storeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				launchStoreWindow();
			}
		});
		frmFarmSimulator.getContentPane().add(storeButton);
		
		nextDayButton = new JButton("Go to next day");
		nextDayButton.setBounds(500, 354, 140, 75);
		nextDayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				game.nextDay();
				JOptionPane.showMessageDialog(frmFarmSimulator, "You retire to your home after a hard days work");
				newDay();
			}
		});
		frmFarmSimulator.getContentPane().add(nextDayButton);
		
		JButton inventoryButton = new JButton("Inventory");
		inventoryButton.setBounds(132, 354, 112, 75);
		inventoryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				launchInventoryWindow();
			}
		});
		frmFarmSimulator.getContentPane().add(inventoryButton);
		
		JPanel animalPanel = new JPanel();
		animalPanel.setBounds(352, 62, 288, 228);
		animalPanel.setBackground(new Color(139, 69, 19));
		frmFarmSimulator.getContentPane().add(animalPanel);
		animalPanel.setLayout(new GridLayout(3, 1, 1, 1));
		
		JPanel cowPanel = new JPanel();
		cowPanel.setBackground(Color.WHITE);
		animalPanel.add(cowPanel);
		cowPanel.setLayout(null);

		JLabel cowReturnLabel = new JLabel("Daily Return (Base): $" + new Cow().getBaseReturn());
		cowReturnLabel.setBounds(103, 39, 175, 37);
		cowPanel.add(cowReturnLabel);

		cowCountLabel = new JLabel("Owned: 0");
		cowCountLabel.setBounds(103, 0, 175, 37);
		cowPanel.add(cowCountLabel);

		JLabel cowLabel = new JLabel();
		cowLabel.setToolTipText("Cow");
		cowLabel.setIcon(new ImageIcon(MainScreen.class.getResource("/img/cow.png")));
		cowLabel.setBounds(0, 0, 76, 76);
		cowPanel.add(cowLabel);

		JPanel sheepPanel = new JPanel();
		sheepPanel.setBackground(Color.WHITE);
		animalPanel.add(sheepPanel);
		sheepPanel.setLayout(null);

		JLabel sheepLabel = new JLabel();
		sheepLabel.setToolTipText("Sheep");
		sheepLabel.setForeground(Color.WHITE);
		sheepLabel.setBackground(Color.WHITE);
		sheepLabel.setIcon(new ImageIcon(MainScreen.class.getResource("/img/sheep.png")));
		sheepLabel.setHorizontalAlignment(SwingConstants.CENTER);
		sheepLabel.setBounds(0, 0, 76, 76);
		sheepPanel.add(sheepLabel);

		JLabel sheepReturnLabel = new JLabel("Daily Return (Base): $" + new Sheep().getBaseReturn());
		sheepReturnLabel.setBounds(103, 39, 175, 37);
		sheepPanel.add(sheepReturnLabel);

		sheepCountLabel = new JLabel("Owned: 0");
		sheepCountLabel.setBounds(103, 0, 175, 37);
		sheepPanel.add(sheepCountLabel);
		
		JPanel chickenPanel = new JPanel();
		chickenPanel.setBackground(Color.WHITE);
		animalPanel.add(chickenPanel);
		chickenPanel.setLayout(null);

		JLabel chickenLabel = new JLabel();
		chickenLabel.setToolTipText("Chicken");
		chickenLabel.setIcon(new ImageIcon(MainScreen.class.getResource("/img/chicken.png")));
		chickenLabel.setHorizontalAlignment(SwingConstants.CENTER);
		chickenLabel.setBounds(0, 0, 76, 76);
		chickenPanel.add(chickenLabel);
		
		chickenCountLabel = new JLabel("Owned: 0");
		chickenCountLabel.setBounds(103, 0, 175, 37);
		chickenPanel.add(chickenCountLabel);
		
		JLabel chickenReturnLabel = new JLabel("Daily Return (Base): $" + new Cow().getBaseReturn());
		chickenReturnLabel.setBounds(103, 39, 175, 37);
		chickenPanel.add(chickenReturnLabel);
		
		dayLabel = new JLabel("Day " + Integer.toString(game.getCurrentDay()) + "/" + Integer.toString(game.getTotalDays()));
		dayLabel.setBounds(10, 11, 140, 23);
		frmFarmSimulator.getContentPane().add(dayLabel);
		
		actionLabel = new JLabel(Integer.toString(game.getRemainingActions()) + " actions remaining");
		actionLabel.setBounds(160, 11, 140, 23);
		frmFarmSimulator.getContentPane().add(actionLabel);
		
		moneyLabel = new JLabel("You have $" + Integer.toString(game.getFarm().getMoney()));
		moneyLabel.setBounds(366, 11, 140, 23);
		frmFarmSimulator.getContentPane().add(moneyLabel);
		
		JButton harvestButton = new JButton("Harvest crops");
		harvestButton.setBounds(41, 301, 128, 23);
		harvestButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				harvestCrops();
			}
		});
		frmFarmSimulator.getContentPane().add(harvestButton);
		
		JButton waterCropsButton = new JButton("Water Crops");
		waterCropsButton.setBounds(179, 301, 128, 23);
		frmFarmSimulator.getContentPane().add(waterCropsButton);
		waterCropsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				waterCropSelection();
			}
		});
		
		JButton playButton = new JButton("Play with animals");
		playButton.setBounds(500, 301, 140, 23);
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (game.getRemainingActions() <= 0) {
					JOptionPane.showMessageDialog(frmFarmSimulator, "You have already used your two actions for the day");
				} else {
					JOptionPane.showMessageDialog(frmFarmSimulator, "You played with all of your animals and increased their happiness.");
					game.playWithAnimals();
					useAction();
				}
			}
		});
		frmFarmSimulator.getContentPane().add(playButton);
		
		JButton tendLandButton = new JButton("Tend land");
		tendLandButton.setBounds(254, 354, 112, 75);
		tendLandButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (game.getRemainingActions() <= 0) {
					JOptionPane.showMessageDialog(frmFarmSimulator, "You have already used your two actions for the day");
				} else {
					game.tendLand();
					updateCropDisplay();
					useAction();
				}
			}
		});
		frmFarmSimulator.getContentPane().add(tendLandButton);
		
		JButton animalStatusButton = new JButton("Get animal status");
		animalStatusButton.setBounds(352, 301, 140, 23);
		animalStatusButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				launchAnimalWindow();
			}
		});
		frmFarmSimulator.getContentPane().add(animalStatusButton);
		
		JPanel cropPanel = new JPanel();
		cropPanel.setBackground(new Color(0, 0, 0));
		cropPanel.setBounds(10, 62, 323, 228);
		frmFarmSimulator.getContentPane().add(cropPanel);
		cropPanel.setLayout(new GridLayout(8, 2, 1, 1));

		for (int counter = 0; counter < 16; counter ++) {
			JLabel cropSpace = new JLabel("Uncultivated");
			cropSpace.setOpaque(true);
			cropSpace.setBackground(Color.GRAY);
			cropSpaces.add(cropSpace);
			cropPanel.add(cropSpace);
		}
	}
	
	public void waterCropSelection() {
		ArrayList<String> cropTypes = new ArrayList<String>();
		for (Crop crop : game.getFarm().getCrops()) {
			if (!crop.canHarvest() && !cropTypes.contains(crop.getCropType())){
				cropTypes.add(crop.getCropType());
			}
		}
		if (cropTypes.size() > 0) {
			Object[] crops = cropTypes.toArray();
			String initialSelection = "Barley";	
			String selection = (String) JOptionPane.showInputDialog(frmFarmSimulator, "Choose a crop variety to water:", "Choose Crop", JOptionPane.PLAIN_MESSAGE, null, crops, initialSelection);
			if (selection != null) {
				game.waterCrops(selection);
				useAction();
			}		
			updateCropDisplay();
		} else {
			JOptionPane.showMessageDialog(frmFarmSimulator, "There are no crops to water");
		}
	}
}
