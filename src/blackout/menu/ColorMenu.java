package blackout.menu;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import blackout.quake.main.Main;

public class ColorMenu {
	
	public static void open(Player p) {
		Inventory inv = Main.getPlugin(Main.class).getServer().createInventory(null, 54, "Color Menu");
		
		setItem(inv, 11, (byte)0, "§0Black");
		setItem(inv, 12, (byte)1, "§cRed");
		setItem(inv, 13, (byte)2, "§2Green");
		setItem(inv, 14, (byte)3, "§6Brown");
		setItem(inv, 15, (byte)4, "§1Blue");
		setItem(inv, 20, (byte)5, "§5Purple");
		setItem(inv, 21, (byte)6, "§3Blue");
		setItem(inv, 22, (byte)7, "§7Silver");
		setItem(inv, 23, (byte)8, "§8Gray");
		setItem(inv, 24, (byte)9, "§dPink");
		setItem(inv, 29, (byte)10, "§aLime");
		setItem(inv, 30, (byte)11, "§eYellow");
		setItem(inv, 31, (byte)12, "§bAqua");
		setItem(inv, 32, (byte)13, "§5Magenta");
		setItem(inv, 33, (byte)14, "§6Orange");
		setItem(inv, 40, (byte)15, "§fWhite");
		
		p.openInventory(inv);
	}
	
	private static void setItem(Inventory inv, int slot, byte data, String name) {
		ItemStack item = new ItemStack(Material.INK_SACK, 1, (short) data);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
        item.setItemMeta(meta);
		inv.setItem(slot, item);
	}
	
	public static void click(int slot, Player p) {
		switch (slot) {
			case 12: break;
			case 13: break;
			case 14: break;
			default: return;
		}
	}
	
}
