
public class Tester {

	public static void main(String[] args) {
		YahtzeeMain test = new YahtzeeMain();
		test.reroll("1 2 3 4 5");
		System.out.println(test.getDies());
		testLowerSection(test);
		
		test.reroll("2 4 5");
		System.out.println(test.getDies());
		testLowerSection(test);
		
		test.reroll("5 4 1");
		System.out.println(test.getDies());
		testLowerSection(test);
		
		test.reroll("3 5 4");
		System.out.println(test.getDies());
		testLowerSection(test);
	}
	
	public static void testLowerSection(YahtzeeMain y) {
		System.out.println("Three of a Kind: " + y.getSameDie());
		System.out.println(" Four of a Kind: " + y.getSameDie());
		System.out.println(" Full House: " + y.getFullHouse());
		System.out.println(" Small Straight: " + y.getSmallStraight());
		System.out.println(" Large Straight: " + y.getLargeStraight());
		System.out.println(" Chance: " + y.getRollSum());
		System.out.println(" Yahtzee: " + y.getYahtzee());
		System.out.println(" Die counts: " + y.getCount());
	}
	
}
