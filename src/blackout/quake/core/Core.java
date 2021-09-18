package blackout.quake.core;

import java.io.File;
import java.util.Collections;
import java.util.Random;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import blackout.quake.main.Main;

public class Core {

	public static void startGame() {
		Main.gameRunning = true;
		
		ItemStack gun = new ItemStack(Material.IRON_HOE);
		ItemMeta gunMeta = gun.getItemMeta();
		
		gunMeta.setDisplayName("§b§oRailGun");
		gunMeta.addEnchant(Enchantment.ARROW_DAMAGE, 10, true);
		gun.setItemMeta(gunMeta);
		
		for (QuakePlayer p : Main.players) {
			p.getPlayer().getInventory().setItem(0, gun);
			p.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100000, 3, false, false));
			teleportToRespawn(p.getPlayer());
			p.cooldown = RailGun.FIRE_DELAY;
		}
	}

	public static void teleportToRespawn(Player p) {
		p.teleport(Main.respawns.get(new Random().nextInt(Main.respawns.size())));
	}
	
	public static void loadRespawn() {
		YamlConfiguration config = YamlConfiguration.loadConfiguration(new File("./plugins/Quake/respawn.yml"));
		
		Set<String> respawns = config.getConfigurationSection("respawn").getKeys(false);
		
		for (String resp : respawns) {
			String pos[] = config.getString("respawn."+resp).split(", ");
			float x = Float.valueOf(pos[0]);
			float y = Float.valueOf(pos[1]);
			float z = Float.valueOf(pos[2]);
			float yaw = Float.valueOf(pos[3]);
			float pitch = Float.valueOf(pos[4]);
			Main.respawns.add(new Location(Bukkit.getWorld("world"), x, y, z, yaw, pitch));
		}
	}
	
	public static void endGame() {
		Main.gameRunning = false;

		Bukkit.broadcastMessage("§a==============================");
		Bukkit.broadcastMessage(centerText("§6Quake"));
		Bukkit.broadcastMessage("");
		for (int i = 0; i < 3; i++) {
			if (i < Main.players.size()) {
				Bukkit.broadcastMessage(centerText(Main.players.get(i).getPlayer().getName()+": "+Main.players.get(i).getScore()));
			}
		}
		Bukkit.broadcastMessage("§a==============================");
		
		
		for (QuakePlayer p : Main.players) {
			p.getPlayer().teleport(Main.spawn);
			p.getPlayer().getInventory().clear();
			p.setScore(0);
			p.getPlayer().removePotionEffect(PotionEffectType.SPEED);
		}
	}
	
	public void cooldownTimer() {
		new BukkitRunnable(){
			@Override
			public void run(){
				try {
					if (Main.gameRunning) {
						timer();
					}
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
			if (p.dashCooldown > 0)
				p.dashCooldown--;
		}
	}
	
	public static String centerText(String text) {
		int maxWidth = 60;
		int spaces = (int) Math.round((maxWidth - 1.4 * ChatColor.stripColor(text).length()) / 2);
		
		return StringUtils.repeat(" ", spaces) + text;
	}
	
}
