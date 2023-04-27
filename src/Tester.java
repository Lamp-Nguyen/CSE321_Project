import java.util.Scanner;

public class Tester {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		String input;
		int noRerolls = 0;
		YahtzeeMain test = new YahtzeeMain();
		
		// 13 rounds
		for (int i = 0; i < 13; i++) {
			System.out.println("Round no: " + (i + 1));
			System.out.println("-----------------------------");
			// while the user still has rerolls
			while (noRerolls < 3) {
				
				System.out.println(test.getDies());
				testUpperSection(test);
				System.out.println("-----------------------------");
				testLowerSection(test);
				System.out.println("-----------------------------");
				
				// if the user hasn't rerolled 2 times
				if (noRerolls < 2) {
					System.out.println("Enter the dies you want to reroll or enter your score: ");
					input = sc.nextLine().trim();
					// if the user keeps a score
					if (input.equals("score")) {
						noRerolls = 0;
						test.reroll();
						break;
					// if the user enters a string of dies to reroll
					} else if (input != ""){
						noRerolls++;
						test.reroll(input);
					// if the user enters nothing
					} else {
						noRerolls++;
						test.reroll();
					}
				// if the user used all their rerolls
				} else {
					noRerolls++;
					System.out.println("You've run out of rolls, enter your score");
					input = sc.nextLine().trim();
				}
			}
			
			noRerolls = 0;
			System.out.println("-----------------------------");
		}
	}
	
	public static void testUpperSection(YahtzeeMain y) {
		System.out.println("Aces: " + y.getDieSum(1));
		System.out.println("Twos: " + y.getDieSum(2));
		System.out.println("Threes: " + y.getDieSum(3));
		System.out.println("Fours: " + y.getDieSum(4));
		System.out.println("Fives: " + y.getDieSum(5));
		System.out.println("Sixes: " + y.getDieSum(6));
	}
	
	public static void testLowerSection(YahtzeeMain y) {
		System.out.println("Three of a Kind: " + y.getSameDie());
		System.out.println("Four of a Kind: " + y.getSameDie());
		System.out.println("Full House: " + y.getFullHouse());
		System.out.println("Small Straight: " + y.getSmallStraight());
		System.out.println("Large Straight: " + y.getLargeStraight());
		System.out.println("Chance: " + y.getRollSum());
		System.out.println("Yahtzee: " + y.getYahtzee());
		System.out.println("Die counts: " + y.getCount());
	}
	
}
