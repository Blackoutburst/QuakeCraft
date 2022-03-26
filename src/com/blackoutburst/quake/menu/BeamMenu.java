package com.blackoutburst.quake.menu;

import com.blackoutburst.quake.core.QuakePlayer;
import com.blackoutburst.quake.main.Main;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

public class BeamMenu {
	
	/*
	[00] [01] [02] [03] [04] [05] [06] [07] [08]
	[09] [10] [11] [12] [13] [14] [15] [16] [17]
	[18] [19] [20] [21] [22] [23] [24] [25] [26]
	[27] [28] [29] [30] [31] [32] [33] [34] [35]
	[36] [37] [38] [39] [40] [41] [42] [43] [44]
	*/
	
	public static void open(Player p) {
		Inventory inv = Main.getPlugin(Main.class).getServer().createInventory(null, 45, "Beam Menu");
		
		ItemStack item = new ItemStack(Material.SUNFLOWER, 1);
		ItemMeta meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.values());
		meta.setDisplayName("§bPower");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7Choose a gun trail particle!");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(10, item);
		
		item = new ItemStack(Material.FIREWORK_ROCKET, 1);
		meta = item.getItemMeta();
		meta.setDisplayName("§bFirework Sparks");
		lore = new ArrayList<>();
		lore.add("§7Choose a gun trail particle!");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(11, item);
		
		item = new ItemStack(Material.IRON_SWORD, 1);
		meta = item.getItemMeta();
		meta.setDisplayName("§bCritical Hit");
		lore = new ArrayList<>();
		lore.add("§7Choose a gun trail particle!");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(12, item);
		
		item = new ItemStack(Material.POPPY, 1);
		meta = item.getItemMeta();
		meta.setDisplayName("§bLove");
		lore = new ArrayList<>();
		lore.add("§7Choose a gun trail particle!");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(13, item);
		
		item = new ItemStack(Material.EMERALD, 1);
		meta = item.getItemMeta();
		meta.setDisplayName("§bGreen Stars");
		lore = new ArrayList<>();
		lore.add("§7Choose a gun trail particle!");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(14, item);		
		
		item = new ItemStack(Material.FIREWORK_STAR, 1);
		meta = item.getItemMeta();
		meta.setDisplayName("§bSmoke");
		lore = new ArrayList<>();
		lore.add("§7Choose a gun trail particle!");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(15, item);		
		
		item = new ItemStack(Material.SNOWBALL, 1);
		meta = item.getItemMeta();
		meta.setDisplayName("§bCloud");
		lore = new ArrayList<>();
		lore.add("§7Choose a gun trail particle!");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(16, item);		
		
		item = new ItemStack(Material.BOOK, 1);
		meta = item.getItemMeta();
		meta.setDisplayName("§bBookworm");
		lore = new ArrayList<>();
		lore.add("§7Choose a gun trail particle!");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(19, item);		
		
		item = new ItemStack(Material.BREWING_STAND, 1);
		meta = item.getItemMeta();
		meta.setDisplayName("§bPurple Stars");
		lore = new ArrayList<>();
		lore.add("§7Choose a gun trail particle!");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(20, item);	
		
		item = new ItemStack(Material.PAINTING, 1);
		meta = item.getItemMeta();
		meta.setDisplayName("§bRainbow");
		lore = new ArrayList<>();
		lore.add("§7Choose a gun trail particle!");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(21, item);		
		
		item = new ItemStack(Material.MAGMA_CREAM, 1);
		meta = item.getItemMeta();
		meta.setDisplayName("§bThundercoulds");
		lore = new ArrayList<>();
		lore.add("§7Choose a gun trail particle!");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(22, item);	
		
		item = new ItemStack(Material.BLAZE_POWDER, 1);
		meta = item.getItemMeta();
		meta.setDisplayName("§bFlames");
		lore = new ArrayList<>();
		lore.add("§7Choose a gun trail particle!");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(23, item);	
		
