package com.blackoutburst.quake.commands;

import org.bukkit.command.CommandSender;

import com.blackoutburst.quake.core.Core;
import com.blackoutburst.quake.main.Main;

public class CommandEnd {

	public void execute(CommandSender sender) {
		if (Main.gameRunning)
			Core.endGame();
		else
			sender.sendMessage("§cNo game are running right now !");
	}
	
}
