package farmSimulatorGUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSlider;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.SystemColor;

/**
 * Screen in player sets up their farm and farmer before they start the game
 * @author David Frost, Ella Johnson
 *
 */
public class SetupScreen {

	private JFrame setupWindow;
	private GameEnvironment game;
	private JTextField farmerNameField;
	private JTextField farmerAgeField;
	private JTextField farmNameField;
	private JLabel farmerNameError;
	private JLabel farmNameError;
	private JLabel farmerAgeError;

	/**
	 * Make the setup screen visible and initialize its display
	 */
	public SetupScreen(GameEnvironment newGame) {
		game = newGame;
		initialize();
		setupWindow.setVisible(true);
	}

	/**
	 * Dispose of the setup screen
	 */
	public void closeWindow() {
		setupWindow.dispose();
	}

	/**
	 * Close setup screen when player moves on to main game
	 */
	public void finishedWindow() {
		game.closeSetupScreen(this);
	}

	/**
	 * Initialize slide for player to choose duration of game, buttons for player to choose farm
	 * type and text fields for player to input farm name, farmer name and farmer age
	 */
	private void initialize() {
		setupWindow = new JFrame();
		setupWindow.setTitle("Game Setup");
		setupWindow.getContentPane().setBackground(new Color(0, 128, 0));
		setupWindow.setResizable(false);
		setupWindow.setBounds(100, 100, 700, 500);
		setupWindow.setPreferredSize(new Dimension(710, 510));
		setupWindow.pack();
		setupWindow.setLocationRelativeTo(null);
		setupWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setupWindow.getContentPane().setLayout(null);

		JLabel welcomeLabel = new JLabel("Welcome to Farm Simulator (Space Edition).\r\n");
		welcomeLabel.setForeground(new Color(255, 255, 255));
		welcomeLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setBounds(104, 0, 485, 55);
		setupWindow.getContentPane().add(welcomeLabel);

		JLabel missionLabel = new JLabel("Your mission is to colonise a planet by building a succesful farm there.");
		missionLabel.setForeground(new Color(255, 255, 255));
		missionLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		missionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		missionLabel.setBounds(107, 45, 479, 33);
		setupWindow.getContentPane().add(missionLabel);

		ButtonGroup farmTypeGroup = new ButtonGroup();
		
		JPanel detailSubmissionPanel = new JPanel();
		detailSubmissionPanel.setBackground(SystemColor.menu);
		detailSubmissionPanel.setBounds(10, 76, 674, 384);
		setupWindow.getContentPane().add(detailSubmissionPanel);
		detailSubmissionPanel.setLayout(null);

		JLabel firstThingsLabel = new JLabel("First, let's sort a few things out...");
		firstThingsLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		firstThingsLabel.setBackground(SystemColor.menu);
		firstThingsLabel.setBounds(258, 11, 162, 14);
		detailSubmissionPanel.add(firstThingsLabel);
		firstThingsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		firstThingsLabel.setForeground(Color.BLACK);
		
		JSlider gameLengthSlider = new JSlider();
		gameLengthSlider.setBounds(394, 36, 252, 58);
		detailSubmissionPanel.add(gameLengthSlider);
		gameLengthSlider.setForeground(Color.BLACK);
		gameLengthSlider.setBackground(SystemColor.menu);
		gameLengthSlider.setValue(5);
		gameLengthSlider.setSnapToTicks(true);
		gameLengthSlider.setPaintTicks(true);
		gameLengthSlider.setPaintLabels(true);
		gameLengthSlider.setMajorTickSpacing(1);
		gameLengthSlider.setMinimum(5);
		gameLengthSlider.setMaximum(10);
		
		JLabel gameDaysLabel = new JLabel("How many days would you like your game to last?");
		gameDaysLabel.setBounds(23, 36, 361, 58);
		detailSubmissionPanel.add(gameDaysLabel);
		gameDaysLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		gameDaysLabel.setForeground(Color.BLACK);
		gameDaysLabel.setBackground(new Color(240, 240, 240));
		gameDaysLabel.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel farmerNameLabel = new JLabel("What is your farmer's name?");
		farmerNameLabel.setBackground(SystemColor.menu);
		farmerNameLabel.setBounds(23, 101, 274, 33);
		detailSubmissionPanel.add(farmerNameLabel);
		farmerNameLabel.setForeground(Color.BLACK);
		farmerNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		farmerNameField = new JTextField("Bob");
		farmerNameField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		farmerNameField.setForeground(Color.BLACK);
		farmerNameField.setBounds(309, 103, 146, 33);
		detailSubmissionPanel.add(farmerNameField);
		farmerNameField.setBackground(SystemColor.menu);
		farmerNameField.setColumns(10);
		
		farmerNameError = new JLabel("Error");
		farmerNameError.setBounds(465, 102, 219, 35);
		detailSubmissionPanel.add(farmerNameError);
		farmerNameError.setVisible(false);
		farmerNameError.setForeground(Color.RED);
		
		farmerAgeError = new JLabel("Error");
		farmerAgeError.setBounds(465, 138, 219, 33);
		detailSubmissionPanel.add(farmerAgeError);
		farmerAgeError.setForeground(Color.RED);
		
		farmerAgeField = new JTextField("25");
		farmerAgeField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		farmerAgeField.setForeground(Color.BLACK);
		farmerAgeField.setBounds(309, 138, 146, 33);
		detailSubmissionPanel.add(farmerAgeField);
		farmerAgeField.setBackground(SystemColor.menu);
		farmerAgeField.setColumns(10);
		
		JLabel farmerAgeLabel = new JLabel("What is your farmer's age?");
		farmerAgeLabel.setBackground(SystemColor.menu);
		farmerAgeLabel.setBounds(23, 136, 252, 33);
		detailSubmissionPanel.add(farmerAgeLabel);
		farmerAgeLabel.setForeground(Color.BLACK);
		farmerAgeLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JPanel farmTypePanel = new JPanel();
		farmTypePanel.setForeground(Color.BLACK);
		farmTypePanel.setBounds(27, 180, 620, 117);
		detailSubmissionPanel.add(farmTypePanel);
		farmTypePanel.setBackground(SystemColor.menu);
		farmTypePanel.setLayout(null);
		
		JLabel farmTypeLabel = new JLabel("What type of farm would you like?");
		farmTypeLabel.setBackground(SystemColor.menu);
		farmTypeLabel.setForeground(Color.BLACK);
		farmTypeLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		farmTypeLabel.setBounds(0, 0, 620, 21);
		farmTypePanel.add(farmTypeLabel);
		
		JRadioButton earthFarmRadio = new JRadioButton("Earth Farm", true);
		earthFarmRadio.setForeground(Color.BLACK);
		earthFarmRadio.setBackground(SystemColor.menu);
		earthFarmRadio.setBounds(38, 21, 109, 21);
		farmTypePanel.add(earthFarmRadio);
		earthFarmRadio.setActionCommand("Earth");
		
		
		JRadioButton marsFarmRadio = new JRadioButton("Mars Farm");
		marsFarmRadio.setForeground(Color.BLACK);
		marsFarmRadio.setBackground(SystemColor.menu);
		marsFarmRadio.setBounds(38, 45, 109, 23);
		farmTypePanel.add(marsFarmRadio);
		marsFarmRadio.setActionCommand("Mars");
		
		JRadioButton venusFarmRadio = new JRadioButton("Venus Farm");
		venusFarmRadio.setForeground(Color.BLACK);
		venusFarmRadio.setBackground(SystemColor.menu);
		venusFarmRadio.setBounds(38, 71, 109, 23);
		farmTypePanel.add(venusFarmRadio);
		venusFarmRadio.setActionCommand("Venus");
		
		JRadioButton jupiterFarmRadio = new JRadioButton("Jupiter Farm");
		jupiterFarmRadio.setForeground(Color.BLACK);
		jupiterFarmRadio.setBackground(SystemColor.menu);
		jupiterFarmRadio.setBounds(38, 95, 109, 23);
		farmTypePanel.add(jupiterFarmRadio);
		jupiterFarmRadio.setActionCommand("Jupiter");
		farmTypeGroup.add(earthFarmRadio);
		farmTypeGroup.add(marsFarmRadio);
		farmTypeGroup.add(venusFarmRadio);
		farmTypeGroup.add(jupiterFarmRadio);
		
		JTextPane farmBonuses = new JTextPane();
		farmBonuses.setForeground(Color.BLACK);
		farmBonuses.setEditable(false);
		farmBonuses.setBackground(new Color(240, 240, 240));
		farmBonuses.setFont(new Font("Tahoma", Font.PLAIN, 14));
		farmBonuses.setText("Starting Money: $1000\r\nStarting Animals: Chicken\r\nStarting Crops: Barley, Wheat\r\nInitial Crop Limit: 6\r\nBonuses: Animal Happiness +1");
		farmBonuses.setBounds(233, 21, 354, 97);
		farmTypePanel.add(farmBonuses);
		
		RadioListener farmTypeListener = new RadioListener(farmBonuses, farmTypeGroup);
		
		JLabel farmNameLabel = new JLabel("What is the name of your farm?");
		farmNameLabel.setBackground(SystemColor.menu);
		farmNameLabel.setBounds(23, 296, 228, 33);
		detailSubmissionPanel.add(farmNameLabel);
		farmNameLabel.setForeground(Color.BLACK);
		farmNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		farmNameField = new JTextField("Bobs Farm");
		farmNameField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		farmNameField.setBounds(23, 328, 182, 33);
		detailSubmissionPanel.add(farmNameField);
		farmNameField.setForeground(Color.BLACK);
		farmNameField.setBackground(SystemColor.menu);
		farmNameField.setColumns(10);
		
		farmNameError = new JLabel("Error");
		farmNameError.setBounds(215, 328, 228, 33);
		detailSubmissionPanel.add(farmNameError);
		farmNameError.setVisible(false);
		farmNameError.setForeground(Color.RED);
		
		JButton beginGameButton = new JButton("Begin Game");
		beginGameButton.setBounds(501, 308, 146, 68);
		detailSubmissionPanel.add(beginGameButton);
		beginGameButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		beginGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isValid(farmerNameField.getText(), farmerAgeField.getText(), farmNameField.getText())) {
					game.setTotalDays(gameLengthSlider.getValue());
					game.initiateFarmer(farmerNameField.getText(), Integer.parseInt(farmerAgeField.getText()));
					game.initiateFarm(farmTypeGroup.getSelection().getActionCommand(), farmNameField.getText());
					finishedWindow();
				}
			}
		});
		earthFarmRadio.addActionListener(farmTypeListener);
		marsFarmRadio.addActionListener(farmTypeListener);
		venusFarmRadio.addActionListener(farmTypeListener);
		jupiterFarmRadio.addActionListener(farmTypeListener);
		farmerAgeError.setVisible(false);
		
	}	
	
	/**
	 * Checks that name is 3-15 characters long and does not contain numbers or special characters
	 * @param name String for farmer or farm name
	 * @param errorLabel Label on which error message will be displayed
	 * @return
	 */
	public boolean checkNameValid(String name, JLabel errorLabel) {
		boolean valid = true;
		if (name.length() < 3 || name.length() > 15) {
			valid = false;
			errorLabel.setText("<html>Name must be 3 to 15<br>characters long</html>");
			errorLabel.setVisible(true);
		}
		else if (!name.matches("[a-zA-Z ]+")) {
			valid = false;
			errorLabel.setText("<html>Name must not contain numbers<br>or special characters</html>");
			errorLabel.setVisible(true);
		}
		else {
			errorLabel.setVisible(false);
		}

		return valid;
	}
	
	/**
	 * Checks that age is in the range 1-100
	 * @param age Player input for age
	 * @param errorLabel Label on which error message will be displayed
	 * @return
	 */
	public boolean checkAgeValid(String age, JLabel errorLabel) {
		boolean valid = true;
		
		try {
			int farmerAge = Integer.parseInt(age);
			if (farmerAge <= 0 || farmerAge > 100){
				valid = false;
				farmerAgeError.setText("Age must be from 1 to 100");
				farmerAgeError.setVisible(true);
			}
			else {
				farmerAgeError.setVisible(false);
			}
		} catch (NumberFormatException e) {
			farmerAgeError.setText("Age must be an integer");
			farmerAgeError.setVisible(true);
			valid = false;

		}
		
		return valid;
	}
	
	/**
	 * Checks if player input is within constraints
	 * @param farmerName Name for farmer from player input
	 * @param farmerAge Age for farmer from player input
	 * @param farmName Name for farm from player input
	 * @return
	 */
	public boolean isValid(String farmerName, String farmerAge, String farmName) {
		boolean allValid = true;

		if(!checkNameValid(farmerName, farmerNameError)) {
			allValid = false;
		}

		if (!checkNameValid(farmName, farmNameError)) {
			allValid = false;
		}
		
		if (!checkAgeValid(farmerAge, farmerAgeError)) {
			allValid = false;
		}
		
		return allValid;
	}
}

