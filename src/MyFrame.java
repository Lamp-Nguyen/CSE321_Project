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

public class MyFrame extends JFrame implements ActionListener {

	// Global variables
	JMenuItem exit;
	
	MyFrame() {
		// Initializing frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(2, 1));
		this.setTitle("Yahtzee Game");
		this.setResizable(false);
		this.setSize(420, 500);
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
		JPanel lowerSection = new JPanel();
		lowerSection.setBorder(BorderFactory.createEtchedBorder());
		lowerSection.setLayout(new GridLayout(6, 1));
		JPanel group1 = new JPanel();
		group1.setBorder(BorderFactory.createLoweredBevelBorder());
		JButton button1 = new JButton("1");
		JTextField field1 = new JTextField("     ");
		field1.setEditable(false);
		group1.add(button1);
		group1.add(field1);
		JPanel group2 = new JPanel();
		group2.setBorder(BorderFactory.createLoweredBevelBorder());
		JButton button2 = new JButton("2");
		JTextField field2 = new JTextField("     ");
		field2.setEditable(false);
		group2.add(button2);
		group2.add(field2);
		JPanel group3 = new JPanel();
		group3.setBorder(BorderFactory.createLoweredBevelBorder());
		JButton button3 = new JButton("3");
		JTextField field3 = new JTextField("     ");
		field3.setEditable(false);
		group3.add(button3);
		group3.add(field3);
		JPanel group4 = new JPanel();
		group4.setBorder(BorderFactory.createLoweredBevelBorder());
		JButton button4 = new JButton("4");
		JTextField field4 = new JTextField("     ");
		field4.setEditable(false);
		group4.add(button4);
		group4.add(field4);
		JPanel group5 = new JPanel();
		group5.setBorder(BorderFactory.createLoweredBevelBorder());
		JButton button5 = new JButton("5");
		JTextField field5 = new JTextField("     ");
		field5.setEditable(false);
		group5.add(button5);
		group5.add(field5);
		JPanel group6 = new JPanel();
		group6.setBorder(BorderFactory.createLoweredBevelBorder());
		JButton button6 = new JButton("6");
		JTextField field6 = new JTextField("     ");
		field6.setEditable(false);
		group6.add(button6);
		group6.add(field6);
		lowerSection.add(group1);
		lowerSection.add(group2);
		lowerSection.add(group3);
		lowerSection.add(group4);
		lowerSection.add(group5);
		lowerSection.add(group6);

		// Initializing upper section panel: middle panel within top panel
		JPanel upperSection = new JPanel();
		upperSection.setBorder(BorderFactory.createEtchedBorder());
		upperSection.setLayout(new GridLayout(6, 1));
		JPanel group7 = new JPanel();
		group7.setBorder(BorderFactory.createLoweredBevelBorder());
		JButton button7 = new JButton("1");
		JTextField field7 = new JTextField("     ");
		field7.setEditable(false);
		group7.add(button7);
		group7.add(field7);
		JPanel group8 = new JPanel();
		group8.setBorder(BorderFactory.createLoweredBevelBorder());
		JButton button8 = new JButton("2");
		JTextField field8 = new JTextField("     ");
		field8.setEditable(false);
		group8.add(button8);
		group8.add(field8);
		JPanel group9 = new JPanel();
		group9.setBorder(BorderFactory.createLoweredBevelBorder());
		JButton button9 = new JButton("3");
		JTextField field9 = new JTextField("     ");
		field9.setEditable(false);
		group9.add(button9);
		group9.add(field9);
		JPanel group10 = new JPanel();
		group10.setBorder(BorderFactory.createLoweredBevelBorder());
		JButton button10 = new JButton("4");
		JTextField field10 = new JTextField("     ");
		field10.setEditable(false);
		group10.add(button10);
		group10.add(field10);
		JPanel group11 = new JPanel();
		group11.setBorder(BorderFactory.createLoweredBevelBorder());
		JButton button11 = new JButton("5");
		JTextField field11 = new JTextField("     ");
		field11.setEditable(false);
		group11.add(button11);
		group11.add(field11);
		JPanel group12 = new JPanel();
		group12.setBorder(BorderFactory.createLoweredBevelBorder());
		JButton button12 = new JButton("6");
		JTextField field12 = new JTextField("     ");
		field12.setEditable(false);
		group12.add(button12);
		group12.add(field12);
		upperSection.add(group7);
		upperSection.add(group8);
		upperSection.add(group9);
		upperSection.add(group10);
		upperSection.add(group11);
		upperSection.add(group12);
		
		// Initializing die panel: right-most panel within top panel
		JPanel diePanel = new JPanel();
		diePanel.setBorder(BorderFactory.createEtchedBorder());
		diePanel.setLayout(new GridLayout(6, 1));
		JPanel group13 = new JPanel();
		group13.setBorder(BorderFactory.createLoweredBevelBorder());
		JCheckBox check1 = new JCheckBox();
		JLabel label1 = new JLabel("Die 1");
		JTextField field13 = new JTextField("     ");
		field13.setEditable(false);
		group13.add(check1);
		group13.add(label1);
		group13.add(field13);
		JPanel group14 = new JPanel();
		group14.setBorder(BorderFactory.createLoweredBevelBorder());
		JCheckBox check2 = new JCheckBox();
		JLabel label2 = new JLabel("Die 2");
		JTextField field14 = new JTextField("     ");
		field14.setEditable(false);
		group14.add(check2);
		group14.add(label2);
		group14.add(field14);
		JPanel group15 = new JPanel();
		group15.setBorder(BorderFactory.createLoweredBevelBorder());
		JCheckBox check3 = new JCheckBox();
		JLabel label3 = new JLabel("Die 3");
		JTextField field15 = new JTextField("     ");
		field15.setEditable(false);
		group15.add(check3);
		group15.add(label3);
		group15.add(field15);
		JPanel group16 = new JPanel();
		group16.setBorder(BorderFactory.createLoweredBevelBorder());
		JCheckBox check4 = new JCheckBox();
		JLabel label4 = new JLabel("Die 4");
		JTextField field16 = new JTextField("     ");
		field16.setEditable(false);
		group16.add(check4);
		group16.add(label4);
		group16.add(field16);
		JPanel group17 = new JPanel();
		group17.setBorder(BorderFactory.createLoweredBevelBorder());
		JCheckBox check5 = new JCheckBox();
		JLabel label5 = new JLabel("Die 5");
		JTextField field17 = new JTextField("     ");
		field17.setEditable(false);
		group17.add(check5);
		group17.add(label5);
		group17.add(field17);
		JPanel group18 = new JPanel();
		group18.setBorder(BorderFactory.createLoweredBevelBorder());
		JButton roll = new JButton("Roll Dice");
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
		JTextField rollsRemaining = new JTextField("Rolls left:     ");
		rollsRemaining.setEditable(false);
		JTextField rounds = new JTextField("Round:     ");
		rounds.setEditable(false);
		JTextField totalScore = new JTextField("Score:     ");
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
		}
	}
}
