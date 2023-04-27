import java.util.ArrayList;
import java.util.HashMap;

public class YahtzeeMain {

	//------------------------------------- Instance variables
	
	// Hashmap to store the dies in <String, Die> pairs (Ex: <"1", Die>, <"2", Die>)
	private HashMap<String, Die> dies;
	
	// ArrayList to store the count of the dies of value n (Ex: If the user roll 2 aces,
	// 2 twos and 1 three, the count list will be [2, 2, 1, 0, 0, 0]
	private ArrayList<Integer> count;
	
	// Count for Yahtzee bonus
	private int yahtzeeCount = 0;
	
	//------------------------------------- Methods
	
	/**
	 * Main constructor for the class, initialize the HashMap,
	 * ArrayList, and update the count
	 */
	public YahtzeeMain() {
		dies = new HashMap<String, Die>();
		count = new ArrayList<Integer>(6);
		for (int i = 1; i <= 5; i++) {
			dies.put(i + "", new Die());
		}
		for (int i = 0; i < 6; i++) {
			count.add(0);
		}
		updateCount();
	}
	
	/**
	 * Reroll all dies, updateCount is called to update the count ArrayList to match
	 * the current turn
	 */
	public void reroll() {
		for (int i = 1; i <= 5; i++) {
			String idx = i + "";
			dies.get(idx).roll();
		}
		updateCount();
	}
	
	/**
	 * Reroll specific dies, updateCount is called to update the count ArrayList to match
	 * the current turn
	 * @param dieNumbers the dies the players want to reroll, represented as
	 * 	a string of integers seperated by spaces
	 */
	public void reroll(String dieNumbers) {
		String[] diesToReroll = dieNumbers.trim().split(" ");
		for (String str : diesToReroll) {
			dies.get(str).roll();
		}
		updateCount();
	}
	
	/**
	 * @return The String representation of the current dies
	 */
	public String getDies() {
		String ret = "";
		for (String key : dies.keySet()) {
			ret += "[Die " + key + ": " + dies.get(key).getValue() + "]  |  ";
		}
		return ret;
	}
	
	/**
	 * Gets the current number of dies with the value dieValue
	 * @param dieValue the value of the Die
	 * @return the number of dies with the value dieValue
	 */
	public int dieCount(int dieValue) {
		int count = 0;
		for (Die die : dies.values())
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
		if (count.contains(5)) {
			yahtzeeCount++;
			return 50;
		}
		return 0;
	}
	
	//Debug method for printing the count arraylist
	public String getCount() {
		return count.toString();
	}
}
