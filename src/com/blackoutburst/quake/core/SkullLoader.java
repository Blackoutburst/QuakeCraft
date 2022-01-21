package com.blackoutburst.quake.core;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class SkullLoader {
	
	public static ItemStack hannd;
	
	public static void load() {
		hannd = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
		SkullMeta skullMeta = (SkullMeta) hannd.getItemMeta();
		skullMeta.addItemFlags(ItemFlag.values());
		skullMeta.setOwner("hannd");
		hannd.setItemMeta(skullMeta);
	}
}
