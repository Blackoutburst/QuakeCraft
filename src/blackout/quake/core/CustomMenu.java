package blackout.quake.core;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
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
		Inventory inv = Main.getPlugin(Main.class).getServer().createInventory(null, 27,  "Gun Menu");
		
		p.openInventory(inv);
	}
	
}
