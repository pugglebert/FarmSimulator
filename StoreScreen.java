package farmSimulatorGUI;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.ArrayList;
import java.lang.IllegalArgumentException;
import java.awt.Color;
import javax.swing.SwingConstants;

/**
 * Screen from which players can purchase animals, crops, food items and crop items
 * @author David Frost, Ella Johnson
 *
 */
public class StoreScreen {

	/**
	 * Main store screen window
	 */
	private JFrame frmCountyStore;
	
	/**
	 * Game environment which controls store screen
	 */
	private GameEnvironment game;
	
	/**
	 * Label to display player's current money
	 */
	private JLabel moneyLabel;

	/**
	 * Initializes store display and makes store screen visible
	 * @param newGame Game environment which controls store screen
	 */
	public StoreScreen(GameEnvironment newGame) {
		game = newGame;
		initialize();
		frmCountyStore.setVisible(true);
	}
	
	/**
	 * Dispose of store screen
	 */
	public void closeWindow() {
		frmCountyStore.dispose();
	}

	/**
	 * Closes store screen window when player returns to main screen
	 */
	public void finishedWindow() {
		game.getMainScreen().closeStoreWindow(this);
	}	
	
	/**
	 * Change money label to farmer's current money
	 */
	public void updateMoney() {
		String money = Integer.toString(game.getFarm().getMoney());
		moneyLabel.setText("You have $" + money + " to spend");
	}

	/**
	 * Initialize buttons to purchase merchandise or to return to farm or inventory
	 */
	private void initialize() {
		frmCountyStore = new JFrame();
		frmCountyStore.getContentPane().setBackground(new Color(0, 128, 0));
		frmCountyStore.setTitle("County Store");
		frmCountyStore.setBounds(100, 100, 700, 500);
		frmCountyStore.setLocationRelativeTo(null);
		frmCountyStore.setResizable(false);
		frmCountyStore.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCountyStore.getContentPane().setLayout(null);
		
		JLabel welcomeLabel = new JLabel("Welcome to the County Store!");
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setForeground(new Color(255, 255, 255));
		welcomeLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		welcomeLabel.setBounds(145, 11, 404, 34);
		frmCountyStore.getContentPane().add(welcomeLabel);
		
		moneyLabel = new JLabel("You have $" + game.getFarm().getMoney() + " to spend");
		moneyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		moneyLabel.setForeground(new Color(255, 255, 255));
		moneyLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		moneyLabel.setBounds(160, 56, 373, 51);
		frmCountyStore.getContentPane().add(moneyLabel);
		
		// Launches option pane which allows player to purchase food items
		JButton foodItemButton = new JButton("<html>Food<br/>items</html>");
		foodItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Buyable> foodItems = game.getStore().getFoodItems();
				merchandiseOptionPane(foodItems);
			}
		});
		foodItemButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		foodItemButton.setBounds(53, 193, 89, 89);
		frmCountyStore.getContentPane().add(foodItemButton);
		
		// Launches option pane which allows player to purchase crop items
		JButton cropItemButton = new JButton("<html>Crop<br/>items</html>");
		cropItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Buyable> cropItems = game.getStore().getCropItems();
				merchandiseOptionPane(cropItems);
			}
		});
		cropItemButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cropItemButton.setBounds(221, 193, 89, 89);
		frmCountyStore.getContentPane().add(cropItemButton);
		
		// Launches option pane which allows player to purchase animals
		JButton animalButton = new JButton("Animals");
		animalButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Buyable> animals = game.getStore().getAnimals();
				merchandiseOptionPane(animals);
			}
		});
		animalButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		animalButton.setBounds(383, 193, 89, 89);
		frmCountyStore.getContentPane().add(animalButton);
		
		// Launches option pane which allows player to purchase crops
		JButton cropButton = new JButton("Crops");
		cropButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (game.getFarm().getCrops().size() < game.getFarm().getCropLimit()) {
					ArrayList<Buyable> crops = game.getStore().getCrops();
					merchandiseOptionPane(crops);	
				}
				else {
					JOptionPane.showMessageDialog(frmCountyStore, "<html>You don't have enough space to grow more crops!<br/>Tend to the farm land to grow more</html>");
				}
				
			}
		});
		cropButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cropButton.setBounds(545, 193, 89, 89);
		frmCountyStore.getContentPane().add(cropButton);
		
		JLabel merchandiseLabel = new JLabel("View our merchandise:");
		merchandiseLabel.setHorizontalAlignment(SwingConstants.CENTER);
		merchandiseLabel.setForeground(new Color(255, 255, 255));
		merchandiseLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		merchandiseLabel.setBounds(160, 121, 373, 40);
		frmCountyStore.getContentPane().add(merchandiseLabel);
		
		// Closes store screen and opens main screen
		JButton exitButton = new JButton("Return to Farm");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		exitButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		exitButton.setBounds(221, 391, 251, 34);
		frmCountyStore.getContentPane().add(exitButton);
		
		// Closes store screen and opens inventory screen
		JButton inventoryButton = new JButton("View inventory");
		inventoryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
				game.getMainScreen().launchInventoryWindow();
			}
		});
		inventoryButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		inventoryButton.setBounds(221, 333, 251, 34);
		frmCountyStore.getContentPane().add(inventoryButton);
	}
	
	/**
	 * Launches option pane with range of buyable objects, and uses calls Farm.buy() to 
	 * purchase selected item
	 * @param buyables List of options from which player can choose something to purchase
	 */
	public void merchandiseOptionPane(ArrayList<Buyable> buyables) {
		String[] merchandiseArray = game.getStore().getStoreArray(buyables);
		String selection = (String) JOptionPane.showInputDialog(
				frmCountyStore,
				"Select an item to purchase",
				"Merchandise",
				JOptionPane.PLAIN_MESSAGE,
				null,
				merchandiseArray,
				null);
		if (selection != null) {
			for (int i = 0; i < merchandiseArray.length; i ++) {
				if (selection == merchandiseArray[i]) {
					try {
					game.getFarm().buy(buyables.get(i));
					updateMoney();
					} catch (IllegalArgumentException e) {
						JOptionPane.showMessageDialog(frmCountyStore, e.getMessage());
					}
				}
			}
		}
	}
}
