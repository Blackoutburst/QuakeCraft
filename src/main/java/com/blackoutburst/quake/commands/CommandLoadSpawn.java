package com.blackoutburst.quake.commands;

import com.blackoutburst.quake.core.Core;
import com.blackoutburst.quake.main.Main;
import org.bukkit.command.CommandSender;

import java.io.File;

public class CommandLoadSpawn {

	public void execute(CommandSender sender, String[] args) {
		if (args.length == 0) {
			sender.sendMessage("§cInvalid usage try §e/loadspawn <worldName>");
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
		sender.sendMessage("§bLoaded the §6"+Main.respawns.size()+" §bspawnpoints found in §6"+finalWorldName);
	}
}
