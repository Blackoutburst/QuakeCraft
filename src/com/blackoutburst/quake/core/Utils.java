package com.blackoutburst.quake.core;

import com.blackoutburst.quake.main.Main;
import org.apache.commons.lang.StringUtils;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

public class Utils {

	public static void loadConfig() {
		File f = new File("plugins/Quake/config/config.yml");
		YamlConfiguration file = YamlConfiguration.loadConfiguration(f);

		Main.getAtSpawnOnJoin = file.getBoolean("get-at-spawn-on-join");
		Main.voidBackToLobbyOutsideGame = file.getBoolean("void-back-to-lobby-outside-game");
		Main.disableDamageEverywhere = file.getBoolean("disable-damage-everywhere");
		Main.allowMapSelector = file.getBoolean("allow-map-selector");
		World world = Bukkit.getWorld(file.getString("spawn.world"));
		double x = file.getDouble("spawn.x");
		double y = file.getDouble("spawn.y");
		double z = file.getDouble("spawn.z");
		float yaw = (float) file.getDouble("spawn.yaw");
		float pitch = (float) file.getDouble("spawn.pitch");
		Main.spawn = new Location(world, x, y, z, yaw, pitch);
	}

	public static String centerText(String text) {
		int maxWidth = 60;
		int spaces = (int) Math.round((maxWidth - 1.4 * ChatColor.stripColor(text).length()) / 2);
		
		return StringUtils.repeat(" ", spaces) + text;
	}

	public static boolean isWand(PlayerInventory inv) {
		ItemStack item = inv.getItemInHand();

		return(item.getType() == Material.BLAZE_ROD &&
				item.getItemMeta().getDisplayName().equals("§6Spawn wand"));
	}

