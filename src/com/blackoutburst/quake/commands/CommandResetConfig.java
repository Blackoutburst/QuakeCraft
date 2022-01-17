package com.blackoutburst.quake.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.blackoutburst.quake.core.GameOption;

public class CommandResetConfig {

	public void execute(CommandSender sender) {
		Player p = (Player) sender;
		run(p);
	}
	
	public static void run(Player p) {
		Bukkit.broadcastMessage(p.getDisplayName()+" §bhas restored the default configuration");
		GameOption.restoreConfiguration();
	}
}
