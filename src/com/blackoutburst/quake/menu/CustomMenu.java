package com.blackoutburst.quake.menu;

import com.blackoutburst.quake.core.GunProfile;
import com.blackoutburst.quake.core.QuakePlayer;
import com.blackoutburst.quake.main.Main;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

public class CustomMenu {

	public static void giveItem(Player p) {
		ItemStack menu = new ItemStack(Material.NETHER_STAR, 1);
		ItemMeta menuMeta = menu.getItemMeta();
		
		p.getInventory().clear();
		menuMeta.setDisplayName("§bGun customisation");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§6Open the gun customisation menu");
		menuMeta.setLore(lore);
		menu.setItemMeta(menuMeta);
		p.getInventory().setItem(8, menu);
	}
	
	private static Material getSoundIcon(GunProfile gp) {
		switch(gp.getSound()) {
			case ENTITY_HORSE_DEATH: return (Material.SADDLE);
			case ENTITY_BAT_DEATH: return (Material.FEATHER);
			case ENTITY_ENDERMAN_DEATH: return (Material.ENDER_PEARL);
			case ENTITY_IRON_GOLEM_DEATH: return (Material.IRON_BLOCK);
			case ENTITY_PIG_DEATH: return (Material.LEGACY_PORK);
			case ENTITY_COW_HURT: return (Material.LEATHER);
			case ENTITY_CREEPER_DEATH: return (Material.LEGACY_SULPHUR);
			case BLOCK_ANVIL_LAND: return (Material.ANVIL);
			case ENTITY_GHAST_DEATH: return (Material.GHAST_TEAR);
			case ENTITY_ENDER_DRAGON_GROWL: return (Material.DRAGON_EGG);
			case ENTITY_VILLAGER_AMBIENT: return (Material.EMERALD);
			case ENTITY_WITHER_AMBIENT: return (Material.LEGACY_ENDER_PORTAL_FRAME);
			case ENTITY_PLAYER_LEVELUP: return (Material.LEGACY_EXP_BOTTLE);
			case ENTITY_ZOMBIE_BREAK_WOODEN_DOOR: return (Material.ROTTEN_FLESH);
			case ENTITY_ENDERMAN_TELEPORT: return (Material.LEGACY_ENDER_STONE);
			case ENTITY_SKELETON_DEATH: return (Material.BONE);
			case ENTITY_PLAYER_SPLASH: return (Material.WATER_BUCKET);
			default: return (Material.BLAZE_ROD);
		}
	}
	
	private static Material getExplosionIcon(GunProfile gp) {
		switch(gp.getShape()) {
			case BALL_LARGE: return (Material.LEGACY_FIREBALL);
			case BURST: return (Material.FEATHER);
			case STAR: return (Material.GOLD_NUGGET);
			case CREEPER: return (Material.LEGACY_SKULL_ITEM);
			default: return (Material.LEGACY_FIREWORK_CHARGE);
		}
	}
	
	private static Material getBeamIcon(GunProfile gp) {
		switch(gp.getTrail()) {
			case BARRIER: return (Material.LEGACY_DOUBLE_PLANT);
			case CRIT: return (Material.IRON_SWORD);
			case HEART: return (Material.LEGACY_RED_ROSE);
			case VILLAGER_HAPPY: return (Material.EMERALD);
			case SMOKE_NORMAL: return (Material.LEGACY_FIREWORK_CHARGE);
			case CLOUD: return (Material.LEGACY_SNOW_BALL);
			case ENCHANTMENT_TABLE: return (Material.BOOK);
			case SPELL_WITCH: return (Material.LEGACY_BREWING_STAND_ITEM);
			case REDSTONE: return (Material.PAINTING);
			case VILLAGER_ANGRY: return (Material.MAGMA_CREAM);
			case FLAME: return (Material.BLAZE_POWDER);
			case WATER_BUBBLE: return (Material.LEGACY_WATER_LILY);
			case NOTE: return (Material.NOTE_BLOCK);
			case SUSPENDED_DEPTH: return (Material.LEGACY_SKULL_ITEM);
			default: return (Material.LEGACY_FIREWORK);
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
		meta.setDisplayName("§aGun");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7Select your gun case");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(10, item);
		
		item = new ItemStack(getExplosionIcon(qp.getGunProfile()), 1, (byte)(getExplosionIcon(qp.getGunProfile()).equals(Material.LEGACY_SKULL_ITEM) ? 4 : 0));
		meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.values());
		meta.setDisplayName("§aExplosion shape");
		lore = new ArrayList<>();
		lore.add("§7Select the shape of your explosion!");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(11, item);
		
		item = new ItemStack(Material.LEGACY_INK_SACK, 1, getExplosionColor(qp.getGunProfile()));
		meta = item.getItemMeta();
		meta.setDisplayName("§aExplosion color");
		lore = new ArrayList<>();
		lore.add("§7Select the color of your explosion!");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(12, item);
		
		item = new ItemStack(getSoundIcon(qp.getGunProfile()), 1);
		meta = item.getItemMeta();
		meta.setDisplayName("§aKill Sounds");
		lore = new ArrayList<>();
		lore.add("§7Pick your sound to play on kill!");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(14, item);
		
		item = new ItemStack(Material.LEGACY_BANNER, 1, getNameColor(qp.getGunProfile()));
		meta = item.getItemMeta();
		meta.setDisplayName("§aName Color");
		lore = new ArrayList<>();
		lore.add("§7Select your name color!");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(15, item);
		
		if (getBeamIcon(qp.getGunProfile()).equals(Material.LEGACY_SKULL_ITEM)) {
			item = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (byte) 3);
			SkullMeta skullMeta = (SkullMeta) item.getItemMeta();
			skullMeta.addItemFlags(ItemFlag.values());
			skullMeta.setOwner("hannd");
			skullMeta.setDisplayName("§aBeam");
			lore = new ArrayList<>();
			lore.add("§7Select your beam!");
			skullMeta.setLore(lore);
			item.setItemMeta(skullMeta);
		} else {
			item = new ItemStack(getBeamIcon(qp.getGunProfile()), 1);
			meta = item.getItemMeta();
			meta.setDisplayName("§aBeam");
			lore = new ArrayList<>();
			lore.add("§7Select your beam!");
			meta.setLore(lore);
			item.setItemMeta(meta);
		}
		inv.setItem(16, item);
		
		p.openInventory(inv);
	}
	
	public static void click(int slot, Player p) {
		switch (slot) {
			case 10: GunMenu.open(p); break;
			case 11: ShapeMenu.open(p); break;
			case 12: ColorMenu.open(p); break;
			case 14: SoundsMenu.open(p); break;
			case 15: NameColorMenu.open(p); break;
			case 16: BeamMenu.open(p); break;
			default: break;
		}
	}
	
}
