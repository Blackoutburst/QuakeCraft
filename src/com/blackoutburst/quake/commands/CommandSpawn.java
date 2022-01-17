package com.blackoutburst.quake.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.blackoutburst.quake.main.Main;

public class CommandSpawn {

	public void execute(CommandSender sender) {
		if (sender instanceof Player)
			((Player) sender).teleport(Main.spawn);
	}
}
