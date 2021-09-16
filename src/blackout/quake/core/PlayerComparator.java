package blackout.quake.core;

import java.util.Comparator;

public class PlayerComparator implements Comparator<QuakePlayer> {
	
	public int compare(QuakePlayer b, QuakePlayer a) {
		int comparator = Integer.valueOf(a.score).compareTo(b.score);
		return comparator == 0 ? Integer.valueOf(a.score).compareTo(b.score) : comparator;
	}
}