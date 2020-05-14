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
	private Store store;
	private Farm farm;
	private GameEnvironment game;
	private JLabel moneyLabel;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StoreScreen window = new StoreScreen();
					window.frmCountyStore.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public StoreScreen(GameEnvironment newGame) {
		game = newGame;
		farm = game.getFarm();
		store = game.getStore();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCountyStore = new JFrame();
		frmCountyStore.setTitle("County Store");
		frmCountyStore.setBounds(100, 100, 700, 500);
		frmCountyStore.setResizable(false);
		frmCountyStore.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCountyStore.getContentPane().setLayout(null);
		
		JLabel welcomeLabel = new JLabel("Welcome to the County Store!");
		welcomeLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		welcomeLabel.setBounds(230, 11, 319, 34);
		frmCountyStore.getContentPane().add(welcomeLabel);
		
		moneyLabel = new JLabel("You have $0 to spend");
		moneyLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		moneyLabel.setBounds(261, 56, 229, 51);
		frmCountyStore.getContentPane().add(moneyLabel);
		
		JButton foodItemButton = new JButton("<html>Food<br/>items</html>");
		foodItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Buyable> foodItems = store.getFoodItems();
				merchandiseOptionPane(foodItems);
			}
		});
		foodItemButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		foodItemButton.setBounds(53, 193, 89, 89);
		frmCountyStore.getContentPane().add(foodItemButton);
		
		JButton cropItemButton = new JButton("<html>Crop<br/>items</html>");
		cropItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Buyable> cropItems = store.getCropItems();
				merchandiseOptionPane(cropItems);
			}
		});
		cropItemButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cropItemButton.setBounds(221, 193, 89, 89);
		frmCountyStore.getContentPane().add(cropItemButton);
		
		JButton animalButton = new JButton("Animals");
		animalButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Buyable> animals = store.getAnimals();
				merchandiseOptionPane(animals);
			}
		});
		animalButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		animalButton.setBounds(383, 193, 89, 89);
		frmCountyStore.getContentPane().add(animalButton);
		
		JButton cropButton = new JButton("Crops");
		cropButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Buyable> crops = store.getCrops();
				merchandiseOptionPane(crops);
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
				game.openMainScreen();
				closeWindow();
			}
		});
		exitButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		exitButton.setBounds(223, 320, 251, 34);
		frmCountyStore.getContentPane().add(exitButton);
		
		JButton inventoryButton = new JButton("View inventory");
		inventoryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.openInventoryScreen();
				closeWindow();
			}
		});
		inventoryButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		inventoryButton.setBounds(223, 376, 251, 34);
		frmCountyStore.getContentPane().add(inventoryButton);
	}
		
	public void merchandiseOptionPane(ArrayList<Buyable> buyables) {
		String[] merchandiseArray = store.getStoreArray(buyables);
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
					farm.buy(buyables.get(i));
					updateMoney();
					} catch (IllegalArgumentException e) {
						JOptionPane.showMessageDialog(frmCountyStore, "You don't have enough money to buy that!");
					}
				}
			}
		}
	}
	
	public void updateMoney() {
		String money = Integer.toString(farm.getMoney());
		moneyLabel.setText("You have $" + money + " to spend");
	}
	
	public void launch() {
		updateMoney();
		frmCountyStore.setVisible(true);
	}
	
	public void closeWindow() {
		frmCountyStore.setVisible(false);
	}

	public void finishedWindow() {
		frmCountyStore.dispose();
	}	
}
