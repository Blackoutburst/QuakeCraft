package com.blackoutburst.quake.commands;

import java.io.File;
import java.util.Random;

import org.bukkit.command.CommandSender;

import com.blackoutburst.quake.core.Core;
import com.blackoutburst.quake.main.Main;

public class CommandStart {

	public void execute(CommandSender sender, String[] args) {
		if (args.length == 0) {
			sender.sendMessage("§cYou must specifie the world name!");
			return;
			
		}
		
		if (Main.gameRunning) {
			sender.sendMessage("§cYou must end the current game with §e/end§c before starting a new one!");
			return;
		}
		
		StringBuilder worldName = new StringBuilder();
		
		for (String s : args) {
			worldName.append(s).append(" ");
		}
		
		worldName = new StringBuilder(worldName.substring(0, worldName.length() - 1).toLowerCase());
		
		if (worldName.toString().equals("random")) {
			File index = new File("./plugins/Quake/");
			File[] entries = index.listFiles();
			
			Core.startGame(entries[new Random().nextInt(entries.length)].getName().replace(".yml", ""));
			
			return;
		}
		
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
		
		Core.startGame(finalWorldName);

	}
	
}
