import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class YahtzeeMainGraphCoverage {

	YahtzeeMain test;
	
	@Test
	void testReroll() {
		test = new YahtzeeMain();
		int[] arr = {1, 1, 1, 1, 0};
		// Coverage for t1 and t2
		assertTrue(test.reroll(arr));
		// Coverage for t3
		test.reroll(arr);
		test.reroll(arr);
		assertFalse(test.reroll(arr));
	}

	@Test
	void testGetDieValue() {
		test = new YahtzeeMain();
		int[] dieValue = test.getDies();
		// Coverage for t1
		String ret = test.getDieValue(1);
		assertTrue(dieValue[1] == Integer.parseInt(ret));
		// Coverage for t2
		try {
			test.getDieValue(-1);
		} catch (Exception e) {
			Assert.fail();
		}
	}

	@Test
	void testDieCount() {
		test = new YahtzeeMain();
		int[] arr = {1, 1, 1, 2, 3};
		test.setDies(arr);
		test.updateCount();
		// Coverage for t1, t2, and t3
		assertTrue(test.dieCount(1) == 3);
		assertTrue(test.dieCount(2) == 1);
		assertTrue(test.dieCount(4) == 0);
	}

	@Test
	void testUpdateCount() {
		test = new YahtzeeMain();
		int[] arr1 = {1, 2, 2, 3, 3};
		int[] arr2 = {2, 2, 3, 3, 3};
		ArrayList<Integer> oldCount = test.getCount();
		// Coverage for t1 and t2
		test.setDies(arr1);
		test.updateCount();
		assertTrue(oldCount.get(0) == 1);
		assertTrue(oldCount.get(1) == 2);
		assertTrue(oldCount.get(2) == 2);
		
		test.setDies(arr2);
		ArrayList<Integer> newCount = test.getCount();
		assertFalse(newCount.get(2) == 3);
		test.updateCount();
		newCount = test.getCount();
		assertTrue(newCount.get(2) == 3);
		assertTrue(newCount.get(0) == 0);
	}

	@Test
	void testGetDieSum() {
		test = new YahtzeeMain();
		int[] arr = {3, 4, 3, 6, 1};
		test.setDies(arr);
		test.updateCount();
		assertTrue(test.getDieSum(3) == 6);
		assertTrue(test.getDieSum(4) == 4);
		assertTrue(test.getDieSum(5) == 0);
	}

	@Test
	void testGetRollSum() {
		test = new YahtzeeMain();
		int[] arr1 = {1, 2, 2, 3, 3};
		int[] arr2 = {2, 2, 3, 3, 3};
		// Coverage for t1 and t2
		test.setDies(arr1);
		test.updateCount();
		assertTrue(test.getRollSum() == 11);
		
		test.setDies(arr2);
		test.updateCount();
		assertTrue(test.getRollSum() == 13);
	}

	@Test
	void testHasBonus() {
		test = new YahtzeeMain();
		int[] score1 = {5, 10, 15, 20, 25, 30, 0, 0, 0, 0, 0, 0, 0};
		int[] score2 = {5, 10, 15, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		// Coverage for t2
		test.setScoreTable(score1);
		assertTrue(test.hasBonus());
		// Coverage for t1
		test.setScoreTable(score2);
		assertFalse(test.hasBonus());
	}

	@Test
	void testGetSameDie() {
		test = new YahtzeeMain();
		int[] arr1 = {1, 1, 1, 4, 5};
		int[] arr2 = {1, 1, 1, 1, 5};
		int[] arr3 = {1, 1, 2, 2, 3};
		// Coverage for t1
		test.setDies(arr1);
		test.updateCount();
		assertTrue(test.getSameDie() == 12);
		test.setDies(arr2);
		test.updateCount();
		assertTrue(test.getSameDie() == 9);
		// Coverage for t2
		test.setDies(arr3);
		test.updateCount();
		assertTrue(test.getSameDie() == 0);
		
	}

	@Test
	void testGetFullHouse() {
		test = new YahtzeeMain();
		int[] arr1 = {1, 1, 1, 2, 2};
		int[] arr2 = {1, 1, 2, 2, 3};
		// Coverage for t1
		test.setDies(arr1);
		test.updateCount();
		assertTrue(test.getFullHouse() == 25);
		// Coverage for t2
		test.setDies(arr2);
		test.updateCount();
		assertTrue(test.getSameDie() == 0);
	}

	@Test
	void testGetSmallStraight() {
		test = new YahtzeeMain();
		int[] arr1 = {1, 2, 3, 4, 6};
		int[] arr2 = {2, 4, 3, 6, 5};
		int[] arr3 = {1, 3, 5, 6, 2};
		// Coverage for t1
		test.setDies(arr1);
		test.updateCount();
		assertTrue(test.getSmallStraight() == 30);
		
		test.setDies(arr2);
		test.updateCount();
		assertTrue(test.getSmallStraight() == 30);
		
		// Coverage for t2
		test.setDies(arr3);
		test.updateCount();
		assertTrue(test.getSmallStraight() == 0);
	}

	@Test
	void testGetLargeStraight() {
		test = new YahtzeeMain();
		int[] arr1 = {2, 4, 3, 6, 5};
		int[] arr2 = {1, 3, 5, 6, 2};
		// Coverage for t1
		test.setDies(arr1);
		test.updateCount();
		assertTrue(test.getLargeStraight() == 40);
		
		// Coverage for t2
		test.setDies(arr2);
		test.updateCount();
		assertTrue(test.getSmallStraight() == 0);
	}

	@Test
	void testGetYahtzee() {
		test = new YahtzeeMain();
		int[] score1 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 50, 0};
		int[] score2 = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
		
		int[] arr1 = {1, 1, 1, 1, 1};
		int[] arr2 = {1, 3, 5, 6, 2};
		
		// Coverage for t1
		test.setScoreTable(score1);
		test.setDies(arr1);
		test.updateCount();
		assertTrue(test.getYahtzee() == 150);
		
		// Coverage for t2
		test.setDies(arr2);
		test.updateCount();
		assertTrue(test.getYahtzee() == 150);
		
		// Coverage for t3
		test.setScoreTable(score2);
		test.setDies(arr1);
		test.updateCount();
		assertTrue(test.getYahtzee() == 50);
		
		// Coverage for t4
		test.setScoreTable(score2);
		test.setDies(arr2);
		test.updateCount();
		assertTrue(test.getYahtzee() == 0);
	}

	@Test
	void testGetScore() {
		test = new YahtzeeMain();
		int[] score1 = {3, 6, 9, 8, 15, 24, 16, 16, 25, 30, 40, 50, 22};
		test.setScoreTable(score1);
		// Coverage for t1
		assertTrue(test.getScore(0) == 3);
		assertTrue(test.getScore(7) == 16);
		assertFalse(test.getScore(2) == 10);
		// Coverage for t2
		try {
			test.getScore(-2);
		} catch (Exception e) {
			Assert.fail();
		}
	}

	@Test
	void testStoreScore() {
		test = new YahtzeeMain();
		
		// Coverage for t1
		test.storeScore(0, 9);
		test.storeScore(5, 30);
		test.storeScore(12, 24);
		assertTrue(test.getScore(0) == 9);
		assertTrue(test.getScore(5) == 30);
		assertTrue(test.getScore(12) == 24);
		assertTrue(test.getScore(1) == -1);
		
		// Coverage for t2
		try {
			test.getScore(13);
		} catch (Exception e) {
			Assert.fail();
		}
	}

	@Test
	void testIsScoreFilled() {
		test = new YahtzeeMain();
		
		// Coverage for t1
		test.storeScore(0, 2);
		test.storeScore(1, 4);
		assertTrue(test.isScoreFilled(0));
		assertTrue(test.isScoreFilled(1));
		
		// Coverage for t2
		assertFalse(test.isScoreFilled(2));
		assertFalse(test.isScoreFilled(3));
		
		// Coverage for t3
		try {
			assertFalse(test.isScoreFilled(14));
		} catch (Exception e) {
			Assert.fail();
		}
	}

	@Test
	void testTotalScore() {
		test = new YahtzeeMain();
		int[] score1 = {1, 2, 3, 4, 5, 6, 10, 11, 25, 30, 40, 50, 13};
		int[] score2 = {5, 10, 15, 20, 25, 30, 0, 0, 0, 0, 0, 0, 0};
		
		// Coverage for t1 and t2
		test.setScoreTable(score1);
		assertTrue(test.totalScore() == 200);
		
		// Coverage for t3
		test.setScoreTable(score2);
		assertFalse(test.totalScore() == 105);
		assertTrue(test.totalScore() ==  140);
	}

}
