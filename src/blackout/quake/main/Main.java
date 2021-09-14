package blackout.quake.main;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import blackout.quake.core.Core;
import blackout.quake.core.QuakePlayer;
import blackout.quake.core.RailGun;
import blackout.quake.core.ScoreboardManager;


public class Main extends JavaPlugin implements Listener {

	public static List<QuakePlayer> players = new ArrayList<QuakePlayer>();
	
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		new Core().cooldownTimer();
	}
	
	@EventHandler
 	public void onPlayerJoin(PlayerJoinEvent event) {
		players.add(new QuakePlayer(event.getPlayer()));
		ScoreboardManager.init(event.getPlayer());
	}
	
	@EventHandler
 	public void onPlayerQuit(PlayerQuitEvent event) {
		players.remove(QuakePlayer.getFromPlayer(event.getPlayer()));
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		QuakePlayer qp = QuakePlayer.getFromPlayer(event.getPlayer());
		
		if ((event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || 
			event.getAction().equals(Action.RIGHT_CLICK_AIR)) &&
			event.getPlayer().getItemInHand().getType().equals(Material.DIAMOND_HOE) &&
			qp.getCooldown() <= 0) {
			
			Location loc = event.getPlayer().getLocation().clone();
			loc.setY(loc.getY() + event.getPlayer().getEyeHeight());
			
			new RailGun(loc, event.getPlayer().getLocation().getDirection().clone(), event.getPlayer()).fire(event.getPlayer());
		}
	}
	
}
