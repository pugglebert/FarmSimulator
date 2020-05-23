package farmSimulatorGUI;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import farmSimulator.GameEnvironment;
import java.awt.Color;
import java.awt.Dimension;

/**
 * After the main game is played, end screen displays information about player's farm and their final
 * score
 * @author David Frost, Ella Johnson
 *
 */
public class EndScreen {

	/**
	 * The main endscreen window
	 */
	private JFrame endWindow;
	
	/**
	 * Game environment which controls the window
	 */
	private GameEnvironment game;
	
	/**
	 * Initialize display of information and score and makes endscreen visible
	 * @param newGame Game environment which controls window
	 */
	public EndScreen(GameEnvironment newGame) {
		game = newGame;
		initialize();
		endWindow.setVisible(true);
	}

	/**
	 * Dispose of endscreen
	 */
	public void closeWindow() {
		endWindow.dispose();
	}

	/**
	 * Close screen when player decides to end game
	 */
	public void finishedWindow() {
		game.closeEndScreen(this);
	}	
		
	/**
	 * Initializes display with information from player's time on farm and player's score
	 */
	private void initialize() {
		endWindow = new JFrame();
		endWindow.getContentPane().setBackground(new Color(0, 128, 0));
		endWindow.setResizable(false);
		endWindow.setTitle("End Game Summary");
		endWindow.setBounds(100, 100, 700, 500);
		endWindow.setPreferredSize(new Dimension(710, 510));
		endWindow.pack();
		endWindow.setLocationRelativeTo(null);
		endWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		endWindow.getContentPane().setLayout(null);
		
		JPanel congratPanel = new JPanel();
		congratPanel.setBounds(10, 11, 674, 142);
		endWindow.getContentPane().add(congratPanel);
		congratPanel.setLayout(null);
		
		JLabel congratLabel = new JLabel("Congratulations " + game.getFarmer().getName() + "!");
		congratLabel.setHorizontalAlignment(SwingConstants.CENTER);
		congratLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		congratLabel.setBounds(186, 11, 322, 46);
		congratPanel.add(congratLabel);
		
		JLabel farmCongratPanel = new JLabel("Your farm, " + game.getFarm().getName() + ", was succesfully set up on " + game.getFarm().getPlanetType() + ", making a net profit of " + game.getNetProfit() + ".");
		farmCongratPanel.setHorizontalAlignment(SwingConstants.CENTER);
		farmCongratPanel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		farmCongratPanel.setBounds(10, 68, 654, 30);
		congratPanel.add(farmCongratPanel);
		
		JLabel timeSpentLabel = new JLabel("You spent " + game.getTotalDays() + " days on your farm, and here is what you have to show for it:");
		timeSpentLabel.setHorizontalAlignment(SwingConstants.CENTER);
		timeSpentLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		timeSpentLabel.setBounds(10, 109, 654, 22);
		congratPanel.add(timeSpentLabel);
		
		JPanel scoreSummaryPanel = new JPanel();
		scoreSummaryPanel.setBounds(101, 164, 491, 222);
		endWindow.getContentPane().add(scoreSummaryPanel);
		scoreSummaryPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel attributePanel = new JPanel();
		scoreSummaryPanel.add(attributePanel);
		attributePanel.setLayout(new GridLayout(5, 1, 0, 0));
		
		JLabel blankSpacer = new JLabel("");
		blankSpacer.setHorizontalAlignment(SwingConstants.CENTER);
		attributePanel.add(blankSpacer);
		
		JLabel moneyLabel = new JLabel("Money: $" + game.getFarm().getMoney());
		moneyLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		moneyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		attributePanel.add(moneyLabel);
		
		JLabel animalLabel = new JLabel("Animals Owned: " + game.getFarm().getAnimals().size());
		animalLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		animalLabel.setHorizontalAlignment(SwingConstants.CENTER);
		attributePanel.add(animalLabel);
		
		JLabel cropLabel = new JLabel("Unharvested Crops: " + game.getFarm().getCrops().size());
		cropLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cropLabel.setHorizontalAlignment(SwingConstants.CENTER);
		attributePanel.add(cropLabel);
		
		JLabel totalScoreLabel = new JLabel("Total Score");
		totalScoreLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		totalScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		attributePanel.add(totalScoreLabel);
		
		JPanel scorePanel = new JPanel();
		scoreSummaryPanel.add(scorePanel);
		scorePanel.setLayout(new GridLayout(5, 1, 0, 0));
		
		JLabel scoreHeader = new JLabel("Score");
		scoreHeader.setFont(new Font("Tahoma", Font.PLAIN, 20));
		scoreHeader.setHorizontalAlignment(SwingConstants.CENTER);
		scorePanel.add(scoreHeader);
		
		JLabel moneyScore = new JLabel(Integer.toString(game.getMoneyScore()));
		moneyScore.setFont(new Font("Tahoma", Font.PLAIN, 20));
		moneyScore.setHorizontalAlignment(SwingConstants.CENTER);
		scorePanel.add(moneyScore);
		
		JLabel animalScore = new JLabel(Integer.toString(game.getAnimalScore()));
		animalScore.setFont(new Font("Tahoma", Font.PLAIN, 20));
		animalScore.setHorizontalAlignment(SwingConstants.CENTER);
		scorePanel.add(animalScore);
		
		JLabel cropScore = new JLabel(Integer.toString(game.getCropScore()));
		cropScore.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cropScore.setHorizontalAlignment(SwingConstants.CENTER);
		scorePanel.add(cropScore);
		
		JLabel totalScore = new JLabel(Integer.toString(game.calcScore()));
		totalScore.setFont(new Font("Tahoma", Font.PLAIN, 20));
		totalScore.setHorizontalAlignment(SwingConstants.CENTER);
		scorePanel.add(totalScore);
		
		// Close window and end game
		JButton exitButton = new JButton("Exit Game");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		exitButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		exitButton.setBounds(253, 397, 188, 63);
		endWindow.getContentPane().add(exitButton);
	}
}
