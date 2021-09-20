package blackout.menu;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import blackout.quake.main.Main;

public class CustomMenu {

	public static void giveItem(Player p) {
		ItemStack menu = new ItemStack(Material.NETHER_STAR, 1);
		ItemMeta menuMeta = menu.getItemMeta();
		
		p.getInventory().clear();
		menuMeta.setDisplayName("§bGun customisation");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§6Open the gun customisation menu");
		menuMeta.setLore(lore);
		menu.setItemMeta(menuMeta);
		p.getInventory().setItem(0, menu);
	}
	
	public static void open(Player p) {
		Inventory inv = Main.getPlugin(Main.class).getServer().createInventory(null, 27, "Gun customisation Menu");
		
		ItemStack item = new ItemStack(Material.WOOD_HOE, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§bGun");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§6Open the gun menu");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(12, item);
		
		item = new ItemStack(Material.FIREWORK_CHARGE, 1);
		meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.values());
		meta.setDisplayName("§bExplosion shape");
		lore = new ArrayList<String>();
		lore.add("§6Open the explosion shape menu");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(13, item);
		
		item = new ItemStack(Material.INK_SACK, 1, (byte) 12);
		meta = item.getItemMeta();
		meta.setDisplayName("§bExplosion color");
		lore = new ArrayList<String>();
		lore.add("§6Open the explosion color menu");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(14, item);
		
		p.openInventory(inv);
	}
	
	public static void click(int slot, Player p) {
		switch (slot) {
			case 12: GunMenu.open(p); break;
			case 13: ShapeMenu.open(p); break;
			case 14: ColorMenu.open(p); break;
			default: return;
		}
	}
	
}
