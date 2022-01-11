package blackout.menu;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import blackout.quake.core.GameOption;
import blackout.quake.core.QuakePlayer;
import blackout.quake.main.Main;

public class ConfigMenu {
	
	public static void open(Player p) {
		Inventory inv = Main.getPlugin(Main.class).getServer().createInventory(null, 54, "Game Configuration");
		
		/////////////////////////////
		///////// 1st row ///////////
		/////////////////////////////
		
		ItemStack item = new ItemStack(Material.IRON_HOE, 1);
		ItemMeta meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.values());
		meta.setDisplayName("§bTrigger speed");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7Click here to change the trigger speed");
		lore.add("§aCurrent speed: §6"+GameOption.FIRE_DELAY / 20+"s");
		lore.add("");
		lore.add("§7You can also use §e/triggerspeed");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(10, item);
		
		item = new ItemStack(Material.FEATHER, 1);
		meta = item.getItemMeta();
		meta.setDisplayName("§bDash delay");
		lore = new ArrayList<>();
		lore.add("§7Click to change the dash delay");
		lore.add("§aCurrent speed: §6"+GameOption.DASH_DELAY / 20+"s");
		lore.add("");
		lore.add("§7You can also use §e/dashdelay");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(11, item);
		
		item = new ItemStack(Material.DIAMOND, 1);
		meta = item.getItemMeta();
		meta.setDisplayName("§bMax score");
		lore = new ArrayList<>();
		lore.add("§7Click to change the max score");
		lore.add("§aCurrent max score: §6"+GameOption.MAX_SCORE);
		lore.add("");
		lore.add("§7You can also use §e/maxscore");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(12, item);
		
		item = new ItemStack(Material.SUGAR, 1);
		meta = item.getItemMeta();
		meta.setDisplayName("§bSpeed");
		lore = new ArrayList<>();
		lore.add("§7Click to change the player speed");
		lore.add("§aCurrent player speed: §6"+GameOption.PLAYER_SPEED);
		lore.add("§70 means disabled");
		lore.add("");
		lore.add("§7You can also use §e/playerspeed");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(14, item);
		
		item = new ItemStack(Material.RABBIT_FOOT, 1);
		meta = item.getItemMeta();
		meta.setDisplayName("§bJump boost");
		lore = new ArrayList<>();
		lore.add("§7Click to change the jump boost");
		lore.add("§aCurrent player jump boost: §6"+GameOption.JUMP_BOOST);
		lore.add("§70 means disabled");
		lore.add("");
		lore.add("§7You can also use §e/playerjump");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(15, item);		
		
		item = new ItemStack(Material.COAL, 1);
		meta = item.getItemMeta();
		meta.setDisplayName("§bSlowness");
		lore = new ArrayList<>();
		lore.add("§7Click to change the slowness");
		lore.add("§aCurrent player slowness: §6"+GameOption.SLOWNESS);
		lore.add("§70 means disabled");
		lore.add("");
		lore.add("§7You can also use §e/playerslow");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(16, item);	
		
		/////////////////////////////
		///////// 2nd row ///////////
		/////////////////////////////
		
		item = new ItemStack(Material.WOOL, 1, (byte) 15);
		meta = item.getItemMeta();
		meta.setDisplayName("§bBlindness");
		lore = new ArrayList<>();
		lore.add("§7Toggle blindness");
		lore.add("");
		lore.add("§7You can also use §e/toggleblindness");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(28, item);	
		
		item = new ItemStack(Material.GLASS, 1);
		meta = item.getItemMeta();
		meta.setDisplayName("§bInvisibility");
		lore = new ArrayList<>();
		lore.add("§7Toggle invisibility");
		lore.add("");
		lore.add("§7You can also use §e/toggleinvisibility");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(29, item);	
		
		item = new ItemStack(Material.NAME_TAG, 1);
		meta = item.getItemMeta();
		meta.setDisplayName("§bNametag");
		lore = new ArrayList<>();
		lore.add("§7Toggle nametag visibility");
		lore.add("");
		lore.add("§7You can also use §e/togglenametag");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(30, item);	
		
		item = new ItemStack(Material.FEATHER, 1);
		meta = item.getItemMeta();
		meta.setDisplayName("§bDash");
		lore = new ArrayList<>();
		lore.add("§7Toggle dash");
		lore.add("");
		lore.add("§7You can also use §e/toggledash");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(32, item);	
		
		item = new ItemStack(Material.SUGAR, 1);
		meta = item.getItemMeta();
		meta.setDisplayName("§bWalk");
		lore = new ArrayList<>();
		lore.add("§7Toggle walk");
		lore.add("");
		lore.add("§7You can also use §e/togglewalk");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(33, item);	
		
		item = new ItemStack(Material.RABBIT_FOOT, 1);
		meta = item.getItemMeta();
		meta.setDisplayName("§bJump");
		lore = new ArrayList<>();
		lore.add("§7Toggle jump");
		lore.add("");
		lore.add("§7You can also use §e/togglejump");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(34, item);	
		
