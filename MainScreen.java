package farmSimulatorGUI;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class MainScreen {

	private JFrame frmFarmSimulator;
	private ArrayList<JLabel> cropSpaces = new ArrayList<JLabel>();
	private Farm farm;
	private Farmer farmer;
	private int remainingActions = 2;
	private GameEnvironment game;
	private int totalDays = 10;
	private int currentDay = 1;
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
		farm = game.getFarm();
		farmer = game.getFarmer();
		totalDays = game.getTotalDays();
		initialize();
		setCrops();
		setAnimals();
		frmFarmSimulator.setVisible(true);
	}
	
	public void closeWindow() {
		frmFarmSimulator.dispose();
	}

	public void finishedWindow() {
		game.closeMainScreen(this);
	}
	
	public void launchAnimalWindow() {
		frmFarmSimulator.setVisible(false);
		AnimalStatusScreen animalWindow = new AnimalStatusScreen(game, this);	
	}
	
	public void closeAnimalScreen(AnimalStatusScreen animalWindow) {
		animalWindow.closeWindow();
		frmFarmSimulator.setVisible(true);
	}
	
	public void launchStoreWindow() {
		frmFarmSimulator.setVisible(false);
		StoreScreen storeWindow = new StoreScreen(game, this);	
	}
	
	public void closeStoreScreen(StoreScreen storeWindow) {
		storeWindow.closeWindow();
		frmFarmSimulator.setVisible(true);
	}
	
	public void newDay() {
		if (currentDay == totalDays) {
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
		remainingActions = 2;
		setCrops();
		setAnimals();
		dayLabel.setText("Day " + Integer.toString(currentDay) + "/" + Integer.toString(totalDays));
		moneyLabel.setText("You have $" + farm.getMoney());
		actionLabel.setText(Integer.toString(remainingActions) + " actions remaining");
	}
	
	public void nextDay() {
		for (Animal animal : farm.getAnimals()) {
			farm.earnMoney((int)animal.dailyReturn());
			animal.advanceDay();
		}
		for (Crop crop : farm.getCrops()) {
			crop.advanceDay();
		}
		currentDay++;
		JOptionPane.showMessageDialog(frmFarmSimulator, "You retire to your home after a hard days work");
		newDay();
	}
	
	public void setCrops() {
		ArrayList<Crop> crops = farm.getCrops();
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
		int cropLimit = farm.getCropLimit();
		for (int counter = crops.size(); counter < cropLimit; counter ++) {
			JLabel space = cropSpaces.get(counter);
			space.setText("Fertile");
			space.setBackground(Color.WHITE);
		}
	}
	
	public void setAnimals() {
		int cows = 0;
		int sheep = 0;
		int chickens = 0;
		for (Animal animal : farm.getAnimals()) {
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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFarmSimulator = new JFrame();
		frmFarmSimulator.getContentPane().setBackground(new Color(0, 128, 0));
		frmFarmSimulator.setTitle("Farm Simulator");
		frmFarmSimulator.setBounds(100, 100, 700, 500);
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
				nextDay();
			}
		});
		frmFarmSimulator.getContentPane().add(nextDayButton);
		
		JButton inventoryButton = new JButton("Inventory");
		inventoryButton.setBounds(132, 354, 112, 75);
		inventoryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		
		dayLabel = new JLabel("Day " + Integer.toString(currentDay) + "/" + Integer.toString(totalDays));
		dayLabel.setBounds(370, 354, 120, 14);
		frmFarmSimulator.getContentPane().add(dayLabel);
		
		actionLabel = new JLabel(Integer.toString(remainingActions) + " actions remaining");
		actionLabel.setBounds(370, 384, 120, 14);
		frmFarmSimulator.getContentPane().add(actionLabel);
		
		moneyLabel = new JLabel("You have $" + Integer.toString(farm.getMoney()));
		moneyLabel.setBounds(370, 415, 120, 14);
		frmFarmSimulator.getContentPane().add(moneyLabel);
		
		JButton harvestButton = new JButton("Harvest crops");
		harvestButton.setBounds(41, 301, 128, 23);
		harvestButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (remainingActions <= 0) {
					JOptionPane.showMessageDialog(frmFarmSimulator, "You have already used your two actions for the day");
				} else {
					int earnings = 0;
					boolean cropsToHarvest =false;
					ArrayList<Crop> crops = farm.getCrops();
					for (int counter = crops.size() - 1; counter >= 0; counter --) {
						Crop crop = crops.get(counter);
						if (crop.canHarvest()) {
							cropsToHarvest = true;
							earnings += crop.getSellPrice();
							farm.removeCrop(crop);
						}
					}
					if (cropsToHarvest) {
						JOptionPane.showMessageDialog(frmFarmSimulator, "You harvested all fully grown crops and earned $" + earnings + ".");
						farm.earnMoney(earnings);
						remainingActions --;
						actionLabel.setText(Integer.toString(remainingActions) + " actions remaining");
					}
					else {
						JOptionPane.showMessageDialog(frmFarmSimulator, "You have no fully grown crops to harvest!");
					}
					setCrops();
				}
			}
		});
		frmFarmSimulator.getContentPane().add(harvestButton);
		
		JButton playButton = new JButton("Play with animals");
		playButton.setBounds(500, 301, 140, 23);
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (remainingActions <= 0) {
					JOptionPane.showMessageDialog(frmFarmSimulator, "You have already used your two actions for the day");
				} else {
					JOptionPane.showMessageDialog(frmFarmSimulator, "You played with all of your animals and increased their happiness.");
					for (Animal animal : farm.getAnimals()) {
						animal.increaseHappiness(2);
					}
					remainingActions --;
					actionLabel.setText(Integer.toString(remainingActions) + " actions remaining");
				}
			}
		});
		frmFarmSimulator.getContentPane().add(playButton);
		
		JButton tendLandButton = new JButton("Tend land");
		tendLandButton.setBounds(179, 301, 120, 23);
		tendLandButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (remainingActions <= 0) {
					JOptionPane.showMessageDialog(frmFarmSimulator, "You have already used your two actions for the day");
				} else {
					farm.setCropLimit(farm.getCropLimit() + 1);
					setCrops();
					remainingActions --;
					actionLabel.setText(Integer.toString(remainingActions) + " actions remaining");
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
	
	public void open() {
		frmFarmSimulator.setVisible(true);
	}
	
	public void close() {
		frmFarmSimulator.setVisible(false);
	}
}