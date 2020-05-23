package farmSimulatorGUI;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JOptionPane;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.EtchedBorder;
import javax.swing.border.BevelBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JProgressBar;

/**
 * Screen where main game play occurs. Displays crop and animal information and allows player to
 * preform actions and visit store or view animal status or inventory.
 * @author David Frost, Ella Johnson
 *
 */
public class MainScreen {

	/**
	 * Main window
	 */
	private JFrame frmFarmSimulator;
	
	/**
	 * Spaces which can be occupied by crops, fertile or uncultivated
	 */
	private ArrayList<JPanel> cropSpaces = new ArrayList<JPanel>();
	
	/**
	 * Game environment which controls main screen
	 */
	private GameEnvironment game;
	
	/**
	 * Button to move to next day
	 */
	private JButton nextDayButton;
	
	/**
	 * Label showing how many days player has been on farm
	 */
	private JLabel dayLabel;
	
	/**
	 * Label showing how much money player has
	 */
	private JLabel moneyLabel;
	
	/**
	 * Label showing how many actions player has remaining
	 */
	private JLabel actionLabel;
	
	/**
	 * Label showing how many cows player has
	 */
	private JLabel cowCountLabel;
	
	/**
	 * Label showing how many sheep player has
	 */
	private JLabel sheepCountLabel;
	
	/**
	 * Label showing how many chickens player has
	 */
	private JLabel chickenCountLabel;
	private JLabel toolTipLabel;
	
	/**
	 * Create the application.
	 * @param newGame		Game environment passed into the main screen
	 */
	public MainScreen(GameEnvironment newGame) {
		game = newGame;
		initialize();
		updateMoneyDisplay();
		updateCropDisplay();
		updateAnimalDisplay();
		frmFarmSimulator.setVisible(true);
	}
	
	/**
	 * Close application after main part of game has finished
	 */
	public void closeWindow() {
		frmFarmSimulator.dispose();
	}
	
	/**
	 * Calls function to close main screen window
	 */
	public void finishedWindow() {
		game.closeMainScreen(this);
	}
	
	/**
	 * Makes main screen window hidden, and store window visible
	 */
	public void launchStoreWindow() {
		frmFarmSimulator.setVisible(false);
		StoreScreen storeWindow = new StoreScreen(game);
	}
	
	/**
	 * Closes store window, updates value on main screen and makes main screen visible again
	 * @param storeWindow The screen for the store
	 */
	public void closeStoreWindow(StoreScreen storeWindow) {
		storeWindow.closeWindow();
		updateMoneyDisplay();
		updateAnimalDisplay();
		updateCropDisplay();
		frmFarmSimulator.setVisible(true);
	}
	
	/**
	 * Makes the main screen window hidden and the inventory window visible
	 */
	public void launchInventoryWindow() {
		frmFarmSimulator.setVisible(false);
		InventoryScreen inventoryWindow = new InventoryScreen(game);
	}
	
	/**
	 * Makes the inventory window hidden, updates the values on main screen and makes it visible
	 * @param inventoryWindow The window to close
	 */
	public void closeInventoryWindow(InventoryScreen inventoryWindow) {
		inventoryWindow.closeWindow();
		updateAnimalDisplay();
		updateCropDisplay();
		frmFarmSimulator.setVisible(true);
	}
	
	/**
	 * Makes the main screen window hidden and the animal status window visible
	 */
	public void launchAnimalWindow() {
		frmFarmSimulator.setVisible(false);
		AnimalStatusScreen animalWindow = new AnimalStatusScreen(game);	
	}
	
	/**
	 * Makes the animal status window hidden and the mains screen visible
	 * @param animalWindow The window to close
	 */
	public void closeAnimalWindow(AnimalStatusScreen animalWindow) {
		animalWindow.closeWindow();
		frmFarmSimulator.setVisible(true);
	}
	
	/**
	 * Decreases the game environment's tally of remaining actions and the number of remaining actions
	 * visible on actionLable
	 */
	public void useAction() {
		game.useAction();
		actionLabel.setText("Actions Remaining: " + game.getRemainingActions());
	}
	
