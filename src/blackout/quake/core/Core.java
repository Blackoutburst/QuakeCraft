package blackout.quake.core;

import java.io.File;
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

import blackout.menu.CustomMenu;
import blackout.quake.main.Main;

public class Core {

	public static void updateName(QuakePlayer qp) {
		qp.getPlayer().setDisplayName(qp.getGunProfile().getNameColor()+qp.getPlayer().getName()+"§r");
		qp.getPlayer().setPlayerListName(qp.getGunProfile().getNameColor()+qp.getPlayer().getName()+"§r");
		for (QuakePlayer hp : Main.players) {
			hp.getBoard().addTeam(hp, qp);
			qp.getBoard().addTeam(qp, hp);
		}
		ScoreboardManager.updatePlayers();
	}
	
	public static void startGame(String worldName) {
		Main.gameWorld = Bukkit.getWorld(worldName);
		
		
		Main.gameRunning = true;
		Main.gameTime = 0;
		
		loadRespawn(worldName);
		
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
			p.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100000, 1, false, false));
			p.setCooldown(RailGun.FIRE_DELAY);
			
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

		Bukkit.broadcastMessage("§a==============================");
		Bukkit.broadcastMessage(Utils.centerText("Map: §6"+Main.players.get(0).getPlayer().getLocation().getWorld().getName()));
		Bukkit.broadcastMessage(Utils.centerText("Time: §e"+time));
		Bukkit.broadcastMessage("");
		
		for (int i = 0; i < 3; i++)
			if (i < Main.players.size())
				Bukkit.broadcastMessage(Utils.centerText(Main.players.get(i).getPlayer().getDisplayName()+": "+Main.players.get(i).getScore()));
		
		Bukkit.broadcastMessage("§a==============================");
		
		
		for (QuakePlayer p : Main.players) {
			p.getPlayer().teleport(Main.spawn);
			p.getPlayer().getInventory().clear();
			p.setScore(0);
			p.getPlayer().removePotionEffect(PotionEffectType.SPEED);
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
