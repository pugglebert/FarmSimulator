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

public class StoreScreen {

	private JFrame frmCountyStore;
	private GameEnvironment game;
	private JLabel moneyLabel;

	public StoreScreen(GameEnvironment newGame) {
		game = newGame;
		initialize();
		frmCountyStore.setVisible(true);
	}
	
	public void closeWindow() {
		frmCountyStore.dispose();
	}

	public void finishedWindow() {
		game.getMainScreen().closeStoreWindow(this);
	}	
	
	public void updateMoney() {
		String money = Integer.toString(game.getFarm().getMoney());
		moneyLabel.setText("You have $" + money + " to spend");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCountyStore = new JFrame();
		frmCountyStore.setTitle("County Store");
		frmCountyStore.setBounds(100, 100, 700, 500);
		frmCountyStore.setLocationRelativeTo(null);
		frmCountyStore.setResizable(false);
		frmCountyStore.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCountyStore.getContentPane().setLayout(null);
		
		JLabel welcomeLabel = new JLabel("Welcome to the County Store!");
		welcomeLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		welcomeLabel.setBounds(230, 11, 319, 34);
		frmCountyStore.getContentPane().add(welcomeLabel);
		
		moneyLabel = new JLabel("You have $" + game.getFarm().getMoney() + " to spend");
		moneyLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		moneyLabel.setBounds(261, 56, 229, 51);
		frmCountyStore.getContentPane().add(moneyLabel);
		
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
		merchandiseLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		merchandiseLabel.setBounds(261, 121, 190, 40);
		frmCountyStore.getContentPane().add(merchandiseLabel);
		
		JButton exitButton = new JButton("Return to farm");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		exitButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		exitButton.setBounds(223, 320, 251, 34);
		frmCountyStore.getContentPane().add(exitButton);
		
		JButton inventoryButton = new JButton("View inventory");
		inventoryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
				game.getMainScreen().launchInventoryWindow();
			}
		});
		inventoryButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		inventoryButton.setBounds(223, 376, 251, 34);
		frmCountyStore.getContentPane().add(inventoryButton);
	}
		
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
