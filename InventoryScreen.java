package farmSimulatorGUI;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class InventoryScreen {

	private JFrame frmInventory;
	private GameEnvironment game;
	private DefaultListModel<FoodItem> foodItemListModel;
	private DefaultListModel<CropItem> cropItemListModel;
	
	public InventoryScreen(GameEnvironment newGame) {
		game = newGame;
		initialize();
		frmInventory.setVisible(true);
	}
	
	public void closeWindow() {
		frmInventory.dispose();
	}
	
	public void finishedWindow() {
		game.getMainScreen().closeInventoryWindow(this);
	}
	
	public void updateItems() {
		foodItemListModel.clear();
		for (FoodItem food : game.getFarmer().getFoodItems()) {
			foodItemListModel.addElement(food);
		}
		cropItemListModel.clear();
		for (CropItem crop : game.getFarmer().getCropItems()) {
			cropItemListModel.addElement(crop);
		}
	}

	private void initialize() {
		frmInventory = new JFrame();
		frmInventory.setResizable(false);
		frmInventory.setTitle("Inventory");
		frmInventory.setBounds(100, 100, 700, 500);
		frmInventory.setLocationRelativeTo(null);
		frmInventory.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmInventory.getContentPane().setLayout(null);
		
		foodItemListModel = new DefaultListModel<FoodItem>();
		for (FoodItem food : game.getFarmer().getFoodItems()) {
			foodItemListModel.addElement(food);
		}
		
		JList<FoodItem> foodItemList = new JList<FoodItem>(foodItemListModel);
		foodItemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		foodItemList.setFont(new Font("Tahoma", Font.PLAIN, 14));
		foodItemList.setBounds(30, 57, 489, 78);
		frmInventory.getContentPane().add(foodItemList);
		
		cropItemListModel = new DefaultListModel<CropItem>();
		for (CropItem cropItem : game.getFarmer().getCropItems()) {
			cropItemListModel.addElement(cropItem);
		}
		
		JList<CropItem> cropItemList = new JList<CropItem>(cropItemListModel);
		cropItemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cropItemList.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cropItemList.setBounds(30, 203, 483, 78);
		frmInventory.getContentPane().add(cropItemList);
		
		JButton backButton = new JButton("Return to Farm");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		backButton.setBounds(223, 320, 251, 34);
		frmInventory.getContentPane().add(backButton);
		
		JLabel foodItemLabel = new JLabel("Food Items");
		foodItemLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		foodItemLabel.setBounds(263, 11, 119, 35);
		frmInventory.getContentPane().add(foodItemLabel);
		
		JLabel cropItemLabel = new JLabel("Crop Items");
		cropItemLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cropItemLabel.setBounds(263, 157, 119, 35);
		frmInventory.getContentPane().add(cropItemLabel);
		
		JButton foodItemButton = new JButton("<html>Use<br/>food item!</html>");
		foodItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FoodItem selected = foodItemList.getSelectedValue();
				if (selected == null) {
					JOptionPane.showMessageDialog(frmInventory, "Please select an item to use.");
				} else if (game.getRemainingActions() <= 0) {
					JOptionPane.showMessageDialog(frmInventory, "You have already used all your actions for today.");
				} else {
					game.getFarm().useItem(selected);
					updateItems();
					game.getMainScreen().useAction();
				}
			}
		});
		foodItemButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		foodItemButton.setBounds(535, 57, 98, 78);
		frmInventory.getContentPane().add(foodItemButton);
		
		JButton cropItemButton = new JButton("<html>Use<br/>crop item!</html>");
		cropItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CropItem selected = cropItemList.getSelectedValue();
				if (selected == null) {
					JOptionPane.showMessageDialog(frmInventory, "Please select an item to use.");
				} else if (game.getRemainingActions() <= 0){
					JOptionPane.showMessageDialog(frmInventory, "You have already used all your actions for today.");
				} else {
					cropSelection(selected);
					updateItems();
				}
			}
		});
		cropItemButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cropItemButton.setBounds(535, 203, 98, 78);
		frmInventory.getContentPane().add(cropItemButton);
		
		JButton storeButton = new JButton("Visit store");
		storeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				finishedWindow();
				game.getMainScreen().launchStoreWindow();
			}
		});
		storeButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		storeButton.setBounds(223, 376, 251, 34);
		frmInventory.getContentPane().add(storeButton);
	}
	
	public void cropSelection(CropItem item) {
		ArrayList<String> cropTypes = new ArrayList<String>();
		for (Crop crop : game.getFarm().getCrops()) {
			if (!crop.canHarvest() && !cropTypes.contains(crop.getCropType())){
				cropTypes.add(crop.getCropType());
			}
		}
		if (cropTypes.size() > 0) {
			Object[] crops = cropTypes.toArray();
			String initialSelection = "Barley";
			String selection = (String) JOptionPane.showInputDialog(frmInventory, "Choose a crop variety to use " + item.getName() + " on:", "Choose Crop", JOptionPane.PLAIN_MESSAGE, null, crops, initialSelection);
			if (selection != null) {
				game.getFarm().useItem(item, selection);
			
				game.getMainScreen().updateCropDisplay();
				game.getMainScreen().useAction();
			}
		} else {
			JOptionPane.showMessageDialog(frmInventory, "There are no crops to use this item on");
		}
	}
}
