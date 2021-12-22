package blackout.quake.main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import blackout.commands.CommandClean;
import blackout.commands.CommandEnd;
import blackout.commands.CommandScan;
import blackout.commands.CommandShowSpawn;
import blackout.commands.CommandStart;
import blackout.menu.ColorMenu;
import blackout.menu.CustomMenu;
import blackout.menu.GunMenu;
import blackout.menu.ShapeMenu;
import blackout.quake.core.Core;
import blackout.quake.core.GunProfile;
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
		new File("./plugins/Quake/player data/").mkdirs();
		
		spawn = new Location(Bukkit.getWorld("world"), 8.5f, 5.0f, 8.5f, 0, 0);
	}
	
 	public void onPlayerJoin(PlayerJoinEvent event) {
		event.getPlayer().removePotionEffect(PotionEffectType.SPEED);
		event.getPlayer().setHealth(20);
		event.getPlayer().setSaturation(20);
		event.getPlayer().setGameMode(GameMode.ADVENTURE);
		event.getPlayer().getInventory().clear();
		event.getPlayer().teleport(spawn);
		
		GunProfile gunProfile = new GunProfile("§bWood gun", Material.WOOD_HOE, Type.BALL, Color.AQUA, false);
		
		if (new File("./plugins/Quake/player data/"+event.getPlayer().getUniqueId().toString().replace("-", "")+".yml").exists()) {
			QuakePlayer qp = new QuakePlayer(event.getPlayer(), gunProfile);
			
			players.add(qp);
			qp.readPlayerData();
		} else {
			QuakePlayer qp = new QuakePlayer(event.getPlayer(), gunProfile);
			qp.savePlayerData("gun", 11);
			qp.savePlayerData("shape", 11);
			qp.savePlayerData("color", 31);
			players.add(qp);
		}
		
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
		QuakePlayer qp = QuakePlayer.getFromPlayer(event.getPlayer());
		
		if (event.getPlayer().getItemInHand().getType().equals(Material.NETHER_STAR)) {
			CustomMenu.open(event.getPlayer());
		}
		
		if (!gameRunning) return;
		
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
			
			new RailGun(loc, event.getPlayer().getLocation().getDirection().clone(), qp).fire(event.getPlayer());
		}
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if (event.getInventory().getName().equals("Gun customisation Menu")) {
			CustomMenu.click(event.getSlot(), (Player) event.getWhoClicked());
			event.setCancelled(true);
		}
		if (event.getInventory().getName().equals("Gun Menu")) {
			GunMenu.getValue(event.getSlot(), (Player) event.getWhoClicked(), true);
			event.setCancelled(true);
		}
		
		if (event.getInventory().getName().equals("Shape Menu")) {
			ShapeMenu.getValue(event.getSlot(), (Player) event.getWhoClicked(), true);
			event.setCancelled(true);
		}
		
		if (event.getInventory().getName().equals("Color Menu")) {
			ColorMenu.getValue(event.getSlot(), (Player) event.getWhoClicked(), true);
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		event.setCancelled(gameRunning);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		switch(command.getName()) {
			case "start": new CommandStart().execute(sender, args); break;
			case "end": new CommandEnd().execute(); break;
			case "scan": new CommandScan().execute(sender, args); break;
			case "clean": new CommandClean().execute(args); break;
			case "showspawn": new CommandShowSpawn().execute(args); break;
			default: return true;
		}
		return true;
	}
}
