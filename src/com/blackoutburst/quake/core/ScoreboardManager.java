package com.blackoutburst.quake.core;

import com.blackoutburst.quake.main.Main;

public class ScoreboardManager {

	public static void clear(QuakePlayer p) {
		for (int i = 15; i >= 0; i--)
			p.getBoard().remove(i);
	}
	
	public static void init(QuakePlayer qp) {
		Board board = new Board(qp.getPlayer());
		
		board.setTitle("§6Quake");
		board.set(15, "§b§m--------------------"); 
		board.set(14, "Map: §aSpawn");
		board.set(13, "Time: §a0:00");
		board.set(12, " "); 
		board.set(2, "  ");
		board.set(1, "§b§m-------------------- "); 
		qp.setBoard(board);
	}
	
  	
  	public static void updatePlayers() {
  		Main.players.sort(new PlayerComparator());
  		
  		for (int i = 0; i < 9; i++) {
  			if (i < Main.players.size()) {
	  			for (QuakePlayer qp : Main.players) {
	  				QuakePlayer q = Main.players.get(i);
	  				qp.getBoard().set(11 - i, q.getPlayer().getDisplayName()+": §a"+q.getScore());
	  			}
  			} else {
  				for (QuakePlayer qp : Main.players) {
  					qp.getBoard().set(11 - i, "   ");
  				}
  			}
  		}
  	}
} 
