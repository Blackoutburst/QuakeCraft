package com.blackoutburst.quake.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandPlay {

	public void execute(CommandSender sender) {
		if (sender instanceof Player) {
			sender.sendMessage("§cI'm sorry, this command is unavailable");
			//MapMenu.open((Player)sender);
		}
	}
}
