package com.blackoutburst.quake.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.blackoutburst.quake.menu.MapMenu;

public class CommandPlay {

	public void execute(CommandSender sender) {
		if (sender instanceof Player)
			MapMenu.open((Player)sender);
	}
}
