package com.blackoutburst.quake.core;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.NameTagVisibility;

import com.blackoutburst.quake.main.Main;
import com.blackoutburst.quake.menu.CustomMenu;

public class Core {

	public static void updateName(QuakePlayer qp, NameTagVisibility tagVisible) {
		qp.getPlayer().setDisplayName(qp.getGunProfile().getNameColor()+qp.getPlayer().getName()+"§r");
		qp.getPlayer().setPlayerListName(qp.getGunProfile().getNameColor()+qp.getPlayer().getName()+"§r");
		for (QuakePlayer hp : Main.players) {
			hp.getBoard().addTeam(hp, qp, tagVisible);
			qp.getBoard().addTeam(qp, hp, tagVisible);
		}
		ScoreboardManager.updatePlayers();
	}
	
	public static void startGame(String worldName) {
		Main.gameWorld = Bukkit.getWorld(worldName);
		
		
		Main.gameRunning = true;
		Main.gameTime = 0;
		
		loadRespawn(worldName);
		
		for (int i = 0; i < Main.players.size(); i++)
			Core.updateName(Main.players.get(i), GameOption.NAMETAG ? NameTagVisibility.ALWAYS : NameTagVisibility.NEVER);
		
		for (QuakePlayer p : Main.players) {
			
			p.board.set(14, "Map: §a"+Main.gameWorld.getName());
			ItemStack gun = new ItemStack(p.getGunProfile().getGun());
			ItemMeta gunMeta = gun.getItemMeta();
			
			gunMeta.setDisplayName(p.getGunProfile().getName());
			
			if (p.getGunProfile().isSuperior()) {
				gunMeta.addItemFlags(ItemFlag.values());
				gunMeta.addEnchant(Enchantment.ARROW_DAMAGE, 10, true);
			}
			gun.setItemMeta(gunMeta);
			
			p.getPlayer().getInventory().setItem(0, gun);
			
			
			if (GameOption.WALK && GameOption.PLAYER_SPEED > 0)
				p.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100000, GameOption.PLAYER_SPEED - 1, false, false));
			if (GameOption.JUMP && GameOption.JUMP_BOOST > 0)
				p.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 100000, GameOption.JUMP_BOOST - 1, false, false));
			if (GameOption.WALK && GameOption.SLOWNESS > 0)
				p.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100000, GameOption.SLOWNESS - 1, false, false));
			
			if (GameOption.BLINDNESS)
				p.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100000, 0, false, false));
			if (GameOption.INVISIBILITY)
				p.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 100000, 0, false, false));
			if (!GameOption.JUMP)
				p.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 100000, 199, false, false));
			if (!GameOption.WALK)
				p.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100000, 254, false, false));
				
			
			p.setCooldown(GameOption.FIRE_DELAY);
			
			teleportToRespawn(p.getPlayer());
			
			p.getPlayer().setHealth(20);
			p.getPlayer().setSaturation(20);
			p.getPlayer().setGameMode(GameMode.ADVENTURE);
			
		}
		ScoreboardManager.updatePlayers();
	}
	
	public static void endGame() {
		Main.gameRunning = false;
		
		int minutes = Main.gameTime / 60;
		int seconds = Main.gameTime % 60;
		String time = String.format("%d:%02d", minutes, seconds);

		for (QuakePlayer qp : new ArrayList<>(Main.players)) {
			qp.getPlayer().sendMessage("§a==============================");
			qp.getPlayer().sendMessage(Utils.centerText("Map: §6"+Main.players.get(0).getPlayer().getLocation().getWorld().getName()));
			qp.getPlayer().sendMessage(Utils.centerText("Time: §e"+time));
			qp.getPlayer().sendMessage("");
			
			for (int i = 0; i < 3; i++)
				if (i < Main.players.size())
					qp.getPlayer().sendMessage(Utils.centerText(Main.players.get(i).getPlayer().getDisplayName()+": "+Main.players.get(i).getScore()));
			
			qp.getPlayer().sendMessage("§a==============================");
		}
		
		for (int i = 0; i < Main.players.size(); i++)
			Core.updateName(Main.players.get(i), NameTagVisibility.ALWAYS);
		
		for (QuakePlayer p : Main.players) {
			p.getPlayer().teleport(Main.spawn);
			p.getPlayer().getInventory().clear();
			p.setScore(0);
			p.killstreak = 0;
			p.getPlayer().removePotionEffect(PotionEffectType.SPEED);
			p.getPlayer().removePotionEffect(PotionEffectType.JUMP);
			p.getPlayer().removePotionEffect(PotionEffectType.SLOW);
			p.getPlayer().removePotionEffect(PotionEffectType.BLINDNESS);
			p.getPlayer().removePotionEffect(PotionEffectType.INVISIBILITY);
			CustomMenu.giveItem(p.getPlayer());
		}
	}
	
	public static void teleportToRespawn(Player p) {
		p.teleport(Main.respawns.get(new Random().nextInt(Main.respawns.size())));
	}
	
	public static void loadRespawn(String worldName) {
		YamlConfiguration config = YamlConfiguration.loadConfiguration(new File("./plugins/Quake/"+worldName+".yml"));
		
		Main.respawns.clear();
		Set<String> respawns = config.getConfigurationSection("loc").getKeys(false);
		
		for (String i : respawns) {
            String world = config.getString("loc."+i+".world");
            double x = config.getDouble("loc."+i+".x") + 0.5f;
            double y = config.getDouble("loc."+i+".y") + 0.5f;
            double z = config.getDouble("loc."+i+".z") + 0.5f;
			Main.respawns.add(new Location(Bukkit.getWorld(world), x, y, z, 0, 0));
		}
	}
	
	public static boolean clickHoe(Material mat) {
		return (mat.equals(Material.WOOD_HOE) ||
				mat.equals(Material.STONE_HOE) ||
				mat.equals(Material.IRON_HOE) ||
				mat.equals(Material.GOLD_HOE) ||
				mat.equals(Material.DIAMOND_HOE));
	}
	
	public void cooldownTimer() {
		new BukkitRunnable(){
			@Override
			public void run(){
				try {
					if (Main.gameRunning) {
						timer();
					}
				} catch(Exception ignored) {}
			}
		}.runTaskTimerAsynchronously(Main.getPlugin(Main.class), 0L, 1L);
	}
	
	public void gameTimer() {
		new BukkitRunnable(){
			@Override
			public void run(){
				try {
					if (Main.gameRunning)
						for (QuakePlayer p : Main.players)
							if (p.score >= GameOption.MAX_SCORE)
								endGame();
				} catch(Exception ignored) {}
			}
		}.runTaskTimer(Main.getPlugin(Main.class), 0L, 1L);
		
		new BukkitRunnable(){
			@Override
			public void run(){
				try {
					if (Main.gameRunning) {
						gameTimerFunc();
					}
				} catch(Exception ignored) {}
			}
		}.runTaskTimerAsynchronously(Main.getPlugin(Main.class), 0L, 20L);
	}
	
	private void gameTimerFunc() {
		Main.gameTime++;
		
		int minutes = Main.gameTime / 60;
		int seconds = Main.gameTime % 60;
		String time = String.format("%d:%02d", minutes, seconds);
		
		for (QuakePlayer p : Main.players) {
			p.getBoard().set(13, "Time: §a"+time);
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
}
