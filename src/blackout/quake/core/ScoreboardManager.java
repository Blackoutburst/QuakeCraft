package blackout.quake.core;

import java.util.Collections;

import blackout.quake.main.Main;

public class ScoreboardManager {

	public static void init(QuakePlayer qp) {
		Board board = new Board(qp.getPlayer());
		
		board.setTitle("�6Quake");
		board.set(15, "�b�m--------------------"); 
		board.set(14, "Map: �a"+Main.gameWorld.getName());
		board.set(13, "Time: �a0:00");
		board.set(12, " "); 
		board.set(2, "  ");
		board.set(1, "�b�m-------------------- "); 
		qp.setBoard(board);
	}
	
  	
  	public static void updatePlayers() {
  		Collections.sort(Main.players, new PlayerComparator());
  		
  		for (int i = 0; i < 9; i++) {
  			if (i < Main.players.size()) {
	  			for (QuakePlayer qp : Main.players) {
	  				QuakePlayer q = Main.players.get(i);
	  				qp.getBoard().set(11 - i, q.getPlayer().getDisplayName()+": �a"+q.getScore());
	  			}
  			} else {
  				for (QuakePlayer qp : Main.players) {
  					qp.getBoard().set(10 - i, "   ");
  				}
  			}
  		}
  	}
} 