		item = new ItemStack(Material.LILY_PAD, 1);
		meta = item.getItemMeta();
		meta.setDisplayName("§bBubbles");
		lore = new ArrayList<>();
		lore.add("§7Choose a gun trail particle!");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(24, item);	
		
		item = new ItemStack(Material.NOTE_BLOCK, 1);
		meta = item.getItemMeta();
		meta.setDisplayName("§bNotes");
		lore = new ArrayList<>();
		lore.add("§7Choose a gun trail particle!");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(25, item);	
		
		
		item = new ItemStack(Material.PLAYER_HEAD, 1, (byte) 3);
		SkullMeta skullMeta = (SkullMeta) item.getItemMeta();
		skullMeta.addItemFlags(ItemFlag.values());
		skullMeta.setOwner("hannd");
		skullMeta.setDisplayName("§bHannd ???");
		lore = new ArrayList<>();
		lore.add("§7HAND, HAANND");
		lore.add("§4§lHaNnD??????");
		lore.add("§6§mRackals suggestions are weird §r§b§nHAAAAAAANNNNNNNNNND");
		skullMeta.setLore(lore);
		item.setItemMeta(skullMeta);
		inv.setItem(31, item);
		
		p.openInventory(inv);
	}
	
	public static void getValue(int slot, Player p, boolean open) {
		QuakePlayer qp = QuakePlayer.getFromPlayer(p);
		if (qp == null) return;

		switch (slot) {
			case 10: qp.getGunProfile().setTrail(Particle.BARRIER); qp.savePlayerData("trail", slot); if (open) CustomMenu.open(p); break;
			case 11: qp.getGunProfile().setTrail(Particle.FIREWORKS_SPARK); qp.savePlayerData("trail", slot); if (open) CustomMenu.open(p); break;
			case 12: qp.getGunProfile().setTrail(Particle.CRIT); qp.savePlayerData("trail", slot); if (open) CustomMenu.open(p); break;
			case 13: qp.getGunProfile().setTrail(Particle.HEART); qp.savePlayerData("trail", slot); if (open) CustomMenu.open(p); break;
			case 14: qp.getGunProfile().setTrail(Particle.VILLAGER_HAPPY); qp.savePlayerData("trail", slot); if (open) CustomMenu.open(p); break;
			case 15: qp.getGunProfile().setTrail(Particle.SMOKE_NORMAL); qp.savePlayerData("trail", slot); if (open) CustomMenu.open(p); break;
			case 16: qp.getGunProfile().setTrail(Particle.CLOUD); qp.savePlayerData("trail", slot); if (open) CustomMenu.open(p); break;
			case 19: qp.getGunProfile().setTrail(Particle.ENCHANTMENT_TABLE); qp.savePlayerData("trail", slot); if (open) CustomMenu.open(p); break;
			case 20: qp.getGunProfile().setTrail(Particle.SPELL_WITCH); qp.savePlayerData("trail", slot); if (open) CustomMenu.open(p); break;
			case 21: qp.getGunProfile().setTrail(Particle.REDSTONE); qp.savePlayerData("trail", slot); if (open) CustomMenu.open(p); break;
			case 22: qp.getGunProfile().setTrail(Particle.VILLAGER_ANGRY); qp.savePlayerData("trail", slot); if (open) CustomMenu.open(p); break;
			case 23: qp.getGunProfile().setTrail(Particle.FLAME); qp.savePlayerData("trail", slot); if (open) CustomMenu.open(p); break;
			case 24: qp.getGunProfile().setTrail(Particle.WATER_BUBBLE); qp.savePlayerData("trail", slot); if (open) CustomMenu.open(p); break;
			case 25: qp.getGunProfile().setTrail(Particle.NOTE); qp.savePlayerData("trail", slot); if (open) CustomMenu.open(p); break;
			case 31: qp.getGunProfile().setTrail(Particle.SUSPENDED_DEPTH); qp.savePlayerData("trail", slot); if (open) CustomMenu.open(p); break;
			default: break;
		}
	}
	
}
