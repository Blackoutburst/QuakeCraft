package blackout.menu;

import java.util.ArrayList;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import blackout.quake.core.GunProfile;
import blackout.quake.core.QuakePlayer;
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
	
	private static Material getExplosionIcon(GunProfile gp) {
		switch(gp.getShape()) {
			case BALL: return (Material.FIREWORK_CHARGE);
			case BALL_LARGE: return (Material.FIREBALL);
			case BURST: return (Material.FEATHER);
			case STAR: return (Material.GOLD_NUGGET);
			case CREEPER: return (Material.SKULL_ITEM);
			default: return (Material.FIREWORK_CHARGE);
		}
	}
	
	private static byte getExplosionColor(GunProfile gp) {
		if (gp.getColor().equals(Color.BLACK)) return (0);
		if (gp.getColor().equals(Color.RED)) return (1);
		if (gp.getColor().equals(Color.GREEN)) return (2);
		if (gp.getColor().equals(Color.fromRGB(89, 69, 13))) return (3);
		if (gp.getColor().equals(Color.NAVY)) return (4);
		if (gp.getColor().equals(Color.PURPLE)) return (5);
		if (gp.getColor().equals(Color.fromRGB(66, 135, 245))) return (6);
		if (gp.getColor().equals(Color.SILVER)) return (7);
		if (gp.getColor().equals(Color.GRAY)) return (8);
		if (gp.getColor().equals(Color.fromRGB(255, 130, 197))) return (9);
		if (gp.getColor().equals(Color.LIME)) return (10);
		if (gp.getColor().equals(Color.YELLOW)) return (11);
		if (gp.getColor().equals(Color.AQUA)) return (12);
		if (gp.getColor().equals(Color.FUCHSIA)) return (13);
		if (gp.getColor().equals(Color.ORANGE)) return (14);
		if (gp.getColor().equals(Color.WHITE)) return (15);
		return (0);
	}
	
	public static void open(Player p) {
		QuakePlayer qp = QuakePlayer.getFromPlayer(p);
		Inventory inv = Main.getPlugin(Main.class).getServer().createInventory(null, 27, "Gun customisation Menu");
		
		ItemStack item = new ItemStack(qp.getGunProfile().getGun(), 1);
		ItemMeta meta = item.getItemMeta();
		if (qp.getGunProfile().isSuperior()) {
			meta.addItemFlags(ItemFlag.values());
			meta.addEnchant(Enchantment.ARROW_DAMAGE, 10, true);
		}
		meta.setDisplayName("§bGun");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§6Open the gun menu");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(12, item);
		
		item = new ItemStack(getExplosionIcon(qp.getGunProfile()), 1, (byte)(getExplosionIcon(qp.getGunProfile()).equals(Material.SKULL_ITEM) ? 4 : 0));
		meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.values());
		meta.setDisplayName("§bExplosion shape");
		lore = new ArrayList<String>();
		lore.add("§6Open the explosion shape menu");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(13, item);
		
		item = new ItemStack(Material.INK_SACK, 1, getExplosionColor(qp.getGunProfile()));
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
