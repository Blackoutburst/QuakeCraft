package blackout.quake.main;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import blackout.quake.core.RailGun;


public class Main extends JavaPlugin implements Listener {

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if ((event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || 
			event.getAction().equals(Action.RIGHT_CLICK_AIR)) &&
			event.getPlayer().getItemInHand().getType().equals(Material.DIAMOND_HOE)) {
			
			Location loc = event.getPlayer().getLocation().clone();
			loc.setY(loc.getY() + event.getPlayer().getEyeHeight());
			
			new RailGun(loc, event.getPlayer().getLocation().getDirection().clone(), event.getPlayer()).fire(event.getPlayer());
		}
	}
	
}
