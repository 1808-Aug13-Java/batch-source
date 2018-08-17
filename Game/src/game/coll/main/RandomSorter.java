package game.coll.main;

import java.util.Comparator;

public class RandomSorter implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {
		
//		int ran1 = (int) Math.random();
//		int ran2 = (int) Math.random();
		return (int) (Math.random() * 1000 - Math.random() * 1000);
	}

}
