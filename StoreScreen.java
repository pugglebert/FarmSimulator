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

public class StoreScreen {

	private JFrame frmCountyStore;
	private Store store;
	private Farm farm;
	private GameEnvironment game;
	private MainScreen mainWindow;
	private JLabel moneyLabel;

	public StoreScreen(GameEnvironment newGame, MainScreen newMainWindow) {
		game = newGame;
		mainWindow = newMainWindow;
		farm = game.getFarm();
		store = game.getStore();
		initialize();
		frmCountyStore.setVisible(true);
	}

	
	public void closeWindow() {
		frmCountyStore.dispose();
	}

	public void finishedWindow() {
		mainWindow.closeStoreScreen(this);
	}	
	
	private void initialize() {
		frmCountyStore = new JFrame();
		frmCountyStore.setTitle("County Store");
		frmCountyStore.setBounds(100, 100, 700, 500);
		frmCountyStore.setResizable(false);
		frmCountyStore.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCountyStore.getContentPane().setLayout(null);
		
		JLabel welcomeLabel = new JLabel("Welcome to the County Store!");
		welcomeLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		welcomeLabel.setBounds(193, 11, 319, 34);
		frmCountyStore.getContentPane().add(welcomeLabel);
		
		moneyLabel = new JLabel("You have $0 to spend");
		moneyLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		moneyLabel.setBounds(213, 56, 211, 51);
		frmCountyStore.getContentPane().add(moneyLabel);
		
		JButton foodItemButton = new JButton("<html>Food<br/>items</html>");
		foodItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Buyable> foodItems = store.getFoodItems();
				merchandiseOptionPane(foodItems);
			}
		});
		foodItemButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		foodItemButton.setBounds(40, 193, 89, 89);
		frmCountyStore.getContentPane().add(foodItemButton);
		
		JButton cropItemButton = new JButton("<html>Crop<br/>items</html>");
		cropItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Buyable> cropItems = store.getCropItems();
				merchandiseOptionPane(cropItems);
			}
		});
		cropItemButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cropItemButton.setBounds(193, 193, 89, 89);
		frmCountyStore.getContentPane().add(cropItemButton);
		
		JButton animalButton = new JButton("Animals");
		animalButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Buyable> animals = store.getAnimals();
				merchandiseOptionPane(animals);
			}
		});
		animalButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		animalButton.setBounds(355, 193, 89, 89);
		frmCountyStore.getContentPane().add(animalButton);
		
		JButton cropButton = new JButton("Crops");
		cropButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Buyable> crops = store.getCrops();
				merchandiseOptionPane(crops);
			}
		});
		cropButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cropButton.setBounds(508, 193, 89, 89);
		frmCountyStore.getContentPane().add(cropButton);
		
		JLabel merchandiseLabel = new JLabel("View our merchandise:");
		merchandiseLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		merchandiseLabel.setBounds(213, 118, 190, 40);
		frmCountyStore.getContentPane().add(merchandiseLabel);
		
		JButton exitButton = new JButton("Return to farm");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.openMainScreen();
				close();
			}
		});
		exitButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		exitButton.setBounds(193, 320, 251, 34);
		frmCountyStore.getContentPane().add(exitButton);
		
		JButton inventoryButton = new JButton("View inventory");
		inventoryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		inventoryButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		inventoryButton.setBounds(193, 378, 251, 34);
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
					farm.buy(buyables.get(i));
					updateMoney();
				}
			}
		}
	}
	
	public void open() {
		frmCountyStore.setVisible(true);
		updateMoney();
	}
	
	public void close() {
		frmCountyStore.setVisible(false);
	}
	
	public void updateMoney() {
		String money = Integer.toString(farm.getMoney());
		moneyLabel.setText("You have $" + money + " to spend");
	}
}
