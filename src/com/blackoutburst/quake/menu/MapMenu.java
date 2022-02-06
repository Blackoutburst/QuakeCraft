package com.blackoutburst.quake.menu;

import com.blackoutburst.quake.core.Core;
import com.blackoutburst.quake.main.Main;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.io.File;
import java.util.ArrayList;
import java.util.Set;

public class MapMenu {
	
	public static void open(Player p) {
		Inventory inv = Main.getPlugin(Main.class).getServer().createInventory(null, 54, "Map Selector (1/2)");
		
		setItem(inv, Material.STONE, (byte) 2, "§6Ancient", 0);
		setItem(inv, Material.WOOL, (byte) 14, "§6Apex", 1);
		setItem(inv, Material.WOOL, (byte) 14, "§6Apex II", 2);
		setItem(inv, Material.WOOL, (byte) 14, "§6Apex III", 3);
		setItem(inv, Material.WOOL, (byte) 14, "§bApex IV", 4);
		setItem(inv, Material.WOOL, (byte) 14, "§bApexSH", 5);
		setItem(inv, Material.SANDSTONE, (byte) 1, "§6Ascended", 6);
		setItem(inv, Material.ENDER_STONE, (byte) 0, "§6Belmorn", 7);
		setItem(inv, Material.ENDER_STONE, (byte) 0, "§bBelmorn2", 8);
		setItem(inv, Material.LOG, (byte) 1, "§bBlackwood", 9);
		setItem(inv, Material.SMOOTH_BRICK, (byte) 0, "§bBowel", 10);
		setItem(inv, Material.NOTE_BLOCK, (byte) 0, "§bClassic Lobby 2", 11);
		setItem(inv, Material.PACKED_ICE, (byte) 0, "§6Cold War", 12);
		setItem(inv, Material.NETHERRACK, (byte) 0, "§6Demonic", 13);
		setItem(inv, Material.STONE, (byte) 6, "§6Depths", 14);
		setItem(inv, Material.COBBLESTONE, (byte) 0, "§6DigSite", 15);
		setItem(inv, Material.GRAVEL, (byte) 0, "§6DigSite2", 16);
		setItem(inv, Material.GRAVEL, (byte) 0, "§bDigSite2 Flipped", 17);
		setItem(inv, Material.WOOD, (byte) 0, "§bDropper", 18);
		setItem(inv, Material.SMOOTH_BRICK, (byte) 1, "§bDropper II", 19);
		setItem(inv, Material.SMOOTH_BRICK, (byte) 2, "§bDungeon", 20);
		setItem(inv, Material.SANDSTONE, (byte) 2, "§6Faarah", 21);
		setItem(inv, Material.SANDSTONE, (byte) 2, "§bFaarahII", 22);
		setItem(inv, Material.SANDSTONE, (byte) 2, "§bFaarah Edit", 23);
		setSkullItem(inv,"§bFlat", "Hannd", 24);
		setItem(inv, Material.WOOD, (byte) 3, "§6Forgotten", 25);
		setItem(inv, Material.ICE, (byte) 0, "§bFrostmonic", 26);
		setItem(inv, Material.SNOW, (byte) 0, "§6Fryst", 27);
		setItem(inv, Material.LOG, (byte) 3, "§bHaikyo", 28);
		setItem(inv, Material.NETHER_BRICK, (byte) 0, "§bHot War", 29);
		setItem(inv, Material.LOG, (byte) 0, "§6HustWood", 30);
		setItem(inv, Material.LOG, (byte) 0, "§bHustWood Edit", 31);
		setItem(inv, Material.STAINED_CLAY, (byte) 7, "§6Karunesh", 32);
		setItem(inv, Material.BOOKSHELF, (byte) 0, "§bLibrary", 33);
		setItem(inv, Material.QUARTZ_BLOCK, (byte) 1, "§bLobby 1", 34);
		setItem(inv, Material.QUARTZ_BLOCK, (byte) 1, "§bLobby 2", 35);
		setItem(inv, Material.COBBLESTONE, (byte) 0, "§6Lost World", 36);
		setItem(inv, Material.WATER_LILY, (byte) 0, "§bLotus", 37);
		setItem(inv, Material.GOLD_BLOCK, (byte) 0, "§bLunar Lost World", 38);
		setItem(inv, Material.DARK_OAK_DOOR, (byte) 0, "§bMansion", 39);
		setItem(inv, Material.STAINED_GLASS, (byte) 5, "§6Martian", 40);
		setItem(inv, Material.STAINED_GLASS, (byte) 5, "§bMartian Flipped", 41);
		setItem(inv, Material.WOOL, (byte) 14, "§bMega Apex", 42);
		setItem(inv, Material.DIAMOND_ORE, (byte) 0, "§6Mines", 43);
		setItem(inv, Material.DIAMOND_ORE, (byte) 0, "§bMines Flipped", 44);

		ItemStack item = new ItemStack(Material.ARROW, 1);
		ItemMeta meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.values());
		meta.setDisplayName("§aNext page");
		item.setItemMeta(meta);
		inv.setItem(53, item);