	/**
	 * Changes next day button to end game if this is the last day of the game.
	 * If some days remain, resets actions to 2 and updates displays to reflect changes to crops,
	 * animals, current day and money.
	 */
	public void newDay() {
		if (game.getCurrentDay() == game.getTotalDays()) {
			nextDayButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					toolTipLabel.setText("<html>Ends the game and takes you to the end game screen where you can see your score.<html>");
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					toolTipLabel.setText("");
				}
			});
			
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
		moneyLabel.setText("Money: $" + game.getFarm().getMoney());
		actionLabel.setText("Actions Remaining: " + game.getRemainingActions());
	}
	
	/**
	 * Changes moneyLabel to reflect changes in farmer's money
	 */
	public void updateMoneyDisplay() {
		moneyLabel.setText("Money: $" + game.getFarm().getMoney());
	}
	
	/**
	 * Updates properties of crops and fertile/uncultivated crop spaces
	 */
	public void updateCropDisplay() {
		ArrayList<Crop> crops = game.getFarm().getCrops();
		for (int counter = crops.size() - 1; counter >= 0; counter --) {
			JPanel cropSpace = cropSpaces.get(counter);
			cropSpace.setBackground(Color.WHITE);
			cropSpace.removeAll();
			Crop crop = crops.get(counter);

			cropSpace.setLayout(new GridLayout(1, 0, 0, 0));
			
			JLabel cropType = new JLabel(crop.getCropType());
			cropType.setHorizontalAlignment(SwingConstants.CENTER);
			cropSpace.add(cropType);
			
			JPanel growthPanel = new JPanel();
			growthPanel.setBackground(Color.WHITE);
			cropSpace.add(growthPanel);
			growthPanel.setLayout(new GridLayout(2, 1, 0, 0));
			
			JLabel growthLabel = new JLabel("Growth Progress:");
			growthLabel.setHorizontalAlignment(SwingConstants.CENTER);
			growthLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
			growthPanel.add(growthLabel);
			
			if (crop.getAge() == crop.getHarvestAge()) {
				JLabel fullyGrownLabel = new JLabel("Ready to Harvest");
				fullyGrownLabel.setForeground(new Color(0, 180, 0));
				fullyGrownLabel.setHorizontalAlignment(SwingConstants.CENTER);
				fullyGrownLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
				growthPanel.add(fullyGrownLabel);
				
			}
			else {
				JProgressBar growthProgressBar = new JProgressBar();
				growthProgressBar.setString("" + crop.getAge() + "/" + crop.getHarvestAge());
				growthProgressBar.setStringPainted(true);
				growthProgressBar.setMaximum(crop.getHarvestAge());
				growthProgressBar.setValue(crop.getAge());
				growthProgressBar.setForeground(new Color(0, 180, 0));
				
				growthPanel.add(growthProgressBar);
			}

			
			
		}
		int cropLimit = game.getFarm().getCropLimit();
		for (int counter = crops.size(); counter < cropLimit; counter ++) {
			JPanel space = cropSpaces.get(counter);
			space.removeAll();
			JLabel status = new JLabel("Fertile");
			status.setHorizontalAlignment(SwingConstants.CENTER);
			space.add(status);
			space.setBackground(Color.WHITE);
		}
	}
	
	/**
	 * Updates amount owned of each animal
	 */
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
	    cowCountLabel.setText("Owned: " + cows + "/10");
	    sheepCountLabel.setText("Owned: " + sheep + "/10");
	    chickenCountLabel.setText("Owned: " + chickens + "/10");
	}
	
	/**
	 * Shows error message if there are no crops available to harvest
	 * If some crops are available to harvest, removes them from display, increases farmer money
	 * by earnings from selling crops, and decreases remaining actions by 1
	 */
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
	 * Initialize display of information on farm's crops, animals, money, remaining actions and
	 * current day
	 * Initialize buttons to preform actions or open animal status, store or inventory screens
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
		
		//When pressed will open store screen and close main screen
		JButton storeButton = new JButton("Store");
		storeButton.setBounds(10, 361, 112, 87);
		storeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				toolTipLabel.setText("<html>Opens the store where you can puchase animals, crops, and items.<html>");
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				toolTipLabel.setText("");
			}
		});
		storeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				launchStoreWindow();
			}
		});
		frmFarmSimulator.getContentPane().add(storeButton);
		
		//When pressed will move game to next day
		nextDayButton = new JButton("Go to next day");
		nextDayButton.setBounds(531, 361, 140, 87);
		nextDayButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				toolTipLabel.setText("<html>Ends the day and goes on to the next one allowing you to perform more actions<html>");
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				toolTipLabel.setText("");
			}
		});
		nextDayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				game.nextDay();
				JOptionPane.showMessageDialog(frmFarmSimulator, "You retire to your home after a hard days work");
				newDay();
			}
		});
		frmFarmSimulator.getContentPane().add(nextDayButton);
		
		//When pressed will close main screen and open inventory
		JButton inventoryButton = new JButton("Inventory");
		inventoryButton.setBounds(132, 361, 112, 87);
		inventoryButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				toolTipLabel.setText("<html>Opens up the inventory screen where you can see all of your items and allows you to use them.<html>");
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				toolTipLabel.setText("");
			}
		});
		inventoryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				launchInventoryWindow();
			}
		});
		frmFarmSimulator.getContentPane().add(inventoryButton);
		
		//Panel within which animal information is displayed
		JPanel animalPanel = new JPanel();
		animalPanel.setBounds(410, 45, 261, 250);
		animalPanel.setBackground(Color.BLACK);
		frmFarmSimulator.getContentPane().add(animalPanel);
		animalPanel.setLayout(new GridLayout(3, 1, 1, 1));
		
		JPanel cowPanel = new JPanel();
		cowPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		cowPanel.setBackground(Color.WHITE);
		animalPanel.add(cowPanel);
		cowPanel.setLayout(null);

		JLabel cowReturnLabel = new JLabel("Daily Return (Base): $" + new Cow().getBaseReturn());
		cowReturnLabel.setBounds(103, 39, 148, 37);
		cowPanel.add(cowReturnLabel);

		cowCountLabel = new JLabel("Owned: 0");
		cowCountLabel.setBounds(103, 0, 148, 37);
		cowPanel.add(cowCountLabel);

		JLabel cowLabel = new JLabel();
		cowLabel.setToolTipText("Cow");
		cowLabel.setIcon(new ImageIcon(MainScreen.class.getResource("/img/cow.png")));
		cowLabel.setBounds(5, 5, 76, 76);
		cowPanel.add(cowLabel);

		JPanel sheepPanel = new JPanel();
		sheepPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		sheepPanel.setBackground(Color.WHITE);
		animalPanel.add(sheepPanel);
		sheepPanel.setLayout(null);

		JLabel sheepLabel = new JLabel();
		sheepLabel.setToolTipText("Sheep");
		sheepLabel.setForeground(Color.WHITE);
		sheepLabel.setBackground(Color.WHITE);
		sheepLabel.setIcon(new ImageIcon(MainScreen.class.getResource("/img/sheep.png")));
		sheepLabel.setHorizontalAlignment(SwingConstants.CENTER);
		sheepLabel.setBounds(5, 5, 76, 76);
		sheepPanel.add(sheepLabel);

		JLabel sheepReturnLabel = new JLabel("Daily Return (Base): $" + new Sheep().getBaseReturn());
		sheepReturnLabel.setBounds(103, 39, 148, 37);
		sheepPanel.add(sheepReturnLabel);

		sheepCountLabel = new JLabel("Owned: 0");
		sheepCountLabel.setBounds(103, 0, 148, 37);
		sheepPanel.add(sheepCountLabel);
		
		JPanel chickenPanel = new JPanel();
		chickenPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		chickenPanel.setBackground(Color.WHITE);
		animalPanel.add(chickenPanel);
		chickenPanel.setLayout(null);

		JLabel chickenLabel = new JLabel();
		chickenLabel.setToolTipText("Chicken");
		chickenLabel.setIcon(new ImageIcon(MainScreen.class.getResource("/img/chicken.png")));
		chickenLabel.setHorizontalAlignment(SwingConstants.CENTER);
		chickenLabel.setBounds(5, 5, 76, 76);
		chickenPanel.add(chickenLabel);
		
		chickenCountLabel = new JLabel("Owned: 0");
		chickenCountLabel.setBounds(103, 0, 148, 37);
		chickenPanel.add(chickenCountLabel);
		
		JLabel chickenReturnLabel = new JLabel("Daily Return (Base): $" + new Chicken().getBaseReturn());
		chickenReturnLabel.setBounds(103, 39, 148, 37);
		chickenPanel.add(chickenReturnLabel);
		
		JPanel cropPanel = new JPanel();
		cropPanel.setBackground(Color.BLACK);
		cropPanel.setBounds(10, 45, 390, 250);
		frmFarmSimulator.getContentPane().add(cropPanel);
		cropPanel.setLayout(new GridLayout(8, 2, 1, 1));
		
		JPanel gameDetailsPanel = new JPanel();
		gameDetailsPanel.setBounds(0, 0, 694, 34);
		frmFarmSimulator.getContentPane().add(gameDetailsPanel);
		gameDetailsPanel.setLayout(new GridLayout(0, 3, 0, 0));
		
		dayLabel = new JLabel("Day " + Integer.toString(game.getCurrentDay()) + "/" + Integer.toString(game.getTotalDays()));
		dayLabel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		dayLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		dayLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gameDetailsPanel.add(dayLabel);
		
		actionLabel = new JLabel("Actions Remaining: " + game.getRemainingActions());
		actionLabel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		actionLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		actionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gameDetailsPanel.add(actionLabel);
		
		moneyLabel = new JLabel("Money: $" + Integer.toString(game.getFarm().getMoney()));
		moneyLabel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		moneyLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		moneyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gameDetailsPanel.add(moneyLabel);
		
		JPanel actionButtonsPanel = new JPanel();
		actionButtonsPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		actionButtonsPanel.setBounds(10, 306, 661, 44);
		frmFarmSimulator.getContentPane().add(actionButtonsPanel);
		actionButtonsPanel.setLayout(new GridLayout(1, 5, 0, 0));
		
		//Calls harvestCrops() method when pressed
		JButton harvestButton = new JButton("Harvest crops");
		harvestButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				toolTipLabel.setText("<html>All crops that are fully grown are harvested and removed from the fields and sold at the market.<html>");
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				toolTipLabel.setText("");
			}
		});
		actionButtonsPanel.add(harvestButton);
		harvestButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				harvestCrops();
			}
		});
		
		//Calls waterCropSelection() when pressed if player has actions remaining
		JButton waterCropsButton = new JButton("Water Crops");
		actionButtonsPanel.add(waterCropsButton);
		waterCropsButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				toolTipLabel.setText("<html>You can select a crop variety to water, all crops of that type grow by 1 day.<html>");
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				toolTipLabel.setText("");
			}
		});
		waterCropsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (game.getRemainingActions() <= 0) {
					JOptionPane.showMessageDialog(frmFarmSimulator, "You have already used your two actions for the day");
				} else {
					waterCropSelection();
				}
			}
		});
		
		//Increases animal happiness and fertile crop spaces if player has actions remaining
		JButton tendLandButton = new JButton("Tend land");
		actionButtonsPanel.add(tendLandButton);
		tendLandButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				toolTipLabel.setText("<html>Tending to the land increases the limit of how many crops you can grow at one time. It also increases the happiness of all your animals by 1.<html>");
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				toolTipLabel.setText("");
			}
		});
		tendLandButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (game.getRemainingActions() <= 0) {
					JOptionPane.showMessageDialog(frmFarmSimulator, "You have already used your two actions for the day");
				} else {
					try {
						game.tendLand();
						updateCropDisplay();
						useAction();
						if (game.getFarm().getCropLimit() < 16) {
							JOptionPane.showMessageDialog(frmFarmSimulator, "You have an extra space to grow crops and your animal's happiness increased by 1");
						} else {
							JOptionPane.showMessageDialog(frmFarmSimulator, "Your animal's happiness increased by 1");
						}
					} catch (IllegalArgumentException noEffect) {
						JOptionPane.showMessageDialog(frmFarmSimulator, noEffect.getMessage());
					}
				}
			}
		});
		
		toolTipLabel = new JLabel("");
		toolTipLabel.setForeground(Color.WHITE);
		toolTipLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		toolTipLabel.setHorizontalAlignment(SwingConstants.CENTER);
		toolTipLabel.setBounds(254, 361, 261, 87);
		frmFarmSimulator.getContentPane().add(toolTipLabel);
		
		//Calls the Game Environment method playWithAnimals if the player has actions remaining
		JButton playButton = new JButton();
		actionButtonsPanel.add(playButton);
		playButton.setLayout(new BorderLayout());
		JLabel playwithLbl = new JLabel("Play With");
		JLabel animalsLbl = new JLabel("Animals");
		animalsLbl.setHorizontalAlignment(SwingConstants.CENTER);
		playwithLbl.setHorizontalAlignment(SwingConstants.CENTER);
		playButton.add(BorderLayout.NORTH,playwithLbl);
		playButton.add(BorderLayout.SOUTH,animalsLbl);
		playButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				toolTipLabel.setText("<html>You can play with all of your animals and increase their happiness by 2.<html>");
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				toolTipLabel.setText("");
			}
		});
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (game.getRemainingActions() <= 0) {
					JOptionPane.showMessageDialog(frmFarmSimulator, "You have already used your two actions for the day");
				} else {
					try {
						game.playWithAnimals();
						JOptionPane.showMessageDialog(frmFarmSimulator, "You played with all of your animals and increased their happiness by 2.");
						useAction();
					} catch (IllegalArgumentException maxHappiness) {
						JOptionPane.showMessageDialog(frmFarmSimulator, maxHappiness.getMessage());
					}
						
				}
			}
		});
		
		//Hides main screen and makes animal status screen visible when pressed
		JButton animalStatusButton = new JButton();
		actionButtonsPanel.add(animalStatusButton);
		animalStatusButton.setLayout(new BorderLayout());
		JLabel viewanimalLbl = new JLabel("View Animal");
		JLabel statusLbl = new JLabel("Status");
		viewanimalLbl.setHorizontalAlignment(SwingConstants.CENTER);
		statusLbl.setHorizontalAlignment(SwingConstants.CENTER);
		animalStatusButton.add(BorderLayout.NORTH,viewanimalLbl);
		animalStatusButton.add(BorderLayout.SOUTH,statusLbl);
		animalStatusButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				toolTipLabel.setText("<html>Allows you to see the status of all your animals in one place, including their health and happiness.<html>");
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				toolTipLabel.setText("");
			}
		});
		animalStatusButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				launchAnimalWindow();
			}
		});
		
		//Initializes labels on all crop spaces to uncultivated
		for (int counter = 0; counter < 16; counter ++) {
			JPanel cropSpace = new JPanel();
			cropSpace.setLayout(new GridLayout(1,1,0,0));
			cropSpace.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			JLabel spaceStatus = new JLabel("Uncultivated");
			spaceStatus.setHorizontalAlignment(SwingConstants.CENTER);
			cropSpace.add(spaceStatus);
			cropSpace.setOpaque(true);
			cropSpace.setBackground(Color.GRAY);
			cropSpaces.add(cropSpace);
			cropPanel.add(cropSpace);
		}
	}
	
	/**
	 * If the farm does not have any crops, pop up message will tell player this.
	 * If the farm does have crops, pop up message will prompt player to choose a variety to water.
	 * The harvest times of all these crops will decrease by 1 and remaining actions will decrease
	 * by 1.
	 */
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
