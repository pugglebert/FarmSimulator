package farmSimulatorGUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSlider;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.JButton;

public class SetupScreen {

	private JFrame setupWindow;
	private GameEnvironment game;
	private JTextField farmerNameField;
	private JTextField farmerAgeField;
	private JTextField farmNameField;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */	

	public SetupScreen(GameEnvironment newGame) {
		game = newGame;
		initialize();
		setupWindow.setVisible(true);
	}

	public void closeWindow() {
		setupWindow.dispose();
	}

	public void finishedWindow() {
		game.closeSetupScreen(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setupWindow = new JFrame();
		setupWindow.setTitle("Game Setup");
		setupWindow.setResizable(false);
		setupWindow.setBounds(100, 100, 700, 500);
		setupWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setupWindow.getContentPane().setLayout(null);

		JLabel welcomeLabel = new JLabel("Welcome to Farm Simulator (Space Edition).\r\n");
		welcomeLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setBounds(76, 0, 485, 55);
		setupWindow.getContentPane().add(welcomeLabel);

		JLabel missionLabel = new JLabel("Your mission is to colonise a planet by building a succesful farm there.");
		missionLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		missionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		missionLabel.setBounds(76, 52, 479, 33);
		setupWindow.getContentPane().add(missionLabel);

		JLabel firstThingsLabel = new JLabel("First, let's sort a few things out...");
		firstThingsLabel.setBounds(36, 89, 330, 45);
		setupWindow.getContentPane().add(firstThingsLabel);

		JLabel gameDaysLabel = new JLabel("How many days would you like your game to last?");
		gameDaysLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		gameDaysLabel.setForeground(Color.BLACK);
		gameDaysLabel.setBackground(new Color(240, 240, 240));
		gameDaysLabel.setHorizontalAlignment(SwingConstants.LEFT);
		gameDaysLabel.setBounds(36, 124, 361, 58);
		setupWindow.getContentPane().add(gameDaysLabel);

		JSlider gameLengthSlider = new JSlider();
		gameLengthSlider.setValue(5);
		gameLengthSlider.setSnapToTicks(true);
		gameLengthSlider.setPaintTicks(true);
		gameLengthSlider.setPaintLabels(true);
		gameLengthSlider.setMajorTickSpacing(1);
		gameLengthSlider.setMinimum(5);
		gameLengthSlider.setMaximum(10);
		gameLengthSlider.setBounds(407, 124, 252, 58);
		setupWindow.getContentPane().add(gameLengthSlider);

		JLabel farmerNameLabel = new JLabel("What is your farmer's name?");
		farmerNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		farmerNameLabel.setBounds(61, 187, 274, 33);
		setupWindow.getContentPane().add(farmerNameLabel);

		farmerNameField = new JTextField();
		farmerNameField.setText("Bob");
		farmerNameField.setBounds(309, 189, 146, 33);
		setupWindow.getContentPane().add(farmerNameField);
		farmerNameField.setColumns(10);

		JLabel farmerAgeLabel = new JLabel("What is your farmer's age?");
		farmerAgeLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		farmerAgeLabel.setBounds(61, 226, 252, 33);
		setupWindow.getContentPane().add(farmerAgeLabel);
		
		farmerAgeField = new JTextField();
		farmerAgeField.setText("25");
		farmerAgeField.setBounds(309, 228, 146, 33);
		setupWindow.getContentPane().add(farmerAgeField);
		farmerAgeField.setColumns(10);

		JPanel farmTypePanel = new JPanel();
		farmTypePanel.setBounds(36, 262, 620, 119);
		setupWindow.getContentPane().add(farmTypePanel);
		farmTypePanel.setLayout(null);

		JLabel farmTypeLabel = new JLabel("What type of farm would you like?");
		farmTypeLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		farmTypeLabel.setBounds(0, 0, 620, 21);
		farmTypePanel.add(farmTypeLabel);

		JRadioButton earthFarmRadio = new JRadioButton("Earth Farm", true);
		earthFarmRadio.setBounds(38, 21, 79, 21);
		farmTypePanel.add(earthFarmRadio);
		earthFarmRadio.setActionCommand("earth");

		JRadioButton marsFarmRadio = new JRadioButton("Mars Farm");
		marsFarmRadio.setBounds(38, 45, 109, 23);
		farmTypePanel.add(marsFarmRadio);
		marsFarmRadio.setActionCommand("mars");

		JRadioButton venusFarmRadio = new JRadioButton("Venus Farm");
		venusFarmRadio.setBounds(38, 71, 109, 23);
		farmTypePanel.add(venusFarmRadio);
		venusFarmRadio.setActionCommand("venus");

		JRadioButton jupiterFarmRadio = new JRadioButton("Jupiter Farm");
		jupiterFarmRadio.setBounds(38, 95, 109, 23);
		farmTypePanel.add(jupiterFarmRadio);
		jupiterFarmRadio.setActionCommand("jupiter");

		ButtonGroup farmTypeGroup = new ButtonGroup();
		farmTypeGroup.add(earthFarmRadio);
		farmTypeGroup.add(marsFarmRadio);
		farmTypeGroup.add(venusFarmRadio);
		farmTypeGroup.add(jupiterFarmRadio);

		JTextPane farmBonuses = new JTextPane();
		farmBonuses.setEditable(false);
		farmBonuses.setBackground(UIManager.getColor("SplitPane.background"));
		farmBonuses.setFont(new Font("Tahoma", Font.PLAIN, 14));
		farmBonuses.setText("Starting Money: $1000\r\nStarting Animals: Chicken\r\nStarting Crops: Barley, Wheat\r\nBonuses: Animal Happiness +1");
		farmBonuses.setBounds(234, 34, 354, 74);
		farmTypePanel.add(farmBonuses);

		RadioListener farmTypeListener = new RadioListener(farmBonuses, farmTypeGroup);
		earthFarmRadio.addActionListener(farmTypeListener);
		marsFarmRadio.addActionListener(farmTypeListener);
		venusFarmRadio.addActionListener(farmTypeListener);
		jupiterFarmRadio.addActionListener(farmTypeListener);

		JLabel farmNameLabel = new JLabel("What is the name of your farm?");
		farmNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		farmNameLabel.setBounds(36, 392, 228, 33);
		setupWindow.getContentPane().add(farmNameLabel);

		farmNameField = new JTextField();
		farmNameField.setText("Bobs Farm");
		farmNameField.setBounds(36, 427, 182, 33);
		setupWindow.getContentPane().add(farmNameField);
		farmNameField.setColumns(10);

		JLabel farmerNameError = new JLabel("Error");
		farmerNameError.setVisible(false);
		farmerNameError.setForeground(Color.RED);
		farmerNameError.setBounds(465, 187, 219, 35);
		setupWindow.getContentPane().add(farmerNameError);

		JLabel farmerAgeError = new JLabel("Error");
		farmerAgeError.setForeground(Color.RED);
		farmerAgeError.setVisible(false);
		farmerAgeError.setBounds(465, 226, 219, 33);
		setupWindow.getContentPane().add(farmerAgeError);

		JLabel farmNameError = new JLabel("Error");
		farmNameError.setVisible(false);
		farmNameError.setForeground(Color.RED);
		farmNameError.setBounds(228, 427, 228, 33);
		setupWindow.getContentPane().add(farmNameError);

		JButton beginGameButton = new JButton("Begin Game");
		beginGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isValid()) {
					game.setTotalDays(gameLengthSlider.getValue());
					game.initiateFarmer(farmerNameField.getText(), Integer.parseInt(farmerAgeField.getText()));
					game.initiateFarm(farmTypeGroup.getSelection().getActionCommand(), farmNameField.getText());
					finishedWindow();
				}
			}

