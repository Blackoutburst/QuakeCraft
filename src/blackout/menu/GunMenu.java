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
		meta.setDisplayName("§bWood gun");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§7Click to select the wood gun");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(11, item);
		
		item = new ItemStack(Material.STONE_HOE, 1);
		meta = item.getItemMeta();
		meta.setDisplayName("§bStone gun");
		lore = new ArrayList<String>();
		lore.add("§7Click to select the stone gun");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(12, item);
		
		item = new ItemStack(Material.IRON_HOE, 1);
		meta = item.getItemMeta();
		meta.setDisplayName("§bIron gun");
		lore = new ArrayList<String>();
		lore.add("§7Click to select the iron gun");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(13, item);
		
		item = new ItemStack(Material.GOLD_HOE, 1);
		meta = item.getItemMeta();
		meta.setDisplayName("§bGold gun");
		lore = new ArrayList<String>();
		lore.add("§7Click to select the gold gun");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(14, item);
		
		item = new ItemStack(Material.DIAMOND_HOE, 1);
		meta = item.getItemMeta();
		meta.setDisplayName("§bDiamond gun");
		lore = new ArrayList<String>();
		lore.add("§7Click to select the diamond gun");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(15, item);

		item = new ItemStack(Material.WOOD_HOE, 1);
		meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.values());
		meta.addEnchant(Enchantment.ARROW_DAMAGE, 10, true);
		meta.setDisplayName("§6Superior wood gun");
		lore = new ArrayList<String>();
		lore.add("§7Click to select the superior wood gun");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(20, item);
		
		item = new ItemStack(Material.STONE_HOE, 1);
		meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.values());
		meta.addEnchant(Enchantment.ARROW_DAMAGE, 10, true);
		meta.setDisplayName("§6Superior stone gun");
		lore = new ArrayList<String>();
		lore.add("§7Click to select the superior stone gun");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(21, item);
		
		item = new ItemStack(Material.IRON_HOE, 1);
		meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.values());
		meta.addEnchant(Enchantment.ARROW_DAMAGE, 10, true);
		meta.setDisplayName("§6Superior iron gun");
		lore = new ArrayList<String>();
		lore.add("§7Click to select the superior iron gun");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(22, item);
		
		item = new ItemStack(Material.GOLD_HOE, 1);
		meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.values());
		meta.addEnchant(Enchantment.ARROW_DAMAGE, 10, true);
		meta.setDisplayName("§6Superior gold gun");
		lore = new ArrayList<String>();
		lore.add("§7Click to select the superior gold gun");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(23, item);
		
		item = new ItemStack(Material.DIAMOND_HOE, 1);
		meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.values());
		meta.addEnchant(Enchantment.ARROW_DAMAGE, 10, true);
		meta.setDisplayName("§6Superior diamond gun");
		lore = new ArrayList<String>();
		lore.add("§7Click to select the superior diamond gun");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(24, item);
		
		
		p.openInventory(inv);
	}
	
	public static void click(int slot, Player p) {
		QuakePlayer qp = QuakePlayer.getFromPlayer(p);
		
		switch (slot) {
			case 11: qp.getGunProfile().setGun(Material.WOOD_HOE).setSuperior(false); CustomMenu.open(p); break;
			case 12: qp.getGunProfile().setGun(Material.STONE_HOE).setSuperior(false); CustomMenu.open(p); break;
			case 13: qp.getGunProfile().setGun(Material.IRON_HOE).setSuperior(false); CustomMenu.open(p); break;
			case 14: qp.getGunProfile().setGun(Material.GOLD_HOE).setSuperior(false); CustomMenu.open(p); break;
			case 15: qp.getGunProfile().setGun(Material.DIAMOND_HOE).setSuperior(false); CustomMenu.open(p); break;
			case 20: qp.getGunProfile().setGun(Material.WOOD_HOE).setSuperior(true); CustomMenu.open(p); break;
			case 21: qp.getGunProfile().setGun(Material.STONE_HOE).setSuperior(true); CustomMenu.open(p); break;
			case 22: qp.getGunProfile().setGun(Material.IRON_HOE).setSuperior(true); CustomMenu.open(p); break;
			case 23: qp.getGunProfile().setGun(Material.GOLD_HOE).setSuperior(true); CustomMenu.open(p); break;
			case 24: qp.getGunProfile().setGun(Material.DIAMOND_HOE).setSuperior(true); CustomMenu.open(p); break;
			default: return;
		}
	}
	
}
