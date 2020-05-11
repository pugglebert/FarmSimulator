package farmSimulator;

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
import javax.swing.JProgressBar;
import java.awt.Dimension;
import java.awt.Cursor;

public class MainScreen {

	private GameEnvironment game;
	private JFrame frmFarmSimulator;
	private JTable cropTabel;

	public MainScreen(GameEnvironment newGame) {
		game = newGame;
		initialize();
		frmFarmSimulator.setVisible(true);
	}

	public void closeWindow() {
		frmFarmSimulator.dispose();
	}
	
	public void finishedWindow() {
		game.closeMainScreen(this);
	}
	
	/**
	 * Create the application.
	 */
	public MainScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFarmSimulator = new JFrame();
		frmFarmSimulator.setResizable(false);
		frmFarmSimulator.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		frmFarmSimulator.getContentPane().setBackground(new Color(0, 128, 0));
		frmFarmSimulator.setTitle("Farm Simulator");
		frmFarmSimulator.setBounds(100, 100, 666, 480);
		frmFarmSimulator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFarmSimulator.getContentPane().setLayout(null);
		
		JButton storeButton = new JButton("Store");
		storeButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		storeButton.setBounds(10, 354, 112, 75);
		frmFarmSimulator.getContentPane().add(storeButton);
		
		JButton nextDayButton = new JButton("Go to next day");
		nextDayButton.setBounds(489, 354, 151, 75);
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
		
		JLabel dayLabel = new JLabel("Day 1/10");
		dayLabel.setBounds(254, 354, 98, 14);
		frmFarmSimulator.getContentPane().add(dayLabel);
		
		JLabel actionLable = new JLabel("2 actions remaining");
		actionLable.setBounds(254, 384, 98, 14);
		frmFarmSimulator.getContentPane().add(actionLable);
		
		JLabel moneyLabel = new JLabel("You have $1000");
		moneyLabel.setBounds(254, 415, 98, 14);
		frmFarmSimulator.getContentPane().add(moneyLabel);
		
		JScrollPane cropPane = new JScrollPane();
		cropPane.setBounds(10, 61, 332, 229);
		frmFarmSimulator.getContentPane().add(cropPane);
		
		cropTabel = new JTable();
		cropTabel.setEnabled(false);
		cropTabel.setRowSelectionAllowed(false);
		cropTabel.setToolTipText("");
		cropTabel.setModel(new DefaultTableModel(
			new Object[][] {
				{"Wheat", "0"},
				{"Pumpkin", "0"},
				{null, null},
				{null, null},
			},
			new String[] {
				"Crop type", "Days until harvest"
			}
		));
		cropTabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		cropPane.setViewportView(cropTabel);
		
		JButton harvestButton = new JButton("Harvest crops");
		harvestButton.setBounds(41, 301, 128, 23);
		frmFarmSimulator.getContentPane().add(harvestButton);
		
		JButton btnNewButton_2 = new JButton("Play with animals");
		btnNewButton_2.setBounds(489, 301, 151, 23);
		frmFarmSimulator.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Tend land");
		btnNewButton_3.setBounds(179, 301, 120, 23);
		frmFarmSimulator.getContentPane().add(btnNewButton_3);
		
		JLabel lblNewLabel = new JLabel("Farmland: 2/10 available land used");
		lblNewLabel.setBounds(73, 36, 222, 14);
		frmFarmSimulator.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Animals");
		lblNewLabel_1.setBounds(493, 36, 46, 14);
		frmFarmSimulator.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton_4 = new JButton("Get animal status");
		btnNewButton_4.setBounds(352, 301, 128, 23);
		frmFarmSimulator.getContentPane().add(btnNewButton_4);
	}
}