			private boolean isValid() {
				boolean allValid = true;
				String farmerName = farmerNameField.getText();
				String farmName = farmNameField.getText();
				int farmerAge;

				if(!checkName(farmerName, farmerNameError)) {
					allValid = false;
				}

				if (!checkName(farmName, farmNameError)) {
					allValid = false;
				}

				try {
					farmerAge = Integer.parseInt(farmerAgeField.getText());
					if (farmerAge <= 0 || farmerAge > 100){
						allValid = false;
						farmerAgeError.setText("Age must be from 1 to 100");
						farmerAgeError.setVisible(true);
					}
					else {
						farmerAgeError.setVisible(false);
					}
				} catch (NumberFormatException e) {
					farmerAgeError.setText("Age must be an integer");
					farmerAgeError.setVisible(true);
					allValid = false;

				}


				return allValid;
			}

			private boolean checkName(String name, JLabel errorLabel) {
				boolean valid = true;
				if (name.length() < 3 || name.length() > 15) {
					valid = false;
					errorLabel.setText("Name must be 3 to 15 characters long");
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

		});
		beginGameButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		beginGameButton.setBounds(513, 392, 146, 68);
		setupWindow.getContentPane().add(beginGameButton);
	}

}

class RadioListener implements ActionListener{
	private JTextPane text;
	private ButtonGroup radioGroup;

	RadioListener(JTextPane newText, ButtonGroup farmTypeGroup){
		text = newText;
		radioGroup = farmTypeGroup;
	}

	public void actionPerformed(ActionEvent e) {

		String button = radioGroup.getSelection().getActionCommand();
		if (button == "earth") {
			text.setText("Starting Money: $1000\r\nStarting Animals: Chicken\r\nStarting Crops: Barley, Wheat\r\nBonuses: Animal Happiness +1");
		}
		else if (button == "mars") {
			text.setText("Starting Money: $800\r\nStarting Animals: Cow\r\nStarting Crops: Maize, Potato\r\nBonuses: Crop Harvest Time -1");
		}
		else if (button == "venus") {
			text.setText("Starting Money: $1500\r\nStarting Animals: Sheep\r\nStarting Crops: Pumpkin, Kale\r\nBonuses: N/A");
		}
		else if (button == "jupiter") {
			text.setText("Starting Money: $500\r\nStarting Animals: Cow, Sheep\r\nStarting Crops: Wheat, Maize, Pumpkin\r\nBonuses: Animal Happiness +1, Crop Harvest Time -1");
		}
	}
}