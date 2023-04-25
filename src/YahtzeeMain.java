import java.util.ArrayList;
import java.util.HashMap;

public class YahtzeeMain {

	private HashMap<String, Die> dies;
	private ArrayList<Integer> count;
	
	public YahtzeeMain() {
		dies = new HashMap<String, Die>();
		count = new ArrayList<Integer>(6);
		for (int i = 1; i <= 5; i++) {
			dies.put(i + "", new Die());
		}
		for (int i = 0; i < 6; i++) {
			count.add(0);
		}
	}
	
	public void reroll(String dieNumbers) {
		String[] diesToReroll = dieNumbers.trim().split(" ");
		for (String str : diesToReroll) {
			dies.get(str).roll();
		}
		updateCount();
	}
	
	public String getDies() {
		String ret = "";
		for (String key : dies.keySet()) {
			ret += "Die " + key + ":" + dies.get(key).getValue() + " ";
		}
		return ret;
	}
	
	public int dieCount(int dieValue) {
		int count = 0;
		for (Die die : dies.values())
			if (die.getValue() == dieValue)
				count++;
		return count;
	}
	
	public void updateCount() {
		for (int i = 0; i < 6; i++)
			count.set(i, dieCount(i + 1));
	}
	
	public int getDieSum(int dieValue) {
		return count.get(dieValue - 1) * dieValue;
	}
	
	public int getRollSum() {
		int ret = 0;
		for (int  i = 0; i < 6; i++) {
			ret += getDieSum(i + 1);
		}
		return ret;
	}
	
	public int getSameDie() {
		if (count.contains(3) || count.contains(4)) return getRollSum();
		return 0;
	}
	
	public int getFullHouse() {
		if (count.contains(3) && count.contains(2)) return 25;
		return 0;
	}
	
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
	
	public int getYahtzee() {
		if (count.contains(5)) return 50;
		return 0;
	}
	
	public String getCount() {
		return count.toString();
	}
}
