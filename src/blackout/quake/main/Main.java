package blackout.quake.main;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import blackout.quake.core.Core;
import blackout.quake.core.CustomMenu;
import blackout.quake.core.QuakePlayer;
import blackout.quake.core.RailGun;
import blackout.quake.core.ScoreboardManager;


public class Main extends JavaPlugin implements Listener {

	public static List<QuakePlayer> players = new ArrayList<QuakePlayer>();
	public static boolean gameRunning = false;
	public static int gameTime = 0;
	public static List<Location> respawns = new ArrayList<Location>();
	
	public static Location spawn;
	
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		new Core().cooldownTimer();
		new Core().gameTimer();
		Core.loadRespawn();
		
		spawn = new Location(Bukkit.getWorld("world"), 910.5f, 55, 1331.5f, 0, 0);
	}
	
	@EventHandler
 	public void onPlayerJoin(PlayerJoinEvent event) {
		event.getPlayer().removePotionEffect(PotionEffectType.SPEED);
		event.getPlayer().setHealth(20);
		event.getPlayer().setSaturation(10000);
		event.getPlayer().setGameMode(GameMode.ADVENTURE);
		event.getPlayer().getInventory().clear();
		
		event.getPlayer().teleport(spawn);
		players.add(new QuakePlayer(event.getPlayer()));
		ScoreboardManager.init(event.getPlayer());
		ScoreboardManager.updatePlayers();
		CustomMenu.giveItem(event.getPlayer());
	}
	
	@EventHandler
 	public void onPlayerQuit(PlayerQuitEvent event) {
		players.remove(QuakePlayer.getFromPlayer(event.getPlayer()));
		ScoreboardManager.updatePlayers();
	}
	
	@EventHandler
	public void onItemDrop(PlayerDropItemEvent event) {
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (!gameRunning) return;
		
		QuakePlayer qp = QuakePlayer.getFromPlayer(event.getPlayer());
		
		if ((event.getAction().equals(Action.LEFT_CLICK_BLOCK) || 
				event.getAction().equals(Action.LEFT_CLICK_AIR)) &&
				Core.clickHoe(event.getPlayer().getItemInHand().getType())) {
			
			if (qp.getDashCooldown() > 0) {
				event.getPlayer().sendMessage("§cYou can only dash once every seconds");
			} else {
				qp.setDashCooldown(RailGun.DASH_DELAY);
				
				Vector dash = event.getPlayer().getLocation().getDirection();
				dash.setY(0.2f);
				
				event.getPlayer().setVelocity(dash.multiply(2));
				event.getPlayer().getWorld().playSound(event.getPlayer().getLocation(), Sound.BAT_TAKEOFF, 1, 1);
			}
		}
		
		if ((event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || 
			event.getAction().equals(Action.RIGHT_CLICK_AIR)) &&
			Core.clickHoe(event.getPlayer().getItemInHand().getType()) &&
			qp.getCooldown() <= 0) {
			
			Location loc = event.getPlayer().getLocation().clone();
			loc.setY(loc.getY() + event.getPlayer().getEyeHeight());
			
			new RailGun(loc, event.getPlayer().getLocation().getDirection().clone(), event.getPlayer()).fire(event.getPlayer());
		}
	}
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		event.setCancelled(gameRunning);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		switch(command.getName()) {
			case "start": Core.startGame(); break;
			case "end": Core.endGame(); break;
			default: return true;
		}
		return true;
	}
}