	public static void saveSpawns(String worldName) {
		File f = new File("plugins/Quake/"+worldName+".yml");
		if (f.exists()) f.delete();

		YamlConfiguration file = YamlConfiguration.loadConfiguration(f);

		for (int i = 0; i < Main.respawns.size(); i++) {
			file.set("loc."+i+".world", worldName);
			file.set("loc."+i+".x", Main.respawns.get(i).getBlockX());
			file.set("loc."+i+".y", Main.respawns.get(i).getBlockY());
			file.set("loc."+i+".z", Main.respawns.get(i).getBlockZ());
			file.set("loc."+i+".yaw", Main.respawns.get(i).getYaw());
			file.set("loc."+i+".pitch", Main.respawns.get(i).getPitch());
		}
		try {
			file.save(new File("plugins/Quake/"+worldName+".yml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean isSpawn(Location loc) {
		for (Location s : Main.respawns) {
			if (s.getBlockX() == loc.getBlockX() &&
					s.getBlockY() == loc.getBlockY() &&
					s.getBlockZ() == loc.getBlockZ()) {
				return (true);
			}
		}
		return (false);
	}

	public static Location getSpawn(Location loc) {
		for (Location s : Main.respawns) {
			if (s.getBlockX() == loc.getBlockX() &&
					s.getBlockY() == loc.getBlockY() &&
					s.getBlockZ() == loc.getBlockZ()) {
				return (s);
			}
		}
		return (null);
	}

	public static void spawnParticleCubeCustom(Block b, Player p, Particle part) {
		final float nb = 0.2f;
		float x = b.getX();
		float y = b.getY();
		float z = b.getZ();

		for (float i = 0; i < 1; i += nb)
			b.getWorld().spawnParticle(part, x + i, y, z, 1);
		for (float i = 0; i < 1; i += nb)
			b.getWorld().spawnParticle(part, x, y + i, z, 1);
		for (float i = 0; i < 1; i += nb)
			b.getWorld().spawnParticle(part, x, y, z + i, 1);

		x = b.getX() + 1;
		y = b.getY() + 1;
		z = b.getZ() + 1;

		for (float i = 0; i < 1; i += nb)
			b.getWorld().spawnParticle(part, x - i, y, z, 1);
		for (float i = 0; i < 1; i += nb)
			b.getWorld().spawnParticle(part, x, y - i, z, 1);
		for (float i = 0; i < 1; i += nb)
			b.getWorld().spawnParticle(part, x, y, z - i, 1);

		x = b.getX() + 1;
		y = b.getY() + 1;
		z = b.getZ();

		for (float i = 0; i < 1; i += 0.2f)
			b.getWorld().spawnParticle(part, x - i, y, z, 1);
		for (float i = 0; i < 1; i += 0.2f)
			b.getWorld().spawnParticle(part, x, y - i, z, 1);

		x = b.getX() + 1;
		y = b.getY();
		z = b.getZ() + 1;

		for (float i = 0; i < 1; i += nb)
			b.getWorld().spawnParticle(part, x - i, y, z, 1);
		for (float i = 0; i < 1; i += nb)
			b.getWorld().spawnParticle(part, x, y, z - 1, 1);

		x = b.getX();
		y = b.getY() + 1;
		z = b.getZ() + 1;

		for (float i = 0; i < 1; i += nb)
			b.getWorld().spawnParticle(part, x, y - i, z, 1);
		for (float i = 0; i < 1; i += nb)
			b.getWorld().spawnParticle(part, x, y, z - 1, 1);

		x = b.getX() + 1;
		y = b.getY();
		z = b.getZ();

		for (float i = 0; i < 1; i += nb)
			b.getWorld().spawnParticle(part, x, y, z + i, 1);

		x = b.getX();
		y = b.getY() + 1;
		z = b.getZ();

		for (float i = 0; i < 1; i += nb)
			b.getWorld().spawnParticle(part, x, y, z + i, 1);
	}

	public static void spawnRotationLine(Location s, Player p) {
		final Particle part = Particle.CRIT_MAGIC;
		final float nb = 0.1f;
		float x = (float)s.getX();
		float y = (float)s.getY();
		float z = (float)s.getZ();

		switch ((int)(s.getYaw())) {
			case 45:
				for (float i = 0; i <= 0.5f; i += nb)
					s.getWorld().spawnParticle(part, x - i, y + 0.5f, z + i, 1);
				break;
			case 90:
				for (float i = 0; i <= 0.5f; i += nb)
					s.getWorld().spawnParticle(part, x - i, y + 0.5f, z, 1);
				break;
			case 135:
				for (float i = 0; i <= 0.5f; i += nb)
					s.getWorld().spawnParticle(part, x - i, y + 0.5f, z - i, 1);
				break;
			case 180:
				for (float i = 0; i <= 0.5f; i += nb)
					s.getWorld().spawnParticle(part, x, y + 0.5f, z - i, 1);
				break;
			case 225:
				for (float i = 0; i <= 0.5f; i += nb)
					s.getWorld().spawnParticle(part, x + i, y + 0.5f, z - i, 1);
				break;
			case 270:
				for (float i = 0; i <= 0.5f; i += nb)
					s.getWorld().spawnParticle(part, x + i, y + 0.5f, z, 1);
				break;
			case 315:
				for (float i = 0; i <= 0.5f; i += nb)
					s.getWorld().spawnParticle(part, x + i, y + 0.5f, z + i, 1);
				break;
			default:
				for (float i = 0; i <= 0.5f; i += nb)
					s.getWorld().spawnParticle(part, x, y + 0.5f, z + i, 1);
				break;

		}
	}

	public static void spawnParticleCube(Player p) {
		for (Location s : Main.respawns) {
			if (Math.pow(s.getX() - p.getLocation().getX(), 2) +
					Math.pow(s.getY() - p.getLocation().getY(), 2) +
					Math.pow(s.getZ() - p.getLocation().getZ(), 2) > 1000)
				continue;

			spawnParticleCubeCustom(p.getWorld().getBlockAt(s), p, Particle.CRIT);
			spawnRotationLine(s, p);
		}
	}

	public static void spawnParticlesScheduler() {
		new BukkitRunnable(){
			@Override
			public void run(){
				try {
					for (Player p : Bukkit.getOnlinePlayers()) {
						if (isWand(p.getInventory())) {
							spawnParticleCube(p);
						}
					}
				} catch(Exception ignored) {}
			}
		}.runTaskTimerAsynchronously(Main.getPlugin(Main.class), 0L, 5L);
	}

	public static String multiKill(int kill) {
		switch(kill) {
			case 2: return ("§c§l§oDouble-kill!");
			case 3: return ("§c§l§oTriple-kill!");
			case 4: return ("§c§l§oQuadruple-kill!");
			case 5: return ("§c§l§oPenta-kill!");
			case 6: return ("§c§l§oHexa-kill!");
			case 7: case 8: case 9: return ("§c§l§oMonster-kill!");
			default: return ("§c§l§oWtf-bro!");
		}
	}

	public static String killStreak(QuakePlayer owner) {
		switch(owner.killstreak) {
			case 5: return (owner.player.getDisplayName()+" §r§b§ois on a Killing Spree!");
			case 10: return (owner.player.getDisplayName()+" §r§b§ois on a Rampage!");
			case 15: return (owner.player.getDisplayName()+" §r§b§ois Dominating!");
			case 20: return (owner.player.getDisplayName()+" §r§b§ois Unstoppable!");
			case 25: return (owner.player.getDisplayName()+" §r§b§ois Godlike!");
			case 30: return (owner.player.getDisplayName()+" §r§b§obroke the game!");
			case 35: return (owner.player.getDisplayName()+" §r§b§ofixed the game!");
			case 40: return (owner.player.getDisplayName()+" §r§b§oentered a new dimension!");
			case 50: return (owner.player.getDisplayName()+" §r§b§owent into oblivion!");
			case 60: return (owner.player.getDisplayName()+" §r§b§oreached infinite and beyond!");
			case 70: return (owner.player.getDisplayName()+" §r§b§ofound the meaning of life!");
			case 75: return (owner.player.getDisplayName()+" §r§b§ocame back to Earth!");
			case 80: return (owner.player.getDisplayName()+" §r§b§obecame the first Quakecraft prophet!");
			case 85: return (owner.player.getDisplayName()+" §r§b§ocreated an army of Quake players!");
			case 90: return (owner.player.getDisplayName()+" §r§b§ois now a Quake emperor!");
			case 100: return (owner.player.getDisplayName()+"§r§b§o, you sure have a lot of friends.");
			case 101: return ("§r§b§oNice party you have there, §r"+owner.player.getDisplayName()+"§r§b§o.");
			default: return ("none");
		}
	}

	public static Color getColor(int trailColor) {
		switch(trailColor) {
			default: return new Color(255, 0, 0);
			case 1: return new Color(255, 85, 0);
			case 2: return new Color(255, 132, 0);
			case 3: return new Color(255, 174, 0);
			case 4: return new Color(255, 255, 0);
			case 5: return new Color(174, 255, 0);
			case 6: return new Color(132, 255, 0);
			case 7: return new Color(85, 255, 0);
			case 8: return new Color(0, 255, 0);
			case 9: return new Color(0, 255, 85);
			case 10: return new Color(0, 255, 132);
			case 11: return new Color(0, 255, 174);
			case 12: return new Color(0, 255, 255);
			case 13: return new Color(0, 174, 255);
			case 14: return new Color(0, 132, 255);
			case 15: return new Color(0, 85, 255);
			case 16: return new Color(0, 0, 255);
			case 17: return new Color(85, 0, 255);
			case 18: return new Color(132, 0, 255);
			case 19: return new Color(174, 0, 255);
			case 20: return new Color(255, 0, 255);
			case 21: return new Color(255, 0, 174);
			case 22: return new Color(255, 0, 132);
			case 23: return new Color(255, 0, 85);
		}
	}

	public static void createCircle(Location location) {
		for (int i = 9; i > 0; i--) {
			final double angle = 2 * Math.PI * i / 9;
			final double x = Math.cos(angle) * 0.3f;
			final double y = Math.sin(angle) * 0.3f;

			Vector v = rotateAroundAxisX(new Vector(x, y, 0), location.getPitch());
			v = rotateAroundAxisY(v, location.getYaw());

			final Location temp = location.clone().add(v);

			location.getWorld().spawnParticle(Particle.FLAME, (float) temp.getX(), (float) temp.getY(), (float) temp.getZ(), 1);
		}
	}

	private static Vector rotateAroundAxisX(Vector v, double angle) {
		angle = Math.toRadians(angle);

		final double cos = Math.cos(angle);
		final double sin = Math.sin(angle);
		final double y = v.getY() * cos - v.getZ() * sin;
		final double z = v.getY() * sin + v.getZ() * cos;
		return v.setY(y).setZ(z);
	}

	private static Vector rotateAroundAxisY(Vector v, double angle) {
		angle = -angle;
		angle = Math.toRadians(angle);

		final double cos = Math.cos(angle);
		final double sin = Math.sin(angle);
		final double x = v.getX() * cos + v.getZ() * sin;
		final double z = v.getX() * -sin + v.getZ() * cos;
		return v.setX(x).setZ(z);
	}
}
