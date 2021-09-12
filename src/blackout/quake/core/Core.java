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
	
	private void timer() {
		for (QuakePlayer p : Main.players) {
			p.displayCooldown();
			if (p.cooldown > 0)
				p.cooldown -= 5;
		}
	}
	
}
