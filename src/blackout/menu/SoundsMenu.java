package blackout.menu;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import blackout.quake.core.QuakePlayer;
import blackout.quake.main.Main;

public class SoundsMenu {
	
	public static void open(Player p) {
		Inventory inv = Main.getPlugin(Main.class).getServer().createInventory(null, 54, "Kill Sounds");
		
		setItem(inv, 11, "§aBlaze Death", Material.BLAZE_ROD);
		setItem(inv, 12, "§aHorse Death", Material.SADDLE);
		setItem(inv, 13, "§aBat Death", Material.FEATHER);
		setItem(inv, 14, "§aEnderman Death", Material.ENDER_PEARL);
		setItem(inv, 15, "§aGolem Death", Material.IRON_BLOCK);
		setItem(inv, 20, "§aPig Death", Material.PORK);
		setItem(inv, 21, "§aCow Hurt", Material.LEATHER);
		setItem(inv, 22, "§aCreeper Death", Material.SULPHUR);
		setItem(inv, 23, "§aAnvil Land", Material.ANVIL);
		setItem(inv, 24, "§aGhast Death", Material.GHAST_TEAR);
		setItem(inv, 29, "§aDragon Growl", Material.DRAGON_EGG);
		setItem(inv, 30, "§aVillager MHM", Material.EMERALD);
		setItem(inv, 31, "§aWither", Material.ENDER_PORTAL_FRAME);
		setItem(inv, 32, "§aLevel Up", Material.EXP_BOTTLE);
		setItem(inv, 33, "§aZombie Destroy", Material.ROTTEN_FLESH);
		
		p.openInventory(inv);
	}
	
	private static void setItem(Inventory inv, int slot, String name, Material mat) {
		ItemStack item = new ItemStack(mat, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		List<String> lore = new ArrayList<>();
		lore.add("§bLeft click to select");
		lore.add("§bRight click to preview");
		meta.setLore(lore);
        item.setItemMeta(meta);
		inv.setItem(slot, item);
	}
	
	public static void getValue(int slot, Player p, boolean open) {
		QuakePlayer qp = QuakePlayer.getFromPlayer(p);
		if (qp == null) return;

		switch (slot) {
			case 11: qp.getGunProfile().setSound(Sound.BLAZE_DEATH).setPitch(2); qp.savePlayerData("sound", slot); if (open) CustomMenu.open(p); break;
			case 12: qp.getGunProfile().setSound(Sound.HORSE_DEATH).setPitch(2); qp.savePlayerData("sound", slot); if (open) CustomMenu.open(p); break;
			case 13: qp.getGunProfile().setSound(Sound.BAT_DEATH).setPitch(2); qp.savePlayerData("sound", slot); if (open) CustomMenu.open(p); break;
			case 14: qp.getGunProfile().setSound(Sound.ENDERMAN_DEATH).setPitch(2); qp.savePlayerData("sound", slot); if (open) CustomMenu.open(p); break;
			case 15: qp.getGunProfile().setSound(Sound.IRONGOLEM_DEATH).setPitch(2); qp.savePlayerData("sound", slot); if (open) CustomMenu.open(p); break;
			case 20: qp.getGunProfile().setSound(Sound.PIG_DEATH).setPitch(2); qp.savePlayerData("sound", slot); if (open) CustomMenu.open(p); break;
			case 21: qp.getGunProfile().setSound(Sound.COW_HURT).setPitch(1); qp.savePlayerData("sound", slot); if (open) CustomMenu.open(p); break;
			case 22: qp.getGunProfile().setSound(Sound.CREEPER_DEATH).setPitch(1); qp.savePlayerData("sound", slot); if (open) CustomMenu.open(p); break;
			case 23: qp.getGunProfile().setSound(Sound.ANVIL_LAND).setPitch(1); qp.savePlayerData("sound", slot); if (open) CustomMenu.open(p); break;
			case 24: qp.getGunProfile().setSound(Sound.GHAST_DEATH).setPitch(2); qp.savePlayerData("sound", slot); if (open) CustomMenu.open(p); break;
			case 29: qp.getGunProfile().setSound(Sound.ENDERDRAGON_GROWL).setPitch(1); qp.savePlayerData("sound", slot); if (open) CustomMenu.open(p); break;
			case 30: qp.getGunProfile().setSound(Sound.VILLAGER_IDLE).setPitch(1); qp.savePlayerData("sound", slot); if (open) CustomMenu.open(p); break;
			case 31: qp.getGunProfile().setSound(Sound.WITHER_IDLE).setPitch(1); qp.savePlayerData("sound", slot); if (open) CustomMenu.open(p); break;
			case 32: qp.getGunProfile().setSound(Sound.LEVEL_UP).setPitch(1); qp.savePlayerData("sound", slot); if (open) CustomMenu.open(p); break;
			case 33: qp.getGunProfile().setSound(Sound.ZOMBIE_WOODBREAK).setPitch(1); qp.savePlayerData("sound", slot); if (open) CustomMenu.open(p); break;
			default: break;
		}
	}
	
	public static void preview(int slot, Player p) {
		switch (slot) {
			case 11: p.playSound(p.getLocation(), Sound.BLAZE_DEATH, 1, 2); break;
			case 12: p.playSound(p.getLocation(), Sound.HORSE_DEATH, 1, 2); break;
			case 13: p.playSound(p.getLocation(), Sound.BAT_DEATH, 1, 2); break;
			case 14: p.playSound(p.getLocation(), Sound.ENDERMAN_DEATH, 1, 2); break;
			case 15: p.playSound(p.getLocation(), Sound.IRONGOLEM_DEATH, 1, 2); break;
			case 20: p.playSound(p.getLocation(), Sound.PIG_DEATH, 1, 2); break;
			case 21: p.playSound(p.getLocation(), Sound.COW_HURT, 1, 1); break;
			case 22: p.playSound(p.getLocation(), Sound.CREEPER_DEATH, 1, 1); break;
			case 23: p.playSound(p.getLocation(), Sound.ANVIL_LAND, 1, 1); break;
			case 24: p.playSound(p.getLocation(), Sound.GHAST_DEATH, 1, 2); break;
			case 29: p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 1, 1); break;
			case 30: p.playSound(p.getLocation(), Sound.VILLAGER_IDLE, 1, 1); break;
			case 31: p.playSound(p.getLocation(), Sound.WITHER_IDLE, 1, 1); break;
			case 32: p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1); break;
			case 33: p.playSound(p.getLocation(), Sound.ZOMBIE_WOODBREAK, 1, 1); break;
			default: break;
		}
	}
	
}
