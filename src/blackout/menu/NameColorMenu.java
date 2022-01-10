package blackout.menu;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import blackout.quake.core.Core;
import blackout.quake.core.QuakePlayer;
import blackout.quake.main.Main;

public class NameColorMenu {
	
	public static void open(Player p) {
		Inventory inv = Main.getPlugin(Main.class).getServer().createInventory(null, 36, "Name Color Menu");
		
		setItem(inv, 10, (byte)1, "§4Dark Red");
		setItem(inv, 11, (byte)9, "§cRed");
		setItem(inv, 12, (byte)14, "§6Gold");
		setItem(inv, 13, (byte)11, "§eYellow");
		setItem(inv, 14, (byte)2, "§2Dark Green");
		setItem(inv, 15, (byte)10, "§aGreen");
		setItem(inv, 16, (byte)6, "§3Dark Aqua");
		setItem(inv, 19, (byte)4, "§1Dark Blue");
		setItem(inv, 20, (byte)12, "§9Blue");
		setItem(inv, 21, (byte)13, "§dLight Purple");
		setItem(inv, 22, (byte)5, "§5Dark Purple");
		setItem(inv, 23, (byte)15, "§fWhite");
		setItem(inv, 24, (byte)7, "§7Gray");
		setItem(inv, 25, (byte)8, "§8Dark Gray");
		
		p.openInventory(inv);
	}
	
	private static void setItem(Inventory inv, int slot, byte data, String name) {
		ItemStack item = new ItemStack(Material.BANNER, 1, data);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
        item.setItemMeta(meta);
		inv.setItem(slot, item);
	}
	
	public static void getValue(int slot, Player p, boolean open) {
		QuakePlayer qp = QuakePlayer.getFromPlayer(p);
		if (qp == null) return;

		switch (slot) {
			case 10: qp.getGunProfile().setNameColor(ChatColor.DARK_RED); qp.savePlayerData("nameColor", slot); Core.updateName(qp); if (open) CustomMenu.open(p); break;
			case 11: qp.getGunProfile().setNameColor(ChatColor.RED); qp.savePlayerData("nameColor", slot); Core.updateName(qp); if (open) CustomMenu.open(p); break;
			case 12: qp.getGunProfile().setNameColor(ChatColor.GOLD); qp.savePlayerData("nameColor", slot); Core.updateName(qp); if (open) CustomMenu.open(p); break;
			case 13: qp.getGunProfile().setNameColor(ChatColor.YELLOW); qp.savePlayerData("nameColor", slot); Core.updateName(qp); if (open) CustomMenu.open(p); break;
			case 14: qp.getGunProfile().setNameColor(ChatColor.DARK_GREEN); qp.savePlayerData("nameColor", slot); Core.updateName(qp); if (open) CustomMenu.open(p); break;
			case 15: qp.getGunProfile().setNameColor(ChatColor.GREEN); qp.savePlayerData("nameColor", slot); Core.updateName(qp); if (open) CustomMenu.open(p); break;
			case 16: qp.getGunProfile().setNameColor(ChatColor.DARK_AQUA); qp.savePlayerData("nameColor", slot); Core.updateName(qp); if (open) CustomMenu.open(p); break;
			case 19: qp.getGunProfile().setNameColor(ChatColor.DARK_BLUE); qp.savePlayerData("nameColor", slot); Core.updateName(qp); if (open) CustomMenu.open(p); break;
			case 20: qp.getGunProfile().setNameColor(ChatColor.BLUE); qp.savePlayerData("nameColor", slot); Core.updateName(qp); if (open) CustomMenu.open(p); break;
			case 21: qp.getGunProfile().setNameColor(ChatColor.LIGHT_PURPLE); qp.savePlayerData("nameColor", slot); Core.updateName(qp); if (open) CustomMenu.open(p); break;
			case 22: qp.getGunProfile().setNameColor(ChatColor.DARK_PURPLE); qp.savePlayerData("nameColor", slot); Core.updateName(qp); if (open) CustomMenu.open(p); break;
			case 23: qp.getGunProfile().setNameColor(ChatColor.WHITE); qp.savePlayerData("nameColor", slot); Core.updateName(qp); if (open) CustomMenu.open(p); break;
			case 24: qp.getGunProfile().setNameColor(ChatColor.GRAY); qp.savePlayerData("nameColor", slot); Core.updateName(qp); if (open) CustomMenu.open(p); break;
			case 25: qp.getGunProfile().setNameColor(ChatColor.DARK_GRAY); qp.savePlayerData("nameColor", slot); Core.updateName(qp); if (open) CustomMenu.open(p); break;
			default: break;
		}
	}
	
}
