package com.blackoutburst.quake.core;

import net.minecraft.world.level.World;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class SkullLoader {


	public static ItemStack hannd;
	public static net.minecraft.world.item.ItemStack hanndNMS;
	public static World world;

	public static void load() {
		hannd = new ItemStack(Material.PLAYER_HEAD, 1, (byte) 3);
		SkullMeta skullMeta = (SkullMeta) hannd.getItemMeta();
		skullMeta.addItemFlags(ItemFlag.values());
		skullMeta.setOwner("hannd");
		hannd.setItemMeta(skullMeta);

		hanndNMS = CraftItemStack.asNMSCopy(hannd);
	}

	public static void loadBeamWorld(String worldName) {
		world = ((CraftWorld) Bukkit.getWorld(worldName)).getHandle();
	}
}
