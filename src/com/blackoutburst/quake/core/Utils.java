package com.blackoutburst.quake.core;

import com.blackoutburst.quake.main.Main;
import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;

public class Utils {
	
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

	public static void spawnParticleCubeCustom(Block b, Player p, EnumParticle part) {
		final PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
		final float nb = 0.2f;
		float x = b.getX();
		float y = b.getY();
		float z = b.getZ();

		for (float i = 0; i < 1; i += nb)
			connection.sendPacket(new PacketPlayOutWorldParticles(part, true, x + i, y, z, 0, 0, 0, 0, 1));
		for (float i = 0; i < 1; i += nb)
			connection.sendPacket(new PacketPlayOutWorldParticles(part, true, x, y + i, z, 0, 0, 0, 0, 1));
		for (float i = 0; i < 1; i += nb)
			connection.sendPacket(new PacketPlayOutWorldParticles(part, true, x, y, z + i, 0, 0, 0, 0, 1));

		x = b.getX() + 1;
		y = b.getY() + 1;
		z = b.getZ() + 1;

		for (float i = 0; i < 1; i += nb)
			connection.sendPacket(new PacketPlayOutWorldParticles(part, true, x - i, y, z, 0, 0, 0, 0, 1));
		for (float i = 0; i < 1; i += nb)
			connection.sendPacket(new PacketPlayOutWorldParticles(part, true, x, y - i, z, 0, 0, 0, 0, 1));
		for (float i = 0; i < 1; i += nb)
			connection.sendPacket(new PacketPlayOutWorldParticles(part, true, x, y, z - i, 0, 0, 0, 0, 1));

		x = b.getX() + 1;
		y = b.getY() + 1;
		z = b.getZ();

		for (float i = 0; i < 1; i += 0.2f)
			connection.sendPacket(new PacketPlayOutWorldParticles(part, true, x - i, y, z, 0, 0, 0, 0, 1));
		for (float i = 0; i < 1; i += 0.2f)
			connection.sendPacket(new PacketPlayOutWorldParticles(part, true, x, y - i, z, 0, 0, 0, 0, 1));

		x = b.getX() + 1;
		y = b.getY();
		z = b.getZ() + 1;

		for (float i = 0; i < 1; i += nb)
			connection.sendPacket(new PacketPlayOutWorldParticles(part, true, x - i, y, z, 0, 0, 0, 0, 1));
		for (float i = 0; i < 1; i += nb)
			connection.sendPacket(new PacketPlayOutWorldParticles(part, true, x, y, z - 1, 0, 0, 0, 0, 1));

		x = b.getX();
		y = b.getY() + 1;
		z = b.getZ() + 1;

		for (float i = 0; i < 1; i += nb)
			connection.sendPacket(new PacketPlayOutWorldParticles(part, true, x, y - i, z, 0, 0, 0, 0, 1));
		for (float i = 0; i < 1; i += nb)
			connection.sendPacket(new PacketPlayOutWorldParticles(part, true, x, y, z - 1, 0, 0, 0, 0, 1));

		x = b.getX() + 1;
		y = b.getY();
		z = b.getZ();

		for (float i = 0; i < 1; i += nb)
			connection.sendPacket(new PacketPlayOutWorldParticles(part, true, x, y, z + i, 0, 0, 0, 0, 1));

		x = b.getX();
		y = b.getY() + 1;
		z = b.getZ();

		for (float i = 0; i < 1; i += nb)
			connection.sendPacket(new PacketPlayOutWorldParticles(part, true, x, y, z + i, 0, 0, 0, 0, 1));
	}

	public static void spawnRotationLine(Location s, Player p) {
		final PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
		final EnumParticle part = EnumParticle.CRIT_MAGIC;
		final float nb = 0.1f;
		float x = (float)s.getX();
		float y = (float)s.getY();
		float z = (float)s.getZ();

		switch ((int)(s.getYaw())) {
			case 45:
				for (float i = 0; i <= 0.5f; i += nb)
					connection.sendPacket(new PacketPlayOutWorldParticles(part, true, x - i, y + 0.5f, z + i, 0, 0, 0, 0, 1));
				break;
			case 90:
				for (float i = 0; i <= 0.5f; i += nb)
					connection.sendPacket(new PacketPlayOutWorldParticles(part, true, x - i, y + 0.5f, z, 0, 0, 0, 0, 1));
				break;
			case 135:
				for (float i = 0; i <= 0.5f; i += nb)
					connection.sendPacket(new PacketPlayOutWorldParticles(part, true, x - i, y + 0.5f, z - i, 0, 0, 0, 0, 1));
				break;
			case 180:
				for (float i = 0; i <= 0.5f; i += nb)
					connection.sendPacket(new PacketPlayOutWorldParticles(part, true, x, y + 0.5f, z - i, 0, 0, 0, 0, 1));
				break;
			case 225:
				for (float i = 0; i <= 0.5f; i += nb)
					connection.sendPacket(new PacketPlayOutWorldParticles(part, true, x + i, y + 0.5f, z - i, 0, 0, 0, 0, 1));
				break;
			case 270:
				for (float i = 0; i <= 0.5f; i += nb)
					connection.sendPacket(new PacketPlayOutWorldParticles(part, true, x + i, y + 0.5f, z, 0, 0, 0, 0, 1));
				break;
			case 315:
				for (float i = 0; i <= 0.5f; i += nb)
					connection.sendPacket(new PacketPlayOutWorldParticles(part, true, x + i, y + 0.5f, z + i, 0, 0, 0, 0, 1));
				break;
			default:
				for (float i = 0; i <= 0.5f; i += nb)
					connection.sendPacket(new PacketPlayOutWorldParticles(part, true, x, y + 0.5f, z + i, 0, 0, 0, 0, 1));
				break;

		}
	}

	public static void spawnParticleCube(Player p) {
		for (Location s : Main.respawns) {
			if (Math.pow(s.getX() - p.getLocation().getX(), 2) +
					Math.pow(s.getY() - p.getLocation().getY(), 2) +
					Math.pow(s.getZ() - p.getLocation().getZ(), 2) > 1000)
				continue;

			spawnParticleCubeCustom(p.getWorld().getBlockAt(s), p, EnumParticle.CRIT);
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
}
