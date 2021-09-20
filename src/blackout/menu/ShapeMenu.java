package blackout.menu;

import java.util.ArrayList;

import org.bukkit.FireworkEffect.Type;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import blackout.quake.core.QuakePlayer;
import blackout.quake.main.Main;

public class ShapeMenu {
	
	public static void open(Player p) {
		Inventory inv = Main.getPlugin(Main.class).getServer().createInventory(null, 27, "Shape Menu");
		
		ItemStack item = new ItemStack(Material.FIREWORK_CHARGE, 1);
		ItemMeta meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.values());
		meta.setDisplayName("§bSmall explosion");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§7Click to select the small explosion");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(11, item);
		
		item = new ItemStack(Material.FIREBALL, 1);
		meta = item.getItemMeta();
		meta.setDisplayName("§bLarge explosion");
		lore = new ArrayList<String>();
		lore.add("§7Click to select the lage explosion");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(12, item);
		
		item = new ItemStack(Material.FEATHER, 1);
		meta = item.getItemMeta();
		meta.setDisplayName("§bBurst explosion");
		lore = new ArrayList<String>();
		lore.add("§7Click to select the burst explosion");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(13, item);
		
		item = new ItemStack(Material.GOLD_NUGGET, 1);
		meta = item.getItemMeta();
		meta.setDisplayName("§bStar explosion");
		lore = new ArrayList<String>();
		lore.add("§7Click to select the star explosion");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(14, item);
		
		item = new ItemStack(Material.SKULL_ITEM, 1, (byte)4);
		meta = item.getItemMeta();
		meta.setDisplayName("§bCreeper explosion");
		lore = new ArrayList<String>();
		lore.add("§7Click to select the creeper explosion");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(15, item);		
		
		p.openInventory(inv);
	}
	
	public static void click(int slot, Player p) {
		QuakePlayer qp = QuakePlayer.getFromPlayer(p);
		
		switch (slot) {
			case 11: qp.getGunProfile().setShape(Type.BALL); CustomMenu.open(p); break;
			case 12: qp.getGunProfile().setShape(Type.BALL_LARGE); CustomMenu.open(p); break;
			case 13: qp.getGunProfile().setShape(Type.BURST); CustomMenu.open(p); break;
			case 14: qp.getGunProfile().setShape(Type.STAR); CustomMenu.open(p); break;
			case 15: qp.getGunProfile().setShape(Type.CREEPER); CustomMenu.open(p); break;
			default: return;
		}
	}
	
}
