import java.util.ArrayList;
import java.util.Scanner;

public class YahtzeeMain {

	//------------------------------------- Instance variables
	boolean debug = true;
	
	// Hashmap to store the dies in <String, Die> pairs (Ex: <"1", Die>, <"2", Die>)
	//private HashMap<Integer, Die> dies;
	private Die[] dies;
	
	// ArrayList to store the count of the dies of value n (Ex: If the user roll 2 aces,
	// 2 twos and 1 three, the count list will be [2, 2, 1, 0, 0, 0]
	private ArrayList<Integer> count;
	
	// Score array for lower section
	private int[] scoreTable = {-1, -1, -1, -1, -1, -1 
			, -1, -1, -1, -1, -1, -1, -1};
	
	// Number of rerolls remaining
	private int numRolls = 0;
	
	private int yahtzeeCount = 0;
	
	//------------------------------------- Methods
	
	/**
	 * Main constructor for the class, initialize the HashMap,
	 * ArrayList, and update the count
	 */
	public YahtzeeMain() {
		dies = new Die[5];
		count = new ArrayList<Integer>(6);
		for (int i = 0; i < 5; i++) {
			dies[i] = new Die();
		}
		for (int i = 0; i < 6; i++) {
			count.add(0);
		}
		updateCount();
	}
	
//	/**
//	 * Reroll all dies, updateCount is called to update the count ArrayList to match
//	 * the current turn
//	 */
//	public void reroll() {
//		for (int i = 1; i <= 5; i++) {
//			String idx = i + "";
//			dies.get(idx).roll();
//		}
//		updateCount();
//	}
	
	/**
	 * Reroll specific dies, updateCount is called to update the count ArrayList to match
	 * the current turn
	 * @param dieNumbers the dies the players want to reroll, represented as
	 * 	a string of integers seperated by spaces
	 */
	public boolean reroll(int[] diesToReroll) {
		if (++numRolls < 4) {
			
			if (debug) {
				return debugRoll();
			}
			
			for (int i = 0; i < 5; i++) {
				if (diesToReroll[i] == 1) {
					dies[i].roll();
				}
			}
			updateCount();
			return true;
		} else {
			return false;
		}
	}
	
	private boolean debugRoll() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter test values as one string seperated by spaces: ");
		String testInput = sc.nextLine();
		
		String[] testInputArr = testInput.trim().split(" "); // split the string by space
		int[] testVals = new int[5]; // create an array to hold the numbers

		for (int i = 0; i < 5; i++) {
		    testVals[i] = Integer.parseInt(testInputArr[i]); // parse each token as an integer
		}
		
		for (int i = 0; i < 5; i++) {
			dies[i].setValue(testVals[i]);
		}
		updateCount();
		return true;
	}
	
	/**
	 * @return The String representation of the specified die
	 */
	public String getDieValue(int index) {
		String ret = "";
		ret += dies[index].getValue();
		return ret;
	}
	
	/**
	 * Gets the current number of dies with the value dieValue
	 * @param dieValue the value of the Die
	 * @return the number of dies with the value dieValue
	 */
	public int dieCount(int dieValue) {
		int count = 0;
		for (Die die : dies)
			if (die.getValue() == dieValue)
				count++;
		return count;
	}
	
	/**
	 * Update the count ArrayList to match with the current turn
	 */
	public void updateCount() {
		for (int i = 0; i < 6; i++)
			count.set(i, dieCount(i + 1));
	}
	
	/**
	 * Get the total values of the dies with the value dieValue
	 * @param dieValue the value of the die
	 * @return the total values of the dies with the value dieValue
	 */
	public int getDieSum(int dieValue) {
		return count.get(dieValue - 1) * dieValue;
	}
	
	/**
	 * Get the total values of all five dies
	 * @return total values of all five dies
	 */
	public int getRollSum() {
		int ret = 0;
		for (int  i = 0; i < 6; i++) {
			ret += getDieSum(i + 1);
		}
		return ret;
	}
	
	public boolean hasBonus() {
		int bonusReq = 0;
		for (int i = 0; i < 6; i++) {
			if (scoreTable[i] != -1) {
				bonusReq += scoreTable[i];
			}
		}
		if (bonusReq >= 63) {
			return true;
		}
		return false;
	}
	
	/**
	 * Method for getting 3 or 4 of a kind
	 * @return the roll total if the user gets 3 or 4 of a kind,
	 * 0 otherwise 
	 */
	public int getSameDie() {
		if (count.contains(3) || count.contains(4)) return getRollSum();
		return 0;
	}
	/**
	 * Method for full house
	 * @return 25 if the user get full house, 0 otherwise
	 */
	public int getFullHouse() {
		if (count.contains(3) && count.contains(2)) return 25;
		return 0;
	}
	
	/**
	 * Method for small straight
	 * @return 30 if the user get a small straight, 0 otherwise
	 */
	public int getSmallStraight() {
		int straights = 0;
		for (int i = 0; i < 6; i++) {
			if (count.get(i) != 0) {
				straights++;
				if (straights == 4) return 30;
			} else {
				straights = 0;
			}
		}
		return 0;
	}
	
	/**
	 * Method for large straight
	 * @return 40 if the user get a large straight, 0 otherwise
	 */
	public int getLargeStraight() {
		int straights = 0;
		for (int i = 0; i < 6; i++) {
			if (count.get(i) != 0) {
				straights++;
				if (straights == 5) return 40;
			} else {
				straights = 0;
			}
		}
		return 0;
	}
	
	/**
	 * Method for Yahtzee
	 * @return return 50 if the user get a Yahtzee and increment the
	 * Yahtzee bonus, 0 otherwise
	 */
	public int getYahtzee() {
		if (scoreTable[11] != -1) {
			if (count.contains(5)) scoreTable[11] += 100;
			return scoreTable[11];
		} else {
			if (count.contains(5)) {
				return 50;
			}
			return 0;
		}
	}
	
	public int getScore(int index) {
		return scoreTable[index];
	}
	
	public void storeScore(int index, int score) {
		numRolls = 0;
		scoreTable[index] = score;
	}
	
	public boolean isScoreFilled(int index) {
		if (scoreTable[index] == -1) {
			return false;
		}
		return true;
	}
	
	public int totalScore() {
		int ret = 0;
		for (int i : scoreTable) {
			if (i == -1) {
				ret += 0;
			} else {
				ret += i;
			}
		}
		if (hasBonus()) return ret + 35;
		return ret;
	}
}