/**
 * Buttons with which to choose farm type
 * @author David Frost, Ella Johnson
 *
 */
class RadioListener implements ActionListener{
	private JTextPane text;
	private ButtonGroup radioGroup;

	RadioListener(JTextPane newText, ButtonGroup farmTypeGroup){
		text = newText;
		radioGroup = farmTypeGroup;
	}

	/**
	 * Displays information about each planet and allows player to select planet
	 */
	public void actionPerformed(ActionEvent e) {

		String button = radioGroup.getSelection().getActionCommand();
		if (button == "Earth") {
			text.setText("Starting Money: $1000\r\nStarting Animals: Chicken\r\nStarting Crops: Barley, Wheat\r\nInitial Crop Limit: 6\r\nBonuses: Animal Happiness +1");
		}
		else if (button == "Mars") {
			text.setText("Starting Money: $800\r\nStarting Animals: Cow\r\nStarting Crops: Maize, Potato\r\nInitial Crop Limit: 8\r\nBonuses: Crop Harvest Time -1");
		}
		else if (button == "Venus") {
			text.setText("Starting Money: $1500\r\nStarting Animals: Sheep\r\nStarting Crops: Pumpkin, Kale\r\nInitial Crop Limit: 10\r\nBonuses: N/A");
		}
		else if (button == "Jupiter") {
			text.setText("Starting Money: $500\r\nStarting Animals: Cow, Sheep\r\nStarting Crops: Wheat, Maize, Pumpkin\r\nInitial Crop Limit: 6\r\nBonuses: Animal Happiness +1, Crop Harvest Time -1");
		}
	}
}
