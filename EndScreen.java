package farmSimulatorGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JSplitPane;

public class EndScreen {

	private JFrame endWindow;
	private GameEnvironment game;
	
	public EndScreen(GameEnvironment newGame) {
		game = newGame;
		initialize();
		endWindow.setVisible(true);
	}

	public void closeWindow() {
		endWindow.dispose();
	}

	public void finishedWindow() {
		game.closeEndScreen(this);
	}	
		
	private void initialize() {
		endWindow = new JFrame();
		endWindow.setResizable(false);
		endWindow.setTitle("End Game Summary");
		endWindow.setBounds(100, 100, 700, 500);
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
		farmCongratPanel.setBounds(10, 84, 654, 14);
		congratPanel.add(farmCongratPanel);
		
		JLabel lblNewLabel = new JLabel("You spent " + game.getTotalDays() + " days on your farm, and here is what you have to show for it:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 109, 654, 22);
		congratPanel.add(lblNewLabel);
		
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
		
		JLabel cropScore = new JLabel(Double.toString(game.getCropScore()));
		cropScore.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cropScore.setHorizontalAlignment(SwingConstants.CENTER);
		scorePanel.add(cropScore);
		
		JLabel totalScore = new JLabel(Double.toString(game.calcScore()));
		totalScore.setFont(new Font("Tahoma", Font.PLAIN, 20));
		totalScore.setHorizontalAlignment(SwingConstants.CENTER);
		scorePanel.add(totalScore);
		
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
