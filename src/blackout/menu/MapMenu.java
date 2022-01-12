package blackout.menu;

import java.io.File;
import java.util.ArrayList;
import java.util.Set;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import blackout.quake.core.Core;
import blackout.quake.main.Main;

public class MapMenu {
	
	public static void open(Player p) {
		Inventory inv = Main.getPlugin(Main.class).getServer().createInventory(null, 54, "Map Selector");
		
		setItem(inv, Material.WOOL, (byte) 14, "§6Apex", 0);
		setItem(inv, Material.WOOL, (byte) 14, "§6Apex II", 1);
		setItem(inv, Material.WOOL, (byte) 14, "§6Apex III", 2);
		setItem(inv, Material.WOOL, (byte) 14, "§bApex IV", 3);
		setItem(inv, Material.WOOL, (byte) 14, "§bApexSH", 4);
		setItem(inv, Material.STONE, (byte) 2, "§6Ancient", 5);
		setItem(inv, Material.STONE, (byte) 1, "§bOld Ancient", 6);
		setItem(inv, Material.SANDSTONE, (byte) 2, "§6Ascended", 7);
		setItem(inv, Material.ENDER_STONE, (byte) 0, "§6Belmorn", 8);
		setItem(inv, Material.ENDER_STONE, (byte) 0, "§bBelmorn2", 9);
		setItem(inv, Material.ENDER_STONE, (byte) 0, "§bBelmorn2 Edit", 10);
		setItem(inv, Material.LOG, (byte) 1, "§bBlackwood", 11);
		setItem(inv, Material.SMOOTH_BRICK, (byte) 0, "§bBowel", 12);
		setItem(inv, Material.NOTE_BLOCK, (byte) 0, "§bClassic Lobby 2", 13);
		setItem(inv, Material.ICE, (byte) 0, "§6Cold War", 14);
		setItem(inv, Material.NETHERRACK, (byte) 0, "§bHot War", 15);
		setItem(inv, Material.NETHER_BRICK, (byte) 0, "§6Demonic", 16);
		setItem(inv, Material.PACKED_ICE, (byte) 0, "§bFrostmonic", 17);
		setItem(inv, Material.STONE, (byte) 6, "§6Depths", 18);
		setItem(inv, Material.COBBLESTONE, (byte) 0, "§6DigSite", 19);
		setItem(inv, Material.COBBLESTONE, (byte) 0, "§6DigSite2", 20);
		setItem(inv, Material.SANDSTONE, (byte) 1, "§6Faarah", 21);
		setItem(inv, Material.SANDSTONE, (byte) 1, "§bFaarah Edit", 22);
		setSkullItem(inv, Material.SKULL_ITEM, (byte) 3, "§bFlat", 23, "Hannd");
		setItem(inv, Material.WOOD, (byte) 3, "§6Forgotten", 24);
		setItem(inv, Material.SNOW_BLOCK, (byte) 0, "§6Fryst", 25);
		setItem(inv, Material.LOG, (byte) 0, "§6HustWood", 26);
		setItem(inv, Material.LOG, (byte) 0, "§bHustWood Edit", 27);
		setItem(inv, Material.LOG, (byte) 0, "§bOld HustWood", 28);
		setItem(inv, Material.STAINED_CLAY, (byte) 7, "§6Karunesh", 29);
		setItem(inv, Material.QUARTZ_BLOCK, (byte) 1, "§bLobby 1", 30);
		setItem(inv, Material.QUARTZ_BLOCK, (byte) 1, "§bLobby 2", 31);
		setItem(inv, Material.SMOOTH_BRICK, (byte) 2, "§6Lost World", 32);
		setItem(inv, Material.SMOOTH_BRICK, (byte) 2, "§bOld Lost World", 33);
		setItem(inv, Material.DARK_OAK_DOOR_ITEM, (byte) 0, "§bMansion", 34);
		setItem(inv, Material.STAINED_GLASS, (byte) 5, "§6Martian", 35);
		setItem(inv, Material.STAINED_GLASS, (byte) 13, "§bOld Martian", 36);
		setItem(inv, Material.DIAMOND_ORE, (byte) 0, "§6Mines", 37);
		setItem(inv, Material.IRON_ORE, (byte) 0, "§bOld Mines", 38);
		setItem(inv, Material.RED_ROSE, (byte) 1, "§bOrchid", 39);
		setItem(inv, Material.STAINED_GLASS, (byte) 2, "§6Reactor", 40);
		setItem(inv, Material.STAINED_GLASS, (byte) 10, "§bBeta Reactor", 41);
		setItem(inv, Material.SMOOTH_BRICK, (byte) 1, "§bRuin", 42);
		setItem(inv, Material.PRISMARINE, (byte) 0, "§6Sunken", 43);
		setItem(inv, Material.PRISMARINE, (byte) 1, "§bBeta Sunken", 44);
		setItem(inv, Material.LOG, (byte) 3, "§bThe Devil Castle", 45);
		setItem(inv, Material.BRICK, (byte) 0, "§6Town", 46);
		setItem(inv, Material.BRICK, (byte) 0, "§bTown Edit", 47);
		setItem(inv, Material.WOOD, (byte) 0, "§6WoodStone", 48);
		setItem(inv, Material.WOOD, (byte) 0, "§bOld WoodStone", 49);
		
		p.openInventory(inv);
	}
	
	private static void setSkullItem(Inventory inv, Material mat, byte data, String name, int slot, String owner) {
		YamlConfiguration config = YamlConfiguration.loadConfiguration(new File("./plugins/Quake/"+name.substring(2)+".yml"));
		Set<String> respawns = config.getConfigurationSection("loc").getKeys(false);
		
		ItemStack item = new ItemStack(mat, 1, data);
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
		lore.add("§7This world contains §e"+respawns.size());
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(slot, item);
	}
	
	public static void getValue(String name) {
		Core.startGame(name.substring(2));
	}
	
}
