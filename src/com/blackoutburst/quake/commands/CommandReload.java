package com.blackoutburst.quake.commands;

import com.blackoutburst.quake.core.Utils;
import org.bukkit.command.CommandSender;

public class CommandReload {

	public void execute(CommandSender sender) {
		Utils.loadConfig();
		sender.sendMessage("§bConfig reloaded successfully!");
	}
	
}
