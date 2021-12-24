package blackout.menu;

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
        item.setItemMeta(meta);
		inv.setItem(slot, item);
	}
	
	public static void getValue(int slot, Player p, boolean open) {
		QuakePlayer qp = QuakePlayer.getFromPlayer(p);
		
		switch (slot) {
			case 11: qp.getGunProfile().setSound(Sound.BLAZE_DEATH).setPitch(2); qp.savePlayerData("sound", slot); if (open) CustomMenu.open(p); break;
			case 12: qp.getGunProfile().setSound(Sound.HORSE_DEATH).setPitch(2); qp.savePlayerData("sound", slot); if (open) CustomMenu.open(p); break;
			case 13: qp.getGunProfile().setSound(Sound.BAT_DEATH).setPitch(2); qp.savePlayerData("sound", slot); if (open) CustomMenu.open(p); break;
			case 14: qp.getGunProfile().setSound(Sound.ENDERMAN_DEATH).setPitch(2); qp.savePlayerData("sound", slot); if (open) CustomMenu.open(p); break;
			case 15: qp.getGunProfile().setSound(Sound.IRONGOLEM_DEATH).setPitch(2); qp.savePlayerData("sound", slot); if (open) CustomMenu.open(p); break;
			case 20: qp.getGunProfile().setSound(Sound.PIG_DEATH).setPitch(2); qp.savePlayerData("sound", slot); if (open) CustomMenu.open(p); break;
			case 21: qp.getGunProfile().setSound(Sound.COW_HURT).setPitch(2); qp.savePlayerData("sound", slot); if (open) CustomMenu.open(p); break;
			case 22: qp.getGunProfile().setSound(Sound.CREEPER_DEATH).setPitch(1); qp.savePlayerData("sound", slot); if (open) CustomMenu.open(p); break;
			case 23: qp.getGunProfile().setSound(Sound.ANVIL_LAND).setPitch(1); qp.savePlayerData("sound", slot); if (open) CustomMenu.open(p); break;
			case 24: qp.getGunProfile().setSound(Sound.GHAST_DEATH).setPitch(2); qp.savePlayerData("sound", slot); if (open) CustomMenu.open(p); break;
			case 29: qp.getGunProfile().setSound(Sound.ENDERDRAGON_GROWL).setPitch(1); qp.savePlayerData("sound", slot); if (open) CustomMenu.open(p); break;
			case 30: qp.getGunProfile().setSound(Sound.VILLAGER_IDLE).setPitch(1); qp.savePlayerData("sound", slot); if (open) CustomMenu.open(p); break;
			case 31: qp.getGunProfile().setSound(Sound.WITHER_IDLE).setPitch(1); qp.savePlayerData("sound", slot); if (open) CustomMenu.open(p); break;
			case 32: qp.getGunProfile().setSound(Sound.LEVEL_UP).setPitch(1); qp.savePlayerData("sound", slot); if (open) CustomMenu.open(p); break;
			case 33: qp.getGunProfile().setSound(Sound.ZOMBIE_WOODBREAK).setPitch(1); qp.savePlayerData("sound", slot); if (open) CustomMenu.open(p); break;
			default: return;
		}
	}
	
}
