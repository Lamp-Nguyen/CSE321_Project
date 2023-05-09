import java.util.ArrayList;
import java.util.Arrays;

import junit.framework.TestCase;

public class YahtzeeMainTest extends TestCase {

	YahtzeeMain yahtzee = new YahtzeeMain();
	
	/**
	 * Test reroll() implementation (see method description).
	 */
	public void testReroll() {
		int[] diesToReroll = {1, 1, 0, 0, 0}; // rolling just 1st and 2nd dice.
		System.out.println("Before roll (starting set): " + Arrays.toString(yahtzee.getDies()));
		assertTrue(yahtzee.reroll(diesToReroll));
		System.out.println("1st roll: " + Arrays.toString(yahtzee.getDies()));
		assertTrue(yahtzee.reroll(diesToReroll));
		System.out.println("2nd roll: " + Arrays.toString(yahtzee.getDies()));
		assertTrue(yahtzee.reroll(diesToReroll));
		System.out.println("3rd roll: " + Arrays.toString(yahtzee.getDies()));
		assertFalse(yahtzee.reroll(diesToReroll));
		System.out.println("4th roll (set not change): " + Arrays.toString(yahtzee.getDies()));
	}

	/**
	 * Test getDieValue() implementation (see method description).
	 */
	public void testGetDieValue() {
		int[] dies = {1, 2, 3, 4, 5};
		yahtzee.setDies(dies);
		assertEquals(yahtzee.getDieValue(0), "1");
		assertEquals(yahtzee.getDieValue(1), "2");
		assertEquals(yahtzee.getDieValue(2), "3");
		assertEquals(yahtzee.getDieValue(3), "4");
		assertEquals(yahtzee.getDieValue(4), "5");
		assertEquals(yahtzee.getDieValue(5), "-1"); // index out of bounds
	}

	/**
	 * Test dieCount() implementation (see method description).
	 */
	public void testDieCount() {
		int[] dies = {2, 2, 3, 1, 5};
		yahtzee.setDies(dies);
		assertEquals(yahtzee.dieCount(1), 1);
		assertEquals(yahtzee.dieCount(2), 2);
		assertEquals(yahtzee.dieCount(6), 0);
	}

	/**
	 * Test updateCount() implementation (see method description).
	 */
	public void testUpdateCount() {
		int[] dies = {2, 2, 3, 1, 5};
		ArrayList<Integer> count = new ArrayList<>(Arrays.asList(2, 2, 1, 0, 0, 0));
		yahtzee.setDies(dies);
		yahtzee.setCount(count);
		System.out.println(yahtzee.getCount()); // Equals [2, 2, 1, 0, 0, 0] as it should
		yahtzee.updateCount();
		System.out.println(yahtzee.getCount()); // Equals [1, 2, 1, 0, 1, 0] as it should
		System.out.println("[1, 2, 1, 0, 1, 0]");
	}

	/**
	 * Test getDieSum() implementation (see method description).
	 */
	public void testGetDieSum() {
		ArrayList<Integer> count = new ArrayList<>(Arrays.asList(2, 2, 1, 0, 0, 0));
		yahtzee.setCount(count);
		assertEquals(yahtzee.getDieSum(1), 2);
		assertEquals(yahtzee.getDieSum(2), 4);
		assertEquals(yahtzee.getDieSum(3), 3);
		assertEquals(yahtzee.getDieSum(4), 0);
		assertEquals(yahtzee.getDieSum(5), 0);
		assertEquals(yahtzee.getDieSum(6), 0);
	}

