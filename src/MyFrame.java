import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JOptionPane;

public class MyFrame extends JFrame implements ActionListener {

	// Global variables
	
	// Buttons
	private JButton button0, button1, button2, button3, button4, button5, button6, button7, button8,
		button9, button10, button11, button12, roll;
	
	// Textfields
	private JTextField field0, field1, field2, field3, field4, field5, fieldBonus, field6, field7, field8, field9,
	 	field10, field11, field12, field13, field14, field15, field16, field17, 
	 	rollsRemaining, rounds, totalScore;
	
	// Checkboxes
	private JCheckBox check1, check2, check3, check4, check5, checkBonus;
	
	private JPanel lowerSection, upperSection;
	private JMenuItem exit;
	
	// Array of textfields to simulate the score table
	// [0 : aces, 1: twos, ..., 6: 3 of a kind, 7: 4 of a kind, ...]
	private JTextField[] scoreTable;
	
	// Array of 0s and 1s to specify which Die to keep and which to reroll
	private int[] diesToReroll = new int[5];
	
	// The number of rounds
	private int noRounds = 1;
	
	// The number rerolls remaining
	private int rollsLeft = 2;
	
	// The main logic for the game
	private YahtzeeMain game = new YahtzeeMain();
	
	// Font for selected and unselected score
	private Font selected;
	private Font unselected;
	
