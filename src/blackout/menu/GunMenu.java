package blackout.menu;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import blackout.quake.core.QuakePlayer;
import blackout.quake.main.Main;

public class GunMenu {
	
	public static void open(Player p) {
		Inventory inv = Main.getPlugin(Main.class).getServer().createInventory(null, 36, "Gun Menu");
		
		ItemStack item = new ItemStack(Material.WOOD_HOE, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§bWooden Case");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7Click to select the Wooden Case");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(11, item);
		
		item = new ItemStack(Material.STONE_HOE, 1);
		meta = item.getItemMeta();
		meta.setDisplayName("§bMarbled Case");
		lore = new ArrayList<>();
		lore.add("§7Click to select the Marbled Case");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(12, item);
		
		item = new ItemStack(Material.IRON_HOE, 1);
		meta = item.getItemMeta();
		meta.setDisplayName("§bReinforced Case");
		lore = new ArrayList<>();
		lore.add("§7Click to select the Reinforced Case");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(13, item);
		
		item = new ItemStack(Material.GOLD_HOE, 1);
		meta = item.getItemMeta();
		meta.setDisplayName("§bPlated Case");
		lore = new ArrayList<>();
		lore.add("§7Click to select the Plated Case");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(14, item);
		
		item = new ItemStack(Material.DIAMOND_HOE, 1);
		meta = item.getItemMeta();
		meta.setDisplayName("§bBling Case");
		lore = new ArrayList<>();
		lore.add("§7Click to select the Bling Case");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(15, item);

		item = new ItemStack(Material.WOOD_HOE, 1);
		meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.values());
		meta.addEnchant(Enchantment.ARROW_DAMAGE, 10, true);
		meta.setDisplayName("§6Varnished Wooden Case");
		lore = new ArrayList<>();
		lore.add("§7Click to select the Varnished Wooden Case");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(20, item);
		
		item = new ItemStack(Material.STONE_HOE, 1);
		meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.values());
		meta.addEnchant(Enchantment.ARROW_DAMAGE, 10, true);
		meta.setDisplayName("§6Polished Marbled Case");
		lore = new ArrayList<>();
		lore.add("§7Click to select the Polished Marbled Case");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(21, item);
		
		item = new ItemStack(Material.IRON_HOE, 1);
		meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.values());
		meta.addEnchant(Enchantment.ARROW_DAMAGE, 10, true);
		meta.setDisplayName("§6Polished Reinforced Case");
		lore = new ArrayList<>();
		lore.add("§7Click to select the Polished Reinforced Case");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(22, item);
		
		item = new ItemStack(Material.GOLD_HOE, 1);
		meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.values());
		meta.addEnchant(Enchantment.ARROW_DAMAGE, 10, true);
		meta.setDisplayName("§6Polished Plated Case");
		lore = new ArrayList<>();
		lore.add("§7Click to select the Polished Plated Case");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(23, item);
		
		item = new ItemStack(Material.DIAMOND_HOE, 1);
		meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.values());
		meta.addEnchant(Enchantment.ARROW_DAMAGE, 10, true);
		meta.setDisplayName("§6Polished Bling Case");
		lore = new ArrayList<>();
		lore.add("§7Click to select the Polished Bling Case");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(24, item);
		
		
		p.openInventory(inv);
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