		p.openInventory(inv);
	}

	public static void open2(Player p) {
		Inventory inv = Main.getPlugin(Main.class).getServer().createInventory(null, 54, "Map Selector (2/2)");

		setItem(inv, Material.STONE, (byte) 1, "§bOld Ancient", 0);
		setItem(inv, Material.LOG, (byte) 0, "§bOld HustWood", 1);
		setItem(inv, Material.COBBLESTONE, (byte) 0, "§bOld Lost World", 2);
		setItem(inv, Material.STAINED_GLASS, (byte) 13, "§bOld Martian", 3);
		setItem(inv, Material.IRON_ORE, (byte) 0, "§bOld Mines", 4);
		setItem(inv, Material.WOOD, (byte) 0, "§bOld WoodStone", 5);
		setItem(inv, Material.RED_ROSE, (byte) 1, "§bOrchid", 6);
		setItem(inv, Material.STAINED_CLAY, (byte) 15, "§bQuake City", 7);
		setItem(inv, Material.STAINED_GLASS, (byte) 2, "§6Reactor", 8);
		setItem(inv, Material.SMOOTH_BRICK, (byte) 1, "§bRuin", 9);
		setItem(inv, Material.SAND, (byte) 0, "§bSandstorm", 10);
		setItem(inv, Material.SMOOTH_BRICK, (byte) 3, "§6Sero", 11);
		setItem(inv, Material.SMOOTH_BRICK, (byte) 3, "§bSeroII", 12);
		setItem(inv, Material.QUARTZ_BLOCK, (byte) 2, "§bSilo", 13);
		setItem(inv, Material.SNOW, (byte) 0, "§bSnowglobe", 14);
		setItem(inv, Material.PRISMARINE, (byte) 2, "§6Sunken", 15);
		setItem(inv, Material.BRICK, (byte) 0, "§6Town", 16);
		setItem(inv, Material.STEP, (byte) 4, "§bTown Edit", 17);
		setItem(inv, Material.MINECART, (byte) 0, "§bTrain", 18);
		setItem(inv, Material.WOOL, (byte) 0, "§bWhiteroom", 19);
		setItem(inv, Material.WOOD, (byte) 0, "§6WoodStone", 20);

		ItemStack item = new ItemStack(Material.ARROW, 1);
		ItemMeta meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.values());
		meta.setDisplayName("§aPrevious page");
		item.setItemMeta(meta);
		inv.setItem(45, item);

		p.openInventory(inv);
	}

	private static void setSkullItem(Inventory inv, String name, String owner, int slot) {
		YamlConfiguration config = YamlConfiguration.loadConfiguration(new File("./plugins/Quake/"+name.substring(2)+".yml"));
		Set<String> respawns = config.getConfigurationSection("loc").getKeys(false);
		
		ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.addItemFlags(ItemFlag.values());
		meta.setOwner(owner);
		meta.setDisplayName(name);
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7Click me to start a game on "+name);
		lore.add("");
		lore.add("§7This world contains §e"+respawns.size());
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(slot, item);
	}
	
	private static void setItem(Inventory inv, Material mat, byte data, String name, int slot) {
		YamlConfiguration config = YamlConfiguration.loadConfiguration(new File("./plugins/Quake/"+name.substring(2)+".yml"));
		Set<String> respawns = config.getConfigurationSection("loc").getKeys(false);
		
		ItemStack item = new ItemStack(mat, 1, data);
		ItemMeta meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.values());
		meta.setDisplayName(name);
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7Click me to start a game on "+name);
		lore.add("");
		lore.add("§7This world contains §e"+respawns.size()+" §7spawnpoints");
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(slot, item);
	}
	
	public static void getValue(String name) {
		Core.startGame(name.substring(2));
	}
	
}
