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
		menuMeta.setDisplayName("�bGun customisation");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("�6Open the gun customisation menu");
		menuMeta.setLore(lore);
		menu.setItemMeta(menuMeta);
		p.getInventory().setItem(0, menu);
	}
	
	private static Material getExplosionIcon(GunProfile gp) {
		switch(gp.getShape()) {
			case BALL_LARGE: return (Material.FIREBALL);
			case BURST: return (Material.FEATHER);
			case STAR: return (Material.GOLD_NUGGET);
			case CREEPER: return (Material.SKULL_ITEM);
			default: return (Material.FIREWORK_CHARGE);
		}
	}
	
	private static byte getNameColor(GunProfile gp) {
		switch(gp.getNameColor()) {
			case DARK_RED: return ((byte) 1);
			case RED: return ((byte) 9);
			case GOLD: return ((byte) 14);
			case YELLOW: return ((byte) 11);
			case DARK_GREEN: return ((byte) 2);
			case GREEN: return ((byte) 10);
			case DARK_AQUA: return ((byte) 6);
			case DARK_BLUE: return ((byte) 4);
			case BLUE: return ((byte) 12);
			case LIGHT_PURPLE: return ((byte) 13);
			case DARK_PURPLE: return ((byte) 5);
			case GRAY: return ((byte) 7);
			case DARK_GRAY: return ((byte) 8);
			default: return ((byte) 15);
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
		if (qp == null) return;

		Inventory inv = Main.getPlugin(Main.class).getServer().createInventory(null, 27, "Gun customisation Menu");
		
		ItemStack item = new ItemStack(qp.getGunProfile().getGun(), 1);
		ItemMeta meta = item.getItemMeta();
		if (qp.getGunProfile().isSuperior()) {
			meta.addItemFlags(ItemFlag.values());
			meta.addEnchant(Enchantment.ARROW_DAMAGE, 10, true);
		}
		meta.setDisplayName("�aGun");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("�7Select your gun case");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(10, item);
		
		item = new ItemStack(getExplosionIcon(qp.getGunProfile()), 1, (byte)(getExplosionIcon(qp.getGunProfile()).equals(Material.SKULL_ITEM) ? 4 : 0));
		meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.values());
		meta.setDisplayName("�aExplosion shape");
		lore = new ArrayList<>();
		lore.add("�7Select the shape of your explosion!");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(11, item);
		
		item = new ItemStack(Material.INK_SACK, 1, getExplosionColor(qp.getGunProfile()));
		meta = item.getItemMeta();
		meta.setDisplayName("�aExplosion color");
		lore = new ArrayList<>();
		lore.add("�7Select the color of your explosion!");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(12, item);
		
		item = new ItemStack(Material.NOTE_BLOCK, 1);
		meta = item.getItemMeta();
		meta.setDisplayName("�aKill Sounds");
		lore = new ArrayList<>();
		lore.add("�7Pick your sound to play on kill!");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(14, item);
		
		item = new ItemStack(Material.BANNER, 1, getNameColor(qp.getGunProfile()));
		meta = item.getItemMeta();
		meta.setDisplayName("�aName Color");
		lore = new ArrayList<>();
		lore.add("�7Select your name color!");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(15, item);
		
		item = new ItemStack(Material.STRING, 1);
		meta = item.getItemMeta();
		meta.setDisplayName("�aBeam");
		lore = new ArrayList<>();
		lore.add("�7Select your beam!");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(16, item);
		
		p.openInventory(inv);
	}
	
	public static void click(int slot, Player p) {
		switch (slot) {
			case 11: GunMenu.open(p); break;
			case 12: ShapeMenu.open(p); break;
			case 13: ColorMenu.open(p); break;
			case 14: SoundsMenu.open(p); break;
			case 15: NameColorMenu.open(p); break;
			default: break;
		}
	}
	
}
