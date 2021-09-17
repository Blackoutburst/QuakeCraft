package blackout.quake.core;

import java.util.Collections;

import org.bukkit.entity.Player;

import blackout.quake.main.Main;

public class ScoreboardManager {

	public static void init(Player p) {
		Board board = new Board(p);
		QuakePlayer qp = QuakePlayer.getFromPlayer(p);
		
		board.setTitle("§6Quake");
		board.set(15, "§a§m----------"); 
		board.set(14, "Time: §a0:00");
		board.set(13, " "); 
		board.set(7, "  ");
		board.set(6, "§a§m---------- "); 
		qp.setBoard(board);
	}
	
  	
  	public static void updatePlayers() {
  		Collections.sort(Main.players, new PlayerComparator());
  		
  		for (int i = 0; i < 5; i++) {
  			if (i < Main.players.size()) {
	  			for (QuakePlayer qp : Main.players) {
	  				QuakePlayer q = Main.players.get(i);
	  				qp.getBoard().set(12 - i, q.getPlayer().getName()+": §a"+q.getScore());
	  			}
  			} else {
  				for (QuakePlayer qp : Main.players) {
  					qp.getBoard().set(12 - i, "   ");
  				}
  			}
  		}
  	}
} 