		/////////////////////////////
		///////// 3rd row ///////////
		/////////////////////////////
		
		item = new ItemStack(Material.INK_SACK, 1, (byte) (GameOption.BLINDNESS ? 10 : 8));
		meta = item.getItemMeta();
		meta.setDisplayName("§bBlindness");
		lore = new ArrayList<>();
		lore.add("§7Toggle blindness");
		lore.add("");
		lore.add("§7You can also use §e/toggleblindness");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(37, item);	
		
		item = new ItemStack(Material.INK_SACK, 1, (byte) (GameOption.INVISIBILITY ? 10 : 8));
		meta = item.getItemMeta();
		meta.setDisplayName("§bInvisibility");
		lore = new ArrayList<>();
		lore.add("§7Toggle invisibility");
		lore.add("");
		lore.add("§7You can also use §e/toggleinvisibility");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(38, item);	
		
		item = new ItemStack(Material.INK_SACK, 1, (byte) (GameOption.NAMETAG ? 10 : 8));
		meta = item.getItemMeta();
		meta.setDisplayName("§bNametag");
		lore = new ArrayList<>();
		lore.add("§7Toggle nametag visibility");
		lore.add("");
		lore.add("§7You can also use §e/togglenametag");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(39, item);	
		
		item = new ItemStack(Material.INK_SACK, 1, (byte) (GameOption.DASH ? 10 : 8));
		meta = item.getItemMeta();
		meta.setDisplayName("§bDash");
		lore = new ArrayList<>();
		lore.add("§7Toggle dash");
		lore.add("");
		lore.add("§7You can also use §e/toggledash");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(41, item);	
		
		item = new ItemStack(Material.INK_SACK, 1, (byte) (GameOption.WALK ? 10 : 8));
		meta = item.getItemMeta();
		meta.setDisplayName("§bWalk");
		lore = new ArrayList<>();
		lore.add("§7Toggle walk");
		lore.add("");
		lore.add("§7You can also use §e/togglewalk");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(42, item);	
		
		item = new ItemStack(Material.INK_SACK, 1, (byte) (GameOption.JUMP ? 10 : 8));
		meta = item.getItemMeta();
		meta.setDisplayName("§bJump");
		lore = new ArrayList<>();
		lore.add("§7Toggle jump");
		lore.add("");
		lore.add("§7You can also use §e/togglejump");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(43, item);	
		
		/////////////////////////////
		///////// 4th row ///////////
		/////////////////////////////
		
		item = new ItemStack(Material.NETHER_STAR, 1);
		meta = item.getItemMeta();
		meta.setDisplayName("§aCurrent configuration");
		lore = new ArrayList<>();
		lore.add("§6Trigger: §e"+GameOption.FIRE_DELAY/20+"s");
		lore.add("§6Dash: §e"+GameOption.DASH_DELAY/20+"s");
		lore.add("§6Max score: §e"+GameOption.MAX_SCORE);
		lore.add("");
		lore.add("§6Speed: §e"+GameOption.PLAYER_SPEED);
		lore.add("§6Jump: §e"+GameOption.JUMP_BOOST);
		lore.add("§6Slow: §e"+GameOption.SLOWNESS);
		lore.add("");
		lore.add("§6Blindness: "+(GameOption.BLINDNESS ? "§aYes" : "§cNo"));
		lore.add("§6Invisibility: "+(GameOption.INVISIBILITY ? "§aYes" : "§cNo"));
		lore.add("§6NameTag: "+(GameOption.NAMETAG ? "§aYes" : "§cNo"));
		lore.add("");
		lore.add("§6Dash: "+(GameOption.DASH ? "§aYes" : "§cNo"));
		lore.add("§6Walk: "+(GameOption.WALK ? "§aYes" : "§cNo"));
		lore.add("§6Jump: "+(GameOption.JUMP ? "§aYes" : "§cNo"));
		lore.add("");
		lore.add("§7You can also use §e/showconfig");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(45, item);
		
		item = new ItemStack(Material.BEDROCK, 1);
		meta = item.getItemMeta();
		meta.setDisplayName("§4Reset configuration");
		lore = new ArrayList<>();
		lore.add("§7Restore all value");
		lore.add("");
		lore.add("§7You can also use §e/resetconfig");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(48, item);	
		
		item = new ItemStack(Material.BARRIER, 1);
		meta = item.getItemMeta();
		meta.setDisplayName("§cClose menu");
		item.setItemMeta(meta);
		inv.setItem(53, item);
		
		p.openInventory(inv);
	}
	
	public static void getValue(int slot, Player p) {
		QuakePlayer qp = QuakePlayer.getFromPlayer(p);
		if (qp == null) return;

		switch (slot) {
			case 11: break;
			default: break;
		}
	}
	
}
