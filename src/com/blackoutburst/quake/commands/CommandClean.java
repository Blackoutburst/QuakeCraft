package com.blackoutburst.quake.commands;

import java.io.File;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;

import com.blackoutburst.quake.core.Core;
import com.blackoutburst.quake.main.Main;

public class CommandClean {

	public void execute(CommandSender sender, String[] args) {
		if (args.length == 0) {
			sender.sendMessage("§cInvalid usage try §e/clean <worldName>");
			return;
		}
		
		StringBuilder worldName = new StringBuilder();
		
		for (String s : args) {
			worldName.append(s).append(" ");
		}
		
		worldName = new StringBuilder(worldName.substring(0, worldName.length() - 1).toLowerCase());
		
		File index = new File("./plugins/Quake/");
		File[] entries = index.listFiles();
		String finalWorldName = null;
		
		if (entries == null) return;

		for(File s: entries) {
			if (s.getName().replace(".yml", "").equalsIgnoreCase(worldName.toString())) {
				finalWorldName = s.getName().replace(".yml", "");
			}
		}
		
		if (finalWorldName == null) {
			sender.sendMessage("§cThe world §f"+worldName+"§c doesn't exist!");
			return;
		}
		
		Core.loadRespawn(finalWorldName);
		
		for (Location l : Main.respawns) {
            Block b = l.getWorld().getBlockAt(l);
            b.setType(Material.AIR);
        }
		sender.sendMessage("§bRemoved sponges on the §6"+Main.respawns.size()+" §bspawnpoints found in §6"+finalWorldName);
	}
}
