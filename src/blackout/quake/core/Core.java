package blackout.quake.core;

import org.bukkit.scheduler.BukkitRunnable;

import blackout.quake.main.Main;

public class Core {

	public void cooldownTimer() {
		new BukkitRunnable(){
			@Override
			public void run(){
				try {
					timer();
				} catch(Exception e) {}
			}
		}.runTaskTimerAsynchronously(Main.getPlugin(Main.class), 0L, 1L);
	}
	
	public void gameTimer() {
		new BukkitRunnable(){
			@Override
			public void run(){
				try {
					if (Main.gameRunning) {
						gameTimerFunc();
					}
				} catch(Exception e) {}
			}
		}.runTaskTimerAsynchronously(Main.getPlugin(Main.class), 0L, 20L);
	}
	
	private void gameTimerFunc() {
		Main.gameTime++;
		
		int minutes = Main.gameTime / 60;
		int seconds = Main.gameTime % 60;
		String time = String.format("%d:%02d", minutes, seconds);
		
		for (QuakePlayer p : Main.players) {
			p.getBoard().set(14, "Time: §a"+time);
		}
	}
	
	private void timer() {
		for (QuakePlayer p : Main.players) {
			p.displayCooldown();
			if (p.cooldown > 0)
				p.cooldown--;
		}
	}
	
}