	public MyFrame() {
		// Initializing frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(2, 1));
		this.setTitle("Yahtzee Game");
		this.setResizable(false);
		this.setSize(550, 500);
		this.getContentPane().setBackground(Color.WHITE);
		
		// Initializing menu bar
		JMenuBar menuBar = new JMenuBar();
		JMenu game = new JMenu("Game");
		JMenu tutorial = new JMenu("Tutorial");
		JMenu about = new JMenu("About");
		exit = new JMenuItem("Exit");
		exit.addActionListener(this);
		game.add(exit);
		menuBar.add(game);
		menuBar.add(tutorial);
		menuBar.add(about);
		this.setJMenuBar(menuBar);
		
		// Initializing top panel: top-half part of frame
		JPanel topPanel = new JPanel();
		topPanel.setBorder(BorderFactory.createEtchedBorder());
		topPanel.setLayout(new GridLayout(1, 3));
		
		// Initializing lower section panel: left-most panel within top panel
		lowerSection = new JPanel();
		lowerSection.setBorder(BorderFactory.createEtchedBorder());
		lowerSection.setLayout(new GridLayout(7, 1));
		
		JPanel group1 = new JPanel();
		group1.setBorder(BorderFactory.createLoweredBevelBorder());
		button0 = new JButton("Aces");
		button0.addActionListener(this);
		field0 = new JTextField("     ");
		field0.setEditable(false);
		group1.add(button0);
		group1.add(field0);
		
		JPanel group2 = new JPanel();
		group2.setBorder(BorderFactory.createLoweredBevelBorder());
		button1 = new JButton("Twos");
		button1.addActionListener(this);
		field1 = new JTextField("     ");
		field1.setEditable(false);
		group2.add(button1);
		group2.add(field1);
		
		JPanel group3 = new JPanel();
		group3.setBorder(BorderFactory.createLoweredBevelBorder());
		button2 = new JButton("Threes");
		button2.addActionListener(this);
		field2 = new JTextField("     ");
		field2.setEditable(false);
		group3.add(button2);
		group3.add(field2);
		
		JPanel group4 = new JPanel();
		group4.setBorder(BorderFactory.createLoweredBevelBorder());
		button3 = new JButton("Fours");
		button3.addActionListener(this);
		field3 = new JTextField("     ");
		field3.setEditable(false);
		group4.add(button3);
		group4.add(field3);
		
		JPanel group5 = new JPanel();
		group5.setBorder(BorderFactory.createLoweredBevelBorder());
		button4 = new JButton("Fives");
		button4.addActionListener(this);
		field4 = new JTextField("     ");
		field4.setEditable(false);
		group5.add(button4);
		group5.add(field4);
		
		JPanel group6 = new JPanel();
		group6.setBorder(BorderFactory.createLoweredBevelBorder());
		button5 = new JButton("Sixes");
		button5.addActionListener(this);
		field5 = new JTextField("     ");
		field5.setEditable(false);
		group6.add(button5);
		group6.add(field5);
		
		JPanel groupBonus = new JPanel();
		groupBonus.setBorder(BorderFactory.createLoweredBevelBorder());
		checkBonus = new JCheckBox();
		checkBonus.setEnabled(false);
		JLabel bonus = new JLabel("Bonus: ");
		fieldBonus = new JTextField("     ");
		fieldBonus.setEditable(false);
		groupBonus.add(checkBonus);
		groupBonus.add(bonus);
		groupBonus.add(fieldBonus);
		
		lowerSection.add(group1);
		lowerSection.add(group2);
		lowerSection.add(group3);
		lowerSection.add(group4);
		lowerSection.add(group5);
		lowerSection.add(group6);
		lowerSection.add(groupBonus);

		// Initializing upper section panel: middle panel within top panel
		upperSection = new JPanel();
		upperSection.setBorder(BorderFactory.createEtchedBorder());
		upperSection.setLayout(new GridLayout(7, 1));
		
		JPanel group7 = new JPanel();
		group7.setBorder(BorderFactory.createLoweredBevelBorder());
		button6 = new JButton("3 of a kind");
		button6.addActionListener(this);
		field6 = new JTextField("     ");
		field6.setEditable(false);
		group7.add(button6);
		group7.add(field6);
		
		JPanel group8 = new JPanel();
		group8.setBorder(BorderFactory.createLoweredBevelBorder());
		button7 = new JButton("4 of a kind");
		button7.addActionListener(this);
		field7 = new JTextField("     ");
		field7.setEditable(false);
		group8.add(button7);
		group8.add(field7);
		
		JPanel group9 = new JPanel();
		group9.setBorder(BorderFactory.createLoweredBevelBorder());
		button8 = new JButton("Full house");
		button8.addActionListener(this);
		field8 = new JTextField("     ");
		field8.setEditable(false);
		group9.add(button8);
		group9.add(field8);
		
		JPanel group10 = new JPanel();
		group10.setBorder(BorderFactory.createLoweredBevelBorder());
		button9 = new JButton("Small straight");
		button9.addActionListener(this);
		field9 = new JTextField("     ");
		field9.setEditable(false);
		group10.add(button9);
		group10.add(field9);
		
		JPanel group11 = new JPanel();
		group11.setBorder(BorderFactory.createLoweredBevelBorder());
		button10 = new JButton("Large straight");
		button10.addActionListener(this);
		field10 = new JTextField("     ");
		field10.setEditable(false);
		group11.add(button10);
		group11.add(field10);
		
		JPanel group12 = new JPanel();
		group12.setBorder(BorderFactory.createLoweredBevelBorder());
		button11 = new JButton("Yahtzee");
		button11.addActionListener(this);
		field11 = new JTextField("         ");
		field11.setEditable(false);
		group12.add(button11);
		group12.add(field11);
		
		JPanel groupChance = new JPanel();
		groupChance.setBorder(BorderFactory.createLoweredBevelBorder());
		button12 = new JButton("Chance");
		button12.addActionListener(this);
		field12 = new JTextField("     ");
		field12.setEditable(false);
		groupChance.add(button12);
		groupChance.add(field12);
		
		upperSection.add(group7);
		upperSection.add(group8);
		upperSection.add(group9);
		upperSection.add(group10);
		upperSection.add(group11);
		upperSection.add(group12);
		upperSection.add(groupChance);
		
		scoreTable = new JTextField[] {field0, field1, field2, field3, field4, field5, field6, field7,
				field8, field9, field10, field11, field12};
		selected = new Font(field0.getFont().getName(), Font.BOLD , field0.getFont().getSize());
		unselected = new Font(field0.getFont().getName(), Font.PLAIN , field0.getFont().getSize());
		
		// Initializing die panel: right-most panel within top panel
		JPanel diePanel = new JPanel();
		diePanel.setBorder(BorderFactory.createEtchedBorder());
		diePanel.setLayout(new GridLayout(6, 1));
		
		JPanel group13 = new JPanel();
		group13.setBorder(BorderFactory.createLoweredBevelBorder());
		check1 = new JCheckBox();
		check1.addActionListener(this);
		JLabel label1 = new JLabel("Die 1");
		field13 = new JTextField("     ");
		field13.setEditable(false);
		group13.add(check1);
		group13.add(label1);
		group13.add(field13);
		
		JPanel group14 = new JPanel();
		group14.setBorder(BorderFactory.createLoweredBevelBorder());
		check2 = new JCheckBox();
		JLabel label2 = new JLabel("Die 2");
		check2.addActionListener(this);
		field14 = new JTextField("     ");
		field14.setEditable(false);
		group14.add(check2);
		group14.add(label2);
		group14.add(field14);
		
		JPanel group15 = new JPanel();
		group15.setBorder(BorderFactory.createLoweredBevelBorder());
		check3 = new JCheckBox();
		check3.addActionListener(this);
		JLabel label3 = new JLabel("Die 3");
		field15 = new JTextField("     ");
		field15.setEditable(false);
		group15.add(check3);
		group15.add(label3);
		group15.add(field15);
		
		JPanel group16 = new JPanel();
		group16.setBorder(BorderFactory.createLoweredBevelBorder());
		check4 = new JCheckBox();
		check4.addActionListener(this);
		JLabel label4 = new JLabel("Die 4");
		field16 = new JTextField("     ");
		field16.setEditable(false);
		group16.add(check4);
		group16.add(label4);
		group16.add(field16);
		
		JPanel group17 = new JPanel();
		group17.setBorder(BorderFactory.createLoweredBevelBorder());
		check5 = new JCheckBox();
		check5.addActionListener(this);
		JLabel label5 = new JLabel("Die 5");
		field17 = new JTextField("     ");
		field17.setEditable(false);
		group17.add(check5);
		group17.add(label5);
		group17.add(field17);
		
		JPanel group18 = new JPanel();
		group18.setBorder(BorderFactory.createLoweredBevelBorder());
		roll = new JButton("Roll Dice");
		roll.addActionListener(this);
		group18.add(roll);
		diePanel.add(group13);
		diePanel.add(group14);
		diePanel.add(group15);
		diePanel.add(group16);
		diePanel.add(group17);
		diePanel.add(group18);
		
		// Adding three panels (lowerSection, upperSection, diePanel) to top panel
		topPanel.add(lowerSection);
		topPanel.add(upperSection);
		topPanel.add(diePanel);
		
		// Initializing bottom panel: bottom-half part of frame
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBorder(BorderFactory.createEtchedBorder());
		bottomPanel.setLayout(new FlowLayout());
		JTextArea rules = new JTextArea(13, 32);
		rules.setLineWrap(true);
		rules.setWrapStyleWord(true);
		rules.setFont(new Font("Times New Roman", Font.ITALIC, 12));
		rules.setText("Yahtzee Game Rules \n\n"
				+ "a. The game lasts 13 rounds of rolling and scoring.\n" + 
				" \n" + 
				"b. The player may re-roll any number of the 5 dice an additional 2 times per round, totaling to 3 dice rolls per round. That is, you roll the dice and then choose to reroll a subset of dice a second time, then again for the third time. When rerolling the dice in a round, you may reroll any subset of dice or all of the dice per reroll.\n" +
				" \n" +
				"c. At the end of the 13th round, all the boxes which can be scored in must be filled. That is, all unscored boxes will be defaulted with a 0.\n" + 
				" \n" +
				"d. For the upper section, if the user has any 1's in the roll, they can add the total of the 1’s to the Aces column.\n" + 
				" \n" +
				"e. For the upper section, if the user has any 2's in the roll, they can add the total of the 2’s to the Twos column.\n" + 
				" \n" +
				"f. For the upper section, if the user has any 3's in the roll, they can add the total of the 3’s to the Threes column.\n" + 
				" \n" +
				"g. For the upper section, if the user has any 4's in the roll, they can add the total of the 4’s to the Fours column.\n" + 
				" \n" +
				"h. For the upper section, if the user has any 5's in the roll, they can add the total of the 5’s to the Fives column.\n" + 
				" \n" +
				"i. For the upper section, if the user has any 6's in the roll, they can add the total of the 6’s to the Sixes column.\n" + 
				" \n" +
				"j. If the user has over 63 points in the upper section, then they receive a bonus of 35 points.\n" + 
				" \n" +
				"k. For the lower section, if the user has a 3 of a kind, they will add the total of all 5 dice to\n" + 
				"the box.\n" + 
				" \n" +
				"l. For the lower section, if the user has a 4 of a kind, they will add the total of all 5 dice to\n" + 
				"the box.\n" + 
				" \n" +
				"m. For the lower section, if the user has a full house (a three of a kind and a pair), they will\n" + 
				"add 25 to the box.\n" + 
				" \n" +
				"n. For the lower section, if the user has a small straight (four consecutive faces (1, 2, 3, 4, X)" + 
				"where X can be any value), they will add 30 to the box.\n" + 
				" \n" +
				"o. For the lower section, if the user has a large straight (five consecutive faces (1, 2, 3, 4, 5))," + 
				"they will add 40 to the box.\n" + 
				" \n" +
				"p. For the lower section, if the user has a Yahtzee (a five of a kind), they will add 50 to the" + 
				"box.\n" + 
				" \n" +
				"q. For the lower section, if the user chooses to use the chance box, they will sum the 5 dice" + 
				"add the score to the box.\n" + 
				" \n" +
				"r. For the lower section, if the user scored a Yahtzee previously, they will receive a 100 point" + 
				"bonus per Yahtzee scored.");
		rules.setEditable(false);
		rules.setBorder(BorderFactory.createLoweredBevelBorder());
		JScrollPane scrollPane = new JScrollPane(rules);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		JPanel outputPanel = new JPanel();
		outputPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		outputPanel.setLayout(new GridLayout(3, 1));
		rollsRemaining = new JTextField("Rolls left:     ");
		rollsRemaining.setEditable(false);
		rounds = new JTextField("Round:  1");
		rounds.setEditable(false);
		totalScore = new JTextField("Score:  0");
		totalScore.setEditable(false);
		outputPanel.add(rollsRemaining);
		outputPanel.add(rounds);
		outputPanel.add(totalScore);
		
		// Adding rules scrollPane and outputPanel (rolls, round, score) to bottom panel
		bottomPanel.add(scrollPane);
		bottomPanel.add(outputPanel);
		
		// Adding top and bottom panels to frame
		this.add(topPanel);
		this.add(bottomPanel);

		// Making frame visible
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == exit) {
			System.exit(0);
		} else if (e.getSource() == roll) {
			isChecked();
			if (game.reroll(diesToReroll)) {
				appendDieValue();
				rollsRemaining.setText("Rolls left:   " + rollsLeft--);
				appendUpperScore();
				appendLowerScore();
			} else {
				JOptionPane.showMessageDialog(new JFrame(), "Max number of rolls reached");
			}
		} else if (e.getSource() == button0) {
			setAces();
		} else if (e.getSource() == button1) {
			setTwos();
		} else if (e.getSource() == button2) {
			setThrees();
		} else if (e.getSource() == button3) {
			setFours();
		} else if (e.getSource() == button4) {
			setFives();
		} else if (e.getSource() == button5) {
			setSixes();
		} else if (e.getSource() == button6) {
			setThreeOKind();
		} else if (e.getSource() == button7) {
			setFourOKind();
		} else if (e.getSource() == button8) {
			setFullHouse();
		} else if (e.getSource() == button9) {
			setSmallStraight();
		} else if (e.getSource() == button10) {
			setLargeStraight();
		} else if (e.getSource() == button11) {
			setYahtzee();
		} else if (e.getSource() == button12) {
			setChance();
		}
	}
	
	/**
	 * Check which Die is checked to be kept and update the diesToReroll
	 * array accordingly
	 */
	private void isChecked() {
		if (check1.isSelected()) {
			diesToReroll[0] = 0;
		} else {
			diesToReroll[0] = 1;
		}
		
		if (check2.isSelected()) {
			diesToReroll[1] = 0;
		} else {
			diesToReroll[1] = 1;
		}
		
		if (check3.isSelected()) {
			diesToReroll[2] = 0;
		} else {
			diesToReroll[2] = 1;
		}
		
		if (check4.isSelected()) {
			diesToReroll[3] = 0;
		} else {
			diesToReroll[3] = 1;
		}
		
		if (check5.isSelected()) {
			diesToReroll[4] = 0;
		} else {
			diesToReroll[4] = 1;
		}
	}
	
	/**
	 * Display the value for each die
	 */
	private void appendDieValue() {
		field13.setText(game.getDieValue(0));
		field14.setText(game.getDieValue(1));
		field15.setText(game.getDieValue(2));
		field16.setText(game.getDieValue(3));
		field17.setText(game.getDieValue(4));
	}
	
	/**
	 * Display the scoring options for the upper section
	 */
	private void appendUpperScore() {
		for (int i = 0; i < 6; i++) {
			JPanel temp = (JPanel) lowerSection.getComponent(i);
			JTextField fieldToAppend = (JTextField) temp.getComponent(1);
			if (!game.isScoreFilled(i)) {
				fieldToAppend.setText(Integer.toString(game.getDieSum(i + 1)));
			} else {
				fieldToAppend.setText(Integer.toString(game.getScore(i)));
			}
		}
	}
	
	/**
	 * Driver for displaying the scoring options for the lower section
	 */
	private void appendLowerScore() {
		appendThreesOKind();
		appendFoursOKind();
		appendFullHouse();
		appendSmallStraight();
		appendLargeStraight();
		appendYahtzee();
		appendChance();
	}
	
	/**
	 * Display the scoring option for 3 of a kind, if the section is already
	 * filled, retrieve the stored score from the score array in the game
	 */
	private void appendThreesOKind() {
		if (!game.isScoreFilled(6)) {
			field6.setText(Integer.toString(game.getSameDie()));
		} else {
			field6.setText(Integer.toString(game.getScore(6)));
		}
	}
	
	/**
	 * Display the scoring option for 4 of a kind, if the section is already
	 * filled, retrieve the stored score from the score array in the game
	 */
	private void appendFoursOKind() {
		if (!game.isScoreFilled(7)) {
			field7.setText(Integer.toString(game.getSameDie()));
		} else {
			field7.setText(Integer.toString(game.getScore(7)));
		}
	}
	
	/**
	 * Display the scoring option for full house, if the section is already
	 * filled, retrieve the stored score from the score array in the game
	 */
	private void appendFullHouse() {
		if (!game.isScoreFilled(8)) {
			field8.setText(Integer.toString(game.getFullHouse()));
		} else {
			field8.setText(Integer.toString(game.getScore(8)));
		}
	}
	
	/**
	 * Display the scoring option for small straight, if the section is already
	 * filled, retrieve the stored score from the score array in the game
	 */
	private void appendSmallStraight() {
		if (!game.isScoreFilled(9)) {
			field9.setText(Integer.toString(game.getSmallStraight()));
		} else {
			field9.setText(Integer.toString(game.getScore(9)));
		}
	}
	
	/**
	 * Display the scoring option for large straight, if the section is already
	 * filled, retrieve the stored score from the score array in the game
	 */
	private void appendLargeStraight() {
		if (!game.isScoreFilled(10)) {
			field10.setText(Integer.toString(game.getLargeStraight()));
		} else {
			field10.setText(Integer.toString(game.getScore(10)));
		}
	}
	
	/**
	 * Display the scoring option for Yahtzee, if the section is already
	 * filled, retrieve the stored score from the score array in the game
	 */
	private void appendYahtzee() {
		field11.setText(Integer.toString(game.getYahtzee()));
	}
	
	/**
	 * Display the scoring option for Chance, if the section is already
	 * filled, retrieve the stored score from the score array in the game
	 */
	private void appendChance() {
		if (!game.isScoreFilled(12)) {
			field12.setText(Integer.toString(game.getRollSum()));
		} else {
			field12.setText(Integer.toString(game.getScore(12)));
		}
	}
	
	/**
	 * Store the score for aces in the game's score array. If the section is already
	 * filled, inform the user.
	 */
	private void setAces() {
		if (game.isScoreFilled(0)) {
			JOptionPane.showMessageDialog(new JFrame(), "Score already filled");
		} else {
			game.storeScore(0, Integer.parseInt(field0.getText()));
			field0.setFont(selected);
			newRound();
		}
	}
	
	/**
	 * Store the score for twos in the game's score array. If the section is already
	 * filled, inform the user.
	 */
	private void setTwos() {
		if (game.isScoreFilled(1)) {
			JOptionPane.showMessageDialog(new JFrame(), "Score already filled");
		} else {
			game.storeScore(1, Integer.parseInt(field1.getText()));
			field1.setFont(selected);
			newRound();
		}
	}
	
	/**
	 * Store the score for threes in the game's score array. If the section is already
	 * filled, inform the user.
	 */
	private void setThrees() {
		if (game.isScoreFilled(2)) {
			JOptionPane.showMessageDialog(new JFrame(), "Score already filled");
		} else {
			game.storeScore(2, Integer.parseInt(field2.getText()));
			field2.setFont(selected);
			newRound();
		}
	}
	
	/**
	 * Store the score for fours in the game's score array. If the section is already
	 * filled, inform the user.
	 */
	private void setFours() {
		if (game.isScoreFilled(3)) {
			JOptionPane.showMessageDialog(new JFrame(), "Score already filled");
		} else {
			game.storeScore(3, Integer.parseInt(field3.getText()));
			field3.setFont(selected);
			newRound();
		}
	}
	
	/**
	 * Store the score for fives in the game's score array. If the section is already
	 * filled, inform the user.
	 */
	private void setFives() {
		if (game.isScoreFilled(4)) {
			JOptionPane.showMessageDialog(new JFrame(), "Score already filled");
		} else {
			game.storeScore(4, Integer.parseInt(field4.getText()));
			field4.setFont(selected);
			newRound();
		}
	}
	
	/**
	 * Store the score for sixes in the game's score array. If the section is already
	 * filled, inform the user.
	 */
	private void setSixes() {
		if (game.isScoreFilled(5)) {
			JOptionPane.showMessageDialog(new JFrame(), "Score already filled");
		} else {
			game.storeScore(5, Integer.parseInt(field5.getText()));
			field5.setFont(selected);
			newRound();
		}
	}
	
	/**
	 * Store the score for 3 of a kind in the game's score array. If the section is already
	 * filled, inform the user.
	 */
	private void setThreeOKind() {
		if (game.isScoreFilled(6)) {
			JOptionPane.showMessageDialog(new JFrame(), "Score already filled");
		} else {
			game.storeScore(6, Integer.parseInt(field6.getText()));
			field6.setFont(selected);
			newRound();
		}
	}
	
	/**
	 * Store the score for 4 of a kind in the game's score array. If the section is already
	 * filled, inform the user.
	 */
	private void setFourOKind() {
		if (game.isScoreFilled(7)) {
			JOptionPane.showMessageDialog(new JFrame(), "Score already filled");
		} else {
			game.storeScore(7, Integer.parseInt(field7.getText()));
			field7.setFont(selected);
			newRound();
		}
	}
	
	/**
	 * Store the score for full house in the game's score array. If the section is already
	 * filled, inform the user.
	 */
	private void setFullHouse() {
		if (game.isScoreFilled(8)) {
			JOptionPane.showMessageDialog(new JFrame(), "Score already filled");
		} else {
			game.storeScore(8, Integer.parseInt(field8.getText()));
			field8.setFont(selected);
			newRound();
		}
	}
	
	/**
	 * Store the score for small straight in the game's score array. If the section is already
	 * filled, inform the user.
	 */
	private void setSmallStraight() {
		if (game.isScoreFilled(9)) {
			JOptionPane.showMessageDialog(new JFrame(), "Score already filled");
		} else {
			game.storeScore(9, Integer.parseInt(field9.getText()));
			field9.setFont(selected);
			newRound();
		}
	}
	
	/**
	 * Store the score for large straight in the game's score array. If the section is already
	 * filled, inform the user.
	 */
	private void setLargeStraight() {
		if (game.isScoreFilled(10)) {
			JOptionPane.showMessageDialog(new JFrame(), "Score already filled");
		} else {
			game.storeScore(10, Integer.parseInt(field10.getText()));
			field10.setFont(selected);
			newRound();
		}
	}
	
	/**
	 * Store the score for Yahtzee in the game's score array. If the section is already
	 * filled, inform the user.
	 */
	private void setYahtzee() {
		if (game.isScoreFilled(11)) {
			JOptionPane.showMessageDialog(new JFrame(), "Score already filled");
		} else {
			game.storeScore(11, Integer.parseInt(field11.getText()));
			field11.setFont(selected);
			newRound();
		}
	}
	
	/**
	 * Store the score for Chance in the game's score array. If the section is already
	 * filled, inform the user.
	 */
	private void setChance() {
		if (game.isScoreFilled(12)) {
			JOptionPane.showMessageDialog(new JFrame(), "Score already filled");
		} else {
			game.storeScore(12, Integer.parseInt(field12.getText()));
			field12.setFont(selected);
			newRound();
		}
	}
	
	/**
	 * Start a new round, if the number of rounds reached 13, run newGame() instead
	 * Reset the text fields and update the output field
	 */
	private void newRound() {
		
		if (game.hasBonus()) {
			checkBonus.setSelected(true);
			fieldBonus.setText("35");
		}
		if (noRounds == 13) {
			newGame();
			return;
		}
		
		rounds.setText("Round:  " + (++noRounds));
		totalScore.setText("Score:  " + game.totalScore());
		rollsLeft = 2;
		
		field13.setText("");
		field14.setText("");
		field15.setText("");
		field16.setText("");
		field17.setText("");
		
		check1.setSelected(false);
		check2.setSelected(false);
		check3.setSelected(false);
		check4.setSelected(false);
		check5.setSelected(false);
		
		for (int i = 0; i < 13; i++) {
			if (!game.isScoreFilled(i)) {
				scoreTable[i].setText("");
			} else {
				scoreTable[i].setText(Integer.toString(game.getScore(i)));
			}
		}
	}
	
	/**
	 * Prompt the user if they want to start a new game. If selected YES, start a new instance
	 * of the game. If selected NO, terminate the program
	 */
	private void newGame() {
		rounds.setText("Round:  " + noRounds);
		totalScore.setText("Score:  " + game.totalScore());
		
		if (JOptionPane.showConfirmDialog(null, "Thank you for playing \n Your total score is: " + game.totalScore() +
			"\n Would you like to start a new game?", "Game Over", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
		    // yes option
			game = new YahtzeeMain();
			
			noRounds = 1;
			rollsLeft = 2;
			rollsRemaining.setText("Rolls left:     ");
			rounds.setText("Round:  1");
			totalScore.setText("Score:  0");
			
			field13.setText("");
			field14.setText("");
			field15.setText("");
			field16.setText("");
			field17.setText("");
			
			check1.setSelected(false);
			check2.setSelected(false);
			check3.setSelected(false);
			check4.setSelected(false);
			check5.setSelected(false);
			
			for (int i = 0; i < 13; i++) {
				scoreTable[i].setText("");
				scoreTable[i].setFont(unselected);
			}
			
		} else {
		    // no option
			System.exit(0);
		}
	}
}
