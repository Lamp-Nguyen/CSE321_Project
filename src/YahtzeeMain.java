import java.util.ArrayList;
import java.util.Scanner;

public class YahtzeeMain {

	//------------------------------------- Instance variables
	
	// Array of integers with values from 1 to 6, each index representing one die
	private int[] dies;
	
	// ArrayList to store the count of the dies of value n (Ex: If the user roll 2 aces,
	// 2 twos and 1 three, the count list will be [2, 2, 1, 0, 0, 0]
	private ArrayList<Integer> count;
	
	// Array for storing scores, index from (0 -> 6) are for Aces -> Sixes
	// 7: 3 of a kind, 8: 4 of a kind, 9: full house, 10: small straight, 11: large straight
	// 12: yahtzee
	private int[] scoreTable = {-1, -1, -1, -1, -1, -1 
			, -1, -1, -1, -1, -1, -1, -1};
	
	// Number of rerolls remaining
	private int numRolls = 0;
	
	//------------------------------------- Methods
	
	/**
	 * Main constructor for the class, initialize the HashMap,
	 * ArrayList, and update the count
	 */
	public YahtzeeMain() {
		dies = new int[5];
		count = new ArrayList<Integer>(6);
		for (int i = 0; i < 5; i++) {
			dies[i] = (int)(Math.random() * 6 + 1);
		}
		for (int i = 0; i < 6; i++) {
			count.add(0);
		}
		updateCount();
	}
	
	/**
	 * Roll the Dies specified by an array of size 5, consisting of 0s and 1s
	 * (Ex: [1, 1, 0, 0, 0] will re-roll the dies 1 and 2)
	 * @param diesToReroll
	 * @return true if the can be re-rolled (i.e  numRolls <= 3), false otherwise
	 */
	public boolean reroll(int[] diesToReroll) {
		// If the player has rolled less than 3 times
		if (++numRolls < 4) {			
			for (int i = 0; i < 5; i++) {
				if (diesToReroll[i] == 1) {
					dies[i] = (int)(Math.random() * 6 + 1);
				}
			}
			updateCount();
			return true;
		// If the player has rolled more than 3 times
		} else {
			return false;
		}
	}
	
	/**
	 * @return The String representation of the specified die,
	 * return -1 if the index is invalid
	 */
	public String getDieValue(int index) {
		String ret = "";
		try {
			ret += dies[index];
			return ret;	
		} catch (Exception e) {
			e.printStackTrace();
			return "-1";
		}
	}
	
	/**
	 * Gets the current number of dies with the value dieValue
	 * @param dieValue the value of the Die
	 * @return the number of dies with the value dieValue
	 */
	public int dieCount(int dieValue) {
		int count = 0;
		for (int die : dies)
			if (die == dieValue)
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
	
	/**
	 * Check if the player qualifies for a bonus for the upper section
	 * @return true if the total upper section score is >= 63, false otherwise
	 */
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
		// If the player has scored a Yahtzee
		if (scoreTable[11] != -1) {
			// If the current roll is another Yahtzee, add 100 bonus points
			if (count.contains(5))
				scoreTable[11] += 100;
			return scoreTable[11];
		// If the player hasn't scored a Yahtzee
		} else {
			// If the current roll is another Yahtzee
			if (count.contains(5)) 
				return 50;
			return 0;
		}
	}
	
	/**
	 * Get the score at a specified index
	 * @param index the index to retrieve
	 * @return the score at the index
	 */
	public int getScore(int index) {
		int ret = 0;
		try {
			ret = scoreTable[index];
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	/**
	 * Store the score at its matching index
	 * @param index the index to store the score
	 * @param score
	 */
	public void storeScore(int index, int score) {
		numRolls = 0;
		try {
			scoreTable[index] = score;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Check if the score category at the specified index has already
	 * been filled out
	 * @param index the index to check
	 * @return true if the score is already filled, false otherwise
	 */
	public boolean isScoreFilled(int index) {
		try {
			if (scoreTable[index] == -1)
				return false;
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Return the total score
	 * @return the total score
	 */
	public int totalScore() {
		int ret = 0;
		for (int i : scoreTable) {
			if (i == -1)
				ret += 0;
			else 
				ret += i;
		}
		if (hasBonus()) return ret + 35;
		return ret;
	}
	
	/**
	 * Set specific die values
	 * ONLY USE FOR TESTING
	 * @param newDies array of new die values
	 */
	public void setDies(int[] newDies) {
		this.dies = newDies;
	}
}
