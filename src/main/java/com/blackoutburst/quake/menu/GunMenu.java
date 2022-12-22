package com.blackoutburst.quake.menu;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.blackoutburst.quake.core.QuakePlayer;
import com.blackoutburst.quake.main.Main;

public class GunMenu {
	
	public static void open(Player p) {
		Inventory inv = Main.getPlugin(Main.class).getServer().createInventory(null, 36, "Gun Menu");
		
		setItem(inv, Material.WOOD_HOE, "§bWooden Case", 11, false);	
		setItem(inv, Material.STONE_HOE, "§bMarbled Case", 12, false);		
		setItem(inv, Material.IRON_HOE, "§bReinforced Case", 13, false);
		setItem(inv, Material.GOLD_HOE, "§bPlated Case", 14, false);
		setItem(inv, Material.DIAMOND_HOE, "§bBling Case", 15, false);
		
		setItem(inv, Material.WOOD_HOE, "§6Varnished Wooden Case", 20, true);	
		setItem(inv, Material.STONE_HOE, "§6Polished Marbled Case", 21, true);		
		setItem(inv, Material.IRON_HOE, "§6Polished Reinforced Case", 22, true);
		setItem(inv, Material.GOLD_HOE, "§6Polished Plated Case", 23, true);
		setItem(inv, Material.DIAMOND_HOE, "§6Polished Bling Case", 24, true);
		
		p.openInventory(inv);
	}
	
	private static void setItem(Inventory inv, Material mat, String name, int slot, boolean polished) {
		ItemStack item = new ItemStack(mat, 1);
		ItemMeta meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.values());
		if (polished)
			meta.addEnchant(Enchantment.ARROW_DAMAGE, 10, true);
		meta.setDisplayName(name);
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7Click to select the "+name);
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(slot, item);
	}
	
	public static void getValue(int slot, Player p, boolean open) {
		QuakePlayer qp = QuakePlayer.getFromPlayer(p);
		if (qp == null) return;

		switch (slot) {
			case 11: qp.getGunProfile().setGun(Material.WOOD_HOE).setSuperior(false).setName("§bWooden Case"); qp.savePlayerData("gun", slot); if (open) CustomMenu.open(p); break;
			case 12: qp.getGunProfile().setGun(Material.STONE_HOE).setSuperior(false).setName("§bMarbled Case"); qp.savePlayerData("gun", slot); if (open) CustomMenu.open(p); break;
			case 13: qp.getGunProfile().setGun(Material.IRON_HOE).setSuperior(false).setName("§bReinforced Case"); qp.savePlayerData("gun", slot); if (open) CustomMenu.open(p); break;
			case 14: qp.getGunProfile().setGun(Material.GOLD_HOE).setSuperior(false).setName("§bPlated Case"); qp.savePlayerData("gun", slot); if (open) CustomMenu.open(p); break;
			case 15: qp.getGunProfile().setGun(Material.DIAMOND_HOE).setSuperior(false).setName("§bBling Case"); qp.savePlayerData("gun", slot); if (open) CustomMenu.open(p); break;
			case 20: qp.getGunProfile().setGun(Material.WOOD_HOE).setSuperior(true).setName("§6Varnished Wooden Case"); qp.savePlayerData("gun", slot); if (open) CustomMenu.open(p); break;
			case 21: qp.getGunProfile().setGun(Material.STONE_HOE).setSuperior(true).setName("§6Polished Marbled Case"); qp.savePlayerData("gun", slot); if (open) CustomMenu.open(p); break;
			case 22: qp.getGunProfile().setGun(Material.IRON_HOE).setSuperior(true).setName("§6Polished Reinforced Case"); qp.savePlayerData("gun", slot); if (open) CustomMenu.open(p); break;
			case 23: qp.getGunProfile().setGun(Material.GOLD_HOE).setSuperior(true).setName("§6Polished Plated Case"); qp.savePlayerData("gun", slot); if (open) CustomMenu.open(p); break;
			case 24: qp.getGunProfile().setGun(Material.DIAMOND_HOE).setSuperior(true).setName("§6Polished Bling Case"); qp.savePlayerData("gun", slot); if (open) CustomMenu.open(p); break;
			default: break;
		}
	}
	
}