	/**
	 * Test getRollSum() implementation (see method description).
	 */
	public void testGetRollSum() {
		ArrayList<Integer> count = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0));
		yahtzee.setCount(count);
		assertEquals(yahtzee.getRollSum(), 0);
		count = new ArrayList<>(Arrays.asList(2, 2, 1, 0, 0, 0));
		yahtzee.setCount(count);
		assertEquals(yahtzee.getRollSum(), 9);
	}

	/**
	 * Test hasBonus() implementation (see method description).
	 */
	public void testHasBonus() {
		 int[] scoreTable = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
		 yahtzee.setScoreTable(scoreTable);
		 assertFalse(yahtzee.hasBonus());
		 scoreTable = new int[] {-1, 30, 40, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
		 yahtzee.setScoreTable(scoreTable);
		 assertTrue(yahtzee.hasBonus());
	}

	/**
	 * Test getSameDie() implementation (see method description).
	 */
	public void testGetSameDie() {
		ArrayList<Integer> count = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0));
		yahtzee.setCount(count);
		assertEquals(yahtzee.getSameDie(), 0);
		count = new ArrayList<>(Arrays.asList(0, 3, 0, 0, 0, 0));
		yahtzee.setCount(count);
		assertEquals(yahtzee.getSameDie(), 6);
		count = new ArrayList<>(Arrays.asList(0, 4, 0, 0, 0, 0));
		yahtzee.setCount(count);
		assertEquals(yahtzee.getSameDie(), 8);
		count = new ArrayList<>(Arrays.asList(0, 3, 0, 4, 0, 0));
		yahtzee.setCount(count);
		assertEquals(yahtzee.getSameDie(), 22);
	}

	/**
	 * Test getFullHouse() implementation (see method description).
	 */
	public void testGetFullHouse() {
		ArrayList<Integer> count = new ArrayList<>(Arrays.asList(0, 3, 0, 0, 0, 0));
		yahtzee.setCount(count);
		assertEquals(yahtzee.getFullHouse(), 0);
		count = new ArrayList<>(Arrays.asList(0, 2, 0, 0, 0, 0));
		yahtzee.setCount(count);
		assertEquals(yahtzee.getFullHouse(), 0);
		count = new ArrayList<>(Arrays.asList(0, 3, 0, 2, 0, 0));
		yahtzee.setCount(count);
		assertEquals(yahtzee.getFullHouse(), 25);
	}

	/**
	 * Test getSmallStraight() implementation (see method description).
	 */
	public void testGetSmallStraight() {
		ArrayList<Integer> count = new ArrayList<>(Arrays.asList(0, 3, 0, 0, 0, 0));
		yahtzee.setCount(count);
		assertEquals(yahtzee.getSmallStraight(), 0);
		count = new ArrayList<>(Arrays.asList(0, 2, 2, 2, 3, 0));
		yahtzee.setCount(count);
		assertEquals(yahtzee.getSmallStraight(), 30);
	}

	/**
	 * Test getLargeStraight() implementation (see method description).
	 */
	public void testGetLargeStraight() {
		ArrayList<Integer> count = new ArrayList<>(Arrays.asList(0, 3, 0, 0, 0, 0));
		yahtzee.setCount(count);
		assertEquals(yahtzee.getLargeStraight(), 0);
		count = new ArrayList<>(Arrays.asList(0, 2, 2, 2, 3, 3));
		yahtzee.setCount(count);
		assertEquals(yahtzee.getLargeStraight(), 40);
	}

	/**
	 * Test getYahtzee() implementation (see method description).
	 */
	public void testGetYahtzee() {
		ArrayList<Integer> count = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0));
		yahtzee.setCount(count);
		assertEquals(yahtzee.getYahtzee(), 0);
		count = new ArrayList<>(Arrays.asList(0, 2, 5, 2, 3, 3));
		yahtzee.setCount(count);
		assertEquals(yahtzee.getYahtzee(), 50);
		int[] scoreTable = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 30, -1};
		yahtzee.setScoreTable(scoreTable);
		assertEquals(yahtzee.getYahtzee(), 130);
	}

	/**
	 * Test getScore() implementation (see method description).
	 */
	public void testGetScore() {
		int[] scoreTable = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 30, -1};
		yahtzee.setScoreTable(scoreTable);
		assertEquals(yahtzee.getScore(0), -1);
		assertEquals(yahtzee.getScore(11), 30);
	}

	/**
	 * Test storeScore() implementation (see method description).
	 */
	public void testStoreScore() {
		int[] scoreTable = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 30, -1};
		yahtzee.setScoreTable(scoreTable);
		assertEquals(yahtzee.getScore(3), -1);
		yahtzee.storeScore(3, 40);
		assertEquals(yahtzee.getScore(3), 40);
	}

	/**
	 * Test isScoreFilled() implementation (see method description).
	 */
	public void testIsScoreFilled() {
		int[] scoreTable = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 30, -1};
		yahtzee.setScoreTable(scoreTable);
		assertFalse(yahtzee.isScoreFilled(3));
		assertTrue(yahtzee.isScoreFilled(11));
	}

	/**
	 * Test totalScore() implementation (see method description).
	 */
	public void testTotalScore() {
		int[] scoreTable = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
		yahtzee.setScoreTable(scoreTable);
		assertFalse(yahtzee.hasBonus());
		assertEquals(yahtzee.totalScore(), 0);
		scoreTable = new int[] {-1, 30, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
		yahtzee.setScoreTable(scoreTable);
		assertFalse(yahtzee.hasBonus());
		assertEquals(yahtzee.totalScore(), 40);
		scoreTable = new int[] {-1, 30, 40, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
		yahtzee.setScoreTable(scoreTable);
		assertTrue(yahtzee.hasBonus());
		assertEquals(yahtzee.totalScore(), 105);
	}
}
