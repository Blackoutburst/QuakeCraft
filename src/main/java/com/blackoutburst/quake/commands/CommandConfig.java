package com.blackoutburst.quake.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.blackoutburst.quake.menu.ConfigMenu;

public class CommandConfig {

	public void execute(CommandSender sender) {
		if (sender instanceof Player)
			ConfigMenu.open((Player)sender);
	}
}
