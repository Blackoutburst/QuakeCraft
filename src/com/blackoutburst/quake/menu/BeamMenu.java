package com.blackoutburst.quake.menu;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.blackoutburst.quake.core.QuakePlayer;
import com.blackoutburst.quake.main.Main;

import net.minecraft.server.v1_8_R3.EnumParticle;

public class BeamMenu {
	
	public static void open(Player p) {
		Inventory inv = Main.getPlugin(Main.class).getServer().createInventory(null, 36, "Beam Menu");
		
		ItemStack item = new ItemStack(Material.DOUBLE_PLANT, 1);
		ItemMeta meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.values());
		meta.setDisplayName("§bPower");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7Choose a gun trail particle!");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(10, item);
		
		item = new ItemStack(Material.FIREWORK, 1);
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
		
		item = new ItemStack(Material.RED_ROSE, 1);
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
		
		item = new ItemStack(Material.FIREWORK_CHARGE, 1);
		meta = item.getItemMeta();
		meta.setDisplayName("§bSmoke");
		lore = new ArrayList<>();
		lore.add("§7Choose a gun trail particle!");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(15, item);		
		
		item = new ItemStack(Material.SNOW_BALL, 1);
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
		
		item = new ItemStack(Material.BREWING_STAND_ITEM, 1);
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
		
		item = new ItemStack(Material.WATER_LILY, 1);
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
		
		p.openInventory(inv);
	}
	
	public static void getValue(int slot, Player p, boolean open) {
		QuakePlayer qp = QuakePlayer.getFromPlayer(p);
		if (qp == null) return;

		switch (slot) {
			case 10: qp.getGunProfile().setTrail(EnumParticle.BARRIER); qp.savePlayerData("trail", slot); if (open) CustomMenu.open(p); break;
			case 11: qp.getGunProfile().setTrail(EnumParticle.FIREWORKS_SPARK); qp.savePlayerData("trail", slot); if (open) CustomMenu.open(p); break;
			case 12: qp.getGunProfile().setTrail(EnumParticle.CRIT); qp.savePlayerData("trail", slot); if (open) CustomMenu.open(p); break;
			case 13: qp.getGunProfile().setTrail(EnumParticle.HEART); qp.savePlayerData("trail", slot); if (open) CustomMenu.open(p); break;
			case 14: qp.getGunProfile().setTrail(EnumParticle.VILLAGER_HAPPY); qp.savePlayerData("trail", slot); if (open) CustomMenu.open(p); break;
			case 15: qp.getGunProfile().setTrail(EnumParticle.SMOKE_NORMAL); qp.savePlayerData("trail", slot); if (open) CustomMenu.open(p); break;
			case 16: qp.getGunProfile().setTrail(EnumParticle.CLOUD); qp.savePlayerData("trail", slot); if (open) CustomMenu.open(p); break;
			case 19: qp.getGunProfile().setTrail(EnumParticle.ENCHANTMENT_TABLE); qp.savePlayerData("trail", slot); if (open) CustomMenu.open(p); break;
			case 20: qp.getGunProfile().setTrail(EnumParticle.SPELL_WITCH); qp.savePlayerData("trail", slot); if (open) CustomMenu.open(p); break;
			case 21: qp.getGunProfile().setTrail(EnumParticle.REDSTONE); qp.savePlayerData("trail", slot); if (open) CustomMenu.open(p); break;
			case 22: qp.getGunProfile().setTrail(EnumParticle.VILLAGER_ANGRY); qp.savePlayerData("trail", slot); if (open) CustomMenu.open(p); break;
			case 23: qp.getGunProfile().setTrail(EnumParticle.FLAME); qp.savePlayerData("trail", slot); if (open) CustomMenu.open(p); break;
			case 24: qp.getGunProfile().setTrail(EnumParticle.WATER_BUBBLE); qp.savePlayerData("trail", slot); if (open) CustomMenu.open(p); break;
			case 25: qp.getGunProfile().setTrail(EnumParticle.NOTE); qp.savePlayerData("trail", slot); if (open) CustomMenu.open(p); break;
			default: break;
		}
	}
	
}
