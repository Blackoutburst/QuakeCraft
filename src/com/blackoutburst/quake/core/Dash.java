package com.blackoutburst.quake.core;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.blackoutburst.quake.main.Main;

public class Dash {

	private static void executeDash(Player player, QuakePlayer qp) {
		qp.setDashCooldown(GameOption.DASH_DELAY);
		
		final Vector dash = player.getLocation().getDirection().clone();
		dash.setY(0.05f);
		dash.setX(0.0f);
		dash.setZ(0.0f);
		
		player.setVelocity(dash.multiply(5));
		
		new BukkitRunnable(){
			@Override
			public void run(){
				Vector dash = player.getLocation().getDirection().clone();
				if (!GameOption.VERTICAL_DASH)
					dash.setY(0.0f);
				player.setVelocity(dash.multiply(GameOption.DASH_STRENGTH));
				this.cancel();
			}
		}.runTaskLater(Main.getPlugin(Main.class), 1L);
		
		player.getWorld().playSound(player.getLocation(), Sound.ENTITY_BAT_TAKEOFF, 3, 0.5f);
	}
	
	public static void dash(QuakePlayer qp, PlayerInteractEvent event) {
		final Player player = event.getPlayer();
		
		if ((event.getAction().equals(Action.LEFT_CLICK_BLOCK) || 
				event.getAction().equals(Action.LEFT_CLICK_AIR)) &&
				Core.clickHoe(player.getItemInHand().getType())) {
			
			if (GameOption.DASH) {
				if (qp.getDashCooldown() > 0) {
					player.sendMessage("§cYour dash will be available in §e"+String.format("%.2f", qp.dashCooldown / 20)+" §cseconds!");
				} else {
					executeDash(player, qp);
				}
			} else {
				player.sendMessage("§cDash is disabled in this game !");
			}
		}
	}
	
}
