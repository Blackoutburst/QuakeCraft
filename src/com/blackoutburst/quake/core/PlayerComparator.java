package com.blackoutburst.quake.core;

import java.util.Comparator;

public class PlayerComparator implements Comparator<QuakePlayer> {
	
	public int compare(QuakePlayer b, QuakePlayer a) {
		int comparator = Integer.compare(a.score, b.score);
		return comparator == 0 ? Integer.compare(a.score, b.score) : comparator;
	}
}