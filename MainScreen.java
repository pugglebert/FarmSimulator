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

public class MainScreen {

	private JFrame frmFarmSimulator;
	private ArrayList<JLabel> cropSpaces = new ArrayList<JLabel>();
	private Farm farm;
	private Farmer farmer;
	private int remainingActions = 2;
	private GameEnvironment game;
	
	/**
	 * Create the application.
	 */
	public MainScreen(GameEnvironment newGame) {
		game = newGame;
		farm = game.getFarm();
		farmer = game.getFarmer();
		initialize();

	}
	
	public void newDay() {
		remainingActions = 2;
		frmFarmSimulator.setVisible(true);
		setCrops();
	}
	
	public void setCrops() {
		ArrayList<Crop> crops = farm.getCrops();
		for (int counter = 0; counter < crops.size(); counter ++) {
			JLabel space = cropSpaces.get(counter);
			Crop crop = crops.get(counter);
			int daysUntilHarvest = crop.getHarvestAge() - crop.getAge();
			if (daysUntilHarvest < 0) {daysUntilHarvest = 0;}
			space.setText(crop.getCropType() + ": " + daysUntilHarvest + " days until harvest");
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
		ArrayList<Animal> animals = farm.getAnimals();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFarmSimulator = new JFrame();
		frmFarmSimulator.getContentPane().setBackground(new Color(0, 128, 0));
		frmFarmSimulator.setTitle("Farm Simulator");
		frmFarmSimulator.setBounds(100, 100, 666, 479);
		frmFarmSimulator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFarmSimulator.getContentPane().setLayout(null);
		
		JButton storeButton = new JButton("Store");
		storeButton.setBounds(10, 354, 112, 75);
		frmFarmSimulator.getContentPane().add(storeButton);
		
		JButton nextDayButton = new JButton("Go to next day");
		nextDayButton.setBounds(534, 354, 106, 75);
		nextDayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
		animalPanel.setLayout(null);
		
		JLabel cowLabel = new JLabel("Cow");
		cowLabel.setBounds(10, 38, 46, 14);
		animalPanel.add(cowLabel);
		
		JLabel sheepLabel = new JLabel("Sheep");
		sheepLabel.setBounds(10, 94, 46, 14);
		animalPanel.add(sheepLabel);
		
		JLabel chickenLabel = new JLabel("Chicken");
		chickenLabel.setBounds(10, 153, 46, 14);
		animalPanel.add(chickenLabel);
		
		JLabel dayLabel = new JLabel("Day " + Integer.toString(game.getCurrentDay()) + "/" + Integer.toString(game.getTotalDays()));
		dayLabel.setBounds(370, 354, 98, 14);
		frmFarmSimulator.getContentPane().add(dayLabel);
		
		JLabel actionLabel = new JLabel(Integer.toString(remainingActions) + " actions remaining");
		actionLabel.setBounds(370, 384, 98, 14);
		frmFarmSimulator.getContentPane().add(actionLabel);
		
		JLabel moneyLabel = new JLabel("You have $" + farm.getMoney());
		moneyLabel.setBounds(370, 415, 98, 14);
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
					for (Crop crop : farm.getCrops()) {
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
				JOptionPane.showMessageDialog(frmFarmSimulator, farm.getAnimalStatus());
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
}
