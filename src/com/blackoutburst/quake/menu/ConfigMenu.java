package com.blackoutburst.quake.menu;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.blackoutburst.quake.commands.CommandResetConfig;
import com.blackoutburst.quake.commands.CommandToggleBlindness;
import com.blackoutburst.quake.commands.CommandToggleDash;
import com.blackoutburst.quake.commands.CommandToggleInvisibility;
import com.blackoutburst.quake.commands.CommandToggleJump;
import com.blackoutburst.quake.commands.CommandToggleNametag;
import com.blackoutburst.quake.commands.CommandToggleVerticalDash;
import com.blackoutburst.quake.commands.CommandToggleWalk;
import com.blackoutburst.quake.core.GameOption;
import com.blackoutburst.quake.core.Utils;
import com.blackoutburst.quake.main.Main;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;

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
		
		item = new ItemStack(Material.BLAZE_POWDER, 1);
		meta = item.getItemMeta();
		meta.setDisplayName("§bDash strength");
		lore = new ArrayList<>();
		lore.add("§7Click to change the dash strength");
		lore.add("§aCurrent dash strength: §6"+GameOption.DASH_STRENGTH);
		lore.add("");
		lore.add("§7You can also use §e/dashstrength");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(13, item);
		
		item = new ItemStack(Material.SUGAR, 1);
		meta = item.getItemMeta();
		meta.setDisplayName("§bSpeed");
		lore = new ArrayList<>();
		lore.add("§7Click to change the player speed");
		lore.add("§aCurrent player speed: §6"+GameOption.PLAYER_SPEED);
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
		lore.add("");
		lore.add("§7You can also use §e/playerslow");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(16, item);	
		
		/////////////////////////////
		///////// 2nd row ///////////
		/////////////////////////////
		
		item = new ItemStack(Material.SLIME_BALL, 1);
		meta = item.getItemMeta();
		meta.setDisplayName("§bBounces");
		lore = new ArrayList<>();
		lore.add("§7Click to change the number of bounces");
		lore.add("§aCurrent ray bounces: §6"+GameOption.BOUNCE_COUNT);
		lore.add("");
		lore.add("§7You can also use §e/bouncecount");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(20, item);	
		
		item = new ItemStack(Material.LEVER, 1);
		meta = item.getItemMeta();
		meta.setDisplayName("§bRay length");
		lore = new ArrayList<>();
		lore.add("§7Click to change the ray max length");
		lore.add("§aCurrent ray length: §6"+GameOption.RAY_LENGTH);
		lore.add("");
		lore.add("§7You can also use §e/raylength");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(21, item);	
		
		item = new ItemStack(Material.SAND, 1);
		meta = item.getItemMeta();
		meta.setDisplayName("§bGravity");
		lore = new ArrayList<>();
		lore.add("§7Click to change the gravity strength");
		lore.add("§aCurrent ray length: §6"+GameOption.GRAVITY);
		lore.add("");
		lore.add("§7You can also use §e/gravity");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(22, item);	
		
		item = new ItemStack(Material.PRISMARINE_SHARD, 1);
		meta = item.getItemMeta();
		meta.setDisplayName("§bShatter count");
		lore = new ArrayList<>();
		lore.add("§7Click to change the number of shatter ray");
		lore.add("§aCurrent shatter: §6"+GameOption.SHATTER_COUNT);
		lore.add("");
		lore.add("§7You can also use §e/shattercount");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(23, item);	
		
		item = new ItemStack(Material.REDSTONE_TORCH_ON, 1);
		meta = item.getItemMeta();
		meta.setDisplayName("§bShatter length");
		lore = new ArrayList<>();
		lore.add("§7Click to change the length of shatter ray");
		lore.add("§aCurrent shatter ray length: §6"+GameOption.SHATTER_LENGTH);
		lore.add("");
		lore.add("§7You can also use §e/shatterlength");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(24, item);	
		
		/////////////////////////////
		///////// 3rd row ///////////
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
		
		item = new ItemStack(Material.STRING, 1);
		meta = item.getItemMeta();
		meta.setDisplayName("§bVertical dash");
		lore = new ArrayList<>();
		lore.add("§7Toggle vertical dash");
		lore.add("");
		lore.add("§7You can also use §e/toggleverticaldash");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(31, item);	
		
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
		///////// 4th row ///////////
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
		
		item = new ItemStack(Material.INK_SACK, 1, (byte) (GameOption.VERTICAL_DASH ? 10 : 8));
		meta = item.getItemMeta();
		meta.setDisplayName("§bVertical dash");
		lore = new ArrayList<>();
		lore.add("§7Toggle vertical dash");
		lore.add("");
		lore.add("§7You can also use §e/toggleverticaldash");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(40, item);	
		
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
		///////// 5th row ///////////
		/////////////////////////////
		
		item = new ItemStack(Material.NETHER_STAR, 1);
		meta = item.getItemMeta();
		meta.setDisplayName("§aCurrent configuration");
		lore = new ArrayList<>();
		lore.add("§6Trigger: §e"+GameOption.FIRE_DELAY/20+"s");
		lore.add("§6Dash: §e"+GameOption.DASH_DELAY/20+"s");
		lore.add("§6Max score: §e"+GameOption.MAX_SCORE);
		lore.add("§6Dash strength: §e"+GameOption.DASH_STRENGTH);
		lore.add("§6Speed: §e"+GameOption.PLAYER_SPEED);
		lore.add("§6Jump: §e"+GameOption.JUMP_BOOST);
		lore.add("§6Slow: §e"+GameOption.SLOWNESS);
		lore.add("§6Bounces: §e"+GameOption.BOUNCE_COUNT);
		lore.add("§6Ray length: §e"+GameOption.RAY_LENGTH);
		lore.add("§6Shatter count: §e"+GameOption.SHATTER_COUNT);
		lore.add("§6Shatter length: §e"+GameOption.SHATTER_LENGTH);
		lore.add("§6Gravity: §e"+GameOption.GRAVITY);
		lore.add("");
		lore.add("§6Blindness: "+(GameOption.BLINDNESS ? "§aYes" : "§cNo"));
		lore.add("§6Invisibility: "+(GameOption.INVISIBILITY ? "§aYes" : "§cNo"));
		lore.add("§6NameTag: "+(GameOption.NAMETAG ? "§aYes" : "§cNo"));
		lore.add("§6Vertical dash: §e"+(GameOption.VERTICAL_DASH ? "§aYes" : "§cNo"));
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
		inv.setItem(49, item);	
		
		item = new ItemStack(Material.BARRIER, 1);
		meta = item.getItemMeta();
		meta.setDisplayName("§cClose menu");
		item.setItemMeta(meta);
		inv.setItem(53, item);
		
		p.openInventory(inv);
	}
	
	private static void toggle(boolean isEnabled, Inventory inv, List<String> lore, String name, int slot) {
		ItemStack item = new ItemStack(Material.INK_SACK, 1, (byte) (isEnabled ? 10 : 8));
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);
        inv.setItem(slot, item);
        
        item = new ItemStack(Material.NETHER_STAR, 1);
		meta = item.getItemMeta();
		meta.setDisplayName("§aCurrent configuration");
		lore = new ArrayList<>();
		lore.add("§6Trigger: §e"+GameOption.FIRE_DELAY/20+"s");
		lore.add("§6Dash: §e"+GameOption.DASH_DELAY/20+"s");
		lore.add("§6Max score: §e"+GameOption.MAX_SCORE);
		lore.add("§6Dash strength: §e"+GameOption.DASH_STRENGTH);
		lore.add("§6Speed: §e"+GameOption.PLAYER_SPEED);
		lore.add("§6Jump: §e"+GameOption.JUMP_BOOST);
		lore.add("§6Slow: §e"+GameOption.SLOWNESS);
		lore.add("§6Bounces: §e"+GameOption.BOUNCE_COUNT);
		lore.add("§6Ray length: §e"+GameOption.RAY_LENGTH);
		lore.add("§6Shatter count: §e"+GameOption.SHATTER_COUNT);
		lore.add("§6Shatter length: §e"+GameOption.SHATTER_LENGTH);
		lore.add("§6Gravity: §e"+GameOption.GRAVITY);
		lore.add("");
		lore.add("§6Blindness: "+(GameOption.BLINDNESS ? "§aYes" : "§cNo"));
		lore.add("§6Invisibility: "+(GameOption.INVISIBILITY ? "§aYes" : "§cNo"));
		lore.add("§6NameTag: "+(GameOption.NAMETAG ? "§aYes" : "§cNo"));
		lore.add("§6Vertical dash: §e"+(GameOption.VERTICAL_DASH ? "§aYes" : "§cNo"));
		lore.add("§6Dash: "+(GameOption.DASH ? "§aYes" : "§cNo"));
		lore.add("§6Walk: "+(GameOption.WALK ? "§aYes" : "§cNo"));
		lore.add("§6Jump: "+(GameOption.JUMP ? "§aYes" : "§cNo"));
		lore.add("");
		lore.add("§7You can also use §e/showconfig");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(45, item);
	}
	
	private static void commandMessage(String commandName, float value, Player p) {
		TextComponent msg = new TextComponent(Utils.centerText("§6Use §b/" + commandName + " §r[value]"));
		msg.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/" + commandName + " " + value));
		
		p.closeInventory();
		p.sendMessage("§a§l§m---------------------------------------------");
		p.sendMessage(" ");
		p.spigot().sendMessage(msg);
		msg = new TextComponent(Utils.centerText("§e(Click me to copy the command)"));
		msg.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/" + commandName + " " + value));
		p.spigot().sendMessage(msg);
		p.sendMessage(" ");
		p.sendMessage("§a§l§m---------------------------------------------");
	}
	
	public static void getValue(int slot, Player p, Inventory inv) {
		switch (slot) {
			case 10: commandMessage("triggerspeed", GameOption.FIRE_DELAY/20, p); break;
			case 11: commandMessage("dashdelay", GameOption.DASH_DELAY/20, p); break;
			case 12: commandMessage("maxscore", GameOption.MAX_SCORE, p); break;
			case 13: commandMessage("dashstrength", GameOption.DASH_STRENGTH, p); break;
			case 14: commandMessage("playerspeed", GameOption.PLAYER_SPEED, p); break;
			case 15: commandMessage("playerjump", GameOption.JUMP_BOOST, p); break;
			case 16: commandMessage("playerslow", GameOption.SLOWNESS, p); break;
			case 20: commandMessage("bouncecount", GameOption.BOUNCE_COUNT, p); break;
			case 21: commandMessage("raylength", GameOption.RAY_LENGTH, p); break;
			case 22: commandMessage("gravity", GameOption.GRAVITY, p); break;
			case 23: commandMessage("shattercount", GameOption.SHATTER_COUNT, p); break;
			case 24: commandMessage("shatterlength", GameOption.SHATTER_LENGTH, p); break;
			case 37: case 28: CommandToggleBlindness.run(p); 
				List<String> lore = new ArrayList<>();
				lore.add("§7Toggle blindness");
				lore.add("");
				lore.add("§7You can also use §e/toggleblindness");
				toggle(GameOption.BLINDNESS, inv, lore, "§bBlindness", 37);
			break;
			case 38: case 29: CommandToggleInvisibility.run(p); 
				lore = new ArrayList<>();
				lore.add("§7Toggle invisibility");
				lore.add("");
				lore.add("§7You can also use §e/toggleinvisibility");
				toggle(GameOption.INVISIBILITY, inv, lore, "§bInvisibility", 38);
			break;
			case 39: case 30: CommandToggleNametag.run(p); 
				lore = new ArrayList<>();
				lore.add("§7Toggle nametag");
				lore.add("");
				lore.add("§7You can also use §e/togglenametag");
				toggle(GameOption.NAMETAG, inv, lore, "§bNametag", 39);
			break;
				case 40: case 31: CommandToggleVerticalDash.run(p); 
				lore = new ArrayList<>();
				lore.add("§7Toggle vertical dash");
				lore.add("");
				lore.add("§7You can also use §e/toggleverticaldash");
				toggle(GameOption.VERTICAL_DASH, inv, lore, "§bVertical dash", 40);
			break;
			case 41: case 32: CommandToggleDash.run(p); 
				lore = new ArrayList<>();
				lore.add("§7Toggle dash");
				lore.add("");
				lore.add("§7You can also use §e/toggledash");
				toggle(GameOption.DASH, inv, lore, "§bDash", 41);
			break;
			case 42: case 33: CommandToggleWalk.run(p); 
				lore = new ArrayList<>();
				lore.add("§7Toggle walk");
				lore.add("");
				lore.add("§7You can also use §e/togglewalk");
				toggle(GameOption.WALK, inv, lore, "§bWalk", 42);
			break;
			case 43: case 34: CommandToggleJump.run(p); 
				lore = new ArrayList<>();
				lore.add("§7Toggle jump");
				lore.add("");
				lore.add("§7You can also use §e/togglejump");
				toggle(GameOption.JUMP, inv, lore, "§bJump", 43);
			break;
			case 49: CommandResetConfig.run(p); open(p); break;
			case 53: p.closeInventory(); break;
			default: break;
		}
	}
	
}
