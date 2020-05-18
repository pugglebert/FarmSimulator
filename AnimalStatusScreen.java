package farmSimulatorGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.awt.GridLayout;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JProgressBar;
import java.awt.Color;

/**
 * Window to display the health and happiness of each animal owned by the farmer
 * @author David Frost, Ella Johnson
 *
 */
public class AnimalStatusScreen {

	/**
	 * Main animal status screen window
	 */
	private JFrame animalStatusWindow;
	
	/**
	 * Game environment which controls window
	 */
	private GameEnvironment game;
	
	/**
	 * Displays cow health and happiness
	 */
	private JPanel cowPanel;
	
	/**
	 * Displays sheep health and happiness
	 */
	private JPanel sheepPanel;
	
	/**
	 * Displays chicken health and happiness
	 */
	private JPanel chickenPanel;
	
	/**
	 * Initializes display of animal information and makes animal status screen visible
	 * @param newGame Game environment which controls animal status screen
	 */
	public AnimalStatusScreen(GameEnvironment newGame) {
		game = newGame;	
		initialize();
		setUpAnimals();
		animalStatusWindow.setVisible(true);
	}
	
	/**
	 * Close animal status screen
	 */
	public void closeWindow() {
		animalStatusWindow.dispose();
	}

	/**
	 * Dispose of animal status screen when main game finished
	 */
	public void finishedWindow() {
		game.getMainScreen().closeAnimalWindow(this);
	}	
		
	/**
	 * Initialize three columns which display health and happiness for each of the three types of
	 * animal
	 */
	private void initialize() {
		animalStatusWindow = new JFrame();
		animalStatusWindow.setResizable(false);
		animalStatusWindow.setTitle("Animal Status");
		animalStatusWindow.setBounds(100, 100, 700, 500);
		animalStatusWindow.setLocationRelativeTo(null);
		animalStatusWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		animalStatusWindow.getContentPane().setLayout(null);
		
		cowPanel = new JPanel();
		cowPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		cowPanel.setBounds(12, 49, 215, 345);
		animalStatusWindow.getContentPane().add(cowPanel);
		cowPanel.setLayout(new GridLayout(10, 1, 1, 1));
		
		sheepPanel = new JPanel();
		sheepPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		sheepPanel.setBounds(239, 49, 215, 345);
		animalStatusWindow.getContentPane().add(sheepPanel);
		sheepPanel.setLayout(new GridLayout(10, 1, 1, 1));
		
		chickenPanel = new JPanel();
		chickenPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		chickenPanel.setBounds(466, 49, 215, 345);
		animalStatusWindow.getContentPane().add(chickenPanel);
		chickenPanel.setLayout(new GridLayout(10, 1, 1, 1));
		
		// Button to return to farm
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		backButton.setBounds(269, 408, 155, 52);
		animalStatusWindow.getContentPane().add(backButton);
		
		JLabel cowLabel = new JLabel("Cows");
		cowLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cowLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cowLabel.setBounds(12, 11, 215, 27);
		animalStatusWindow.getContentPane().add(cowLabel);
		
		JLabel sheepLabel = new JLabel("Sheep");
		sheepLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		sheepLabel.setHorizontalAlignment(SwingConstants.CENTER);
		sheepLabel.setBounds(239, 11, 215, 27);
		animalStatusWindow.getContentPane().add(sheepLabel);
		
		JLabel chickenLabel = new JLabel("Chickens");
		chickenLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		chickenLabel.setHorizontalAlignment(SwingConstants.CENTER);
		chickenLabel.setBounds(466, 11, 215, 27);
		animalStatusWindow.getContentPane().add(chickenLabel);
	}
	
	/**
	 * Get information on animal health and happiness from health and display that information
	 * as a progress bars for each animal
	 */
	private void setUpAnimals() {
		for (Animal animal : game.getFarm().getAnimals()) {
			JPanel animalPanel = new JPanel();
			animalPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			animalPanel.setLayout(new GridLayout(2, 2, 0, 0));
			
			JLabel happinessLabel = new JLabel("Happiness:");
			happinessLabel.setHorizontalAlignment(SwingConstants.CENTER);
			animalPanel.add(happinessLabel);
			
			JProgressBar happinessBar = new JProgressBar();
			happinessBar.setString("" + animal.getHappiness() + "/10");
			happinessBar.setStringPainted(true);
			happinessBar.setMaximum(10);
			happinessBar.setValue(animal.getHappiness());
			happinessBar.setForeground(new Color(0, 180, 0));
			animalPanel.add(happinessBar);
			
			JLabel healthLabel = new JLabel("Health:");
			healthLabel.setHorizontalAlignment(SwingConstants.CENTER);
			animalPanel.add(healthLabel);
			
			JProgressBar healthBar = new JProgressBar();
			healthBar.setString("" + animal.getHealth() + "/10");
			healthBar.setStringPainted(true);
			healthBar.setMaximum(10);
			healthBar.setValue(animal.getHealth());
			healthBar.setForeground(Color.RED);
			animalPanel.add(healthBar);
			
			if (animal instanceof Cow) {
				cowPanel.add(animalPanel);
			}
			else if (animal instanceof Sheep) {
				sheepPanel.add(animalPanel);
			}
			else {
				chickenPanel.add(animalPanel);
			}
		}
	}
}