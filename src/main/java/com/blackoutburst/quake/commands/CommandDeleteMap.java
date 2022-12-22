package com.blackoutburst.quake.commands;

import com.blackoutburst.quake.core.Core;
import com.blackoutburst.quake.main.Main;
import org.bukkit.command.CommandSender;

import java.io.File;
import java.nio.file.Files;

public class CommandDeleteMap {

	public void execute(CommandSender sender, String[] args) {
		if (args.length == 0) {
			sender.sendMessage("§cInvalid usage try §e/deletemap <worldName>");
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
		File toRemove = null;

		if (entries == null) return;

		for(File s: entries) {
			if (s.getName().replace(".yml", "").equalsIgnoreCase(worldName.toString())) {
				finalWorldName = s.getName().replace(".yml", "");
				toRemove = s;
			}
		}
		
		if (finalWorldName == null) {
			sender.sendMessage("§cThe world §f"+worldName+"§c doesn't exist!");
			return;
		}

		if (toRemove.delete())
			sender.sendMessage("§bDeleted §6"+finalWorldName+" §bsuccessfully");
		else
			sender.sendMessage("§cUnable to delete this world!");
	}
}
